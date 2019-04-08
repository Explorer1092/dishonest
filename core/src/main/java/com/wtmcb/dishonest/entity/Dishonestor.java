package com.wtmcb.dishonest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by WangGang on 2019-04-04.
 * Email: wanggang1@vipkid.com.cn
 *
 * SiteId: 2015330
 * StdStg: 6899
 * StdStl: 8
 * age: "37"
 * areaName: "浙江"
 * businessEntity: ""
 * cambrian_appid: "0"
 * cardNum: "3305011983****5416"
 * caseCode: "(2019)浙0503执96号"
 * changefreq: "always"
 * courtName: "湖州市南浔区人民法院"
 * disruptTypeName: "有履行能力而拒不履行生效法律文书确定义务"
 * duty: "23150"
 * focusNumber: "0"
 * gistId: "(2018)浙0503民初4452号"
 * gistUnit: "湖州南浔法院"
 * iname: "任建东"
 * lastmod: "2019-04-03T08:38:06"
 * loc: "http://shixin.court.gov.cn/detail?id=706044720"
 * partyTypeName: "0"
 * performance: "全部未履行"
 * performedPart: "暂无"
 * priority: "1.0"
 * publishDate: "2019年04月01日"
 * publishDateStamp: "1554048000"
 * regDate: "20190110"
 * sexy: "男性"
 * sitelink: "http://zxgk.court.gov.cn/"
 * type: "失信被执行人名单"
 * unperformPart: "暂无"
 * _select_time: 1554289369
 * _update_time: "1554292351"
 * _version: 156
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dishonestor {
    private static final long serialVersionUID = 2952636877575478687L;
    private Long id;
    private Long StdStg;
    private Long StdStl;
    private String _update_time;
    private String cambrian_appid;
    private String changefreq;
    private String age;
    private String areaName;
    private String businessEntity;
    private String cardNum;
    private String caseCode;
    private String courtName;
    private String disruptTypeName;
    private String duty;
    private String focusNumber;
    private String gistId;
    private String gistUnit;
    private String iname;
    private String partyTypeName;
    private String performance;
    private String performedPart;
    private String publishDate;
    private String publishDateStamp;
    private String regDate;
    private String sexy;
    private String sitelink;
    private String type;
    private String unperformPart;
    private String lastmod;
    private String loc;
    private String priority;
    private long SiteId;
    private long _version;
    private long _select_time;
}
