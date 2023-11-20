package booksearch.model.entity.movie;

import booksearch.model.entity.interfaces.Entity;
import lombok.Data;

import java.sql.Date;


@Data
public class Movie implements Entity<Long> {

    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private Date releaseDate;
}
