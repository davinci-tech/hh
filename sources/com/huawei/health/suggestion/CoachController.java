package com.huawei.health.suggestion;

import android.os.Bundle;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle;
import com.huawei.health.suggestion.protobuf.CourseProto;
import com.huawei.health.suggestion.protobuf.CourseStateProto;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwfoundationmodel.trackmodel.ISportDataCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import defpackage.ggn;
import defpackage.koq;
import defpackage.kvq;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes4.dex */
public class CoachController {

    /* renamed from: a, reason: collision with root package name */
    private CourseStateProto.CourseState.Builder f3009a;
    private float b;
    private float c;
    private int d;
    private CourseProto.CourseStorageInfo.Builder e;
    private int f;
    private int g;
    private boolean h;
    private int i;
    private boolean j;
    private boolean k;
    private List<ISportDataCallback> l;
    private boolean m;
    private kvq n;
    private long o;
    private Map<StatusSource, List<CourseLinkageLifecycle>> p;

    public enum StatusSource {
        NEW_LINK_WEAR,
        APP
    }

    private CoachController() {
        this.p = new HashMap();
        this.l = new CopyOnWriteArrayList();
        this.k = false;
        this.j = false;
        this.h = false;
        this.d = 0;
        this.m = false;
    }

    static class a {
        private static final CoachController e = new CoachController();
    }

    public static final CoachController d() {
        return a.e;
    }

    public void d(StatusSource statusSource, CourseLinkageLifecycle courseLinkageLifecycle) {
        List<CourseLinkageLifecycle> list;
        if (statusSource == null) {
            LogUtil.b("Suggestion_CoachController", "source null.");
            return;
        }
        LogUtil.a("Suggestion_CoachController", "registerStatusCallback.", statusSource.toString());
        if (!this.p.containsKey(statusSource)) {
            list = new ArrayList<>();
        } else {
            list = this.p.get(statusSource);
        }
        if (list == null || list.contains(courseLinkageLifecycle)) {
            return;
        }
        list.add(courseLinkageLifecycle);
        this.p.put(statusSource, list);
    }

    public void b(StatusSource statusSource) {
        if (statusSource == null) {
            LogUtil.b("Suggestion_CoachController", "source null.");
        } else {
            LogUtil.a("Suggestion_CoachController", "unregisterStatusCallback.", statusSource.toString());
            this.p.remove(statusSource);
        }
    }

    public void e(ISportDataCallback iSportDataCallback) {
        List<ISportDataCallback> list = this.l;
        if (list != null) {
            list.add(iSportDataCallback);
        }
    }

    public void c() {
        this.o = 0L;
        this.m = false;
        this.n = null;
        this.f = 0;
        this.c = 0.0f;
        this.b = 0.0f;
        this.i = -1;
        this.d = 0;
        this.h = false;
        this.f3009a = null;
    }

    public boolean a(StatusSource statusSource, int i) {
        if (!this.k) {
            LogUtil.a("Suggestion_CoachController", "not support fitness link.");
            return false;
        }
        if (e(statusSource, i)) {
            return b(statusSource, true);
        }
        LogUtil.a("Suggestion_CoachController", "updateCoachState coachState:", Integer.valueOf(i), "mCoachState:", Integer.valueOf(this.d));
        int i2 = this.d;
        if (i == i2 || i2 == 3) {
            LogUtil.a("Suggestion_CoachController", "coach state is same.", Integer.valueOf(i2));
            return true;
        }
        if (i == 1) {
            if (i2 == 0) {
                return e(statusSource);
            }
            return d(statusSource);
        }
        if (i == 2) {
            return a(statusSource);
        }
        if (i == 3) {
            return c(statusSource);
        }
        LogUtil.a("Suggestion_CoachController", "getCoachState other condition");
        return false;
    }

    private boolean e(StatusSource statusSource, int i) {
        if (statusSource == StatusSource.APP && i == 1 && g()) {
            int courseSleepTime = b().getCourseSleepTime();
            LogUtil.a("Suggestion_CoachController", "isSleepResumeState courseSleepTime:", Integer.valueOf(courseSleepTime));
            if (courseSleepTime > 0) {
                return true;
            }
        }
        return false;
    }

    public int a() {
        return this.d;
    }

    public boolean e(StatusSource statusSource) {
        if (statusSource == null) {
            LogUtil.b("Suggestion_CoachController", "source null.");
            return false;
        }
        ReleaseLogUtil.e("Suggestion_CoachController", "start coach.", statusSource.toString());
        List<CourseLinkageLifecycle> list = this.p.get(statusSource);
        if (koq.b(list)) {
            LogUtil.b("Suggestion_CoachController", "startCoach coachStateCallbacks null.");
            return false;
        }
        CourseStateProto.CourseState.Builder newBuilder = CourseStateProto.CourseState.newBuilder();
        newBuilder.setCurrentVolume(ggn.d());
        newBuilder.setCourseIndex(0);
        for (CourseLinkageLifecycle courseLinkageLifecycle : list) {
            if (courseLinkageLifecycle != null && this.d != 1) {
                Bundle bundle = new Bundle();
                bundle.putLong("startSportTime", this.o);
                bundle.putSerializable("CourseStateProto", newBuilder.build());
                CourseProto.CourseStorageInfo.Builder builder = this.e;
                if (builder != null) {
                    bundle.putSerializable("CourseProto", builder.build());
                }
                courseLinkageLifecycle.start(this.g, bundle);
            }
        }
        this.d = 1;
        return true;
    }

    public boolean a(StatusSource statusSource) {
        if (statusSource == null) {
            LogUtil.b("Suggestion_CoachController", "source null.");
            return false;
        }
        ReleaseLogUtil.e("Suggestion_CoachController", "pause coach.", statusSource.toString());
        List<CourseLinkageLifecycle> list = this.p.get(statusSource);
        if (koq.b(list)) {
            LogUtil.b("Suggestion_CoachController", "coachStateCallbacks null.");
            return false;
        }
        for (CourseLinkageLifecycle courseLinkageLifecycle : list) {
            if (courseLinkageLifecycle != null && this.d != 2) {
                courseLinkageLifecycle.pause(this.g);
            }
        }
        this.d = 2;
        return true;
    }

    public boolean b(StatusSource statusSource, boolean z) {
        if (statusSource == null) {
            LogUtil.b("Suggestion_CoachController", "source null.");
            return false;
        }
        ReleaseLogUtil.e("Suggestion_CoachController", "resume coach.", statusSource.toString());
        List<CourseLinkageLifecycle> list = this.p.get(statusSource);
        if (koq.b(list)) {
            LogUtil.b("Suggestion_CoachController", "coachStateCallbacks null.");
            return false;
        }
        for (CourseLinkageLifecycle courseLinkageLifecycle : list) {
            if (courseLinkageLifecycle != null && (this.d != 1 || z)) {
                courseLinkageLifecycle.resume(this.g);
            }
        }
        this.d = 1;
        return true;
    }

    public boolean d(StatusSource statusSource) {
        return b(statusSource, false);
    }

    public boolean c(StatusSource statusSource) {
        if (statusSource == null) {
            LogUtil.b("Suggestion_CoachController", "source null.");
            return false;
        }
        ReleaseLogUtil.e("Suggestion_CoachController", "stop coach.", statusSource.toString());
        List<CourseLinkageLifecycle> list = this.p.get(statusSource);
        if (koq.b(list)) {
            LogUtil.b("Suggestion_CoachController", "coachStateCallbacks null.");
            return false;
        }
        for (CourseLinkageLifecycle courseLinkageLifecycle : list) {
            if (courseLinkageLifecycle != null && this.d != 3) {
                courseLinkageLifecycle.stop(this.g);
            }
        }
        this.d = 3;
        return true;
    }

    public void axL_(Bundle bundle) {
        if (bundle == null || koq.b(this.l)) {
            LogUtil.b("Suggestion_CoachController", "coachInfo null.");
            return;
        }
        for (ISportDataCallback iSportDataCallback : this.l) {
            if (iSportDataCallback != null) {
                iSportDataCallback.getSportInfo(bundle);
            }
        }
    }

    public void c(kvq kvqVar) {
        this.n = kvqVar;
    }

    public Bundle axM_(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(BleConstants.SPORT_TYPE, this.g);
        bundle.putInt("sportState", i);
        bundle.putLong("sportStartTime", this.o);
        bundle.putInt("duration", this.f);
        bundle.putInt("calorie", (int) Math.floor(this.c));
        bundle.putInt(HwExerciseConstants.JSON_NAME_ACTIVECALORIE, (int) Math.floor(this.b));
        bundle.putInt("distance", 0);
        kvq kvqVar = this.n;
        if (kvqVar != null) {
            bundle.putInt(IndoorEquipManagerApi.KEY_HEART_RATE, kvqVar.j());
            bundle.putInt("aerobicExercise", this.n.b());
            bundle.putInt("anaerobicExercise", this.n.d());
            bundle.putInt("performanceIndicator", this.n.o());
        } else {
            bundle.putInt(IndoorEquipManagerApi.KEY_HEART_RATE, this.i);
        }
        return bundle;
    }

    public boolean i() {
        return this.k;
    }

    public void e(boolean z) {
        this.k = z;
    }

    public void a(long j) {
        this.o = j;
    }

    public void c(int i) {
        this.g = i;
    }

    public void b(float f) {
        this.c = f;
    }

    public void d(float f) {
        this.b = f;
    }

    public int e() {
        return this.f;
    }

    public void a(int i) {
        this.f = i;
    }

    public boolean f() {
        return this.m;
    }

    public void c(boolean z) {
        this.m = z;
    }

    public void d(int i) {
        this.i = i;
    }

    public boolean h() {
        return this.h;
    }

    public void d(boolean z) {
        this.h = z;
    }

    public void b(StatusSource statusSource, int i) {
        ReleaseLogUtil.e("Suggestion_CoachController", "Chapter Forward.", statusSource.toString());
        CourseStateProto.CourseState.Builder newBuilder = CourseStateProto.CourseState.newBuilder();
        newBuilder.setCourseIndex(i);
        this.f3009a = newBuilder;
        List<CourseLinkageLifecycle> list = this.p.get(statusSource);
        if (koq.b(list)) {
            LogUtil.b("Suggestion_CoachController", "coachStateCallbacks null.");
            return;
        }
        for (CourseLinkageLifecycle courseLinkageLifecycle : list) {
            if (courseLinkageLifecycle != null) {
                courseLinkageLifecycle.chapterForward(newBuilder.build());
            }
        }
    }

    public void c(StatusSource statusSource, int i) {
        ReleaseLogUtil.e("Suggestion_CoachController", "Chapter Backward.", statusSource.toString());
        CourseStateProto.CourseState.Builder newBuilder = CourseStateProto.CourseState.newBuilder();
        newBuilder.setCourseIndex(i);
        this.f3009a = newBuilder;
        List<CourseLinkageLifecycle> list = this.p.get(statusSource);
        if (koq.b(list)) {
            LogUtil.b("Suggestion_CoachController", "coachStateCallbacks null.");
            return;
        }
        for (CourseLinkageLifecycle courseLinkageLifecycle : list) {
            if (courseLinkageLifecycle != null) {
                courseLinkageLifecycle.chapterBackward(newBuilder.build());
            }
        }
    }

    public void e(StatusSource statusSource, CourseStateProto.CourseState courseState) {
        ReleaseLogUtil.e("Suggestion_CoachController", "Inter Chapter Break.", statusSource.toString());
        this.f3009a = courseState.toBuilder();
        List<CourseLinkageLifecycle> list = this.p.get(statusSource);
        if (koq.b(list)) {
            LogUtil.b("Suggestion_CoachController", "coachStateCallbacks null.");
            return;
        }
        for (CourseLinkageLifecycle courseLinkageLifecycle : list) {
            if (courseLinkageLifecycle != null) {
                courseLinkageLifecycle.chapterBreak(courseState);
            }
        }
    }

    public void d(StatusSource statusSource, CourseStateProto.CourseState.Builder builder) {
        ReleaseLogUtil.e("Suggestion_CoachController", "volume Adjust.", statusSource.toString());
        List<CourseLinkageLifecycle> list = this.p.get(statusSource);
        if (koq.b(list)) {
            LogUtil.b("Suggestion_CoachController", "coachStateCallbacks null.");
            return;
        }
        for (CourseLinkageLifecycle courseLinkageLifecycle : list) {
            if (courseLinkageLifecycle != null) {
                courseLinkageLifecycle.volumeAdjust(builder.build());
            }
        }
    }

    public CourseStateProto.CourseState b() {
        CourseStateProto.CourseState.Builder builder = this.f3009a;
        if (builder != null) {
            builder.setCurrentVolume(ggn.d());
            return this.f3009a.build();
        }
        CourseStateProto.CourseState.Builder newBuilder = CourseStateProto.CourseState.newBuilder();
        newBuilder.setCurrentVolume(ggn.d());
        return newBuilder.build();
    }

    public void b(CourseProto.CourseStorageInfo.Builder builder) {
        this.e = builder;
    }

    public void c(CourseStateProto.CourseState.Builder builder) {
        this.f3009a = builder;
    }

    public boolean g() {
        return this.j;
    }

    public void a(boolean z) {
        this.j = z;
    }

    public void e(int i) {
        if (g()) {
            b(StatusSource.APP, i);
        } else {
            a(StatusSource.APP, 1);
        }
    }

    public void a(int i, int i2, int i3) {
        if (g()) {
            ReleaseLogUtil.e("Suggestion_CoachController", "enterRestingCommand tempCurrent:", Integer.valueOf(i));
            e(StatusSource.APP, ggn.c(i, i2, i3).build());
        } else {
            a(StatusSource.APP, 2);
        }
    }
}
