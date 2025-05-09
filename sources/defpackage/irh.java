package defpackage;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.huawei.health.ITrackDataForDeveloper;
import com.huawei.hihealth.ICommonCallback;
import com.huawei.hihealth.IRealTimeDataCallback;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes7.dex */
public class irh {
    private static final Object b = new Object();
    private static HashMap<String, List<iqf>> c = new HashMap<>(16);
    private static List<iqf> d = new ArrayList();

    public static void e(String str, int i, String str2, IRealTimeDataCallback iRealTimeDataCallback, ITrackDataForDeveloper iTrackDataForDeveloper) {
        synchronized (b) {
            if (c.containsKey(str)) {
                List<iqf> list = c.get(str);
                d = list;
                if (list.size() == 0) {
                    d(str, i, str2, iRealTimeDataCallback, iTrackDataForDeveloper);
                } else if (d.size() > 0) {
                    b(i, str2, str, iRealTimeDataCallback, iTrackDataForDeveloper);
                }
            } else {
                d(str, i, str2, iRealTimeDataCallback, iTrackDataForDeveloper);
            }
        }
    }

    private static void b(int i, String str, String str2, IRealTimeDataCallback iRealTimeDataCallback, ITrackDataForDeveloper iTrackDataForDeveloper) {
        boolean z = false;
        for (int i2 = 0; i2 < d.size(); i2++) {
            if (str.equals(d.get(i2).getPermissionTypeName())) {
                z = true;
            }
        }
        if (z) {
            return;
        }
        d(str2, i, str, iRealTimeDataCallback, iTrackDataForDeveloper);
    }

    private static void d(String str, int i, String str2, IRealTimeDataCallback iRealTimeDataCallback, ITrackDataForDeveloper iTrackDataForDeveloper) {
        synchronized (b) {
            d.add(new iqf(i, str2, iRealTimeDataCallback, iTrackDataForDeveloper));
            c.put(str, d);
        }
    }

    public static void e(String str, String str2) {
        synchronized (b) {
            if (c.size() > 0 && c.containsKey(str)) {
                List<iqf> list = c.get(str);
                if (list.size() == 0) {
                    return;
                }
                for (int size = list.size() - 1; size >= 0; size--) {
                    if (str2.equals(list.get(size).getPermissionTypeName())) {
                        list.remove(size);
                    }
                }
            }
        }
    }

    public static void a(int i, String str, Context context) {
        LogUtil.a("HiHealthPermissionTypeUtil", "stopRealingTime scopeId = ", Integer.valueOf(i), " packageName = ", str);
        synchronized (b) {
            if (c.containsKey(str)) {
                List<iqf> list = c.get(str);
                if (list.size() == 0) {
                    return;
                }
                int size = list.size() - 1;
                while (true) {
                    if (size < 0) {
                        break;
                    }
                    if (list.get(size).getPermissionTypeId() == i) {
                        e(list, size, i, str, context);
                        break;
                    }
                    size--;
                }
            }
        }
    }

    public static void e(List<iqf> list, int i, int i2, String str, Context context) {
        ICommonCallback iCommonCallback = new ICommonCallback() { // from class: irh.4
            @Override // android.os.IInterface
            public IBinder asBinder() {
                return null;
            }

            @Override // com.huawei.hihealth.ICommonCallback
            public void onResult(int i3, String str2) throws RemoteException {
            }
        };
        if (i2 == 50001) {
            ins.a(context).m(str, list.get(i).getCallback());
            ins.a(context).o(str, list.get(i).getCallback());
        } else if (i2 == 101003) {
            ino.b(context).d(list.get(i).getTrackDataForDeveloper(), iCommonCallback);
        } else if (i2 == 101202) {
            ins.a(context).k(str, list.get(i).getCallback());
        } else {
            LogUtil.a("HiHealthPermissionTypeUtil", "stopRealingTimeData none");
        }
        list.remove(i);
    }
}
