package com.huawei.healthcloud.plugintrack.ui.adapter;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.eet;
import defpackage.gxg;
import defpackage.nsj;
import defpackage.nsn;
import java.util.List;

/* loaded from: classes4.dex */
public class HistoricalRoutesGroupAdapter extends BaseRecyclerAdapter<gxg> {
    private HealthRecycleView b;
    private HistoricalRoutesGroupItemAdapter d;
    private Context e;

    public HistoricalRoutesGroupAdapter(Context context, List<gxg> list, int i) {
        super(list, i);
        this.e = context;
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void convert(RecyclerHolder recyclerHolder, int i, gxg gxgVar) {
        if (recyclerHolder == null || gxgVar == null) {
            LogUtil.h("HistoricalRoutesGroupAdapter", "convert holder or itemData is null");
            return;
        }
        String c = nsj.c(this.e, gxgVar.d());
        if (nsj.h(gxgVar.d())) {
            c = this.e.getResources().getString(R.string._2130840224_res_0x7f020aa0);
        }
        recyclerHolder.b(R.id.data_group_title, c);
        a(recyclerHolder, gxgVar);
    }

    private void a(RecyclerHolder recyclerHolder, gxg gxgVar) {
        LogUtil.a("HistoricalRoutesGroupAdapter", "setHoldView item=", Integer.valueOf(gxgVar.b().size()));
        LogUtil.a("HistoricalRoutesGroupAdapter", "setHoldView itemData.getList()=", gxgVar.b());
        HealthRecycleView healthRecycleView = (HealthRecycleView) recyclerHolder.cwK_(R.id.historical_routes_child_recycler);
        this.b = healthRecycleView;
        healthRecycleView.setAdapter(null);
        this.b.setLayoutManager(null);
        HistoricalRoutesGroupItemAdapter historicalRoutesGroupItemAdapter = new HistoricalRoutesGroupItemAdapter(this.e, gxgVar.b(), R.layout.section_popular_routes_item);
        this.d = historicalRoutesGroupItemAdapter;
        this.b.setAdapter(historicalRoutesGroupItemAdapter);
        if (nsn.ag(this.e)) {
            eet.c(this.e, this.b, new HealthLinearLayoutManager(this.e), false, 0);
            this.d.notifyDataSetChanged();
        } else {
            this.b.setLayoutManager(new LinearLayoutManager(this.e, 1, false));
        }
    }

    public void a() {
        HealthRecycleView healthRecycleView;
        if (this.e == null || (healthRecycleView = this.b) == null || this.d == null) {
            return;
        }
        healthRecycleView.setAdapter(null);
        this.b.setLayoutManager(null);
        this.b.setAdapter(this.d);
        eet.c(this.e, this.b, new HealthLinearLayoutManager(this.e), false, 0);
        this.d.notifyDataSetChanged();
    }
}
