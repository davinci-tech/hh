package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.metadata.v3.Asset;
import com.huawei.openalliance.ad.beans.metadata.v3.MotionData;
import com.huawei.openalliance.ad.beans.metadata.v3.TemplateData;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.Scheme;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.db.bean.ContentTemplateRecord;
import java.io.File;
import java.util.List;

/* loaded from: classes5.dex */
public class fj {

    /* renamed from: a, reason: collision with root package name */
    private fs f6875a;
    private fu b;
    private ge c;
    private ft d;
    private Context e;

    public List<ContentRecord> b() {
        return this.f6875a.a(1);
    }

    public void a(ContentRecord contentRecord, List<Asset> list, TemplateData templateData) {
        if (contentRecord == null) {
            return;
        }
        this.f6875a.c(contentRecord);
        if (TextUtils.isEmpty(contentRecord.aN()) || com.huawei.openalliance.ad.utils.bg.a(list)) {
            return;
        }
        ContentTemplateRecord contentTemplateRecord = new ContentTemplateRecord(contentRecord.m(), contentRecord.aN(), list, templateData);
        if (!TextUtils.isEmpty(contentRecord.aX())) {
            contentTemplateRecord.a(contentRecord.aX());
        }
        this.b.a(contentTemplateRecord);
    }

    public void a(ContentRecord contentRecord, String str) {
        if (contentRecord == null) {
            ho.c("TContentRecordManager", "deleteContentByIds, record is null");
            return;
        }
        ho.b("TContentRecordManager", "deleteContentByIds %s %s because %s", contentRecord.m(), contentRecord.aN(), str);
        a(contentRecord.m(), contentRecord.aN(), str);
        this.f6875a.a(contentRecord.m(), contentRecord.aN(), contentRecord.l(), str);
    }

    public void a(ContentRecord contentRecord) {
        if (contentRecord == null) {
            ho.c("TContentRecordManager", "deleteContent, record is null");
            return;
        }
        ho.b("TContentRecordManager", "deleteContent: %s %s", contentRecord.m(), contentRecord.aN());
        a(contentRecord.m(), contentRecord.aN(), "delete invalids");
        this.f6875a.b(contentRecord.m(), contentRecord.l());
    }

    public List<ContentRecord> a(String str) {
        return this.f6875a.b(str);
    }

    public List<ContentRecord> a() {
        return this.f6875a.c();
    }

    public String a(String str, String str2) {
        return this.c.a(str, str2);
    }

    public ContentRecord a(ContentRecord contentRecord, int i, long j) {
        String str;
        if (contentRecord == null) {
            return null;
        }
        ContentRecord a2 = this.f6875a.a(contentRecord.m(), contentRecord.aN(), contentRecord.l(), i, j);
        if (a2 == null) {
            str = "showContent is null";
        } else {
            ContentTemplateRecord a3 = this.b.a(contentRecord.m(), contentRecord.aN());
            if (a3 != null && !com.huawei.openalliance.ad.utils.bg.a(a3.c())) {
                a2.l(a3.c());
                TemplateData templateData = new TemplateData(a3.d(), a3.e(), a3.f());
                if (contentRecord.aT() != null) {
                    templateData.c(contentRecord.aT().d());
                }
                a2.a(templateData);
                a2.M(a(contentRecord.l(), contentRecord.aN()));
                return a2;
            }
            str = "template is null";
        }
        ho.b("TContentRecordManager", str);
        return null;
    }

    private String c(String str) {
        Context context = this.e;
        String str2 = Constants.TPLATE_CACHE;
        String c = dh.a(context, Constants.TPLATE_CACHE).c(str);
        if (!com.huawei.openalliance.ad.utils.cz.b(c) && !com.huawei.openalliance.ad.utils.ae.d(new File(c))) {
            String c2 = dh.a(this.e, "normal").c(str);
            if (!com.huawei.openalliance.ad.utils.cz.b(c2) && com.huawei.openalliance.ad.utils.ae.d(new File(c2))) {
                str2 = "normal";
            }
        }
        ho.b("TContentRecordManager", "realCacheType is %s", str2);
        return str2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0011, code lost:
    
        if (r2.size() == 1) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.Boolean b(java.lang.String r2) {
        /*
            r1 = this;
            com.huawei.openalliance.ad.ft r0 = r1.d
            java.util.List r2 = r0.a(r2)
            boolean r0 = com.huawei.openalliance.ad.utils.bg.a(r2)
            if (r0 != 0) goto L14
            int r2 = r2.size()
            r0 = 1
            if (r2 != r0) goto L14
            goto L15
        L14:
            r0 = 0
        L15:
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.fj.b(java.lang.String):java.lang.Boolean");
    }

    private void a(String str, String str2, String str3) {
        ContentTemplateRecord a2 = this.b.a(str, str2);
        if (a2 == null) {
            ho.b("TContentRecordManager", "templateRecord is null");
            return;
        }
        List<Asset> c = a2.c();
        if (!com.huawei.openalliance.ad.utils.bg.a(c)) {
            for (Asset asset : c) {
                if (asset != null) {
                    String g = asset.g();
                    if (!com.huawei.openalliance.ad.utils.cz.b(g)) {
                        String substring = g.substring(g.lastIndexOf("/") + 1);
                        if (b(substring).booleanValue()) {
                            String l = dk.l(g);
                            com.huawei.openalliance.ad.utils.ae.a(this.e, l, c(l));
                        } else {
                            this.d.b(str, substring);
                        }
                    }
                }
            }
        }
        List<MotionData> f = a2.f();
        if (!com.huawei.openalliance.ad.utils.bg.a(f)) {
            for (MotionData motionData : f) {
                if (motionData != null && !com.huawei.openalliance.ad.utils.cz.b(motionData.g())) {
                    String a3 = com.huawei.openalliance.ad.utils.cu.a(motionData.g());
                    String str4 = Scheme.DISKCACHE + a3;
                    if (b(a3).booleanValue()) {
                        com.huawei.openalliance.ad.utils.ae.a(this.e, str4, c(str4));
                    } else {
                        this.d.b(str, a3);
                    }
                }
            }
        }
        this.b.a(str, str2, str3);
    }

    public fj(Context context) {
        this.e = context.getApplicationContext();
        this.f6875a = et.b(context);
        this.b = ev.a(context);
        this.c = fl.a(context);
        this.d = eu.a(context);
    }
}
