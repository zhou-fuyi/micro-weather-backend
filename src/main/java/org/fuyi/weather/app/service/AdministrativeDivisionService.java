package org.fuyi.weather.app.service;

import org.fuyi.weather.app.dto.AdministrativeDivisionDto;
import org.fuyi.weather.app.dto.qce.AdminDivFullTextQuery;
import org.fuyi.weather.app.dto.qce.AdminDivCommonQuery;
import org.fuyi.weather.app.dto.qce.AdminDivLocationQuery;
import org.fuyi.weather.domain.entity.AdministrativeDivisionEntity;

import java.util.List;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/1/19 下午8:17
 * @since: 1.0
 */
public interface AdministrativeDivisionService {

    AdministrativeDivisionDto findById(Long id);

    AdministrativeDivisionDto findByCode(String code);

    AdministrativeDivisionEntity internalFindById(Long id);

    List<AdministrativeDivisionDto> listAllByOptions(AdminDivCommonQuery query);

    List<AdministrativeDivisionDto> listAllByLocationOptions(AdminDivLocationQuery query);

    AdministrativeDivisionDto entityToDto(AdministrativeDivisionEntity entity, Boolean spatial);

    List<AdministrativeDivisionDto> listAllByFullTextOptions(AdminDivFullTextQuery query);

}
