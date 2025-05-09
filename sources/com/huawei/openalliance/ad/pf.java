package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.metadata.AdEvent;
import com.huawei.openalliance.ad.beans.metadata.AppCollectInfo;
import com.huawei.openalliance.ad.beans.metadata.AppCollection;
import com.huawei.openalliance.ad.beans.metadata.BluetoothInfo;
import com.huawei.openalliance.ad.beans.metadata.Content;
import com.huawei.openalliance.ad.beans.metadata.ImageInfo;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.beans.metadata.NegativeFb;
import com.huawei.openalliance.ad.beans.metadata.ParamFromServer;
import com.huawei.openalliance.ad.beans.metadata.Precontent;
import com.huawei.openalliance.ad.beans.metadata.Skip;
import com.huawei.openalliance.ad.beans.metadata.Template;
import com.huawei.openalliance.ad.beans.metadata.VideoInfo;
import com.huawei.openalliance.ad.beans.metadata.WifiInfo;
import com.huawei.openalliance.ad.db.bean.AppDataCollectionRecord;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.db.bean.EncryptionField;
import com.huawei.openalliance.ad.db.bean.EventRecord;
import com.huawei.openalliance.ad.db.bean.PlacementRecord;
import com.huawei.openalliance.ad.db.bean.TemplateRecord;
import com.huawei.openalliance.ad.inter.data.AdLandingPageData;
import com.huawei.openalliance.ad.inter.data.FeedbackInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/* loaded from: classes5.dex */
public abstract class pf {
    private static void c(Content content, ContentRecord contentRecord) {
        int e;
        contentRecord.b(content.d());
        MetaData c = content.c();
        if (c == null) {
            return;
        }
        contentRecord.d(c.c() == null ? 0 : c.c().intValue());
        contentRecord.a(c.j());
        contentRecord.a(c.i());
        contentRecord.b(c.b());
        contentRecord.m(c.n());
        contentRecord.k(c.h());
        contentRecord.f(c.v());
        VideoInfo d = c.d();
        if (d != null) {
            Float m = d.m();
            if (m != null) {
                e = (int) ((720 * 1.0f) / m.floatValue());
                contentRecord.f(720);
                contentRecord.g(e);
            }
            contentRecord.x(c.A());
        }
        List<ImageInfo> o = c.o();
        if (o != null && o.size() > 0) {
            ImageInfo imageInfo = o.get(0);
            contentRecord.i(imageInfo.c());
            contentRecord.f(imageInfo.d());
            e = imageInfo.e();
            contentRecord.g(e);
        }
        contentRecord.x(c.A());
    }

    private static void b(Content content, ContentRecord contentRecord) {
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
        contentRecord.j(arrayList);
    }

    public static List<AppCollection> b(Collection<AppDataCollectionRecord> collection, Context context) {
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        byte[] b = com.huawei.openalliance.ad.utils.cp.b(context);
        ArrayList arrayList = new ArrayList();
        for (AppDataCollectionRecord appDataCollectionRecord : collection) {
            AppCollection appCollection = new AppCollection();
            appCollection.a(Long.valueOf(appDataCollectionRecord.o()));
            appCollection.a(appDataCollectionRecord.b());
            appCollection.b(appDataCollectionRecord.p());
            appCollection.c(appDataCollectionRecord.q());
            appCollection.d(appDataCollectionRecord.r());
            appCollection.e(appDataCollectionRecord.s());
            appCollection.b(com.huawei.openalliance.ad.utils.cz.i(appDataCollectionRecord.c()));
            appCollection.a(com.huawei.openalliance.ad.utils.cz.h(appDataCollectionRecord.d()));
            appCollection.f(appDataCollectionRecord.e());
            appCollection.g(appDataCollectionRecord.f());
            appCollection.b(Integer.valueOf(appDataCollectionRecord.g()));
            appCollection.h(appDataCollectionRecord.h());
            appCollection.o(appDataCollectionRecord.t());
            appCollection.n(appDataCollectionRecord.i());
            EncryptionField<List<AppCollectInfo>> X = appDataCollectionRecord.X();
            if (X != null) {
                List<AppCollectInfo> a2 = X.a(b);
                if (!com.huawei.openalliance.ad.utils.bg.a(a2)) {
                    appCollection.a(a2);
                }
            }
            appCollection.p(appDataCollectionRecord.u());
            appCollection.q(appDataCollectionRecord.v());
            appCollection.r(appDataCollectionRecord.w());
            appCollection.s(appDataCollectionRecord.x());
            appCollection.t(appDataCollectionRecord.y());
            appCollection.i(appDataCollectionRecord.j());
            appCollection.j(appDataCollectionRecord.k());
            appCollection.k(appDataCollectionRecord.l());
            appCollection.l(appDataCollectionRecord.m());
            appCollection.m(appDataCollectionRecord.n());
            appCollection.u(appDataCollectionRecord.z());
            appCollection.v(appDataCollectionRecord.A());
            appCollection.w(appDataCollectionRecord.B());
            appCollection.x(appDataCollectionRecord.C());
            appCollection.y(appDataCollectionRecord.D());
            appCollection.z(appDataCollectionRecord.E());
            appCollection.A(appDataCollectionRecord.F());
            appCollection.B(appDataCollectionRecord.G());
            appCollection.C(appDataCollectionRecord.H());
            appCollection.D(appDataCollectionRecord.I());
            appCollection.E(appDataCollectionRecord.J());
            appCollection.F(appDataCollectionRecord.K());
            appCollection.G(appDataCollectionRecord.L());
            appCollection.H(appDataCollectionRecord.M());
            appCollection.I(appDataCollectionRecord.N());
            appCollection.J(appDataCollectionRecord.O());
            appCollection.K(appDataCollectionRecord.P());
            appCollection.L(appDataCollectionRecord.Q());
            appCollection.M(appDataCollectionRecord.R());
            appCollection.N(appDataCollectionRecord.S());
            appCollection.ag(appDataCollectionRecord.aq());
            appCollection.a(appDataCollectionRecord.a());
            appCollection.d(com.huawei.openalliance.ad.utils.cz.h(appDataCollectionRecord.U()));
            appCollection.c(com.huawei.openalliance.ad.utils.cz.h(appDataCollectionRecord.T()));
            EncryptionField<List<BluetoothInfo>> V = appDataCollectionRecord.V();
            if (V != null) {
                List<BluetoothInfo> a3 = V.a(b);
                if (!com.huawei.openalliance.ad.utils.bg.a(a3)) {
                    appCollection.b(a3);
                }
            }
            EncryptionField<List<WifiInfo>> W = appDataCollectionRecord.W();
            if (W != null) {
                List<WifiInfo> a4 = W.a(b);
                if (!com.huawei.openalliance.ad.utils.bg.a(a4)) {
                    appCollection.c(a4);
                }
            }
            appCollection.c(Long.valueOf(com.huawei.openalliance.ad.utils.dd.f()));
            appCollection.O(appDataCollectionRecord.Y());
            appCollection.P(appDataCollectionRecord.Z());
            EncryptionField<String> aa = appDataCollectionRecord.aa();
            if (aa != null) {
                String a5 = aa.a(b);
                if (!com.huawei.openalliance.ad.utils.cz.b(a5)) {
                    appCollection.Q(a5);
                }
            }
            appCollection.R(appDataCollectionRecord.ab());
            appCollection.S(appDataCollectionRecord.ac());
            appCollection.T(appDataCollectionRecord.ad());
            appCollection.U(appDataCollectionRecord.ae());
            appCollection.V(appDataCollectionRecord.af());
            appCollection.W(appDataCollectionRecord.ag());
            appCollection.X(appDataCollectionRecord.ah());
            appCollection.Y(appDataCollectionRecord.ai());
            appCollection.Z(appDataCollectionRecord.aj());
            appCollection.aa(appDataCollectionRecord.ak());
            appCollection.ad(appDataCollectionRecord.an());
            appCollection.ae(appDataCollectionRecord.ao());
            appCollection.ab(appDataCollectionRecord.al());
            appCollection.ac(appDataCollectionRecord.am());
            appCollection.af(appDataCollectionRecord.ap());
            arrayList.add(appCollection);
        }
        return arrayList;
    }

    public static PlacementRecord b(String str, Precontent precontent, int i) {
        PlacementRecord a2 = a(str, new Content(precontent), i);
        if (a2 != null) {
            a2.e(0);
        }
        return a2;
    }

    private static void a(EventRecord eventRecord, Context context, AdEvent adEvent) {
        EncryptionField<String> m = eventRecord.m();
        if (m != null) {
            byte[] bn = eventRecord.bn();
            String a2 = bn != null ? m.a(bn) : m.a(context);
            if (!com.huawei.openalliance.ad.utils.cz.b(a2)) {
                adEvent.a((ParamFromServer) com.huawei.openalliance.ad.utils.be.b(a2, ParamFromServer.class, new Class[0]));
            }
        }
        EncryptionField<String> q = eventRecord.q();
        if (q != null) {
            byte[] bn2 = eventRecord.bn();
            String a3 = bn2 != null ? q.a(bn2) : q.a(context);
            if (com.huawei.openalliance.ad.utils.cz.b(a3)) {
                return;
            }
            adEvent.e(a3);
        }
    }

    private static void a(Content content, ContentRecord contentRecord) {
        contentRecord.a(content.b());
        contentRecord.n(content.s());
        Skip G = content.G();
        if (G != null) {
            if (!TextUtils.isEmpty(G.a())) {
                contentRecord.n(G.a());
            }
            if (G.b() > 0) {
                contentRecord.n(G.b());
            }
            if (G.c() > 0) {
                contentRecord.m(G.c());
            }
        }
    }

    public static List<AdEvent> a(Collection<EventRecord> collection, Context context) {
        ArrayList arrayList = new ArrayList();
        if (com.huawei.openalliance.ad.utils.bg.a(collection)) {
            return arrayList;
        }
        byte[] b = com.huawei.openalliance.ad.utils.cp.b(context);
        for (EventRecord eventRecord : collection) {
            eventRecord.a(b);
            AdEvent a2 = a(eventRecord, context);
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        return arrayList;
    }

    public static Collection<AppDataCollectionRecord> a(List<AppCollection> list, String str) {
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (AppCollection appCollection : list) {
            AppDataCollectionRecord appDataCollectionRecord = new AppDataCollectionRecord();
            appDataCollectionRecord.a(appCollection.c());
            appDataCollectionRecord.b(appCollection.b().longValue());
            appDataCollectionRecord.m(appCollection.d());
            appDataCollectionRecord.n(appCollection.e());
            appDataCollectionRecord.o(appCollection.f());
            appDataCollectionRecord.p(appCollection.g());
            if (appCollection.h() != null) {
                appDataCollectionRecord.b(String.valueOf(appCollection.h()));
            }
            if (appCollection.i() != null) {
                appDataCollectionRecord.c(String.valueOf(appCollection.i()));
            }
            appDataCollectionRecord.d(appCollection.j());
            appDataCollectionRecord.e(appCollection.k());
            appDataCollectionRecord.a(appCollection.l().intValue());
            appDataCollectionRecord.f(appCollection.m());
            appDataCollectionRecord.g(appCollection.n());
            appDataCollectionRecord.h(appCollection.o());
            appDataCollectionRecord.i(appCollection.p());
            appDataCollectionRecord.j(appCollection.q());
            appDataCollectionRecord.k(appCollection.r());
            appDataCollectionRecord.l(str);
            appDataCollectionRecord.q(appCollection.t());
            appDataCollectionRecord.r(appCollection.u());
            appDataCollectionRecord.s(appCollection.v());
            appDataCollectionRecord.t(appCollection.w());
            appDataCollectionRecord.u(appCollection.x());
            appDataCollectionRecord.v(appCollection.y());
            appDataCollectionRecord.w(appCollection.z());
            appDataCollectionRecord.x(appCollection.A());
            appDataCollectionRecord.y(appCollection.B());
            appDataCollectionRecord.z(appCollection.C());
            appDataCollectionRecord.A(appCollection.D());
            appDataCollectionRecord.B(appCollection.E());
            appDataCollectionRecord.C(appCollection.F());
            appDataCollectionRecord.D(appCollection.G());
            appDataCollectionRecord.E(appCollection.H());
            appDataCollectionRecord.F(appCollection.I());
            appDataCollectionRecord.G(appCollection.J());
            appDataCollectionRecord.H(appCollection.K());
            appDataCollectionRecord.I(appCollection.L());
            appDataCollectionRecord.J(appCollection.M());
            appDataCollectionRecord.K(appCollection.N());
            appDataCollectionRecord.L(appCollection.O());
            appDataCollectionRecord.M(appCollection.P());
            appDataCollectionRecord.N(appCollection.Q());
            if (appCollection.R() != null) {
                appDataCollectionRecord.O(String.valueOf(appCollection.R()));
            }
            if (appCollection.S() != null) {
                appDataCollectionRecord.P(String.valueOf(appCollection.S()));
            }
            appDataCollectionRecord.Q(appCollection.U());
            appDataCollectionRecord.R(appCollection.V());
            appDataCollectionRecord.a(appCollection.a());
            appDataCollectionRecord.c(appCollection.s());
            appDataCollectionRecord.a(appCollection.T());
            appDataCollectionRecord.b(appCollection.W());
            appDataCollectionRecord.S(appCollection.X());
            appDataCollectionRecord.T(appCollection.Y());
            appDataCollectionRecord.V(appCollection.aa());
            appDataCollectionRecord.U(appCollection.Z());
            appDataCollectionRecord.W(appCollection.ab());
            appDataCollectionRecord.X(appCollection.ac());
            appDataCollectionRecord.Y(appCollection.ad());
            appDataCollectionRecord.Z(appCollection.ae());
            appDataCollectionRecord.aa(appCollection.af());
            appDataCollectionRecord.ab(appCollection.ag());
            appDataCollectionRecord.ac(appCollection.ah());
            appDataCollectionRecord.ad(appCollection.ai());
            appDataCollectionRecord.ae(appCollection.aj());
            appDataCollectionRecord.af(appCollection.ak());
            appDataCollectionRecord.ag(appCollection.al());
            appDataCollectionRecord.ah(appCollection.am());
            appDataCollectionRecord.ai(appCollection.an());
            arrayList.add(appDataCollectionRecord);
        }
        return arrayList;
    }

    public static TemplateRecord a(Template template) {
        if (!template.a()) {
            return null;
        }
        TemplateRecord templateRecord = new TemplateRecord();
        templateRecord.a(template.b());
        templateRecord.b(template.c());
        return templateRecord;
    }

    public static PlacementRecord a(String str, Content content, int i) {
        if (com.huawei.openalliance.ad.utils.cz.b(str) || content == null) {
            return null;
        }
        PlacementRecord placementRecord = new PlacementRecord();
        placementRecord.a(str);
        placementRecord.f(i);
        String m = content.m();
        if (m != null) {
            placementRecord.c(m);
        }
        placementRecord.b(content.u());
        placementRecord.b(content.g());
        placementRecord.b(content.f());
        placementRecord.h(content.w());
        placementRecord.b(content.j());
        placementRecord.a(content.l());
        placementRecord.c(content.r());
        placementRecord.a(content.p());
        placementRecord.e(1);
        placementRecord.d(content.x());
        placementRecord.a(content.k());
        placementRecord.f(content.h());
        placementRecord.g(content.v());
        MetaData c = content.c();
        if (c != null) {
            placementRecord.e(com.huawei.openalliance.ad.utils.be.b(c));
            placementRecord.d(c.n());
            placementRecord.i(c.h());
            placementRecord.a(c.u());
            if (c.w() != null) {
                placementRecord.c(c.w());
            }
            placementRecord.j(c.A());
        }
        placementRecord.k(content.I() != null ? String.valueOf(content.I()) : null);
        return placementRecord;
    }

    public static ContentRecord a(String str, Precontent precontent, int i) {
        ContentRecord a2 = a(str, new Content(precontent), i, null, true);
        if (a2 != null) {
            a2.c(0);
            a2.f(true);
        }
        return a2;
    }

    private static ContentRecord a(String str, Content content, int i, String str2, boolean z) {
        if (content == null || (!z && com.huawei.openalliance.ad.utils.cz.b(str))) {
            return null;
        }
        ContentRecord contentRecord = new ContentRecord();
        contentRecord.a(content.a());
        contentRecord.h(0);
        contentRecord.h(com.huawei.openalliance.ad.utils.ao.b("yyyy-MM-dd"));
        contentRecord.i(content.l());
        contentRecord.k(content.f());
        contentRecord.e(content.g());
        contentRecord.f(content.h());
        contentRecord.d(str);
        contentRecord.c(content.j());
        contentRecord.d(content.k());
        contentRecord.e(content.i());
        contentRecord.j(0);
        contentRecord.e(com.huawei.openalliance.ad.utils.ao.c());
        contentRecord.a(content.n());
        contentRecord.b(content.o());
        contentRecord.g(content.z());
        contentRecord.t(content.A());
        contentRecord.c(content.J() != 0);
        contentRecord.F(content.M());
        String m = content.m();
        if (m != null) {
            contentRecord.l(m);
        }
        contentRecord.b(i);
        a(content, contentRecord);
        c(content, contentRecord);
        contentRecord.c(content.p());
        contentRecord.o(com.huawei.openalliance.ad.utils.cz.c(content.q()));
        contentRecord.p(content.t());
        contentRecord.l(content.r());
        contentRecord.e(content.u());
        contentRecord.r(content.v());
        contentRecord.s(content.w());
        contentRecord.w(content.B());
        contentRecord.I(content.O());
        contentRecord.u(content.D());
        contentRecord.z(content.E());
        contentRecord.A(content.F());
        contentRecord.e(content.ae().booleanValue());
        contentRecord.S(content.ad());
        contentRecord.o(content.H());
        contentRecord.B(content.I() != null ? String.valueOf(content.I()) : null);
        if (content.L() != null) {
            contentRecord.q(content.L().intValue());
        }
        if (content.K() != null) {
            contentRecord.p(content.K().intValue());
        }
        contentRecord.r(content.N());
        contentRecord.h(content.P());
        contentRecord.i(content.Q());
        contentRecord.d(content.e());
        contentRecord.J(content.R());
        contentRecord.K(str2);
        contentRecord.a(content.S());
        b(content, contentRecord);
        if (content.W() != null) {
            contentRecord.L(content.W().a());
            contentRecord.a(content.W().b());
            contentRecord.P(content.W().c());
            contentRecord.Y(content.W().d());
            contentRecord.Z(content.W().e());
        }
        contentRecord.l(content.V());
        contentRecord.s(content.U() == null ? -1 : content.U().intValue());
        contentRecord.N(content.X());
        contentRecord.O(com.huawei.openalliance.ad.utils.cz.c(content.Y()));
        contentRecord.k(content.Z());
        contentRecord.a(content.aa());
        if (1 == i) {
            contentRecord.v(UUID.randomUUID().toString());
        }
        contentRecord.t(content.ab());
        contentRecord.Q(content.ac());
        contentRecord.V(content.ag());
        contentRecord.a(content.ah());
        contentRecord.W(content.ai());
        contentRecord.X(content.aj());
        ps.a(contentRecord);
        return contentRecord;
    }

    public static ContentRecord a(String str, Content content, int i, String str2) {
        ContentRecord a2 = a(str, content, i, str2, false);
        if (a2 != null) {
            a2.c(1);
        }
        return a2;
    }

    public static ContentRecord a(AdLandingPageData adLandingPageData) {
        return adLandingPageData == null ? new ContentRecord() : adLandingPageData.x();
    }

    private static AdEvent a(EventRecord eventRecord, Context context) {
        if (eventRecord == null) {
            return null;
        }
        AdEvent adEvent = new AdEvent();
        adEvent.c(eventRecord.i());
        adEvent.a(eventRecord.j());
        adEvent.a(eventRecord.n());
        adEvent.b(eventRecord.o());
        adEvent.c(eventRecord.p());
        adEvent.d(eventRecord.h());
        adEvent.b(eventRecord.k());
        adEvent.a(eventRecord.r());
        adEvent.b(eventRecord.s());
        adEvent.b(eventRecord.v());
        adEvent.f(eventRecord.t());
        adEvent.g(eventRecord.u());
        adEvent.d(com.huawei.openalliance.ad.utils.cz.h(eventRecord.w()));
        adEvent.c(eventRecord.x());
        adEvent.e(com.huawei.openalliance.ad.utils.cz.h(eventRecord.y()));
        adEvent.f(com.huawei.openalliance.ad.utils.cz.h(eventRecord.z()));
        adEvent.g(com.huawei.openalliance.ad.utils.cz.h(eventRecord.A()));
        adEvent.h(com.huawei.openalliance.ad.utils.cz.h(eventRecord.B()));
        adEvent.k(eventRecord.S());
        adEvent.l(eventRecord.T());
        adEvent.i(com.huawei.openalliance.ad.utils.cz.h(eventRecord.C()));
        adEvent.j(com.huawei.openalliance.ad.utils.cz.h(eventRecord.D()));
        adEvent.h(eventRecord.F());
        adEvent.k(com.huawei.openalliance.ad.utils.cz.h(eventRecord.I()));
        adEvent.d(com.huawei.openalliance.ad.utils.cz.i(eventRecord.H()));
        adEvent.l(com.huawei.openalliance.ad.utils.cz.h(eventRecord.K()));
        adEvent.m(com.huawei.openalliance.ad.utils.cz.h(eventRecord.G()));
        adEvent.n(com.huawei.openalliance.ad.utils.cz.h(eventRecord.J()));
        adEvent.o(Integer.valueOf(eventRecord.L()));
        adEvent.i(eventRecord.M());
        adEvent.j(eventRecord.N());
        adEvent.p(Integer.valueOf(eventRecord.P()));
        adEvent.q(eventRecord.O());
        adEvent.m(eventRecord.W());
        adEvent.n(eventRecord.X());
        adEvent.p(eventRecord.ag());
        if (-111111 != eventRecord.ah()) {
            adEvent.x(Integer.valueOf(eventRecord.ah()));
        }
        if (-111111 != eventRecord.U()) {
            adEvent.r(Integer.valueOf(eventRecord.U()));
        }
        if (-111111 != eventRecord.V()) {
            adEvent.s(Integer.valueOf(eventRecord.V()));
        }
        if (-111111 != eventRecord.g()) {
            adEvent.a(Integer.valueOf(eventRecord.g()));
        }
        if (-111111 != eventRecord.a()) {
            adEvent.a(Long.valueOf(eventRecord.a()));
        }
        adEvent.a(eventRecord.b());
        if (-111111 != eventRecord.c()) {
            adEvent.b(Long.valueOf(eventRecord.c()));
        }
        if (-111111 != eventRecord.d()) {
            adEvent.c(Long.valueOf(eventRecord.d()));
        }
        if (-111111 != eventRecord.e()) {
            adEvent.b(Integer.valueOf(eventRecord.e()));
        }
        if (-111111 != eventRecord.f()) {
            adEvent.c(Integer.valueOf(eventRecord.f()));
        }
        if (-111111 != eventRecord.Y() && eventRecord.Y() > 0) {
            adEvent.e(Long.valueOf(eventRecord.Y()));
        }
        if (-111111 != eventRecord.Z()) {
            adEvent.u(Integer.valueOf(eventRecord.Z()));
            adEvent.v(Integer.valueOf(eventRecord.Z()));
        }
        adEvent.o(eventRecord.aa());
        if (-111111 != eventRecord.ab()) {
            adEvent.w(Integer.valueOf(eventRecord.ab()));
        }
        if (-111111 != eventRecord.ac()) {
            adEvent.t(Integer.valueOf(eventRecord.ac()));
        }
        if (-111111 != eventRecord.ak()) {
            adEvent.f(Long.valueOf(eventRecord.ak()));
        }
        if (-111111 != eventRecord.al()) {
            adEvent.g(Long.valueOf(eventRecord.al()));
        }
        if (-111111 != eventRecord.ae()) {
            adEvent.y(Integer.valueOf(eventRecord.ae()));
        }
        if (-111111 != eventRecord.af()) {
            adEvent.z(Integer.valueOf(eventRecord.af()));
        }
        if (!com.huawei.openalliance.ad.utils.cz.b(eventRecord.am())) {
            adEvent.q(eventRecord.am());
        }
        a(eventRecord, context, adEvent);
        return adEvent;
    }
}
