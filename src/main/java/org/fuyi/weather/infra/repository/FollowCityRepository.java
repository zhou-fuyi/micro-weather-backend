package org.fuyi.weather.infra.repository;

import org.fuyi.weather.domain.entity.FollowCityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/5 下午7:20
 * @since: 1.0
 */
public interface FollowCityRepository extends JpaRepository<FollowCityEntity, Long> {

    boolean existsByDivisionCodeAndSubjectId(String divisionCode, Long subjectId);

    FollowCityEntity findByDivisionCodeAndSubjectId(String divisionCode, Long subjectId);

    List<FollowCityEntity> findAllBySubjectIdOrderByOrderNum(Long subjectId);
}
