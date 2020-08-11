package com.szfg.bean;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  17:03 2020-07-31
 **/
public class AccessTokenResult {

    private String resultMsg;

    private String resultCode;

    private ResultData resultData;

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public ResultData getResultData() {
        return resultData;
    }

    public void setResultData(ResultData resultData) {
        this.resultData = resultData;
    }

    @Override
    public String toString() {
        return "Result{" +
                "resultMsg='" + resultMsg + '\'' +
                ", resultCode='" + resultCode + '\'' +
                ", resultData=" + resultData +
                '}';
    }

    public static class ResultData {

        private String accessToken;

        private String tokenMsg;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getTokenMsg() {
            return tokenMsg;
        }

        public void setTokenMsg(String tokenMsg) {
            this.tokenMsg = tokenMsg;
        }

        @Override
        public String toString() {
            return "ResultData{" +
                    "accessToken='" + accessToken + '\'' +
                    ", tokenMsg='" + tokenMsg + '\'' +
                    '}';
        }
    }
}
