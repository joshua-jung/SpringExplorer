package me.jungor.common.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;

/**
 * 单例
 */
public interface JsonUtil {

	// 设置托管类防止外部直接获取objectMapper。
	class Holder {

		// 不设置private，为了JsonConfig能引用，同时外包不能引用。
		static ObjectMapper objectMapper;


		static {
			objectMapper = new ObjectMapper();
			config(objectMapper);
		}
	}


	// --------------------------------


	static void config(ObjectMapper objectMapper) {
		objectMapper
				// whether Map entries with null values are to be serialized
				.disable(SerializationFeature.WRITE_NULL_MAP_VALUES)
				// whether Map entries are sorted by key before serialization or not
				.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)
				// when a direct self-reference is detected by a POJO, either a JsonMappingException is thrown (if true), or reference is
				// normally processed (false).
				.disable(SerializationFeature.FAIL_ON_SELF_REFERENCES)
				// when no accessors are found for a type, If enabled, an exception is thrown; if disabled, they are serialized as empty
				// Objects without any properties.
				.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
				// whether encountering of unknown properties (there is no "any setter" or handler that can handle it) should throw a
				// JsonMappingException or not.
				.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
				// if an Object Id reference does not refer to an actual Object with that id, either an exception is thrown (true), or a
				// null object is used instead (false).
				.disable(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS);

	}


	/**
	 * @return 异常返回null
	 */
	@SneakyThrows
	static String serialize(Object value) {
		return Holder.objectMapper.writeValueAsString(value);
	}


	/**
	 * @return 异常返回null
	 */
	@SneakyThrows
	static <T> T deserialize(String value, Class<T> clazz) {
		if (clazz == String.class) {
			return (T) value;
		}

		return Holder.objectMapper.readValue(value, clazz);
	}


	/**
	 * @param typeRef example:
	 *                <p>
	 *                TypeReference ref = new TypeReference<List<Integer>>() { };
	 *                </p>
	 * @apiNote TypeReference holds java.lang.reflect.Type
	 */
	@SneakyThrows
	static <T> T deserialize(String value, TypeReference typeRef) {
		return Holder.objectMapper.readValue(value, typeRef);
	}


	/**
	 * @see #deserialize(String, TypeReference)
	 */
	@SneakyThrows
	static <T> T deserialize(byte[] value, TypeReference typeRef) {
		return Holder.objectMapper.readValue(value, typeRef);
	}


	/**
	 * 查找字段：
	 * node().findValue("fieldName").asText();
	 * node().findValuesAsText("fieldName");
	 * <p>
	 * 要加字段：
	 * String body = ((ObjectNode) jsonNode)
	 * .put("field1", value1)
	 * .put("field2", value2)
	 * .toString();
	 * </p>
	 * 获取空node：
	 * ObjectNode node = objectMapper.createObjectNode();
	 *
	 * @return 异常返回null
	 */
	@SneakyThrows
	static JsonNode node(String json) {
		return Holder.objectMapper.readTree(json);
	}


}
