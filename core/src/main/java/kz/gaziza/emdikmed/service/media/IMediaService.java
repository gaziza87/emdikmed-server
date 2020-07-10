package kz.gaziza.emdikmed.service.media;

import kz.gaziza.emdikmed.model.media.Media;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IMediaService {

    Page<Media> readPageable(Map<String, String> allRequestParams);
    Page<Media> searchPageable(Map<String, String> allRequestParams);
    List<Media> readIterable();
    List<Media> readByMediaCategoryId(String categoryId);
    Media readOne(String id);
    Media create(Media media);
    Media update(Media media);
    void delete(String id);


}
