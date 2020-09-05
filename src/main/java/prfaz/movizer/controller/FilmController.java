package prfaz.movizer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prfaz.movizer.dao.FilmRepository;
import prfaz.movizer.model.Film;
import prfaz.movizer.model.FilmRequest;
import prfaz.movizer.model.dto.FilmEngDTO;
import prfaz.movizer.model.dto.FilmRuDTO;
import prfaz.movizer.service.FilmService;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.joining;

@CrossOrigin(origins =  "*" )
@RestController
@RequestMapping(path = "/film")
public class FilmController {

    private FilmRepository filmRepository;
    private FilmService filmService;
    private final Logger logger = LoggerFactory.getLogger(FilmController.class);


    @Autowired
    public FilmController(FilmRepository filmRepository, FilmService filmService) {
        this.filmRepository = filmRepository;
        this.filmService = filmService;

    }

    private void logHeaders(@RequestHeader HttpHeaders headers) {
        logger.info("Controller request headers {}",
                headers.entrySet()
                        .stream()
                        .map(entry -> String.format("%s->[%s]", entry.getKey(), String.join(",", entry.getValue())))
                        .collect(joining(","))
        );

    }

    @GetMapping(path = "/ru/{FilmId}")
    public ResponseEntity<FilmRuDTO> getRuFilmById(@RequestHeader HttpHeaders headers,
                                                   @PathVariable long FilmId) {
        logHeaders(headers);
        Film result = filmRepository.findById(FilmId).orElseGet(() -> Film.EMPTY);
        if (Film.EMPTY.equals(result)) {
            return new ResponseEntity<>(new FilmRuDTO(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new FilmRuDTO(result), HttpStatus.OK);

    }

    @GetMapping(path = "/eng/{FilmId}")
    public ResponseEntity<FilmEngDTO> getEngFilmById(@RequestHeader HttpHeaders headers,
                                                     @PathVariable long FilmId) {
        logHeaders(headers);
        Film result = filmRepository.findById(FilmId).orElseGet(() -> Film.EMPTY);
        if (Film.EMPTY.equals(result)) {
            return new ResponseEntity<>(new FilmEngDTO(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new FilmEngDTO(result), HttpStatus.OK);

    }


    @GetMapping(path = "/ru")
    public ResponseEntity<FilmRuDTO> getRandomRuFilm(@RequestHeader HttpHeaders headers) {
        logHeaders(headers);
        return ResponseEntity.ok(new FilmRuDTO(randomFilm()));

    }

    @GetMapping(path = "/eng")
    public ResponseEntity<FilmEngDTO> getRandomEngFilm(@RequestHeader HttpHeaders headers) {
        logHeaders(headers);
        return ResponseEntity.ok(new FilmEngDTO(randomFilm()));

    }

    @PostMapping(path = "/ru")
    public ResponseEntity<FilmRuDTO> getRandomFilmRuPost(@RequestHeader HttpHeaders headers,
                                                         @Valid @RequestBody FilmRequest request) {
        logHeaders(headers);
        return ResponseEntity.ok(new FilmRuDTO(filmService.getFilteredRandomFilm(request)));
    }

    @PostMapping(path = "/eng")
    public ResponseEntity<FilmEngDTO> getRandomFilmEngPost(@RequestHeader HttpHeaders headers,
                                                           @Valid @RequestBody FilmRequest request) {
        logHeaders(headers);
        return ResponseEntity.ok(new FilmEngDTO(filmService.getFilteredRandomFilm(request)));

    }

    public Film randomFilm() {
        long qty = filmRepository.count();
        int idx = (int)(Math.random() * qty);
        Page<Film> questionPage = filmRepository.findAll(PageRequest.of(idx, 1));
        Film film = null;
        if (questionPage.hasContent()) {
            film = questionPage.getContent().get(0);
        }
        return film;

    }

}