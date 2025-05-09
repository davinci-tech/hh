package com.huawei.health.ecologydevice.ui.measure.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.ui.measure.adapter.ProductListAdapter;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.cpp;
import defpackage.dbp;
import defpackage.dcx;
import defpackage.dcz;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class WeightListAdapter extends ProductListAdapter {
    private LayoutInflater c;
    private ArrayList<dcz> e;

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return 2;
    }

    public WeightListAdapter(Context context, ArrayList<dcz> arrayList) {
        super(arrayList);
        this.e = arrayList;
        this.c = LayoutInflater.from(context);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.adapter.ListAdapter, android.widget.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public dcz getItem(int i) {
        ArrayList<dcz> arrayList = this.e;
        if (arrayList == null || i >= arrayList.size()) {
            return null;
        }
        return this.e.get(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.huawei.health.ecologydevice.ui.measure.adapter.ProductListAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar = null;
        Object[] objArr = 0;
        if (i >= this.e.size()) {
            return null;
        }
        dcz dczVar = this.e.get(i);
        if (getItemViewType(i) != 1) {
            return super.getView(i, view, viewGroup);
        }
        if (view == null) {
            a aVar2 = new a();
            view = this.c.inflate(R.layout.my_device_bind_list_header, viewGroup, false);
            aVar2.e = (HealthTextView) view.findViewById(R.id.tv_header_name);
            aVar2.b = (HealthDivider) view.findViewById(R.id.view_header_line);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            Object tag = view.getTag();
            if (tag instanceof a) {
                aVar = (a) tag;
            }
        }
        if (aVar != null) {
            d(aVar, dczVar);
        } else {
            LogUtil.h("WeightListAdapter", "holder is null");
        }
        return view;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i) {
        ArrayList<dcz> arrayList = this.e;
        if (arrayList == null || i >= arrayList.size()) {
            return -1;
        }
        return this.e.get(i).o();
    }

    private void d(a aVar, dcz dczVar) {
        aVar.e.setText(dczVar.f());
        if (dczVar.i()) {
            aVar.b.setVisibility(4);
        } else {
            aVar.b.setVisibility(0);
        }
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            aVar.e.setTextDirection(4);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.adapter.ProductListAdapter
    protected void b(Context context, int i, ProductListAdapter.e eVar) {
        super.b(context, i, eVar);
        if (this.e.get(i).u() || eVar == null) {
            return;
        }
        eVar.b.setVisibility(8);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.adapter.ProductListAdapter
    protected void e(HealthTextView healthTextView, dcz dczVar, String str) {
        if ("9323f6b7-b459-44f4-a698-988d1769832a".equals(dczVar.t())) {
            if (LanguageUtil.ba(cpp.a()) || LanguageUtil.ab(cpp.a()) || LanguageUtil.m(cpp.a())) {
                healthTextView.setText(dcx.d(dczVar.t(), dczVar.n().b()));
                return;
            } else {
                healthTextView.setText("HUAWEI FIT");
                return;
            }
        }
        super.e(healthTextView, dczVar, "");
    }

    public void d(ArrayList<dcz> arrayList) {
        this.d = dbp.c(arrayList);
        super.setProductList(this.d);
    }

    static class a {
        HealthDivider b;
        HealthTextView e;

        private a() {
        }
    }
}
