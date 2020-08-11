package com.szfg.bean;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  9:33 2020-08-03
 **/
public class FileUploadResult {

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
        return "FileUploadResult{" +
                "resultMsg='" + resultMsg + '\'' +
                ", resultCode='" + resultCode + '\'' +
                ", resultData=" + resultData +
                '}';
    }

    public static class ResultData {

        private String fileName;

        private String md5sum;

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getMd5sum() {
            return md5sum;
        }

        public void setMd5sum(String md5sum) {
            this.md5sum = md5sum;
        }

        @Override
        public String toString() {
            return "ResultData{" +
                    "fileName='" + fileName + '\'' +
                    ", md5sum='" + md5sum + '\'' +
                    '}';
        }
    }
}
