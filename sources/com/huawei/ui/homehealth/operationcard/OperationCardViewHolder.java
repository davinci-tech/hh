package com.huawei.ui.homehealth.operationcard;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import com.huawei.health.R;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.recycleview.RecyclerItemDecoration;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.homehealth.refreshcard.CardViewHolder;
import defpackage.koq;
import defpackage.nrr;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class OperationCardViewHolder extends CardViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f9486a;
    private Context b;
    private GridLayoutManager c;
    private OperationRecycleViewAdapter d;
    private HealthColumnSystem e;
    private HealthRecycleView g;
    private LinearLayout i;

    OperationCardViewHolder(View view, Context context, boolean z) {
        super(view, context, z);
        this.b = context;
        HealthRecycleView healthRecycleView = (HealthRecycleView) view.findViewById(R.id.operation_rv);
        this.g = healthRecycleView;
        healthRecycleView.a(false);
        this.g.d(false);
        this.g.setFocusableInTouchMode(false);
        this.f9486a = (LinearLayout) view.findViewById(R.id.health_grid_operation_card);
        HealthColumnSystem healthColumnSystem = new HealthColumnSystem(this.b, 1);
        this.e = healthColumnSystem;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, Math.min(healthColumnSystem.f() / 4, 2), 1, false);
        this.c = gridLayoutManager;
        this.g.setLayoutManager(gridLayoutManager);
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(0);
        arrayList.add(0);
        arrayList.add(0);
        arrayList.add(0);
        this.g.addItemDecoration(new RecyclerItemDecoration(nrr.b(this.b), 0, arrayList));
        this.i = (LinearLayout) view.findViewById(R.id.operation_card_layout);
        BaseActivity.setViewSafeRegion(true, view.findViewById(R.id.operation_rv));
        HealthSubHeader healthSubHeader = (HealthSubHeader) view.findViewById(R.id.operation_card_sub_header);
        healthSubHeader.setSubHeaderBackgroundColor(0);
        healthSubHeader.setSubHeaderSafeRegion();
    }

    public void d(List<MessageObject> list, Map<Integer, ResourceResultInfo> map) {
        OperationRecycleViewAdapter operationRecycleViewAdapter = new OperationRecycleViewAdapter(this.b, list);
        this.d = operationRecycleViewAdapter;
        this.g.setAdapter(operationRecycleViewAdapter);
        List<View> marketingViewList = ((MarketingApi) Services.c("FeatureMarketing", MarketingApi.class)).getMarketingViewList(this.b, map);
        if (koq.b(marketingViewList)) {
            LogUtil.a("OperationCardViewHolder", "GRID INFO VIEW IS EMPTY.");
            return;
        }
        if (this.f9486a == null) {
            LogUtil.a("OperationCardViewHolder", "mGridLayout == null.");
            return;
        }
        Iterator<View> it = marketingViewList.iterator();
        while (it.hasNext()) {
            this.f9486a.addView(it.next());
        }
    }

    private void d() {
        HealthColumnSystem healthColumnSystem = this.e;
        if (healthColumnSystem != null) {
            healthColumnSystem.e(this.b);
            if (this.c != null) {
                int min = Math.min(this.e.f() / 4, 2);
                this.c.setSpanCount(min);
                this.d.e(min);
            }
        }
    }

    public boolean a() {
        LogUtil.a("OperationCardViewHolder", "notifyDataSetChanged start");
        d();
        if (this.d == null) {
            return false;
        }
        if (this.g.getScrollState() == 0 && !this.g.isComputingLayout()) {
            try {
                this.g.getRecycledViewPool().clear();
                this.d.notifyDataSetChanged();
                LogUtil.a("OperationCardViewHolder", "notifyDataSetChanged succ");
                return true;
            } catch (Exception unused) {
                LogUtil.b("OperationCardViewHolder", "notifyDataSetChanged occurred unknown exception");
                return false;
            }
        }
        LogUtil.b("OperationCardViewHolder", "notifyDataSetChanged ----------- fail");
        return false;
    }

    public void d(int i) {
        this.i.setVisibility(i);
    }
}
