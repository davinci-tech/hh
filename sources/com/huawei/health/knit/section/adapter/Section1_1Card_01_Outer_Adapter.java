package com.huawei.health.knit.section.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.utils.AsyncLoadDrawableCallback;
import defpackage.ear;
import defpackage.eav;
import defpackage.nrf;
import health.compact.a.util.LogUtil;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class Section1_1Card_01_Outer_Adapter extends RecyclerView.Adapter<ListThreeOuterViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private List<Object> f2530a;
    private Context b;
    private OnClickSectionListener c;
    private List<Object> d;
    private List<Object> e;
    private List<Object> g;
    private List<Map<Object, Object>> i;

    public Section1_1Card_01_Outer_Adapter(Context context) {
        this.b = context;
    }

    public void e(eav eavVar) {
        if (eavVar != null) {
            this.d = eavVar.d();
            this.g = eavVar.a();
            this.e = eavVar.b();
            this.i = eavVar.j();
            this.f2530a = eavVar.c();
            this.c = eavVar.e();
        }
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: acq_, reason: merged with bridge method [inline-methods] */
    public ListThreeOuterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ListThreeOuterViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.section1_1card_outer_recyclerview_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(ListThreeOuterViewHolder listThreeOuterViewHolder, final int i) {
        List<Object> list;
        List<Object> list2 = this.d;
        if (list2 != null && i < list2.size() && listThreeOuterViewHolder.c != null && listThreeOuterViewHolder.b != null) {
            c(listThreeOuterViewHolder, i);
        }
        List<Object> list3 = this.g;
        if (list3 != null && i < list3.size() && listThreeOuterViewHolder.j != null && (this.g.get(i) instanceof String)) {
            listThreeOuterViewHolder.j.setText(String.valueOf(this.g.get(i)));
        }
        List<Object> list4 = this.e;
        if (list4 != null && i < list4.size() && listThreeOuterViewHolder.f2532a != null && (this.e.get(i) instanceof String)) {
            listThreeOuterViewHolder.f2532a.setText(String.valueOf(this.e.get(i)));
        }
        if (listThreeOuterViewHolder.e != null && (list = this.f2530a) != null && i < list.size() && this.c != null && (this.f2530a.get(i) instanceof String)) {
            listThreeOuterViewHolder.e.setText(String.valueOf(this.f2530a.get(i)));
            listThreeOuterViewHolder.e.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.adapter.Section1_1Card_01_Outer_Adapter.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    Section1_1Card_01_Outer_Adapter.this.c.onClick(i, "SHOW_MORE_CLICK_EVENT");
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        c(i, listThreeOuterViewHolder);
        a(listThreeOuterViewHolder, i);
    }

    private void c(final ListThreeOuterViewHolder listThreeOuterViewHolder, int i) {
        if (this.d.get(i) instanceof String) {
            String str = (String) this.d.get(i);
            if (!TextUtils.isEmpty(str)) {
                nrf.cIk_(str, listThreeOuterViewHolder.c, R.drawable._2131431376_res_0x7f0b0fd0, new AsyncLoadDrawableCallback() { // from class: com.huawei.health.knit.section.adapter.Section1_1Card_01_Outer_Adapter.3
                    @Override // com.huawei.ui.commonui.utils.AsyncLoadDrawableCallback
                    public void getDrawable(final Drawable drawable) {
                        nrf.cHF_(drawable);
                        HandlerExecutor.a(new Runnable() { // from class: com.huawei.health.knit.section.adapter.Section1_1Card_01_Outer_Adapter.3.4
                            @Override // java.lang.Runnable
                            public void run() {
                                listThreeOuterViewHolder.b.setBackground(drawable);
                                listThreeOuterViewHolder.c.setVisibility(8);
                            }
                        });
                    }
                });
            } else {
                listThreeOuterViewHolder.c.setVisibility(8);
            }
        }
        if (this.d.get(i) instanceof Integer) {
            listThreeOuterViewHolder.c.setVisibility(8);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<Object> list = this.d;
        int size = list != null ? list.size() : 0;
        List<Object> list2 = this.g;
        int size2 = list2 != null ? list2.size() : 0;
        List<Object> list3 = this.e;
        int size3 = list3 != null ? list3.size() : 0;
        List<Map<Object, Object>> list4 = this.i;
        int size4 = list4 != null ? list4.size() : 0;
        List<Object> list5 = this.f2530a;
        return Math.max(size, Math.max(size2, Math.max(size3, Math.max(size4, list5 != null ? list5.size() : 0))));
    }

    private void a(ListThreeOuterViewHolder listThreeOuterViewHolder, int i) {
        RecyclerView.LayoutParams e = e(listThreeOuterViewHolder, i);
        if (e == null) {
            LogUtil.e("params is invalid!", new Object[0]);
        } else if (listThreeOuterViewHolder.b != null) {
            listThreeOuterViewHolder.b.setLayoutParams(e);
        }
    }

    protected RecyclerView.LayoutParams e(ListThreeOuterViewHolder listThreeOuterViewHolder, int i) {
        ViewGroup.LayoutParams layoutParams = listThreeOuterViewHolder.b.getLayoutParams();
        if (!(layoutParams instanceof RecyclerView.LayoutParams)) {
            LogUtil.e("layoutParams error", new Object[0]);
            return null;
        }
        RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
        if (getItemCount() > 1) {
            layoutParams2.width = this.b.getResources().getDimensionPixelSize(R.dimen._2131363005_res_0x7f0a04bd);
        }
        int dimensionPixelSize = this.b.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
        int dimensionPixelSize2 = this.b.getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d);
        int dimensionPixelSize3 = this.b.getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
        if (i == 0) {
            layoutParams2.setMarginStart(dimensionPixelSize);
        }
        if (i == getItemCount() - 1) {
            if (i != 0) {
                dimensionPixelSize = dimensionPixelSize3;
            }
            layoutParams2.setMarginStart(dimensionPixelSize);
            layoutParams2.setMarginEnd(dimensionPixelSize2);
        }
        if (i > 0 && i < getItemCount() - 1) {
            layoutParams2.setMarginStart(dimensionPixelSize3);
        }
        return layoutParams2;
    }

    public static class ListThreeOuterViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f2532a;
        private FrameLayout b;
        private ImageView c;
        private HealthRecycleView d;
        private HealthButton e;
        private HealthTextView j;

        public ListThreeOuterViewHolder(View view) {
            super(view);
            this.b = (FrameLayout) view.findViewById(R.id.course_item_tahiti);
            this.c = (ImageView) view.findViewById(R.id.course_item_tahiti_background);
            this.j = (HealthTextView) view.findViewById(R.id.series_course_tahiti_title);
            this.f2532a = (HealthTextView) view.findViewById(R.id.series_course_tahiti_subtitle);
            this.d = (HealthRecycleView) view.findViewById(R.id.course_item_recyclerview);
            this.e = (HealthButton) view.findViewById(R.id.bt_show_more);
        }
    }

    private void c(int i, ListThreeOuterViewHolder listThreeOuterViewHolder) {
        List<Map<Object, Object>> list = this.i;
        if (list == null || i >= list.size() || this.i.get(i) == null) {
            return;
        }
        Object obj = this.i.get(i).get("IMAGE");
        Object obj2 = this.i.get(i).get("NAME");
        Object obj3 = this.i.get(i).get("DIFFICULTY");
        Object obj4 = this.i.get(i).get("TIME");
        if ((obj instanceof List) && (obj2 instanceof List) && (obj3 instanceof List) && (obj4 instanceof List)) {
            ear earVar = new ear((List) obj, (List) obj2, (List) obj3, (List) obj4, i);
            Section1_1Card_01_Inner_Adapter section1_1Card_01_Inner_Adapter = new Section1_1Card_01_Inner_Adapter(this.b, this.c);
            section1_1Card_01_Inner_Adapter.b(earVar);
            if (listThreeOuterViewHolder.d != null) {
                listThreeOuterViewHolder.d.a(false);
                listThreeOuterViewHolder.d.d(false);
                listThreeOuterViewHolder.d.setAdapter(section1_1Card_01_Inner_Adapter);
                listThreeOuterViewHolder.d.setLayoutManager(new LinearLayoutManager(this.b, 1, false));
            }
        }
    }
}
