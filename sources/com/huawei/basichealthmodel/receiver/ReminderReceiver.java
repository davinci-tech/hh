package com.huawei.basichealthmodel.receiver;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.basichealthmodel.R$string;
import com.huawei.basichealthmodel.devicelink.bean.NoticesBean;
import com.huawei.basichealthmodel.devicelink.bean.ResponseMsgBody;
import com.huawei.basichealthmodel.medicine.MedicinesRule;
import com.huawei.basichealthmodel.receiver.ReminderReceiver;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.health.healthmodel.bean.TextBean;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import defpackage.auc;
import defpackage.auz;
import defpackage.avm;
import defpackage.axf;
import defpackage.ayh;
import defpackage.azi;
import defpackage.bao;
import defpackage.bby;
import defpackage.bcc;
import defpackage.bcm;
import defpackage.dsl;
import defpackage.glq;
import defpackage.jdh;
import defpackage.jdl;
import defpackage.koq;
import defpackage.nsg;
import defpackage.nsn;
import defpackage.sqa;
import defpackage.wq;
import health.compact.a.CommonUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes3.dex */
public class ReminderReceiver extends BroadcastReceiver {
    private String b;
    private long d;
    private Context e;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, final Intent intent) {
        boolean ah = azi.ah();
        boolean k = Utils.k();
        ReleaseLogUtil.b("R_HealthLife_ReminderReceiver", "onReceive isUseNewHealthModel ", Boolean.valueOf(ah), " isSupportHealthModel ", Boolean.valueOf(k));
        if (ah || !k) {
            return;
        }
        this.e = context;
        if (intent.hasExtra("reminderHuid")) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: axt
                @Override // java.lang.Runnable
                public final void run() {
                    ReminderReceiver.this.kN_(intent);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: kM_, reason: merged with bridge method [inline-methods] */
    public void kN_(Intent intent) {
        String stringExtra = intent.getStringExtra("reminderHuid");
        String accountInfo = LoginInit.getInstance(this.e).getAccountInfo(1011);
        if (TextUtils.isEmpty(stringExtra) || TextUtils.isEmpty(accountInfo) || !stringExtra.equals(accountInfo)) {
            LogUtil.h("HealthLife_ReminderReceiver", "processRemindNotify invalid userId");
            return;
        }
        int intExtra = intent.getIntExtra("reminderId", -1);
        int intExtra2 = intent.getIntExtra("reminderTotalNumber", 1);
        int intExtra3 = intent.getIntExtra("reminderCurrentNumber", 1);
        if (sqa.ekw_(intent)) {
            long eku_ = sqa.eku_(intent, 5, 1);
            ReleaseLogUtil.b("R_HealthLife_ReminderReceiver", "processRemindNotify timeMillis ", Long.valueOf(eku_), " reminderType ", Integer.valueOf(intExtra), " totalNumber ", Integer.valueOf(intExtra2), " reminderCurrentNumber ", Integer.valueOf(intExtra3));
            if (eku_ > 0) {
                bby.b(intExtra, eku_, intExtra2, intExtra3);
                return;
            }
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (intExtra == 3000 && bcm.a() >= jdl.v(currentTimeMillis)) {
            ReleaseLogUtil.b("R_HealthLife_ReminderReceiver", "processRemindNotify have sent Celebrating message");
            return;
        }
        this.d = (sqa.ekq_(intent) / 60000) * 60000;
        ReleaseLogUtil.b("R_HealthLife_ReminderReceiver", "processRemindNotify reminderType ", Integer.valueOf(intExtra), " reminderCurrentNumber ", Integer.valueOf(intExtra3), " mReminderTime ", Long.valueOf(this.d));
        this.b = intExtra + " " + intExtra3;
        if (Math.abs(currentTimeMillis - this.d) > (intExtra == 3011 ? 60 : 15) * 60000 || a(currentTimeMillis)) {
            return;
        }
        avm.a().c();
        e(intExtra, intExtra2, intExtra3);
    }

    private boolean a(long j) {
        String e = bao.e("health_model_remind_last_time_" + this.b);
        LogUtil.c("HealthLife_ReminderReceiver", "isRepeatedNotification mLastRemindMap: ", e);
        if (TextUtils.isEmpty(e) || Math.abs(j - nsn.h(e)) >= 1800000) {
            return false;
        }
        ReleaseLogUtil.b("R_HealthLife_ReminderReceiver", "isRepeatedNotification mMapKey ", this.b, " mapString ", e);
        return true;
    }

    private void e(int i, int i2, int i3) {
        String b;
        ReleaseLogUtil.b("R_HealthLife_ReminderReceiver", "sendNotification reminderId ", Integer.valueOf(i), " reminderTotalNumber ", Integer.valueOf(i2), " reminderCurrentNumber ", Integer.valueOf(i3));
        if (i == 3000) {
            return;
        }
        int[] iArr = bby.a().get(Integer.valueOf(i));
        if (iArr == null || iArr.length < 1) {
            ReleaseLogUtil.a("R_HealthLife_ReminderReceiver", "sendNotification ids ", iArr);
            return;
        }
        int i4 = iArr[0];
        ayh ayhVar = bby.e().get(Integer.valueOf(i4));
        if (ayhVar == null) {
            ReleaseLogUtil.a("R_HealthLife_ReminderReceiver", "sendNotification reminderBean is null");
            return;
        }
        boolean d = ayhVar.d();
        ReleaseLogUtil.b("R_HealthLife_ReminderReceiver", "sendNotification isEnabled ", Boolean.valueOf(d));
        if (d) {
            if (i == 4000) {
                b = b(bcc.e("reticent_user_wake_up_two_day"));
            } else if (i == 4001) {
                b = b(bcc.e("reticent_user_wake_up_seven_day"));
            } else if (i == 3011) {
                a(i, iArr[1], i4);
                return;
            } else {
                if (i4 == 10 && !e(ayhVar, i2, i3)) {
                    LogUtil.a("HealthLife_ReminderReceiver", "sendNotification not need remind");
                    return;
                }
                b = bby.b(i4);
            }
            e(i, iArr[1], i4, b);
        }
    }

    private void a(final int i, final int i2, final int i3) {
        ReleaseLogUtil.b("R_HealthLife_ReminderReceiver", "sendMedicineNotify reminderId", Integer.valueOf(i), ",taskId ", Integer.valueOf(i3));
        axf.a(DateFormatUtil.b(System.currentTimeMillis()), new IBaseResponseCallback() { // from class: axu
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i4, Object obj) {
                ReminderReceiver.this.a(i, i2, i3, i4, obj);
            }
        });
    }

    public /* synthetic */ void a(int i, int i2, int i3, int i4, Object obj) {
        if (i4 != 0 || !(obj instanceof Map)) {
            ReleaseLogUtil.a("R_HealthLife_ReminderReceiver", "sendMedicineNotify errorCode ", Integer.valueOf(i4));
            return;
        }
        Map map = (Map) obj;
        LogUtil.a("HealthLife_ReminderReceiver", "sendMedicineNotify medicinesRuleBeans ", map.toString());
        int t = ((int) (this.d - jdl.t(this.d))) / 1000;
        List<MedicinesRule> list = (List) map.get(Integer.valueOf(t));
        if (koq.b(list)) {
            ReleaseLogUtil.a("R_HealthLife_ReminderReceiver", "sendMedicineNotify medicinesRuleBeans ", list, " medicineTime ", Integer.valueOf(t));
        } else {
            e(i, i2, i3, String.format(Locale.ENGLISH, this.e.getResources().getString(R$string.IDS_health_model_medicines_remind_1), d(list)));
        }
    }

    private String d(List<MedicinesRule> list) {
        String str;
        int size = list.size();
        String str2 = "";
        String name = size >= 1 ? list.get(0).getName() : "";
        if (size >= 2) {
            str2 = list.get(1).getName();
            str = String.format(Locale.ENGLISH, this.e.getResources().getString(R$string.IDS_medicines_remind_2), name, str2);
        } else {
            str = name;
        }
        if (size == 3) {
            return String.format(Locale.ENGLISH, this.e.getResources().getString(R$string.IDS_medicines_remind_3), name, str2, list.get(2).getName());
        }
        if (size > 3) {
            return String.format(Locale.ENGLISH, this.e.getResources().getString(R$string.IDS_health_model_medicines_remind_4), name, str2, list.get(2).getName());
        }
        LogUtil.c("HealthLife_ReminderReceiver", "sendMedicineNotify others");
        return str;
    }

    private boolean e(ayh ayhVar, int i, int i2) {
        long currentTimeMillis = System.currentTimeMillis();
        if (ayhVar != null) {
            List<wq> c = ayhVar.c();
            if (koq.c(c)) {
                long j = 0;
                long j2 = 0;
                for (wq wqVar : c) {
                    if (wqVar != null) {
                        long e = jdl.e(currentTimeMillis, wqVar.a(), wqVar.e());
                        j = Math.min(j, e);
                        j2 = Math.max(j2, e);
                    }
                }
                ReleaseLogUtil.b("R_HealthLife_ReminderReceiver", "needRemind mReminderTime ", Long.valueOf(this.d), " minReminderTime ", Long.valueOf(j), " maxReminderTime ", Long.valueOf(j2), " reminderTimeRange ", 900000);
                long j3 = this.d;
                if (j3 < j && j - j3 > 900000) {
                    return false;
                }
                if (j3 > j2 && j3 - j2 > 900000) {
                    return false;
                }
            }
        }
        HealthLifeBean a2 = auz.a(10, DateFormatUtil.b(currentTimeMillis), azi.p());
        if (!azi.j(a2)) {
            ReleaseLogUtil.b("R_HealthLife_ReminderReceiver", "needRemind bean ", a2);
            return true;
        }
        if (i2 == 0 || i == 0) {
            ReleaseLogUtil.a("R_HealthLife_ReminderReceiver", "needRemind reminderCurrentNumber ", Integer.valueOf(i2), " reminderTotalNumber ", Integer.valueOf(i));
            return false;
        }
        if (nsn.e(a2.getResult()) / i2 < nsn.e(a2.getTarget()) / i) {
            return true;
        }
        ReleaseLogUtil.b("R_HealthLife_ReminderReceiver", "needRemind don't need sendNotification");
        return false;
    }

    private String b(auc aucVar) {
        if (aucVar == null) {
            ReleaseLogUtil.a("R_HealthLife_ReminderReceiver", "getContentText interactiveSemanticBean is null");
            return "";
        }
        ArrayList<TextBean> a2 = aucVar.a();
        if (koq.b(a2)) {
            ReleaseLogUtil.a("R_HealthLife_ReminderReceiver", "getContentText textBeanList is empty");
            return "";
        }
        ArrayList<String> d = bcc.d(a2, (HashMap<String, String>) null);
        if (koq.b(d)) {
            ReleaseLogUtil.a("R_HealthLife_ReminderReceiver", "getContentText textList ", d);
            return "";
        }
        return d.get(nsg.b(d.size()));
    }

    private void e(int i, int i2, int i3, String str) {
        PendingIntent broadcast;
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.a("R_HealthLife_ReminderReceiver", "setNotify text ", str, " reminderId ", Integer.valueOf(i), " notificationId ", Integer.valueOf(i2), " taskId ", Integer.valueOf(i3));
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (a(currentTimeMillis)) {
            ReleaseLogUtil.a("R_HealthLife_ReminderReceiver", "setNotify isRepeatedNotification text ", str, " reminderId ", Integer.valueOf(i), " notificationId ", Integer.valueOf(i2), " taskId ", Integer.valueOf(i3));
            return;
        }
        jdh.c().a(i2);
        Notification.Builder xf_ = jdh.c().xf_();
        nsn.cLB_(xf_);
        if (Build.VERSION.SDK_INT >= 33) {
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("huaweischeme://healthapp/basicHealth?healthType=6&urlType=4&pName=com.huawei.health&id=" + i3));
            intent.setPackage(BaseApplication.d());
            broadcast = PendingIntent.getActivity(this.e, i, intent, 201326592);
        } else {
            Intent intent2 = new Intent(this.e, (Class<?>) NotificationReceiver.class);
            intent2.setPackage(BaseApplication.d());
            intent2.putExtra("id", i3);
            broadcast = PendingIntent.getBroadcast(this.e, i, intent2, 201326592);
        }
        xf_.setContentTitle(azi.i()).setContentText(str).setStyle(new Notification.BigTextStyle().bigText(str)).setTicker(str).setContentIntent(broadcast);
        xf_.setCategory("HwLifestyle01");
        xf_.setAutoCancel(true).setDefaults(1).setOngoing(false).setOnlyAlertOnce(true);
        if (d()) {
            LogUtil.a("HealthLife_ReminderReceiver", "setNotify isSupportDeviceRemind");
            xf_.setGroupSummary(true);
            ResponseMsgBody responseMsgBody = new ResponseMsgBody();
            responseMsgBody.setMsgType(2002);
            responseMsgBody.setNoticesBean(c(i3, str));
            avm.a().a(2, responseMsgBody);
        }
        glq.aNY_(i2, xf_, str);
        bao.e("health_model_remind_last_time_" + this.b, String.valueOf(currentTimeMillis));
        ReleaseLogUtil.b("R_HealthLife_ReminderReceiver", "setNotify reminderId ", Integer.valueOf(i), " notificationId ", Integer.valueOf(i2), " taskId ", Integer.valueOf(i3), " text ", str);
    }

    private boolean d() {
        boolean cf = CommonUtil.cf();
        boolean d = avm.a().d();
        boolean c = bby.c(BaseApplication.d());
        ReleaseLogUtil.b("R_HealthLife_ReminderReceiver", "isSupportDeviceRemind isSynergy91 ", Boolean.valueOf(cf), " isSupportCapability ", Boolean.valueOf(d), " notificationStatus ", Boolean.valueOf(c));
        return cf && d && c;
    }

    private List<NoticesBean> c(int i, String str) {
        NoticesBean noticesBean = new NoticesBean();
        if (i != 1 && azi.c(i)) {
            noticesBean.setTaskName(BaseApplication.e().getResources().getString(dsl.ZJ_().get(i)));
        } else {
            noticesBean.setTaskName(azi.i());
        }
        noticesBean.setRecordDay(DateFormatUtil.b(System.currentTimeMillis()));
        noticesBean.setTaskId(i);
        noticesBean.setMsg(str);
        ArrayList arrayList = new ArrayList();
        arrayList.add(noticesBean);
        return arrayList;
    }
}
