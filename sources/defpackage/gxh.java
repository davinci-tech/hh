package defpackage;

import com.huawei.healthcloud.plugintrack.manager.voice.SportAudioScenarioId;
import com.huawei.healthcloud.plugintrack.manager.voice.constructor.ChineseVoiceConstructor;
import com.huawei.healthcloud.plugintrack.manager.voice.constructor.ISoundResourceConstructor;
import com.huawei.healthcloud.plugintrack.manager.voice.constructor.IVoiceContentConstructor;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.UnitUtil;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes4.dex */
public class gxh extends ChineseVoiceConstructor implements IVoiceContentConstructor, ISoundResourceConstructor {
    private boolean c(int i) {
        return i == 259 || i == 265;
    }

    private boolean d(int i) {
        return i == 264 || i == 257 || i == 258;
    }

    public gxh() {
        Locale locale = BaseApplication.getContext().getResources().getConfiguration().locale;
        String c = mxb.c();
        long currentTimeMillis = System.currentTimeMillis();
        mxb.a().init(mxh.d(c, "", "Sport", locale));
        LogUtil.a("MultilingualAudioConstructor", "MultilingualAudio init lang ", c, " locale ", locale, " timbre ", "", " init time ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.constructor.ChineseVoiceConstructor, com.huawei.healthcloud.plugintrack.manager.voice.constructor.ISoundResourceConstructor
    public Map<Integer, Integer> getSoundResource() {
        return super.getSoundResource();
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.constructor.ChineseVoiceConstructor, com.huawei.healthcloud.plugintrack.manager.voice.constructor.IVoiceContentConstructor
    public Object constructContent(int i, Object obj) {
        LogUtil.a("MultilingualAudioConstructor", "constructContent type : ", Integer.valueOf(i));
        if (!(obj instanceof gxi)) {
            LogUtil.a("MultilingualAudioConstructor", "parameter not instance of SportStateVoiceParameter");
            return a();
        }
        gxi gxiVar = (gxi) obj;
        ArrayList arrayList = new ArrayList(1);
        if (e((List<Object>) arrayList, i) || c(arrayList, i, gxiVar) || b(arrayList, i, gxiVar) || a(arrayList, i, gxiVar)) {
            return arrayList;
        }
        c((List<Object>) arrayList);
        return arrayList;
    }

    private void c(List<Object> list) {
        if (list.isEmpty()) {
            list.addAll(a());
        }
    }

    private boolean a(List<Object> list, int i, gxi gxiVar) {
        if (i == 9) {
            list.addAll(e(gxiVar, 9));
        } else {
            if (i != 11) {
                return false;
            }
            list.addAll(e(gxiVar, 11));
        }
        return true;
    }

    private boolean b(List<Object> list, int i, gxi gxiVar) {
        if (i == 14) {
            list.addAll(f());
        } else {
            switch (i) {
                case 21:
                    list.addAll(e());
                    break;
                case 22:
                    list.addAll(d());
                    break;
                case 23:
                    list.addAll(o(gxiVar));
                    break;
                case 24:
                    list.addAll(b());
                    break;
                case 25:
                    list.addAll(k());
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    private boolean c(List<Object> list, int i, gxi gxiVar) {
        if (i == 6) {
            list.addAll(i());
        } else if (i == 12) {
            list.addAll(i(gxiVar));
        } else if (i == 13) {
            list.addAll(j(gxiVar));
        } else {
            switch (i) {
                case 17:
                    list.addAll(k(gxiVar));
                    break;
                case 18:
                    list.addAll(c());
                    break;
                case 19:
                    list.addAll(j());
                    break;
                case 20:
                    list.addAll(b(gxiVar));
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    private boolean e(List<Object> list, int i) {
        if (i == 0) {
            list.addAll(p());
            return true;
        }
        if (i == 1) {
            list.addAll(q());
            return true;
        }
        if (i == 2) {
            list.addAll(w());
            return true;
        }
        if (i == 3) {
            list.addAll(l());
            return true;
        }
        if (i == 4) {
            list.addAll(y());
            return true;
        }
        if (i == 5) {
            list.addAll(m());
            return true;
        }
        if (i == 15) {
            list.addAll(v());
            return true;
        }
        if (i != 16) {
            switch (i) {
                case 110:
                    list.addAll(u());
                    break;
                case 111:
                    list.addAll(t());
                    break;
                case 112:
                    list.addAll(r());
                    break;
            }
            return true;
        }
        list.addAll(x());
        return true;
    }

    private List<String> b(gxi gxiVar) {
        int b = gxiVar.b();
        List<SportAudioScenarioId> countDownScenarios = SportAudioScenarioId.getCountDownScenarios();
        if (koq.b(countDownScenarios, b)) {
            return Collections.emptyList();
        }
        return d(countDownScenarios.get(b));
    }

    public List<String> e(gxi gxiVar, int i) {
        LogUtil.a("MultilingualAudioConstructor", "getSportInfoUrls ", gxiVar, " ", Integer.valueOf(i));
        ArrayList arrayList = new ArrayList(1);
        arrayList.addAll(a(gxiVar, i));
        arrayList.addAll(g(gxiVar));
        arrayList.addAll(h(gxiVar));
        arrayList.addAll(l(gxiVar));
        arrayList.addAll(d(gxiVar));
        arrayList.addAll(m(gxiVar));
        arrayList.addAll(c(gxiVar));
        arrayList.addAll(a(gxiVar));
        arrayList.addAll(f(gxiVar));
        arrayList.addAll(e(gxiVar));
        arrayList.addAll(s(gxiVar));
        arrayList.addAll(n(gxiVar));
        arrayList.addAll(a());
        LogUtil.a("MultilingualAudioConstructor", "getSportInfoUrls ", arrayList);
        return arrayList;
    }

    private List<String> a() {
        return d(SportAudioScenarioId.EMPTY);
    }

    private List<String> a(gxi gxiVar, int i) {
        boolean p = gxiVar.p();
        boolean s = gxiVar.s();
        float a2 = gxiVar.a();
        if (p && i == 9) {
            a2 = (int) a2;
        }
        int r = ((int) gxiVar.r()) / 1000;
        int x = gxiVar.x();
        if (a2 <= 0.0f || r <= 0 || x < 0) {
            return Collections.emptyList();
        }
        boolean z = gxiVar.m() > 50;
        boolean z2 = gxiVar.o() && gxiVar.j() > 0.0f;
        if (p && z2) {
            return a(a2, r, x, gxiVar.j() / 1000.0f, z);
        }
        if (s && z2) {
            return e(a2, r, x, (int) gxiVar.j(), z);
        }
        return e(a2, r);
    }

    private List<String> e(double d, int i, int i2, int i3, boolean z) {
        SportAudioScenarioId sportAudioScenarioId;
        mwz mwzVar = new mwz();
        a(mwzVar, d);
        mwzVar.b(Integer.valueOf(i));
        mwzVar.b(Integer.valueOf(i2));
        mwzVar.b(Integer.valueOf(i3));
        if (z) {
            sportAudioScenarioId = SportAudioScenarioId.EXERCISE_DETAIL_WHEN_TIME_TARGET_HANG_ON;
        } else {
            sportAudioScenarioId = SportAudioScenarioId.EXERCISE_DETAIL_WHEN_TIME_TARGET_COME_ON;
        }
        return d(sportAudioScenarioId, mwzVar);
    }

    private List<String> a(double d, int i, int i2, double d2, boolean z) {
        SportAudioScenarioId sportAudioScenarioId;
        mwz mwzVar = new mwz();
        a(mwzVar, d);
        mwzVar.b(Integer.valueOf(i));
        mwzVar.b(Integer.valueOf(i2));
        mwzVar.b(Double.valueOf(d2));
        if (z) {
            sportAudioScenarioId = SportAudioScenarioId.EXERCISE_DETAIL_WHEN_DISTANCE_TARGET_HANG_ON;
        } else {
            sportAudioScenarioId = SportAudioScenarioId.EXERCISE_DETAIL_WHEN_DISTANCE_TARGET_COME_ON;
        }
        return d(sportAudioScenarioId, mwzVar);
    }

    private List<String> e(double d, int i) {
        mwz mwzVar = new mwz();
        a(mwzVar, d);
        mwzVar.b(Integer.valueOf(i));
        return d(SportAudioScenarioId.EXERCISE_DETAIL_WITHOUT_TARGET, mwzVar);
    }

    private void a(mwz mwzVar, double d) {
        double a2 = UnitUtil.a(d, 2);
        if (Math.abs(a2 - Math.round(a2)) < Double.MIN_VALUE) {
            mwzVar.b(Integer.valueOf((int) a2));
        } else {
            mwzVar.b(Double.valueOf(a2));
        }
    }

    private List<String> s(gxi gxiVar) {
        if (!gxiVar.ak()) {
            return Collections.emptyList();
        }
        int ab = gxiVar.ab();
        if (ab == 22) {
            return r(gxiVar);
        }
        if (ab == 23) {
            return ac();
        }
        return Collections.emptyList();
    }

    private List<String> ac() {
        return d(SportAudioScenarioId.WARM_UP_NOT_ENOUGH);
    }

    private List<String> r(gxi gxiVar) {
        mwz mwzVar = new mwz();
        mwzVar.b(Integer.valueOf(gxiVar.ah()));
        mwzVar.b(Integer.valueOf(gxiVar.aa()));
        return d(SportAudioScenarioId.WARM_UP_IN_HEART_RATE_RANGE, mwzVar);
    }

    private List<String> n(gxi gxiVar) {
        if (!gxiVar.ae()) {
            return Collections.emptyList();
        }
        int y = gxiVar.y();
        Map<Integer, SportAudioScenarioId> runningPostureScenarios = SportAudioScenarioId.getRunningPostureScenarios();
        if (!runningPostureScenarios.containsKey(Integer.valueOf(y))) {
            return Collections.emptyList();
        }
        return d(runningPostureScenarios.get(Integer.valueOf(y)));
    }

    private List<String> e(gxi gxiVar) {
        if (!gxiVar.al()) {
            return Collections.emptyList();
        }
        int ad = gxiVar.ad();
        Map<Integer, SportAudioScenarioId> cadenceSpeedScenarios = SportAudioScenarioId.getCadenceSpeedScenarios();
        if (!cadenceSpeedScenarios.containsKey(Integer.valueOf(ad))) {
            return Collections.emptyList();
        }
        return d(cadenceSpeedScenarios.get(Integer.valueOf(ad)));
    }

    private List<String> f(gxi gxiVar) {
        if (!gxiVar.ag()) {
            return Collections.emptyList();
        }
        int u = gxiVar.u();
        Map<Integer, SportAudioScenarioId> landPostureEstimationScenarios = SportAudioScenarioId.getLandPostureEstimationScenarios();
        if (!landPostureEstimationScenarios.containsKey(Integer.valueOf(u))) {
            return Collections.emptyList();
        }
        return d(landPostureEstimationScenarios.get(Integer.valueOf(u)));
    }

    private List<String> a(gxi gxiVar) {
        if (!gxiVar.ai()) {
            return Collections.emptyList();
        }
        return d(SportAudioScenarioId.ADJUST_POSTURE_REMIND);
    }

    private List<String> c(gxi gxiVar) {
        if (!gxiVar.am()) {
            return Collections.emptyList();
        }
        return d(SportAudioScenarioId.FOLLOW_DEVICE_GUIDE_TO_SPORT);
    }

    private List<String> m(gxi gxiVar) {
        if (!gxiVar.aj()) {
            return Collections.emptyList();
        }
        return d(SportAudioScenarioId.PRESS_START_ON_DEVICE_TO_SPORT);
    }

    private List<String> d(gxi gxiVar) {
        if (!gxiVar.af()) {
            return Collections.emptyList();
        }
        return d(SportAudioScenarioId.DEVICE_CONNECTED);
    }

    private List<String> l(gxi gxiVar) {
        if (!gxiVar.w()) {
            return Collections.emptyList();
        }
        float e = gxiVar.e();
        int n = gxiVar.n();
        if (e > 0.0f) {
            return c(gxiVar, e, n);
        }
        return c(gxiVar, n);
    }

    private List<String> c(gxi gxiVar, int i) {
        LogUtil.a("MultilingualAudioConstructor", "getCurrentSpeedEqualsZero, mSpeed = 0");
        if (gxiVar.p() && gxiVar.s() && gxiVar.w() && gxiVar.q()) {
            return Collections.emptyList();
        }
        if (d(i)) {
            return n();
        }
        if (c(i)) {
            return s();
        }
        return Collections.emptyList();
    }

    private List<String> c(gxi gxiVar, float f, int i) {
        double d;
        LogUtil.a("MultilingualAudioConstructor", "getCurrentSpeedGreaterThanZero");
        if (d(i)) {
            return c(((int) (3600.0f / f)) * 1000, gxiVar.h());
        }
        if (c(i)) {
            try {
                d = Double.parseDouble(gwg.c(f, 1));
            } catch (NumberFormatException unused) {
                LogUtil.h("MultilingualAudioConstructor", "numberFormatException");
                d = 0.0d;
            }
            return e(d, gxiVar.h());
        }
        return Collections.emptyList();
    }

    private List<String> e(double d, boolean z) {
        new mwz().b(Double.valueOf(d));
        return d(z ? SportAudioScenarioId.CURRENT_SPEED_GREAT : SportAudioScenarioId.CURRENT_SPEED_COME_ON);
    }

    private List<String> c(int i, boolean z) {
        new mwz().b(Integer.valueOf(i));
        return d(z ? SportAudioScenarioId.CURRENT_PACE_GREAT : SportAudioScenarioId.CURRENT_PACE_COME_ON);
    }

    private List<String> s() {
        return d(SportAudioScenarioId.SPEED_MEASURING);
    }

    private List<String> n() {
        return d(SportAudioScenarioId.PACE_MEASURING);
    }

    private List<String> h(gxi gxiVar) {
        if (!gxiVar.q()) {
            return Collections.emptyList();
        }
        LogUtil.a("MultilingualAudioConstructor", "getHeartRateUrls");
        int c = gxiVar.c();
        if (c > 0) {
            return e(c);
        }
        if (gxiVar.p() && gxiVar.s() && ((gxiVar.w() || gxiVar.v()) && gxiVar.q())) {
            return Collections.emptyList();
        }
        if (gxiVar.z() > 1) {
            return Collections.emptyList();
        }
        boolean j = gso.e().j();
        boolean l = gso.e().l();
        if (!j || !l) {
            return g();
        }
        return h();
    }

    private List<String> h() {
        return d(SportAudioScenarioId.HEART_RATE_MEASURING);
    }

    private List<String> g() {
        return d(SportAudioScenarioId.HEART_RATE_DEVICE_NOT_CONNECTED);
    }

    private List<String> e(int i) {
        mwz mwzVar = new mwz();
        mwzVar.b(Integer.valueOf(i));
        return d(SportAudioScenarioId.CURRENT_HEART_RATE, mwzVar);
    }

    private List<String> g(gxi gxiVar) {
        if (!gxiVar.v()) {
            return Collections.emptyList();
        }
        long g = gxiVar.g();
        LogUtil.a("MultilingualAudioConstructor", "getPaceUrls");
        if (g > 0) {
            return b(gxiVar, g);
        }
        return Collections.emptyList();
    }

    private List<String> b(gxi gxiVar, long j) {
        if (j <= 1000) {
            if (gxiVar.p() && gxiVar.s() && gxiVar.v() && gxiVar.q()) {
                return Collections.emptyList();
            }
            return o();
        }
        return c(j / 1000, gxiVar.h());
    }

    private List<String> c(long j, boolean z) {
        mwz mwzVar = new mwz();
        mwzVar.b(Integer.valueOf((int) j));
        return d(z ? SportAudioScenarioId.RECENT_ONE_KM_PACE_GREAT : SportAudioScenarioId.RECENT_ONE_KM_PACE_COMMON_ON, mwzVar);
    }

    private List<String> o() {
        return d(SportAudioScenarioId.LESS_THEN_ONE_KM);
    }

    private List<String> q() {
        return d(SportAudioScenarioId.START_RUN);
    }

    private List<String> r() {
        return d(SportAudioScenarioId.START_CLIMB);
    }

    private List<String> t() {
        return d(SportAudioScenarioId.START_HIKING);
    }

    private List<String> u() {
        return d(SportAudioScenarioId.START_SPORT);
    }

    private List<String> p() {
        return d(SportAudioScenarioId.START_BIKE);
    }

    private List<String> w() {
        return d(SportAudioScenarioId.START_WALK);
    }

    private List<String> d() {
        return a(SportAudioScenarioId.getEncourageScenarios());
    }

    private List<String> e() {
        return a(SportAudioScenarioId.getEncourageWithRunningScenarios());
    }

    private List<String> a(List<SportAudioScenarioId> list) {
        int b = b(list.size());
        if (koq.b(list, b)) {
            b = 0;
        }
        return d(list.get(b));
    }

    private int b(int i) {
        return new SecureRandom().nextInt(i);
    }

    private List<String> l() {
        return d(SportAudioScenarioId.PAUSE_SPORT);
    }

    private List<String> m() {
        return d(SportAudioScenarioId.RESUME_SPORT);
    }

    private List<String> v() {
        return d(SportAudioScenarioId.STOP_SPORT_WITH_RELAX);
    }

    private List<String> x() {
        return d(SportAudioScenarioId.STOP_SPORT_WITH_STRETCH);
    }

    private List<String> y() {
        return d(SportAudioScenarioId.STOP_SPORT);
    }

    private List<String> i() {
        return d(SportAudioScenarioId.ACHIEVE_GOAL);
    }

    private List<String> j() {
        return d(SportAudioScenarioId.ACHIEVE_HALF_GOAL);
    }

    private List<String> j(gxi gxiVar) {
        new mwz().b(Integer.valueOf(d(gxiVar.r())));
        return d(SportAudioScenarioId.HALF_MARATHON_COMPLETE);
    }

    private List<String> i(gxi gxiVar) {
        new mwz().b(Integer.valueOf(d(gxiVar.r())));
        return d(SportAudioScenarioId.MARATHON_COMPLETE);
    }

    private List<String> c() {
        return d(SportAudioScenarioId.FIVE_HUNDRED_LEFT);
    }

    private List<String> k(gxi gxiVar) {
        double j = gxiVar.j();
        int b = gxiVar.b();
        List<SportAudioScenarioId> distanceRemainingScenarios = SportAudioScenarioId.getDistanceRemainingScenarios();
        a(new mwz(), j);
        return d(distanceRemainingScenarios.get(b % distanceRemainingScenarios.size()));
    }

    private List<String> o(gxi gxiVar) {
        new mwz().b(Integer.valueOf((int) gxiVar.a()));
        return d(SportAudioScenarioId.SENSOR_DISTANCE);
    }

    private List<String> b() {
        List<String> d = d(SportAudioScenarioId.STOP_SPORT);
        d.addAll(d(SportAudioScenarioId.DISTANCE_TOO_SHORT));
        return d;
    }

    private List<String> k() {
        return d(SportAudioScenarioId.PRIVACY_AUTHORIZE_REMIND);
    }

    private List<String> f() {
        return d(SportAudioScenarioId.HEART_RATE_TOO_FAST_WARNING);
    }

    private List<String> d(SportAudioScenarioId sportAudioScenarioId) {
        return d(sportAudioScenarioId, new mwz());
    }

    private List<String> d(SportAudioScenarioId sportAudioScenarioId, mwz mwzVar) {
        LogUtil.a("MultilingualAudioConstructor", "getAudioUrls id: ", sportAudioScenarioId.getId(), " name: ", sportAudioScenarioId.name(), " input: ", mwzVar);
        return mxb.a().getScenarioAudioPaths(sportAudioScenarioId.getId(), mwzVar);
    }

    private int d(long j) {
        return (int) (j / 1000);
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.constructor.IVoiceContentConstructor
    public void release() {
        mxb.a().release();
    }
}
