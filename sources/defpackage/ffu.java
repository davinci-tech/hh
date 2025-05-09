package defpackage;

import android.text.TextUtils;
import com.huawei.health.servicesui.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class ffu {
    private Map<String, Integer> c = new HashMap(16);

    public ffu() {
        d();
    }

    private void d() {
        this.c.put("C001B", Integer.valueOf(R$string.IDS_fitness_class_C001B));
        this.c.put("L001B", Integer.valueOf(R$string.IDS_fitness_class_L001B));
        this.c.put("L002B", Integer.valueOf(R$string.IDS_fitness_class_L002B));
        this.c.put("S001B", Integer.valueOf(R$string.IDS_fitness_class_S001B));
        this.c.put("S002B", Integer.valueOf(R$string.IDS_fitness_class_S002B));
        this.c.put("S003B", Integer.valueOf(R$string.IDS_fitness_class_S003B));
        this.c.put("S004B", Integer.valueOf(R$string.IDS_fitness_class_S004B));
        this.c.put("S005B", Integer.valueOf(R$string.IDS_fitness_class_S005B));
        this.c.put("W001B", Integer.valueOf(R$string.IDS_fitness_class_W001B));
        this.c.put("W002B", Integer.valueOf(R$string.IDS_fitness_class_W002B));
        this.c.put("W003B", Integer.valueOf(R$string.IDS_fitness_class_W003B));
        this.c.put("W004B", Integer.valueOf(R$string.IDS_fitness_class_W004B));
        this.c.put("W9001", Integer.valueOf(R$string.IDS_fitness_class_W9001));
        this.c.put("W9002", Integer.valueOf(R$string.IDS_fitness_class_W9002));
        this.c.put("W9003", Integer.valueOf(R$string.IDS_fitness_class_W9003));
        this.c.put("W9004", Integer.valueOf(R$string.IDS_fitness_class_W9004));
        this.c.put("W9005", Integer.valueOf(R$string.IDS_fitness_class_W9005));
        this.c.put("W9006", Integer.valueOf(R$string.IDS_fitness_class_W9006));
        this.c.put("W9007", Integer.valueOf(R$string.IDS_fitness_class_W9007));
        this.c.put("W9008", Integer.valueOf(R$string.IDS_fitness_class_W9008));
        this.c.put("W9009", Integer.valueOf(R$string.IDS_fitness_class_W9009));
        this.c.put("W9010", Integer.valueOf(R$string.IDS_fitness_class_W9010));
        e();
    }

    private void e() {
        c();
        b();
        a();
    }

    private void a() {
        this.c.put("032B", Integer.valueOf(R$string.IDS_fitness_action_032B));
        this.c.put("035B", Integer.valueOf(R$string.IDS_fitness_action_035B));
        this.c.put("051B", Integer.valueOf(R$string.IDS_fitness_action_051B));
        this.c.put("052B", Integer.valueOf(R$string.IDS_fitness_action_052B));
        this.c.put("053B", Integer.valueOf(R$string.IDS_fitness_action_053B));
        this.c.put("054B", Integer.valueOf(R$string.IDS_fitness_action_054B));
        this.c.put("030B", Integer.valueOf(R$string.IDS_fitness_action_030B));
        this.c.put("038B", Integer.valueOf(R$string.IDS_fitness_action_038B));
        this.c.put("046B", Integer.valueOf(R$string.IDS_fitness_action_046B));
        this.c.put("047B", Integer.valueOf(R$string.IDS_fitness_action_047B));
        this.c.put("042B", Integer.valueOf(R$string.IDS_fitness_action_042B));
        this.c.put("043B", Integer.valueOf(R$string.IDS_fitness_action_043B));
        this.c.put("119B", Integer.valueOf(R$string.IDS_fitness_action_119B));
        this.c.put("120B", Integer.valueOf(R$string.IDS_fitness_action_120B));
        this.c.put("112B", Integer.valueOf(R$string.IDS_fitness_action_112B));
        this.c.put("113B", Integer.valueOf(R$string.IDS_fitness_action_113B));
        this.c.put("320B", Integer.valueOf(R$string.IDS_fitness_action_320B));
        this.c.put("321B", Integer.valueOf(R$string.IDS_fitness_action_321B));
        this.c.put("033B", Integer.valueOf(R$string.IDS_fitness_action_033B));
        this.c.put("322B", Integer.valueOf(R$string.IDS_fitness_action_322B));
        this.c.put("323B", Integer.valueOf(R$string.IDS_fitness_action_323B));
        this.c.put("324B", Integer.valueOf(R$string.IDS_fitness_action_324B));
        this.c.put("316B", Integer.valueOf(R$string.IDS_fitness_action_316B));
        this.c.put("317B", Integer.valueOf(R$string.IDS_fitness_action_317B));
        this.c.put("330B", Integer.valueOf(R$string.IDS_fitness_action_330B));
        this.c.put("331B", Integer.valueOf(R$string.IDS_fitness_action_331B));
        this.c.put("059B", Integer.valueOf(R$string.IDS_fitness_action_059B));
        this.c.put("060B", Integer.valueOf(R$string.IDS_fitness_action_060B));
        this.c.put("318B", Integer.valueOf(R$string.IDS_fitness_action_318B));
        this.c.put("319B", Integer.valueOf(R$string.IDS_fitness_action_319B));
        this.c.put("031B", Integer.valueOf(R$string.IDS_fitness_action_031B));
        this.c.put("022B", Integer.valueOf(R$string.IDS_fitness_action_022B));
        this.c.put("048B", Integer.valueOf(R$string.IDS_fitness_action_048B));
        this.c.put("062B", Integer.valueOf(R$string.IDS_fitness_action_062B));
        this.c.put("061B", Integer.valueOf(R$string.IDS_fitness_action_061B));
        this.c.put("039B", Integer.valueOf(R$string.IDS_fitness_action_039B));
        this.c.put("049B", Integer.valueOf(R$string.IDS_fitness_action_049B));
        this.c.put("050B", Integer.valueOf(R$string.IDS_fitness_action_050B));
        this.c.put("057B", Integer.valueOf(R$string.IDS_fitness_action_057B));
        this.c.put("058B", Integer.valueOf(R$string.IDS_fitness_action_058B));
    }

    private void b() {
        this.c.put("RD001", Integer.valueOf(R$string.IDS_rate_zone_warmup_threshold));
        this.c.put("RD002", Integer.valueOf(R$string.IDS_settings_one_level_menu_settings_item_text_id5));
        this.c.put("RD003", Integer.valueOf(R$string.IDS_sug_ai_run_plan_walk));
        this.c.put("RD004", Integer.valueOf(R$string.IDS_sug_ai_run_plan_rest));
        this.c.put("RD005", Integer.valueOf(R$string.IDS_sug_ai_run_plan_relax));
        this.c.put("RD006", Integer.valueOf(R$string.IDS_sug_ai_run_plan_walk_slowly));
        this.c.put("RD007", Integer.valueOf(R$string.IDS_sug_ai_run_plan_quick_walk));
        this.c.put("RD008", Integer.valueOf(R$string.IDS_sug_ai_run_plan_jogging));
        this.c.put("RD009", Integer.valueOf(R$string.IDS_sug_ai_run_plan_medium_run));
        this.c.put("RD010", Integer.valueOf(R$string.IDS_sug_ai_run_plan_quick_run));
        this.c.put("RD011", Integer.valueOf(R$string.IDS_sug_ai_run_plan_easy_walk));
        this.c.put("RD012", Integer.valueOf(R$string.IDS_sug_ai_run_plan_sprint_run));
        this.c.put("RD013", Integer.valueOf(R$string.IDS_sug_cardio_basic_run));
        this.c.put("RD014", Integer.valueOf(R$string.IDS_sug_aerobic_adveanced_run));
        this.c.put("RD015", Integer.valueOf(R$string.IDS_sug_lactate_threshold_run));
        this.c.put("RD016", Integer.valueOf(R$string.IDS_sug_anaerobic_endurance_run));
        this.c.put("RD017", Integer.valueOf(R$string.IDS_sug_high_intensity_run));
        this.c.put("RD018", Integer.valueOf(R$string.IDS_sug_trot_run));
    }

    private void c() {
        this.c.put("001B", Integer.valueOf(R$string.IDS_fitness_action_001B));
        this.c.put("003B", Integer.valueOf(R$string.IDS_fitness_action_003B));
        this.c.put("007B", Integer.valueOf(R$string.IDS_fitness_action_007B));
        this.c.put("012B", Integer.valueOf(R$string.IDS_fitness_action_012B));
        this.c.put("015B", Integer.valueOf(R$string.IDS_fitness_action_015B));
        this.c.put("018B", Integer.valueOf(R$string.IDS_fitness_action_018B));
        this.c.put("023B", Integer.valueOf(R$string.IDS_fitness_action_023B));
        this.c.put("026B", Integer.valueOf(R$string.IDS_fitness_action_026B));
        this.c.put("028B", Integer.valueOf(R$string.IDS_fitness_action_028B));
        this.c.put("034B", Integer.valueOf(R$string.IDS_fitness_action_034B));
        this.c.put("040B", Integer.valueOf(R$string.IDS_fitness_action_040B));
        this.c.put("231B", Integer.valueOf(R$string.IDS_fitness_action_231B));
        this.c.put("234B", Integer.valueOf(R$string.IDS_fitness_action_234B));
        this.c.put("217B", Integer.valueOf(R$string.IDS_fitness_action_217B));
        this.c.put("198B", Integer.valueOf(R$string.IDS_fitness_action_198B));
        this.c.put("201B", Integer.valueOf(R$string.IDS_fitness_action_201B));
        this.c.put("199B", Integer.valueOf(R$string.IDS_fitness_action_199B));
        this.c.put("207B", Integer.valueOf(R$string.IDS_fitness_action_207B));
        this.c.put("213B", Integer.valueOf(R$string.IDS_fitness_action_213B));
        this.c.put("171B", Integer.valueOf(R$string.IDS_fitness_action_171B));
        this.c.put("172B", Integer.valueOf(R$string.IDS_fitness_action_172B));
        this.c.put("175B", Integer.valueOf(R$string.IDS_fitness_action_175B));
        this.c.put("193B", Integer.valueOf(R$string.IDS_fitness_action_193B));
        this.c.put("176B", Integer.valueOf(R$string.IDS_fitness_action_176B));
        this.c.put("190B", Integer.valueOf(R$string.IDS_fitness_action_190B));
        this.c.put("197B", Integer.valueOf(R$string.IDS_fitness_action_197B));
        this.c.put("146B", Integer.valueOf(R$string.IDS_fitness_action_146B));
        this.c.put("152B", Integer.valueOf(R$string.IDS_fitness_action_152B));
        this.c.put("153B", Integer.valueOf(R$string.IDS_fitness_action_153B));
        this.c.put("140B", Integer.valueOf(R$string.IDS_fitness_action_140B));
        this.c.put("141B", Integer.valueOf(R$string.IDS_fitness_action_141B));
        this.c.put("137B", Integer.valueOf(R$string.IDS_fitness_action_137B));
        this.c.put("136B", Integer.valueOf(R$string.IDS_fitness_action_136B));
        this.c.put("129B", Integer.valueOf(R$string.IDS_fitness_action_129B));
        this.c.put("288B", Integer.valueOf(R$string.IDS_fitness_action_288B));
        this.c.put("289B", Integer.valueOf(R$string.IDS_fitness_action_289B));
        this.c.put("290B", Integer.valueOf(R$string.IDS_fitness_action_290B));
        this.c.put("291B", Integer.valueOf(R$string.IDS_fitness_action_291B));
        this.c.put("292B", Integer.valueOf(R$string.IDS_fitness_action_292B));
        this.c.put("055B", Integer.valueOf(R$string.IDS_fitness_action_055B));
        this.c.put("056B", Integer.valueOf(R$string.IDS_fitness_action_056B));
        this.c.put("041B", Integer.valueOf(R$string.IDS_fitness_action_041B));
        this.c.put("226B", Integer.valueOf(R$string.IDS_fitness_action_226B));
        this.c.put("013B", Integer.valueOf(R$string.IDS_fitness_action_013B));
    }

    public int e(String str) {
        if (TextUtils.isEmpty(str) || !this.c.containsKey(str)) {
            LogUtil.h("ClassAndActionName", "getName id is empty");
            return 0;
        }
        return this.c.get(str).intValue();
    }
}
