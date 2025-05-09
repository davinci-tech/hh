package com.huawei.ui.main.stories.template.health.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;

/* loaded from: classes7.dex */
public class RecommendActivityView extends BaseOperationView {
    @Override // com.huawei.ui.main.stories.template.health.view.BaseOperationView
    public int getLayoutId() {
        return R.layout.layout_recommend_activity;
    }

    public RecommendActivityView(Context context) {
        this(context, null);
    }

    public RecommendActivityView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RecommendActivityView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.ui.main.stories.template.health.view.BaseOperationView
    public void findViews(View view) {
        this.mConfiguredItemRootLayout = (LinearLayout) view.findViewById(R.id.layout_configured_page);
        this.mRecyclerView = (HealthRecycleView) view.findViewById(R.id.recommend_activity_recycler_view);
        this.mHealthSubHeader = (HealthSubHeader) view.findViewById(R.id.recommend_activity_page_sub_header);
    }
}
