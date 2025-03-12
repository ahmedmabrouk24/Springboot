package com.global.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.global.dto.MovieDTO;
import com.global.dto.MovieSummaryDTO;
import com.global.dto.SearchResultDTO;
import com.global.entity.Movie;
import com.global.entity.UserRating;
import com.global.mapper.MovieMapper;
import com.global.repository.MovieRepository;
import com.global.repository.UserRatingRepository;
import com.global.security.JwtTokenUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserMoviesService {
	@Autowired
    private final MovieRepository movieRepository;

    @Autowired
    private final UserRatingRepository userRatingRepository;
    
    @Autowired
    private final JwtTokenUtils jwtTokenUtils; // Utility to decode JWT token

    // Fetch movie by ID and include the user's rating
    public MovieDTO getMovieById(String imdbID, String authorizationHeader) {
        Movie movie = movieRepository.findByImdbID(imdbID)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        String token = authorizationHeader.substring(7); // Remove "Bearer " prefix
        Long userId = jwtTokenUtils.getUserIdFromToken(token);
        
        // Get user's rating for this movie
        UserRating userRating = userRatingRepository.findByUser_IdAndMovie_ImdbID(userId, imdbID);

        // Convert movie to MovieDTO and add rating
        MovieDTO movieDTO = MovieMapper.toMovieDTO(movie);
        if (userRating != null) {
            movieDTO.setUserRating(userRating.getRating());
        }

        return movieDTO;
    }

    // Fetch movies by title and include the user's rating
    public SearchResultDTO getMoviesByTitle(int page_no, String movieName, String authorizationHeader) {
        
        // Pagination setup
        PageRequest pageable = PageRequest.of(page_no - 1, 10); // Page starts at 0, so subtract 1 from the page_no

        // Fetch movies by title with pagination
        Page<Movie> moviePage = movieRepository.findByTitleContainingIgnoreCase(movieName, pageable);
        return searchResultDTO(moviePage, authorizationHeader);
    }

    // Fetch all movies and include the user's rating
    public SearchResultDTO getAllMovies(int page_no, String authorizationHeader) {
    	// Pagination setup
        PageRequest pageable = PageRequest.of(page_no - 1, 10); // Page starts at 0, so subtract 1 from the page_no
        
        Page<Movie> moviePage = movieRepository.findAll(pageable);
        return searchResultDTO(moviePage, authorizationHeader);
    }
    
    // helper method
    private SearchResultDTO searchResultDTO(Page<Movie> moviePage, String authorizationHeader) {
    	
    	// Extract the token from the authorization header
        String token = authorizationHeader.substring(7); // Remove "Bearer " prefix
        Long userId = jwtTokenUtils.getUserIdFromToken(token); // Get the user ID from the JWT token
        
    	// Map the movies and include the user's rating
        List<MovieSummaryDTO> movieSummaryDTOs = moviePage.getContent().stream()
            .map(movie -> {
                MovieSummaryDTO movieSummaryDTO = new MovieSummaryDTO();
                movieSummaryDTO.setTitle(movie.getTitle());
                movieSummaryDTO.setYear(movie.getYear());
                movieSummaryDTO.setImdbID(movie.getImdbID());
                movieSummaryDTO.setPoster(movie.getPoster());
                
                // Get user's rating for this movie
                UserRating userRating = userRatingRepository.findByUser_IdAndMovie_ImdbID(userId, movie.getImdbID());
                if (userRating != null) {
                    movieSummaryDTO.setUserRating(userRating.getRating());
                } else {
                    movieSummaryDTO.setUserRating(0); // Default to 0 if no rating is found
                }
                return movieSummaryDTO;
            })
            .collect(Collectors.toList());

        // Return SearchResultDTO with pagination information
        return new SearchResultDTO(
            movieSummaryDTOs, 
            String.valueOf(moviePage.getTotalElements()), 
            "True" 
        );
    }
}
