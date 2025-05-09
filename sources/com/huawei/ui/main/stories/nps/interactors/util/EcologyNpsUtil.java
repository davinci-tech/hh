package com.huawei.ui.main.stories.nps.interactors.util;

import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import defpackage.mst;
import defpackage.msx;
import java.util.List;

/* loaded from: classes7.dex */
public class EcologyNpsUtil {
    private static final int DEFAULT_NPS_ID = 0;
    private static final int ECOLOGY_HEALTH_NPS_ID = 31;
    private static final int ECOLOGY_SPORT_NPS_ID = 30;
    private static final String TAG = "EcologyNpsUtil";

    public static int getDeviceNpsId(String str) {
        LogUtil.a(TAG, "productId : ", str);
        msx intelligentDeviceBean = getIntelligentDeviceBean(str);
        if (intelligentDeviceBean == null) {
            return 0;
        }
        if (intelligentDeviceBean.e() == 1) {
            return 30;
        }
        return intelligentDeviceBean.e() == 2 ? 31 : 0;
    }

    private static msx getIntelligentDeviceBean(String str) {
        List<msx> c = mst.a().c(str);
        if (koq.b(c)) {
            return null;
        }
        msx msxVar = c.get(0);
        if (mst.a().e(c.get(0))) {
            return msxVar;
        }
        return null;
    }

    public static String getEcologyModel(String str) {
        LogUtil.a(TAG, "productId : ", str);
        msx intelligentDeviceBean = getIntelligentDeviceBean(str);
        return intelligentDeviceBean == null ? "" : intelligentDeviceBean.n();
    }
}
