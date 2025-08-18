package net.bounceme.chronos.eventsourcingcqrs.query.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import net.bounceme.chronos.eventsourcingcqrs.query.model.Post;

public interface PostRepository extends MongoRepository<Post, String> {

}
