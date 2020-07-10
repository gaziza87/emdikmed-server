package kz.gaziza.emdikmed.service.media.impl;

import kz.gaziza.emdikmed.constant.State;
import kz.gaziza.emdikmed.model.media.Media;
import kz.gaziza.emdikmed.repository.media.MediaRepository;
import kz.gaziza.emdikmed.service.media.IMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class MediaService implements IMediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Override
    public Page<Media> readPageable(Map<String, String> allRequestParams) {
        return null;
    }

    @Override
    public Page<Media> searchPageable(Map<String, String> allRequestParams) {
        return null;
    }

    @Override
    public List<Media> readIterable() {
        return mediaRepository.findAll();
    }

    @Override
    public List<Media> readByMediaCategoryId(String categoryId) {
        return mediaRepository.getAllByMediaCategoryId(categoryId, State.ACTIVE);
    }

    @Override
    public Media readOne(String id) {
        return mediaRepository.getById(id, State.ACTIVE);
    }

    @Override
    public Media create(Media media) {
        media.setState(State.ACTIVE);
        return mediaRepository.save(media);
    }

    @Override
    public Media update(Media media) {
        media.setState(State.ACTIVE);
        return mediaRepository.save(media);
    }

    @Override
    public void delete(String id) {
        Media media = mediaRepository.getById(id, State.ACTIVE);
        media.setState(State.DELETED);
        mediaRepository.save(media);
    }
}
