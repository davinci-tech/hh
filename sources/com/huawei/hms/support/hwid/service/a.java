package com.huawei.hms.support.hwid.service;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.common.internal.constant.AuthInternalPickerConstant;
import com.huawei.hms.support.hianalytics.HiAnalyticsClient;
import com.huawei.hms.support.hwid.bean.CheckPasswordByUserIdReq;
import com.huawei.hms.support.hwid.common.cloudservice.CloudRequestHandler;
import com.huawei.hms.support.hwid.common.e.c;
import com.huawei.hms.support.hwid.common.e.d;
import com.huawei.hms.support.hwid.common.e.e;
import com.huawei.hms.support.hwid.common.e.f;
import com.huawei.hms.support.hwid.common.e.g;
import com.huawei.hwid.core.helper.handler.ErrorStatus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static String f6022a = "";
    private static String b = "";
    private static final List<Integer> c = new ArrayList(Arrays.asList(1, 5, 7, 8));

    public static void a(final Context context, final CheckPasswordByUserIdReq checkPasswordByUserIdReq, final CloudRequestHandler cloudRequestHandler) {
        if (checkPasswordByUserIdReq == null || context == null || cloudRequestHandler == null) {
            g.b("CloudAccountImpl", "checkPasswordByUserId: request parameter is null");
            f.a(f6022a, "hwid.checkPasswordByUserId", 2015, b);
            return;
        }
        b = checkPasswordByUserIdReq.getPackageName();
        f6022a = HiAnalyticsClient.reportEntry(context, "hwid.checkPasswordByUserId", AuthInternalPickerConstant.HMS_SDK_VERSION);
        if (!a(checkPasswordByUserIdReq.getUserId(), checkPasswordByUserIdReq.getPassword(), checkPasswordByUserIdReq.getPackageName(), checkPasswordByUserIdReq.getDeviceId(), checkPasswordByUserIdReq.getDeviceType(), context, checkPasswordByUserIdReq.getSiteId())) {
            g.a("CloudAccountImpl", "the params is invalid.");
            cloudRequestHandler.onError(new ErrorStatus(2003, "the params is invalid."));
            f.a(f6022a, "hwid.checkPasswordByUserId", 2003, b);
            return;
        }
        if (c.a(checkPasswordByUserIdReq.getAccountType())) {
            cloudRequestHandler.onError(new ErrorStatus(2026, "third account is not allowed to verify password"));
            g.a("CloudAccountImpl", "third account is not allowed to verify password");
            f.a(f6022a, "hwid.checkPasswordByUserId", 2026, b);
            return;
        }
        if (!c.a(context)) {
            cloudRequestHandler.onError(new ErrorStatus(2005, "Network is Unavailable"));
            g.b("CloudAccountImpl", "Network Unavailable");
            f.a(f6022a, "hwid.checkPasswordByUserId", 2005, b);
            return;
        }
        if (!c.f(context)) {
            g.a("CloudAccountImpl", "checkIsUseHuaweiAccount false can not use hwid");
            cloudRequestHandler.onError(new ErrorStatus(2033, "checkIsUseHuaweiAccount false can not use hwid"));
            f.a(f6022a, "hwid.checkPasswordByUserId", 2033, b);
            return;
        }
        if (context.getPackageManager().checkPermission("com.huawei.android.permission.ANTITHEFT", checkPasswordByUserIdReq.getPackageName()) != 0) {
            cloudRequestHandler.onError(new ErrorStatus(27, "permission is deny"));
            g.a("CloudAccountImpl", "permission is deny");
            f.a(f6022a, "hwid.checkPasswordByUserId", 27, checkPasswordByUserIdReq.getPackageName());
            return;
        }
        int length = com.huawei.hms.support.hwid.common.d.a.a(context).a(String.valueOf(checkPasswordByUserIdReq.getSiteId()), "").length();
        Map<String, ?> a2 = com.huawei.hms.support.hwid.common.d.a.a(context).a();
        if (a2 != null && a2.size() != 0 && length < 40) {
            g.a("CloudAccountImpl", "sp is useable");
            c(context, checkPasswordByUserIdReq, cloudRequestHandler);
            d.a().a(context, f6022a, checkPasswordByUserIdReq.getPackageName());
        } else {
            g.a("CloudAccountImpl", "sp is unuseable");
            d.a().a(context, f6022a, checkPasswordByUserIdReq.getPackageName(), new com.huawei.hms.support.hwid.result.c<com.huawei.hms.support.hwid.result.a>() { // from class: com.huawei.hms.support.hwid.service.a.1
                @Override // com.huawei.hms.support.hwid.result.c
                public void a(com.huawei.hms.support.hwid.result.a aVar) {
                    com.huawei.hms.support.hwid.result.d a3 = aVar.a();
                    if (a3.a() == 0) {
                        a.c(context, checkPasswordByUserIdReq, cloudRequestHandler);
                    } else {
                        cloudRequestHandler.onError(new ErrorStatus(a3.a(), a3.b()));
                    }
                }
            });
        }
    }

    private static boolean a(String str, String str2, String str3, String str4, String str5, Context context, int i) {
        if (!c.contains(Integer.valueOf(i))) {
            g.a("CloudAccountImpl", "the siteId is invalid");
            return false;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            g.a("CloudAccountImpl", "userId or password is empty");
            return false;
        }
        if (TextUtils.isEmpty(str3) || !str3.equals(context.getPackageName())) {
            g.a("CloudAccountImpl", "appId is empty or not equals");
            return false;
        }
        if (!TextUtils.isEmpty(str4) && !TextUtils.isEmpty(str5)) {
            return true;
        }
        g.a("CloudAccountImpl", "deviceId or deviceType is empty");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(Context context, CheckPasswordByUserIdReq checkPasswordByUserIdReq, CloudRequestHandler cloudRequestHandler) {
        String a2 = com.huawei.hms.support.hwid.common.d.a.a(context).a(String.valueOf(checkPasswordByUserIdReq.getSiteId()), "");
        String a3 = com.huawei.hms.support.hwid.common.d.a.a(context).a("public-key", "");
        g.a("CloudAccountImpl", "verifyPasswordRequest: siteDomainName = " + a2, false);
        g.a("CloudAccountImpl", "verifyPasswordRequest: publicKey = " + a3, false);
        if (TextUtils.isEmpty(a2) || TextUtils.isEmpty(a3)) {
            cloudRequestHandler.onError(new ErrorStatus(14, "siteDomainName or publicKey is null"));
            g.a("CloudAccountImpl", "siteDomainName or publicKey is null");
            f.a(f6022a, "hwid.checkPasswordByUserId", 14, b);
            return;
        }
        e.a().a(context, checkPasswordByUserIdReq, a2, a3, f6022a, cloudRequestHandler);
    }
}
