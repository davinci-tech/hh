package com.huawei.hiai.awareness.util;

import android.content.Context;
import android.os.RemoteException;
import com.huawei.hiai.awareness.service.AwarenessFence;

/* loaded from: classes8.dex */
public class ServiceCallUtils {
    private static final String TAG = "ServiceCallUtils";

    @FunctionalInterface
    public interface ServiceSupplier<T> {
        T filterRemoteExc() throws RemoteException;
    }

    private ServiceCallUtils() {
    }

    public static <T> T serviceCall(AwarenessFence awarenessFence, Context context, ServiceSupplier<T> serviceSupplier, T t) {
        LogUtil.i(TAG, "awarenessFence = " + awarenessFence);
        if (context != null && awarenessFence != null) {
            String packageName = context.getPackageName();
            if (packageName != null && packageName.contains("_")) {
                packageName = packageName.replace("_", "");
            }
            awarenessFence.setPackageName(packageName);
            awarenessFence.setTopKey(awarenessFence.getPackageName() + "_" + SystemUtil.parseAwareness2String(awarenessFence));
        }
        return (T) filterRemoteException(serviceSupplier, t);
    }

    private static <T> T filterRemoteException(ServiceSupplier<T> serviceSupplier, T t) {
        try {
            return serviceSupplier.filterRemoteExc();
        } catch (RemoteException unused) {
            LogUtil.e(TAG, "RemoteException error");
            return t;
        }
    }
}
