package com.huawei.healthcloud.plugintrack.ui.adapter;

import android.view.View;
import android.widget.RelativeLayout;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.motiontrack.model.runningroute.HotPathCityInfo;
import com.huawei.healthcloud.plugintrack.ui.adapter.RouteCityExpandableListAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.adapter.BaseGroupDataAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import defpackage.hje;
import defpackage.nsn;

/* loaded from: classes4.dex */
public class RouteCityExpandableListAdapter extends BaseGroupDataAdapter<hje, HotPathCityInfo> {
    private BaseGroupDataAdapter.OnItemClickListener e;

    @Override // com.huawei.ui.commonui.adapter.BaseGroupDataAdapter, android.widget.ExpandableListAdapter
    public boolean hasStableIds() {
        return true;
    }

    @Override // com.huawei.ui.commonui.adapter.BaseGroupDataAdapter
    public void setChildViewData(RecyclerHolder recyclerHolder, final int i, final int i2) {
        HotPathCityInfo child = getChild(i2, i);
        if (child == null) {
            LogUtil.h("Track_RouteCityExpandableListAdapter", "child is null'");
            return;
        }
        ((RelativeLayout) recyclerHolder.cwK_(R.id.item_layout)).setMinimumHeight(nsn.c(BaseApplication.e(), 48.0f));
        recyclerHolder.cwK_(R.id.item_out_layout).setLayoutParams(new RelativeLayout.LayoutParams(-1, nsn.c(BaseApplication.e(), 48.0f)));
        recyclerHolder.cwK_(R.id.item_subtitle).setVisibility(8);
        recyclerHolder.cwK_(R.id.item_right_data).setVisibility(8);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(3, R.id.item_out_layout);
        recyclerHolder.cwK_(R.id.divider).setLayoutParams(layoutParams);
        recyclerHolder.b(R.id.item_title, child.getCityName());
        recyclerHolder.cwK_(R.id.item_layout).setOnClickListener(new View.OnClickListener() { // from class: hgh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RouteCityExpandableListAdapter.this.bdt_(i2, i, view);
            }
        });
    }

    public /* synthetic */ void bdt_(int i, int i2, View view) {
        BaseGroupDataAdapter.OnItemClickListener onItemClickListener = this.e;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClickListener(i, i2);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.adapter.BaseGroupDataAdapter
    public void setOnItemClickListener(BaseGroupDataAdapter.OnItemClickListener onItemClickListener) {
        this.e = onItemClickListener;
    }
}
