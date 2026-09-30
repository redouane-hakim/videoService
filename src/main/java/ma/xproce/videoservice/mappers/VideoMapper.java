package ma.xproce.videoservice.mappers;

import ma.xproce.videoservice.dtos.VideoRequest;
import ma.xproce.videoservice.dtos.VideoResponse;
import ma.xproce.videoservice.entities.Video;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class VideoMapper {

    private static final String DATE_FORMAT = "dd/MM/yyyy";

    private final ModelMapper modelMapper;
    private final CreatorMapper creatorMapper;

    public VideoMapper(ModelMapper modelMapper, CreatorMapper creatorMapper) {
        this.modelMapper = modelMapper;
        this.creatorMapper = creatorMapper;
    }

    public Video toEntity(VideoRequest request) {
        Video video = new Video();
        video.setName(request.getName());
        video.setUrl(request.getUrl());
        video.setDescription(request.getDescription());
        video.setDatePublication(parseDate(request.getDatePublication()));
        if (request.getCreator() != null) {
            video.setCreator(creatorMapper.toEntity(request.getCreator()));
        }
        return video;
    }

    public VideoResponse toResponse(Video video) {
        VideoResponse response = modelMapper.map(video, VideoResponse.class);
        response.setDatePublication(formatDate(video.getDatePublication()));
        return response;
    }

    private Date parseDate(String value) {
        if (value == null || value.isBlank()) return null;
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(value);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date '" + value + "', expected " + DATE_FORMAT);
        }
    }

    private String formatDate(Date date) {
        return date == null ? null : new SimpleDateFormat(DATE_FORMAT).format(date);
    }
}