package com.wtmcb.dishonest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FetchLog {
    private Integer id;

    private String url;

    private Integer result;

    private Integer page;

    private Date createTime;
}