package com.huawei.hms.iapfull.widget.actionbar;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huawei.health.R;

/* loaded from: classes4.dex */
public class CustomActionBar extends RelativeLayout {
    private static final String TAG = "CustomActionBar";
    private Activity activity;
    private ImageView backIcon;
    private OnBackClickListener onBackClickListener;
    private OnRightImageClickListener onRightImageClickListener;
    private ImageView rightImage;
    private LinearLayout rightImageHotArea;
    private TextView tvTitle;

    public interface OnBackClickListener {
        void onBackClick();
    }

    /* loaded from: classes9.dex */
    public interface OnRightImageClickListener {
        void onRightImageClick();
    }

    public void setTitle(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.tvTitle.setText(str);
    }

    public void setTitle(int i) {
        this.tvTitle.setText(i);
    }

    public void setRightImageVisibility(int i) {
        this.rightImageHotArea.setVisibility(i);
    }

    public void setRightImageResource(int i) {
        this.rightImage.setImageResource(i);
    }

    public void setOnRightImageClickListener(OnRightImageClickListener onRightImageClickListener) {
        this.onRightImageClickListener = onRightImageClickListener;
    }

    public void setOnBackClickListener(OnBackClickListener onBackClickListener) {
        this.onBackClickListener = onBackClickListener;
    }

    public void setActionbarTextColorWhite() {
        if (getContext() != null) {
            this.tvTitle.setTextColor(getContext().getResources().getColor(R.color.emui_white));
            this.backIcon.setColorFilter(-1);
        }
    }

    public CustomActionBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.activity = (Activity) context;
        LayoutInflater.from(context).inflate(R.layout.hwpay_custom_actionbar, this);
        this.tvTitle = (TextView) findViewById(R.id.tv_title);
        this.backIcon = (ImageView) findViewById(R.id.back_icon);
        this.rightImage = (ImageView) findViewById(R.id.right_image);
        this.rightImageHotArea = (LinearLayout) findViewById(R.id.right_image_hot_area);
        ((RelativeLayout) findViewById(R.id.layout_back)).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.hms.iapfull.widget.actionbar.CustomActionBar.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CustomActionBar.this.onBackClickListener != null) {
                    CustomActionBar.this.onBackClickListener.onBackClick();
                }
            }
        });
        this.rightImageHotArea.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.hms.iapfull.widget.actionbar.CustomActionBar.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CustomActionBar.this.onRightImageClickListener != null) {
                    CustomActionBar.this.onRightImageClickListener.onRightImageClick();
                }
            }
        });
    }
}
