package com.huawei.ui.commonui.errortip;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;

/* loaded from: classes6.dex */
public class ErrorTipBar extends RelativeLayout {
    public ErrorTipBar(Context context) {
        super(context);
        a(context);
    }

    public ErrorTipBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        a(context);
    }

    public ErrorTipBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public void setOnClicked(View.OnClickListener onClickListener) {
        setOnClickListener(onClickListener);
    }

    public void setTipText(int i) {
        ((HealthTextView) findViewById(R.id.main_tip_text)).setText(i);
    }

    public void setLeadBtnText(int i) {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.set_network_btn_layout);
        if (i == -1) {
            relativeLayout.setVisibility(8);
        } else {
            relativeLayout.setVisibility(0);
            ((HealthTextView) findViewById(R.id.lead_btn_text)).setText(i);
        }
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.error_tip_bar, this);
    }
}
