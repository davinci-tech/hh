package defpackage;

import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes3.dex */
public class dwk {

    /* renamed from: a, reason: collision with root package name */
    public static final Set<String> f11866a = Collections.unmodifiableSet(new HashSet<String>() { // from class: dwk.1
        private static final long serialVersionUID = -4982539112710710368L;

        {
            add("exercise_duration");
            add("distance");
            add("calorie");
            add("speed");
            add("pace");
            add("hr");
            add("avg_speed");
            add("avg_pace");
            add("total_rise");
            add("total_descend");
            add("altitude");
            add("aerobic_te");
            add("anaerobic_te");
            add("performance_condition");
            add("operator_type");
            add("running_course_number");
            add("running_course_action_count");
            add("running_course_action_id");
            add("running_course_left_type");
            add("running_course_content");
            add("step_cadence");
            add("step");
            add("linkage_cadence");
            add("linkage_pulp_frequency");
            add("linkage_slurry");
            add("linkage_power");
            add("linkage_resistance_level");
            add("linkage_resistance_level_max");
            add("linkage_resistance_level_min");
            add(HwExerciseConstants.JSON_NAME_ACTIVECALORIE);
        }
    });
    public static final Map<String, Integer> e = Collections.unmodifiableMap(new HashMap<String, Integer>() { // from class: dwk.3
        {
            put("exercise_duration", 50004396);
            put("distance", 50004765);
            put("calorie", 50004829);
            put("speed", 50004799);
            put("pace", 50004979);
            put("hr", 50004838);
            put("avg_speed", 50004858);
            put("avg_pace", 50004872);
            put("total_rise", 50004395);
            put("total_descend", 50004774);
            put("altitude", 50004286);
            put("aerobic_te", 50004172);
            put("anaerobic_te", 50004881);
            put("performance_condition", 50004011);
            put("operator_type", 50004770);
            put("running_course_number", 50004632);
            put("running_course_action_count", 50004251);
            put("running_course_action_id", 50004561);
            put("running_course_left_type", 50004992);
            put("running_course_content", 50004187);
            put("step_cadence", 50004721);
            put("step", 50004684);
            put("linkage_cadence", 50004254);
            put("linkage_pulp_frequency", 50004184);
            put("linkage_slurry", 50004232);
            put("linkage_power", 50004757);
            put("linkage_resistance_level", 50004861);
            put("linkage_resistance_level_max", 50004961);
            put("linkage_resistance_level_min", 50004199);
            put(HwExerciseConstants.JSON_NAME_ACTIVECALORIE, 50004617);
        }
    });

    public static kon e(String str, ldo ldoVar) {
        kon konVar = new kon();
        konVar.c(str + "#ANDROID21");
        konVar.c(((long) ldoVar.n()) * 1000);
        konVar.f(ldoVar.m());
        konVar.j(ldoVar.k());
        konVar.l(ldoVar.z());
        konVar.a(ldoVar.h());
        konVar.b(ldoVar.x());
        konVar.b(ldoVar.j());
        konVar.g(ldoVar.i());
        konVar.n(ldoVar.ad());
        konVar.o(ldoVar.ac());
        konVar.m(ldoVar.ab());
        konVar.b(ldoVar.a());
        konVar.e(ldoVar.d());
        konVar.i(ldoVar.y());
        konVar.d(ldoVar.aa() * 1000);
        konVar.h(ldoVar.q());
        konVar.e(ldoVar.v());
        konVar.a(ldoVar.r());
        konVar.c(ldoVar.e());
        konVar.d(ldoVar.c());
        return konVar;
    }
}
