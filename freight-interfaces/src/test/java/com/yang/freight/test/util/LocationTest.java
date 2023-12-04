package com.yang.freight.test.util;

import com.yang.freight.common.Location;
import com.yang.freight.domain.support.location.LocationUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author：杨超
 * @date: 2023/12/2
 * @Copyright：
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationTest {

    private Logger logger = LoggerFactory.getLogger(LocationTest.class);

    @Test
    public void locationTest() {

        String s = "116.310003,39.991957";

        String[] str = s.split(",");

        double longitude = Double.parseDouble(str[0]);
        double latitude = Double.parseDouble(str[1]);

        logger.info("l1:{},l2:{}", str[0], str[1]);
        logger.info("longitude:{},latitude:{}", longitude,latitude);

    }

    @Test
    public void distanceTest() {
        double longitude1 = 117.344733;
        double latitude1 = 31.912334;
        double longitude2 = 117.272186;
        double latitude2 = 31.79422;

        Location location1 = new Location(latitude1, longitude1);
        Location location2 = new Location(latitude2, longitude2);

        Double distance = LocationUtils.distance(location1, location2);
        logger.info("distance:{}",distance);
    }

    @Test
    public void addressToCoordinateTest() {
        //String address = "北京市朝阳区阜通东大街6号";
        String address = "湖州市吴兴区二环东路759号";
        Location location = LocationUtils.addressToCoordinate(address);
        logger.info(location.toCoordinateString());
    }

    @Test
    public void coordinateToStringTest() {
        Location location = new Location(116.310003, 39.991957);
        String s = LocationUtils.coordinateToAddress(location.toCoordinateString());
        logger.info(s);
    }

    @Test
    public void getFormattedAddressTest() {
        String jsonString = "{\n" +
                "  \"status\": \"1\",\n" +
                "  \"regeocode\": {\n" +
                "    \"roads\": [\n" +
                "      {\n" +
                "        \"id\": \"010J50F0010196591\",\n" +
                "        \"location\": \"116.31,39.9926\",\n" +
                "        \"direction\": \"南\",\n" +
                "        \"name\": \"求知路\",\n" +
                "        \"distance\": \"67.7634\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"010J50F0010195645\",\n" +
                "        \"location\": \"116.311,39.992\",\n" +
                "        \"direction\": \"西\",\n" +
                "        \"name\": \"五四路\",\n" +
                "        \"distance\": \"96.7016\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"010J50F0010199620\",\n" +
                "        \"location\": \"116.311,39.9919\",\n" +
                "        \"direction\": \"西\",\n" +
                "        \"name\": \"科学路\",\n" +
                "        \"distance\": \"115.312\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"roadinters\": [\n" +
                "      {\n" +
                "        \"second_name\": \"科学路\",\n" +
                "        \"first_id\": \"010J50F0010195645\",\n" +
                "        \"second_id\": \"010J50F0010199620\",\n" +
                "        \"location\": \"116.311356,39.991930\",\n" +
                "        \"distance\": \"115.312\",\n" +
                "        \"first_name\": \"五四路\",\n" +
                "        \"direction\": \"西\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"formatted_address\": \"北京市海淀区燕园街道北京大学\",\n" +
                "    \"addressComponent\": {\n" +
                "      \"city\": [],\n" +
                "      \"province\": \"北京市\",\n" +
                "      \"adcode\": \"110108\",\n" +
                "      \"district\": \"海淀区\",\n" +
                "      \"towncode\": \"110108015000\",\n" +
                "      \"streetNumber\": {\n" +
                "        \"number\": \"5号\",\n" +
                "        \"location\": \"116.310454,39.992734\",\n" +
                "        \"direction\": \"东北\",\n" +
                "        \"distance\": \"94.5489\",\n" +
                "        \"street\": \"颐和园路\"\n" +
                "      },\n" +
                "      \"country\": \"中国\",\n" +
                "      \"township\": \"燕园街道\",\n" +
                "      \"businessAreas\": [\n" +
                "        {\n" +
                "          \"location\": \"116.303364,39.976410\",\n" +
                "          \"name\": \"万泉河\",\n" +
                "          \"id\": \"110108\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"location\": \"116.314222,39.982490\",\n" +
                "          \"name\": \"中关村\",\n" +
                "          \"id\": \"110108\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"location\": \"116.294214,39.996850\",\n" +
                "          \"name\": \"西苑\",\n" +
                "          \"id\": \"110108\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"building\": {\n" +
                "        \"name\": \"北京大学\",\n" +
                "        \"type\": \"科教文化服务;学校;高等院校\"\n" +
                "      },\n" +
                "      \"neighborhood\": {\n" +
                "        \"name\": \"北京大学\",\n" +
                "        \"type\": \"科教文化服务;学校;高等院校\"\n" +
                "      },\n" +
                "      \"citycode\": \"010\"\n" +
                "    },\n" +
                "    \"aois\": [\n" +
                "      {\n" +
                "        \"area\": \"1885122.274223\",\n" +
                "        \"type\": \"141201\",\n" +
                "        \"id\": \"B000A816R6\",\n" +
                "        \"location\": \"116.310918,39.992873\",\n" +
                "        \"adcode\": \"110108\",\n" +
                "        \"name\": \"北京大学\",\n" +
                "        \"distance\": \"0\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"pois\": [\n" +
                "      {\n" +
                "        \"id\": \"B000A816R6\",\n" +
                "        \"direction\": \"东北\",\n" +
                "        \"businessarea\": \"万泉河\",\n" +
                "        \"address\": \"颐和园路5号\",\n" +
                "        \"poiweight\": \"0.806322\",\n" +
                "        \"name\": \"北京大学\",\n" +
                "        \"location\": \"116.310918,39.992873\",\n" +
                "        \"distance\": \"128.275\",\n" +
                "        \"tel\": [],\n" +
                "        \"type\": \"科教文化服务;学校;高等院校\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"info\": \"OK\",\n" +
                "  \"infocode\": \"10000\"\n" +
                "}";

        String formattedAddress = LocationUtils.getFormattedAddress(jsonString);
        logger.info(formattedAddress);
    }
}
