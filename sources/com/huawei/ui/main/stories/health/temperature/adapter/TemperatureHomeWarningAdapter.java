package com.huawei.ui.main.stories.health.temperature.adapter;

import com.huawei.health.R;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import defpackage.qoi;
import defpackage.qpr;
import java.util.List;

/* loaded from: classes6.dex */
public class TemperatureHomeWarningAdapter extends BaseRecyclerAdapter<qoi> {
    public TemperatureHomeWarningAdapter(List<qoi> list, int i) {
        super(list, i);
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void convert(RecyclerHolder recyclerHolder, int i, qoi qoiVar) {
        qpr.e(qoiVar, recyclerHolder);
        recyclerHolder.b(R.id.temperature_list_time, qoiVar.a());
        if (qoiVar.h()) {
            recyclerHolder.a(R.id.temperature_red_dot, 0);
        } else {
            recyclerHolder.a(R.id.temperature_red_dot, 8);
        }
        if (i == getItemCount() - 1) {
            recyclerHolder.a(R.id.temperature_divider, 8);
        } else {
            recyclerHolder.a(R.id.temperature_divider, 0);
        }
    }
}
