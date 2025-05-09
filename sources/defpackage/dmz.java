package defpackage;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.featuremarketing.route.IMarketRouteHelper;
import com.huawei.hwauthutil.HsfSignValidator;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.WebViewHelp;
import com.tencent.open.SocialOperation;

/* loaded from: classes3.dex */
public class dmz implements IMarketRouteHelper {

    /* renamed from: a, reason: collision with root package name */
    private IBaseResponseCallback f11724a;
    private String c;

    public dmz(String str, IBaseResponseCallback iBaseResponseCallback) {
        this.c = str;
        this.f11724a = iBaseResponseCallback;
    }

    @Override // com.huawei.health.featuremarketing.route.IMarketRouteHelper
    public void jumpActivity() {
        if (TextUtils.isEmpty(this.c)) {
            LogUtil.h("ApkRouterHelper", "mLinkUrl is null");
            IBaseResponseCallback iBaseResponseCallback = this.f11724a;
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(-1, null);
                return;
            }
            return;
        }
        Uri parse = Uri.parse(nsa.g(this.c));
        String queryParameter = parse.getQueryParameter("pName");
        String queryParameter2 = parse.getQueryParameter(WebViewHelp.BI_KEY_PULL_FROM);
        String queryParameter3 = parse.getQueryParameter(SocialOperation.GAME_SIGNATURE);
        String a2 = nsa.a(this.c);
        if (TextUtils.isEmpty(queryParameter)) {
            LogUtil.h("ApkRouterHelper", "packageNames is null");
            IBaseResponseCallback iBaseResponseCallback2 = this.f11724a;
            if (iBaseResponseCallback2 != null) {
                iBaseResponseCallback2.d(-1, null);
                return;
            }
            return;
        }
        String[] split = queryParameter.split("\\|");
        if (split.length == 1) {
            String b = nsa.b(queryParameter);
            if (jdm.b(BaseApplication.e(), b)) {
                c(b, queryParameter3, queryParameter2);
                return;
            }
            nsa.b(b, a2);
            IBaseResponseCallback iBaseResponseCallback3 = this.f11724a;
            if (iBaseResponseCallback3 != null) {
                iBaseResponseCallback3.d(1, null);
                return;
            }
            return;
        }
        String c = nsa.c(split);
        if (TextUtils.isEmpty(c)) {
            nsa.i(a2);
        } else {
            c(c, queryParameter3, queryParameter2);
        }
    }

    private boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            LogUtil.a("ApkRouterHelper", "signature is null");
            return true;
        }
        if (str2.equals(HsfSignValidator.e(BaseApplication.e(), str))) {
            return true;
        }
        LogUtil.h("ApkRouterHelper", "signature is invalids");
        return false;
    }

    private void c(String str, String str2, String str3) {
        if (!a(str, str2)) {
            LogUtil.a("ApkRouterHelper", "signature is invalids");
            IBaseResponseCallback iBaseResponseCallback = this.f11724a;
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(-1, null);
                return;
            }
            return;
        }
        Intent launchIntentForPackage = BaseApplication.e().getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage == null) {
            LogUtil.h("ApkRouterHelper", "openApp intent is null");
            IBaseResponseCallback iBaseResponseCallback2 = this.f11724a;
            if (iBaseResponseCallback2 != null) {
                iBaseResponseCallback2.d(-1, null);
                return;
            }
            return;
        }
        if (!TextUtils.isEmpty(str3)) {
            launchIntentForPackage.putExtra(WebViewHelp.BI_KEY_PULL_FROM, str3);
        }
        nsn.cLM_(launchIntentForPackage, str, BaseApplication.wa_(), "");
        IBaseResponseCallback iBaseResponseCallback3 = this.f11724a;
        if (iBaseResponseCallback3 != null) {
            iBaseResponseCallback3.d(0, null);
        }
    }
}
