package defpackage;

import com.huawei.health.healthcloudconfig.listener.DownloadCallback;
import java.io.File;

/* loaded from: classes3.dex */
public class drf extends dqy {

    /* renamed from: a, reason: collision with root package name */
    private String f11801a;
    private dqi b;

    public drf(a aVar) {
        super(aVar.c, aVar.b, aVar.e, aVar.f11802a, aVar.g);
        this.f11801a = aVar.d;
        this.b = aVar.j;
    }

    @Override // defpackage.dqy, com.huawei.networkclient.ProgressListener
    public void onFinish(Object obj) {
        super.onFinish(this.b);
        if (this.f11801a.equals("-1")) {
            return;
        }
        drd.d(this.d + this.c, this.f11801a);
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private File f11802a;
        private String b;
        private String c;
        private String d;
        private String e;
        private DownloadCallback g;
        private dqi j;

        public a a(String str) {
            this.c = str;
            return this;
        }

        public a e(String str) {
            this.b = str;
            return this;
        }

        public a b(String str) {
            this.e = str;
            return this;
        }

        public a e(File file) {
            this.f11802a = file;
            return this;
        }

        public a a(DownloadCallback downloadCallback) {
            this.g = downloadCallback;
            return this;
        }

        public a c(String str) {
            this.d = str;
            return this;
        }

        public a d(dqi dqiVar) {
            this.j = dqiVar;
            return this;
        }

        public drf e() {
            return new drf(this);
        }
    }
}
