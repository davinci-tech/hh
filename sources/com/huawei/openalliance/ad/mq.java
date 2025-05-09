package com.huawei.openalliance.ad;

import com.iab.omid.library.huawei.adsession.media.Position;
import com.iab.omid.library.huawei.adsession.media.VastProperties;

/* loaded from: classes5.dex */
public class mq {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f7271a = ma.a("com.iab.omid.library.huawei.adsession.media.VastProperties");
    private final boolean b = false;
    private final boolean c;
    private final VastProperties d;
    private final mp e;
    private Float f;

    public VastProperties b() {
        return this.d;
    }

    public static boolean a() {
        return f7271a;
    }

    public static mq a(float f, boolean z, mp mpVar) {
        Position a2;
        return new mq(f, z, mpVar, (mpVar == null || !a() || (a2 = mp.a(mpVar)) == null) ? null : VastProperties.createVastPropertiesForSkippableMedia(f, z, a2));
    }

    private mq(float f, boolean z, mp mpVar, VastProperties vastProperties) {
        this.f = Float.valueOf(0.0f);
        this.f = Float.valueOf(f);
        this.c = z;
        this.e = mpVar;
        this.d = vastProperties;
    }
}
