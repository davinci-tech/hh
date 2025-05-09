package defpackage;

import android.text.TextUtils;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class cwf {
    public static String a(cvc cvcVar, int i, DeviceInfo deviceInfo) {
        String str = "";
        if (cvcVar == null) {
            LogUtil.h("HwCloudDeviceInfoUtil", "ezPluginInfo is null.");
            return "";
        }
        cvj f = cvcVar.f();
        if (f != null) {
            LogUtil.a("HwCloudDeviceInfoUtil", "imageType:", Integer.valueOf(i));
            str = d(f, i, deviceInfo);
        }
        LogUtil.a("HwCloudDeviceInfoUtil", "imageName:", str);
        return str;
    }

    public static String a(cvc cvcVar, String str, DeviceInfo deviceInfo) {
        if (cvcVar == null || str == null || deviceInfo == null) {
            LogUtil.h("HwCloudDeviceInfoUtil", "getMappingWearHomeDeviceBackground descriptionInfo or styleType or deviceInfo is null.");
            return null;
        }
        cvj f = cvcVar.f();
        if (f == null || deviceInfo.getHiLinkDeviceId() == null || deviceInfo.getDeviceModel() == null) {
            LogUtil.h("HwCloudDeviceInfoUtil", "getMappingWearHomeDeviceBackground wearDeviceInfo or deviceInfo deviceKey is null.");
            return null;
        }
        String concat = deviceInfo.getHiLinkDeviceId().concat("_").concat(deviceInfo.getDeviceModel());
        Map<String, Map<String, String>> bg = f.bg();
        if (CollectionUtils.e(bg)) {
            LogUtil.h("HwCloudDeviceInfoUtil", "getMappingWearHomeDeviceBackground productWearHomeBackgroundMap is empty.");
            return null;
        }
        Map<String, String> map = bg.get(concat);
        if (map == null) {
            Iterator<Map.Entry<String, Map<String, String>>> it = bg.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<String, Map<String, String>> next = it.next();
                if (next.getKey().contains(deviceInfo.getHiLinkDeviceId()) && next.getKey().contains(deviceInfo.getDeviceModel())) {
                    map = next.getValue();
                    break;
                }
            }
        }
        if (map == null) {
            LogUtil.h("HwCloudDeviceInfoUtil", "getMappingWearHomeDeviceBackground deviceWearHomeBackgroundMap is null.");
            return null;
        }
        return map.get(str);
    }

    public static ArrayList<String> c(cvc cvcVar, int i) {
        if (cvcVar == null) {
            LogUtil.h("HwCloudDeviceInfoUtil", "ezPluginInfo is null.");
            return null;
        }
        cvj f = cvcVar.f();
        if (f == null) {
            return null;
        }
        LogUtil.a("HwCloudDeviceInfoUtil", "imageType:", Integer.valueOf(i));
        if (i == 5) {
            return f.ak();
        }
        if (i == 6) {
            return f.aj();
        }
        LogUtil.h("HwCloudDeviceInfoUtil", "imageList is null");
        return null;
    }

    public static ArrayList<String> a(cvc cvcVar) {
        LogUtil.a("HwCloudDeviceInfoUtil", "enter getGuideTitleList");
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<cvh> j = j(cvcVar);
        if (j != null && !j.isEmpty()) {
            for (int i = 0; i < j.size(); i++) {
                cvh cvhVar = j.get(i);
                if (cvhVar != null) {
                    arrayList.add(cvhVar.c());
                }
            }
        }
        return arrayList;
    }

    public static ArrayList<String> c(cvc cvcVar) {
        LogUtil.a("HwCloudDeviceInfoUtil", "enter getGuideDescriptionList.");
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<cvh> j = j(cvcVar);
        if (j == null || j.isEmpty()) {
            LogUtil.a("HwCloudDeviceInfoUtil", "this device don't support user guide");
            return arrayList;
        }
        for (int i = 0; i < j.size(); i++) {
            cvh cvhVar = j.get(i);
            if (cvhVar.a() != null) {
                arrayList.add(cvhVar.a());
            }
        }
        if (arrayList.size() == j.size()) {
            return arrayList;
        }
        LogUtil.a("HwCloudDeviceInfoUtil", "getGuideDescriptionList is null");
        return new ArrayList<>();
    }

    public static List<List<String>> e(cvc cvcVar) {
        LogUtil.a("HwCloudDeviceInfoUtil", "enter getGuideDescriptionList.");
        ArrayList arrayList = new ArrayList();
        ArrayList<cvh> j = j(cvcVar);
        if (j == null || j.isEmpty()) {
            LogUtil.h("HwCloudDeviceInfoUtil", "deviceUserGuideBeanList is null");
            return arrayList;
        }
        for (int i = 0; i < j.size(); i++) {
            cvh cvhVar = j.get(i);
            if (cvhVar.b() == null || cvhVar.b().isEmpty()) {
                LogUtil.h("HwCloudDeviceInfoUtil", "descriptionList is null");
            } else {
                arrayList.add(cvhVar.b());
            }
        }
        if (!arrayList.isEmpty() && arrayList.size() == j.size()) {
            return arrayList;
        }
        LogUtil.h("HwCloudDeviceInfoUtil", "getGuideDescriptionList is null or substandard");
        return new ArrayList();
    }

    public static List<List<String>> d(cvc cvcVar) {
        LogUtil.a("HwCloudDeviceInfoUtil", "enter getGuideImageList.");
        ArrayList arrayList = new ArrayList();
        ArrayList<cvh> j = j(cvcVar);
        if (j == null || j.isEmpty()) {
            return arrayList;
        }
        for (int i = 0; i < j.size(); i++) {
            cvh cvhVar = j.get(i);
            if (cvhVar.d() == null || cvhVar.d().isEmpty()) {
                LogUtil.a("HwCloudDeviceInfoUtil", "imageList is null");
            } else {
                arrayList.add(cvhVar.d());
            }
        }
        if (!arrayList.isEmpty() && arrayList.size() == j.size()) {
            return arrayList;
        }
        LogUtil.a("HwCloudDeviceInfoUtil", "guideImageNameList is null or substandard");
        return new ArrayList();
    }

    public static boolean i(cvc cvcVar) {
        LogUtil.a("HwCloudDeviceInfoUtil", "enter isShowGuidePage");
        return !j(cvcVar).isEmpty();
    }

    private static ArrayList<cvh> j(cvc cvcVar) {
        if (cvcVar == null) {
            LogUtil.a("HwCloudDeviceInfoUtil", "ezPluginInfo is null");
            return new ArrayList<>();
        }
        cvj f = cvcVar.f();
        if (f == null) {
            LogUtil.a("HwCloudDeviceInfoUtil", "getGuideDataList exPluginInfoForWear is null");
            return new ArrayList<>();
        }
        ArrayList<cvh> ar = f.ar();
        if (ar != null && !ar.isEmpty()) {
            return ar;
        }
        LogUtil.a("HwCloudDeviceInfoUtil", "guideDataList is null");
        return new ArrayList<>();
    }

    private static String d(cvj cvjVar, int i, DeviceInfo deviceInfo) {
        if (i == 1) {
            return b(cvjVar.ay(), cvjVar.am(), deviceInfo);
        }
        if (i == 2) {
            return b(cvjVar.ax(), cvjVar.z(), deviceInfo);
        }
        if (i == 3) {
            return b(cvjVar.bm(), cvjVar.bp(), deviceInfo);
        }
        if (i == 4) {
            return b(cvjVar.bm(), cvjVar.ai(), deviceInfo);
        }
        LogUtil.h("HwCloudDeviceInfoUtil", "getDeviceImageNameWithModel default.");
        return "";
    }

    private static String b(String str, ArrayList<String> arrayList, DeviceInfo deviceInfo) {
        LogUtil.a("HwCloudDeviceInfoUtil", "imageList:", arrayList, "defaultImage:", str);
        if (deviceInfo == null || arrayList == null || arrayList.isEmpty()) {
            LogUtil.h("HwCloudDeviceInfoUtil", "deviceInfo or imageList is null.");
            return str;
        }
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            LogUtil.h("HwCloudDeviceInfoUtil", "deviceInfo imageString:", next);
            if (!TextUtils.isEmpty(deviceInfo.getHiLinkDeviceId()) && !TextUtils.isEmpty(deviceInfo.getDeviceSubModelId())) {
                if (next.contains(deviceInfo.getHiLinkDeviceId() + Constants.LINK + deviceInfo.getDeviceSubModelId())) {
                    return next;
                }
            }
            if ((TextUtils.isEmpty(deviceInfo.getDeviceModel()) || !next.contains(deviceInfo.getDeviceModel())) && (TextUtils.isEmpty(deviceInfo.getHiLinkDeviceId()) || !next.contains(deviceInfo.getHiLinkDeviceId()) || next.contains("subId"))) {
            }
            return next;
        }
        return str;
    }

    public static int b(cvc cvcVar) {
        if (cvcVar == null) {
            LogUtil.a("HwCloudDeviceInfoUtil", "ezPluginInfo is null");
            return 200;
        }
        cvj f = cvcVar.f();
        if (f == null) {
            LogUtil.a("HwCloudDeviceInfoUtil", "getGuideDataList exPluginInfoForWear is null");
            return 200;
        }
        return f.as();
    }

    public static void e(cvc cvcVar, String str, ArrayList<String> arrayList, ArrayList<String> arrayList2, List<List<String>> list) {
        LogUtil.a("HwCloudDeviceInfoUtil", "enter addDesignGuideData");
        if (cvcVar == null) {
            ReleaseLogUtil.d("R_GRS_HwCloudDeviceInfoUtil", "addDesignGuideData descriptionInfo is null");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("R_GRS_HwCloudDeviceInfoUtil", "addDesignGuideData hiLinkDeviceId is null");
            return;
        }
        if (arrayList == null || arrayList2 == null || list == null) {
            ReleaseLogUtil.d("R_GRS_HwCloudDeviceInfoUtil", "addDesignGuideData param list is null");
            return;
        }
        if (arrayList2.size() != list.size()) {
            LogUtil.a("HwCloudDeviceInfoUtil", "addDesignGuideData user guide data not show");
            arrayList.clear();
            arrayList2.clear();
            list.clear();
        }
        cvj f = cvcVar.f();
        if (f == null) {
            ReleaseLogUtil.d("R_GRS_HwCloudDeviceInfoUtil", "addDesignGuideData wearDeviceInfo is null");
            return;
        }
        ArrayList<cvh> ac = f.ac();
        if (CollectionUtils.d(ac)) {
            ReleaseLogUtil.d("R_GRS_HwCloudDeviceInfoUtil", "designGuideDataList isEmpty");
            return;
        }
        for (int i = 0; i < ac.size(); i++) {
            cvh cvhVar = ac.get(i);
            if (cvhVar == null) {
                ReleaseLogUtil.d("R_GRS_HwCloudDeviceInfoUtil", "deviceUserGuideBean is null");
                return;
            }
            String e = cvhVar.e();
            if (TextUtils.isEmpty(e) || !TextUtils.equals(e, str) || CollectionUtils.d(cvhVar.d())) {
                ReleaseLogUtil.d("R_GRS_HwCloudDeviceInfoUtil", "deviceUserGuideBean doesn't match");
            } else {
                arrayList.add(cvhVar.c());
                arrayList2.add(cvhVar.a());
                list.add(cvhVar.d());
            }
        }
    }
}
