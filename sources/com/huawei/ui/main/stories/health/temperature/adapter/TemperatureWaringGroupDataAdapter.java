package com.huawei.ui.main.stories.health.temperature.adapter;

import android.content.Context;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.adapter.BaseGroupDataAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.main.R$string;
import defpackage.qoi;
import defpackage.qon;
import defpackage.qpr;
import health.compact.a.UnitUtil;

/* loaded from: classes6.dex */
public class TemperatureWaringGroupDataAdapter extends BaseGroupDataAdapter<qon, qoi> {
    private boolean b = UnitUtil.d();

    @Override // com.huawei.ui.commonui.adapter.BaseGroupDataAdapter
    public void setChildViewData(RecyclerHolder recyclerHolder, int i, int i2) {
        super.setChildViewData(recyclerHolder, i, i2);
        qoi child = getChild(i2, i);
        if (child != null) {
            b(child, recyclerHolder, this.b);
            recyclerHolder.b(R.id.item_right_data, child.c());
        }
    }

    private static void b(qoi qoiVar, RecyclerHolder recyclerHolder, boolean z) {
        String string;
        if (recyclerHolder == null || qoiVar == null) {
            LogUtil.h("TemperatureWaringGroupDataAdapter", "setTextProperties holder or itemData is null");
            return;
        }
        Context context = BaseApplication.getContext();
        if (z) {
            string = context.getResources().getString(R$string.IDS_settings_health_temperature_unit, qoiVar.i());
        } else {
            string = context.getResources().getString(R$string.IDS_temp_unit_fahrenheit, qoiVar.i());
        }
        recyclerHolder.b(R.id.item_title, string);
        int d = qoiVar.d();
        if (d == qpr.b) {
            recyclerHolder.c(R.id.item_subtitle, ContextCompat.getColor(context, R.color._2131299221_res_0x7f090b95));
            recyclerHolder.b(R.id.item_subtitle, context.getString(R$string.IDS_temperature_warning_high));
        } else if (d == qpr.d) {
            recyclerHolder.c(R.id.item_subtitle, ContextCompat.getColor(context, R.color._2131299223_res_0x7f090b97));
            recyclerHolder.b(R.id.item_subtitle, context.getString(R$string.IDS_temperature_warning_low));
        } else {
            recyclerHolder.c(R.id.item_subtitle, ContextCompat.getColor(context, R.color._2131299222_res_0x7f090b96));
            recyclerHolder.b(R.id.item_subtitle, context.getString(R$string.IDS_temperature_warning_high));
        }
    }
}
