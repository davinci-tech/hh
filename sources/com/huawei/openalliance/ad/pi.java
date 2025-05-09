package com.huawei.openalliance.ad;

import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.metadata.ApkInfo;
import com.huawei.openalliance.ad.beans.metadata.Content;
import com.huawei.openalliance.ad.beans.metadata.MediaFile;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.beans.metadata.Monitor;
import com.huawei.openalliance.ad.beans.vast.VastContent;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.db.bean.EncryptionField;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.data.BiddingInfo;
import com.huawei.openalliance.ad.inter.data.PlacementMediaFile;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class pi {
    public static com.huawei.openalliance.ad.inter.data.g a(String str, Content content, byte[] bArr) {
        com.huawei.openalliance.ad.inter.data.g gVar = new com.huawei.openalliance.ad.inter.data.g();
        gVar.a(content.a());
        gVar.d(content.u());
        gVar.D(str);
        gVar.c(content.g());
        gVar.e(false);
        gVar.a(content.f());
        gVar.B(content.w());
        gVar.b(content.j());
        gVar.g(content.l());
        gVar.j(content.x());
        gVar.i(content.r());
        gVar.f(false);
        gVar.a(content.k());
        gVar.A(content.v());
        gVar.e(content.h());
        gVar.G(content.A());
        gVar.f(content.z());
        gVar.b(60);
        gVar.j(com.huawei.openalliance.ad.utils.cz.c(content.D()));
        String B = content.B();
        if (!TextUtils.isEmpty(B)) {
            EncryptionField encryptionField = new EncryptionField(String.class);
            encryptionField.a((EncryptionField) B);
            gVar.E(encryptionField.b(bArr));
        }
        BiddingInfo.a b = new BiddingInfo.a().a(content.ag()).a(content.ah()).setLurl(content.aj()).b(content.ai());
        if (!b.a().a()) {
            gVar.a(b.a());
        }
        String m = content.m();
        if (m != null) {
            gVar.v(com.huawei.openalliance.ad.utils.f.a(m, bArr));
        }
        List<Monitor> p = content.p();
        if (p == null) {
            p = new ArrayList<>();
        }
        MetaData c = content.c();
        VastContent a2 = ps.a(c, 60, content.f());
        if (a2 != null) {
            ho.b("PlacementAdConverter", "content:%s is vast ad, merge monitors", content.g());
            p = ps.a(p, ps.a(a2));
        }
        if (p.size() > 0) {
            EncryptionField encryptionField2 = new EncryptionField(List.class, Monitor.class);
            encryptionField2.a((EncryptionField) p);
            gVar.z(encryptionField2.b(bArr));
        }
        if (c == null) {
            return gVar;
        }
        if (a2 != null) {
            ho.b("PlacementAdConverter", "content:%s is vast ad, merge meta data", content.g());
            ps.a(c, a2, 60);
            gVar.c(true);
        }
        gVar.d(c.i());
        gVar.d(c.b());
        gVar.c(c.c());
        gVar.h(c.j());
        gVar.y(c.m());
        gVar.w(c.h());
        gVar.d(c.G());
        gVar.f(com.huawei.openalliance.ad.utils.cz.c(c.k()));
        gVar.g(c.n());
        gVar.b(com.huawei.openalliance.ad.utils.cz.c(c.a()));
        gVar.x(c.l());
        gVar.d(c.t());
        gVar.F(c.A());
        gVar.q(com.huawei.openalliance.ad.utils.cz.c(content.q()));
        gVar.b(c.K());
        gVar.e(c.N());
        gVar.a(c.s());
        ApkInfo r = c.r();
        if (r != null) {
            AppInfo appInfo = new AppInfo(r);
            appInfo.k(gVar.getIntent());
            appInfo.s(gVar.getUniqueId());
            appInfo.h(c.B());
            gVar.a(appInfo);
        }
        MediaFile u = c.u();
        if (u != null) {
            gVar.a(new PlacementMediaFile(u, c.x()));
        }
        List<MediaFile> w = c.w();
        if (!com.huawei.openalliance.ad.utils.bg.a(w)) {
            int size = w.size();
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < size; i++) {
                arrayList.add(new PlacementMediaFile(w.get(i), 0L));
            }
            gVar.e(arrayList);
        }
        try {
            gVar.C(com.huawei.openalliance.ad.utils.be.a(c));
        } catch (JSONException unused) {
            ho.d("PlacementAdConverter", "MetaData.toJson error");
        }
        gVar.b(content.I());
        gVar.a(content.e());
        gVar.p(content.R());
        gVar.s(com.huawei.openalliance.ad.utils.cz.c(content.Y()));
        gVar.r(content.X());
        gVar.c(content.Z());
        gVar.a(content.ac());
        gVar.d(content.ae().booleanValue());
        gVar.t(content.ad());
        return gVar;
    }

    public static ContentRecord a(com.huawei.openalliance.ad.inter.data.g gVar) {
        if (gVar == null) {
            return null;
        }
        ContentRecord contentRecord = new ContentRecord();
        contentRecord.a(gVar.a());
        contentRecord.a(gVar.getMinEffectiveShowTime());
        contentRecord.a(gVar.getMinEffectiveShowRatio());
        contentRecord.b(gVar.r());
        contentRecord.d(gVar.q() == null ? 0 : gVar.q().intValue());
        contentRecord.c(gVar.getShowId());
        contentRecord.d(gVar.F());
        contentRecord.e(gVar.getContentId());
        contentRecord.f(gVar.getTaskId());
        contentRecord.t(gVar.I());
        contentRecord.d(gVar.getStartTime());
        contentRecord.c(gVar.getEndTime());
        String encodedParamFromServer = gVar.getEncodedParamFromServer();
        if (!com.huawei.openalliance.ad.utils.cz.b(encodedParamFromServer)) {
            EncryptionField<String> encryptionField = new EncryptionField<>(String.class);
            encryptionField.a(encodedParamFromServer);
            contentRecord.a(encryptionField);
        }
        contentRecord.b(60);
        contentRecord.k(gVar.getLandWebUrl());
        contentRecord.i(gVar.getInterActionType());
        contentRecord.m(gVar.getIntent());
        contentRecord.b(gVar.C());
        String encodedeMonitors = gVar.getEncodedeMonitors();
        if (!com.huawei.openalliance.ad.utils.cz.b(encodedeMonitors)) {
            EncryptionField<List<Monitor>> encryptionField2 = new EncryptionField<>(List.class, Monitor.class);
            encryptionField2.a(encodedeMonitors);
            contentRecord.b(encryptionField2);
        }
        contentRecord.l(gVar.getShowLandingPageTitleFlag());
        contentRecord.e(gVar.getClickActionList());
        contentRecord.r(gVar.getWebConfig());
        contentRecord.s(gVar.getCtrlSwitchs());
        contentRecord.g(gVar.E());
        contentRecord.x(gVar.H());
        String G = gVar.G();
        if (!TextUtils.isEmpty(G)) {
            EncryptionField<String> encryptionField3 = new EncryptionField<>(String.class);
            encryptionField3.a(G);
            contentRecord.c(encryptionField3);
        }
        contentRecord.a(gVar.isAutoDownloadApp());
        contentRecord.y(gVar.f());
        contentRecord.o(gVar.g());
        contentRecord.B(gVar.h() != null ? String.valueOf(gVar.h()) : null);
        contentRecord.v(gVar.getUniqueId());
        contentRecord.u(gVar.getWhyThisAd());
        contentRecord.D(gVar.i());
        contentRecord.E(gVar.j());
        contentRecord.G(gVar.k());
        contentRecord.H(gVar.l());
        contentRecord.d(gVar.m());
        contentRecord.J(gVar.n());
        contentRecord.f(gVar.p());
        contentRecord.d(gVar.s());
        contentRecord.O(gVar.getHwChannelId());
        contentRecord.N(gVar.getAbilityDetailInfo());
        contentRecord.k(gVar.getCompliance());
        contentRecord.Q(gVar.c());
        contentRecord.e(gVar.isTransparencyOpen());
        contentRecord.S(gVar.getTransparencyTplUrl());
        contentRecord.T(gVar.A());
        return contentRecord;
    }
}
