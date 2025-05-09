package com.huawei.health.device.ui.measure.adapter;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class WifiListAdapter extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f2258a;
    private List<ScanResult> c;
    private int d;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public WifiListAdapter(Context context, List<ScanResult> list) {
        new ArrayList(16);
        this.d = -1;
        this.f2258a = context;
        this.c = list;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.c.size() > 0) {
            return this.c.size();
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (this.c.size() <= 0 || i >= this.c.size()) {
            return null;
        }
        return this.c.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        b bVar = null;
        if (view == null) {
            b bVar2 = new b();
            View inflate = LayoutInflater.from(this.f2258a).inflate(R.layout.wifi_list_item_layout, (ViewGroup) null);
            bVar2.b = (HealthTextView) inflate.findViewById(R.id.wifi_name);
            bVar2.e = (HealthRadioButton) inflate.findViewById(R.id.wifi_check_img);
            bVar2.f2259a = (HealthDivider) inflate.findViewById(R.id.wifi_name_divider_line);
            bVar2.e.setClickable(false);
            inflate.setTag(bVar2);
            bVar = bVar2;
            view = inflate;
        } else {
            Object tag = view.getTag();
            if (tag instanceof b) {
                bVar = (b) tag;
            }
        }
        if (bVar == null) {
            LogUtil.h("WifiListAdapter", "viewHolder is null");
        } else {
            bVar.b.setText(this.c.get(i).SSID);
            if (this.d == i) {
                bVar.e.setChecked(true);
            } else {
                bVar.e.setChecked(false);
            }
            if (i == this.c.size() - 1) {
                bVar.f2259a.setVisibility(8);
            } else {
                bVar.f2259a.setVisibility(0);
            }
        }
        return view;
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        private HealthDivider f2259a;
        private HealthTextView b;
        private HealthRadioButton e;

        b() {
        }
    }

    public void b(int i) {
        this.d = i;
    }
}
