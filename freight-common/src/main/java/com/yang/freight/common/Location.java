package com.yang.freight.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author：杨超
 * @date: 2023/12/2
 * @Copyright：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    public String toCoordinateString() {
        return longitude.toString() + "," + latitude.toString();
    }

}
