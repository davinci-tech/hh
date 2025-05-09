package com.huawei.health.knit.section.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.Section21_9Target_01_Adapter_01;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import defpackage.eba;
import defpackage.eet;
import defpackage.nrf;
import defpackage.nsn;
import java.util.List;

/* loaded from: classes3.dex */
public class Section21_9Target_01_Adapter_01 extends RecyclerView.Adapter<e> {
    private static final int e = (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131363122_res_0x7f0a0532);

    /* renamed from: a, reason: collision with root package name */
    private List<String> f2542a;
    private List<Integer> b;
    private List<String> c;
    private Context d;
    private List<String> f;
    private List<Integer> g;
    private OnClickSectionListener h;
    private e i;
    private List<String> j;
    private List<String> m;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    public Section21_9Target_01_Adapter_01(Context context, eba ebaVar) {
        this.d = context;
        b(ebaVar);
    }

    private void b(eba ebaVar) {
        this.b = ebaVar.e();
        this.f = ebaVar.b();
        this.f2542a = ebaVar.a();
        this.m = ebaVar.i();
        this.c = ebaVar.d();
        this.g = ebaVar.c();
        this.j = ebaVar.g();
        this.h = ebaVar.j();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: acG_, reason: merged with bridge method [inline-methods] */
    public e onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new e(LayoutInflater.from(this.d).inflate(R.layout.section21_9target_01_item_01, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(e eVar, int i) {
        this.i = eVar;
        c(i);
    }

    private void c(final int i) {
        if (eet.b(this.b, i) && this.i.d != null) {
            Object obj = this.b.get(i);
            Object e2 = this.i.e();
            if (obj instanceof String) {
                String str = (String) obj;
                String str2 = e2 instanceof String ? (String) e2 : "";
                if (str.length() > 0 && !str2.equals(str)) {
                    nrf.c(this.i.d, str, e, 0, R.drawable._2131431393_res_0x7f0b0fe1);
                    this.i.d(str);
                }
            } else if (obj instanceof Integer) {
                int intValue = ((Integer) obj).intValue();
                if ((e2 instanceof Integer ? ((Integer) e2).intValue() : 0) != intValue) {
                    nrf.a(intValue, this.i.d, e);
                    this.i.d(Integer.valueOf(intValue));
                }
            } else {
                nrf.a(R.drawable._2131431393_res_0x7f0b0fe1, this.i.d, e);
            }
        }
        b(i);
        a(i);
        if (nsn.s()) {
            this.i.h.setVisibility(8);
            nsn.b(this.i.f2543a);
            nsn.b(this.i.c);
            nsn.b(this.i.g);
            this.i.f.setVisibility(8);
        }
        if (this.i.e != null) {
            this.i.e.setOnClickListener(new View.OnClickListener() { // from class: eay
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    Section21_9Target_01_Adapter_01.this.acF_(i, view);
                }
            });
        }
    }

    public /* synthetic */ void acF_(int i, View view) {
        OnClickSectionListener onClickSectionListener = this.h;
        if (onClickSectionListener != null) {
            onClickSectionListener.onClick(i);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return eet.d(this.b, this.f, this.m, this.f2542a, this.c, this.j);
    }

    private void b(int i) {
        if (eet.b(this.f, i) && this.i.f2543a != null) {
            this.i.f2543a.setText(this.f.get(i));
        }
        if (eet.b(this.m, i) && this.i.i != null) {
            this.i.i.setText(this.m.get(i));
        }
        if (eet.b(this.f2542a, i) && this.i.c != null) {
            this.i.c.setText(this.f2542a.get(i));
        }
        if (eet.b(this.c, i) && this.i.g != null) {
            this.i.g.setText(this.c.get(i));
        }
        if (!eet.b(this.g, i) || this.i.f == null) {
            return;
        }
        this.i.f.setProgress(this.g.get(i).intValue());
    }

    private void a(final int i) {
        List<String> list = this.j;
        if (list != null && eet.b(list, i) && this.i.j != null) {
            this.i.j.setText(this.j.get(i));
            this.i.j.setVisibility(0);
            this.i.h.setVisibility(8);
            this.i.j.setOnClickListener(new View.OnClickListener() { // from class: eaz
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    Section21_9Target_01_Adapter_01.this.acE_(i, view);
                }
            });
            return;
        }
        this.i.h.setVisibility(0);
    }

    public /* synthetic */ void acE_(int i, View view) {
        OnClickSectionListener onClickSectionListener = this.h;
        if (onClickSectionListener != null) {
            onClickSectionListener.onClick(i, "BUTTON_CLICK_EVENT");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    static class e extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f2543a;
        Object b;
        HealthTextView c;
        HealthImageView d;
        HealthCardView e;
        HealthProgressBar f;
        HealthTextView g;
        View h;
        HealthTextView i;
        HealthButton j;

        public e(View view) {
            super(view);
            this.e = (HealthCardView) view.findViewById(R.id.section21_9target_01_item_01);
            this.d = (HealthImageView) view.findViewById(R.id.background_image);
            this.i = (HealthTextView) view.findViewById(R.id.section_title);
            this.f2543a = (HealthTextView) view.findViewById(R.id.left_value_text);
            this.c = (HealthTextView) view.findViewById(R.id.middle_value_text);
            this.g = (HealthTextView) view.findViewById(R.id.right_value_text);
            this.f = (HealthProgressBar) view.findViewById(R.id.progress_bar);
            this.j = (HealthButton) view.findViewById(R.id.start_button);
            this.h = view.findViewById(R.id.space_view);
        }

        public void d(Object obj) {
            this.b = obj;
        }

        public Object e() {
            return this.b;
        }
    }
}
