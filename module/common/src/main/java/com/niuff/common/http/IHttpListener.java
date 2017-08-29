package com.niuff.common.http;


/**
 * description: 网络请求回调接口
 * author: linqiang
 * date:2017/6/15   9:26
 */

public interface IHttpListener {
	/**网络请求开始*/
	void onStart(int requestId);
	/**服务器数据返回*/
	void onSuccess(int requestId, Object response);
	/**服务器没有查询到数据*/
	void onNodata(int requestId,Object response);
	/**服务器异常*/
	void onServerError(int requestId, Object response);
	/**网络错误*/
	void onFailure(int requestId, int httpStatus, Throwable error);
}
