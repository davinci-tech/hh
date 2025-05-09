package com.huawei.health.marketing.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.ColumRecommendInfo;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleCourseRecommendListStandardContent;
import com.huawei.health.marketing.views.holders.CourseSeriesViewHolder;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.eie;
import defpackage.koq;
import defpackage.nkx;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.util.LogUtil;
import java.util.Collection;

/* loaded from: classes3.dex */
public class CourseSeriesSidingAdapter extends BaseSeriesSidingAdapter<SingleCourseRecommendListStandardContent> {
    @Override // com.huawei.health.marketing.views.BaseSeriesSidingAdapter
    protected int getTitleColorId() {
        return R.color._2131299064_res_0x7f090af8;
    }

    public CourseSeriesSidingAdapter(Context context, int i, ResourceBriefInfo resourceBriefInfo, String str) {
        super(context, i, resourceBriefInfo, str);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new CourseSeriesViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.course_series_layout, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (koq.b((Collection<?>) this.mList, i)) {
            LogUtil.e(this.TAG, "position is error");
            return;
        }
        if (!(viewHolder instanceof CourseSeriesViewHolder)) {
            LogUtil.e(this.TAG, "holder error");
            return;
        }
        CourseSeriesViewHolder courseSeriesViewHolder = (CourseSeriesViewHolder) viewHolder;
        SingleCourseRecommendListStandardContent singleCourseRecommendListStandardContent = (SingleCourseRecommendListStandardContent) this.mList.get(i);
        nsy.cMv_(courseSeriesViewHolder.j(), singleCourseRecommendListStandardContent.isThemeVisibility() ? singleCourseRecommendListStandardContent.getTheme() : "", true);
        courseSeriesViewHolder.j().setTextColor(this.mContext.getResources().getColor(getTitleColorId()));
        nsy.cMv_(courseSeriesViewHolder.h(), singleCourseRecommendListStandardContent.isSubThemeVisibility() ? singleCourseRecommendListStandardContent.getSubTheme() : "", true);
        setBackground(singleCourseRecommendListStandardContent.getBackground(), singleCourseRecommendListStandardContent.getBackgroundColor(), singleCourseRecommendListStandardContent.getDarkBackground(), singleCourseRecommendListStandardContent.getDarkBackgroundColor(), courseSeriesViewHolder.arb_());
        setBackground(singleCourseRecommendListStandardContent.getListBackground(), singleCourseRecommendListStandardContent.getListBackgroundColor(), singleCourseRecommendListStandardContent.getDarkListBackground(), singleCourseRecommendListStandardContent.getDarkListBackgroundColor(), courseSeriesViewHolder.arc_());
        nsy.cMs_(courseSeriesViewHolder.b(), singleCourseRecommendListStandardContent.getButtonDescription(), true);
        if (courseSeriesViewHolder.b() != null && courseSeriesViewHolder.b().getVisibility() == 8) {
            nsy.cMA_(courseSeriesViewHolder.ard_(), 0);
        } else {
            nsy.cMA_(courseSeriesViewHolder.ard_(), 8);
        }
        nsy.cMn_(courseSeriesViewHolder.b(), nkx.cwY_(getViewClickListener(singleCourseRecommendListStandardContent.getButtonLink(), true, (ColumRecommendInfo) singleCourseRecommendListStandardContent), this.mContext, true, ""));
        nsy.cMn_(courseSeriesViewHolder.are_(), nkx.cwY_(getViewClickListener(singleCourseRecommendListStandardContent.getLink(), false, (ColumRecommendInfo) singleCourseRecommendListStandardContent), this.mContext, true, ""));
        e(singleCourseRecommendListStandardContent, courseSeriesViewHolder);
        setRecyclerViewItemLayout(viewHolder, i);
        setBiEventListener(this.mResourceBriefInfo.getResourceName(), singleCourseRecommendListStandardContent.getTheme(), singleCourseRecommendListStandardContent, i, viewHolder.itemView);
    }

    private void e(SingleCourseRecommendListStandardContent singleCourseRecommendListStandardContent, CourseSeriesViewHolder courseSeriesViewHolder) {
        CourseItemSidingAdapter courseItemSidingAdapter = new CourseItemSidingAdapter(this.mContext, singleCourseRecommendListStandardContent, this.mPositionId, getMarketingBiUtil(), this.mResourceBriefInfo);
        HealthRecycleView i = courseSeriesViewHolder.i();
        if (i == null) {
            LogUtil.e(this.TAG, "recycle is null");
            return;
        }
        i.a(false);
        i.d(false);
        if (getItemCount() == 1 && nsn.ag(this.mContext)) {
            i.setLayoutManager(new GridLayoutManager(this.mContext, 2));
        } else {
            i.setLayoutManager(new LinearLayoutManager(this.mContext, 1, false));
        }
        courseItemSidingAdapter.c(getItemCount() == 1);
        i.setAdapter(courseItemSidingAdapter);
    }

    @Override // com.huawei.health.marketing.views.BaseSeriesSidingAdapter
    protected int getItemWidth() {
        if (getItemCount() == 1) {
            return eie.a(this.mContext);
        }
        return nsf.b(R.dimen._2131362983_res_0x7f0a04a7);
    }

    @Override // com.huawei.health.marketing.views.BaseSeriesSidingAdapter
    protected String getLogTag() {
        return "CourseSeriesSidingAdapter";
    }
}
