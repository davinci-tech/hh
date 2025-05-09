package com.huawei.hihealth.api;

import android.content.Context;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.data.listener.HiDataClientListener;
import com.huawei.hihealth.data.listener.HiRegisterClientListener;
import java.util.List;

/* loaded from: classes.dex */
public class HiHealthDeviceApi implements IDeviceApi {
    private static Context c;
    private HiHealthNativeApi b;

    private HiHealthDeviceApi() {
        this.b = HiHealthNativeApi.a(c);
    }

    public static HiHealthDeviceApi a(Context context) {
        c = context.getApplicationContext();
        return Instance.d;
    }

    @Override // com.huawei.hihealth.api.IDeviceApi
    public void registerDataClient(HiDeviceInfo hiDeviceInfo, List<Integer> list, HiRegisterClientListener hiRegisterClientListener) {
        this.b.registerDataClient(hiDeviceInfo, list, hiRegisterClientListener);
    }

    @Override // com.huawei.hihealth.api.IDeviceApi
    public void registerDataClient(HiDeviceInfo hiDeviceInfo, HiUserInfo hiUserInfo, List<Integer> list, HiRegisterClientListener hiRegisterClientListener) {
        this.b.registerDataClient(hiDeviceInfo, list, hiRegisterClientListener);
    }

    @Override // com.huawei.hihealth.api.IDeviceApi
    public void fetchDataClientByUniqueId(int i, String str, HiDataClientListener hiDataClientListener) {
        this.b.fetchDataClientByUniqueId(i, str, hiDataClientListener);
    }

    @Override // com.huawei.hihealth.api.IDeviceApi
    public void fetchDataSourceByType(int i, HiTimeInterval hiTimeInterval, HiDataClientListener hiDataClientListener) {
        this.b.fetchDataSourceByType(i, hiTimeInterval, hiDataClientListener);
    }

    static class Instance {
        public static final HiHealthDeviceApi d = new HiHealthDeviceApi();

        private Instance() {
        }
    }
}
