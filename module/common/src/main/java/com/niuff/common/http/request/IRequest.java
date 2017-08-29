package com.niuff.common.http.request;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.niuff.common.http.RequestParams;

import java.lang.reflect.Type;

/**
 * description:网络请求地址参数管理类
 * class: IRequest
 * params:
 * author: linqiang
 * date:2017/7/26 10:49
 */

public abstract class IRequest extends IDontObfuscate {
	protected RequestParams mParams = new RequestParams();
	public int mRequestId = 0;
	protected final static Gson mGson = new Gson();
	protected Context mContext;
	//正式发布的时候打开下面所有域名!!!

//	public static final String HOST_PUBLIC = "http://app.baoduitong.com";
//	public static final String HOST_PUBLIC_ShopPay = "http://finance.baoduitong.com";//支付
//	public static final String HOST_UNION_PAY="http://etc.baoduitong.com/";//ETC正式


//	public static final String HOST_NEW="http://ext.baoduitong.com/";//新接口请求地址
	//在开发测试阶段使用内部服务器域名!!!(所有充值消费的测试请检查域名是否正确!!!!)
	public static final String HOST_PUBLIC = "http://test.baoduitong.com";
	public static final String HOST_PUBLIC_ShopPay = "http://60.2.180.146:59696/bdt.pay/";//支付
	//public static final String HOST_UNION_PAY = "http://test.baoduitong.com/etc/";//ETC测试
	public static final String HOST_UNION_PAY = "http://test.baoduitong.com/etc/";//ETC测试
	public static final String HOST_NEW = "http://60.2.180.146:59696/";//新接口请求地址
	public static final String HOST_MESSAGE = HOST_PUBLIC + "/bdt.sns/app/sms/";//验证码相关


	public static final String HOST_PUBLIC_ZJ = "http://ext.baoduitong.com/zj/data/getData?par=";//中交数据 (生产测试一模一样)

	/**
	 * 新的支付接口请求地址(暂时未用)
	 */
	public static final String HOST_PAY = "http://60.2.180.146:59696/";

	public IRequest(Context context) {
		mContext = context;
	}

	/**
	 * 接口请求参数
	 */
	public RequestParams getParams() {
		String jsonData = getParamJson();
		Log.i("TAG", "getParams: " + jsonData);
		if (getUrl().contains("upLoadFile")) {
			mParams.put("param", jsonData);
		} else {
			String pStr = "";
			if (jsonData != null || !TextUtils.isEmpty(jsonData)) {
				pStr = AesSecret.encode(jsonData);
			} else {
				pStr = jsonData;
			}
			mParams.put("param", pStr);
		}
		return mParams;
	}

	/**
	 * 设置context
	 */
	public Context getContext() {
		return mContext;
	}

	/**
	 * 设置接口请求唯一标识
	 */
	public void setRequestId(int requestId) {
		mRequestId = requestId;
	}

	/**
	 * 获取请求的唯一标识
	 */
	public int getRequestId() {
		return mRequestId;
	}

	/**
	 * 当前接口的url地址
	 */
	public abstract String getUrl();

	/**
	 * 获取解析类型
	 */
	public abstract Type getParserType();

	/**
	 * 将参数转换为json
	 */
	public abstract String getParamJson();

}
