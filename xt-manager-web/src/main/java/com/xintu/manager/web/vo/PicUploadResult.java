package com.xintu.manager.web.vo;

import lombok.Data;

/**
 * kindEditor上传成功后返回的数据格式：
	包含的属性应该有：error(等于0表示成功，其它则失败)，url(图片地址)，width,height
 *
 */
@Data
public class PicUploadResult {

	private Integer error;
	
	private String url;
	
	private String width;
	
	private String height;


	
}
