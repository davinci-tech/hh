package defpackage;

import android.content.Context;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class guj {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f12942a = new Object();
    private static volatile guj d;
    private boolean e = false;
    private boolean b = false;
    private int f = -1;
    private float i = -1.0f;
    private boolean c = false;

    private guj() {
    }

    public static guj d() {
        if (d == null) {
            synchronized (f12942a) {
                if (d == null) {
                    d = new guj();
                }
            }
        }
        return d;
    }

    public void i() {
        this.e = false;
        this.b = false;
    }

    public void e(boolean z) {
        this.e = z;
    }

    public boolean e() {
        return this.e;
    }

    void c(Context context, int i, float f, float f2) {
        CountDownLatch countDownLatch;
        if (i != 1 || this.b || f2 <= f) {
            return;
        }
        LogUtil.a("Track_StopSportWithTargetManager", " targetType", Integer.valueOf(i), " targetValue", Float.valueOf(f), " distance", Float.valueOf(f2), " mIsOverTarget: ", false);
        this.i = f;
        this.f = i;
        this.b = true;
        gwo.d(context, "motion_path2.txt", "target_motion_path.txt");
        gwo.d(context, "simplemotion.txt", "target_motion_simplify.txt");
        gtx c = gtx.c(context);
        LogUtil.a("Track_StopSportWithTargetManager", "!CommonUtil.closeRoad(),", Boolean.valueOf(gvv.e()));
        if (!gvv.e() && c.n() == 264) {
            c.br();
        }
        c.c(true);
        c.e(true);
        gul ak = c.ak();
        if (ak != null) {
            ak.d();
            countDownLatch = new CountDownLatch(1);
            ak.e(countDownLatch);
        } else {
            countDownLatch = null;
        }
        CountDownLatch countDownLatch2 = countDownLatch;
        MotionPath c2 = gwk.c(context, "target_motion_path.txt");
        MotionPathSimplify e = gwk.e(context, "target_motion_simplify.txt");
        gvp ai = c.ai();
        if (c2 != null && e != null) {
            c2.savePaceMap(c(f, ai.h()));
            c2.saveBritishPaceMap(ai.j());
            c.e(e, c2, true);
            e.saveTotalDistance(Math.round(f));
        }
        c.a("target_motion_path.txt", true);
        MotionPath motionPath = new MotionPath();
        motionPath.savePaceMap(c(f, ai.h()));
        motionPath.saveBritishPaceMap(ai.j());
        if (c2 != null) {
            motionPath.saveLbsDataMap(c2.requestLbsDataMap());
        }
        b(motionPath, context, e, ak, countDownLatch2);
    }

    private Map<Integer, Float> c(float f, Map<Integer, Float> map) {
        if (f % 1000.0f == 0.0f && map.size() > ((int) f) / 1000) {
            Iterator<Integer> it = map.keySet().iterator();
            while (it.hasNext()) {
                Integer next = it.next();
                if ((next.intValue() / 100000) % 100 != 0) {
                    LogUtil.a("Track_StopSportWithTargetManager", "remove Key is ", next);
                    it.remove();
                }
            }
        }
        return map;
    }

    private void b(MotionPath motionPath, Context context, MotionPathSimplify motionPathSimplify, gul gulVar, CountDownLatch countDownLatch) {
        if (gulVar != null && countDownLatch != null) {
            try {
                countDownLatch.await(2000L, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                ReleaseLogUtil.c("Track_StopSportWithTargetManager", "get step rate count down error, ", LogAnonymous.b((Throwable) e));
            }
            if (motionPathSimplify != null) {
                motionPathSimplify.savePaceMap(motionPath.requestPaceMap());
                motionPathSimplify.saveBritishPaceMap(motionPath.requestBritishPaceMap());
                e(motionPathSimplify);
                int m = gulVar.m();
                if (m == 0) {
                    m = gtx.c(context).af();
                    LogUtil.a("Track_StopSportWithTargetManager", "targetTotalSteps = ", Integer.valueOf(m));
                }
                motionPathSimplify.saveBestStepRate(gulVar.f());
                motionPathSimplify.saveTotalSteps(m);
                motionPathSimplify.saveAvgStepRate(gulVar.e(motionPathSimplify.requestTotalTime()));
                gwo.a(context, motionPathSimplify, "target_motion_simplify.txt");
            }
            motionPath.saveStepRateList(gulVar.c(true));
            gulVar.e();
        } else {
            gwo.a(context, motionPathSimplify, "target_motion_simplify.txt");
        }
        a(motionPath, motionPathSimplify);
        boolean a2 = gwo.a(context, motionPath, "target_motion_path.txt");
        this.c = a2;
        ReleaseLogUtil.e("Track_StopSportWithTargetManager", "refreshMotionData mIsSaveTargetSuccess：", Boolean.valueOf(a2));
    }

    private void e(MotionPathSimplify motionPathSimplify) {
        long requestTotalTime = motionPathSimplify.requestTotalTime();
        motionPathSimplify.correctTotalTime("Track_StopSportWithTargetManager");
        if (motionPathSimplify.requestTotalTime() + motionPathSimplify.requestStartTime() > System.currentTimeMillis()) {
            motionPathSimplify.saveTotalTime(requestTotalTime);
        }
    }

    private void a(MotionPath motionPath, MotionPathSimplify motionPathSimplify) {
        if (motionPath.isValidLbsDataMap() && motionPathSimplify != null && (motionPath.requestLbsDataMap() instanceof TreeMap)) {
            TreeMap treeMap = (TreeMap) motionPath.requestLbsDataMap();
            long requestTotalTime = motionPathSimplify.requestTotalTime() / 1000;
            if (treeMap.size() > requestTotalTime) {
                motionPath.saveLbsDataMap(treeMap.subMap(0L, Long.valueOf(requestTotalTime)));
            }
            ReleaseLogUtil.e("Track_StopSportWithTargetManager", "correctLbsDataByTotalTime：lbsMap.size()=", Integer.valueOf(treeMap.size()), " totalTime= ", Long.valueOf(requestTotalTime));
        }
    }

    public boolean a() {
        return this.c;
    }

    public int b() {
        return this.f;
    }

    public float h() {
        return this.i;
    }

    boolean c() {
        return this.b;
    }
}
