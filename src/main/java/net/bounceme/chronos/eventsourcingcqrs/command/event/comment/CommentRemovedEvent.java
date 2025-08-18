package net.bounceme.chronos.eventsourcingcqrs.command.event.comment;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.bounceme.chronos.eventsourcingcqrs.command.event.Event;

@Data
@EqualsAndHashCode(callSuper = false)
public class CommentRemovedEvent extends Event {
  
	private String commentId;
}
