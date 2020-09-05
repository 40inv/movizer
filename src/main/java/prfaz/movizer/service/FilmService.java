package prfaz.movizer.service;

import prfaz.movizer.model.Film;
import prfaz.movizer.model.FilmRequest;

public interface FilmService {
    public Film getFilteredRandomFilm(FilmRequest request);
}
