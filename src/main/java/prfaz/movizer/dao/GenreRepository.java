package prfaz.movizer.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import prfaz.movizer.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    long count();
    Page<Genre> findAll(Pageable pageable);

}