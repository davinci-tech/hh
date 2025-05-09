package defpackage;

import android.text.TextUtils;
import com.huawei.hihealthservice.hihealthkit.cpcheck.OnRequestCallBack;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class ipj<T> {

    /* renamed from: a, reason: collision with root package name */
    private final Map<String, Object> f13532a;
    private final String b;
    private final OnRequestCallBack<T> c;
    private final boolean d;
    private final Map<String, Object> e;
    private final String g;

    private ipj(e<T> eVar) {
        this.g = ((e) eVar).g;
        this.c = ((e) eVar).e;
        this.b = ((e) eVar).f13533a;
        this.f13532a = ((e) eVar).c;
        this.e = ((e) eVar).d;
        this.d = ((e) eVar).b;
    }

    public Map<String, Object> a() {
        return this.e;
    }

    public String d() {
        return this.b;
    }

    public String c() {
        return this.g;
    }

    public Map<String, Object> e() {
        return this.f13532a;
    }

    public OnRequestCallBack<T> b() {
        return this.c;
    }

    public boolean f() {
        return this.d;
    }

    public static final class e<T> {

        /* renamed from: a, reason: collision with root package name */
        private String f13533a;
        private boolean b;
        private Map<String, Object> c;
        private Map<String, Object> d;
        private OnRequestCallBack<T> e;
        private String g;

        public e(String str) {
            this.g = str;
        }

        public e<T> b(OnRequestCallBack<T> onRequestCallBack) {
            this.e = onRequestCallBack;
            return this;
        }

        public e<T> a(String str) {
            this.f13533a = str;
            return this;
        }

        public e<T> c(Map<String, Object> map) {
            this.c = map;
            return this;
        }

        public e<T> d(Map<String, Object> map) {
            this.d = map;
            return this;
        }

        public e<T> e(boolean z) {
            this.b = z;
            return this;
        }

        public ipj<T> a() {
            if (TextUtils.isEmpty(this.g)) {
                throw new IllegalArgumentException("the url is empty");
            }
            if (this.e == null) {
                throw new IllegalArgumentException("the OnRequestCallBack is null");
            }
            if (this.c == null) {
                this.c = new HashMap(10);
            }
            if (this.d == null) {
                this.d = new HashMap(10);
            }
            if (TextUtils.isEmpty(this.f13533a)) {
                this.f13533a = "GET";
            }
            return new ipj<>(this);
        }
    }
}
