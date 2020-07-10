package kz.gaziza.emdikmed.service.media.impl;

import kz.gaziza.emdikmed.constant.State;
import kz.gaziza.emdikmed.model.media.MediaCategory;
import kz.gaziza.emdikmed.repository.media.MediaCategoryRepository;
import kz.gaziza.emdikmed.service.media.IMediaCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MediaCategoryService implements IMediaCategoryService {

    @Autowired
    private MediaCategoryRepository mediaCategoryRepository;

    @Override
    public Page<MediaCategory> readPageable(Map<String, String> allRequestParams) {
        return null;
    }

    @Override
    public Page<MediaCategory> searchPageable(Map<String, String> allRequestParams) {
        return null;
    }

    @Override
    public List<MediaCategory> readIterable() {
        return mediaCategoryRepository.findAll();
    }

    @Override
    public MediaCategory readOne(String id) {
        return mediaCategoryRepository.getById(id, State.ACTIVE);
    }

    @Override
    public MediaCategory readOneByCode(String code) {
        return mediaCategoryRepository.getByCode(code, State.ACTIVE);
    }

    @Override
    public MediaCategory create(MediaCategory mediaCategory) {
        mediaCategory.setState(State.ACTIVE);
        return mediaCategoryRepository.save(mediaCategory);
    }

    @Override
    public MediaCategory update(MediaCategory mediaCategory) {
        mediaCategory.setState(State.ACTIVE);
        return mediaCategoryRepository.save(mediaCategory);
    }

    @Override
    public void delete(String id) {
        MediaCategory mediaCategory = mediaCategoryRepository.getById(id, State.ACTIVE);
        mediaCategory.setState(State.DELETED);
        mediaCategoryRepository.save(mediaCategory);


    }
}
