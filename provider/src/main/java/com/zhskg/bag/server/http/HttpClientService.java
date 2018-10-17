package com.zhskg.bag.server.http;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.apache.commons.codec.Charsets;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @author jean
 * @date 2018/10/6
 * desc: httpClient
 */

@Service
public class HttpClientService {

    @Autowired
    private CloseableHttpClient httpClient;


    private static final String DEFAULT_CHARSET = "UTF-8";

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientService.class);




    /**
     * 简单get请求
     * @param url
     * @return
     */

    public String get(String url) {
        return doHttp(RequestBuilder.get(),url,null,null, Charsets.UTF_8,Charsets.UTF_8);
    }


    /**
     * get请求(带参数)
     *
     * @param url 请求url
     * @param params get请求参数
     * @return html 页面数据
     */
    public String get(String url, Map<String, String> params) {
        return doHttp(RequestBuilder.get(),url,null,params, Charsets.UTF_8,Charsets.UTF_8);
    }


    /**
     * get请求 headers,params
     * @param url
     * @param headers
     * @param params
     * @return
     */
    public String get(String url, Map<String,String> headers, Map<String,String> params){
        return doHttp(RequestBuilder.get(),url,headers,params, Charsets.UTF_8,Charsets.UTF_8);

    }
    /**
     * get请求 headers
     *
     * @param url 请求url
     * @param headers 请求头
     * @return 数据 页面数据
     */
    public String getWithHeader(String url, Map<String, String> headers) {
        return doHttp(RequestBuilder.get(),url,headers,null,Charsets.UTF_8,Charsets.UTF_8);
    }

    /**
     *
     * @param url
     * @return
     */
    public String post(String url){
        return doHttp(RequestBuilder.post(),url,null,null, Charsets.UTF_8,Charsets.UTF_8);
    }

    /**
     * post请求 params
     *
     * @param url 请求url
     * @param params post请求参数
     * @return html 页面数据
     */
    public String post(String url, Map<String, String> params){
        return doHttp(RequestBuilder.post(),url,null,params, Charsets.UTF_8,Charsets.UTF_8);

    }

    /**
     * post请求 headers params
     * @param url
     * @param headers
     * @param params
     * @return
     */
    public String post(String url, Map<String, String> headers, Map<String, String> params){
        return doHttp(RequestBuilder.post(),url,headers,params, Charsets.UTF_8,Charsets.UTF_8);

    }
    /**
     * post请求 headers
     * @param url
     * @param headers
     * @return
     */
    public String postWithHeader(String url, Map<String,String> headers){
        return doHttp(RequestBuilder.post(),url,headers,null, Charsets.UTF_8,Charsets.UTF_8);
    }



    /**
     * 转换参数列表用于get请求
     *
     * @param paramsMap
     * @return
     */
    /**
     * 对get请求的参数封装
     * @param url
     * @param param
     * @param isEncode 是否需要urlEncode
     * @return
     */
    public String getUrl(String url, Map<String,String> param, Boolean isEncode){
        StringBuilder sb = new StringBuilder(url);
        sb.append(url);
        try {
            if (Objects.nonNull(param)&&param.size()>0){
                if (url.indexOf("?") == -1) {
                    sb.append("?");
                }
                if (sb.charAt(sb.length() - 1) != ('?')) {
                    sb.append("&");
                }
                for (Map.Entry<String,String> entry: param.entrySet()) {
                    if (isEncode){
                        sb.append(URLEncoder.encode(entry.getKey(), DEFAULT_CHARSET));
                    }else {
                        sb.append(entry.getKey());
                    }
                    sb.append("=");
                    if (isEncode){
                        sb.append(URLEncoder.encode(entry.getValue(), DEFAULT_CHARSET));
                    }else {
                        sb.append(entry.getValue());
                    }
                    sb.append("&");
                }
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }


    private void setHeaders(Map<String,String> headers, RequestBuilder requestBuilder){
        if (MapUtils.isNotEmpty(headers)) {
            headers = Maps.filterValues(headers, Objects::nonNull);
            requestBuilder.addHeader("Accept", "application/json");
            Set<Map.Entry<String, String>> entrys = headers.entrySet();
            for (Map.Entry<String, String> entry : entrys) {
                requestBuilder.addHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * 支持post put get delete
     * @param requestBuilder
     * @param paramsMap
     * @param method
     * @param charset
     */
    private void setParams(RequestBuilder requestBuilder, Map<String, String> paramsMap, String method, Charset charset){
        if (MapUtils.isEmpty(paramsMap)) {
            return;
        }
        if (HttpGet.METHOD_NAME.equals(method) || HttpDelete.METHOD_NAME.equals(method)) {
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                requestBuilder.addParameter(entry.getKey(), entry.getValue());
            }
        } else if (HttpPost.METHOD_NAME.equals(method) || HttpPut.METHOD_NAME.equals(method)) {
            List<NameValuePair> params = new ArrayList<>();
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            requestBuilder.setEntity(new UrlEncodedFormEntity(params, charset));
        }
    }
    private String doHttp(RequestBuilder requestBuilder, String uri, Map<String, String> headers, Map<String, String> parameters, Charset reqCharset, Charset resCharset) {
        if (Objects.isNull(requestBuilder)|| StringUtils.isBlank(uri)) {
            LOGGER.info("requestBuilder cannot be empty or uri cannot be empty, uri:{}", uri);
            return null;
        }

        String method = requestBuilder.getMethod();
        LOGGER.info("{}, uri:{}, headers:{}, parameters:{}", method, uri, headers, StringUtils.substring(JSON.toJSONString(parameters)+ "", 0, 200));
        HttpEntity httpEntity = null;
        CloseableHttpResponse httpResponse = null;
        try {
            /************** 设置URI **************/
            requestBuilder.setUri(uri);
            /************** 设置Header **************/
            this.setHeaders(headers,requestBuilder);
            /************** 设置Parameter **************/
            this.setParams(requestBuilder, parameters, method,reqCharset);
            /************** 发送请求 **************/
            httpResponse = httpClient.execute(requestBuilder.build());
            httpEntity = httpResponse.getEntity();
            String response = EntityUtils.toString(httpEntity, resCharset);

            LOGGER.info("{}, uri:{}, response:{}", method, uri, StringUtils.substring(response, 0, 200));
            return response;
        } catch (Exception e) {
            LOGGER.error("{}, uri:{}, headers:{}, parameters:{}", method, uri, headers, StringUtils.substring(JSON.toJSONString(parameters) + "", 0, 200), e);
            return null;
        } finally {
            EntityUtils.consumeQuietly(httpEntity);
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
            } catch (Exception e) {
                LOGGER.error("{}, httpResponse.close error", method, e);
            }
        }
    }





}
