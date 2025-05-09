package com.huawei.health.knit.section.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.knit.section.view.BaseBiItemView;
import com.huawei.health.search.SearchResultItemView;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.dpf;
import defpackage.eaw;
import defpackage.eet;
import defpackage.koq;
import defpackage.nsy;
import java.util.List;

/* loaded from: classes3.dex */
public class Section21_9List_02Adapter extends RecyclerView.Adapter<c> {

    /* renamed from: a, reason: collision with root package name */
    private OnClickSectionListener f2540a;
    private Context b;
    private String d;
    private List<eaw> e;
    private int h = Integer.MAX_VALUE;
    private int c = -1;

    public Section21_9List_02Adapter(Context context, List<eaw> list) {
        this.b = context;
        this.e = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: acC_, reason: merged with bridge method [inline-methods] */
    public c onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new c(new SearchResultItemView(this.b, R.layout.section21_9list_02_item));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(c cVar, int i) {
        if (koq.b(this.e, i)) {
            LogUtil.a("Section21_9List_02Adapter", "onBindViewHolder position out of bound");
            return;
        }
        eet.ahd_(this.b, cVar.b, i, getItemCount());
        eaw eawVar = this.e.get(i);
        if (!TextUtils.isEmpty(eawVar.b())) {
            eet.b(cVar.c, eawVar.b());
        } else {
            eet.a(cVar.c);
        }
        if (cVar.itemView instanceof BaseBiItemView) {
            ((BaseBiItemView) cVar.itemView).b(eawVar.a());
        }
        nsy.cMr_(cVar.d, dpf.Ym_(eawVar.e(), this.d));
        nsy.cMs_(cVar.f2541a, eawVar.c(), true);
        eet.ahe_(cVar.e, this.f2540a, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.c == -1) {
            int i = this.h;
            List<eaw> list = this.e;
            this.c = Math.min(i, list != null ? list.size() : 0);
        }
        return this.c;
    }

    public void d(OnClickSectionListener onClickSectionListener) {
        this.f2540a = onClickSectionListener;
    }

    public void c(String str) {
        this.d = str;
    }

    static class c extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f2541a;
        private HealthDivider b;
        private HealthImageView c;
        private HealthTextView d;
        private RelativeLayout e;

        c(View view) {
            super(view);
            this.e = (RelativeLayout) view.findViewById(R.id.section21_9list_02_item_content);
            this.c = (HealthImageView) view.findViewById(R.id.section21_9list_02_item_left_image);
            this.d = (HealthTextView) view.findViewById(R.id.section21_9list_02_item_title);
            this.b = (HealthDivider) view.findViewById(R.id.section21_9list_02_item_divider);
            this.f2541a = (HealthTextView) view.findViewById(R.id.section21_9list_02_item_special_tag);
        }
    }
}
