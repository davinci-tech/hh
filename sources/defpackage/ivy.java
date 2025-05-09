package defpackage;

import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.auth.HiUserAuth;
import com.huawei.hihealthservice.auth.WhiteListApp;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes7.dex */
public class ivy {
    /* JADX WARN: Removed duplicated region for block: B:7:0x0033 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.huawei.hihealthservice.auth.HiAuthorization e(boolean r1, java.lang.String r2, java.lang.String r3) {
        /*
            r0 = 0
            if (r1 == 0) goto L12
            ikw r1 = defpackage.ikw.b()
            com.huawei.hihealthservice.auth.WhiteListApp r1 = r1.d(r2)
            if (r1 == 0) goto L30
            java.lang.String r1 = r1.getAppScopes()
            goto L31
        L12:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "/rest.php?appid="
            r1.<init>(r2)
            r1.append(r3)
            java.lang.String r2 = "&type=3&access_token=xxx&nsp_svc=nsp.scope.app.get"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "by ID :"
            java.lang.Object[] r1 = new java.lang.Object[]{r2, r1}
            java.lang.String r2 = "HiHttpUtil"
            com.huawei.hwlogsmodel.LogUtil.c(r2, r1)
        L30:
            r1 = r0
        L31:
            if (r1 != 0) goto L34
            return r0
        L34:
            java.lang.Class<com.huawei.hihealthservice.auth.HiAuthorization> r2 = com.huawei.hihealthservice.auth.HiAuthorization.class
            java.lang.Object r1 = com.huawei.hihealth.util.HiJsonUtil.e(r1, r2)
            com.huawei.hihealthservice.auth.HiAuthorization r1 = (com.huawei.hihealthservice.auth.HiAuthorization) r1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ivy.e(boolean, java.lang.String, java.lang.String):com.huawei.hihealthservice.auth.HiAuthorization");
    }

    public static HiUserAuth c(boolean z, String str, String str2) {
        String str3;
        if (z) {
            WhiteListApp d = ikw.b().d(str);
            if (d != null) {
                str3 = d.getUserScopes();
                return (HiUserAuth) HiJsonUtil.e(str3, HiUserAuth.class);
            }
        } else {
            LogUtil.c("HiHttpUtil", "by Token :", "/rest.php?nsp_svc=huawei.oauth2.user.getTokenInfo&open_id=OPENID&access_token=" + str2);
        }
        str3 = null;
        return (HiUserAuth) HiJsonUtil.e(str3, HiUserAuth.class);
    }
}
