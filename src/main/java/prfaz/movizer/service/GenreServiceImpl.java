package prfaz.movizer.service;

import org.springframework.stereotype.Service;
import prfaz.movizer.dao.GenreRepository;

@Service
public class GenreServiceImpl implements GenreService  {
    private GenreRepository repository;
}