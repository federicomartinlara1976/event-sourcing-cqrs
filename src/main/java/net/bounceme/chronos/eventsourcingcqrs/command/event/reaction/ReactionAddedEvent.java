package net.bounceme.chronos.eventsourcingcqrs.command.event.reaction;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.bounceme.chronos.eventsourcingcqrs.command.event.Event;

@Data
@EqualsAndHashCode(callSuper = false)
public class ReactionAddedEvent extends Event {

	private String reactionId;
	
	private String commentId;
	
	private String postId;
	
	private String emoji;
}
