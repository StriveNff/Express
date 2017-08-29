package com.niuff.common.http;

import android.content.Context;

import com.niuff.common.http.request.IRequest;

/**
 * description:新的DataSource层
 * author: linqiang
 * date:2016/12/19   15:51
 */

public class CommonDataSource {
	protected AsyncHttp mAsyncHttp;
	protected Context mContext;
	CommonDataSource commonDataSource;

	public CommonDataSource(Context context) {
		mAsyncHttp = new AsyncHttp(context);
		//commonDataSource = new CommonDataSource(context);
	}

	public void httpPost(IRequest request, IHttpListener httpListener) {
		mAsyncHttp.post(request, httpListener);
	}

	public void destroy() {
		mContext = null;
		mAsyncHttp.cancelRequest();
	}
}
