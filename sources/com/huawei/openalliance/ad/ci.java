package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.hihonor.common.grs.HihonorGrsBaseInfo;
import com.hihonor.common.grs.HihonorGrsClient;
import com.huawei.openalliance.ad.beans.inner.CountryCodeBean;
import java.util.Map;

/* loaded from: classes5.dex */
public class ci implements cp {

    /* renamed from: a, reason: collision with root package name */
    private Context f6681a;
    private HihonorGrsBaseInfo b = new HihonorGrsBaseInfo();

    @Override // com.huawei.openalliance.ad.cp
    public void b() {
    }

    @Override // com.huawei.openalliance.ad.cp
    public Map<String, String> c(String str) {
        return new HihonorGrsClient(this.f6681a, this.b).synGetGrsUrls(str);
    }

    @Override // com.huawei.openalliance.ad.cp
    public void b(String str) {
        this.b.setSerCountry(str);
    }

    @Override // com.huawei.openalliance.ad.cp
    public void a(String str) {
        this.b.setAppName(str);
    }

    @Override // com.huawei.openalliance.ad.cp
    public String a() {
        String b = com.huawei.openalliance.ad.utils.ct.a().b();
        boolean equalsIgnoreCase = "CN".equalsIgnoreCase(b);
        ho.a("HwGrsImpl", "init country code: %s ", b);
        return (bz.b(this.f6681a) || !equalsIgnoreCase) ? (TextUtils.isEmpty(b) || CountryCodeBean.OVERSEAS.equalsIgnoreCase(b)) ? new CountryCodeBean(this.f6681a).a() : b : b;
    }

    public ci(Context context) {
        this.f6681a = context.getApplicationContext();
    }
}
