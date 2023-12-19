package org.fuyi.weather.app.dto.qce;

import lombok.*;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/3 上午10:57
 * @since: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class AdminDivLocationQuery {

    private static WKTReader reader = new WKTReader();

    /**
     * wkt格式数据
     */
    private String location;
    /**
     * 默认为县级
     */
    @Builder.Default
    private Integer grade = 3;

    /**
     * 是否同步查询关联空间数据，默认不进行查询
     */
    @Builder.Default
    private Boolean spatialCapable = false;

    public boolean locationCheck(){
        try {
            reader.read(location);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
