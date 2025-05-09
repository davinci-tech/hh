package com.huawei.openalliance.ad;

import android.os.FileObserver;
import java.io.File;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class dm extends FileObserver {

    /* renamed from: a, reason: collision with root package name */
    private Map<String, Integer> f6708a;

    @Override // android.os.FileObserver
    public void onEvent(int i, String str) {
        if (i == 16) {
            if (ho.a()) {
                ho.a("FileListener", "CLOSE_NOWRITE, path= %s", str);
            }
            d(str);
        } else {
            if (i != 32) {
                return;
            }
            if (ho.a()) {
                ho.a("FileListener", "OPEN, path= %s", str);
            }
            c(str);
        }
    }

    public boolean a(String str) {
        if (ho.a()) {
            ho.a("FileListener", "accessMap = %s", Arrays.asList(this.f6708a));
        }
        return b(str);
    }

    private void d(String str) {
        if (ho.a()) {
            ho.a("FileListener", "setNotAccessed, accessMap = %s", Arrays.asList(this.f6708a));
        }
        if (this.f6708a.containsKey(str)) {
            if (this.f6708a.get(str).intValue() <= 1) {
                this.f6708a.remove(str);
            } else {
                Map<String, Integer> map = this.f6708a;
                map.put(str, Integer.valueOf(map.get(str).intValue() - 1));
            }
        }
    }

    private void c(String str) {
        Map<String, Integer> map;
        int i;
        if (ho.a()) {
            ho.a("FileListener", "setNotAccessed, accessMap = %s", Arrays.asList(this.f6708a));
        }
        ho.a("FileListener", "setAccessed, accessMap = %s", Arrays.asList(this.f6708a));
        if (this.f6708a.containsKey(str)) {
            map = this.f6708a;
            i = Integer.valueOf(map.get(str).intValue() + 1);
        } else {
            map = this.f6708a;
            i = 1;
        }
        map.put(str, i);
    }

    private boolean b(String str) {
        return this.f6708a.containsKey(str) && this.f6708a.get(str).intValue() > 0;
    }

    public dm(String str) {
        super(str);
        this.f6708a = new ConcurrentHashMap();
    }

    public dm(File file) {
        super(file);
        this.f6708a = new ConcurrentHashMap();
    }
}
