package defpackage;

import com.huawei.health.R;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.model.HiHealthDictType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealthservice.postprocessing.HiPostProcessAction;
import com.huawei.hihealthservice.postprocessing.HiPostProcessor;
import com.huawei.hwcommonmodel.application.BaseApplication;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public class irs implements HiPostProcessor {
    private static final Object e = new Object();
    private final List<HiPostProcessAction> c;

    private irs() {
        this.c = new CopyOnWriteArrayList();
        d();
    }

    private void d() {
        d("BloodPressure", R.string.IDS_blood_pressure_sync_title, R.string.IDS_blood_pressure_sync_notify, "messagecenter://BloodPressure?skipType=1&destination=com.huawei.ui.main.stories.health.activity.healthdata.KnitBloodPressureActivity", 10002);
        d("VascularHealth", -1, R.string.IDS_vascular_health_sync_notify, "huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.vascular-health?h5pro=true&urlType=4&from=7", DicDataTypeUtil.DataType.VASCULAR_HEALTH.value());
        d("HiHealthProcessor_PPG", R.string.IDS_ppg_sync_title, -1, "huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.ppg?h5pro=true&urlType=4&pName=com.huawei.health&dst=result&from=7", DicDataTypeUtil.DataType.ARRHYTHMIA_RESULT_TYPE.value());
        a("HiHealthProcessor_MedicalExamReport", R.string.IDS_medical_exam_report_generated_title, R.string.IDS_medical_exam_report_generated_content, "huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.health-record?h5pro=true&isNeedLogin=true&urlType=4&pName=com.huawei.health&path=micro_report_detail&from=2", DicDataTypeUtil.DataType.MEDICAL_EXAM_REPORT.value(), true);
    }

    private void d(String str, int i, int i2, String str2, int i3) {
        a(str, i, i2, str2, i3, false);
    }

    private void a(String str, int i, int i2, String str2, int i3, boolean z) {
        irv irvVar = new irv(5L, TimeUnit.MINUTES);
        irvVar.setModule("HiHealthService");
        irvVar.setType(str);
        irvVar.c(i);
        irvVar.e(i2);
        irvVar.a(str2);
        irvVar.setPosition(2);
        irvVar.setGroupSummary(z);
        irw irwVar = new irw();
        irwVar.c(Integer.valueOf(i3), irvVar);
        this.c.add(irwVar);
    }

    static class b {
        private static final irs b = new irs();
    }

    public static irs c() {
        return b.b;
    }

    @Override // com.huawei.hihealthservice.postprocessing.HiPostProcessor
    public boolean process(HiHealthData hiHealthData) {
        HiHealthDictType f;
        int i;
        ArrayList<HiPostProcessAction> arrayList = new ArrayList(this.c);
        HiHealthDataType.Category e2 = HiHealthDataType.e(hiHealthData.getType());
        if (e2 == HiHealthDataType.Category.SEQUENCE) {
            i = hiHealthData.getType();
        } else {
            if (e2 != HiHealthDataType.Category.POINT || (f = HiHealthDictManager.d(BaseApplication.getContext()).f(hiHealthData.getType())) == null) {
                return false;
            }
            i = f.i();
        }
        if (i == DicDataTypeUtil.DataType.VASCULAR_HEALTH.value() && !Utils.o()) {
            ReleaseLogUtil.e("HiH_HiDefaultPostProcessor", "VASCULAR_HEALTH not show in china");
            return false;
        }
        for (HiPostProcessAction hiPostProcessAction : arrayList) {
            if (hiPostProcessAction.getDataTypes().contains(Integer.valueOf(i)) && hiPostProcessAction.beforePostProcessAction(hiHealthData, i) && !hiPostProcessAction.postProcessAction(hiHealthData, i)) {
                return false;
            }
        }
        return true;
    }
}
