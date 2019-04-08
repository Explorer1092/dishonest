package com.wtmcb.dishonest.spider.service;

import com.wtmcb.dishonest.entity.Dishonestor;
import com.wtmcb.dishonest.mapper.DishonestorMapper;
import com.wtmcb.dishonest.spider.job.DishonestUtils;
import com.wtmcb.dishonest.spider.job.SpiderExecutor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WangGang on 2019-04-08.
 * Email: wanggang1@vipkid.com.cn
 */
@Slf4j
@Service
public class SpiderService {

    @Autowired
    private DishonestorMapper dishonestorMapper;

    public void exec(){
        for (int i = 960; i < 240000; i++) {
            int finalI = i;
            if (finalI % 4 == 3){
                SpiderExecutor.pushTask(() -> {
                    fetchAndRecord(finalI);
                    try {
                        Thread.sleep(3000L);
                    } catch (InterruptedException e) {
                        log.error("获取失信被执行人出错", e);
                    }
                });
            }
        }
    }

    public void fetchAndRecord(Integer page){
        try {
            List<Dishonestor> dishonestorList = DishonestUtils.fetchList(page);
            if (CollectionUtils.isNotEmpty(dishonestorList)){
                for (Dishonestor dishonestor :
                        dishonestorList) {
                    dishonestorMapper.insertSelective(dishonestor);
                }
            }
        } catch (Exception e) {
            log.info("执行爬虫失败-页码{}", page, e);
        }
    }
}
