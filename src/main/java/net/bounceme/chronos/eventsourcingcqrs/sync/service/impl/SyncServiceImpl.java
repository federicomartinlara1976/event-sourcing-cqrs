package net.bounceme.chronos.eventsourcingcqrs.sync.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.bounceme.chronos.eventsourcingcqrs.command.event.comment.CommentAddedEvent;
import net.bounceme.chronos.eventsourcingcqrs.command.event.comment.CommentRemovedEvent;
import net.bounceme.chronos.eventsourcingcqrs.command.event.comment.CommentUpdatedEvent;
import net.bounceme.chronos.eventsourcingcqrs.command.event.post.PostAddedEvent;
import net.bounceme.chronos.eventsourcingcqrs.command.event.post.PostRemovedEvent;
import net.bounceme.chronos.eventsourcingcqrs.command.event.post.PostUpdatedEvent;
import net.bounceme.chronos.eventsourcingcqrs.command.event.reaction.ReactionAddedEvent;
import net.bounceme.chronos.eventsourcingcqrs.command.event.reaction.ReactionRemovedEvent;
import net.bounceme.chronos.eventsourcingcqrs.command.repository.EventStore;
import net.bounceme.chronos.eventsourcingcqrs.query.model.Comment;
import net.bounceme.chronos.eventsourcingcqrs.query.model.Post;
import net.bounceme.chronos.eventsourcingcqrs.query.repository.PostRepository;
import net.bounceme.chronos.eventsourcingcqrs.sync.service.SyncService;
import net.bounceme.chronos.eventsourcingcqrs.utils.Ordering;

@Service
@Slf4j
public class SyncServiceImpl implements SyncService {

	Date lastSyncDate = new Date();

	@Autowired
	private EventStore eventStore;

	@Autowired
	private MongoOperations mongoOps;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void sync() {
		Date newSyncDate = new Date();
		
		eventStore
			.getEventsAfterOrder(lastSyncDate, Ordering.DESC)
			.forEach(e -> {
				switch(e) {
					case PostAddedEvent ev -> applyEvent(ev);
					case PostUpdatedEvent ev -> applyEvent(ev);
					case PostRemovedEvent ev -> applyEvent(ev);
					case CommentAddedEvent ev -> applyEvent(ev);
					case CommentUpdatedEvent ev -> applyEvent(ev);
					case CommentRemovedEvent ev -> applyEvent(ev);
					case ReactionAddedEvent ev -> applyEvent(ev);
					case ReactionRemovedEvent ev -> applyEvent(ev);
					default -> throw new IllegalStateException("Tipo de evento desconocido: " + e.getClass());
				}
			});

		lastSyncDate = newSyncDate;
	}

	private void applyEvent(PostAddedEvent event) {
		Post post = new Post();
		post.setId(event.getPostId());
		post.setContent(event.getContent());
		savePost(post);
		
		log.info("Saved: {}", post.toString());
	}

	private void applyEvent(PostUpdatedEvent event) {
		Post post = new Post();
		post.setId(event.getPostId());
		post.setContent(event.getContent());
		savePost(post);
		
		log.info("Updated: {}", post.toString());
	}

	private void applyEvent(PostRemovedEvent event) {
		postRepository.deleteById(event.getPostId());
		
		log.info("Deleted: {}", event.getPostId());
	}

	private void applyEvent(CommentAddedEvent event) {
		Comment comment = new Comment();
		comment.setId(event.getCommentId());
		comment.setContent(event.getContent());
		saveComment(event.getPostId(), comment);
	}

	private void applyEvent(CommentUpdatedEvent event) {
		Comment comment = new Comment();
		comment.setId(event.getCommentId());
		comment.setContent(event.getContent());
		saveComment(event.getPostId(), comment);
	}

	private void applyEvent(CommentRemovedEvent event) {
		// Similar logic
	}

	private void applyEvent(ReactionAddedEvent event) {
		// Similar logic
	}

	private void applyEvent(ReactionRemovedEvent event) {
		// Similar logic
	}

	private void savePost(Post post) {
		Query query = new Query(Criteria.where("id").is(post.getId()));
		Update update = new Update();
		update.set("content", post.getContent());
		mongoOps.findAndModify(query, update, FindAndModifyOptions.options().returnNew(true).upsert(true), Post.class);
	}

	private void saveComment(String postId, Comment comment) {
		Query query = new Query(Criteria.where("id").is(postId));
		Update update = new Update();
		update.addToSet("comments", comment);
		mongoOps.findAndModify(query, update, FindAndModifyOptions.options().returnNew(true).upsert(true), Post.class);
	}
}
