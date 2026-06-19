package com.github.notzero0907;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Md3Dialog extends Dialog {

    private String titleText = "标题";
    private String messageText = "这里是消息内容。";
    private String positiveText = "确定";
    private String negativeText = "取消";
    private View.OnClickListener positiveListener;
    private View.OnClickListener negativeListener;

    public Md3Dialog(Context context) {
        super(context);
    }

    // 链式设置方法（方便调用）
    public Md3Dialog setTitleText(String title) {
        this.titleText = title;
        return this;
    }

    public Md3Dialog setMessageText(String msg) {
        this.messageText = msg;
        return this;
    }

    public Md3Dialog setPositiveButton(String text, View.OnClickListener listener) {
        this.positiveText = text;
        this.positiveListener = listener;
        return this;
    }

    public Md3Dialog setNegativeButton(String text, View.OnClickListener listener) {
        this.negativeText = text;
        this.negativeListener = listener;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去掉默认标题栏，我们完全自定义
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 创建主布局（垂直排列）
        LinearLayout mainLayout = new LinearLayout(getContext());
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setPadding(60, 60, 60, 40);

        // 设置 MD3 白色背景 + 大圆角 (28dp)
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(Color.WHITE);
        bg.setCornerRadius(100); // 像素值，约 28dp (按手机密度换算，直接写大一点效果明显)
        mainLayout.setBackground(bg);

        // ----- 1. 标题 (TextView) -----
        TextView titleView = new TextView(getContext());
        titleView.setText(titleText);
        titleView.setTextSize(20);
        titleView.setTextColor(Color.parseColor("#1C1B1F")); // MD3 深灰
        titleView.setTypeface(android.graphics.Typeface.DEFAULT_BOLD);
        titleView.setPadding(0, 0, 0, 20);
        mainLayout.addView(titleView);

        // ----- 2. 消息内容 (TextView) -----
        TextView msgView = new TextView(getContext());
        msgView.setText(messageText);
        msgView.setTextSize(16);
        msgView.setTextColor(Color.parseColor("#49454F")); // MD3 中灰
        msgView.setPadding(0, 0, 0, 40);
        mainLayout.addView(msgView);

        // ----- 3. 按钮区域 (横向排列) -----
        LinearLayout buttonLayout = new LinearLayout(getContext());
        buttonLayout.setOrientation(LinearLayout.HORIZONTAL);
        buttonLayout.setGravity(android.view.Gravity.END); // 靠右对齐（MD3 规范）

        // 创建“取消”按钮
        Button negativeBtn = new Button(getContext());
        negativeBtn.setText(negativeText);
        negativeBtn.setBackgroundColor(Color.TRANSPARENT); // 透明背景
        negativeBtn.setTextColor(Color.parseColor("#6750A4")); // MD3 紫色
        negativeBtn.setTextSize(14);
        negativeBtn.setAllCaps(true);
        negativeBtn.setPadding(30, 10, 30, 10);
        if (negativeListener != null) {
            negativeBtn.setOnClickListener(v -> {
                dismiss();
                negativeListener.onClick(v);
            });
        } else {
            negativeBtn.setOnClickListener(v -> dismiss());
        }
        buttonLayout.addView(negativeBtn);

        // 创建“确定”按钮
        Button positiveBtn = new Button(getContext());
        positiveBtn.setText(positiveText);
        positiveBtn.setBackgroundColor(Color.TRANSPARENT);
        positiveBtn.setTextColor(Color.parseColor("#6750A4"));
        positiveBtn.setTextSize(14);
        positiveBtn.setAllCaps(true);
        positiveBtn.setPadding(30, 10, 30, 10);
        if (positiveListener != null) {
            positiveBtn.setOnClickListener(v -> {
                dismiss();
                positiveListener.onClick(v);
            });
        } else {
            positiveBtn.setOnClickListener(v -> dismiss());
        }
        buttonLayout.addView(positiveBtn);

        mainLayout.addView(buttonLayout);
        setContentView(mainLayout);

        // 设置弹窗宽度占屏幕的 85% 左右
        if (getWindow() != null) {
            getWindow().setLayout(
                (int) (getContext().getResources().getDisplayMetrics().widthPixels * 0.85),
                ViewGroup.LayoutParams.WRAP_CONTENT
            );
        }
    }
}