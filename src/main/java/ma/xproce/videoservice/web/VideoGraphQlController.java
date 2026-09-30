package ma.xproce.videoservice.web;

import ma.xproce.videoservice.dtos.CreatorRequest;
import ma.xproce.videoservice.dtos.CreatorResponse;
import ma.xproce.videoservice.dtos.VideoRequest;
import ma.xproce.videoservice.dtos.VideoResponse;
import ma.xproce.videoservice.mappers.CreatorMapper;
import ma.xproce.videoservice.mappers.VideoMapper;
import ma.xproce.videoservice.service.CreatorManager;
import ma.xproce.videoservice.service.VideoManager;
import ma.xproce.videoservice.entities.Creator;
import ma.xproce.videoservice.entities.Video;
import ma.xproce.videoservice.repositories.CreatorRepository;
import ma.xproce.videoservice.repositories.VideoRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import ma.xproce.videoservice.dtos.CreatorRequest;
import ma.xproce.videoservice.entities.Creator;
import ma.xproce.videoservice.entities.Video;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import reactor.core.publisher.Flux;

import java.util.Random;
import java.util.stream.Stream;

import org.springframework.stereotype.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class VideoGraphQlController {

    private final CreatorManager creatorManager;
    private final VideoManager videoManager;
    private final CreatorMapper creatorMapper;
    private final VideoMapper videoMapper;

    public VideoGraphQlController(CreatorManager creatorManager, VideoManager videoManager,
                                  CreatorMapper creatorMapper, VideoMapper videoMapper) {
        this.creatorManager = creatorManager;
        this.videoManager = videoManager;
        this.creatorMapper = creatorMapper;
        this.videoMapper = videoMapper;
    }

    @QueryMapping
    public List<VideoResponse> videoList() {
        return videoManager.findAll().stream().map(videoMapper::toResponse).toList();
    }

    @QueryMapping
    public VideoResponse videoById(@Argument Long id) {
        return videoMapper.toResponse(videoManager.findById(id));
    }

    @QueryMapping
    public List<CreatorResponse> creatorList() {
        return creatorManager.findAll().stream().map(creatorMapper::toResponse).toList();
    }

    @QueryMapping
    public CreatorResponse creatorById(@Argument Long id) {
        return creatorMapper.toResponse(creatorManager.findById(id));
    }

    @MutationMapping
    public CreatorResponse saveCreator(@Argument CreatorRequest creator) {
        return creatorMapper.toResponse(creatorManager.saveCreator(creator));
    }

    @MutationMapping
    public VideoResponse saveVideo(@Argument VideoRequest video) {
        return videoMapper.toResponse(videoManager.saveVideo(video));
    }

    @SubscriptionMapping
    public Flux<Video> notifyVideoChange() {
        return Flux.fromStream(
        Stream.generate(() -> {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Random random = new Random();
        CreatorRequest creatorRequest = CreatorRequest.builder().name("x" + new Random().nextInt()).email("x@gmail.com").build();
        Creator creator = creatorManager.saveCreator(creatorRequest);
        Video video = videoManager.findById(1L);
        video.setCreator(creator);
        videoManager.updateVideo(video);
        return video;
        }));
}
}