package defpackage;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import com.huawei.haf.common.security.SecurityConstant;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StepCounterSupportUtils;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class gno {
    public static String[] aPG_(String[] strArr, final Activity activity) {
        ArrayList arrayList = new ArrayList(Arrays.asList(strArr));
        if (!Utils.o() && arrayList.contains("android.permission.ACTIVITY_RECOGNITION")) {
            LogUtil.a("Login_ActivityRecognitionRequestDialogUtils", "permissionList  size ", Integer.valueOf(arrayList.size()));
            arrayList.remove("android.permission.ACTIVITY_RECOGNITION");
            strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
            PermissionUtil.PermissionResult b = PermissionUtil.b(activity, new String[]{"android.permission.ACTIVITY_RECOGNITION"});
            if (PermissionUtil.PermissionResult.FOREVER_DENIED.equals(b)) {
                ReleaseLogUtil.d("Login_ActivityRecognitionRequestDialogUtils", "ACTIVITY_RECOGNITION is FOREVER_DENIED");
                e();
                return strArr;
            }
            if (PermissionUtil.PermissionResult.GRANTED.equals(b)) {
                LogUtil.a("Login_ActivityRecognitionRequestDialogUtils", "ACTIVITY_RECOGNITION is GRANTED");
                return strArr;
            }
            HandlerExecutor.e(new Runnable() { // from class: gnq
                @Override // java.lang.Runnable
                public final void run() {
                    gno.aPF_(activity);
                }
            });
        }
        return strArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void aPF_(Activity activity) {
        BaseDialog a2;
        if (StepCounterSupportUtils.e(BaseApplication.getContext())) {
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(activity);
            builder.e(R.string._2130846366_res_0x7f02229e).czC_(R.string._2130846701_res_0x7f0223ed, aPL_(activity)).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: gnw
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    gno.aPH_(view);
                }
            });
            a2 = builder.e();
        } else {
            CustomTextAlertDialog.Builder builder2 = new CustomTextAlertDialog.Builder(activity);
            builder2.b(R.string._2130846699_res_0x7f0223eb).d(R.string._2130846700_res_0x7f0223ec).cyU_(R.string._2130846701_res_0x7f0223ed, aPL_(activity)).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: gnu
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    gno.aPI_(view);
                }
            });
            a2 = builder2.a();
        }
        a2.setCancelable(false);
        a2.show();
    }

    static /* synthetic */ void aPH_(View view) {
        e();
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void aPI_(View view) {
        e();
        ViewClickInstrumentation.clickOnView(view);
    }

    private static View.OnClickListener aPL_(final Activity activity) {
        return new View.OnClickListener() { // from class: gnt
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                gno.aPK_(activity, view);
            }
        };
    }

    static /* synthetic */ void aPK_(final Activity activity, View view) {
        jdi.bFL_(activity, new String[]{"android.permission.ACTIVITY_RECOGNITION"}, new PermissionsResultAction() { // from class: gno.3
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                LogUtil.a("Login_ActivityRecognitionRequestDialogUtils", "ACTIVITY_RECOGNITION onGranted");
                Intent intent = new Intent();
                intent.setPackage(BaseApplication.getAppPackage());
                intent.setAction("com.huawei.health.start_step_counter");
                activity.sendBroadcast(intent, SecurityConstant.d);
                gno.a(2);
            }

            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onDenied(String str) {
                LogUtil.a("Login_ActivityRecognitionRequestDialogUtils", "ACTIVITY_RECOGNITION onDenied");
                gno.e();
            }
        });
        ViewClickInstrumentation.clickOnView(view);
    }

    public static void e() {
        SharedPreferenceManager.e(Integer.toString(10000), "HAS_APPLY_ACTIVITY_RECOGNITION", System.currentTimeMillis());
        a(1);
    }

    public static void a(int i) {
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put("from", 2);
        hashMap.put("click_type", Integer.valueOf(i));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_HOME_DIALOG_20401108.value(), hashMap, 0);
    }
}
