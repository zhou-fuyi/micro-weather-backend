package org.fuyi.weather.infra.common.dto;

import java.io.Serializable;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/1/18 下午10:13
 * @since: 1.0
 */
public abstract class AbstractBaseDto implements Serializable {

    private static final long serialVersionUID = 5401125120027360107L;

    private Long id;

    public AbstractBaseDto() {
    }

    public AbstractBaseDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AbstractBaseDto{" +
                "id=" + id +
                '}';
    }
}
