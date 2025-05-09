package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.beans.server.AdContentRsp;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public class dy {
    public static AdContentRsp a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            ho.c("AdRspFbSerializeUtil", "data is empty");
            return null;
        }
        try {
            vw a2 = vw.a(ByteBuffer.wrap(bArr));
            if (a2 == null) {
                ho.c("AdRspFbSerializeUtil", "from flat buffer, rsp is null.");
                return null;
            }
            ho.b("AdRspFbSerializeUtil", "parse adContentRsp from fb.");
            AdContentRsp adContentRsp = new AdContentRsp();
            adContentRsp.a(dx.a(a2.a(), -1));
            adContentRsp.a(a2.b());
            adContentRsp.a(dx.a(a2.c()));
            adContentRsp.b(dx.a(a2.d()));
            adContentRsp.c(dx.a(a2.f()));
            adContentRsp.d(dx.a(a2.e()));
            adContentRsp.b(a2.g());
            adContentRsp.b(dx.a(a2.i(), 800));
            adContentRsp.e(dx.a(a2.h()));
            adContentRsp.c(dx.b(a2.j()));
            adContentRsp.d(dx.b(a2.k()));
            adContentRsp.e(dx.b(a2.l()));
            adContentRsp.f(a2.m());
            if (ho.a()) {
                ho.a("AdRspFbSerializeUtil", "parse adContentRsp from fb end.");
                ho.a("AdRspFbSerializeUtil", "rsp: %s", com.huawei.openalliance.ad.utils.be.b(adContentRsp));
            }
            return adContentRsp;
        } catch (Throwable th) {
            ho.c("AdRspFbSerializeUtil", "get rsp from flat buffer ex: %s", th.getClass().getSimpleName());
            return null;
        }
    }
}
