package com.szfg.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.szfg.bean.AccessTokenResult;
import com.szfg.bean.FileUploadResult;
import com.szfg.util.HttpRequestUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  9:36 2020-08-03
 **/
public class FileUploader {

    private String authorSecret;

    private String fileUploadPath;

    private String fileOutputPath;

    private String fileUploadUrl;
    // 是否同步到外网：1.需要，0.不需要
    private Integer inSync;

    private String token;

    private List<File> fileList = new ArrayList<>();

    public FileUploader() {
        this.authorSecret = "e573f29f472741af997bc6486e18baa9";
        this.fileUploadPath = "E:\\Download";
        this.fileUploadUrl = "/E/BasicDB/FileUpload";
        this.fileOutputPath = "E:\\works\\my-excel\\";
        this.inSync = 1;
    }

    public FileUploadResult doUp() throws IOException {
        // 获取token
        AccessToken accessToken = new AccessToken();
        AccessTokenResult accessTokenResult = accessToken.get();
        AccessTokenResult.ResultData resultData = accessTokenResult.getResultData();
        this.token = resultData.getAccessToken();
        // 设置个性化请求头
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("accessToken", token);
        headers.put("authorSecret", this.authorSecret);
        // 获取待上传文件列表
        findAll(new File(this.fileUploadPath));
        List<Object[]> dataList = new ArrayList<Object[]>();
        for (File file : fileList) {
            String fileName = file.getName().substring(0, (file.getName().indexOf("号")+1));
            Map<String, Object> fileParams = new HashMap<>();
            Map<String, String> textParams = new HashMap<>();
            fileParams.put("file", file);
            textParams.put("filename", fileName);
            textParams.put("inSync", this.inSync.toString());
            // 请求上传文件，一个一个上传
            String resultStr = HttpRequestUtil.formUpload(HttpRequestUtil.address + fileUploadUrl, headers, textParams, fileParams);
            System.out.println(resultStr);
            ObjectMapper objectMapper = new ObjectMapper();
            FileUploadResult fileUploadResult = objectMapper.readValue(resultStr, FileUploadResult.class);
            // 生成Excel需要的列数据
            FileUploadResult.ResultData data = fileUploadResult.getResultData();
            String md5 = data.getMd5sum();
            Object[] obj = new Object[2];
            obj[0] = fileName;
            obj[1] = md5;
            dataList.add(obj);
        }
        // 生成Excel
        String[] rowName = new String[]{"No.","fileName", "MD5"};
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String title = "文件名MD5映射";
        String excelName = "excel_" + sdf.format(date) + ".xls";
        ExportExcel exportExcel = new ExportExcel(title, rowName, dataList);
        File exportFile = new File(this.fileOutputPath+excelName);
        if (!exportFile.getParentFile().exists()) {
            exportFile.getParentFile().mkdirs();
            exportFile.createNewFile();
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(exportFile)) {
            exportExcel.doEx(fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void findAll(File file) {
        if (!file.exists()) {
            return;
        }
        if (file.isFile()) {
            System.out.println(file.getPath() + " " + file.getName());
            // todo 校验文件大小
            fileList.add(file);
        } else {
            File[] files = file.listFiles();
            if (files== null || files.length == 0) {
                System.out.println("no file");
            } else {
                for (File f : files) {
                    findAll(f);
                }
            }
        }
    }

    public static void main(String[] args) {
        FileUploader fileUploader = new FileUploader();
        try {
            fileUploader.doUp();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getAuthorSecret() {
        return authorSecret;
    }

    public void setAuthorSecret(String authorSecret) {
        this.authorSecret = authorSecret;
    }

    public String getFileUploadUrl() {
        return fileUploadUrl;
    }

    public void setFileUploadUrl(String fileUploadUrl) {
        this.fileUploadUrl = fileUploadUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getInSync() {
        return inSync;
    }

    public void setInSync(Integer inSync) {
        this.inSync = inSync;
    }

    public String getFileUploadPath() {
        return fileUploadPath;
    }

    public void setFileUploadPath(String fileUploadPath) {
        this.fileUploadPath = fileUploadPath;
    }

    public String getFileOutputPath() {
        return fileOutputPath;
    }

    public void setFileOutputPath(String fileOutputPath) {
        this.fileOutputPath = fileOutputPath;
    }
}
