package prfaz.movizer.model;

import javax.persistence.*;
import lombok.Data;

@Entity(name = "Genres")
@Table(name = "genres")
@Data
public class Genre {

    @Id
    @Column(name="genre_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long genreId;
    @Column(name = "genre", nullable = false)
    private String genreName;

    public long getId(){ return genreId; }
    public String getGenreName(){ return genreName; }

}
