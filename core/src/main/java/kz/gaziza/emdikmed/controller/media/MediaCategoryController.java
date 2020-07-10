package kz.gaziza.emdikmed.controller.media;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import kz.gaziza.emdikmed.model.media.MediaCategory;
import kz.gaziza.emdikmed.service.media.impl.MediaCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("media-category")
@Api(tags = {"Media-category"}, description = "Media-category", authorizations = {@Authorization(value = "bearerAuth")})
public class MediaCategoryController {

    /******************************************************************************************************
     * Media-category Services
     */

    @Autowired
    private MediaCategoryService mediaCategoryService;


    @ApiOperation(value = "Получить список всех категорий Медий", tags = {"MEDIA-CATEGORY"})
    @RequestMapping(value = "read/media-category/iterable", method = RequestMethod.GET, produces = "application/json")
    public List<MediaCategory> getMediaCategoryIterable() {
        return mediaCategoryService.readIterable();
    }

    @ApiOperation(value = "Получить категорий Медий по заданному ID", tags = {"MEDIA-CATEGORY"})
    @RequestMapping(value = "read/media-category/{id}", method = RequestMethod.GET, produces = "application/json")
    public MediaCategory getMediaCategory(@PathVariable(name = "id") String id) {
        return mediaCategoryService.readOne(id);
    }

    @ApiOperation(value = "Создать новую категорий Медий", tags = {"MEDIA-CATEGORY"})
    @RequestMapping(value = "create/media-category", method = RequestMethod.POST, produces = "application/json")
    public MediaCategory createMediaCategory(@RequestBody @Valid MediaCategory mediaCategory) {
        return mediaCategoryService.create(mediaCategory);
    }

    @ApiOperation(value = "Обновить существующую категорий Медий", tags = {"MEDIA-CATEGORY"})
    @RequestMapping(value = "update/media-category", method = RequestMethod.PUT, produces = "application/json")
    public MediaCategory updateMediaCategory(@RequestBody @Valid MediaCategory mediaCategory) {
        return mediaCategoryService.update(mediaCategory);
    }

    @ApiOperation(value = "Удалить категорий Медий", tags = {"MEDIA-CATEGORY"})
    @RequestMapping(value = "delete/media-category/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public String deleteMediaCategory(@PathVariable(name = "id") String id) {
        mediaCategoryService.delete(id);
        return ("OK");
    }
}
