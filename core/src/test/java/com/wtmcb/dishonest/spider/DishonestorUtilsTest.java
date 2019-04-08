package com.wtmcb.dishonest.spider;

import com.alibaba.fastjson.JSON;
import com.wtmcb.dishonest.entity.Dishonestor;
import com.wtmcb.dishonest.mapper.DishonestorMapper;
import com.wtmcb.dishonest.spider.job.DishonestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by WangGang on 2019-04-04.
 * Email: wanggang1@vipkid.com.cn
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class DishonestorUtilsTest {

    @Autowired
    private DishonestorMapper dishonestorMapper;

    @Test
    public void testFetch(){
        DishonestUtils dishonestUtils = new DishonestUtils();
        List<Dishonestor> dishonestorCustomerInfoList = null;
        try {
            dishonestorCustomerInfoList = dishonestUtils.fetchList(1);
        } catch (Exception e) {
            log.error("获取失信被执行人列表异常");
        }
        log.info("获取失信被执行人列表{}", JSON.toJSONString(dishonestorCustomerInfoList, true));
    }

    @Test
    public void testMybatis(){
        log.info(JSON.toJSONString(dishonestorMapper.selectByPrimaryKey(1L)));
    }
}