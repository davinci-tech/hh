package com.huawei.health.device.ui.measure.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.cnu;
import defpackage.koq;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class HagridDeviceManagerAdapter extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    private LayoutInflater f2245a;
    private Context c;
    private String d;
    private List<cnu> e = new ArrayList(10);

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public HagridDeviceManagerAdapter(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.h("HonourDeviceSettingListAdapter", "HonourDeviceSettingListAdapter context or productId is null");
            return;
        }
        this.c = context;
        this.f2245a = LayoutInflater.from(context);
        this.d = str;
    }

    public void b(List<cnu> list) {
        List<cnu> list2 = this.e;
        if (list2 != null) {
            list2.clear();
            this.e.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void a(cnu cnuVar) {
        if (cnuVar == null) {
            LogUtil.h("HonourDeviceSettingListAdapter", "refreshItem() item is null.");
            return;
        }
        for (cnu cnuVar2 : this.e) {
            if (cnuVar2.e() == cnuVar.e()) {
                LogUtil.a("HonourDeviceSettingListAdapter", "itemId:", Integer.valueOf(cnuVar.e()));
                cnuVar2.d(cnuVar.c());
                cnuVar2.b(cnuVar.f());
                cnuVar2.d(cnuVar.a());
                cnuVar2.a(cnuVar.d());
                cnuVar2.a(cnuVar.b());
                cnuVar2.c(cnuVar.i());
                cnuVar2.e(cnuVar.g());
            }
        }
        notifyDataSetChanged();
    }

    public boolean c(int i, cnu cnuVar) {
        boolean z = false;
        if (cnuVar == null) {
            LogUtil.h("HonourDeviceSettingListAdapter", "replaceItem() item is null.");
            return false;
        }
        Iterator<cnu> it = this.e.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            cnu next = it.next();
            if (i == next.e()) {
                LogUtil.a("HonourDeviceSettingListAdapter", "itemId:", Integer.valueOf(next.e()));
                next.b(cnuVar.e());
                next.d(cnuVar.c());
                next.b(cnuVar.f());
                next.d(cnuVar.a());
                next.a(cnuVar.d());
                next.a(cnuVar.b());
                next.c(cnuVar.i());
                next.e(cnuVar.g());
                z = true;
                break;
            }
        }
        notifyDataSetChanged();
        return z;
    }

    public void b(int i) {
        Iterator<cnu> it = this.e.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            cnu next = it.next();
            if (i == next.e()) {
                next.e(false);
                break;
            }
        }
        notifyDataSetChanged();
    }

    public void d(int i, cnu cnuVar) {
        Iterator<cnu> it = this.e.iterator();
        int i2 = 0;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            if (i == it.next().e()) {
                this.e.set(i2, cnuVar);
                break;
            }
            i2++;
        }
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<cnu> list = this.e;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public cnu getItem(int i) {
        if (koq.b(this.e) || i >= this.e.size()) {
            return null;
        }
        return this.e.get(i);
    }

    public cnu a(int i) {
        for (cnu cnuVar : this.e) {
            if (i == cnuVar.e()) {
                return cnuVar;
            }
        }
        return null;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        d dVar = null;
        if (view == null) {
            view = this.f2245a.inflate(R.layout.item_hagrid_device_manager, (ViewGroup) null);
            dVar = new d();
            dVar.b = (HealthTextView) view.findViewById(R.id.weight_clock_text);
            dVar.c = (HealthTextView) view.findViewById(R.id.text_sub_content);
            dVar.g = (HealthTextView) nsy.cMd_(view, R.id.right_text);
            dVar.e = (ImageView) nsy.cMd_(view, R.id.settings_switch);
            dVar.d = nsy.cMd_(view, R.id.item_layout);
            dVar.f2246a = (ImageView) nsy.cMd_(view, R.id.red_point_img_tips);
            view.setTag(dVar);
        } else {
            Object tag = view.getTag();
            if (tag instanceof d) {
                dVar = (d) tag;
            }
        }
        cnu item = getItem(i);
        if (item != null && dVar != null) {
            if (!item.g()) {
                dVar.d.setVisibility(8);
                return view;
            }
            if (item.e() == 0 || item.e() == 17) {
                dVar.d.setVisibility(8);
            } else if (item.e() == 16) {
                dVar.d.setVisibility(8);
            } else {
                d(i, dVar, item);
            }
        } else {
            LogUtil.h("HonourDeviceSettingListAdapter", "item = null or holder = null");
        }
        return view;
    }

    private void d(int i, d dVar, cnu cnuVar) {
        if (dVar == null || cnuVar == null) {
            LogUtil.h("HonourDeviceSettingListAdapter", "getViewDefault holder or item is null");
            return;
        }
        dVar.d.setVisibility(0);
        if (TextUtils.isEmpty(cnuVar.b())) {
            dVar.g.setVisibility(8);
        } else {
            dVar.g.setVisibility(0);
            dVar.g.setText(cnuVar.b());
        }
        dVar.b.setText(cnuVar.c());
        if (!TextUtils.isEmpty(cnuVar.i()) && !"6d5416d9-2167-41df-ab10-c492e152b44f".equals(this.d)) {
            dVar.c.setVisibility(0);
            dVar.c.setText(cnuVar.i());
        } else {
            dVar.c.setVisibility(8);
        }
        if (getItem(i) != null && !getItem(i).f()) {
            dVar.b.setTextColor(this.c.getResources().getColor(R.color._2131297783_res_0x7f0905f7));
        } else {
            dVar.b.setTextColor(this.c.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        }
        if (LanguageUtil.bc(this.c)) {
            dVar.e.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            dVar.e.setBackgroundResource(R.drawable._2131427842_res_0x7f0b0202);
        }
        dVar.f2246a.setVisibility(cnuVar.d() ? 0 : 8);
    }

    public void c(int i) {
        LogUtil.a("HonourDeviceSettingListAdapter", "removeTargetItem enter,id: ", Integer.valueOf(i));
        int i2 = 0;
        while (true) {
            if (i2 >= this.e.size()) {
                break;
            }
            if (i == this.e.get(i2).e()) {
                this.e.remove(i2);
                break;
            }
            i2++;
        }
        LogUtil.a("HonourDeviceSettingListAdapter", "remove targetItem success.");
        notifyDataSetChanged();
    }

    static class d {

        /* renamed from: a, reason: collision with root package name */
        ImageView f2246a;
        HealthTextView b;
        HealthTextView c;
        View d;
        ImageView e;
        HealthTextView g;

        d() {
        }
    }
}
