package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.beans.metadata.CheckResult;
import com.huawei.openalliance.ad.beans.metadata.ImageInfo;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class pm implements qt {

    /* renamed from: a, reason: collision with root package name */
    protected List<ContentRecord> f7441a;
    protected int b;
    protected fs c;
    protected fs d;
    protected Context e;
    private List<String> f;
    private List<String> g;
    private qp h;
    private qp i;

    @Override // com.huawei.openalliance.ad.qt
    public void d() {
        ho.b("ResponseProcessor", "trimAllContents");
        this.h.b();
        this.i.b();
    }

    @Override // com.huawei.openalliance.ad.qt
    public void c() {
        if (com.huawei.openalliance.ad.utils.bg.a(this.g)) {
            ho.b("ResponseProcessor", "todayNoShowContentIds is empty");
            return;
        }
        ContentRecord contentRecord = new ContentRecord();
        contentRecord.g(com.huawei.openalliance.ad.utils.ao.b("yyyy-MM-dd"));
        ArrayList arrayList = new ArrayList();
        arrayList.add(ContentRecord.FC_CTRL_DATE);
        for (String str : this.g) {
            this.c.a(contentRecord, arrayList, str);
            this.d.a(contentRecord, arrayList, str);
        }
    }

    @Override // com.huawei.openalliance.ad.qt
    public void b(List<String> list) {
        this.g = list;
    }

    @Override // com.huawei.openalliance.ad.qt
    public void b() {
        this.h.a();
        this.i.a();
    }

    @Override // com.huawei.openalliance.ad.qt
    public void a(List<String> list) {
        this.f = list;
    }

    protected void a(String str, String str2, String str3) {
        com.huawei.openalliance.ad.utils.t.a(this.e, str, str2, str3);
    }

    @Override // com.huawei.openalliance.ad.qt
    public void a(Integer num) {
        this.h.a(num);
    }

    @Override // com.huawei.openalliance.ad.qt
    public void a(ContentRecord contentRecord, String str) {
        b(contentRecord, str);
    }

    @Override // com.huawei.openalliance.ad.qt
    public void a(ImageInfo imageInfo, ContentRecord contentRecord, long j) {
        this.h.a(imageInfo, contentRecord, j);
    }

    @Override // com.huawei.openalliance.ad.qt
    public void a() {
        if (com.huawei.openalliance.ad.utils.bg.a(this.f)) {
            ho.b("ResponseProcessor", "invalidContentIds is empty");
            return;
        }
        for (String str : this.f) {
            this.h.a(str);
            this.i.a(str);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0024 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0025  */
    @Override // com.huawei.openalliance.ad.qt
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.openalliance.ad.db.bean.ContentRecord a(com.huawei.openalliance.ad.db.bean.ContentRecord r10, int r11, long r12, byte[] r14, int r15) {
        /*
            r9 = this;
            r0 = 0
            if (r10 != 0) goto L4
            return r0
        L4:
            int r1 = r10.aO()
            r2 = -1
            if (r1 == r2) goto L1f
            r1 = 2
            int r2 = r10.aO()
            if (r1 != r2) goto L13
            goto L1f
        L13:
            r1 = 3
            int r2 = r10.aO()
            if (r1 != r2) goto L1d
            com.huawei.openalliance.ad.qp r1 = r9.i
            goto L21
        L1d:
            r2 = r0
            goto L22
        L1f:
            com.huawei.openalliance.ad.qp r1 = r9.h
        L21:
            r2 = r1
        L22:
            if (r2 != 0) goto L25
            return r0
        L25:
            r3 = r10
            r4 = r11
            r5 = r12
            r7 = r14
            r8 = r15
            com.huawei.openalliance.ad.db.bean.ContentRecord r10 = r2.a(r3, r4, r5, r7, r8)
            if (r10 != 0) goto L31
            goto L35
        L31:
            java.lang.String r0 = r10.m()
        L35:
            java.lang.Object[] r11 = new java.lang.Object[]{r0}
            java.lang.String r12 = "ResponseProcessor"
            java.lang.String r13 = "downloadOneContent, showContentId:%s"
            com.huawei.openalliance.ad.ho.b(r12, r13, r11)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.pm.a(com.huawei.openalliance.ad.db.bean.ContentRecord, int, long, byte[], int):com.huawei.openalliance.ad.db.bean.ContentRecord");
    }

    @Override // com.huawei.openalliance.ad.qt
    public ContentRecord a(ContentRecord contentRecord, int i, long j) {
        return (contentRecord.aO() == -1 || 2 == contentRecord.aO()) ? es.a(this.e).a(contentRecord.m(), contentRecord.l(), i, j) : this.i.a(contentRecord, i, j);
    }

    @Override // com.huawei.openalliance.ad.qt
    public ContentRecord a(long j, int i, int i2) {
        ho.b("ResponseProcessor", "download contents start");
        if (com.huawei.openalliance.ad.utils.bg.a(this.f7441a)) {
            ho.c("ResponseProcessor", "download contents, content records is empty");
            return null;
        }
        byte[] b = com.huawei.openalliance.ad.utils.cp.b(this.e);
        ContentRecord contentRecord = null;
        for (ContentRecord contentRecord2 : this.f7441a) {
            if (contentRecord2 != null) {
                qp qpVar = (contentRecord2.aO() == -1 || 2 == contentRecord2.aO()) ? this.h : 3 == contentRecord2.aO() ? this.i : null;
                if (qpVar != null) {
                    contentRecord = qpVar.a(contentRecord2, i, j, b, i2);
                }
            }
        }
        StringBuilder sb = new StringBuilder("download contents end, showContentId:");
        sb.append(contentRecord != null ? contentRecord.m() : null);
        ho.b("ResponseProcessor", sb.toString());
        return contentRecord;
    }

    @Override // com.huawei.openalliance.ad.qt
    public CheckResult a(ContentRecord contentRecord) {
        return ((contentRecord.aO() == -1 || contentRecord.aO() != 3) ? this.h : this.i).b(contentRecord);
    }

    private void b(ContentRecord contentRecord, String str) {
        String an;
        String str2;
        ArrayList arrayList = new ArrayList();
        arrayList.add("_id");
        arrayList.add(ContentRecord.DISPLAY_COUNT);
        arrayList.add(ContentRecord.DISPLAY_DATE);
        arrayList.add(ContentRecord.SPLASH_MEDIA_PATH);
        arrayList.add(ContentRecord.LAST_SHOW_TIME);
        arrayList.add(ContentRecord.FC_CTRL_DATE);
        if (contentRecord.aO() != -1 && 2 != contentRecord.aO()) {
            if (3 == contentRecord.aO()) {
                this.d.b(contentRecord, arrayList);
                an = contentRecord.an();
                str2 = Constants.TPLATE_CACHE;
            }
            a(str, contentRecord.an(), "ar");
        }
        this.c.b(contentRecord, arrayList);
        an = contentRecord.an();
        str2 = "normal";
        a(str, an, str2);
        a(str, contentRecord.an(), "ar");
    }

    public pm(Context context, boolean z, int i) {
        Context applicationContext = context.getApplicationContext();
        this.e = applicationContext;
        this.h = new or(applicationContext, z, i);
        this.i = new pq(this.e, z);
    }

    public pm(Context context, List<ContentRecord> list, boolean z, int i) {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(list != null ? list.size() : 0);
        objArr[1] = Boolean.valueOf(z);
        ho.b("ResponseProcessor", "ResponseProcessor - content records size: %d isPreContent: %s", objArr);
        this.e = context.getApplicationContext();
        this.f7441a = list;
        this.c = es.a(context);
        this.d = et.b(context);
        this.b = i;
        this.h = new or(context, z, i);
        this.i = new pq(context, z);
    }

    public pm() {
    }
}
