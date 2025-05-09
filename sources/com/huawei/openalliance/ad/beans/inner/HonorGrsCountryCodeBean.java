package com.huawei.openalliance.ad.beans.inner;

import android.content.Context;
import com.hihonor.common.grs.HihonorGrsApp;
import com.huawei.openalliance.ad.ho;
import java.security.InvalidParameterException;

/* loaded from: classes9.dex */
public class HonorGrsCountryCodeBean {
    private static final String TAG = "HonorGrsCountryCodeBean";
    private static final String UNKNOWN = "UNKNOWN";
    private String countryCode = "UNKNOWN";

    public String a(Context context) {
        try {
            String issueCountryCode = HihonorGrsApp.getInstance().getIssueCountryCode(context);
            this.countryCode = issueCountryCode;
            return issueCountryCode;
        } catch (Throwable th) {
            ho.c(TAG, "getIssueCountryCode via grs sdk: %s", th.getClass().getSimpleName());
            throw new InvalidParameterException();
        }
    }
}
