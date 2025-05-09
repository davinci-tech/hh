package com.huawei.health.section.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.search.SearchResultItemView;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import defpackage.eet;
import defpackage.fbm;
import defpackage.koq;
import defpackage.nsn;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class Section1_1Search_01Adapter extends RecyclerView.Adapter<e> {

    /* renamed from: a, reason: collision with root package name */
    private Context f2966a;
    private int b = -1;
    private int d = Integer.MAX_VALUE;
    private fbm e;

    public Section1_1Search_01Adapter(Context context, fbm fbmVar) {
        this.f2966a = context;
        this.e = fbmVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: avK_, reason: merged with bridge method [inline-methods] */
    public e onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new e(new SearchResultItemView(this.f2966a, R.layout.section1_1search_01_item));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(e eVar, int i) {
        eet.ahd_(this.f2966a, eVar.e, i, getItemCount());
        a(eVar, i);
    }

    private void a(e eVar, int i) {
        eet.ahc_(eVar.itemView, this.e.c(), i);
        eet.e(eVar.b, this.e.d(), i);
        eet.ahf_(eVar.l, this.e.o(), null, i, false);
        eet.ahf_(eVar.i, this.e.f(), null, i, false);
        eet.ahf_(eVar.n, this.e.n(), this.e.a(), i, true);
        eVar.n.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
        eVar.n.getPaint().setStrokeWidth(0.5f);
        eet.ahf_(eVar.m, this.e.m(), this.e.a(), i, true);
        eet.ahf_(eVar.g, this.e.h(), this.e.a(), i, true);
        eet.ahf_(eVar.d, this.e.e(), this.e.a(), i, true);
        eet.ahf_(eVar.f, this.e.j(), this.e.a(), i, false);
        c(eVar, i);
        if (koq.b(this.e.b()) || this.e.b().get(i) == null) {
            eVar.c.setVisibility(8);
        } else {
            eet.b(eVar.c, this.e.b().get(i));
            eVar.c.setVisibility(0);
        }
        eet.ahe_(eVar.i, this.e.i(), i);
        eet.ahe_(eVar.j, this.e.i(), i);
    }

    private void c(e eVar, int i) {
        if (koq.b(this.e.g())) {
            ReleaseLogUtil.d("Section1_1Search_01Adapter", "addLabelToLayout mRecyclerViewData.getLabelList() is null");
            return;
        }
        if (i < 0 || i >= this.e.g().size()) {
            ReleaseLogUtil.d("Section1_1Search_01Adapter", "IndexOutOfBoundsException");
            return;
        }
        for (String str : this.e.g().get(i).split("  ")) {
            if (!TextUtils.isEmpty(str)) {
                HealthTextView healthTextView = new HealthTextView(this.f2966a);
                healthTextView.setTypeface(Typeface.create("sans-serif", 0));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
                layoutParams.setMargins(0, 0, nsn.c(this.f2966a, 8.0f), 0);
                healthTextView.setLayoutParams(layoutParams);
                healthTextView.setPadding(nsn.c(this.f2966a, 4.0f), nsn.c(this.f2966a, 1.0f), nsn.c(this.f2966a, 4.0f), nsn.c(this.f2966a, 1.0f));
                healthTextView.setBackgroundResource(R.drawable._2131427832_res_0x7f0b01f8);
                healthTextView.setIncludeFontPadding(false);
                healthTextView.setGravity(17);
                healthTextView.setTextColor(this.f2966a.getColor(R.color._2131299241_res_0x7f090ba9));
                healthTextView.setTextSize(1, 10.0f);
                healthTextView.setSingleLine(true);
                healthTextView.setEllipsize(TextUtils.TruncateAt.END);
                ArrayList arrayList = new ArrayList(1);
                arrayList.add(str);
                eet.ahf_(healthTextView, arrayList, this.e.a(), 0, true);
                eVar.h.addView(healthTextView);
            }
        }
        eVar.h.setVisibility(eVar.h.getChildCount() <= 0 ? 8 : 0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.b == -1) {
            int d = eet.d(this.e.d(), this.e.n(), this.e.m());
            this.b = d;
            this.b = Math.min(d, this.d);
        }
        return this.b;
    }

    public void d(int i) {
        this.d = i;
    }

    class e extends RecyclerView.ViewHolder {
        private HealthImageView b;
        private HealthImageView c;
        private HealthTextView d;
        private LinearLayout e;
        private HealthTextView f;
        private HealthTextView g;
        private LinearLayout h;
        private HealthButton i;
        private RelativeLayout j;
        private HealthTextView l;
        private HealthTextView m;
        private HealthTextView n;

        e(View view) {
            super(view);
            this.j = (RelativeLayout) view.findViewById(R.id.recycle_item);
            this.b = (HealthImageView) view.findViewById(R.id.item_picture);
            this.n = (HealthTextView) view.findViewById(R.id.item_title);
            this.l = (HealthTextView) view.findViewById(R.id.item_special_tag);
            this.i = (HealthButton) view.findViewById(R.id.item_right_btn);
            this.m = (HealthTextView) view.findViewById(R.id.item_subtitle);
            this.e = (LinearLayout) view.findViewById(R.id.item_divider);
            this.g = (HealthTextView) view.findViewById(R.id.item_parent_title);
            this.f = (HealthTextView) view.findViewById(R.id.item_join_number);
            this.d = (HealthTextView) view.findViewById(R.id.item_description);
            this.h = (LinearLayout) view.findViewById(R.id.item_labels_layout);
            this.c = (HealthImageView) view.findViewById(R.id.item_audio_tag);
        }
    }
}
