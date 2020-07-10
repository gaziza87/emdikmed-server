package kz.gaziza.emdikmed.controller.dmp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import kz.gaziza.emdikmed.model.dmp.configuration.DMPCategoryFilter;
import kz.gaziza.emdikmed.model.dmp.configuration.Laboratory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("category-filter")
@Api(tags = {"DMPCategoryFilter"}, description = "DMPFilter", authorizations = {@Authorization(value = "bearerAuth")})
public class DMPCategoryFilterController {

    /******************************************************************************************************
     * DMP-CATEGORY-FILTER Services
     */

    @ApiOperation(value = "Получить список всех категорий по фильтру", tags = {"DMP-Category-Filter"})
    @RequestMapping(value = "/read/filters", method = RequestMethod.GET, produces = "application/json")
    public List<DMPCategoryFilter> getFilter() {
        return DMPCategoryFilter.getFilter();
    }

}
