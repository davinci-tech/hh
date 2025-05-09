package com.huawei.health.knit.section.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.Section21_9Target_03_Adapter_01;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import defpackage.ebm;
import defpackage.eet;
import defpackage.nrf;
import defpackage.nsn;
import java.util.List;

/* loaded from: classes8.dex */
public class Section21_9Target_03_Adapter_01 extends RecyclerView.Adapter<d> {

    /* renamed from: a, reason: collision with root package name */
    private static final int f2550a = (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131363122_res_0x7f0a0532);
    private Context b;
    private List<String> c;
    private List<String> d;
    private List<Object> e;
    private List<Integer> f;
    private List<String> g;
    private List<Integer> h;
    private OnClickSectionListener i;
    private d j;
    private List<String> k;
    private List<String> l;
    private List<String> n;
    private List<String> o;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    public Section21_9Target_03_Adapter_01(Context context, ebm ebmVar) {
        this.b = context;
        d(ebmVar);
    }

    private void d(ebm ebmVar) {
        this.e = ebmVar.c();
        this.g = ebmVar.d();
        this.c = ebmVar.e();
        this.n = ebmVar.n();
        this.d = ebmVar.a();
        this.h = ebmVar.b();
        this.k = ebmVar.j();
        this.f = ebmVar.i();
        this.l = ebmVar.h();
        this.o = ebmVar.f();
        this.i = ebmVar.g();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: acZ_, reason: merged with bridge method [inline-methods] */
    public d onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new d(LayoutInflater.from(this.b).inflate(R.layout.section21_9target_03_item_01, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(d dVar, int i) {
        this.j = dVar;
        e(i);
    }

    private void e(final int i) {
        if (eet.b(this.e, i) && this.j.f2551a != null) {
            c(i);
        }
        if (eet.b(this.o, i) && this.j.g != null) {
            if (eet.b(this.g, i) && this.j.h != null) {
                this.j.h.setText(this.g.get(i));
            }
            this.j.g.setText(this.o.get(i));
            this.j.i.setVisibility(8);
            this.j.d.setVisibility(0);
        } else {
            a(i);
        }
        if (this.j.b != null) {
            this.j.b.setLayoutParams(b(i));
            this.j.b.setOnClickListener(new View.OnClickListener() { // from class: ebg
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    Section21_9Target_03_Adapter_01.this.acY_(i, view);
                }
            });
        }
    }

    public /* synthetic */ void acY_(int i, View view) {
        OnClickSectionListener onClickSectionListener = this.i;
        if (onClickSectionListener != null) {
            onClickSectionListener.onClick(i);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private RecyclerView.LayoutParams b(int i) {
        ViewGroup.LayoutParams layoutParams = this.j.b.getLayoutParams();
        if (!(layoutParams instanceof RecyclerView.LayoutParams)) {
            LogUtil.b("layoutParams error", new Object[0]);
            return null;
        }
        RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
        int dimensionPixelSize = this.b.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
        int dimensionPixelSize2 = this.b.getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d);
        int dimensionPixelSize3 = this.b.getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
        if (i == 0) {
            layoutParams2.setMarginStart(dimensionPixelSize);
        } else if (i == getItemCount() - 1) {
            layoutParams2.setMarginStart(dimensionPixelSize3);
            layoutParams2.setMarginEnd(dimensionPixelSize2);
        } else {
            layoutParams2.setMarginStart(dimensionPixelSize3);
        }
        return layoutParams2;
    }

    private void a(final int i) {
        this.j.i.setVisibility(0);
        this.j.d.setVisibility(8);
        if (eet.b(this.l, i) && this.j.f != null) {
            this.j.f.setText(this.l.get(i));
            this.j.f.setVisibility(0);
            this.j.k.setVisibility(8);
            this.j.l.setVisibility(8);
        } else if (eet.b(this.k, i) && this.j.k != null) {
            this.j.k.setText(this.k.get(i));
            this.j.k.setVisibility(0);
            this.j.l.setVisibility(8);
            this.j.k.setOnClickListener(new View.OnClickListener() { // from class: ebj
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    Section21_9Target_03_Adapter_01.this.acX_(i, view);
                }
            });
            d(i);
        } else {
            this.j.l.setVisibility(0);
        }
        h(i);
    }

    public /* synthetic */ void acX_(int i, View view) {
        OnClickSectionListener onClickSectionListener = this.i;
        if (onClickSectionListener != null) {
            onClickSectionListener.onClick(i, "BUTTON_CLICK_EVENT");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d(int i) {
        Integer num;
        if (!eet.b(this.f, i) || this.j.k == null || (num = this.f.get(i)) == null || num.intValue() == 0) {
            return;
        }
        this.j.k.setTextColor(num.intValue());
    }

    private void h(int i) {
        if (eet.b(this.g, i) && this.j.e != null) {
            this.j.e.setText(this.g.get(i));
        }
        if (eet.b(this.n, i) && this.j.n != null) {
            this.j.n.setText(this.n.get(i));
        }
        if (eet.b(this.c, i) && this.j.j != null) {
            this.j.j.setText(this.c.get(i));
        }
        if (eet.b(this.d, i) && this.j.m != null) {
            this.j.m.setText(this.d.get(i));
        }
        if (eet.b(this.h, i) && this.j.o != null) {
            this.j.o.setProgress(this.h.get(i).intValue());
        }
        g(i);
    }

    private void c(int i) {
        a();
        Object obj = this.e.get(i);
        Object a2 = this.j.a();
        if (obj instanceof String) {
            d(obj, a2);
        } else if (obj instanceof Integer) {
            e(obj, a2);
        } else {
            nrf.a(R.drawable._2131431393_res_0x7f0b0fe1, this.j.f2551a, f2550a);
        }
    }

    private void d(Object obj, Object obj2) {
        String str = (String) obj;
        String str2 = obj2 instanceof String ? (String) obj2 : "";
        if (str.length() <= 0 || str2.equals(str)) {
            return;
        }
        nrf.c(this.j.f2551a, str, f2550a, 0, R.drawable._2131431393_res_0x7f0b0fe1);
        this.j.a(str);
    }

    private void e(Object obj, Object obj2) {
        int intValue = ((Integer) obj).intValue();
        if ((obj2 instanceof Integer ? ((Integer) obj2).intValue() : 0) != intValue) {
            nrf.a(intValue, this.j.f2551a, f2550a);
            this.j.a(Integer.valueOf(intValue));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return eet.d(this.e, this.g, this.n, this.c, this.d, this.k);
    }

    private void a() {
        if (this.j.o != null) {
            this.j.o.setVisibility(0);
        }
        if (this.j.j != null) {
            this.j.j.setVisibility(0);
        }
        if (this.j.m != null) {
            this.j.m.setVisibility(0);
        }
    }

    private void g(int i) {
        if (this.b == null || !eet.b(this.k, i) || TextUtils.isEmpty(this.k.get(i)) || !TextUtils.equals(this.k.get(i), BaseApplication.getContext().getResources().getString(R$string.IDS_user_vip_renew))) {
            return;
        }
        if (this.j.k != null) {
            this.j.k.setVisibility(0);
        }
        if (this.j.o != null) {
            this.j.o.setVisibility(8);
        }
        if (this.j.j != null) {
            this.j.j.setVisibility(8);
        }
        if (this.j.m != null) {
            this.j.m.setVisibility(8);
        }
    }

    static class d extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthImageView f2551a;
        HealthCardView b;
        Object c;
        LinearLayout d;
        HealthTextView e;
        HealthTextView f;
        HealthTextView g;
        HealthTextView h;
        LinearLayout i;
        HealthTextView j;
        HealthButton k;
        View l;
        HealthTextView m;
        HealthTextView n;
        HealthProgressBar o;

        public d(View view) {
            super(view);
            this.b = (HealthCardView) view.findViewById(R.id.section21_9target_03_item_01);
            this.f2551a = (HealthImageView) view.findViewById(R.id.background_image);
            this.n = (HealthTextView) view.findViewById(R.id.section_title);
            this.e = (HealthTextView) view.findViewById(R.id.left_value_text);
            this.j = (HealthTextView) view.findViewById(R.id.middle_value_text);
            this.m = (HealthTextView) view.findViewById(R.id.right_value_text);
            this.o = (HealthProgressBar) view.findViewById(R.id.progress_bar);
            this.k = (HealthButton) view.findViewById(R.id.start_button);
            this.l = view.findViewById(R.id.space_view);
            this.f = (HealthTextView) view.findViewById(R.id.summary_text);
            this.h = (HealthTextView) this.b.findViewById(R.id.lowerText);
            this.g = (HealthTextView) view.findViewById(R.id.upperText);
            this.i = (LinearLayout) this.b.findViewById(R.id.ongoing_view);
            this.d = (LinearLayout) this.b.findViewById(R.id.helper_view);
            c();
        }

        public void a(Object obj) {
            this.c = obj;
        }

        public Object a() {
            return this.c;
        }

        private void c() {
            d();
        }

        private void d() {
            if (nsn.e(1.45f)) {
                if (BaseApplication.getContext().getResources() == null) {
                    LogUtil.h("Section21_9Target_03_Adapter_01", "setLargeFontScaleMode resources is null.");
                    return;
                }
                if (this.n.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                    ((ViewGroup.MarginLayoutParams) this.n.getLayoutParams()).topMargin = 36;
                }
                this.n.setTextSize(0, 78.0f);
                this.e.setTextSize(0, 51.0f);
                this.j.setTextSize(0, 51.0f);
                this.m.setTextSize(0, 51.0f);
                this.k.setTextSize(0, 51.0f);
                this.k.setAutoTextSize(0, 51.0f);
                this.k.setAutoTextInfo(51, 1, 0);
                if (this.k.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                    ((ViewGroup.MarginLayoutParams) this.k.getLayoutParams()).bottomMargin = 36;
                }
            }
        }
    }
}
