package com.huawei.hwdevice.phoneprocess.impl;

import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.api.phoneprocess.PhoneServiceMgrApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.jsz;
import defpackage.jta;
import java.util.List;

/* loaded from: classes9.dex */
public class PhoneServiceMgrImpl implements PhoneServiceMgrApi {
    @Override // com.huawei.health.devicemgr.api.phoneprocess.PhoneServiceMgrApi
    public List<DeviceInfo> getDeviceList(HwGetDevicesMode hwGetDevicesMode, HwGetDevicesParameter hwGetDevicesParameter, String str) {
        LogUtil.a("PhoneServiceMgrImpl", "getDeviceList, mode: ", hwGetDevicesMode, " ,tag: ", str);
        return jsz.b(BaseApplication.getContext()).getDeviceList(hwGetDevicesMode, hwGetDevicesParameter, str);
    }

    @Override // com.huawei.health.devicemgr.api.phoneprocess.PhoneServiceMgrApi
    public String getDeviceRelation(String str) {
        return jta.d().e(str);
    }
}
