package com.huawei.hihealth.motion.service;

import android.content.Context;
import android.util.Log;
import com.huawei.hihealth.motion.HealthOpenSDK;
import com.huawei.hihealth.motion.IExecuteResult;
import com.huawei.hihealth.motion.service.healthdevice.HealthDeviceOper;
import com.huawei.hihealth.motion.service.healthdevice.IHealthDeviceOper;
import com.huawei.hihealth.motion.service.stepcounter.HwStepCounter;
import com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: classes.dex */
public class OpenSDKServiceRegister {
    private static HashMap<String, ServiceFetcher<?>> c = new HashMap<>();

    /* renamed from: a, reason: collision with root package name */
    private static ConcurrentHashMap<String, CopyOnWriteArraySet<HealthOpenSDK>> f4135a = new ConcurrentHashMap<>();
    private static ArrayList<IExecuteResult> d = new ArrayList<>();

    interface ServiceFetcher<T> {
        T getService(Context context);

        void release();
    }

    static {
        c("hwstepcounter", new CachedServiceFetcher<IHwStepCounter>("hwstepcounter") { // from class: com.huawei.hihealth.motion.service.OpenSDKServiceRegister.1
            @Override // com.huawei.hihealth.motion.service.OpenSDKServiceRegister.CachedServiceFetcher
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public IHwStepCounter createService(Context context, IExecuteResult iExecuteResult) {
                return HwStepCounter.e(context, iExecuteResult);
            }

            @Override // com.huawei.hihealth.motion.service.OpenSDKServiceRegister.CachedServiceFetcher
            protected void releaseService() {
                HwStepCounter.e();
            }
        });
        c("healthdeviceoper", new CachedServiceFetcher<IHealthDeviceOper>("healthdeviceoper") { // from class: com.huawei.hihealth.motion.service.OpenSDKServiceRegister.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.hihealth.motion.service.OpenSDKServiceRegister.CachedServiceFetcher
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public IHealthDeviceOper createService(Context context, IExecuteResult iExecuteResult) {
                return HealthDeviceOper.b(context, iExecuteResult);
            }

            @Override // com.huawei.hihealth.motion.service.OpenSDKServiceRegister.CachedServiceFetcher
            protected void releaseService() {
                HealthDeviceOper.e();
            }
        });
    }

    static abstract class CachedServiceFetcher<T> implements ServiceFetcher<T> {
        private static final Map<Class, Object> SERVICE_CACHE = new HashMap();
        private static final int STATUS_SERVICE_EXCEPTION = 2;
        private static final int STATUS_SERVICE_READY = 1;
        private String mName;

        protected abstract T createService(Context context, IExecuteResult iExecuteResult);

        protected abstract void releaseService();

        public CachedServiceFetcher(String str) {
            this.mName = str;
        }

        @Override // com.huawei.hihealth.motion.service.OpenSDKServiceRegister.ServiceFetcher
        public final T getService(Context context) {
            Map<Class, Object> map = SERVICE_CACHE;
            synchronized (map) {
                T t = (T) map.get(getClass());
                Log.d("HealthOpenSDK_OSSR", "getService service = " + t);
                if (t == null) {
                    if (context == null) {
                        return null;
                    }
                    t = createService(context, new IExecuteResult() { // from class: com.huawei.hihealth.motion.service.OpenSDKServiceRegister.CachedServiceFetcher.1
                        @Override // com.huawei.hihealth.motion.IExecuteResult
                        public void onFailed(Object obj) {
                        }

                        @Override // com.huawei.hihealth.motion.IExecuteResult
                        public void onSuccess(Object obj) {
                            CachedServiceFetcher.this.notifyServiceStatus(1, obj);
                        }

                        @Override // com.huawei.hihealth.motion.IExecuteResult
                        public void onServiceException(Object obj) {
                            CachedServiceFetcher.this.notifyServiceStatus(2, obj);
                        }
                    });
                    map.put(getClass(), t);
                }
                return t;
            }
        }

        @Override // com.huawei.hihealth.motion.service.OpenSDKServiceRegister.ServiceFetcher
        public void release() {
            Map<Class, Object> map = SERVICE_CACHE;
            synchronized (map) {
                if (map.get(getClass()) == null) {
                    Log.w("HealthOpenSDK_OSSR", "no need to release");
                } else {
                    releaseService();
                    map.put(getClass(), null);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void notifyServiceStatus(int i, Object obj) {
            CopyOnWriteArraySet copyOnWriteArraySet = (CopyOnWriteArraySet) OpenSDKServiceRegister.f4135a.get(this.mName);
            if (copyOnWriteArraySet == null) {
                Log.w("HealthOpenSDK_OSSR", "HealthOpenSDK no init");
                return;
            }
            Iterator it = copyOnWriteArraySet.iterator();
            while (it.hasNext()) {
                HealthOpenSDK healthOpenSDK = (HealthOpenSDK) it.next();
                if (2 == i) {
                    healthOpenSDK.d(obj);
                } else if (1 == i) {
                    healthOpenSDK.b(obj);
                } else {
                    Log.i("HealthOpenSDK_OSSR", "status no match");
                }
            }
        }
    }

    private OpenSDKServiceRegister() {
    }

    public static Object b(Context context, String str, HealthOpenSDK healthOpenSDK) {
        ServiceFetcher<?> serviceFetcher = c.get(str);
        CopyOnWriteArraySet<HealthOpenSDK> copyOnWriteArraySet = new CopyOnWriteArraySet<>();
        if (f4135a.get(str) == null) {
            Log.d("HealthOpenSDK_OSSR", "result =" + f4135a.putIfAbsent(str, copyOnWriteArraySet));
        } else {
            copyOnWriteArraySet = f4135a.get(str);
        }
        if (!copyOnWriteArraySet.contains(healthOpenSDK)) {
            copyOnWriteArraySet.add(healthOpenSDK);
        }
        Log.d("HealthOpenSDK_OSSR", "getServiceByOpenSDK fetcher = " + serviceFetcher);
        if (serviceFetcher != null) {
            return serviceFetcher.getService(context);
        }
        return null;
    }

    public static void c(HealthOpenSDK healthOpenSDK) {
        for (Map.Entry<String, CopyOnWriteArraySet<HealthOpenSDK>> entry : f4135a.entrySet()) {
            CopyOnWriteArraySet<HealthOpenSDK> value = entry.getValue();
            if (value != null && value.contains(healthOpenSDK)) {
                value.remove(healthOpenSDK);
                if (value.size() == 0) {
                    ServiceFetcher<?> serviceFetcher = c.get(entry.getKey());
                    if (serviceFetcher == null) {
                        Log.w("HealthOpenSDK_OSSR", "release by opensdk fetcher null");
                    } else {
                        serviceFetcher.release();
                    }
                }
            }
        }
    }

    private static <T> void c(String str, ServiceFetcher<T> serviceFetcher) {
        c.put(str, serviceFetcher);
    }
}
