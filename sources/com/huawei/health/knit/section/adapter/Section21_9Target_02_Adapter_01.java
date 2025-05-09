package com.huawei.health.knit.section.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.Section21_9Target_02_Adapter_01;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.columnlayout.HealthColumnLinearLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import defpackage.ebi;
import defpackage.eet;
import defpackage.nrf;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class Section21_9Target_02_Adapter_01 extends RecyclerView.Adapter<a> {
    private static final int e = (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131363122_res_0x7f0a0532);

    /* renamed from: a, reason: collision with root package name */
    private List<String> f2546a;
    private List<String> b;
    private Context c;
    private List<String> d;
    private List<Integer> f;
    private a g;
    private OnClickSectionListener h;
    private List<String> i;
    private List<Integer> j;
    private List<String> m;
    private List<String> o;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    public Section21_9Target_02_Adapter_01(Context context, ebi ebiVar) {
        this.c = context;
        b(ebiVar);
    }

    private void b(ebi ebiVar) {
        this.f2546a = ebiVar.c();
        this.i = ebiVar.b();
        this.d = ebiVar.e();
        this.m = ebiVar.j();
        this.b = ebiVar.d();
        this.j = ebiVar.a();
        this.o = ebiVar.h();
        this.f = ebiVar.f();
        this.h = ebiVar.g();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: acO_, reason: merged with bridge method [inline-methods] */
    public a onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new a(LayoutInflater.from(this.c).inflate(R.layout.section21_9target_02_item_01, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(a aVar, int i) {
        this.g = aVar;
        if (nsn.t()) {
            this.g.k.setTextSize(1, 31.5f);
            nsn.b(this.g.k);
            this.g.f.setTextSize(1, 17.4f);
            nsn.b(this.g.f);
            this.g.g.setTextSize(1, 17.4f);
            nsn.b(this.g.g);
            this.g.j.setTextSize(1, 17.4f);
            nsn.b(this.g.j);
            this.g.c.setTextSize(1, 31.5f);
            nsn.b(this.g.c);
            this.g.b.setTextSize(1, 17.4f);
            nsn.b(this.g.b);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.g.n.getLayoutParams();
            layoutParams.topMargin = (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131362886_res_0x7f0a0446);
            layoutParams.bottomMargin = (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131362886_res_0x7f0a0446);
            this.g.n.setLayoutParams(layoutParams);
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.g.o.getLayoutParams();
            layoutParams2.topMargin = (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131363003_res_0x7f0a04bb);
            this.g.o.setLayoutParams(layoutParams2);
            LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) this.g.i.getLayoutParams();
            layoutParams3.height = (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131362886_res_0x7f0a0446);
            this.g.i.setLayoutParams(layoutParams3);
        }
        this.g.k.setVisibility(8);
        this.g.f.setVisibility(8);
        this.g.g.setVisibility(8);
        this.g.j.setVisibility(8);
        this.g.i.setVisibility(8);
        this.g.m.setVisibility(8);
        this.g.c.setVisibility(8);
        this.g.b.setVisibility(8);
        if (this.f2546a != null && this.i != null && this.d == null && this.m != null && this.b == null && this.j == null && this.o == null) {
            c(i, true);
        } else {
            c(i, false);
        }
    }

    private void c(final int i, boolean z) {
        if (!z) {
            this.g.n.setVisibility(0);
            this.g.f2547a.setVisibility(8);
        } else {
            this.g.n.setVisibility(8);
            this.g.f2547a.setVisibility(0);
        }
        b(i);
        if (this.g.o != null) {
            if (LanguageUtil.h(BaseApplication.getContext())) {
                this.g.o.setOrientation(0);
            } else {
                this.g.o.setOrientation(1);
            }
        }
        d(i);
        if (this.g.e != null) {
            this.g.e.setOnClickListener(new View.OnClickListener() { // from class: ebd
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    Section21_9Target_02_Adapter_01.this.acM_(i, view);
                }
            });
        }
    }

    public /* synthetic */ void acM_(int i, View view) {
        if (this.h != null) {
            LogUtil.a("Track_RunPlanProvider", "target click");
            this.h.onClick(i);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return eet.d(this.f2546a, this.i, this.m, this.d, this.b, this.o);
    }

    private void d(final int i) {
        if (eet.b(this.i, i) && this.g.f != null) {
            this.g.f.setVisibility(0);
            this.g.f.setText(this.i.get(i));
            acP_(this.g.f);
        }
        if (eet.b(this.m, i) && this.g.k != null) {
            this.g.k.setVisibility(0);
            this.g.k.setText(this.m.get(i));
            if (LanguageUtil.ba(this.c)) {
                this.g.k.setTextSize(17.0f);
            }
            if (LanguageUtil.au(this.c)) {
                this.g.k.setTextSize(16.0f);
            }
            if (nsn.s()) {
                nsn.b(this.g.k);
            }
        }
        if (eet.b(this.d, i) && this.g.g != null) {
            this.g.g.setVisibility(0);
            this.g.g.setText(this.d.get(i));
            acP_(this.g.g);
        }
        if (eet.b(this.b, i) && this.g.j != null) {
            this.g.j.setVisibility(0);
            this.g.j.setText(this.b.get(i));
            acP_(this.g.j);
        }
        if (eet.b(this.j, i) && this.g.i != null) {
            this.g.i.setVisibility(0);
            this.g.i.setProgress(this.j.get(i).intValue());
            acP_(this.g.i);
        }
        if (eet.b(this.o, i) && this.g.m != null) {
            this.g.m.setText(this.o.get(i));
            this.g.m.setVisibility(0);
            this.g.m.setOnClickListener(new View.OnClickListener() { // from class: ebb
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    Section21_9Target_02_Adapter_01.this.acN_(i, view);
                }
            });
            e(i);
        }
        c(i);
    }

    public /* synthetic */ void acN_(int i, View view) {
        OnClickSectionListener onClickSectionListener = this.h;
        if (onClickSectionListener != null) {
            onClickSectionListener.onClick(i, "BUTTON_CLICK_EVENT");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e(int i) {
        Integer num;
        if (!eet.b(this.f, i) || this.g.m == null || (num = this.f.get(i)) == null || num.intValue() == 0) {
            return;
        }
        this.g.m.setTextColor(num.intValue());
    }

    private void c(int i) {
        if (eet.b(this.i, i) && this.g.b != null) {
            this.g.b.setVisibility(0);
            this.g.b.setText(this.i.get(i));
            acP_(this.g.b);
        }
        if (!eet.b(this.m, i) || this.g.c == null) {
            return;
        }
        this.g.c.setVisibility(0);
        this.g.c.setText(this.m.get(i));
        if (nsn.s()) {
            nsn.b(this.g.c);
        }
    }

    private void b(int i) {
        if (!eet.b(this.f2546a, i) || this.g.d == null) {
            return;
        }
        Object obj = this.f2546a.get(i);
        Object a2 = this.g.a();
        if (obj instanceof String) {
            String str = (String) obj;
            String str2 = a2 instanceof String ? (String) a2 : "";
            if (str.length() <= 0 || str2.equals(str)) {
                return;
            }
            nrf.c(this.g.d, str, e, 0, R.drawable._2131431393_res_0x7f0b0fe1);
            this.g.e(str);
            return;
        }
        if (obj instanceof Integer) {
            int intValue = ((Integer) obj).intValue();
            if ((a2 instanceof Integer ? ((Integer) a2).intValue() : 0) != intValue) {
                nrf.a(intValue, this.g.d, e);
                this.g.e(Integer.valueOf(intValue));
                return;
            }
            return;
        }
        nrf.a(R.drawable._2131431393_res_0x7f0b0fe1, this.g.d, e);
    }

    static class a extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthColumnLinearLayout f2547a;
        HealthTextView b;
        HealthTextView c;
        HealthImageView d;
        HealthCardView e;
        HealthTextView f;
        HealthTextView g;
        Object h;
        HealthProgressBar i;
        HealthTextView j;
        HealthTextView k;
        HealthButton m;
        HealthColumnLinearLayout n;
        LinearLayout o;

        public a(View view) {
            super(view);
            this.e = (HealthCardView) view.findViewById(R.id.section21_9target_02_item_01);
            this.d = (HealthImageView) view.findViewById(R.id.background_image);
            this.k = (HealthTextView) view.findViewById(R.id.section_title);
            this.f = (HealthTextView) view.findViewById(R.id.left_value_text);
            this.g = (HealthTextView) view.findViewById(R.id.middle_value_text);
            this.j = (HealthTextView) view.findViewById(R.id.right_value_text);
            this.o = (LinearLayout) view.findViewById(R.id.value_layout);
            this.i = (HealthProgressBar) view.findViewById(R.id.progress_bar);
            this.m = (HealthButton) view.findViewById(R.id.start_button);
            this.c = (HealthTextView) view.findViewById(R.id.bottom_section_title);
            this.b = (HealthTextView) view.findViewById(R.id.bottom_left_value_text);
            this.n = (HealthColumnLinearLayout) view.findViewById(R.id.section_layout);
            this.f2547a = (HealthColumnLinearLayout) view.findViewById(R.id.bottom_section_layout);
        }

        public void e(Object obj) {
            this.h = obj;
        }

        public Object a() {
            return this.h;
        }
    }

    public void acP_(View view) {
        if (nsn.s()) {
            view.setVisibility(8);
        }
    }
}
