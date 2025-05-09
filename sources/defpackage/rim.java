package defpackage;

import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Services;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes7.dex */
public class rim {
    public static final String d(int i, String str) {
        cvc pluginInfoByDeviceType = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByDeviceType(i);
        if (pluginInfoByDeviceType == null) {
            LogUtil.a("LegalInformationHelper", "getUserAgreementUrl descriptionInfo is null");
            return "";
        }
        cvj f = pluginInfoByDeviceType.f();
        if (f == null) {
            LogUtil.a("LegalInformationHelper", "getUserAgreementUrl pluginInfoForWear is null");
            return "";
        }
        Map<String, String> ba = f.ba();
        if (ba == null) {
            LogUtil.a("LegalInformationHelper", "getUserAgreementUrl legalInformation is null");
            return "";
        }
        String str2 = ba.get(str);
        LogUtil.a("LegalInformationHelper", "getLegalUrl key: ", str, " url: ", str2);
        return str2;
    }

    public static String b(int i, String str, String str2, String str3) {
        if (i == 84) {
            return String.format(Locale.ENGLISH, str, "CN", "zh-CN");
        }
        return String.format(Locale.ENGLISH, str, str2, str3);
    }
}
