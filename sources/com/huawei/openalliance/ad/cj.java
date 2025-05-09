package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.huawei.android.util.HwNotchSizeUtil;
import com.huawei.openalliance.ad.beans.inner.CountryCodeBean;
import com.huawei.openalliance.ad.utils.as;

/* loaded from: classes5.dex */
public class cj extends cd {
    private static co c;
    private static final byte[] d = new byte[0];

    @Override // com.huawei.openalliance.ad.cd
    protected String s() {
        return "hw_sc.build.platform.version";
    }

    @Override // com.huawei.openalliance.ad.cc, com.huawei.openalliance.ad.co
    public boolean r() {
        return "true".equalsIgnoreCase(com.huawei.openalliance.ad.utils.dd.a("hw_mc.pure_mode.enable"));
    }

    @Override // com.huawei.openalliance.ad.cc, com.huawei.openalliance.ad.co
    public String q() {
        return com.huawei.openalliance.ad.utils.dd.a(CountryCodeBean.VENDORCOUNTRY_SYSTEMPROP);
    }

    @Override // com.huawei.openalliance.ad.cc, com.huawei.openalliance.ad.co
    public String p() {
        return com.huawei.openalliance.ad.utils.dd.a(CountryCodeBean.VENDOR_SYSTEMPROP);
    }

    @Override // com.huawei.openalliance.ad.cc, com.huawei.openalliance.ad.co
    public boolean m() {
        String bI = fh.b(this.f6674a).bI();
        if (TextUtils.isEmpty(bI)) {
            return false;
        }
        ho.a("HwDeviceImpl", "device in test mode, countryCode:%s", bI);
        return true;
    }

    @Override // com.huawei.openalliance.ad.cc, com.huawei.openalliance.ad.co
    public boolean l() {
        return com.huawei.openalliance.ad.utils.x.e(this.f6674a);
    }

    @Override // com.huawei.openalliance.ad.cc, com.huawei.openalliance.ad.co
    public Integer h() {
        return Integer.valueOf(as.a.f7593a);
    }

    @Override // com.huawei.openalliance.ad.cc, com.huawei.openalliance.ad.co
    public boolean a() {
        return "156".equals(com.huawei.openalliance.ad.utils.dd.a("ro.config.hw_optb"));
    }

    @Override // com.huawei.openalliance.ad.cc, com.huawei.openalliance.ad.co
    public int a(View view) {
        StringBuilder sb;
        try {
            if (!HwNotchSizeUtil.hasNotchInScreen()) {
                return 0;
            }
            int[] notchSize = HwNotchSizeUtil.getNotchSize();
            if (notchSize.length >= 2) {
                return notchSize[1];
            }
            return 0;
        } catch (Exception e) {
            e = e;
            sb = new StringBuilder("getNotchHeight error:");
            sb.append(e.getClass().getSimpleName());
            ho.c("HwDeviceImpl", sb.toString());
            return 0;
        } catch (Throwable th) {
            e = th;
            sb = new StringBuilder("getNotchHeight error:");
            sb.append(e.getClass().getSimpleName());
            ho.c("HwDeviceImpl", sb.toString());
            return 0;
        }
    }

    private static co c(Context context) {
        co coVar;
        synchronized (d) {
            if (c == null) {
                c = new cj(context);
            }
            coVar = c;
        }
        return coVar;
    }

    public static co b(Context context) {
        return c(context);
    }

    private cj(Context context) {
        super(context);
    }
}
