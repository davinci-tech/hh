package com.huawei.health.knit.section.adapter;

import android.content.Context;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import defpackage.eek;
import java.util.List;

/* loaded from: classes3.dex */
public class SectionRemindViewAdapter extends BaseRecyclerAdapter<eek> {

    /* renamed from: a, reason: collision with root package name */
    private String f2585a;
    private String b;
    private static final int e = DicDataTypeUtil.DataType.LOW_BODY_TEMPERATURE_ALARM.value();
    private static final int c = DicDataTypeUtil.DataType.HIGH_BODY_TEMPERATURE_ALARM.value();

    public SectionRemindViewAdapter(List<eek> list, int i, String str, String str2) {
        super(list, i);
        this.f2585a = str;
        this.b = str2;
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void convert(RecyclerHolder recyclerHolder, int i, eek eekVar) {
        d(eekVar, recyclerHolder);
        recyclerHolder.b(R.id.temperature_list_time, eekVar.e());
        if (eekVar.a()) {
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

    private void d(eek eekVar, RecyclerHolder recyclerHolder) {
        if (recyclerHolder == null || eekVar == null) {
            LogUtil.h("SectionRemindViewAdapter", "setTextProperties holder or itemData is null");
            return;
        }
        Context context = BaseApplication.getContext();
        recyclerHolder.b(R.id.temperature_list_data, eekVar.d());
        if (eekVar.c() == e) {
            recyclerHolder.c(R.id.temperature_list_status, ContextCompat.getColor(context, R.color._2131299223_res_0x7f090b97));
            recyclerHolder.b(R.id.temperature_list_status, this.f2585a);
        } else if (eekVar.c() == c) {
            recyclerHolder.c(R.id.temperature_list_status, ContextCompat.getColor(context, R.color._2131299222_res_0x7f090b96));
            recyclerHolder.b(R.id.temperature_list_status, this.b);
        } else {
            recyclerHolder.c(R.id.temperature_list_status, ContextCompat.getColor(context, R.color._2131299226_res_0x7f090b9a));
            recyclerHolder.b(R.id.temperature_list_status, this.b);
        }
    }
}
