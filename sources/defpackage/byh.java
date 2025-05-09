package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.health.MainActivityHandlerMsg;
import com.huawei.health.R;
import com.huawei.hiai.awareness.AwarenessConstants;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import health.compact.a.CommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.LogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.utils.StringUtils;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes3.dex */
public class byh {
    public static void BK_(Context context, Handler handler) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (CommonUtil.cg()) {
            LogUtil.c("PreLauncherDialogHelper", "isTestThirdDeviceVersion return true, showing dialog...");
            BL_(context, CommonUtil.al(), CommonUtil.af(), handler);
        } else if (sbc.c()) {
            LogUtil.c("PreLauncherDialogHelper", "BetaVersion&ChinaRom detected, showing dialog...");
            BL_(context, CommonUtil.b(), CommonUtil.e(), handler);
        } else if (sbc.f() && sbc.j()) {
            LogUtil.c("PreLauncherDialogHelper", "preLaunchCheck isBlockedBetaSubmission = false, isNotAgreeBetaVersion = true");
            handler.sendEmptyMessage(Constants.ON_PAGE_STARTED);
        } else {
            LogUtil.c("PreLauncherDialogHelper", "common version, no dialog, keep going...");
            handler.sendEmptyMessage(MainActivityHandlerMsg.AFTER_LAUNCH_CHECK);
        }
        LogUtil.c("PreLauncherDialogHelper", "preLaunchCheck finished, time cost:", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private static void BL_(Context context, long j, long j2, Handler handler) {
        long currentTimeMillis = System.currentTimeMillis();
        if (j <= currentTimeMillis && currentTimeMillis <= j2) {
            if (sbc.e()) {
                BG_((Activity) context, handler);
                return;
            }
            if (b(context)) {
                handler.sendEmptyMessage(MainActivityHandlerMsg.CHECK_BETA_AGREEMENTS_UPDATE);
            }
            handler.sendEmptyMessage(MainActivityHandlerMsg.AFTER_LAUNCH_CHECK);
            return;
        }
        if (StringUtils.g(SharedPreferenceManager.b(context, Integer.toString(PrebakedEffectId.RT_ASSAULT_RIFLE), HiDateUtil.c("yyyyMMdd")))) {
            BH_((Activity) context, handler);
        } else {
            handler.sendEmptyMessage(MainActivityHandlerMsg.AFTER_LAUNCH_CHECK);
        }
    }

    private static boolean b(Context context) {
        boolean aa = CommonUtil.aa(context);
        boolean c = sbc.c();
        boolean isLogined = LoginInit.getInstance(context).getIsLogined();
        boolean d = d();
        LogUtil.c("PreLauncherDialogHelper", "isCheckBetaAgrUpdate isNetworkConnected:", Boolean.valueOf(aa), " isChinaBeta:", Boolean.valueOf(c), " isLogin:", Boolean.valueOf(isLogined), " isSignBetaPrivacy:", Boolean.valueOf(d));
        return aa && c && isLogined && d;
    }

    public static boolean d() {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "beta_agr_if_agree_authorize");
        LogUtil.c("PreLauncherDialogHelper", "isSignBetaPrivacy betaSign:", b);
        return "1".equals(b);
    }

    public static void BG_(final Activity activity, final Handler handler) {
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        View inflate = View.inflate(activity, R.layout.services_custom_view_beta_dialog, null);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String format = simpleDateFormat.format(new Date(CommonUtil.b()));
        String format2 = simpleDateFormat.format(new Date(CommonUtil.e()));
        if (CommonUtil.cg()) {
            format2 = simpleDateFormat.format(new Date(CommonUtil.af()));
        }
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.hw_health_service_item_two);
        HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.hw_health_service_item_three);
        HealthTextView healthTextView3 = (HealthTextView) inflate.findViewById(R.id.hw_health_service_item_four);
        healthTextView.setText(activity.getString(R.string._2130849068_res_0x7f022d2c) + ":" + format + AwarenessConstants.SECOND_ACTION_SPLITE_TAG + format2);
        healthTextView2.setText(activity.getString(R.string._2130849070_res_0x7f022d2e));
        healthTextView3.setText(activity.getString(R.string._2130849069_res_0x7f022d2d));
        if (nsn.r()) {
            healthTextView.setTextSize(1, 26.0f);
            healthTextView2.setTextSize(1, 26.0f);
            healthTextView3.setTextSize(1, 26.0f);
        }
        CustomViewDialog e = new CustomViewDialog.Builder(activity).a(activity.getString(R.string._2130850007_res_0x7f0230d7)).czg_(inflate).czd_(activity.getString(R.string._2130837555_res_0x7f020033), new View.OnClickListener() { // from class: bye
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                byh.BI_(activity, view);
            }
        }).czf_(activity.getString(R.string._2130839489_res_0x7f0207c1), new View.OnClickListener() { // from class: byi
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                byh.BJ_(handler, activity, view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    static /* synthetic */ void BI_(Activity activity, View view) {
        Intent intent = new Intent();
        intent.setAction("com.huawei.commonui.CLEAN_ACTIVITY");
        LocalBroadcastManager.getInstance(activity).sendBroadcast(intent);
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void BJ_(Handler handler, Activity activity, View view) {
        if (sbc.c()) {
            handler.sendEmptyMessage(Constants.ON_PAGE_STARTED);
        } else {
            SharedPreferenceManager.e(activity, Integer.toString(10000), "health_beta_user_agree", Integer.toString(1), new StorageParams());
            handler.sendEmptyMessage(MainActivityHandlerMsg.AFTER_LAUNCH_CHECK);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private static void BH_(final Activity activity, final Handler handler) {
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        View inflate = View.inflate(activity, R.layout.services_custom_view_beta_dialog, null);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.hw_health_service_item_image_two);
        ((HealthTextView) inflate.findViewById(R.id.hw_health_service_item_two)).setText(activity.getString(R.string._2130849067_res_0x7f022d2b));
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.hw_health_services_three_layout);
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.hw_health_services_four_layout);
        imageView.setVisibility(8);
        linearLayout.setVisibility(8);
        linearLayout2.setVisibility(8);
        CustomViewDialog e = new CustomViewDialog.Builder(activity).a(activity.getString(R.string._2130850007_res_0x7f0230d7)).czg_(inflate).czf_(activity.getString(R.string._2130841492_res_0x7f020f94), new View.OnClickListener() { // from class: byh.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("com.huawei.commonui.CLEAN_ACTIVITY");
                LocalBroadcastManager.getInstance(activity).sendBroadcast(intent);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czd_(activity.getString(R.string._2130843112_res_0x7f0215e8), new View.OnClickListener() { // from class: byh.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String c = HiDateUtil.c("yyyyMMdd");
                SharedPreferenceManager.e(activity, Integer.toString(PrebakedEffectId.RT_ASSAULT_RIFLE), c, c, new StorageParams());
                handler.sendEmptyMessage(MainActivityHandlerMsg.AFTER_LAUNCH_CHECK);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }
}
