package net.bounceme.chronos.eventsourcingcqrs.sync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import net.bounceme.chronos.eventsourcingcqrs.sync.service.SyncService;

@RestController
public class SyncController {

	@Autowired
	private SyncService syncService;

	@PostMapping("/sync")
	public void sync() {
		syncService.sync();
	}
}
