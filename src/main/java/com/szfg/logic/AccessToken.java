package com.szfg.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.szfg.bean.AccessTokenResult;
import com.szfg.util.HttpRequestUtil;

import java.io.IOException;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  16:58 2020-07-31
 **/
public class AccessToken {

    private String appid;

    private String appsecret;

    private String tokenAccessUrl;

    public AccessToken() {
        this.appid = "59bb287138df47c7aeb55eeb8c41c4bb";
        this.appsecret = "fbb7b2b7be4448d289fec57f997c4679";
        this.tokenAccessUrl = "/B/BasicApi/GetAccessToken";
    }

    public AccessTokenResult get() {
        String params = "appid=" + this.appid + "&appsecret=" + this.appsecret;
        String resultStr = HttpRequestUtil.sendGet(HttpRequestUtil.address+this.tokenAccessUrl, params);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(resultStr, AccessTokenResult.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getTokenAccessUrl() {
        return tokenAccessUrl;
    }

    public void setTokenAccessUrl(String tokenAccessUrl) {
        this.tokenAccessUrl = tokenAccessUrl;
    }
}
