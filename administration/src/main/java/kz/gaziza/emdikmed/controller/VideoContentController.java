package kz.gaziza.emdikmed.controller;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import kz.gaziza.emdikmed.config.MongoConfig;
import kz.gaziza.emdikmed.model.VideoContent;
import kz.gaziza.emdikmed.service.IVideoContentService;
import kz.gaziza.emdikmed.service.docs.IMongoDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@RestController
@RequestMapping("/video")
@Api(tags = {"Video"}, description = "Управление медиафайлами", authorizations = {@Authorization(value = "bearerAuth")})
public class VideoContentController {

    @Autowired
    IVideoContentService iVideoContentService;

    @Autowired
    private GridFsTemplate gridFsTemplate;
    @Autowired
    private IMongoDocService iMongoDocService;
    @Autowired
    private MongoDbFactory mongoDbFactory;
    @Autowired
    private MongoConfig mongoConfig;

    @ApiOperation(value = "Возвращает id File - a", consumes = "multipart/form-data", tags = {"File"}, notes = "uploadDoc(file)")
    @RequestMapping(value = "/upload/file", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uploadDoc(HttpServletResponse response, HttpServletRequest request,
                            @RequestParam("file") MultipartFile imageFile) throws IOException {
//
        if (!(request instanceof MultipartHttpServletRequest)) {
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        }

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Content-Type", "application/json");

        return ResponseEntity.ok(iVideoContentService.uploadFile(imageFile));
    }

    @RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
    public void getTestVideo(HttpServletRequest request, HttpServletResponse response,
                             @ApiParam(name = "id", value = "Идентификатор видео.")
                             @PathVariable String id) throws Exception {
        GridFsResource resource = getResult(id);
        GridFSFile files = gridFsTemplate.findOne(query(where("_id").is(id)));
        System.out.println(files.getObjectId());
        MultipartFileSender multipartFileSender = new MultipartFileSender();
        multipartFileSender.with(request);
        multipartFileSender.with(response);
        multipartFileSender.serveResource(resource, getGridFs().openDownloadStream(files.getObjectId()));

    }

    /*
     *  2
     * */
    @ApiOperation(value = "Delete file", tags = {"File"}, notes = "Delete()")
    @DeleteMapping("/delete/file/{id}")
    public void deleteFile(@ApiParam(name = "id", value = "id file")
                                        @PathVariable(name = "id") String id) {
            iVideoContentService.deleteFile(id);
    }

    private GridFSBucket getGridFs() {
        MongoDatabase db = mongoDbFactory.getDb();
        return GridFSBuckets.create(db, mongoConfig.getDefaultBucket());

    }

    private GridFsResource getResult(String imageId) throws IOException {
        return iMongoDocService.downloadScanDoc(imageId);
    }

    /***************************************CRUD*************************************************************/

    @ApiOperation(value = "Создать аккаунтов", tags = {"User Account"})
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public VideoContent setUserAccount(@Valid @RequestBody VideoContent videoContent) {
        return iVideoContentService.create(videoContent);
    }

    @ApiOperation(value = "Получить список организация", tags = {"Organization"})
    @GetMapping("/all")
    public List<VideoContent> getAllOrganization() {
        return iVideoContentService.getAll();
    }

    @ApiOperation(value = "Delete VideoContent", tags = {"File"}, notes = "Delete()")
    @GetMapping("/delete/{id}")
    public void deleteVideoContent(@ApiParam(name = "id", value = "id VideoContent")
                             @PathVariable(name = "id") String id) {
        iVideoContentService.delete(id);
    }

}
