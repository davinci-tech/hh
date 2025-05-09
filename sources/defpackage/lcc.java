package defpackage;

import android.content.Context;
import android.os.SystemClock;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class lcc {
    private long j = 0;
    private long k = 0;
    private long m = 0;
    private long l = 0;
    private long o = 0;
    private long n = 0;
    private long t = 0;
    private long p = 0;

    /* renamed from: a, reason: collision with root package name */
    private boolean f14759a = false;
    private boolean e = false;
    private boolean i = false;
    private boolean d = false;
    private boolean f = false;
    private int g = 0;
    private int h = 0;
    private int s = 0;
    private int q = 0;
    private int c = 0;
    private int b = 0;
    private int v = 96;
    private int r = 114;

    private boolean e(int i, int i2, int i3) {
        return i == 0 && i2 == 0 && i3 == 0;
    }

    private boolean e(int i, int i2, int i3, int i4) {
        return i == 0 && i2 == 0 && i3 == 0 && i4 == 0;
    }

    public void a(int i, int i2) {
        this.j = 0L;
        this.k = 0L;
        this.m = 0L;
        this.l = 0L;
        this.o = 0L;
        this.n = 0L;
        this.t = 0L;
        this.g = 0;
        this.h = 0;
        this.s = 0;
        this.q = 0;
        this.c = 0;
        this.b = 0;
        this.f14759a = false;
        this.e = false;
        this.i = false;
        this.d = false;
        this.f = false;
        this.v = i;
        this.r = i2;
        LogUtil.a("IDEQ_ScienceGuideVoiceHelper", "init success");
    }

    public void b(float f, int i, ffs ffsVar, Context context) {
        if (b(context)) {
            float f2 = f / 100.0f;
            int b = b(i, f2);
            if (b != -1) {
                LogUtil.a("IDEQ_ScienceGuideVoiceHelper", "speakSteprateTips ", Integer.valueOf(b));
                gso.e().a(17, b);
            }
            c(ffsVar, i, f2);
        }
    }

    public void e(float f, int i, ffs ffsVar, ArrayList<HeartRateData> arrayList, Context context) {
        if (arrayList == null) {
            LogUtil.a("IDEQ_ScienceGuideVoiceHelper", "speakGuideVoice input null");
            return;
        }
        if (b(context)) {
            float f2 = f / 100.0f;
            int a2 = a(arrayList);
            if (a2 != -1) {
                LogUtil.a("IDEQ_ScienceGuideVoiceHelper", "speakWarmupTips ", Integer.valueOf(a2));
                gso.e().e(21, a2, this.v, this.r);
            }
            int b = b(i, f2);
            if (b != -1) {
                LogUtil.a("IDEQ_ScienceGuideVoiceHelper", "speakSteprateTips ", Integer.valueOf(b));
                gso.e().a(17, b);
            }
            c(ffsVar, i, f2);
        }
    }

    private boolean b(Context context) {
        if (!lbv.a(context)) {
            LogUtil.a("IDEQ_ScienceGuideVoiceHelper", "speakGuideVoice is not real chinese mainland");
            return false;
        }
        if (gww.a()) {
            return true;
        }
        LogUtil.a("IDEQ_ScienceGuideVoiceHelper", "speakGuideVoice voice switch off");
        return false;
    }

    private void c(ffs ffsVar, int i, float f) {
        if (ffsVar != null) {
            int e = e(ffsVar, f, i);
            if (e != -1) {
                LogUtil.a("IDEQ_ScienceGuideVoiceHelper", "speakImpactTips ", Integer.valueOf(e));
                gso.e().a(11, e);
            }
            int b = b(ffsVar, f);
            if (b != -1) {
                if (b == 10) {
                    LogUtil.a("IDEQ_ScienceGuideVoiceHelper", "speakCommonAbnormal");
                    gso.e().a(b, 0);
                    return;
                } else {
                    LogUtil.a("IDEQ_ScienceGuideVoiceHelper", "speakCommonNormal");
                    gso.e().a(24, b);
                    return;
                }
            }
            return;
        }
        LogUtil.a("IDEQ_ScienceGuideVoiceHelper", "runningPosture is null");
    }

    private int e(ffs ffsVar, float f, int i) {
        if (ffsVar == null) {
            return -1;
        }
        int c = c(ffsVar, f);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (c == 0) {
            int i2 = this.g + 1;
            this.g = i2;
            this.h = 0;
            if (this.f14759a) {
                if (i2 == 15 && elapsedRealtime - this.l >= 60000) {
                    this.l = elapsedRealtime;
                    return 16;
                }
            } else if (i2 == 30) {
                return 12;
            }
        } else if (c == 2) {
            this.g = 0;
            this.b = 0;
            int i3 = this.h + 1;
            this.h = i3;
            if (i3 >= 10) {
                this.f14759a = true;
                if (elapsedRealtime - this.m >= 60000) {
                    this.m = elapsedRealtime;
                    return i > 170 ? 14 : 13;
                }
            }
        } else {
            LogUtil.c("IDEQ_ScienceGuideVoiceHelper", "detectVoiceForImpact, result:", Integer.valueOf(c));
        }
        return -1;
    }

    private int c(ffs ffsVar, float f) {
        return lbv.b("ground impact", ffsVar.e(), f);
    }

    private int b(ffs ffsVar, float f) {
        if (ffsVar == null) {
            return -1;
        }
        int a2 = a(ffsVar, f);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        boolean z = false;
        if (a2 == 0) {
            this.c = 0;
            int i = this.b + 1;
            this.b = i;
            if (i >= 30 && elapsedRealtime - this.k >= 60000) {
                this.k = elapsedRealtime;
                boolean z2 = !this.f;
                this.f = z2;
                return z2 ? 25 : 26;
            }
        } else if (a2 == 2) {
            int i2 = this.c + 1;
            this.c = i2;
            this.b = 0;
            if (elapsedRealtime - this.n >= PreConnectManager.CONNECT_INTERNAL && elapsedRealtime - this.o >= PreConnectManager.CONNECT_INTERNAL && elapsedRealtime - this.m >= PreConnectManager.CONNECT_INTERNAL) {
                z = true;
            }
            if (i2 >= 10 && z && elapsedRealtime - this.j >= 60000) {
                this.j = elapsedRealtime;
                return 10;
            }
        } else if (a2 == 3) {
            this.c = 0;
        } else {
            LogUtil.c("IDEQ_ScienceGuideVoiceHelper", "detectVoiceForOtherPosture, result:", Integer.valueOf(a2));
        }
        return -1;
    }

    private int a(ffs ffsVar, float f) {
        int b = lbv.b("eversion angle", ffsVar.a(), f);
        int b2 = lbv.b("swing angle", ffsVar.i(), f);
        int b3 = lbv.b("touch time", ffsVar.b(), f);
        int b4 = lbv.b("ground impact", ffsVar.e(), f);
        boolean z = true;
        boolean z2 = b == 2 || b == 1;
        boolean z3 = b2 == 2 || b2 == 1;
        if (b3 != 2 && b3 != 1) {
            z = false;
        }
        if (e(b, b2, b3, b4)) {
            return 0;
        }
        if (e(b, b2, b3)) {
            return 3;
        }
        return (z2 || z3 || z) ? 2 : -1;
    }

    private int a(ArrayList<HeartRateData> arrayList) {
        if (arrayList == null) {
            return -1;
        }
        if (!this.e && c(arrayList)) {
            this.e = true;
            this.p = SystemClock.elapsedRealtime();
            return 22;
        }
        if (!this.i && this.e && SystemClock.elapsedRealtime() - this.p < 300000 && e(arrayList)) {
            this.i = true;
            return 23;
        }
        if (this.i || !this.e || SystemClock.elapsedRealtime() - this.p < 300000) {
            return -1;
        }
        this.i = true;
        return -1;
    }

    private boolean c(ArrayList<HeartRateData> arrayList) {
        if (arrayList.size() >= 3 && e(arrayList.get(0), arrayList.get(1), arrayList.get(2))) {
            return true;
        }
        LogUtil.a("IDEQ_ScienceGuideVoiceHelper", "needToSpeakWarmUp, false");
        return false;
    }

    private boolean e(HeartRateData heartRateData, HeartRateData heartRateData2, HeartRateData heartRateData3) {
        if (heartRateData != null && heartRateData2 != null && heartRateData3 != null) {
            boolean z = heartRateData.acquireHeartRate() > 0 && heartRateData.acquireHeartRate() < 220;
            boolean z2 = heartRateData2.acquireHeartRate() > 0 && heartRateData2.acquireHeartRate() < 220;
            boolean z3 = heartRateData3.acquireHeartRate() > 0 && heartRateData3.acquireHeartRate() < 220;
            if (z && z2 && z3 && ((heartRateData.acquireHeartRate() + heartRateData2.acquireHeartRate()) + heartRateData3.acquireHeartRate()) / 3.0f < this.v) {
                LogUtil.a("IDEQ_ScienceGuideVoiceHelper", "needToSpeakWarmUp, true");
                return true;
            }
        }
        return false;
    }

    private boolean e(ArrayList<HeartRateData> arrayList) {
        int size = arrayList.size();
        if (size >= 3) {
            HeartRateData heartRateData = arrayList.get(size - 1);
            HeartRateData heartRateData2 = arrayList.get(size - 2);
            HeartRateData heartRateData3 = arrayList.get(size - 3);
            if (heartRateData != null && heartRateData2 != null && heartRateData3 != null) {
                boolean z = heartRateData.acquireHeartRate() > this.r && heartRateData.acquireHeartRate() < 220;
                boolean z2 = heartRateData2.acquireHeartRate() > this.r && heartRateData2.acquireHeartRate() < 220;
                boolean z3 = heartRateData3.acquireHeartRate() > this.r && heartRateData3.acquireHeartRate() < 220;
                if (z && z2 && z3) {
                    LogUtil.a("IDEQ_ScienceGuideVoiceHelper", "exceedWarmUpRange, true");
                    return true;
                }
            }
        }
        LogUtil.a("IDEQ_ScienceGuideVoiceHelper", "exceedWarmUpRange, false");
        return false;
    }

    private int b(int i, float f) {
        int d = d(i, f);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (d == 0) {
            int i2 = this.s + 1;
            this.s = i2;
            this.q = 0;
            if (!this.d) {
                return i2 == 30 ? 18 : -1;
            }
            if (i2 != 10 || elapsedRealtime - this.t < 60000) {
                return -1;
            }
            this.t = elapsedRealtime;
            return 18;
        }
        if (d != 1) {
            if (d == 2) {
                this.s = 0;
                this.q = 0;
                this.d = true;
                if (elapsedRealtime - this.o < 60000) {
                    return -1;
                }
                this.o = elapsedRealtime;
                return 20;
            }
            LogUtil.c("IDEQ_ScienceGuideVoiceHelper", "detectVoiceForStepRate, result:", Integer.valueOf(d));
            return -1;
        }
        this.s = 0;
        int i3 = this.q + 1;
        this.q = i3;
        if (i3 < 10) {
            return -1;
        }
        this.d = true;
        if (elapsedRealtime - this.n < 60000) {
            return -1;
        }
        this.n = elapsedRealtime;
        return 19;
    }

    private int d(int i, float f) {
        return lbv.b("step frequency", i, f);
    }
}
