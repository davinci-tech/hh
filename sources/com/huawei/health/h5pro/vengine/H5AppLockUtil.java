package com.huawei.health.h5pro.vengine;

import com.huawei.health.h5pro.utils.LogUtil;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes3.dex */
public class H5AppLockUtil {
    public static final Map<String, H5AppLock> e = new ConcurrentHashMap();

    public static void unLock(String str) {
        H5AppLock h5AppLock = e.get(str);
        if (h5AppLock != null) {
            h5AppLock.unLock(str);
        } else {
            LogUtil.w(getTag(str), "unLock: h5AppLock is null");
        }
    }

    public static boolean lock(String str, long j) {
        Map<String, H5AppLock> map = e;
        H5AppLock h5AppLock = map.get(str);
        LogUtil.i(getTag(str), "lock: getH5AppLock-> " + h5AppLock);
        if (h5AppLock == null) {
            synchronized (map) {
                h5AppLock = map.get(str);
                if (h5AppLock == null) {
                    map.put(str, new H5AppLock());
                    LogUtil.i(getTag(str), "lock: getH5AppLock -> new");
                    return true;
                }
            }
        }
        return h5AppLock.lock(str, j);
    }

    public static class H5AppLock {

        /* renamed from: a, reason: collision with root package name */
        public final AtomicBoolean f2460a = new AtomicBoolean(true);
        public volatile CountDownLatch b = new CountDownLatch(1);

        public void unLock(String str) {
            if (!this.f2460a.compareAndSet(true, false) || this.b.getCount() <= 0) {
                LogUtil.w(H5AppLockUtil.getTag(str), "unLock: failed");
            } else {
                LogUtil.i(H5AppLockUtil.getTag(str), "unLock: succeeded");
                this.b.countDown();
            }
        }

        public boolean lock(String str, long j) {
            LogUtil.i(H5AppLockUtil.getTag(str), "lock: start -> " + j);
            if (this.b.getCount() != 0 || !this.f2460a.compareAndSet(false, true)) {
                LogUtil.i(H5AppLockUtil.getTag(str), "lock: await");
                try {
                } catch (InterruptedException e) {
                    LogUtil.e("H5PRO_H5AppLockUtil", "lock: exception -> " + e.getMessage());
                }
                if (this.b.await(j, TimeUnit.MILLISECONDS)) {
                    LogUtil.i(H5AppLockUtil.getTag(str), "lock: await -> true");
                    if (this.f2460a.compareAndSet(false, true)) {
                        this.b = new CountDownLatch(1);
                        LogUtil.i(H5AppLockUtil.getTag(str), "lock: true");
                        return true;
                    }
                    LogUtil.i(H5AppLockUtil.getTag(str), "lock: end");
                    return false;
                }
                LogUtil.w(H5AppLockUtil.getTag(str), "lock: await -> false");
                return false;
            }
            this.b = new CountDownLatch(1);
            LogUtil.i(H5AppLockUtil.getTag(str), "lock: true");
            return true;
        }
    }

    public static String getTag(String str) {
        return LogUtil.getTag(str, "AppLocker");
    }
}
