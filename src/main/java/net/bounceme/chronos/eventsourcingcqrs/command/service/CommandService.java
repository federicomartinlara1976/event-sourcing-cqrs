package net.bounceme.chronos.eventsourcingcqrs.command.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bounceme.chronos.eventsourcingcqrs.command.event.comment.CommentAddedEvent;
import net.bounceme.chronos.eventsourcingcqrs.command.event.comment.CommentRemovedEvent;
import net.bounceme.chronos.eventsourcingcqrs.command.event.comment.CommentUpdatedEvent;
import net.bounceme.chronos.eventsourcingcqrs.command.event.post.PostAddedEvent;
import net.bounceme.chronos.eventsourcingcqrs.command.event.post.PostRemovedEvent;
import net.bounceme.chronos.eventsourcingcqrs.command.event.post.PostUpdatedEvent;
import net.bounceme.chronos.eventsourcingcqrs.command.event.reaction.ReactionAddedEvent;
import net.bounceme.chronos.eventsourcingcqrs.command.event.reaction.ReactionRemovedEvent;
import net.bounceme.chronos.eventsourcingcqrs.command.repository.EventStore;

@Service
public class CommandService {

	@Autowired
	private EventStore eventStore;

	public PostAddedEvent addPost(PostAddedEvent event) {
		event.setPostId(UUID.randomUUID().toString());
		eventStore.addEvent(event);
		return event;
	}

	public PostUpdatedEvent updatePost(PostUpdatedEvent event) {
		eventStore.addEvent(event);
		return event;
	}

	public PostRemovedEvent removePost(String postId) {
		PostRemovedEvent event = new PostRemovedEvent();
		event.setPostId(postId);
		eventStore.addEvent(event);
		return event;
	}

	public CommentAddedEvent addComment(String postId, CommentAddedEvent event) {
		event.setCommentId(UUID.randomUUID().toString());
		event.setPostId(postId);
		eventStore.addEvent(event);
		return event;
	}

	public CommentUpdatedEvent updateComment(CommentUpdatedEvent event) {
		eventStore.addEvent(event);
		return event;
	}

	public CommentRemovedEvent removeComment(String commentId) {
		CommentRemovedEvent event = new CommentRemovedEvent();
		event.setCommentId(commentId);
		eventStore.addEvent(event);
		return event;
	}

	public ReactionAddedEvent addReaction(String postId, String commentId, ReactionAddedEvent event) {
		event.setCommentId(commentId);
		event.setPostId(postId);
		eventStore.addEvent(event);
		return event;
	}

	public ReactionRemovedEvent removeReaction(String reactionId) {
		ReactionRemovedEvent event = new ReactionRemovedEvent();
		event.setReactionId(reactionId);
		eventStore.addEvent(event);
		return event;
	}
}
