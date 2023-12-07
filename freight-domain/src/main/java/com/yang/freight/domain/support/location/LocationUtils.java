package com.yang.freight.domain.support.location;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yang.freight.common.Location;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: 位置相关工具
 * @author：杨超
 * @date: 2023/12/2
 * @Copyright：
 */
public class LocationUtils {

    private static final String LOCATION_URL = "https://restapi.amap.com/v3/geocode/regeo";
    private static final String ADDRESS_URL = "https://restapi.amap.com/v3/geocode/geo";
    private static final String KEY = "d31c10af75f60c3b96c62b5b2e633746";
    private static final String OUTPUT = "json";
    private static final String EXTENSIONS = "all";
    private static final String RADIUS = "10";


    private static Logger logger = LoggerFactory.getLogger(LocationUtils.class);

    /**
     * 求两个位置坐标的距离
     * @param location1
     * @param location2
     * @return
     */
    public static Double distance(Location location1, Location location2) {

        Ellipsoid ellipsoid = Ellipsoid.Sphere;
        GlobalCoordinates firstPoint = new GlobalCoordinates(location1.getLatitude(), location1.getLongitude());
        GlobalCoordinates secondPoint = new GlobalCoordinates(location2.getLatitude(), location2.getLongitude());
        GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(ellipsoid, firstPoint, secondPoint);
        return geoCurve.getEllipsoidalDistance();
    }

    public static Location addressToCoordinate(String address) {

        StringBuilder result = new StringBuilder();
        try {

            Map<String, String> parameters = new HashMap<>();
            parameters.put("output", OUTPUT);
            parameters.put("address", address);
            parameters.put("key", KEY);


            String apiUrl = ADDRESS_URL;
            apiUrl += parameters.entrySet().stream()
                    .map(p -> p.getKey() + "=" + p.getValue())
                    .collect(Collectors.joining("&", "?", ""));
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");


            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                result.append(response.toString());
                boolean status = getStatus(result.toString());
                if (!status) {
                    return null;
                }
                String coordinate = getCoordinate(result.toString());
                System.out.println(response.toString());
                String[] split = coordinate.split(",");
                if (split.length == 2) {
                    Location location = new Location(Double.parseDouble(split[0]), Double.parseDouble(split[1]));
                    return location;
                }else {
                    return null;
                }

            } else {
                System.out.println("GET request not worked");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将坐标信息转换为明文位置信息
     * @param location
     * @return
     */
    public static String coordinateToAddress(String location) {

        StringBuilder result = new StringBuilder();

        try {

            Map<String, String> parameters = new HashMap<>();
            parameters.put("output", OUTPUT);
            parameters.put("location", location);
            parameters.put("key", KEY);
            parameters.put("radius", RADIUS);
            parameters.put("extensions", EXTENSIONS);

            String apiUrl = LOCATION_URL;
            apiUrl += parameters.entrySet().stream()
                    .map(p -> p.getKey() + "=" + p.getValue())
                    .collect(Collectors.joining("&", "?", ""));
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");


            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                result.append(response.toString());
                //判断调用结果是否正确
                boolean status = getStatus(result.toString());
                if (!status) {
                    return null;
                }
                String formattedAddress = getFormattedAddress(result.toString());
                System.out.println(response.toString());
                return formattedAddress;
            } else {
                System.out.println("GET request not worked");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static String getFormattedAddress(String jsonString) {

        JSONObject jsonObject = JSON.parseObject(jsonString);
        String formattedAddress = jsonObject.getJSONObject("regeocode").getString("formatted_address");

        return formattedAddress;
    }

    public static String getCoordinate(String jsonString) {
        logger.info("coordinateJson:{}",jsonString);
        JSONObject jsonObject = JSON.parseObject(jsonString);
        JSONArray geocodes = jsonObject.getJSONArray("geocodes");

        // 如果你确定只想获取第二个元素的location，则可以直接获取
        String location = geocodes.getJSONObject(0).getString("location");
        return location;
    }

    public static boolean getStatus(String jsonString) {
        JSONObject jsonObject = JSON.parseObject(jsonString);
        String status = jsonObject.getString("status");
        return "1".equals(status);
    }
}
