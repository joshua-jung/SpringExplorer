package me.jungor.common.bean;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import me.jungor.common.exception.ParameterException;
import me.jungor.common.json.JsonUtil;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.List;


/**
 * 分页
 */
@Data
@Slf4j
public class BasePageInvo implements Serializable {

	private Integer pageSize = 20;

	private Integer maxPageSize = 50;

	private boolean isCount = true;

	private Integer pageNo = 1;

	/**
	 * 格式：[{"column":"", "asc":true}]
	 */
	private String orderBys;


	public void setPageSize(Integer pageSize) {
		if (pageSize <= maxPageSize) {
			this.pageSize = pageSize;
		}
		else {
			this.pageSize = maxPageSize;
		}
	}


	/**
	 * 过滤常量值 过滤关键字
	 */
	public String getSort() {
		if (StringUtils.isEmpty(orderBys)) {
			return null;
		}

		List<OrderBy> orderByList;
		try {
			orderByList = JsonUtil.deserialize(orderBys, new TypeReference<List<OrderBy>>() {

			});
		}
		catch (Exception ex) {
			log.error("解析排序字段列表失败:{}", ex.getMessage());
			throw new ParameterException();
		}

		if (!CollectionUtils.isEmpty(orderByList)) {
			StringBuilder stringBuilder = new StringBuilder();
			int index = 0;
			for (OrderBy orderBy : orderByList) {
				// 过滤
				if (StringUtils.isEmpty(orderBy.getColumn()) || isConstant(orderBy.getColumn()) || hasWhitespace(orderBy.getColumn())) {
					continue;
				}

				if (index++ > 0) {
					stringBuilder.append(",");
				}
				stringBuilder
						.append(orderBy.getColumn())
						.append(" ")
						.append(orderBy.isAsc() ? "asc" : "desc");
			}
			return stringBuilder.toString();
		}
		return null;
	}


	private boolean isConstant(String string) {
		if (string.contains("'")) {
			return true;
		}
		for (int i = 0; i < string.length(); ++i) {
			if (!Character.isDigit(string.codePointAt(i))) {
				return false;
			}
		}

		return true;
	}


	private boolean hasWhitespace(String column) {
		for (int i = 0; i < column.length(); i++) {
			if (column.charAt(i) == 9 || column.charAt(i) == 10 || column.charAt(i) == 12 || column.charAt(i) == 13 || column.charAt(i) == 32) {
				return true;
			}
		}
		return false;
	}


	@Data
	public static class OrderBy {

		private String column;

		private boolean asc = true;

	}

}
