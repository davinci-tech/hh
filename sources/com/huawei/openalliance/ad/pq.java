package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.metadata.CheckResult;
import com.huawei.openalliance.ad.beans.metadata.v3.Asset;
import com.huawei.openalliance.ad.beans.metadata.v3.MotionData;
import com.huawei.openalliance.ad.beans.metadata.v3.TemplateData;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import com.huawei.operation.utils.Constants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class pq extends or {
    private Context e;
    private fj f;
    private fx g;
    private boolean h;
    private String i;

    @Override // com.huawei.openalliance.ad.or, com.huawei.openalliance.ad.qp
    public void b() {
        List<ContentRecord> b = this.f.b();
        if (com.huawei.openalliance.ad.utils.bg.a(b)) {
            ho.b("TemplateContentProcessor", "trimAllContents, cacheContents is empty.");
            return;
        }
        for (ContentRecord contentRecord : b) {
            if (contentRecord != null && !com.huawei.openalliance.ad.utils.bg.a(contentRecord.aV())) {
                if (ho.a()) {
                    ho.a("TemplateContentProcessor", "begin check %s, %s", contentRecord.m(), contentRecord.aN());
                }
                for (Asset asset : contentRecord.aV()) {
                    if (asset != null && (asset.d() != null || asset.e() != null)) {
                        if (ho.a()) {
                            ho.a("TemplateContentProcessor", "check asset, %s %s", asset.b(), asset.h());
                        }
                        if (c(asset.h())) {
                            ho.b("TemplateContentProcessor", "is optional");
                        } else if (asset.d() != null) {
                            if (!a(contentRecord.l(), contentRecord, asset, com.huawei.openalliance.ad.utils.az.b(this.e, asset.d().a()), (rt) null)) {
                                this.f.a(contentRecord, "media not valid");
                                if (ho.a()) {
                                    ho.a("TemplateContentProcessor", "img is valid: %s", asset.d().a());
                                }
                            }
                        } else if (asset.e() != null && !a(contentRecord.l(), contentRecord, asset, asset.e().a(), (rt) null)) {
                            this.f.a(contentRecord, "media not valid");
                            if (ho.a()) {
                                ho.a("TemplateContentProcessor", "video is valid: %s", asset.e().a());
                            }
                        }
                    }
                }
            }
        }
    }

    @Override // com.huawei.openalliance.ad.or, com.huawei.openalliance.ad.qp
    public CheckResult b(ContentRecord contentRecord) {
        if (contentRecord == null || com.huawei.openalliance.ad.utils.cz.b(contentRecord.aN()) || com.huawei.openalliance.ad.utils.bg.a(contentRecord.aV())) {
            return new CheckResult(false, "spareRecord is null", "");
        }
        ArrayList arrayList = new ArrayList();
        for (Asset asset : contentRecord.aV()) {
            if (asset != null) {
                String l = contentRecord.l();
                if (asset.d() != null) {
                    if (!a(l, contentRecord, asset, com.huawei.openalliance.ad.utils.az.b(this.e, asset.d().a()), (rt) null)) {
                        return new CheckResult(false, "asset img not exist", "");
                    }
                } else if (asset.e() != null && !a(l, contentRecord, asset, asset.e().a(), (rt) null)) {
                    return new CheckResult(false, "asset video not exist", "");
                }
                arrayList.add(asset);
            }
        }
        contentRecord.l(arrayList);
        this.f.a(contentRecord, arrayList, contentRecord.aT());
        ho.b("TemplateContentProcessor", "spare exists");
        return new CheckResult(true, "assets exists", Constants.NULL, contentRecord.T());
    }

    @Override // com.huawei.openalliance.ad.or, com.huawei.openalliance.ad.qp
    public void a(String str) {
        List<ContentRecord> a2 = this.f.a(str);
        if (com.huawei.openalliance.ad.utils.bg.a(a2)) {
            return;
        }
        Iterator<ContentRecord> it = a2.iterator();
        while (it.hasNext()) {
            a(it.next());
        }
    }

    @Override // com.huawei.openalliance.ad.or
    protected void a(ContentRecord contentRecord) {
        if (contentRecord == null) {
            ho.d("TemplateContentProcessor", "fail to delete content, content is null");
        } else {
            this.f.a(contentRecord);
        }
    }

    @Override // com.huawei.openalliance.ad.or, com.huawei.openalliance.ad.qp
    public void a() {
        List<ContentRecord> a2 = this.f.a();
        if (com.huawei.openalliance.ad.utils.bg.a(a2)) {
            return;
        }
        Iterator<ContentRecord> it = a2.iterator();
        while (it.hasNext()) {
            a(it.next());
        }
    }

    @Override // com.huawei.openalliance.ad.or, com.huawei.openalliance.ad.qp
    public ContentRecord a(ContentRecord contentRecord, int i, long j, byte[] bArr, int i2) {
        Asset asset;
        rt rtVar;
        ho.b("TemplateContentProcessor", "downloadElements start");
        ContentRecord contentRecord2 = null;
        if (contentRecord == null || com.huawei.openalliance.ad.utils.cz.b(contentRecord.aN()) || com.huawei.openalliance.ad.utils.bg.a(contentRecord.aV())) {
            return null;
        }
        String m = contentRecord.m();
        int c = c(contentRecord);
        String a2 = com.huawei.openalliance.ad.utils.d.a(i2);
        this.i = a2;
        contentRecord.q(a2);
        ArrayList arrayList = new ArrayList();
        String str = m;
        for (Asset asset2 : contentRecord.aV()) {
            if (asset2 != null) {
                if (asset2.d() != null) {
                    rt a3 = a(asset2, j, contentRecord.V());
                    if (a3 == null) {
                        return contentRecord2;
                    }
                    String b = com.huawei.openalliance.ad.utils.az.b(this.e, asset2.d().a());
                    a3.c(b);
                    asset = asset2;
                    if (a(contentRecord.l(), contentRecord, asset2, b, a3)) {
                        arrayList.add(asset);
                        if (ho.a()) {
                            ho.a("TemplateContentProcessor", "asset img path: %s", asset.g());
                        }
                    } else {
                        rtVar = a3;
                        rtVar.d(true);
                        rtVar.a(Integer.valueOf(this.i));
                        str = a(contentRecord, str, arrayList, asset, rtVar);
                    }
                } else {
                    asset = asset2;
                    if (asset.e() != null) {
                        rt a4 = a(asset, j);
                        if (a(contentRecord.l(), contentRecord, asset, asset.e().a(), a4)) {
                            arrayList.add(asset);
                            if (ho.a()) {
                                ho.a("TemplateContentProcessor", "asset video path: %s", asset.g());
                            }
                        } else {
                            rtVar = a4;
                            rtVar.d(true);
                            rtVar.a(Integer.valueOf(this.i));
                            if (!a(c)) {
                                com.huawei.openalliance.ad.analysis.d.a(this.e, contentRecord);
                                ho.b("TemplateContentProcessor", "video content can only download in wifi");
                                return null;
                            }
                            str = a(contentRecord, str, arrayList, asset, rtVar);
                        }
                    } else {
                        arrayList.add(asset);
                    }
                }
            }
            contentRecord2 = null;
        }
        contentRecord.l(arrayList);
        TemplateData aT = contentRecord.aT();
        ContentRecord a5 = a(contentRecord, i, bArr, str, arrayList, aT, a(contentRecord, j, i2, c, aT));
        ho.b("TemplateContentProcessor", "downloadElements end, showContentId = %s", str);
        return a5;
    }

    @Override // com.huawei.openalliance.ad.or, com.huawei.openalliance.ad.qp
    public ContentRecord a(ContentRecord contentRecord, int i, long j) {
        return this.f.a(contentRecord, i, j);
    }

    private boolean c(String str) {
        try {
            return new JSONObject(str).optInt("optional", 0) == 1;
        } catch (Throwable th) {
            ho.b("TemplateContentProcessor", "isOptional err: %s", th.getClass().getSimpleName());
            return false;
        }
    }

    private int c(ContentRecord contentRecord) {
        if (contentRecord.aT() != null && contentRecord.aT().a() != null) {
            try {
                JSONArray jSONArray = new JSONArray(contentRecord.aT().a());
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    if (!jSONObject.isNull("videoDwnNetwork")) {
                        return jSONObject.optInt("videoDwnNetwork");
                    }
                }
            } catch (Throwable th) {
                ho.b("TemplateContentProcessor", "getDownNetwork err: %s", th.getClass().getSimpleName());
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(ContentRecord contentRecord, TemplateData templateData, long j, int i, int i2) {
        if (templateData == null || com.huawei.openalliance.ad.utils.bg.a(templateData.c())) {
            return true;
        }
        boolean z = true;
        for (MotionData motionData : templateData.c()) {
            rt a2 = a(motionData, j);
            a2.a(contentRecord);
            if (motionData == null || !a(contentRecord.l(), (ContentRecord) null, (Asset) null, motionData.g(), a2)) {
                if (a(i)) {
                    a2.d(true);
                    a2.a(Integer.valueOf(com.huawei.openalliance.ad.utils.d.a(i2)));
                    if (com.huawei.openalliance.ad.utils.cz.b(a(contentRecord, a2, false))) {
                        ho.b("TemplateContentProcessor", "motionDataId %s is Download failed, Because filePath is blank!", Long.valueOf(motionData.a()));
                    }
                } else {
                    ho.b("TemplateContentProcessor", "motionDataId %s is Download failed, Because network can not download!", Long.valueOf(motionData.a()));
                }
                z = false;
            }
        }
        ho.b("TemplateContentProcessor", "downMotionSuccessFlag is %s", Boolean.valueOf(z));
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(ContentRecord contentRecord, Asset asset, String str, rt rtVar) {
        String a2 = a(this.e, contentRecord, str, com.huawei.openalliance.ad.constant.Constants.TPLATE_CACHE, rtVar);
        if (!TextUtils.isEmpty(a2)) {
            if (asset != null) {
                asset.b(a2);
            }
            ho.b("TemplateContentProcessor", "tplate isExistLocal: %s", com.huawei.openalliance.ad.utils.dl.a(a2));
            return true;
        }
        String a3 = a(this.e, contentRecord, str, "normal", rtVar);
        if (TextUtils.isEmpty(a3)) {
            return false;
        }
        if (asset != null) {
            asset.b(a3);
        }
        ho.b("TemplateContentProcessor", "normal isExistLocal: %s", com.huawei.openalliance.ad.utils.dl.a(a3));
        return true;
    }

    private String b(String str) {
        String str2 = this.i;
        List<ContentResource> c = eu.a(this.e).c(com.huawei.openalliance.ad.utils.cu.a(str), com.huawei.openalliance.ad.constant.Constants.TPLATE_CACHE);
        return (com.huawei.openalliance.ad.utils.bg.a(c) || c.get(0) == null) ? str2 : String.valueOf(c.get(0).g());
    }

    private boolean a(String str, ContentRecord contentRecord, Asset asset, String str2, rt rtVar) {
        return com.huawei.openalliance.ad.utils.d.c(this.e, str) ? b(contentRecord, asset, str2, rtVar) : a(contentRecord, asset, str2, rtVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x004e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0083  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean a(final com.huawei.openalliance.ad.db.bean.ContentRecord r9, final com.huawei.openalliance.ad.beans.metadata.v3.Asset r10, final java.lang.String r11, final com.huawei.openalliance.ad.rt r12) {
        /*
            r8 = this;
            java.lang.String r0 = "TemplateContentProcessor"
            com.huawei.openalliance.ad.pq$1 r7 = new com.huawei.openalliance.ad.pq$1
            r1 = r7
            r2 = r8
            r3 = r9
            r4 = r10
            r5 = r11
            r6 = r12
            r1.<init>()
            java.util.concurrent.Future r12 = com.huawei.openalliance.ad.utils.dc.a(r7)
            com.huawei.openalliance.ad.pq$2 r1 = new com.huawei.openalliance.ad.pq$2
            r1.<init>()
            java.util.concurrent.Future r10 = com.huawei.openalliance.ad.utils.dc.a(r1)
            r11 = 1
            r1 = 0
            java.lang.Object r12 = r12.get()     // Catch: java.lang.Throwable -> L37
            java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch: java.lang.Throwable -> L37
            boolean r12 = r12.booleanValue()     // Catch: java.lang.Throwable -> L37
            java.lang.Object[] r2 = new java.lang.Object[r11]     // Catch: java.lang.Throwable -> L35
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r12)     // Catch: java.lang.Throwable -> L35
            r2[r1] = r3     // Catch: java.lang.Throwable -> L35
            java.lang.String r3 = "sdk res: %s"
            com.huawei.openalliance.ad.ho.a(r0, r3, r2)     // Catch: java.lang.Throwable -> L35
            goto L4c
        L35:
            r2 = move-exception
            goto L3a
        L37:
            r12 = move-exception
            r2 = r12
            r12 = r1
        L3a:
            java.lang.Class r2 = r2.getClass()
            java.lang.String r2 = r2.getSimpleName()
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            java.lang.String r3 = "sdk res err: %s"
            com.huawei.openalliance.ad.ho.c(r0, r3, r2)
        L4c:
            if (r12 != 0) goto L83
            java.lang.Object r10 = r10.get()     // Catch: java.lang.Throwable -> L70
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch: java.lang.Throwable -> L70
            boolean r12 = r10.booleanValue()     // Catch: java.lang.Throwable -> L70
            if (r12 == 0) goto L5f
            r10 = 2
            r8.a(r9, r10)     // Catch: java.lang.Throwable -> L70
            goto L62
        L5f:
            r8.a(r9, r1)     // Catch: java.lang.Throwable -> L70
        L62:
            java.lang.Object[] r9 = new java.lang.Object[r11]     // Catch: java.lang.Throwable -> L70
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r12)     // Catch: java.lang.Throwable -> L70
            r9[r1] = r10     // Catch: java.lang.Throwable -> L70
            java.lang.String r10 = "kit res: %s"
            com.huawei.openalliance.ad.ho.a(r0, r10, r9)     // Catch: java.lang.Throwable -> L70
            goto L8b
        L70:
            r9 = move-exception
            java.lang.Class r9 = r9.getClass()
            java.lang.String r9 = r9.getSimpleName()
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            java.lang.String r10 = "kit res err: %s"
            com.huawei.openalliance.ad.ho.c(r0, r10, r9)
            goto L8b
        L83:
            com.huawei.openalliance.ad.pq$3 r11 = new com.huawei.openalliance.ad.pq$3
            r11.<init>()
            com.huawei.openalliance.ad.utils.m.f(r11)
        L8b:
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r12)
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            java.lang.String r10 = "isExistBoth: %s"
            com.huawei.openalliance.ad.ho.b(r0, r10, r9)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.pq.a(com.huawei.openalliance.ad.db.bean.ContentRecord, com.huawei.openalliance.ad.beans.metadata.v3.Asset, java.lang.String, com.huawei.openalliance.ad.rt):boolean");
    }

    private boolean a(ContentRecord contentRecord, long j, int i, int i2, TemplateData templateData) {
        boolean z;
        boolean z2 = !this.h && os.I(contentRecord.V());
        ho.b("TemplateContentProcessor", "isPreContent is  %s, isNeedAsyncDownMotion is %s", Boolean.valueOf(this.h), Boolean.valueOf(z2));
        if (z2) {
            a(contentRecord, templateData, j, i2, i);
            z = false;
        } else {
            z = b(contentRecord, templateData, j, i2, i);
        }
        return z2 || z;
    }

    private void a(final ContentRecord contentRecord, final TemplateData templateData, final long j, final int i, final int i2) {
        com.huawei.openalliance.ad.utils.m.d(new Runnable() { // from class: com.huawei.openalliance.ad.pq.4
            @Override // java.lang.Runnable
            public void run() {
                pq.this.b(contentRecord, templateData, j, i, i2);
            }
        });
    }

    private String a(ContentRecord contentRecord, String str, List<Asset> list, Asset asset, rt rtVar) {
        String c = dh.a(this.e, com.huawei.openalliance.ad.constant.Constants.TPLATE_CACHE).c(a(contentRecord, rtVar, true));
        if (!com.huawei.openalliance.ad.utils.cz.b(c)) {
            asset.b(c);
        } else if (!c(asset.h())) {
            str = null;
        }
        list.add(asset);
        return str;
    }

    private String a(ContentRecord contentRecord, rt rtVar, boolean z) {
        if (rtVar != null) {
            rtVar.a(contentRecord);
            rtVar.c(true);
            rtVar.d(com.huawei.openalliance.ad.constant.Constants.TPLATE_CACHE);
            ru a2 = this.g.a(rtVar);
            if (a2 != null) {
                return a2.a();
            }
        }
        return null;
    }

    private String a(Context context, ContentRecord contentRecord, String str, String str2, rt rtVar) {
        dk a2 = dh.a(context, str2);
        String e = a2.e(com.huawei.openalliance.ad.utils.az.b(context, str));
        String c = a2.c(e);
        if (!com.huawei.openalliance.ad.utils.ae.b(c)) {
            return null;
        }
        if (contentRecord != null) {
            contentRecord.q(b(str));
            if (rtVar != null) {
                rtVar.a(contentRecord);
            }
        }
        rs.a(context, e, a2, rtVar, str2);
        return c;
    }

    private rt a(MotionData motionData, long j) {
        if (motionData == null) {
            return null;
        }
        rt rtVar = new rt();
        rtVar.c(motionData.g());
        rtVar.b(motionData.h());
        rtVar.b(true);
        rtVar.c(true);
        rtVar.d(com.huawei.openalliance.ad.constant.Constants.TPLATE_CACHE);
        rtVar.a(Long.valueOf(j));
        return rtVar;
    }

    private rt a(Asset asset, long j, String str) {
        if (asset == null || asset.d() == null) {
            return null;
        }
        if (this.h && os.a(str) && !com.huawei.openalliance.ad.utils.bv.c(this.e)) {
            ho.b("TemplateContentProcessor", "pre content only download in wifi");
            return null;
        }
        rt rtVar = new rt();
        rtVar.c(asset.d().a());
        rtVar.b(asset.d().d() != null ? asset.d().d().b() : null);
        rtVar.b(asset.d().d() == null || asset.d().d().d() == 0);
        rtVar.c(true);
        rtVar.a(Long.valueOf(j));
        return rtVar;
    }

    private rt a(Asset asset, long j) {
        if (asset == null || asset.e() == null) {
            return null;
        }
        rt rtVar = new rt();
        rtVar.c(asset.e().a());
        rtVar.b(asset.e().f());
        rtVar.b(asset.e().h() == 0);
        rtVar.c(true);
        rtVar.a(Long.valueOf(j));
        return rtVar;
    }

    private ContentRecord a(ContentRecord contentRecord, int i, byte[] bArr, String str, List<Asset> list, TemplateData templateData, boolean z) {
        if (str == null || !z) {
            return null;
        }
        contentRecord.a(bArr);
        contentRecord.v(UUID.randomUUID().toString());
        contentRecord.M(this.f.a(contentRecord.l(), contentRecord.aN()));
        if (1 == i) {
            contentRecord.f(720);
            contentRecord.g(1080);
        } else {
            contentRecord.f(1080);
            contentRecord.g(720);
        }
        contentRecord.k(199);
        this.f.a(contentRecord, list, templateData);
        return contentRecord;
    }

    public pq(Context context, boolean z) {
        super(context, z, -1);
        this.i = "3";
        this.e = context;
        this.g = fb.a(context);
        this.f = new fj(context);
        this.h = z;
    }
}
