package net.bounceme.chronos.eventsourcingcqrs.query.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {

	@Id
	private String id;

	private String content;

	private List<Comment> comments;
}
