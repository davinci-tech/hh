package com.huawei.health.knit.section.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.ebv;
import defpackage.eet;
import defpackage.eie;
import defpackage.jdw;
import defpackage.koq;
import defpackage.nrf;
import health.compact.a.Services;
import java.util.List;

/* loaded from: classes3.dex */
public class SectionSideSlipAdapter extends RecyclerView.Adapter<SectionSideSlipViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private Context f2589a;
    private List<String> b;
    private List<String> c;
    private List<Drawable> d;
    private List<OnClickSectionListener> e;
    private OnClickSectionListener f;
    private List<String> g;
    private List<String> h;
    private List<String> i;
    private List<String> j;
    private List<String> l;
    private List<Integer> o;

    public SectionSideSlipAdapter(Context context, ebv ebvVar) {
        this.f2589a = context;
        c(ebvVar);
    }

    public void b(ebv ebvVar) {
        c(ebvVar);
        notifyDataSetChanged();
    }

    private void c(ebv ebvVar) {
        this.j = ebvVar.b();
        this.g = ebvVar.g();
        this.c = ebvVar.e();
        this.f = ebvVar.f();
        this.o = ebvVar.m();
        this.l = ebvVar.i();
        this.i = ebvVar.j();
        this.d = ebvVar.a();
        this.h = ebvVar.h();
        this.b = ebvVar.c();
        this.e = ebvVar.d();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: aec_, reason: merged with bridge method [inline-methods] */
    public SectionSideSlipViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SectionSideSlipViewHolder(LayoutInflater.from(this.f2589a).inflate(i != 101 ? R.layout.section_side_slip_item : R.layout.section_member_coupon, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(SectionSideSlipViewHolder sectionSideSlipViewHolder, int i) {
        d(sectionSideSlipViewHolder, i);
    }

    private void d(SectionSideSlipViewHolder sectionSideSlipViewHolder, final int i) {
        RecyclerView.LayoutParams a2 = a(sectionSideSlipViewHolder, i);
        if (a2 == null) {
            LogUtil.b("params is invalid!", new Object[0]);
            return;
        }
        if (sectionSideSlipViewHolder.c != null) {
            sectionSideSlipViewHolder.c.setLayoutParams(a2);
            sectionSideSlipViewHolder.c.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.adapter.SectionSideSlipAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (SectionSideSlipAdapter.this.f != null) {
                        SectionSideSlipAdapter.this.f.onClick(i);
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        b(sectionSideSlipViewHolder, i);
        i(sectionSideSlipViewHolder, i);
        e(sectionSideSlipViewHolder, i);
        g(sectionSideSlipViewHolder, i);
    }

    private void g(SectionSideSlipViewHolder sectionSideSlipViewHolder, int i) {
        if (sectionSideSlipViewHolder.j != null && eet.b(this.l, i)) {
            sectionSideSlipViewHolder.j.setText(this.l.get(i));
        }
        if (sectionSideSlipViewHolder.b != null && eet.b(this.b, i)) {
            sectionSideSlipViewHolder.b.setText(this.b.get(i));
        }
        if (sectionSideSlipViewHolder.h != null && eet.b(this.i, i)) {
            sectionSideSlipViewHolder.h.setText(this.i.get(i));
            sectionSideSlipViewHolder.h.setTypeface(Typeface.createFromAsset(this.f2589a.getAssets(), "font/HarmonyOSCondensedClockProportional-Medium.ttf"));
        }
        if (sectionSideSlipViewHolder.l != null && eet.b(this.h, i)) {
            sectionSideSlipViewHolder.l.setText(this.h.get(i));
        }
        if (sectionSideSlipViewHolder.g != null && eet.b(this.d, i)) {
            sectionSideSlipViewHolder.g.setBackground(this.d.get(i));
        }
        if (sectionSideSlipViewHolder.f2591a == null || !eet.b(this.e, i)) {
            return;
        }
        sectionSideSlipViewHolder.f2591a.setClickable(true);
        sectionSideSlipViewHolder.f2591a.setOnClickListener(this.e.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return (!koq.d(this.o, i) || this.o.get(i) == null) ? i : this.o.get(i).intValue();
    }

    private void e(SectionSideSlipViewHolder sectionSideSlipViewHolder, final int i) {
        if (!eet.b(this.c, i) || sectionSideSlipViewHolder.c == null) {
            return;
        }
        sectionSideSlipViewHolder.c.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.adapter.SectionSideSlipAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SectionSideSlipAdapter.this.c.get(i) != null) {
                    String str = (String) SectionSideSlipAdapter.this.c.get(i);
                    if (str.startsWith("huaweischeme://")) {
                        Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(str));
                        intent.setPackage(SectionSideSlipAdapter.this.f2589a.getPackageName());
                        jdw.bGh_(intent, SectionSideSlipAdapter.this.f2589a);
                    } else {
                        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
                        if (marketRouterApi != null) {
                            marketRouterApi.router(str);
                        }
                    }
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void i(SectionSideSlipViewHolder sectionSideSlipViewHolder, int i) {
        if (!eet.b(this.g, i) || sectionSideSlipViewHolder.f == null) {
            return;
        }
        sectionSideSlipViewHolder.f.setText(this.g.get(i));
    }

    private void b(SectionSideSlipViewHolder sectionSideSlipViewHolder, int i) {
        if (!eet.b(this.j, i) || sectionSideSlipViewHolder.i == null) {
            return;
        }
        Object obj = this.j.get(i);
        Object b = sectionSideSlipViewHolder.b();
        if (obj instanceof String) {
            String str = (String) obj;
            String str2 = b instanceof String ? (String) b : "";
            if (str.length() <= 0 || str.equals(str2)) {
                return;
            }
            nrf.c(sectionSideSlipViewHolder.i, str, nrf.d, 0, R.drawable._2131431376_res_0x7f0b0fd0);
            sectionSideSlipViewHolder.a(str);
            return;
        }
        if (obj instanceof Integer) {
            int intValue = b instanceof Integer ? ((Integer) b).intValue() : 0;
            int intValue2 = ((Integer) obj).intValue();
            if (intValue != intValue2) {
                sectionSideSlipViewHolder.i.setImageResource(intValue2);
                sectionSideSlipViewHolder.a(Integer.valueOf(intValue2));
                return;
            }
            return;
        }
        nrf.a(R.drawable._2131431376_res_0x7f0b0fd0, sectionSideSlipViewHolder.i, nrf.d);
    }

    protected RecyclerView.LayoutParams a(SectionSideSlipViewHolder sectionSideSlipViewHolder, int i) {
        ViewGroup.LayoutParams layoutParams = sectionSideSlipViewHolder.c.getLayoutParams();
        if (!(layoutParams instanceof RecyclerView.LayoutParams)) {
            LogUtil.b("layoutParams error", new Object[0]);
            return null;
        }
        RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
        layoutParams2.width = eie.e(this.f2589a, 22, getItemCount());
        layoutParams2.height = -2;
        return layoutParams2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.o != null) {
            return Math.min(3, this.l.size());
        }
        return eet.d(this.j, this.g, this.c, this.l);
    }

    public class SectionSideSlipViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private RelativeLayout f2591a;
        private HealthTextView b;
        private RelativeLayout c;
        private Object d;
        private TextView f;
        private FrameLayout g;
        private HealthTextView h;
        private HealthImageView i;
        private HealthTextView j;
        private HealthTextView l;

        public SectionSideSlipViewHolder(View view) {
            super(view);
            RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.item_side_slip);
            this.c = relativeLayout;
            if (relativeLayout == null) {
                this.c = (RelativeLayout) view.findViewById(R.id.item_coupon_view);
            }
            this.i = (HealthImageView) view.findViewById(R.id.item_main_image);
            this.f = (TextView) view.findViewById(R.id.item_main_title);
            this.g = (FrameLayout) view.findViewById(R.id.item_coupon_background);
            this.h = (HealthTextView) view.findViewById(R.id.text_price_number);
            this.l = (HealthTextView) view.findViewById(R.id.text_unit);
            this.j = (HealthTextView) view.findViewById(R.id.item_coupon_name);
            this.b = (HealthTextView) view.findViewById(R.id.item_coupon_action);
            this.f2591a = (RelativeLayout) view.findViewById(R.id.item_coupon_action_rl);
        }

        public Object b() {
            return this.d;
        }

        public void a(Object obj) {
            this.d = obj;
        }
    }
}
