package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.ads.VideoConfiguration;
import com.huawei.openalliance.ad.constant.ClickDestination;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.openalliance.ad.inter.data.AdLandingPageData;
import com.huawei.openalliance.ad.inter.listeners.LandingPageAction;
import java.util.Map;

/* loaded from: classes5.dex */
public class su extends ta {
    private final boolean c;
    private gz d;

    protected boolean a(AdLandingPageData adLandingPageData) {
        Throwable th;
        boolean z;
        LandingPageAction b = HiAd.a(this.f7529a).b();
        if (b == null || os.d(this.b.V())) {
            if (!os.b(this.b.V()) && !com.huawei.openalliance.ad.utils.bv.e(this.f7529a)) {
                return b();
            }
            b(ClickDestination.WEB);
            bx.a(this.f7529a, adLandingPageData, this.d, this.b);
            return true;
        }
        try {
            z = b.openLandingPage(this.f7529a, adLandingPageData);
            try {
                b(ClickDestination.WEB);
            } catch (Throwable th2) {
                th = th2;
                ho.c("InnerWebAction", "openLandingPage Exception");
                ho.a(5, th);
                new com.huawei.openalliance.ad.analysis.c(this.f7529a).a(th);
                return z ? true : true;
            }
        } catch (Throwable th3) {
            th = th3;
            z = false;
        }
        if (z && !b()) {
            return false;
        }
    }

    @Override // com.huawei.openalliance.ad.ta
    public boolean a() {
        if (this.b == null) {
            return b();
        }
        ho.b("InnerWebAction", "handle inner web action");
        AdLandingPageData adLandingPageData = new AdLandingPageData(this.b, this.f7529a, this.c);
        return TextUtils.isEmpty(adLandingPageData.getLandingUrl()) ? b() : a(adLandingPageData);
    }

    private void a(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        this.d.a(this.b.R());
        this.d.a(this.b.Q());
        this.d.a(this.b.ae());
        String orDefault = map.getOrDefault(MapKeyNames.LINKED_CUSTOM_SHOW_ID, String.valueOf(0));
        String orDefault2 = map.getOrDefault(MapKeyNames.LINKED_CUSTOM_RETURN_VIDEO_DIRECT, "false");
        String orDefault3 = map.getOrDefault(MapKeyNames.LINKED_CUSTOM_VIDEO_PROGRESS, null);
        String orDefault4 = map.getOrDefault(MapKeyNames.LINKED_CUSTOM_VIDEO_SOUND_SWITCH, "n");
        String orDefault5 = map.getOrDefault(MapKeyNames.LINKED_SPLASH_MEDIA_PATH, null);
        String orDefault6 = map.getOrDefault(MapKeyNames.USE_TEMPLATE, "false");
        this.d.c(orDefault);
        Integer h = com.huawei.openalliance.ad.utils.cz.h(orDefault3);
        if (h != null) {
            this.d.a(h.intValue());
            ho.b("InnerWebAction", "set progress from native view " + h);
        } else {
            this.d.a(0);
        }
        if (!TextUtils.isEmpty(orDefault6)) {
            this.d.d(orDefault6);
        }
        this.d.a(orDefault4);
        this.d.a("true".equals(orDefault2));
        this.d.b(orDefault5);
        String str = map.containsKey(MapKeyNames.AUTO_PLAY_VIDEO_NETWORK) ? map.get(MapKeyNames.AUTO_PLAY_VIDEO_NETWORK) : null;
        String str2 = map.containsKey(MapKeyNames.PLAY_VIDEO_IS_MUTE) ? map.get(MapKeyNames.PLAY_VIDEO_IS_MUTE) : "true";
        if (str != null) {
            this.d.a(new VideoConfiguration.Builder().setAutoPlayNetwork(com.huawei.openalliance.ad.utils.cz.a(str, 0)).setMute(Boolean.getBoolean(str2)).build());
        }
    }

    public su(Context context, ContentRecord contentRecord, boolean z, Map<String, String> map) {
        super(context, contentRecord);
        this.d = new gz();
        this.c = z;
        a(map);
    }
}
