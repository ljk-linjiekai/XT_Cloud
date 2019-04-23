package com.xintu.manager.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xintu.manager.web.vo.PicUploadResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/pic")
@Controller
public class PicUploadController {

	//定义常见的图片格式
	private static final String[] IMAGE_TYPES = {".jpg",".png",".bmp",".jpeg",".gif",".JPG",".PNG",".BMP",".JPEG",".GIF"};
	
	
	//@Value("${TAOTAO_IMAGE_PATH}")
	private String TAOTAO_IMAGE_PATH;
	
	//json转换工具类
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	
	@RequestMapping("/upload")
	@ResponseBody
	public String upload(@RequestParam("uploadFile") MultipartFile multipartFile) throws JsonProcessingException {
		//返回结果
		PicUploadResult picUploadResult = new PicUploadResult();
		picUploadResult.setError(1);//非0表示成功
		//判断图片是否合法
		boolean isLegal = false;
		//1.校验图片后缀是否合法
		for (String string : IMAGE_TYPES) {
			if(multipartFile.getOriginalFilename().lastIndexOf(string) > 0){
				isLegal = true;
				break;
			}
		}
		
	/*	//2. 校验图片内容是否是图片
		if(isLegal){
			try {
				// 如果是图片流则不会出现异常，说明内容合法
				BufferedImage image = ImageIO.read(multipartFile.getInputStream());
				
				//3.上传图片
				//获取track server的地址的配置文件路径
				String trackerConf = this.getClass().getClassLoader().getResource("tracker.conf").toString().replaceAll("file:/", "");
				trackerConf = trackerConf.replaceAll("%20", " ");
				//设置track server的地址
				ClientGlobal.init(trackerConf);
				//创建trackerClient
				TrackerClient trackClient = new TrackerClient();
				//创建trackerServer
				TrackerServer trackerServer = trackClient.getConnection();
				//创建storageServer
				StorageServer storageServer = null;
				// 创建 StorageClient
				StorageClient storageClient = new StorageClient(trackerServer, storageServer);
				
				//上传文件
				//获取文件的后缀名
				String file_ext_name = StringUtils.substringAfterLast(multipartFile.getOriginalFilename(), ".");
				// 第一个参数为本地的图片路径，第二个参数为图片的后缀，第三个为文件的信息可以设置为null
				String[] fileInfos = storageClient.upload_file(multipartFile.getBytes(), file_ext_name, null);
				// 第一个值是组名，第二个是相对路径；两个字符串拼接后是完整图片路径
				String url = TAOTAO_IMAGE_PATH;
				for (String fileInfo : fileInfos) {
					url = url + "/" +fileInfo;
				}
				
				//设置返回结果的四个参数
				picUploadResult.setError(0);//0表示上传成功
				picUploadResult.setUrl(url);
				picUploadResult.setWidth(image.getWidth()+"");
				picUploadResult.setHeight(image.getHeight()+"");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
		
		//将返回结果转换成text/html格式，可以在响应数据中查看
		return MAPPER.writeValueAsString(picUploadResult);
	}
}
