package com.huawei.health.suggestion.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import defpackage.arx;
import defpackage.ase;
import defpackage.ash;
import defpackage.ggl;
import defpackage.gib;
import defpackage.jdh;
import defpackage.nsn;
import defpackage.sqa;
import health.compact.a.Services;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes4.dex */
public class PlanReportNotificationService extends Service {
    private IntPlan c;
    private Context d;
    private final Binder e = new d();
    private Handler b = new e(Looper.getMainLooper(), this);

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        LogUtil.a("Suggestion_PlanReportNotificationService", "onCreate");
        this.d = getApplicationContext();
        d();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        LogUtil.a("Suggestion_PlanReportNotificationService", "onStartCommand");
        c();
        PluginSuggestion.getInstance().startSendIntelligentPlan();
        return 2;
    }

    private void c() {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.b("Suggestion_PlanReportNotificationService", "createFitnessPlan, createPlan : planApi is null.");
        } else {
            planApi.b(new UiCallback<IntPlan>() { // from class: com.huawei.health.suggestion.service.PlanReportNotificationService.2
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.h("Suggestion_PlanReportNotificationService", "getCurrentPlan fail errCode:", Integer.valueOf(i), ",errInfo:", str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onSuccess(IntPlan intPlan) {
                    if (intPlan == null || !intPlan.getPlanType().equals(IntPlan.PlanType.AI_RUN_PLAN)) {
                        return;
                    }
                    PlanReportNotificationService.this.c = intPlan;
                    LogUtil.a("Suggestion_PlanReportNotificationService", "getCurrentPlan onSuccess data:", PlanReportNotificationService.this.c.getPlanId());
                    int g = ase.g(PlanReportNotificationService.this.c);
                    LogUtil.a("Suggestion_PlanReportNotificationService", "currentWeek:", Integer.valueOf(g));
                    if (g > 1) {
                        PlanReportNotificationService.this.b();
                    }
                }
            });
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.e;
    }

    private void d() {
        LogUtil.a("Suggestion_PlanReportNotificationService", "start PlanReportNotificationService");
        Calendar calendar = Calendar.getInstance();
        int d2 = gib.d(calendar.get(7));
        LogUtil.a("Suggestion_PlanReportNotificationService", "dayOfWeeks=", Integer.valueOf(d2));
        calendar.add(5, 8 - d2);
        calendar.set(11, 9);
        LogUtil.a("Suggestion_PlanReportNotificationService", "startReportAlarm  Calendar=", calendar.toString());
        sqa.ekD_(0, new Intent(this.d, (Class<?>) PlanReportNotificationService.class), 201326592, 0, calendar.getTimeInMillis(), 604800000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        Handler handler;
        int d2 = gib.d(Calendar.getInstance().get(7));
        LogUtil.a("Suggestion_PlanReportNotificationService", "sendPlanReport dayOfWeeks:", Integer.valueOf(d2));
        if (d2 != 1 || (handler = this.b) == null) {
            return;
        }
        handler.sendEmptyMessage(101);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        LogUtil.a("Suggestion_PlanReportNotificationService", "sendNotification");
        Intent intent = new Intent();
        intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.health.suggestion.ui.MyPlanReportActivity");
        IntPlan intPlan = this.c;
        if (intPlan != null) {
            intent.putExtra("planId", intPlan.getPlanId());
            intent.putExtra("plantype", this.c.getPlanType());
        }
        intent.setFlags(268435456);
        Resources resources = BaseApplication.getContext().getResources();
        String string = resources.getString(R.string._2130844900_res_0x7f021ce4);
        String string2 = resources.getString(R.string._2130848557_res_0x7f022b2d, this.c.getMetaInfo() == null ? "" : this.c.getMetaInfo().getName());
        Notification.Builder xf_ = jdh.c().xf_();
        nsn.cLB_(xf_);
        xf_.setTicker(string2);
        xf_.setContentTitle(string);
        xf_.setContentText(string2);
        xf_.setContentIntent(PendingIntent.getActivity(this.d, 0, intent, 201326592));
        xf_.setAutoCancel(true);
        xf_.setDefaults(1);
        jdh.c().xh_(ggl.c(), xf_.build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        LogUtil.a("Suggestion_PlanReportNotificationService", "createMessageCenterMessage");
        MessageObject messageObject = new MessageObject();
        String string = arx.b().getResources().getString(R.string._2130848557_res_0x7f022b2d, this.c.getMetaInfo() == null ? "" : this.c.getMetaInfo().getName());
        messageObject.setMsgTitle(string);
        messageObject.setMsgContent(string);
        messageObject.setFlag(0);
        messageObject.setMsgType(1);
        messageObject.setWeight(1);
        messageObject.setReadFlag(0);
        messageObject.setCreateTime(new Date().getTime());
        messageObject.setDetailUri("messagecenter://intPlan");
        messageObject.setPosition(1);
        messageObject.setMetadata(string);
        messageObject.setMsgPosition(11);
        messageObject.setHuid(LoginInit.getInstance(BaseApplication.getContext()).getHuidOrDefault());
        messageObject.setExpireTime(0L);
        messageObject.setModule(String.valueOf(15));
        messageObject.setType("planMessage");
        MessageCenterApi messageCenterApi = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
        messageObject.setMsgId(messageCenterApi.getMessageId(String.valueOf(15), "planMessage"));
        messageCenterApi.insertMessage(messageObject);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        LogUtil.a("Suggestion_PlanReportNotificationService", "stop service.");
        stopSelf();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("Suggestion_PlanReportNotificationService", "onDestroy");
        this.d = null;
        Handler handler = this.b;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.b = null;
        }
    }

    static class d extends Binder {
        private d() {
        }
    }

    static class e extends BaseHandler<PlanReportNotificationService> {
        e(Looper looper, PlanReportNotificationService planReportNotificationService) {
            super(looper, planReportNotificationService);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: ayh_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(PlanReportNotificationService planReportNotificationService, Message message) {
            if (message.what == 101) {
                planReportNotificationService.a();
                planReportNotificationService.e();
                ash.a("planReportData", Boolean.toString(true));
                planReportNotificationService.i();
            }
        }
    }
}
