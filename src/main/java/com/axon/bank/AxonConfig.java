package com.axon.bank;

import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.eventsourcing.Snapshotter;
import org.axonframework.serialization.upcasting.event.SingleEventUpcaster;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class AxonConfig {

	@Bean
	public SnapshotTriggerDefinition bankAccountSnapshotTrigger(Snapshotter snapshotter) {
	    return new EventCountSnapshotTriggerDefinition(snapshotter, 6);
	}
	
	@Bean
	@Order(0)
	public SingleEventUpcaster complaintEventUpcasterOne() {
		return new Upcaster();
	}
}
