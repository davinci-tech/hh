package com.huawei.openalliance.ad;

import android.content.ComponentName;
import android.content.Intent;
import com.huawei.openalliance.ad.beans.metadata.ApkInfo;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.AppInfo;

/* loaded from: classes5.dex */
public class tf {

    /* renamed from: a, reason: collision with root package name */
    private String f7531a;
    private String b;
    private String c;
    private String d;
    private int e;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private String f7532a;
        private String b;
        private String c;
        private String d;
        private int e;

        public a c(String str) {
            this.c = str;
            return this;
        }

        public a b(String str) {
            this.b = str;
            return this;
        }

        public tf a() {
            return new tf(this);
        }

        public a a(String str) {
            this.f7532a = str;
            return this;
        }

        public a a(AppInfo appInfo) {
            if (appInfo == null) {
                return this;
            }
            String packageName = appInfo.getPackageName();
            String y = appInfo.y();
            if (!com.huawei.openalliance.ad.utils.cz.b(packageName)) {
                this.c = packageName;
            }
            if (!com.huawei.openalliance.ad.utils.cz.b(y)) {
                this.d = y;
            }
            return this;
        }

        public a a(ContentRecord contentRecord) {
            if (contentRecord == null) {
                ho.c("RedirectionMatchParam.Builder", "send param by content record,record is null.");
                return this;
            }
            this.f7532a = contentRecord.m();
            this.b = contentRecord.n();
            this.e = contentRecord.e();
            return this;
        }

        public a a(ApkInfo apkInfo) {
            if (apkInfo == null) {
                return this;
            }
            String a2 = apkInfo.a();
            String M = apkInfo.M();
            if (!com.huawei.openalliance.ad.utils.cz.b(a2)) {
                this.c = a2;
            }
            if (!com.huawei.openalliance.ad.utils.cz.b(M)) {
                this.d = M;
            }
            return this;
        }

        public a a(Intent intent) {
            if (intent == null) {
                return this;
            }
            if (com.huawei.openalliance.ad.utils.cz.b(this.c)) {
                this.c = intent.getPackage();
            }
            ComponentName component = intent.getComponent();
            if (component != null && com.huawei.openalliance.ad.utils.cz.b(this.d)) {
                this.d = component.getClassName();
            }
            return this;
        }

        public a a(int i) {
            this.e = i;
            return this;
        }
    }

    public String e() {
        return this.d;
    }

    public String d() {
        return this.c;
    }

    public String c() {
        return this.b;
    }

    public void b(String str) {
        this.d = str;
    }

    public String b() {
        return this.f7531a;
    }

    public void a(String str) {
        this.c = str;
    }

    public void a(Intent intent) {
        if (intent == null) {
            return;
        }
        if (this.c == null) {
            this.c = intent.getPackage();
        }
        ComponentName component = intent.getComponent();
        if (component != null) {
            b(component.getClassName());
        }
    }

    public int a() {
        return this.e;
    }

    public tf(a aVar) {
        this.c = aVar.c;
        this.d = aVar.d;
        this.f7531a = aVar.f7532a;
        this.b = aVar.b;
        this.e = aVar.e;
    }
}
