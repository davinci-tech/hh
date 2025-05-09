package com.huawei.sim.esim.view.adapter;

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
import defpackage.nbu;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class WirelessAdapter extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<nbu> f8702a;
    private Context c;
    private LayoutInflater d;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public WirelessAdapter(ArrayList<nbu> arrayList, Context context) {
        if (arrayList.clone() instanceof ArrayList) {
            this.f8702a = (ArrayList) arrayList.clone();
        }
        this.d = LayoutInflater.from(context);
        this.c = context;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f8702a.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (i < 0 || i >= this.f8702a.size()) {
            return null;
        }
        return this.f8702a.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (i < 0 || i >= this.f8702a.size()) {
            return null;
        }
        nbu nbuVar = this.f8702a.get(i);
        if (view == null) {
            a aVar2 = new a();
            View inflate = this.d.inflate(R.layout.wireless_item_activity, (ViewGroup) null);
            aVar2.f8703a = (HealthTextView) inflate.findViewById(R.id.open_esim);
            aVar2.b = (HealthTextView) inflate.findViewById(R.id.open_esim_tips);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.set_tips_image);
            if (LanguageUtil.bc(this.c)) {
                imageView.setImageResource(R.drawable._2131431482_res_0x7f0b103a);
            }
            inflate.setTag(aVar2);
            aVar = aVar2;
            view = inflate;
        } else if (view.getTag() instanceof a) {
            aVar = (a) view.getTag();
        } else {
            LogUtil.h("WirelessAdapter", "convertView.getTag() not instanceof ViewHolder");
            return view;
        }
        view.setOnClickListener(nbuVar.crI_());
        aVar.f8703a.setText(nbuVar.e());
        if (TextUtils.isEmpty(nbuVar.d())) {
            aVar.b.setVisibility(8);
        } else {
            aVar.b.setVisibility(0);
            aVar.b.setText(nbuVar.d());
        }
        return view;
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f8703a;
        HealthTextView b;

        a() {
        }
    }
}
