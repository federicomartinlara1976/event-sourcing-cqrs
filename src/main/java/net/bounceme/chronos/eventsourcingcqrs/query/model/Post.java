package net.bounceme.chronos.eventsourcingcqrs.query.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document("posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Post {

	@Id
	@Field("_id")
	private String id;

	private String content;

	private List<Comment> comments;
}
