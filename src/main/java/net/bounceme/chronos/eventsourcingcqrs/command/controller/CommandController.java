package net.bounceme.chronos.eventsourcingcqrs.command.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.bounceme.chronos.eventsourcingcqrs.command.event.comment.CommentAddedEvent;
import net.bounceme.chronos.eventsourcingcqrs.command.event.comment.CommentRemovedEvent;
import net.bounceme.chronos.eventsourcingcqrs.command.event.comment.CommentUpdatedEvent;
import net.bounceme.chronos.eventsourcingcqrs.command.event.post.PostAddedEvent;
import net.bounceme.chronos.eventsourcingcqrs.command.event.post.PostRemovedEvent;
import net.bounceme.chronos.eventsourcingcqrs.command.event.post.PostUpdatedEvent;
import net.bounceme.chronos.eventsourcingcqrs.command.event.reaction.ReactionAddedEvent;
import net.bounceme.chronos.eventsourcingcqrs.command.event.reaction.ReactionRemovedEvent;
import net.bounceme.chronos.eventsourcingcqrs.command.service.CommandService;

@RestController
public class CommandController {

	@Autowired
	private CommandService commandService;

	@PostMapping("/post")
	public PostAddedEvent addPost(@RequestBody PostAddedEvent post) {
		return commandService.addPost(post);
	}

	@PutMapping("/post")
	public PostUpdatedEvent updatePost(@RequestBody PostUpdatedEvent post) {
		return commandService.updatePost(post);
	}

	@DeleteMapping("/post/{postId}")
	public PostRemovedEvent removePost(@PathVariable String postId) {
		return commandService.removePost(postId);
	}

	@PostMapping("/post/{postId}/comment")
	public CommentAddedEvent addComment(@PathVariable String postId, @RequestBody CommentAddedEvent comment) {
		return commandService.addComment(postId, comment);
	}

	@PutMapping("/comment")
	public CommentUpdatedEvent updateComment(@RequestBody CommentUpdatedEvent comment) {
		return commandService.updateComment(comment);
	}

	@DeleteMapping("/comment/{commentId}")
	public CommentRemovedEvent removeComment(@PathVariable String commentId) {
		return commandService.removeComment(commentId);
	}

	@PostMapping("/post/{postId}/comment/{commentId}/reaction")
	public ReactionAddedEvent addReaction(@PathVariable String postId, @PathVariable String commentId,
			@RequestBody ReactionAddedEvent reaction) {
		return commandService.addReaction(postId, commentId, reaction);
	}

	@DeleteMapping("/reaction/{reactionId}")
	public ReactionRemovedEvent removeReaction(@PathVariable String reactionId) {
		return commandService.removeReaction(reactionId);
	}
}
