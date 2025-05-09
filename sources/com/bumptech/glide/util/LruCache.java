package com.bumptech.glide.util;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class LruCache<T, Y> {
    private final Map<T, Entry<Y>> cache = new LinkedHashMap(100, 0.75f, true);
    private long currentSize;
    private final long initialMaxSize;
    private long maxSize;

    public int getSize(Y y) {
        return 1;
    }

    protected void onItemEvicted(T t, Y y) {
    }

    public LruCache(long j) {
        this.initialMaxSize = j;
        this.maxSize = j;
    }

    public void setSizeMultiplier(float f) {
        synchronized (this) {
            if (f < 0.0f) {
                throw new IllegalArgumentException("Multiplier must be >= 0");
            }
            this.maxSize = Math.round(this.initialMaxSize * f);
            evict();
        }
    }

    protected int getCount() {
        int size;
        synchronized (this) {
            size = this.cache.size();
        }
        return size;
    }

    public long getMaxSize() {
        long j;
        synchronized (this) {
            j = this.maxSize;
        }
        return j;
    }

    public long getCurrentSize() {
        long j;
        synchronized (this) {
            j = this.currentSize;
        }
        return j;
    }

    public boolean contains(T t) {
        boolean containsKey;
        synchronized (this) {
            containsKey = this.cache.containsKey(t);
        }
        return containsKey;
    }

    public Y get(T t) {
        Y y;
        synchronized (this) {
            Entry<Y> entry = this.cache.get(t);
            y = entry != null ? entry.value : null;
        }
        return y;
    }

    public Y put(T t, Y y) {
        synchronized (this) {
            int size = getSize(y);
            long j = size;
            if (j >= this.maxSize) {
                onItemEvicted(t, y);
                return null;
            }
            if (y != null) {
                this.currentSize += j;
            }
            Entry<Y> put = this.cache.put(t, y == null ? null : new Entry<>(y, size));
            if (put != null) {
                this.currentSize -= put.size;
                if (!put.value.equals(y)) {
                    onItemEvicted(t, put.value);
                }
            }
            evict();
            return put != null ? put.value : null;
        }
    }

    public Y remove(T t) {
        synchronized (this) {
            Entry<Y> remove = this.cache.remove(t);
            if (remove == null) {
                return null;
            }
            this.currentSize -= remove.size;
            return remove.value;
        }
    }

    public void clearMemory() {
        trimToSize(0L);
    }

    protected void trimToSize(long j) {
        synchronized (this) {
            while (this.currentSize > j) {
                Iterator<Map.Entry<T, Entry<Y>>> it = this.cache.entrySet().iterator();
                Map.Entry<T, Entry<Y>> next = it.next();
                Entry<Y> value = next.getValue();
                this.currentSize -= value.size;
                T key = next.getKey();
                it.remove();
                onItemEvicted(key, value.value);
            }
        }
    }

    private void evict() {
        trimToSize(this.maxSize);
    }

    static final class Entry<Y> {
        final int size;
        final Y value;

        Entry(Y y, int i) {
            this.value = y;
            this.size = i;
        }
    }
}
