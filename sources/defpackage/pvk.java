package defpackage;

import android.content.Context;
import android.os.Bundle;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class pvk {
    public static void a(float f, long j) {
        double c = pvm.c(f) / 1000.0d;
        double d = pvm.d(f);
        LogUtil.a("SCUI_StepShareUtil", "averSteps", Float.valueOf(f), " calorie", Double.valueOf(c), " distance", Double.valueOf(d));
        HashMap hashMap = new HashMap();
        Context e = BaseApplication.e();
        hashMap.put("step", UnitUtil.e(f, 1, 0));
        hashMap.put("step_unit", e.getResources().getString(R$string.IDS_settings_steps_unit));
        hashMap.put("step_title", e.getResources().getString(R$string.IDS_settings_steps));
        hashMap.put("calorie", UnitUtil.e(c, 1, 0));
        hashMap.put("calorie_unit", e.getResources().getString(R$string.IDS_band_data_sport_energy_unit));
        hashMap.put("calorie_title", e.getResources().getString(R$string.IDS_active_caloric));
        hashMap.put("distance_title", e.getResources().getString(R$string.IDS_sport_distance));
        hashMap.put("sport_data", UnitUtil.a("MM/dd", j));
        if (UnitUtil.h()) {
            hashMap.put("distance_unit", e.getResources().getString(R$string.IDS_band_data_sport_distance_unit_en));
            hashMap.put("distance", UnitUtil.e(UnitUtil.e(d / 1000.0d, 3), 1, 2));
        } else {
            hashMap.put("distance_unit", e.getResources().getString(R$string.IDS_motiontrack_show_sport_unit_km));
            hashMap.put("distance", UnitUtil.e(d / 1000.0d, 1, 2));
        }
        feh fehVar = new feh();
        fehVar.d(hashMap);
        ArrayList arrayList = new ArrayList(1);
        feb febVar = new feb();
        febVar.d(999);
        febVar.e(R.drawable.share_geometry_1);
        arrayList.add(febVar);
        Bundle bundle = new Bundle();
        bundle.putSerializable("shareWaterMarkData", fehVar);
        bundle.putSerializable("waterMarkIds", arrayList);
        bundle.putInt("downLoadId", 257);
        AppRouter.b("/PluginSocialShare/CustomizeShareActivity").zF_(bundle).c(e);
    }
}
