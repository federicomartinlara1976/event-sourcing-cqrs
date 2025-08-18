package net.bounceme.chronos.eventsourcingcqrs.command.event.post;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.bounceme.chronos.eventsourcingcqrs.command.event.Event;

@Data
@EqualsAndHashCode(callSuper = false)
public class PostRemovedEvent extends Event {

	private String postId;
}
