package com.huawei.health.marketing.views;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleCourseRecommendListContent;
import com.huawei.health.marketing.datatype.SingleCourseRecommendListStandardContent;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import defpackage.eih;
import defpackage.eil;
import defpackage.ekx;
import defpackage.koq;
import defpackage.nkx;
import defpackage.nrf;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class CourseItemSidingAdapter extends RecyclerView.Adapter<CourseItemViewHolder> {
    private static final int e = BaseApplication.getContext().getResources().getDimensionPixelOffset(R.dimen._2131363122_res_0x7f0a0532);

    /* renamed from: a, reason: collision with root package name */
    private boolean f2856a;
    private List<SingleCourseRecommendListContent> b = new ArrayList();
    private Context c;
    private eih d;
    private ResourceBriefInfo f;
    private String g;
    private int h;
    private SingleCourseRecommendListStandardContent i;
    private String j;

    public CourseItemSidingAdapter(Context context, SingleCourseRecommendListStandardContent singleCourseRecommendListStandardContent, int i, eih eihVar, ResourceBriefInfo resourceBriefInfo) {
        this.c = context;
        this.h = i;
        if (resourceBriefInfo != null) {
            this.g = resourceBriefInfo.getResourceId();
        }
        this.f = resourceBriefInfo;
        this.d = eihVar;
        if (singleCourseRecommendListStandardContent == null) {
            return;
        }
        this.i = singleCourseRecommendListStandardContent;
        this.j = singleCourseRecommendListStandardContent.getTheme();
        this.b.clear();
        List<SingleCourseRecommendListContent> subContents = singleCourseRecommendListStandardContent.getSubContents();
        if (koq.b(subContents)) {
            LogUtil.b("CourseItemSidingAdapter", "subContents is null");
        } else {
            this.b.addAll(subContents);
        }
    }

    public void c(boolean z) {
        this.f2856a = z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: aoX_, reason: merged with bridge method [inline-methods] */
    public CourseItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new CourseItemViewHolder(LayoutInflater.from(this.c).inflate(R.layout.course_item_layout, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(CourseItemViewHolder courseItemViewHolder, int i) {
        if (koq.b(this.b, i)) {
            LogUtil.b("CourseItemSidingAdapter", "position error");
            return;
        }
        SingleCourseRecommendListContent singleCourseRecommendListContent = this.b.get(i);
        nsy.cMn_(courseItemViewHolder.h, nkx.cwY_(new b(this, singleCourseRecommendListContent, i, this.g), this.c, true, ""));
        String picture = singleCourseRecommendListContent.getPicture();
        if (!TextUtils.isEmpty(picture)) {
            nrf.c(courseItemViewHolder.f2858a, picture, e, 0, R.drawable._2131431376_res_0x7f0b0fd0);
        } else {
            nrf.a(R.drawable._2131431376_res_0x7f0b0fd0, courseItemViewHolder.f2858a, e);
        }
        ViewGroup.LayoutParams layoutParams = courseItemViewHolder.f2858a.getLayoutParams();
        ViewGroup.LayoutParams layoutParams2 = courseItemViewHolder.f.getLayoutParams();
        LinearLayout.LayoutParams layoutParams3 = layoutParams2 instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams2 : null;
        if (this.f2856a) {
            layoutParams.width = nsf.b(R.dimen._2131362863_res_0x7f0a042f);
            if (layoutParams3 != null) {
                layoutParams3.setMarginStart(nsf.b(R.dimen._2131362883_res_0x7f0a0443));
            }
            if (nsn.ag(this.c) && (i == getItemCount() - 1 || i == getItemCount() - 2)) {
                nsy.cMA_(courseItemViewHolder.f, 8);
            }
        } else {
            layoutParams.width = nsf.b(R.dimen._2131363076_res_0x7f0a0504);
            if (layoutParams3 != null) {
                layoutParams3.setMarginStart(nsf.b(R.dimen._2131363112_res_0x7f0a0528));
            }
        }
        if (i == getItemCount() - 1) {
            nsy.cMA_(courseItemViewHolder.f, 8);
        }
        layoutParams.height = nsf.b(R.dimen._2131363076_res_0x7f0a0504);
        courseItemViewHolder.f2858a.setLayoutParams(layoutParams);
        nsy.cMr_(courseItemViewHolder.c, singleCourseRecommendListContent.getTheme());
        nsy.cMr_(courseItemViewHolder.b, singleCourseRecommendListContent.getSubTheme());
        if (!TextUtils.isEmpty(singleCourseRecommendListContent.getLabel())) {
            courseItemViewHolder.d.setTextColor(BaseApplication.getContext().getResources().getColor(R.color._2131299094_res_0x7f090b16));
            courseItemViewHolder.d.setBackground(BaseApplication.getContext().getResources().getDrawable(R.drawable.sleep_course_membership_tag_background));
            int c = nsn.c(BaseApplication.getContext(), 4.0f);
            int c2 = nsn.c(BaseApplication.getContext(), 1.0f);
            courseItemViewHolder.d.setPadding(c, c2, c, c2);
            courseItemViewHolder.d.setText(singleCourseRecommendListContent.getLabel());
            courseItemViewHolder.d.setVisibility(0);
        } else {
            courseItemViewHolder.d.setVisibility(8);
        }
        if (a(singleCourseRecommendListContent)) {
            a(courseItemViewHolder, singleCourseRecommendListContent, i);
            d(courseItemViewHolder, false);
        } else {
            courseItemViewHolder.i.setVisibility(8);
        }
        aoV_(courseItemViewHolder.itemView, this.i, singleCourseRecommendListContent, i);
    }

    private void aoV_(final View view, final SingleCourseRecommendListStandardContent singleCourseRecommendListStandardContent, final SingleCourseRecommendListContent singleCourseRecommendListContent, final int i) {
        HandlerExecutor.d(new Runnable() { // from class: com.huawei.health.marketing.views.CourseItemSidingAdapter.1
            @Override // java.lang.Runnable
            public void run() {
                CourseItemSidingAdapter.this.aoW_(view, singleCourseRecommendListStandardContent, singleCourseRecommendListContent, i);
            }
        }, 200L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aoW_(View view, SingleCourseRecommendListStandardContent singleCourseRecommendListStandardContent, SingleCourseRecommendListContent singleCourseRecommendListContent, int i) {
        if (view == null || this.d.alN_(singleCourseRecommendListStandardContent.getTheme(), singleCourseRecommendListContent.getItemId(), view)) {
            return;
        }
        nsy.cMa_(view, new a(view, singleCourseRecommendListStandardContent, singleCourseRecommendListContent, i));
        nsy.cMb_(view, new a(view, singleCourseRecommendListStandardContent, singleCourseRecommendListContent, i));
    }

    private void a(CourseItemViewHolder courseItemViewHolder, SingleCourseRecommendListContent singleCourseRecommendListContent, int i) {
        courseItemViewHolder.i.setVisibility(0);
        SingleGridContent singleGridContent = new SingleGridContent(new SingleGridContent.Builder().setDynamicDataId(singleCourseRecommendListContent.getAuditionLink() + i));
        singleGridContent.setVip(1);
        singleGridContent.setAuditionLink(singleCourseRecommendListContent.getAuditionLink());
        singleGridContent.setAuditionDetailUrl(singleCourseRecommendListContent.getPicture());
        singleGridContent.setTheme(singleCourseRecommendListContent.getTheme());
        HashMap hashMap = new HashMap(16);
        hashMap.put(WebViewHelp.BI_KEY_PULL_FROM, String.valueOf(this.h));
        hashMap.put("resourceId", this.g);
        hashMap.put("resourceName", singleCourseRecommendListContent.getTheme());
        hashMap.put("pullOrder", String.valueOf(i + 1));
        ekx.aqu_(courseItemViewHolder.itemView, courseItemViewHolder.i, singleGridContent, hashMap);
    }

    private void d(CourseItemViewHolder courseItemViewHolder, boolean z) {
        if (courseItemViewHolder == null || courseItemViewHolder.i == null) {
            return;
        }
        if (z) {
            courseItemViewHolder.i.setImageDrawable(this.c.getResources().getDrawable(R.drawable._2131428570_res_0x7f0b04da));
        } else {
            courseItemViewHolder.i.setImageDrawable(this.c.getResources().getDrawable(R.drawable._2131430254_res_0x7f0b0b6e));
        }
    }

    private boolean a(SingleCourseRecommendListContent singleCourseRecommendListContent) {
        return singleCourseRecommendListContent.isAuditionFlag() && !TextUtils.isEmpty(singleCourseRecommendListContent.getAuditionLink());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    static class b implements View.OnClickListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<CourseItemSidingAdapter> f2860a;
        private String c;
        private SingleCourseRecommendListContent d;
        private int e;

        public b(CourseItemSidingAdapter courseItemSidingAdapter, SingleCourseRecommendListContent singleCourseRecommendListContent, int i, String str) {
            this.f2860a = new WeakReference<>(courseItemSidingAdapter);
            this.d = singleCourseRecommendListContent;
            this.e = i;
            this.c = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CourseItemSidingAdapter courseItemSidingAdapter = this.f2860a.get();
            if (courseItemSidingAdapter == null) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            SingleCourseRecommendListContent singleCourseRecommendListContent = this.d;
            if (singleCourseRecommendListContent == null || TextUtils.isEmpty(singleCourseRecommendListContent.getLinkValue()) || nsn.o()) {
                LogUtil.h("CourseItemSidingAdapter", "linkValue is empty ");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            MarketRouterApi marketRouterApi = (MarketRouterApi) Services.c("FeatureMarketing", MarketRouterApi.class);
            String c = eil.c(this.d.getLinkValue(), courseItemSidingAdapter.f.getCategory());
            LogUtil.a("CourseItemSidingAdapter", "addShopInfoUrl: ", c);
            marketRouterApi.router(eil.c(c, courseItemSidingAdapter.h, this.e + 1, this.d, courseItemSidingAdapter.f));
            courseItemSidingAdapter.d.d(2, courseItemSidingAdapter.i, this.d, this.e);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public static class CourseItemViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthImageView f2858a;
        private HealthTextView b;
        private HealthTextView c;
        private HealthTextView d;
        private LinearLayout e;
        private HealthDivider f;
        private ConstraintLayout h;
        private ImageView i;

        public CourseItemViewHolder(View view) {
            super(view);
            this.h = (ConstraintLayout) view.findViewById(R.id.inner_recyclerview_item);
            this.f2858a = (HealthImageView) view.findViewById(R.id.section_inner_image);
            this.c = (HealthTextView) view.findViewById(R.id.section_inner_title);
            this.d = (HealthTextView) view.findViewById(R.id.section_item_left_text);
            this.i = (ImageView) view.findViewById(R.id.section_play_image);
            this.b = (HealthTextView) view.findViewById(R.id.section_item_right_text);
            this.f = (HealthDivider) view.findViewById(R.id.recyclerview_divider);
            this.e = (LinearLayout) view.findViewById(R.id.section1_1card_inner_item_ll);
        }
    }

    class a implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {

        /* renamed from: a, reason: collision with root package name */
        private final int f2859a;
        private final SingleCourseRecommendListStandardContent c;
        private final SingleCourseRecommendListContent d;
        private final WeakReference<View> e;

        public a(View view, SingleCourseRecommendListStandardContent singleCourseRecommendListStandardContent, SingleCourseRecommendListContent singleCourseRecommendListContent, int i) {
            this.e = new WeakReference<>(view);
            this.c = singleCourseRecommendListStandardContent;
            this.d = singleCourseRecommendListContent;
            this.f2859a = i;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            View view = this.e.get();
            if (view == null) {
                return;
            }
            if (!CourseItemSidingAdapter.this.d.alN_(this.c.getTheme(), this.d.getTheme(), view)) {
                CourseItemSidingAdapter.this.d.alM_(view, this.c, this.d, this.f2859a);
            } else {
                nsy.cMf_(view, this);
            }
        }

        @Override // android.view.ViewTreeObserver.OnScrollChangedListener
        public void onScrollChanged() {
            View view = this.e.get();
            if (view == null) {
                return;
            }
            if (!CourseItemSidingAdapter.this.d.alN_(this.c.getTheme(), this.d.getTheme(), view)) {
                CourseItemSidingAdapter.this.d.alM_(view, this.c, this.d, this.f2859a);
            } else {
                nsy.cMg_(view, this);
            }
        }
    }
}
