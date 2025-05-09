package defpackage;

import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.CommonUtil;
import java.util.List;

/* loaded from: classes6.dex */
public class ntk {
    public static boolean c() {
        List<String> d;
        boolean b;
        boolean z;
        nth nthVar = (nth) ntf.b().e("heartRateConfig", nth.class);
        if (nthVar == null) {
            LogUtil.h("ArkuixPageConfigUtil", "not found config.");
            return false;
        }
        if (nthVar.c() != 1) {
            LogUtil.h("ArkuixPageConfigUtil", "isOpenArkuix IS_SWITCH_OPEN:", 1);
            return false;
        }
        if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            LogUtil.h("ArkuixPageConfigUtil", "isOpenArkuix isBrowseMode true.");
            return false;
        }
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.h("ArkuixPageConfigUtil", "currentHuid is empty.");
            return false;
        }
        String c = CommonUtil.c(BaseApplication.getContext());
        if (TextUtils.isEmpty(c)) {
            LogUtil.h("ArkuixPageConfigUtil", "currentVersion is empty.");
            return false;
        }
        ntj d2 = d(nthVar, c);
        if (nti.c(d2 == null ? nthVar.a() : d2.c(), accountInfo)) {
            LogUtil.h("ArkuixPageConfigUtil", "isOpenArkuix huid is blocked!");
            return false;
        }
        if (d2 == null) {
            d = nthVar.f();
        } else {
            d = d2.d();
        }
        if (!nti.e(d, LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010))) {
            LogUtil.h("ArkuixPageConfigUtil", "isOpenArkuix not white list country!");
            return false;
        }
        if (d2 != null) {
            LogUtil.a("ArkuixPageConfigUtil", "versionInnerConfigBean enter.");
            z = nti.d(d2.e());
            b = nti.b(d2.b(), accountInfo);
        } else {
            boolean d3 = nti.d(nthVar.d());
            b = nti.b(nthVar.e(), accountInfo);
            z = d3;
        }
        LogUtil.a("ArkuixPageConfigUtil", "isDeviceTypeSupport:", Boolean.valueOf(z), ",isHuidSupport:", Boolean.valueOf(b));
        return z && b;
    }

    private static ntj d(nth nthVar, String str) {
        if (koq.b(nthVar.b())) {
            LogUtil.h("ArkuixPageConfigUtil", "getInnerConfig SupportVersions is empty.");
            return null;
        }
        for (ntj ntjVar : nthVar.b()) {
            if (ntjVar != null) {
                String a2 = ntjVar.a();
                if (!TextUtils.isEmpty(a2) && str.contains(a2)) {
                    return ntjVar;
                }
            }
        }
        return null;
    }
}
