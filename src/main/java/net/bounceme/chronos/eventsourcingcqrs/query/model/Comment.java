package net.bounceme.chronos.eventsourcingcqrs.query.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {

	private String id;

	private String content;

	private List<Reaction> reactions;
}
