package defpackage;

import android.content.Context;
import android.text.format.DateFormat;
import com.huawei.health.R;
import com.huawei.health.suggestion.model.FitnessShareRecord;
import com.huawei.ui.commonui.R$string;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes4.dex */
public class fjh {

    /* renamed from: a, reason: collision with root package name */
    private feh f12541a;
    private Context b;
    private FitnessShareRecord c;

    public fjh(Context context, FitnessShareRecord fitnessShareRecord) {
        this.b = context;
        this.c = fitnessShareRecord;
    }

    public void b() {
        this.f12541a = new feh();
        HashMap hashMap = new HashMap();
        b(hashMap);
        d(hashMap);
        e(hashMap);
        c(hashMap);
        a(hashMap);
        this.f12541a.d(hashMap);
    }

    private void b(Map<String, String> map) {
        FitnessShareRecord fitnessShareRecord = this.c;
        if (fitnessShareRecord != null) {
            String e = ggm.e(fitnessShareRecord.getLevelCount());
            String exerciseName = this.c.getExerciseName();
            String format = new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "Md")).format(Long.valueOf(this.c.getExerciseTime()));
            int deviceType = this.c.getDeviceType();
            Context context = this.b;
            String a2 = cwa.a(deviceType, context, context.getPackageName());
            map.put("fitness_course_level", e);
            map.put("fitness_course_name", exerciseName);
            map.put("sport_date", format);
            map.put("sport_source", a2);
            map.put("sport_type", exerciseName);
        }
    }

    private void d(Map<String, String> map) {
        FitnessShareRecord fitnessShareRecord = this.c;
        if (fitnessShareRecord != null) {
            String i = moe.i(moe.b(fitnessShareRecord.getCalorie()));
            String string = this.b.getString(R.string._2130848385_res_0x7f022a81);
            String string2 = this.b.getString(R.string._2130847440_res_0x7f0226d0);
            String string3 = this.b.getResources().getString(R$string.IDS_track_total_calorie_kcal);
            map.put("calorie", i);
            map.put("calorie_unit", string);
            map.put("calorie_title", string2);
            map.put("calorie_title_with_unit", string3);
        }
    }

    private void e(Map<String, String> map) {
        if (this.c != null) {
            String g = moe.g((int) r0.getDuration());
            String string = this.b.getString(R.string._2130837546_res_0x7f02002a);
            String string2 = this.b.getString(R.string._2130851571_res_0x7f0236f3);
            String string3 = this.b.getResources().getString(R$string.IDS_motiontrack_history_sum_catetory_time_unit_min);
            map.put("duration", g);
            map.put("duration_unit", string2);
            map.put("duration_title", string);
            map.put("duration_title_with_unit", string3);
        }
    }

    private void c(Map<String, String> map) {
        FitnessShareRecord fitnessShareRecord = this.c;
        if (fitnessShareRecord != null) {
            int actionCount = fitnessShareRecord.getActionCount();
            String e = e(actionCount);
            String string = this.b.getString(R.string._2130848434_res_0x7f022ab2);
            String quantityString = this.b.getResources().getQuantityString(R.plurals._2130903395_res_0x7f030163, actionCount, "");
            String string2 = this.b.getResources().getString(R.string._2130848553_res_0x7f022b29);
            map.put("fitness_action_count", e);
            map.put("fitness_action_count_title", string);
            map.put("fitness_action_count_unit", quantityString);
            map.put("fitness_action_count_title_with_unit", string2);
        }
    }

    private String e(int i) {
        if (i > 0) {
            return String.valueOf(i);
        }
        return this.b.getString(R$string.IDS_motiontrack_show_invalid_data);
    }

    private void a(Map<String, String> map) {
        FitnessShareRecord fitnessShareRecord = this.c;
        if (fitnessShareRecord != null) {
            int exerciseTimes = fitnessShareRecord.getExerciseTimes();
            String e = e(exerciseTimes);
            String string = this.b.getString(R$string.IDS_hwh_motiontrack_sport_fitness_times);
            String quantityString = this.b.getResources().getQuantityString(R.plurals._2130903476_res_0x7f0301b4, exerciseTimes, "");
            String string2 = this.b.getResources().getString(R.string._2130848554_res_0x7f022b2a);
            map.put("training_times", e);
            map.put("training_times_title", string);
            map.put("training_times_title_with_unit", string2);
            map.put("training_times_unit", quantityString);
        }
    }

    public feh c() {
        return this.f12541a;
    }
}
