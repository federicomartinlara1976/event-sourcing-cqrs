package net.bounceme.chronos.eventsourcingcqrs.query.service;

import net.bounceme.chronos.eventsourcingcqrs.query.model.Post;

public interface QueryService {

	Post getPost(String id);

}