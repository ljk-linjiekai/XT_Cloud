package com.xintu.common.utils;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 用jackson来进行json序列化
 * @author Kyon
 *
 */
public class JacksonMapper {

	/**这个可以复用，用于json绑定obj*/
	private static ObjectMapper objectMapper;
	
	static{
		objectMapper = new ObjectMapper();		
		objectMapper.setSerializationInclusion(Include.NON_NULL);
//	    JaxbAnnotationModule jaxbMod = new JaxbAnnotationModule();
//	    jaxbMod.setPriority(Priority.SECONDARY);
//	    objectMapper.registerModule(jaxbMod);
	    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	/**
	 * 对象转json
	 * @param obj
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static String toJson(Object obj) 
			throws JsonGenerationException, JsonMappingException, IOException {
		return objectMapper.writeValueAsString(obj);
	}

	/**
	 * json转回对象，使用了泛型
	 * @param jsonStr
	 * @param clazz
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <T> T fromJson(String jsonStr, Class<T> clazz) 
			throws JsonGenerationException, JsonMappingException, IOException {
		return objectMapper.readValue(jsonStr, clazz);
	}
}