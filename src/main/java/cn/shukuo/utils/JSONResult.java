package cn.shukuo.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONResult {
	/**
	 * 自定义响应数据结构 
	 * 200：表示成功*
	 * 201:表示邮箱重复
	 * 202:表示数据不存在或登录信息错误
	 * 203:表示未激活用户
	 * 500：表示错误，错误信息在msg字段中*
	 * 501：bean验证错误，不管多少个错误都以map形式返回
	 * 502：拦截器拦截到用户token出错 
	 * 555：异常抛出信息
	 * 
	 * @author forev
	 *
	 */

	// 定义jackson对象
	private static final ObjectMapper MAPPER = new ObjectMapper();

	// 响应业务状态
	private Integer status;

	// 响应消息
	private String msg;

	// 响应中的数据
	private Object data;

	public JSONResult() {

	}

	public static JSONResult build(Integer status, String msg, Object data) {
		return new JSONResult(status, msg, data);
	}

	public static JSONResult ok(Object data) {
		return new JSONResult(data);
	}

	public static JSONResult ok() {
		return new JSONResult(null);
	}

	public static JSONResult noObject() {
		return new JSONResult(200, "object not exist", null);
	}

	public static JSONResult noStock() {
		return new JSONResult(202, "no enough stock", null);
	}

	public static JSONResult errorMsg() {
		return new JSONResult(500, "internal error", null);
	}

	public static JSONResult errorMap(Object data) {
		return new JSONResult(501, "bean error", data);
	}

	public static JSONResult errorTokenMsg(String msg) {
		return new JSONResult(502, msg, null);
	}

	public static JSONResult errorException(String msg) {
		return new JSONResult(555, msg, null);
	}

	public JSONResult(Integer status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public JSONResult(Object data) {
		this.status = 200;
		this.msg = "OK";
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 
	 * @Description: 将json结果集转化为JSONResult对象 需要转换的对象是一个类
	 * @param jsonData
	 * @param clazz
	 * @return
	 */
	public static JSONResult formatToPojo(String jsonData, Class<?> clazz) {
		try {
			if (clazz == null) {
				return MAPPER.readValue(jsonData, JSONResult.class);
			}
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if (clazz != null) {
				if (data.isObject()) {
					obj = MAPPER.readValue(data.traverse(), clazz);
				} else if (data.isTextual()) {
					obj = MAPPER.readValue(data.asText(), clazz);
				}
			}
			return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
		} catch (Exception e) {
			return null;
		}
	}

}
