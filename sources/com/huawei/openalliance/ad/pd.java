package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.inner.AdContentData;
import com.huawei.openalliance.ad.beans.metadata.ApkInfo;
import com.huawei.openalliance.ad.beans.metadata.AudioInfo;
import com.huawei.openalliance.ad.beans.metadata.Content;
import com.huawei.openalliance.ad.beans.metadata.ImageInfo;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.beans.metadata.Monitor;
import com.huawei.openalliance.ad.beans.metadata.NegativeFb;
import com.huawei.openalliance.ad.beans.metadata.VideoInfo;
import com.huawei.openalliance.ad.beans.metadata.v3.TemplateV3;
import com.huawei.openalliance.ad.beans.vast.VastContent;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.db.bean.EncryptionField;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.data.BiddingInfo;
import com.huawei.openalliance.ad.inter.data.FeedbackInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class pd {

    /* renamed from: a, reason: collision with root package name */
    private static final String f7431a = "pd";

    private static void c(ContentRecord contentRecord, boolean z, com.huawei.openalliance.ad.inter.data.e eVar, byte[] bArr) {
        EncryptionField<String> F = contentRecord.F();
        if (F != null) {
            if (z) {
                eVar.R(F.a(bArr));
            } else {
                eVar.R(F.a(bArr));
                eVar.z(contentRecord.F().b(bArr));
            }
        }
    }

    private static void b(ContentRecord contentRecord, boolean z, com.huawei.openalliance.ad.inter.data.e eVar, byte[] bArr) {
        EncryptionField<List<Monitor>> K = contentRecord.K();
        if (K != null) {
            if (z) {
                eVar.p(K.a(bArr));
                return;
            }
            EncryptionField encryptionField = new EncryptionField(List.class, Monitor.class);
            encryptionField.a((EncryptionField) K.a(bArr));
            eVar.G(encryptionField.b(bArr));
        }
    }

    private static void a(List<String> list, com.huawei.openalliance.ad.inter.data.e eVar, List<String> list2) {
        if (list != null && list.size() > 0) {
            ArrayList arrayList = new ArrayList();
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(com.huawei.openalliance.ad.utils.cz.c(it.next()));
            }
            eVar.f(arrayList);
        }
        if (list2 == null || list2.size() <= 0) {
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator<String> it2 = list2.iterator();
        while (it2.hasNext()) {
            arrayList2.add(com.huawei.openalliance.ad.utils.cz.c(it2.next()));
        }
        eVar.g(arrayList2);
    }

    private static void a(ContentRecord contentRecord, boolean z, com.huawei.openalliance.ad.inter.data.e eVar, byte[] bArr) {
        EncryptionField<String> ab = contentRecord.ab();
        if (ab != null) {
            if (z) {
                eVar.S(ab.a(bArr));
            } else {
                ab.a((EncryptionField<String>) ab.a(bArr));
                eVar.N(ab.b(bArr));
            }
        }
    }

    private static void a(Content content, byte[] bArr, boolean z, com.huawei.openalliance.ad.inter.data.e eVar) {
        String B = content.B();
        if (!TextUtils.isEmpty(B)) {
            if (z) {
                eVar.S(B);
            } else {
                EncryptionField encryptionField = new EncryptionField(String.class);
                encryptionField.a((EncryptionField) B);
                eVar.N(encryptionField.b(bArr));
            }
        }
        String O = content.O();
        if (TextUtils.isEmpty(O)) {
            return;
        }
        if (z) {
            eVar.T(O);
            return;
        }
        EncryptionField encryptionField2 = new EncryptionField(String.class);
        encryptionField2.a((EncryptionField) O);
        eVar.O(encryptionField2.b(bArr));
    }

    private static void a(Content content, com.huawei.openalliance.ad.inter.data.e eVar, MetaData metaData, VastContent vastContent) {
        if (vastContent != null) {
            ho.b(f7431a, "content:%s is vast ad, merge meta data", content.g());
            ps.a(metaData, vastContent, 3);
            eVar.F(com.huawei.openalliance.ad.utils.be.b(metaData));
            eVar.c(true);
        }
        eVar.A(com.huawei.openalliance.ad.utils.cz.c(metaData.e()));
        eVar.B(com.huawei.openalliance.ad.utils.cz.c(metaData.f()));
        eVar.d(com.huawei.openalliance.ad.utils.cz.a(metaData.O()));
        List<ImageInfo> g = metaData.g();
        if (g != null && !g.isEmpty()) {
            eVar.a(new com.huawei.openalliance.ad.inter.data.ImageInfo(g.get(0)));
        }
        List<com.huawei.openalliance.ad.inter.data.ImageInfo> a2 = a(metaData.o());
        eVar.e(a2);
        eVar.l(a2);
        VideoInfo d = metaData.d();
        if (d != null) {
            com.huawei.openalliance.ad.inter.data.VideoInfo videoInfo = new com.huawei.openalliance.ad.inter.data.VideoInfo(d);
            if (metaData.c() != null) {
                videoInfo.j(metaData.c().intValue());
            }
            eVar.a(videoInfo);
        }
        eVar.C(metaData.h());
        eVar.d(metaData.G());
        eVar.d(metaData.i());
        eVar.d(metaData.b());
        eVar.c(metaData.c());
        eVar.k(metaData.j());
        eVar.f(com.huawei.openalliance.ad.utils.cz.c(metaData.k()));
        eVar.D(metaData.l());
        eVar.E(metaData.m());
        eVar.g(metaData.n());
        eVar.b(com.huawei.openalliance.ad.utils.cz.c(metaData.a()));
        eVar.P(metaData.A());
        eVar.b(metaData.K());
        eVar.e(metaData.N());
        eVar.a(metaData.s());
        ApkInfo r = metaData.r();
        if (r != null) {
            AppInfo appInfo = new AppInfo(r);
            appInfo.k(eVar.getIntent());
            appInfo.s(eVar.getUniqueId());
            appInfo.h(metaData.B());
            eVar.a(appInfo);
        }
    }

    private static void a(Content content, com.huawei.openalliance.ad.inter.data.e eVar) {
        List<NegativeFb> T = content.T();
        if (com.huawei.openalliance.ad.utils.bg.a(T)) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (NegativeFb negativeFb : T) {
            if (negativeFb != null) {
                FeedbackInfo feedbackInfo = new FeedbackInfo();
                feedbackInfo.a(negativeFb.a());
                feedbackInfo.a(com.huawei.openalliance.ad.utils.cz.c(negativeFb.b()));
                feedbackInfo.a(negativeFb.c());
                arrayList.add(feedbackInfo);
            }
        }
        eVar.o(arrayList);
    }

    private static List<com.huawei.openalliance.ad.inter.data.ImageInfo> a(List<ImageInfo> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && !list.isEmpty()) {
            Iterator<ImageInfo> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(new com.huawei.openalliance.ad.inter.data.ImageInfo(it.next()));
            }
        }
        return arrayList;
    }

    public static com.huawei.openalliance.ad.inter.data.e a(String str, Content content, byte[] bArr, int i, boolean z, MetaData metaData) {
        com.huawei.openalliance.ad.inter.data.e eVar = new com.huawei.openalliance.ad.inter.data.e();
        AdContentData adContentData = new AdContentData();
        adContentData.a(i);
        eVar.J(str);
        eVar.a(content.a());
        eVar.c(content.g());
        eVar.j(content.l());
        eVar.a(content.k());
        eVar.b(content.j());
        eVar.a(content.f());
        eVar.e(content.f());
        adContentData.c(content.f());
        adContentData.d(content.f());
        eVar.i(content.z());
        eVar.e(content.h());
        eVar.M(content.A());
        eVar.b(i);
        eVar.k(z);
        eVar.a(bArr);
        eVar.a(content.ac());
        BiddingInfo.a lurl = new BiddingInfo.a().a(content.ag()).a(content.ah()).b(content.ai()).setLurl(content.aj());
        if (!lurl.a().a()) {
            eVar.a(lurl.a());
        }
        List<String> n = content.n();
        if (n != null && n.size() > 0) {
            ArrayList arrayList = new ArrayList();
            Iterator<String> it = n.iterator();
            while (it.hasNext()) {
                arrayList.add(com.huawei.openalliance.ad.utils.cz.c(it.next()));
            }
            eVar.f(arrayList);
        }
        List<String> o = content.o();
        if (o != null && o.size() > 0) {
            ArrayList arrayList2 = new ArrayList();
            Iterator<String> it2 = o.iterator();
            while (it2.hasNext()) {
                arrayList2.add(com.huawei.openalliance.ad.utils.cz.c(it2.next()));
            }
            eVar.g(arrayList2);
        }
        String m = content.m();
        if (m != null) {
            if (z) {
                eVar.R(m);
            } else {
                eVar.z(com.huawei.openalliance.ad.utils.f.a(m, bArr));
            }
        }
        List<Monitor> p = content.p();
        if (p == null) {
            p = new ArrayList<>();
        }
        VastContent a2 = ps.a(metaData, 3, content.f());
        if (a2 != null) {
            ho.b(f7431a, "content:%s is vast ad, merge monitors", content.g());
            p = ps.a(p, ps.a(a2));
        }
        if (p.size() > 0) {
            if (z) {
                eVar.p(p);
            } else {
                EncryptionField encryptionField = new EncryptionField(List.class, Monitor.class);
                encryptionField.a((EncryptionField) p);
                eVar.G(encryptionField.b(bArr));
            }
        }
        eVar.l(content.r());
        eVar.h(content.u());
        eVar.H(content.v());
        eVar.I(content.w());
        eVar.F(content.d());
        adContentData.a(content.d());
        eVar.f(content.ab());
        if (metaData == null) {
            return eVar;
        }
        a(content, eVar, metaData, a2);
        eVar.q(com.huawei.openalliance.ad.utils.cz.c(content.q()));
        eVar.j(com.huawei.openalliance.ad.utils.cz.c(content.D()));
        eVar.k(com.huawei.openalliance.ad.utils.cz.c(content.E()));
        eVar.l(com.huawei.openalliance.ad.utils.cz.c(content.F()));
        eVar.d(content.ae().booleanValue());
        eVar.t(content.ad());
        eVar.d(metaData.t());
        AudioInfo E = metaData.E();
        if (E != null) {
            eVar.a(new com.huawei.openalliance.ad.inter.data.AudioInfo(E));
        }
        a(content, bArr, z, eVar);
        eVar.b(content.I());
        eVar.m(content.N());
        eVar.n(content.Q());
        eVar.m(content.P());
        eVar.a(content.e());
        eVar.p(content.R());
        a(content, eVar);
        eVar.r(content.X());
        eVar.s(com.huawei.openalliance.ad.utils.cz.c(content.Y()));
        TemplateV3 W = content.W();
        if (W != null) {
            eVar.K(W.a());
            eVar.a(W.b());
            eVar.j(content.V());
            eVar.U(W.c());
            eVar.W(W.d());
            eVar.X(W.e());
            adContentData.b(W.a());
            adContentData.a(W.b());
            adContentData.a(content.V());
            adContentData.c(W.c());
        }
        eVar.f(content.U());
        eVar.c(content.Z());
        eVar.a(content.aa());
        eVar.V(content.af());
        adContentData.b(content.U() == null ? -1 : content.U().intValue());
        adContentData.a(content.aa());
        eVar.a(adContentData);
        return eVar;
    }

    public static com.huawei.openalliance.ad.inter.data.e a(Context context, ContentRecord contentRecord, boolean z) {
        com.huawei.openalliance.ad.inter.data.e eVar = new com.huawei.openalliance.ad.inter.data.e();
        AdContentData adContentData = new AdContentData();
        adContentData.a(contentRecord.e());
        eVar.J(contentRecord.l());
        eVar.c(contentRecord.m());
        eVar.j(contentRecord.B());
        eVar.a(contentRecord.r());
        eVar.b(contentRecord.q());
        eVar.a(contentRecord.D());
        eVar.e(contentRecord.D());
        adContentData.c(contentRecord.D());
        adContentData.d(contentRecord.D());
        eVar.i(contentRecord.X());
        eVar.e(contentRecord.n());
        eVar.M(contentRecord.Y());
        eVar.b(contentRecord.e());
        eVar.k(z);
        eVar.c(contentRecord.am());
        eVar.i(contentRecord.ag());
        if (!TextUtils.isEmpty(contentRecord.j())) {
            eVar.h(contentRecord.j());
        }
        a(contentRecord.I(), eVar, contentRecord.J());
        byte[] b = com.huawei.openalliance.ad.utils.cp.b(context);
        eVar.a(b);
        c(contentRecord, z, eVar, b);
        MetaData h = contentRecord.h();
        b(contentRecord, z, eVar, b);
        a(contentRecord, z, eVar, b);
        EncryptionField<String> aD = contentRecord.aD();
        if (aD != null) {
            eVar.O(aD.b(b));
        }
        eVar.l(contentRecord.O());
        eVar.h(contentRecord.M());
        eVar.H(contentRecord.U());
        eVar.I(contentRecord.V());
        eVar.F(contentRecord.g());
        adContentData.a(contentRecord.g());
        if (h == null) {
            return eVar;
        }
        eVar.A(com.huawei.openalliance.ad.utils.cz.c(h.e()));
        eVar.B(com.huawei.openalliance.ad.utils.cz.c(h.f()));
        eVar.d(com.huawei.openalliance.ad.utils.cz.a(h.O()));
        List<ImageInfo> g = h.g();
        if (g != null && !g.isEmpty()) {
            eVar.a(new com.huawei.openalliance.ad.inter.data.ImageInfo(g.get(0)));
        }
        List<com.huawei.openalliance.ad.inter.data.ImageInfo> a2 = a(h.o());
        eVar.e(a2);
        eVar.l(a2);
        VideoInfo d = h.d();
        if (d != null) {
            com.huawei.openalliance.ad.inter.data.VideoInfo videoInfo = new com.huawei.openalliance.ad.inter.data.VideoInfo(d);
            if (h.c() != null) {
                videoInfo.j(h.c().intValue());
            }
            eVar.a(videoInfo);
        }
        eVar.C(h.h());
        eVar.d(h.G());
        eVar.d(h.i());
        eVar.d(h.b());
        eVar.c(h.c());
        eVar.k(h.j());
        eVar.f(com.huawei.openalliance.ad.utils.cz.c(h.k()));
        eVar.D(h.l());
        eVar.E(h.m());
        eVar.g(h.n());
        eVar.b(com.huawei.openalliance.ad.utils.cz.c(h.a()));
        eVar.P(h.A());
        eVar.b(h.K());
        eVar.q(com.huawei.openalliance.ad.utils.cz.c(contentRecord.N()));
        eVar.j(com.huawei.openalliance.ad.utils.cz.c(contentRecord.Z()));
        eVar.k(com.huawei.openalliance.ad.utils.cz.c(contentRecord.ak()));
        eVar.l(com.huawei.openalliance.ad.utils.cz.c(contentRecord.al()));
        ApkInfo r = h.r();
        if (r != null) {
            AppInfo appInfo = new AppInfo(r);
            appInfo.k(eVar.getIntent());
            AppInfo ae = contentRecord.ae();
            appInfo.s((ae == null || TextUtils.isEmpty(ae.getUniqueId())) ? eVar.getUniqueId() : ae.getUniqueId());
            appInfo.h(h.B());
            eVar.a(appInfo);
        }
        eVar.a(h.s());
        eVar.d(h.t());
        AudioInfo E = h.E();
        if (E != null) {
            eVar.a(new com.huawei.openalliance.ad.inter.data.AudioInfo(E));
        }
        eVar.b(Integer.valueOf(contentRecord.C()));
        eVar.m(contentRecord.aC());
        eVar.n(contentRecord.aF());
        eVar.m(contentRecord.aE());
        eVar.a(contentRecord.b(context));
        eVar.p(contentRecord.aG());
        eVar.o(contentRecord.aL());
        eVar.r(contentRecord.aQ());
        eVar.s(contentRecord.aR());
        eVar.K(contentRecord.aN());
        eVar.a(contentRecord.aT());
        eVar.j(contentRecord.aV());
        adContentData.b(contentRecord.aN());
        adContentData.a(contentRecord.aT());
        adContentData.a(contentRecord.aV());
        eVar.f(Integer.valueOf(contentRecord.aO()));
        eVar.c(contentRecord.aS());
        eVar.a(contentRecord.aU());
        adContentData.b(contentRecord.aO());
        adContentData.a(contentRecord.aU());
        eVar.a(adContentData);
        eVar.f(contentRecord.aW());
        eVar.a(contentRecord.aY());
        return eVar;
    }

    public static ContentRecord a(com.huawei.openalliance.ad.inter.data.e eVar) {
        String P;
        String ad;
        String ae;
        if (eVar == null) {
            return null;
        }
        ContentRecord contentRecord = new ContentRecord();
        contentRecord.a(eVar.a());
        contentRecord.a(eVar.getMinEffectiveShowRatio());
        contentRecord.a(eVar.getMinEffectiveShowTime());
        contentRecord.d(eVar.q() == null ? 0 : eVar.q().intValue());
        contentRecord.b(Integer.valueOf(eVar.r() != null ? eVar.r().intValue() : 0));
        contentRecord.c(eVar.getShowId());
        contentRecord.d(eVar.getSlotId());
        contentRecord.e(eVar.getContentId());
        contentRecord.d(eVar.getStartTime());
        contentRecord.c(eVar.getEndTime());
        contentRecord.g(eVar.U());
        contentRecord.f(eVar.getTaskId());
        contentRecord.t(eVar.ac());
        contentRecord.u(eVar.getWhyThisAd());
        contentRecord.z(eVar.getAdChoiceUrl());
        contentRecord.A(eVar.getAdChoiceIcon());
        contentRecord.k(eVar.getCreativeType());
        EncryptionField<String> encryptionField = new EncryptionField<>(String.class);
        if (eVar.al()) {
            encryptionField.a((EncryptionField<String>) eVar.an());
        } else {
            encryptionField.a(eVar.K());
        }
        contentRecord.a(encryptionField);
        contentRecord.b(eVar.e());
        contentRecord.k(eVar.getLandWebUrl());
        contentRecord.i(eVar.getInterActionType());
        contentRecord.m(eVar.getIntent());
        contentRecord.b(eVar.O());
        if (eVar.al()) {
            List<Monitor> ao = eVar.ao();
            EncryptionField encryptionField2 = new EncryptionField(List.class, Monitor.class);
            encryptionField2.a((EncryptionField) ao);
            P = encryptionField2.b(eVar.am());
        } else {
            P = eVar.P();
        }
        if (!com.huawei.openalliance.ad.utils.cz.b(P)) {
            EncryptionField<List<Monitor>> encryptionField3 = new EncryptionField<>(List.class, Monitor.class);
            encryptionField3.a(P);
            contentRecord.b(encryptionField3);
        }
        contentRecord.l(eVar.Q());
        contentRecord.e(eVar.R());
        contentRecord.r(eVar.T());
        contentRecord.s(eVar.getCtrlSwitchs());
        contentRecord.v(eVar.getUniqueId());
        if (eVar.al()) {
            EncryptionField encryptionField4 = new EncryptionField(String.class);
            encryptionField4.a((EncryptionField) eVar.ap());
            ad = encryptionField4.b(eVar.am());
        } else {
            ad = eVar.ad();
        }
        if (!TextUtils.isEmpty(ad)) {
            EncryptionField<String> encryptionField5 = new EncryptionField<>(String.class);
            encryptionField5.a(ad);
            contentRecord.c(encryptionField5);
        }
        if (eVar.al()) {
            EncryptionField encryptionField6 = new EncryptionField(String.class);
            encryptionField6.a((EncryptionField) eVar.aq());
            ae = encryptionField6.b(eVar.am());
        } else {
            ae = eVar.ae();
        }
        if (!TextUtils.isEmpty(ae)) {
            EncryptionField<String> encryptionField7 = new EncryptionField<>(String.class);
            encryptionField7.a(ae);
            contentRecord.d(encryptionField7);
        }
        contentRecord.a(eVar.isAutoDownloadApp());
        contentRecord.x(eVar.af());
        contentRecord.y(eVar.f());
        contentRecord.a(eVar.getAdCloseKeyWords());
        contentRecord.b(eVar.N());
        contentRecord.o(eVar.g());
        contentRecord.c(1);
        contentRecord.B(eVar.h() != null ? String.valueOf(eVar.h()) : null);
        contentRecord.D(eVar.i());
        contentRecord.E(eVar.j());
        contentRecord.G(eVar.k());
        contentRecord.H(eVar.l());
        contentRecord.h(eVar.aj());
        contentRecord.i(eVar.ak());
        contentRecord.d(eVar.m());
        contentRecord.J(eVar.n());
        contentRecord.f(eVar.p());
        contentRecord.j(eVar.getFeedbackInfoList());
        contentRecord.d(eVar.s());
        contentRecord.N(eVar.getAbilityDetailInfo());
        contentRecord.O(eVar.getHwChannelId());
        contentRecord.L(eVar.W());
        contentRecord.M(eVar.Z());
        contentRecord.a(eVar.Y());
        contentRecord.P(eVar.au());
        contentRecord.Y(eVar.aw());
        contentRecord.Z(eVar.ax());
        contentRecord.l(eVar.X());
        contentRecord.s(eVar.aa() != null ? eVar.aa().intValue() : -1);
        contentRecord.k(eVar.getCompliance());
        contentRecord.t(eVar.x());
        contentRecord.Q(eVar.c());
        contentRecord.e(eVar.isTransparencyOpen());
        contentRecord.S(eVar.getTransparencyTplUrl());
        contentRecord.T(eVar.A());
        contentRecord.U(eVar.av());
        return contentRecord;
    }
}
