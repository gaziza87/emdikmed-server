package kz.gaziza.emdikmed.repository.media;

import kz.gaziza.emdikmed.constant.State;
import kz.gaziza.emdikmed.model.media.MediaCategory;
import kz.gaziza.emdikmed.repository.ResourceUtilRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaCategoryRepository extends ResourceUtilRepository<MediaCategory, String> {

    @Query(value = "{ $or: [ " +
            "{ 'id' : {$regex:'?0', $options: 'i'} }, " +
            "{ 'name' : {$regex: '?0', $options: 'i'} }, " +
            "{ 'description' : {$regex:'?0', $options: 'i'} }, " +
            "{ 'code' : {$regex:'?0', $options: 'i'} }, " +
            "{ 'state' : {$regex:'?0', $options: 'i'} }, " +
            "] }")
    Page<MediaCategory> query(String searchString, Pageable pageable);

    @Query(value = "{id:'?0', state: '?1'}")
    MediaCategory getById(String id, State state);

    @Query("{code: '?0', state: '?1'}")
    MediaCategory getByCode(String code, State state);

}
