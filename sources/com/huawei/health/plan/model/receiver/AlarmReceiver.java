package com.huawei.health.plan.model.receiver;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.health.R;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import defpackage.ase;
import defpackage.ash;
import defpackage.epl;
import defpackage.epm;
import defpackage.epo;
import defpackage.etl;
import defpackage.ety;
import defpackage.eve;
import defpackage.ggl;
import defpackage.gib;
import defpackage.jdh;
import defpackage.koq;
import defpackage.mnt;
import defpackage.nsn;
import defpackage.sqa;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.utils.StringUtils;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes3.dex */
public class AlarmReceiver extends BroadcastReceiver {
    private static int e;

    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, final Intent intent) {
        LogUtil.a("Suggestion_AlarmReceiver", "Receive alarm broadcast-----------");
        if (intent == null) {
            LogUtil.h("Suggestion_AlarmReceiver", "i == null");
            return;
        }
        final String c = StringUtils.c((Object) intent.getStringExtra(JsbMapKeyNames.H5_USER_ID));
        LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
        if (loginInit.getIsLogined() && c.equals(loginInit.getAccountInfo(1011))) {
            LogUtil.a("Suggestion_AlarmReceiver", "oldPlanType: " + intent.getIntExtra("planType", -1));
            ety.c().b(new UiCallback<epo>() { // from class: com.huawei.health.plan.model.receiver.AlarmReceiver.5
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.h("Suggestion_AlarmReceiver", "errorCode :" + i + ";errorInfo :" + str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: asF_, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Handler handler, epo epoVar) {
                    onSuccess(epoVar);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onSuccess(epo epoVar) {
                    if (epoVar != null && epoVar.getPlanType() != null) {
                        if (!AlarmReceiver.this.d(epoVar.getPlanType().getType())) {
                            ReleaseLogUtil.b("Suggestion_AlarmReceiver", "AlarmReceiver isOpenRemind false");
                            return;
                        }
                        if (!sqa.ekw_(intent)) {
                            AlarmReceiver.e();
                            LogUtil.a("Suggestion_AlarmReceiver", "planType: " + epoVar.getPlanType().getType() + ", planNotifyId = ", Integer.valueOf(AlarmReceiver.e));
                            epm e2 = AlarmReceiver.this.e(epoVar);
                            String a2 = AlarmReceiver.this.a(e2, epoVar.getPlanId());
                            if (!TextUtils.isEmpty(a2) && e2 != null) {
                                AlarmReceiver alarmReceiver = AlarmReceiver.this;
                                alarmReceiver.asE_(epoVar, context, alarmReceiver.asD_(e2.getActionId()), AlarmReceiver.e, a2);
                                AlarmReceiver.this.b(c, epoVar);
                                return;
                            }
                            LogUtil.a("Suggestion_AlarmReceiver", "today is rest day.");
                            return;
                        }
                        long eku_ = sqa.eku_(intent, 5, 1);
                        etl.a(epoVar, true, eku_);
                        ReleaseLogUtil.b("Suggestion_AlarmReceiver", "onReceive onSuccess timeMillis ", Long.valueOf(eku_));
                        return;
                    }
                    LogUtil.h("Suggestion_AlarmReceiver", "onReceive current plan or planType is null");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(int i) {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h("Suggestion_AlarmReceiver", "isOpenRemind planApi is null.");
            return true;
        }
        planApi.setPlanType(i);
        return planApi.isOpenRemind();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Intent asD_(String str) {
        Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL);
        intent.setData(Uri.parse("huaweischeme://healthapp/fitnesspage?skip_type=train_details&id=" + str + "&version=2.0"));
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void asE_(epo epoVar, Context context, Intent intent, int i, String str) {
        String str2;
        String str3 = "";
        if (epoVar.getPlanType().equals(IntPlan.PlanType.RUN_PLAN) || epoVar.getPlanType().equals(IntPlan.PlanType.AI_RUN_PLAN)) {
            str2 = context.getString(R.string._2130839518_res_0x7f0207de) + " ";
        } else {
            str2 = (epoVar.getPlanType().equals(IntPlan.PlanType.FIT_PLAN) || epoVar.getPlanType().equals(IntPlan.PlanType.AI_FITNESS_PLAN)) ? context.getString(R.string._2130851543_res_0x7f0236d7) : "";
        }
        if (epoVar.getCompatiblePlan() != null) {
            str3 = epoVar.getCompatiblePlan().acquireName();
        } else if (epoVar.getMetaInfo() != null) {
            str3 = epoVar.getMetaInfo().getName();
        } else {
            LogUtil.h("Suggestion_AlarmReceiver", "planName plan error.");
        }
        PendingIntent activity = PendingIntent.getActivity(context, epoVar.getPlanType().getType(), intent, 201326592);
        jdh.b().xh_(i, asC_(str2 + str, str3, str2 + str, activity));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, epo epoVar) {
        LogUtil.a("Suggestion_AlarmReceiver", "createMessageCenterMessage");
        MessageObject messageObject = new MessageObject();
        String string = BaseApplication.getContext().getString(R.string._2130839519_res_0x7f0207df);
        messageObject.setMsgTitle(string);
        messageObject.setMsgContent(string);
        messageObject.setFlag(0);
        messageObject.setMsgType(1);
        messageObject.setWeight(1);
        messageObject.setReadFlag(0);
        messageObject.setCreateTime(new Date().getTime());
        messageObject.setDetailUri(a(epoVar.getPlanType()));
        messageObject.setPosition(1);
        messageObject.setMetadata(string);
        messageObject.setMsgPosition(11);
        messageObject.setHuid(str);
        messageObject.setExpireTime(0L);
        messageObject.setModule(String.valueOf(15));
        messageObject.setType("planMessage");
        MessageCenterApi messageCenterApi = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
        messageObject.setMsgId(messageCenterApi.getMessageId(String.valueOf(15), "planMessage"));
        messageCenterApi.insertMessage(messageObject);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e() {
        e = ggl.c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public epm e(epo epoVar) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        epl a2 = eve.a(epoVar, ase.g(epoVar), gib.d(calendar.get(7)));
        if (a2 == null || a2.getPunchFlag() == 1) {
            LogUtil.a("Suggestion_AlarmReceiver", "dayInfo is null or has trained");
            return null;
        }
        if (koq.c(a2.d())) {
            for (epm epmVar : a2.d()) {
                if (epmVar.getPunchFlag() == 0) {
                    return epmVar;
                }
            }
        } else if (koq.c(a2.a())) {
            for (epm epmVar2 : a2.a()) {
                if (epmVar2.getPunchFlag() == 0) {
                    return epmVar2;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(epm epmVar, String str) {
        if (epmVar == null) {
            return "";
        }
        mnt actionInfo = epmVar.getActionInfo();
        String b = b(str);
        return (actionInfo == null || (!StringUtils.g(b) && epmVar.getActionId().equals(b))) ? "" : actionInfo.d();
    }

    private Notification asC_(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, PendingIntent pendingIntent) {
        Notification.Builder xf_ = jdh.c().xf_();
        nsn.cLB_(xf_);
        xf_.setTicker(charSequence);
        xf_.setContentTitle(charSequence2);
        xf_.setContentText(charSequence3);
        xf_.setContentIntent(pendingIntent);
        xf_.setAutoCancel(true);
        xf_.setDefaults(1);
        xf_.setOngoing(false);
        xf_.setOnlyAlertOnce(true);
        return xf_.build();
    }

    private String a(IntPlan.PlanType planType) {
        return planType.equals(IntPlan.PlanType.RUN_PLAN) ? "" : "messagecenter://intPlan";
    }

    private String b(String str) {
        String b = ash.b("intPlanTrainingCourse");
        if (StringUtils.g(b) || StringUtils.g(str)) {
            return "";
        }
        try {
            WorkoutRecord workoutRecord = (WorkoutRecord) new Gson().fromJson(b, WorkoutRecord.class);
            LogUtil.a("Suggestion_AlarmReceiver", "trainingCourse record:" + workoutRecord);
            return (workoutRecord == null || !str.equals(workoutRecord.acquirePlanId()) || workoutRecord.acquireExerciseTime() <= 0 || Math.abs(System.currentTimeMillis() - workoutRecord.acquireExerciseTime()) >= 3600000) ? "" : workoutRecord.acquireWorkoutId();
        } catch (JsonSyntaxException unused) {
            LogUtil.b("Suggestion_AlarmReceiver", "getTrainingCourseId JsonSyntaxException");
            return "";
        }
    }
}
