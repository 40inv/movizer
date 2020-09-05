package prfaz.movizer.model.dto;

import prfaz.movizer.model.Film;

public class FilmEngDTO extends FilmDTO {

    public FilmEngDTO() {
        super();
    }

    public FilmEngDTO(Film film) {
        super(film);
        this.setTrailerUrl(film.getFilmInfoEng().iterator().next().getTrailerUrl());
        this.setImageUrl(film.getFilmInfoEng().iterator().next().getImageUrl());
        this.setName(film.getFilmInfoEng().iterator().next().getName());
        this.setDescription(film.getFilmInfoEng().iterator().next().getDescription());
    }

}
