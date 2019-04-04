package com.wtmcb.dishonest.spider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wtmcb.dishonest.entity.DishonestCustomerInfo;
import com.wtmcb.dishonest.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WangGang on 2019-04-04.
 * Email: wanggang1@vipkid.com.cn
 */
@Slf4j
public class BaiduDishonestSpider {

    private static final String ENCODING = "UTF-8";
    private static final String SPIDER_URL = "https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php";

    private static final Integer LIMIT = 20;

    public List<DishonestCustomerInfo> fetchList(Integer page){
        Map<String, Object> queryParams = warpQueryParams(LIMIT, LIMIT * (page - 1));
        try {
            String stringDishonest = getStringDishonest(queryParams);
        } catch (Exception e) {
            log.error("失信人查询异常", e);
        }
        return null;
    }

    private static String getStringDishonest(Map<String, Object> params) throws Exception {
        String fullUrl = null;
        // 构建请求参数
        StringBuilder buffer = new StringBuilder();
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                buffer.append(entry.getKey());
                buffer.append("=");
                try {
                    buffer.append(URLEncoder.encode((String) entry.getValue(), ENCODING));
                } catch (UnsupportedEncodingException e) {
                    throw new Exception("失信人查询编码异常:"+e);
                }
                buffer.append("&");
            }
        }
        // 拼接查询URL
        if (buffer.length() > 0) {
            fullUrl = SPIDER_URL + "?" + buffer.substring(0, buffer.length() - 1);
        }
        // 请求拼接后的地址获取详细信息
        return HttpUtils.getResponse(fullUrl);
    }

    private static Map<String, Object> warpQueryParams(Integer rn, Integer pn){
        // 查询条件
        Map<String, Object> map = new HashMap<>();
        map.put("resource_id", "6899");
        map.put("query", "失信被执行人名单");
        map.put("cardNum", "");
        map.put("iname", "");
        map.put("areaName", "");
        map.put("rn", rn.toString());
        map.put("pn", pn.toString());
        map.put("ie", "utf-8");
        map.put("oe", "utf-8");
        map.put("format", "json");
        map.put("t", "1524537973200");
        map.put("cb", "jQuery110207319777455577083_1524537959352");
        map.put("_", "1524537959354");
        return map;
    }

    private static List<DishonestCustomerInfo> wrapDishonest(String strResult) {
        // 查询结果
        strResult = strResult.substring(strResult.indexOf("(")+1,strResult.lastIndexOf(");"));
        log.info("FastJson转换字符串为对象："+ JSONObject.parseObject(strResult));
        // json封装
        JSONObject firstMap = JSONObject.parseObject(strResult);
        JSONArray secondMap = (JSONArray) firstMap.get("data");
        // 返回的结果初始化列表
        List<DishonestCustomerInfo> infoList = new ArrayList<>();
        if (secondMap != null && secondMap.size() > 0) {
            JSONObject thirdMap = (JSONObject) secondMap.get(0);
            JSONArray forthMap = (JSONArray) thirdMap.get("result");
            for (int i = 0; i < forthMap.size(); i++) {
                JSONObject item = forthMap.getJSONObject(i);
                log.info("JSON Object["+ i +"]:"+item);
                // 自定义类封装
                DishonestCustomerInfo dishonestCustomerInfo = JSON.parseObject(item.toJSONString(), DishonestCustomerInfo.class);
                infoList.add(dishonestCustomerInfo);
            }
        }
        return infoList;
    }
}
