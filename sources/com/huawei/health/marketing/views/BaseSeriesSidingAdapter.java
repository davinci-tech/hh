package com.huawei.health.marketing.views;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.ColumRecommendInfo;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleCourseRecommendListStandardContent;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import defpackage.eih;
import defpackage.eil;
import defpackage.nrf;
import defpackage.nrt;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.util.LogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class BaseSeriesSidingAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected Context mContext;
    private eih mMarketingBiUtil;
    protected int mPositionId;
    protected ResourceBriefInfo mResourceBriefInfo;
    protected String mResourceId;
    protected String TAG = getLogTag();
    protected List<T> mList = new ArrayList();

    protected abstract int getItemWidth();

    protected abstract int getTitleColorId();

    public BaseSeriesSidingAdapter(Context context, int i, ResourceBriefInfo resourceBriefInfo, String str) {
        this.mContext = context;
        this.mPositionId = i;
        this.mResourceBriefInfo = resourceBriefInfo;
        this.mResourceId = resourceBriefInfo.getResourceId();
        this.mMarketingBiUtil = new eih(i, str, this.mResourceBriefInfo);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    public void setDataList(List<T> list, ResourceBriefInfo resourceBriefInfo) {
        this.mList.clear();
        this.mList.addAll(list);
        this.mResourceBriefInfo = resourceBriefInfo;
    }

    protected eih getMarketingBiUtil() {
        return this.mMarketingBiUtil;
    }

    protected void setRecyclerViewItemLayout(RecyclerView.ViewHolder viewHolder, int i) {
        RecyclerView.LayoutParams layoutParams = getLayoutParams(viewHolder, i);
        if (layoutParams == null) {
            LogUtil.e("params is invalid!", new Object[0]);
        } else {
            viewHolder.itemView.setLayoutParams(layoutParams);
        }
    }

    private RecyclerView.LayoutParams getLayoutParams(RecyclerView.ViewHolder viewHolder, int i) {
        ViewGroup.LayoutParams layoutParams = viewHolder.itemView.getLayoutParams();
        if (!(layoutParams instanceof RecyclerView.LayoutParams)) {
            LogUtil.e("layoutParams error", new Object[0]);
            return null;
        }
        RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
        layoutParams2.width = getItemWidth();
        int d2 = nsn.d(this.mContext, 0, true);
        int c = nsn.c(this.mContext, 1, true);
        int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
        if (i == 0) {
            layoutParams2.setMarginStart(d2);
        }
        if (i == getItemCount() - 1) {
            if (i != 0) {
                d2 = dimensionPixelSize;
            }
            layoutParams2.setMarginStart(d2);
            layoutParams2.setMarginEnd(c);
        }
        if (i > 0 && i < getItemCount() - 1) {
            layoutParams2.setMarginStart(dimensionPixelSize);
        }
        return layoutParams2;
    }

    protected void setBiEventListener(String str, String str2, ColumRecommendInfo columRecommendInfo, int i, View view) {
        this.mMarketingBiUtil.alO_(str, str2, columRecommendInfo, i, view);
    }

    protected View.OnClickListener getViewClickListener(String str, String str2, int i) {
        return new d((BaseSeriesSidingAdapter) this, str, false, str2, i);
    }

    protected View.OnClickListener getViewClickListener(String str, boolean z, ColumRecommendInfo columRecommendInfo) {
        return new d(this, str, z, columRecommendInfo, 0);
    }

    static class d implements View.OnClickListener {

        /* renamed from: a, reason: collision with root package name */
        private final String f2823a;
        private final boolean b;
        private final int c;
        private final WeakReference<BaseSeriesSidingAdapter> d;
        private final String e;
        private final ColumRecommendInfo f;

        public d(BaseSeriesSidingAdapter baseSeriesSidingAdapter, String str, boolean z, String str2, int i) {
            this.d = new WeakReference<>(baseSeriesSidingAdapter);
            this.e = str;
            this.b = z;
            this.f2823a = str2;
            this.f = null;
            this.c = i;
        }

        public d(BaseSeriesSidingAdapter baseSeriesSidingAdapter, String str, boolean z, ColumRecommendInfo columRecommendInfo, int i) {
            this.d = new WeakReference<>(baseSeriesSidingAdapter);
            this.e = str;
            this.b = z;
            this.f = columRecommendInfo;
            this.f2823a = null;
            this.c = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (TextUtils.isEmpty(this.e) || nsn.o()) {
                LogUtil.c("BaseSeriesSidingAdapter_ClickListener", "linkValue is error, linkValue: ", this.e);
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            BaseSeriesSidingAdapter baseSeriesSidingAdapter = this.d.get();
            if (baseSeriesSidingAdapter == null) {
                LogUtil.c("BaseSeriesSidingAdapter_ClickListener", "adapter is null when click.");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            eil.c(this.e, baseSeriesSidingAdapter.mPositionId, baseSeriesSidingAdapter.mResourceBriefInfo, this.c + 1);
            if (baseSeriesSidingAdapter.mMarketingBiUtil == null) {
                LogUtil.c("BaseSeriesSidingAdapter_ClickListener", "mMarketingBiUtil is null in ClickListener. Fail to setBiEvent.");
                ViewClickInstrumentation.clickOnView(view);
            } else if (this.f instanceof SingleCourseRecommendListStandardContent) {
                baseSeriesSidingAdapter.mMarketingBiUtil.a(2, this.b ? "e2" : "e1", (SingleCourseRecommendListStandardContent) this.f, this.c);
                ViewClickInstrumentation.clickOnView(view);
            } else {
                if (this.b) {
                    baseSeriesSidingAdapter.mMarketingBiUtil.c(this.f2823a);
                } else {
                    baseSeriesSidingAdapter.mMarketingBiUtil.d(2, "", this.f2823a, this.c, null);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }
    }

    private String setUrlPositionId(String str, int i) {
        if (str.contains("?")) {
            return str + "&pullfrom=" + i;
        }
        return str + "?pullfrom=" + i;
    }

    protected void setBackground(String str, String str2, String str3, String str4, ImageView imageView) {
        if (nrt.a(this.mContext)) {
            setBackgroundInner(str3, str4, imageView);
        } else {
            setBackgroundInner(str, str2, imageView);
        }
    }

    private void setBackgroundInner(String str, String str2, ImageView imageView) {
        if (!TextUtils.isEmpty(str)) {
            nrf.cIS_(imageView, str, 0, 0, R.drawable._2131431376_res_0x7f0b0fd0);
        } else {
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            try {
                nsy.cMk_(imageView, Color.parseColor(str2));
            } catch (IllegalArgumentException unused) {
                LogUtil.e(this.TAG, "IllegalArgumentException");
            }
        }
    }

    protected String getLogTag() {
        return "BaseSeriesSidingAdapter";
    }
}
