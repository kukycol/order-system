package com.aplan.utils;

import lombok.SneakyThrows;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

/**
 * @Description: 文件转换工具类
 * @Author: kuky
 * @Date: 2021/12/23 15:39
 * @Version: 0.0.1
 */
public class FileUtil {


    /**
     * inputStream 转 File
     */
    public static File inputStreamToFile(InputStream ins, String name) {
        File file = new File(System.getProperty("java.io.tmpdir") + File.separator + name);
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            int bytesRead;
            int len = 8192;
            byte[] buffer = new byte[len];
            while ((bytesRead = ins.read(buffer, 0, len)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }


    /**
     * file转multipartFile
     */
    public static MultipartFile fileToMultipartFile(File file) {
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        FileItem item=factory.createItem(file.getName(),"text/plain",true,file.getName());
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        try {
            FileInputStream fis = new FileInputStream(file);
            OutputStream os = item.getOutputStream();
            while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new CommonsMultipartFile(item);
    }


    /**
     * url转MultipartFile
     * example: https://img2.baidu.com/it/u=982549611,3731122317&fm=26&fmt=auto&gp=0.jpg
     */
    public static MultipartFile urlToMultipartFile(String url)  {
        File file = null;
        MultipartFile multipartFile = null;
        try {
            HttpURLConnection httpUrl = (HttpURLConnection) new URL(url).openConnection();
            httpUrl.connect();
            file = inputStreamToFile(httpUrl.getInputStream(), UUID.randomUUID()+".jpg");
            multipartFile = fileToMultipartFile(file);
            httpUrl.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return multipartFile;
    }

    /**
     * @Description:
     * @Author: kuky
     * @Date: 2021/12/28 16:33
     * @param: saveFileName 保存的位置
     * @param: url 网络图片地址
     * @param: keyWord 文件名字
     * @Return void
     * @Version: 0.0.1
     */
    @SneakyThrows
    public static void downloadImage(String saveFileName, String url, String keyWord, String type) {
        File file=new File(saveFileName);
        if(!file.exists()){
            file.mkdir();
        }
        saveFileName=saveFileName+keyWord+"."+type;

        URL url1 = new URL(url);
        URLConnection uc = url1.openConnection();
        InputStream inputStream = null;
        FileOutputStream out = null;
        try {
            inputStream = uc.getInputStream();
            out = new FileOutputStream(saveFileName);
            int j = 0;
            while ((j = inputStream.read()) != -1) {
                out.write(j);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(out!=null){
                out.close();
            }
            if(inputStream!=null) {
                inputStream.close();
            }
        }

    }

}
