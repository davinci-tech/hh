package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.healthlinkage.api.HWhealthLinkageApi;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/* loaded from: classes4.dex */
public class hpx {
    public static String[] c(int i) {
        HashSet hashSet = new HashSet(10);
        HashSet hashSet2 = new HashSet(10);
        String[] strArr = {d(hashSet), e(hashSet2)};
        HWhealthLinkageApi hWhealthLinkageApi = (HWhealthLinkageApi) Services.a("HWhealthLinkage", HWhealthLinkageApi.class);
        if (hWhealthLinkageApi == null) {
            LogUtil.b("Track_TrackLinkedDevicesUtil", "healthLinkage not link");
            return strArr;
        }
        List<DeviceInfo> linkedDevice = hWhealthLinkageApi.getLinkedDevice(i);
        if (koq.b(linkedDevice)) {
            return strArr;
        }
        for (DeviceInfo deviceInfo : linkedDevice) {
            if (deviceInfo == null) {
                LogUtil.b("Track_TrackLinkedDevicesUtil", "deviceInfo is null or not run posture.");
            } else {
                hashSet.add(Integer.valueOf(cun.c().getDeviceTypeId(deviceInfo)));
                hashSet2.add(deviceInfo.getHiLinkDeviceId());
            }
        }
        strArr[0] = d(hashSet);
        strArr[1] = e(hashSet2);
        LogUtil.a("Track_TrackLinkedDevicesUtil", "localConnectedDevice is ", Arrays.toString(strArr));
        return strArr;
    }

    private static String d(HashSet<Integer> hashSet) {
        return nrv.a(new ArrayList(hashSet));
    }

    private static String e(HashSet<String> hashSet) {
        return nrv.a(new ArrayList(hashSet));
    }

    public static String b(MotionPathSimplify motionPathSimplify) {
        if (motionPathSimplify == null) {
            return "";
        }
        String d = d("deviceId", motionPathSimplify.getExtendDataString("localDeviceId"));
        return TextUtils.isEmpty(d) ? d("smartProductId", motionPathSimplify.getExtendDataString("localProdId")) : d;
    }

    public static void c(MotionPathSimplify motionPathSimplify) {
        if (motionPathSimplify != null && TextUtils.isEmpty(motionPathSimplify.getExtendDataString("localDeviceId")) && TextUtils.isEmpty(motionPathSimplify.getExtendDataString("localProdId"))) {
            String[] c = c(motionPathSimplify.requestSportType());
            motionPathSimplify.addExtendDataMap("localDeviceId", c[0]);
            motionPathSimplify.addExtendDataMap("localProdId", c[1]);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x008e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String d(final java.lang.String r7, java.lang.String r8) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            boolean r1 = android.text.TextUtils.isEmpty(r8)
            java.lang.String r2 = "Track_TrackLinkedDevicesUtil"
            if (r1 == 0) goto L1b
            java.lang.String r7 = "deviceIdListStr is null in getShareLocalDevice"
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            com.huawei.hwlogsmodel.LogUtil.h(r2, r7)
            java.lang.String r7 = r0.toString()
            return r7
        L1b:
            hpx$2 r1 = new hpx$2     // Catch: com.google.gson.JsonSyntaxException -> Lbe
            r1.<init>()     // Catch: com.google.gson.JsonSyntaxException -> Lbe
            java.lang.reflect.Type r1 = r1.getType()     // Catch: com.google.gson.JsonSyntaxException -> Lbe
            java.lang.Object r1 = defpackage.nrv.c(r8, r1)     // Catch: com.google.gson.JsonSyntaxException -> Lbe
            java.util.ArrayList r1 = (java.util.ArrayList) r1     // Catch: com.google.gson.JsonSyntaxException -> Lbe
            boolean r8 = defpackage.koq.b(r1)
            if (r8 == 0) goto L3e
            java.lang.String r7 = "No local connected device in getShareLocalDevice"
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            com.huawei.hwlogsmodel.LogUtil.h(r2, r7)
            java.lang.String r7 = r0.toString()
            return r7
        L3e:
            hpx$1 r8 = new hpx$1
            r8.<init>()
            java.util.Collections.sort(r1, r8)
            android.content.Context r8 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            java.util.Iterator r1 = r1.iterator()
        L4e:
            boolean r3 = r1.hasNext()
            r4 = 0
            java.lang.String r5 = " | "
            if (r3 == 0) goto L95
            java.lang.Object r3 = r1.next()
            java.lang.String r3 = (java.lang.String) r3
            java.lang.String r6 = "deviceId"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L79
            boolean r4 = health.compact.a.utils.StringUtils.a(r3)
            if (r4 != 0) goto L6c
            goto L4e
        L6c:
            int r3 = java.lang.Integer.parseInt(r3)
            java.lang.String r4 = r8.getPackageName()
            java.lang.String r3 = defpackage.cwa.a(r3, r8, r4)
            goto L88
        L79:
            boolean r6 = health.compact.a.utils.StringUtils.g(r3)
            if (r6 == 0) goto L80
            goto L4e
        L80:
            java.lang.String r6 = r8.getPackageName()
            java.lang.String r3 = defpackage.cwa.a(r4, r8, r6, r3)
        L88:
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 != 0) goto L4e
            r0.append(r3)
            r0.append(r5)
            goto L4e
        L95:
            int r7 = r0.length()
            int r7 = r7 + (-3)
            if (r7 < 0) goto Lac
            java.lang.String r8 = r0.substring(r7)
            boolean r8 = r5.equals(r8)
            if (r8 == 0) goto Lac
            java.lang.String r7 = r0.substring(r4, r7)
            return r7
        Lac:
            java.lang.String r7 = "The local connected device to show is "
            java.lang.String r8 = r0.toString()
            java.lang.Object[] r7 = new java.lang.Object[]{r7, r8}
            com.huawei.hwlogsmodel.LogUtil.h(r2, r7)
            java.lang.String r7 = r0.toString()
            return r7
        Lbe:
            java.lang.String r7 = "getShareLocalDevice json syntax exception: "
            java.lang.Object[] r7 = new java.lang.Object[]{r7, r8}
            com.huawei.hwlogsmodel.LogUtil.b(r2, r7)
            java.lang.String r7 = r0.toString()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.hpx.d(java.lang.String, java.lang.String):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int d(String str, String str2, String str3) {
        String b = b(str, str2);
        String b2 = b(str, str3);
        if ("06D".equals(b) || "A12".equals(b2)) {
            return -1;
        }
        return ("A12".equals(b) || "06D".equals(b2)) ? 1 : 0;
    }

    private static String b(String str, String str2) {
        List<ProductMapInfo> b = ProductMap.b(str, str2);
        return koq.c(b) ? b.get(0).e() : "";
    }
}
