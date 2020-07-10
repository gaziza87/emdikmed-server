package kz.gaziza.emdikmed.repository.media;

import kz.gaziza.emdikmed.constant.State;
import kz.gaziza.emdikmed.model.media.Media;
import kz.gaziza.emdikmed.repository.ResourceUtilRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends ResourceUtilRepository<Media, String> {

    @Query(value = "{ $or: [ " +
            "{ 'id' : {$regex:'?0', $options: 'i'} }, " +
            "{ 'name' : {$regex: '?0', $options: 'i'} }, " +
            "{ 'description' : {$regex:'?0', $options: 'i'} }, " +
            "{ 'mediaCategoryId' : {$regex:'?0', $options: 'i'} }, " +
            "{ 'contentId' : {$regex:'?0', $options: 'i'} }, " +
            "{ 'state' : {$regex:'?0', $options: 'i'} }, " +
            "] }")
    Page<Media> query(String searchString, Pageable pageable);

    @Query(value = "{id:'?0', state: '?1'}")
    Media getById(String id, State state);

    @Query("{categoryId: '?0', state: '?1'}")
    List<Media> getAllByMediaCategoryId(String categoryId, State state);

}
