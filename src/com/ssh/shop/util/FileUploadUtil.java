package com.ssh.shop.util;

import java.io.File;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ssh.shop.model.fileupload.FileUpload;

@Component("uploadUtil")  //代表工具类
public class FileUploadUtil implements UploadUtil {
	String filePath = "images";
	@Value("#{prop.filePath}") //基于注解的注入属性，并获取properties的值，并且使用Spring SpEl表达式#{beanid.porperty}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	/**
	 * 通过文件名获取扩展名
	 */
	private String getFileExt(String fileName){
		return FilenameUtils.getExtension(fileName);
	}
	
	/**
	 * 生成UUID随机数，作为新的文件名
	 */
	private String getNewFileName(String fileName){
		System.out.println(fileName);
		String ext = getFileExt(fileName);
		return UUID.randomUUID() + "." + ext;
	}
	
	/* (non-Javadoc)
	 * @see com.ssh.shop.util.UploadUtil#uploadFile(com.ssh.shop.model.fileupload.FileUpload)
	 */
	@Override
	public String uploadFile(FileUpload fileUpload){
		//获取新的文件名
		String newName = getNewFileName(fileUpload.getUploadFileName());
		try {
			System.out.println("@prop.filePath="+filePath+ newName);
			FileUtil.copyFile(fileUpload.getUpload(), new File(filePath + newName));
			return newName;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			fileUpload.getUpload().delete();
		}
	}
	
	public static void main(String[] args) {
		String relativelyPath=System.getProperty("user.dir");
		System.out.println(relativelyPath+"\\");
	}
}
