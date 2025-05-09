package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.metadata.ApkInfo;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.beans.metadata.Monitor;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.db.bean.EncryptionField;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.data.LinkedSplashAd;
import com.huawei.openalliance.ad.inter.data.VideoInfo;
import java.util.List;

/* loaded from: classes5.dex */
public class oz {
    public static LinkedSplashAd a(Context context, ContentRecord contentRecord) {
        LinkedSplashAd linkedSplashAd = new LinkedSplashAd();
        linkedSplashAd.a(contentRecord);
        linkedSplashAd.i(contentRecord.ag());
        linkedSplashAd.a(Integer.valueOf(contentRecord.a()));
        linkedSplashAd.b(1);
        linkedSplashAd.a(contentRecord.ae());
        linkedSplashAd.h(contentRecord.M());
        linkedSplashAd.f(contentRecord.I());
        linkedSplashAd.a(contentRecord.D());
        linkedSplashAd.c(contentRecord.m());
        linkedSplashAd.g(contentRecord.J());
        linkedSplashAd.C(contentRecord.A());
        linkedSplashAd.P(contentRecord.ad());
        linkedSplashAd.J(contentRecord.l());
        linkedSplashAd.h(contentRecord.j());
        linkedSplashAd.b(contentRecord.q());
        linkedSplashAd.a(contentRecord.r());
        linkedSplashAd.i(contentRecord.X());
        linkedSplashAd.e(contentRecord.n());
        linkedSplashAd.M(contentRecord.Y());
        linkedSplashAd.j(contentRecord.Z());
        if (contentRecord.F() != null) {
            linkedSplashAd.z(contentRecord.F().a());
        }
        linkedSplashAd.j(contentRecord.B());
        linkedSplashAd.g(contentRecord.G());
        linkedSplashAd.F(contentRecord.g());
        if (contentRecord.K() != null) {
            linkedSplashAd.G(contentRecord.K().a());
        }
        linkedSplashAd.l(contentRecord.O());
        linkedSplashAd.H(contentRecord.U());
        linkedSplashAd.I(contentRecord.V());
        if (contentRecord.ab() != null) {
            linkedSplashAd.N(contentRecord.ab().a());
        }
        linkedSplashAd.a(new VideoInfo(contentRecord.R()));
        MetaData h = contentRecord.h();
        if (h != null) {
            linkedSplashAd.A(com.huawei.openalliance.ad.utils.cz.c(h.e()));
            linkedSplashAd.B(com.huawei.openalliance.ad.utils.cz.c(h.f()));
            linkedSplashAd.C(h.h());
            linkedSplashAd.d(h.G());
            linkedSplashAd.d(h.i());
            linkedSplashAd.k(h.j());
            linkedSplashAd.f(com.huawei.openalliance.ad.utils.cz.c(contentRecord.h().k()));
            linkedSplashAd.D(h.l());
            linkedSplashAd.E(h.m());
            linkedSplashAd.g(h.n());
            linkedSplashAd.b(com.huawei.openalliance.ad.utils.cz.c(h.a()));
            linkedSplashAd.P(h.A());
            ApkInfo r = h.r();
            if (r != null) {
                AppInfo appInfo = new AppInfo(r);
                appInfo.k(linkedSplashAd.getIntent());
                appInfo.s(linkedSplashAd.getUniqueId());
                appInfo.h(h.B());
                linkedSplashAd.a(appInfo);
            }
            linkedSplashAd.d(h.t());
        }
        linkedSplashAd.g(contentRecord.o());
        linkedSplashAd.v(contentRecord.P());
        linkedSplashAd.q(contentRecord.N());
        linkedSplashAd.x(contentRecord.f());
        linkedSplashAd.y(contentRecord.L());
        linkedSplashAd.h(contentRecord.ai());
        linkedSplashAd.i(contentRecord.aj());
        linkedSplashAd.w(contentRecord.y());
        linkedSplashAd.a(contentRecord.b(context));
        linkedSplashAd.p(contentRecord.aG());
        linkedSplashAd.c(contentRecord.aM());
        linkedSplashAd.a(contentRecord.aY());
        linkedSplashAd.d(contentRecord.bc());
        linkedSplashAd.t(contentRecord.bb());
        linkedSplashAd.u(contentRecord.bf());
        linkedSplashAd.f(contentRecord.aW());
        return linkedSplashAd;
    }

    public static ContentRecord a(LinkedSplashAd linkedSplashAd) {
        if (linkedSplashAd == null) {
            return null;
        }
        ContentRecord contentRecord = new ContentRecord();
        contentRecord.c(linkedSplashAd.getShowId());
        contentRecord.a(linkedSplashAd.a());
        contentRecord.d(linkedSplashAd.getSlotId());
        contentRecord.e(linkedSplashAd.getContentId());
        contentRecord.d(linkedSplashAd.getStartTime());
        contentRecord.c(linkedSplashAd.getEndTime());
        contentRecord.g(linkedSplashAd.U());
        contentRecord.f(linkedSplashAd.getTaskId());
        contentRecord.t(linkedSplashAd.ac());
        contentRecord.u(linkedSplashAd.getWhyThisAd());
        contentRecord.k(linkedSplashAd.getCreativeType());
        String K = linkedSplashAd.K();
        if (!com.huawei.openalliance.ad.utils.cz.b(K)) {
            EncryptionField<String> encryptionField = new EncryptionField<>(String.class);
            encryptionField.a(K);
            contentRecord.a(encryptionField);
        }
        contentRecord.b(1);
        contentRecord.k(linkedSplashAd.getLandWebUrl());
        contentRecord.i(linkedSplashAd.getInterActionType());
        contentRecord.m(linkedSplashAd.getIntent());
        contentRecord.b(linkedSplashAd.O());
        String P = linkedSplashAd.P();
        if (!com.huawei.openalliance.ad.utils.cz.b(P)) {
            EncryptionField<List<Monitor>> encryptionField2 = new EncryptionField<>(List.class, Monitor.class);
            encryptionField2.a(P);
            contentRecord.b(encryptionField2);
        }
        contentRecord.l(linkedSplashAd.Q());
        contentRecord.e(linkedSplashAd.R());
        contentRecord.r(linkedSplashAd.T());
        contentRecord.s(linkedSplashAd.getCtrlSwitchs());
        contentRecord.v(linkedSplashAd.getUniqueId());
        String ad = linkedSplashAd.ad();
        if (!TextUtils.isEmpty(ad)) {
            EncryptionField<String> encryptionField3 = new EncryptionField<>(String.class);
            encryptionField3.a(ad);
            contentRecord.c(encryptionField3);
        }
        contentRecord.a(linkedSplashAd.isAutoDownloadApp());
        contentRecord.x(linkedSplashAd.af());
        contentRecord.y(linkedSplashAd.f());
        contentRecord.a(linkedSplashAd.getAdCloseKeyWords());
        contentRecord.b(linkedSplashAd.N());
        contentRecord.p(linkedSplashAd.C());
        contentRecord.o(linkedSplashAd.u());
        contentRecord.e(linkedSplashAd.B());
        contentRecord.a(linkedSplashAd.E());
        contentRecord.n(linkedSplashAd.F());
        contentRecord.m(linkedSplashAd.G());
        contentRecord.n(linkedSplashAd.H());
        contentRecord.i(linkedSplashAd.D());
        contentRecord.d(linkedSplashAd.m());
        contentRecord.J(linkedSplashAd.n());
        contentRecord.d(linkedSplashAd.s());
        contentRecord.Q(linkedSplashAd.c());
        contentRecord.T(linkedSplashAd.A());
        contentRecord.t(linkedSplashAd.x());
        return contentRecord;
    }
}
