package com.huawei.health.marketing.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.templates.CourseNoticeStandardTemplate;
import com.huawei.health.marketing.datatype.templates.SingleCourseNoticeContent;
import com.huawei.health.marketing.datatype.templates.SingleCourseNoticeStandardContent;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.health.marketing.views.CoursePreviewAdapter;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import defpackage.eie;
import defpackage.eil;
import defpackage.eiv;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes3.dex */
public class CoursePreviewAdapter extends RecyclerView.Adapter<ViewHolder> {
    private ResourceBriefInfo b;
    private List<SingleCourseNoticeStandardContent> e;
    private CourseNoticeStandardTemplate g;
    private long i;
    private int j;
    private Set<SingleCourseNoticeContent> d = new HashSet();
    private Set<SingleCourseNoticeContent> c = new HashSet();

    /* renamed from: a, reason: collision with root package name */
    private Context f2861a = BaseApplication.e();

    public CoursePreviewAdapter(CourseNoticeStandardTemplate courseNoticeStandardTemplate, int i, ResourceBriefInfo resourceBriefInfo) {
        this.j = 0;
        this.g = courseNoticeStandardTemplate;
        this.e = courseNoticeStandardTemplate.getGridContents();
        this.b = resourceBriefInfo;
        this.j = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: apb_, reason: merged with bridge method [inline-methods] */
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.f2861a).inflate(R.layout.item_course_preview_time, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (koq.b(this.e, i)) {
            LogUtil.h("CoursePreviewAdapter", "mCourseContents isOutOfBounds");
            return;
        }
        SingleCourseNoticeStandardContent singleCourseNoticeStandardContent = this.e.get(i);
        viewHolder.b.setText(singleCourseNoticeStandardContent.getTheme());
        d(viewHolder, singleCourseNoticeStandardContent, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (koq.b(this.e)) {
            return 0;
        }
        return this.e.size();
    }

    private void d(ViewHolder viewHolder, SingleCourseNoticeStandardContent singleCourseNoticeStandardContent, int i) {
        if (singleCourseNoticeStandardContent == null) {
            LogUtil.h("CoursePreviewAdapter", "addCourseItem courseContent is null");
            return;
        }
        List<SingleCourseNoticeContent> subContents = singleCourseNoticeStandardContent.getSubContents();
        if (koq.b(subContents)) {
            LogUtil.h("CoursePreviewAdapter", "addCourseItem contentItemLists is empty");
            return;
        }
        LogUtil.a("CoursePreviewAdapter", "addCourseItem contentItemLists size = ", Integer.valueOf(subContents.size()));
        int i2 = 0;
        while (i2 < subContents.size()) {
            SingleCourseNoticeContent singleCourseNoticeContent = subContents.get(i2);
            if (singleCourseNoticeContent == null) {
                LogUtil.h("CoursePreviewAdapter", "addCourseItem courseNoticeContent is null");
            } else if (this.c.contains(singleCourseNoticeContent)) {
                LogUtil.h("CoursePreviewAdapter", "addCourseItem courseNoticeContent is added");
            } else {
                this.c.add(singleCourseNoticeContent);
                viewHolder.c.addView(aoZ_(singleCourseNoticeContent, i2, i == this.e.size() - 1 && i2 == subContents.size() - 1, singleCourseNoticeStandardContent.getTheme()));
            }
            i2++;
        }
    }

    private View aoZ_(final SingleCourseNoticeContent singleCourseNoticeContent, final int i, boolean z, final String str) {
        final View inflate = LayoutInflater.from(this.f2861a).inflate(R.layout.item_course_preview_course, (ViewGroup) null);
        HealthCardView healthCardView = (HealthCardView) inflate.findViewById(R.id.course_preview_layout);
        HealthImageView healthImageView = (HealthImageView) inflate.findViewById(R.id.course_preview_img);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.course_preview_corner);
        HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.course_preview_theme);
        HealthTextView healthTextView3 = (HealthTextView) inflate.findViewById(R.id.course_preview_sub_theme);
        nrf.d(singleCourseNoticeContent.getPicture(), healthImageView);
        if (singleCourseNoticeContent.isThemeVisibility() && !TextUtils.isEmpty(singleCourseNoticeContent.getTheme())) {
            healthTextView2.setText(singleCourseNoticeContent.getTheme());
            healthTextView2.setVisibility(0);
        } else {
            healthTextView2.setVisibility(8);
        }
        healthTextView3.setText(singleCourseNoticeContent.getSubTheme());
        e(healthTextView, singleCourseNoticeContent);
        ViewGroup.LayoutParams layoutParams = healthCardView.getLayoutParams();
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
            if (!z) {
                layoutParams2.setMarginEnd(BaseApplication.e().getResources().getDimensionPixelOffset(R.dimen._2131362886_res_0x7f0a0446));
            }
        }
        inflate.setOnClickListener(new View.OnClickListener() { // from class: ejn
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CoursePreviewAdapter.this.apa_(inflate, singleCourseNoticeContent, i, str, view);
            }
        });
        nsy.cMb_(inflate, new e(this, inflate, singleCourseNoticeContent, i, str));
        return inflate;
    }

    public /* synthetic */ void apa_(View view, SingleCourseNoticeContent singleCourseNoticeContent, int i, String str, View view2) {
        if (nsn.cLk_(view) || TextUtils.isEmpty(singleCourseNoticeContent.getLinkValue())) {
            LogUtil.h("CoursePreviewAdapter", "click too fast or url is empty");
            ViewClickInstrumentation.clickOnView(view2);
        } else {
            a(singleCourseNoticeContent, i, str);
            ViewClickInstrumentation.clickOnView(view2);
        }
    }

    public static void e(HealthTextView healthTextView, SingleCourseNoticeContent singleCourseNoticeContent) {
        if (singleCourseNoticeContent == null) {
            LogUtil.h("CoursePreviewAdapter", "content = null");
            return;
        }
        if (!d(healthTextView, singleCourseNoticeContent)) {
            LogUtil.h("CoursePreviewAdapter", "setCornerShow can not show");
            return;
        }
        try {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(0);
            gradientDrawable.setCornerRadii(new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, eiv.d(12.0f), eiv.d(12.0f)});
            gradientDrawable.setColor(Color.parseColor(singleCourseNoticeContent.getCornerColor()));
            healthTextView.setBackground(gradientDrawable);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("CoursePreviewAdapter", "IllegalArgumentException");
        }
        healthTextView.setGravity(17);
        healthTextView.setText(singleCourseNoticeContent.getCorner());
        healthTextView.setVisibility(0);
    }

    private static boolean d(HealthTextView healthTextView, SingleCourseNoticeContent singleCourseNoticeContent) {
        if (healthTextView == null) {
            LogUtil.h("CoursePreviewAdapter", "textView is null");
            return false;
        }
        if (singleCourseNoticeContent.getCornerVisibility() && !TextUtils.isEmpty(singleCourseNoticeContent.getCorner()) && !TextUtils.isEmpty(singleCourseNoticeContent.getCornerColor())) {
            return true;
        }
        healthTextView.setVisibility(8);
        return false;
    }

    private void a(final SingleCourseNoticeContent singleCourseNoticeContent, final int i, final String str) {
        if (!eie.b(singleCourseNoticeContent.getLinkValue())) {
            c(singleCourseNoticeContent, i, str);
        } else {
            LoginInit.getInstance(BaseApplication.e()).browsingToLogin(new IBaseResponseCallback() { // from class: ejo
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i2, Object obj) {
                    CoursePreviewAdapter.this.d(singleCourseNoticeContent, i, str, i2, obj);
                }
            }, AnalyticsValue.MARKETING_RESOURCE.value());
        }
    }

    public /* synthetic */ void d(SingleCourseNoticeContent singleCourseNoticeContent, int i, String str, int i2, Object obj) {
        if (i2 == 0) {
            c(singleCourseNoticeContent, i, str);
        } else {
            LogUtil.h("CoursePreviewAdapter", "onClick errorCode = ", Integer.valueOf(i2));
        }
    }

    private void c(SingleCourseNoticeContent singleCourseNoticeContent, int i, String str) {
        String linkValue = singleCourseNoticeContent.getLinkValue();
        LogUtil.a("CoursePreviewAdapter", "goToDetail linkValue:", linkValue);
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        if (marketRouterApi != null) {
            marketRouterApi.router(eil.c(linkValue, this.b, this.j, 1));
        }
        e(2, singleCourseNoticeContent, i, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, SingleCourseNoticeContent singleCourseNoticeContent, int i2, String str) {
        String str2 = this.g.getName() + " " + str;
        HashMap hashMap = new HashMap(10);
        hashMap.put("resourceName", str2);
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("itemCardName", singleCourseNoticeContent.getTheme());
        if (i == 2) {
            hashMap.put("durationTime", Integer.valueOf((int) (System.currentTimeMillis() - this.i)));
            this.i = System.currentTimeMillis();
        }
        hashMap.put("pullOrder", Integer.valueOf(i2 + 1));
        hashMap.put("moreEntryName", this.g.getMoreMenuTitle());
        LogUtil.a("CoursePreviewAdapter", "marketing biEvent: mPositionId: ", Integer.valueOf(this.j), ", biMap: ", hashMap.toString());
        MarketingBiUtils.d(i, this.j, this.b, hashMap);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView b;
        private LinearLayout c;

        ViewHolder(View view) {
            super(view);
            this.b = (TextView) view.findViewById(R.id.course_preview_time_title);
            this.c = (LinearLayout) view.findViewById(R.id.course_preview_container);
        }
    }

    static class e implements ViewTreeObserver.OnScrollChangedListener {

        /* renamed from: a, reason: collision with root package name */
        private final SingleCourseNoticeContent f2862a;
        private final WeakReference<View> b;
        private final String c;
        private final WeakReference<CoursePreviewAdapter> d;
        private final int e;

        public e(CoursePreviewAdapter coursePreviewAdapter, View view, SingleCourseNoticeContent singleCourseNoticeContent, int i, String str) {
            this.d = new WeakReference<>(coursePreviewAdapter);
            this.b = new WeakReference<>(view);
            this.f2862a = singleCourseNoticeContent;
            this.e = i;
            this.c = str;
        }

        @Override // android.view.ViewTreeObserver.OnScrollChangedListener
        public void onScrollChanged() {
            CoursePreviewAdapter coursePreviewAdapter = this.d.get();
            View view = this.b.get();
            if (coursePreviewAdapter == null || view == null || coursePreviewAdapter.d.contains(this.f2862a)) {
                nsy.cMg_(view, this);
            } else if (MarketingBiUtils.alP_(view)) {
                coursePreviewAdapter.e(1, this.f2862a, this.e, this.c);
                coursePreviewAdapter.i = System.currentTimeMillis();
                coursePreviewAdapter.d.add(this.f2862a);
                nsy.cMg_(view, this);
            }
        }
    }
}
