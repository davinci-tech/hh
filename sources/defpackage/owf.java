package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.h5pro.H5proUtil;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import java.util.Locale;

/* loaded from: classes6.dex */
public class owf {

    /* renamed from: a, reason: collision with root package name */
    private String f15983a;

    public void e(final Context context) {
        LogUtil.a("SCUI_AlivePromptGuideSetting", "obtainUrlDomain ENTER ");
        GRSManager.a(BaseApplication.getContext()).e("domainKlgMapDtlUrl", new GrsQueryCallback() { // from class: owf.1
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                if (!TextUtils.isEmpty(str)) {
                    owf.this.f15983a = str;
                    HandlerExecutor.e(new Runnable() { // from class: owf.1.4
                        @Override // java.lang.Runnable
                        public void run() {
                            H5proUtil.jumpFromDeeplink(context, owf.this.c(context));
                        }
                    });
                } else {
                    LogUtil.h("SCUI_AlivePromptGuideSetting", "obtainUrlDomain urlDomain is empty");
                }
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.h("SCUI_AlivePromptGuideSetting", "obtainUrlDomain onCallBackFail ", Integer.valueOf(i));
            }
        });
    }

    public String c(Context context) {
        String format;
        int m = CommonUtil.m(context, LoginInit.getInstance(context).getAccountInfo(1009));
        LogUtil.a("SCUI_AlivePromptGuideSetting", "getStepAlivePromptUrl ", Integer.valueOf(m));
        if (m == 1) {
            if (LanguageUtil.h(context)) {
                format = String.format(Locale.ENGLISH, "/hwtips/protection_setup/%s/protection.html?cid=%s", "zh-CN", "11074");
            } else {
                format = String.format(Locale.ENGLISH, "/hwtips/protection_setup/%s/protection.html?cid=%s", "en-US", "11074");
            }
        } else {
            format = String.format(Locale.ENGLISH, "/handbook/SingleJumppage/protection_setup/en-US/index.html?lang=%s", CommonUtil.u());
        }
        return this.f15983a + format;
    }
}
