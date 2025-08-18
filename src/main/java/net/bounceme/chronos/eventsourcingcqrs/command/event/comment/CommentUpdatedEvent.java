package net.bounceme.chronos.eventsourcingcqrs.command.event.comment;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.bounceme.chronos.eventsourcingcqrs.command.event.Event;

@Data
@EqualsAndHashCode(callSuper = false)
public class CommentUpdatedEvent extends Event {

	private String commentId;
	
	private String postId;
	
	private String content;
}
