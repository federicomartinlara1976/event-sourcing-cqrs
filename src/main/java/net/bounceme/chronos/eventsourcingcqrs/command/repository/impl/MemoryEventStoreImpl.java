package net.bounceme.chronos.eventsourcingcqrs.command.repository.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import net.bounceme.chronos.eventsourcingcqrs.command.event.Event;
import net.bounceme.chronos.eventsourcingcqrs.command.repository.EventStore;
import net.bounceme.chronos.eventsourcingcqrs.utils.Ordering;

@Repository
@Slf4j
public class MemoryEventStoreImpl implements EventStore {

	private final List<Event> eventStore = new ArrayList<>();

	// El criterio de ordenación es desde el evento más reciente al más antiguo
	private static final Comparator<Event> FROM_MOST_RECENT = Comparator.comparing(Event::getCreatedDate).reversed();

	// El criterio de ordenación es desde el evento más antiguo al más reciente
	private static final Comparator<Event> FROM_LEAST_RECENT = Comparator.comparing(Event::getCreatedDate);

	@Override
	public void addEvent(Event event) {
		eventStore.add(event);
		log.info("Added: {}", event);
	}

	@Override
	public List<Event> getEventsAfterOrder(Date dateAfter, Ordering ordering) {
		return eventStore.stream().filter(e -> e.getCreatedDate().after(dateAfter))
				.sorted(Ordering.DESC.equals(ordering) ? FROM_MOST_RECENT : FROM_LEAST_RECENT).toList();
	}
}
