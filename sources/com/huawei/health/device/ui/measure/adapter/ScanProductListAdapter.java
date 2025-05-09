package com.huawei.health.device.ui.measure.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.huawei.health.R;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.koq;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class ScanProductListAdapter extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    private String f2249a;
    private LayoutInflater d;
    private ArrayList<HealthDevice> e;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public ScanProductListAdapter(ArrayList<HealthDevice> arrayList, Context context, String str) {
        this.e = null;
        if (context != null) {
            this.d = LayoutInflater.from(context);
            this.e = arrayList;
            this.f2249a = str;
        }
    }

    public void a(ArrayList<HealthDevice> arrayList) {
        this.e = arrayList;
        LogUtil.a("PluginDevice_ScanProductListAdapter", "ScanProductListAdapter porduct size is ", Integer.valueOf(arrayList.size()));
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.e.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        ArrayList<HealthDevice> arrayList = this.e;
        if (arrayList == null || i >= arrayList.size()) {
            return null;
        }
        return this.e.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (viewGroup == null) {
            LogUtil.h("R_Weight_PluginDevice_ScanProductListAdapter", "ProductListAdapter getView parent viewGroup is null");
        }
        if (koq.b(this.e, i)) {
            return view;
        }
        HealthDevice healthDevice = this.e.get(i);
        d dVar = null;
        if (view == null) {
            view = this.d.inflate(R.layout.device_search_item_layout, (ViewGroup) null);
            dVar = new d();
            dVar.b = (HealthTextView) view.findViewById(R.id.item_device_name);
            dVar.d = view.findViewById(R.id.item_device_line);
            view.setTag(dVar);
        } else if (view.getTag() instanceof d) {
            dVar = (d) view.getTag();
        }
        if (dVar != null) {
            dVar.d.setVisibility(0);
            if (ResourceManager.e().d(this.f2249a) == null) {
                if (healthDevice.getDeviceName() == null || healthDevice.getDeviceName().length() <= 10) {
                    dVar.b.setText("unknown");
                } else {
                    dVar.b.setText(healthDevice.getDeviceName());
                }
            } else {
                dVar.b.setText(this.e.get(i).getDeviceName());
            }
        }
        return view;
    }

    public static class d {
        private HealthTextView b;
        private View d;
    }
}
