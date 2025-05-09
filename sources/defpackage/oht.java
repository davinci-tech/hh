package defpackage;

import android.content.Context;
import android.view.View;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class oht {
    public static void e(final Context context, String str) {
        if (context == null) {
            return;
        }
        new CustomTextAlertDialog.Builder(context).b(R.string._2130842089_res_0x7f0211e9).e(str).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: oht.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                oht.b("from", 1, "click_type", 1, AnalyticsValue.HEALTH_HOME_DIALOG_20401108);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyU_(R.string._2130842041_res_0x7f0211b9, new View.OnClickListener() { // from class: oht.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                oht.b("from", 1, "click_type", 2, AnalyticsValue.HEALTH_HOME_DIALOG_20401108);
                nsn.ak(context);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a().show();
    }

    public static void b(String str, int i, String str2, int i2, AnalyticsValue analyticsValue) {
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put(str, Integer.valueOf(i));
        hashMap.put(str2, Integer.valueOf(i2));
        ixx.d().d(BaseApplication.e(), analyticsValue.value(), hashMap, 0);
    }
}
