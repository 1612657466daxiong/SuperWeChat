package cn.ucai.superwechat.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 分页实体类
 */
public class Pager implements Serializable {
	private int currentPage;	//当前页号
	private int maxRecord;		//当前页面的最大记录数
	
	private List<?> pageData = null;

	public Pager() {
		
	}
	
	public Pager(int currentPage, int maxRecord) {
		this.currentPage = currentPage;
		this.maxRecord = maxRecord;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getMaxRecord() {
		return maxRecord;
	}

	public void setMaxRecord(int maxRecord) {
		this.maxRecord = maxRecord;
	}

	public List<?> getPageData() {
		return pageData;
	}

	public void setPageData(List<?> pageData) {
		this.pageData = pageData;
	}

	@Override
	public String toString() {
		return "Pager [currentPage=" + currentPage + ", maxRecord=" + maxRecord + ", pageData=" + pageData + "]";
	}
	
}
