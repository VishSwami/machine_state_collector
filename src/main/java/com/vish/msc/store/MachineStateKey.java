package com.vish.msc.store;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;

@PrimaryKeyClass
@Data
public class MachineStateKey implements Serializable {
    @PrimaryKeyColumn(name = "machine_name", type = PrimaryKeyType.PARTITIONED)
    @NonNull
    private String machineName;
}