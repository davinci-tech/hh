package defpackage;

import com.huawei.health.R;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.ui.homehealth.sportsrecordingcard.SportsRecordingBean;

/* loaded from: classes9.dex */
public class otr extends oth {
    private WorkoutRecord d;

    @Override // defpackage.oth
    public int c() {
        return 3;
    }

    public SportsRecordingBean d(WorkoutRecord workoutRecord) {
        SportsRecordingBean sportsRecordingBean = new SportsRecordingBean();
        if (workoutRecord == null) {
            return sportsRecordingBean;
        }
        this.d = workoutRecord;
        sportsRecordingBean.setSportStartTime(workoutRecord.startTime());
        sportsRecordingBean.setSportEndTime(workoutRecord.acquireExerciseTime());
        sportsRecordingBean.setShowType(c());
        sportsRecordingBean.setSportTime(b(workoutRecord.startTime()));
        sportsRecordingBean.setSportData(b());
        sportsRecordingBean.setSportUnit(e());
        sportsRecordingBean.setSportSpeed(d());
        sportsRecordingBean.setSportSpeedUnit(d());
        sportsRecordingBean.setSportTypeName(workoutRecord.acquireWorkoutName());
        sportsRecordingBean.setSportKeepTime(a(workoutRecord.getDuration()));
        return sportsRecordingBean;
    }

    @Override // defpackage.oth
    public String b() {
        return this.d == null ? "" : e(r0.getActiveCalorie(), this.d.acquireActualCalorie());
    }

    @Override // defpackage.oth
    public String e() {
        return nsf.h(R.string._2130837585_res_0x7f020051);
    }
}
