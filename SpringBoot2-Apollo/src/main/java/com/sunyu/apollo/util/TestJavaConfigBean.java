package com.sunyu.apollo.util;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author yu 2018/11/12.
 */
public class TestJavaConfigBean {

    @ApolloConfig("application")
    private Config config; //inject config for namespace application

    @Value("${spring.datasource.name}")//如果配置中心没有值，默认key为test的value值为test
    private String name;

    //config change listener for namespace application
    @ApolloConfigChangeListener("application")
    private void anotherOnChange(ConfigChangeEvent changeEvent) {

        ConfigChange change = changeEvent.getChange("test");
        System.out.println(String.format("Found change - key: %s, oldValue: %s,"
                + " newValue: %s, changeType: %s", change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
    }
}
