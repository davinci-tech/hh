package com.huawei.ui.main.stories.fitness.views.bloodsugar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.template.BaseComponent;
import com.huawei.ui.main.stories.template.ComponentParam;
import com.huawei.ui.main.stories.template.Constants;
import com.huawei.ui.main.stories.template.health.contract.DataDetailFragmentContract;
import java.util.List;

/* loaded from: classes6.dex */
public class BloodSugarLogoView extends RelativeLayout implements BaseComponent {
    private static final String TAG = "BloodSugarLogoView";
    private Context mContext;

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public View getView(Context context) {
        return this;
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void initComponent(List<ComponentParam> list) {
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void onCreate() {
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void onDayWeekYear(int i) {
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void onDestory() {
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void onPause() {
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void onResume() {
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void onStop() {
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void refreshView(boolean z) {
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void setDateStamp(long j) {
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void setPageType(Constants.PageType pageType) {
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void setPresenter(DataDetailFragmentContract.DetailFragmentPresenter detailFragmentPresenter) {
    }

    public BloodSugarLogoView(Context context) {
        this(context, null);
    }

    public BloodSugarLogoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initView();
    }

    private void initView() {
        Context context = this.mContext;
        if (context != null) {
            LayoutInflater from = LayoutInflater.from(context);
            if (from != null) {
                from.inflate(R.layout.health_data_bloodsugar_logo, this);
                return;
            }
            return;
        }
        LogUtil.h(TAG, "mContext is null");
    }
}
