package com.huawei.health.marketing.views;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.datatype.templates.BaseTemplate;
import com.huawei.health.marketing.datatype.templates.GridTemplate;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.health.marketing.views.SpecialShapedPictureLayout;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.imageview.HealthImageView;
import defpackage.eie;
import defpackage.eil;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.Services;

/* loaded from: classes3.dex */
public class SpecialShapedPictureLayout extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private HealthImageView f2890a;
    private int b;
    private ResourceBriefInfo c;
    private GridTemplate d;
    private Context e;

    public SpecialShapedPictureLayout(Context context) {
        super(context);
        this.b = 0;
        this.e = BaseApplication.getContext();
    }

    public void e(int i, ResourceBriefInfo resourceBriefInfo, BaseTemplate baseTemplate) {
        LogUtil.a("SpecialShapedPictureLayout", "initData");
        if (!(baseTemplate instanceof GridTemplate)) {
            LogUtil.b("SpecialShapedPictureLayout", "template error");
            return;
        }
        this.c = resourceBriefInfo;
        this.b = i;
        this.d = (GridTemplate) baseTemplate;
        LogUtil.a("SpecialShapedPictureLayout", "template:", baseTemplate.toString());
        if (koq.b(this.d.getGridContents())) {
            LogUtil.h("SpecialShapedPictureLayout", "initData mTemplate is empty, GONE");
            setVisibility(8);
        } else {
            e();
        }
    }

    private void e() {
        this.f2890a = (HealthImageView) LayoutInflater.from(this.e).inflate(R.layout.layout_special_shaped_picture, this).findViewById(R.id.background_image);
        final SingleGridContent singleGridContent = this.d.getGridContents().get(0);
        if (singleGridContent == null) {
            LogUtil.h("SpecialShapedPictureLayout", "initView gridContent is empty, GONE");
            setVisibility(8);
        } else {
            nrf.d(singleGridContent.getPicture(), this.f2890a);
            this.f2890a.setOnClickListener(new View.OnClickListener() { // from class: ekj
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SpecialShapedPictureLayout.this.apQ_(singleGridContent, view);
                }
            });
            nsy.cMb_(this.f2890a, new ViewTreeObserver.OnScrollChangedListener() { // from class: com.huawei.health.marketing.views.SpecialShapedPictureLayout.1
                @Override // android.view.ViewTreeObserver.OnScrollChangedListener
                public void onScrollChanged() {
                    if (MarketingBiUtils.alP_(SpecialShapedPictureLayout.this.f2890a)) {
                        MarketingBiUtils.d(1, SpecialShapedPictureLayout.this.b, SpecialShapedPictureLayout.this.c, null);
                        nsy.cMg_(SpecialShapedPictureLayout.this.f2890a, this);
                    }
                }
            });
        }
    }

    public /* synthetic */ void apQ_(final SingleGridContent singleGridContent, View view) {
        if (TextUtils.isEmpty(singleGridContent.getLinkValue())) {
            LogUtil.h("SpecialShapedPictureLayout", "click linkValue is empty");
            ViewClickInstrumentation.clickOnView(view);
        } else if (nsn.cLk_(view)) {
            LogUtil.h("SpecialShapedPictureLayout", "click too fast");
            ViewClickInstrumentation.clickOnView(view);
        } else if (!eie.b(singleGridContent.getLinkValue())) {
            b(singleGridContent.getLinkValue());
            ViewClickInstrumentation.clickOnView(view);
        } else {
            LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: ekh
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    SpecialShapedPictureLayout.this.c(singleGridContent, i, obj);
                }
            }, AnalyticsValue.MARKETING_RESOURCE.value());
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public /* synthetic */ void c(SingleGridContent singleGridContent, int i, Object obj) {
        if (i == 0) {
            b(singleGridContent.getLinkValue());
        } else {
            LogUtil.h("SpecialShapedPictureLayout", "onClick errorCode = ", Integer.valueOf(i));
        }
    }

    private void b(String str) {
        MarketingBiUtils.d(2, this.b, this.c, null);
        ((MarketRouterApi) Services.c("FeatureMarketing", MarketRouterApi.class)).router(eil.c(str, this.c, this.b, 1));
    }
}
