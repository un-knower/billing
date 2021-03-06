package com.ai.citic.billing.web.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.bmc.api.baseInfo.interfaces.IBaseInfoSV;
import com.ai.baas.bmc.api.baseInfo.params.BaseCode;
import com.ai.baas.bmc.api.baseInfo.params.BaseCodeInfo;
import com.ai.baas.bmc.api.baseInfo.params.QueryInfoParams;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.constants.ExceptCodeConstants;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.dubbo.util.HttpClientUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class AdapterUtil {
    private static String ORG_QUERY_PATH = "http://10.1.130.84:10883/adapter-url/usrmgt/service_org";// 供应商查询地址

    private static String SELECT_TYPE = "8";// 查询类型

    private static String ORG_ID = "6ba76566-0251-4e1a-a188-64e1b2d26be2";// 供应商ID TODO

    private static String TOTAL_BILL_REST_PATH = "/adapter-url/suppliers_service/total_bill/:576203a86ae6ca04e1459582";

    private static String CONSUME_DETAIL_PATH = "/adapter-url/suppliers_service/consume_detail";

    private static Logger LOG = LoggerFactory.getLogger(AdapterUtil.class);

    /**
     * 余额查询
     * 
     * @return
     * @author LiangMeng
     */
    public static String queryAliBalance() {
        String balance = "0";
        try {
            String url = querySuppliderUrl() + CONSUME_DETAIL_PATH;
            Map<String, String> parameters = new HashMap<String, String>();
            String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
            parameters.put("start_date", today);
            parameters.put("end_date", today);
            String json = HttpClientUtil.sendGet(url, parameters);
            JSONObject jsonReturn = JSONObject.parseObject(json);
            String returnCode = jsonReturn.getString("resultCode");
            if (!ExceptCodeConstants.Special.SUCCESS.equals(returnCode)) {
                throw new BusinessException("余额接口查询异常：[" + returnCode + "]");
            }
            String data = jsonReturn.getString("data");
            JSONObject jsonData = JSONObject.parseObject(data);

            balance = jsonData.getString("balance");
        } catch (Exception e) {
            LOG.error("获取余额出错：" + e.getMessage(), e);
        }
        return balance;
    }

    /**
     * 查询阿里总账
     * 
     * @return
     * @author LiangMeng
     */
    public static String queryAliTotalBill() {
        String totalBill = "0";
        try {
            String url = querySuppliderUrl() + TOTAL_BILL_REST_PATH;
            Map<String, String> parameters = new HashMap<String, String>();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -1);// 月份减1
            Date resultDate = cal.getTime(); // 结果
            String preMonth = new SimpleDateFormat("yyyyMM").format(resultDate);
            parameters.put("start_date", preMonth);
            parameters.put("end_date", preMonth);
            parameters.put("grain_size", "month");
            String json = HttpClientUtil.sendGet(url, parameters);
            JSONObject jsonReturn = JSONObject.parseObject(json);
            String returnCode = jsonReturn.getString("resultCode");
            if (!ExceptCodeConstants.Special.SUCCESS.equals(returnCode)) {
                throw new BusinessException("总账接口查询异常：[" + returnCode + "]");
            }
            String data = jsonReturn.getString("data");
            JSONObject jsonData = JSONObject.parseObject(data);
            JSONArray totalArray = jsonData.getJSONArray("bill");
            if (totalArray != null && totalArray.size() > 0) {
                totalBill = totalArray.getJSONObject(0).getString("amount");
            }
        } catch (Exception e) {
            LOG.error("获取总账出错：" + e.getMessage(), e);
        }
        return totalBill;
    }

    /**
     * 获取供应商url
     * 
     * @return
     * @author LiangMeng
     */
    private static String querySuppliderUrl() {
        String url = null;
        try {
            init();
            Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("select_type", SELECT_TYPE);
            String json = HttpClientUtil.sendGet(ORG_QUERY_PATH, parameters);
            JSONObject jsonReturn = JSONObject.parseObject(json);
            String data = jsonReturn.getString("data");
            JSONObject jsonData = JSONObject.parseObject(data);
            JSONArray jsonArr = jsonData.getJSONArray("orgs");
            for (Object jsonsub : jsonArr) {
                JSONObject jsonele = (JSONObject) jsonsub;
                if (ORG_ID.equals(jsonele.getString("org_id"))) {
                    url = jsonele.getString("uri");
                    url = url.replaceAll("&", ":");
                }
            }
        } catch (Exception e) {
            LOG.error("获取供应商url出错：" + e.getMessage(), e);
        }
        return url;
    }

    private static void init() {
        try {
            IBaseInfoSV baseinfoSV = DubboConsumerFactory.getService(IBaseInfoSV.class);
            QueryInfoParams param = new QueryInfoParams();
            param.setParamType("ADAPTER_PARAM");
            param.setTenantId("PUB");
            param.setTradeSeq(System.currentTimeMillis() + "");
            BaseCodeInfo info = baseinfoSV.getBaseInfo(param);
            List<BaseCode> baseCodes = info.getParamList();
            if (baseCodes != null) {
                for (BaseCode baseCode : baseCodes) {
                    if ("ORG_QUERY_PATH".equals(baseCode.getParamCode())) {
                        ORG_QUERY_PATH = baseCode.getDefaultValue();
                    }
                    if ("SELECT_TYPE".equals(baseCode.getParamCode())) {
                        SELECT_TYPE = baseCode.getDefaultValue();
                    }
                    if ("ORG_ID".equals(baseCode.getParamCode())) {
                        ORG_ID = baseCode.getDefaultValue();
                    }
                    if ("TOTAL_BILL_REST_PATH".equals(baseCode.getParamCode())) {
                        TOTAL_BILL_REST_PATH = baseCode.getDefaultValue();
                    }
                    if ("CONSUME_DETAIL_PATH".equals(baseCode.getParamCode())) {
                        CONSUME_DETAIL_PATH = baseCode.getDefaultValue();
                    }
                }
            }
        } catch (BusinessException e) {
            LOG.error("获取缓存数据出错：" + e.getMessage(), e);
        } catch (SystemException e) {
            LOG.error("获取缓存数据出错：" + e.getMessage(), e);
        }
    }
}
