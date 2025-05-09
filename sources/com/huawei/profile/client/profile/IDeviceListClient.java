package com.huawei.profile.client.profile;

import android.os.Bundle;
import com.huawei.profile.profile.DeviceProfile;
import com.huawei.profile.service.AbstractSubscribeProfileListener;
import java.util.List;

/* loaded from: classes6.dex */
public interface IDeviceListClient {
    Bundle addDeviceList(List<DeviceProfile> list, int i, Bundle bundle);

    Bundle deleteDeviceListById(List<String> list, int i, Bundle bundle);

    Bundle deleteDeviceListByType(int i, Bundle bundle);

    List<DeviceProfile> queryDeviceList(int i, Bundle bundle);

    Bundle subscribeDeviceList(int i, AbstractSubscribeProfileListener abstractSubscribeProfileListener, Bundle bundle);

    Bundle unsubscribeDeviceList(int i, AbstractSubscribeProfileListener abstractSubscribeProfileListener, Bundle bundle);

    Bundle updateDeviceListInfo(List<DeviceProfile> list, int i, Bundle bundle);
}
