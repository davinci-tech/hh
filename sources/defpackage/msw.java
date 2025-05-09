package defpackage;

import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.openalliance.ad.constant.AdShowExtras;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginmgr.hwwear.bean.DeviceDownloadSourceInfo;
import com.huawei.pluginmgr.hwwear.bean.DeviceFittingsBean;
import health.compact.a.KeyValDbManager;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class msw {
    private static String c = "";
    private static String e = "";

    private static int c(int i) {
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

    public static mss e(String str) {
        mss mssVar = new mss();
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("ParesIndexAllUtils", "parseIndexFile, indexAllJson is empty");
            return mssVar;
        }
        try {
            mssVar.a(b(a(str, mssVar)));
        } catch (JSONException unused) {
            LogUtil.b("ParesIndexAllUtils", "parseIndexFile exception");
        }
        return mssVar;
    }

    private static JSONObject a(String str, mss mssVar) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        mssVar.e(jSONObject.optString("version"));
        String optString = jSONObject.optString("lang_version");
        e = optString;
        mssVar.d(optString);
        mssVar.b(d(jSONObject.getJSONArray("lang_info")));
        String optString2 = jSONObject.optString("img_version");
        c = optString2;
        mssVar.a(optString2);
        mssVar.c(d(jSONObject.getJSONArray("img_info")));
        b(jSONObject.optString(MapKeyNames.APP_INFO));
        return jSONObject;
    }

    private static void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        KeyValDbManager.b(BaseApplication.e()).e("app_info_device_cloud", str);
    }

    private static List<msz> d(JSONArray jSONArray) {
        LogUtil.a("ParesIndexAllUtils", "start getPathList");
        ArrayList arrayList = new ArrayList(16);
        if (jSONArray == null || jSONArray.length() == 0) {
            LogUtil.h("ParesIndexAllUtils", "pathArray is no data");
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject == null) {
                    LogUtil.h("ParesIndexAllUtils", "getPathList, jsonObject is null");
                } else {
                    msz mszVar = new msz();
                    mszVar.b(jSONObject.optString(BleConstants.KEY_PATH));
                    mszVar.c(jSONObject.optInt("size"));
                    mszVar.c(jSONObject.optString("digest"));
                    arrayList.add(mszVar);
                }
            } catch (JSONException unused) {
                LogUtil.b("ParesIndexAllUtils", "getPathList JSONException");
            }
        }
        LogUtil.a("ParesIndexAllUtils", "resourceList size: ", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    private static List<mta> b(JSONObject jSONObject) {
        LogUtil.a("ParesIndexAllUtils", "start setKindList");
        ArrayList arrayList = new ArrayList(16);
        if (jSONObject == null) {
            LogUtil.h("ParesIndexAllUtils", "setKindList, jsonObject is empty");
            return arrayList;
        }
        try {
            if (!jSONObject.isNull(DeviceCategoryFragment.DEVICE_KIND)) {
                JSONArray jSONArray = jSONObject.getJSONArray(DeviceCategoryFragment.DEVICE_KIND);
                for (int i = 0; i < jSONArray.length(); i++) {
                    mta mtaVar = new mta();
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    String optString = jSONObject2.optString("kind_name");
                    mtaVar.b(optString);
                    String optString2 = jSONObject2.optString("kind_id");
                    mtaVar.e(optString2);
                    String string = jSONObject2.getString("resource_site");
                    mtaVar.d(string);
                    List<msx> d = d(jSONObject2.getJSONArray("device_list"), optString2, optString, string);
                    if (d.isEmpty()) {
                        LogUtil.h("ParesIndexAllUtils", "setKindList, kindName: ", optString, " device info is empty");
                    } else {
                        mtaVar.a(d);
                        arrayList.add(mtaVar);
                    }
                }
            } else {
                LogUtil.h("ParesIndexAllUtils", "start jsonObject.isNull(DevicePluginConstants.DEVICE_KIND)");
            }
        } catch (JSONException unused) {
            LogUtil.b("ParesIndexAllUtils", "setKindList JSONException");
        }
        return arrayList;
    }

    private static List<msx> d(JSONArray jSONArray, String str, String str2, String str3) {
        ArrayList arrayList = new ArrayList(16);
        if (jSONArray == null || jSONArray.length() == 0) {
            LogUtil.h("ParesIndexAllUtils", "getDeviceKindList, jsonArray is no data");
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                msx msxVar = new msx();
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject != null) {
                    msxVar.f(d(jSONObject));
                    a(msxVar, jSONObject);
                    msxVar.b(jSONObject.optInt("cloud_type"));
                    msxVar.h(str);
                    msxVar.g(str2);
                    String optString = jSONObject.optString("brand_name");
                    msxVar.d(optString);
                    msxVar.c(optString);
                    c(jSONObject, msxVar);
                    String optString2 = jSONObject.optString(PluginPayAdapter.KEY_DEVICE_INFO_NAME);
                    msxVar.f(optString2);
                    msxVar.b(jSONObject.optString("device_description"));
                    b(msxVar, jSONObject);
                    msxVar.e(c(jSONObject.optInt("bluetooth_type")));
                    msxVar.a(jSONObject.optString("bluetooth_match"));
                    msxVar.g(jSONObject.optInt("jump_to_product_page"));
                    msxVar.f(jSONObject.optInt("product_page"));
                    msxVar.d(a(jSONObject));
                    msxVar.o(jSONObject.optString("publish_mode"));
                    int optInt = jSONObject.optInt("support_all_scan");
                    if (optInt == 0) {
                        optInt = 1;
                    }
                    msxVar.i(optInt);
                    e(str3, msxVar, jSONObject, optString2);
                    arrayList.add(msxVar);
                }
            } catch (JSONException unused) {
                LogUtil.b("ParesIndexAllUtils", "getDeviceList JSONException");
            }
        }
        return arrayList;
    }

    private static void e(String str, msx msxVar, JSONObject jSONObject, String str2) {
        int optInt = jSONObject.optInt("can_scan");
        if (optInt == 0) {
            optInt = 2;
        }
        msxVar.a(optInt);
        d(msxVar, jSONObject);
        b(jSONObject, msxVar);
        msxVar.h(jSONObject.optInt("is_aw70"));
        msxVar.c(jSONObject.optInt("device_relationship"));
        msxVar.m(str);
        JSONObject optJSONObject = jSONObject.optJSONObject(AdShowExtras.DOWNLOAD_SOURCE);
        if (optJSONObject != null) {
            LogUtil.a("ParesIndexAllUtils", "downloadSource device : ", str2);
            DeviceDownloadSourceInfo deviceDownloadSourceInfo = new DeviceDownloadSourceInfo(null);
            deviceDownloadSourceInfo.setSite(optJSONObject.optInt("site"));
            deviceDownloadSourceInfo.setIndexName(optJSONObject.optString("indexName"));
            deviceDownloadSourceInfo.setConfigurationPoint(optJSONObject.optString("configurationPoint"));
            msxVar.a(deviceDownloadSourceInfo);
        }
        JSONObject optJSONObject2 = jSONObject.optJSONObject("ecology_profile");
        if (optJSONObject2 != null) {
            LogUtil.a("ParesIndexAllUtils", "ecology device : ", str2);
            msxVar.j(optJSONObject2.optInt("ecology_type"));
            msxVar.d(optJSONObject2.optInt(PluginPayAdapter.DEVICE_CATEGORY));
            msxVar.j(optJSONObject2.optString("model"));
        }
        msxVar.a(c(jSONObject));
        e(jSONObject, msxVar);
        b(msxVar);
    }

    private static List<DeviceFittingsBean> c(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList(16);
        if (jSONObject.isNull("fittings")) {
            return arrayList;
        }
        LogUtil.a("ParesIndexAllUtils", "setDeviceFittings entry");
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("fittings");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                if (jSONObject2 == null) {
                    LogUtil.h("ParesIndexAllUtils", "setDeviceFittings fittingsJsonObject is null");
                } else {
                    DeviceFittingsBean deviceFittingsBean = new DeviceFittingsBean(null);
                    deviceFittingsBean.setBluetoothMatch(jSONObject2.optString("bluetooth_match"));
                    deviceFittingsBean.setBluetoothNameList(a(jSONObject2));
                    deviceFittingsBean.setUuidList(d(jSONObject2));
                    JSONObject optJSONObject = jSONObject2.optJSONObject(AdShowExtras.DOWNLOAD_SOURCE);
                    if (optJSONObject != null) {
                        DeviceDownloadSourceInfo deviceDownloadSourceInfo = new DeviceDownloadSourceInfo(null);
                        deviceDownloadSourceInfo.setSite(optJSONObject.optInt("site"));
                        deviceDownloadSourceInfo.setIndexName(optJSONObject.optString("indexName"));
                        deviceDownloadSourceInfo.setConfigurationPoint(optJSONObject.optString("configurationPoint"));
                        deviceFittingsBean.setDeviceDownloadSourceInfo(deviceDownloadSourceInfo);
                    } else {
                        LogUtil.a("ParesIndexAllUtils", "setDeviceFittings downloadSource null");
                    }
                    arrayList.add(deviceFittingsBean);
                }
            }
        } catch (JSONException unused) {
            LogUtil.b("ParesIndexAllUtils", "setDeviceFittings is JSONException");
        }
        return arrayList;
    }

    private static void e(JSONObject jSONObject, msx msxVar) {
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("device_detail");
            if (jSONArray == null) {
                LogUtil.h("ParesIndexAllUtils", "setDeviceDetails deviceDetailArray is null");
                return;
            }
            List<String> k = msxVar.k();
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                msu msuVar = new msu();
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                msuVar.c(jSONObject2.getString("uuid"));
                k.add(msuVar.c());
                msuVar.b(jSONObject2.getString("digest"));
                msuVar.a(jSONObject2.getString("version"));
                JSONObject optJSONObject = jSONObject2.optJSONObject("download_source_site");
                if (optJSONObject != null) {
                    DeviceDownloadSourceInfo deviceDownloadSourceInfo = new DeviceDownloadSourceInfo(null);
                    deviceDownloadSourceInfo.setSite(optJSONObject.optInt("site"));
                    deviceDownloadSourceInfo.setIndexName(optJSONObject.optString("indexName"));
                    deviceDownloadSourceInfo.setConfigurationPoint(optJSONObject.optString("configurationPoint"));
                    msuVar.c(deviceDownloadSourceInfo);
                } else {
                    LogUtil.a("ParesIndexAllUtils", "setDeviceDetails downloadSource null");
                }
                arrayList.add(msuVar);
            }
            msxVar.e(arrayList);
            msxVar.f(k);
        } catch (JSONException unused) {
            LogUtil.b("ParesIndexAllUtils", "setDeviceDetails JSONException");
        }
    }

    private static void b(msx msxVar) {
        List<msu> d = msxVar.d();
        if (d == null || d.size() == 0) {
            LogUtil.a("ParesIndexAllUtils", "setEzPluginIndexInfos devicePluginInfo is null");
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < d.size(); i++) {
            msa msaVar = new msa();
            msaVar.e(d.get(i).c());
            msaVar.c(d.get(i).a());
            msaVar.b(d.get(i).e());
            msaVar.d(d.get(i).d());
            d(msxVar, msaVar);
            msaVar.h(msxVar.o());
            msaVar.e(msxVar.a());
            arrayList.add(msaVar);
        }
        msxVar.b(arrayList);
    }

    private static void d(msx msxVar, msa msaVar) {
        if (msxVar.f().equals("wear_watch")) {
            msaVar.g("SMART_WATCH");
        } else if (msxVar.f().equals("wear_band") || msxVar.f().toUpperCase(Locale.ENGLISH).equals("SPORTS_GENIE")) {
            msaVar.g("SMART_BAND");
        } else {
            msaVar.g(msxVar.f());
        }
    }

    private static void c(JSONObject jSONObject, msx msxVar) {
        if (jSONObject == null) {
            LogUtil.h("ParesIndexAllUtils", "setOtherBand jsonObject is null");
            return;
        }
        try {
            ArrayList arrayList = new ArrayList(16);
            if (!jSONObject.isNull("brand_config")) {
                JSONArray jSONArray = jSONObject.getJSONArray("brand_config");
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    mtb mtbVar = new mtb();
                    if (!jSONObject2.isNull("country")) {
                        List<String> asList = Arrays.asList(jSONObject2.getString("country").split(";"));
                        if (!koq.b(asList)) {
                            mtbVar.c(asList);
                            if (!jSONObject2.isNull("brand_name")) {
                                mtbVar.a(jSONObject2.getString("brand_name"));
                                arrayList.add(mtbVar);
                            }
                        }
                    }
                }
            }
            msxVar.c(arrayList);
        } catch (JSONException unused) {
            LogUtil.b("ParesIndexAllUtils", "setOtherBand JSONException");
        }
    }

    private static List<String> a(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList(16);
        if (jSONObject == null) {
            LogUtil.h("ParesIndexAllUtils", "setUuidList, jsonObject is empty");
            return arrayList;
        }
        try {
            if (!jSONObject.isNull("bluetooth_name")) {
                JSONArray jSONArray = jSONObject.getJSONArray("bluetooth_name");
                for (int i = 0; i < jSONArray.length(); i++) {
                    String string = jSONArray.getString(i);
                    if (!TextUtils.isEmpty(string)) {
                        arrayList.add(string);
                    }
                }
            }
        } catch (JSONException unused) {
            LogUtil.b("ParesIndexAllUtils", "set uuid list is exception");
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x007d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void b(org.json.JSONObject r9, defpackage.msx r10) {
        /*
            java.lang.String r0 = "overseas"
            java.lang.String r1 = "domestic"
            java.lang.String r2 = "ParesIndexAllUtils"
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
            r10.d(r4)
        L83:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.msw.b(org.json.JSONObject, msx):void");
    }

    private static void b(msx msxVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            LogUtil.h("ParesIndexAllUtils", "setImagePath, json object is null");
            return;
        }
        msxVar.i(a(jSONObject.optString("icon")));
        String optString = jSONObject.optString("icon_backup");
        if (TextUtils.isEmpty(optString)) {
            return;
        }
        msxVar.e(a(optString));
    }

    private static void d(msx msxVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            LogUtil.h("ParesIndexAllUtils", "setPairGuid, json object is null");
            return;
        }
        String optString = jSONObject.optString("pair_guide");
        msxVar.k(optString);
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
                LogUtil.b("ParesIndexAllUtils", "setPairGuid json exception");
            }
            msxVar.a(hashMap);
        }
    }

    private static void a(msx msxVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            LogUtil.h("ParesIndexAllUtils", "setPrivacyImagePaths, jsonObject is empty");
            return;
        }
        try {
            if (jSONObject.isNull("privacy_image")) {
                return;
            }
            HashMap hashMap = new HashMap(16);
            JSONObject jSONObject2 = jSONObject.getJSONObject("privacy_image");
            LogUtil.h("ParesIndexAllUtils", "jsonMap is ", jSONObject2.toString());
            Iterator<String> keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String valueOf = String.valueOf(keys.next());
                hashMap.put(valueOf, a(jSONObject2.optString(valueOf)));
            }
            msxVar.e(hashMap);
        } catch (JSONException unused) {
            LogUtil.b("ParesIndexAllUtils", "set privacyImagePaths is exception");
        }
    }

    private static List<String> d(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList(16);
        if (jSONObject == null) {
            LogUtil.h("ParesIndexAllUtils", "setUuidList, jsonObject is empty");
            return arrayList;
        }
        try {
            if (!jSONObject.isNull("uuid")) {
                JSONArray jSONArray = jSONObject.getJSONArray("uuid");
                for (int i = 0; i < jSONArray.length(); i++) {
                    String string = jSONArray.getString(i);
                    if (TextUtils.isEmpty(string)) {
                        LogUtil.h("ParesIndexAllUtils", "uuid is null");
                    } else {
                        arrayList.add(string);
                    }
                }
            }
        } catch (JSONException unused) {
            LogUtil.b("ParesIndexAllUtils", "set uuid list is exception");
        }
        return arrayList;
    }

    private static String a(String str) {
        return (msv.b() + c + File.separator + str) + ".webp";
    }
}
