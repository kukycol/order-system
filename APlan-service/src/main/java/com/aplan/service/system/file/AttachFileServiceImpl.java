package com.aplan.service.system.file;

import cn.hutool.core.date.DateUtil;
import com.aplan.exception.custom.Oauth2Exception;
import com.aplan.utils.IPHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/** Created by lgh on 2018/07/27. */
@Service
public class AttachFileServiceImpl implements AttachFileService {

  // 绑定文件上传路径到uploadPath
  @Value("${web.upload-path}")
  private String uploadPath;

  @Value("${server.port}")
  private Integer port;

  public static final String NORM_MONTH_PATTERN = "yyyy/MM/dd/";


  @Override
  public String localhostUploadFile(MultipartFile file) {
    String pre ="cn/images/";
    String format = DateUtil.format(new Date(), NORM_MONTH_PATTERN);
    format = pre + format;
    File folder = new File(uploadPath + format);
    if (!folder.isDirectory()) {
      folder.mkdirs();
    }


    // 对上传的文件重命名，避免文件重名
    String oldName = file.getOriginalFilename();
    String newName =
        UUID.randomUUID().toString()
            + oldName.substring(oldName.lastIndexOf("."), oldName.length());
    try {
      // 文件保存
      file.transferTo(new File(folder, newName));
      // 返回上传文件的访问路径
      String filePath = format + newName;

      String baseUrl = "http://" + IPHelper.getInet4Address() + ":" + port + "/";


      return baseUrl+filePath;
    } catch (IOException e) {
      throw new Oauth2Exception("系统错误");
    }
  }

  @Override
  public void deleteLocalhostUploadFile(String fileName) {
    File folder = new File(uploadPath + fileName);
    folder.delete();
  }
}
