package com.huawei.openalliance.ad.beans.inner;

import android.content.Context;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.aj;
import com.huawei.openalliance.ad.utils.x;
import java.util.Locale;

/* loaded from: classes9.dex */
public class HonorCountryCodeBean extends CountryCodeBean {
    private static final String TAG = "HonorCountryCodeBean";
    private static boolean isHonorGrsAvailable = aj.d();

    @Override // com.huawei.openalliance.ad.beans.inner.CountryCodeBean
    protected void a(Context context, boolean z) {
        if (isHonorGrsAvailable && bz.b(context) && !x.n(context)) {
            try {
                this.countryCode = new HonorGrsCountryCodeBean().a(context);
            } catch (Throwable th) {
                ho.c(TAG, "getIssueCountryCode via grs sdk: %s", th.getClass().getSimpleName());
            }
            this.countryCode = this.countryCode.toUpperCase(Locale.ENGLISH);
        }
        b(context, z);
        this.countryCode = this.countryCode.toUpperCase(Locale.ENGLISH);
    }
}
