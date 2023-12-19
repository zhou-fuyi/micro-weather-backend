package org.fuyi.weather.app.controller.v1;

import org.fuyi.weather.app.dto.GenericDistrictInfoResponse;
import org.fuyi.weather.app.dto.ListDistrictInfoResponse;
import org.fuyi.weather.app.dto.qce.AdminDivCommonQuery;
import org.fuyi.weather.app.dto.qce.AdminDivFullTextQuery;
import org.fuyi.weather.app.dto.qce.AdminDivLocationQuery;
import org.fuyi.weather.app.service.AdministrativeDivisionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/1/19 下午9:25
 * @since: 1.0
 */
@RestController
@RequestMapping("/v1/admin_divisions")
public class AdministrativeDivisionController {

    private AdministrativeDivisionService divisionService;

    public AdministrativeDivisionController(AdministrativeDivisionService administrativeDivisionService) {
        this.divisionService = administrativeDivisionService;
    }

    @GetMapping("/{code}")
    public GenericDistrictInfoResponse findById(@PathVariable("code") String code){
        return new GenericDistrictInfoResponse(divisionService.findByCode(code));
    }

    @GetMapping("/auto_incr/{id}")
    public GenericDistrictInfoResponse findByCode(@PathVariable("id") Long id){
        return new GenericDistrictInfoResponse(divisionService.findById(id));
    }

    @GetMapping
    public ListDistrictInfoResponse listAllByOptions(AdminDivCommonQuery query){
        return new ListDistrictInfoResponse(divisionService.listAllByOptions(query));
    }

    @GetMapping("/search")
    public ListDistrictInfoResponse fullTextSearch(AdminDivFullTextQuery query){
        return new ListDistrictInfoResponse(divisionService.listAllByFullTextOptions(query));
    }

    @GetMapping("/spatial/lookup")
    public ListDistrictInfoResponse listAllByLocation(AdminDivLocationQuery query){
        return new ListDistrictInfoResponse(divisionService.listAllByLocationOptions(query));
    }
}
