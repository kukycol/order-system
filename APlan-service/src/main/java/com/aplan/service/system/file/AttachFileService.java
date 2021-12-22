
package com.aplan.service.system.file;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * Created by lgh on 2018/07/27.
 */
public interface AttachFileService  {

	String localhostUploadFile(MultipartFile file);

	void deleteLocalhostUploadFile(String fileName);
}
