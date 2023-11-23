package org.fuyi.weather.infra.repository;

import org.fuyi.weather.domain.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/5 下午10:45
 * @since: 1.0
 */
public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {

    boolean existsByOid(String oid);

    SubjectEntity findByOid(String oid);
}
