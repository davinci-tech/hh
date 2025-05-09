package com.huawei.ui.main.stories.template.health.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.main.stories.template.ComponentParam;
import defpackage.koq;
import java.util.List;

/* loaded from: classes7.dex */
public class NoDataNewsView extends BaseOperationView {
    @Override // com.huawei.ui.main.stories.template.health.view.BaseOperationView
    public int getLayoutId() {
        return R.layout.layout_no_data_news;
    }

    public NoDataNewsView(Context context) {
        this(context, null);
    }

    public NoDataNewsView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NoDataNewsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.ui.main.stories.template.health.view.BaseOperationView
    public void findViews(View view) {
        this.mConfiguredItemRootLayout = (LinearLayout) view.findViewById(R.id.layout_configured_page);
        this.mRecyclerView = (HealthRecycleView) view.findViewById(R.id.no_data_news_recycler_view);
        this.mHealthSubHeader = (HealthSubHeader) view.findViewById(R.id.no_data_news_page_sub_header);
    }

    @Override // com.huawei.ui.main.stories.template.health.view.BaseNoDataView, com.huawei.ui.main.stories.template.BaseComponent
    public void initComponent(List<ComponentParam> list) {
        if (koq.c(list) && this.mConfiguredItemRootLayout != null) {
            this.mHealthSubHeader.setSubHeaderBackgroundColor(0);
        }
        super.initComponent(list);
    }
}
