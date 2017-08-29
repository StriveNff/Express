package com.niuff.common.http;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.niuff.common.http.request.AesSecret;
import com.niuff.common.http.request.IRequest;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;


/**
 * 网络请求类
 */
public class AsyncHttp {

	private static Gson mGson = null;
	private OkHttpClient okHttpClient = new OkHttpClient.Builder()
			.connectTimeout(30 * 1000, TimeUnit.MILLISECONDS)
			.readTimeout(30 * 1000, TimeUnit.MILLISECONDS)
			.build();
	private Context mContext;

	public AsyncHttp(Context context) {
		this();
		mContext = context;
	}

	public AsyncHttp() {
		if (mGson == null) {
			mGson = new GsonBuilder()
					.setLongSerializationPolicy(LongSerializationPolicy.DEFAULT)
					.create();
		}
		OkHttpUtils.initClient(okHttpClient);
	}

	public void get(IRequest request, IHttpListener listener) {
		Log.i("log", "post: url=" + request.getUrl());
		if (request != null) {
			request.getParams().getGetBuilder().url(request.getUrl()).id(request.getRequestId()).
					build().execute(new HttpResponse(listener, request.getParserType()));
		} else {
			throw new RuntimeException("Request param is null");
		}
	}

	public void post(IRequest request, IHttpListener listener) {
		Log.i("loglog", request.getUrl() + "?" + request.getParams().toString());
		if (request != null) {
			request.getParams().getPostFormBuilder().url(request.getUrl()).id(request.getRequestId()).
					build().execute(new HttpResponse(listener, request.getParserType()));
		} else {
			throw new RuntimeException("Request param is null");
		}
	}

	public void cancelRequest() {
		if (mContext != null) {
			OkHttpUtils.getInstance().cancelTag(mContext);
		}
	}


	abstract class StringCallback extends Callback<String> {
		@Override
		public String parseNetworkResponse(okhttp3.Response response, int id) throws IOException {
			return response.body().string();
		}
	}

	/**
	 * 网络请求返回接口
	 */
	class HttpResponse extends Callback<Object> {
		private IHttpListener mHttpListener;
		private Type mParserType;

		public HttpResponse(IHttpListener httpListener, Type parserType) {
			mHttpListener = httpListener;
			mParserType = parserType;
		}

		@Override
		public void onBefore(Request request, int id) {
			if (mHttpListener != null) {
				mHttpListener.onStart(id);
			}
		}

		@Override
		public Object parseNetworkResponse(okhttp3.Response response, int id) throws Exception {

			Object responseData = null;
			if (mHttpListener != null && response != null) {
				if (response.isSuccessful()) {
					String content = response.body().string();
					if (content != null) {
						try {
							if (content.contains("{")) {
								responseData = mGson.fromJson(content, mParserType);
							} else {

								responseData = mGson.fromJson(AesSecret.decode(content), mParserType);
							}
						} catch (Exception e) {
							e.printStackTrace();
							mHttpListener.onFailure(id, response.code(), e);
						}
					}
				} else {
					mHttpListener.onFailure(id, response.code(), new Exception("net error"));
				}
			}
			return responseData;
		}

		@Override
		public void onError(okhttp3.Call call, Exception e, int id) {
			if (mHttpListener != null) {
				if (!call.isCanceled()) {
					mHttpListener.onFailure(id, 0, e);
				}
			}
		}


		@Override
		public void onResponse(Object response, int id) {
			if (mHttpListener != null) {
				try {
					if (mHttpListener != null) {
						mHttpListener.onSuccess(id, response);
					}
				} catch (Exception e) {
					e.printStackTrace();
					mHttpListener.onFailure(id, 0, e);
				}
			}
		}
	}

}
