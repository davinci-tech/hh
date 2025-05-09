package com.huawei.sim.multisim.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ncf;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class EsimFaqAdapter extends BaseAdapter {
    private String b;
    private Context d;
    private OnItemClickListener g;
    private List<String> e = h();
    private List<String> c = g();

    /* renamed from: a, reason: collision with root package name */
    private List<String> f8717a = i();

    public interface OnItemClickListener {
        void onClick(String str, String str2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    public EsimFaqAdapter(Context context, String str) {
        this.d = context;
        this.b = str;
        LogUtil.a("EsimFaqAdapter", "mFaqTextList.length = ", Integer.valueOf(this.e.size()), ",mFaqUrlList.length = ", Integer.valueOf(this.c.size()));
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<String> list = this.e;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        List<String> list = this.e;
        if (list == null) {
            LogUtil.h("EsimFaqAdapter", "getItem mFaqTextList is null");
            return null;
        }
        if (i < 0 || i >= list.size()) {
            LogUtil.h("EsimFaqAdapter", "getItem mFaqTextList is out of range");
            return null;
        }
        return this.e.get(i);
    }

    public void a(OnItemClickListener onItemClickListener) {
        this.g = onItemClickListener;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar = null;
        if (view == null) {
            a aVar2 = new a();
            View inflate = LayoutInflater.from(this.d).inflate(R.layout.esim_faq_item, (ViewGroup) null);
            aVar2.d = (HealthTextView) inflate.findViewById(R.id.tv_esim_faq);
            aVar2.b = (ImageView) inflate.findViewById(R.id.iv_esim_faq);
            aVar2.e = (LinearLayout) inflate.findViewById(R.id.esim_faq_layout);
            aVar2.f8718a = inflate.findViewById(R.id.esim_faq_item_line);
            inflate.setTag(aVar2);
            aVar = aVar2;
            view = inflate;
        } else {
            Object tag = view.getTag();
            if (tag instanceof a) {
                aVar = (a) tag;
            }
        }
        if (aVar != null) {
            d(i, aVar);
        } else {
            LogUtil.h("EsimFaqAdapter", "getView() holder is null");
        }
        return view;
    }

    private void d(final int i, a aVar) {
        List<String> list = this.e;
        if (list == null || this.f8717a == null) {
            LogUtil.h("EsimFaqAdapter", "setViewContent mFaqTextList or mFaqTitleList is null");
            return;
        }
        if (i < 0 || i >= list.size() || i >= this.f8717a.size()) {
            LogUtil.h("EsimFaqAdapter", "position is out of range");
        }
        aVar.b.setBackgroundResource(LanguageUtil.bc(this.d) ? R.drawable._2131427841_res_0x7f0b0201 : R.drawable._2131427842_res_0x7f0b0202);
        aVar.d.setText(this.e.get(i));
        aVar.e.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.sim.multisim.adapter.EsimFaqAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (EsimFaqAdapter.this.g != null) {
                    EsimFaqAdapter.this.g.onClick((String) EsimFaqAdapter.this.f8717a.get(i), (String) EsimFaqAdapter.this.c.get(i));
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        if (i == this.e.size() - 1) {
            aVar.f8718a.setVisibility(4);
        } else {
            aVar.f8718a.setVisibility(0);
        }
    }

    private List<String> i() {
        ArrayList arrayList = new ArrayList();
        if (LanguageUtil.m(this.d)) {
            arrayList.add(this.d.getString(R.string._2130848144_res_0x7f022990));
        }
        arrayList.add(this.d.getString(R.string._2130848096_res_0x7f022960));
        arrayList.add(this.d.getString(R.string._2130848042_res_0x7f02292a));
        if (!TextUtils.isEmpty(this.b)) {
            arrayList.add(this.d.getString(R.string._2130848043_res_0x7f02292b));
        }
        arrayList.add(this.d.getString(R.string._2130848023_res_0x7f022917));
        return arrayList;
    }

    private List<String> h() {
        ArrayList arrayList = new ArrayList();
        if (LanguageUtil.m(this.d)) {
            arrayList.add(this.d.getString(R.string._2130848144_res_0x7f022990));
        }
        arrayList.add(this.d.getString(R.string._2130848096_res_0x7f022960));
        arrayList.add(this.d.getString(R.string._2130848042_res_0x7f02292a));
        if (!TextUtils.isEmpty(this.b)) {
            arrayList.add(this.d.getString(R.string._2130848043_res_0x7f02292b));
        }
        arrayList.add(this.d.getString(R.string._2130848023_res_0x7f022917));
        return arrayList;
    }

    private List<String> g() {
        ArrayList arrayList = new ArrayList();
        if (LanguageUtil.m(this.d)) {
            arrayList.add(f());
        }
        arrayList.add(j());
        arrayList.add(b());
        if (!TextUtils.isEmpty(this.b)) {
            arrayList.add(c());
        }
        arrayList.add(l());
        return arrayList;
    }

    private String c() {
        if (ncf.b(this.b)) {
            LogUtil.a("EsimFaqAdapter", "getOperatorUrl operator is CMCC");
            return a();
        }
        if (ncf.d(this.b)) {
            return d();
        }
        if (ncf.a(this.b)) {
            return e();
        }
        LogUtil.h("EsimFaqAdapter", "getFaqLogoutUrl is not operator imsi");
        return "";
    }

    private String l() {
        return LanguageUtil.m(this.d) ? "/SmartWear/eSIM_GLL/EMUI8.0/C001B001/zh-CN/index.html" : "/SmartWear/eSIM_GLL/EMUI8.0/C001B001/en-US/index.html";
    }

    private String a() {
        return LanguageUtil.m(this.d) ? "/hwtips/topic/M005/zh-CN/SF-10190314_f2262.html?cid=11065" : "/hwtips/topic/M005/en-US/SF-10190314_f2262.html?cid=11065";
    }

    private String d() {
        return LanguageUtil.m(this.d) ? "/hwtips/topic/M005/zh-CN/SF-10190314_f2264.html?cid=11065" : "/hwtips/topic/M005/en-US/SF-10190314_f2264.html?cid=11065";
    }

    private String e() {
        return LanguageUtil.m(this.d) ? "/hwtips/topic/M005/zh-CN/SF-10190314_f2266.html?cid=11065" : "/hwtips/topic/M005/en-US/SF-10190314_f2266.html?cid=11065";
    }

    private String b() {
        return LanguageUtil.m(this.d) ? "/hwtips/topic/M005/zh-CN/SF-10190354_f2101.html?cid=11065" : "/hwtips/topic/M005/en-US/SF-10190354_f2150.html?cid=11065";
    }

    private String j() {
        return LanguageUtil.m(this.d) ? "/hwtips/topic/M005/zh-CN/SF-10190314_f2260.html" : "/hwtips/topic/M005/en-US/SF-10190314_f2260.html";
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        View f8718a;
        ImageView b;
        HealthTextView d;
        LinearLayout e;

        a() {
        }
    }

    private String f() {
        return "/eSIM_FAQ/EMUI8.0/C001B001/zh-CN/index.html";
    }
}
