package prfaz.movizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prfaz.movizer.dao.FilmRepository;
import prfaz.movizer.exception.InvalidFimFilter;
import prfaz.movizer.model.Film;
import prfaz.movizer.model.FilmRequest;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService  {
    private FilmRepository filmRepository;

    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public Film getFilteredRandomFilm(FilmRequest request) {

            List<Film> queryResult = filmRepository.findFilmsUsingFilters(request.getGenreIds(), request.getMood(), request.getImdbRating(),
                    request.getStartDate(), request.getEndDate());

            long qty = queryResult.size();
            if (qty == 0) throw new InvalidFimFilter("No films found by this filter");
            int idx = (int) (Math.random() * qty);
            return queryResult.get(idx);

    }
}
