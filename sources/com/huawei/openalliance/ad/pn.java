package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.metadata.ApkInfo;
import com.huawei.openalliance.ad.beans.metadata.Content;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.beans.metadata.Monitor;
import com.huawei.openalliance.ad.beans.metadata.VideoInfo;
import com.huawei.openalliance.ad.beans.metadata.v3.TemplateV3;
import com.huawei.openalliance.ad.beans.vast.VastContent;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.db.bean.EncryptionField;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.data.BiddingInfo;
import com.huawei.openalliance.ad.inter.data.ImageInfo;
import com.huawei.openalliance.ad.inter.data.RewardItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class pn {
    private static void a(com.huawei.openalliance.ad.inter.data.h hVar, MetaData metaData) {
        hVar.v(com.huawei.openalliance.ad.utils.cz.c(metaData.e()));
        hVar.w(com.huawei.openalliance.ad.utils.cz.c(metaData.f()));
        hVar.I(metaData.y());
        hVar.K(metaData.z());
        hVar.L(metaData.A());
        hVar.e(metaData.x());
        hVar.d(a(metaData.o()));
        hVar.e(a(metaData.g()));
        VideoInfo d = metaData.d();
        if (d != null) {
            hVar.a(new com.huawei.openalliance.ad.inter.data.VideoInfo(d));
        }
        hVar.x(metaData.h());
        hVar.d(metaData.G());
        hVar.d(metaData.i());
        hVar.h(metaData.j());
        hVar.f(com.huawei.openalliance.ad.utils.cz.c(metaData.k()));
        hVar.y(metaData.l());
        hVar.z(metaData.m());
        hVar.g(metaData.n());
        hVar.b(com.huawei.openalliance.ad.utils.cz.c(metaData.a()));
        hVar.e(metaData.N());
        ApkInfo r = metaData.r();
        if (r != null) {
            AppInfo appInfo = new AppInfo(r);
            appInfo.k(hVar.getIntent());
            appInfo.s(hVar.getUniqueId());
            appInfo.h(metaData.B());
            hVar.a(appInfo);
        }
        hVar.d(metaData.t());
    }

    private static void a(ContentRecord contentRecord, com.huawei.openalliance.ad.inter.data.h hVar) {
        if (contentRecord == null || hVar == null) {
            return;
        }
        hVar.a(contentRecord.aT());
        hVar.i(contentRecord.aV());
        hVar.f(Integer.valueOf(contentRecord.aO()));
        hVar.N(contentRecord.aN());
    }

    private static void a(Content content, com.huawei.openalliance.ad.inter.data.h hVar) {
        if (content == null || hVar == null) {
            return;
        }
        TemplateV3 W = content.W();
        if (W != null) {
            hVar.N(W.a());
            hVar.a(W.b());
            hVar.i(content.V());
        }
        hVar.f(content.U());
    }

    private static List<ImageInfo> a(List<com.huawei.openalliance.ad.beans.metadata.ImageInfo> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && !list.isEmpty()) {
            Iterator<com.huawei.openalliance.ad.beans.metadata.ImageInfo> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(new ImageInfo(it.next()));
            }
        }
        return arrayList;
    }

    public static com.huawei.openalliance.ad.inter.data.h a(String str, Content content, byte[] bArr, String str2) {
        com.huawei.openalliance.ad.inter.data.h hVar = new com.huawei.openalliance.ad.inter.data.h();
        hVar.a(content.a());
        hVar.D(str);
        hVar.c(content.g());
        hVar.g(content.l());
        hVar.a(content.k());
        hVar.b(content.j());
        hVar.a(content.f());
        hVar.c(content.H());
        hVar.h(content.z());
        hVar.e(content.h());
        hVar.E(content.A());
        hVar.J(com.huawei.openalliance.ad.utils.cz.c(content.b()));
        hVar.b(7);
        hVar.j(com.huawei.openalliance.ad.utils.cz.c(content.D()));
        hVar.k(com.huawei.openalliance.ad.utils.cz.c(content.E()));
        hVar.l(com.huawei.openalliance.ad.utils.cz.c(content.F()));
        hVar.d(content.ae().booleanValue());
        hVar.t(content.ad());
        BiddingInfo.a b = new BiddingInfo.a().a(content.ag()).a(content.ah()).setLurl(content.aj()).b(content.ai());
        if (!b.a().a()) {
            hVar.a(b.a());
        }
        a(content, hVar);
        List<String> n = content.n();
        if (n != null && n.size() > 0) {
            ArrayList arrayList = new ArrayList();
            Iterator<String> it = n.iterator();
            while (it.hasNext()) {
                arrayList.add(com.huawei.openalliance.ad.utils.cz.c(it.next()));
            }
            hVar.f(arrayList);
        }
        String m = content.m();
        if (m != null) {
            hVar.F(com.huawei.openalliance.ad.utils.f.a(m, bArr));
        }
        List<Monitor> p = content.p();
        if (p == null) {
            p = new ArrayList<>();
        }
        MetaData c = content.c();
        VastContent a2 = ps.a(c, 7, content.f());
        if (a2 != null) {
            ho.b("RewardAdConverter", "content:%s is vast ad, merge monitors", content.g());
            p = ps.a(p, ps.a(a2));
        }
        if (p.size() > 0) {
            EncryptionField encryptionField = new EncryptionField(List.class, Monitor.class);
            encryptionField.a((EncryptionField) p);
            hVar.G(encryptionField.b(bArr));
        }
        RewardItem C = content.C();
        if (C != null) {
            hVar.a(C);
        }
        hVar.i(content.r());
        hVar.g(content.u());
        hVar.B(content.v());
        hVar.C(content.w());
        hVar.A(content.d());
        if (c == null) {
            return hVar;
        }
        if (a2 != null) {
            ho.b("RewardAdConverter", "content:%s is vast ad, merge meta data", content.g());
            ps.a(c, a2, 7);
            hVar.A(com.huawei.openalliance.ad.utils.be.b(c));
            hVar.c(true);
        }
        a(hVar, c);
        String B = content.B();
        if (!TextUtils.isEmpty(B)) {
            EncryptionField encryptionField2 = new EncryptionField(String.class);
            encryptionField2.a((EncryptionField) B);
            hVar.H(encryptionField2.b(bArr));
        }
        hVar.b(content.I());
        hVar.a(content.e());
        hVar.p(content.R());
        hVar.M(str2);
        hVar.q(com.huawei.openalliance.ad.utils.cz.c(content.q()));
        hVar.b(c.K());
        hVar.r(content.X());
        hVar.s(com.huawei.openalliance.ad.utils.cz.c(content.Y()));
        hVar.c(content.Z());
        hVar.a(content.ac());
        hVar.f(content.ab());
        return hVar;
    }

    public static com.huawei.openalliance.ad.inter.data.h a(Context context, ContentRecord contentRecord) {
        if (contentRecord == null || context == null) {
            return null;
        }
        com.huawei.openalliance.ad.inter.data.h hVar = new com.huawei.openalliance.ad.inter.data.h();
        hVar.D(contentRecord.l());
        hVar.c(contentRecord.m());
        hVar.g(contentRecord.B());
        hVar.a(contentRecord.r());
        hVar.b(contentRecord.q());
        hVar.a(contentRecord.D());
        hVar.c(contentRecord.am());
        hVar.h(contentRecord.X());
        hVar.e(contentRecord.n());
        hVar.E(contentRecord.Y());
        hVar.J(com.huawei.openalliance.ad.utils.cz.c(contentRecord.f()));
        hVar.b(7);
        hVar.j(com.huawei.openalliance.ad.utils.cz.c(contentRecord.Z()));
        hVar.k(com.huawei.openalliance.ad.utils.cz.c(contentRecord.ak()));
        hVar.l(com.huawei.openalliance.ad.utils.cz.c(contentRecord.al()));
        hVar.i(contentRecord.ag());
        hVar.M(contentRecord.aH());
        List<String> I = contentRecord.I();
        if (I != null && I.size() > 0) {
            ArrayList arrayList = new ArrayList();
            Iterator<String> it = I.iterator();
            while (it.hasNext()) {
                arrayList.add(com.huawei.openalliance.ad.utils.cz.c(it.next()));
            }
            hVar.f(arrayList);
        }
        byte[] b = com.huawei.openalliance.ad.utils.cp.b(context);
        if (contentRecord.F() != null) {
            hVar.F(contentRecord.F().b(b));
        }
        EncryptionField<List<Monitor>> K = contentRecord.K();
        if (K != null) {
            hVar.G(K.b(b));
        }
        RewardItem ah = contentRecord.ah();
        if (ah != null) {
            hVar.a(ah);
        }
        hVar.i(contentRecord.O());
        hVar.g(contentRecord.M());
        hVar.B(contentRecord.U());
        hVar.C(contentRecord.V());
        hVar.A(contentRecord.g());
        MetaData h = contentRecord.h();
        if (h == null) {
            return hVar;
        }
        a(hVar, h);
        EncryptionField<String> ab = contentRecord.ab();
        if (ab != null) {
            hVar.H(ab.b(b));
        }
        hVar.b(com.huawei.openalliance.ad.utils.cz.h(contentRecord.an()));
        hVar.a(contentRecord.b(context));
        hVar.p(contentRecord.aG());
        hVar.q(com.huawei.openalliance.ad.utils.cz.c(contentRecord.N()));
        hVar.b(h.K());
        hVar.c(contentRecord.aS());
        hVar.a(contentRecord.aY());
        hVar.d(contentRecord.bc());
        hVar.t(contentRecord.bb());
        a(contentRecord, hVar);
        hVar.f(contentRecord.aW());
        hVar.u(contentRecord.bf());
        return hVar;
    }

    public static ContentRecord a(com.huawei.openalliance.ad.inter.data.h hVar) {
        if (hVar == null) {
            return null;
        }
        ContentRecord contentRecord = new ContentRecord();
        contentRecord.a(hVar.a());
        contentRecord.a(hVar.getMinEffectiveShowTime());
        contentRecord.a(hVar.getMinEffectiveShowRatio());
        contentRecord.b(hVar.r());
        contentRecord.d(hVar.q() == null ? 0 : hVar.q().intValue());
        contentRecord.c(hVar.getShowId());
        contentRecord.d(hVar.P());
        contentRecord.e(hVar.getContentId());
        contentRecord.d(hVar.getStartTime());
        contentRecord.c(hVar.getEndTime());
        contentRecord.g(hVar.O());
        contentRecord.f(hVar.getTaskId());
        contentRecord.t(hVar.R());
        contentRecord.u(hVar.getWhyThisAd());
        contentRecord.z(hVar.getAdChoiceUrl());
        contentRecord.A(hVar.getAdChoiceIcon());
        String S = hVar.S();
        if (!com.huawei.openalliance.ad.utils.cz.b(S)) {
            EncryptionField<String> encryptionField = new EncryptionField<>(String.class);
            encryptionField.a(S);
            contentRecord.a(encryptionField);
        }
        RewardItem rewardItem = hVar.getRewardItem();
        if (rewardItem != null) {
            contentRecord.a(rewardItem);
        }
        contentRecord.b(7);
        contentRecord.x(hVar.ab());
        contentRecord.k(hVar.F());
        contentRecord.i(hVar.B());
        contentRecord.m(hVar.getIntent());
        contentRecord.b(hVar.J());
        String T = hVar.T();
        if (!com.huawei.openalliance.ad.utils.cz.b(T)) {
            EncryptionField<List<Monitor>> encryptionField2 = new EncryptionField<>(List.class, Monitor.class);
            encryptionField2.a(T);
            contentRecord.b(encryptionField2);
        }
        contentRecord.l(hVar.L());
        contentRecord.e(hVar.K());
        contentRecord.r(hVar.N());
        contentRecord.s(hVar.getCtrlSwitchs());
        contentRecord.v(hVar.getUniqueId());
        String U = hVar.U();
        if (!TextUtils.isEmpty(U)) {
            EncryptionField<String> encryptionField3 = new EncryptionField<>(String.class);
            encryptionField3.a(U);
            contentRecord.c(encryptionField3);
        }
        contentRecord.a(hVar.isAutoDownloadApp());
        contentRecord.y(hVar.f());
        contentRecord.o(hVar.g());
        contentRecord.c(1);
        contentRecord.B(hVar.h() != null ? String.valueOf(hVar.h()) : null);
        contentRecord.D(hVar.i());
        contentRecord.E(hVar.j());
        contentRecord.G(hVar.k());
        contentRecord.H(hVar.l());
        contentRecord.d(hVar.m());
        contentRecord.J(hVar.n());
        contentRecord.K(hVar.af());
        contentRecord.f(hVar.p());
        contentRecord.d(hVar.s());
        contentRecord.o(com.huawei.openalliance.ad.utils.cz.d(hVar.u()));
        contentRecord.N(hVar.getAbilityDetailInfo());
        contentRecord.O(hVar.getHwChannelId());
        contentRecord.k(hVar.getCompliance());
        contentRecord.Q(hVar.c());
        contentRecord.L(hVar.ag());
        contentRecord.l(hVar.ah());
        contentRecord.a(hVar.ai());
        contentRecord.s(hVar.aj() == null ? -1 : hVar.aj().intValue());
        contentRecord.e(hVar.isTransparencyOpen());
        contentRecord.S(hVar.getTransparencyTplUrl());
        contentRecord.T(hVar.A());
        contentRecord.t(hVar.x());
        return contentRecord;
    }
}
