package com.huawei.agconnect.common.api;

import android.text.TextUtils;
import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.datastore.annotation.DefaultCrypto;
import com.huawei.agconnect.datastore.core.SharedPrefUtil;
import com.huawei.agconnect.exception.AGCException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;

/* loaded from: classes2.dex */
public class RequestThrottle {
    private static final String TAG = "AGC_THROTTLE";
    private final Map<String, Throttle> throttleMap = new HashMap();
    private static final Object LOCK = new Object();
    private static final RequestThrottle INSTANCE = new RequestThrottle();

    public static class Throttle {
        private static final Object LOCK = new Object();
        private static final int MAX_REQUEST_COUNT_IN_INTERVAL = 10;
        private static final String SP_FILE_NAME = "com.huawei.agc.throttle";
        private static final long THROTTLE_INTERVAL_TIME = 3600000;
        private final ArrayDeque<Long> countDeque = new ArrayDeque<>();
        private Integer failCount;
        private Long failTime;
        private final String name;

        @Deprecated
        public void setDeveloperMode(boolean z) {
        }

        public long getEndTime() {
            synchronized (LOCK) {
                if (this.countDeque.size() >= 10 && System.currentTimeMillis() - getFirst() < 3600000) {
                    return (getFirst() + 3600000) - System.currentTimeMillis();
                }
                if (this.failCount.intValue() > 1 && this.failTime.longValue() > 0) {
                    long pow = ((long) Math.pow(2.0d, Math.min(this.failCount.intValue() - 1, 8))) * 60000;
                    if (System.currentTimeMillis() < this.failTime.longValue() + pow) {
                        return (this.failTime.longValue() + pow) - System.currentTimeMillis();
                    }
                }
                return 0L;
            }
        }

        public boolean checkFail(Exception exc) {
            if (exc instanceof AGCException) {
                AGCException aGCException = (AGCException) exc;
                if (aGCException.getCode() == 429 || aGCException.getCode() == 500 || aGCException.getCode() == 502 || aGCException.getCode() == 503 || aGCException.getCode() == 504) {
                    return true;
                }
            }
            return false;
        }

        public void addForSuccess() {
            synchronized (LOCK) {
                this.failCount = 0;
                this.failTime = 0L;
                saveCache();
            }
        }

        public void addForStart() {
            synchronized (LOCK) {
                if (this.countDeque.size() == 10) {
                    this.countDeque.pollFirst();
                }
                this.countDeque.addLast(Long.valueOf(System.currentTimeMillis()));
            }
        }

        public void addForFail() {
            synchronized (LOCK) {
                this.failCount = Integer.valueOf(this.failCount.intValue() + 1);
                this.failTime = Long.valueOf(System.currentTimeMillis());
                saveCache();
            }
        }

        private void saveCache() {
            StringBuilder sb = new StringBuilder();
            ArrayList arrayList = new ArrayList(this.countDeque);
            for (int i = 0; i < arrayList.size(); i++) {
                sb.append(arrayList.get(i));
                if (i != arrayList.size() - 1) {
                    sb.append(",");
                }
            }
            SharedPrefUtil.getInstance().put(SP_FILE_NAME, this.name + "-countDeque", String.class, sb.toString(), DefaultCrypto.class);
            SharedPrefUtil.getInstance().put(SP_FILE_NAME, this.name + "-failCount", Integer.class, this.failCount, DefaultCrypto.class);
            SharedPrefUtil.getInstance().put(SP_FILE_NAME, this.name + "-failTime", Long.class, this.failTime, DefaultCrypto.class);
        }

        private void loadCache() {
            String[] split = ((String) SharedPrefUtil.getInstance().get(SP_FILE_NAME, this.name + "-countDeque", String.class, "", DefaultCrypto.class)).split(",");
            int length = split.length;
            for (int i = 0; i < length; i++) {
                String str = split[i];
                try {
                    if (str.length() > 0) {
                        this.countDeque.addLast(Long.valueOf(str));
                    }
                } catch (NumberFormatException e) {
                    Logger.e(RequestThrottle.TAG, "count deque", e);
                }
            }
            this.failCount = (Integer) SharedPrefUtil.getInstance().get(SP_FILE_NAME, this.name + "-failCount", Integer.class, 0, DefaultCrypto.class);
            this.failTime = (Long) SharedPrefUtil.getInstance().get(SP_FILE_NAME, this.name + "-failTime", Long.class, 0L, DefaultCrypto.class);
        }

        private long getFirst() {
            try {
                return this.countDeque.getFirst().longValue();
            } catch (NoSuchElementException unused) {
                return 0L;
            }
        }

        Throttle(String str) {
            this.name = str;
            loadCache();
        }
    }

    public Throttle get(String str) {
        return get(null, str);
    }

    public Throttle get(AGConnectInstance aGConnectInstance, String str) {
        if (TextUtils.isEmpty(str)) {
            throw new RuntimeException("throttle name can not be empty");
        }
        if (aGConnectInstance == null) {
            aGConnectInstance = AGConnectInstance.getInstance();
        }
        synchronized (LOCK) {
            String format = String.format(Locale.ENGLISH, "%s_%s", str, aGConnectInstance.getOptions().getIdentifier());
            if (this.throttleMap.containsKey(format)) {
                return this.throttleMap.get(format);
            }
            Throttle throttle = new Throttle(format);
            this.throttleMap.put(format, throttle);
            return throttle;
        }
    }

    public static RequestThrottle getInstance() {
        return INSTANCE;
    }

    private RequestThrottle() {
    }
}
