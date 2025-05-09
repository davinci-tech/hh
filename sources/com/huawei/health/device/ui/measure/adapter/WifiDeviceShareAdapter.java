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
import defpackage.crx;
import defpackage.ctv;
import defpackage.nmn;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class WifiDeviceShareAdapter extends BaseAdapter {
    private List<crx> c;
    private Context d;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public WifiDeviceShareAdapter(Context context, List<crx> list) {
        new ArrayList(16);
        this.d = context;
        this.c = list;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.c.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (i < 0 || i >= this.c.size()) {
            return null;
        }
        return this.c.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        d dVar;
        if (i < 0 || i > this.c.size()) {
            return view;
        }
        if (view == null) {
            dVar = new d();
            view2 = LayoutInflater.from(this.d).inflate(R.layout.item_wifi_device_share_layout, (ViewGroup) null);
            dVar.c = (ImageView) view2.findViewById(R.id.share_member_header_img);
            dVar.f2256a = (HealthTextView) view2.findViewById(R.id.share_member_header_title_tv);
            dVar.d = (HealthTextView) view2.findViewById(R.id.share_member_sub_title_tv);
            dVar.b = (ImageView) view2.findViewById(R.id.arrow_img);
            view2.setTag(dVar);
        } else if (view.getTag() instanceof d) {
            dVar = (d) view.getTag();
            view2 = view;
        } else {
            view2 = view;
            dVar = null;
        }
        if (dVar == null) {
            LogUtil.h("WifiDeviceShareAdapter", "viewHolder is null");
            return view;
        }
        crx crxVar = this.c.get(i);
        ctv.Mh_(crxVar.a(), dVar.c, nmn.cBh_(this.d.getResources(), new nmn.c(null, crxVar.e(), true)));
        if (!TextUtils.isEmpty(crxVar.d())) {
            dVar.f2256a.setText(crxVar.d());
        } else {
            dVar.f2256a.setText(crxVar.c());
        }
        if (LanguageUtil.bc(this.d)) {
            dVar.b.setBackground(this.d.getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
        } else {
            dVar.b.setBackground(this.d.getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
        }
        dVar.d.setText(crxVar.c());
        dVar.b.setVisibility(0);
        return view2;
    }

    static class d {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f2256a;
        private ImageView b;
        private ImageView c;
        private HealthTextView d;

        d() {
        }
    }
}
