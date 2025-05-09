package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.metadata.CheckResult;
import com.huawei.openalliance.ad.beans.metadata.ImageInfo;
import com.huawei.openalliance.ad.beans.metadata.VideoInfo;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.MetaCreativeType;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.HiAdSplash;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/* loaded from: classes5.dex */
public class or implements qp {

    /* renamed from: a, reason: collision with root package name */
    protected int f7396a;
    protected boolean b;
    protected fs c;
    protected Context d;
    private ft e;
    private fx f;
    private gc g;
    private cs h;
    private Integer i;

    @Override // com.huawei.openalliance.ad.qp
    public ContentRecord a(ContentRecord contentRecord, int i, long j) {
        return null;
    }

    @Override // com.huawei.openalliance.ad.qp
    public void b() {
        List<ContentRecord> a2 = this.c.a(this.f7396a);
        if (com.huawei.openalliance.ad.utils.bg.a(a2)) {
            return;
        }
        for (ContentRecord contentRecord : a2) {
            if (contentRecord != null) {
                e(contentRecord);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.qp
    public CheckResult b(ContentRecord contentRecord) {
        return com.huawei.openalliance.ad.utils.d.c(this.d, contentRecord.l()) ? b(contentRecord, true) : h(contentRecord);
    }

    protected boolean a(int i) {
        Integer allowMobileTraffic = HiAdSplash.getInstance(this.d).getAllowMobileTraffic();
        if (allowMobileTraffic != null) {
            if (1 == allowMobileTraffic.intValue()) {
                return true;
            }
            if (allowMobileTraffic.intValue() == 0) {
                return com.huawei.openalliance.ad.utils.bv.c(this.d);
            }
        }
        if (i == 1) {
            return true;
        }
        return i == 0 && com.huawei.openalliance.ad.utils.bv.c(this.d);
    }

    protected void a(String str, String str2, String str3) {
        com.huawei.openalliance.ad.utils.t.a(this.d, str, str2, str3);
    }

    @Override // com.huawei.openalliance.ad.qp
    public void a(String str) {
        List<ContentRecord> b = this.c.b(str);
        if (com.huawei.openalliance.ad.utils.bg.a(b)) {
            return;
        }
        Iterator<ContentRecord> it = b.iterator();
        while (it.hasNext()) {
            a(it.next());
        }
    }

    @Override // com.huawei.openalliance.ad.qp
    public void a(Integer num) {
        this.i = num;
    }

    protected void a(final ContentRecord contentRecord, final int i) {
        com.huawei.openalliance.ad.utils.m.h(new Runnable() { // from class: com.huawei.openalliance.ad.or.7
            @Override // java.lang.Runnable
            public void run() {
                or.this.h.d(contentRecord, i);
            }
        });
    }

    protected void a(ContentRecord contentRecord) {
        if (contentRecord == null) {
            ho.d("ContentProcessor", "fail to delete content, content is null");
            return;
        }
        String y = contentRecord.y();
        int i = this.f7396a;
        if ((1 == i || 18 == i) && !com.huawei.openalliance.ad.utils.cz.b(y)) {
            String substring = y.substring(y.lastIndexOf("/") + 1);
            if (c(substring).booleanValue()) {
                com.huawei.openalliance.ad.utils.ae.a(this.d, y, b(y));
            } else {
                this.e.b(contentRecord.m(), substring);
            }
        }
        this.c.b(contentRecord.m(), contentRecord.l());
    }

    @Override // com.huawei.openalliance.ad.qp
    public void a(ImageInfo imageInfo, ContentRecord contentRecord, long j) {
        rt b = b(imageInfo, contentRecord, j);
        if (b != null) {
            b.a(contentRecord);
            ru a2 = this.f.a(b);
            Object[] objArr = new Object[1];
            objArr[0] = a2 != null ? com.huawei.openalliance.ad.utils.dl.a(a2.b()) : null;
            ho.b("ContentProcessor", "downloadSplashIcon result= %s", objArr);
        }
    }

    @Override // com.huawei.openalliance.ad.qp
    public void a() {
        Iterator<ContentRecord> it = this.c.c().iterator();
        while (it.hasNext()) {
            a(it.next());
        }
    }

    @Override // com.huawei.openalliance.ad.qp
    public ContentRecord a(ContentRecord contentRecord, int i, long j, byte[] bArr, int i2) {
        ho.b("ContentProcessor", "downloadOneContent start");
        if (contentRecord == null) {
            ho.c("ContentProcessor", "downloadOneContent, contentRecord in null");
            return null;
        }
        if (9 != contentRecord.D() && 12 != contentRecord.D() && this.b && os.a(contentRecord.V()) && !com.huawei.openalliance.ad.utils.bv.c(this.d)) {
            ho.b("ContentProcessor", "downloadOneContent, pre content only download in wifi");
            return null;
        }
        if (!b(contentRecord.D()) && contentRecord.i() == 1) {
            ho.b("ContentProcessor", "downloadOneContent - content creativeType %d not supported", Integer.valueOf(contentRecord.D()));
            return null;
        }
        contentRecord.a(bArr);
        ContentRecord a2 = a(contentRecord, j, i2);
        Object[] objArr = new Object[1];
        objArr[0] = a2 != null ? a2.m() : null;
        ho.b("ContentProcessor", "downloadOneContent, showContentId:%s", objArr);
        return a2;
    }

    private CheckResult h(ContentRecord contentRecord) {
        return a(contentRecord, true);
    }

    private boolean g(ContentRecord contentRecord) {
        CheckResult a2 = a(contentRecord, false);
        return a2 != null && a2.a();
    }

    private boolean f(ContentRecord contentRecord) {
        return b(contentRecord, false).a();
    }

    private boolean e(ContentRecord contentRecord) {
        return com.huawei.openalliance.ad.utils.d.c(this.d, contentRecord.l()) ? f(contentRecord) : g(contentRecord);
    }

    private void d(ContentRecord contentRecord) {
        if (2 == contentRecord.D()) {
            String y = contentRecord.y();
            if (com.huawei.openalliance.ad.utils.cz.b(y)) {
                return;
            }
            com.huawei.openalliance.ad.utils.az.a(this.d, y, null, null);
        }
    }

    private void c(final ContentRecord contentRecord) {
        if (contentRecord == null) {
            return;
        }
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.or.1
            @Override // java.lang.Runnable
            public void run() {
                dk a2 = dh.a(or.this.d, "normal");
                String b = com.huawei.openalliance.ad.utils.az.b(or.this.d, com.huawei.openalliance.ad.utils.c.a(or.this.d, contentRecord));
                String e = a2.e(b);
                String c = a2.c(e);
                if (!com.huawei.openalliance.ad.utils.ae.b(c)) {
                    dk a3 = dh.a(or.this.d, Constants.TPLATE_CACHE);
                    String e2 = a3.e(b);
                    c = a3.c(e2);
                    e = e2;
                }
                if (com.huawei.openalliance.ad.utils.ae.b(c)) {
                    a2.h(e);
                }
            }
        });
    }

    private String c(String str, String str2) {
        return dk.i(str) ? dh.a(this.d, str2).c(str) : str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0011, code lost:
    
        if (r2.size() == 1) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.Boolean c(java.lang.String r2) {
        /*
            r1 = this;
            com.huawei.openalliance.ad.ft r0 = r1.e
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
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.or.c(java.lang.String):java.lang.Boolean");
    }

    private boolean b(int i) {
        int i2 = this.f7396a;
        if (1 == i2 || 18 == i2) {
            return 2 == i || 4 == i || 9 == i || 12 == i;
        }
        return false;
    }

    private void b(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            this.c.c(str);
        } else {
            this.c.b(str, str2);
        }
    }

    private String b(String str) {
        String str2 = "normal";
        String c = dh.a(this.d, "normal").c(str);
        if (!com.huawei.openalliance.ad.utils.cz.b(c) && !com.huawei.openalliance.ad.utils.ae.d(new File(c))) {
            String c2 = dh.a(this.d, Constants.TPLATE_CACHE).c(str);
            if (!com.huawei.openalliance.ad.utils.cz.b(c2) && com.huawei.openalliance.ad.utils.ae.d(new File(c2))) {
                str2 = Constants.TPLATE_CACHE;
            }
        }
        ho.b("ContentProcessor", "realCacheType is %s", str2);
        return str2;
    }

    private rt b(ImageInfo imageInfo, ContentRecord contentRecord, long j) {
        if (imageInfo == null) {
            return null;
        }
        rt rtVar = new rt();
        rtVar.a(contentRecord);
        rtVar.c(imageInfo.c());
        rtVar.b(imageInfo.a());
        rtVar.b(imageInfo.g() == 0);
        rtVar.c(true);
        rtVar.a(true);
        rtVar.a(Long.valueOf(j));
        rtVar.c(MetaCreativeType.GIF.equals(imageInfo.b()) ? this.g.d() : this.g.e());
        return rtVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CheckResult b(ContentRecord contentRecord, boolean z) {
        String str;
        String m = contentRecord.m();
        ContentRecord a2 = a(m, contentRecord.l());
        if (a2 == null) {
            return new CheckResult(false, "cachedContentRecord is null", "");
        }
        String T = a2.T();
        CheckResult b = b(a2, m);
        String y = a2.y();
        if (b != null && !b.a()) {
            return b;
        }
        if (b == null || !b.a()) {
            str = null;
        } else {
            y = b.b();
            str = b.c();
        }
        if (z) {
            return b != null ? b : new CheckResult(true, y, str, T);
        }
        if (this.i != null && !this.b) {
            int t = a2.t();
            int s = a2.s();
            boolean z2 = this.i.intValue() != 0 || (a2.D() != 12 && s > t);
            if ((this.i.intValue() == 1 && a2.D() != 12 && s > t) || !z2) {
                ho.b("ContentProcessor", "delete content %s because of media mismatch screen orientation.", m);
                com.huawei.openalliance.ad.utils.ae.a(this.d, y);
                this.c.c(m);
                return new CheckResult(false, "media mismatch screen orientation", str);
            }
        }
        contentRecord.j(str);
        contentRecord.q(T);
        return new CheckResult(true, y, str, T);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CheckResult b(ContentRecord contentRecord, String str, String str2) {
        String c = c(str2, "normal");
        String T = contentRecord.T();
        if (com.huawei.openalliance.ad.utils.cz.b(c)) {
            ho.b("ContentProcessor", "checkFileExistInNormal filePath is blank %s.", str);
            return new CheckResult(false, "filePath is blank", c);
        }
        if (com.huawei.openalliance.ad.utils.ae.c(this.d, c, "normal")) {
            ho.b("ContentProcessor", "normal localFilePath is %s", com.huawei.openalliance.ad.utils.dl.a(c));
            return new CheckResult(true, str2, c, T);
        }
        ho.b("ContentProcessor", "checkFileExistInNormal is FilePath is invalid : %s.", str);
        com.huawei.openalliance.ad.utils.ae.a(this.d, str2);
        return new CheckResult(false, str2, c);
    }

    private CheckResult b(final ContentRecord contentRecord, final String str) {
        Object obj;
        String l = contentRecord.l();
        final String y = contentRecord.y();
        Future a2 = com.huawei.openalliance.ad.utils.dc.a(new Callable<CheckResult>() { // from class: com.huawei.openalliance.ad.or.2
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public CheckResult call() {
                return or.this.b(contentRecord, str, y);
            }
        });
        Future a3 = com.huawei.openalliance.ad.utils.dc.a(new Callable<CheckResult>() { // from class: com.huawei.openalliance.ad.or.3
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public CheckResult call() {
                return or.this.a(contentRecord, str, y);
            }
        });
        try {
        } catch (Throwable th) {
            ho.c("ContentProcessor", "normalPathFuture or tplatePathFuture res err: %s", th.getClass().getSimpleName());
        }
        if (!((CheckResult) a2.get()).a() && !((CheckResult) a3.get()).a()) {
            ho.c("ContentProcessor", "normalPathFuture and tplatePathFuture is invalid");
            b(str, l);
        } else {
            if (!((CheckResult) a2.get()).a()) {
                if (((CheckResult) a3.get()).a()) {
                    ho.c("ContentProcessor", "tplatePathFuture is Valid");
                    obj = a3.get();
                    return (CheckResult) obj;
                }
                return null;
            }
            ho.c("ContentProcessor", "normalPathFuture is Valid");
        }
        obj = a2.get();
        return (CheckResult) obj;
    }

    private boolean a(VideoInfo videoInfo) {
        if (videoInfo == null) {
            return false;
        }
        return a(videoInfo.l());
    }

    private void a(ContentRecord contentRecord, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("_id");
        arrayList.add(ContentRecord.DISPLAY_COUNT);
        arrayList.add(ContentRecord.DISPLAY_DATE);
        arrayList.add(ContentRecord.SPLASH_MEDIA_PATH);
        arrayList.add(ContentRecord.LAST_SHOW_TIME);
        arrayList.add(ContentRecord.FC_CTRL_DATE);
        int i = this.f7396a;
        if (1 == i || 18 == i) {
            ho.a("ContentProcessor", "ad type is splash");
        }
        this.c.b(contentRecord, arrayList);
        a(str, contentRecord.an(), "normal");
        a(str, contentRecord.an(), "ar");
        a(str, contentRecord.an(), Constants.TPLATE_CACHE);
    }

    private rt a(VideoInfo videoInfo, ContentRecord contentRecord, long j) {
        if (videoInfo == null) {
            return null;
        }
        rt rtVar = new rt();
        rtVar.a(contentRecord);
        rtVar.c(videoInfo.a());
        rtVar.b(videoInfo.g());
        rtVar.b(videoInfo.i() == 0);
        rtVar.c(true);
        rtVar.a(true);
        rtVar.a(Long.valueOf(j));
        rtVar.a(209715200L);
        return rtVar;
    }

    private ContentRecord a(String str, String str2) {
        return TextUtils.isEmpty(str2) ? this.c.a(str) : this.c.a(str, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.huawei.openalliance.ad.db.bean.ContentRecord a(com.huawei.openalliance.ad.db.bean.ContentRecord r5, long r6, java.lang.String r8) {
        /*
            r4 = this;
            com.huawei.openalliance.ad.beans.metadata.VideoInfo r0 = r5.R()
            java.lang.String r1 = "ContentProcessor"
            r2 = 0
            if (r0 != 0) goto L15
            com.huawei.openalliance.ad.beans.metadata.ImageInfo r0 = r5.Q()
            if (r0 != 0) goto L15
            java.lang.String r5 = "no material"
            com.huawei.openalliance.ad.ho.a(r1, r5)
            return r2
        L15:
            int r0 = r5.D()
            r3 = 9
            if (r0 == r3) goto L2b
            r3 = 12
            if (r0 != r3) goto L22
            goto L2b
        L22:
            com.huawei.openalliance.ad.beans.metadata.ImageInfo r0 = r5.Q()
            com.huawei.openalliance.ad.rt r6 = r4.b(r0, r5, r6)
            goto L46
        L2b:
            com.huawei.openalliance.ad.beans.metadata.VideoInfo r0 = r5.R()
            boolean r3 = r4.a(r0)
            if (r3 == 0) goto L3a
            com.huawei.openalliance.ad.rt r6 = r4.a(r0, r5, r6)
            goto L46
        L3a:
            android.content.Context r6 = r4.d
            com.huawei.openalliance.ad.analysis.d.a(r6, r5)
            java.lang.String r6 = "video content can only download in wifi"
            com.huawei.openalliance.ad.ho.b(r1, r6)
            r6 = r2
        L46:
            if (r6 == 0) goto L5c
            r6.a(r5)
            com.huawei.openalliance.ad.fx r7 = r4.f
            com.huawei.openalliance.ad.ru r6 = r7.a(r6)
            if (r6 == 0) goto L5c
            java.lang.String r7 = r6.a()
            java.lang.String r6 = r6.b()
            goto L5e
        L5c:
            r6 = r2
            r7 = r6
        L5e:
            boolean r0 = com.huawei.openalliance.ad.utils.cz.b(r7)
            if (r0 != 0) goto L7d
            r5.i(r7)
            r5.j(r6)
            com.huawei.openalliance.ad.fs r6 = r4.c
            java.lang.String r7 = r5.n()
            long r6 = r6.d(r7)
            r5.b(r6)
            com.huawei.openalliance.ad.fs r6 = r4.c
            r6.a(r5)
            goto L7f
        L7d:
            r5 = r2
            r8 = r5
        L7f:
            java.lang.Object[] r6 = new java.lang.Object[]{r8}
            java.lang.String r7 = "downloadAndSaveContent, contentId: %s. "
            com.huawei.openalliance.ad.ho.a(r1, r7, r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.or.a(com.huawei.openalliance.ad.db.bean.ContentRecord, long, java.lang.String):com.huawei.openalliance.ad.db.bean.ContentRecord");
    }

    private ContentRecord a(ContentRecord contentRecord, long j, int i) {
        String m = contentRecord.m();
        boolean e = e(contentRecord);
        ho.b("ContentProcessor", "downContent: %s isExist: %s isPreContent: %s", m, Boolean.valueOf(e), Boolean.valueOf(this.b));
        ho.a("ContentProcessor", "path: %s", contentRecord.y());
        if (e && !this.b) {
            a(contentRecord, m);
            d(contentRecord);
        } else {
            if (!e) {
                int i2 = this.f7396a;
                if (1 == i2 || 18 == i2) {
                    contentRecord.q(com.huawei.openalliance.ad.utils.d.a(i));
                    return a(contentRecord, j, m);
                }
                this.c.a(contentRecord);
                return contentRecord;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add("updateTime");
            if (TextUtils.isEmpty(contentRecord.l())) {
                this.c.a(contentRecord, arrayList, m);
            } else {
                this.c.a(contentRecord, arrayList);
            }
            a(m, contentRecord.an(), "normal");
            a(m, contentRecord.an(), "ar");
            a(m, contentRecord.an(), Constants.TPLATE_CACHE);
        }
        c(contentRecord);
        return contentRecord;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0082 A[Catch: all -> 0x009e, TryCatch #2 {all -> 0x009e, blocks: (B:36:0x006c, B:38:0x0072, B:21:0x007e, B:23:0x0082, B:26:0x008b, B:20:0x007b), top: B:35:0x006c }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x006c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.huawei.openalliance.ad.beans.metadata.CheckResult a(final com.huawei.openalliance.ad.db.bean.ContentRecord r9, final boolean r10) {
        /*
            r8 = this;
            java.lang.String r0 = "ContentProcessor"
            com.huawei.openalliance.ad.or$4 r1 = new com.huawei.openalliance.ad.or$4
            r1.<init>()
            java.util.concurrent.Future r1 = com.huawei.openalliance.ad.utils.dc.a(r1)
            com.huawei.openalliance.ad.or$5 r2 = new com.huawei.openalliance.ad.or$5
            r2.<init>()
            java.util.concurrent.Future r2 = com.huawei.openalliance.ad.utils.dc.a(r2)
            r3 = 2
            r4 = 1
            r5 = 0
            java.lang.Object r1 = r1.get()     // Catch: java.lang.Throwable -> L3e
            com.huawei.openalliance.ad.beans.metadata.CheckResult r1 = (com.huawei.openalliance.ad.beans.metadata.CheckResult) r1     // Catch: java.lang.Throwable -> L3e
            java.lang.Object[] r6 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L3c
            if (r1 == 0) goto L29
            boolean r7 = r1.a()     // Catch: java.lang.Throwable -> L3c
            if (r7 == 0) goto L29
            r7 = r4
            goto L2a
        L29:
            r7 = r5
        L2a:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)     // Catch: java.lang.Throwable -> L3c
            r6[r5] = r7     // Catch: java.lang.Throwable -> L3c
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r10)     // Catch: java.lang.Throwable -> L3c
            r6[r4] = r7     // Catch: java.lang.Throwable -> L3c
            java.lang.String r7 = "exist in sdk: %s, is spare: %s"
            com.huawei.openalliance.ad.ho.b(r0, r7, r6)     // Catch: java.lang.Throwable -> L3c
            goto L52
        L3c:
            r6 = move-exception
            goto L41
        L3e:
            r1 = move-exception
            r6 = r1
            r1 = 0
        L41:
            java.lang.Class r6 = r6.getClass()
            java.lang.String r6 = r6.getSimpleName()
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            java.lang.String r7 = "exist in sdk: %s"
            com.huawei.openalliance.ad.ho.c(r0, r7, r6)
        L52:
            if (r1 == 0) goto L64
            boolean r6 = r1.a()
            if (r6 != 0) goto L5b
            goto L64
        L5b:
            com.huawei.openalliance.ad.or$6 r10 = new com.huawei.openalliance.ad.or$6
            r10.<init>()
            com.huawei.openalliance.ad.utils.m.f(r10)
            goto Lb3
        L64:
            java.lang.Object r2 = r2.get()     // Catch: java.lang.Throwable -> La1
            com.huawei.openalliance.ad.beans.metadata.CheckResult r2 = (com.huawei.openalliance.ad.beans.metadata.CheckResult) r2     // Catch: java.lang.Throwable -> La1
            if (r2 == 0) goto L7b
            boolean r1 = r2.a()     // Catch: java.lang.Throwable -> L9e
            if (r1 == 0) goto L7b
            com.huawei.openalliance.ad.fs r1 = r8.c     // Catch: java.lang.Throwable -> L9e
            r1.a(r9)     // Catch: java.lang.Throwable -> L9e
            r8.a(r9, r3)     // Catch: java.lang.Throwable -> L9e
            goto L7e
        L7b:
            r8.a(r9, r5)     // Catch: java.lang.Throwable -> L9e
        L7e:
            java.lang.Object[] r9 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L9e
            if (r2 == 0) goto L8a
            boolean r1 = r2.a()     // Catch: java.lang.Throwable -> L9e
            if (r1 == 0) goto L8a
            r1 = r4
            goto L8b
        L8a:
            r1 = r5
        L8b:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch: java.lang.Throwable -> L9e
            r9[r5] = r1     // Catch: java.lang.Throwable -> L9e
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r10)     // Catch: java.lang.Throwable -> L9e
            r9[r4] = r10     // Catch: java.lang.Throwable -> L9e
            java.lang.String r10 = "exist in kit: %s, is spare: %s"
            com.huawei.openalliance.ad.ho.b(r0, r10, r9)     // Catch: java.lang.Throwable -> L9e
            r1 = r2
            goto Lb3
        L9e:
            r9 = move-exception
            r1 = r2
            goto La2
        La1:
            r9 = move-exception
        La2:
            java.lang.Class r9 = r9.getClass()
            java.lang.String r9 = r9.getSimpleName()
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            java.lang.String r10 = "exist in kit: %s"
            com.huawei.openalliance.ad.ho.c(r0, r10, r9)
        Lb3:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.or.a(com.huawei.openalliance.ad.db.bean.ContentRecord, boolean):com.huawei.openalliance.ad.beans.metadata.CheckResult");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CheckResult a(ContentRecord contentRecord, String str, String str2) {
        String c = c(str2, Constants.TPLATE_CACHE);
        String T = contentRecord.T();
        if (com.huawei.openalliance.ad.utils.cz.b(c)) {
            ho.b("ContentProcessor", "checkFileExistInTplate filePath is blank %s.", str);
            return new CheckResult(false, "filePath is blank", c);
        }
        if (com.huawei.openalliance.ad.utils.ae.c(this.d, c, Constants.TPLATE_CACHE)) {
            ho.b("ContentProcessor", "tplate localFilePath is %s", com.huawei.openalliance.ad.utils.dl.a(c));
            return new CheckResult(true, str2, c, T);
        }
        ho.b("ContentProcessor", "checkFileExistInTplate is FilePath is invalid : %s.", str);
        com.huawei.openalliance.ad.utils.ae.a(this.d, str2, Constants.TPLATE_CACHE);
        return new CheckResult(false, str2, c);
    }

    public or(Context context, boolean z, int i) {
        ho.b("ContentProcessor", "ContentProcessor - isPreContent: %s", Boolean.valueOf(z));
        this.d = context.getApplicationContext();
        this.b = z;
        this.f = fb.a(context);
        this.g = fh.b(context);
        this.c = es.a(context);
        this.h = new com.huawei.openalliance.ad.analysis.c(context);
        this.f7396a = i;
        this.e = eu.a(context);
    }
}
