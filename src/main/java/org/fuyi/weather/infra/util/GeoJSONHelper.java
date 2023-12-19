package org.fuyi.weather.infra.util;

import org.geotools.geojson.GeoJSONUtil;
import org.geotools.geojson.geom.GeometryJSON;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.StringWriter;

/**
 * 当前转换支持格式: {"type":"Point","coordinates":[120.269136194,30.189746915]}、
 * {"type":"Point","crs":{"type":"name","properties":{"name":"EPSG:4490"}},"coordinates":[118.597188736,28.67472152]}
 * <p>
 * 当前转换不支持格式: {"type":"Point","coordinates":["120.26913619365064","30.189746914956487"]}
 *
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/1/19 下午9:04
 * @since: 1.0
 */
public class GeoJSONHelper {

    private static GeometryJSON GEO_JSON_CONVERT = new GeometryJSON(18);
    private static Logger logger = LoggerFactory.getLogger(GeoJSONHelper.class.getName());

    /**
     * GeoJSON convert to Geometry
     * <p>
     * should check NPE!
     *
     * @param geoJSON
     * @return
     */
    public static Geometry read(String geoJSON) {
        if (StringUtils.isEmpty(geoJSON)) {
            throw new IllegalArgumentException("geoJSON can not be empty.");
        }
        try {
            return GEO_JSON_CONVERT.read(GeoJSONUtil.toReader(geoJSON));
        } catch (IOException exception) {
            logger.error(exception.getMessage());
        }
        return null;
    }

    public static Point readPoint(String geoJSON) {
        if (StringUtils.isEmpty(geoJSON)) {
            throw new IllegalArgumentException("geoJSON can not be empty.");
        }
        try {
            return GEO_JSON_CONVERT.readPoint(GeoJSONUtil.toReader(geoJSON));
        } catch (IOException exception) {
            logger.error(exception.getMessage());
        }
        return null;
    }

    public static String write(Geometry geometry){
        StringWriter writer = new StringWriter();
        try {
            GEO_JSON_CONVERT.write(geometry, writer);
            return writer.toString();
        } catch (IOException exception) {
            logger.error(exception.getMessage());
        }finally {
            try {
                writer.close();
            } catch (IOException exception) {
                logger.error(exception.getMessage());
            }
        }
        return null;
    }

    public static class InternalGeometry {

        private String type;

        private Object coordinates;

        public InternalGeometry() {
        }

        public InternalGeometry(String type, Object coordinates) {
            this.type = type;
            this.coordinates = coordinates;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(Object coordinates) {
            this.coordinates = coordinates;
        }
    }

}
