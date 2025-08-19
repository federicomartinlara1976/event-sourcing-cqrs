package net.bounceme.chronos.eventsourcingcqrs.command.repository;

import java.util.Date;
import java.util.List;

import net.bounceme.chronos.eventsourcingcqrs.command.event.Event;

public interface EventStore {

	void addEvent(Event event);

	List<Event> getEventsAfterOrderAsc(Date dateAfter);

}