package defpackage;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class suv {

    /* renamed from: a, reason: collision with root package name */
    private String f17244a;
    private String d;
    private boolean b = true;
    private Map<String, Object> c = new HashMap();
    private boolean e = true;

    public suv() {
    }

    public suv(String str, String str2) {
        this.f17244a = str;
        this.d = str2;
    }

    public void a(String str) {
        this.d = str;
    }

    public void b(String str) {
        this.f17244a = str;
    }

    public void c(boolean z) {
        this.e = z;
    }

    public Object e(String str) {
        return this.c.get(str);
    }

    public void a(Map<String, Object> map) {
        this.c.putAll(map);
    }

    public void b(boolean z) {
        this.b = z;
    }
}
