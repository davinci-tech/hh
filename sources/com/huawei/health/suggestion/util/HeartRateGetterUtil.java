package com.huawei.health.suggestion.util;

import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.model.MeasureResult;
import com.huawei.health.suggestion.SuggestionBaseCallback;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hwbasemgr.IHeartRateCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cei;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class HeartRateGetterUtil {
    private IHeartRateCallback e;
    private String f;
    private String j;
    private static final HeartRateGetterUtil d = new HeartRateGetterUtil();

    /* renamed from: a, reason: collision with root package name */
    private static final Object f3432a = new Object();
    private List<SuggestionBaseCallback> c = new ArrayList();
    private List<HeartRateConnectStateCallBack> g = new ArrayList();
    private List<Integer> b = null;
    private HiSubscribeListener i = new HiSubscribeListener() { // from class: com.huawei.health.suggestion.util.HeartRateGetterUtil.1
        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            if (list == null || list.isEmpty()) {
                return;
            }
            LogUtil.a("Suggestion_HeartRateGetterUtil", "registerHeartRateListener success");
            synchronized (HeartRateGetterUtil.f3432a) {
                HeartRateGetterUtil.this.b = list;
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            if (hiHealthData == null) {
                LogUtil.b("Suggestion_HeartRateGetterUtil", "onChange HiHealthData is null!");
                return;
            }
            int intValue = hiHealthData.getIntValue();
            HeartRateData heartRateData = new HeartRateData();
            heartRateData.saveHeartRate(intValue);
            heartRateData.saveTime(hiHealthData.getStartTime());
            LogUtil.a("Suggestion_HeartRateGetterUtil", "time: ", Long.valueOf(hiHealthData.getStartTime()), "; heartRate:", Integer.valueOf(intValue));
            synchronized (HeartRateGetterUtil.f3432a) {
                for (SuggestionBaseCallback suggestionBaseCallback : HeartRateGetterUtil.this.c) {
                    if (suggestionBaseCallback == null) {
                        return;
                    }
                    if (!HeartRateGetterUtil.this.c.contains(suggestionBaseCallback)) {
                        return;
                    } else {
                        suggestionBaseCallback.onResponse(0, heartRateData);
                    }
                }
            }
        }
    };

    public interface HeartRateConnectStateCallBack {
        void setConnectStateChange(boolean z);
    }

    private HeartRateGetterUtil() {
    }

    public static HeartRateGetterUtil a() {
        return d;
    }

    public void a(IHeartRateCallback iHeartRateCallback) {
        this.e = iHeartRateCallback;
    }

    public void c(int i, boolean z) {
        LogUtil.a("Suggestion_HeartRateGetterUtil", "onHeartRateEnableChange = ", Integer.valueOf(i));
        if (i == 0) {
            IHeartRateCallback iHeartRateCallback = this.e;
            if (iHeartRateCallback != null && z) {
                iHeartRateCallback.startHeartRateMeasure();
            }
            d();
            return;
        }
        if (i == 1) {
            IHeartRateCallback iHeartRateCallback2 = this.e;
            if (iHeartRateCallback2 != null && z) {
                iHeartRateCallback2.stopHeartRateMeasure();
            }
            c();
        }
    }

    public void c(HeartRateConnectStateCallBack heartRateConnectStateCallBack) {
        synchronized (f3432a) {
            if (heartRateConnectStateCallBack == null) {
                LogUtil.h("Suggestion_HeartRateGetterUtil", "RateConnectCallback callback is null");
            } else if (this.g.contains(heartRateConnectStateCallBack)) {
                LogUtil.a("Suggestion_HeartRateGetterUtil", "RateConnectCallback failed, exist");
            } else {
                this.g.add(heartRateConnectStateCallBack);
                LogUtil.a("Suggestion_HeartRateGetterUtil", "ConnectCallback listeners num", Integer.valueOf(this.c.size()));
            }
        }
    }

    public void d(HeartRateConnectStateCallBack heartRateConnectStateCallBack) {
        LogUtil.a("Suggestion_HeartRateGetterUtil", "unRegisterHeartRateConnectCallback");
        synchronized (f3432a) {
            if (heartRateConnectStateCallBack == null) {
                LogUtil.h("Suggestion_HeartRateGetterUtil", "unRegisterHeartRateConnectCallback callback is null");
            } else if (!this.g.contains(heartRateConnectStateCallBack)) {
                LogUtil.h("Suggestion_HeartRateGetterUtil", "unRegisterHeartRateConnectCallback failed, not exist");
            } else {
                this.g.remove(heartRateConnectStateCallBack);
            }
        }
    }

    public void c(SuggestionBaseCallback suggestionBaseCallback) {
        synchronized (f3432a) {
            if (suggestionBaseCallback == null) {
                LogUtil.h("Suggestion_HeartRateGetterUtil", "registerHeartRateListener callback is null");
                return;
            }
            if (this.c.contains(suggestionBaseCallback)) {
                LogUtil.a("Suggestion_HeartRateGetterUtil", "registerHeartRateListener failed, exist");
                return;
            }
            this.c.add(suggestionBaseCallback);
            LogUtil.a("Suggestion_HeartRateGetterUtil", "registerHeartRateListener listeners num", Integer.valueOf(this.c.size()));
            if (this.c.size() == 1) {
                LogUtil.a("Suggestion_HeartRateGetterUtil", "register to db");
                HiHealthManager.d(BaseApplication.getContext()).subscribeHiHealthData(13, this.i);
            }
        }
    }

    public void a(SuggestionBaseCallback suggestionBaseCallback) {
        List<Integer> list;
        synchronized (f3432a) {
            if (suggestionBaseCallback == null) {
                LogUtil.h("Suggestion_HeartRateGetterUtil", "unregisterHeartRateListener callback is null");
                return;
            }
            if (!this.c.contains(suggestionBaseCallback)) {
                LogUtil.h("Suggestion_HeartRateGetterUtil", "unregisterHeartRateListener failed, not exist");
                return;
            }
            this.c.remove(suggestionBaseCallback);
            LogUtil.a("Suggestion_HeartRateGetterUtil", "unregisterHeartRateListener listeners size ", Integer.valueOf(this.c.size()));
            if (this.c.size() == 0 && (list = this.b) != null && !list.isEmpty()) {
                LogUtil.a("Suggestion_HeartRateGetterUtil", "unregister from db");
                HiHealthManager.d(BaseApplication.getContext()).unSubscribeHiHealthData(this.b, new HiUnSubscribeListener() { // from class: com.huawei.health.suggestion.util.HeartRateGetterUtil.5
                    @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
                    public void onResult(boolean z) {
                        LogUtil.a("Suggestion_HeartRateGetterUtil", "unregisterHeartRateListener isSuccess = ", Boolean.valueOf(z));
                    }
                });
            }
        }
    }

    public void d() {
        LogUtil.a("Suggestion_HeartRateGetterUtil", "start measure!");
        cei.b().init();
        cei.b().startMeasureBackground(BaseApplication.getContext(), 0, HealthDevice.HealthDeviceKind.HDK_HEART_RATE, new c());
    }

    public void c() {
        if (this.j != null) {
            cei.b().stopMeasureBackground(this.j, this.f);
        }
    }

    class c implements MeasureResult.MeasureResultListener {
        private c() {
        }

        @Override // com.huawei.health.device.model.MeasureResult.MeasureResultListener
        public void onMeasureFailed(MeasureResult.MeasureErrorCode measureErrorCode) {
            LogUtil.b("Suggestion_HeartRateGetterUtil", "onMeasureFailed");
        }

        @Override // com.huawei.health.device.model.MeasureResult.MeasureResultListener
        public void onMeasureDevice(String str, String str2, boolean z) {
            LogUtil.c("Suggestion_HeartRateGetterUtil", "onMeasureDevice, onMeasureDevice productId is ", str);
            HeartRateGetterUtil.this.j = str;
            HeartRateGetterUtil.this.f = str2;
            if (z) {
                for (HeartRateConnectStateCallBack heartRateConnectStateCallBack : HeartRateGetterUtil.this.g) {
                    LogUtil.a("Suggestion_HeartRateGetterUtil", "onMeasureDevice, setConnectStateChange");
                    heartRateConnectStateCallBack.setConnectStateChange(z);
                }
            }
        }
    }
}
