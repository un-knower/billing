package com.ai.baas.bmc.service.business.impl;

import com.ai.baas.bmc.api.proferentialprocuct.params.*;
import com.ai.baas.bmc.constants.BmcConstants;
import com.ai.baas.bmc.constants.BmcConstants.ResultCode;
import com.ai.baas.bmc.constants.ExceptCodeConstant;
import com.ai.baas.bmc.dao.mapper.bo.CpFullPresent;
import com.ai.baas.bmc.dao.mapper.bo.CpFullReduce;
import com.ai.baas.bmc.dao.mapper.bo.CpPriceDetail;
import com.ai.baas.bmc.dao.mapper.bo.CpPriceInfo;
import com.ai.baas.bmc.service.business.interfaces.*;
import com.ai.baas.bmc.util.BmcSeqUtil;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


@Service
@Transactional
public class ProferProductManageBusiImpl implements IProferProductManageBusi {

	@Autowired
	private ICpPriceInfoBusi cpPriceInfoBusi;
	@Autowired
	private ICpPriceDetailBusi cpPriceDetailBusi;
	@Autowired
	private ICpFullPresentBusi cpFullPresentBusi;
	@Autowired
	private ICpFullReduceBusi cpFullReduceBusi;

	/**
	 * 满赠添加
	 */
	@Override
	public ProductResponse addProferProduct(ProferProductVO vo) {
		/**
		 * 插入资费信息表 cp_price_info
		 */
		String priceCode = BmcSeqUtil.getPriceCode();
		CpPriceInfo cpPriceInfo = new CpPriceInfo();
		cpPriceInfo.setPriceInfoId(BmcSeqUtil.getPriceInfoId());
		cpPriceInfo.setPriceCode(priceCode);
		cpPriceInfo.setCreateTime(DateUtil.getSysDate());
		cpPriceInfo.setActiveTime(vo.getActiveDate());
		cpPriceInfo.setComments(vo.getComments());
		cpPriceInfo.setOperatorId(vo.getOperatorId());
		cpPriceInfo.setInactiveTime(vo.getInvalidDate());
		cpPriceInfo.setPriceName(vo.getProgramName());
		// cpPriceInfo.setProductType(vo.getProductType()); 暂时不启用
		cpPriceInfo.setChargeType(vo.getProductType());
		cpPriceInfo.setTenantId(vo.getTenantId());
		cpPriceInfo.setActiveStatus(BmcConstants.ProferName.INACTIVE); // inoperative 待生效
		
		

		/**
		 * 插入资费计划明细表 cp_price_detail
		 */
		CpPriceDetail detail = new CpPriceDetail();
		detail.setPriceCode(priceCode);
		// detail.setActiveTime(vo.getActiveDate());
		String billType = vo.getProductType();
		detail.setChargeType(billType);
		detail.setComments(vo.getComments());
		String detailCode = BmcSeqUtil.getDetailCode();
		detail.setDetailCode(detailCode);
		detail.setDetailId(BmcSeqUtil.getDetailId());
		// 冗余字段，暂时不填
		// detail.setDetailName(vo.getProgramName());
		// detail.setInactiveTime(vo.getInvalidDate());
		detail.setPriceCode(priceCode);
		// 冗余字段，暂时不填
		// detail.setServiceType(vo.getProductType());

		detail.setActiveTime(DateUtil.getTimestamp("2015-1-1"));
		detail.setInactiveTime(DateUtil.getTimestamp("2030-1-1"));
		detail.setServiceType(vo.getProductType());
		
		
		//集中插入表的操作
		// TODO 有返回值，后期注意处理
		cpPriceInfoBusi.addCpPriceInfo(cpPriceInfo);
		cpPriceDetailBusi.addCpPriceDetail(detail);

		/**
		 * 插入满赠的详细表
		 */

		for (FullPresent p : vo.getPresentList()) {
			CpFullPresent present = new CpFullPresent();
			present.setPresentType(p.getGiftType());
			present.setActiveTime(p.getGiftActiveDate());
			present.setDetailCode(detailCode);
			present.setInactiveTime(p.getGiftInvalidDate());
			String presentCode = BmcSeqUtil.getPresentCode();
			present.setPresentCode(presentCode);
			present.setPresentId(BmcSeqUtil.getPresentId());
			present.setProductGiftIds(JSON.toJSONString(p.getGiftProList()));
			present.setProductIds(JSON.toJSONString(vo.getProductList()));
			present.setReachAmount(vo.getRuleAmount());
			present.setPresentAmount(p.getGitfAmount());
			present.setUnit(vo.getRuleUnit());
			present.setActiveCycle(p.getActiveCycle());
			present.setActiveFlag(p.getActiveFlag());
			cpFullPresentBusi.addFullPresent(present);
		}
		ProductResponse response = new ProductResponse();
		ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
		response.setResponseHeader(responseHeader);

		return response;
	}

	@Override
	public ProductResponse addDiscontProduct(ProferProductVO vo) throws BusinessException, SystemException {
		/**
		 * 插入资费信息表 cp_price_info
		 */
		String priceCode = BmcSeqUtil.getPriceCode();
		CpPriceInfo cpPriceInfo = new CpPriceInfo();
		cpPriceInfo.setPriceInfoId(BmcSeqUtil.getPriceInfoId());
		cpPriceInfo.setPriceCode(priceCode);
		cpPriceInfo.setCreateTime(DateUtil.getSysDate());
		cpPriceInfo.setActiveTime(vo.getActiveDate());
		cpPriceInfo.setComments(vo.getComments());
		cpPriceInfo.setOperatorId(vo.getOperatorId());
		cpPriceInfo.setInactiveTime(vo.getInvalidDate());
		cpPriceInfo.setPriceName(vo.getProgramName());
		//cpPriceInfo.setProductType(vo.getProductType());
		cpPriceInfo.setChargeType(vo.getProductType());
		cpPriceInfo.setTenantId(vo.getTenantId());
		cpPriceInfo.setActiveStatus(BmcConstants.ProferName.INACTIVE); // inoperative 待生效
		// TODO 有返回值，后期注意处理
		cpPriceInfoBusi.addCpPriceInfo(cpPriceInfo);

		/**
		 * 插入资费计划明细表 cp_price_detail
		 */
		CpPriceDetail detail = new CpPriceDetail();
		detail.setPriceCode(priceCode);
		// detail.setActiveTime(vo.getActiveDate());
		String billType = vo.getProductType();
		detail.setChargeType(billType);
		detail.setComments(vo.getComments());
		String detailCode = BmcSeqUtil.getDetailCode();
		detail.setDetailCode(detailCode);
		detail.setDetailId(BmcSeqUtil.getDetailId());
		// 设置缺省值
		detail.setActiveTime(DateUtil.getTimestamp("2015-1-1"));
		detail.setInactiveTime(DateUtil.getTimestamp("2030-1-1"));
		detail.setServiceType(vo.getProductType());
		// 冗余字段，暂时不填
		// detail.setDetailName(vo.getProgramName());
		// detail.setInactiveTime(vo.getInvalidDate());
		detail.setPriceCode(priceCode);
		// 冗余字段，暂时不填
		// detail.setServiceType(vo.getProductType());
		cpPriceDetailBusi.addCpPriceDetail(detail);

		/**
		 * 插入满减的详细表
		 */
		CpFullReduce reduce = new CpFullReduce();

		reduce.setActiveTime(vo.getActiveDate());
		reduce.setDetailCode(detailCode);
		reduce.setInactiveTime(vo.getInvalidDate());
		reduce.setProductIds(JSON.toJSONString(vo.getProductList()));
		reduce.setReachAmount(vo.getRuleAmount());
		
		 BigDecimal fz = new BigDecimal(vo.getReduceAmount());
         BigDecimal fm = new BigDecimal(1000L);
		reduce.setReduceAmount(fz.multiply(fm).doubleValue());
		reduce.setReduceCode(BmcSeqUtil.getReduceCode());
		reduce.setReduceId(BmcSeqUtil.getReduceId());
		reduce.setUnit(vo.getRuleUnit());
		cpFullReduceBusi.add(reduce);

		ProductResponse response = new ProductResponse();
		ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
		response.setResponseHeader(responseHeader);

		return response;
	}

	@Override
	public BaseResponse updateProferProductStatus(ActiveProductVO vo) {
		
		CpPriceInfo cpPriceInfo = new CpPriceInfo();
		cpPriceInfo.setPriceInfoId(vo.getProductId());
		cpPriceInfo.setActiveStatus(vo.getStatus()); // 设置状态
		cpPriceInfo.setTenantId(vo.getTenantId());
		int count = cpPriceInfoBusi.delCpRpriceInfo(cpPriceInfo);
		// 整理返回对象
		ResponseHeader responseHeader = new ResponseHeader();
		if (count > 0) {
			responseHeader.setIsSuccess(true);
			responseHeader.setResultCode(ResultCode.SUCCESS_CODE);
			responseHeader.setResultMessage("数据更新成功");
		} else {
			responseHeader.setIsSuccess(false);
			responseHeader.setResultCode(ResultCode.FAIL_CODE);
			responseHeader.setResultMessage("数据更新不成功");
		}
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setResponseHeader(responseHeader);
		return baseResponse;

	}

	@Override
	public BaseResponse delProferProduct(productDelVO vo) {
		CpPriceInfo cpPriceInfo = new CpPriceInfo();
		cpPriceInfo.setPriceInfoId(vo.getProductId());
		cpPriceInfo.setTenantId(vo.getTenantId());
		cpPriceInfo.setActiveStatus(BmcConstants.ProferName.DEL); // 设置状态为删除
		int count = cpPriceInfoBusi.delCpRpriceInfo(cpPriceInfo);
		ResponseHeader responseHeader = new ResponseHeader();
		if (count > 0) {
			responseHeader.setIsSuccess(true);
			responseHeader.setResultCode(ResultCode.SUCCESS_CODE);
			responseHeader.setResultMessage("数据更新成功");
		} else {
			responseHeader.setIsSuccess(false);
			responseHeader.setResultCode(ResultCode.FAIL_CODE);
			responseHeader.setResultMessage("数据更新不成功");
		}
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setResponseHeader(responseHeader);
		return baseResponse;
	}

	@Override
	public BaseResponse updateProferProduct(ProferProductVO vo){

		
		/**
		 * 更新资费信息表 cp_price_info
		 */
		// String priceCode = BmcSeqUtil.getPriceCode();
		CpPriceInfo cpPriceInfo = new CpPriceInfo();
		cpPriceInfo.setPriceInfoId(vo.getProductId()); // 获得PriceId
		cpPriceInfo.setTenantId(vo.getTenantId()); // 得到租户
		// cpPriceInfo.setPriceCode(priceCode);
		// cpPriceInfo.setCreateTime(DateUtil.getSysDate());
		cpPriceInfo.setActiveTime(vo.getActiveDate());
		cpPriceInfo.setComments(vo.getComments());
		cpPriceInfo.setOperatorId(vo.getOperatorId());
		cpPriceInfo.setInactiveTime(vo.getInvalidDate());
		cpPriceInfo.setPriceName(vo.getProgramName());
		cpPriceInfo.setPriceInfoId(vo.getProductId());
		// cpPriceInfo.setProductType(vo.getProductType()); 暂时不填写
		
		cpPriceInfoBusi.updatePriceInfo(cpPriceInfo);
		/**
		 * 更新资费计划明细表 cp_price_detail
		 */
		// CpPriceDetail detail = new CpPriceDetail();
		CpPriceDetail detail = cpPriceDetailBusi.getCpPriceDetail(vo.getPriceCode());

		detail.setDetailName(vo.getProgramName());

		cpPriceDetailBusi.updatePriceDetail(detail);
		String chargeType = detail.getChargeType();
		String detailCode = detail.getDetailCode();
		if ((BmcConstants.ProferName.DR_OFFER).equals(chargeType)) {// 满赠
			// 由于数量不好对应所以进行先删后增
			cpFullPresentBusi.deleteFullPresent(detailCode);
			List<FullPresent> fList = vo.getPresentList();
			if(!CollectionUtil.isEmpty(fList)){
				for (FullPresent p : fList) {
					CpFullPresent cfp = new CpFullPresent();
					cfp.setPresentType(p.getGiftType());
					cfp.setActiveTime(p.getGiftActiveDate());
					cfp.setDetailCode(detailCode);
					cfp.setInactiveTime(p.getGiftInvalidDate());
					String presentCode = BmcSeqUtil.getPresentCode();
					cfp.setPresentCode(presentCode);
					cfp.setPresentId(BmcSeqUtil.getPresentId());
					cfp.setProductGiftIds(JSON.toJSONString(p.getGiftProList()));
					cfp.setReachAmount(vo.getRuleAmount());
					cfp.setProductIds(JSON.toJSONString(vo.getProductList()));
					cfp.setPresentAmount(p.getGitfAmount());
					cfp.setUnit(vo.getRuleUnit());
					cfp.setActiveCycle(p.getActiveCycle());
					cfp.setActiveFlag(p.getActiveFlag());
					cpFullPresentBusi.addFullPresent(cfp);
				}
			}
			
		}
		if ((BmcConstants.ProferName.DR_MINUS).equals(chargeType)) {// 满减
			CpFullReduce r=cpFullReduceBusi.getFullReduce(detailCode);
			CpFullReduce reduce = new CpFullReduce();
			reduce.setActiveTime(vo.getActiveDate());
			// reduce.setDetailCode(detailCode);
			reduce.setInactiveTime(vo.getInvalidDate());
			reduce.setProductIds(JSON.toJSONString(vo.getProductList()));
			reduce.setReachAmount(vo.getRuleAmount());
			reduce.setReduceAmount(vo.getReduceAmount());
			reduce.setReduceCode(BmcSeqUtil.getReduceCode());
			reduce.setReduceId(BmcSeqUtil.getReduceId());
			reduce.setUnit(vo.getRuleUnit());
			reduce.setReduceId(r.getReduceId());
			cpFullReduceBusi.updateFullReduce(reduce);
		}
		ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setResponseHeader(responseHeader);
		return baseResponse;
	}

	@Override
	public BaseResponse relatedAccout(RelatedAccountVO vo)  {
		int  count=0;
		ProductQueryParam param=new ProductQueryParam();
		param.setProductId(vo.getProductId());
		param.setTenantId(vo.getTenantId());
		param.setTradeSeq(vo.getTradeSeq());
		String priceCode=cpPriceInfoBusi.getCpPriceInfo(param).getPriceCode();
		String detailCode=cpPriceDetailBusi.getCpPriceDetail(priceCode).getDetailCode();
		
		// 满赠
		if ((BmcConstants.ProferName.DR_OFFER).equals(vo.getChargeType())) {
			//List<Long> pIds = vo.getFullIds();
			List<CpFullPresent> plist=cpFullPresentBusi.getFullPresents(detailCode);
			if(!CollectionUtil.isEmpty(plist)){
				for(CpFullPresent cp:plist){
					CpFullPresent p = cpFullPresentBusi.getFullPresent(cp.getPresentId());

					p.setAccountType(vo.getAccountType());
					p.setRelatedAccount(JSON.toJSONString(vo.getRelAccounts()));
					count=cpFullPresentBusi.updateFullPresent(p);
				}
			}
			
		
		}
		// 满减
		if ((BmcConstants.ProferName.DR_MINUS).equals(vo.getChargeType())) {
			//Long rId = vo.getFullIds().get(0);
			CpFullReduce fr=cpFullReduceBusi.getFullReduce(detailCode);
			
			//CpFullReduce r = cpFullReduceBusi.getFullReduce(rId);
			CpFullReduce r = cpFullReduceBusi.getFullReduce(fr.getReduceId());
			if (r != null) {
				CpFullReduce cfr = new CpFullReduce();
				cfr.setRelatedAccount(JSON.toJSONString(vo.getRelAccounts()));
				cfr.setAccountType(vo.getAccountType());
				cfr.setReduceId(r.getReduceId());
				count=cpFullReduceBusi.updateFullReduce(cfr);
			}

		}
		ResponseHeader responseHeader = new ResponseHeader();
		if (count > 0) {
			responseHeader.setIsSuccess(true);
			responseHeader.setResultCode(ResultCode.SUCCESS_CODE);
			responseHeader.setResultMessage("关联成功");
		} else {
			responseHeader.setIsSuccess(false);
			responseHeader.setResultCode(ResultCode.FAIL_CODE);
			responseHeader.setResultMessage("关联失败");
		}
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setResponseHeader(responseHeader);
		return baseResponse;
	}

}
