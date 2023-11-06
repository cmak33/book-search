package booksearch.model.entity.comment;

import booksearch.model.entity.interfaces.Entity;
import lombok.Data;

@Data
public class Comment implements Entity<Long> {

    private Long id;
    private String message;
    private String publisherUsername;
    private Long movieId;
}
