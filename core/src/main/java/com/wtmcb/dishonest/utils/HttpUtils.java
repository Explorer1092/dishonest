package com.wtmcb.dishonest.utils;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class HttpUtils {

    private static final HttpClient HTTP_CLIENT;
    
    static {
        HttpConnectionManagerParams params = new HttpConnectionManagerParams();
        params.setDefaultMaxConnectionsPerHost(20);
        params.setMaxConnectionsPerHost(HostConfiguration.ANY_HOST_CONFIGURATION, MultiThreadedHttpConnectionManager.DEFAULT_MAX_TOTAL_CONNECTIONS);
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.setParams(params);
        HTTP_CLIENT = new HttpClient(connectionManager);
    }

    public static String getResponse(String url) throws Exception {
        return getResponse(url, null);
    }

    public static String getResponse(String url, List<Header> headerList) throws Exception {
        HttpMethod getMethod = new GetMethod(url);
        if (CollectionUtils.isNotEmpty(headerList)){
            for (Header header :
                    headerList) {
                getMethod.addRequestHeader(header);
            }
        }
        StringBuilder responseBody = new StringBuilder();
        HTTP_CLIENT.executeMethod(getMethod);
        int statusCode = getMethod.getStatusCode();
        if (HttpStatus.SC_OK != statusCode){
            throw new Exception("状态码返回异常");
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getMethod.getResponseBodyAsStream()));
        String line = null;
        while ((line = bufferedReader.readLine()) != null){
            responseBody.append(line);
        }
        return responseBody.toString();
    }

    public static List<Header> wrapHeader(Map<String, String> headerMap){
        Header header;
        List<Header> headerList = null;
        if (MapUtils.isNotEmpty(headerMap)){
            headerList = new ArrayList<Header>();
            Set<Map.Entry<String, String>> entries = headerMap.entrySet();
            for (Map.Entry<String, String> entry :
                    entries) {
                header = new Header();
                header.setName(entry.getKey());
                header.setValue(entry.getValue());
                headerList.add(header);
            }
        }
        return headerList;
    }

}
