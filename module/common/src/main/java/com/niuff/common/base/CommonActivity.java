package com.niuff.common.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;


/**
 * description:通用最上层activity
 * class: CommonActivity
 * params:
 * author: linqiang
 * date:2017/7/26 11:59
 */
public abstract class CommonActivity extends AppCompatActivity {
	public Dialog mLoadingDialog;
	protected Context mContext;
	private WindowManager windowManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		windowManager = getWindowManager();
}

	@Override
	protected void onPause() {
		super.onPause();
	}
	/**初始化actionbar*/
	public void initActionBar() {}

	/**显示网络请求的过场动画 参数:true 显示 false 不显示*/
	public void showLoading(boolean show) {
		initDialog(show, 0, "", false);
	}

	/**显示网络请求的过场动画 参数:true 显示 false 不显示,back true显示，false不显示*/
	public void showLoading(boolean show, boolean isShowBack) {
		initDialog(show, 0, "", isShowBack);
	}

	/** 显示网络请求的过场动画 参数:true 显示 false 不显示*/
	public void showCustomLoading(boolean show,String content) {
		initDialog(show, 1, content, false);
	}

	/**显示网络请求的过场动画 参数:true 显示 false 不显示*/
	public void showCustomLoading(boolean show, String content, boolean isShowBack) {
		initDialog(show, 1, content, isShowBack);
	}

	/**初始化弹窗*/
	protected void initDialog(boolean show, int type, String msg, boolean isShowBack) {
		try {
			if (show) {
				// 由于这个dialog可能是由不同的activity唤起，所以每次都新建
				if (mLoadingDialog == null) {
					mLoadingDialog = ShowLoding.ShowLodingDialog(this, type, msg, this, windowManager, isShowBack);
				}
				try {
					mLoadingDialog.show();
					mLoadingDialog.setCancelable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
					mLoadingDialog.cancel();
					mLoadingDialog = null;
				}
			}
		} catch (Exception e) {

		}
	}



}



