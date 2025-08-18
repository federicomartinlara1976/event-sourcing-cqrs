package net.bounceme.chronos.eventsourcingcqrs.command.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.bounceme.chronos.eventsourcingcqrs.command.event.Event;

@Component
@Slf4j
public class EventStore {

	private final List<Event> eventStore = new ArrayList<>();

	public void addEvent(Event event) {
		eventStore.add(event);
		log.info("Added: {}", event.toString());
	}

	public List<Event> getEventsAfterOrderAsc(Date dateAfter) {
		List<Event> events = eventStore.stream()
				.filter(e -> e.getCreatedDate().after(dateAfter))
				.collect(Collectors.toList());
		Collections.reverse(events); // TODO - Implementar esto usando sorted antes de hacer el collect
		return events;
	}
}
