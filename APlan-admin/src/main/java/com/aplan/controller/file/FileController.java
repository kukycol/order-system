
package com.aplan.controller.file;

import com.aplan.common.Oauth2Response;
import com.aplan.common.Oauth2ResponseCode;
import com.aplan.service.system.file.AttachFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "文件上传接口组")
@RestController
@RequestMapping("/sys/file")
public class FileController {

	@Autowired
	private AttachFileService attachFileService;

	@ApiOperation("文件上传")
	@PostMapping("/upload/local")
	public Oauth2Response upload(
			@ApiParam(value = "二进制文件数据",required = true) @RequestParam("file") MultipartFile file) {
		if(file.isEmpty()){
			return new Oauth2Response(Oauth2ResponseCode.FILE_NOT_NULL);
		}
		String fileName = attachFileService.localhostUploadFile(file);
		return new Oauth2Response(Oauth2ResponseCode.SAVE_SUCCESS,fileName);
	}


	@ApiOperation("文件删除")
	@DeleteMapping("/upload/delete")
	public void upload(
			@ApiParam(value = "文件访问路径",required = true) @RequestParam("fileName") String fileName) {
		attachFileService.deleteLocalhostUploadFile(fileName);
	}




}
