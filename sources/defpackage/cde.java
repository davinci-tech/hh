package defpackage;

import android.text.TextUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DeviceDownloadSourceInfo;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hms.network.embedded.x2;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.openalliance.ad.constant.AdShowExtras;
import health.compact.a.CommonUtil;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
class cde {
    public static String e(File file) {
        if (file == null) {
            LogUtil.a("IndexParser", "readFileToData, file == null");
            return null;
        }
        LogUtil.c("IndexParser", "enter readFileToData: fileName = ", file.getName());
        if (!file.exists()) {
            LogUtil.a("IndexParser", "readFileToData, file not exist");
            return null;
        }
        try {
            return FileUtils.b(file, 5242880L);
        } catch (IOException e) {
            LogUtil.a("IndexParser", "readFileToData, ex=", LogUtil.a(e));
            return "";
        }
    }

    public static cdi c(String str) {
        LogUtil.c("IndexParser", "parseIndexFile enter parseIndexFile:");
        cdi cdiVar = new cdi();
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("IndexParser", "parseIndexFile indexJson is empty or null");
            return cdiVar;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.isNull("plugins")) {
                cdiVar.d(jSONObject.optString("version"));
                cdiVar.c(jSONObject.optString("updatedTime"));
                cdiVar.a(e(jSONObject.getJSONArray("plugins")));
            } else {
                sqo.k("parseIndexFile plugins convert exception.");
                ReleaseLogUtil.b("R_IndexParser", "parseIndexFile plugins convert exception");
            }
        } catch (JSONException unused) {
            LogUtil.e("IndexParser", "parseIndexFile JSONException");
        }
        return cdiVar;
    }

    private static List<cvk> e(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            LogUtil.a("IndexParser", "parsePlugins jsonArrayPlugins is null or no data");
            return new ArrayList(0);
        }
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject != null) {
                    b(jSONObject, arrayList);
                } else {
                    LogUtil.a("IndexParser", "parsePlugins jsonObject is null.");
                }
            } catch (JSONException unused) {
                LogUtil.e("IndexParser", "parsePlugins JSONException");
            }
        }
        return arrayList;
    }

    private static void b(JSONObject jSONObject, List<cvk> list) throws JSONException {
        cvk cvkVar = new cvk();
        d(jSONObject, cvkVar);
        if (jSONObject.has("applyRules")) {
            a(cvkVar, jSONObject);
        }
        if (jSONObject.has("wearkind") && !jSONObject.isNull("wearkind")) {
            cvkVar.f(jSONObject.getString("wearkind"));
        }
        if (jSONObject.has("publish_mode") && !jSONObject.isNull("publish_mode")) {
            cvkVar.i(jSONObject.getString("publish_mode"));
        }
        if (jSONObject.has(x2.PROTOCOL) && !jSONObject.isNull(x2.PROTOCOL)) {
            cvkVar.g(jSONObject.getString(x2.PROTOCOL));
        }
        if (jSONObject.has("support_mode") && !jSONObject.isNull("support_mode")) {
            cvkVar.h(jSONObject.getString("support_mode"));
        }
        if (jSONObject.has("publish_version") && !jSONObject.isNull("publish_version")) {
            b(cvkVar, jSONObject);
        }
        if (jSONObject.has(AdShowExtras.DOWNLOAD_SOURCE) && !jSONObject.isNull(AdShowExtras.DOWNLOAD_SOURCE)) {
            c(cvkVar, jSONObject.optJSONObject(AdShowExtras.DOWNLOAD_SOURCE));
        }
        if (jSONObject.has("oobe")) {
            cvkVar.j(jSONObject.getString("oobe"));
        }
        list.add(cvkVar);
    }

    private static void d(JSONObject jSONObject, cvk cvkVar) throws JSONException {
        if (!jSONObject.isNull("uuid")) {
            cvkVar.d(jSONObject.getString("uuid"));
        }
        if (!jSONObject.isNull("version")) {
            cvkVar.a(jSONObject.getString("version"));
        }
        if (!jSONObject.isNull("digest")) {
            cvkVar.b(jSONObject.getString("digest"));
        }
        if (!jSONObject.isNull("filename")) {
            cvkVar.c(jSONObject.getString("filename"));
        }
        if (!jSONObject.isNull("fileSize")) {
            cvkVar.c(CommonUtil.e(jSONObject.getString("fileSize"), 0));
        }
        if (!jSONObject.isNull("fileCount")) {
            cvkVar.d(jSONObject.getLong("fileCount"));
        }
        if (jSONObject.isNull("descUrl")) {
            return;
        }
        cvkVar.e(jSONObject.getString("descUrl"));
    }

    private static void a(cvk cvkVar, JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2;
        if (jSONObject.isNull("applyRules") || (jSONObject2 = jSONObject.getJSONObject("applyRules")) == null) {
            return;
        }
        cvf cvfVar = new cvf();
        if (jSONObject2.has("minAppVersion")) {
            cvfVar.b(jSONObject2.getString("minAppVersion"));
        }
        if (jSONObject2.has("minIndexVersion")) {
            cvfVar.c(jSONObject2.getString("minIndexVersion"));
        }
        cvkVar.e(cvfVar);
    }

    public static cvc e(String str) {
        cvc cvcVar = new cvc();
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("IndexParser", "parseDescJsonFile descriptionJson is empty or null");
            return cvcVar;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            cvcVar.b(jSONObject.optString("form"));
            cvcVar.i(jSONObject.optString("updatedTime"));
            cvcVar.h(jSONObject.optString("uuid"));
            cvcVar.f(jSONObject.optString("version"));
            a(cvcVar, jSONObject);
            d(cvcVar, jSONObject);
            c(cvcVar, jSONObject);
            cvcVar.a(CommonUtil.e(jSONObject.optString("fileSize"), 0));
            cvcVar.e(jSONObject.optString("filename"));
            cvcVar.a(jSONObject.optString("fileType"));
            cvcVar.d(jSONObject.optString("digest"));
            b(cvcVar, jSONObject);
        } catch (JSONException unused) {
            LogUtil.e("IndexParser", "parseDescJsonFile JSONException");
        }
        return cvcVar;
    }

    private static void a(cvc cvcVar, JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray("name");
        if (optJSONArray == null) {
            return;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            try {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                if (!jSONObject2.isNull("en_us")) {
                    String string = jSONObject2.getString("en_us");
                    cvcVar.g(jSONObject2.getString("en_us"));
                    LogUtil.d("IndexParser", "parsePluginName name_enUs : ", string);
                }
                if (!jSONObject2.isNull("zh_rCN")) {
                    LogUtil.d("IndexParser", "parsePluginName name_zh_rCN : ", jSONObject2.getString("zh_rCN"));
                }
            } catch (JSONException unused) {
                LogUtil.e("IndexParser", "parsePluginName JSONException");
            }
        }
    }

    private static void c(cvc cvcVar, JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray("description");
        if (optJSONArray == null) {
            return;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            try {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                if (!jSONObject2.isNull("en_us")) {
                    String string = jSONObject2.getString("en_us");
                    cvcVar.c(string);
                    LogUtil.d("IndexParser", "parsePluginAuthorization description_enUs : ", string);
                }
                if (!jSONObject2.isNull("zh_rCN")) {
                    LogUtil.d("IndexParser", "parsePluginAuthorization description_zh_rCN : ", jSONObject2.getString("zh_rCN"));
                }
            } catch (JSONException unused) {
                LogUtil.e("IndexParser", "parsePluginAuthorization JSONException");
            }
        }
    }

    private static void d(cvc cvcVar, JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("authorization");
        if (optJSONObject == null) {
            return;
        }
        JSONArray optJSONArray = optJSONObject.optJSONArray("permissions");
        if (optJSONArray != null) {
            LogUtil.d("IndexParser", "parsePluginDescription jsonArrayPermissions = ", optJSONArray);
        }
        JSONArray optJSONArray2 = optJSONObject.optJSONArray("prompt");
        if (optJSONArray2 == null) {
            return;
        }
        for (int i = 0; i < optJSONArray2.length(); i++) {
            try {
                JSONObject jSONObject2 = optJSONArray2.getJSONObject(i);
                if (!jSONObject2.isNull("en_us")) {
                    String string = jSONObject2.getString("en_us");
                    cvcVar.j(string);
                    LogUtil.d("IndexParser", "parsePluginDescription authorization_enUs : ", string);
                }
                if (!jSONObject2.isNull("zh_rCN")) {
                    LogUtil.d("IndexParser", "parsePluginDescription authorization_zh_rCN : ", jSONObject2.getString("zh_rCN"));
                }
            } catch (JSONException unused) {
                LogUtil.e("IndexParser", "parsePluginDescription JSONException");
            }
        }
    }

    private static void b(cvc cvcVar, JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("wear_device_info")) {
            cvj cvjVar = new cvj();
            JSONObject jSONObject2 = jSONObject.getJSONObject("wear_device_info");
            d(cvcVar, cvjVar, jSONObject2);
            a(cvjVar, jSONObject2);
            l(cvjVar, jSONObject2);
            c(cvjVar, jSONObject2);
            j(cvjVar, jSONObject2);
            h(cvjVar, jSONObject2);
            i(cvjVar, jSONObject2);
            b(cvjVar, jSONObject2);
            d(cvjVar, jSONObject2);
            e(cvjVar, jSONObject2);
            q(cvjVar, jSONObject2);
            a(cvcVar, cvjVar, jSONObject2);
            e(cvcVar, cvjVar, jSONObject2);
            n(cvjVar, jSONObject2);
            r(cvjVar, jSONObject2);
            cvcVar.c(cvjVar);
            LogUtil.c("IndexParser", "parseWearDeviceInfo deviceInfo :", cvjVar.toString());
        }
    }

    private static void r(cvj cvjVar, JSONObject jSONObject) throws JSONException {
        if (cvjVar == null || jSONObject == null) {
            LogUtil.a("IndexParser", "parseWearFunctionSupport param is null.");
            return;
        }
        if (jSONObject.has("help_support")) {
            cvjVar.b(jSONObject.getBoolean("help_support"));
        }
        if (jSONObject.has("hotline_support")) {
            cvjVar.i(jSONObject.getBoolean("hotline_support"));
        }
        if (jSONObject.has("huafen_support")) {
            cvjVar.j(jSONObject.getBoolean("huafen_support"));
        }
    }

    private static void n(cvj cvjVar, JSONObject jSONObject) throws JSONException {
        LogUtil.c("IndexParser", "parseWearDeviceEarphoneDeepLink enter");
        if (cvjVar == null || jSONObject == null) {
            LogUtil.a("IndexParser", "parseWearDeviceEarphoneDeepLink param is null.");
        } else if (jSONObject.has("earphone_find_uri")) {
            cvjVar.x(jSONObject.getString("earphone_find_uri"));
        }
    }

    private static void e(cvc cvcVar, cvj cvjVar, JSONObject jSONObject) throws JSONException {
        LogUtil.c("IndexParser", "parseWearDevicePairGuide enter");
        if (cvcVar == null || cvjVar == null || jSONObject == null) {
            LogUtil.a("IndexParser", "parseWearDevicePairGuide param is null.");
            return;
        }
        if (!jSONObject.has("pair_guide_steps")) {
            LogUtil.a("IndexParser", "WEAR_DEVICE_PAIR_GUIDE_STEPS has no this key.");
            return;
        }
        JSONObject jSONObject2 = jSONObject.getJSONObject("pair_guide_steps");
        if (jSONObject2.has("pair_guide_steps_description")) {
            JSONArray optJSONArray = jSONObject2.optJSONArray("pair_guide_steps_description");
            LogUtil.c("IndexParser", "pairGuideDescArray param has WEAR_DEVICE_PAIR_GUIDE_STEPS_DESC_KEY.");
            if (optJSONArray == null) {
                LogUtil.a("IndexParser", "pairGuideDescArray param is null.");
                return;
            } else {
                List<String> a2 = a(cvcVar, optJSONArray);
                if (a2 instanceof ArrayList) {
                    cvjVar.l((ArrayList<String>) a2);
                }
            }
        }
        if (jSONObject2.has("pair_guide_steps_img")) {
            JSONArray optJSONArray2 = jSONObject2.optJSONArray("pair_guide_steps_img");
            if (optJSONArray2 == null) {
                LogUtil.c("IndexParser", "pairGuideImgArray param is null.");
                return;
            }
            ArrayList<String> arrayList = new ArrayList<>(optJSONArray2.length());
            for (int i = 0; i < optJSONArray2.length(); i++) {
                arrayList.add(optJSONArray2.optString(i));
            }
            LogUtil.c("IndexParser", "guideImgList param is :", arrayList.toString());
            cvjVar.m(arrayList);
        }
    }

    private static List<String> a(cvc cvcVar, JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            List<String> c = cdk.c(jSONArray.optString(i), cvcVar);
            if (c != null && !c.isEmpty()) {
                arrayList.add(c.get(0));
            }
        }
        LogUtil.c("IndexParser", "guideDescList param is :", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    private static void q(cvj cvjVar, JSONObject jSONObject) throws JSONException {
        LogUtil.c("IndexParser", "parseWearDeviceProductInfo enter");
        if (cvjVar != null && jSONObject != null) {
            LogUtil.d("IndexParser", "parseWearDeviceProductInfo wearObject:", jSONObject.toString());
            if (jSONObject.has("product")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("product");
                LogUtil.c("IndexParser", "parseWearDeviceProductInfo productObject:", jSONObject2.toString());
                cvjVar.d(jSONObject2.toString());
                if (jSONObject2.has("product_default")) {
                    JSONObject jSONObject3 = jSONObject2.getJSONObject("product_default");
                    if (jSONObject3.has("entry_image_sku")) {
                        e(cvjVar, jSONObject3, "entry_image_sku");
                    }
                    if (jSONObject3.has("home_background_image_sku")) {
                        e(cvjVar, jSONObject3, "home_background_image_sku");
                    }
                    if (jSONObject3.has("ota_background_image_sku")) {
                        e(cvjVar, jSONObject3, "ota_background_image_sku");
                    }
                    if (jSONObject3.has("disconnect_background_image_sku")) {
                        e(cvjVar, jSONObject3, "disconnect_background_image_sku");
                    }
                    if (jSONObject3.has("ear_muff_install")) {
                        e(cvjVar, jSONObject3, "ear_muff_install");
                    }
                    if (jSONObject3.has("ear_muff_removal")) {
                        LogUtil.c("IndexParser", "defaultObject.has(DownloadResourceConstants.ERA_MUFF_REMOVAL)");
                        e(cvjVar, jSONObject3, "ear_muff_removal");
                        return;
                    }
                    return;
                }
                return;
            }
            LogUtil.c("IndexParser", "parseWearDeviceProductInfo wearObject no product key");
            return;
        }
        LogUtil.a("IndexParser", "parseWearDeviceInfoModelImage param is null.");
    }

    private static void a(cvc cvcVar, cvj cvjVar, JSONObject jSONObject) throws JSONException {
        LogUtil.c("IndexParser", "parseWearDeviceUserGuide enter");
        if (cvcVar == null || cvjVar == null || jSONObject == null) {
            LogUtil.c("IndexParser", "parseWearDeviceUserGuide param is null.");
            return;
        }
        if (jSONObject.has("user_guide")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("user_guide");
            LogUtil.c("IndexParser", "userGuideLineObject.has(DownloadResourceConstants.WEAR_DEVICE_USER_GUIDE_LINE_KEY)");
            if (jSONObject2.has("guide_data")) {
                b(cvcVar, cvjVar, jSONObject2.optJSONArray("guide_data"));
            }
            if (jSONObject2.has("guide_frame")) {
                cvjVar.j(jSONObject2.optInt("guide_frame"));
            }
            if (jSONObject2.has("design_guide_data")) {
                e(cvcVar, cvjVar, jSONObject2.optJSONArray("design_guide_data"));
            }
        }
    }

    private static void e(cvc cvcVar, cvj cvjVar, JSONArray jSONArray) throws JSONException {
        LogUtil.c("IndexParser", "enter parseDesignGuideData");
        if (jSONArray == null) {
            LogUtil.c("IndexParser", "parseDesignGuideData param is null.");
        } else {
            cvjVar.e(b(cvcVar, jSONArray));
        }
    }

    private static void b(cvc cvcVar, cvj cvjVar, JSONArray jSONArray) throws JSONException {
        LogUtil.c("IndexParser", "enter parseUserGuideData");
        if (jSONArray == null) {
            LogUtil.c("IndexParser", "parseUserGuideData param is null.");
        } else {
            cvjVar.k(b(cvcVar, jSONArray));
        }
    }

    private static ArrayList<cvh> b(cvc cvcVar, JSONArray jSONArray) throws JSONException {
        String optString;
        JSONArray optJSONArray;
        JSONArray jSONArray2;
        ArrayList<cvh> arrayList = new ArrayList<>();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            cvh cvhVar = new cvh();
            a(cvhVar, cvcVar, optJSONObject);
            if (optJSONObject.has("guide_description_list") && (jSONArray2 = optJSONObject.getJSONArray("guide_description_list")) != null) {
                ArrayList<String> arrayList2 = new ArrayList<>();
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    List<String> c = cdk.c(jSONArray2.getString(i2), cvcVar);
                    if (c.size() > 0) {
                        arrayList2.add(c.get(0));
                    }
                }
                cvhVar.d(arrayList2);
            }
            if (optJSONObject.has("guide_image") && (optJSONArray = optJSONObject.optJSONArray("guide_image")) != null) {
                LogUtil.c("IndexParser", "guide data has image");
                ArrayList<String> arrayList3 = new ArrayList<>(optJSONArray.length());
                for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                    arrayList3.add(optJSONArray.optString(i3));
                }
                cvhVar.b(arrayList3);
            }
            if (optJSONObject.has("design_guide_hiLinkDeviceId") && (optString = optJSONObject.optString("design_guide_hiLinkDeviceId")) != null) {
                cvhVar.d(optString);
            }
            arrayList.add(cvhVar);
        }
        return arrayList;
    }

    private static void a(cvh cvhVar, cvc cvcVar, JSONObject jSONObject) throws JSONException {
        LogUtil.c("IndexParser", "enter parseUserGuidLineString");
        if (cvcVar == null || jSONObject == null) {
            LogUtil.a("IndexParser", "parseUserGuidLineString param is null");
            return;
        }
        if (jSONObject.has("guide_title")) {
            List<String> c = cdk.c(jSONObject.optString("guide_title"), cvcVar);
            if (c.size() > 0) {
                cvhVar.a(c.get(0));
            }
        }
        if (jSONObject.has("guide_description")) {
            List<String> c2 = cdk.c(jSONObject.optString("guide_description"), cvcVar);
            if (c2.size() > 0) {
                cvhVar.e(c2.get(0));
            }
        }
    }

    private static void e(cvj cvjVar, JSONObject jSONObject, String str) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONArray(str);
        ArrayList<String> arrayList = new ArrayList<>(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.optString(i));
        }
        if (str.equals("entry_image_sku")) {
            cvjVar.f(arrayList);
            return;
        }
        if (str.equals("home_background_image_sku")) {
            cvjVar.d(arrayList);
            return;
        }
        if (str.equals("ota_background_image_sku")) {
            cvjVar.o(arrayList);
            return;
        }
        if (str.equals("disconnect_background_image_sku")) {
            cvjVar.j(arrayList);
            return;
        }
        if (str.equals("ear_muff_install")) {
            cvjVar.g(arrayList);
        } else if (str.equals("ear_muff_removal")) {
            cvjVar.h(arrayList);
        } else {
            LogUtil.a("IndexParser", "parseWearDeviceImageList else branch.");
        }
    }

    private static void d(cvc cvcVar, cvj cvjVar, JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("entry_img")) {
            cvjVar.ag(jSONObject.getString("entry_img"));
        }
        if (jSONObject.has("buy_url")) {
            cvjVar.i(jSONObject.getString("buy_url"));
        }
        if (jSONObject.has("des_img")) {
            k(cvjVar, jSONObject);
        }
        if (jSONObject.has("guide")) {
            m(cvjVar, jSONObject);
        }
        if (jSONObject.has("home_img_new")) {
            cvjVar.z(jSONObject.getString("home_img_new"));
        }
        if (jSONObject.has("home_background_img")) {
            cvjVar.z(jSONObject.getString("home_background_img"));
        }
        if (jSONObject.has("home_background_power_img")) {
            cvjVar.aa(jSONObject.getString("home_background_power_img"));
        }
        if (jSONObject.has("update_img")) {
            cvjVar.as(jSONObject.getString("update_img"));
        }
        if (jSONObject.has("device_brand")) {
            cvjVar.a(jSONObject.getString("device_brand"));
        }
        if (jSONObject.has("bluetooth_name")) {
            o(cvjVar, jSONObject);
        }
        if (jSONObject.has("device_string")) {
            e(cvjVar, cvcVar, jSONObject.getJSONArray("device_string"));
        } else {
            cvjVar.c(false);
        }
        if (jSONObject.has("esim_img")) {
            g(cvjVar, jSONObject);
        }
        if (jSONObject.has("esim_operator_url")) {
            f(cvjVar, jSONObject);
        }
    }

    private static void k(cvj cvjVar, JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONArray("des_img");
        ArrayList<String> arrayList = new ArrayList<>(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.optString(i));
        }
        cvjVar.a(arrayList);
    }

    private static void m(cvj cvjVar, JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("guide");
        if (jSONObject2.has("guide_img")) {
            cvjVar.ab(jSONObject2.getString("guide_img"));
        }
        if (jSONObject2.has("ani_img")) {
            JSONArray jSONArray = jSONObject2.getJSONArray("ani_img");
            ArrayList<String> arrayList = new ArrayList<>(jSONArray.length());
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(jSONArray.optString(i));
            }
            cvjVar.i(arrayList);
        }
        if (jSONObject2.has("background_img")) {
            cvjVar.ad(jSONObject2.getString("background_img"));
        }
    }

    private static void g(cvj cvjVar, JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("esim_img");
        if (jSONObject2.has("cmcc_install_guide_img")) {
            cvjVar.g(jSONObject2.getString("cmcc_install_guide_img"));
        }
        if (jSONObject2.has("cmcc_open_guide_img")) {
            cvjVar.m(jSONObject2.getString("cmcc_open_guide_img"));
        }
        if (jSONObject2.has("cmcc_install_guide_standalone_img")) {
            cvjVar.h(jSONObject2.getString("cmcc_install_guide_standalone_img"));
        }
        if (jSONObject2.has("cucc_install_guide_img")) {
            cvjVar.q(jSONObject2.getString("cucc_install_guide_img"));
        }
        if (jSONObject2.has("cucc_open_guide_img")) {
            cvjVar.p(jSONObject2.getString("cucc_open_guide_img"));
        }
        if (jSONObject2.has("ctcc_install_guide_img")) {
            cvjVar.k(jSONObject2.getString("ctcc_install_guide_img"));
        }
        if (jSONObject2.has("ctcc_open_guide_img")) {
            cvjVar.o(jSONObject2.getString("ctcc_open_guide_img"));
        }
        if (jSONObject2.has("cmcc_install_guide_img_no_text")) {
            cvjVar.j(jSONObject2.getString("cmcc_install_guide_img_no_text"));
        }
        if (jSONObject2.has("cucc_install_guide_img_no_text")) {
            cvjVar.t(jSONObject2.getString("cucc_install_guide_img_no_text"));
        }
        if (jSONObject2.has("ctcc_install_guide_img_no_text")) {
            cvjVar.l(jSONObject2.getString("ctcc_install_guide_img_no_text"));
        }
        if (jSONObject2.has("operator_open_guide_img_no_text")) {
            cvjVar.af(jSONObject2.getString("operator_open_guide_img_no_text"));
        }
    }

    private static void f(cvj cvjVar, JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("esim_operator_url");
        if (jSONObject2.has("cucc_operator_url")) {
            cvjVar.w(jSONObject2.getString("cucc_operator_url"));
        }
        if (jSONObject2.has("ctcc_operator_url")) {
            cvjVar.s(jSONObject2.getString("ctcc_operator_url"));
        }
        if (jSONObject2.has("cmcc_operator_standalone_url")) {
            cvjVar.n(jSONObject2.getString("cmcc_operator_standalone_url"));
        }
        if (jSONObject2.has("cucc_operator_standalone_url")) {
            cvjVar.y(jSONObject2.getString("cucc_operator_standalone_url"));
        }
        if (jSONObject2.has("ctcc_operator_standalone_url")) {
            cvjVar.r(jSONObject2.getString("ctcc_operator_standalone_url"));
        }
    }

    private static void o(cvj cvjVar, JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONArray("bluetooth_name");
        ArrayList<String> arrayList = new ArrayList<>(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getString(i));
        }
        cvjVar.b(arrayList);
    }

    private static void e(cvj cvjVar, cvc cvcVar, JSONArray jSONArray) throws JSONException {
        if (cvjVar == null || cvcVar == null || jSONArray == null) {
            LogUtil.a("IndexParser", "parseDescriptionDeviceString param is null");
            return;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            String string = jSONArray.getString(i);
            if (string != null) {
                c(cvjVar, cvcVar, string);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static void c(cvj cvjVar, cvc cvcVar, String str) throws JSONException {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -2019560437:
                if (str.equals("pairCompleteTips")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1802743328:
                if (str.equals("track_device_name")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1724546052:
                if (str.equals("description")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -537922217:
                if (str.equals("pair_guide")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 681349922:
                if (str.equals("briefDescription")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 780988929:
                if (str.equals("deviceName")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            e(cvjVar, cvcVar);
            return;
        }
        if (c == 1) {
            List<String> c2 = cdk.c("track_device_name", cvcVar);
            if (c2.size() > 0) {
                cvjVar.ak(c2.get(0));
                return;
            }
            return;
        }
        if (c == 2) {
            List<String> c3 = cdk.c("description", cvcVar);
            if (c3.size() > 0) {
                if (c3 instanceof ArrayList) {
                    cvjVar.c((ArrayList<String>) c3);
                    return;
                } else {
                    cvjVar.c(new ArrayList<>(c3));
                    return;
                }
            }
            return;
        }
        if (c == 3) {
            List<String> c4 = cdk.c("pair_guide", cvcVar);
            if (c4.size() > 0) {
                cvjVar.aj(c4.get(0));
                return;
            }
            return;
        }
        if (c == 4) {
            List<String> c5 = cdk.c("briefDescription", cvcVar);
            if (c5.size() > 0) {
                cvjVar.c(c5.get(0));
                return;
            }
            return;
        }
        if (c == 5) {
            List<String> c6 = cdk.c("deviceName", cvcVar);
            if (c6.size() > 0) {
                cvjVar.u(c6.get(0));
                return;
            }
            return;
        }
        d(cvjVar, cvcVar, str);
    }

    private static void d(cvj cvjVar, cvc cvcVar, String str) {
        str.hashCode();
        if (str.equals("awakenDevice")) {
            List<String> c = cdk.c("awakenDevice", cvcVar);
            if (c.size() > 0) {
                cvjVar.b(c.get(0));
            }
        }
    }

    private static void e(cvj cvjVar, cvc cvcVar) {
        List<String> c = cdk.c("pairCompleteTips", cvcVar);
        if (c.size() > 0) {
            cvjVar.ai(c.get(0));
        }
    }

    private static void a(cvj cvjVar, JSONObject jSONObject) {
        if (cvjVar == null || jSONObject == null) {
            LogUtil.a("IndexParser", "parseDescriptionAppVersion param is null");
            return;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("direct_version");
        if (optJSONArray == null) {
            return;
        }
        HashMap hashMap = new HashMap(16);
        int i = -1;
        int i2 = -1;
        for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
            try {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i3);
                if (!jSONObject2.isNull("domestic")) {
                    i = jSONObject2.getInt("domestic");
                    LogUtil.d("IndexParser", "appVersionDomestic : ", Integer.valueOf(i));
                }
                if (!jSONObject2.isNull("overseas")) {
                    i2 = jSONObject2.getInt("overseas");
                    LogUtil.d("IndexParser", "appVersionOverseas : ", Integer.valueOf(i2));
                }
            } catch (JSONException unused) {
                LogUtil.e("IndexParser", "parseDescriptionAppVersion JSONException");
            }
        }
        if (i == -1 && i2 == -1) {
            LogUtil.c("IndexParser", "AppVersion parameters not configured.");
            return;
        }
        if (i != -1) {
            hashMap.put("domestic", Integer.valueOf(i));
        }
        if (i2 != -1) {
            hashMap.put("overseas", Integer.valueOf(i2));
        }
        cvjVar.b(hashMap);
    }

    private static void b(cvk cvkVar, JSONObject jSONObject) {
        if (cvkVar == null || jSONObject == null) {
            LogUtil.a("IndexParser", "parsePublishVersion param is null");
            return;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("publish_version");
        if (optJSONArray == null) {
            return;
        }
        HashMap hashMap = new HashMap(16);
        String str = null;
        String str2 = null;
        for (int i = 0; i < optJSONArray.length(); i++) {
            try {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                if (!jSONObject2.isNull("domestic")) {
                    str = jSONObject2.getString("domestic");
                    LogUtil.d("IndexParser", "publishVersionDomestic : ", str);
                }
                if (!jSONObject2.isNull("overseas")) {
                    str2 = jSONObject2.getString("overseas");
                    LogUtil.d("IndexParser", "publishVersionOverseas : ", str2);
                }
            } catch (JSONException unused) {
                LogUtil.e("IndexParser", "parsePublishVersion JSONException");
            }
        }
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            LogUtil.a("IndexParser", "publicVersion parameters not configured.");
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            hashMap.put("domestic", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("overseas", str2);
        }
        cvkVar.a(hashMap);
    }

    private static void c(cvk cvkVar, JSONObject jSONObject) {
        if (cvkVar == null || jSONObject == null) {
            LogUtil.a("IndexParser", "parseDownloadSource param is null");
            return;
        }
        DeviceDownloadSourceInfo deviceDownloadSourceInfo = new DeviceDownloadSourceInfo(null);
        if (jSONObject.has("site")) {
            deviceDownloadSourceInfo.setSite(jSONObject.optInt("site"));
        }
        if (jSONObject.has("configurationPoint")) {
            deviceDownloadSourceInfo.setConfigurationPoint(jSONObject.optString("configurationPoint"));
        }
        if (jSONObject.has("indexName")) {
            deviceDownloadSourceInfo.setIndexName(jSONObject.optString("indexName"));
        }
        cvkVar.b(deviceDownloadSourceInfo);
    }

    private static void h(cvj cvjVar, JSONObject jSONObject) {
        if (cvjVar == null || jSONObject == null) {
            LogUtil.a("IndexParser", "parseLegalInformation param is null");
            return;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("legal_information");
        if (optJSONArray == null) {
            LogUtil.a("IndexParser", "parseLegalInformation jsonArrayLegalInformation is null");
            return;
        }
        HashMap hashMap = new HashMap(16);
        for (int i = 0; i < optJSONArray.length(); i++) {
            try {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                if (!jSONObject2.isNull("user_agreement")) {
                    String string = jSONObject2.getString("user_agreement");
                    LogUtil.d("IndexParser", "legalUserAgreementUrl : ", string);
                    hashMap.put("user_agreement", string);
                }
            } catch (JSONException unused) {
                LogUtil.e("IndexParser", "parseHonorHelpUrl JSONException");
            }
        }
        if (hashMap.isEmpty()) {
            return;
        }
        cvjVar.c(hashMap);
    }

    private static void j(cvj cvjVar, JSONObject jSONObject) {
        if (cvjVar == null || jSONObject == null) {
            LogUtil.a("IndexParser", "parseDescriptionHelpUrl param is null");
            return;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("new_honor_help_url");
        if (optJSONArray == null) {
            return;
        }
        HashMap hashMap = new HashMap(16);
        String str = "";
        String str2 = "";
        for (int i = 0; i < optJSONArray.length(); i++) {
            try {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                if (!jSONObject2.isNull("en_us")) {
                    str = jSONObject2.getString("en_us");
                    LogUtil.d("IndexParser", "honorHelpUrl_enUs : ", str);
                }
                if (!jSONObject2.isNull("zh_rCN")) {
                    str2 = jSONObject2.getString("zh_rCN");
                    LogUtil.d("IndexParser", "honorHelpUrl_zh_rCN : ", str2);
                }
            } catch (JSONException unused) {
                LogUtil.e("IndexParser", "parseHonorHelpUrl JSONException");
            }
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a("IndexParser", "honorHelpUrlEnEnglish : ", str, " helpUrlEnChinese : ", str2);
            return;
        }
        hashMap.put("en_us", str);
        hashMap.put("zh_rCN", str2);
        cvjVar.e(hashMap);
    }

    private static void l(cvj cvjVar, JSONObject jSONObject) {
        if (cvjVar == null || jSONObject == null) {
            LogUtil.a("IndexParser", "parseProductWearHomeBackground param is null");
            return;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("product_wearhome_background");
        if (optJSONObject == null) {
            LogUtil.a("IndexParser", "parseProductWearHomeBackground productBackground is null");
            return;
        }
        HashMap hashMap = new HashMap();
        JSONArray names = optJSONObject.names();
        for (int i = 0; i < names.length(); i++) {
            String optString = names.optString(i);
            HashMap hashMap2 = new HashMap();
            hashMap.put(optString, hashMap2);
            JSONObject optJSONObject2 = optJSONObject.optJSONObject(optString);
            if (optJSONObject2 != null) {
                hashMap2.put("light", optJSONObject2.optString("light", ""));
                hashMap2.put("dark", optJSONObject2.optString("dark", ""));
            }
        }
        cvjVar.d(hashMap);
    }

    private static void c(cvj cvjVar, JSONObject jSONObject) {
        if (cvjVar == null || jSONObject == null) {
            LogUtil.a("IndexParser", "parseDescriptionHelpUrl param is null");
            return;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("help_url");
        if (optJSONArray == null) {
            return;
        }
        HashMap hashMap = new HashMap(16);
        String str = "";
        String str2 = "";
        for (int i = 0; i < optJSONArray.length(); i++) {
            try {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                if (!jSONObject2.isNull("en_us")) {
                    str = jSONObject2.getString("en_us");
                    LogUtil.d("IndexParser", "helpUrl_enUs : ", str);
                }
                if (!jSONObject2.isNull("zh_rCN")) {
                    str2 = jSONObject2.getString("zh_rCN");
                    LogUtil.d("IndexParser", "helpUrl_zh_rCN : ", str2);
                }
            } catch (JSONException unused) {
                LogUtil.e("IndexParser", "parseDescriptionHelpUrl JSONException");
            }
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a("IndexParser", "helpUrlEnEnglish : ", str, " helpUrlEnChinese : ", str2);
            return;
        }
        hashMap.put("en_us", str);
        hashMap.put("zh_rCN", str2);
        cvjVar.a(hashMap);
    }

    private static void i(cvj cvjVar, JSONObject jSONObject) {
        if (cvjVar == null || jSONObject == null) {
            LogUtil.a("IndexParser", "parseDescriptionUpgradeCycle param is null");
            return;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("upgrade_cycle");
        if (optJSONArray == null) {
            return;
        }
        HashMap hashMap = new HashMap(16);
        String str = "";
        String str2 = "";
        for (int i = 0; i < optJSONArray.length(); i++) {
            try {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                if (!jSONObject2.isNull("upgrade_start")) {
                    str = jSONObject2.getString("upgrade_start");
                    LogUtil.d("IndexParser", "UpdateCycle_start : ", str);
                }
                if (!jSONObject2.isNull("upgrade_end")) {
                    str2 = jSONObject2.getString("upgrade_end");
                    LogUtil.d("IndexParser", "UpdateCycle_end : ", str2);
                }
            } catch (JSONException unused) {
                LogUtil.e("IndexParser", "parseDescriptionUpgradeCycle JSONException");
            }
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a("IndexParser", "UpdateCycle_start : ", str, " UpdateCycle_end : ", str2);
            return;
        }
        hashMap.put("upgrade_start", str);
        hashMap.put("upgrade_end", str2);
        cvjVar.f(hashMap);
    }

    private static void b(cvj cvjVar, JSONObject jSONObject) throws JSONException {
        if (cvjVar == null || jSONObject == null) {
            LogUtil.a("IndexParser", "parseDescriptionAllType param is null");
            return;
        }
        LogUtil.c("IndexParser", "parseDescriptionAllType wearObject:", jSONObject.toString());
        if (jSONObject.has("hi_device_type")) {
            cvjVar.f(jSONObject.getInt("hi_device_type"));
        } else {
            LogUtil.c("IndexParser", "parseDescriptionAllType not has hi_device_type");
        }
        if (jSONObject.has(DeviceCategoryFragment.DEVICE_TYPE)) {
            cvjVar.b(jSONObject.getInt(DeviceCategoryFragment.DEVICE_TYPE));
        }
        if (jSONObject.has("aw70_period")) {
            cvjVar.c(jSONObject.getInt("aw70_period"));
            LogUtil.c("IndexParser", "parseDescriptionAllType deviceInfo.getAw70Period() = ", Integer.valueOf(cvjVar.a()));
        }
        if (jSONObject.has("package_name")) {
            cvjVar.ae(jSONObject.getString("package_name"));
            LogUtil.c("IndexParser", "parseDescriptionAllType deviceInfo.getPackageName() = ", cvjVar.bf());
        }
        if (jSONObject.has("bluetooth_type")) {
            cvjVar.a(jSONObject.getInt("bluetooth_type"));
        }
        if (jSONObject.has("wear_type")) {
            cvjVar.ar(jSONObject.getString("wear_type"));
        }
        if (jSONObject.has(PluginPayAdapter.DEVICE_CATEGORY)) {
            cvjVar.e(jSONObject.getInt(PluginPayAdapter.DEVICE_CATEGORY));
        }
        if (jSONObject.has("device_shapes")) {
            cvjVar.d(jSONObject.getInt("device_shapes"));
        }
        if (jSONObject.has("is_porsche")) {
            cvjVar.a(jSONObject.getBoolean("is_porsche"));
        }
        if (jSONObject.has("support_ear_muff_guide")) {
            cvjVar.e(jSONObject.getBoolean("support_ear_muff_guide"));
        }
        if (jSONObject.has("device_relationship")) {
            cvjVar.al(jSONObject.getString("device_relationship"));
        }
    }

    private static void d(cvj cvjVar, JSONObject jSONObject) throws JSONException {
        if (cvjVar == null || jSONObject == null) {
            LogUtil.a("IndexParser", "parseDescriptionSupportOrNot param is null");
            return;
        }
        if (jSONObject.has("br_and_ble")) {
            cvjVar.d(jSONObject.getBoolean("br_and_ble"));
        }
        if (jSONObject.has("support_midware")) {
            cvjVar.h(jSONObject.getBoolean("support_midware"));
        }
        if (jSONObject.has("wlan_anto_download")) {
            cvjVar.h(jSONObject.getInt("wlan_anto_download"));
        }
        if (jSONObject.has("heart_rate_function")) {
            cvjVar.g(jSONObject.getInt("heart_rate_function"));
        }
        if (jSONObject.has("pair_model_bt_name")) {
            cvjVar.an(jSONObject.getString("pair_model_bt_name"));
        }
    }

    private static void e(cvj cvjVar, JSONObject jSONObject) throws JSONException {
        if (cvjVar == null || jSONObject == null) {
            LogUtil.a("IndexParser", "parseDescriptionFunctionalField param is null");
            return;
        }
        if (jSONObject.has("nps_name")) {
            cvjVar.ah(jSONObject.getString("nps_name"));
        }
        if (jSONObject.has("club_url")) {
            cvjVar.f(jSONObject.getString("club_url"));
        }
        if (jSONObject.has("earphone_manager_uri")) {
            cvjVar.v(jSONObject.getString("earphone_manager_uri"));
        }
        if (jSONObject.has("honor_club_url")) {
            cvjVar.ac(jSONObject.getString("honor_club_url"));
        }
        if (jSONObject.has("bi_name")) {
            cvjVar.e(jSONObject.getString("bi_name"));
        }
        if (jSONObject.has("track_logo_image")) {
            cvjVar.am(jSONObject.getString("track_logo_image"));
        }
    }
}
