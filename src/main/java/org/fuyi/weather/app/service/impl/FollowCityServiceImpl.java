package org.fuyi.weather.app.service.impl;

import org.fuyi.weather.app.dto.FollowCityDto;
import org.fuyi.weather.app.dto.qce.FollowCityCommand;
import org.fuyi.weather.app.service.FollowCityService;
import org.fuyi.weather.domain.entity.FollowCityEntity;
import org.fuyi.weather.infra.repository.FollowCityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/5 下午7:28
 * @since: 1.0
 */
@Service
public class FollowCityServiceImpl implements FollowCityService {

    private FollowCityRepository followCityRepository;
    private ModelMapper modelMapper;

    public FollowCityServiceImpl(FollowCityRepository followCityRepository, ModelMapper modelMapper) {
        this.followCityRepository = followCityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public FollowCityDto follow(FollowCityCommand cityCommand) {
        if (!followCityRepository.existsByDivisionCodeAndSubjectId(cityCommand.getDivisionCode(), cityCommand.getSubjectId())){
            return entityToDto(followCityRepository.save(modelMapper.map(cityCommand, FollowCityEntity.class)));
        }
        return entityToDto(followCityRepository.findByDivisionCodeAndSubjectId(cityCommand.getDivisionCode(), cityCommand.getSubjectId()));
    }

    @Override
    public void unFollow(Long id) {
        if (followCityRepository.existsById(id)){
            followCityRepository.deleteById(id);
        }
    }

    @Override
    public FollowCityDto update(FollowCityCommand cityCommand) {
        FollowCityEntity cityEntity = new FollowCityEntity();
        if (!cityCommand.isNew()){
            FollowCityEntity old = followCityRepository.getById(cityCommand.getId());
            if (Objects.nonNull(old)){
                cityEntity = old;
            }
        }
        modelMapper.map(cityCommand, cityEntity);
        return entityToDto(followCityRepository.save(cityEntity));
    }

    @Override
    public List<FollowCityDto> listAllFollowCitiesByUserId(Long userId) {
        return batchEntityToDtoConvert(followCityRepository.findAllBySubjectIdOrderByOrderNum(userId));
    }

    private FollowCityDto entityToDto(FollowCityEntity cityEntity){
        if (Objects.isNull(cityEntity)){
            return null;
        }
        return modelMapper.map(cityEntity, FollowCityDto.class);
    }

    private List<FollowCityDto> batchEntityToDtoConvert(List<FollowCityEntity> followCityEntities){
        if (CollectionUtils.isEmpty(followCityEntities)) {
            return Collections.EMPTY_LIST;
        }
        List<FollowCityDto> collect = followCityEntities.stream().filter(Objects::nonNull).map(this::entityToDto).collect(Collectors.toList());
        return collect;
    }
}
