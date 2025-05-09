package com.huawei.health.interactor;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.interactor.OperationAdInteractor;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.framework.network.restclient.hwhttp.Response;
import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ResultCallback;
import com.huawei.ui.commonui.dialog.CustomAdViewDialog;
import defpackage.ixx;
import defpackage.koq;
import defpackage.lqi;
import defpackage.lql;
import defpackage.mht;
import defpackage.nrf;
import defpackage.pub;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.IoUtils;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class OperationAdInteractor {

    /* renamed from: a, reason: collision with root package name */
    private CustomAdViewDialog f2514a;
    private final Context b;
    private Handler g;
    private Window c = null;
    private d e = new d();
    private String f = "2018YearReport";
    private String j = "G0002018";
    private boolean d = false;
    private final MessageCenterApi i = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);

    public OperationAdInteractor(Context context) {
        this.b = context;
    }

    public void aaP_(Handler handler) {
        this.g = handler;
        f();
    }

    public boolean b() {
        if (Utils.o() || CommonUtil.bu()) {
            return false;
        }
        String accountInfo = LoginInit.getInstance(this.b).getAccountInfo(1011);
        LogUtil.a("Login_AdUtils", "isAllowShowYearReport() huid=" + accountInfo);
        long currentTimeMillis = System.currentTimeMillis();
        String b = SharedPreferenceManager.b(this.b, Integer.toString(10006), h());
        LogUtil.a("Login_AdUtils", "isAllowShowYearReport() with alert count=", b, "current date=", Long.valueOf(currentTimeMillis));
        String b2 = SharedPreferenceManager.b(this.b, Integer.toString(10006), accountInfo + "key_ui_year_report_time");
        long a2 = mht.a(b2);
        LogUtil.a("Login_AdUtils", "isAllowShowYearReport() history date =" + b2);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(a2);
        Calendar i = i();
        if (!TextUtils.isEmpty(b) && mht.b(b) >= 3) {
            LogUtil.a("Login_AdUtils", "isAllowShowYearReport() alert times reach maxtimes");
            return false;
        }
        if (currentTimeMillis > 1580486399000L) {
            LogUtil.a("Login_AdUtils", "isAllowShowYearReport() alert reach endDate");
            return false;
        }
        if (!TextUtils.isEmpty(b2) && i.get(5) <= calendar.get(5) && i.get(2) == calendar.get(2)) {
            LogUtil.a("Login_AdUtils", "isAllowShowYearReport() alert date same date");
            return false;
        }
        b(b, currentTimeMillis, accountInfo);
        return true;
    }

    public void b(String str, long j, String str2) {
        LogUtil.a("Login_AdUtils", "yearReportAlertInfoUpdate() update alertInfo with count =", str, "currentDate=", Long.valueOf(j));
        if (TextUtils.isEmpty(str)) {
            SharedPreferenceManager.e(this.b, Integer.toString(10006), h(), "1", new StorageParams());
        } else {
            SharedPreferenceManager.e(this.b, Integer.toString(10006), h(), String.valueOf(mht.b(str) + 1), new StorageParams());
        }
        SharedPreferenceManager.e(this.b, Integer.toString(10006), str2 + "key_ui_year_report_time", String.valueOf(j), new StorageParams());
    }

    public void e() {
        LogUtil.a("Login_AdUtils", "showYearReport() show year report");
        GRSManager.a(this.b).e("achievementUrl", new GrsQueryCallback() { // from class: com.huawei.health.interactor.OperationAdInteractor.2
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                String str2 = str + "/web/annualHtml/annualReport2019.html";
                LogUtil.c("Login_AdUtils", "webUrl2019= ", str2);
                int i = OperationAdInteractor.this.i().get(1);
                OperationAdInteractor.this.j = "G000" + i;
                OperationAdInteractor.this.f = i + "YearReport";
                OperationAdInteractor.this.g.obtainMessage(200, new a(null, str2, OperationAdInteractor.this.j, OperationAdInteractor.this.f, "2")).sendToTarget();
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.b("Login_AdUtils", "onCallBackFail errorCode = ", Integer.valueOf(i));
            }
        });
    }

    public void c() {
        Context context;
        LogUtil.a("Login_AdUtils", "registerReceiver CloseAgreeDialogsReceiver");
        IntentFilter intentFilter = new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS");
        if (this.e == null || (context = this.b) == null) {
            return;
        }
        BroadcastManagerUtil.bFB_(context.getApplicationContext(), this.e, intentFilter);
    }

    public void g() {
        Context context;
        LogUtil.a("Login_AdUtils", "unregisterReceiver CloseAgreeDialogsReceiver");
        if (this.e == null || (context = this.b) == null) {
            return;
        }
        context.getApplicationContext().unregisterReceiver(this.e);
    }

    class d extends BroadcastReceiver {
        private d() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || !"android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(intent.getAction()) || !"homekey".equals(intent.getStringExtra("reason")) || OperationAdInteractor.this.c == null) {
                return;
            }
            LogUtil.a("Login_AdUtils", "setWindowAnimations(0) homekey");
            if (OperationAdInteractor.this.c.getAttributes().windowAnimations != 0) {
                LogUtil.a("Login_AdUtils", "setWindowAnimations(0) homekey windowAnimations != 0");
                OperationAdInteractor.this.c.setWindowAnimations(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Calendar i() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        return calendar;
    }

    private String h() {
        return LoginInit.getInstance(this.b).getAccountInfo(1011) + "key_ui_year_report_alert_count_" + i().get(1);
    }

    private void f() {
        LogUtil.a("Login_AdUtils", "getHomeDialogMessage enter");
        String b = SharedPreferenceManager.b(this.b, Integer.toString(10000), "health_msg_switch_promotion");
        LogUtil.a("Login_AdUtils", "getHomeDialogMessage promotionRecommend = " + b);
        if ("0".equals(b)) {
            return;
        }
        ThreadPoolManager.d().c("Login_AdUtils", new Runnable() { // from class: com.huawei.health.interactor.OperationAdInteractor.1
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("Login_AdUtils", "getHomeDialogMessage execute");
                if (!OperationAdInteractor.this.d) {
                    List<MessageObject> messages = OperationAdInteractor.this.i.getMessages(26);
                    boolean c = koq.c(messages);
                    OperationAdInteractor.this.d = true;
                    LogUtil.a("Login_AdUtils", "getHomeDialogMessage isHasData = ", Boolean.valueOf(c));
                    if (OperationAdInteractor.this.b()) {
                        OperationAdInteractor.this.e();
                        return;
                    } else if (c) {
                        LogUtil.c("Login_AdUtils", "getHomeDialogMessage dialogMessageList = ", messages.get(0));
                        OperationAdInteractor.this.a(messages.get(0));
                        return;
                    } else {
                        LogUtil.h("Login_AdUtils", "getHomeDialogMessage is other condition");
                        return;
                    }
                }
                LogUtil.a("Login_AdUtils", "has show");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(MessageObject messageObject) {
        LogUtil.a("Login_AdUtils", "downloadAdImage");
        final String imgUri = messageObject.getImgUri();
        String detailUri = messageObject.getDetailUri();
        final String msgId = messageObject.getMsgId();
        final String msgTitle = messageObject.getMsgTitle();
        final String module = messageObject.getModule();
        if (TextUtils.isEmpty(msgId) || this.b == null) {
            return;
        }
        if (TextUtils.isEmpty(imgUri) || TextUtils.isEmpty(detailUri)) {
            this.i.setMessageRead(msgId);
        } else {
            lqi.d().b(imgUri, new pub().getHeaders(), lql.b(null), Response.class, new ResultCallback<Response>() { // from class: com.huawei.health.interactor.OperationAdInteractor.5
                @Override // com.huawei.networkclient.ResultCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Response response) {
                    OperationAdInteractor.this.c(response, imgUri, msgId, msgTitle, module);
                }

                @Override // com.huawei.networkclient.ResultCallback
                public void onFailure(Throwable th) {
                    LogUtil.a("Login_AdUtils", "downloadAdImage onFailure");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Response response, String str, String str2, String str3, String str4) {
        LogUtil.a("Login_AdUtils", "downloadAdImage onResponse");
        ResponseBody body = response.getBody();
        if (body == null || !response.isSuccessful()) {
            LogUtil.a("Login_AdUtils", "downloadAdImage null==responseBody || !response.isSuccessful()");
            return;
        }
        LogUtil.a("Login_AdUtils", "downloadAdImage contentLength = " + body.getContentLength());
        InputStream inputStream = body.getInputStream();
        try {
            Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
            if (decodeStream != null) {
                LogUtil.a("Login_AdUtils", "bitmap width:", Integer.valueOf(decodeStream.getWidth()), " heigh:", Integer.valueOf(decodeStream.getHeight()));
                if (!"2".equals(str4) || (decodeStream = nrf.cIa_(decodeStream, nrf.cHN_(R.drawable._2131430872_res_0x7f0b0dd8, this.b), this.b.getResources().getString(R.string._2130842470_res_0x7f021366), this.b)) != null) {
                    Bitmap bitmap = decodeStream;
                    LogUtil.a("Login_AdUtils", "newBitmap width:", Integer.valueOf(bitmap.getWidth()), " heigh:", Integer.valueOf(bitmap.getHeight()));
                    this.g.obtainMessage(200, new a(bitmap, str, str2, str3, str4)).sendToTarget();
                    return;
                }
                LogUtil.a("Login_AdUtils", "downloadAdImage newBitmap == null");
                return;
            }
            LogUtil.h("Login_AdUtils", "downloadAdImage null = bitmap");
        } catch (IllegalArgumentException unused) {
            LogUtil.b("getBitmap IllegalArgumentException", new Object[0]);
        } finally {
            IoUtils.e(inputStream);
        }
    }

    public void e(final Context context, final a aVar) {
        LogUtil.a("Login_AdUtils", "showAdDialog");
        if (context == null) {
            LogUtil.a("Login_AdUtils", "showAdDialog context == null");
            return;
        }
        CustomAdViewDialog.Builder cyg_ = new CustomAdViewDialog.Builder(context).cye_(aVar.c).cyf_(new View.OnClickListener() { // from class: dzl
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OperationAdInteractor.this.aaQ_(context, aVar, view);
            }
        }).cyg_(new View.OnClickListener() { // from class: dzm
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OperationAdInteractor.aaO_(context, aVar, view);
            }
        });
        this.f2514a = cyg_.a();
        boolean e = cyg_.e();
        LogUtil.a("Login_AdUtils", "showSucess = " + e);
        if (e) {
            this.i.setMessageRead(aVar.b);
            b(context.getApplicationContext(), 1, aVar);
            this.f2514a.setCanceledOnTouchOutside(false);
            this.f2514a.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.huawei.health.interactor.OperationAdInteractor.3
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (i != 4 || keyEvent.getAction() != 0) {
                        return false;
                    }
                    OperationAdInteractor.b(context.getApplicationContext(), 4, aVar);
                    return false;
                }
            });
            Activity activity = (Activity) context;
            if (activity.isDestroyed() || activity.isFinishing()) {
                return;
            }
            this.c = this.f2514a.getWindow();
            this.f2514a.show();
            Handler handler = this.g;
            handler.sendMessageDelayed(handler.obtainMessage(300), 500L);
        }
    }

    public /* synthetic */ void aaQ_(Context context, a aVar, View view) {
        LogUtil.a("Login_AdUtils", "showAdDialog onclick");
        b(context.getApplicationContext(), 2, aVar);
        b(context, aVar);
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void aaO_(Context context, a aVar, View view) {
        b(context.getApplicationContext(), 3, aVar);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b(Context context, a aVar) {
        if (aVar.d.startsWith("huaweischeme")) {
            MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
            if (marketRouterApi != null) {
                marketRouterApi.router(aVar.d);
                return;
            }
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("msgId", aVar.b);
        bundle.putString(com.huawei.health.messagecenter.model.CommonUtil.DETAIL_URI, aVar.d);
        bundle.putString("EXTRA_BI_NAME", aVar.e);
        bundle.putString("EXTRA_BI_SOURCE", "ADDIALOG");
        bundle.putString("EXTRA_BI_SHOWTIME", "SHOW_TIME_BI");
        AppRouter.b("/PluginMessageCenter/DispatchSkipEventActivity").zF_(bundle).c(this.b);
    }

    public void a() {
        LogUtil.a("Login_AdUtils", "enter is_dialog_dismiss()");
        CustomAdViewDialog customAdViewDialog = this.f2514a;
        if (customAdViewDialog == null || !customAdViewDialog.c()) {
            return;
        }
        LogUtil.a("Login_AdUtils", "dialog_dismiss");
        this.f2514a.dismiss();
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private String f2517a;
        private String b;
        private Bitmap c;
        private String d;
        private String e;

        private a(Bitmap bitmap, String str, String str2, String str3, String str4) {
            this.c = bitmap;
            this.d = str;
            this.b = str2;
            this.e = str3;
            this.f2517a = str4;
        }
    }

    public void d() {
        LogUtil.a("Login_AdUtils", "onDestroy");
        this.e = null;
        CustomAdViewDialog customAdViewDialog = this.f2514a;
        if (customAdViewDialog != null) {
            customAdViewDialog.dismiss();
            this.f2514a = null;
        }
        if (this.c != null) {
            this.c = null;
        }
    }

    public static void b(Context context, int i, a aVar) {
        LogUtil.a("Login_AdUtils", "setAdBiAnalyticsEvent", Integer.valueOf(i));
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(i));
        hashMap.put("id", aVar.b);
        hashMap.put("name", aVar.e);
        hashMap.put("module", aVar.f2517a);
        ixx.d().d(context, AnalyticsValue.HEALTH_HOME_OPERATION_DIALOG_CLICK_2010059.value(), hashMap, 0);
    }
}
