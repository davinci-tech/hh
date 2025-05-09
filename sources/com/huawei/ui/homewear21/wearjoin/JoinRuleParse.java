package com.huawei.ui.homewear21.wearjoin;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.datatypes.HealthSupportModel;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.device.interactors.CompatibilityInteractor;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JoinRuleParse {
    private static final String JOIN_FILE_NAME = "healthconfig.json";
    private static final String KEY_FORCE_MIGRATE = "isForceMigrate";
    private static final String KEY_LEO_MINI_VERSION = "watch2MiniVersion";
    private static final String KEY_NEED_MIGRATE = "isNeedMigrate";
    private static final String KEY_PROSCHE_MINI_VERSION = "proscheDesignMiniVersion";
    private static final String KEY_SUPPORT_A2 = "isSupportA2";
    private static final String KEY_SUPPORT_B3 = "isSupportB3";
    private static final String KEY_SUPPORT_ERIS = "isSupportEris";
    private static final String KEY_SUPPORT_GRUS = "isSupportB3Lite";
    private static final String KEY_SUPPORT_LEO = "isSupportWatch2";
    private static final String KEY_SUPPORT_METIS = "isSupportMetis";
    private static final String KEY_SUPPORT_NYX = "isSupportNyx";
    private static final String KEY_SUPPORT_PROSCHE = "isSupportProscheDesign";
    private static final String KEY_SUPPORT_R1 = "isSupportR1";
    private static final String TAG = "UIDV_JoinRuleParse";

    private JoinRuleParse() {
    }

    public static int parseResult(Context context) {
        LogUtil.a(TAG, "parseResult");
        int i = -1;
        if (context == null) {
            return -1;
        }
        try {
            String str = BaseApplication.getContext().getFilesDir().getCanonicalPath() + File.separator + "lightcloud" + File.separator + "healthconfig" + File.separator + JOIN_FILE_NAME;
            LogUtil.a(TAG, "parseResult filePath:" + str);
            String stringFile = getStringFile(str);
            LogUtil.a(TAG, "parseResult resp: " + stringFile);
            if (!TextUtils.isEmpty(stringFile)) {
                i = saveParse(context, stringFile);
            } else {
                LogUtil.a(TAG, "resp is null ");
            }
            LogUtil.a(TAG, "AIRuleParse finish ");
        } catch (IOException e) {
            LogUtil.a(TAG, "parse result error,", e.getMessage());
        }
        return i;
    }

    private static String getStringFile(String str) {
        String str2;
        FileInputStream fileInputStream;
        str2 = "";
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(str);
            } catch (IOException e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
            fileInputStream = fileInputStream2;
        }
        try {
            byte[] bArr = new byte[fileInputStream.available()];
            str2 = fileInputStream.read(bArr) > 0 ? new String(bArr, "UTF-8") : "";
            try {
                fileInputStream.close();
            } catch (IOException e2) {
                LogUtil.b(TAG, "inputStream.close IOException", e2.getMessage());
            }
        } catch (IOException e3) {
            e = e3;
            fileInputStream2 = fileInputStream;
            LogUtil.b(TAG, "getStringFile IOException :", e.getMessage());
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (IOException e4) {
                    LogUtil.b(TAG, "inputStream.close IOException", e4.getMessage());
                }
            }
            return str2;
        } catch (Throwable th2) {
            th = th2;
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e5) {
                    LogUtil.b(TAG, "inputStream.close IOException", e5.getMessage());
                }
            }
            throw th;
        }
        return str2;
    }

    public static int saveParse(Context context, String str) {
        LogUtil.a(TAG, "saveParse resp:" + str);
        int i = -1;
        if (!TextUtils.isEmpty(str) && context != null) {
            Gson gson = new Gson();
            JoinInfo joinInfo = new JoinInfo();
            HealthSupportModel healthSupportModel = new HealthSupportModel();
            try {
                setHealthDataModel(new JSONObject(str), joinInfo, healthSupportModel);
                i = 0;
            } catch (JSONException e) {
                LogUtil.b(TAG, "JSONException: ", e.getMessage());
            }
            LogUtil.a(TAG, "saveParse gson:" + gson.toJson(joinInfo));
            SharedPreferenceManager.e(context, String.valueOf(10000), "wear_join_notice_to_migrate", joinInfo.isNeedMigrate() + "", new StorageParams());
            new CompatibilityInteractor().b(100, healthSupportModel);
        }
        return i;
    }

    private static void setHealthDataModel(JSONObject jSONObject, JoinInfo joinInfo, HealthSupportModel healthSupportModel) throws JSONException {
        if (!jSONObject.isNull(KEY_NEED_MIGRATE)) {
            joinInfo.setNeedMigrate(jSONObject.getBoolean(KEY_NEED_MIGRATE));
        }
        if (!jSONObject.isNull(KEY_FORCE_MIGRATE)) {
            joinInfo.setForceMigrate(jSONObject.getBoolean(KEY_FORCE_MIGRATE));
        }
        if (!jSONObject.isNull(KEY_SUPPORT_LEO)) {
            healthSupportModel.setSupportLeo(jSONObject.getBoolean(KEY_SUPPORT_LEO));
        }
        if (!jSONObject.isNull(KEY_LEO_MINI_VERSION)) {
            healthSupportModel.setWatch2MiniVersion(jSONObject.getString(KEY_LEO_MINI_VERSION));
        }
        if (!jSONObject.isNull(KEY_SUPPORT_PROSCHE)) {
            healthSupportModel.setSupportPro(jSONObject.getBoolean(KEY_SUPPORT_PROSCHE));
        }
        if (!jSONObject.isNull(KEY_PROSCHE_MINI_VERSION)) {
            healthSupportModel.setProscheDesignMiniVersion(jSONObject.getString(KEY_PROSCHE_MINI_VERSION));
        }
        if (!jSONObject.isNull(KEY_SUPPORT_METIS)) {
            healthSupportModel.setSupportMetis(jSONObject.getBoolean(KEY_SUPPORT_METIS));
        }
        if (!jSONObject.isNull(KEY_SUPPORT_GRUS)) {
            healthSupportModel.setSupportB3Lite(jSONObject.getBoolean(KEY_SUPPORT_GRUS));
        }
        if (!jSONObject.isNull(KEY_SUPPORT_ERIS)) {
            healthSupportModel.setSupportEris(jSONObject.getBoolean(KEY_SUPPORT_ERIS));
        }
        if (!jSONObject.isNull(KEY_SUPPORT_NYX)) {
            healthSupportModel.setSupportNyx(jSONObject.getBoolean(KEY_SUPPORT_NYX));
        }
        if (!jSONObject.isNull(KEY_SUPPORT_A2)) {
            healthSupportModel.setSupportA2(jSONObject.getBoolean(KEY_SUPPORT_A2));
        }
        if (!jSONObject.isNull(KEY_SUPPORT_B3)) {
            healthSupportModel.setSupportB3(jSONObject.getBoolean(KEY_SUPPORT_B3));
        }
        if (jSONObject.isNull(KEY_SUPPORT_R1)) {
            return;
        }
        healthSupportModel.setSupportR1(jSONObject.getBoolean(KEY_SUPPORT_R1));
    }
}
