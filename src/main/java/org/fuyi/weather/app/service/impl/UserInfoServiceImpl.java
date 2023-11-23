package org.fuyi.weather.app.service.impl;

import org.fuyi.weather.app.dto.UserInfoDto;
import org.fuyi.weather.app.dto.qce.UserInfoCommand;
import org.fuyi.weather.app.service.UserInfoService;
import org.fuyi.weather.domain.entity.SubjectEntity;
import org.fuyi.weather.infra.repository.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/5 下午10:45
 * @since: 1.0
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private SubjectRepository subjectRepository;
    private ModelMapper modelMapper;

    public UserInfoServiceImpl(SubjectRepository subjectRepository, ModelMapper modelMapper) {
        this.subjectRepository = subjectRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean existsByOid(String oid) {
        return subjectRepository.existsByOid(oid);
    }

    @Override
    public UserInfoDto findByOid(String oid) {
        return entityToDto(subjectRepository.findByOid(oid));
    }

    @Override
    public UserInfoDto register(UserInfoCommand userInfoCommand) {
        return entityToDto(subjectRepository.save(modelMapper.map(userInfoCommand, SubjectEntity.class)));
    }

    @Override
    public UserInfoDto update(UserInfoCommand userInfoCommand) {
        SubjectEntity newEntity = new SubjectEntity();
        if (!userInfoCommand.isNew()){
            SubjectEntity old = subjectRepository.getById(userInfoCommand.getId());
            if (Objects.nonNull(old)){
                newEntity = old;
            }
        }
        modelMapper.map(userInfoCommand, newEntity);
        return entityToDto(subjectRepository.save(newEntity));
    }

    private UserInfoDto entityToDto(SubjectEntity entity){
        if (Objects.isNull(entity)){
            return null;
        }
        return modelMapper.map(entity, UserInfoDto.class);
    }
}
