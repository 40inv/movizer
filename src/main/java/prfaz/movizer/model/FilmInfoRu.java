package prfaz.movizer.model;

import javax.persistence.*;
import java.net.URL;

@Entity
@Table(name = "ru")
public class FilmInfoRu {
    @Id
    @Column(name="id")
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false, columnDefinition="varchar(1000)")
    private String description;
    @Column(name = "imageUrl")
    private URL imageUrl;
    @Column(name = "trailerUrl")
    private URL trailerUrl;


    @ManyToOne
    @JoinColumn(name="film_id", nullable=false)
    private Film film;

    public String getName() { return  name; }
    public String getDescription() { return description; }
    public URL getImageUrl() {return imageUrl; }
    public URL getTrailerUrl() { return trailerUrl; }
}
