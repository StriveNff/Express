package com.niuff.common.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.niuff.common.R;


/**
 * 
* @packgeName:com.bdt.app.view
* @ClassName: LoadingDialog 
* @Description: 等待加载中view
* @author:linqiang
* @date 2016年8月19日 下午1:29:58
 */
public class LoadingDialog extends Dialog {
	Context context;
	String content;
	public LoadingDialog(Context context) {
		super(context);
		this.context = context;
	}

	public LoadingDialog(Context context, int theme) {
		super(context, theme);
		this.context = context;
	}
	public LoadingDialog(Context context, int theme, String content) {
		super(context, theme);
		this.context = context;
		this.content=content;
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.dialog_loading);
		TextView textView = (TextView) findViewById(R.id.tv_loading_text);
		textView.setText(content);

	}

}
