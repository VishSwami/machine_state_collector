package com.vish.msc.queue;

import com.vish.msc.store.MachineState;
import com.vish.msc.store.MachineStateKey;
import com.vish.msc.store.MachineStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@EnableCassandraRepositories(basePackages = "com.vish.msc.store")
public class MachineStateService {
    @Autowired
    private RequestThrottler requestThrottler;
    @Autowired
    private MachineStateQueue stateQueue;
    @Autowired
    private MachineStateRepository machineStateRepository;
    @Value("${redis-throttle-limit}")
    private int throttleLimit;


    @PutMapping("/machines/status")
    public void setStatus(@RequestParam String machineName, @RequestParam int status,
                          @RequestParam double temperature, @RequestParam int itemsProcessed) {
        produce(machineName, status, temperature, itemsProcessed);
    }

    public ListenableFuture produce(String machineName, int status,
                                    double temperature, int itemsProcessed) {
        if(!requestThrottler.throttleRequestCount(machineName, throttleLimit)) {
            return null;
        }
        MachineStateKey key = new MachineStateKey(machineName);
        MachineState m = new MachineState(key, status, temperature, itemsProcessed, LocalDateTime.now());
        return stateQueue.push(m);
    }

    @GetMapping("/machines/status")
    public List<MachineState> getStatus(@RequestParam(required = false) String machinename) {
        if (machinename != null) {
            return machineStateRepository.findByKeyMachineName(machinename);
        }
        return machineStateRepository.findAll();
    }

    @GetMapping("cassandra/clear_all")
    public void clearAll() { machineStateRepository.deleteAll(); }
}
