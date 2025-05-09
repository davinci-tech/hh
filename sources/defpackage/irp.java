package defpackage;

import android.content.Context;
import android.os.RemoteException;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.ISubScribeCallback;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hihealth.model.Notification;
import com.huawei.hihealth.model.SubscribeModel;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes7.dex */
public class irp {
    private static volatile ConcurrentMap<Integer, Integer> c = new ConcurrentHashMap();
    private static volatile irp d;
    private final Context e;
    private final Object b = new Object();

    /* renamed from: a, reason: collision with root package name */
    private final iqn f13558a = new iqn();

    private irp(Context context) {
        this.e = context.getApplicationContext();
    }

    public static irp d(Context context) {
        if (d == null) {
            synchronized (irn.class) {
                if (d == null) {
                    d = new irp(context);
                }
            }
        }
        return d;
    }

    private void a(SubscribeModel subscribeModel, ISubScribeCallback iSubScribeCallback) {
        synchronized (this.b) {
            this.f13558a.registerObserver(subscribeModel, iSubScribeCallback);
        }
    }

    private void e(SubscribeModel subscribeModel, ISubScribeCallback iSubScribeCallback) {
        synchronized (this.b) {
            this.f13558a.unregisterObserver(subscribeModel, iSubScribeCallback);
        }
    }

    public void a(SubscribeModel subscribeModel, ISubScribeCallback iSubScribeCallback, irc ircVar) {
        a(subscribeModel, iSubScribeCallback);
        ArrayList arrayList = new ArrayList(subscribeModel.getDataTypes().length);
        ArrayList arrayList2 = new ArrayList(subscribeModel.getDataTypes().length);
        for (int i : subscribeModel.getDataTypes()) {
            Integer valueOf = Integer.valueOf(i);
            if (c.containsKey(valueOf)) {
                arrayList2.add(valueOf);
            } else {
                arrayList.add(valueOf);
            }
        }
        ReleaseLogUtil.e("HiH_SubscribeDataUtil", "hasSubscribedTypes = ", arrayList2.toString(), " tobeSubscribedTypes = ", arrayList.toString());
        if (arrayList.isEmpty()) {
            ReleaseLogUtil.e("HiH_SubscribeDataUtil", "has subscribed before");
            c(true, (List<Integer>) arrayList2, (List<Integer>) arrayList, iSubScribeCallback);
            ircVar.c(arrayList2, arrayList);
            return;
        }
        a(arrayList2, arrayList, iSubScribeCallback, ircVar);
    }

    private void a(final List<Integer> list, final List<Integer> list2, final ISubScribeCallback iSubScribeCallback, final irc ircVar) {
        HiHealthNativeApi.a(this.e).subscribeHiHealthData(list2, new HiSubscribeListener() { // from class: irp.3
            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onResult(List<Integer> list3, List<Integer> list4) {
                if (list3 == null || list3.isEmpty()) {
                    ReleaseLogUtil.d("HiH_SubscribeDataUtil", "all fail subscribe");
                    irp.this.c(true, (List<Integer>) new ArrayList(), (List<Integer>) list2, iSubScribeCallback);
                    ircVar.c(new ArrayList(), list2);
                    return;
                }
                ReleaseLogUtil.d("HiH_SubscribeDataUtil", "subscribe fail list ", list4.toString());
                list2.removeAll(list4);
                for (int i = 0; i < list3.size(); i++) {
                    irp.c.put((Integer) list2.get(i), list3.get(i));
                }
                list.addAll(list2);
                irp.this.c(true, (List<Integer>) list, list4, iSubScribeCallback);
                ircVar.c(list, list4);
            }

            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
                irp.this.f13558a.notifyDataChanged(new Notification(i, 0, str));
            }
        });
    }

    public void c(SubscribeModel subscribeModel, ISubScribeCallback iSubScribeCallback, irc ircVar) {
        e(subscribeModel, iSubScribeCallback);
        ArrayList arrayList = new ArrayList(subscribeModel.getDataTypes().length);
        ArrayList arrayList2 = new ArrayList(subscribeModel.getDataTypes().length);
        for (int i : subscribeModel.getDataTypes()) {
            Integer valueOf = Integer.valueOf(i);
            if (this.f13558a.isTypeSubscribed(valueOf.intValue())) {
                arrayList2.add(valueOf);
            } else {
                arrayList.add(valueOf);
            }
        }
        ReleaseLogUtil.e("HiH_SubscribeDataUtil", "hasUnsubscribedTypes: ", arrayList2.toString(), " toUnsubscribeTypes :", arrayList.toString());
        if (arrayList2.size() == subscribeModel.getDataTypes().length) {
            ReleaseLogUtil.e("HiH_SubscribeDataUtil", "no need to unsubscribe from platform");
            c(false, (List<Integer>) arrayList2, (List<Integer>) arrayList, iSubScribeCallback);
            ircVar.c(arrayList2, arrayList);
            return;
        }
        c(arrayList2, arrayList, iSubScribeCallback, ircVar);
    }

    private void c(final List<Integer> list, final List<Integer> list2, final ISubScribeCallback iSubScribeCallback, final irc ircVar) {
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = list2.iterator();
        while (it.hasNext()) {
            Integer next = it.next();
            if (c.containsKey(next)) {
                arrayList.add(c.get(next));
            } else {
                list.add(next);
                it.remove();
            }
        }
        HiHealthNativeApi.a(this.e).unSubscribeHiHealthData(arrayList, new HiUnSubscribeListener() { // from class: irq
            @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
            public final void onResult(boolean z) {
                irp.this.d(list, list2, iSubScribeCallback, ircVar, z);
            }
        });
    }

    /* synthetic */ void d(List list, List list2, ISubScribeCallback iSubScribeCallback, irc ircVar, boolean z) {
        if (z) {
            ReleaseLogUtil.e("HiH_SubscribeDataUtil", "unsubscribe success");
            list.addAll(list2);
            c(false, (List<Integer>) list, (List<Integer>) new ArrayList(), iSubScribeCallback);
            Iterator it = list2.iterator();
            while (it.hasNext()) {
                c.remove((Integer) it.next());
            }
            ircVar.c((List<Integer>) list, new ArrayList());
            return;
        }
        ReleaseLogUtil.e("HiH_SubscribeDataUtil", "unsubscribe fail");
        c(false, (List<Integer>) list, (List<Integer>) list2, iSubScribeCallback);
        ircVar.c((List<Integer>) list, (List<Integer>) list2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z, List<Integer> list, List<Integer> list2, ISubScribeCallback iSubScribeCallback) {
        ReleaseLogUtil.e("HiH_SubscribeDataUtil", "subscribeOnResult: isSubscribeTag = ", Boolean.valueOf(z), " successTypes = ", list.toString(), " dataTypes = ", list2.toString());
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            Notification notification = new Notification(it.next().intValue(), 0, ipd.b(0));
            notification.setSubscribeTag(z);
            arrayList.add(notification);
        }
        Iterator<Integer> it2 = list2.iterator();
        while (it2.hasNext()) {
            Notification notification2 = new Notification(it2.next().intValue(), 4, ipd.b(4));
            notification2.setSubscribeTag(z);
            arrayList2.add(notification2);
        }
        try {
            iSubScribeCallback.onResult(arrayList, arrayList2);
        } catch (RemoteException e) {
            ReleaseLogUtil.c("HiH_SubscribeDataUtil", "subscribeOnResult RemoteException: ", e.getMessage());
        }
    }
}
