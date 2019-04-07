package com.xintu.common.vo;

import java.io.Serializable;
import java.util.List;

public class DataGridResult implements Serializable{

	/**
	 * TODO
	 */
	private static final long serialVersionUID = 1L;

	private Long total;
	
	//?表示占位符
	private List<?> rows;

	public DataGridResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DataGridResult(Long total, List<?> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	
	
}
