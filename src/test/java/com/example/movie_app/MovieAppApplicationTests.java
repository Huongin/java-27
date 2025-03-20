package com.example.movie_app;

import com.example.movie_app.entity.Movie;
import com.example.movie_app.model.enums.MovieType;
import com.example.movie_app.repository.MovieRepository;
import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@SpringBootTest
class MovieAppApplicationTests {

	@Autowired
	private MovieRepository movieRepository;

	@Test
	void save_movies() {
		Faker faker = new Faker();
		Slugify slugify = Slugify.builder().build();
		Random rd = new Random();

		for (int i = 0; i < 150; i++) {
			//Tạo entity
			String name = faker.funnyName().name();
			String thumbnail = "https://placehold.co/600x400?text=" + name.substring(0,1).toLowerCase();
			Boolean status = faker.bool().bool();

			int rdIndexMovieType = rd.nextInt(MovieType.values().length);
			MovieType type = MovieType.values()[rdIndexMovieType];

			Movie movie = Movie.builder()
					.name(name)
					.slug(slugify.slugify(name))
					.description(faker.lorem().paragraph())
					.thumbnail(thumbnail)
					.releaseYear(faker.number().numberBetween(1990,2021))
					.trailer("https://www.youtube.com/embed/W_0AMP9yO1w?si=JcCeGorHalCHKCPl")
					.status(status)
					.rating(faker.number().randomDouble(1,5,10))
					.type(type)
					.createdAt(LocalDateTime.now())
					.updatedAt(LocalDateTime.now())
					.publishedAt(status ? LocalDateTime.now():null)
					.build();

			//Luu vào DB
			movieRepository.save(movie);

		}
	}
	@Test
	void testQuery(){
//		Movie movie = movieRepository.findByName("Annie Howe");
//		System.out.println(movie);

		// sắp xếp
//		List<Movie> movieSortByRating = movieRepository
//				.findByRatingLessThan(8.0, Sort.by(Sort.Direction.DESC,"rating"));
//		movieSortByRating.forEach(movie1 -> System.out.println(movie1.getName() + "-" + movie1.getRating()));

		// Phân trang
		Pageable pageable = PageRequest.of(19,5, Sort.by("rating").descending());
		Page<Movie> moviePage = movieRepository.findByNameContaining("a",pageable);
		System.out.println("Total pages: " + moviePage.getTotalPages());
		System.out.println("Total elements: "+ moviePage.getTotalElements());
		moviePage.getContent().forEach(movie -> System.out.println(movie.getName() + "-" +movie.getRating()));

	}

}
