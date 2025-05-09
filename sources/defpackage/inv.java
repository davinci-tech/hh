package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.ICommonCallback;
import com.huawei.hihealth.model.EventTypeInfo;
import com.huawei.hihealth.model.Goal;
import com.huawei.hihealth.model.GoalInfo;
import com.huawei.hihealth.model.HealthAlarmInfo;
import com.huawei.hihealth.model.MetricGoal;
import com.huawei.hihealth.model.SampleEvent;
import com.huawei.hihealth.model.SportStatusInfo;
import com.huawei.hihealth.model.Subscriber;
import com.huawei.hihealth.model.Subscription;
import com.huawei.hwcommonmodel.application.BaseApplication;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes.dex */
public class inv {

    /* renamed from: a, reason: collision with root package name */
    private static Context f13504a;
    private Timer e;

    private inv() {
    }

    /* loaded from: classes7.dex */
    static class a {
        private static final inv c = new inv();
    }

    public static inv c(Context context) {
        f13504a = context;
        return a.c;
    }

    public void b(Subscriber subscriber, EventTypeInfo eventTypeInfo, ICommonCallback iCommonCallback, irc ircVar) throws RemoteException {
        int a2;
        int d = d(subscriber, eventTypeInfo);
        if (d != 0) {
            d(eventTypeInfo, subscriber, new ICommonCallback.Stub() { // from class: inv.1
                @Override // com.huawei.hihealth.ICommonCallback
                public void onResult(int i, String str) {
                    ReleaseLogUtil.e("R_SubscribeProxy", "unsubscribe part type result ", Integer.valueOf(i));
                }
            }, null);
            iCommonCallback.onResult(d, ipd.b(d));
            a(d, ircVar);
            return;
        }
        int i = AnonymousClass2.d[eventTypeInfo.getType().ordinal()];
        if (i != 1) {
            a2 = 2;
            if (i == 2) {
                a2 = c(subscriber, (HealthAlarmInfo) eventTypeInfo);
            } else if (i == 3) {
                a2 = b(subscriber, (SportStatusInfo) eventTypeInfo);
            } else {
                iCommonCallback.onResult(2, ipd.b(2));
                a(2, ircVar);
            }
        } else {
            a2 = a(subscriber, (GoalInfo) eventTypeInfo);
        }
        if (a2 == 0) {
            ReleaseLogUtil.e("R_SubscribeProxy", "subscribe success");
            ire.i();
            iqh.getInstance(f13504a).getDataAndReportNow();
        } else {
            ReleaseLogUtil.d("R_SubscribeProxy", "subscribe fail, delete subscribed result");
            d(eventTypeInfo, subscriber, new ICommonCallback.Stub() { // from class: inv.4
                @Override // com.huawei.hihealth.ICommonCallback
                public void onResult(int i2, String str) {
                    ReleaseLogUtil.e("R_SubscribeProxy", "unsubscribe part type result ", Integer.valueOf(i2));
                }
            }, null);
        }
        iCommonCallback.onResult(a2, ipd.b(a2));
        a(a2, ircVar);
    }

    /* renamed from: inv$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] d;

        static {
            int[] iArr = new int[SampleEvent.Type.values().length];
            d = iArr;
            try {
                iArr[SampleEvent.Type.SCENARIO_GOAL_EVENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                d[SampleEvent.Type.HEALTH_ALARM_EVENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                d[SampleEvent.Type.SPORT_STATUS_EVENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public void d(EventTypeInfo eventTypeInfo, Subscriber subscriber, ICommonCallback iCommonCallback, irc ircVar) throws RemoteException {
        if ("com.huawei.hiai".equals(iwi.a(f13504a))) {
            eventTypeInfo.setOpenId(new irr().c());
        }
        try {
            int i = AnonymousClass2.d[eventTypeInfo.getType().ordinal()];
            if (i == 1) {
                a(subscriber, (GoalInfo) eventTypeInfo, iCommonCallback, ircVar);
            } else if (i == 2) {
                e(subscriber, (HealthAlarmInfo) eventTypeInfo, iCommonCallback, ircVar);
            } else if (i == 3) {
                b(subscriber, (SportStatusInfo) eventTypeInfo, iCommonCallback, ircVar);
            } else {
                ReleaseLogUtil.e("R_SubscribeProxy", "wrong type");
                iCommonCallback.onResult(2, ipd.b(2));
            }
        } catch (ClassCastException e) {
            ReleaseLogUtil.d("R_SubscribeProxy", "class cast exception: ", e.getMessage());
            iCommonCallback.onResult(2, ipd.b(2));
        }
    }

    public void e() {
        LogUtil.d("SubscribeProxy", "enter autoRegisterSubscription.");
        ThreadPoolManager.d().execute(new Runnable() { // from class: inu
            @Override // java.lang.Runnable
            public final void run() {
                inv.this.c();
            }
        });
    }

    /* synthetic */ void c() {
        irr irrVar = new irr();
        int b = irrVar.b();
        if (b != 0 && b != igm.e().d()) {
            ReleaseLogUtil.e("R_SubscribeProxy", "user changed, begin downloadSubscription.");
            c(BaseApplication.getContext()).d();
            irrVar.d(igm.e().d());
            b();
            return;
        }
        irrVar.d(igm.e().d());
        Long valueOf = Long.valueOf(System.currentTimeMillis());
        Long d = irrVar.d();
        if (valueOf.longValue() - d.longValue() < 86400000) {
            ReleaseLogUtil.e("R_SubscribeProxy", "No more than 24 hours since the last synchronization.");
            ipw.c(irrVar.a());
            return;
        }
        if ((valueOf.longValue() - d.longValue()) / 8.64E7d >= 3.0d) {
            ReleaseLogUtil.e("R_SubscribeProxy", "more than 3 days since the last synchronization.");
            b();
            return;
        }
        long longValue = (d.longValue() + i()) - valueOf.longValue();
        if (longValue <= 0) {
            ReleaseLogUtil.e("R_SubscribeProxy", "downloadSubscription immediately.");
            b();
        } else {
            ReleaseLogUtil.e("R_SubscribeProxy", "downloadSubscription delay ", Long.valueOf(longValue));
            b(longValue);
            ipw.c(irrVar.a());
        }
    }

    public void a() {
        iqi.getInstance(f13504a).clearSubscribeEvent();
        iqh.getInstance(f13504a).clearSubscribeEvent();
    }

    public void d() {
        List<Subscriber> d = ipw.d();
        ReleaseLogUtil.e("R_SubscribeProxy", "broadUserChange size:", Integer.valueOf(d.size()));
        List<Subscription> a2 = new irr().a();
        HashMap hashMap = new HashMap(16);
        for (Subscription subscription : a2) {
            if (hashMap.get(subscription.getSubscriberId()) == null) {
                hashMap.put(subscription.getSubscriberId(), true);
                EventTypeInfo e = ipw.e(subscription);
                if (e != null) {
                    irf.c(f13504a, d, e, subscription.getSubscriberId());
                }
            }
        }
    }

    public void bBR_(Intent intent) {
        ReleaseLogUtil.e("R_SubscribeProxy", "device connection state changed.");
        Parcelable parcelableExtra = intent.getParcelableExtra("deviceinfo");
        if (!(parcelableExtra instanceof DeviceInfo)) {
            ReleaseLogUtil.d("R_SubscribeProxy", "current device model is wrong.");
            return;
        }
        DeviceInfo deviceInfo = (DeviceInfo) parcelableExtra;
        if (ipu.hasObservableInit()) {
            ipu.getInstance(f13504a).notifyConnectionStateChange(deviceInfo);
        }
        if (iqm.hasObservableInit()) {
            iqm.getInstance(f13504a).notifyConnectionStateChange(deviceInfo);
        }
    }

    private int a(Subscriber subscriber, GoalInfo goalInfo) {
        boolean z = true;
        for (Goal goal : goalInfo.getGoals()) {
            if ((goal instanceof MetricGoal) && iqi.containDataType(goal.getDataType())) {
                ReleaseLogUtil.e("R_SubscribeProxy", "enter openSdk observable, register goalType is:", Integer.valueOf(goal.getDataType()));
                z = iqi.getInstance(f13504a).registerObserver((EventTypeInfo) goalInfo, subscriber);
            } else if (iqh.supportType(goal.getDataType())) {
                ReleaseLogUtil.e("R_SubscribeProxy", "enter platform observable, register goalType is:", Integer.valueOf(goal.getDataType()));
                z = iqh.getInstance(f13504a).registerObserver((Parcelable) goalInfo, subscriber);
            } else {
                LogUtil.c("SubscribeProxy", ipd.b(2));
                z = false;
            }
            if (!z) {
                break;
            }
        }
        return z ? 0 : 2;
    }

    private void a(Subscriber subscriber, GoalInfo goalInfo, ICommonCallback iCommonCallback, irc ircVar) throws RemoteException {
        boolean z = true;
        for (Goal goal : goalInfo.getGoals()) {
            if ((goal instanceof MetricGoal) && iqi.containDataType(goal.getDataType())) {
                ReleaseLogUtil.e("R_SubscribeProxy", "enter opensdk observable");
                z = iqi.getInstance(f13504a).unregisterObserver((EventTypeInfo) goalInfo, subscriber);
            } else if (iqh.supportType(goal.getDataType())) {
                ReleaseLogUtil.e("R_SubscribeProxy", "enter platform observable");
                z = iqh.getInstance(f13504a).unregisterObserver((EventTypeInfo) goalInfo, subscriber);
            } else {
                LogUtil.c("SubscribeProxy", ipd.b(2));
                z = false;
            }
            if (!z) {
                break;
            }
        }
        b(subscriber, goalInfo, iCommonCallback, ircVar, z);
    }

    private int c(Subscriber subscriber, HealthAlarmInfo healthAlarmInfo) {
        ReleaseLogUtil.e("R_SubscribeProxy", "enter device observable");
        return ipu.getInstance(f13504a).registerObserver((Parcelable) healthAlarmInfo, subscriber) ? 0 : 2;
    }

    private void e(Subscriber subscriber, HealthAlarmInfo healthAlarmInfo, ICommonCallback iCommonCallback, irc ircVar) throws RemoteException {
        b(subscriber, healthAlarmInfo, iCommonCallback, ircVar, ipu.getInstance(f13504a).unregisterObserver((EventTypeInfo) healthAlarmInfo, subscriber));
    }

    private int b(Subscriber subscriber, SportStatusInfo sportStatusInfo) {
        ReleaseLogUtil.e("R_SubscribeProxy", "enter sport status observable");
        return iqm.getInstance(f13504a).registerObserver((Parcelable) sportStatusInfo, subscriber) ? 0 : 2;
    }

    private void b(Subscriber subscriber, SportStatusInfo sportStatusInfo, ICommonCallback iCommonCallback, irc ircVar) throws RemoteException {
        b(subscriber, sportStatusInfo, iCommonCallback, ircVar, iqm.getInstance(f13504a).unregisterObserver((EventTypeInfo) sportStatusInfo, subscriber));
    }

    private void a(int i, irc ircVar) {
        if (ircVar == null) {
            return;
        }
        ircVar.c().b(i);
        ircVar.d();
    }

    private void b(Subscriber subscriber, EventTypeInfo eventTypeInfo, ICommonCallback iCommonCallback, irc ircVar, boolean z) throws RemoteException {
        ReleaseLogUtil.e("R_SubscribeProxy", "reportUnRegisterResult isSuccess:", Boolean.valueOf(z));
        if (z) {
            int c = c(subscriber, eventTypeInfo);
            ReleaseLogUtil.e("R_SubscribeProxy", "deleteSubscription errCode is:", Integer.valueOf(c));
            iCommonCallback.onResult(c, ipd.b(c));
            a(c, ircVar);
            return;
        }
        ReleaseLogUtil.d("R_SubscribeProxy", "wrong parameter when unsubscribe");
        iCommonCallback.onResult(2, ipd.b(2));
        a(2, ircVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        ipw.b((List<String>) Arrays.asList(SampleEvent.Type.SCENARIO_GOAL_EVENT.getName(), SampleEvent.Type.HEALTH_ALARM_EVENT.getName()), (List<String>) Arrays.asList(String.valueOf(2)), (List<String>) null);
    }

    private int i() {
        return (new SecureRandom().nextInt(71) + 1) * 3600000;
    }

    private void b(long j) {
        if (this.e != null) {
            return;
        }
        Timer timer = new Timer("SubscribeProxy");
        this.e = timer;
        timer.schedule(new TimerTask() { // from class: inv.3
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                inv.this.b();
            }
        }, j);
        LogUtil.d("SubscribeProxy", "start background sync timer.");
    }

    private int d(Subscriber subscriber, EventTypeInfo eventTypeInfo) {
        if (eventTypeInfo instanceof SportStatusInfo) {
            b(subscriber, eventTypeInfo);
            return 0;
        }
        if (TextUtils.isEmpty(eventTypeInfo.getSubscriptionId())) {
            return ipw.b(subscriber, eventTypeInfo);
        }
        return 0;
    }

    private int c(Subscriber subscriber, EventTypeInfo eventTypeInfo) {
        if (eventTypeInfo instanceof SportStatusInfo) {
            a(subscriber, eventTypeInfo);
            return 0;
        }
        if (!TextUtils.isEmpty(eventTypeInfo.getSubscriptionId())) {
            return 0;
        }
        ReleaseLogUtil.e("R_SubscribeProxy", "reportUnRegisterResult eventInfo:", moj.e(eventTypeInfo));
        return ipw.e(eventTypeInfo, subscriber);
    }

    private void b(Subscriber subscriber, EventTypeInfo eventTypeInfo) {
        Subscription e = e(subscriber, eventTypeInfo);
        irr irrVar = new irr();
        List<Subscription> a2 = irrVar.a();
        Iterator<Subscription> it = a2.iterator();
        while (it.hasNext()) {
            if (it.next().equals(e)) {
                return;
            }
        }
        a2.add(e);
        irrVar.e(a2);
    }

    private void a(Subscriber subscriber, EventTypeInfo eventTypeInfo) {
        Subscription e = e(subscriber, eventTypeInfo);
        irr irrVar = new irr();
        List<Subscription> a2 = irrVar.a();
        Iterator<Subscription> it = a2.iterator();
        while (it.hasNext()) {
            if (it.next().equals(e)) {
                it.remove();
            }
        }
        irrVar.e(a2);
    }

    private Subscription e(Subscriber subscriber, EventTypeInfo eventTypeInfo) {
        Subscription subscription = new Subscription(eventTypeInfo.getType(), eventTypeInfo.getSubType(), eventTypeInfo.getOpenId(), eventTypeInfo.getStartDay());
        subscription.setSubscriptionMode(eventTypeInfo.getSubscriptionMode());
        subscription.setAppId(subscriber.getAppId());
        subscription.setSubscriberId(subscriber.getSubscriberId());
        return subscription;
    }
}
