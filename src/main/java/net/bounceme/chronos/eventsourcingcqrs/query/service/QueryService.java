package net.bounceme.chronos.eventsourcingcqrs.query.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bounceme.chronos.eventsourcingcqrs.exceptions.NotFoundException;
import net.bounceme.chronos.eventsourcingcqrs.query.model.Post;
import net.bounceme.chronos.eventsourcingcqrs.query.repository.PostRepository;

@Service
public class QueryService {

	@Autowired
	PostRepository postRepository;

	public Post getPost(String id) {
		return postRepository.findById(id).orElseThrow(() -> new NotFoundException());
	}
}
