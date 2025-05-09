package com.autonavi.base.ae.gmap.maploader;

import com.autonavi.ae.gmap.maploader.Pools;

/* loaded from: classes8.dex */
public class ProcessingTile {
    private static final Pools.SynchronizedPool<ProcessingTile> M_POOL = new Pools.SynchronizedPool<>(30);
    public long mCreateTime = 0;
    public String mKeyName;

    public static ProcessingTile obtain(String str) {
        ProcessingTile acquire = M_POOL.acquire();
        if (acquire != null) {
            acquire.setParams(str);
            return acquire;
        }
        return new ProcessingTile(str);
    }

    public void recycle() {
        this.mKeyName = null;
        this.mCreateTime = 0L;
        M_POOL.release(this);
    }

    public ProcessingTile(String str) {
        setParams(str);
    }

    private void setParams(String str) {
        this.mKeyName = str;
        this.mCreateTime = System.currentTimeMillis() / 1000;
    }
}
