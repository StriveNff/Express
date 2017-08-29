package com.niuff.common.base.impl;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bdt.app.bdt_common.R;
import com.bdt.app.bdt_common.base.CommonFragment;
import com.nostra13.universalimageloader.core.ImageLoader;

public abstract class BaseFragment extends CommonFragment {
	private TextView tvTitle;
	private ImageView ivTitleLeft;
	private ImageView ivTitleRight;
	private ImageView ibTitle;
	private TextView tvRight;
	/**
	 * 图片加载
	 */
	protected static ImageLoader imageLoader = ImageLoader.getInstance();
	protected View mRootView;
	protected Intent mBundleIntent;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		if (getLayoutId() != 0) {
			mRootView = inflater.inflate(getLayoutId(), container, false);
		}
		initView(mRootView);
		setUserVisibleHint(true);
		setListener();
		initData();
		Log.v("log", "createView");
		return mRootView;
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		if (hidden) {
			mBundleIntent = null;
		}
		super.onHiddenChanged(hidden);
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		// TODO Auto-generated method stub
		super.setUserVisibleHint(isVisibleToUser);
	}

	/**
	 * 此方法描述的是： 初始化所有数据的方法
	 *
	 * @author: zhm
	 * @version: 2014-3-12 下午3:17:46
	 */
	protected abstract void initData();

	/**
	 * 此方法描述的是： 获取布局
	 *
	 * @author: zhangwb
	 * @version: 2015-11-20 上午0:10:30
	 */
	protected abstract int getLayoutId();

	/**
	 * 此方法描述的是： 初始化界面
	 *
	 * @author: zhangwb
	 * @version: 2015-11-20 上午0:10:30
	 */
	protected abstract void initView(View rootView);

	/**
	 * 此方法描述的是： 初始化界面
	 *
	 * @author: zhangwb
	 * @version: 2015-11-20 下午13:10:30
	 */
	protected abstract void setListener();

	/**设置ActionBar*/
	public void initActionBar() {

	}
	public void setToolbarContent(View view){
		tvTitle = (TextView) view.findViewById(R.id.tv_title);
		ivTitleLeft = (ImageView) view.findViewById(R.id.iv_left);
		ivTitleRight = (ImageView) view.findViewById(R.id.iv_right);
		tvRight = (TextView) view.findViewById(R.id.tv_right);
		ibTitle = (ImageView) view.findViewById(R.id.iv_title);
	}


	public void setBundleIntent(Intent intent) {
		mBundleIntent = intent;
	}

	public <T extends View> T obtainView(int resId) {
		return (T) mRootView.findViewById(resId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

	}

	/**设置titlebar左边样式*/
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
	/**设置titlebar左边样式*/
	public void setActionBarLeft(Bitmap resId) {
		if (ivTitleLeft != null) {
			ivTitleLeft.setImageBitmap(resId);
			ivTitleLeft.setVisibility(View.VISIBLE);
		}
	}

	/**设置titlebar右边样式*/
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
				if("账单".equals(rightText)){
					tvRight.setVisibility(View.VISIBLE);
					ivTitleRight.setVisibility(View.GONE);
					tvRight.setText(rightText);

					Resources res = getResources();
					Drawable icon = res.getDrawable(R.mipmap.etc_recharge_mune);
					//调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
					icon.setBounds(0, 0, icon.getMinimumWidth(), icon.getMinimumHeight());
					tvRight.setCompoundDrawables(icon, null, null, null); //设置左图标
					tvRight.setCompoundDrawablePadding(5);

				}else{
					tvRight.setVisibility(View.VISIBLE);
					ivTitleRight.setVisibility(View.GONE);
					tvRight.setText(rightText);
				}

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
	 * @param isImage
	 *            是否是图片
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


}
