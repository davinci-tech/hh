package com.huawei.hianalytics.framework;

import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.core.storage.IStorageHandler;
import com.huawei.hianalytics.framework.config.ICallback;
import com.huawei.hianalytics.framework.config.ICollectorConfig;
import com.huawei.hianalytics.framework.config.IMandatoryParameters;
import com.huawei.hianalytics.framework.policy.IStoragePolicy;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public interface HAFrameworkInstance {
    public static final String TAG = "HAFrameworkInstance";

    void clearCacheDataAll();

    void clearCacheDataByTag();

    void clearCacheDataByTagType(String str);

    void onEvent(String str, String str2, JSONObject jSONObject, ICallback iCallback);

    void onEvent(String str, String str2, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, ICallback iCallback);

    void onEventSync(String str, String str2, JSONObject jSONObject);

    void onReport(String str);

    void onStreamEvent(String str, String str2, JSONObject jSONObject, ICallback iCallback);

    void onStreamEvent(String str, String str2, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, ICallback iCallback);

    public static final class Builder {
        public ICollectorConfig collectorConfig;
        public IMandatoryParameters parameters;
        public IStorageHandler storageHandler;
        public IStoragePolicy storagePolicy;

        public HAFrameworkInstance build(String str) {
            if (this.parameters == null && d.e.f3856a == null) {
                return null;
            }
            if (d.e.b.containsKey(str)) {
                HiLog.sw(HAFrameworkInstance.TAG, "serviceTag check failed, TAG: " + str);
                return null;
            }
            d dVar = d.e;
            ICollectorConfig iCollectorConfig = this.collectorConfig;
            IMandatoryParameters iMandatoryParameters = this.parameters;
            IStorageHandler iStorageHandler = this.storageHandler;
            IStoragePolicy iStoragePolicy = this.storagePolicy;
            synchronized (dVar) {
                dVar.b.put(str, iCollectorConfig);
                e eVar = new e();
                eVar.f3859a = iStorageHandler;
                dVar.c.put(str, eVar);
                dVar.d.put(str, iStoragePolicy);
                if (dVar.f3856a == null && iMandatoryParameters != null) {
                    dVar.f3856a = iMandatoryParameters;
                }
            }
            return new a(str);
        }

        public Builder setStoragePolicy(IStoragePolicy iStoragePolicy) {
            this.storagePolicy = iStoragePolicy;
            return this;
        }

        public Builder setStorageHandler(IStorageHandler iStorageHandler) {
            this.storageHandler = iStorageHandler;
            return this;
        }

        public Builder setMandatoryParameters(IMandatoryParameters iMandatoryParameters) {
            this.parameters = iMandatoryParameters;
            return this;
        }

        public Builder setCollectorConfig(ICollectorConfig iCollectorConfig) {
            this.collectorConfig = iCollectorConfig;
            return this;
        }
    }
}
