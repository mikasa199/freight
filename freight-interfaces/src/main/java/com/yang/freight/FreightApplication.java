package com.yang.freight;


import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/11
 * @Copyright：
 */
@SpringBootApplication
@ServletComponentScan
@Configurable
public class FreightApplication {
    public static void main(String[] args) {
        SpringApplication.run(FreightApplication.class,args);
    }
}
