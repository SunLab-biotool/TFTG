package com.lcq.tftgdb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lcq.tftgdb.mapper")
public class TftGdbApplication {
    public static void main(String[] args) {
        SpringApplication.run(TftGdbApplication.class, args);
    }

}
