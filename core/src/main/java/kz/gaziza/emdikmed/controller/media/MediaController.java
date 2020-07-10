package kz.gaziza.emdikmed.controller.media;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import kz.gaziza.emdikmed.model.media.Media;
import kz.gaziza.emdikmed.service.media.impl.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("media")
@Api(tags = {"Media"}, description = "Media", authorizations = {@Authorization(value = "bearerAuth")})
public class MediaController {

    /******************************************************************************************************
     * Media Services
     */

    @Autowired
    private MediaService mediaService;

    @ApiOperation(value = "Получить список всех Медий", tags = {"MEDIA"})
    @RequestMapping(value = "read/media/iterable", method = RequestMethod.GET, produces = "application/json")
    public List<Media> getMediaIterable() {
        return mediaService.readIterable();
    }

    @ApiOperation(value = "Получить Медий по заданному ID", tags = {"MEDIA"})
    @RequestMapping(value = "read/media/{id}", method = RequestMethod.GET, produces = "application/json")
    public Media getMedia(@PathVariable(name = "id") String id) {
        return mediaService.readOne(id);
    }

    @ApiOperation(value = "Создать новую Медий", tags = {"MEDIA"})
    @RequestMapping(value = "create/media", method = RequestMethod.POST, produces = "application/json")
    public Media createMedia(@RequestBody @Valid Media media) {
        return mediaService.create(media);
    }

    @ApiOperation(value = "Обновить существующую Медий", tags = {"MEDIA"})
    @RequestMapping(value = "update/media", method = RequestMethod.PUT, produces = "application/json")
    public Media updateMedia(@RequestBody @Valid Media media) {
        return mediaService.update(media);
    }

    @ApiOperation(value = "Удалить Медий", tags = {"MEDIA"})
    @RequestMapping(value = "delete/media/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public String deleteMedia(@PathVariable(name = "id") String id) {
        mediaService.delete(id);
        return ("OK");
    }


}
