package com.niuff.common.base;

import android.content.Context;

/**
 * description:MVP中View的基本接口
 * author: zhm
 * time:2016/9/3 16:19
 */

public interface BaseView<T> {
	/**
	 * 网络加载或耗时加载时界面显示
	 */
	void showLoading();

	/**
	 * 网络加载或耗时加载完成时界面显示
	 */
	void dismissLoading();
	abstract Context getContext();
}
