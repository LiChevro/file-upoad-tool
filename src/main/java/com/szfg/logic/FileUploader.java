package com.szfg.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.szfg.bean.FileUploadResult;
import com.szfg.ui.component.ConsoleTextArea;
import com.szfg.util.HttpRequestUtil;
import com.szfg.util.SettingUtil;

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

    private final List<File> fileList = new ArrayList<>();
    private String authorSecret;
    private String fileUploadPath;
    private String fileOutputPath;
    private String fileUploadUrl;
    // 是否同步到外网：1.需要，0.不需要
    private int inSync;

    private boolean checkParam() {
        if (this.fileOutputPath == null) {
            ConsoleTextArea.startWriter("输出路径为空！");
            return false;
        } else if (this.fileUploadPath == null) {
            ConsoleTextArea.startWriter("输入路径为空！");
            return false;
        }
        return true;
    }

    public FileUploadResult doUp() {
        if (!this.checkParam()) {
            return null;
        }
        // 设置个性化请求头
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("accessToken", SettingUtil.getValues("accessToken"));
        headers.put("authorSecret", this.authorSecret);
        // 获取待上传文件列表
        findAll(new File(this.fileUploadPath));
        List<Object[]> dataList = new ArrayList<Object[]>();
        for (File file : fileList) {
            String fileName = file.getName().substring(0, (file.getName().indexOf("号") + 1));
            Map<String, Object> fileParams = new HashMap<>();
            Map<String, String> textParams = new HashMap<>();
            fileParams.put("file", file);
            textParams.put("filename", fileName);
            textParams.put("inSync", this.inSync + "");
            // 请求上传文件，一个一个上传
            String resultStr = HttpRequestUtil.formUpload(HttpRequestUtil.address + fileUploadUrl, headers, textParams, fileParams);
            ConsoleTextArea.startWriter(resultStr);
            ObjectMapper objectMapper = new ObjectMapper();
            FileUploadResult fileUploadResult = null;
            try {
                fileUploadResult = objectMapper.readValue(resultStr, FileUploadResult.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 生成Excel需要的列数据
            FileUploadResult.ResultData data = fileUploadResult.getResultData();
            String md5 = data.getMd5sum();
            Object[] obj = new Object[2];
            obj[0] = fileName;
            obj[1] = md5;
            dataList.add(obj);
        }
        // 生成Excel
        String[] rowName = new String[]{"No.", "fileName", "MD5"};
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String title = "文件名MD5映射";
        String excelName = "excel_" + sdf.format(date) + ".xls";
        ExportExcel exportExcel = new ExportExcel(title, rowName, dataList);
        File exportFile = new File(this.fileOutputPath + "\\" + excelName);
        try (FileOutputStream fileOutputStream = new FileOutputStream(exportFile)) {
            exportExcel.doEx(fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ConsoleTextArea.startWriter("处理完成！");
        return null;
    }

    private void findAll(File file) {
        if (!file.exists()) {
            return;
        }
        if (file.isFile()) {
            // todo 校验文件大小
            fileList.add(file);
        } else {
            File[] files = file.listFiles();
            if (files == null || files.length == 0) {
                ConsoleTextArea.startWriter("no file");
            } else {
                for (File f : files) {
                    findAll(f);
                }
            }
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
