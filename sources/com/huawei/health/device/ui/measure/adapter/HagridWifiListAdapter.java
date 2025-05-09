package com.huawei.health.device.ui.measure.adapter;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.cub;
import defpackage.koq;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class HagridWifiListAdapter extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    private LayoutInflater f2247a;
    private String b;
    private Context e;
    private List<ScanResult> h;
    private boolean d = false;
    private boolean c = true;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public HagridWifiListAdapter(Context context, List<ScanResult> list) {
        this.e = context;
        this.h = list;
        this.f2247a = LayoutInflater.from(context);
    }

    public void c(boolean z) {
        this.d = z;
    }

    public void c(String str) {
        this.b = str;
    }

    public void b(boolean z) {
        this.c = z;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (koq.c(this.h)) {
            return this.h.size();
        }
        LogUtil.h("HagridWifiListAdapter", "getCount wifi list is null");
        return 0;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (koq.d(this.h, i)) {
            return this.h.get(i);
        }
        LogUtil.h("HagridWifiListAdapter", "getItem wifi list is null or position is out of bounds.");
        return null;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        d dVar = null;
        if (view == null) {
            d dVar2 = new d();
            View inflate = this.f2247a.inflate(R.layout.hygride_wifi_info_list_item_layout, (ViewGroup) null);
            dVar2.f2248a = (HealthTextView) inflate.findViewById(R.id.wifi_item_name_text);
            dVar2.e = (HealthTextView) inflate.findViewById(R.id.wifi_item_tip);
            dVar2.c = (ImageView) inflate.findViewById(R.id.wifi_signal_strength);
            inflate.setTag(dVar2);
            dVar = dVar2;
            view = inflate;
        } else {
            Object tag = view.getTag();
            if (tag instanceof d) {
                dVar = (d) tag;
            }
        }
        if (koq.d(this.h, i) && this.h.get(i) != null) {
            dVar.f2248a.setText(this.h.get(i).SSID);
            dVar.f2248a.setTextColor(this.e.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
            if (this.h.get(i).SSID.equals(cub.e(cub.c(this.e))) && cub.f(this.e)) {
                if (this.h.get(i).SSID.equals(this.b)) {
                    dVar.e.setText(R.string.IDS_device_rope_device_connected);
                } else {
                    dVar.e.setText(R.string.IDS_device_wifi_current);
                }
                if (this.c) {
                    dVar.f2248a.setTextColor(this.e.getResources().getColor(R.color._2131296651_res_0x7f09018b));
                }
            } else if (cub.MB_(this.h.get(i))) {
                if (!cub.MC_(this.h.get(i))) {
                    dVar.e.setText(R.string.IDS_device_wifi_encrypt);
                } else {
                    dVar.e.setText(R.string.IDS_device_wifi_open);
                }
                if (this.d) {
                    dVar.e.setText(R.string.IDS_device_wifi_save);
                }
                dVar.e.setTextColor(this.e.getResources().getColor(R.color._2131299241_res_0x7f090ba9));
            } else {
                dVar.e.setText(R.string.IDS_device_wifi_5g);
                dVar.e.setTextColor(this.e.getResources().getColor(R.color._2131297322_res_0x7f09042a));
                dVar.f2248a.setTextColor(this.e.getResources().getColor(R.color._2131297322_res_0x7f09042a));
            }
            if (LanguageUtil.bc(BaseApplication.getContext())) {
                dVar.f2248a.setTextDirection(4);
            }
        }
        return Ir_(dVar, view);
    }

    private View Ir_(d dVar, View view) {
        if (dVar == null) {
            LogUtil.h("HagridWifiListAdapter", "viewHolder is null");
        } else {
            dVar.c.setBackgroundResource(R.drawable._2131430584_res_0x7f0b0cb8);
        }
        return view;
    }

    static class d {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f2248a;
        private ImageView c;
        private HealthTextView e;

        d() {
        }
    }
}
