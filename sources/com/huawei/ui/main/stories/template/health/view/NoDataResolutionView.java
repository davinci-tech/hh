package com.huawei.ui.main.stories.template.health.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;

/* loaded from: classes7.dex */
public class NoDataResolutionView extends BaseNoDataView {
    private HealthTextView mResolutionDescribeTv;

    public NoDataResolutionView(Context context) {
        super(context);
    }

    public NoDataResolutionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_no_data_resolution_describe, (ViewGroup) this, false);
        addView(inflate);
        initView(inflate);
    }

    public NoDataResolutionView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private void initView(View view) {
        this.mResolutionDescribeTv = (HealthTextView) view.findViewById(R.id.no_data_resolution_tv_describe);
    }

    public void setData(String str) {
        HealthTextView healthTextView = this.mResolutionDescribeTv;
        if (healthTextView != null) {
            healthTextView.setText(str);
        }
    }
}
