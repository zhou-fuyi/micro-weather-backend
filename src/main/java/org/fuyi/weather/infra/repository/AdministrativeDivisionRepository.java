package org.fuyi.weather.infra.repository;

import org.fuyi.weather.domain.entity.AdministrativeDivisionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/1/19 下午8:45
 * @since: 1.0
 */
public interface AdministrativeDivisionRepository extends JpaRepository<AdministrativeDivisionEntity, Long> {

    List<AdministrativeDivisionEntity> findAllByGradeAndCodeStartsWith(Integer grade, String code);

    List<AdministrativeDivisionEntity> findAllByGrade(Integer grade);

    AdministrativeDivisionEntity findFirstByCode(String code);

    @Query(nativeQuery = true, value = "SELECT * FROM administrative_division WHERE grade = :grade and ST_Intersects(ST_PointFromText(:location, 4326), bounds)")
    List<AdministrativeDivisionEntity> findAllByGradeAndLocation(@Param("grade") Integer grade, @Param("location") String locationWkt);

    List<AdministrativeDivisionEntity> findAllByGradeAndNameContaining(Integer grade, String name);

    List<AdministrativeDivisionEntity> findAllByGradeNotAndNameContaining(Integer grade, String name);
}
