package com.huawei.hwcloudjs.service.hms;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;

/* loaded from: classes5.dex */
public class a extends com.huawei.hwcloudjs.e.b.c<b> {
    private static final String b = "ActivityResultNotifier";
    private static a c = new a();

    /* loaded from: classes9.dex */
    public interface c {
        void a(b bVar);
    }

    public static final class b extends com.huawei.hwcloudjs.e.b.d {

        /* renamed from: a, reason: collision with root package name */
        private Intent f6233a;
        private int b;
        private int c;

        public int c() {
            return this.b;
        }

        public void b(int i) {
            this.b = i;
        }

        public int b() {
            return this.c;
        }

        public void a(Intent intent) {
            this.f6233a = intent;
        }

        public void a(int i) {
            this.c = i;
        }

        public Intent a() {
            return this.f6233a;
        }
    }

    public void a(Activity activity, Intent intent, int i, c cVar) {
        a().b(new C0159a(i, cVar));
        try {
            activity.startActivityForResult(intent, i);
        } catch (ActivityNotFoundException unused) {
            com.huawei.hwcloudjs.f.d.b(b, "ActivityNotFoundException .. ", true);
        }
    }

    public void a(int i, c cVar) {
        a().b(new C0159a(i, cVar));
    }

    /* renamed from: com.huawei.hwcloudjs.service.hms.a$a, reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    static final class C0159a implements com.huawei.hwcloudjs.e.b.b<b> {

        /* renamed from: a, reason: collision with root package name */
        private int f6232a;
        private c b;

        @Override // com.huawei.hwcloudjs.e.b.b
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public boolean onReceive(b bVar) {
            if (this.f6232a != bVar.c) {
                return true;
            }
            this.b.a(bVar);
            return false;
        }

        public C0159a(int i, c cVar) {
            this.f6232a = i;
            this.b = cVar;
        }
    }

    public static a a() {
        return c;
    }
}
