--  上云迁移（服务器）服务-300034 数据库迁移服务-300035
INSERT INTO `amc_dr_bill_subject_map` (`TENANT_ID`, `DR_SUBJECT`, `BILL_SUBJECT`) VALUES ('ECITIC', 'CTRANS01', '300034');   
INSERT INTO `amc_dr_bill_subject_map` (`TENANT_ID`, `DR_SUBJECT`, `BILL_SUBJECT`) VALUES ('ECITIC', 'DTRANS01', '300035');  
INSERT INTO `amc_deduct_rule` (`TENANT_ID`, `FUND_SUBJECT`, `FEE_SUBJECT`) VALUES ('ECITIC', '100001', '300034');
INSERT INTO `amc_deduct_rule` (`TENANT_ID`, `FUND_SUBJECT`, `FEE_SUBJECT`) VALUES ('ECITIC', '100001', '300035');
commit;