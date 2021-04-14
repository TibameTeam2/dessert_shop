package com.live_support.model;

import java.util.List;

public interface HistroyService {
	/**
	 * 取得排序後歷史紀錄
	 */
	List<ChatData> findHistoryByUser(String userName);
}
