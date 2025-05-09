package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.GrsClient;
import com.huawei.openalliance.ad.beans.inner.CountryCodeBean;
import com.huawei.openalliance.ad.constant.FlavorConstants;
import java.util.Map;

/* loaded from: classes5.dex */
public class ck implements cp {

    /* renamed from: a, reason: collision with root package name */
    private GrsBaseInfo f6682a = new GrsBaseInfo();
    private Context b;

    @Override // com.huawei.openalliance.ad.cp
    public Map<String, String> c(String str) {
        return new GrsClient(this.b, this.f6682a).synGetGrsUrls(FlavorConstants.HIAD_GRS_SERVICE_NAME);
    }

    @Override // com.huawei.openalliance.ad.cp
    public void b(String str) {
        this.f6682a.setSerCountry(str);
    }

    @Override // com.huawei.openalliance.ad.cp
    public void b() {
        ho.b("HwGrsImpl", "forceExpire");
        try {
            new GrsClient(this.b, this.f6682a).forceExpire();
        } catch (Throwable th) {
            ho.c("HwGrsImpl", "GrsClient.forceExpire, exception: %s", th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.cp
    public void a(String str) {
        this.f6682a.setAppName(str);
    }

    @Override // com.huawei.openalliance.ad.cp
    public String a() {
        String b = com.huawei.openalliance.ad.utils.ct.a().b();
        String c = com.huawei.openalliance.ad.utils.ct.a().c();
        boolean equalsIgnoreCase = "CN".equalsIgnoreCase(b);
        ho.a("HwGrsImpl", "init country code: %s ", b);
        ho.a("HwGrsImpl", "media country code: %s ", c);
        return (bz.b(this.b) || !equalsIgnoreCase) ? !TextUtils.isEmpty(c) ? c : (TextUtils.isEmpty(b) || CountryCodeBean.OVERSEAS.equalsIgnoreCase(b)) ? new CountryCodeBean(this.b).a() : b : b;
    }

    public ck(Context context) {
        this.b = context.getApplicationContext();
    }
}
