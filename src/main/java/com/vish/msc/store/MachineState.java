package com.vish.msc.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;

@Table("machine_states")
@Data
@AllArgsConstructor
public class MachineState {
    @PrimaryKey
    private MachineStateKey key;
    @Column
    private int status;
    @Column
    private double temperature;
    @Column("items_processed")
    private int itemsProcessed;
    @Column("time_stamp")
    private LocalDateTime timeStamp;
}