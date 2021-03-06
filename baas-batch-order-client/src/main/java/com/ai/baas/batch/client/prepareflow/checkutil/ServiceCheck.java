package com.ai.baas.batch.client.prepareflow.checkutil;

import java.util.ArrayList;
import java.util.List;

public class ServiceCheck {
   
    private List<String> packageList = new ArrayList<>();
    private List<String> singleUsageList = new ArrayList<>();
    private List<String> doubleUsageList = new ArrayList<>();
    public ServiceCheck() {
        packageList.add("YOUYC");
        packageList.add("YOUHR");
        packageList.add("YOUBZ");
        packageList.add("WAF");
        
        packageList.add("CYD");
        packageList.add("ZBJZ");
        packageList.add("CMSTOP");
        packageList.add("TEAMBITION");
        packageList.add("GIXI");
        packageList.add("ZHUCE");
        /*
         * record_type : TIME ; STREAM
         */
        doubleUsageList.add("ECS");
        doubleUsageList.add("SLB");
        doubleUsageList.add("EIP");
        
        singleUsageList.add("DISK");
        singleUsageList.add("KVS");
        singleUsageList.add("RDS");      
        singleUsageList.add("EXCONN");

    }
    
    public boolean isPackage (String type){
        if(packageList.contains(type)){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean isDoubleUsage (String type){
        if(doubleUsageList.contains(type)){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean isSingleUsage (String type){
        if(singleUsageList.contains(type)){
            return true;
        }else{
            return false;
        }
    }
}
