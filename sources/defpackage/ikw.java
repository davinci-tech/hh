package defpackage;

import android.util.ArrayMap;
import com.huawei.hihealthservice.auth.WhiteListApp;
import java.util.Map;

/* loaded from: classes7.dex */
public class ikw {

    /* renamed from: a, reason: collision with root package name */
    private Map<String, WhiteListApp> f13419a;

    private ikw() {
        this.f13419a = new ArrayMap(16);
    }

    static class a {
        private static final ikw c = new ikw();
    }

    public static ikw b() {
        return a.c;
    }

    public WhiteListApp d(String str) {
        return this.f13419a.get(str);
    }

    public void c(Map<String, WhiteListApp> map) {
        this.f13419a.clear();
        this.f13419a = map;
    }
}
