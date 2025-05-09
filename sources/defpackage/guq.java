package defpackage;

import android.os.Bundle;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class guq {

    /* renamed from: a, reason: collision with root package name */
    private int f12948a;
    private List<Map<String, Float>> b;
    private long c;
    private boolean d;
    private int e;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;
    private String n;
    private boolean o;
    private int p;
    private long q;
    private int r;
    private int s;
    private StorageParams t;
    private gww v;
    private int x;

    private boolean h(int i) {
        return i == 258 || i == 264;
    }

    private static boolean i(int i) {
        return i == 258 || i == 264;
    }

    private float n(int i) {
        return (i * 1.0f) / 1000.0f;
    }

    public guq(int i, int i2) {
        this.s = 258;
        this.e = 0;
        this.f12948a = 1000;
        this.q = 300000L;
        this.r = 0;
        this.k = true;
        this.x = 176;
        this.c = 0L;
        this.i = true;
        this.g = true;
        this.p = -1;
        this.b = null;
        this.j = true;
        this.l = true;
        this.m = true;
        this.o = true;
        this.h = true;
        LogUtil.a("Track_TrackVoiceManager", "new TrackVoiceManager");
        this.s = i;
        this.p = i2;
        this.d = false;
        this.f = false;
        this.t = new StorageParams();
        this.n = Integer.toString(20002);
        this.v = new gww(BaseApplication.getContext(), this.t, this.n);
        PluginSportTrackAdapter c = gso.e().c();
        if (c != null) {
            this.k = c.isWarningEnable();
            this.x = c.getWarningLimitHeartRate();
            ReleaseLogUtil.e("Track_TrackVoiceManager", "TrackVoiceManager mIsWarningEnable is ", Boolean.valueOf(this.k), ", warningLimit is  ", Integer.valueOf(this.x));
        }
    }

    public guq() {
        this.s = 258;
        this.e = 0;
        this.f12948a = 1000;
        this.q = 300000L;
        this.r = 0;
        this.k = true;
        this.x = 176;
        this.c = 0L;
        this.i = true;
        this.g = true;
        this.p = -1;
        this.b = null;
        this.j = true;
        this.l = true;
        this.m = true;
        this.o = true;
        this.h = true;
        LogUtil.a("Track_TrackVoiceManager", "new TrackVoiceManager");
    }

    public void c(long j, int i) {
        int i2 = this.p;
        if (i2 == 3 || i2 == 4) {
            this.e = 2;
            this.f12948a = 1000;
            this.r = i / 1000;
            ReleaseLogUtil.e("Track_TrackVoiceManager", "useDefaultInterval = 1km");
            return;
        }
        if (this.s == 258) {
            this.g = false;
            this.i = false;
            if (i >= 21097) {
                this.g = true;
            }
            if (i >= 42195) {
                this.i = true;
            }
        }
        int i3 = this.v.i();
        this.e = i3;
        if (i3 == 1) {
            long millis = TimeUnit.MINUTES.toMillis(this.v.f());
            this.q = millis;
            if (millis < 300000) {
                this.q = 300000L;
            }
            this.r = (int) (j / this.q);
        } else if (i3 == 2) {
            int g = this.v.g();
            this.f12948a = g;
            if (g < 500) {
                this.f12948a = 500;
            }
            this.r = i / this.f12948a;
        } else {
            this.f12948a = 1000;
            this.r = i / 1000;
        }
        o();
        ReleaseLogUtil.e("Track_TrackVoiceManager", "type = ", Integer.valueOf(this.e), " distance = ", Integer.valueOf(this.f12948a), " time = ", Long.valueOf(this.q), " mPlayCount = ", Integer.valueOf(this.r));
    }

    public int e() {
        return this.f12948a;
    }

    public void d(int i) {
        this.r = i;
    }

    public int c() {
        return this.e;
    }

    public void g() {
        int i = this.s;
        if (i != 257) {
            if (i == 265 || i == 259) {
                e(new gxi(0, null));
            } else if (i != 260) {
                if (i != 273 && i != 274) {
                    switch (i) {
                        case 281:
                            break;
                        case 282:
                            e(new gxi(111, null));
                            break;
                        case 283:
                            break;
                        default:
                            e(new gxi(1, null));
                            break;
                    }
                }
                LogUtil.a("Track_TrackVoiceManager", "start the work out");
                e(new gxi(110, null));
            } else {
                e(new gxi(112, null));
            }
            f(this.s);
            gso.e().b(0);
        }
        e(new gxi(2, null));
        f(this.s);
        gso.e().b(0);
    }

    public void b() {
        e(new gxi(3, null));
        gso.e().b(0);
    }

    public void h() {
        e(new gxi(5, null));
        gso.e().b(0);
    }

    public void l() {
        if (h(this.s)) {
            e(new gxi(16, null));
        } else {
            e(new gxi(15, null));
        }
        gso.e().b(0);
    }

    public void j() {
        e(new gxi(4, null));
        gso.e().b(0);
    }

    public void a() {
        e(new gxi(6, null));
    }

    public void d() {
        e(new gxi(19, null));
    }

    public void c(long j) {
        Bundle bundle = new Bundle();
        bundle.putString("voiceType", "marathon");
        bundle.putLong("duration", j);
        e(new gxi(13, bundle));
    }

    public void d(long j) {
        Bundle bundle = new Bundle();
        bundle.putString("voiceType", "marathon");
        bundle.putLong("duration", j);
        e(new gxi(12, bundle));
    }

    public static float a(long j) {
        LogUtil.a("Track_TrackVoiceManager", "pace", " mLastKiloTime speed=", Long.valueOf(j));
        if (j <= 0) {
            return 0.0f;
        }
        float round = Math.round(((1000.0f / (j * 1.0f)) * 3600.0f) * 10.0f) / 10.0f;
        LogUtil.a("Track_TrackVoiceManager", "pace", " getLastKilSpeed speed kmph=", Float.valueOf(round));
        return round;
    }

    public static boolean d(int i, long j) {
        float a2 = a(j);
        if (i == 259 && a2 > 30.0f) {
            return true;
        }
        if (!i(i) || a2 <= 12.0f) {
            return i == 257 && a2 > 7.0f;
        }
        return true;
    }

    public void c(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("skippingNum", i);
        bundle.putString("voiceType", "skippingNum");
        e(new gxi(33, bundle));
    }

    public void d(long j, int i, long j2, int i2) {
        boolean z = true;
        if (this.e == 1) {
            long j3 = this.q;
            long j4 = j / j3;
            if (j4 > this.r) {
                int i3 = (int) j4;
                this.r = i3;
                Bundle aUl_ = aUl_(i, j3 * i3, -1L, false, i2);
                aUl_.putString("voiceType", "sportStateWithPace");
                e(new gxi(11, aUl_));
                return;
            }
            return;
        }
        int i4 = this.f12948a;
        int i5 = i / i4;
        if (i5 <= this.r) {
            return;
        }
        this.r = i5;
        if (i4 == 500 && i5 % 2 != 0) {
            z = false;
        }
        if (i < 1000 || !z) {
            Bundle aUl_2 = aUl_(i4 * i5, j, -1L, false, i2);
            aUl_2.putString("voiceType", "sportStateWithPace");
            e(new gxi(11, aUl_2));
            return;
        }
        long j5 = j2;
        boolean d = d(this.s, j5);
        if (i < 2000 && (!this.m || this.l)) {
            j5 = -1;
        }
        Bundle aUl_3 = aUl_(i, j, j5, d, i2);
        aUl_3.putString("voiceType", "sportStateWithPace");
        e(new gxi(9, aUl_3));
    }

    public void c(float f, int i, Map<Double, Double> map) {
        if (map == null) {
            ReleaseLogUtil.d("Track_TrackVoiceManager", "intelligentVoice error with pace time map is null ");
            return;
        }
        e(f, map);
        b(i);
        b(f, map);
        e((int) f);
    }

    private void f(int i) {
        if (this.h) {
            if (i == 258) {
                e(new gxi(21, null));
            } else {
                e(new gxi(22, null));
            }
        }
    }

    private void e(gxi gxiVar) {
        if (gww.e() == 1 || gso.e().b() == 2) {
            gxd.a().d(gxiVar);
        }
    }

    private void o() {
        this.j = e("voice_distance");
        this.l = e("voice_speed_time");
        this.m = e("voice_pace");
        this.o = e("voice_heart_rate");
        this.h = e("voice_intelligent");
        LogUtil.a("Track_TrackVoiceManager", "mIsVoiceDistance = ", Boolean.valueOf(this.j), " mIsVoiceSpeedTime = ", Boolean.valueOf(this.l), " mIsVoicePace = ", Boolean.valueOf(this.m), "mIsVoiceHeartRate = ", Boolean.valueOf(this.o), " mIsVoiceEncourage = ", Boolean.valueOf(this.h));
    }

    private boolean e(String str) {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(20002), str);
        if (b != null && !"".equals(b)) {
            try {
                return Integer.parseInt(b) == 1;
            } catch (NumberFormatException unused) {
            }
        }
        return true;
    }

    private Bundle aUl_(int i, long j, long j2, boolean z, int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt(BleConstants.SPORT_TYPE, this.s);
        if (UnitUtil.h()) {
            bundle.putFloat("distance", o(i));
        } else {
            bundle.putFloat("distance", n(i));
        }
        bundle.putLong("duration", j);
        bundle.putLong("pace", j2);
        if (z) {
            bundle.putBoolean("paceTip", d(this.s, j2));
        }
        bundle.putInt(IndoorEquipManagerApi.KEY_HEART_RATE, i2);
        bundle.putBoolean("voice_distance", this.j);
        bundle.putBoolean("voice_speed_time", this.l);
        bundle.putBoolean("voice_pace", this.m);
        bundle.putBoolean("voice_heart_rate", this.o);
        bundle.putInt("voice_play_count", this.r);
        return bundle;
    }

    private float o(int i) {
        return (float) UnitUtil.e((i * 1.0d) / 1000.0d, 3);
    }

    public void d(HeartRateData heartRateData) {
        if (!this.k || heartRateData == null || hab.g() || heartRateData.acquireTime() - this.c <= 15000 || heartRateData.acquireHeartRate() <= this.x || c(heartRateData)) {
            return;
        }
        this.c = System.currentTimeMillis();
        e(new gxi(14, null));
    }

    private boolean c(HeartRateData heartRateData) {
        return System.currentTimeMillis() - heartRateData.acquireTime() > 300000;
    }

    public void e(int i) {
        if (!koq.b(this.b) && h(this.s)) {
            float j = j(i);
            if (j <= 0.0f || i < j) {
                return;
            }
            int size = this.b.size() - 1;
            Map<String, Float> map = this.b.get(size);
            if (map == null) {
                ReleaseLogUtil.d("Track_TrackVoiceManager", "playCountdownVoice map is null");
                return;
            }
            if (map.get("countDownDistance") == null) {
                ReleaseLogUtil.d("Track_TrackVoiceManager", "playCountdownVoice map.get(COUNTDOWN_DISTANCE) is null");
                return;
            }
            if (map.get("distance") == null) {
                ReleaseLogUtil.d("Track_TrackVoiceManager", "playCountdownVoice map.get(DISTANCE) is null");
                return;
            }
            if (map.get("intelligent") == null) {
                ReleaseLogUtil.d("Track_TrackVoiceManager", "playCountdownVoice map.get(INTELLIGENT) is null");
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("voiceType", "countdown");
            bundle.putFloat("lastValue", map.get("countDownDistance").floatValue());
            bundle.putFloat("distance", map.get("distance").floatValue());
            bundle.putInt("intelligentVoice", Math.round(map.get("intelligent").floatValue()));
            if (this.b.size() == 1) {
                gxi gxiVar = new gxi(18, bundle);
                this.b.remove(size);
                e(gxiVar);
            } else {
                gxi gxiVar2 = new gxi(17, bundle);
                this.b.remove(size);
                e(gxiVar2);
            }
        }
    }

    private boolean e(float f, Map<Double, Double> map) {
        if (this.g || f < 21097.5f) {
            return false;
        }
        this.g = true;
        if (f >= 22152.373f) {
            return false;
        }
        long doubleValue = map.containsKey(Double.valueOf(21.0975d)) ? (long) (map.get(Double.valueOf(21.0975d)).doubleValue() * 1000.0d) : -1L;
        ReleaseLogUtil.e("Track_TrackVoiceManager", "playMarathonFinish marathonDuration is ", Long.valueOf(doubleValue));
        c(doubleValue);
        return true;
    }

    private void b(float f, Map<Double, Double> map) {
        if (this.i || f < 42195.0f) {
            return;
        }
        this.i = true;
        if (f < 44304.746f) {
            long doubleValue = map.containsKey(Double.valueOf(42.195d)) ? (long) (map.get(Double.valueOf(42.195d)).doubleValue() * TimeUnit.SECONDS.toMillis(1L)) : -1L;
            ReleaseLogUtil.e("Track_TrackVoiceManager", "playMarathonFinish marathonDuration is ", Long.valueOf(doubleValue));
            d(doubleValue);
        }
    }

    public void d(int i, float f, float f2, long j, float f3) {
        if (i == 0) {
            float f4 = j;
            if (f4 >= f3) {
                this.d = true;
            }
            if (f4 >= f3 / 2.0f) {
                this.f = true;
            }
        } else if (i == 1) {
            if (f >= f3) {
                this.d = true;
            }
            if (f >= f3 / 2.0f) {
                this.f = true;
            }
        } else if (i == 2) {
            if (f2 >= f3) {
                this.d = true;
            }
            if (f2 >= f3 / 2.0f) {
                this.f = true;
            }
        }
        LogUtil.a("Track_TrackVoiceManager", "checkGoalStatus mIsGoalFinish is ", Boolean.valueOf(this.d), " ,mIsHalfGoalFinish is ", Boolean.valueOf(this.f));
    }

    public boolean a(int i) {
        return i >= 100 && !this.d;
    }

    public void e(boolean z) {
        this.d = z;
    }

    public void b(int i) {
        if (i < 50 || this.f) {
            return;
        }
        this.f = true;
        if (i < 55) {
            d();
        }
    }

    public void e(float f, float f2) {
        ArrayList arrayList = new ArrayList(10);
        float f3 = f / 1000.0f;
        if (f3 < 3.0f) {
            LogUtil.a("Track_TrackVoiceManager", "targetCountdownList target is less than 3km");
            return;
        }
        if (h(this.s)) {
            arrayList.add(a(0.5f, f3 - 0.5f, 0));
            for (int i = 1; i <= 3; i++) {
                float f4 = i;
                if (f4 < f3 / 2.0f) {
                    arrayList.add(a(f4, f3 - f4, i));
                }
            }
            if (f3 > 10.0f) {
                for (int i2 = 90; i2 > 50; i2 -= 10) {
                    float round = Math.round((1.0f - (i2 / 100.0f)) * f3);
                    if (c(round, arrayList)) {
                        arrayList.add(a(round, f3 - round, i2));
                    }
                }
            }
            this.b = new ArrayList(10);
            for (Map<String, Float> map : arrayList) {
                if (map != null && map.get("distance") != null && f2 / 1000.0f < map.get("distance").floatValue()) {
                    this.b.add(map);
                }
            }
            LogUtil.a("Track_TrackVoiceManager", "targetCountdownList");
        }
    }

    private Map<String, Float> a(float f, float f2, int i) {
        HashMap hashMap = new HashMap(3);
        hashMap.put("countDownDistance", Float.valueOf(f));
        hashMap.put("distance", Float.valueOf(f2));
        hashMap.put("intelligent", Float.valueOf(i));
        return hashMap;
    }

    private boolean c(float f, List<Map<String, Float>> list) {
        if (list.size() == 0) {
            return true;
        }
        return list.get(list.size() - 1).get("countDownDistance") != null && f > list.get(list.size() - 1).get("countDownDistance").floatValue();
    }

    private float j(int i) {
        g(i);
        if (!koq.c(this.b)) {
            return -1.0f;
        }
        return this.b.get(r2.size() - 1).get("distance").floatValue() * 1000.0f;
    }

    private void g(int i) {
        if (koq.b(this.b)) {
            return;
        }
        for (int size = this.b.size() - 1; size >= 0; size--) {
            if (i > (this.b.get(size).get("distance").floatValue() * 1000.0f) + 100.0f) {
                this.b.remove(size);
            }
        }
    }

    public void d(gxi gxiVar) {
        gso.e().b(2);
        gxd.a().d(gxiVar);
        gso.e().b(0);
    }

    public void i() {
        gso.e().b(2);
        gxd.a().d(new gxi(24, null));
        gso.e().b(0);
    }

    public void f() {
        gso.e().b(2);
        gxd.a().d(new gxi(25, null));
        gso.e().b(0);
    }
}
