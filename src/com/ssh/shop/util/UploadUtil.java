package com.ssh.shop.util;

import com.ssh.shop.model.fileupload.FileUpload;

public interface UploadUtil {

	/**
	 * 实现文件上传，返回上传后新的文件名称
	 */
	public abstract String uploadFile(FileUpload fileUpload);

}