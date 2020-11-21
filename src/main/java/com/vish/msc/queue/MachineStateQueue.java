package com.vish.msc.queue;

import com.google.gson.Gson;
import com.vish.msc.store.MachineState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;

public class MachineStateQueue {
    @Autowired
    private KafkaTemplate<String, String> template;
    @Autowired
    private Gson jsonTool;
    @Value("${topic}")
    private String topic;

    public ListenableFuture push(MachineState machineState) {
        String message = jsonTool.toJson(machineState);
        return template.send(topic, message);
    }
}
