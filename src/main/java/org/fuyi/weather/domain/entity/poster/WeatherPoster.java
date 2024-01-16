package org.fuyi.weather.domain.entity.poster;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/19 下午4:20
 * @since: 1.0
 */
@Slf4j
@Data
public class WeatherPoster {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    private static String DEFAULT_DIRECTORY_PREFIX = "static/";

    private static String DEFAULT_CACHE_PATH = "/tmp/micro-weather/data/poster/map/";
    private static String DEFAULT_CACHE_IMAGE_TYPE = ".png";

    private String temperature;

    private String text;

    private String wind;

    private String aqi;
    // 存放 RGB 值
    private int[] aqiColor;

    private String location;

    private String center;

    private String centerColor;

    private int scale = 2;

    private int zoom = 11;

    private String size = "520*640";
//    private String size = "1040*1200";

    private String type = "terrain";

    private String path;

    /**
     * 边界
     * 例如: bounds=29.96850041387669,120.06994859115798;30.447716197272673,120.35407140884195
     */
    private String bounds;

    // 额外属性 --start
    private String api;

    private String key;

    private String proxy;
    // 额外属性 --end

    public WeatherPoster(String api, String key, String proxy) {
        this.api = api;
        this.key = key;
        this.proxy = proxy;
    }

    public String getUrl() {
        return proxy + File.separator + path;
    }

    public void draw(String path) throws IOException {
        BufferedImage background = ImageIO.read(new ClassPathResource("static/images/weather-poster-background.png").getInputStream());
        Graphics2D baseGraphics = background.createGraphics();
        baseGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        baseGraphics.drawImage(getMapImage(bounds), 0, 0, 1040, 1280, null);
//        baseGraphics.drawImage(map.getScaledInstance(1040, 1280, Image.SCALE_SMOOTH), 0, 0, null);

        Font font = new Font(null, Font.BOLD, 180);
        baseGraphics.setFont(font);
        baseGraphics.setColor(new Color(0, 0, 0));

        int temperatureWidth = 70;
        // 绘制温度
        baseGraphics.drawString(temperature, temperatureWidth, 1514);

        int unitWidth = temperatureWidth + 20;
        int temp = Integer.parseInt(temperature);
        if (temp < 0) {
            if (Math.abs(temp) > 9){
                unitWidth = unitWidth + 100 * (temperature.length() - 1) + 82;
            }else {
                unitWidth = unitWidth + 100 * (temperature.length() - 1) + 76;
            }

        } else {
            if (temp > 9){
                unitWidth += 15;
            }else {
                unitWidth += 9;
            }
            unitWidth = unitWidth + 100 * temperature.length();
        }

        // 绘制度图标
        baseGraphics.drawOval(unitWidth, 1376, 50, 50);
        baseGraphics.fillOval(unitWidth, 1376, 50, 50);
        baseGraphics.setColor(new Color(255, 255, 255));
        baseGraphics.drawOval(unitWidth + 14, 1390, 22, 22);
        baseGraphics.fillOval(unitWidth + 14, 1390, 22, 22);

        baseGraphics.setColor(new Color(0, 0, 0));
        baseGraphics.setFont(new Font(null, Font.BOLD, 70));

        int locationWidth = unitWidth + 50 + 78;
        // 绘制位置
        baseGraphics.drawString(location, locationWidth, 1406);

        baseGraphics.setFont(new Font(null, Font.PLAIN, 50));
        // 绘制天气
        baseGraphics.drawString(text, locationWidth, 1482);

        // 绘制风力风向
        baseGraphics.drawString(wind, locationWidth, 1556);

        // 绘制时间
        LocalDate now = LocalDate.now();
        baseGraphics.setFont(new Font(null, Font.PLAIN, 30));
        baseGraphics.drawString(String.format("%s/%s", now.getMonthValue() > 9 ? now.getMonthValue() : "0" + now.getMonthValue()
                , now.getDayOfMonth()), 880, 1376);

        // 绘制AQI
        baseGraphics.setFont(new Font(null, Font.PLAIN, 36));
        baseGraphics.setColor(new Color(aqiColor[0], aqiColor[1], aqiColor[2]));
        int aqiLeftIndex = 860;
        if (aqi.length() <= 2) {
            aqiLeftIndex = 840;
            baseGraphics.drawRoundRect(830, 1436, 162, 50, 20, 20);
        }
        if (aqi.length() > 2 && aqi.length() <= 4) {
            aqiLeftIndex = 805;
            baseGraphics.drawRoundRect(795, 1436, 232, 50, 20, 20);
        }
        baseGraphics.drawString("AQI " + aqi, aqiLeftIndex, 1474);


        baseGraphics.setFont(new Font(null, Font.BOLD, 30));
        baseGraphics.setColor(new Color(129, 129, 129));

        // 绘制短分割线
        baseGraphics.drawLine(830, 1396, 992, 1396);
        // 绘制长分割线
        baseGraphics.drawLine(48, 1586, 992, 1586);

        baseGraphics.setFont(new Font(null, Font.PLAIN, 40));
        baseGraphics.setColor(new Color(0, 0, 0));

        BufferedImage logo = ImageIO.read(new ClassPathResource("static/images/logo.png").getInputStream());
        baseGraphics.drawImage(logo, 75, 1608, 150, 150, null);

        baseGraphics.drawString("微天气", 250, 1703);
        baseGraphics.setFont(new Font(null, Font.PLAIN, 34));
        baseGraphics.drawString("开始使用吧", 652, 1700);

        BufferedImage qrCode = ImageIO.read(new ClassPathResource("static/images/micro-weather-qr-code.png").getInputStream());
        baseGraphics.drawImage(qrCode, 846, 1608, 150, 150, null);

        baseGraphics.dispose();
        String directory = DEFAULT_DIRECTORY_PREFIX + now.format(dateTimeFormatter);
        String filename = UUID.randomUUID() + ".png";
        String filePath = path + directory + File.separator + filename;
        File file = new File(filePath);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        ImageIO.write(background, "png", new FileOutputStream(file));
        this.path = directory + File.separator + filename;
    }

    private BufferedImage getMapImage(String secret) throws IOException {
        LocalDate now = LocalDate.now();
        String path = DEFAULT_CACHE_PATH + File.separator + now.format(dateTimeFormatter) + File.separator;
        String filename = path + secret.hashCode() + DEFAULT_CACHE_IMAGE_TYPE;
        File file = new File(filename);
        if (file.exists()){
            return ImageIO.read(file);
        }else {
            String url = String.format(api, centerColor, center, bounds, scale, size, key);
            log.info("静态图URL is {}", url);
            BufferedImage map = ImageIO.read(new URL(url));
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            // 进行缓存
            if (Objects.nonNull(map)){
                log.info("Data buffer size: {}", map.getData().getDataBuffer().getSize());
                if (map.getData().getDataBuffer().getSize() > 1024 * 1024){
                    ImageIO.write(map, "png", new FileOutputStream(file));
                    log.info("File size: {}", file.length());
                }
            }
            return map;
        }

    }

}
