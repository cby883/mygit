/**
 * 
 */
package com.esgov.jrw.sysmgrservice.common.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.sun.xml.internal.ws.util.UtilException;

/**
 * @author wuchuang
 * 
 * 2017年5月24日 下午4:11:41
 */
public class FastJSONUtil {
	
	private FastJSONUtil(){};
	
	private static SerializeConfig serializeConfig = new SerializeConfig();
	private static SerializerFeature[] serializerFeatures = new SerializerFeature[4];
	static{
		serializeConfig.put(java.util.Date.class, new SimpleDateFormatSerializer(TimeUtil.FORMAT_DAY));
		serializeConfig.put(java.sql.Date.class, new SimpleDateFormatSerializer(TimeUtil.FORMAT_DAY));
		serializeConfig.put(java.sql.Timestamp.class, new SimpleDateFormatSerializer(TimeUtil.FORMAT_SECOND));
		serializeConfig.put(java.sql.Time.class, new SimpleDateFormatSerializer(TimeUtil.FORMAT_TIME));
		
		serializerFeatures[0] = SerializerFeature.WriteNullStringAsEmpty;
		serializerFeatures[1] = SerializerFeature.WriteMapNullValue;
		serializerFeatures[2] = SerializerFeature.WriteNullListAsEmpty;
		serializerFeatures[3] = SerializerFeature.DisableCircularReferenceDetect;
	}
	
	/**
	 * 转成JSON对象
	 * @param json
	 * @return
	 * @throws UtilException
	 */
	public static JSONObject toJSONObject(String json) throws UtilException {
		return JSON.parseObject(json);
	}
	
	/**
	 * 转成JSON数组
	 * @param json
	 * @return
	 * @throws UtilException
	 */
	public static JSONArray toJSONArray(String json) throws UtilException{
		return JSON.parseArray(json);
	}
	
	/**
	 * 将Object转成JSON字符串
	 * @param obj
	 * @return
	 * @throws UtilException
	 */
	public static String toJSON(Object obj) throws UtilException{
		return JSON.toJSONString(obj,serializeConfig,serializerFeatures);
	}
	
	/**
	 * 转成Bean
	 * @param json
	 * @param clazz
	 * @return
	 * @throws UtilException
	 */
	public static <T>T convertToBean(String json,Class<T> clazz) throws UtilException{
		return JSON.parseObject(json, clazz);
	}
	
	/**
	 * 转成列表
	 * @param json
	 * @param clazz
	 * @return
	 * @throws UtilException
	 */
	public static <T>List<T> convertToList(String json,Class<T> clazz) throws UtilException{
		return JSON.parseArray(json, clazz);
	}
	
	/**
	 * 
	 * 转成Map<String,Object>>
	 * @param json
	 * @return
	 * @throws UtilException
	 */
	public static Map<String,Object> convertToMap(String json) throws UtilException{
		return JSONObject.parseObject(json, new TypeReference<Map<String,Object>>(){});
	}
	/**
	 * 
	 * 转成List<Map<String,Object>>
	 * @param json
	 * @return
	 * @throws UtilException
	 */
	public static List<Map<String,Object>> convertToListMap(String json) throws UtilException{
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		JSONArray array = FastJSONUtil.toJSONArray(json);
		Iterator<Object> itor=array.iterator();
		while(itor.hasNext()){
			JSONObject jsonObject = (JSONObject)itor.next();
			list.add(JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<Map<String,Object>>(){}));
		}
		return list;
	}
}
