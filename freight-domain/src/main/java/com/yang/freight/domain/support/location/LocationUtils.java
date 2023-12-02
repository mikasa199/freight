package com.yang.freight.domain.support.location;

import com.yang.freight.common.Location;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;

/**
 * @description: 位置相关工具
 * @author：杨超
 * @date: 2023/12/2
 * @Copyright：
 */
public class LocationUtils {

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
}
