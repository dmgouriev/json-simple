/*
 * $Id: JSONArray.java,v 1.1 2006/04/15 14:10:48 platform Exp $
 * Created on 2006-4-10
 */
package org.json.simple;

import org.json.simple.writer.JSONWriter;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * A JSON array. JSONObject supports java.util.List interface.
 *
 * @author FangYidong<fangyidong @ yahoo.com.cn>
 */
public class JSONArray<T> extends ArrayList<T> implements JSONAware, JSONStreamAware, Serializable {
	private static final long serialVersionUID = 3957988303675231981L;

	/**
	 * Constructs an empty JSONArray.
	 */
	public JSONArray() {
		super();
	}

	/**
	 * Constructs an empty JSONArray with the specified initial capacity.
	 *
	 * @param initialCapacity the initial capacity of the list
	 */
	public JSONArray(int initialCapacity) {
		super(initialCapacity);
	}

	/**
	 * Constructs a JSONArray containing the elements of the specified
	 * collection, in the order they are returned by the collection's iterator.
	 *
	 * @param c the collection whose elements are to be placed into this JSONArray
	 */
	public JSONArray(Collection<T> c) {
		super(c);
	}


	/**
	 * Allows creation of a JSONArray from a String.
	 * @param str
	 */
	public static JSONArray of(String str) {
		return of(str, true);
	}

	/**
	 * Allows creation of a JSONArray from a String.
	 * @param str
	 * @param nullOnFail
	 */
	public static JSONArray of(String str, boolean nullOnFail) {
		try {
			return (JSONArray) JSONValue.parseWithException(str);
		} catch (ParseException e) {
			if (nullOnFail) {
				return null;
			} else {
				return new JSONArray();
			}
		}
    }

	public void writeJSONString(Writer out) throws IOException {
		JSONWriter.writeJSONString(this, out);
	}

	public String toJSONString() {
		return JSONWriter.toJSONString(this);
	}

	/**
	 * Returns a string representation of this array. This is equivalent to
	 * calling {@link JSONArray#toJSONString()}.
	 */
	public String toString() {
		return toJSONString();
	}
}
