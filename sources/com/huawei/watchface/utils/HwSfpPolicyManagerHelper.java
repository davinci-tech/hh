package com.huawei.watchface.utils;

import android.app.Application;
import android.text.TextUtils;
import com.huawei.fileprotect.HwSfpPolicyManager;
import com.huawei.watchface.dz;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.flavor.FlavorConfig;
import com.huawei.watchface.mvp.model.crypt.PversionSdUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class HwSfpPolicyManagerHelper {
    private static final int FLAG_FILE_PROTECTION_COMPLETE = 0;
    private static final String LABEL_VALUE_S2 = "S2";
    private static final String TAG = "HwSfpPolicyManagerHelper";
    private static final String[] DEFAULT_PATH_ARR = {"watchface", "watchfacePhoto", "watchfaceVideo", "14", "16"};
    private static final List<String> WHITE_LIST = new ArrayList();

    static {
        addToWhiteList(getDataUserPath());
        addToWhiteList(getDataPathPrefix());
    }

    public static void setDefaultCeLabel(String str) {
        if (wantLabelPath(str)) {
            setLabel(convertPath(str), LABEL_VALUE_S2, 0);
        }
    }

    public static void setDefaultCeLabel(File file) {
        if (file == null) {
            return;
        }
        String path = file.getPath();
        if (wantLabelPath(path)) {
            setLabel(convertPath(path), LABEL_VALUE_S2, 0);
        }
    }

    public static void setFilePathLabel(String str) {
        if (TextUtils.isEmpty(str)) {
            HwLog.d(TAG, "setFilePathLabel filePath is null");
            return;
        }
        File file = PversionSdUtils.getFile(str);
        if (file == null || !FileHelper.d(file)) {
            HwLog.d(TAG, "setFilePathLabel newGeneratedFile is null");
            return;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            HwLog.e(TAG, "checkSkinFiles fail --> files is empty");
            return;
        }
        for (File file2 : listFiles) {
            if (!FileHelper.d(file2)) {
                setDefaultCeLabel(file2);
            }
        }
    }

    public static void initDefaultCeLabelInDir() {
        int b = dz.b("sp_key_is_first_install", 0);
        HwLog.d(TAG, "initDefaultCeLabelInDir isFirstInstall:" + b);
        if (b == 1) {
            return;
        }
        if (!wantLabel()) {
            dz.a("sp_key_is_first_install", 1);
            return;
        }
        HwLog.d(TAG, "initDefaultCeLabelInDir first");
        String fileDataUserPath = getFileDataUserPath();
        for (String str : DEFAULT_PATH_ARR) {
            setDefaultCeLabelInDir(fileDataUserPath + File.separator + str);
        }
        HwLog.d(TAG, "initDefaultCeLabelInDir end");
        dz.a("sp_key_is_first_install", 1);
    }

    public static void setDefaultCeLabelInDir(String str) {
        File file = PversionSdUtils.getFile(str);
        if (FileHelper.d(file)) {
            File[] listFiles = file.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                HwLog.e(TAG, "checkSkinFiles fail --> files is empty");
                return;
            }
            for (File file2 : listFiles) {
                setDefaultCeLabelInDir(file2.getPath());
            }
            return;
        }
        setDefaultCeLabel(str);
    }

    public static void setCustLabel(String str, String str2, int i) {
        if (wantLabelPath(str)) {
            setLabel(convertPath(str), str2, i);
        }
    }

    private static void setLabel(String str, String str2, int i) {
        if (TextUtils.isEmpty(str2)) {
            HwLog.e(TAG, "setLabel, labelValue is empty");
            return;
        }
        if (FileHelper.d(str)) {
            return;
        }
        try {
            Application applicationContext = Environment.getApplicationContext();
            if (applicationContext == null) {
                HwLog.e(TAG, "setLabel, context is null");
                return;
            }
            HwSfpPolicyManager hwSfpPolicyManager = HwSfpPolicyManager.getDefault();
            if (hwSfpPolicyManager == null) {
                HwLog.d(TAG, "setLable, policyManager is null");
                return;
            }
            int label = hwSfpPolicyManager.setLabel(applicationContext, str, "SecurityLevel", str2, i);
            HwLog.d(TAG, "setLabel result:" + label + ",fileName:" + CommonUtils.g(str));
            FlavorConfig.safeHwLog(TAG, "setLabel result:" + label + ",filePath:" + str);
        } catch (Exception e) {
            HwLog.e(TAG, "setLabel, Exception:" + HwLog.printException(e));
        } catch (NoSuchMethodError e2) {
            HwLog.e(TAG, "setLabel, NoSuchMethodError:" + HwLog.printException((Error) e2));
        }
    }

    private static boolean isInWhiteList(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Iterator<String> it = WHITE_LIST.iterator();
        while (it.hasNext()) {
            if (str.contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    public static String getDataUserPath() {
        File filesDir;
        Application applicationContext = Environment.getApplicationContext();
        return (applicationContext == null || (filesDir = applicationContext.getFilesDir()) == null) ? "" : filesDir.getParent();
    }

    public static String getFileDataUserPath() {
        Application applicationContext = Environment.getApplicationContext();
        if (applicationContext == null || applicationContext.getFilesDir() == null) {
            return "";
        }
        return applicationContext.getFilesDir() + "";
    }

    public static String getDataPathPrefix() {
        File cacheDir;
        Application applicationContext = Environment.getApplicationContext();
        return (applicationContext == null || (cacheDir = applicationContext.getCacheDir()) == null) ? "" : FileHelper.f(cacheDir).replace("/cache", "");
    }

    private static String convertPath(String str) {
        String dataPathPrefix = getDataPathPrefix();
        return (TextUtils.isEmpty(str) || !str.startsWith(dataPathPrefix)) ? str : str.replace(dataPathPrefix, getDataUserPath());
    }

    private static void addToWhiteList(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        List<String> list = WHITE_LIST;
        if (list.contains(str)) {
            return;
        }
        list.add(str);
    }

    private static boolean wantLabelPath(String str) {
        if (!wantLabel()) {
            return false;
        }
        if (isInWhiteList(str)) {
            return true;
        }
        HwLog.d(TAG, "is not in whiteList");
        return false;
    }

    private static boolean wantLabel() {
        if (!MobileInfoHelper.isEmui11()) {
            HwLog.d(TAG, "is not Emui 11");
            return false;
        }
        if (!MobileInfoHelper.isHonorDevice()) {
            return true;
        }
        HwLog.d(TAG, "is honor devices");
        return false;
    }
}
