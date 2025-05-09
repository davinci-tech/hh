package defpackage;

import android.content.Context;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;

/* loaded from: classes4.dex */
public class hpq {
    public static void c(Context context) {
        if (context == null) {
            LogUtil.h("Track_TrackDetailDialogUtils", "showStepAbnormalDialog mContext is null");
            return;
        }
        LogUtil.a("Track_TrackDetailDialogUtils", "showStepAbnormalDialog");
        new CustomTextAlertDialog.Builder(context).b(R.string.IDS_service_area_notice_title).e(context.getString(R.string._2130840107_res_0x7f020a2b)).cyR_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: hpr
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a().show();
    }
}
