package prfaz.movizer.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import prfaz.movizer.model.Film;

import java.time.LocalDate;
import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long>, JpaSpecificationExecutor<Film> {

    long count();
    Page<Film> findAll(Pageable pageable);

    @Query("SELECT distinct films FROM Films films " +
            "join films.genres genr " +
            "where (films.releaseDate <= :toDate and films.releaseDate >= :fromDate) and" +
            "((:mood is null or films.mood = :mood) " +
            "and ((genr.genreId in :genreIds or :genreIds is null) " +
            "and (films.imdbRating >= : imdbRating or :imdbRating is null)))")
    List<Film> findFilmsUsingFilters(@Param("genreIds") List<Long> genreIds,
                                     @Param("mood") String mood,
                                     @Param("imdbRating") Double imdbRating,
                                     @Param("fromDate") LocalDate fromDate,
                                     @Param("toDate") LocalDate toDate);

}