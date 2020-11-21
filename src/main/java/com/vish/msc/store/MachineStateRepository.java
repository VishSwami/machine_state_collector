package com.vish.msc.store;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachineStateRepository extends CassandraRepository<MachineState, MachineStateKey> {
    List<MachineState> findByKeyMachineName(final String machineName);
}
