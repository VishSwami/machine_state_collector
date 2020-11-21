package com.vish.msc.msc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MscApplicationTests {
//	@Autowired
//	private MachineStateService machineStateService;
//	@Autowired
//	private Gson jsonTool;
//	@Autowired
//	private RequestThrottler requestThrottler;
	private final String machineName = "machine_name";
	private final int status = 0;
	private final double temperature = 0;
	private final int itemsProcessed = 0;

	@Test
	public void dummyTest() {}

//	@Test
//	@Order(1)
//	public void kafkaProducerTest() throws Exception {
//		ListenableFuture<SendResult<String, String>> future = machineStateService.produce(
//				machineName,
//				status,
//				temperature,
//				itemsProcessed);
//		SendResult<String, String> result = future.get();
//		String messageSent = result.getProducerRecord().value();
//		MachineState objectSent = jsonTool.fromJson(messageSent, MachineState.class);
//		assert objectSent.getKey().getMachineName().equals(machineName);
//		assert objectSent.getStatus() == status;
//		assert objectSent.getTemperature() == temperature;
//		assert objectSent.getItemsProcessed() == itemsProcessed;
//	}
//
//	@Test
//	@Order(2)
//	public void cassandraDataTest() throws Exception {
//		MachineState state = machineStateService.getStatus(machineName).get(0);
//		assert machineName.equals(state.getKey().getMachineName());
//		assert status == state.getStatus();
//		assert temperature == state.getTemperature();
//		assert itemsProcessed == state.getItemsProcessed();
//	}
//
//	@Test
//	@Order(3)
//	public void redisThrottleTest() throws Exception {
//		assert requestThrottler.throttleRequestCount(machineName, 10);
//		for(int i = 0; i < 20; ++i) {
//			requestThrottler.throttleRequestCount(machineName, 10);
//		}
//		assert !requestThrottler.throttleRequestCount(machineName, 10);
//	}
//
//	@Test
//	@Order(4)
//	public void redisLockTest() throws Exception {
//		assert requestThrottler.lockResource(machineName);
//		assert !requestThrottler.lockResource(machineName);
//		assert requestThrottler.unlockResource(machineName);
//		assert !requestThrottler.unlockResource(machineName);
//	}

}
