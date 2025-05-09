package com.huawei.health.sport.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.HealthButtonBarLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ixx;
import health.compact.a.Utils;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class RunPopularRoutesUtil {

    public interface DialogCallBack {
        void goNext();

        void notGoNext();
    }

    public static void a(Context context) {
        context.getSharedPreferences("popularRoutes_sharedpreference_msg", 0).edit().putBoolean("enter_popular_routes", true).commit();
    }

    public static boolean b(Context context) {
        return context.getSharedPreferences("popularRoutes_sharedpreference_msg", 0).getBoolean("enter_popular_routes", false);
    }

    public static void e(final Context context, final int i, final DialogCallBack dialogCallBack) {
        if (b(context)) {
            dialogCallBack.goNext();
            return;
        }
        LogUtil.a("RunPopularRoutesUtil", "showFirstPopularRoutesDialog enter");
        CustomAlertDialog c = new CustomAlertDialog.Builder(context).cyp_(View.inflate(context, R.layout.dialog_first_popular_routes_tip, null)).cyn_(R$string.IDS_device_release_user_profile_log_collect_cancel, new DialogInterface.OnClickListener() { // from class: com.huawei.health.sport.utils.RunPopularRoutesUtil.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                LogUtil.a("RunPopularRoutesUtil", "showFirstPopularRoutesDialog click cancel");
                DialogCallBack.this.notGoNext();
                RunPopularRoutesUtil.e(1, i);
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i2);
            }
        }).cyo_(R$string.IDS_device_release_user_profile_log_collect_agree, new DialogInterface.OnClickListener() { // from class: com.huawei.health.sport.utils.RunPopularRoutesUtil.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                LogUtil.a("RunPopularRoutesUtil", "showFirstPopularRoutesDialog click agree");
                RunPopularRoutesUtil.a(context);
                dialogCallBack.goNext();
                RunPopularRoutesUtil.e(2, i);
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i2);
            }
        }).c();
        HealthTextView healthTextView = (HealthTextView) c.findViewById(R.id.second);
        HealthButton healthButton = (HealthButton) c.findViewById(R.id.dialog_btn_positive);
        if (!Utils.o()) {
            healthButton.setTextColor(context.getResources().getColor(R.color._2131298273_res_0x7f0907e1, null));
            healthButton.setBackgroundResource(R.drawable.button_background_emphasize);
        } else {
            healthTextView.setText(context.getString(R$string.IDS_hw_routes_dialog_permission_knowledge_string_overseas));
        }
        ((HealthButtonBarLayout) c.findViewById(R.id.button_bar)).setDividerDrawable(context.getResources().getDrawable(R.drawable._2131427926_res_0x7f0b0256, null));
        c.setCancelable(false);
        if (c.isShowing()) {
            return;
        }
        e(0, i);
        c.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(int i, int i2) {
        if (i2 < 0) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("from", Integer.valueOf(i2));
        ixx.d().d(BaseApplication.e(), "1040078", hashMap, 0);
    }
}
