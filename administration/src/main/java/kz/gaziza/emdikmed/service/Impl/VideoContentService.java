package kz.gaziza.emdikmed.service.Impl;

import kz.gaziza.emdikmed.model.VideoContent;
import kz.gaziza.emdikmed.model.custom.VideoResponseDTO;
import kz.gaziza.emdikmed.repository.VideoContentRepository;
import kz.gaziza.emdikmed.service.IVideoContentService;
import kz.gaziza.emdikmed.service.docs.IMongoDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class VideoContentService implements IVideoContentService {

    @Autowired
    private IMongoDocService iMongoDocService;

    @Autowired
    private VideoContentRepository videoContentRepository;

    @Override
    public VideoResponseDTO uploadFile(MultipartFile multipartFile) throws IOException {
        System.out.println(multipartFile.getInputStream() + "         " + multipartFile.getContentType() + "         " + multipartFile.getOriginalFilename());
        String id = iMongoDocService.saveScanDoc(multipartFile.getInputStream(), multipartFile.getContentType(), multipartFile.getOriginalFilename());
        System.out.println("answer id:" + id);
        return new VideoResponseDTO(id);
    }

    @Override
    public String deleteFile(String id) {
        iMongoDocService.deleteScanDoc(id);
        return "DELETED";
    }

    @Override
    public void delete(String id) {
        videoContentRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<InputStreamResource> downloadImage(String imageId) throws IOException {
        GridFsResource resource = getResult(imageId);
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(resource.getContentType()))
                .body(new InputStreamResource(resource.getInputStream()));
    }

    @Override
    public VideoContent create(VideoContent videoContent) {
        return videoContentRepository.save(videoContent);
    }

    @Override
    public List<VideoContent> getAll() {
        return videoContentRepository.findAll();
    }

    private GridFsResource getResult(String imageId) throws IOException {
        return iMongoDocService.downloadScanDoc(imageId);
    }
}
