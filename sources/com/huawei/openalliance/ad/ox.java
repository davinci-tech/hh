package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.metadata.ApkInfo;
import com.huawei.openalliance.ad.beans.metadata.Content;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.beans.metadata.Monitor;
import com.huawei.openalliance.ad.beans.metadata.VideoInfo;
import com.huawei.openalliance.ad.beans.metadata.v3.TemplateData;
import com.huawei.openalliance.ad.beans.metadata.v3.TemplateV3;
import com.huawei.openalliance.ad.beans.vast.VastContent;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.db.bean.EncryptionField;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.data.ImageInfo;
import com.huawei.openalliance.ad.inter.data.RewardItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class ox {
    private static void a(com.huawei.openalliance.ad.inter.data.d dVar, MetaData metaData) {
        dVar.w(com.huawei.openalliance.ad.utils.cz.c(metaData.e()));
        dVar.x(com.huawei.openalliance.ad.utils.cz.c(metaData.f()));
        dVar.I(metaData.y());
        dVar.J(metaData.z());
        dVar.K(metaData.A());
        dVar.e(metaData.x());
        dVar.d(a(metaData.o()));
        VideoInfo d = metaData.d();
        if (d != null) {
            dVar.a(new com.huawei.openalliance.ad.inter.data.VideoInfo(d));
        }
        dVar.y(metaData.h());
        dVar.d(metaData.G());
        dVar.d(metaData.i());
        dVar.h(metaData.j());
        dVar.f(com.huawei.openalliance.ad.utils.cz.c(metaData.k()));
        dVar.z(metaData.l());
        dVar.A(metaData.m());
        dVar.g(metaData.n());
        dVar.b(com.huawei.openalliance.ad.utils.cz.c(metaData.a()));
        dVar.b(metaData.K());
        dVar.e(metaData.N());
        ApkInfo r = metaData.r();
        if (r != null) {
            AppInfo appInfo = new AppInfo(r);
            appInfo.k(dVar.getIntent());
            appInfo.s(dVar.getUniqueId());
            appInfo.h(metaData.B());
            dVar.a(appInfo);
        }
        dVar.d(metaData.t());
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

    public static com.huawei.openalliance.ad.inter.data.d a(String str, Content content, byte[] bArr) {
        com.huawei.openalliance.ad.inter.data.d dVar = new com.huawei.openalliance.ad.inter.data.d();
        dVar.F(str);
        dVar.f(content.U());
        dVar.a(content.a());
        dVar.c(content.g());
        dVar.g(content.l());
        dVar.a(content.k());
        dVar.b(content.j());
        dVar.a(content.f());
        dVar.f(content.z());
        dVar.e(content.h());
        dVar.G(content.A());
        dVar.b(12);
        dVar.j(com.huawei.openalliance.ad.utils.cz.c(content.D()));
        dVar.k(com.huawei.openalliance.ad.utils.cz.c(content.E()));
        dVar.l(com.huawei.openalliance.ad.utils.cz.c(content.F()));
        dVar.q(com.huawei.openalliance.ad.utils.cz.c(content.q()));
        dVar.d(content.ae().booleanValue());
        dVar.t(content.ad());
        dVar.f(content.ab());
        List<String> n = content.n();
        if (n != null && n.size() > 0) {
            ArrayList arrayList = new ArrayList();
            Iterator<String> it = n.iterator();
            while (it.hasNext()) {
                arrayList.add(com.huawei.openalliance.ad.utils.cz.c(it.next()));
            }
            dVar.e(arrayList);
        }
        String m = content.m();
        if (m != null) {
            dVar.v(com.huawei.openalliance.ad.utils.f.a(m, bArr));
        }
        List<Monitor> p = content.p();
        if (p == null) {
            p = new ArrayList<>();
        }
        MetaData c = content.c();
        VastContent a2 = ps.a(c, 12, content.f());
        if (a2 != null) {
            ho.b("InterstitialAdConverter", "content:%s is vast ad, merge monitors", content.g());
            p = ps.a(p, ps.a(a2));
        }
        if (p.size() > 0) {
            EncryptionField encryptionField = new EncryptionField(List.class, Monitor.class);
            encryptionField.a((EncryptionField) p);
            dVar.C(encryptionField.b(bArr));
        }
        RewardItem C = content.C();
        if (C != null) {
            dVar.a(C);
        }
        dVar.i(content.r());
        dVar.h(content.u());
        dVar.D(content.v());
        dVar.E(content.w());
        dVar.B(content.d());
        if (c == null) {
            return dVar;
        }
        if (a2 != null) {
            ho.b("InterstitialAdConverter", "content:%s is vast ad, merge meta data", content.g());
            ps.a(c, a2, 12);
            dVar.B(com.huawei.openalliance.ad.utils.be.b(c));
            dVar.c(true);
        }
        a(dVar, c);
        String B = content.B();
        if (!TextUtils.isEmpty(B)) {
            EncryptionField encryptionField2 = new EncryptionField(String.class);
            encryptionField2.a((EncryptionField) B);
            dVar.H(encryptionField2.b(bArr));
        }
        TemplateV3 W = content.W();
        if (W != null) {
            dVar.L(W.a());
            dVar.a(W.b());
            dVar.g(content.V());
        }
        dVar.f(content.U());
        dVar.b(content.I());
        dVar.a(content.e());
        dVar.p(content.R());
        dVar.r(content.X());
        dVar.s(com.huawei.openalliance.ad.utils.cz.c(content.Y()));
        dVar.c(content.Z());
        dVar.a(content.ac());
        return dVar;
    }

    public static com.huawei.openalliance.ad.inter.data.d a(Context context, ContentRecord contentRecord) {
        if (contentRecord == null || context == null) {
            return null;
        }
        com.huawei.openalliance.ad.inter.data.d dVar = new com.huawei.openalliance.ad.inter.data.d();
        dVar.f(Integer.valueOf(contentRecord.aO()));
        dVar.g(contentRecord.aV());
        dVar.L(contentRecord.aN());
        dVar.a(contentRecord.aT());
        dVar.F(contentRecord.l());
        dVar.c(contentRecord.m());
        dVar.g(contentRecord.B());
        dVar.a(contentRecord.r());
        dVar.b(contentRecord.q());
        dVar.a(contentRecord.D());
        dVar.c(contentRecord.am());
        dVar.f(contentRecord.X());
        dVar.e(contentRecord.n());
        dVar.G(contentRecord.Y());
        dVar.b(12);
        dVar.j(com.huawei.openalliance.ad.utils.cz.c(contentRecord.Z()));
        dVar.k(com.huawei.openalliance.ad.utils.cz.c(contentRecord.ak()));
        dVar.l(com.huawei.openalliance.ad.utils.cz.c(contentRecord.al()));
        dVar.i(contentRecord.ag());
        dVar.q(com.huawei.openalliance.ad.utils.cz.c(contentRecord.N()));
        dVar.d(contentRecord.bc());
        dVar.t(contentRecord.bb());
        dVar.f(contentRecord.aW());
        dVar.u(contentRecord.bf());
        List<String> I = contentRecord.I();
        if (I != null && I.size() > 0) {
            ArrayList arrayList = new ArrayList();
            Iterator<String> it = I.iterator();
            while (it.hasNext()) {
                arrayList.add(com.huawei.openalliance.ad.utils.cz.c(it.next()));
            }
            dVar.e(arrayList);
        }
        byte[] b = com.huawei.openalliance.ad.utils.cp.b(context);
        if (contentRecord.F() != null) {
            dVar.v(contentRecord.F().b(b));
        }
        EncryptionField<List<Monitor>> K = contentRecord.K();
        if (K != null) {
            dVar.C(K.b(b));
        }
        RewardItem ah = contentRecord.ah();
        if (ah != null) {
            dVar.a(ah);
        }
        dVar.i(contentRecord.O());
        dVar.h(contentRecord.M());
        dVar.D(contentRecord.U());
        dVar.E(contentRecord.V());
        dVar.B(contentRecord.g());
        MetaData h = contentRecord.h();
        if (h == null) {
            return dVar;
        }
        a(dVar, h);
        EncryptionField<String> ab = contentRecord.ab();
        if (ab != null) {
            dVar.H(ab.b(b));
        }
        dVar.b(com.huawei.openalliance.ad.utils.cz.h(contentRecord.an()));
        dVar.M(contentRecord.y());
        dVar.a(contentRecord.b(context));
        dVar.p(contentRecord.aG());
        dVar.c(contentRecord.aS());
        dVar.a(contentRecord.aY());
        return dVar;
    }

    public static ContentRecord a(com.huawei.openalliance.ad.inter.data.d dVar) {
        if (dVar == null) {
            return null;
        }
        ContentRecord contentRecord = new ContentRecord();
        contentRecord.a(dVar.a());
        contentRecord.a(dVar.getMinEffectiveShowRatio());
        contentRecord.a(dVar.getMinEffectiveShowRatio());
        contentRecord.b(dVar.r());
        contentRecord.d(dVar.q() == null ? 0 : dVar.q().intValue());
        contentRecord.a(dVar.X() == null ? new TemplateData() : dVar.X());
        contentRecord.L(dVar.W() == null ? Constants.NULL_TEMPLATE_ID : dVar.W());
        contentRecord.s(dVar.V() == null ? -1 : dVar.V().intValue());
        contentRecord.l(dVar.Y() == null ? new ArrayList<>() : dVar.Y());
        contentRecord.c(dVar.getShowId());
        contentRecord.d(dVar.O());
        contentRecord.e(dVar.getContentId());
        contentRecord.d(dVar.getStartTime());
        contentRecord.c(dVar.getEndTime());
        contentRecord.g(dVar.N());
        contentRecord.f(dVar.getTaskId());
        contentRecord.t(dVar.Q());
        contentRecord.u(dVar.getWhyThisAd());
        contentRecord.z(dVar.getAdChoiceUrl());
        contentRecord.A(dVar.getAdChoiceIcon());
        contentRecord.G(dVar.k());
        contentRecord.H(dVar.l());
        contentRecord.o(dVar.u());
        contentRecord.t(dVar.x());
        String D = dVar.D();
        if (!com.huawei.openalliance.ad.utils.cz.b(D)) {
            EncryptionField<String> encryptionField = new EncryptionField<>(String.class);
            encryptionField.a(D);
            contentRecord.a(encryptionField);
        }
        RewardItem B = dVar.B();
        if (B != null) {
            contentRecord.a(B);
        }
        contentRecord.b(12);
        contentRecord.x(dVar.T());
        contentRecord.k(dVar.F());
        contentRecord.i(dVar.C());
        contentRecord.m(dVar.getIntent());
        contentRecord.b(dVar.J());
        String K = dVar.K();
        if (!com.huawei.openalliance.ad.utils.cz.b(K)) {
            EncryptionField<List<Monitor>> encryptionField2 = new EncryptionField<>(List.class, Monitor.class);
            encryptionField2.a(K);
            contentRecord.b(encryptionField2);
        }
        contentRecord.l(dVar.L());
        contentRecord.e(dVar.Z());
        contentRecord.r(dVar.M());
        contentRecord.s(dVar.getCtrlSwitchs());
        contentRecord.v(dVar.getUniqueId());
        String R = dVar.R();
        if (!TextUtils.isEmpty(R)) {
            EncryptionField<String> encryptionField3 = new EncryptionField<>(String.class);
            encryptionField3.a(R);
            contentRecord.c(encryptionField3);
        }
        contentRecord.a(dVar.isAutoDownloadApp());
        contentRecord.y(dVar.f());
        contentRecord.o(dVar.g());
        contentRecord.B(dVar.h() != null ? String.valueOf(dVar.h()) : null);
        contentRecord.D(dVar.i());
        contentRecord.E(dVar.j());
        contentRecord.i(dVar.aa());
        contentRecord.k(dVar.getCreativeType());
        contentRecord.d(dVar.m());
        contentRecord.J(dVar.n());
        contentRecord.f(dVar.p());
        contentRecord.d(dVar.s());
        contentRecord.N(dVar.getAbilityDetailInfo());
        contentRecord.O(dVar.getHwChannelId());
        contentRecord.k(dVar.getCompliance());
        contentRecord.Q(dVar.c());
        contentRecord.e(dVar.isTransparencyOpen());
        contentRecord.S(dVar.getTransparencyTplUrl());
        contentRecord.T(dVar.A());
        return contentRecord;
    }
}
