package com.huawei.health.knit.section.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.eat;
import defpackage.eet;
import defpackage.nrf;
import defpackage.nsn;
import defpackage.nsy;
import java.util.List;

/* loaded from: classes3.dex */
public class Section16_9Series_01Adapter extends RecyclerView.Adapter<Section16_9Series_01ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private List<String> f2525a;
    private List<String> b;
    private List<Object> c;
    private List<String> d;
    private Context e;
    private List<String> f;
    private List<String> g;
    private List<String> h;
    private List<Object> i;
    private OnClickSectionListener j;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    public Section16_9Series_01Adapter(Context context, eat eatVar) {
        this.e = context;
        b(eatVar);
    }

    public void c(eat eatVar) {
        b(eatVar);
        notifyDataSetChanged();
    }

    private void b(eat eatVar) {
        this.g = eatVar.j();
        this.c = eatVar.e();
        this.i = eatVar.g();
        this.b = eatVar.c();
        this.f2525a = eatVar.a();
        this.f = eatVar.b();
        this.d = eatVar.d();
        this.h = eatVar.i();
        this.j = eatVar.f();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: acm_, reason: merged with bridge method [inline-methods] */
    public Section16_9Series_01ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new Section16_9Series_01ViewHolder(LayoutInflater.from(this.e).inflate(R.layout.section16_9series_01_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(Section16_9Series_01ViewHolder section16_9Series_01ViewHolder, int i) {
        d(section16_9Series_01ViewHolder, i);
    }

    private void d(Section16_9Series_01ViewHolder section16_9Series_01ViewHolder, final int i) {
        RecyclerView.LayoutParams c = c(section16_9Series_01ViewHolder, i);
        if (c == null) {
            LogUtil.b("params is invalid!", new Object[0]);
            return;
        }
        if (section16_9Series_01ViewHolder.h != null) {
            section16_9Series_01ViewHolder.h.setLayoutParams(c);
            section16_9Series_01ViewHolder.h.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.adapter.Section16_9Series_01Adapter.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (Section16_9Series_01Adapter.this.j != null) {
                        Section16_9Series_01Adapter.this.j.onClick(i);
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        b(section16_9Series_01ViewHolder, i);
        j(section16_9Series_01ViewHolder, i);
        a(section16_9Series_01ViewHolder, i);
        g(section16_9Series_01ViewHolder, i);
    }

    protected RecyclerView.LayoutParams c(Section16_9Series_01ViewHolder section16_9Series_01ViewHolder, int i) {
        ViewGroup.LayoutParams layoutParams = section16_9Series_01ViewHolder.h.getLayoutParams();
        if (!(layoutParams instanceof RecyclerView.LayoutParams)) {
            LogUtil.b("layoutParams error", new Object[0]);
            return null;
        }
        RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
        int dimensionPixelSize = this.e.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
        int dimensionPixelSize2 = this.e.getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d);
        int dimensionPixelSize3 = this.e.getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
        if (i == 0) {
            layoutParams2.setMarginStart(dimensionPixelSize + ((Integer) BaseActivity.getSafeRegionWidth().first).intValue());
        } else if (i == getItemCount() - 1) {
            layoutParams2.setMarginStart(dimensionPixelSize3);
            layoutParams2.setMarginEnd(dimensionPixelSize2 + ((Integer) BaseActivity.getSafeRegionWidth().second).intValue());
        } else {
            layoutParams2.setMarginStart(dimensionPixelSize3);
        }
        return layoutParams2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return eet.d(this.c, this.g, this.f2525a, this.f, this.d, this.h);
    }

    private void g(Section16_9Series_01ViewHolder section16_9Series_01ViewHolder, int i) {
        if (eet.b(this.g, i) && section16_9Series_01ViewHolder.n != null) {
            section16_9Series_01ViewHolder.n.setText(this.g.get(i));
        }
        if (!eet.b(this.f2525a, i) || TextUtils.isEmpty(this.f2525a.get(i)) || section16_9Series_01ViewHolder.f == null) {
            nsy.cMA_(section16_9Series_01ViewHolder.f, 8);
        } else {
            section16_9Series_01ViewHolder.f.setText(this.f2525a.get(i));
        }
        if (eet.b(this.f, i) && section16_9Series_01ViewHolder.j != null) {
            section16_9Series_01ViewHolder.j.setText(this.f.get(i));
        }
        if (!eet.b(this.d, i) || TextUtils.isEmpty(this.d.get(i)) || section16_9Series_01ViewHolder.b == null) {
            nsy.cMA_(section16_9Series_01ViewHolder.b, 8);
        } else {
            section16_9Series_01ViewHolder.b.setText(this.d.get(i));
        }
        if (!eet.b(this.h, i) || section16_9Series_01ViewHolder.k == null) {
            return;
        }
        section16_9Series_01ViewHolder.k.setText(this.h.get(i));
        if (nsn.s()) {
            section16_9Series_01ViewHolder.k.setVisibility(8);
        }
    }

    private void e(final Section16_9Series_01ViewHolder section16_9Series_01ViewHolder, Object obj, Object obj2) {
        final String str = (String) obj;
        String str2 = obj2 instanceof String ? (String) obj2 : "";
        if (str.length() > 0 && !str.equals(str2)) {
            nrf.d(str, new CustomTarget<Drawable>() { // from class: com.huawei.health.knit.section.adapter.Section16_9Series_01Adapter.1
                @Override // com.bumptech.glide.request.target.Target
                public void onLoadCleared(Drawable drawable) {
                }

                @Override // com.bumptech.glide.request.target.Target
                /* renamed from: aci_, reason: merged with bridge method [inline-methods] */
                public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                    section16_9Series_01ViewHolder.g.setImageDrawable(drawable);
                    section16_9Series_01ViewHolder.d(str);
                }

                @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
                public void onLoadFailed(Drawable drawable) {
                    LogUtil.h("Section16_9Series_01Adapter", "loadRoundRectangle onLoadFailed");
                }
            });
        }
        section16_9Series_01ViewHolder.g.setVisibility(0);
    }

    private void j(Section16_9Series_01ViewHolder section16_9Series_01ViewHolder, int i) {
        if (eet.b(this.i, i) && section16_9Series_01ViewHolder.g != null) {
            Object obj = this.i.get(i);
            Object e = section16_9Series_01ViewHolder.e();
            if (obj instanceof String) {
                e(section16_9Series_01ViewHolder, obj, e);
                return;
            }
            if (obj instanceof Integer) {
                int intValue = ((Integer) obj).intValue();
                if (intValue != (e instanceof Integer ? ((Integer) e).intValue() : 0)) {
                    section16_9Series_01ViewHolder.g.setImageResource(intValue);
                    section16_9Series_01ViewHolder.d(Integer.valueOf(intValue));
                }
                section16_9Series_01ViewHolder.g.setVisibility(0);
                return;
            }
            section16_9Series_01ViewHolder.g.setVisibility(8);
            return;
        }
        nsy.cMA_(section16_9Series_01ViewHolder.g, 8);
    }

    private void a(Section16_9Series_01ViewHolder section16_9Series_01ViewHolder, int i) {
        if (section16_9Series_01ViewHolder.g == null || section16_9Series_01ViewHolder.g.getVisibility() != 0) {
            if (eet.b(this.b, i) && section16_9Series_01ViewHolder.c != null) {
                String str = this.b.get(i);
                boolean isEmpty = TextUtils.isEmpty(str);
                if (!isEmpty) {
                    nrf.a(str, section16_9Series_01ViewHolder.c, 0.0f, nrf.d, 0.0f, 0.0f);
                }
                nsy.cMA_(section16_9Series_01ViewHolder.c, isEmpty ? 8 : 0);
                return;
            }
            nsy.cMA_(section16_9Series_01ViewHolder.c, 8);
            return;
        }
        nsy.cMA_(section16_9Series_01ViewHolder.c, 8);
    }

    private void b(Section16_9Series_01ViewHolder section16_9Series_01ViewHolder, int i) {
        if (!eet.b(this.c, i) || section16_9Series_01ViewHolder.e == null) {
            return;
        }
        Object obj = this.c.get(i);
        Object a2 = section16_9Series_01ViewHolder.a();
        if (obj instanceof String) {
            String str = (String) obj;
            String str2 = a2 instanceof String ? (String) a2 : "";
            if (str.length() <= 0 || str.equals(str2)) {
                return;
            }
            nrf.c(section16_9Series_01ViewHolder.e, str, nrf.d, 6, R.drawable._2131431376_res_0x7f0b0fd0);
            section16_9Series_01ViewHolder.b(str);
            return;
        }
        if (obj instanceof Integer) {
            int intValue = a2 instanceof Integer ? ((Integer) a2).intValue() : 0;
            int intValue2 = ((Integer) obj).intValue();
            if (intValue != intValue2) {
                section16_9Series_01ViewHolder.e.setImageResource(intValue2);
                section16_9Series_01ViewHolder.b(Integer.valueOf(intValue2));
                return;
            }
            return;
        }
        nrf.a(R.drawable._2131431376_res_0x7f0b0fd0, section16_9Series_01ViewHolder.e, nrf.d);
    }

    public class Section16_9Series_01ViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private Object f2527a;
        private HealthTextView b;
        private HealthImageView c;
        private HealthImageView e;
        private HealthTextView f;
        private ImageView g;
        private FrameLayout h;
        private Object i;
        private HealthTextView j;
        private HealthTextView k;
        private HealthTextView n;

        public Section16_9Series_01ViewHolder(View view) {
            super(view);
            this.h = (FrameLayout) view.findViewById(R.id.recycle_item);
            this.e = (HealthImageView) view.findViewById(R.id.background_image);
            this.c = (HealthImageView) view.findViewById(R.id.section16_9series_01_card_corner);
            this.g = (ImageView) view.findViewById(R.id.new_image);
            this.n = (HealthTextView) view.findViewById(R.id.section_title);
            this.f = (HealthTextView) view.findViewById(R.id.section_difficulty);
            this.j = (HealthTextView) view.findViewById(R.id.section_duration);
            this.b = (HealthTextView) view.findViewById(R.id.section_calorie);
            this.k = (HealthTextView) view.findViewById(R.id.section_train_number);
        }

        Object a() {
            return this.f2527a;
        }

        void b(Object obj) {
            this.f2527a = obj;
        }

        Object e() {
            return this.i;
        }

        void d(Object obj) {
            this.i = obj;
        }

        public FrameLayout acl_() {
            return this.h;
        }
    }
}
