package defpackage;

import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes5.dex */
public class joq {
    private List<Integer> e;

    static class c {
        static final joq e = new joq();
    }

    public static joq b() {
        return c.e;
    }

    public void d() {
        HiHealthNativeApi.a(BaseApplication.getContext()).subscribeHiHealthData(102, new HiSubscribeListener() { // from class: joq.4
            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onResult(List<Integer> list, List<Integer> list2) {
                LogUtil.a("DeviceSwitchSubscribe", "subscribeHiHealthData onResult");
                joq.this.e = list;
            }

            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
                if (i == 102 && "HiSyncUserData".equals(str)) {
                    LogUtil.a("DeviceSwitchSubscribe", "subscribeHiHealthData HiSyncUserData onChange");
                    joy.d().c();
                }
            }
        });
    }

    public void c() {
        HiHealthNativeApi.a(BaseApplication.getContext()).unSubscribeHiHealthData(this.e, new HiUnSubscribeListener() { // from class: joq.2
            @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
            public void onResult(boolean z) {
                LogUtil.a("DeviceSwitchSubscribe", "unsubscribeHiHealthData, isSuccess is ", Boolean.valueOf(z));
            }
        });
    }
}
