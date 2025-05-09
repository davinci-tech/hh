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
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.ebv;
import defpackage.eet;
import defpackage.jdw;
import defpackage.nrf;
import defpackage.nrt;
import defpackage.nsn;
import health.compact.a.Services;
import java.util.List;

/* loaded from: classes3.dex */
public class SectionNoSlideAdapter extends RecyclerView.Adapter<SectionNoSlideViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private List<Drawable> f2577a;
    private List<String> b;
    private List<String> c;
    private List<OnClickSectionListener> d;
    private List<String> e;
    private Context f;
    private List<String> g;
    private OnClickSectionListener h;
    private List<String> i;
    private List<String> j;
    private List<Integer> k;
    private List<String> n;

    public SectionNoSlideAdapter(Context context, ebv ebvVar) {
        this.f = context;
        e(ebvVar);
    }

    public void d(ebv ebvVar) {
        e(ebvVar);
        notifyDataSetChanged();
    }

    private void e(ebv ebvVar) {
        this.e = ebvVar.b();
        this.j = ebvVar.g();
        this.c = ebvVar.e();
        this.h = ebvVar.f();
        this.k = ebvVar.m();
        this.n = ebvVar.i();
        this.i = ebvVar.j();
        this.f2577a = ebvVar.a();
        this.g = ebvVar.h();
        this.b = ebvVar.c();
        this.d = ebvVar.d();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: adO_, reason: merged with bridge method [inline-methods] */
    public SectionNoSlideViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SectionNoSlideViewHolder(LayoutInflater.from(this.f).inflate(R.layout.layout_no_slide_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(SectionNoSlideViewHolder sectionNoSlideViewHolder, int i) {
        b(sectionNoSlideViewHolder, i);
    }

    private void b(SectionNoSlideViewHolder sectionNoSlideViewHolder, int i) {
        if (i == 0) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) sectionNoSlideViewHolder.itemView.getLayoutParams();
            layoutParams.leftMargin = nsn.c(this.f, 12.0f);
            layoutParams.rightMargin = nsn.c(this.f, 6.0f);
            sectionNoSlideViewHolder.itemView.setLayoutParams(layoutParams);
        } else if (i == getItemCount() - 1) {
            RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) sectionNoSlideViewHolder.itemView.getLayoutParams();
            layoutParams2.rightMargin = nsn.c(this.f, 12.0f);
            layoutParams2.leftMargin = nsn.c(this.f, 6.0f);
            sectionNoSlideViewHolder.itemView.setLayoutParams(layoutParams2);
        } else {
            RecyclerView.LayoutParams layoutParams3 = (RecyclerView.LayoutParams) sectionNoSlideViewHolder.itemView.getLayoutParams();
            layoutParams3.rightMargin = nsn.c(this.f, 6.0f);
            layoutParams3.leftMargin = nsn.c(this.f, 6.0f);
            sectionNoSlideViewHolder.itemView.setLayoutParams(layoutParams3);
        }
        c(sectionNoSlideViewHolder, i);
        i(sectionNoSlideViewHolder, i);
        a(sectionNoSlideViewHolder, i);
        e(sectionNoSlideViewHolder, i);
    }

    private void c(SectionNoSlideViewHolder sectionNoSlideViewHolder, int i) {
        if (!eet.b(this.e, i) || sectionNoSlideViewHolder.f == null) {
            return;
        }
        Object obj = this.e.get(i);
        Object b = sectionNoSlideViewHolder.b();
        if (obj instanceof String) {
            String str = (String) obj;
            String str2 = b instanceof String ? (String) b : "";
            if (str.length() <= 0 || str.equals(str2)) {
                return;
            }
            nrf.c(sectionNoSlideViewHolder.f, str, nrf.d, 0, R.drawable._2131431376_res_0x7f0b0fd0);
            sectionNoSlideViewHolder.b(str);
            return;
        }
        if (obj instanceof Integer) {
            int intValue = b instanceof Integer ? ((Integer) b).intValue() : 0;
            int intValue2 = ((Integer) obj).intValue();
            if (intValue != intValue2) {
                sectionNoSlideViewHolder.f.setImageResource(intValue2);
                sectionNoSlideViewHolder.b(Integer.valueOf(intValue2));
                return;
            }
            return;
        }
        nrf.a(R.drawable._2131431376_res_0x7f0b0fd0, sectionNoSlideViewHolder.f, nrf.d);
    }

    private void i(SectionNoSlideViewHolder sectionNoSlideViewHolder, int i) {
        if (!eet.b(this.j, i) || sectionNoSlideViewHolder.j == null) {
            return;
        }
        sectionNoSlideViewHolder.j.setText(this.j.get(i));
    }

    private void a(SectionNoSlideViewHolder sectionNoSlideViewHolder, final int i) {
        if (!eet.b(this.c, i) || sectionNoSlideViewHolder.b == null) {
            return;
        }
        sectionNoSlideViewHolder.b.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.adapter.SectionNoSlideAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SectionNoSlideAdapter.this.c.get(i) != null) {
                    String str = (String) SectionNoSlideAdapter.this.c.get(i);
                    if (str.startsWith("huaweischeme://")) {
                        Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(str));
                        intent.setPackage(SectionNoSlideAdapter.this.f.getPackageName());
                        jdw.bGh_(intent, SectionNoSlideAdapter.this.f);
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

    private void e(SectionNoSlideViewHolder sectionNoSlideViewHolder, final int i) {
        if (sectionNoSlideViewHolder.h != null && eet.b(this.n, i)) {
            sectionNoSlideViewHolder.h.setText(this.n.get(i));
        }
        if (sectionNoSlideViewHolder.d != null && eet.b(this.b, i)) {
            sectionNoSlideViewHolder.d.setText(this.b.get(i));
        }
        if (sectionNoSlideViewHolder.g != null && eet.b(this.i, i)) {
            sectionNoSlideViewHolder.g.setText(this.i.get(i));
            sectionNoSlideViewHolder.g.setTypeface(Typeface.createFromAsset(this.f.getAssets(), "font/HarmonyOSCondensedClockProportional-Medium.ttf"));
        }
        if (sectionNoSlideViewHolder.k != null && eet.b(this.g, i)) {
            sectionNoSlideViewHolder.k.setText(this.g.get(i));
        }
        if (sectionNoSlideViewHolder.i != null && eet.b(this.f2577a, i)) {
            sectionNoSlideViewHolder.i.setBackground(this.f2577a.get(i));
        }
        if (nrt.a(this.f)) {
            sectionNoSlideViewHolder.i.setAlpha(0.86f);
        }
        if (sectionNoSlideViewHolder.e != null && eet.b(this.d, i)) {
            sectionNoSlideViewHolder.e.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.adapter.SectionNoSlideAdapter.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ((OnClickSectionListener) SectionNoSlideAdapter.this.d.get(i)).onClick(i, "BOTTOM_TEXT_CLICK_EVENT");
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        if (sectionNoSlideViewHolder.i == null || !eet.b(this.d, i)) {
            return;
        }
        sectionNoSlideViewHolder.i.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.adapter.SectionNoSlideAdapter.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ((OnClickSectionListener) SectionNoSlideAdapter.this.d.get(i)).onClick(i, "RIGHT_TOP_IMAGE");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.n.size();
    }

    public class SectionNoSlideViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout b;
        private Object c;
        private HealthTextView d;
        private RelativeLayout e;
        private HealthImageView f;
        private HealthTextView g;
        private HealthTextView h;
        private FrameLayout i;
        private TextView j;
        private HealthTextView k;

        public SectionNoSlideViewHolder(View view) {
            super(view);
            this.b = (RelativeLayout) view.findViewById(R.id.item_coupon_view);
            this.f = (HealthImageView) view.findViewById(R.id.item_main_image);
            this.j = (TextView) view.findViewById(R.id.item_main_title);
            this.i = (FrameLayout) view.findViewById(R.id.item_coupon_background);
            this.g = (HealthTextView) view.findViewById(R.id.text_price_number);
            this.k = (HealthTextView) view.findViewById(R.id.text_unit);
            this.h = (HealthTextView) view.findViewById(R.id.item_coupon_name);
            this.d = (HealthTextView) view.findViewById(R.id.item_coupon_action);
            this.e = (RelativeLayout) view.findViewById(R.id.item_coupon_action_rl);
        }

        public Object b() {
            return this.c;
        }

        public void b(Object obj) {
            this.c = obj;
        }
    }
}
