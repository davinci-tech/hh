package com.huawei.ui.main.stories.me.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.api.SyncApi;
import com.huawei.hihealth.motion.HealthOpenSDK;
import com.huawei.hihealth.motion.IFlushResult;
import com.huawei.hihealth.util.HealthSyncUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.downloadwidget.HealthDownLoadWidget;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.main.stories.me.util.AppSettingUtil;
import defpackage.caq;
import defpackage.guz;
import defpackage.ixx;
import defpackage.nrh;
import defpackage.nsn;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class AppSettingUtil {

    /* renamed from: a, reason: collision with root package name */
    private final WeakReference<Context> f10371a;
    private Context b;
    private CustomPermissionAction c;

    public AppSettingUtil(Context context) {
        this.b = context;
        this.f10371a = new WeakReference<>(context);
    }

    private void b(boolean z) {
        guz guzVar = new guz();
        Intent intent = new Intent("com.huawei.health.track.config");
        intent.setPackage("com.huawei.health");
        intent.setClassName("com.huawei.health", "com.huawei.health.manager.DaemonService");
        intent.putExtra("autotrack_enable", z);
        intent.putExtra("stop_delay", guzVar.a());
        intent.putExtra("start_delay", guzVar.d());
        Context context = this.b;
        if (context != null) {
            context.startService(intent);
        }
    }

    public void c(HealthOpenSDK healthOpenSDK, HealthDownLoadWidget healthDownLoadWidget, String str) {
        if (healthOpenSDK == null) {
            LogUtil.h("AppSettingUtil", "syncData healthOpenSdk is null");
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            nrh.d(this.b, str);
        }
        try {
            HealthSyncUtil.b(SyncApi.FITNESS);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("AppSettingUtil", "FitnessAdvice syncData failed");
        }
        try {
            HealthSyncUtil.b(SyncApi.HEALTH_LIFE);
        } catch (IllegalArgumentException unused2) {
            LogUtil.b("AppSettingUtil", "HealthLifeModel syncData failed");
        }
        if (healthDownLoadWidget != null) {
            healthDownLoadWidget.setProgress(0);
            healthDownLoadWidget.setVisibility(0);
        }
        healthOpenSDK.a(new IFlushResult() { // from class: com.huawei.ui.main.stories.me.util.AppSettingUtil.3
            @Override // com.huawei.hihealth.motion.IFlushResult
            public void onSuccess(Bundle bundle) {
                AppSettingUtil.this.d();
            }

            @Override // com.huawei.hihealth.motion.IFlushResult
            public void onFailed(Bundle bundle) {
                AppSettingUtil.this.d();
            }

            @Override // com.huawei.hihealth.motion.IFlushResult
            public void onServiceException(Bundle bundle) {
                AppSettingUtil.this.d();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(20000);
        hiSyncOption.setSyncScope(1);
        hiSyncOption.setSyncMethod(2);
        hiSyncOption.setSyncManual(1);
        HiHealthNativeApi.a(this.b).synCloud(hiSyncOption, null);
    }

    public void c(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        if (str2 != null) {
            hashMap.put("type", str2);
        }
        ixx.d().d(this.b, str, hashMap, 0);
    }

    public boolean a(String str) {
        return "".equals(str) || str == null || "0".equals(str);
    }

    public void e(boolean z, guz guzVar, HealthSwitchButton healthSwitchButton) {
        if (healthSwitchButton == null) {
            LogUtil.b("AppSettingUtil", "autoTrackSwitch is null");
        } else if (guzVar != null) {
            if (z) {
                b(guzVar, healthSwitchButton);
            } else {
                a(guzVar, healthSwitchButton, false);
            }
        }
    }

    public void b(boolean z, guz guzVar) {
        if (guzVar == null) {
            LogUtil.h("AppSettingUtil", "permissionSetConfigFile autoTrackConfig is null");
            return;
        }
        c(AnalyticsValue.HEALTH_MINE_SETTINGS_AUTO_TRACK_2040031.value(), z ? "1" : "2");
        guzVar.e(this.b, z);
        guzVar.a(this.b);
        b(z);
    }

    /* renamed from: com.huawei.ui.main.stories.me.util.AppSettingUtil$5, reason: invalid class name */
    public class AnonymousClass5 extends CustomPermissionAction {
        final /* synthetic */ guz b;
        final /* synthetic */ HealthSwitchButton c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass5(Context context, guz guzVar, HealthSwitchButton healthSwitchButton) {
            super(context);
            this.b = guzVar;
            this.c = healthSwitchButton;
        }

        @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onGranted() {
            LogUtil.a("AppSettingUtil", "Granted Permission");
            if (!AppSettingUtil.this.e()) {
                AppSettingUtil.this.a(this.b, this.c, true);
            } else {
                PermissionUtil.b(AppSettingUtil.this.b(), PermissionUtil.PermissionType.LOCATION_WITH_BACKGROUND, AppSettingUtil.this.c);
            }
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onDenied(String str) {
            super.onDenied(str);
            LogUtil.h("AppSettingUtil", "permission denied by the user");
            if (!PermissionUtil.d() || !caq.c(AppSettingUtil.this.b())) {
                AppSettingUtil.this.a(this.b, this.c, PermissionUtil.j() && caq.c(AppSettingUtil.this.b()));
            } else {
                AppSettingUtil.this.a(this.b, this.c);
            }
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
            LogUtil.h("AppSettingUtil", "permission forever denied, show the guide window");
            if (permissionType == PermissionUtil.PermissionType.LOCATION || permissionType == PermissionUtil.PermissionType.LOCATION_WITH_BACKGROUND) {
                Context context = AppSettingUtil.this.b;
                final guz guzVar = this.b;
                final HealthSwitchButton healthSwitchButton = this.c;
                View.OnClickListener onClickListener = new View.OnClickListener() { // from class: rhp
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        AppSettingUtil.AnonymousClass5.this.dOs_(guzVar, healthSwitchButton, view);
                    }
                };
                final guz guzVar2 = this.b;
                final HealthSwitchButton healthSwitchButton2 = this.c;
                nsn.cLJ_(context, permissionType, onClickListener, new View.OnClickListener() { // from class: rhl
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        AppSettingUtil.AnonymousClass5.this.dOt_(guzVar2, healthSwitchButton2, view);
                    }
                });
            }
        }

        public /* synthetic */ void dOs_(guz guzVar, HealthSwitchButton healthSwitchButton, View view) {
            LogUtil.a("AppSettingUtil", "onForeverDenied on click confirm");
            AppSettingUtil.this.a(guzVar, healthSwitchButton, false);
            ViewClickInstrumentation.clickOnView(view);
        }

        public /* synthetic */ void dOt_(guz guzVar, HealthSwitchButton healthSwitchButton, View view) {
            LogUtil.a("AppSettingUtil", "onForeverDenied on click cancel");
            AppSettingUtil.this.a(guzVar, healthSwitchButton, false);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void b(guz guzVar, HealthSwitchButton healthSwitchButton) {
        if (this.c == null) {
            this.c = new AnonymousClass5(b(), guzVar, healthSwitchButton);
        }
        d(guzVar, healthSwitchButton);
    }

    private void d(guz guzVar, HealthSwitchButton healthSwitchButton) {
        if (PermissionUtil.j() && caq.c(b())) {
            a(guzVar, healthSwitchButton, true);
        } else if (PermissionUtil.d() && caq.c(b())) {
            a(guzVar, healthSwitchButton);
        } else {
            PermissionUtil.b(b(), PermissionUtil.PermissionType.LOCATION, this.c);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(guz guzVar, HealthSwitchButton healthSwitchButton, boolean z) {
        b(z, guzVar);
        healthSwitchButton.setChecked(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final guz guzVar, final HealthSwitchButton healthSwitchButton) {
        LogUtil.a("AppSettingUtil", "enter showLocationTips show dialog");
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.b).e(this.b.getString(R.string._2130843342_res_0x7f0216ce)).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: rhk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AppSettingUtil.this.dOq_(guzVar, healthSwitchButton, view);
            }
        }).czC_(R.string._2130842176_res_0x7f021240, new View.OnClickListener() { // from class: rhj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AppSettingUtil.this.dOr_(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    public /* synthetic */ void dOq_(guz guzVar, HealthSwitchButton healthSwitchButton, View view) {
        a(guzVar, healthSwitchButton, false);
        LogUtil.a("AppSettingUtil", "showLocationTips onClick cancel");
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dOr_(View view) {
        LogUtil.a("AppSettingUtil", "showLocationTips onClick confirm");
        PermissionUtil.b(b(), PermissionUtil.PermissionType.LOCATION, this.c);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e() {
        LogUtil.a("AppSettingUtil", "enter isApplyBackgroundLocationPermission");
        return (PermissionUtil.j() || PermissionUtil.e(b(), PermissionUtil.PermissionType.LOCATION_WITH_BACKGROUND) == PermissionUtil.PermissionResult.GRANTED) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Context b() {
        Context context = this.f10371a.get();
        return context == null ? BaseApplication.getContext() : context;
    }
}
