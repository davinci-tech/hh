package defpackage;

import android.content.Context;
import android.content.res.Resources;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import health.compact.a.UnitUtil;

/* loaded from: classes7.dex */
public class qrd {
    private static Resources dHC_() {
        Context context = BaseApplication.getContext();
        if (context == null) {
            LogUtil.h("HealthBodyCompositionKnowledge", "getResources Context is null");
            return null;
        }
        return context.getResources();
    }

    public static String i(int i) {
        Resources dHC_ = dHC_();
        if (dHC_ == null) {
            LogUtil.h("HealthBodyCompositionKnowledge", "getBodyWeight Resources is null");
            return "";
        }
        if (i == 0) {
            return dHC_.getString(R$string.IDS_hw_health_show_healthdata_weight);
        }
        if (i != 1) {
            return "";
        }
        return dHC_.getString(R$string.IDS_knowledge_definition_body_weight) + System.lineSeparator() + dHC_.getString(R$string.IDS_hw_ideal_weight_des);
    }

    public static String b(int i) {
        Resources dHC_ = dHC_();
        if (dHC_ == null) {
            LogUtil.h("HealthBodyCompositionKnowledge", "getBodyMassIndex Resources is null");
            return "";
        }
        if (i == 0) {
            return dHC_.getString(R$string.IDS_hw_show_BMI);
        }
        if (i != 1) {
            return "";
        }
        if (UnitUtil.h()) {
            return String.format(dHC_.getString(R$string.IDS_hw_weight_bmi_des_imp), 703);
        }
        return dHC_.getString(R$string.IDS_knowledge_definition_body_mass_index);
    }

    public static String j(int i) {
        Resources dHC_ = dHC_();
        if (dHC_ == null) {
            LogUtil.h("HealthBodyCompositionKnowledge", "getFatRate Resources is null");
            return "";
        }
        if (i != 0) {
            return i != 1 ? "" : dHC_.getString(R$string.IDS_knowledge_definition_fat_rate);
        }
        return dHC_.getString(R$string.IDS_hw_health_show_healthdata_bodyfat_rate);
    }

    public static String g(int i) {
        Resources dHC_ = dHC_();
        if (dHC_ == null) {
            LogUtil.h("HealthBodyCompositionKnowledge", "getFatFree Resources is null");
            return "";
        }
        if (i != 0) {
            return i != 1 ? "" : dHC_.getString(R$string.IDS_knowledge_definition_fat_free);
        }
        return dHC_.getString(R$string.IDS_weight_fat_free);
    }

    public static String q(int i) {
        Resources dHC_ = dHC_();
        if (dHC_ == null) {
            LogUtil.h("HealthBodyCompositionKnowledge", "getVisceralFatGrade Resources is null");
            return "";
        }
        if (i != 0) {
            return i != 1 ? "" : dHC_.getString(R$string.IDS_knowledge_definition_visceral_fat_grade);
        }
        return dHC_.getString(R$string.IDS_hw_show_haslet);
    }

    public static String k(int i) {
        Resources dHC_ = dHC_();
        if (dHC_ == null) {
            LogUtil.h("HealthBodyCompositionKnowledge", "getMuscleMass Resources is null");
            return "";
        }
        if (i != 0) {
            return i != 1 ? "" : dHC_.getString(R$string.IDS_knowledge_definition_muscle_mass);
        }
        return dHC_.getString(R$string.IDS_hw_show_muscle);
    }

    public static String l(int i) {
        Resources dHC_ = dHC_();
        if (dHC_ == null) {
            LogUtil.h("HealthBodyCompositionKnowledge", "getSkeletalMuscle Resources is null");
            return "";
        }
        if (i != 0) {
            return i != 1 ? "" : dHC_.getString(R$string.IDS_knowledge_definition_skeletal_muscle);
        }
        return dHC_.getString(R$string.IDS_hw_show_skeletal_muscle_mass);
    }

    public static String o(int i) {
        Resources dHC_ = dHC_();
        if (dHC_ == null) {
            LogUtil.h("HealthBodyCompositionKnowledge", "getRelativeAppendicularSkeletalMuscle Resources is null");
            return "";
        }
        if (i != 0) {
            return i != 1 ? "" : String.format(dHC_.getString(R$string.IDS_knowledge_definition_relative_appendicular_skeletal_muscle), UnitUtil.e(7.0d, 1, 1), UnitUtil.e(6.0d, 1, 1));
        }
        return dHC_.getString(R$string.IDS_weight_limb_skeletal_muscle_index);
    }

    public static String s(int i) {
        Resources dHC_ = dHC_();
        if (dHC_ == null) {
            LogUtil.h("HealthBodyCompositionKnowledge", "getTotalBodyWater Resources is null");
            return "";
        }
        if (i != 0) {
            return i != 1 ? "" : dHC_.getString(R$string.IDS_knowledge_definition_total_body_water);
        }
        return dHC_.getString(R$string.IDS_hw_show_water);
    }

    public static String n(int i) {
        Resources dHC_ = dHC_();
        if (dHC_ == null) {
            LogUtil.h("HealthBodyCompositionKnowledge", "getProtein Resources is null");
            return "";
        }
        if (i != 0) {
            return i != 1 ? "" : dHC_.getString(R$string.IDS_knowledge_definition_protein);
        }
        return dHC_.getString(R$string.IDS_hw_show_protein);
    }

    public static String h(int i) {
        Resources dHC_ = dHC_();
        if (dHC_ == null) {
            LogUtil.h("HealthBodyCompositionKnowledge", "getBoneMineralContent Resources is null");
            return "";
        }
        if (i != 0) {
            return i != 1 ? "" : dHC_.getString(R$string.IDS_knowledge_definition_bone_mineral_content);
        }
        return dHC_.getString(R$string.IDS_hw_show_bone);
    }

    public static String a(int i) {
        Resources dHC_ = dHC_();
        if (dHC_ == null) {
            LogUtil.h("HealthBodyCompositionKnowledge", "getBasalMetabolicRate Resources is null");
            return "";
        }
        if (i != 0) {
            return i != 1 ? "" : dHC_.getString(R$string.IDS_knowledge_definition_basal_metabolic_rate);
        }
        return dHC_.getString(R$string.IDS_hw_show_metabolism);
    }

    public static String f(int i) {
        Resources dHC_ = dHC_();
        if (dHC_ == null) {
            LogUtil.h("HealthBodyCompositionKnowledge", "getHeartRate Resources is null");
            return "";
        }
        if (i != 0) {
            return i != 1 ? "" : String.format(dHC_.getString(R$string.IDS_knowledge_definition_heart_rate), 60, 100, 50);
        }
        return dHC_.getString(R$string.IDS_main_watch_heart_rate_string);
    }

    public static String e(int i) {
        Resources dHC_ = dHC_();
        if (dHC_ == null) {
            LogUtil.h("HealthBodyCompositionKnowledge", "getBodyAge Resources is null");
            return "";
        }
        if (i != 0) {
            return i != 1 ? "" : dHC_.getString(R$string.IDS_knowledge_definition_body_age);
        }
        return dHC_.getString(R$string.IDS_hw_show_bodyage);
    }

    public static String c(int i) {
        Resources dHC_ = dHC_();
        if (dHC_ == null) {
            LogUtil.h("HealthBodyCompositionKnowledge", "getBodyType Resources is null");
            return "";
        }
        if (i != 0) {
            return i != 1 ? "" : dHC_.getString(R$string.IDS_knowledge_definition_body_type);
        }
        return dHC_.getString(R$string.IDS_hw_weight_body_type);
    }

    public static String d(int i) {
        Resources dHC_ = dHC_();
        if (dHC_ == null) {
            LogUtil.h("HealthBodyCompositionKnowledge", "getBodyShape Resources is null");
            return "";
        }
        if (i != 0) {
            return i != 1 ? "" : dHC_.getString(R$string.IDS_knowledge_definition_body_shape);
        }
        return dHC_.getString(R$string.IDS_hw_weight_body_shape);
    }

    public static String r(int i) {
        Resources dHC_ = dHC_();
        if (dHC_ == null) {
            LogUtil.h("HealthBodyCompositionKnowledge", "getWaistToHipRatio Resources is null");
            return "";
        }
        if (i != 0) {
            return i != 1 ? "" : String.format(dHC_.getString(R$string.IDS_knowledge_definition_waist_to_hip_ratio), UnitUtil.e(0.9d, 1, 1), UnitUtil.e(0.85d, 1, 2));
        }
        return dHC_.getString(R$string.IDS_weight_spectral_waist_to_hip_ratio);
    }

    public static String m(int i) {
        Resources dHC_ = dHC_();
        if (dHC_ == null) {
            LogUtil.h("HealthBodyCompositionKnowledge", "getPressureIndex Resources is null");
            return "";
        }
        if (i != 0) {
            return i != 1 ? "" : dHC_.getString(R$string.IDS_hw_weight_pressure_des);
        }
        return dHC_.getString(R$string.IDS_hw_show_pressure_index);
    }
}
