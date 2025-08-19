package net.bounceme.chronos.eventsourcingcqrs.command.service;

import net.bounceme.chronos.eventsourcingcqrs.command.event.comment.CommentAddedEvent;
import net.bounceme.chronos.eventsourcingcqrs.command.event.comment.CommentRemovedEvent;
import net.bounceme.chronos.eventsourcingcqrs.command.event.comment.CommentUpdatedEvent;
import net.bounceme.chronos.eventsourcingcqrs.command.event.post.PostAddedEvent;
import net.bounceme.chronos.eventsourcingcqrs.command.event.post.PostRemovedEvent;
import net.bounceme.chronos.eventsourcingcqrs.command.event.post.PostUpdatedEvent;
import net.bounceme.chronos.eventsourcingcqrs.command.event.reaction.ReactionAddedEvent;
import net.bounceme.chronos.eventsourcingcqrs.command.event.reaction.ReactionRemovedEvent;

public interface CommandService {

	PostAddedEvent addPost(PostAddedEvent event);

	PostUpdatedEvent updatePost(PostUpdatedEvent event);

	PostRemovedEvent removePost(String postId);

	CommentAddedEvent addComment(String postId, CommentAddedEvent event);

	CommentUpdatedEvent updateComment(CommentUpdatedEvent event);

	CommentRemovedEvent removeComment(String commentId);

	ReactionAddedEvent addReaction(String postId, String commentId, ReactionAddedEvent event);

	ReactionRemovedEvent removeReaction(String reactionId);

}