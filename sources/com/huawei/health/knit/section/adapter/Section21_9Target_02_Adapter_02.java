package com.huawei.health.knit.section.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.Section21_9Target_02_Adapter_02;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.ebh;
import defpackage.eet;
import defpackage.nrf;
import defpackage.nsn;
import java.util.List;

/* loaded from: classes3.dex */
public class Section21_9Target_02_Adapter_02 extends RecyclerView.Adapter<b> {
    private static final int d = (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131363122_res_0x7f0a0532);

    /* renamed from: a, reason: collision with root package name */
    private List<Object> f2548a;
    private List<String> b;
    private Context c;
    private b e;
    private OnClickSectionListener f;
    private List<Integer> g;
    private List<String> h;
    private List<String> i;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    public Section21_9Target_02_Adapter_02(Context context, ebh ebhVar) {
        this.c = context;
        d(ebhVar);
    }

    private void d(ebh ebhVar) {
        this.h = ebhVar.d();
        this.f2548a = ebhVar.a();
        this.b = ebhVar.e();
        this.i = ebhVar.f();
        this.g = ebhVar.b();
        this.f = ebhVar.c();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: acS_, reason: merged with bridge method [inline-methods] */
    public b onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new b(LayoutInflater.from(this.c).inflate(R.layout.section21_9target_02_item_02, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(b bVar, int i) {
        this.e = bVar;
        if (nsn.t()) {
            this.e.i.setTextSize(1, 23.2f);
            this.e.c.setTextSize(1, 17.4f);
            this.e.h.setTextSize(1, 17.4f);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.e.b.getLayoutParams();
            layoutParams.topMargin = (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131362886_res_0x7f0a0446);
            this.e.b.setLayoutParams(layoutParams);
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.e.c.getLayoutParams();
            layoutParams2.topMargin = (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131363003_res_0x7f0a04bb);
            this.e.c.setLayoutParams(layoutParams2);
        }
        d(i);
    }

    private void d(final int i) {
        if (eet.b(this.f2548a, i) && this.e.d != null) {
            Object obj = this.f2548a.get(i);
            Object e = this.e.e();
            if (obj instanceof String) {
                String str = (String) obj;
                String str2 = e instanceof String ? (String) e : "";
                if (str.length() > 0 && !str2.equals(str)) {
                    nrf.c(this.e.d, str, d, 0, R.drawable._2131431393_res_0x7f0b0fe1);
                    this.e.b(str);
                }
            } else if (obj instanceof Integer) {
                int intValue = ((Integer) obj).intValue();
                if ((e instanceof Integer ? ((Integer) e).intValue() : 0) != intValue) {
                    nrf.a(intValue, this.e.d, d);
                    this.e.b(Integer.valueOf(intValue));
                }
            } else {
                nrf.a(R.drawable._2131431393_res_0x7f0b0fe1, this.e.d, d);
            }
        }
        if (eet.b(this.h, i) && this.e.i != null) {
            this.e.i.setText(this.h.get(i));
        }
        if (eet.b(this.b, i) && this.e.c != null) {
            this.e.c.setText(this.b.get(i));
        }
        if (eet.b(this.i, i) && this.e.h != null) {
            this.e.h.setText(this.i.get(i));
            a(i);
        }
        if (this.e.e != null) {
            this.e.e.setOnClickListener(new View.OnClickListener() { // from class: ebc
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    Section21_9Target_02_Adapter_02.this.acQ_(i, view);
                }
            });
            this.e.h.setOnClickListener(new View.OnClickListener() { // from class: ebe
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    Section21_9Target_02_Adapter_02.this.acR_(i, view);
                }
            });
        }
    }

    public /* synthetic */ void acQ_(int i, View view) {
        OnClickSectionListener onClickSectionListener = this.f;
        if (onClickSectionListener != null) {
            onClickSectionListener.onClick(i);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void acR_(int i, View view) {
        OnClickSectionListener onClickSectionListener = this.f;
        if (onClickSectionListener != null) {
            onClickSectionListener.onClick(i, "BUTTON_CLICK_EVENT");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a(int i) {
        Integer num;
        if (!eet.b(this.g, i) || this.e.h == null || (num = this.g.get(i)) == null || num.intValue() == 0) {
            return;
        }
        this.e.h.setTextColor(num.intValue());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return eet.d(this.f2548a, this.h, this.b, this.i);
    }

    static class b extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private Object f2549a;
        private final LinearLayout b;
        private final TextView c;
        private final HealthImageView d;
        private final FrameLayout e;
        private final HealthButton h;
        private final TextView i;

        public b(View view) {
            super(view);
            this.e = (FrameLayout) view.findViewById(R.id.section21_9target_02_item_02);
            this.b = (LinearLayout) view.findViewById(R.id.section_layout);
            this.d = (HealthImageView) view.findViewById(R.id.background_image);
            this.i = (TextView) view.findViewById(R.id.section_title);
            this.c = (TextView) view.findViewById(R.id.section_description);
            this.h = (HealthButton) view.findViewById(R.id.start_button);
        }

        public void b(Object obj) {
            this.f2549a = obj;
        }

        public Object e() {
            return this.f2549a;
        }
    }
}
