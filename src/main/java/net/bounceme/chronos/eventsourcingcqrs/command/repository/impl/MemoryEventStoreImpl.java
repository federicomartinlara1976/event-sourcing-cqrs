package net.bounceme.chronos.eventsourcingcqrs.command.repository.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import net.bounceme.chronos.eventsourcingcqrs.command.event.Event;
import net.bounceme.chronos.eventsourcingcqrs.command.repository.EventStore;

@Repository
@Slf4j
public class MemoryEventStoreImpl implements EventStore {

	private final List<Event> eventStore = new ArrayList<>();
	
	// El criterio de ordenación es desde el evento más reciente al más antiguo
	private Comparator<Event> fromMostRecent = (e1, e2) -> Long.valueOf(e2.getCreatedDate().getTime() - e1.getCreatedDate().getTime()).intValue();

	@Override
	public void addEvent(Event event) {
		eventStore.add(event);
		log.info("Added: {}", event.toString());
	}

	@Override
	public List<Event> getEventsAfterOrderAsc(Date dateAfter) {
		return eventStore
				.stream().filter(e -> e.getCreatedDate().after(dateAfter))
				.sorted(fromMostRecent)
				.toList();
	}
}
