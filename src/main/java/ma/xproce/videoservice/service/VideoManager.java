package ma.xproce.videoservice.service;

import ma.xproce.videoservice.dtos.VideoRequest;
import ma.xproce.videoservice.entities.Video;
import ma.xproce.videoservice.mappers.VideoMapper;
import ma.xproce.videoservice.repositories.CreatorRepository;
import ma.xproce.videoservice.repositories.VideoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoManager {

    private final VideoRepository videoRepository;
    private final CreatorRepository creatorRepository;
    private final VideoMapper videoMapper;

    public VideoManager(VideoRepository videoRepository,
                        CreatorRepository creatorRepository,
                        VideoMapper videoMapper) {
        this.videoRepository = videoRepository;
        this.creatorRepository = creatorRepository;
        this.videoMapper = videoMapper;
    }

    public Video saveVideo(VideoRequest request) {
        Video video = videoMapper.toEntity(request);
        if (video.getCreator() != null) {
            video.setCreator(creatorRepository.save(video.getCreator()));
        }
        return videoRepository.save(video);
    }

    public List<Video> findAll() {
        return videoRepository.findAll();
    }

    public Video findById(Long id) {
        return videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Video %s not found", id)));
    }

    public Video updateVideo(Video video) {
        return videoRepository.save(video);
    }
}