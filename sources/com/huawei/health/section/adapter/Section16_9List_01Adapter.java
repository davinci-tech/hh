package com.huawei.health.section.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.search.SearchResultItemView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import defpackage.eet;
import defpackage.eie;
import defpackage.fbn;
import defpackage.koq;
import java.util.List;

/* loaded from: classes3.dex */
public class Section16_9List_01Adapter extends RecyclerView.Adapter<d> {

    /* renamed from: a, reason: collision with root package name */
    private fbn f2964a;
    private Context b;
    private int d = -1;

    public Section16_9List_01Adapter(Context context, fbn fbnVar) {
        this.b = context;
        this.f2964a = fbnVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: avD_, reason: merged with bridge method [inline-methods] */
    public d onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new d(new SearchResultItemView(this.b, i != 6 ? i != 1008 ? i != 100 ? i != 101 ? R.layout.item_marketing_grid_small : R.layout.section_member_coupon : R.layout.section_sport_plan : R.layout.item_activity_search_grid : R.layout.item_marketing_one_grid_big), i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(d dVar, int i) {
        if (dVar.f2965a != null && dVar.e != null) {
            a(dVar);
        }
        eet.ahc_(dVar.itemView, this.f2964a.d(), i);
        eet.e(dVar.f2965a, this.f2964a.a(), i);
        eet.ahf_(dVar.h, this.f2964a.n(), this.f2964a.b(), i, true);
        eet.ahf_(dVar.c, this.f2964a.e(), this.f2964a.b(), i, true);
        eet.ahf_(dVar.d, this.f2964a.i(), null, i, false);
        eet.ahf_(dVar.i, this.f2964a.j(), null, i, false);
        eet.ahf_(dVar.g, this.f2964a.g(), null, i, false);
        eet.ahb_(dVar.g, this.f2964a.f(), i);
        eet.ahe_(dVar.j, this.f2964a.h(), i);
    }

    private void a(d dVar) {
        int i = dVar.f;
        if (i == 6) {
            e(dVar);
        } else if (i == 15 || i == 1008) {
            c(dVar);
        }
    }

    private void e(d dVar) {
        int e = eie.e(this.b, 6, getItemCount());
        int i = (e * 9) / 16;
        ViewGroup.LayoutParams layoutParams = dVar.f2965a.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.width = e;
            layoutParams2.height = i;
            dVar.f2965a.setLayoutParams(layoutParams2);
        }
        ViewGroup.LayoutParams layoutParams3 = dVar.e.getLayoutParams();
        if (layoutParams3 instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) layoutParams3;
            layoutParams4.height = i;
            layoutParams4.width = e;
            dVar.e.setLayoutParams(layoutParams4);
        }
    }

    private void c(d dVar) {
        int e = eie.e(this.b, 15, getItemCount());
        int i = (e * 9) / 21;
        ViewGroup.LayoutParams layoutParams = dVar.f2965a.getLayoutParams();
        layoutParams.width = e;
        layoutParams.height = i;
        dVar.f2965a.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams layoutParams2 = dVar.e.getLayoutParams();
        layoutParams2.height = i;
        layoutParams2.width = e;
        dVar.e.setLayoutParams(layoutParams2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.d == -1) {
            int d2 = eet.d(this.f2964a.n(), this.f2964a.e(), this.f2964a.a(), this.f2964a.i(), this.f2964a.i(), this.f2964a.j(), this.f2964a.g(), this.f2964a.f());
            this.d = d2;
            this.d = Math.min(d2, this.f2964a.c());
        }
        return this.d;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        List<Integer> l = this.f2964a.l();
        if (!koq.d(l, i) || l.get(i) == null) {
            return 15;
        }
        return l.get(i).intValue();
    }

    class d extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthImageView f2965a;
        private HealthTextView c;
        private HealthTextView d;
        private RelativeLayout e;
        private int f;
        private HealthTextView g;
        private HealthTextView h;
        private HealthTextView i;
        private ViewGroup j;

        d(View view, int i) {
            super(view);
            this.f = i;
            if (i == 6) {
                c();
            } else if (i == 100) {
                b();
            } else {
                d();
            }
        }

        private void c() {
            this.j = (ViewGroup) this.itemView.findViewById(R.id.item_one_grid_big_root_layout);
            this.e = (RelativeLayout) this.itemView.findViewById(R.id.item_one_grid_big_image_layout);
            this.f2965a = (HealthImageView) this.itemView.findViewById(R.id.item_one_grid_big_image);
            this.h = (HealthTextView) this.itemView.findViewById(R.id.item_one_grid_big_title);
            this.c = (HealthTextView) this.itemView.findViewById(R.id.item_one_grid_big_description);
            this.d = (HealthTextView) this.itemView.findViewById(R.id.item_one_grid_big_attribute_text);
            this.i = (HealthTextView) this.itemView.findViewById(R.id.item_one_grid_big_join_num);
            this.g = (HealthTextView) this.itemView.findViewById(R.id.item_one_grid_big_status);
        }

        private void d() {
            this.j = (ViewGroup) this.itemView.findViewById(R.id.item_one_grid_small_root_layout);
            this.e = (RelativeLayout) this.itemView.findViewById(R.id.item_one_grid_small_root_layout);
            this.f2965a = (HealthImageView) this.itemView.findViewById(R.id.item_one_grid_small_image);
            this.h = (HealthTextView) this.itemView.findViewById(R.id.item_one_grid_small_title);
            this.c = (HealthTextView) this.itemView.findViewById(R.id.item_one_grid_small_description);
            this.i = (HealthTextView) this.itemView.findViewById(R.id.item_one_grid_small_join_num);
            this.g = (HealthTextView) this.itemView.findViewById(R.id.item_one_grid_small_status);
        }

        private void b() {
            this.j = (ViewGroup) this.itemView.findViewById(R.id.section_plan_item);
            this.f2965a = (HealthImageView) this.itemView.findViewById(R.id.section_plan_bg);
            this.h = (HealthTextView) this.itemView.findViewById(R.id.section_plan_name);
            this.c = (HealthTextView) this.itemView.findViewById(R.id.section_plan_description);
            this.g = (HealthTextView) this.itemView.findViewById(R.id.section_plan_corner);
            this.e = null;
            this.d = null;
            this.i = null;
        }
    }
}
