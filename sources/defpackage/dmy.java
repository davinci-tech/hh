package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.R;
import com.huawei.health.featuremarketing.route.IMarketRouteHelper;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.WebViewHelp;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes3.dex */
public class dmy implements IMarketRouteHelper {
    private IBaseResponseCallback b;
    private String c;
    private String d;

    public dmy(String str, IBaseResponseCallback iBaseResponseCallback) {
        this.c = str;
        this.b = iBaseResponseCallback;
    }

    @Override // com.huawei.health.featuremarketing.route.IMarketRouteHelper
    public void jumpActivity() {
        if (TextUtils.isEmpty(this.c)) {
            LogUtil.h("DeepLinkRouterHelper", "mLinkUrl is null");
            IBaseResponseCallback iBaseResponseCallback = this.b;
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(-1, null);
                return;
            }
            return;
        }
        Uri parse = Uri.parse(nsa.g(this.c));
        String queryParameter = parse.getQueryParameter("pName");
        String queryParameter2 = parse.getQueryParameter(WebViewHelp.BI_KEY_PULL_FROM);
        this.d = parse.getQueryParameter("locationUrl");
        if (WP_(parse)) {
            return;
        }
        if (WO_(parse)) {
            LogUtil.a("DeepLinkRouterHelper", "route uri");
            return;
        }
        String a2 = nsa.a(this.c);
        if (TextUtils.isEmpty(queryParameter)) {
            LogUtil.h("DeepLinkRouterHelper", "packageNames is null");
            IBaseResponseCallback iBaseResponseCallback2 = this.b;
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
                b(b, a2, queryParameter2);
                return;
            }
            nsa.b(b, a2);
            IBaseResponseCallback iBaseResponseCallback3 = this.b;
            if (iBaseResponseCallback3 != null) {
                iBaseResponseCallback3.d(1, null);
                return;
            }
            return;
        }
        String c = nsa.c(split);
        if (TextUtils.isEmpty(c)) {
            nsa.i(a2);
            IBaseResponseCallback iBaseResponseCallback4 = this.b;
            if (iBaseResponseCallback4 != null) {
                iBaseResponseCallback4.d(1, null);
                return;
            }
            return;
        }
        b(c, a2, queryParameter2);
    }

    private boolean WO_(Uri uri) {
        if (!this.c.contains("RunningRoute")) {
            return false;
        }
        AppRouter.zi_(uri).e("PATH_ID", uri.getQueryParameter("routePathId")).c(BaseApplication.e());
        return true;
    }

    private boolean WP_(Uri uri) {
        String queryParameter;
        if (!gpo.b() || (queryParameter = uri.getQueryParameter("address")) == null) {
            return false;
        }
        String queryParameter2 = uri.getQueryParameter(BleConstants.KEY_PATH);
        String queryParameter3 = uri.getQueryParameter(WebViewHelp.BI_KEY_PULL_FROM);
        String queryParameter4 = uri.getQueryParameter(WebViewHelp.BI_KEY_PULL_FROM);
        if (!queryParameter.contains("vip") || (queryParameter2 != null && !queryParameter2.contains("VipSubscribe") && !queryParameter2.contains("MemberCenter"))) {
            return false;
        }
        if (!TextUtils.isEmpty(queryParameter3) && queryParameter3.equals("4040")) {
            dqj.e(6);
        }
        AppRouter.b("/OperationBundle/MemberRelayActivity").e(WebViewHelp.BI_KEY_PULL_FROM, queryParameter3).e(WebViewHelp.BI_KEY_PULL_FROM, queryParameter4).e("memberTabRelayUri", this.c).c(BaseApplication.e());
        return true;
    }

    private void b(String str, String str2, String str3) {
        try {
            this.c = e();
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(this.c));
            intent.setPackage(str);
            intent.addCategory("android.intent.category.DEFAULT").addFlags(268435456);
            if (!TextUtils.isEmpty(str3)) {
                intent.putExtra(WebViewHelp.BI_KEY_PULL_FROM, str3);
            }
            nsn.cLM_(intent, str, BaseApplication.e(), "");
            IBaseResponseCallback iBaseResponseCallback = this.b;
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(0, null);
            }
        } catch (ActivityNotFoundException | SecurityException unused) {
            LogUtil.b("DeepLinkRouterHelper", "cannot found activity to handle url");
            nsa.b(str, str2);
            IBaseResponseCallback iBaseResponseCallback2 = this.b;
            if (iBaseResponseCallback2 != null) {
                iBaseResponseCallback2.d(1, null);
            }
        }
    }

    private String e() {
        ArrayList arrayList = new ArrayList(Arrays.asList(BaseApplication.e().getResources().getStringArray(R.array._2130968692_res_0x7f040074)));
        if (!TextUtils.isEmpty(this.d)) {
            if ("1".equals(this.d) && arrayList.size() > 0) {
                this.c = (String) arrayList.get(0);
            } else if ("2".equals(this.d) && arrayList.size() > 1) {
                this.c = (String) arrayList.get(1);
            } else if ("3".equals(this.d) && arrayList.size() > 2) {
                this.c = (String) arrayList.get(2);
            } else {
                LogUtil.a("DeepLinkRouterHelper", "mLinkUrl is not special Url");
            }
        }
        return this.c;
    }
}
