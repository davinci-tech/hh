package defpackage;

import android.content.Context;
import android.location.GnssStatus;
import android.location.GpsStatus;
import android.location.Location;
import com.huawei.hianalytics.visual.autocollect.HAWebViewInterface;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlocationmgr.model.IOriginalGpsStatusListener;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.NoSuchElementException;

/* loaded from: classes4.dex */
public class gwd implements IOriginalGpsStatusListener {
    private Location b;
    private long d;
    private long g;
    private int j;
    private long n;
    private long o;
    private final Object f = new Object();
    private long h = 0;
    private long m = 0;
    private long r = 0;
    private long i = 0;
    private long s = 0;
    private long e = 0;
    private int k = 1;
    private long l = 0;
    private int[] c = new int[21];

    /* renamed from: a, reason: collision with root package name */
    private int[] f12967a = new int[11];

    public gwd() {
        this.d = 0L;
        this.d = System.currentTimeMillis();
    }

    public long[] e() {
        long[] jArr = {this.e, this.h, this.m, this.r, this.i, this.s};
        LogUtil.a("Track_GPSPointBIUtil", Arrays.toString(jArr));
        return jArr;
    }

    @Override // com.huawei.hwlocationmgr.model.IOriginalGpsStatusListener
    public void updateGpsStatus(GpsStatus gpsStatus) {
        if (gpsStatus == null) {
            int i = this.k;
            if (i != 1) {
                a(i, 1);
                return;
            }
            return;
        }
        int aUL_ = aUL_(gpsStatus);
        int i2 = this.k;
        if (aUL_ != i2) {
            a(i2, aUL_);
        }
    }

    @Override // com.huawei.hwlocationmgr.model.IOriginalGpsStatusListener
    public void updateGnssStatus(GnssStatus gnssStatus) {
        if (gnssStatus == null) {
            int i = this.k;
            if (i != 1) {
                a(i, 1);
                return;
            }
            return;
        }
        int aUK_ = aUK_(gnssStatus);
        int i2 = this.k;
        if (aUK_ != i2) {
            a(i2, aUK_);
        }
    }

    @Override // com.huawei.hwlocationmgr.model.IOriginalGpsStatusListener
    public void updateGpsStatus(int i) {
        int i2 = this.k;
        if (i2 != i) {
            a(i2, i);
        }
    }

    private void a(int i, int i2) {
        long currentTimeMillis = System.currentTimeMillis();
        if (i == 0) {
            this.e += (currentTimeMillis - this.d) - this.l;
        } else if (i == 1) {
            this.h += (currentTimeMillis - this.d) - this.l;
        } else if (i == 3) {
            this.m += (currentTimeMillis - this.d) - this.l;
        } else if (i == 4) {
            this.r += (currentTimeMillis - this.d) - this.l;
        } else if (i == 5) {
            this.i += (currentTimeMillis - this.d) - this.l;
        } else if (i == 6) {
            this.s += (currentTimeMillis - this.d) - this.l;
        }
        this.l = 0L;
        this.d = currentTimeMillis;
        this.k = i2;
    }

    private int aUK_(GnssStatus gnssStatus) {
        int satelliteCount = gnssStatus.getSatelliteCount();
        if (satelliteCount < 4) {
            return 3;
        }
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < satelliteCount; i4++) {
            try {
                if (gnssStatus.usedInFix(i4)) {
                    if (gnssStatus.getCn0DbHz(i4) >= 35.0f) {
                        i2++;
                    } else if (gnssStatus.getCn0DbHz(i4) < 30.0f) {
                        if (gnssStatus.getCn0DbHz(i4) <= 0.0f) {
                            LogUtil.a("Track_GPSPointBIUtil", "useless satellite singal");
                        }
                        i3++;
                    }
                    i++;
                    i3++;
                }
            } catch (NoSuchElementException e) {
                LogUtil.h("Track_GPSPointBIUtil", e.getMessage());
            }
        }
        if (i2 >= 4) {
            return 6;
        }
        if (i >= 4) {
            return 5;
        }
        return i3 > 0 ? 4 : 3;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x007a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int aUL_(android.location.GpsStatus r11) {
        /*
            r10 = this;
            java.lang.String r0 = "Track_GPSPointBIUtil"
            int r1 = r11.getMaxSatellites()
            r2 = 3
            r3 = 4
            if (r1 >= r3) goto Lb
            return r2
        Lb:
            r1 = 0
            java.lang.Iterable r4 = r11.getSatellites()     // Catch: java.util.NoSuchElementException -> L68
            if (r4 == 0) goto L65
            java.lang.Iterable r11 = r11.getSatellites()     // Catch: java.util.NoSuchElementException -> L68
            java.util.Iterator r11 = r11.iterator()     // Catch: java.util.NoSuchElementException -> L68
            r4 = r1
            r5 = r4
            r6 = r5
        L1d:
            boolean r7 = r11.hasNext()     // Catch: java.util.NoSuchElementException -> L62
            if (r7 == 0) goto L77
            java.lang.Object r7 = r11.next()     // Catch: java.util.NoSuchElementException -> L62
            android.location.GpsSatellite r7 = (android.location.GpsSatellite) r7     // Catch: java.util.NoSuchElementException -> L62
            if (r7 == 0) goto L1d
            boolean r8 = r7.usedInFix()     // Catch: java.util.NoSuchElementException -> L62
            if (r8 == 0) goto L1d
            float r8 = r7.getSnr()     // Catch: java.util.NoSuchElementException -> L62
            r9 = 1108082688(0x420c0000, float:35.0)
            int r8 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1))
            if (r8 < 0) goto L3e
            int r5 = r5 + 1
            goto L48
        L3e:
            float r8 = r7.getSnr()     // Catch: java.util.NoSuchElementException -> L62
            r9 = 1106247680(0x41f00000, float:30.0)
            int r8 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1))
            if (r8 < 0) goto L4b
        L48:
            int r4 = r4 + 1
            goto L54
        L4b:
            float r7 = r7.getSnr()     // Catch: java.util.NoSuchElementException -> L62
            r8 = 0
            int r7 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r7 <= 0) goto L57
        L54:
            int r6 = r6 + 1
            goto L1d
        L57:
            r7 = 1
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch: java.util.NoSuchElementException -> L62
            java.lang.String r8 = "useless satellite singal"
            r7[r1] = r8     // Catch: java.util.NoSuchElementException -> L62
            com.huawei.hwlogsmodel.LogUtil.a(r0, r7)     // Catch: java.util.NoSuchElementException -> L62
            goto L1d
        L62:
            r11 = move-exception
            r1 = r6
            goto L6b
        L65:
            r4 = r1
            r6 = r4
            goto L78
        L68:
            r11 = move-exception
            r4 = r1
            r5 = r4
        L6b:
            java.lang.String r11 = r11.getMessage()
            java.lang.Object[] r11 = new java.lang.Object[]{r11}
            com.huawei.hwlogsmodel.LogUtil.h(r0, r11)
            r6 = r1
        L77:
            r1 = r5
        L78:
            if (r1 < r3) goto L7c
            r11 = 6
            return r11
        L7c:
            if (r4 < r3) goto L80
            r11 = 5
            return r11
        L80:
            if (r6 <= 0) goto L83
            return r3
        L83:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.gwd.aUL_(android.location.GpsStatus):int");
    }

    public void c() {
        synchronized (this.f) {
            this.n = System.currentTimeMillis();
        }
    }

    public void d() {
        synchronized (this.f) {
            long currentTimeMillis = System.currentTimeMillis() - this.n;
            if (currentTimeMillis < 0) {
                currentTimeMillis = 0;
            }
            this.o += currentTimeMillis;
            this.l += currentTimeMillis;
        }
    }

    public void aUM_(Location location) {
        synchronized (this.f) {
            if (location != null) {
                if (!HAWebViewInterface.NETWORK.equals(location.getProvider())) {
                    int accuracy = (int) (location.getAccuracy() / 5.0f);
                    int i = 0;
                    if (accuracy > 20) {
                        accuracy = 20;
                    } else if (accuracy < 0) {
                        accuracy = 0;
                    }
                    int[] iArr = this.c;
                    iArr[accuracy] = iArr[accuracy] + 1;
                    if (this.b != null) {
                        long time = (location.getTime() - this.b.getTime()) - this.o;
                        if (time >= 20000) {
                            this.j++;
                            this.g += time;
                        }
                        int i2 = (int) (time / 1000);
                        if (i2 > 10) {
                            i = 10;
                        } else if (i2 >= 0) {
                            i = i2;
                        }
                        int[] iArr2 = this.f12967a;
                        iArr2[i] = iArr2[i] + 1;
                        this.o = 0L;
                    }
                    this.b = location;
                }
            }
        }
    }

    public void c(Context context) {
        if (context == null) {
            LogUtil.a("Track_GPSPointBIUtil", "updateBi ", "context is null");
        }
        HashMap hashMap = new HashMap(5);
        hashMap.put("gpsAccArray", Arrays.toString(this.c));
        hashMap.put("gpsTimeArray", Arrays.toString(this.f12967a));
        hashMap.put("gpsLostCount", Integer.valueOf(this.j));
        hashMap.put("gpsLostTotalTime", Long.valueOf(this.g));
        long[] e = e();
        hashMap.put("gpsStateStat", Arrays.toString(e));
        ixx.d().d(context, AnalyticsValue.MOTION_TRACK_1040015.value(), hashMap, 0);
        LogUtil.a("Track_GPSPointBIUtil", Arrays.toString(this.c), " ", Arrays.toString(this.f12967a), " ", Integer.valueOf(this.j), " ", Long.valueOf(this.g), " ", Arrays.toString(e));
    }
}
