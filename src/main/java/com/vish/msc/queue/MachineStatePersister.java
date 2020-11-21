package com.vish.msc.queue;


import com.google.gson.Gson;
import com.vish.msc.store.MachineState;
import com.vish.msc.store.MachineStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MachineStatePersister {
    @Autowired
    private Gson jsonTool;
    @Autowired
    private MachineStateRepository machineStateRepository;

    @KafkaListener(topics = "${topic}", groupId = "${group-id}")
    public void listen(String message) {
        System.out.println(message);
        MachineState state = jsonTool.fromJson(message, MachineState.class);
        machineStateRepository.insert(state);
    }
}