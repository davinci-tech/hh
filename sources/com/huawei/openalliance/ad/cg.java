package com.huawei.openalliance.ad;

import android.content.Context;
import android.view.View;
import com.hihonor.android.util.HwNotchSizeUtil;
import com.huawei.openalliance.ad.beans.inner.CountryCodeBean;
import com.huawei.openalliance.ad.utils.as;

/* loaded from: classes5.dex */
public class cg extends cd {
    private static co c;
    private static final byte[] d = new byte[0];

    @Override // com.huawei.openalliance.ad.cd
    protected String s() {
        return "msc.build.platform.version";
    }

    @Override // com.huawei.openalliance.ad.cc, com.huawei.openalliance.ad.co
    public boolean r() {
        return "true".equalsIgnoreCase(com.huawei.openalliance.ad.utils.dd.a("msc.pure_mode.enable"));
    }

    @Override // com.huawei.openalliance.ad.cc, com.huawei.openalliance.ad.co
    public String q() {
        return com.huawei.openalliance.ad.utils.dd.a(CountryCodeBean.VENDORCOUNTRY_SYSTEMPROP_HN);
    }

    @Override // com.huawei.openalliance.ad.cc, com.huawei.openalliance.ad.co
    public String p() {
        return com.huawei.openalliance.ad.utils.dd.a(CountryCodeBean.VENDOR_SYSTEMPROP_HN);
    }

    @Override // com.huawei.openalliance.ad.cc, com.huawei.openalliance.ad.co
    public Integer h() {
        return Integer.valueOf(as.a.b);
    }

    @Override // com.huawei.openalliance.ad.cc, com.huawei.openalliance.ad.co
    public boolean a() {
        return "156".equals(com.huawei.openalliance.ad.utils.dd.a("msc.config.optb"));
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
            ho.c("HnDeviceImpl", sb.toString());
            return 0;
        } catch (Throwable th) {
            e = th;
            sb = new StringBuilder("getNotchHeight error:");
            sb.append(e.getClass().getSimpleName());
            ho.c("HnDeviceImpl", sb.toString());
            return 0;
        }
    }

    private static co c(Context context) {
        co coVar;
        synchronized (d) {
            if (c == null) {
                c = new cg(context);
            }
            coVar = c;
        }
        return coVar;
    }

    public static co b(Context context) {
        return c(context);
    }

    private cg(Context context) {
        super(context);
    }
}
