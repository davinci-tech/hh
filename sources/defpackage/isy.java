package defpackage;

import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcloudmodel.model.unite.SportTotal;
import health.compact.a.HiDateUtil;
import health.compact.a.HiValidatorUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class isy {

    static class d {

        /* renamed from: a, reason: collision with root package name */
        private static final isy f13589a = new isy();
    }

    public static isy d() {
        return d.f13589a;
    }

    public List<SportTotal> b(List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList(list.size() * 5);
        for (HiHealthData hiHealthData : list) {
            int c = HiDateUtil.c(hiHealthData.getStartTime());
            String timeZone = hiHealthData.getTimeZone();
            c(arrayList, hiHealthData, c, 2, timeZone);
            d(arrayList, hiHealthData, c, 2, timeZone);
            e(arrayList, hiHealthData, c, 2, timeZone);
            b(arrayList, hiHealthData, c, 2, timeZone);
            a(arrayList, hiHealthData, c, 2, timeZone);
        }
        return arrayList;
    }

    private void c(List<SportTotal> list, HiHealthData hiHealthData, int i, int i2, String str) {
        SportTotal sportTotal = new SportTotal();
        int i3 = (int) hiHealthData.getDouble("sport_walk_step_sum");
        int i4 = (int) hiHealthData.getDouble("sport_walk_distance_sum");
        int i5 = (int) hiHealthData.getDouble("sport_walk_calorie_sum");
        int i6 = (int) hiHealthData.getDouble("sport_walk_duration_sum");
        if (!HiValidatorUtil.a(i3)) {
            ReleaseLogUtil.c("Debug_SportDataSwitch", "addWalkTotal steps is out of range hiHealthData = ", hiHealthData);
            return;
        }
        sportTotal.setSportBasicInfo(iup.e(Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), Float.valueOf(0.0f), 0, Integer.valueOf(i6 / 60), 0));
        sportTotal.setRecordDay(Integer.valueOf(i));
        sportTotal.setDataSource(Integer.valueOf(i2));
        sportTotal.setTimeZone(str);
        sportTotal.setSportType(5);
        list.add(sportTotal);
    }

    private void d(List<SportTotal> list, HiHealthData hiHealthData, int i, int i2, String str) {
        SportTotal sportTotal = new SportTotal();
        int i3 = (int) hiHealthData.getDouble("sport_run_distance_sum");
        int i4 = (int) hiHealthData.getDouble("sport_run_calorie_sum");
        int i5 = (int) hiHealthData.getDouble("sport_run_duration_sum");
        int i6 = (int) hiHealthData.getDouble("sport_run_step_sum");
        if (!HiValidatorUtil.a(i6)) {
            ReleaseLogUtil.c("Debug_SportDataSwitch", "addRunTotal steps is out of range hiHealthData = ", hiHealthData);
            return;
        }
        sportTotal.setSportBasicInfo(iup.e(Integer.valueOf(i6), Integer.valueOf(i3), Integer.valueOf(i4), Float.valueOf(0.0f), 0, Integer.valueOf(i5 / 60), 0));
        sportTotal.setRecordDay(Integer.valueOf(i));
        sportTotal.setDataSource(Integer.valueOf(i2));
        sportTotal.setTimeZone(str);
        sportTotal.setSportType(4);
        list.add(sportTotal);
    }

    private void e(List<SportTotal> list, HiHealthData hiHealthData, int i, int i2, String str) {
        SportTotal sportTotal = new SportTotal();
        int i3 = (int) hiHealthData.getDouble("sport_cycle_distance_sum");
        int i4 = (int) hiHealthData.getDouble("sport_cycle_calorie_sum");
        int i5 = (int) hiHealthData.getDouble("sport_cycle_duration_sum");
        if (!HiValidatorUtil.e(i4)) {
            ReleaseLogUtil.c("Debug_SportDataSwitch", "addRideTotal calorie is out of range hiHealthData = ", hiHealthData);
            return;
        }
        sportTotal.setSportBasicInfo(iup.e(0, Integer.valueOf(i3), Integer.valueOf(i4), Float.valueOf(0.0f), 0, Integer.valueOf(i5 / 60), 0));
        sportTotal.setRecordDay(Integer.valueOf(i));
        sportTotal.setDataSource(Integer.valueOf(i2));
        sportTotal.setTimeZone(str);
        sportTotal.setSportType(3);
        list.add(sportTotal);
    }

    private void b(List<SportTotal> list, HiHealthData hiHealthData, int i, int i2, String str) {
        SportTotal sportTotal = new SportTotal();
        int i3 = (int) hiHealthData.getDouble("sport_climb_distance_sum");
        int i4 = (int) hiHealthData.getDouble("sport_climb_calorie_sum");
        int i5 = (int) hiHealthData.getDouble("sport_climb_duration_sum");
        float f = (float) hiHealthData.getDouble("sport_altitude_offset_sum");
        int i6 = (int) hiHealthData.getDouble("sport_climb_step_sum");
        if (!HiValidatorUtil.a(i6) || !HiValidatorUtil.e(f)) {
            ReleaseLogUtil.c("Debug_SportDataSwitch", "addMountainTotal STEPS is out of range hiHealthData = ", hiHealthData);
            return;
        }
        sportTotal.setSportBasicInfo(iup.e(Integer.valueOf(i6), Integer.valueOf(i3), Integer.valueOf(i4), Float.valueOf(f / 10.0f), 0, Integer.valueOf(i5 / 60), 0));
        sportTotal.setRecordDay(Integer.valueOf(i));
        sportTotal.setDataSource(Integer.valueOf(i2));
        sportTotal.setTimeZone(str);
        sportTotal.setSportType(2);
        list.add(sportTotal);
    }

    private void a(List<SportTotal> list, HiHealthData hiHealthData, int i, int i2, String str) {
        SportTotal sportTotal = new SportTotal();
        sportTotal.setSportBasicInfo(iup.e(0, Integer.valueOf((int) hiHealthData.getDouble("sport_swim_distance_sum")), Integer.valueOf((int) hiHealthData.getDouble("sport_swim_calorie_sum")), Float.valueOf(0.0f), 0, Integer.valueOf(((int) hiHealthData.getDouble("sport_swim_duration_sum")) / 60), 0));
        sportTotal.setRecordDay(Integer.valueOf(i));
        sportTotal.setDataSource(Integer.valueOf(i2));
        sportTotal.setTimeZone(str);
        sportTotal.setSportType(9);
        list.add(sportTotal);
    }
}
