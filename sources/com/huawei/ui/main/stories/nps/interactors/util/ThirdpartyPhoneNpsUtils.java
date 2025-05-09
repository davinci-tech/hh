package com.huawei.ui.main.stories.nps.interactors.util;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/* loaded from: classes7.dex */
public class ThirdpartyPhoneNpsUtils {
    private static final Set<String> SUPPORT_STHIRD_PARTY_SYSTEM_DEVICE_CERT_MODEL = Collections.unmodifiableSet(new HashSet<String>() { // from class: com.huawei.ui.main.stories.nps.interactors.util.ThirdpartyPhoneNpsUtils.1
        private static final long serialVersionUID = -6381302715026856076L;

        {
            add("xxx");
        }
    });
    private static final String TAG = "ThirdpartyPhoneNpsUtils";
    private static final String TAG_RELEASE = "Nps_ThirdpartyPhoneNpsUtils";

    public static boolean isSupportThirdPartyPhone(String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d(TAG_RELEASE, "isSupportThirdPartyModel CertModel empty");
            return false;
        }
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        LogUtil.a(TAG, "isSupportThirdPartyModel model is ", upperCase);
        if (SUPPORT_STHIRD_PARTY_SYSTEM_DEVICE_CERT_MODEL.contains(upperCase)) {
            ReleaseLogUtil.e(TAG_RELEASE, "isSupportThirdPartyModel true, cause certModel");
            return true;
        }
        ReleaseLogUtil.e(TAG_RELEASE, "isSupportThirdPartyModel false");
        return false;
    }
}
