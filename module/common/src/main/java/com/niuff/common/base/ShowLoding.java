package com.niuff.common.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.niuff.common.R;


/**
 * description:
 * author: me
 * date:2017/2/22:17:25
 */
public class ShowLoding {
	Context context;


	/**
	 * 得到自定义的progressDialog
	 *
	 * @param context
	 * @param
	 * @return
	 */
	public static Dialog ShowLodingDialog(final Context context, int type, String msg, final Activity activity, WindowManager windowManager, boolean isShowBack) {

		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.showloding, null);// 得到加载view
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
		// main.xml中的ImageView
		ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);

		TextView tipTextView = (TextView) v.findViewById(R.id.tip);// 提示文字
		ImageView backImage = (ImageView) v.findViewById(R.id.back);
		if (isShowBack == true) {
			backImage.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					activity.finish();
				}
			});
		} else {
			backImage.setVisibility(View.GONE);
		}
		// 加载动画
		Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
				context, R.anim.showloding);
		// 使用ImageView显示动画
		spaceshipImage.startAnimation(hyperspaceJumpAnimation);
		tipTextView.setText(msg);// 设置加载信息
		if (type == 1) {
			tipTextView.setVisibility(View.VISIBLE);
		} else {
			tipTextView.setVisibility(View.GONE);
		}
		Dialog loadingDialog = new Dialog(context, R.style.showloding);// 创建自定义样式dialog

		loadingDialog.setCancelable(false);// 不可以用“返回键”取消
		loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
		WindowManager.LayoutParams lp = loadingDialog.getWindow().getAttributes();
		Display display = windowManager.getDefaultDisplay();
		lp.width = (int) (display.getWidth()); //设置宽度
		lp.height = (int) (display.getHeight());
		loadingDialog.getWindow().setAttributes(lp);
		return loadingDialog;

	}
}
