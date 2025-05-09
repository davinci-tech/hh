package com.huawei.ui.main.stories.me.js;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.me.views.privacy.PrivacyStatementActivity;
import health.compact.a.CommonUtil;

/* loaded from: classes7.dex */
public class JsInteraction {

    /* renamed from: a, reason: collision with root package name */
    private Context f10370a;

    public JsInteraction(Context context) {
        this.f10370a = context;
    }

    @JavascriptInterface
    public String isSupportPrivacyCenter() {
        return (CommonUtil.cd() && CommonUtil.v(this.f10370a)) ? "true" : "false";
    }

    @JavascriptInterface
    public boolean needDisplay() {
        return CommonUtil.cd() && CommonUtil.v(this.f10370a);
    }

    @JavascriptInterface
    public void enterPrivacyCenter() {
        Context context = this.f10370a;
        if (context == null) {
            LogUtil.h("JsInteraction", "enterPrivacyCenter mContext is null");
            return;
        }
        if ((context instanceof PrivacyStatementActivity) && CommonUtil.v(context)) {
            LogUtil.a("JsInteraction", "enterPrivacyCenter PrivacyStatementActivity finish");
            ((PrivacyStatementActivity) this.f10370a).finish();
        } else {
            Intent intent = new Intent();
            intent.setClassName("com.huawei.systemmanager", "com.huawei.dataprivacycenter.MainActivity");
            intent.setFlags(AppRouterExtras.COLDSTART);
            this.f10370a.startActivity(intent);
        }
    }

    @JavascriptInterface
    public void agreementCheckMore() {
        enterPrivacyCenter();
    }
}
