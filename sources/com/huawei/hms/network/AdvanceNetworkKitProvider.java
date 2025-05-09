package com.huawei.hms.network;

import android.content.Context;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.api.advance.AdvanceNetworkKit;

/* loaded from: classes9.dex */
public abstract class AdvanceNetworkKitProvider extends BaseProvider {
    private static final String DEFAULT_ADVANCE_PROVIDER = "com.huawei.hms.network.httpclient.DefaultAdvanceNetworkKitProvider";
    private static final Object LOCK = new Object();
    private static final String TAG = "AdvanceNetworkKitProvider";
    private static volatile AdvanceNetworkKitProvider enableProvider;

    public abstract AdvanceNetworkKit createAdvanceNetworkKit();

    private static AdvanceNetworkKitProvider getAdvanceProvider(Context context) {
        if (DynamicLoaderHelper.getInstance().getDynamicStatus()) {
            return addHmsAdvanceProvider();
        }
        return addAdvanceProviderByClass(context);
    }

    private static AdvanceNetworkKitProvider addHmsAdvanceProvider() {
        Context remoteContext = NetworkKit.getRemoteInitializer().getRemoteContext();
        if (remoteContext == null) {
            throw new IllegalStateException("Unable to find AdvanceNetworkKit provider");
        }
        return addAdvanceProviderByClass(remoteContext);
    }

    private static AdvanceNetworkKitProvider addAdvanceProviderByClass(Context context) {
        try {
            return (AdvanceNetworkKitProvider) context.getClassLoader().loadClass(DEFAULT_ADVANCE_PROVIDER).asSubclass(AdvanceNetworkKitProvider.class).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            Logger.e(TAG, "com.huawei.hms.network.httpclient.DefaultAdvanceNetworkKitProvider not found", e);
            return null;
        }
    }

    public static AdvanceNetworkKitProvider getEnableProvider() {
        NetworkKit.getInstance();
        synchronized (LOCK) {
            if (enableProvider != null) {
                return enableProvider;
            }
            enableProvider = getAdvanceProvider(ContextHolder.getAppContext());
            if (enableProvider == null) {
                throw new IllegalStateException("Unable to find AdvanceNetworkKit provider");
            }
            Logger.i(TAG, "AdvanceNetworkKitProvider is dynamic provider result : %s", Boolean.valueOf(enableProvider.isDynamicProvider()));
            return enableProvider;
        }
    }
}
