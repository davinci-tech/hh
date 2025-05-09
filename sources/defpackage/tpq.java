package defpackage;

import com.huawei.wearengine.notify.Action;
import com.huawei.wearengine.notify.NotificationTemplate;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class tpq {

    /* renamed from: a, reason: collision with root package name */
    private Action f17328a;
    private int b;
    private int c;
    private HashMap<Integer, String> d;
    private String e;
    private int g;
    private String h;
    private String i;

    private tpq(c cVar) {
        this.b = cVar.e;
        this.e = cVar.f17329a;
        this.i = cVar.i;
        this.h = cVar.f;
        this.d = cVar.c;
        this.g = cVar.j;
        this.c = cVar.b;
        this.f17328a = cVar.d;
    }

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        private String f17329a;
        private HashMap<Integer, String> c;
        private Action d;
        private String f;
        private String i;
        private int e = -1;
        private int j = 2;
        private int b = -1;

        public c e(NotificationTemplate notificationTemplate) {
            this.e = notificationTemplate == null ? -1 : notificationTemplate.value();
            return this;
        }

        public c d(String str) {
            this.f17329a = str;
            return this;
        }

        public c b(String str) {
            this.i = str;
            return this;
        }

        public c e(String str) {
            this.f = str;
            return this;
        }

        public c e(HashMap<Integer, String> hashMap) {
            this.c = hashMap;
            return this;
        }

        public c a(Action action) {
            this.d = action;
            return this;
        }

        public tpq c() {
            return new tpq(this);
        }
    }

    public int e() {
        return this.b;
    }

    public String d() {
        return this.e;
    }

    public String h() {
        return this.i;
    }

    public String c() {
        return this.h;
    }

    public HashMap<Integer, String> b() {
        return this.d;
    }

    public Action a() {
        return this.f17328a;
    }
}
