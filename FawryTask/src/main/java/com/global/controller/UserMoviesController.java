package com.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.dto.MovieDTO;
import com.global.dto.SearchResultDTO;
import com.global.service.UserMoviesService;

@RestController
@RequestMapping("/user/api/v1/movies")
public class UserMoviesController {

	@Autowired
	private UserMoviesService userMovieService;

	@GetMapping("/get-by-id/{imdbID}")
	public ResponseEntity<?> getMovieById(@PathVariable("imdbID") String imdbID,
			@RequestHeader("Authorization") String authorizationHeader) {
        try {
            MovieDTO movieDTO = userMovieService.getMovieById(imdbID, authorizationHeader);
        	return ResponseEntity.ok(movieDTO);
        } catch (RuntimeException ex) {
            // Handle case where movie is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie with id : " + imdbID + " not found");
        }
    }

	@GetMapping("/get-by-title/{page_no}/{movieName}")
	public ResponseEntity<SearchResultDTO> getMoviesByTitle(@PathVariable("page_no") int page_no,
			@PathVariable("movieName") String movieName, @RequestHeader("Authorization") String authorizationHeader) {
		SearchResultDTO searchResultDTO = userMovieService.getMoviesByTitle(page_no, movieName, authorizationHeader);
		return ResponseEntity.ok(searchResultDTO);
	}

	@GetMapping("/{page_no}/get-all")
	public ResponseEntity<SearchResultDTO> getAllMovies(@PathVariable("page_no") int page_no,
			@RequestHeader("Authorization") String authorizationHeader) {
		SearchResultDTO searchResultDTO = userMovieService.getAllMovies(page_no, authorizationHeader);
		return ResponseEntity.ok(searchResultDTO);
	}
}