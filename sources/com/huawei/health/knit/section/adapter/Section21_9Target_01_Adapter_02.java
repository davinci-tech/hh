package com.huawei.health.knit.section.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.Section21_9Target_01_Adapter_02;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.ebf;
import defpackage.eet;
import defpackage.nrf;
import java.util.List;

/* loaded from: classes3.dex */
public class Section21_9Target_01_Adapter_02 extends RecyclerView.Adapter<d> {
    private static final int c = (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131363122_res_0x7f0a0532);

    /* renamed from: a, reason: collision with root package name */
    private Context f2544a;
    private List<Object> b;
    private d d;
    private List<String> e;
    private OnClickSectionListener g;
    private List<String> j;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    public Section21_9Target_01_Adapter_02(Context context, ebf ebfVar) {
        this.f2544a = context;
        b(ebfVar);
    }

    private void b(ebf ebfVar) {
        this.j = ebfVar.c();
        this.b = ebfVar.d();
        this.e = ebfVar.b();
        this.g = ebfVar.a();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: acI_, reason: merged with bridge method [inline-methods] */
    public d onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new d(LayoutInflater.from(this.f2544a).inflate(R.layout.section21_9target_01_item_02, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(d dVar, int i) {
        this.d = dVar;
        d(i);
    }

    private void d(final int i) {
        if (eet.b(this.b, i) && this.d.b != null) {
            Object obj = this.b.get(i);
            Object a2 = this.d.a();
            if (obj instanceof String) {
                String str = (String) obj;
                String str2 = a2 instanceof String ? (String) a2 : "";
                if (str.length() > 0 && !str2.equals(str)) {
                    nrf.c(this.d.b, str, c, 0, R.drawable._2131431393_res_0x7f0b0fe1);
                    this.d.a(str);
                }
            } else if (obj instanceof Integer) {
                int intValue = ((Integer) obj).intValue();
                if ((a2 instanceof Integer ? ((Integer) a2).intValue() : 0) != intValue) {
                    nrf.a(intValue, this.d.b, c);
                    this.d.a(Integer.valueOf(intValue));
                }
            } else {
                nrf.a(R.drawable._2131431393_res_0x7f0b0fe1, this.d.b, c);
            }
        }
        if (eet.b(this.j, i) && this.d.d != null) {
            this.d.d.setText(this.j.get(i));
        }
        if (eet.b(this.e, i) && this.d.f2545a != null) {
            this.d.f2545a.setText(this.e.get(i));
        }
        if (this.d.c != null) {
            this.d.c.setOnClickListener(new View.OnClickListener() { // from class: eax
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    Section21_9Target_01_Adapter_02.this.acH_(i, view);
                }
            });
        }
    }

    public /* synthetic */ void acH_(int i, View view) {
        OnClickSectionListener onClickSectionListener = this.g;
        if (onClickSectionListener != null) {
            onClickSectionListener.onClick(i);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return eet.d(this.b, this.j, this.e);
    }

    static class d extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private final TextView f2545a;
        private final HealthImageView b;
        private final FrameLayout c;
        private final TextView d;
        private Object e;

        public d(View view) {
            super(view);
            this.c = (FrameLayout) view.findViewById(R.id.section21_9target_01_item_02);
            this.b = (HealthImageView) view.findViewById(R.id.background_image);
            this.d = (TextView) view.findViewById(R.id.section_title);
            this.f2545a = (TextView) view.findViewById(R.id.section_description);
        }

        public void a(Object obj) {
            this.e = obj;
        }

        public Object a() {
            return this.e;
        }
    }
}
