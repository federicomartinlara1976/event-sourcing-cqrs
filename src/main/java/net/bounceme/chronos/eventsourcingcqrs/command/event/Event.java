package net.bounceme.chronos.eventsourcingcqrs.command.event;

import java.util.Date;
import java.util.UUID;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Event {

	private final UUID id = UUID.randomUUID();

	private final Date createdDate = new Date();
}
