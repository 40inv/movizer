package prfaz.movizer.model.dto;

import prfaz.movizer.model.Film;

import java.net.URL;

public class FilmRuDTO extends FilmDTO {
    private URL kpUrl;

    public FilmRuDTO() {
        super();
    }

    public FilmRuDTO(Film film) {
        super(film);
        this.kpUrl = film.getKpUrl();
        this.setTrailerUrl(film.getFilmInfoRu().iterator().next().getTrailerUrl());
        this.setImageUrl(film.getFilmInfoRu().iterator().next().getImageUrl());
        this.setName(film.getFilmInfoRu().iterator().next().getName());
        this.setDescription(film.getFilmInfoRu().iterator().next().getDescription());
    }

    public URL getKpUrl() { return  kpUrl; }

}