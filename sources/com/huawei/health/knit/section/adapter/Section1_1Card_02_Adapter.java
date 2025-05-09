package com.huawei.health.knit.section.adapter;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.edv;
import defpackage.koq;
import java.util.List;

/* loaded from: classes3.dex */
public class Section1_1Card_02_Adapter extends RecyclerView.Adapter<Section1_1Card_02_ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private Context f2533a;
    private List<Object> b;
    private OnClickSectionListener d;

    public Section1_1Card_02_Adapter(Context context, List<Object> list, OnClickSectionListener onClickSectionListener) {
        this.f2533a = context;
        this.b = list;
        this.d = onClickSectionListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: act_, reason: merged with bridge method [inline-methods] */
    public Section1_1Card_02_ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new Section1_1Card_02_ViewHolder(LayoutInflater.from(this.f2533a).inflate(R.layout.section1_1card_02_recyclerview_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(Section1_1Card_02_ViewHolder section1_1Card_02_ViewHolder, final int i) {
        RecyclerView.LayoutParams c = c(section1_1Card_02_ViewHolder, i);
        if (c == null) {
            LogUtil.b("Section1_1Card_02_Adapter", "params is invalid!");
            return;
        }
        if (section1_1Card_02_ViewHolder.c != null) {
            section1_1Card_02_ViewHolder.c.setLayoutParams(c);
        }
        if (koq.b(this.b, i)) {
            LogUtil.b("Section1_1Card_02_Adapter", "position is out of bounds");
            return;
        }
        List<Object> list = this.b;
        if (list != null && (list.get(i) instanceof edv)) {
            edv edvVar = (edv) this.b.get(i);
            section1_1Card_02_ViewHolder.d.setImageResource(edvVar.c());
            section1_1Card_02_ViewHolder.e.setText(this.f2533a.getString(edvVar.a()));
        }
        section1_1Card_02_ViewHolder.c.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.adapter.Section1_1Card_02_Adapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Section1_1Card_02_Adapter.this.d != null) {
                    Section1_1Card_02_Adapter.this.d.onClick(i);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<Object> list = this.b;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    protected RecyclerView.LayoutParams c(Section1_1Card_02_ViewHolder section1_1Card_02_ViewHolder, int i) {
        ViewGroup.LayoutParams layoutParams = section1_1Card_02_ViewHolder.c.getLayoutParams();
        if (!(layoutParams instanceof RecyclerView.LayoutParams)) {
            LogUtil.b("layoutParams error", new Object[0]);
            return null;
        }
        RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
        int dimensionPixelSize = this.f2533a.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
        int dimensionPixelSize2 = this.f2533a.getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d);
        int dimensionPixelSize3 = this.f2533a.getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
        Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
        if (i == 0) {
            layoutParams2.setMarginStart(dimensionPixelSize + ((Integer) safeRegionWidth.first).intValue());
        } else if (i == getItemCount() - 1 && layoutParams2.getMarginStart() == 0 && layoutParams2.getMarginEnd() == 0) {
            layoutParams2.setMarginStart(dimensionPixelSize3);
            layoutParams2.setMarginEnd(dimensionPixelSize2 + ((Integer) safeRegionWidth.second).intValue());
        } else {
            d(layoutParams2, dimensionPixelSize3);
        }
        return layoutParams2;
    }

    private void d(RecyclerView.LayoutParams layoutParams, int i) {
        if (layoutParams.getMarginStart() == 0) {
            layoutParams.setMarginStart(i);
        }
    }

    public static class Section1_1Card_02_ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout c;
        private ImageView d;
        private HealthTextView e;

        public Section1_1Card_02_ViewHolder(View view) {
            super(view);
            this.c = (LinearLayout) view.findViewById(R.id.section1_1card_02_recyclerview_item_ll);
            this.d = (ImageView) view.findViewById(R.id.section_top_image);
            this.e = (HealthTextView) view.findViewById(R.id.section_bottom_text);
        }
    }
}
