package com.huawei.health.ecologydevice.ui.measure.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.cjv;
import defpackage.cpp;
import defpackage.dbp;
import defpackage.dcq;
import defpackage.dcx;
import defpackage.dcz;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes3.dex */
public class ProductListAdapter extends ListAdapter {
    ArrayList<cjv> d;
    private Map<String, String> e = new HashMap();
    private Map<String, Bitmap> b = new HashMap();

    /* renamed from: a, reason: collision with root package name */
    private Map<String, String> f2329a = new HashMap();
    private Map<String, String> c = new HashMap();

    public ProductListAdapter(ArrayList<dcz> arrayList) {
        this.d = null;
        ArrayList<cjv> c = dbp.c(arrayList);
        this.d = c;
        super.setProductList(c);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.adapter.ListAdapter, android.widget.Adapter
    public int getCount() {
        ArrayList<cjv> arrayList = this.d;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    public void c(dcz dczVar) {
        if (!d(dczVar)) {
            this.d.add(dbp.b(dczVar));
            setProductList(this.d);
        } else {
            a(dczVar);
        }
    }

    private void a(dcz dczVar) {
        int e2 = e(dczVar);
        if (e2 != -1) {
            this.d.set(e2, dbp.b(dczVar));
            setProductList(this.d);
        }
    }

    private int e(dcz dczVar) {
        for (int i = 0; i < this.d.size(); i++) {
            cjv cjvVar = this.d.get(i);
            if (cjvVar.a() == 0) {
                dcz dczVar2 = cjvVar.i() instanceof dcz ? (dcz) cjvVar.i() : null;
                if (dczVar2 != null && dczVar2.t().equals(dczVar.t())) {
                    return i;
                }
            }
        }
        return -1;
    }

    private boolean d(dcz dczVar) {
        Iterator<cjv> it = this.d.iterator();
        while (it.hasNext()) {
            cjv next = it.next();
            if (next.a() == 0 && ((dcz) next.i()).t().equals(dczVar.t())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.adapter.ListAdapter
    public void setProductList(ArrayList<cjv> arrayList) {
        super.setProductList(arrayList);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        e eVar;
        if (view == null) {
            e eVar2 = new e();
            View inflate = LayoutInflater.from(cpp.a()).inflate(R.layout.my_device_bind_list_item, viewGroup, false);
            eVar2.f2330a = (HealthTextView) inflate.findViewById(R.id.tv_device_content);
            eVar2.e = (ImageView) inflate.findViewById(R.id.arrow_right_icon);
            eVar2.d = (ImageView) inflate.findViewById(R.id.iv_device_icon);
            eVar2.c = (HealthTextView) inflate.findViewById(R.id.tv_device_summary);
            eVar2.b = (HealthDivider) inflate.findViewById(R.id.hw_show_main_layout_sport_bottom_image_interval);
            inflate.setTag(eVar2);
            eVar = eVar2;
            view = inflate;
        } else {
            eVar = view.getTag() instanceof e ? (e) view.getTag() : null;
        }
        b(view.getContext(), i, eVar);
        return view;
    }

    protected void b(Context context, int i, e eVar) {
        String str;
        String d;
        if (eVar == null) {
            LogUtil.h("DeviceCoursesHelper", "setItemContent viewHolder is null");
            return;
        }
        if (LanguageUtil.bc(cpp.a())) {
            eVar.e.setImageDrawable(context.getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
        } else {
            eVar.e.setImageDrawable(context.getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
        }
        cjv cjvVar = this.d.get(i);
        if (cjvVar.i() instanceof dcz) {
            dcz dczVar = (dcz) cjvVar.i();
            ContentValues FT_ = cjvVar.FT_();
            if (FT_ == null) {
                str = "";
            } else if (TextUtils.isEmpty(FT_.getAsString("sn"))) {
                str = FT_.getAsString("sn");
            } else {
                str = FT_.getAsString("uniqueId");
            }
            e(eVar.f2330a, dczVar, str);
            Uv_(eVar.d, dczVar);
            eVar.c.setVisibility(0);
            if (this.c.containsKey(dczVar.t() + dczVar.n().c())) {
                d = this.c.get(dczVar.t() + dczVar.n().c());
            } else {
                d = dcx.d(dczVar.t(), dczVar.n().c());
                this.c.put(dczVar.t() + dczVar.n().c(), d);
            }
            eVar.c.setText(d);
        }
        if (i == this.d.size() - 1) {
            eVar.b.setVisibility(8);
        } else {
            eVar.b.setVisibility(0);
        }
    }

    private void Uv_(ImageView imageView, dcz dczVar) {
        String str;
        Bitmap bitmap;
        if (this.e.containsKey(dczVar.t() + dczVar.n().d())) {
            str = this.e.get(dczVar.t() + dczVar.n().d());
        } else {
            String a2 = dcq.b().a(dczVar.t(), dczVar.n().d());
            this.e.put(dczVar.t() + dczVar.n().d(), a2);
            str = a2;
        }
        if (this.b.containsKey(str)) {
            bitmap = this.b.get(str);
        } else {
            Bitmap TK_ = dcx.TK_(str);
            this.b.put(str, TK_);
            bitmap = TK_;
        }
        imageView.setImageBitmap(bitmap);
    }

    protected void e(HealthTextView healthTextView, dcz dczVar, String str) {
        String str2;
        if (this.f2329a.containsKey(dczVar.t() + dczVar.n().b())) {
            str2 = this.f2329a.get(dczVar.t() + dczVar.n().b());
        } else {
            String d = dcx.d(dczVar.t(), dczVar.n().b());
            this.f2329a.put(dczVar.t() + dczVar.n().b(), d);
            str2 = d;
        }
        if (!TextUtils.isEmpty(str) && str.length() >= 3) {
            healthTextView.setText(str2 + Constants.LINK + str.replace(":", "").substring(str.replace(":", "").length() - 3));
            return;
        }
        healthTextView.setText(str2);
    }

    protected static class e {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f2330a;
        HealthDivider b;
        HealthTextView c;
        ImageView d;
        ImageView e;

        protected e() {
        }
    }
}
