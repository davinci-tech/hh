package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import com.huawei.hihealth.ICommonCallback;
import com.huawei.hihealth.model.EventTypeInfo;
import com.huawei.hihealth.model.GoalInfo;
import com.huawei.hihealth.model.GoalStatus;
import com.huawei.hihealth.model.MetaData;
import com.huawei.hihealth.model.SampleEvent;
import com.huawei.hihealth.model.Subscriber;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes7.dex */
public class irf {
    public static void c(Context context, EventTypeInfo eventTypeInfo, Set<Subscriber> set) {
        int i;
        LogUtil.a("ObservableHelper", "all goals is satisfied");
        ArrayList arrayList = new ArrayList();
        int intValue = Integer.valueOf(new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime())).intValue();
        boolean z = eventTypeInfo instanceof GoalInfo;
        if (z) {
            arrayList.add(new MetaData("goalStatus", HiJsonUtil.e(Arrays.asList(new GoalStatus(intValue, intValue, ((GoalInfo) eventTypeInfo).getGoals())))));
            i = a(eventTypeInfo);
            if (HiDateUtil.c(System.currentTimeMillis()) <= i) {
                arrayList.add(new MetaData("expireDay", String.valueOf(i)));
                arrayList.add(new MetaData("startDay", String.valueOf(eventTypeInfo.getStartDay())));
            } else {
                ReleaseLogUtil.c("HiH_ObservableHelper", "broadEventSatisfied is expired, not add startTime");
            }
        } else {
            i = 0;
        }
        if (HiDateUtil.c(System.currentTimeMillis()) < eventTypeInfo.getStartDay()) {
            ReleaseLogUtil.d("HiH_ObservableHelper", "event info has not started yet");
            return;
        }
        if (z && HiDateUtil.c(System.currentTimeMillis()) > i) {
            ReleaseLogUtil.c("HiH_ObservableHelper", "event info is expired");
            for (final Subscriber subscriber : set) {
                try {
                    inv.c(context).d(eventTypeInfo, subscriber, new ICommonCallback.Stub() { // from class: irf.1
                        @Override // com.huawei.hihealth.ICommonCallback
                        public void onResult(int i2, String str) {
                            ReleaseLogUtil.e("HiH_ObservableHelper", "appId ", Subscriber.this.getAppId(), " unsubscribe result ", Integer.valueOf(i2));
                        }
                    }, null);
                } catch (RemoteException e) {
                    ReleaseLogUtil.c("HiH_ObservableHelper", "unregister expired event info error", LogAnonymous.b((Throwable) e));
                }
            }
            return;
        }
        b(context, eventTypeInfo, set, arrayList);
    }

    public static void b(Context context, EventTypeInfo eventTypeInfo, Set<Subscriber> set, List<MetaData> list) {
        LogUtil.a("ObservableHelper", "all goals is satisfied");
        if (HiDateUtil.c(System.currentTimeMillis()) < eventTypeInfo.getStartDay()) {
            ReleaseLogUtil.d("HiH_ObservableHelper", "event info has not started yet");
            return;
        }
        SampleEvent sampleEvent = new SampleEvent(eventTypeInfo.getType(), eventTypeInfo.getSubType(), eventTypeInfo.getSubscriptionId(), System.currentTimeMillis(), list);
        if (eventTypeInfo.getSubscriptionMode() != 2) {
            ReleaseLogUtil.e("HiH_ObservableHelper", "push to cloud");
            ipw.e(eventTypeInfo.getSubscriptionId(), sampleEvent);
        } else {
            ReleaseLogUtil.e("HiH_ObservableHelper", "subscriber size : ", Integer.valueOf(set.size()));
            e(context, eventTypeInfo, set, Integer.valueOf(new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime())).intValue(), sampleEvent);
        }
    }

    private static void e(Context context, EventTypeInfo eventTypeInfo, Set<Subscriber> set, int i, SampleEvent sampleEvent) {
        for (Subscriber subscriber : set) {
            if (!c(sampleEvent.getType()) || subscriber.getLastNotifyDate() != i) {
                Intent intent = new Intent(subscriber.getFilter().getAction());
                intent.setPackage(subscriber.getFilter().getPkgName());
                intent.putExtra("sampleEvent", sampleEvent);
                intent.putExtra("eventSignature", b(subscriber.getSecret(), eventTypeInfo.getOpenId(), eventTypeInfo.getType().getName(), eventTypeInfo.getSubType()));
                context.sendBroadcast(intent);
                LogUtil.a("ObservableHelper", "send broad cast done");
                subscriber.setLastNotifyDate(HiDateUtil.c(System.currentTimeMillis()));
                ipw.a(eventTypeInfo, subscriber);
            }
        }
    }

    public static void c(Context context, List<Subscriber> list, EventTypeInfo eventTypeInfo, String str) {
        SampleEvent sampleEvent = new SampleEvent(SampleEvent.Type.USER_EVENT, "SWITCH_USER", "", System.currentTimeMillis(), null);
        for (Subscriber subscriber : list) {
            if (subscriber.getSubscriberId().equals(str) && subscriber.getMode() == 2) {
                Intent intent = new Intent(subscriber.getFilter().getAction());
                intent.setPackage(subscriber.getFilter().getPkgName());
                intent.putExtra("sampleEvent", sampleEvent);
                intent.putExtra("eventSignature", b(subscriber.getSecret(), eventTypeInfo.getOpenId(), eventTypeInfo.getType().getName(), eventTypeInfo.getSubType()));
                context.sendBroadcast(intent);
                LogUtil.a("ObservableHelper", "send user change done.");
            }
        }
    }

    private static byte[] b(String str, String str2, String str3, String str4) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes(Charset.forName("UTF-8")), "HmacSHA256");
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);
            return mac.doFinal((str3 + "_" + str4 + "_" + str2).getBytes(Charset.forName("UTF-8")));
        } catch (InvalidKeyException e) {
            ReleaseLogUtil.c("HiH_ObservableHelper", "invalid key exception", LogAnonymous.b((Throwable) e));
            return new byte[0];
        } catch (NoSuchAlgorithmException e2) {
            ReleaseLogUtil.c("HiH_ObservableHelper", "no such algorithm", LogAnonymous.b((Throwable) e2));
            return new byte[0];
        }
    }

    private static int a(EventTypeInfo eventTypeInfo) {
        if (!(eventTypeInfo instanceof GoalInfo)) {
            return 0;
        }
        if (1 == ((GoalInfo) eventTypeInfo).getRecurrence().getUnit()) {
            return HiDateUtil.c(HiDateUtil.a(eventTypeInfo.getStartDay()) + (r0.getCount() * 86400000));
        }
        return 0;
    }

    private static boolean c(SampleEvent.Type type) {
        return type == SampleEvent.Type.SCENARIO_GOAL_EVENT;
    }
}
