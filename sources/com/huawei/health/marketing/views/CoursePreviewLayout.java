package com.huawei.health.marketing.views;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.templates.BaseTemplate;
import com.huawei.health.marketing.datatype.templates.CourseNoticeStandardTemplate;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.health.marketing.views.CoursePreviewLayout;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.eie;
import defpackage.eil;
import defpackage.eiv;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class CoursePreviewLayout extends LinearLayout implements View.OnClickListener, ResourceBriefInfoOwnable {

    /* renamed from: a, reason: collision with root package name */
    private HealthRecycleView f2863a;
    private HealthSubHeader b;
    private CoursePreviewAdapter c;
    private Pair<Integer, Integer> d;
    private String e;
    private ResourceBriefInfo f;
    private CourseNoticeStandardTemplate h;
    private int j;

    public CoursePreviewLayout(Context context) {
        super(context);
        this.d = BaseActivity.getSafeRegionWidth();
    }

    public void a(int i, ResourceBriefInfo resourceBriefInfo, BaseTemplate baseTemplate) {
        if (!(baseTemplate instanceof CourseNoticeStandardTemplate)) {
            LogUtil.h("CoursePreviewLayout", "template is not CourseNoticeStandardTemplate");
            return;
        }
        this.j = i;
        this.h = (CourseNoticeStandardTemplate) baseTemplate;
        this.f = resourceBriefInfo;
        LogUtil.a("CoursePreviewLayout", "template:", baseTemplate.toString());
        b();
    }

    private void b() {
        View inflate = LayoutInflater.from(BaseApplication.getContext()).inflate(R.layout.layout_course_preview, this);
        this.b = (HealthSubHeader) inflate.findViewById(R.id.marketing_course_sub_header);
        this.f2863a = (HealthRecycleView) inflate.findViewById(R.id.marketing_course_recycler_view);
        setSubHeaderUi(this.h);
        this.c = new CoursePreviewAdapter(this.h, this.j, this.f);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BaseApplication.getContext());
        linearLayoutManager.setOrientation(0);
        this.f2863a.setLayoutManager(linearLayoutManager);
        this.f2863a.setAdapter(this.c);
        c(this.f2863a);
        if (eie.e(this.j)) {
            return;
        }
        inflate.setPadding(((Integer) this.d.first).intValue(), 0, ((Integer) this.d.second).intValue(), 0);
    }

    private void setSubHeaderUi(CourseNoticeStandardTemplate courseNoticeStandardTemplate) {
        if (courseNoticeStandardTemplate == null) {
            LogUtil.h("CoursePreviewLayout", "setSubHeaderUi template is null");
            return;
        }
        boolean isNameVisibility = courseNoticeStandardTemplate.isNameVisibility();
        String name = courseNoticeStandardTemplate.getName();
        eiv.e(isNameVisibility && !TextUtils.isEmpty(name), this.b);
        this.b.setHeadTitleText(name);
        String linkValue = courseNoticeStandardTemplate.getLinkValue();
        this.e = linkValue;
        if (!TextUtils.isEmpty(linkValue)) {
            this.b.setMoreLayoutVisibility(0);
            if (!TextUtils.isEmpty(courseNoticeStandardTemplate.getMoreMenuTitle())) {
                this.b.setMoreTextMaxLines(2);
                this.b.setMoreTextAlignment(6);
                this.b.setMoreText(courseNoticeStandardTemplate.getMoreMenuTitle());
            }
            this.b.setMoreViewClickListener(this);
        } else {
            this.b.setMoreLayoutVisibility(8);
        }
        this.b.setSubHeaderBackgroundColor(0);
    }

    private void c(HealthRecycleView healthRecycleView) {
        healthRecycleView.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.huawei.health.marketing.views.CoursePreviewLayout.4
            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
                super.getItemOffsets(rect, view, recyclerView, state);
                int dimensionPixelOffset = CoursePreviewLayout.this.getResources().getDimensionPixelOffset(R.dimen._2131362008_res_0x7f0a00d8);
                int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
                int size = CoursePreviewLayout.this.h.getGridContents().size();
                if (LanguageUtil.bc(BaseApplication.getContext())) {
                    if (childAdapterPosition == 0) {
                        rect.right = dimensionPixelOffset;
                    }
                    if (childAdapterPosition == size - 1) {
                        rect.left = dimensionPixelOffset;
                        return;
                    }
                    return;
                }
                if (childAdapterPosition == 0) {
                    rect.left = dimensionPixelOffset;
                }
                if (childAdapterPosition == size - 1) {
                    rect.right = dimensionPixelOffset;
                }
            }
        });
    }

    @Override // com.huawei.health.marketing.views.ResourceBriefInfoOwnable
    public boolean isOwnedByBriefInfo(ResourceBriefInfo resourceBriefInfo) {
        return (resourceBriefInfo == null || this.f == null || !resourceBriefInfo.getResourceId().equals(this.f.getResourceId())) ? false : true;
    }

    @Override // com.huawei.health.marketing.views.ResourceBriefInfoOwnable
    public int getResourceBriefPriority() {
        ResourceBriefInfo resourceBriefInfo = this.f;
        if (resourceBriefInfo != null) {
            return resourceBriefInfo.getPriority();
        }
        return 0;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.hwsubheader_more_container) {
            if (TextUtils.isEmpty(this.e)) {
                LogUtil.h("CoursePreviewLayout", "mMoreUrl is empty");
                ViewClickInstrumentation.clickOnView(view);
                return;
            } else {
                if (!eie.b(this.e)) {
                    b(this.e, 3);
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: ejt
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        CoursePreviewLayout.this.d(i, obj);
                    }
                }, AnalyticsValue.MARKETING_RESOURCE.value());
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void d(int i, Object obj) {
        if (i == 0) {
            b(this.e, 3);
        } else {
            LogUtil.h("CoursePreviewLayout", "onClick errorCode = ", Integer.valueOf(i));
        }
    }

    private void b(String str, int i) {
        LogUtil.a("CoursePreviewLayout", "goToMore ");
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        if (marketRouterApi != null) {
            marketRouterApi.router(eil.c(str, this.f, this.j, 1));
        }
        HashMap hashMap = new HashMap();
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("moreEntryName", this.h.getMoreMenuTitle());
        MarketingBiUtils.d(i, this.j, this.f, hashMap);
    }
}
