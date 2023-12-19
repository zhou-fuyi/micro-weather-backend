package org.fuyi.weather.app.service.impl;

import com.alibaba.fastjson.JSONObject;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.fuyi.weather.app.dto.AdministrativeDivisionDto;
import org.fuyi.weather.app.dto.qce.AdminDivFullTextQuery;
import org.fuyi.weather.app.dto.qce.AdminDivCommonQuery;
import org.fuyi.weather.app.dto.qce.AdminDivLocationQuery;
import org.fuyi.weather.app.service.AdministrativeDivisionService;
import org.fuyi.weather.domain.entity.AdministrativeDivisionEntity;
import org.fuyi.weather.infra.repository.AdministrativeDivisionRepository;
import org.fuyi.weather.infra.util.GeoJSONHelper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/1/19 下午8:42
 * @since: 1.0
 */
@Service
public class AdministrativeDivisionServiceImpl implements AdministrativeDivisionService {

    private ModelMapper modelMapper;
    private AdministrativeDivisionRepository repository;
    private static Integer COUNTRY_LEVEL = 3;
    private static Integer TOWNSHIP_LEVEL = 4;

    /**
     * 统一转为三级分层的话，建议：
     * 直辖市-县--------》直辖市-直辖市-县
     * 省-市-县--------》省-市-县
     * 省-null-县--------》省-县-县
     * 省-市-null--------》省-市-市
     */

    // 直辖市处理
    private static List<String> MUNICIPALITY_CODES = Arrays.asList("110000", "310000", "120000", "500000");

    private static HanyuPinyinOutputFormat pinyinOutputFormat = new HanyuPinyinOutputFormat();

    private static Boolean SPATIAL_ENABLED = true;

    private static Comparator<AdministrativeDivisionDto> DEFAULT_COMPARATOR = (left, right) -> {
        String currentName = "";
        String rightName = "";
        try {
            currentName = PinyinHelper.toHanYuPinyinString(left.getName(), pinyinOutputFormat, " ", true);
            rightName = PinyinHelper.toHanYuPinyinString(right.getName(), pinyinOutputFormat, " ", true);
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
        }
        return currentName.compareTo(rightName);
    };

    public AdministrativeDivisionServiceImpl(ModelMapper modelMapper, AdministrativeDivisionRepository repository) {
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    @Override
    public AdministrativeDivisionDto findById(Long id) {
        return entityToDto(internalFindById(id), SPATIAL_ENABLED);
    }

    @Override
    public AdministrativeDivisionDto findByCode(String code) {
        return entityToDto(repository.findFirstByCode(code), SPATIAL_ENABLED);
    }

    @Override
    public AdministrativeDivisionEntity internalFindById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<AdministrativeDivisionDto> listAllByOptions(AdminDivCommonQuery query) {
        if (query.isEmpty()){
            return batchEntityToDtoConvert(repository.findAll(), query.getSpatialCapable());
        }
        if (StringUtils.hasText(query.getCode()) && Objects.nonNull(query.getGrade())){
            String subCode = query.getCode().substring(0, query.getGrade());
            if (COUNTRY_LEVEL.equals(query.getGrade()) && !MUNICIPALITY_CODES.contains(query.getCode())) {
                subCode = query.getCode().substring(0, 4);
            }
            if (TOWNSHIP_LEVEL.equals(query.getGrade())) {
                subCode = query.getCode().substring(0, 6);
            }
            return batchEntityToDtoConvert(repository.findAllByGradeAndCodeStartsWith(query.getGrade(), subCode), query.getSpatialCapable());
        }
        if (StringUtils.hasText(query.getCode()) && Objects.isNull(query.getGrade())){
            return Stream.of(entityToDto(repository.findFirstByCode(query.getCode()), query.getSpatialCapable())).collect(Collectors.toList());
        }
        return batchEntityToDtoConvert(repository.findAllByGrade(query.getGrade()), query.getSpatialCapable());
    }

    @Override
    public List<AdministrativeDivisionDto> listAllByLocationOptions(AdminDivLocationQuery query) {
        return batchEntityToDtoConvert(repository.findAllByGradeAndLocation(query.getGrade(), query.getLocation()), query.getSpatialCapable());
    }

    @Override
    public List<AdministrativeDivisionDto> listAllByFullTextOptions(AdminDivFullTextQuery query) {
        if (query.getGradeInversion()){
            return batchEntityToDtoConvert(repository.findAllByGradeNotAndNameContaining(query.getGrade(), query.getName()), query.getSpatialCapable());
        }
        return batchEntityToDtoConvert(repository.findAllByGradeAndNameContaining(query.getGrade(), query.getName()), query.getSpatialCapable());
    }

    private List<AdministrativeDivisionDto> batchEntityToDtoConvert(List<AdministrativeDivisionEntity> entities, Boolean spatial) {
        if (CollectionUtils.isEmpty(entities)) {
            return Collections.EMPTY_LIST;
        }
        List<AdministrativeDivisionDto> collect = entities.stream().filter(Objects::nonNull).map(item -> entityToDto(item, spatial)).collect(Collectors.toList());

        Collections.sort(collect, DEFAULT_COMPARATOR);
        return collect;
    }

    @Override
    public AdministrativeDivisionDto entityToDto(AdministrativeDivisionEntity districtInfoEntity, Boolean spatial) {
        if (Objects.isNull(districtInfoEntity)){
            return null;
        }
        AdministrativeDivisionDto administrativeDivisionDto = new AdministrativeDivisionDto();
        modelMapper.map(districtInfoEntity, administrativeDivisionDto);
        if (spatial && Objects.nonNull(districtInfoEntity.getCenterPoint())) {
            administrativeDivisionDto.setCenterPoint(JSONObject.parseObject(GeoJSONHelper.write(districtInfoEntity.getCenterPoint())));
        }
        if (spatial && Objects.nonNull(districtInfoEntity.getBounds())) {
            administrativeDivisionDto.setBounds(JSONObject.parseObject(GeoJSONHelper.write(districtInfoEntity.getBounds())));
        }
        return administrativeDivisionDto;
    }

}
