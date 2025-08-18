package net.bounceme.chronos.eventsourcingcqrs.command.event.reaction;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.bounceme.chronos.eventsourcingcqrs.command.event.Event;

@Data
@EqualsAndHashCode(callSuper = false)
public class ReactionRemovedEvent extends Event {

	private String reactionId;
}
