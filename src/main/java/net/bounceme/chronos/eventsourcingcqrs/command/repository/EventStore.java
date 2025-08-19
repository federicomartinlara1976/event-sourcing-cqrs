package net.bounceme.chronos.eventsourcingcqrs.command.repository;

import java.util.Date;
import java.util.List;

import net.bounceme.chronos.eventsourcingcqrs.command.event.Event;
import net.bounceme.chronos.eventsourcingcqrs.utils.Ordering;

public interface EventStore {

	void addEvent(Event event);

	List<Event> getEventsAfterOrder(Date dateAfter, Ordering ordering);

}