package com.huawei.health.marketing.views;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.CrossedCardOneCardContent;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.views.holders.DietAnalysisSeriesViewHolder;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.base.BaseActivity;
import defpackage.koq;
import defpackage.nkx;
import defpackage.nrf;
import defpackage.nsf;
import defpackage.nsy;
import health.compact.a.util.LogUtil;
import java.util.Collection;

/* loaded from: classes3.dex */
public class DietAnalysisSeriesSidingAdapter extends BaseSeriesSidingAdapter<CrossedCardOneCardContent> {
    private static final int c = BaseApplication.getContext().getResources().getDimensionPixelOffset(R.dimen._2131362922_res_0x7f0a046a);

    /* renamed from: a, reason: collision with root package name */
    private ResourceBriefInfo f2865a;

    @Override // com.huawei.health.marketing.views.BaseSeriesSidingAdapter
    protected int getTitleColorId() {
        return R.color._2131299236_res_0x7f090ba4;
    }

    public DietAnalysisSeriesSidingAdapter(Context context, int i, String str, ResourceBriefInfo resourceBriefInfo) {
        super(context, i, resourceBriefInfo, str);
        this.f2865a = resourceBriefInfo;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new DietAnalysisSeriesViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.diet_analysis_series_layout, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (koq.b((Collection<?>) this.mList, i)) {
            LogUtil.e(this.TAG, "position is error");
            return;
        }
        if (!(viewHolder instanceof DietAnalysisSeriesViewHolder)) {
            LogUtil.e(this.TAG, "holder error");
            return;
        }
        DietAnalysisSeriesViewHolder dietAnalysisSeriesViewHolder = (DietAnalysisSeriesViewHolder) viewHolder;
        CrossedCardOneCardContent crossedCardOneCardContent = (CrossedCardOneCardContent) this.mList.get(i);
        nsy.cMv_(dietAnalysisSeriesViewHolder.c(), crossedCardOneCardContent.isThemeVisibility() ? crossedCardOneCardContent.getTheme() : "", true);
        dietAnalysisSeriesViewHolder.c().setTextColor(this.mContext.getResources().getColor(getTitleColorId()));
        nsy.cMv_(dietAnalysisSeriesViewHolder.e(), crossedCardOneCardContent.isSubThemeVisibility() ? crossedCardOneCardContent.getSubTheme() : "", true);
        setBackground(crossedCardOneCardContent.getBackgroundPicture(), crossedCardOneCardContent.getBackgroundColor(), crossedCardOneCardContent.getDarkBackgroundPicture(), crossedCardOneCardContent.getDarkBackgroundColor(), dietAnalysisSeriesViewHolder.arf_());
        String picture = crossedCardOneCardContent.getPicture();
        if (!TextUtils.isEmpty(picture)) {
            nrf.c(dietAnalysisSeriesViewHolder.b(), picture, c, 0, R.drawable._2131431376_res_0x7f0b0fd0);
        } else {
            nrf.a(R.drawable._2131431376_res_0x7f0b0fd0, dietAnalysisSeriesViewHolder.b(), c);
        }
        if (this.mContext instanceof BaseActivity) {
            nsy.cMn_(dietAnalysisSeriesViewHolder.arg_(), nkx.cwZ_(super.getViewClickListener(a(crossedCardOneCardContent.getLinkValue(), this.f2865a.getResourceName(), this.f2865a.getResourceId(), i + 1), crossedCardOneCardContent.getTheme(), i), (BaseActivity) this.mContext, true, ""));
        }
        setRecyclerViewItemLayout(viewHolder, i);
        setBiEventListener(crossedCardOneCardContent.getTheme(), "", null, 0, dietAnalysisSeriesViewHolder.b());
    }

    private String a(String str, String str2, String str3, int i) {
        return str + "&resourceName=" + str2 + "&resourceId=" + str3 + "&pullOrder=" + i;
    }

    @Override // com.huawei.health.marketing.views.BaseSeriesSidingAdapter
    protected int getItemWidth() {
        if (getItemCount() > 1) {
            return nsf.b(R.dimen._2131362983_res_0x7f0a04a7);
        }
        return -1;
    }

    @Override // com.huawei.health.marketing.views.BaseSeriesSidingAdapter
    protected String getLogTag() {
        return "DietAnalysisSeriesSidingAdapter";
    }
}
