package defpackage;

import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DeviceDownloadSourceInfo;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.openalliance.ad.constant.AdShowExtras;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.KeyValDbManager;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
class cdl {
    private static String b = "";
    private static String e = "";

    private static int d(int i) {
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 1;
        }
        if (i == 4) {
            return 2;
        }
        if (i == 0) {
            return -1;
        }
        return i;
    }

    public static cuy e(String str) {
        cuy cuyVar = new cuy();
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("ParseIndexAllUtils", "parseIndexFile, indexAllJson is empty");
            return cuyVar;
        }
        try {
            cuyVar.c(a(e(str, cuyVar)));
        } catch (JSONException unused) {
            LogUtil.b("ParseIndexAllUtils", "parseIndexFile exception");
        }
        return cuyVar;
    }

    private static JSONObject e(String str, cuy cuyVar) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        cuyVar.e(jSONObject.optString("version"));
        String optString = jSONObject.optString("lang_version");
        b = optString;
        cuyVar.a(optString);
        cuyVar.e(a(jSONObject.getJSONArray("lang_info")));
        String optString2 = jSONObject.optString("img_version");
        e = optString2;
        cuyVar.b(optString2);
        cuyVar.b(a(jSONObject.getJSONArray("img_info")));
        a(jSONObject.optString(MapKeyNames.APP_INFO));
        return jSONObject;
    }

    private static void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        KeyValDbManager.b(BaseApplication.e()).e("app_info_device_cloud", str);
    }

    public static cuy b(String str) {
        cuy cuyVar = new cuy();
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("ParseIndexAllUtils", "getDownloadIndexBean, indexAllJson is empty");
            return cuyVar;
        }
        try {
            e(str, cuyVar);
        } catch (JSONException unused) {
            LogUtil.b("ParseIndexAllUtils", "getDownloadIndexBean exception");
        }
        return cuyVar;
    }

    private static List<cvg> a(JSONArray jSONArray) {
        LogUtil.a("ParseIndexAllUtils", "start getPathList");
        ArrayList arrayList = new ArrayList(16);
        if (jSONArray == null || jSONArray.length() == 0) {
            LogUtil.h("ParseIndexAllUtils", "pathArray is no data");
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject == null) {
                    LogUtil.h("ParseIndexAllUtils", "getPathList, jsonObject is null");
                } else {
                    cvg cvgVar = new cvg();
                    cvgVar.a(jSONObject.optString(BleConstants.KEY_PATH));
                    cvgVar.a(jSONObject.optInt("size"));
                    cvgVar.e(jSONObject.optString("digest"));
                    arrayList.add(cvgVar);
                }
            } catch (JSONException unused) {
                LogUtil.b("ParseIndexAllUtils", "getPathList JSONException");
            }
        }
        LogUtil.a("ParseIndexAllUtils", "resourceList size: ", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    private static List<cva> a(JSONObject jSONObject) {
        LogUtil.a("ParseIndexAllUtils", "start setKindList");
        ArrayList arrayList = new ArrayList(16);
        if (jSONObject == null) {
            LogUtil.h("ParseIndexAllUtils", "setKindList, jsonObject is empty");
            return arrayList;
        }
        try {
            if (!jSONObject.isNull(DeviceCategoryFragment.DEVICE_KIND)) {
                JSONArray jSONArray = jSONObject.getJSONArray(DeviceCategoryFragment.DEVICE_KIND);
                for (int i = 0; i < jSONArray.length(); i++) {
                    cva cvaVar = new cva();
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    String optString = jSONObject2.optString("kind_name");
                    cvaVar.e(optString);
                    String optString2 = jSONObject2.optString("kind_id");
                    cvaVar.b(optString2);
                    String string = jSONObject2.getString("resource_site");
                    cvaVar.c(string);
                    List<cve> a2 = a(jSONObject2.getJSONArray("device_list"), optString2, optString, string);
                    if (a2.isEmpty()) {
                        LogUtil.h("ParseIndexAllUtils", "setKindList, kindName: ", optString, " device info is empty");
                    } else {
                        cvaVar.c(a2);
                        arrayList.add(cvaVar);
                    }
                }
            } else {
                LogUtil.h("ParseIndexAllUtils", "start jsonObject.isNull(DeviceIndexAllConstants.DEVICE_KIND)");
            }
        } catch (JSONException unused) {
            LogUtil.b("ParseIndexAllUtils", "setKindList JSONException");
        }
        return arrayList;
    }

    private static List<cve> a(JSONArray jSONArray, String str, String str2, String str3) {
        ArrayList arrayList = new ArrayList(16);
        if (jSONArray == null || jSONArray.length() == 0) {
            LogUtil.h("ParseIndexAllUtils", "getDeviceKindList, jsonArray is no data");
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                cve cveVar = new cve();
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject != null) {
                    e(cveVar, jSONObject);
                    d(cveVar, jSONObject);
                    cveVar.e(jSONObject.optInt("cloud_type"));
                    cveVar.j(str);
                    cveVar.h(str2);
                    String optString = jSONObject.optString("brand_name");
                    cveVar.c(optString);
                    cveVar.b(optString);
                    e(jSONObject, cveVar);
                    String optString2 = jSONObject.optString(PluginPayAdapter.KEY_DEVICE_INFO_NAME);
                    cveVar.f(optString2);
                    cveVar.d(jSONObject.optString("device_description"));
                    c(cveVar, jSONObject);
                    cveVar.a(d(jSONObject.optInt("bluetooth_type")));
                    cveVar.e(jSONObject.optString("bluetooth_match"));
                    d(jSONObject, cveVar);
                    cveVar.m(jSONObject.optString("publish_mode"));
                    int optInt = jSONObject.optInt("support_all_scan");
                    if (optInt == 0) {
                        optInt = 1;
                    }
                    cveVar.j(optInt);
                    a(str3, cveVar, jSONObject, optString2);
                    arrayList.add(cveVar);
                }
            } catch (JSONException unused) {
                LogUtil.b("ParseIndexAllUtils", "getDeviceList JSONException");
            }
        }
        return arrayList;
    }

    private static void a(String str, cve cveVar, JSONObject jSONObject, String str2) {
        int optInt = jSONObject.optInt("can_scan");
        if (optInt == 0) {
            optInt = 2;
        }
        cveVar.d(optInt);
        b(cveVar, jSONObject);
        b(jSONObject, cveVar);
        i(jSONObject, cveVar);
        a(jSONObject, cveVar);
        cveVar.i(jSONObject.optInt("is_aw70"));
        cveVar.b(jSONObject.optInt("device_relationship"));
        cveVar.k(str);
        JSONObject optJSONObject = jSONObject.optJSONObject(AdShowExtras.DOWNLOAD_SOURCE);
        if (optJSONObject != null) {
            LogUtil.c("ParseIndexAllUtils", "downloadSource device : ", str2);
            DeviceDownloadSourceInfo deviceDownloadSourceInfo = new DeviceDownloadSourceInfo(null);
            deviceDownloadSourceInfo.setSite(optJSONObject.optInt("site"));
            deviceDownloadSourceInfo.setIndexName(optJSONObject.optString("indexName"));
            deviceDownloadSourceInfo.setConfigurationPoint(optJSONObject.optString("configurationPoint"));
            cveVar.d(deviceDownloadSourceInfo);
        }
        JSONObject optJSONObject2 = jSONObject.optJSONObject("ecology_profile");
        if (optJSONObject2 != null) {
            LogUtil.c("ParseIndexAllUtils", "ecology device : ", str2);
            cveVar.f(optJSONObject2.optInt("ecology_type"));
            cveVar.c(optJSONObject2.optInt(PluginPayAdapter.DEVICE_CATEGORY));
            cveVar.g(optJSONObject2.optString("model"));
        }
        c(jSONObject, cveVar);
        e(cveVar);
    }

    private static void c(JSONObject jSONObject, cve cveVar) {
        try {
            if (!jSONObject.has("device_detail")) {
                LogUtil.c("ParseIndexAllUtils", "setDeviceDetails not have device detail");
                return;
            }
            JSONArray jSONArray = jSONObject.getJSONArray("device_detail");
            if (jSONArray == null) {
                LogUtil.h("ParseIndexAllUtils", "setDeviceDetails deviceDetailArray is null");
                return;
            }
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                cvb cvbVar = new cvb();
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                cvbVar.b(jSONObject2.getString("uuid"));
                arrayList.add(cvbVar.b());
                cvbVar.a(jSONObject2.getString("digest"));
                cvbVar.d(jSONObject2.getString("version"));
                JSONObject optJSONObject = jSONObject2.optJSONObject("download_source_site");
                if (optJSONObject != null) {
                    DeviceDownloadSourceInfo deviceDownloadSourceInfo = new DeviceDownloadSourceInfo(null);
                    deviceDownloadSourceInfo.setSite(optJSONObject.optInt("site"));
                    deviceDownloadSourceInfo.setIndexName(optJSONObject.optString("indexName"));
                    deviceDownloadSourceInfo.setConfigurationPoint(optJSONObject.optString("configurationPoint"));
                    cvbVar.e(deviceDownloadSourceInfo);
                } else {
                    LogUtil.a("ParseIndexAllUtils", "setDeviceDetails downloadSource null");
                }
                if (jSONObject2.has("oobe")) {
                    cvbVar.c(jSONObject2.getString("oobe"));
                }
                arrayList2.add(cvbVar);
            }
            arrayList.addAll(cveVar.ac());
            cveVar.c(arrayList2);
            cveVar.d(arrayList);
        } catch (JSONException unused) {
            LogUtil.b("ParseIndexAllUtils", "setDeviceDetails JSONException");
        }
    }

    private static void e(cve cveVar) {
        List<cvb> l = cveVar.l();
        if (l == null || l.size() == 0) {
            LogUtil.c("ParseIndexAllUtils", "setEzPluginIndexInfos devicePluginInfo is null");
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < l.size(); i++) {
            cvk cvkVar = new cvk();
            cvkVar.d(l.get(i).b());
            cvkVar.b(l.get(i).a());
            cvkVar.a(l.get(i).c());
            cvkVar.b(l.get(i).d());
            d(cveVar, cvkVar);
            cvkVar.i(cveVar.v());
            cvkVar.a(cveVar.a());
            cvkVar.j(l.get(i).e());
            arrayList.add(cvkVar);
        }
        cveVar.e(arrayList);
    }

    private static void d(cve cveVar, cvk cvkVar) {
        if (cveVar.r().equals("wear_watch")) {
            cvkVar.f("SMART_WATCH");
        } else if (cveVar.r().equals("wear_band") || cveVar.r().toUpperCase(Locale.getDefault()).equals("SPORTS_GENIE")) {
            cvkVar.f("SMART_BAND");
        } else {
            cvkVar.f(cveVar.r());
        }
    }

    private static void e(JSONObject jSONObject, cve cveVar) {
        if (jSONObject == null) {
            LogUtil.h("ParseIndexAllUtils", "setOtherBand jsonObject is null");
            return;
        }
        try {
            ArrayList arrayList = new ArrayList(16);
            if (!jSONObject.isNull("brand_config")) {
                JSONArray jSONArray = jSONObject.getJSONArray("brand_config");
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    cvd cvdVar = new cvd();
                    if (!jSONObject2.isNull("country")) {
                        List<String> asList = Arrays.asList(jSONObject2.getString("country").split(";"));
                        if (!koq.b(asList)) {
                            cvdVar.e(asList);
                            if (!jSONObject2.isNull("brand_name")) {
                                cvdVar.b(jSONObject2.getString("brand_name"));
                                arrayList.add(cvdVar);
                            }
                        }
                    }
                }
            }
            cveVar.b(arrayList);
        } catch (JSONException unused) {
            LogUtil.b("ParseIndexAllUtils", "setOtherBand JSONException");
        }
    }

    private static void d(JSONObject jSONObject, cve cveVar) {
        JSONArray jSONArray;
        if (jSONObject == null) {
            LogUtil.h("ParseIndexAllUtils", "setBluetoothList jsonObject is null.");
            return;
        }
        try {
            if (!jSONObject.isNull("bluetooth_name_new")) {
                jSONArray = jSONObject.getJSONArray("bluetooth_name_new");
            } else if (!jSONObject.isNull("bluetooth_name")) {
                jSONArray = jSONObject.getJSONArray("bluetooth_name");
            } else {
                LogUtil.h("ParseIndexAllUtils", "jsonObject no contain bluetooth_name_new ir bluetooth_name.");
                jSONArray = null;
            }
            d(jSONArray, cveVar);
        } catch (JSONException unused) {
            LogUtil.b("ParseIndexAllUtils", "setBluetoothList occur JSONException.");
        }
    }

    private static void d(JSONArray jSONArray, cve cveVar) {
        if (jSONArray == null) {
            LogUtil.h("ParseIndexAllUtils", "setBluetoothListDetails pairArray is null.");
            return;
        }
        try {
            ArrayList arrayList = new ArrayList(16);
            for (int i = 0; i < jSONArray.length(); i++) {
                String string = jSONArray.getString(i);
                if (!TextUtils.isEmpty(string)) {
                    arrayList.add(string);
                }
            }
            cveVar.a(arrayList);
        } catch (JSONException unused) {
            LogUtil.b("ParseIndexAllUtils", "setBluetoothListDetails occur JSONException.");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x007d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void b(org.json.JSONObject r9, defpackage.cve r10) {
        /*
            java.lang.String r0 = "overseas"
            java.lang.String r1 = "domestic"
            java.lang.String r2 = "ParseIndexAllUtils"
            if (r9 != 0) goto L12
            java.lang.String r9 = "getPublishVersion, jsonObject is null"
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            com.huawei.hwlogsmodel.LogUtil.h(r2, r9)
            return
        L12:
            java.lang.String r3 = "publish_version"
            boolean r4 = r9.isNull(r3)
            if (r4 != 0) goto L83
            java.util.HashMap r4 = new java.util.HashMap
            r5 = 16
            r4.<init>(r5)
            r5 = 0
            org.json.JSONArray r9 = r9.getJSONArray(r3)     // Catch: org.json.JSONException -> L4c
            r3 = 0
            r6 = r5
        L28:
            int r7 = r9.length()     // Catch: org.json.JSONException -> L49
            if (r3 >= r7) goto L58
            org.json.JSONObject r7 = r9.getJSONObject(r3)     // Catch: org.json.JSONException -> L49
            boolean r8 = r7.isNull(r1)     // Catch: org.json.JSONException -> L49
            if (r8 != 0) goto L3c
            java.lang.String r6 = r7.getString(r1)     // Catch: org.json.JSONException -> L49
        L3c:
            boolean r8 = r7.isNull(r0)     // Catch: org.json.JSONException -> L49
            if (r8 != 0) goto L46
            java.lang.String r5 = r7.getString(r0)     // Catch: org.json.JSONException -> L49
        L46:
            int r3 = r3 + 1
            goto L28
        L49:
            r9 = r5
            r5 = r6
            goto L4d
        L4c:
            r9 = r5
        L4d:
            java.lang.String r3 = "getPublishVersion JSONException"
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            com.huawei.hwlogsmodel.LogUtil.b(r2, r3)
            r6 = r5
            r5 = r9
        L58:
            boolean r9 = android.text.TextUtils.isEmpty(r6)
            if (r9 == 0) goto L6e
            boolean r9 = android.text.TextUtils.isEmpty(r5)
            if (r9 == 0) goto L6e
            java.lang.String r9 = "publicVersion parameters not configured."
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            com.huawei.hwlogsmodel.LogUtil.h(r2, r9)
            goto L80
        L6e:
            boolean r9 = android.text.TextUtils.isEmpty(r6)
            if (r9 != 0) goto L77
            r4.put(r1, r6)
        L77:
            boolean r9 = android.text.TextUtils.isEmpty(r5)
            if (r9 != 0) goto L80
            r4.put(r0, r5)
        L80:
            r10.a(r4)
        L83:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.cdl.b(org.json.JSONObject, cve):void");
    }

    private static void a(JSONObject jSONObject, cve cveVar) {
        if (jSONObject == null) {
            LogUtil.h("ParseIndexAllUtils", "setPairTypeLink, jsonObject is null");
            return;
        }
        if (jSONObject.isNull("pair_type")) {
            return;
        }
        HashMap hashMap = new HashMap(16);
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("pair_type");
            String str = null;
            String str2 = null;
            String str3 = null;
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                if (!jSONObject2.isNull("uuid")) {
                    str = jSONObject2.getString("uuid");
                }
                if (!jSONObject2.isNull("pair_format")) {
                    str2 = jSONObject2.getString("pair_format");
                }
                if (!jSONObject2.isNull("uri")) {
                    str3 = jSONObject2.getString("uri");
                }
                if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
                    LogUtil.h("ParseIndexAllUtils", "current uuid setPairTypeLink parameters not configured.");
                } else {
                    cvl cvlVar = new cvl();
                    if (!TextUtils.isEmpty(str)) {
                        cvlVar.c(str);
                    }
                    if (!TextUtils.isEmpty(str2)) {
                        cvlVar.d(str2);
                    }
                    if (!TextUtils.isEmpty(str3)) {
                        cvlVar.b(str3);
                    }
                    hashMap.put(str, cvlVar);
                }
            }
        } catch (JSONException unused) {
            LogUtil.b("ParseIndexAllUtils", "setPairTypeLink JSONException");
        }
        cveVar.b(hashMap);
    }

    private static void i(JSONObject jSONObject, cve cveVar) {
        if (jSONObject == null) {
            LogUtil.h("ParseIndexAllUtils", "setSecondResourceInfo, jsonObject is null");
            return;
        }
        if (jSONObject.isNull("second_resource")) {
            return;
        }
        HashMap hashMap = new HashMap(16);
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("second_resource");
            for (int i = 0; i < jSONArray.length(); i++) {
                ArrayList arrayList = new ArrayList(16);
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                arrayList.clear();
                arrayList.addAll(b(jSONObject2));
                cvm cvmVar = new cvm();
                b(cvmVar, arrayList);
                a(cvmVar, jSONObject2);
                e(cvmVar, jSONObject2);
                hashMap.putAll(d(cvmVar));
                LogUtil.c("ParseIndexAllUtils", "resourceBeanMap: ", hashMap.toString());
            }
        } catch (JSONException unused) {
            LogUtil.b("ParseIndexAllUtils", "setSecondResourceInfo JSONException");
        }
        cveVar.e(hashMap);
        LogUtil.c("ParseIndexAllUtils", "setSecondResourceBeanMap:", cveVar.aa().toString());
    }

    private static void b(cvm cvmVar, List<String> list) {
        if (list != null) {
            cvmVar.c(list);
            LogUtil.a("ParseIndexAllUtils", "setSecondResourceInfoPart secondResourceBean.getUuid: ", cvmVar.f().toString());
        }
    }

    private static void a(cvm cvmVar, JSONObject jSONObject) {
        try {
            if (!jSONObject.isNull(PluginPayAdapter.KEY_DEVICE_INFO_NAME)) {
                String string = jSONObject.getString(PluginPayAdapter.KEY_DEVICE_INFO_NAME);
                if (!TextUtils.isEmpty(string)) {
                    cvmVar.c(string);
                }
            }
            if (!jSONObject.isNull("device_description")) {
                String string2 = jSONObject.getString("device_description");
                if (!TextUtils.isEmpty(string2)) {
                    cvmVar.d(string2);
                }
            }
            if (!jSONObject.isNull("second_icon")) {
                String string3 = jSONObject.getString("second_icon");
                if (!TextUtils.isEmpty(string3)) {
                    cvmVar.e(c(string3));
                }
            }
            if (jSONObject.isNull("bluetooth_match")) {
                return;
            }
            cvmVar.a(jSONObject.optString("bluetooth_match"));
        } catch (JSONException unused) {
            LogUtil.b("ParseIndexAllUtils", "setSecondResourceDeal JSONException");
        }
    }

    private static void e(cvm cvmVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            LogUtil.h("ParseIndexAllUtils", "setUuidList, jsonObject is empty");
            return;
        }
        try {
            ArrayList arrayList = new ArrayList(16);
            if (!jSONObject.isNull("bluetooth_name")) {
                JSONArray jSONArray = jSONObject.getJSONArray("bluetooth_name");
                for (int i = 0; i < jSONArray.length(); i++) {
                    String string = jSONArray.getString(i);
                    if (!TextUtils.isEmpty(string)) {
                        arrayList.add(string);
                    }
                }
            }
            cvmVar.d(arrayList);
        } catch (JSONException unused) {
            LogUtil.b("ParseIndexAllUtils", "set uuid list is exception");
        }
    }

    private static Map<String, cvm> d(cvm cvmVar) {
        HashMap hashMap = new HashMap(16);
        hashMap.put(cvmVar.f().get(0), cvmVar);
        return hashMap;
    }

    private static List<String> b(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList(16);
        try {
            LogUtil.a("ParseIndexAllUtils", "setParseUuidList jsonObjectSecond: ", jSONObject.toString());
            if (!jSONObject.isNull("uuid")) {
                JSONArray jSONArray = jSONObject.getJSONArray("uuid");
                for (int i = 0; i < jSONArray.length(); i++) {
                    String string = jSONArray.getString(i);
                    if (TextUtils.isEmpty(string)) {
                        LogUtil.h("ParseIndexAllUtils", "uuid is null");
                    } else {
                        arrayList.add(string);
                    }
                }
            }
        } catch (JSONException unused) {
            LogUtil.b("ParseIndexAllUtils", "setParseUuidList JSONException");
        }
        return arrayList;
    }

    private static void c(cve cveVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            LogUtil.h("ParseIndexAllUtils", "setImagePath, json object is null");
            return;
        }
        cveVar.i(c(jSONObject.optString("icon")));
        String optString = jSONObject.optString("icon_backup");
        if (TextUtils.isEmpty(optString)) {
            return;
        }
        cveVar.a(c(optString));
    }

    private static void b(cve cveVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            LogUtil.h("ParseIndexAllUtils", "setPairGuid, json object is null");
            return;
        }
        String optString = jSONObject.optString("pair_guide");
        cveVar.n(optString);
        if ("4".equals(optString) && jSONObject.has("pair_guide_array")) {
            HashMap hashMap = new HashMap(16);
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("pair_guide_array");
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    Iterator<String> keys = jSONObject2.keys();
                    while (keys.hasNext()) {
                        String valueOf = String.valueOf(keys.next());
                        hashMap.put(valueOf, jSONObject2.optString(valueOf));
                    }
                }
            } catch (JSONException unused) {
                LogUtil.b("ParseIndexAllUtils", "setPairGuid json exception");
            }
            cveVar.c(hashMap);
        }
    }

    private static void d(cve cveVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            LogUtil.h("ParseIndexAllUtils", "setPrivacyImagePaths, jsonObject is empty");
            return;
        }
        try {
            if (jSONObject.isNull("privacy_image")) {
                return;
            }
            HashMap hashMap = new HashMap(16);
            JSONObject jSONObject2 = jSONObject.getJSONObject("privacy_image");
            LogUtil.h("ParseIndexAllUtils", "jsonMap is ", jSONObject2.toString());
            Iterator<String> keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String valueOf = String.valueOf(keys.next());
                hashMap.put(valueOf, c(jSONObject2.optString(valueOf)));
            }
            cveVar.d(hashMap);
        } catch (JSONException unused) {
            LogUtil.b("ParseIndexAllUtils", "set privacyImagePaths is exception");
        }
    }

    private static void e(cve cveVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            LogUtil.h("ParseIndexAllUtils", "setUuidList, jsonObject is empty");
            return;
        }
        try {
            ArrayList arrayList = new ArrayList(16);
            if (!jSONObject.isNull("uuid")) {
                JSONArray jSONArray = jSONObject.getJSONArray("uuid");
                for (int i = 0; i < jSONArray.length(); i++) {
                    String string = jSONArray.getString(i);
                    if (TextUtils.isEmpty(string)) {
                        LogUtil.h("ParseIndexAllUtils", "uuid is null");
                    } else {
                        arrayList.add(string);
                    }
                }
            }
            cveVar.d(arrayList);
        } catch (JSONException unused) {
            LogUtil.b("ParseIndexAllUtils", "set uuid list is exception");
        }
    }

    private static String c(String str) {
        return (cvy.d() + e + File.separator + str) + ".webp";
    }
}
