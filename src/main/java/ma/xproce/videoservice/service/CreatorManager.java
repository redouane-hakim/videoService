package ma.xproce.videoservice.service;

import ma.xproce.videoservice.dtos.CreatorRequest;
import ma.xproce.videoservice.entities.Creator;
import ma.xproce.videoservice.mappers.CreatorMapper;
import ma.xproce.videoservice.repositories.CreatorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreatorManager {

    private final CreatorRepository creatorRepository;
    private final CreatorMapper creatorMapper;

    public CreatorManager(CreatorRepository creatorRepository, CreatorMapper creatorMapper) {
        this.creatorRepository = creatorRepository;
        this.creatorMapper = creatorMapper;
    }

    public Creator saveCreator(CreatorRequest request) {
        return creatorRepository.save(creatorMapper.toEntity(request));
    }

    public List<Creator> findAll() {
        return creatorRepository.findAll();
    }

    public Creator findById(Long id) {
        return creatorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Creator %s not found", id)));
    }
}