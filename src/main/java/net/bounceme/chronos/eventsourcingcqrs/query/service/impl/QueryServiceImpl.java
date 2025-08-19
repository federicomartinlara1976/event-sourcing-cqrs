package net.bounceme.chronos.eventsourcingcqrs.query.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bounceme.chronos.eventsourcingcqrs.exceptions.NotFoundException;
import net.bounceme.chronos.eventsourcingcqrs.query.model.Post;
import net.bounceme.chronos.eventsourcingcqrs.query.repository.PostRepository;
import net.bounceme.chronos.eventsourcingcqrs.query.service.QueryService;

@Service
public class QueryServiceImpl implements QueryService {

	@Autowired
	PostRepository postRepository;

	@Override
	public Post getPost(String id) {
		return postRepository.findById(id).orElseThrow(() -> new NotFoundException());
	}
}
