package health.compact.a;

import android.text.TextUtils;
import com.huawei.haf.download.DownloadPluginUrl;
import com.huawei.hwcommonmodel.application.BaseApplication;
import defpackage.mrv;

/* loaded from: classes.dex */
public class GrsDownloadPluginUrl implements DownloadPluginUrl {

    /* renamed from: a, reason: collision with root package name */
    private final String f13118a;

    public GrsDownloadPluginUrl() {
        this((String) null);
    }

    public GrsDownloadPluginUrl(boolean z) {
        this(z ? "getHealthBatchPluginUrl" : null);
    }

    public GrsDownloadPluginUrl(String str) {
        this.f13118a = TextUtils.isEmpty(str) ? "getBatchPluginUrl" : str;
    }

    @Override // com.huawei.haf.download.DownloadPluginUrl
    public boolean isNetworkConnected() {
        return CommonUtil.aa(BaseApplication.getContext());
    }

    @Override // com.huawei.haf.download.DownloadPluginUrl
    public String getDownloadPluginUrl(String str, boolean z) {
        return e(str, null, z);
    }

    protected final String e(String str, String str2, boolean z) {
        String str3;
        String str4;
        if (TextUtils.isEmpty(str)) {
            str3 = z ? this.f13118a : "getLatestVersion";
        } else {
            str3 = str;
        }
        boolean z2 = true;
        boolean z3 = z && "getHealthBatchPluginUrl".equals(str3);
        if (z3) {
            str3 = "domainHealthCloudCommon";
        }
        GRSManager a2 = GRSManager.a(BaseApplication.getContext());
        String commonCountryCode = TextUtils.isEmpty(str2) ? a2.getCommonCountryCode() : str2;
        String noCheckUrl = a2.getNoCheckUrl(str3, commonCountryCode);
        String str5 = z3 ? "getHealthBatchPluginUrl" : str3;
        if (TextUtils.isEmpty(noCheckUrl)) {
            str4 = a(str5);
            z2 = false;
        } else {
            if (z3) {
                noCheckUrl = noCheckUrl + "/commonAbility/configCenter/";
            }
            str4 = noCheckUrl;
        }
        com.huawei.hwlogsmodel.LogUtil.a("Login_GrsDownloadPluginUrl", "getDownloadPluginUrl=", str4, ", key=", str5, ", countryCode=", commonCountryCode, ", batch=", Boolean.valueOf(z), ", grsResult=", Boolean.valueOf(z2));
        return str4;
    }

    protected String a(String str) {
        if ("getLatestVersion".equals(str)) {
            return mrv.f15136a;
        }
        return mrv.b;
    }
}
