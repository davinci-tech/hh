package defpackage;

import android.os.RemoteCallbackList;
import android.os.RemoteException;
import com.huawei.hihealth.ISubScribeCallback;
import com.huawei.hihealth.model.Notification;
import com.huawei.hihealthservice.hihealthkit.model.DataObservable;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Arrays;
import java.util.Iterator;

/* loaded from: classes7.dex */
public class iqn extends DataObservable<ISubScribeCallback> {
    private void reportData(Notification notification, RemoteCallbackList<ISubScribeCallback> remoteCallbackList) {
        synchronized (remoteCallbackList) {
            try {
                int beginBroadcast = remoteCallbackList.beginBroadcast();
                LogUtil.a("SubscribeDataObservable", "calling remote callback, the length is ", Integer.valueOf(beginBroadcast));
                for (int i = 0; i < beginBroadcast; i++) {
                    try {
                        remoteCallbackList.getBroadcastItem(i).onDataChanged(notification);
                    } catch (RemoteException e) {
                        ReleaseLogUtil.c("HiH_SubscribeDataObservable", "report RemoteException", LogAnonymous.b((Throwable) e));
                    }
                }
                remoteCallbackList.finishBroadcast();
            } catch (IllegalStateException e2) {
                ReleaseLogUtil.c("HiH_SubscribeDataObservable", "report IllegalStateException", LogAnonymous.b((Throwable) e2));
            }
        }
    }

    @Override // com.huawei.hihealthservice.hihealthkit.model.DataObservable
    public void notifyDataChanged(Object obj) {
        if (!(obj instanceof Notification)) {
            ReleaseLogUtil.d("HiH_SubscribeDataObservable", "wrong data type");
            return;
        }
        Notification notification = (Notification) obj;
        for (String str : this.mObservers.keySet()) {
            if (Arrays.asList(str.split("#")).contains(String.valueOf(notification.getDataType()))) {
                reportData(notification, (RemoteCallbackList) this.mObservers.get(str));
            }
        }
    }

    public boolean isTypeSubscribed(int i) {
        Iterator<String> it = this.mObservers.keySet().iterator();
        while (it.hasNext()) {
            if (Arrays.asList(it.next().split("#")).contains(String.valueOf(i))) {
                return true;
            }
        }
        return false;
    }
}
