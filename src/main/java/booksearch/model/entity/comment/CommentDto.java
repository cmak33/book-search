package booksearch.model.entity.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

    public CommentDto(Comment comment, boolean isUserThePublisher){
        this.isUserThePublisher = isUserThePublisher;
        id = comment.getId();
        message = comment.getMessage();
        publisherUsername = comment.getPublisherUsername();
        movieId = comment.getMovieId();
    }

    private Long id;
    private String message;
    private String publisherUsername;
    private Long movieId;
    private boolean isUserThePublisher;
}
