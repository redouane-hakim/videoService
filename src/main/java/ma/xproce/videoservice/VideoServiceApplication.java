package ma.xproce.videoservice;

import ma.xproce.videoservice.entities.Creator;
import ma.xproce.videoservice.entities.Video;
import ma.xproce.videoservice.repositories.CreatorRepository;
import ma.xproce.videoservice.repositories.VideoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class VideoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CreatorRepository creatorRepository, VideoRepository videoRepository) {
        return args -> {
            List<Creator> creators = List.of(
                    Creator.builder().name("Badr").email("badr@gmail.com").build(),
                    Creator.builder().name("Sara").email("sara@gmail.com").build(),
                    Creator.builder().name("Yassine").email("yassine@gmail.com").build()
            );
            creatorRepository.saveAll(creators);

            List<Video> videos = List.of(
                    Video.builder().name("GraphQL introduction").url("https://youtube.com/v1")
                            .description("Basics of GraphQL").datePublication(new Date())
                            .creator(creators.get(0)).build(),
                    Video.builder().name("Spring for GraphQL").url("https://youtube.com/v2")
                            .description("Queries with Spring").datePublication(new Date())
                            .creator(creators.get(1)).build(),
                    Video.builder().name("Mutations and subscriptions").url("https://youtube.com/v3")
                            .description("Write operations and real-time").datePublication(new Date())
                            .creator(creators.get(1)).build(),
                    Video.builder().name("Docker for Java devs").url("https://youtube.com/v4")
                            .description("Dev containers").datePublication(new Date())
                            .creator(creators.get(2)).build()
            );
            videoRepository.saveAll(videos);
        };
    }
}
