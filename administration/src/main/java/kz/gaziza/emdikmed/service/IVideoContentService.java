package kz.gaziza.emdikmed.service;

import kz.gaziza.emdikmed.model.VideoContent;
import kz.gaziza.emdikmed.model.custom.VideoResponseDTO;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface IVideoContentService {

    VideoResponseDTO uploadFile(MultipartFile multipartFile) throws IOException;

    String deleteFile(String id);

    void delete(String id);

    ResponseEntity<InputStreamResource> downloadImage(String imageId) throws IOException;

    VideoContent create(VideoContent videoContent);

    List<VideoContent> getAll();
}
