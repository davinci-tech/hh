package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.common.utils.AppInfoUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hihealth.dictionary.utils.ProductMapParseUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.datatypes.HeartDeviceInfo;
import health.compact.a.KeyValDbManager;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class ivr {
    private static final Map<String, Integer> b = new HashMap<String, Integer>(10) { // from class: ivr.4
        {
            put("e4b0b1d5-2003-4d88-8b5f-c4f64542040b", 82);
            put("a8ba095d-4123-43c4-a30a-0240011c58de", 84);
            put("8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4", 94);
            put("b29df4e3-b1f7-4e40-960d-4cfb63ccca05", 385);
            put("25c6df38-ca23-11e9-a32f-2a2ae2dbcce4", 106);
            put("e835d102-af95-48a6-ae13-2983bc06f5c0", 140);
        }
    };

    public static List<HeartDeviceInfo> d(final Context context) {
        ArrayList arrayList = new ArrayList(10);
        if (context == null) {
            LogUtil.c("HealthDeviceUtils", "buildDeviceInfo device is null");
            return arrayList;
        }
        if (!ProductMapParseUtil.b(context)) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: ivr.3
                @Override // java.lang.Runnable
                public void run() {
                    jbw.d(context, 2);
                }
            });
        }
        for (ProductMapInfo productMapInfo : ProductMap.d()) {
            if (!TextUtils.isEmpty(productMapInfo.h())) {
                b.put(productMapInfo.h(), Integer.valueOf(productMapInfo.c()));
            }
        }
        for (Map.Entry<String, DeviceInfo> entry : b(context).entrySet()) {
            String productId = entry.getValue().getProductId();
            Map<String, Integer> map = b;
            if (map.containsKey(productId)) {
                DeviceInfo value = entry.getValue();
                HeartDeviceInfo heartDeviceInfo = new HeartDeviceInfo();
                heartDeviceInfo.setDeviceName(KeyValDbManager.b(BaseApplication.getContext()).e(knl.d(value.getDeviceIdentify())));
                heartDeviceInfo.setmDeviceId(value.getmDeviceId());
                heartDeviceInfo.setProductType(map.get(productId).intValue());
                heartDeviceInfo.setUuid(knl.d(value.getDeviceIdentify()));
                heartDeviceInfo.setDeviceConnectState(-2);
                heartDeviceInfo.setSoftVersion("");
                heartDeviceInfo.setDeviceCapability("");
                arrayList.add(heartDeviceInfo);
            }
        }
        return arrayList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x00d2, code lost:
    
        if (r6 != null) goto L39;
     */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x010e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.util.Map<java.lang.String, com.huawei.health.devicemgr.business.entity.DeviceInfo> b(android.content.Context r22) {
        /*
            Method dump skipped, instructions count: 274
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ivr.b(android.content.Context):java.util.Map");
    }

    public static void b() {
        ProductMapParseUtil.b(BaseApplication.getContext());
        if (Utils.o()) {
            return;
        }
        int a2 = iwe.a(BaseApplication.getContext(), "app_version_code", 0, 0);
        iwe.d(BaseApplication.getContext(), "app_version_code", AppInfoUtils.c(), 0);
        if (a2 != AppInfoUtils.c()) {
            ReleaseLogUtil.e("HiH_HealthDeviceUtils", "app upgrade station can't update,the lastVersion:", Integer.valueOf(a2));
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: ivt
                @Override // java.lang.Runnable
                public final void run() {
                    ivr.c();
                }
            });
        }
    }

    static /* synthetic */ void c() {
        if (jbw.c(BaseApplication.getContext())) {
            jbw.d(BaseApplication.getContext(), 2);
        }
    }
}
