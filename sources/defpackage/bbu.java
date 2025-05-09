package defpackage;

import com.huawei.basichealthmodel.DeveloperKitProxy;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class bbu {
    private static boolean b;

    public static void d() {
        HiHealthNativeApi.a(BaseApplication.getContext()).setBinder("HealthCapabilityService", new DeveloperKitProxy(), new HiCommonListener() { // from class: bbu.2
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                LogUtil.a("HealthLife_OpenInterfaceUtils", "setBinder onSuccess errorCode ", Integer.valueOf(i), " object ", obj);
                boolean unused = bbu.b = true;
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.h("HealthLife_OpenInterfaceUtils", "setBinder onFailure errorCode ", Integer.valueOf(i), " object ", obj);
            }
        });
    }

    public static boolean b() {
        return b;
    }
}
