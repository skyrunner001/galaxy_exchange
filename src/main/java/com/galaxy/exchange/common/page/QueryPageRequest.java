package com.galaxy.exchange.common.page;

import java.io.Serializable;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

/**
 * Pageable基类，改写了spring data默认的实现，pageNum从1开始。
 */
public class QueryPageRequest implements Pageable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8177977477282846216L;

	/**
	 * 默认页码
	 */
	public final static int DEFAULT_PAGE_NO = 1;

	/**
	 * 默认分页大小
	 */
	public final static int DEFAULT_PAGE_SIZE = 10;
	public final static int DEFAULT_PAGE_SIZE_2 = 50;

	private final int pageNum;
	private final int pageSize;
	private final Sort sort;

	private long total;// 记录总数
	private long totalPage;// 页面总数

	/**
	 * @param pageNum
	 * @param pageSize
	 */
	public QueryPageRequest(int pageNum, int pageSize) {

		this(pageNum, pageSize, null);
	}

	/**
	 * @param pageNum
	 * @param pageSize
	 * @param direction
	 * @param properties
	 */
	public QueryPageRequest(int pageNum, int pageSize, Direction direction,
			String... properties) {

		this(pageNum, pageSize, new Sort(direction, properties));
	}

	/**
	 * @param pageNum
	 * @param size
	 * @param sort
	 */
	public QueryPageRequest(int pageNum, int pageSize, Sort sort) {

		if (1 > pageNum) {
			throw new IllegalArgumentException("pageNum从1开始，不能小于1！");
		}

		if (0 >= pageSize) {
			throw new IllegalArgumentException("pageSize必须大于0！");
		}

		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.sort = sort;
	}

	public int getPageSize() {

		return pageSize;
	}

	public int getPageNumber() {

		return pageNum;
	}

	/**
	 * 由于pageNum从1开始，因此计算Offset时要减1
	 */
	public int getOffset() {

		return (pageNum - 1) * pageSize;
	}

	public Sort getSort() {

		return sort;
	}

	@Override
	public boolean equals(final Object obj) {

		if (this == obj) {
			return true;
		}

		if (!(obj instanceof QueryPageRequest)) {
			return false;
		}

		QueryPageRequest that = (QueryPageRequest) obj;

		boolean pageEqual = this.pageNum == that.pageNum;
		boolean sizeEqual = this.pageSize == that.pageSize;

		boolean sortEqual = this.sort == null ? that.sort == null : this.sort
				.equals(that.sort);

		return pageEqual && sizeEqual && sortEqual;
	}

	@Override
	public int hashCode() {

		int result = 17;

		result = 31 * result + pageNum;
		result = 31 * result + pageSize;
		result = 31 * result + (null == sort ? 0 : sort.hashCode());

		return result;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

}
