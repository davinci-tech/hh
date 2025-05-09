package com.huawei.ui.main.stories.template.health.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.ui.main.stories.template.BaseComponent;
import com.huawei.ui.main.stories.template.ComponentParam;
import com.huawei.ui.main.stories.template.Constants;
import com.huawei.ui.main.stories.template.health.contract.DataDetailFragmentContract;
import java.util.List;

/* loaded from: classes7.dex */
public class BaseNoDataView extends LinearLayout implements BaseComponent {
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

    public void refreshView(boolean z) {
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void setDateStamp(long j) {
    }

    public void setPageType(Constants.PageType pageType) {
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void setPresenter(DataDetailFragmentContract.DetailFragmentPresenter detailFragmentPresenter) {
    }

    public BaseNoDataView(Context context) {
        super(context);
    }

    public BaseNoDataView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BaseNoDataView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
