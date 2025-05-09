package com.huawei.openalliance.ad.beans.metadata;

import com.huawei.openalliance.ad.utils.bg;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes5.dex */
public class AdSource implements Serializable {
    private static final long serialVersionUID = 30457300;
    private int displayPosition;
    private String dspLogo;
    private String dspName;

    public int c() {
        return this.displayPosition;
    }

    public void b(String str) {
        this.dspLogo = str;
    }

    public String b() {
        return this.dspLogo;
    }

    public void a(String str) {
        this.dspName = str;
    }

    public void a(int i) {
        this.displayPosition = i;
    }

    public String a() {
        return this.dspName;
    }

    private static boolean b(AdSource adSource) {
        return adSource != null && adSource.c() == 2;
    }

    public static AdSource b(List<AdSource> list) {
        if (bg.a(list)) {
            return null;
        }
        for (AdSource adSource : list) {
            if (b(adSource)) {
                return adSource;
            }
        }
        return null;
    }

    private static boolean a(AdSource adSource) {
        return adSource != null && adSource.c() == 1;
    }

    public static AdSource a(List<AdSource> list) {
        if (bg.a(list)) {
            return null;
        }
        for (AdSource adSource : list) {
            if (a(adSource)) {
                return adSource;
            }
        }
        return null;
    }
}
