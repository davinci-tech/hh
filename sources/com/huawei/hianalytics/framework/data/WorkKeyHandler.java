package com.huawei.hianalytics.framework.data;

import com.huawei.hianalytics.framework.c;
import com.huawei.hianalytics.framework.d;
import com.huawei.hianalytics.framework.data.IAesKeyGetter;
import com.huawei.hianalytics.framework.data.WorkKeyHandler;
import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;

/* loaded from: classes4.dex */
public class WorkKeyHandler {
    public static final WorkKeyHandler d = new WorkKeyHandler();
    public static IAesKeyGetter e;

    /* renamed from: a, reason: collision with root package name */
    public final WorkKeyBean f3857a = new WorkKeyBean();
    public String b;
    public String c;

    public final void a() {
        long currentTimeMillis = System.currentTimeMillis();
        String generateSecureRandomStr = EncryptUtil.generateSecureRandomStr(16);
        String rsaEncrypt = d.e.f3856a.rsaEncrypt(this.b, this.c, generateSecureRandomStr);
        String str = this.c;
        long j = c.f3855a;
        this.f3857a.setEncrypted(rsaEncrypt);
        this.f3857a.setWorkKey(generateSecureRandomStr);
        this.f3857a.setUploadTime(currentTimeMillis);
        this.f3857a.setKeyTtlTime(j + currentTimeMillis);
        this.f3857a.setPubKeyVer(str);
        this.f3857a.setSource(0);
        IAesKeyGetter iAesKeyGetter = e;
        if (iAesKeyGetter != null) {
            iAesKeyGetter.saveWorkKeyBeanToDisk(this.f3857a);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x009d A[Catch: all -> 0x00c3, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x001f, B:10:0x002f, B:12:0x0033, B:14:0x0052, B:16:0x0058, B:18:0x005e, B:20:0x0066, B:24:0x0072, B:25:0x0094, B:27:0x009d, B:31:0x0091, B:33:0x00a1, B:38:0x00b2, B:39:0x00ba), top: B:2:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void refreshKey(java.lang.String r12, java.lang.String r13) {
        /*
            r11 = this;
            monitor-enter(r11)
            long r0 = r11.getUploadTime()     // Catch: java.lang.Throwable -> Lc3
            com.huawei.hianalytics.framework.d r2 = com.huawei.hianalytics.framework.d.e     // Catch: java.lang.Throwable -> Lc3
            com.huawei.hianalytics.framework.config.IMandatoryParameters r2 = r2.f3856a     // Catch: java.lang.Throwable -> Lc3
            android.util.Pair r12 = r2.getRsaPublicKeyFromNetWork(r12, r13)     // Catch: java.lang.Throwable -> Lc3
            java.lang.Object r13 = r12.first     // Catch: java.lang.Throwable -> Lc3
            java.lang.String r13 = (java.lang.String) r13     // Catch: java.lang.Throwable -> Lc3
            r11.b = r13     // Catch: java.lang.Throwable -> Lc3
            java.lang.Object r12 = r12.second     // Catch: java.lang.Throwable -> Lc3
            java.lang.String r12 = (java.lang.String) r12     // Catch: java.lang.Throwable -> Lc3
            r11.c = r12     // Catch: java.lang.Throwable -> Lc3
            boolean r12 = android.text.TextUtils.isEmpty(r13)     // Catch: java.lang.Throwable -> Lc3
            if (r12 != 0) goto Lba
            java.lang.String r12 = r11.c     // Catch: java.lang.Throwable -> Lc3
            boolean r12 = android.text.TextUtils.isEmpty(r12)     // Catch: java.lang.Throwable -> Lc3
            if (r12 == 0) goto L29
            goto Lba
        L29:
            r12 = 0
            int r2 = (r0 > r12 ? 1 : (r0 == r12 ? 0 : -1))
            if (r2 != 0) goto La1
            com.huawei.hianalytics.framework.data.IAesKeyGetter r0 = com.huawei.hianalytics.framework.data.WorkKeyHandler.e     // Catch: java.lang.Throwable -> Lc3
            if (r0 == 0) goto L4a
            com.huawei.hianalytics.framework.data.WorkKeyHandler$WorkKeyBean r12 = r0.getWorkKeyBeanFromDisk()     // Catch: java.lang.Throwable -> Lc3
            java.lang.String r13 = r12.workKey     // Catch: java.lang.Throwable -> Lc3
            java.lang.String r0 = r12.encrypted     // Catch: java.lang.Throwable -> Lc3
            java.lang.String r1 = r12.pubKeyVer     // Catch: java.lang.Throwable -> Lc3
            long r2 = r12.uploadTime     // Catch: java.lang.Throwable -> Lc3
            long r4 = r12.keyTtlTime     // Catch: java.lang.Throwable -> Lc3
            int r12 = r12.getSource()     // Catch: java.lang.Throwable -> Lc3
            r9 = r2
            r3 = r12
            r2 = r13
            r12 = r9
            goto L52
        L4a:
            java.lang.String r1 = ""
            java.lang.String r0 = ""
            java.lang.String r2 = ""
            r3 = 0
            r4 = r12
        L52:
            boolean r6 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Throwable -> Lc3
            if (r6 != 0) goto L91
            boolean r6 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> Lc3
            if (r6 != 0) goto L91
            java.lang.String r6 = "2.0"
            boolean r6 = android.text.TextUtils.equals(r1, r6)     // Catch: java.lang.Throwable -> Lc3
            if (r6 == 0) goto L91
            long r6 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> Lc3
            int r8 = (r12 > r6 ? 1 : (r12 == r6 ? 0 : -1))
            if (r8 >= 0) goto L91
            int r6 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r6 >= 0) goto L91
            com.huawei.hianalytics.framework.data.WorkKeyHandler$WorkKeyBean r6 = r11.f3857a     // Catch: java.lang.Throwable -> Lc3
            r6.setEncrypted(r0)     // Catch: java.lang.Throwable -> Lc3
            com.huawei.hianalytics.framework.data.WorkKeyHandler$WorkKeyBean r0 = r11.f3857a     // Catch: java.lang.Throwable -> Lc3
            r0.setWorkKey(r2)     // Catch: java.lang.Throwable -> Lc3
            com.huawei.hianalytics.framework.data.WorkKeyHandler$WorkKeyBean r0 = r11.f3857a     // Catch: java.lang.Throwable -> Lc3
            r0.setUploadTime(r12)     // Catch: java.lang.Throwable -> Lc3
            com.huawei.hianalytics.framework.data.WorkKeyHandler$WorkKeyBean r12 = r11.f3857a     // Catch: java.lang.Throwable -> Lc3
            r12.setKeyTtlTime(r4)     // Catch: java.lang.Throwable -> Lc3
            com.huawei.hianalytics.framework.data.WorkKeyHandler$WorkKeyBean r12 = r11.f3857a     // Catch: java.lang.Throwable -> Lc3
            r12.setPubKeyVer(r1)     // Catch: java.lang.Throwable -> Lc3
            com.huawei.hianalytics.framework.data.WorkKeyHandler$WorkKeyBean r12 = r11.f3857a     // Catch: java.lang.Throwable -> Lc3
            r12.setSource(r3)     // Catch: java.lang.Throwable -> Lc3
            goto L94
        L91:
            r11.a()     // Catch: java.lang.Throwable -> Lc3
        L94:
            com.huawei.hianalytics.framework.data.WorkKeyHandler$WorkKeyBean r12 = r11.f3857a     // Catch: java.lang.Throwable -> Lc3
            int r12 = r12.getSource()     // Catch: java.lang.Throwable -> Lc3
            r13 = 1
            if (r12 == r13) goto Lb8
            r11.b()     // Catch: java.lang.Throwable -> Lc3
            goto Lb8
        La1:
            long r12 = r11.getKeyTtl()     // Catch: java.lang.Throwable -> Lc3
            long r2 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> Lc3
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 >= 0) goto Lb2
            int r12 = (r2 > r12 ? 1 : (r2 == r12 ? 0 : -1))
            if (r12 >= 0) goto Lb2
            goto Lb8
        Lb2:
            r11.a()     // Catch: java.lang.Throwable -> Lc3
            r11.b()     // Catch: java.lang.Throwable -> Lc3
        Lb8:
            monitor-exit(r11)
            return
        Lba:
            java.lang.String r12 = "WorkKeyHandler"
            java.lang.String r13 = "get rsa pubkey config error"
            com.huawei.hianalytics.core.log.HiLog.sw(r12, r13)     // Catch: java.lang.Throwable -> Lc3
            monitor-exit(r11)
            return
        Lc3:
            r12 = move-exception
            monitor-exit(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hianalytics.framework.data.WorkKeyHandler.refreshKey(java.lang.String, java.lang.String):void");
    }

    public static class WorkKeyBean {
        public static final int SOURCE_QOES = 1;
        public static final int SOURCE_SDK = 0;
        public String encrypted;
        public String pubKeyVer;
        public String workKey;
        public long uploadTime = 0;
        public long keyTtlTime = 0;
        public int source = 0;

        public void setWorkKey(String str) {
            this.workKey = str;
        }

        public void setUploadTime(long j) {
            this.uploadTime = j;
        }

        public void setSource(int i) {
            this.source = i;
        }

        public void setPubKeyVer(String str) {
            this.pubKeyVer = str;
        }

        public void setKeyTtlTime(long j) {
            this.keyTtlTime = j;
        }

        public void setEncrypted(String str) {
            this.encrypted = str;
        }

        public String getWorkKey() {
            return this.workKey;
        }

        public long getUploadTime() {
            return this.uploadTime;
        }

        public int getSource() {
            return this.source;
        }

        public String getPubKeyVer() {
            return this.pubKeyVer;
        }

        public long getKeyTtlTime() {
            return this.keyTtlTime;
        }

        public String getEncrypted() {
            return this.encrypted;
        }
    }

    public String getWorkKey() {
        return this.f3857a.workKey;
    }

    public long getUploadTime() {
        return this.f3857a.uploadTime;
    }

    public String getPubKeyVer() {
        return this.f3857a.pubKeyVer;
    }

    public long getKeyTtl() {
        return this.f3857a.keyTtlTime;
    }

    public String getEncrypted() {
        return this.f3857a.encrypted;
    }

    public final void b() {
        IAesKeyGetter iAesKeyGetter = e;
        if (iAesKeyGetter == null) {
            return;
        }
        iAesKeyGetter.requestAesKey(this.b, new IAesKeyGetter.Callback() { // from class: com.huawei.hianalytics.framework.data.WorkKeyHandler$$ExternalSyntheticLambda0
            @Override // com.huawei.hianalytics.framework.data.IAesKeyGetter.Callback
            public final void onResult(WorkKeyHandler.WorkKeyBean workKeyBean) {
                WorkKeyHandler.this.a(workKeyBean);
            }
        });
    }

    public static void setAesKeyGetter(IAesKeyGetter iAesKeyGetter) {
        e = iAesKeyGetter;
    }

    public static WorkKeyHandler getHandler() {
        return d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(WorkKeyBean workKeyBean) {
        if (workKeyBean == null) {
            return;
        }
        this.f3857a.setPubKeyVer(workKeyBean.pubKeyVer);
        this.f3857a.setWorkKey(workKeyBean.workKey);
        this.f3857a.setEncrypted(workKeyBean.encrypted);
        this.f3857a.setKeyTtlTime(workKeyBean.keyTtlTime);
        this.f3857a.setUploadTime(workKeyBean.uploadTime);
        this.f3857a.setSource(workKeyBean.source);
    }
}
