package com.huawei.ui.main.stories.nps.harid;

import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ceo;
import defpackage.cjx;
import defpackage.cpa;
import defpackage.jcx;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes7.dex */
public class HagridNpsManager {
    private static final String HAGRID2021_B19 = "Hagrid2021-B19";
    private static final String HAGRID_B19 = "Hagrid-B19";
    private static final String HERM_B19 = "Herm-B19";
    private static final String LUPIN_B19HN = "Lupin-B19HN";
    private static final String TAG = "HagridNpsManager";
    private static volatile HagridNpsManager hwNpsManager;

    private HagridNpsManager() {
        LogUtil.a(TAG, "HagridNpsManager init");
    }

    public static HagridNpsManager getInstance() {
        if (hwNpsManager == null) {
            synchronized (HagridNpsManager.class) {
                if (hwNpsManager == null) {
                    hwNpsManager = new HagridNpsManager();
                }
            }
        }
        return hwNpsManager;
    }

    public boolean isBindWifiDevice() {
        boolean z;
        ArrayList<String> a2 = cjx.e().a(HealthDevice.HealthDeviceKind.HDK_WEIGHT);
        if (a2 != null) {
            Iterator<String> it = a2.iterator();
            while (it.hasNext()) {
                if (cpa.ae(it.next())) {
                    z = true;
                    break;
                }
            }
        }
        z = false;
        LogUtil.a(TAG, "isWifiScales: ", Boolean.valueOf(z));
        return z;
    }

    public boolean isWeightDeviceNps() {
        MeasurableDevice d;
        boolean z = HAGRID_B19.equals(jcx.g()) || HAGRID2021_B19.equals(jcx.g()) || LUPIN_B19HN.equals(jcx.g()) || HERM_B19.equals(jcx.g());
        if (!z && (d = ceo.d().d(jcx.h(), true)) != null) {
            z = cpa.an(d.getProductId());
        }
        LogUtil.a(TAG, "isWeightDevice: ", Boolean.valueOf(z));
        return z;
    }
}
