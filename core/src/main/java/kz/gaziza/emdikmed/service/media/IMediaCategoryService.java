package kz.gaziza.emdikmed.service.media;

import kz.gaziza.emdikmed.model.media.MediaCategory;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IMediaCategoryService {

    Page<MediaCategory> readPageable(Map<String, String> allRequestParams);
    Page<MediaCategory> searchPageable(Map<String, String> allRequestParams);
    List<MediaCategory> readIterable();
    MediaCategory readOne(String id);
    MediaCategory readOneByCode(String code);
    MediaCategory create(MediaCategory mediaCategory);
    MediaCategory update(MediaCategory mediaCategory);
    void delete(String id);

}
