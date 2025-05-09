package com.huawei.health.connectivity.extendstepcounter;

import android.content.Context;
import android.util.SparseArray;
import com.huawei.health.manager.common.FlushableStepDataCache;
import com.huawei.health.manager.util.MotionType;
import com.huawei.health.manager.util.UserInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.jdl;
import health.compact.a.OneMinuteStepData;
import health.compact.a.StepDataFileCache;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class ExtendStepDataManager {
    private FlushableStepDataCache b;
    private final Object c = new Object();
    private Context d;
    private StepDataFileCache e;

    public ExtendStepDataManager(Context context) {
        this.b = null;
        this.e = null;
        this.d = null;
        if (context == null) {
            LogUtil.h("Step_ExtendStepDataManager", "ExtendStepDataManager context is null.");
            this.d = BaseApplication.getContext();
        } else {
            this.d = context;
        }
        this.b = new FlushableStepDataCache(this.d);
        this.e = new StepDataFileCache(this.d, "StepCounterFileCache");
        g();
    }

    public void b(int i, long j, int i2, int i3, int i4) {
        synchronized (this.c) {
            FlushableStepDataCache flushableStepDataCache = this.b;
            if (flushableStepDataCache == null) {
                return;
            }
            OneMinuteStepData d = flushableStepDataCache.d(j);
            if (d != null) {
                int b = MotionType.b(MotionType.b(d.d()), i4);
                d.b(i2, i3);
                d.a(MotionType.a(b));
                this.b.d(d);
            } else {
                this.b.e(i, j, i2, i3, MotionType.a(i4));
            }
        }
    }

    private void g() {
        LogUtil.a("Step_ExtendStepDataManager", "initCacheFromFile enter...");
        long t = jdl.t(System.currentTimeMillis());
        synchronized (this.c) {
            try {
                this.e.d(this.d, this.b, t, System.currentTimeMillis());
            } catch (Exception unused) {
                LogUtil.b("Step_ExtendStepDataManager", "initMemoryFromFile exception");
            }
        }
    }

    public void b() {
        synchronized (this.c) {
            try {
                this.e.akO_(this.d, this.b.akL_());
            } catch (Exception unused) {
                LogUtil.b("Step_ExtendStepDataManager", "flushMemoryCacheToFile exception");
            }
        }
    }

    public void e() {
        synchronized (this.c) {
            this.b.e();
            try {
                this.e.b(this.d);
            } catch (Exception unused) {
                LogUtil.b("Step_ExtendStepDataManager", "clearCache exception");
            }
        }
    }

    public double c() {
        synchronized (this.c) {
            SparseArray<OneMinuteStepData> akL_ = this.b.akL_();
            if (akL_ == null) {
                LogUtil.h("Step_ExtendStepDataManager", "calculateCaloriesWithCache dataArray == null");
                return 0.0d;
            }
            int i = 0;
            for (int i2 = 0; i2 < akL_.size(); i2++) {
                if (akL_.valueAt(i2).i()) {
                    i = (int) (i + akL_.valueAt(i2).e(UserInfo.d()));
                }
            }
            return i;
        }
    }

    public void d() {
        synchronized (this.c) {
            this.b.a();
        }
    }

    public void b(String str) {
        synchronized (this.c) {
            if (str != null) {
                this.b.e(str);
            } else {
                LogUtil.h("Step_ExtendStepDataManager", "deviceUuid == null");
            }
        }
    }

    public void c(FlushableStepDataCache.MyFlushCallback myFlushCallback) {
        synchronized (this.c) {
            if (myFlushCallback != null) {
                this.b.b(myFlushCallback);
            } else {
                LogUtil.h("Step_ExtendStepDataManager", "callback == null");
            }
        }
    }

    public int f() {
        int b;
        synchronized (this.c) {
            b = this.b.b();
        }
        return b;
    }

    public int i() {
        int size;
        synchronized (this.c) {
            size = this.b.akL_().size();
        }
        return size;
    }

    public double a() {
        synchronized (this.c) {
            SparseArray<OneMinuteStepData> akL_ = this.b.akL_();
            if (akL_ == null) {
                LogUtil.h("Step_ExtendStepDataManager", "calculateAltitudeWithCache datas == null");
                return 0.0d;
            }
            int i = 0;
            for (int i2 = 0; i2 < akL_.size(); i2++) {
                if (akL_.valueAt(i2).i()) {
                    i += akL_.valueAt(i2).c();
                }
            }
            return i;
        }
    }

    public OneMinuteStepData d(int i) {
        OneMinuteStepData d;
        synchronized (this.c) {
            d = this.b.d(TimeUnit.MINUTES.toMillis(i));
        }
        return d;
    }
}
