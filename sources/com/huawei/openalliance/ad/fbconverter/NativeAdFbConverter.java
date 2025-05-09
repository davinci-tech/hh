package com.huawei.openalliance.ad.fbconverter;

import com.huawei.openalliance.ad.ea;
import com.huawei.openalliance.ad.inter.data.INativeAd;
import com.huawei.openalliance.ad.inter.data.VideoInfo;
import com.huawei.openalliance.ad.inter.data.e;

/* loaded from: classes9.dex */
public class NativeAdFbConverter {
    public static byte[] serialization(INativeAd iNativeAd) {
        return ea.a((e) iNativeAd);
    }

    public static INativeAd deserialization(byte[] bArr) {
        return a(ea.a(bArr));
    }

    private static e a(e eVar) {
        VideoInfo videoInfo = eVar.getVideoInfo();
        if (videoInfo != null) {
            videoInfo.e(0);
            videoInfo.e(videoInfo.getVideoAutoPlayWithSound());
        }
        eVar.a(videoInfo);
        return eVar;
    }
}
