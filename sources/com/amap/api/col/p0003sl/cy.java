package com.amap.api.col.p0003sl;

import com.autonavi.base.ae.gmap.style.StyleItem;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public final class cy {

    /* renamed from: a, reason: collision with root package name */
    private Map<Integer, StyleItem> f954a = new ConcurrentHashMap();
    private Object b = null;
    private StyleItem[] c;

    public final Map<Integer, StyleItem> a() {
        return this.f954a;
    }

    public final StyleItem[] b() {
        Map<Integer, StyleItem> map = this.f954a;
        if (map == null || map.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (StyleItem styleItem : this.f954a.values()) {
            if (styleItem.isValid()) {
                arrayList.add(styleItem);
            }
        }
        int size = arrayList.size();
        if (size <= 0) {
            return null;
        }
        StyleItem[] styleItemArr = (StyleItem[]) arrayList.toArray(new StyleItem[size]);
        this.c = styleItemArr;
        return styleItemArr;
    }

    public final StyleItem[] c() {
        return this.c;
    }

    public final Object d() {
        return this.b;
    }
}
