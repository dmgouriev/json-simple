package org.json.simple;

import org.json.simple.writer.JSONWriter;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.parser.ParseException;

/**
 * A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
 *
 * @author FangYidong<fangyidong @ yahoo.com.cn>
 */
public class JSONObject<K, V> extends HashMap<K, V> implements Map<K, V>, JSONAware, JSONStreamAware, Serializable {

	private static final long serialVersionUID = -503443796854799292L;

	/**
	 * Constructs an empty JSONObject.
	 */
	public JSONObject() {
		super();
	}

	/**
	 * Constructs an empty JSONObject with the specified initial capacity and the default load factor (0.75).
	 *
	 * @param initialCapacity the initial capacity
	 */
	public JSONObject(int initialCapacity) {
		super(initialCapacity);
	}

	/**
	 * Constructs an empty JSONObject with the specified initial capacity and load factor.
	 *
	 * @param initialCapacity the initial capacity
	 * @param loadFactor      the load factor
	 */
	public JSONObject(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	/**
	 * Allows creation of a JSONObject from a Map. After that, both the
	 * generated JSONObject and the Map can be modified independently.
	 *
	 * @param map
	 */
	public JSONObject(Map<K, V> map) {
		super(map);
	}

	/**
	 * Allows creation of a JSONObject from a String.
	 * @param str
	 */
	public static JSONObject of(String str) {
        return of(str, true);
	}

	/**
	 * Allows creation of a JSONObject from a String.
	 * @param str
	 * @param nullOnFail
	 */
	public static JSONObject of(String str, boolean nullOnFail) {
		try {
			return (JSONObject) JSONValue.parseWithException(str);
		} catch (ParseException e) {
			if (nullOnFail) {
				return null;
			} else {
				return new JSONObject();
			}
		}
	}

	/**
	 * Return value typed as T. If value is null or cast exception return default value
	 *
	 * @param key          key
	 * @param defaultValue default value
	 * @param <T>          result type
	 * @return if exists value otherwise defaultValue
	 */
	public <T> T get(Object key, T defaultValue) {
		try {
			return (T) super.getOrDefault(key, (V) defaultValue);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public void writeJSONString(Writer out) throws IOException {
		JSONWriter.writeJSONString(this, out);
	}

	public String toJSONString() {
		return JSONWriter.toJSONString(this);
	}

	public String toString() {
		return toJSONString();
	}

	public static String toString(String key, Object value) {
		StringBuffer sb = new StringBuffer();
		sb.append('\"');
		if (key == null)
			sb.append("null");
		else
			JSONValue.escape(key, sb);
		sb.append('\"').append(':');

		sb.append(JSONValue.toJSONString(value));

		return sb.toString();
	}

}
