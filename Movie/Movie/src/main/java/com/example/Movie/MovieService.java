package com.example.Movie;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie newMovie(Movie movie){
        return movieRepository.save(movie);
    }
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }


    public void deleteMovie(Long id){
        movieRepository.deleteById(id);
    }

    public Movie updateMovie(Long id, Movie updatedMovie){

        Movie targetMovie = getMovieById(id);

        if(targetMovie != null){

            targetMovie = updatedMovie;
            return newMovie(targetMovie);
        }else{

            throw new EntityNotFoundException("Movie not found");
        }
    }
}