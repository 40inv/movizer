package prfaz.movizer.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import prfaz.movizer.utils.LocalDateDeserializer;
import prfaz.movizer.utils.LocalDateSerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Films")
@Table(name = "films")
@Data
public class Film implements Serializable {

    public static Film EMPTY = new Film();

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "imdbUrl", nullable = false)
    private URL imdbUrl;
    @Column(name = "kpUrl", nullable = false)
    private URL kpUrl;
    @Column(name = "mood", nullable = false)
    private String mood;
    @Column(name = "imdbRating")
    private Double imdbRating;

    @Column(name = "releaseDate", nullable = false)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate releaseDate;

    @OneToMany(mappedBy="film")
    private Set<FilmInfoEng> filmInfoEng;
    @OneToMany(mappedBy="film")
    private Set<FilmInfoRu> filmInfoRu;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "film_genres",
            joinColumns = { @JoinColumn(name = "film_id") },
            inverseJoinColumns = { @JoinColumn(name = "genre_id") })
    Set <Genre> genres = new HashSet<Genre>();

    public long getFilmId() { return id; }
    public URL getImdbUrl() { return imdbUrl; }
    public URL getKpUrl() { return  kpUrl; }
    public String getMood() { return  mood; }
    public LocalDate getReleaseDate() { return releaseDate; }
    public Set <Genre> getGenres() { return genres; }
    public Set<FilmInfoEng> getFilmInfoEng() { return  filmInfoEng; }
    public Set<FilmInfoRu> getFilmInfoRu() { return filmInfoRu; }
    public Double getImdbRating() { return  imdbRating; }


}