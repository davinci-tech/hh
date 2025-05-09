package com.huawei.openalliance.ad;

import com.google.flatbuffers.FlatBufferBuilder;
import com.huawei.openalliance.ad.beans.server.AdContentReq;

/* loaded from: classes5.dex */
public class dw {
    public static byte[] a(AdContentReq adContentReq) {
        String str;
        if (adContentReq == null) {
            str = "toFlatBufferBytes adContentReq is null";
        } else {
            try {
                FlatBufferBuilder flatBufferBuilder = new FlatBufferBuilder(0);
                flatBufferBuilder.finish(vf.a(flatBufferBuilder, dv.a(adContentReq.F()), -1111L, dv.a(adContentReq.f(), flatBufferBuilder), 0, -1111, -1111, vf.a(flatBufferBuilder, dv.a(adContentReq.k(), flatBufferBuilder)), 0, dv.a(adContentReq.g(), flatBufferBuilder), 0, dv.a(adContentReq.h(), flatBufferBuilder), dv.a(adContentReq.i(), flatBufferBuilder), dv.a(adContentReq.j(), flatBufferBuilder), vf.b(flatBufferBuilder, ec.a(adContentReq.l(), flatBufferBuilder)), vf.c(flatBufferBuilder, ec.a(adContentReq.m(), flatBufferBuilder)), dv.a(adContentReq.n(), flatBufferBuilder), adContentReq.p(), vf.d(flatBufferBuilder, ec.a(adContentReq.q(), flatBufferBuilder)), dv.a(adContentReq.r(), flatBufferBuilder), adContentReq.o(), adContentReq.d(), dv.a(adContentReq.s()), dv.a(adContentReq.t()), dv.a(adContentReq.u()), dv.a(adContentReq.v()), dv.a(adContentReq.w(), flatBufferBuilder), dv.a(adContentReq.x(), flatBufferBuilder), dv.a(adContentReq.y(), flatBufferBuilder), dv.a(adContentReq.z(), flatBufferBuilder), dv.a(adContentReq.A()), dv.a(adContentReq.B(), flatBufferBuilder), dv.a(adContentReq.C(), flatBufferBuilder), dv.a(adContentReq.D(), flatBufferBuilder), adContentReq.e(), 0, 0, 0, 0, 0, 0, vf.e(flatBufferBuilder, dv.a(adContentReq.E(), flatBufferBuilder)), dv.a(adContentReq.G(), flatBufferBuilder), vf.f(flatBufferBuilder, dv.b(adContentReq.H(), flatBufferBuilder)), vf.g(flatBufferBuilder, ec.a(adContentReq.I(), flatBufferBuilder)), vf.h(flatBufferBuilder, ec.a(adContentReq.J(), flatBufferBuilder)), dv.a(adContentReq.K()), dv.a(adContentReq.L()), dv.a(adContentReq.M()), vf.i(flatBufferBuilder, adContentReq.N())));
                return flatBufferBuilder.sizedByteArray();
            } catch (Throwable th) {
                str = "toFlatBuffersBytes error:" + th.getClass().getSimpleName();
            }
        }
        ho.c("AdReqFbSerializeUtil", str);
        return null;
    }
}
