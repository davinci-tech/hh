package com.huawei.agconnect.remoteconfig;

import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.common.api.Logger;
import com.huawei.agconnect.remoteconfig.internal.a;
import com.huawei.hmf.tasks.Task;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public abstract class AGConnectConfig {
    private static final Map<String, AGConnectConfig> SERVICES_MAP = new HashMap();
    private static final String TAG = "AGConnectConfig";

    /* loaded from: classes8.dex */
    public static final class DEFAULT {
        public static final boolean BOOLEAN_VALUE = false;
        public static final byte[] BYTE_ARRAY_VALUE = new byte[0];
        public static final double DOUBLE_VALUE = 0.0d;
        public static final long LONG_VALUE = 0;
        public static final String STRING_VALUE = "";
    }

    /* loaded from: classes8.dex */
    public enum SOURCE {
        STATIC,
        DEFAULT,
        REMOTE
    }

    public abstract void apply(ConfigValues configValues);

    public abstract void applyDefault(int i);

    public abstract void applyDefault(Map<String, Object> map);

    public abstract void clearAll();

    public abstract Task<ConfigValues> fetch();

    public abstract Task<ConfigValues> fetch(long j);

    public abstract Task<ConfigValues> fetchDiscrete(long j, long j2);

    public abstract Map<String, String> getCustomAttributes();

    public abstract boolean getEnableCollectUserPrivacy();

    public abstract Map<String, Object> getMergedAll();

    public abstract SOURCE getSource(String str);

    public abstract Boolean getValueAsBoolean(String str);

    public abstract byte[] getValueAsByteArray(String str);

    public abstract Double getValueAsDouble(String str);

    public abstract Long getValueAsLong(String str);

    public abstract String getValueAsString(String str);

    public abstract ConfigValues loadLastFetched();

    public abstract void setCustomAttributes(Map<String, String> map);

    public abstract void setDeveloperMode(boolean z);

    public abstract void setEnableCollectUserPrivacy(boolean z);

    public static AGConnectConfig getInstance(AGConnectInstance aGConnectInstance) {
        if (aGConnectInstance == null) {
            throw new NullPointerException("AGConnectInstance can not be null.");
        }
        AGConnectConfig aGConnectConfig = (AGConnectConfig) aGConnectInstance.getService(a.class);
        if (aGConnectConfig == null) {
            synchronized (AGConnectConfig.class) {
                Map<String, AGConnectConfig> map = SERVICES_MAP;
                AGConnectConfig aGConnectConfig2 = map.get(aGConnectInstance.getIdentifier());
                if (aGConnectConfig2 == null) {
                    Logger.i(TAG, "init config with constructor.");
                    aGConnectConfig2 = new a(aGConnectInstance.getContext(), aGConnectInstance);
                    map.put(aGConnectInstance.getIdentifier(), aGConnectConfig2);
                }
                aGConnectConfig = aGConnectConfig2;
            }
        }
        return aGConnectConfig;
    }

    public static AGConnectConfig getInstance() {
        AGConnectInstance aGConnectInstance = AGConnectInstance.getInstance();
        if (aGConnectInstance != null) {
            return getInstance(aGConnectInstance);
        }
        throw new NullPointerException("The AGConnect SDK is not initialized. Please call the 'AGConnectInstance.initialize()' method first.");
    }
}
