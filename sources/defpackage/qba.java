package defpackage;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import defpackage.qba;
import health.compact.a.GRSManager;
import health.compact.a.Utils;

/* loaded from: classes6.dex */
public class qba {
    public static void d(Context context, final IBaseResponseCallback iBaseResponseCallback) {
        if (context == null || iBaseResponseCallback == null) {
            LogUtil.b("PressureDialog", "showBeginDeviceStressAdjust() parameter is null.");
            return;
        }
        View inflate = View.inflate(context, R.layout.pressure_detect_dialog, null);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.open_pressure_auto_detector_content);
        if (nsn.r()) {
            healthTextView.setTextSize(1, 30.0f);
        }
        healthTextView.setText(context.getString(R$string.IDS_hw_begin_pressure_adjust_dialog_content));
        CustomViewDialog e = new CustomViewDialog.Builder(context).a(context.getString(R$string.IDS_hw_pressure_adjust)).czg_(inflate).czd_(context.getString(R$string.IDS_hw_cancel_begin_pressure_adjust), new View.OnClickListener() { // from class: qba.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                IBaseResponseCallback.this.d(100001, null);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czf_(context.getString(R$string.IDS_hw_begin_pressure_adjust), new View.OnClickListener() { // from class: qba.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                IBaseResponseCallback.this.d(0, null);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e.setCancelable(false);
        LogUtil.a("PressureDialog", "showBeginDeviceStressAdjust");
        e.show();
    }

    private static void d(Context context, int i) {
        if (context == null) {
            LogUtil.b("PressureDialog", "showDeviceNoConnectDialog() parameter is null.");
            return;
        }
        View inflate = View.inflate(context, R.layout.pressure_device_connect, null);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.pressure_device_no_connect);
        if (nsn.r()) {
            healthTextView.setTextSize(1, 30.0f);
        }
        if (i == 1) {
            healthTextView.setText(context.getString(R$string.IDS_hw_pressure_device_connect));
        } else if (i == 2) {
            healthTextView.setText(context.getString(R$string.IDS_hw_adjust_show_pressure_device_connect));
        } else {
            LogUtil.b("PressureDialog", "showDeviceNotConnectedDialog() Unsupported type.");
            return;
        }
        ((LinearLayout) inflate.findViewById(R.id.hw_health_agree_dialog_pressure)).setVisibility(8);
        dxN_(context, inflate);
    }

    private static void dxN_(final Context context, View view) {
        CustomViewDialog.Builder czf_ = new CustomViewDialog.Builder(context).czg_(view).czf_(context.getString(R$string.IDS_hw_pressure_known), new View.OnClickListener() { // from class: qba.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        if (!Utils.o()) {
            czf_.czd_(context.getString(R$string.IDS_hw_pressure_learn_more), new View.OnClickListener() { // from class: qba.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    qba.d(context);
                    ViewClickInstrumentation.clickOnView(view2);
                }
            });
        }
        CustomViewDialog e = czf_.e();
        e.setCancelable(false);
        e.show();
    }

    /* renamed from: qba$4, reason: invalid class name */
    class AnonymousClass4 implements GrsQueryCallback {
        final /* synthetic */ Context d;

        AnonymousClass4(Context context) {
            this.d = context;
        }

        @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
        public void onCallBackSuccess(final String str) {
            if (TextUtils.isEmpty(str)) {
                LogUtil.b("PressureDialog", "showDeviceNotConnected messageCenterHost is empty");
                return;
            }
            LogUtil.a("PressureDialog", "showDeviceNotConnected messageCenterHost = ", str);
            final Context context = this.d;
            HandlerExecutor.a(new Runnable() { // from class: qbb
                @Override // java.lang.Runnable
                public final void run() {
                    qba.AnonymousClass4.c(context, str);
                }
            });
        }

        static /* synthetic */ void c(Context context, String str) {
            Intent intent = new Intent(context, (Class<?>) WebViewActivity.class);
            intent.putExtra("url", str + "/messageH5/sleephtml/salesPromotion.html");
            gnm.aPB_(context, intent);
        }

        @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
        public void onCallBackFail(int i) {
            LogUtil.a("PressureDialog", "GRSManager onCallBackFail ACTIVITY_KEY code = ", Integer.valueOf(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(Context context) {
        GRSManager.a(context).e("messageCenterUrl", new AnonymousClass4(context));
    }

    public static void b(Context context, IBaseResponseCallback iBaseResponseCallback) {
        if (context == null || iBaseResponseCallback == null) {
            LogUtil.b("PressureDialog", "pressureAdjustButtonDialog() parameter is null.");
            return;
        }
        DeviceInfo a2 = jpt.a("PressureDialog");
        if (a2 == null) {
            d(context, 2);
            return;
        }
        LogUtil.c("PressureDialog", "currentDeviceInfo.toString() ", a2.toString());
        if (a2.getDeviceConnectState() != 2) {
            d(context, 2);
            return;
        }
        if (cvs.d() != null && cvs.d().isSupportPressAutoMonitor()) {
            LogUtil.a("PressureDialog", "Adjust isSupportPressAutoMonitor() = true");
            if (HwVersionManager.c(BaseApplication.getContext()).o(a2.getDeviceIdentify())) {
                a(context);
                return;
            } else {
                iBaseResponseCallback.d(100000, null);
                return;
            }
        }
        LogUtil.a("PressureDialog", "Adjust isSupportPressAutoMonitor() = false");
        d(context, 2);
    }

    public static void a(Context context, IBaseResponseCallback iBaseResponseCallback) {
        if (context == null || iBaseResponseCallback == null) {
            LogUtil.b("PressureDialog", "pressureMeasurementButtonDialog() parameter is null.");
            return;
        }
        DeviceInfo a2 = jpt.a("PressureDialog");
        if (a2 == null) {
            d(context, 1);
            return;
        }
        LogUtil.c("PressureDialog", "currentDeviceInfo = " + a2.toString());
        if (a2.getDeviceConnectState() != 2) {
            d(context, 1);
            return;
        }
        if (cvs.d() == null) {
            LogUtil.a("PressureDialog", "CapabilityUtils.getDeviceCapability() is null");
            d(context, 1);
            return;
        }
        boolean isSupportPressAutoMonitor = cvs.d().isSupportPressAutoMonitor();
        LogUtil.a("PressureDialog", "Adjust isSupportPressAutoMonitor() = " + isSupportPressAutoMonitor);
        if (isSupportPressAutoMonitor) {
            if (HwVersionManager.c(BaseApplication.getContext()).o(a2.getDeviceIdentify())) {
                a(context);
                return;
            } else {
                d(iBaseResponseCallback);
                return;
            }
        }
        d(context, 1);
    }

    public static void a(Context context) {
        if (context == null) {
            LogUtil.b("PressureDialog", "showTipDialog() parameter is null.");
            return;
        }
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(context).e(context.getResources().getString(R$string.IDS_device_ota_later_note)).czC_(R$string.IDS_user_permission_know, new View.OnClickListener() { // from class: qba.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("PressureDialog", "showTipDialog，click known button");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    private static void d(final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.b("PressureDialog", "doPressureAdjust() parameter is null.");
        } else {
            new pwr().e(new CommonUiBaseResponse() { // from class: qba.10
                @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
                public void onResponse(int i, Object obj) {
                    if (i == 0 && obj != null) {
                        boolean booleanValue = ((Boolean) obj).booleanValue();
                        LogUtil.a("PressureDialog", "isAlreadyDoPressureAdjust isAlreadyAdjust = ", Boolean.valueOf(booleanValue));
                        if (booleanValue) {
                            IBaseResponseCallback.this.d(0, null);
                            return;
                        } else {
                            IBaseResponseCallback.this.d(100001, null);
                            return;
                        }
                    }
                    IBaseResponseCallback.this.d(100001, null);
                }
            });
        }
    }
}
