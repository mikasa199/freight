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

}
