package com.huawei.operation.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.adapter.PluginOperationAdapter;
import com.huawei.operation.utils.Constants;
import com.huawei.operation.view.CustomWebView;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;

/* loaded from: classes5.dex */
public class WebViewActivityExt {
    private static final String TAG = "PluginOperation_WebViewActivity";
    private CustomTextAlertDialog mUnbindDialog;

    public void showActivityResultExplainDialog(Context context) {
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(context).b(context.getString(R.string._2130841928_res_0x7f021148)).e(context.getResources().getString(R.string._2130843672_res_0x7f021818)).cyU_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: com.huawei.operation.activity.WebViewActivityExt.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
        a2.setCancelable(false);
        if (a2.isShowing()) {
            return;
        }
        a2.show();
    }

    public void startVmallLogout(CustomWebView customWebView, WebView webView) {
        if (customWebView == null || webView == null) {
            return;
        }
        LogUtil.c(TAG, "startVmallLogout Constants.WEB_VIEW_STATUS_ON_DESTROY");
        customWebView.startVmallLogout();
        customWebView.callbackWebViewStatus(Constants.WEBVIEW_STATUS_ON_DESTROY);
        customWebView.setRegisterActivityQuitFunctionRes("");
        customWebView.clear();
    }

    public void showUnBindDeviceDialog(final Context context, final ContentValues contentValues, final String str, final PluginOperationAdapter pluginOperationAdapter) {
        HandlerExecutor.e(new Runnable() { // from class: com.huawei.operation.activity.WebViewActivityExt$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                WebViewActivityExt.this.m683x3bac8ebf(context, pluginOperationAdapter, contentValues, str);
            }
        });
    }

    /* renamed from: lambda$showUnBindDeviceDialog$2$com-huawei-operation-activity-WebViewActivityExt, reason: not valid java name */
    /* synthetic */ void m683x3bac8ebf(final Context context, final PluginOperationAdapter pluginOperationAdapter, final ContentValues contentValues, final String str) {
        if (context == null || pluginOperationAdapter == null) {
            LogUtil.a(TAG, "showUnBindDeviceDialog context or adapter is null");
            return;
        }
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(context).b(R.string.IDS_hw_health_wear_connect_device_unpair_button).d(R.string.IDS_unbind_device_wear_home).cyU_(R.string.IDS_hw_health_wear_connect_device_unpair_button, new View.OnClickListener() { // from class: com.huawei.operation.activity.WebViewActivityExt$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WebViewActivityExt.this.m681x58594281(pluginOperationAdapter, context, contentValues, str, view);
            }
        }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.operation.activity.WebViewActivityExt$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WebViewActivityExt.this.m682x4a02e8a0(view);
            }
        }).a();
        this.mUnbindDialog = a2;
        a2.setCancelable(false);
        this.mUnbindDialog.show();
    }

    /* renamed from: lambda$showUnBindDeviceDialog$0$com-huawei-operation-activity-WebViewActivityExt, reason: not valid java name */
    /* synthetic */ void m681x58594281(PluginOperationAdapter pluginOperationAdapter, Context context, ContentValues contentValues, String str, View view) {
        CustomTextAlertDialog customTextAlertDialog = this.mUnbindDialog;
        if (customTextAlertDialog == null) {
            LogUtil.a(TAG, "showUnBindDeviceDialog setPositiveButton mUnbindDialog = null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        customTextAlertDialog.dismiss();
        if (pluginOperationAdapter.unbindHaveBindingDevice(context, contentValues, str)) {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
            if (launchIntentForPackage != null) {
                launchIntentForPackage.setFlags(AppRouterExtras.COLDSTART);
                context.startActivity(launchIntentForPackage);
            }
            if (context instanceof Activity) {
                ((Activity) context).finish();
            } else {
                LogUtil.a(TAG, "showUnBindDeviceDialog context is not Activity ");
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$showUnBindDeviceDialog$1$com-huawei-operation-activity-WebViewActivityExt, reason: not valid java name */
    /* synthetic */ void m682x4a02e8a0(View view) {
        CustomTextAlertDialog customTextAlertDialog = this.mUnbindDialog;
        if (customTextAlertDialog == null) {
            LogUtil.a(TAG, "showUnBindDeviceDialog setNegativeButton mUnbindDialog = null");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            customTextAlertDialog.dismiss();
            this.mUnbindDialog = null;
            ViewClickInstrumentation.clickOnView(view);
        }
    }
}
