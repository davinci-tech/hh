package com.huawei.hmf.services.ui.internal;

import android.util.LongSparseArray;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes9.dex */
class ObjectPool {
    private final LongSparseArray<Object> pool = new LongSparseArray<>();
    private static final ObjectPool instance = new ObjectPool();
    private static final AtomicLong UNIQUE_ID = new AtomicLong(0);

    private ObjectPool() {
    }

    public static ObjectPool getInstance() {
        return instance;
    }

    public Long add(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("value cannot be null.");
        }
        Long valueOf = Long.valueOf(UNIQUE_ID.getAndIncrement());
        this.pool.put(valueOf.longValue(), obj);
        return valueOf;
    }

    public Object get(Long l) {
        return this.pool.get(l.longValue());
    }

    public void remove(Long l) {
        this.pool.remove(l.longValue());
    }
}
