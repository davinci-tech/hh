package defpackage;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.framework.network.grs.GrsApp;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.GrsClient;
import com.huawei.hwcloudjs.g.a;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public final class kqe {
    private static GrsClient b;
    private static kqe c;

    private kqe() {
    }

    public static kqe e() {
        kqe kqeVar;
        synchronized (kqe.class) {
            if (c == null) {
                c = new kqe();
            }
            kqeVar = c;
        }
        return kqeVar;
    }

    private void a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            ksy.b("HmsGrsApiManager", "initGrsClient grsAppName is empty", true);
            return;
        }
        ksy.b("HmsGrsApiManager", "initGrsClient grsAppName is " + str, false);
        if (b == null) {
            b = new GrsClient(context, d(context, str));
        }
    }

    public Map<String, String> d(Context context, String str, String str2) {
        a(context, str);
        GrsClient grsClient = b;
        if (grsClient == null) {
            return new HashMap();
        }
        return grsClient.synGetGrsUrls(str2);
    }

    private GrsBaseInfo d(Context context, String str) {
        GrsBaseInfo grsBaseInfo = new GrsBaseInfo();
        grsBaseInfo.setAndroidVersion(ksi.f(Build.VERSION.RELEASE));
        grsBaseInfo.setDeviceModel(ksi.f(Build.MODEL));
        grsBaseInfo.setRomVersion(ksi.f(ksi.q()));
        grsBaseInfo.setAppName(ksi.f(str));
        String issueCountryCode = GrsApp.getInstance().getIssueCountryCode(context);
        if (TextUtils.isEmpty(issueCountryCode)) {
            issueCountryCode = "CN";
        }
        grsBaseInfo.setSerCountry(issueCountryCode);
        return grsBaseInfo;
    }

    public String e(Context context, String str, String str2) {
        String synGetGrsUrl = new GrsClient(context, d(context, a.c)).synGetGrsUrl(str, str2);
        return TextUtils.isEmpty(synGetGrsUrl) ? "" : synGetGrsUrl;
    }
}
