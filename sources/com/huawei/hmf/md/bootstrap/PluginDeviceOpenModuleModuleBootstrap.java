package com.huawei.hmf.md.bootstrap;

import com.huawei.health.impl.DeviceManagerImpl;
import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import defpackage.dye;
import defpackage.dyj;
import defpackage.dyk;
import defpackage.dyl;
import defpackage.dym;
import defpackage.dyn;
import defpackage.dyo;
import defpackage.dyp;
import defpackage.dyr;
import defpackage.dyt;
import defpackage.dyu;
import defpackage.dyv;
import defpackage.dyw;
import defpackage.dyx;
import defpackage.dyy;
import defpackage.dyz;
import defpackage.dzb;
import defpackage.dzc;
import defpackage.dzh;
import defpackage.uo;
import defpackage.uq;

/* loaded from: classes4.dex */
public final class PluginDeviceOpenModuleModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(dyn.class, "com.huawei.health.device.api.DeviceInfoUtilsApi");
        builder.add(dyr.class, "com.huawei.health.device.api.DeviceMainActivityApi");
        builder.add(dyt.class, "com.huawei.health.device.api.HwChMeasureControllerApi");
        builder.add(dzc.class, "com.huawei.health.device.api.PluginDeviceApi");
        builder.add(dye.class, "com.huawei.health.device.api.CommonLibUtilApi");
        builder.add(dyo.class, "com.huawei.health.device.api.HealthDeviceEntryApi");
        builder.add(dym.class, "com.huawei.health.device.api.DeviceDataBaseHelperApi");
        builder.add(DeviceManagerImpl.class, "com.huawei.health.device.api.DeviceManagerApi");
        builder.add(dyj.class, "com.huawei.health.device.api.DataBaseapiApi");
        builder.add(dzb.class, "com.huawei.health.device.api.VersionVerifyUtilApi");
        builder.add(dyp.class, "com.huawei.health.device.api.EventBusApi");
        builder.add(dzh.class, "com.huawei.health.device.api.WiFiWeightSaveHandlerApi");
        builder.add(dyw.class, "com.huawei.health.device.api.HonourDeviceConstantsApi");
        builder.add(dyk.class, "com.huawei.health.device.api.DeviceFragmentFactoryApi");
        builder.add(dyu.class, "com.huawei.health.device.api.MeasureKitManagerApi");
        builder.add(dyy.class, "com.huawei.health.device.api.WeightDataUtilsApi");
        builder.add(dyl.class, "com.huawei.health.device.api.ConverResisToWeightDataUtilApi");
        builder.add(dyz.class, "com.huawei.health.device.api.MultiUsersManagerApi");
        builder.add(dyx.class, "com.huawei.health.device.api.HwWspMeasureControllerApi");
        builder.add(dyv.class, "com.huawei.health.device.api.JsonParserApi");
        builder.add(uo.class, "com.huawei.plugindevice.UploadSmartLifeApi");
        new ModuleProviderWrapper(new uq(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "PluginDeviceOpenModule";
    }
}
