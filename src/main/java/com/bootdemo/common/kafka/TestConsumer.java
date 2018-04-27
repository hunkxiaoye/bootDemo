package com.bootdemo.common.kafka;

import com.bootdemo.common.kafka.annotation.KafkaConf;
import org.springframework.stereotype.Component;

@Component
@KafkaConf(topic = "yp_comment", groupid = "test_yp")
public class TestConsumer extends AbstractConsumer<String> {

    public boolean   process(String msg) {

        System.out.println("msg:" + msg);

        return false;
    }

}
