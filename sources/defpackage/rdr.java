package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.huawei.health.R;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hihealth.data.model.RelativeSportData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;
import java.util.Objects;

/* loaded from: classes7.dex */
public class rdr {

    /* renamed from: a, reason: collision with root package name */
    private boolean f16724a;
    private int c;
    private Object e;

    public rdr(int i, Object obj) {
        this.c = i;
        this.e = obj;
    }

    public int r() {
        return this.c;
    }

    public int y() {
        Object obj = this.e;
        if (obj instanceof rdn) {
            return ((rdn) obj).ac();
        }
        return 199;
    }

    public int u() {
        Object obj = this.e;
        if (obj instanceof rdn) {
            return ((rdn) obj).ad();
        }
        return 199;
    }

    public Drawable dMv_(Context context) {
        Object obj = this.e;
        if (obj == null || context == null) {
            LogUtil.h("SportHistorySingleData", "mSportHistoryData is null");
            return BaseApplication.getContext().getDrawable(R.drawable.ic_health_list_outdoor_running);
        }
        if (this.c == 0) {
            return ((rdn) obj).dMu_(context);
        }
        return context.getDrawable(R.drawable.ic_health_list_fitness_new);
    }

    public int i() {
        Object obj = this.e;
        if (obj == null) {
            LogUtil.h("SportHistorySingleData", "mSportHistoryData is null");
            return 0;
        }
        int i = this.c;
        if (i == 0 && (obj instanceof rdn)) {
            return ((rdn) obj).n();
        }
        if (i == 1 && (obj instanceof WorkoutRecord) && ((WorkoutRecord) obj).isFitnessRecordFromDevice()) {
            return R.drawable._2131430490_res_0x7f0b0c5a;
        }
        if (this.c == 1) {
            Object obj2 = this.e;
            if ((obj2 instanceof WorkoutRecord) && ((WorkoutRecord) obj2).acquireWearType() == 3) {
                return R.drawable._2131430554_res_0x7f0b0c9a;
            }
        }
        return 0;
    }

    public float d() {
        Object obj = this.e;
        if (obj == null) {
            LogUtil.h("SportHistorySingleData", "mSportHistoryData is null");
            return 0.0f;
        }
        if (this.c == 0) {
            return ((rdn) obj).a();
        }
        return ((WorkoutRecord) obj).acquireActualCalorie();
    }

    public String o() {
        Object obj = this.e;
        if (obj == null) {
            LogUtil.h("SportHistorySingleData", "mSportHistoryData is null");
            return "";
        }
        return ((rdn) obj).g();
    }

    public long j() {
        Object obj = this.e;
        if (obj == null || !(obj instanceof rdn)) {
            return 0L;
        }
        return ((rdn) obj).c();
    }

    public long k() {
        Object obj = this.e;
        if (obj == null) {
            LogUtil.h("SportHistorySingleData", "mSportHistoryData is null");
            return 0L;
        }
        if (this.c == 0) {
            return ((rdn) obj).f();
        }
        return ((WorkoutRecord) obj).getDuration();
    }

    public float e() {
        Object obj = this.e;
        if (obj == null) {
            LogUtil.h("SportHistorySingleData", "mSportHistoryData is null");
            return 0.0f;
        }
        if (this.c == 0) {
            return ((rdn) obj).d();
        }
        return 0.0f;
    }

    public int x() {
        Object obj = this.e;
        if (obj == null) {
            LogUtil.h("SportHistorySingleData", "mSportHistoryData is null");
            return 0;
        }
        if (this.c == 0) {
            return ((rdn) obj).m();
        }
        return 10001;
    }

    public RelativeSportData l() {
        Object obj = this.e;
        if (obj == null) {
            LogUtil.h("SportHistorySingleData", "mSportHistoryData is null");
            return null;
        }
        if (this.c == 0) {
            return ((rdn) obj).h();
        }
        return null;
    }

    public List<RelativeSportData> f() {
        Object obj = this.e;
        if (obj == null) {
            LogUtil.h("SportHistorySingleData", "mSportHistoryData is null");
            return null;
        }
        if (this.c == 0) {
            return ((rdn) obj).s();
        }
        return null;
    }

    public int c() {
        Object obj = this.e;
        if (obj == null) {
            LogUtil.h("SportHistorySingleData", "mSportHistoryData is null");
            return 0;
        }
        if (this.c == 0) {
            return ((rdn) obj).b();
        }
        return 3;
    }

    public int w() {
        Object obj = this.e;
        if (obj == null) {
            LogUtil.h("SportHistorySingleData", "mSportHistoryData is null");
            return 0;
        }
        if (this.c == 0 && (obj instanceof rdn)) {
            return ((rdn) obj).l();
        }
        return 0;
    }

    public long v() {
        Object obj = this.e;
        if (obj == null) {
            LogUtil.h("SportHistorySingleData", "mSportHistoryData is null");
            return 0L;
        }
        if (this.c == 0) {
            return ((rdn) obj).k();
        }
        return ((WorkoutRecord) obj).startTime();
    }

    public long n() {
        Object obj = this.e;
        if (obj == null) {
            LogUtil.h("SportHistorySingleData", "mSportHistoryData is null");
            return 0L;
        }
        if (this.c == 0) {
            return ((rdn) obj).i();
        }
        return ((WorkoutRecord) obj).acquireExerciseTime();
    }

    public String ac() {
        Object obj = this.e;
        if (obj != null) {
            return this.c == 0 ? "" : ((WorkoutRecord) obj).acquireWorkoutName();
        }
        LogUtil.h("SportHistorySingleData", "mSportHistoryData is null");
        return "";
    }

    public int t() {
        Object obj = this.e;
        if (obj == null) {
            LogUtil.h("SportHistorySingleData", "mSportHistoryData is null");
            return 0;
        }
        if (this.c == 0) {
            return 0;
        }
        return ((WorkoutRecord) obj).acquireId();
    }

    public String ad() {
        Object obj = this.e;
        return obj instanceof WorkoutRecord ? ((WorkoutRecord) obj).acquireWorkoutId() : "";
    }

    public int b() {
        Object obj = this.e;
        if (obj == null) {
            LogUtil.h("SportHistorySingleData", "mSportHistoryData is null");
            return 0;
        }
        if (this.c == 0) {
            return ((rdn) obj).e();
        }
        return 0;
    }

    public float a() {
        Object obj = this.e;
        if (obj == null) {
            LogUtil.h("SportHistorySingleData", "mSportHistoryData is null");
            return 0.0f;
        }
        if (this.c == 0) {
            return ((rdn) obj).o();
        }
        return ((WorkoutRecord) obj).acquireActualCalorie();
    }

    public int h() {
        Object obj = this.e;
        if (obj == null) {
            LogUtil.h("SportHistorySingleData", "mSportHistoryData is null");
            return 0;
        }
        if (this.c == 0 && (obj instanceof rdn)) {
            return ((rdn) obj).j();
        }
        return 0;
    }

    public boolean m() {
        if (this.c != 0) {
            return false;
        }
        Object obj = this.e;
        if (obj instanceof rdn) {
            return ((rdn) obj).w();
        }
        return false;
    }

    public int ae() {
        Object obj = this.e;
        if (obj instanceof rdn) {
            return ((rdn) obj).u();
        }
        LogUtil.h("SportHistorySingleData", "mSportHistoryData is null,getSportDataSource");
        return 0;
    }

    public String ai() {
        Object obj = this.e;
        if (obj instanceof rdn) {
            return ((rdn) obj).y();
        }
        LogUtil.h("SportHistorySingleData", "mSportHistoryData is null,getRunCourseId");
        return "";
    }

    public String af() {
        Object obj = this.e;
        if (obj instanceof rdn) {
            return ((rdn) obj).v();
        }
        LogUtil.h("SportHistorySingleData", "mSportHistoryData is null,getRunCourseName");
        return "";
    }

    public boolean ag() {
        return this.f16724a;
    }

    public void c(boolean z) {
        this.f16724a = z;
    }

    public int g() {
        Object obj = this.e;
        if (obj instanceof rdn) {
            return ((rdn) obj).p();
        }
        LogUtil.h("SportHistorySingleData", "mSportHistoryData is null,acquireDeviceType");
        return 0;
    }

    public int s() {
        Object obj = this.e;
        if (obj instanceof rdn) {
            return ((rdn) obj).m();
        }
        LogUtil.h("SportHistorySingleData", "mSportHistoryData is null,acquireSportType");
        return 258;
    }

    public int e(Context context) {
        Object obj = this.e;
        if (obj == null) {
            LogUtil.h("SportHistorySingleData", "mSportHistoryData is null");
            return 0;
        }
        if (this.c == 0) {
            return ((rdn) obj).e(context);
        }
        return context.getColor(R.color._2131296927_res_0x7f09029f);
    }

    public int ab() {
        Object obj = this.e;
        if (obj == null) {
            LogUtil.h("SportHistorySingleData", "mSportHistoryData is null");
            return 0;
        }
        if (this.c == 0) {
            return ((rdn) obj).r();
        }
        return 0;
    }

    public String q() {
        Object obj = this.e;
        return obj instanceof rdn ? ((rdn) obj).x() : "";
    }

    public String z() {
        Object obj = this.e;
        if (obj != null) {
            return this.c == 0 ? ((rdn) obj).t() : "";
        }
        LogUtil.h("SportHistorySingleData", "mSportHistoryData is null");
        return "";
    }

    public int p() {
        if (this.c != 1) {
            return 0;
        }
        Object obj = this.e;
        if (obj instanceof WorkoutRecord) {
            return ((WorkoutRecord) obj).getRecordModeType();
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof rdr)) {
            return false;
        }
        rdr rdrVar = (rdr) obj;
        return this.c == rdrVar.c && this.f16724a == rdrVar.f16724a && v() == rdrVar.v() && n() == rdrVar.n() && x() == rdrVar.x() && ab() == rdrVar.ab();
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.c), this.e, Boolean.valueOf(this.f16724a));
    }

    public int aa() {
        Object obj = this.e;
        if (!(obj instanceof rdn)) {
            LogUtil.h("SportHistorySingleData", "mSportHistoryData is not instanceof SingleTrackData");
            return -1;
        }
        if (this.c == 0) {
            return ((rdn) obj).q();
        }
        return -1;
    }
}
