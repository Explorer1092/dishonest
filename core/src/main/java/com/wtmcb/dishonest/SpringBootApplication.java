package com.wtmcb.dishonest;

import com.wtmcb.dishonest.spider.service.SpiderService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by WangGang on 2019-04-08.
 * Email: wanggang1@vipkid.com.cn
 */
@MapperScan("com.wtmcb.dishonest.mapper")
@ComponentScan
@EnableAutoConfiguration
@SpringBootConfiguration
public class SpringBootApplication implements CommandLineRunner {

    @Autowired
    private SpiderService spiderService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        spiderService.exec();
    }
}
