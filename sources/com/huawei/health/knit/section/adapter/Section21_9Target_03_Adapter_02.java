package com.huawei.health.knit.section.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.Section21_9Target_03_Adapter_02;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.pricetagbean.PriceTagBean;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.ebn;
import defpackage.eet;
import defpackage.koq;
import defpackage.mod;
import defpackage.nrf;
import defpackage.nsn;
import java.util.List;

/* loaded from: classes8.dex */
public class Section21_9Target_03_Adapter_02 extends RecyclerView.Adapter<d> {
    private static final int e = (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131363122_res_0x7f0a0532);

    /* renamed from: a, reason: collision with root package name */
    private List<String> f2552a;
    private List<Object> b;
    private Context c;
    private List<List<PriceTagBean>> d;
    private OnClickSectionListener g;
    private d i;
    private List<String> j;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    public Section21_9Target_03_Adapter_02(Context context, ebn ebnVar) {
        this.c = context;
        d(ebnVar);
    }

    private void d(ebn ebnVar) {
        this.j = ebnVar.b();
        this.b = ebnVar.a();
        this.f2552a = ebnVar.e();
        this.g = ebnVar.d();
        this.d = ebnVar.c();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: adb_, reason: merged with bridge method [inline-methods] */
    public d onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new d(LayoutInflater.from(this.c).inflate(R.layout.section21_9target_03_item_02, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(d dVar, int i) {
        this.i = dVar;
        e(i);
    }

    private void e(final int i) {
        RecyclerView.LayoutParams b = b(i);
        if (b == null) {
            LogUtil.b("params is invalid!", new Object[0]);
            return;
        }
        if (eet.b(this.b, i) && this.i.c != null) {
            a(i);
        }
        if (eet.b(this.d, i) && this.i.f2553a != null) {
            d(i);
        }
        if (eet.b(this.j, i) && this.i.g != null) {
            this.i.g.setText(this.j.get(i));
        }
        if (eet.b(this.f2552a, i) && this.i.e != null) {
            this.i.e.setText(this.f2552a.get(i));
        }
        if (this.i.b != null) {
            this.i.b.setLayoutParams(b);
            this.i.b.setOnClickListener(new View.OnClickListener() { // from class: ebk
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    Section21_9Target_03_Adapter_02.this.ada_(i, view);
                }
            });
        }
    }

    public /* synthetic */ void ada_(int i, View view) {
        OnClickSectionListener onClickSectionListener = this.g;
        if (onClickSectionListener != null) {
            onClickSectionListener.onClick(i);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d(int i) {
        if (koq.b(this.d, i)) {
            LogUtil.a("Section21_9Target_03_Adapter_02", "position is out of bounds");
            this.i.f2553a.setVisibility(8);
            return;
        }
        List<PriceTagBean> list = this.d.get(i);
        if (koq.c(list)) {
            this.i.f2553a.setVisibility(0);
            nrf.a(mod.b(list), this.i.f2553a, 0.0f, nrf.d, 0.0f, 0.0f);
        } else {
            this.i.f2553a.setVisibility(8);
        }
    }

    private void a(int i) {
        Object obj = this.b.get(i);
        Object c = this.i.c();
        if (obj instanceof String) {
            a(obj, c);
        } else if (obj instanceof Integer) {
            e(obj, c);
        } else {
            nrf.a(R.drawable._2131431393_res_0x7f0b0fe1, this.i.c, e);
        }
    }

    private void a(Object obj, Object obj2) {
        String str = (String) obj;
        String str2 = obj2 instanceof String ? (String) obj2 : "";
        if (str.length() <= 0 || str2.equals(str)) {
            return;
        }
        nrf.c(this.i.c, str, e, 0, R.drawable._2131431393_res_0x7f0b0fe1);
        this.i.d(str);
    }

    private void e(Object obj, Object obj2) {
        int intValue = ((Integer) obj).intValue();
        if ((obj2 instanceof Integer ? ((Integer) obj2).intValue() : 0) != intValue) {
            nrf.a(intValue, this.i.c, e);
            this.i.d(Integer.valueOf(intValue));
        }
    }

    private RecyclerView.LayoutParams b(int i) {
        ViewGroup.LayoutParams layoutParams = this.i.b.getLayoutParams();
        if (!(layoutParams instanceof RecyclerView.LayoutParams)) {
            LogUtil.b("layoutParams error", new Object[0]);
            return null;
        }
        RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
        int dimensionPixelSize = this.c.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
        int dimensionPixelSize2 = this.c.getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d);
        int dimensionPixelSize3 = this.c.getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
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

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return eet.d(this.b, this.j, this.f2552a);
    }

    static class d extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private final HealthImageView f2553a;
        private final FrameLayout b;
        private final HealthImageView c;
        private Object d;
        private final TextView e;
        private final TextView g;

        public d(View view) {
            super(view);
            this.b = (FrameLayout) view.findViewById(R.id.section21_9target_03_item_02);
            this.c = (HealthImageView) view.findViewById(R.id.background_image);
            TextView textView = (TextView) view.findViewById(R.id.section_title);
            this.g = textView;
            TextView textView2 = (TextView) view.findViewById(R.id.section_description);
            this.e = textView2;
            this.f2553a = (HealthImageView) view.findViewById(R.id.card_corner);
            if (nsn.s()) {
                textView.setMaxLines(1);
                textView.setEllipsize(TextUtils.TruncateAt.END);
                textView2.setMaxLines(1);
                textView2.setEllipsize(TextUtils.TruncateAt.END);
            }
        }

        public void d(Object obj) {
            this.d = obj;
        }

        public Object c() {
            return this.d;
        }
    }
}
