package prfaz.movizer.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import prfaz.movizer.utils.LocalDateDeserializer;
import prfaz.movizer.utils.LocalDateSerializer;

import java.time.LocalDate;
import java.util.List;

public class FilmRequest {

    private List<Long> genreIds;
    private String mood;
    private Double imdbRating;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate startDate;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate endDate;

    public List<Long> getGenreIds(){ return genreIds; }
    public String getMood(){ return mood; }
    public Double getImdbRating() { return imdbRating; }
    public LocalDate getStartDate() { return  startDate; }
    public LocalDate getEndDate() { return  endDate; }


}