package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.INativeAd;
import com.huawei.openalliance.ad.inter.data.ImageInfo;

/* loaded from: classes9.dex */
public class oo implements qo {

    /* renamed from: a, reason: collision with root package name */
    private fx f7380a;
    private ContentRecord b;
    private gc c;

    @Override // com.huawei.openalliance.ad.qo
    public String a(ImageInfo imageInfo, long j) {
        rt b = b(imageInfo, j);
        if (b != null) {
            ru a2 = this.f7380a.a(b);
            if (a2 != null) {
                return a2.a();
            }
            ho.c("BannerAdProcessor", "downloadBannerImg sourceResult is null");
        }
        return null;
    }

    private rt b(ImageInfo imageInfo, long j) {
        if (imageInfo == null) {
            return null;
        }
        rt rtVar = new rt();
        rtVar.c(imageInfo.getUrl());
        rtVar.b(imageInfo.getSha256());
        rtVar.b(imageInfo.isCheckSha256());
        rtVar.a(Long.valueOf(j));
        gc gcVar = this.c;
        rtVar.c(gcVar == null ? 52428800 : gcVar.e());
        rtVar.a(this.b);
        rtVar.c(true);
        return rtVar;
    }

    public oo(Context context, INativeAd iNativeAd) {
        this.f7380a = fb.a(context);
        this.c = fh.b(context);
        this.f7380a = fb.a(context);
        if (iNativeAd instanceof com.huawei.openalliance.ad.inter.data.e) {
            this.b = pd.a((com.huawei.openalliance.ad.inter.data.e) iNativeAd);
        }
    }
}
