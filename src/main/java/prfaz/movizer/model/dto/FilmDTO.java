package prfaz.movizer.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import prfaz.movizer.model.Film;
import prfaz.movizer.model.Genre;
import prfaz.movizer.utils.LocalDateDeserializer;
import prfaz.movizer.utils.LocalDateSerializer;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Getter
public class FilmDTO {

    private long id;
    private URL imdbUrl;
    private Double imdbRating;
    private URL imageUrl;
    private URL trailerUrl;
    private List<Long> genres;
    private String mood;
    private String name;
    private String description;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate releaseDate;

    public FilmDTO(){
        super();
    }

    public FilmDTO(Film film) {
        this.id = film.getFilmId();
        this.imdbUrl = film.getImdbUrl();
        this.imdbRating = film.getImdbRating();
        this.genres = createListOfFilmGenres(film.getGenres());
        this.mood = film.getMood();
        this.releaseDate = film.getReleaseDate();

    }
    private List<Long> createListOfFilmGenres( Set<Genre> genreSet){
        List<Long> genresList = new ArrayList<>();

        Iterator<Genre> itr = genreSet.iterator();

        while(itr.hasNext()){
            genresList.add(itr.next().getId());
        }
        return genresList;
    }

    public void setImageUrl(URL imageUrl) { this.imageUrl = imageUrl; }
    public void setTrailerUrl(URL trailerUrl) { this.trailerUrl = trailerUrl; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }

}