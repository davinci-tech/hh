package com.huawei.health.knit.section.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.ear;
import defpackage.nrf;
import health.compact.a.util.LogUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class Section1_1Card_01_Inner_Adapter extends RecyclerView.Adapter<ListThreeInnerViewHolder> {
    private static final int d = (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131363122_res_0x7f0a0532);

    /* renamed from: a, reason: collision with root package name */
    private List<Object> f2528a;
    private List<Object> b;
    private List<Object> c;
    private List<Object> e;
    private int f;
    private OnClickSectionListener h;
    private Context i;

    public Section1_1Card_01_Inner_Adapter(Context context) {
        this.i = context;
    }

    public Section1_1Card_01_Inner_Adapter(Context context, OnClickSectionListener onClickSectionListener) {
        this.i = context;
        this.h = onClickSectionListener;
    }

    public void b(ear earVar) {
        if (earVar != null) {
            this.b = earVar.a();
            this.c = earVar.e();
            this.f2528a = earVar.b();
            this.e = earVar.d();
            this.f = earVar.c();
            notifyDataSetChanged();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: acn_, reason: merged with bridge method [inline-methods] */
    public ListThreeInnerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ListThreeInnerViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.section1_1card_inner_recyclerview_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(ListThreeInnerViewHolder listThreeInnerViewHolder, final int i) {
        if (listThreeInnerViewHolder.g != null && this.h != null) {
            listThreeInnerViewHolder.g.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.adapter.Section1_1Card_01_Inner_Adapter.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    Section1_1Card_01_Inner_Adapter.this.h.onClick(Section1_1Card_01_Inner_Adapter.this.f, i);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        c(listThreeInnerViewHolder, i);
        a(listThreeInnerViewHolder, i);
        d(listThreeInnerViewHolder, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<Object> list = this.b;
        int size = list != null ? list.size() : 0;
        List<Object> list2 = this.c;
        int size2 = list2 != null ? list2.size() : 0;
        List<Object> list3 = this.f2528a;
        int size3 = list3 != null ? list3.size() : 0;
        List<Object> list4 = this.e;
        return Math.max(size, Math.max(size2, Math.max(size3, Math.max(list4 != null ? list4.size() : 0, 2))));
    }

    private void d(ListThreeInnerViewHolder listThreeInnerViewHolder, int i) {
        RecyclerView.LayoutParams b = b(listThreeInnerViewHolder, i);
        if (b == null) {
            LogUtil.e("params is invalid!", new Object[0]);
        } else if (listThreeInnerViewHolder.b != null) {
            listThreeInnerViewHolder.b.setLayoutParams(b);
        }
    }

    protected RecyclerView.LayoutParams b(ListThreeInnerViewHolder listThreeInnerViewHolder, int i) {
        ViewGroup.LayoutParams layoutParams = listThreeInnerViewHolder.b.getLayoutParams();
        if (!(layoutParams instanceof RecyclerView.LayoutParams)) {
            LogUtil.e("layoutParams error", new Object[0]);
            return null;
        }
        RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
        int dimensionPixelSize = this.i.getResources().getDimensionPixelSize(R.dimen._2131362565_res_0x7f0a0305);
        this.i.getResources().getDimensionPixelSize(R.dimen._2131362565_res_0x7f0a0305);
        if (i == 0) {
            layoutParams2.topMargin = dimensionPixelSize;
        }
        return layoutParams2;
    }

    public static class ListThreeInnerViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f2529a;
        private LinearLayout b;
        private HealthTextView c;
        private HealthTextView d;
        private HealthImageView e;
        private RelativeLayout g;
        private HealthDivider h;

        public ListThreeInnerViewHolder(View view) {
            super(view);
            this.g = (RelativeLayout) view.findViewById(R.id.inner_recyclerview_item);
            this.e = (HealthImageView) view.findViewById(R.id.section_inner_image);
            this.c = (HealthTextView) view.findViewById(R.id.section_inner_title);
            this.f2529a = (HealthTextView) view.findViewById(R.id.section_item_left_text);
            this.d = (HealthTextView) view.findViewById(R.id.section_item_right_text);
            this.h = (HealthDivider) view.findViewById(R.id.recyclerview_divider);
            this.b = (LinearLayout) view.findViewById(R.id.section1_1card_inner_item_ll);
        }
    }

    private void a(ListThreeInnerViewHolder listThreeInnerViewHolder, int i) {
        List<Object> list;
        List<Object> list2;
        List<Object> list3;
        if (listThreeInnerViewHolder.c != null && (list3 = this.c) != null && i < list3.size() && (this.c.get(i) instanceof String)) {
            listThreeInnerViewHolder.c.setText(String.valueOf(this.c.get(i)));
        }
        if (listThreeInnerViewHolder.f2529a != null && (list2 = this.f2528a) != null && i < list2.size() && (this.f2528a.get(i) instanceof String)) {
            listThreeInnerViewHolder.f2529a.setText(String.valueOf(this.f2528a.get(i)));
        }
        if (listThreeInnerViewHolder.d != null && (list = this.e) != null && i < list.size() && (this.e.get(i) instanceof String)) {
            listThreeInnerViewHolder.d.setText(String.valueOf(this.e.get(i)));
        }
        if (i != getItemCount() - 1 || listThreeInnerViewHolder.h == null) {
            return;
        }
        listThreeInnerViewHolder.h.setVisibility(8);
    }

    private void c(ListThreeInnerViewHolder listThreeInnerViewHolder, int i) {
        List<Object> list;
        if (listThreeInnerViewHolder.e == null || (list = this.b) == null || i >= list.size()) {
            return;
        }
        Object obj = this.b.get(i);
        if (obj instanceof Integer) {
            listThreeInnerViewHolder.e.setBackgroundResource(((Integer) obj).intValue());
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (!TextUtils.isEmpty(str)) {
                nrf.c(listThreeInnerViewHolder.e, str, d, 0, R.drawable._2131431376_res_0x7f0b0fd0);
            } else {
                nrf.a(R.drawable._2131431376_res_0x7f0b0fd0, listThreeInnerViewHolder.e, d);
            }
        }
    }
}
