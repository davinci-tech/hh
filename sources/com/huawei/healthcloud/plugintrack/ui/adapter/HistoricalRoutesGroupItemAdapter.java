package com.huawei.healthcloud.plugintrack.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteConstants;
import com.huawei.healthcloud.plugintrack.ui.activity.ClockingRankActivity;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.linearlayout.LineWrappingLinearLayout;
import defpackage.dpg;
import defpackage.enf;
import defpackage.enj;
import defpackage.enm;
import defpackage.ffy;
import defpackage.nrf;
import defpackage.nsn;
import health.compact.a.UnitUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class HistoricalRoutesGroupItemAdapter extends BaseRecyclerAdapter<enj> {
    private Context c;

    public HistoricalRoutesGroupItemAdapter(Context context, List<enj> list, int i) {
        super(list, i);
        this.c = context;
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void convert(RecyclerHolder recyclerHolder, int i, enj enjVar) {
        if (recyclerHolder == null || enjVar == null) {
            LogUtil.h("HistoricalRoutesGroupItemAdapter", "convert holder or itemData is null");
            return;
        }
        enf a2 = enjVar.a();
        recyclerHolder.b(R.id.item_title, a2.h());
        recyclerHolder.b(R.id.item_durationl_first, c(R.plurals._2130903096_res_0x7f030038, R.plurals._2130903095_res_0x7f030037, a2.m()));
        recyclerHolder.b(R.id.item_duration_second, c(R.plurals._2130903406_res_0x7f03016e, R.plurals._2130903407_res_0x7f03016f, a2.e()));
        recyclerHolder.b(R.id.item_duration_third, dpg.j(enjVar.d().longValue()));
        recyclerHolder.cwK_(R.id.flag_line_layout).setVisibility(8);
        b(recyclerHolder);
        c(recyclerHolder, a2);
        if (nsn.ag(this.c)) {
            bdr_(recyclerHolder.cwK_(R.id.recycle_item_layout), i, getItemCount());
        } else {
            bdq_(recyclerHolder.cwK_(R.id.recycle_item_layout), i, getItemCount());
        }
        setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<enj>() { // from class: com.huawei.healthcloud.plugintrack.ui.adapter.HistoricalRoutesGroupItemAdapter.3
            @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter.OnItemClickListener
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onItemClicked(RecyclerHolder recyclerHolder2, int i2, enj enjVar2) {
                LogUtil.a("HistoricalRoutesGroupItemAdapter", "onItemClicked");
                String i3 = enjVar2.a().i();
                int b = enjVar2.a().b();
                Intent intent = new Intent(HistoricalRoutesGroupItemAdapter.this.c, (Class<?>) ClockingRankActivity.class);
                intent.putExtra("PATH_ID", i3);
                intent.putExtra("pathClass", b);
                intent.putExtra("ENTRANCE_ACTIVITY", RunningRouteConstants.BiFromActivity.FROM_HISTORY.getIndex());
                HistoricalRoutesGroupItemAdapter.this.c.startActivity(intent);
            }
        });
    }

    private void b(RecyclerHolder recyclerHolder) {
        ViewGroup viewGroup = (ViewGroup) recyclerHolder.cwK_(R.id.second_line_layout);
        if (viewGroup == null) {
            LogUtil.c("HistoricalRoutesGroupItemAdapter", "layout is null,return");
            return;
        }
        if (viewGroup instanceof LineWrappingLinearLayout) {
            final LineWrappingLinearLayout lineWrappingLinearLayout = (LineWrappingLinearLayout) viewGroup;
            if (lineWrappingLinearLayout.getChildCount() == 2) {
                final View childAt = lineWrappingLinearLayout.getChildAt(0);
                final View childAt2 = lineWrappingLinearLayout.getChildAt(1);
                lineWrappingLinearLayout.post(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.adapter.HistoricalRoutesGroupItemAdapter.5
                    @Override // java.lang.Runnable
                    public void run() {
                        int width = childAt.getWidth();
                        int width2 = childAt2.getWidth();
                        if (width <= 0 || width2 <= 0 || width != width2 || !nsn.t()) {
                            return;
                        }
                        lineWrappingLinearLayout.setOrientation(1);
                        lineWrappingLinearLayout.requestLayout();
                    }
                });
            }
            if (lineWrappingLinearLayout.a()) {
                ViewGroup.LayoutParams layoutParams = lineWrappingLinearLayout.getLayoutParams();
                if (layoutParams == null) {
                    LogUtil.c("HistoricalRoutesGroupItemAdapter", "layout params is null,return");
                } else if (layoutParams instanceof LinearLayout.LayoutParams) {
                    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
                    layoutParams2.setMarginStart(0);
                    lineWrappingLinearLayout.setLayoutParams(layoutParams2);
                }
            }
        }
    }

    private void c(RecyclerHolder recyclerHolder, enf enfVar) {
        ImageView imageView = (ImageView) recyclerHolder.cwK_(R.id.item_picture);
        String str = imageView.getTag() instanceof String ? (String) imageView.getTag() : null;
        enm f = enfVar.f();
        if ((TextUtils.isEmpty(str) || !(str == null || str.equals(f.b()))) && f != null) {
            imageView.setTag(f.b());
            LogUtil.a("HistoricalRoutesGroupItemAdapter", "imageInfo ", f.b());
            recyclerHolder.b(R.id.item_picture, f.b(), nrf.d);
        }
    }

    private void bdr_(View view, int i, int i2) {
        if (view == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof RecyclerView.LayoutParams)) {
            health.compact.a.util.LogUtil.e("layoutParams error", new Object[0]);
            return;
        }
        RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
        layoutParams2.setMargins(this.c.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e), this.c.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e), this.c.getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d), this.c.getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d));
        view.setLayoutParams(layoutParams2);
    }

    private void bdq_(View view, int i, int i2) {
        if (view == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof RecyclerView.LayoutParams)) {
            health.compact.a.util.LogUtil.e("layoutParams error", new Object[0]);
            return;
        }
        RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
        int dimensionPixelSize = this.c.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
        int dimensionPixelSize2 = this.c.getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d);
        int dimensionPixelSize3 = this.c.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
        int dimensionPixelSize4 = this.c.getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d);
        if (i == 0) {
            layoutParams2.setMargins(dimensionPixelSize, dimensionPixelSize3, dimensionPixelSize2, 0);
        } else if (i == i2 - 1) {
            layoutParams2.setMargins(dimensionPixelSize, dimensionPixelSize3, dimensionPixelSize2, dimensionPixelSize4);
        } else {
            layoutParams2.setMargins(dimensionPixelSize, dimensionPixelSize3, dimensionPixelSize2, 0);
        }
        view.setLayoutParams(layoutParams2);
    }

    private String c(int i, int i2, double d) {
        boolean h = UnitUtil.h();
        double d2 = d / 1000.0d;
        double e = UnitUtil.e(d2, 3);
        if (h) {
            d2 = e;
        }
        if (h) {
            i = i2;
        }
        return ffy.b(i, (int) d2, UnitUtil.e(d2, 1, 2));
    }
}
