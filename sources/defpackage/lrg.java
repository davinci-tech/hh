package defpackage;

import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.notification.NotificationDeviceLinkage;
import health.compact.a.CommonUtils;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class lrg {
    public static Bundle bZn_(Bundle bundle, List<NotificationDeviceLinkage> list) {
        if (bundle == null || CollectionUtils.d(list)) {
            ReleaseLogUtil.a("NotificationDeviceLinkageUtils", "getDeviceLinkage bundle ", bundle, " deviceLinkage ", list);
            return bundle;
        }
        ArrayList arrayList = new ArrayList();
        for (NotificationDeviceLinkage notificationDeviceLinkage : list) {
            if (notificationDeviceLinkage != null) {
                HashMap hashMap = new HashMap();
                d(hashMap, "ping_open_remote", notificationDeviceLinkage.getPingOpenRemote());
                d(hashMap, "ping_open_type", notificationDeviceLinkage.getPingOpenType());
                e(hashMap, "ping_package_name", notificationDeviceLinkage.getPingPackageName());
                e(hashMap, "ping_class_name", notificationDeviceLinkage.getPingClassName());
                e(hashMap, "ping_url", notificationDeviceLinkage.getPingUrl());
                e(hashMap, "button_content", notificationDeviceLinkage.getButtonContent());
                if (hashMap.size() != 0) {
                    arrayList.add(hashMap);
                }
            }
        }
        if (CollectionUtils.d(arrayList)) {
            ReleaseLogUtil.a("NotificationDeviceLinkageUtils", "getDeviceLinkage list ", arrayList, " deviceLinkageList ", list);
            return bundle;
        }
        String e = HiJsonUtil.e(arrayList);
        bundle.putString("ping_list", e);
        LogUtil.c("NotificationDeviceLinkageUtils", "getDeviceLinkage json ", e, " deviceLinkageList ", list);
        return bundle;
    }

    private static void d(Map<String, Object> map, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        map.put(str, Integer.valueOf(CommonUtils.h(str2)));
    }

    private static void e(Map<String, Object> map, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        map.put(str, str2);
    }
}
