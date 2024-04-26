package com.example.movie.service;

import com.example.movie.Entity.Movie;
import com.example.movie.Entity.MovieType;
import com.example.movie.Entity.Rate;
import com.example.movie.payload.response.MessageResponse;
import com.example.movie.repository.MovieRepository;
import com.example.movie.repository.MovieTypeRepository;
import com.example.movie.repository.RateRepository;
import com.example.movie.util.UploadImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieTypeRepository movieTypeRepository;

    @Autowired
    private RateRepository rateRepository;

    public Movie addMovie(Movie movie, MultipartFile imageMovie, MultipartFile imageHero) throws IOException {
        Optional<MovieType> movieTypeOptional = movieTypeRepository.findById(movie.getMovieType().getId());
        if (movieTypeOptional.isEmpty()) {
            throw new MessageResponse("Thể loại phim không tồn tại");
        }

        Optional<Rate> rateOptional = rateRepository.findById(movie.getRate().getId());
        if (rateOptional.isEmpty()) {
            throw new MessageResponse("Đánh giá phim không tồn tại");
        }

        movie.setIsActive(true);
        movie.setMovieTypeId(movieTypeOptional.get().getId());
        movie.setRateId(rateOptional.get().getId());

        String imageName = StringUtils.cleanPath(imageMovie.getOriginalFilename());
        String imageHeroName = StringUtils.cleanPath(imageHero.getOriginalFilename());

        movie.setImage(imageName);
        movie.setHeroImage(imageHeroName);

        String imageMovieDir = "images/movies/id-" + movie.getId() + "/" + "movieImage";
        String imageHeroDir = "images/movies/id-" + movie.getId() + "/" + "heroImage";

        UploadImageUtil.uploadImage(imageMovieDir, imageName, imageMovie);
        UploadImageUtil.uploadImage(imageHeroDir, imageHeroName, imageHero);

        return movieRepository.save(movie);
    }

    public Movie upadteMovie(Movie movie, MultipartFile imageMovie, MultipartFile imageHero) throws IOException {
        Optional<Movie> movieOptional = movieRepository.findById(movie.getId());
        if (movieOptional.isEmpty()) {
            throw new MessageResponse("Phim không tồn tại");
        }

        Optional<MovieType> movieTypeOptional = movieTypeRepository.findById(movie.getMovieType().getId());
        if (movieTypeOptional.isEmpty()) {
            throw new MessageResponse("Thể loại phim không tồn tại");
        }

        Optional<Rate> rateOptional = rateRepository.findById(movie.getRate().getId());
        if (rateOptional.isEmpty()) {
            throw new MessageResponse("Đánh giá phim không tồn tại");
        }

        movie.setIsActive(movieOptional.get().getIsActive());
        movie.setMovieTypeId(movieTypeOptional.get().getId());
        movie.setRateId(rateOptional.get().getId());

        if (imageMovie == null) {
            movie.setImage(movieOptional.get().getImage());
        } else {
            String imageMovieName = StringUtils.cleanPath(imageMovie.getOriginalFilename());
            movie.setImage(imageMovieName);
            String imageMovieDir = "images/movies/id-" + movie.getId() + "/" + "movieImage";
            UploadImageUtil.uploadImage(imageMovieDir, imageMovieName, imageMovie);
        }

        if (imageHero == null) {
            movie.setHeroImage(movieOptional.get().getHeroImage());
        } else {
            String imageHeroName = StringUtils.cleanPath(imageHero.getOriginalFilename());
            movie.setImage(imageHeroName);
            String imageHeroDir = "images/movies/id-" + movie.getId() + "/" + "heroImage";
            UploadImageUtil.uploadImage(imageHeroDir, imageHeroName, imageHero);
        }

        return movieRepository.save(movie);
    }

    public Movie deleteMovie(Integer movieId) {
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if (movieOptional.isEmpty()) {
            throw new MessageResponse("Phim không tồn tại");
        }

        movieOptional.get().setIsActive(false);
        return movieRepository.save(movieOptional.get());
    }

    public List<Movie> findMovieByCinema(Integer cinemaId, String nameOfCinema) {
        return movieRepository.findMovieByCinema(cinemaId, nameOfCinema);
    }

    public List<Movie> findMovieByRoom(Integer roomId, String nameOfRoom) {
        return movieRepository.findMovieByRoom(roomId, nameOfRoom);
    }
}
