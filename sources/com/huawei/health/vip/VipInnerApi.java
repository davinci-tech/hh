package com.huawei.health.vip;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.view.View;
import android.webkit.JavascriptInterface;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.servicesui.R$string;
import com.huawei.health.vip.VipInnerApi;
import com.huawei.health.vip.api.VipApi;
import com.huawei.health.vip.view.EquityDialogActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import defpackage.dqj;
import health.compact.a.CommonUtil;
import health.compact.a.Services;

/* loaded from: classes.dex */
public class VipInnerApi extends JsBaseModule {
    @JavascriptInterface
    public void startVipPayViewActivity(long j, String str) {
        LogUtil.a(this.TAG, "startVipPayViewActivity");
        if (this.mContext instanceof Activity) {
            VipApi vipApi = (VipApi) Services.a("vip", VipApi.class);
            if (vipApi != null) {
                vipApi.startVipPayViewActivity((Activity) this.mContext, str);
                onSuccessCallback(j);
                return;
            } else {
                LogUtil.h(this.TAG, "vipApi is null.");
                return;
            }
        }
        LogUtil.h(this.TAG, "mContext is not activity.");
    }

    @JavascriptInterface
    /* renamed from: showDialog, reason: merged with bridge method [inline-methods] */
    public void m522lambda$showDialog$0$comhuaweihealthvipVipInnerApi(final long j, final String str) {
        LogUtil.a(this.TAG, "showDialog");
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: gph
                @Override // java.lang.Runnable
                public final void run() {
                    VipInnerApi.this.m522lambda$showDialog$0$comhuaweihealthvipVipInnerApi(j, str);
                }
            });
            return;
        }
        Activity wa_ = BaseApplication.wa_();
        if (wa_ == null || wa_.isFinishing() || wa_.isDestroyed()) {
            LogUtil.b(this.TAG, "activity is wrong or destroyed");
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(wa_);
        builder.e(str);
        final Context e = BaseApplication.e();
        builder.czC_(R$string.IDS_vip_continue_open, new View.OnClickListener() { // from class: gpj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VipInnerApi.this.m523lambda$showDialog$1$comhuaweihealthvipVipInnerApi(e, j, view);
            }
        });
        if (CommonUtil.h(LoginInit.getInstance(this.mContext).getAccountInfo(1009)) == 7) {
            builder.czz_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: gpi
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VipInnerApi.this.m524lambda$showDialog$2$comhuaweihealthvipVipInnerApi(e, j, view);
                }
            });
        } else {
            builder.czy_(R$string.IDS_settings_button_cancal, R$color.textColorSecondary, new View.OnClickListener() { // from class: gpl
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VipInnerApi.this.m525lambda$showDialog$3$comhuaweihealthvipVipInnerApi(e, j, view);
                }
            });
        }
        builder.e(true);
        NoTitleCustomAlertDialog e2 = builder.e();
        e2.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: gpm
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                VipInnerApi.this.m526lambda$showDialog$4$comhuaweihealthvipVipInnerApi(e, j, dialogInterface);
            }
        });
        e2.show();
    }

    /* renamed from: lambda$showDialog$1$com-huawei-health-vip-VipInnerApi, reason: not valid java name */
    public /* synthetic */ void m523lambda$showDialog$1$comhuaweihealthvipVipInnerApi(Context context, long j, View view) {
        LogUtil.a(this.TAG, "agreed confirmed");
        dqj.b(context, 1);
        onSuccessCallback(j, 1);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$showDialog$2$com-huawei-health-vip-VipInnerApi, reason: not valid java name */
    public /* synthetic */ void m524lambda$showDialog$2$comhuaweihealthvipVipInnerApi(Context context, long j, View view) {
        LogUtil.a(this.TAG, "agree canceled");
        dqj.b(context, 2);
        onSuccessCallback(j, 0);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$showDialog$3$com-huawei-health-vip-VipInnerApi, reason: not valid java name */
    public /* synthetic */ void m525lambda$showDialog$3$comhuaweihealthvipVipInnerApi(Context context, long j, View view) {
        LogUtil.a(this.TAG, "agree canceled");
        dqj.b(context, 2);
        onSuccessCallback(j, 0);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$showDialog$4$com-huawei-health-vip-VipInnerApi, reason: not valid java name */
    public /* synthetic */ void m526lambda$showDialog$4$comhuaweihealthvipVipInnerApi(Context context, long j, DialogInterface dialogInterface) {
        LogUtil.a(this.TAG, "dialog canceled");
        dqj.b(context, 3);
        onSuccessCallback(j, 0);
    }

    @JavascriptInterface
    public void finishEquityDialog(final long j) {
        LogUtil.a(this.TAG, "finishEquityDialogActivity");
        HandlerExecutor.e(new Runnable() { // from class: com.huawei.health.vip.VipInnerApi.3
            @Override // java.lang.Runnable
            public void run() {
                Activity activity;
                if (VipInnerApi.this.mContext == null) {
                    return;
                }
                if (VipInnerApi.this.mContext instanceof Activity) {
                    activity = (Activity) VipInnerApi.this.mContext;
                } else if (!(VipInnerApi.this.mContext instanceof ContextWrapper) || !(((ContextWrapper) VipInnerApi.this.mContext).getBaseContext() instanceof Activity)) {
                    return;
                } else {
                    activity = (Activity) ((ContextWrapper) VipInnerApi.this.mContext).getBaseContext();
                }
                if (activity instanceof EquityDialogActivity) {
                    ((EquityDialogActivity) activity).finish();
                    VipInnerApi.this.onSuccessCallback(j, 0);
                } else {
                    VipInnerApi.this.onSuccessCallback(j, -1);
                }
            }
        });
    }
}
