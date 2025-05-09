package org.json.alipay;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

/* loaded from: classes8.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    public ArrayList f15915a;

    public String toString() {
        try {
            return "[" + a(",") + ']';
        } catch (Exception unused) {
            return null;
        }
    }

    public final Object a(int i) {
        Object obj = (i < 0 || i >= this.f15915a.size()) ? null : this.f15915a.get(i);
        if (obj != null) {
            return obj;
        }
        throw new JSONException("JSONArray[" + i + "] not found.");
    }

    public final int a() {
        return this.f15915a.size();
    }

    private String a(String str) {
        int size = this.f15915a.size();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                stringBuffer.append(str);
            }
            stringBuffer.append(b.a(this.f15915a.get(i)));
        }
        return stringBuffer.toString();
    }

    public a(c cVar) {
        this();
        char c;
        ArrayList arrayList;
        Object d;
        char c2 = cVar.c();
        if (c2 == '[') {
            c = ']';
        } else {
            if (c2 != '(') {
                throw cVar.a("A JSONArray text must start with '['");
            }
            c = ')';
        }
        if (cVar.c() == ']') {
            return;
        }
        do {
            cVar.a();
            char c3 = cVar.c();
            cVar.a();
            if (c3 == ',') {
                arrayList = this.f15915a;
                d = null;
            } else {
                arrayList = this.f15915a;
                d = cVar.d();
            }
            arrayList.add(d);
            char c4 = cVar.c();
            if (c4 != ')') {
                if (c4 != ',' && c4 != ';') {
                    if (c4 != ']') {
                        throw cVar.a("Expected a ',' or ']'");
                    }
                }
            }
            if (c == c4) {
                return;
            }
            throw cVar.a("Expected a '" + new Character(c) + "'");
        } while (cVar.c() != ']');
    }

    public a(Collection collection) {
        this.f15915a = collection == null ? new ArrayList() : new ArrayList(collection);
    }

    public a(String str) {
        this(new c(str));
    }

    public a(Object obj) {
        this();
        if (!obj.getClass().isArray()) {
            throw new JSONException("JSONArray initial value should be a string or collection or array.");
        }
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.f15915a.add(Array.get(obj, i));
        }
    }

    public a() {
        this.f15915a = new ArrayList();
    }
}
