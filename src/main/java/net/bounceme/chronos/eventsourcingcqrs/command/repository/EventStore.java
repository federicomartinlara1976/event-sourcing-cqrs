package net.bounceme.chronos.eventsourcingcqrs.command.repository;

import java.util.ArrayList;
import java.util.Comparator;
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
	
	// El criterio de ordenación es desde el evento más reciente al más antiguo
	private Comparator<Event> fromMostRecent = (e1, e2) -> Long.valueOf(e2.getCreatedDate().getTime() - e1.getCreatedDate().getTime()).intValue();

	public void addEvent(Event event) {
		eventStore.add(event);
		log.info("Added: {}", event.toString());
	}

	public List<Event> getEventsAfterOrderAsc(Date dateAfter) {
		return eventStore
				.stream().filter(e -> e.getCreatedDate().after(dateAfter))
				.sorted(fromMostRecent)
				.toList();
	}
}
