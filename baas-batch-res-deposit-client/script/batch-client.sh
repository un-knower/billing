#!/bin/sh

#把根目录下面所有的jar包都加入到CP环境变量中
for file in ${APP_HOME}/libs/3rd-libs/*.jar;
do CP=${CP}:$file;
done

for file in ${APP_HOME}/libs/core-libs/*.jar;
do CP=${CP}:$file;
done

#把config目录加入到CONFIG_PATH环境变量中
export CONFIG_PATH=$APP_HOME/config

#把CP和CONFIG_PATH都加入到CLASSPATH环境变量中
CLASSPATH="${CP}:${CONFIG_PATH}"
export CLASSPATH
echo ${CLASSPATH}

#定义JAVA_OPTIONS环境变量
export JAVA_OPTIONS=" -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Dsun.net.inetaddr.ttl=10 -Dpaas.dubbo.registry.file=./.dubbo-registry.dat -Dpaas.dubbo.protocol.port=$DUBBO_PORT" 
MEM_ARGS="-Xms256m -Xmx512m -XX:PermSize=64M -XX:MaxPermSize=128M" 

sed -i "s/paas.sdk.mode=.*/paas.sdk.mode=${SDK_MODE}/g" /baas-batch-res-deposit-client/config/paas/paas-conf.properties
sed -i "s/ccs.appname=.*/ccs.appname=${CCS_NAME}/g" /baas-batch-res-deposit-client/config/paas/paas-conf.properties
sed -i "s/ccs.zk_address=.*/ccs.zk_address=${ZK_ADDRESS}/g" /baas-batch-res-deposit-client/config/paas/paas-conf.properties

sed -i "s%dubbo.registry.address=.*%dubbo.registry.address=${DUBBO_REGISTRY_ADDR}%g" /baas-batch-res-deposit-client/config/dubbo/dubbo.properties
sed -i "s%dubbo.protocol=.*%dubbo.protocol=${DUBBO_PROTOCOL}%g" /baas-batch-res-deposit-client/config/dubbo/dubbo.properties

#启动java虚拟机和程序
${JAVA_HOME}/bin/java ${MEM_ARGS}   ${JAVA_OPTIONS} com.ai.runner.center.bmc.core.ResAccount  > /logs/batch-client.log  2>&1 &


