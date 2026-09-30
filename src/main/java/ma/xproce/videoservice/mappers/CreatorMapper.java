package ma.xproce.videoservice.mappers;

import ma.xproce.videoservice.dtos.CreatorRequest;
import ma.xproce.videoservice.dtos.CreatorResponse;
import ma.xproce.videoservice.entities.Creator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CreatorMapper {

    private final ModelMapper modelMapper;

    public CreatorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Creator toEntity(CreatorRequest request) {
        return modelMapper.map(request, Creator.class);
    }

    public CreatorResponse toResponse(Creator creator) {
        return modelMapper.map(creator, CreatorResponse.class);
    }
}