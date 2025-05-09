package com.huawei.agconnect.apms;

import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.SparseIntArray;
import android.view.FrameMetrics;
import android.view.Window;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class f0 extends g0 implements Window.OnFrameMetricsAvailableListener {
    public static HandlerThread abc;
    public static Handler bcd;
    public int cde;
    public SparseIntArray[] def = new SparseIntArray[9];
    public ArrayList<WeakReference<Activity>> efg = new ArrayList<>();

    public f0(int i) {
        this.cde = i;
    }

    public final void abc(SparseIntArray sparseIntArray, long j) {
        if (sparseIntArray == null || j < 0) {
            return;
        }
        int i = (int) ((j + 500000) / 1000000);
        sparseIntArray.put(i, sparseIntArray.get(i) + 1);
    }

    @Override // com.huawei.agconnect.apms.g0
    public SparseIntArray[] bcd(Activity activity) {
        Iterator<WeakReference<Activity>> it = this.efg.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            WeakReference<Activity> next = it.next();
            if (next.get() == activity) {
                this.efg.remove(next);
                break;
            }
        }
        activity.getWindow().removeOnFrameMetricsAvailableListener(this);
        return this.def;
    }

    @Override // android.view.Window.OnFrameMetricsAvailableListener
    public void onFrameMetricsAvailable(Window window, FrameMetrics frameMetrics, int i) {
        if ((this.cde & 1) != 0) {
            SparseIntArray[] sparseIntArrayArr = this.def;
            if (sparseIntArrayArr.length > 0) {
                abc(sparseIntArrayArr[0], frameMetrics.getMetric(8));
            }
        }
        if ((this.cde & 2) != 0) {
            SparseIntArray[] sparseIntArrayArr2 = this.def;
            if (1 < sparseIntArrayArr2.length) {
                abc(sparseIntArrayArr2[1], frameMetrics.getMetric(1));
            }
        }
        if ((this.cde & 4) != 0) {
            SparseIntArray[] sparseIntArrayArr3 = this.def;
            if (2 < sparseIntArrayArr3.length) {
                abc(sparseIntArrayArr3[2], frameMetrics.getMetric(3));
            }
        }
        if ((this.cde & 16) != 0) {
            SparseIntArray[] sparseIntArrayArr4 = this.def;
            if (4 < sparseIntArrayArr4.length) {
                abc(sparseIntArrayArr4[4], frameMetrics.getMetric(5));
            }
        }
        if ((this.cde & 64) != 0) {
            SparseIntArray[] sparseIntArrayArr5 = this.def;
            if (6 < sparseIntArrayArr5.length) {
                abc(sparseIntArrayArr5[6], frameMetrics.getMetric(7));
            }
        }
        if ((this.cde & 8) != 0) {
            SparseIntArray[] sparseIntArrayArr6 = this.def;
            if (3 < sparseIntArrayArr6.length) {
                abc(sparseIntArrayArr6[3], frameMetrics.getMetric(4));
            }
        }
        if ((this.cde & 32) != 0) {
            SparseIntArray[] sparseIntArrayArr7 = this.def;
            if (5 < sparseIntArrayArr7.length) {
                abc(sparseIntArrayArr7[5], frameMetrics.getMetric(6));
            }
        }
        if ((this.cde & 128) != 0) {
            SparseIntArray[] sparseIntArrayArr8 = this.def;
            if (7 < sparseIntArrayArr8.length) {
                abc(sparseIntArrayArr8[7], frameMetrics.getMetric(0));
            }
        }
        if ((this.cde & 256) != 0) {
            SparseIntArray[] sparseIntArrayArr9 = this.def;
            if (8 < sparseIntArrayArr9.length) {
                abc(sparseIntArrayArr9[8], frameMetrics.getMetric(2));
            }
        }
    }

    @Override // com.huawei.agconnect.apms.g0
    public void abc(Activity activity) {
        if (abc == null) {
            HandlerThread handlerThread = new HandlerThread("FrameMetricsAggregator");
            abc = handlerThread;
            handlerThread.start();
            Looper looper = abc.getLooper();
            if (looper == null) {
                return;
            } else {
                bcd = new Handler(looper);
            }
        }
        for (int i = 0; i <= 8; i++) {
            SparseIntArray[] sparseIntArrayArr = this.def;
            if (sparseIntArrayArr[i] == null && (this.cde & (1 << i)) != 0) {
                sparseIntArrayArr[i] = new SparseIntArray();
            }
        }
        activity.getWindow().addOnFrameMetricsAvailableListener(this, bcd);
        this.efg.add(new WeakReference<>(activity));
    }
}
