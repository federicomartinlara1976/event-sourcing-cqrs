package net.bounceme.chronos.eventsourcingcqrs.query.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import net.bounceme.chronos.eventsourcingcqrs.query.model.Post;
import net.bounceme.chronos.eventsourcingcqrs.query.service.QueryService;

@RestController
public class MongoController {

	@Autowired
	private QueryService queryService;

	@GetMapping("/post/{id}")
	public Post getPost(@PathVariable String id) {
		return queryService.getPost(id);
	}
}
