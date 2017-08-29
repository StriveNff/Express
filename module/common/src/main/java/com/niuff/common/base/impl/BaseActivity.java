package com.niuff.common.base.impl;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.niuff.common.R;
import com.niuff.common.base.CommonActivity;
import com.nostra13.universalimageloader.core.ImageLoader;


/**
 * description: 基础activity
 * class: BaseActivityPos
 * params:
 * author: linqiang
 * date:2017/7/26 12:57
 */

public abstract class BaseActivity extends CommonActivity {

	private TextView tvTitle;
	private ImageView ivTitleLeft;
	private ImageView ivTitleRight;
	private ImageView ibTitle;
	private TextView tvRight;
	protected Handler mHandler = new Handler();

	/**
	 * 图片加载
	 */
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	public ActionBar actionBar;

	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
		//setStatusBar();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		if (getLayoutId() != 0) {
			setContentView(getLayoutId());
		}
		initView();
		initWindows();
		setListener();
		initData();

	}

	/**
	 * 返回当前界面布局文件
	 */
	protected abstract int getLayoutId();

	/**
	 * 初始化View
	 */
	protected abstract void initView();

	/**
	 * 初始化所有数据的方法
	 */
	protected abstract void initData();

	/**
	 * 设置监听器
	 */
	protected abstract void setListener();

	@Override
	protected void onResume() {
		super.onResume();
	}

	/**
	 * 获取控件
	 */
	public <T extends View> T obtainView(int resId) {
		return (T) findViewById(resId);
	}

	/**
	 * 设置ActionBar
	 */
	@Override
	public void initActionBar() {

	}

	public void setToolbarContent(View view) {
		Log.i("linqiang", "setToolbarContent: ********************");
		tvTitle = (TextView) view.findViewById(R.id.tv_title);
		ivTitleLeft = (ImageView) view.findViewById(R.id.iv_left);
		ivTitleRight = (ImageView) view.findViewById(R.id.iv_right);
		tvRight = (TextView) view.findViewById(R.id.tv_right);
		ibTitle = (ImageView) view.findViewById(R.id.iv_title);
	}

	/**
	 * 设置titlebar左边样式
	 */
	public void setActionBarLeft(int resId) {
		if (ivTitleLeft != null) {
			if (resId != 0) {
				ivTitleLeft.setImageResource(resId);
				ivTitleLeft.setVisibility(View.VISIBLE);
			} else {
				ivTitleLeft.setVisibility(View.INVISIBLE);
				// ibLeftImage.setImageResource(0);
			}
		}
	}

	/**
	 * 设置titlebar左边样式
	 */
	public void setActionBarLeft(Bitmap resId) {
		if (ivTitleLeft != null) {
			ivTitleLeft.setImageBitmap(resId);
			ivTitleLeft.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * 设置titlebar右边样式
	 */
	public void setActionBarRight(boolean isShow, int imageResId,
								  String rightText) {
		if (isShow) {
			if (imageResId != 0) {
				if (ivTitleRight != null) {
					tvRight.setVisibility(View.GONE);
					ivTitleRight.setImageResource(imageResId);
					ivTitleRight.setVisibility(View.VISIBLE);
				}
			} else {

				tvRight.setVisibility(View.VISIBLE);
				ivTitleRight.setVisibility(View.GONE);
				tvRight.setText(rightText);

			}
		} else {
			ivTitleRight.setVisibility(View.GONE);
			tvRight.setVisibility(View.INVISIBLE);
		}
	}

	/**
	 * 设置标题 标题是一个ImageView 和一个TextView 重合的需要判断显示与否 boolean 为true 显示edittext
	 *
	 * @param title
	 */
	public void setActionBarTitle(Boolean bool, int resd, String title) {
		Log.i("linqiang", "setActionBarTitle: ************");
		if (bool) {// 显示图片
			ibTitle.setVisibility(View.VISIBLE);
			ibTitle.setImageResource(resd);
			tvTitle.setVisibility(View.GONE);
		} else {// 显示TextView
			ibTitle.setVisibility(View.GONE);
			tvTitle.setVisibility(View.VISIBLE);
			tvTitle.setText(title);
		}

	}

	/**
	 * 得到左边控件
	 */

	public ImageView getLiftImage() {
		return ivTitleLeft;
	}

	/**
	 * 设置左边点击监听
	 *
	 * @param leftClickListener
	 */
	public void setOnActionBarLeftClickListener(
			View.OnClickListener leftClickListener) {
		ivTitleLeft.setOnClickListener(leftClickListener);
	}

	/**
	 * 设置title点击监听
	 *
	 * @param leftClickListener
	 */
	public void setOnActionBartitleClickListener(
			View.OnClickListener leftClickListener) {
		ibTitle.setOnClickListener(leftClickListener);
	}

	/**
	 * 设置右边点击监听
	 *
	 * @param isImage            是否是图片
	 * @param rightClickListener
	 */
	public void setOnActionBarRightClickListener(boolean isImage,
												 View.OnClickListener rightClickListener) {
		if (isImage) {
			ivTitleRight.setOnClickListener(rightClickListener);
		} else {
			tvRight.setOnClickListener(rightClickListener);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		if (mLoadingDialog != null) {
			mLoadingDialog.dismiss();
		}

	}

	public void initWindows() {
		Window window = getWindow();
		int color = getResources().getColor(R.color.stasusBar);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
					| WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
			window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
					| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
					| View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			//设置状态栏颜色
			window.setStatusBarColor(color);
			//设置导航栏颜色
			//window.setNavigationBarColor(color);
			ViewGroup contentView = ((ViewGroup) findViewById(android.R.id.content));
			View childAt = contentView.getChildAt(0);
			if (childAt != null) {
				childAt.setFitsSystemWindows(true);
			}
		} else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			//透明状态栏
			window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			//透明导航栏
			//window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
			//设置contentview为fitsSystemWindows
			ViewGroup contentView = (ViewGroup) findViewById(android.R.id.content);
			View childAt = contentView.getChildAt(0);
			if (childAt != null) {
				childAt.setFitsSystemWindows(true);
			}
			//给statusbar着色
			View view = new View(this);
			view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(this)));
			view.setBackgroundColor(color);
			contentView.addView(view);
		}
	}

	/**
	 * 获取状态栏高度
	 *
	 * @param context context
	 * @return 状态栏高度
	 */
	private static int getStatusBarHeight(Context context) {
		// 获得状态栏高度
		int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
		return context.getResources().getDimensionPixelSize(resourceId);
	}
}
