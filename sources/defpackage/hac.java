package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.healthcloud.plugintrack.manager.voice.constructor.ChineseVoiceConstructor;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class hac {
    private static final Object g = new Object();
    private static hac i;
    private long aa;
    private long ab;
    private float ad;
    public int d;
    private int l;
    private int o;
    private int y;
    private float z;
    public CopyOnWriteArrayList<Integer> b = new CopyOnWriteArrayList<>();

    /* renamed from: a, reason: collision with root package name */
    public ArrayList<Integer> f13038a = new ArrayList<>(10);
    public int j = 0;
    public float h = 0.0f;
    public CopyOnWriteArrayList<Integer> e = new CopyOnWriteArrayList<>();
    public CopyOnWriteArrayList<Float> c = new CopyOnWriteArrayList<>();
    private Thread n = new c();
    private Boolean[] ai = new Boolean[20];
    private boolean k = false;
    private boolean v = true;
    private boolean w = true;
    private boolean t = false;
    private int m = 0;
    private boolean s = false;
    private int ac = 1;
    private boolean q = true;
    private boolean r = true;
    private boolean u = false;
    private boolean x = false;
    private boolean p = false;
    private Context f = BaseApplication.getContext();

    /* JADX INFO: Access modifiers changed from: private */
    public int e(boolean z, boolean z2, int i2) {
        if (z && z2) {
            return 20000;
        }
        return i2 < 3 ? (i2 + 1) * 20000 : i2 == 3 ? 60000 : 180000;
    }

    private boolean e(float f) {
        return f > 0.0f && f <= 0.6f;
    }

    private hac() {
        for (int i2 = 0; i2 < 20; i2++) {
            this.ai[i2] = false;
        }
    }

    public void d(int i2) {
        this.l = ((Integer) jdy.d(Integer.valueOf(i2))).intValue();
    }

    public void b(int i2) {
        this.o = ((Integer) jdy.d(Integer.valueOf(i2))).intValue();
    }

    public void d(float f) {
        this.ad = ((Float) jdy.d(Float.valueOf(f))).floatValue();
    }

    public void b(float f) {
        this.z = ((Float) jdy.d(Float.valueOf(f))).floatValue();
    }

    public void c(int i2) {
        this.y = ((Integer) jdy.d(Integer.valueOf(i2))).intValue();
    }

    public void a(long j) {
        this.ab = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public void c(float f) {
        this.h = ((Float) jdy.d(Float.valueOf(f))).floatValue();
    }

    public void a(float f) {
        this.c.add(Float.valueOf(f));
    }

    public void e(boolean z) {
        this.p = z;
    }

    public boolean g() {
        return this.p;
    }

    public static hac a() {
        hac hacVar;
        synchronized (g) {
            if (i == null) {
                i = new hac();
            }
            hacVar = i;
        }
        return hacVar;
    }

    public void h() {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        newSingleThreadExecutor.execute(new Runnable() { // from class: hac.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Thread.sleep(60000L);
                } catch (InterruptedException e) {
                    LogUtil.b("Track_VoiceGuideInMotionSport", e.getMessage());
                }
                if (hac.this.f13038a == null || hac.this.f13038a.size() <= 0) {
                    LogUtil.h("Track_VoiceGuideInMotionSport", "handleHeartRateOneMinuteCheck mSaveHeartOneMinuteRateArray.size() <= 0");
                    return;
                }
                Iterator<Integer> it = hac.this.f13038a.iterator();
                boolean z = true;
                boolean z2 = true;
                while (it.hasNext()) {
                    Integer next = it.next();
                    if (next.intValue() > hac.this.y * 0.55d) {
                        z = false;
                    }
                    if (next.intValue() > hac.this.y * 0.6d) {
                        z2 = false;
                    }
                }
                if (!hac.this.w) {
                    LogUtil.h("Track_VoiceGuideInMotionSport", "handleHeartRateOneMinuteCheck no need play voice.");
                    hac.this.f13038a.clear();
                    return;
                }
                if (z) {
                    hab.b(new String[]{had.d().d("L210"), had.d().d("L202")});
                } else if (z2) {
                    hab.b(hac.this.t());
                } else {
                    LogUtil.h("Track_VoiceGuideInMotionSport", "handleHeartRateOneMinuteCheck no warm up suggest.");
                }
                hac.this.f13038a.clear();
            }
        });
        newSingleThreadExecutor.shutdown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String[] t() {
        if (new SecureRandom().nextInt(2) == 0) {
            return new String[]{had.d().d("L202")};
        }
        return new String[]{had.d().d("L215"), had.d().d("L216")};
    }

    public void f() {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        newSingleThreadExecutor.execute(new Runnable() { // from class: hac.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Thread.sleep(180000L);
                    while (hac.this.v) {
                        if (!hac.this.q) {
                            hac hacVar = hac.this;
                            if (hacVar.g(hacVar.d)) {
                                hac.this.q = true;
                                hac hacVar2 = hac.this;
                                hacVar2.e(null, hacVar2.d, 0.0f, 0.0f, 29);
                                HashMap hashMap = new HashMap(10);
                                hashMap.put("click", 1);
                                hashMap.put("type", 6);
                                ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_SMART_COACH_2010101.value(), hashMap, 0);
                            }
                        }
                        Thread.sleep(1000L);
                    }
                } catch (InterruptedException e) {
                    LogUtil.b("Track_VoiceGuideInMotionSport", e.getMessage());
                }
            }
        });
        newSingleThreadExecutor.shutdown();
    }

    public void c(boolean z) {
        this.k = z;
        if (this.n != null) {
            this.b.clear();
            this.e.clear();
            LogUtil.a("Track_VoiceGuideInMotionSport", "handleHeartRateOrPaceCheck ", Boolean.valueOf(this.n.isAlive()));
            if (this.n.isAlive()) {
                return;
            }
            this.n.start();
        }
    }

    public void b() {
        LogUtil.c("Track_VoiceGuideInMotionSport", "handleHeartRateCheck mSaveHeartRateArray = ", this.b);
        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = this.b;
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.size() <= 0) {
            LogUtil.h("Track_VoiceGuideInMotionSport", "heartRateCheckThread mSaveHeartRateArray.size() <= 0");
            return;
        }
        this.t = false;
        this.s = false;
        Iterator<Integer> it = this.b.iterator();
        while (it.hasNext()) {
            if (it.next().intValue() <= this.l) {
                this.t = true;
            }
        }
        if (!this.t) {
            this.q = false;
            CopyOnWriteArrayList<Integer> copyOnWriteArrayList2 = this.b;
            e(new String[]{"L172", "L133"}, copyOnWriteArrayList2.get(copyOnWriteArrayList2.size() - 1).intValue(), this.o, this.l, 28);
            this.m++;
            HashMap hashMap = new HashMap(10);
            hashMap.put("click", 1);
            hashMap.put("type", 6);
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_SMART_COACH_2010101.value(), hashMap, 0);
        }
        Iterator<Integer> it2 = this.b.iterator();
        while (it2.hasNext()) {
            if (it2.next().intValue() >= this.o) {
                this.s = true;
            }
        }
        if (!this.s) {
            this.q = false;
            CopyOnWriteArrayList<Integer> copyOnWriteArrayList3 = this.b;
            e(new String[]{"L173", "L133"}, copyOnWriteArrayList3.get(copyOnWriteArrayList3.size() - 1).intValue(), this.o, this.l, 28);
            this.m++;
            HashMap hashMap2 = new HashMap(10);
            hashMap2.put("click", 1);
            hashMap2.put("type", 6);
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_SMART_COACH_2010101.value(), hashMap2, 0);
        }
        LogUtil.c("Track_VoiceGuideInMotionSport", "handleHeartRateCheck mHeartRateCheckCount = ", Integer.valueOf(this.m));
        this.b.clear();
    }

    public void k() {
        if (hab.a() == 264) {
            LogUtil.h("Track_VoiceGuideInMotionSport", "handlePaceInNormal no need execute");
            return;
        }
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        newSingleThreadExecutor.execute(new Runnable() { // from class: hac.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Thread.sleep(180000L);
                    while (hac.this.v) {
                        float y = gtx.c(hac.this.f).y();
                        if (y > 1.0E-4f) {
                            hac.this.j = (int) (TimeUnit.HOURS.toSeconds(1L) / y);
                        }
                        if (!hac.this.q) {
                            hac hacVar = hac.this;
                            if (hacVar.h(hacVar.j)) {
                                hac.this.q = true;
                                hac hacVar2 = hac.this;
                                hacVar2.e(null, hacVar2.j, 0.0f, 0.0f, 29);
                                HashMap hashMap = new HashMap(10);
                                hashMap.put("click", 1);
                                hashMap.put("type", 7);
                                ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_SMART_COACH_2010101.value(), hashMap, 0);
                            }
                        }
                        Thread.sleep(1000L);
                    }
                } catch (InterruptedException e) {
                    LogUtil.b("Track_VoiceGuideInMotionSport", e.getMessage());
                }
            }
        });
        newSingleThreadExecutor.shutdown();
    }

    public void c() {
        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = this.e;
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.size() <= 0) {
            LogUtil.h("Track_VoiceGuideInMotionSport", "paceCheckThread mSavePaceArray.size() <= 0");
            return;
        }
        this.t = false;
        this.s = false;
        Iterator<Integer> it = this.e.iterator();
        while (it.hasNext()) {
            if (it.next().intValue() <= this.z) {
                this.t = true;
            }
        }
        LogUtil.c("Track_VoiceGuideInMotionSport", "paceCheckThread mIsNeedHeartRateOrPaceHighCheckInit = ", Boolean.valueOf(this.t));
        d(this.t, "L173");
        Iterator<Integer> it2 = this.e.iterator();
        while (it2.hasNext()) {
            if (it2.next().intValue() >= this.ad) {
                this.s = true;
            }
        }
        LogUtil.c("Track_VoiceGuideInMotionSport", "paceCheckThread mIsNeedHeartRateOrPaceLowCheckInit = ", Boolean.valueOf(this.s));
        d(this.s, "L172");
        LogUtil.c("Track_VoiceGuideInMotionSport", "paceCheckThread mHeartRateCheckCount = ", Integer.valueOf(this.m));
        this.e.clear();
    }

    private void d(boolean z, String str) {
        if (z) {
            LogUtil.a("Track_VoiceGuideInMotionSport", "higherOrLowerCheck targetRange = ", str);
            return;
        }
        this.q = false;
        String[] strArr = {str, "E209", "L134"};
        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = this.e;
        e(strArr, copyOnWriteArrayList.get(copyOnWriteArrayList.size() - 1).intValue() * 60, this.ad, this.z, 28);
        this.m++;
        HashMap hashMap = new HashMap(10);
        hashMap.put("click", 1);
        hashMap.put("type", 7);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_SMART_COACH_2010101.value(), hashMap, 0);
    }

    class c extends Thread {
        public c() {
            super("Track_VoiceGuideInMotionSport");
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                if (hac.this.k && hab.c()) {
                    sleep(300000L);
                } else {
                    sleep(180000L);
                }
                while (hac.this.v) {
                    hac hacVar = hac.this;
                    sleep(hacVar.e(hacVar.t, hac.this.s, hac.this.m));
                    if (gtx.c(hac.this.f).m() == 1) {
                        if (hab.c()) {
                            hac.this.b();
                        } else {
                            hac.this.c();
                        }
                    }
                    if (hae.e().h() == 1) {
                        if (hab.c()) {
                            hac.this.b();
                        } else {
                            hac.this.e();
                        }
                    }
                }
            } catch (InterruptedException e) {
                LogUtil.b("Track_VoiceGuideInMotionSport", "MyThread interruptedException = ", e.getMessage());
            }
        }
    }

    public void b(int i2, int i3) {
        String j;
        if (i2 == 10) {
            LogUtil.h("Track_VoiceGuideInMotionSport", "handleTrainEffectCheck trainEffect is 10");
            return;
        }
        double d = i2;
        int i4 = this.ac;
        if (d * 0.1d >= i4) {
            if (i3 == 0) {
                j = a(i4);
            } else {
                j = i3 == 1 ? j(i4) : "";
            }
            if (j.isEmpty()) {
                return;
            }
            hab.b(had.d().d(j));
            this.ac++;
            HashMap hashMap = new HashMap(10);
            hashMap.put("click", 1);
            hashMap.put("type", 9);
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_SMART_COACH_2010101.value(), hashMap, 0);
        }
    }

    public void e() {
        CopyOnWriteArrayList<Float> copyOnWriteArrayList = this.c;
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.size() <= 0) {
            LogUtil.h("Track_VoiceGuideInMotionSport", "speedCheckThread mSaveSpeedArray.size() <= 0");
            return;
        }
        this.t = false;
        this.s = false;
        Iterator<Float> it = this.c.iterator();
        while (it.hasNext()) {
            if (it.next().floatValue() <= this.z) {
                this.t = true;
            }
        }
        LogUtil.c("Track_VoiceGuideInMotionSport", "speedCheckThread mIsNeedHeartRateOrPaceHighCheckInit = ", Boolean.valueOf(this.t));
        if (!this.t) {
            this.q = false;
            e(new String[]{"L147"}, 0, this.ad, this.z, 31);
            this.m++;
            HashMap hashMap = new HashMap(10);
            hashMap.put("click", 1);
            hashMap.put("type", 8);
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_SMART_COACH_2010101.value(), hashMap, 0);
        }
        Iterator<Float> it2 = this.c.iterator();
        while (it2.hasNext()) {
            if (it2.next().floatValue() >= this.ad) {
                this.s = true;
            }
        }
        LogUtil.c("Track_VoiceGuideInMotionSport", "paceCheckThread mIsNeedHeartRateOrPaceLowCheckInit = ", Boolean.valueOf(this.s));
        if (!this.s) {
            this.q = false;
            e(new String[]{"L149"}, 0, this.ad, this.z, 31);
            this.m++;
            HashMap hashMap2 = new HashMap(10);
            hashMap2.put("click", 1);
            hashMap2.put("type", 8);
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_SMART_COACH_2010101.value(), hashMap2, 0);
        }
        LogUtil.c("Track_VoiceGuideInMotionSport", "speedCheckThread mHeartRateCheckCount = ", Integer.valueOf(this.m));
        this.c.clear();
    }

    public void i() {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        newSingleThreadExecutor.execute(new Runnable() { // from class: hac.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Thread.sleep(600000L);
                } catch (InterruptedException e) {
                    LogUtil.b("Track_VoiceGuideInMotionSport", e.getMessage());
                }
                hac.this.aa = 0L;
                while (hac.this.r) {
                    try {
                        if (hac.this.d < hac.this.l + 1) {
                            hac.this.aa = 0L;
                        } else if (hac.this.aa == 0) {
                            hac.this.aa = System.currentTimeMillis();
                            LogUtil.c("Track_VoiceGuideInMotionSport", "handleEncourageReminder mStartTimestamp = ", Long.valueOf(hac.this.aa));
                        }
                    } catch (InterruptedException e2) {
                        LogUtil.b("Track_VoiceGuideInMotionSport", e2.getMessage());
                    }
                    if (hac.this.l()) {
                        return;
                    } else {
                        Thread.sleep(500L);
                    }
                }
            }
        });
        newSingleThreadExecutor.shutdown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean l() {
        if (this.aa != 0 && System.currentTimeMillis() - this.aa >= 60000) {
            float y = gtx.c(this.f).y();
            int seconds = y > 1.0E-4f ? (int) (TimeUnit.HOURS.toSeconds(1L) / y) : 0;
            LogUtil.a("Track_VoiceGuideInMotionSport", "dealEncourageReminder currentPace ", Integer.valueOf(seconds));
            if (seconds > 0 && seconds / 60 <= 9) {
                hab.b(s());
                HashMap hashMap = new HashMap(10);
                hashMap.put("click", 1);
                hashMap.put("type", 3);
                ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_SMART_COACH_2010101.value(), hashMap, 0);
                return true;
            }
        }
        return false;
    }

    private String[] s() {
        String[] strArr;
        if (new SecureRandom().nextInt(2) == 0) {
            strArr = new String[]{"L226", "L222", "L228"};
        } else {
            strArr = new String[]{"L226", "L224", "L223"};
        }
        for (int i2 = 0; i2 < strArr.length; i2++) {
            strArr[i2] = had.d().d(strArr[i2]);
        }
        return strArr;
    }

    private boolean r() {
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        if (gtx.c(this.f).i() != null) {
            copyOnWriteArrayList = (CopyOnWriteArrayList) gtx.c(this.f).i().clone();
        }
        if (!koq.b(copyOnWriteArrayList) && copyOnWriteArrayList.size() >= 12) {
            int size = copyOnWriteArrayList.size();
            Iterator it = copyOnWriteArrayList.iterator();
            float f = Float.MIN_VALUE;
            float f2 = Float.MAX_VALUE;
            float f3 = 0.0f;
            while (it.hasNext()) {
                koe koeVar = (koe) it.next();
                if (koeVar != null) {
                    float b = koeVar.b();
                    f3 += b;
                    if (b > f) {
                        f = b;
                    }
                    if (b < f2) {
                        f2 = b;
                    }
                }
            }
            LogUtil.c("Track_VoiceGuideInMotionSport", "isOneMinutePaceStable minSpeed = ", Float.valueOf(f2), " maxSpeed = ", Float.valueOf(f));
            float f4 = size;
            float f5 = f3 / f4;
            float f6 = 0.0f;
            for (int i2 = 0; i2 < size; i2++) {
                f6 += (float) Math.pow(((koe) copyOnWriteArrayList.get(i2)).b() - f5, 2.0d);
            }
            if (f > 0.0f && f2 > 0.0f && f - f2 < 2.0f) {
                return true;
            }
            if (f6 > 0.0f && f6 / f4 < 2.0f) {
                return true;
            }
        }
        return false;
    }

    public void e(final int i2) {
        if (gtx.c(this.f).y() < 8.0f) {
            return;
        }
        LogUtil.c("Track_VoiceGuideInMotionSport", "runningPostureCheckVoiceGuidance stepRate = ", Integer.valueOf(i2));
        if (i2 <= 0) {
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: hac.2
            @Override // java.lang.Runnable
            public void run() {
                hac.this.i(i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(int i2) {
        boolean r = r();
        if (i2 >= 160) {
            b(r);
        } else if (r) {
            ArrayList arrayList = new ArrayList(16);
            arrayList.add("L252");
            ChineseVoiceConstructor.b(arrayList, Integer.valueOf(i2));
            arrayList.add("L253");
            hab.b(ChineseVoiceConstructor.b((List<String>) arrayList));
            this.ai[9] = true;
        }
        e(i2, r);
    }

    private void b(boolean z) {
        CopyOnWriteArrayList<ffs> d = gtx.c(this.f).d();
        Bundle aXG_ = aXG_(d != null ? (CopyOnWriteArrayList) d.clone() : null);
        int i2 = aXG_.getInt("avgGroundContactTime", 0);
        float f = aXG_.getFloat("groundHangTimeRate", 0.0f);
        if (z && i2 > 270 && f > 0.6f) {
            hab.b(had.d().d("L257"));
            this.ai[10] = true;
        }
        if (this.ai[10].booleanValue() && z && i2 > 0 && i2 <= 270 && e(f)) {
            hab.b(had.d().d("L258"));
            this.ai[10] = false;
        }
        a(z, aXH_(aXG_, i2));
    }

    private boolean aXH_(Bundle bundle, int i2) {
        int i3 = bundle.getInt("avgGroundImpactAcceleration", 0);
        int i4 = bundle.getInt("avgSwingAngle", 0);
        int i5 = bundle.getInt("avgEversionExcursion", 0);
        return (i2 <= 270 && i3 > 6 && i3 < 20) && (i4 > 70 && i4 < 140 && i5 > 5 && i5 < 25);
    }

    private void e(int i2, boolean z) {
        if (!this.ai[9].booleanValue() || i2 <= 170 || i2 >= 190 || !z) {
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        arrayList.add("L254");
        arrayList.add("L255");
        ChineseVoiceConstructor.b(arrayList, Integer.valueOf(i2));
        arrayList.add("L256");
        hab.b(ChineseVoiceConstructor.b((List<String>) arrayList));
        this.ai[9] = false;
    }

    private Bundle aXG_(CopyOnWriteArrayList<ffs> copyOnWriteArrayList) {
        int i2;
        int i3;
        int i4;
        int i5;
        if (koq.b(copyOnWriteArrayList)) {
            return new Bundle();
        }
        Iterator<ffs> it = copyOnWriteArrayList.iterator();
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        while (it.hasNext()) {
            ffs next = it.next();
            if (next != null) {
                i6 += next.d();
                i7 += next.h();
                i8 += next.c();
                int d = next.d() + next.h() + next.c();
                if (next.o() > 0) {
                    i14 += next.o();
                    i13++;
                }
                i9 += next.b() * d;
                i10 += next.e() * d;
                i11 += next.i() * d;
                i12 += next.a() * d;
            }
        }
        float f = i6 + i7 + i8;
        if (f > 0.0f) {
            int round = Math.round(i9 / f);
            int round2 = Math.round(i10 / f);
            int round3 = Math.round(i11 / f);
            i5 = Math.round(i12 / f);
            i2 = round;
            i3 = round2;
            i4 = round3;
        } else {
            i2 = 0;
            i3 = 0;
            i4 = 0;
            i5 = 0;
        }
        return aXD_(i13 > 0 ? (i14 * 0.01f) / i13 : 0.0f, i2, i3, i4, i5);
    }

    private Bundle aXD_(float f, int i2, int i3, int i4, int i5) {
        Bundle bundle = new Bundle();
        bundle.putFloat("groundHangTimeRate", f);
        bundle.putInt("avgGroundContactTime", i2);
        bundle.putInt("avgGroundImpactAcceleration", i3);
        bundle.putInt("avgSwingAngle", i4);
        bundle.putInt("avgEversionExcursion", i5);
        return bundle;
    }

    private void a(boolean z, boolean z2) {
        if (!(this.ai[11].booleanValue() && this.ai[12].booleanValue() && this.ai[13].booleanValue() && this.ai[14].booleanValue() && this.ai[15].booleanValue()) && z && z2) {
            float distance = gtx.c(this.f).getDistance() / 1000.0f;
            if (!this.ai[11].booleanValue()) {
                this.ai[11] = Boolean.valueOf(e(1.1f, distance));
            }
            if (!this.ai[12].booleanValue()) {
                this.ai[12] = Boolean.valueOf(e(3.1f, distance));
            }
            if (!this.ai[13].booleanValue()) {
                this.ai[13] = Boolean.valueOf(e(5.1f, distance));
            }
            if (!this.ai[14].booleanValue()) {
                this.ai[14] = Boolean.valueOf(e(8.1f, distance));
            }
            if (this.ai[15].booleanValue()) {
                return;
            }
            this.ai[15] = Boolean.valueOf(e(10.1f, distance));
        }
    }

    private boolean e(float f, float f2) {
        String d;
        if (f2 < f) {
            return false;
        }
        int nextInt = new SecureRandom().nextInt(4);
        if (nextInt == 0) {
            d = had.d().d("L259");
        } else if (nextInt == 1) {
            d = had.d().d("L260");
        } else if (nextInt == 2) {
            d = had.d().d("L261");
        } else {
            d = had.d().d("L262");
        }
        if (TextUtils.isEmpty(d)) {
            return false;
        }
        hab.b(d);
        return true;
    }

    public void b(long j) {
        d(Math.round(j / 1000.0d));
        c(j);
    }

    private void d(long j) {
        if (this.p) {
            if (j != 600) {
                if (j == 1200) {
                    hab.b(had.d().d("L238"));
                    return;
                } else {
                    if (j == 1800) {
                        hab.b(had.d().d("L239"));
                        return;
                    }
                    return;
                }
            }
            int round = Math.round(gtx.c(this.f).x());
            if (round <= 0) {
                LogUtil.h("Track_VoiceGuideInMotionSport", "dealFatReduction calorieValue <= 0");
                return;
            }
            ArrayList arrayList = new ArrayList(16);
            arrayList.add("L234");
            arrayList.add("L235");
            ChineseVoiceConstructor.b(arrayList, Integer.valueOf(round));
            arrayList.add("L229");
            arrayList.add("L237");
            hab.b(ChineseVoiceConstructor.b((List<String>) arrayList));
        }
    }

    public void d() {
        o();
        n();
        m();
    }

    private void o() {
        if (this.ai[0].booleanValue() && this.ai[1].booleanValue()) {
            return;
        }
        long sportDuration = gtx.c(this.f).getSportDuration();
        if (!this.ai[0].booleanValue() && haa.c().f() > 0 && sportDuration > haa.c().f()) {
            hab.b(had.d().d("L241"));
            LogUtil.a("Track_VoiceGuideInMotionSport", " mVoiceHasPlayArray[1] = true");
            this.ai[0] = true;
        } else {
            if (this.ai[1].booleanValue() || haa.c().i() <= 3 || haa.c().a() <= 0 || sportDuration <= haa.c().a()) {
                return;
            }
            hab.b(had.d().d("L240"));
            LogUtil.a("Track_VoiceGuideInMotionSport", " mVoiceHasPlayArray[0] = true");
            this.ai[1] = true;
        }
    }

    private void n() {
        if (this.ai[2].booleanValue() && this.ai[3].booleanValue()) {
            return;
        }
        float distance = gtx.c(this.f).getDistance();
        LogUtil.c("Track_VoiceGuideInMotionSport", "dealRunDistanceAchievementImprove runDistance = ", Float.valueOf(distance));
        if (!this.ai[2].booleanValue() && haa.c().g() > 0 && distance > haa.c().g()) {
            hab.b(had.d().d("L246"));
            LogUtil.a("Track_VoiceGuideInMotionSport", " mVoiceHasPlayArray[3] = true");
            this.ai[2] = true;
        } else {
            if (this.ai[3].booleanValue() || haa.c().i() <= 3 || haa.c().d() <= 0 || distance <= haa.c().d()) {
                return;
            }
            ArrayList arrayList = new ArrayList(16);
            arrayList.add("L242");
            arrayList.add("L243");
            ChineseVoiceConstructor.b(arrayList, Float.valueOf(gtx.c(this.f).h()));
            arrayList.add("L244");
            arrayList.add("L245");
            hab.b(ChineseVoiceConstructor.b((List<String>) arrayList));
            LogUtil.a("Track_VoiceGuideInMotionSport", " mVoiceHasPlayArray[2] = true");
            this.ai[3] = true;
        }
    }

    private void m() {
        if (this.ai[4].booleanValue() && this.ai[5].booleanValue() && this.ai[6].booleanValue() && this.ai[7].booleanValue() && this.ai[8].booleanValue()) {
            return;
        }
        float distance = gtx.c(this.f).getDistance() / 1000.0f;
        if (!this.ai[4].booleanValue()) {
            long a2 = gtx.c(this.f).ai().a((int) distance, 0);
            LogUtil.c("Track_VoiceGuideInMotionSport", "dealRunPaceAchievementImprove recentOneKiloMeterPace = ", Long.valueOf(a2));
            long j = a2 / 1000;
            if (j > 150 && j < haa.c().h()) {
                hab.b(had.d().d("L247"));
                LogUtil.a("Track_VoiceGuideInMotionSport", " mVoiceHasPlayArray[4] = true");
                this.ai[4] = true;
            }
        }
        if (!this.ai[5].booleanValue()) {
            this.ai[5] = Boolean.valueOf(c(3, distance));
            return;
        }
        if (!this.ai[6].booleanValue()) {
            this.ai[6] = Boolean.valueOf(c(5, distance));
        } else if (!this.ai[7].booleanValue()) {
            this.ai[7] = Boolean.valueOf(c(8, distance));
        } else {
            if (this.ai[8].booleanValue()) {
                return;
            }
            this.ai[8] = Boolean.valueOf(c(10, distance));
        }
    }

    private boolean c(int i2, float f) {
        float f2 = i2;
        if (f < f2 || f >= f2 + 0.5f) {
            return false;
        }
        float v = gtx.c(this.f).v();
        LogUtil.c("Track_VoiceGuideInMotionSport", "dealFixedDistancePaceAchievementImprove averagePace = ", Float.valueOf(v));
        if (haa.c().j() <= 0.0f || v >= haa.c().j() || !q()) {
            return false;
        }
        ArrayList arrayList = new ArrayList(16);
        arrayList.add("L248");
        ChineseVoiceConstructor.b(arrayList, Integer.valueOf(i2));
        arrayList.add("L244");
        arrayList.add("L249");
        arrayList.add("L250");
        hab.b(ChineseVoiceConstructor.b((List<String>) arrayList));
        LogUtil.a("Track_VoiceGuideInMotionSport", "dealFixedDistancePaceAchievementImprove voice play");
        return true;
    }

    private boolean q() {
        Iterator<Map.Entry<Integer, Float>> it = gtx.c(this.f).ai().f().entrySet().iterator();
        while (it.hasNext()) {
            if (it.next().getValue().floatValue() < 150.0f) {
                return false;
            }
        }
        return true;
    }

    private void c(long j) {
        if (!this.x && c(j, this.ab)) {
            a(false, this.ab);
            this.x = true;
        }
        if (this.u || !c(j, this.ab / 2)) {
            return;
        }
        a(true, this.ab / 2);
        this.u = true;
    }

    private void a(final boolean z, final long j) {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        newSingleThreadExecutor.execute(new Runnable() { // from class: hac.9
            @Override // java.lang.Runnable
            public void run() {
                String[] strArr;
                if (z) {
                    strArr = new String[]{"L162"};
                } else {
                    strArr = new String[]{"L164", "L217"};
                }
                gxi gxiVar = hae.e().h() == 1 ? new gxi(30, hac.this.aXF_(strArr, j)) : null;
                if (gtx.c(hac.this.f).m() == 1) {
                    gxiVar = new gxi(30, hac.this.aXE_(strArr, j));
                }
                hab.b(gxiVar);
                HashMap hashMap = new HashMap(10);
                hashMap.put("click", 1);
                hashMap.put("type", 10);
                ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_SMART_COACH_2010101.value(), hashMap, 0);
            }
        });
        newSingleThreadExecutor.shutdown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bundle aXF_(String[] strArr, long j) {
        Bundle bundle = new Bundle();
        bundle.putString("voiceType", "sportState");
        bundle.putStringArray("beforeSportFirstVoicePath", strArr);
        bundle.putFloat("distance", hae.e().d());
        bundle.putLong("duration", j);
        bundle.putInt("calorie", hae.e().b());
        bundle.putInt(IndoorEquipManagerApi.KEY_HEART_RATE, hae.e().a());
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bundle aXE_(String[] strArr, long j) {
        Bundle bundle = new Bundle();
        bundle.putString("voiceType", "sportState");
        bundle.putStringArray("beforeSportFirstVoicePath", strArr);
        bundle.putFloat("distance", gtx.c(this.f).h());
        bundle.putLong("duration", j);
        bundle.putInt("calorie", (int) gtx.c(this.f).x());
        bundle.putInt(IndoorEquipManagerApi.KEY_HEART_RATE, gtx.c(this.f).b());
        return bundle;
    }

    private boolean c(long j, long j2) {
        return Math.round(((double) j) / 1000.0d) == Math.round(((double) j2) / 1000.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g(int i2) {
        int i3 = this.o;
        double d = i3;
        double d2 = this.l - i3;
        return i2 >= ((int) ((0.25d * d2) + d)) && i2 <= ((int) (d + (d2 * 0.75d)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean h(int i2) {
        float f = i2 / 60.0f;
        return f <= this.z && f >= this.ad;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String[] strArr, int i2, float f, float f2, int i3) {
        Bundle bundle = new Bundle();
        try {
            bundle.putString("voiceType", "sportSmartCoach");
            bundle.putStringArray("beforeSportFirstVoicePath", strArr);
            bundle.putFloat("sectionLower", f);
            bundle.putFloat("sectionUpper", f2);
            bundle.putInt("sportDuration", i2);
            hab.b(new gxi(i3, bundle));
        } catch (NumberFormatException e) {
            LogUtil.b("Track_VoiceGuideInMotionSport", "voiceConstructorPlay exception = ", e.getMessage());
        }
    }

    private static void p() {
        synchronized (g) {
            i = null;
        }
    }

    public void j() {
        this.b.clear();
        this.f13038a.clear();
        this.w = false;
        this.r = false;
        if (this.v) {
            this.v = false;
            this.n.interrupt();
            this.n = null;
        }
        p();
    }

    private String j(int i2) {
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? "" : "L271" : "L270" : "L269" : "L268" : "L267";
    }

    public static String a(int i2) {
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? "" : "L186" : "L184" : "L182" : "L180" : "L178";
    }
}
