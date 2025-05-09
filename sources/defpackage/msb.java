package defpackage;

import android.text.TextUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hms.network.embedded.x2;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.openalliance.ad.constant.AdShowExtras;
import com.huawei.pluginmgr.hwwear.bean.DeviceDownloadSourceInfo;
import health.compact.a.CommonUtil;
import health.compact.a.EzPluginManager;
import health.compact.a.LogUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class msb {
    public static String a(File file) {
        if (file == null) {
            LogUtil.a("EzPlugin_EzPluginIndexParser", "readFileToData, file == null");
            return null;
        }
        LogUtil.c("EzPlugin_EzPluginIndexParser", "enter readFileToData: fileName = ", file.getName());
        if (!file.exists()) {
            LogUtil.a("EzPlugin_EzPluginIndexParser", "readFileToData, file not exist");
            return null;
        }
        try {
            return FileUtils.b(file, 5242880L);
        } catch (IOException e) {
            LogUtil.a("EzPlugin_EzPluginIndexParser", "readFileToData, ex=", LogUtil.a(e));
            return "";
        }
    }

    public static msi c(String str) {
        LogUtil.c("EzPlugin_EzPluginIndexParser", "parseIndexFile enter parseIndexFile:");
        msi msiVar = new msi();
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("EzPlugin_EzPluginIndexParser", "parseIndexFile indexJson is empty or null");
            return msiVar;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.isNull("plugins")) {
                msiVar.c(jSONObject.optString("version"));
                msiVar.a(jSONObject.optString("updatedTime"));
                msiVar.e(e(jSONObject.getJSONArray("plugins")));
            }
        } catch (JSONException unused) {
            LogUtil.e("EzPlugin_EzPluginIndexParser", "parseIndexFile JSONException");
        }
        return msiVar;
    }

    private static List<msa> e(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            LogUtil.a("EzPlugin_EzPluginIndexParser", "parsePlugins jsonArrayPlugins is null or no data");
            return new ArrayList(0);
        }
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject != null) {
                    d(jSONObject, arrayList);
                } else {
                    LogUtil.a("EzPlugin_EzPluginIndexParser", "parsePlugins jsonObject is null.");
                }
            } catch (JSONException unused) {
                LogUtil.e("EzPlugin_EzPluginIndexParser", "parsePlugins JSONException");
            }
        }
        return arrayList;
    }

    private static void d(JSONObject jSONObject, List<msa> list) throws JSONException {
        msa msaVar = new msa();
        c(jSONObject, msaVar);
        if (jSONObject.has("applyRules")) {
            c(msaVar, jSONObject);
        }
        if (jSONObject.has("wearkind") && !jSONObject.isNull("wearkind")) {
            msaVar.g(jSONObject.getString("wearkind"));
        }
        if (jSONObject.has("publish_mode") && !jSONObject.isNull("publish_mode")) {
            msaVar.h(jSONObject.getString("publish_mode"));
        }
        if (jSONObject.has(x2.PROTOCOL) && !jSONObject.isNull(x2.PROTOCOL)) {
            msaVar.j(jSONObject.getString(x2.PROTOCOL));
        }
        if (jSONObject.has("support_mode") && !jSONObject.isNull("support_mode")) {
            msaVar.f(jSONObject.getString("support_mode"));
        }
        if (jSONObject.has("publish_version") && !jSONObject.isNull("publish_version")) {
            b(msaVar, jSONObject);
        }
        if (jSONObject.has(AdShowExtras.DOWNLOAD_SOURCE) && !jSONObject.isNull(AdShowExtras.DOWNLOAD_SOURCE)) {
            e(msaVar, jSONObject.optJSONObject(AdShowExtras.DOWNLOAD_SOURCE));
        }
        list.add(msaVar);
    }

    private static void c(JSONObject jSONObject, msa msaVar) throws JSONException {
        if (!jSONObject.isNull("uuid")) {
            msaVar.e(jSONObject.getString("uuid"));
        }
        if (!jSONObject.isNull("version")) {
            msaVar.b(jSONObject.getString("version"));
        }
        if (!jSONObject.isNull("digest")) {
            msaVar.c(jSONObject.getString("digest"));
        }
        if (!jSONObject.isNull("filename")) {
            msaVar.a(jSONObject.getString("filename"));
        }
        if (!jSONObject.isNull("fileSize")) {
            msaVar.e(CommonUtil.e(jSONObject.getString("fileSize"), 0));
        }
        if (!jSONObject.isNull("fileCount")) {
            msaVar.b(jSONObject.getLong("fileCount"));
        }
        if (jSONObject.isNull("descUrl")) {
            return;
        }
        msaVar.d(jSONObject.getString("descUrl"));
    }

    private static void c(msa msaVar, JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2;
        if (jSONObject.isNull("applyRules") || (jSONObject2 = jSONObject.getJSONObject("applyRules")) == null) {
            return;
        }
        msk mskVar = new msk();
        if (jSONObject2.has("minAppVersion")) {
            mskVar.d(jSONObject2.getString("minAppVersion"));
        }
        if (jSONObject2.has("minIndexVersion")) {
            mskVar.b(jSONObject2.getString("minIndexVersion"));
        }
        msaVar.d(mskVar);
    }

    public static msc d(String str) {
        msc mscVar = new msc();
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("EzPlugin_EzPluginIndexParser", "parseDescJsonFile descriptionJson is empty or null");
            return mscVar;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            mscVar.b(jSONObject.optString("form"));
            mscVar.h(jSONObject.optString("updatedTime"));
            mscVar.g(jSONObject.optString("uuid"));
            mscVar.j(jSONObject.optString("version"));
            b(mscVar, jSONObject);
            c(mscVar, jSONObject);
            e(mscVar, jSONObject);
            mscVar.e(CommonUtil.e(jSONObject.optString("fileSize"), 0));
            mscVar.e(jSONObject.optString("filename"));
            mscVar.d(jSONObject.optString("fileType"));
            mscVar.c(jSONObject.optString("digest"));
            a(mscVar, jSONObject);
        } catch (JSONException unused) {
            LogUtil.e("EzPlugin_EzPluginIndexParser", "parseDescJsonFile JSONException");
        }
        return mscVar;
    }

    private static void b(msc mscVar, JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray("name");
        if (optJSONArray == null) {
            return;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            try {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                if (!jSONObject2.isNull("en_us")) {
                    String string = jSONObject2.getString("en_us");
                    mscVar.i(jSONObject2.getString("en_us"));
                    LogUtil.d("EzPlugin_EzPluginIndexParser", "parsePluginName name_enUs : ", string);
                }
                if (!jSONObject2.isNull("zh_rCN")) {
                    LogUtil.d("EzPlugin_EzPluginIndexParser", "parsePluginName name_zh_rCN : ", jSONObject2.getString("zh_rCN"));
                }
            } catch (JSONException unused) {
                LogUtil.e("EzPlugin_EzPluginIndexParser", "parsePluginName JSONException");
            }
        }
    }

    private static void e(msc mscVar, JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray("description");
        if (optJSONArray == null) {
            return;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            try {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                if (!jSONObject2.isNull("en_us")) {
                    String string = jSONObject2.getString("en_us");
                    mscVar.a(string);
                    LogUtil.d("EzPlugin_EzPluginIndexParser", "parsePluginAuthorization description_enUs : ", string);
                }
                if (!jSONObject2.isNull("zh_rCN")) {
                    LogUtil.d("EzPlugin_EzPluginIndexParser", "parsePluginAuthorization description_zh_rCN : ", jSONObject2.getString("zh_rCN"));
                }
            } catch (JSONException unused) {
                LogUtil.e("EzPlugin_EzPluginIndexParser", "parsePluginAuthorization JSONException");
            }
        }
    }

    private static void c(msc mscVar, JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("authorization");
        if (optJSONObject == null) {
            return;
        }
        JSONArray optJSONArray = optJSONObject.optJSONArray("permissions");
        if (optJSONArray != null) {
            LogUtil.d("EzPlugin_EzPluginIndexParser", "parsePluginDescription jsonArrayPermissions = ", optJSONArray);
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
                    mscVar.f(string);
                    LogUtil.d("EzPlugin_EzPluginIndexParser", "parsePluginDescription authorization_enUs : ", string);
                }
                if (!jSONObject2.isNull("zh_rCN")) {
                    LogUtil.d("EzPlugin_EzPluginIndexParser", "parsePluginDescription authorization_zh_rCN : ", jSONObject2.getString("zh_rCN"));
                }
            } catch (JSONException unused) {
                LogUtil.e("EzPlugin_EzPluginIndexParser", "parsePluginDescription JSONException");
            }
        }
    }

    private static void a(msc mscVar, JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("wear_device_info")) {
            mrt mrtVar = new mrt();
            JSONObject jSONObject2 = jSONObject.getJSONObject("wear_device_info");
            b(mscVar, mrtVar, jSONObject2);
            b(mrtVar, jSONObject2);
            a(mrtVar, jSONObject2);
            i(mrtVar, jSONObject2);
            h(mrtVar, jSONObject2);
            c(mrtVar, jSONObject2);
            d(mrtVar, jSONObject2);
            e(mrtVar, jSONObject2);
            k(mrtVar, jSONObject2);
            a(mscVar, mrtVar, jSONObject2);
            c(mscVar, mrtVar, jSONObject2);
            mscVar.c(mrtVar);
            LogUtil.c("EzPlugin_EzPluginIndexParser", "parseWearDeviceInfo deviceInfo :", mrtVar.toString());
        }
    }

    private static void c(msc mscVar, mrt mrtVar, JSONObject jSONObject) throws JSONException {
        LogUtil.c("EzPlugin_EzPluginIndexParser", "parseWearDevicePairGuide enter");
        if (mscVar == null || mrtVar == null || jSONObject == null) {
            LogUtil.a("EzPlugin_EzPluginIndexParser", "parseWearDevicePairGuide param is null.");
            return;
        }
        if (!jSONObject.has("pair_guide_steps")) {
            LogUtil.a("EzPlugin_EzPluginIndexParser", "WEAR_DEVICE_PAIR_GUIDE_STEPS has no this key.");
            return;
        }
        JSONObject jSONObject2 = jSONObject.getJSONObject("pair_guide_steps");
        if (jSONObject2.has("pair_guide_steps_description")) {
            JSONArray optJSONArray = jSONObject2.optJSONArray("pair_guide_steps_description");
            LogUtil.c("EzPlugin_EzPluginIndexParser", "pairGuideDescArray param has WEAR_DEVICE_PAIR_GUIDE_STEPS_DESC_KEY.");
            if (optJSONArray == null) {
                LogUtil.a("EzPlugin_EzPluginIndexParser", "pairGuideDescArray param is null.");
                return;
            } else {
                List<String> d = d(mscVar, optJSONArray);
                if (d instanceof ArrayList) {
                    mrtVar.k((ArrayList<String>) d);
                }
            }
        }
        if (jSONObject2.has("pair_guide_steps_img")) {
            JSONArray optJSONArray2 = jSONObject2.optJSONArray("pair_guide_steps_img");
            if (optJSONArray2 == null) {
                LogUtil.c("EzPlugin_EzPluginIndexParser", "pairGuideImgArray param is null.");
                return;
            }
            ArrayList<String> arrayList = new ArrayList<>(optJSONArray2.length());
            for (int i = 0; i < optJSONArray2.length(); i++) {
                arrayList.add(optJSONArray2.optString(i));
            }
            LogUtil.c("EzPlugin_EzPluginIndexParser", "guideImgList param is :", arrayList.toString());
            mrtVar.o(arrayList);
        }
    }

    private static List<String> d(msc mscVar, JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            List<String> a2 = EzPluginManager.a(jSONArray.optString(i), mscVar);
            if (a2 != null && !a2.isEmpty()) {
                arrayList.add(a2.get(0));
            }
        }
        LogUtil.c("EzPlugin_EzPluginIndexParser", "guideDescList param is :", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    private static void k(mrt mrtVar, JSONObject jSONObject) throws JSONException {
        LogUtil.c("EzPlugin_EzPluginIndexParser", "parseWearDeviceProductInfo enter");
        if (mrtVar != null && jSONObject != null) {
            LogUtil.d("EzPlugin_EzPluginIndexParser", "parseWearDeviceProductInfo wearObject:", jSONObject.toString());
            if (jSONObject.has("product")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("product");
                LogUtil.c("EzPlugin_EzPluginIndexParser", "parseWearDeviceProductInfo productObject:", jSONObject2.toString());
                mrtVar.d(jSONObject2.toString());
                if (jSONObject2.has("product_default")) {
                    JSONObject jSONObject3 = jSONObject2.getJSONObject("product_default");
                    if (jSONObject3.has("entry_image_sku")) {
                        e(mrtVar, jSONObject3, "entry_image_sku");
                    }
                    if (jSONObject3.has("home_background_image_sku")) {
                        e(mrtVar, jSONObject3, "home_background_image_sku");
                    }
                    if (jSONObject3.has("ota_background_image_sku")) {
                        e(mrtVar, jSONObject3, "ota_background_image_sku");
                    }
                    if (jSONObject3.has("disconnect_background_image_sku")) {
                        e(mrtVar, jSONObject3, "disconnect_background_image_sku");
                    }
                    if (jSONObject3.has("ear_muff_install")) {
                        e(mrtVar, jSONObject3, "ear_muff_install");
                    }
                    if (jSONObject3.has("ear_muff_removal")) {
                        LogUtil.c("EzPlugin_EzPluginIndexParser", "defaultObject.has(EzPluginConstants.ERA_MUFF_REMOVAL)");
                        e(mrtVar, jSONObject3, "ear_muff_removal");
                        return;
                    }
                    return;
                }
                return;
            }
            LogUtil.c("EzPlugin_EzPluginIndexParser", "parseWearDeviceProductInfo wearObject no product key");
            return;
        }
        LogUtil.a("EzPlugin_EzPluginIndexParser", "parseWearDeviceInfoModelImage param is null.");
    }

    private static void a(msc mscVar, mrt mrtVar, JSONObject jSONObject) throws JSONException {
        LogUtil.c("EzPlugin_EzPluginIndexParser", "parseWearDeviceUserGuide enter");
        if (mscVar == null || mrtVar == null || jSONObject == null) {
            LogUtil.c("EzPlugin_EzPluginIndexParser", "parseWearDeviceUserGuide param is null.");
            return;
        }
        if (jSONObject.has("user_guide")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("user_guide");
            LogUtil.c("EzPlugin_EzPluginIndexParser", "userGuideLineObject.has(EzPluginConstants.WEAR_DEVICE_USER_GUIDE_LINE_KEY)");
            if (jSONObject2.has("guide_data")) {
                c(mscVar, mrtVar, jSONObject2.optJSONArray("guide_data"));
            }
        }
    }

    private static void c(msc mscVar, mrt mrtVar, JSONArray jSONArray) throws JSONException {
        JSONArray optJSONArray;
        LogUtil.c("EzPlugin_EzPluginIndexParser", "enter parseUserGuideData");
        if (jSONArray == null) {
            LogUtil.c("EzPlugin_EzPluginIndexParser", "parseUserGuideData param is null.");
            return;
        }
        ArrayList<msy> arrayList = new ArrayList<>();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            msy msyVar = new msy();
            d(msyVar, mscVar, optJSONObject);
            if (optJSONObject.has("guide_image") && (optJSONArray = optJSONObject.optJSONArray("guide_image")) != null) {
                LogUtil.c("EzPlugin_EzPluginIndexParser", "guide data has image");
                ArrayList<String> arrayList2 = new ArrayList<>(optJSONArray.length());
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    arrayList2.add(optJSONArray.optString(i2));
                }
                msyVar.a(arrayList2);
            }
            arrayList.add(msyVar);
        }
        mrtVar.g(arrayList);
    }

    private static void d(msy msyVar, msc mscVar, JSONObject jSONObject) throws JSONException {
        LogUtil.c("EzPlugin_EzPluginIndexParser", "enter parseUserGuidLineString");
        if (mscVar == null || jSONObject == null) {
            LogUtil.a("EzPlugin_EzPluginIndexParser", "parseUserGuidLineString param is null");
            return;
        }
        if (jSONObject.has("guide_title")) {
            List<String> a2 = EzPluginManager.a(jSONObject.optString("guide_title"), mscVar);
            if (a2.size() > 0) {
                msyVar.c(a2.get(0));
            }
        }
        if (jSONObject.has("guide_description")) {
            List<String> a3 = EzPluginManager.a(jSONObject.optString("guide_description"), mscVar);
            if (a3.size() > 0) {
                msyVar.d(a3.get(0));
            }
        }
    }

    private static void e(mrt mrtVar, JSONObject jSONObject, String str) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONArray(str);
        ArrayList<String> arrayList = new ArrayList<>(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.optString(i));
        }
        if (str.equals("entry_image_sku")) {
            mrtVar.h(arrayList);
            return;
        }
        if (str.equals("home_background_image_sku")) {
            mrtVar.d(arrayList);
            return;
        }
        if (str.equals("ota_background_image_sku")) {
            mrtVar.m(arrayList);
            return;
        }
        if (str.equals("disconnect_background_image_sku")) {
            mrtVar.a(arrayList);
            return;
        }
        if (str.equals("ear_muff_install")) {
            mrtVar.f(arrayList);
        } else if (str.equals("ear_muff_removal")) {
            mrtVar.i(arrayList);
        } else {
            LogUtil.a("EzPlugin_EzPluginIndexParser", "parseWearDeviceImageList else branch.");
        }
    }

    private static void b(msc mscVar, mrt mrtVar, JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("entry_img")) {
            mrtVar.y(jSONObject.getString("entry_img"));
        }
        if (jSONObject.has("buy_url")) {
            mrtVar.f(jSONObject.getString("buy_url"));
        }
        if (jSONObject.has("des_img")) {
            m(mrtVar, jSONObject);
        }
        if (jSONObject.has("guide")) {
            l(mrtVar, jSONObject);
        }
        if (jSONObject.has("home_img_new")) {
            mrtVar.x(jSONObject.getString("home_img_new"));
        }
        if (jSONObject.has("home_background_img")) {
            mrtVar.x(jSONObject.getString("home_background_img"));
        }
        if (jSONObject.has("home_background_power_img")) {
            mrtVar.u(jSONObject.getString("home_background_power_img"));
        }
        if (jSONObject.has("update_img")) {
            mrtVar.ai(jSONObject.getString("update_img"));
        }
        if (jSONObject.has("device_brand")) {
            mrtVar.a(jSONObject.getString("device_brand"));
        }
        if (jSONObject.has("bluetooth_name")) {
            g(mrtVar, jSONObject);
        }
        if (jSONObject.has("device_string")) {
            a(mrtVar, mscVar, jSONObject.getJSONArray("device_string"));
        } else {
            mrtVar.a(false);
        }
        if (jSONObject.has("esim_img")) {
            f(mrtVar, jSONObject);
        }
        if (jSONObject.has("esim_operator_url")) {
            j(mrtVar, jSONObject);
        }
    }

    private static void m(mrt mrtVar, JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONArray("des_img");
        ArrayList<String> arrayList = new ArrayList<>(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.optString(i));
        }
        mrtVar.c(arrayList);
    }

    private static void l(mrt mrtVar, JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("guide");
        if (jSONObject2.has("guide_img")) {
            mrtVar.w(jSONObject2.getString("guide_img"));
        }
        if (jSONObject2.has("ani_img")) {
            JSONArray jSONArray = jSONObject2.getJSONArray("ani_img");
            ArrayList<String> arrayList = new ArrayList<>(jSONArray.length());
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(jSONArray.optString(i));
            }
            mrtVar.j(arrayList);
        }
        if (jSONObject2.has("background_img")) {
            mrtVar.t(jSONObject2.getString("background_img"));
        }
    }

    private static void f(mrt mrtVar, JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("esim_img");
        if (jSONObject2.has("cmcc_install_guide_img")) {
            mrtVar.i(jSONObject2.getString("cmcc_install_guide_img"));
        }
        if (jSONObject2.has("cmcc_open_guide_img")) {
            mrtVar.g(jSONObject2.getString("cmcc_open_guide_img"));
        }
        if (jSONObject2.has("cucc_install_guide_img")) {
            mrtVar.o(jSONObject2.getString("cucc_install_guide_img"));
        }
        if (jSONObject2.has("cucc_open_guide_img")) {
            mrtVar.p(jSONObject2.getString("cucc_open_guide_img"));
        }
        if (jSONObject2.has("ctcc_install_guide_img")) {
            mrtVar.n(jSONObject2.getString("ctcc_install_guide_img"));
        }
        if (jSONObject2.has("ctcc_open_guide_img")) {
            mrtVar.l(jSONObject2.getString("ctcc_open_guide_img"));
        }
        if (jSONObject2.has("cmcc_install_guide_img_no_text")) {
            mrtVar.j(jSONObject2.getString("cmcc_install_guide_img_no_text"));
        }
        if (jSONObject2.has("cucc_install_guide_img_no_text")) {
            mrtVar.s(jSONObject2.getString("cucc_install_guide_img_no_text"));
        }
        if (jSONObject2.has("ctcc_install_guide_img_no_text")) {
            mrtVar.k(jSONObject2.getString("ctcc_install_guide_img_no_text"));
        }
        if (jSONObject2.has("operator_open_guide_img_no_text")) {
            mrtVar.aa(jSONObject2.getString("operator_open_guide_img_no_text"));
        }
    }

    private static void j(mrt mrtVar, JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("esim_operator_url");
        if (jSONObject2.has("cucc_operator_url")) {
            mrtVar.r(jSONObject2.getString("cucc_operator_url"));
        }
        if (jSONObject2.has("ctcc_operator_url")) {
            mrtVar.m(jSONObject2.getString("ctcc_operator_url"));
        }
    }

    private static void g(mrt mrtVar, JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONArray("bluetooth_name");
        ArrayList<String> arrayList = new ArrayList<>(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getString(i));
        }
        mrtVar.e(arrayList);
    }

    private static void a(mrt mrtVar, msc mscVar, JSONArray jSONArray) throws JSONException {
        if (mrtVar == null || mscVar == null || jSONArray == null) {
            LogUtil.a("EzPlugin_EzPluginIndexParser", "parseDescriptionDeviceString param is null");
            return;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            String string = jSONArray.getString(i);
            if (string != null) {
                e(mrtVar, mscVar, string);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static void e(mrt mrtVar, msc mscVar, String str) throws JSONException {
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
            d(mrtVar, mscVar);
            return;
        }
        if (c == 1) {
            List<String> a2 = EzPluginManager.a("track_device_name", mscVar);
            if (a2.size() > 0) {
                mrtVar.ag(a2.get(0));
                return;
            }
            return;
        }
        if (c == 2) {
            List<String> a3 = EzPluginManager.a("description", mscVar);
            if (a3.size() > 0) {
                if (a3 instanceof ArrayList) {
                    mrtVar.b((ArrayList<String>) a3);
                    return;
                } else {
                    mrtVar.b(new ArrayList<>(a3));
                    return;
                }
            }
            return;
        }
        if (c == 3) {
            List<String> a4 = EzPluginManager.a("pair_guide", mscVar);
            if (a4.size() > 0) {
                mrtVar.ad(a4.get(0));
                return;
            }
            return;
        }
        if (c == 4) {
            List<String> a5 = EzPluginManager.a("briefDescription", mscVar);
            if (a5.size() > 0) {
                mrtVar.e(a5.get(0));
                return;
            }
            return;
        }
        if (c == 5) {
            List<String> a6 = EzPluginManager.a("deviceName", mscVar);
            if (a6.size() > 0) {
                mrtVar.q(a6.get(0));
                return;
            }
            return;
        }
        c(mrtVar, mscVar, str);
    }

    private static void c(mrt mrtVar, msc mscVar, String str) {
        str.hashCode();
        if (str.equals("awakenDevice")) {
            List<String> a2 = EzPluginManager.a("awakenDevice", mscVar);
            if (a2.size() > 0) {
                mrtVar.b(a2.get(0));
            }
        }
    }

    private static void d(mrt mrtVar, msc mscVar) {
        List<String> a2 = EzPluginManager.a("pairCompleteTips", mscVar);
        if (a2.size() > 0) {
            mrtVar.ac(a2.get(0));
        }
    }

    private static void b(mrt mrtVar, JSONObject jSONObject) {
        if (mrtVar == null || jSONObject == null) {
            LogUtil.a("EzPlugin_EzPluginIndexParser", "parseDescriptionAppVersion param is null");
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
                    LogUtil.d("EzPlugin_EzPluginIndexParser", "appVersionDomestic : ", Integer.valueOf(i));
                }
                if (!jSONObject2.isNull("overseas")) {
                    i2 = jSONObject2.getInt("overseas");
                    LogUtil.d("EzPlugin_EzPluginIndexParser", "appVersionOverseas : ", Integer.valueOf(i2));
                }
            } catch (JSONException unused) {
                LogUtil.e("EzPlugin_EzPluginIndexParser", "parseDescriptionAppVersion JSONException");
            }
        }
        if (i == -1 && i2 == -1) {
            LogUtil.c("EzPlugin_EzPluginIndexParser", "AppVersion parameters not configured.");
            return;
        }
        if (i != -1) {
            hashMap.put("domestic", Integer.valueOf(i));
        }
        if (i2 != -1) {
            hashMap.put("overseas", Integer.valueOf(i2));
        }
        mrtVar.b(hashMap);
    }

    private static void b(msa msaVar, JSONObject jSONObject) {
        if (msaVar == null || jSONObject == null) {
            LogUtil.a("EzPlugin_EzPluginIndexParser", "parsePublishVersion param is null");
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
                    LogUtil.d("EzPlugin_EzPluginIndexParser", "publishVersionDomestic : ", str);
                }
                if (!jSONObject2.isNull("overseas")) {
                    str2 = jSONObject2.getString("overseas");
                    LogUtil.d("EzPlugin_EzPluginIndexParser", "publishVersionOverseas : ", str2);
                }
            } catch (JSONException unused) {
                LogUtil.e("EzPlugin_EzPluginIndexParser", "parsePublishVersion JSONException");
            }
        }
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            LogUtil.a("EzPlugin_EzPluginIndexParser", "publicVersion parameters not configured.");
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            hashMap.put("domestic", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("overseas", str2);
        }
        msaVar.e(hashMap);
    }

    private static void e(msa msaVar, JSONObject jSONObject) {
        if (msaVar == null || jSONObject == null) {
            LogUtil.a("EzPlugin_EzPluginIndexParser", "parseDownloadSource param is null");
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
        msaVar.d(deviceDownloadSourceInfo);
    }

    private static void i(mrt mrtVar, JSONObject jSONObject) {
        if (mrtVar == null || jSONObject == null) {
            LogUtil.a("EzPlugin_EzPluginIndexParser", "parseDescriptionHelpUrl param is null");
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
                    LogUtil.d("EzPlugin_EzPluginIndexParser", "honorHelpUrl_enUs : ", str);
                }
                if (!jSONObject2.isNull("zh_rCN")) {
                    str2 = jSONObject2.getString("zh_rCN");
                    LogUtil.d("EzPlugin_EzPluginIndexParser", "honorHelpUrl_zh_rCN : ", str2);
                }
            } catch (JSONException unused) {
                LogUtil.e("EzPlugin_EzPluginIndexParser", "parseHonorHelpUrl JSONException");
            }
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a("EzPlugin_EzPluginIndexParser", "honorHelpUrlEnEnglish : ", str, " helpUrlEnChinese : ", str2);
            return;
        }
        hashMap.put("en_us", str);
        hashMap.put("zh_rCN", str2);
        mrtVar.c(hashMap);
    }

    private static void a(mrt mrtVar, JSONObject jSONObject) {
        if (mrtVar == null || jSONObject == null) {
            LogUtil.a("EzPlugin_EzPluginIndexParser", "parseDescriptionHelpUrl param is null");
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
                    LogUtil.d("EzPlugin_EzPluginIndexParser", "helpUrl_enUs : ", str);
                }
                if (!jSONObject2.isNull("zh_rCN")) {
                    str2 = jSONObject2.getString("zh_rCN");
                    LogUtil.d("EzPlugin_EzPluginIndexParser", "helpUrl_zh_rCN : ", str2);
                }
            } catch (JSONException unused) {
                LogUtil.e("EzPlugin_EzPluginIndexParser", "parseDescriptionHelpUrl JSONException");
            }
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a("EzPlugin_EzPluginIndexParser", "helpUrlEnEnglish : ", str, " helpUrlEnChinese : ", str2);
            return;
        }
        hashMap.put("en_us", str);
        hashMap.put("zh_rCN", str2);
        mrtVar.d(hashMap);
    }

    private static void h(mrt mrtVar, JSONObject jSONObject) {
        if (mrtVar == null || jSONObject == null) {
            LogUtil.a("EzPlugin_EzPluginIndexParser", "parseDescriptionUpgradeCycle param is null");
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
                    LogUtil.d("EzPlugin_EzPluginIndexParser", "UpdateCycle_start : ", str);
                }
                if (!jSONObject2.isNull("upgrade_end")) {
                    str2 = jSONObject2.getString("upgrade_end");
                    LogUtil.d("EzPlugin_EzPluginIndexParser", "UpdateCycle_end : ", str2);
                }
            } catch (JSONException unused) {
                LogUtil.e("EzPlugin_EzPluginIndexParser", "parseDescriptionUpgradeCycle JSONException");
            }
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a("EzPlugin_EzPluginIndexParser", "UpdateCycle_start : ", str, " UpdateCycle_end : ", str2);
            return;
        }
        hashMap.put("upgrade_start", str);
        hashMap.put("upgrade_end", str2);
        mrtVar.a(hashMap);
    }

    private static void c(mrt mrtVar, JSONObject jSONObject) throws JSONException {
        if (mrtVar == null || jSONObject == null) {
            LogUtil.a("EzPlugin_EzPluginIndexParser", "parseDescriptionAllType param is null");
            return;
        }
        LogUtil.c("EzPlugin_EzPluginIndexParser", "parseDescriptionAllType wearObject:", jSONObject.toString());
        if (jSONObject.has("hi_device_type")) {
            mrtVar.i(jSONObject.getInt("hi_device_type"));
        } else {
            LogUtil.c("EzPlugin_EzPluginIndexParser", "parseDescriptionAllType not has hi_device_type");
        }
        if (jSONObject.has(DeviceCategoryFragment.DEVICE_TYPE)) {
            mrtVar.c(jSONObject.getInt(DeviceCategoryFragment.DEVICE_TYPE));
        }
        if (jSONObject.has("aw70_period")) {
            mrtVar.e(jSONObject.getInt("aw70_period"));
            LogUtil.c("EzPlugin_EzPluginIndexParser", "parseDescriptionAllType deviceInfo.getAw70Period() = ", Integer.valueOf(mrtVar.d()));
        }
        if (jSONObject.has("package_name")) {
            mrtVar.ab(jSONObject.getString("package_name"));
            LogUtil.c("EzPlugin_EzPluginIndexParser", "parseDescriptionAllType deviceInfo.getPackageName() = ", mrtVar.f());
        }
        if (jSONObject.has("bluetooth_type")) {
            mrtVar.d(jSONObject.getInt("bluetooth_type"));
        }
        if (jSONObject.has("wear_type")) {
            mrtVar.am(jSONObject.getString("wear_type"));
        }
        if (jSONObject.has(PluginPayAdapter.DEVICE_CATEGORY)) {
            mrtVar.a(jSONObject.getInt(PluginPayAdapter.DEVICE_CATEGORY));
        }
        if (jSONObject.has("device_shapes")) {
            mrtVar.b(jSONObject.getInt("device_shapes"));
        }
        if (jSONObject.has("is_porsche")) {
            mrtVar.c(jSONObject.getBoolean("is_porsche"));
        }
        if (jSONObject.has("support_ear_muff_guide")) {
            mrtVar.e(jSONObject.getBoolean("support_ear_muff_guide"));
        }
        if (jSONObject.has("device_relationship")) {
            mrtVar.af(jSONObject.getString("device_relationship"));
        }
    }

    private static void d(mrt mrtVar, JSONObject jSONObject) throws JSONException {
        if (mrtVar == null || jSONObject == null) {
            LogUtil.a("EzPlugin_EzPluginIndexParser", "parseDescriptionSupportOrNot param is null");
            return;
        }
        if (jSONObject.has("br_and_ble")) {
            mrtVar.d(jSONObject.getBoolean("br_and_ble"));
        }
        if (jSONObject.has("support_midware")) {
            mrtVar.b(jSONObject.getBoolean("support_midware"));
        }
        if (jSONObject.has("wlan_anto_download")) {
            mrtVar.j(jSONObject.getInt("wlan_anto_download"));
        }
        if (jSONObject.has("heart_rate_function")) {
            mrtVar.f(jSONObject.getInt("heart_rate_function"));
        }
        if (jSONObject.has("pair_model_bt_name")) {
            mrtVar.ae(jSONObject.getString("pair_model_bt_name"));
        }
    }

    private static void e(mrt mrtVar, JSONObject jSONObject) throws JSONException {
        if (mrtVar == null || jSONObject == null) {
            LogUtil.a("EzPlugin_EzPluginIndexParser", "parseDescriptionFunctionalField param is null");
            return;
        }
        if (jSONObject.has("nps_name")) {
            mrtVar.z(jSONObject.getString("nps_name"));
        }
        if (jSONObject.has("club_url")) {
            mrtVar.h(jSONObject.getString("club_url"));
        }
        if (jSONObject.has("honor_club_url")) {
            mrtVar.v(jSONObject.getString("honor_club_url"));
        }
        if (jSONObject.has("bi_name")) {
            mrtVar.c(jSONObject.getString("bi_name"));
        }
        if (jSONObject.has("track_logo_image")) {
            mrtVar.ah(jSONObject.getString("track_logo_image"));
        }
    }
}
