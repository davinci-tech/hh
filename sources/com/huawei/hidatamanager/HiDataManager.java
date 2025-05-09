package com.huawei.hidatamanager;

import android.content.ComponentName;
import android.content.Context;
import android.text.TextUtils;
import com.huawei.hidatamanager.hwnaturalbase.NBDataSourceManager;
import com.huawei.hidatamanager.hwnaturalbase.NBValidated;
import com.huawei.hidatamanager.util.Const;
import com.huawei.hidatamanager.util.LogUtils;
import java.lang.reflect.Method;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class HiDataManager {
    private static final int MAX_LENGTH_NUM = 1024;
    private static final int RET_ERR_PARAMS = -1;
    private static final int RET_FAIL = 0;
    private static final int RET_OK = 1;
    private static final String TAG = "HiDataManager";
    boolean isInitialized;
    private Context mContext;
    private NBDataSourceManager mNBDataSourceManager;

    static final class SingletonHolder {
        public static final HiDataManager INSTANCE = new HiDataManager();

        private SingletonHolder() {
        }
    }

    private HiDataManager() {
        this.isInitialized = false;
    }

    public static final HiDataManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void init(Context context) {
        this.mContext = context;
        if (!NBValidated.isOdmfInstalled(context)) {
            LogUtils.e(TAG, "odmf is not installed, init fail!");
            return;
        }
        NBDataSourceManager nBDataSourceManager = new NBDataSourceManager(context);
        this.mNBDataSourceManager = nBDataSourceManager;
        nBDataSourceManager.init();
        CaptureLocalService.getInstance(this.mContext).bindService();
        this.isInitialized = true;
    }

    public void destroy() {
        NBDataSourceManager nBDataSourceManager = this.mNBDataSourceManager;
        if (nBDataSourceManager != null) {
            nBDataSourceManager.disConnect();
            this.mNBDataSourceManager = null;
        }
        Context context = this.mContext;
        if (context != null) {
            CaptureLocalService.getInstance(context).unBindService();
        }
        this.mContext = null;
        this.isInitialized = false;
    }

    public int inputSourceData(long j, String str) {
        if (!this.isInitialized) {
            LogUtils.e(TAG, "HiDataManager hasn't been initialized!");
            return 0;
        }
        if (!SwitchManager.getModuleSwitchStatus(this.mContext)) {
            LogUtils.w(TAG, "main switch or current module switch is off");
            return 0;
        }
        if (!Const.RAW_DATATYPE_LIST.contains(Long.valueOf(j))) {
            LogUtils.w(TAG, "illegal sourceDataType: " + j);
            return 0;
        }
        if (TextUtils.isEmpty(str) || 1024 < str.length()) {
            LogUtils.w(TAG, "jsonData is error!");
            return -1;
        }
        if (isParamsLegal(j, str)) {
            return insertDataToRawTable(j, str) ? 1 : 0;
        }
        LogUtils.w(TAG, "The parameter is illegal!");
        return -1;
    }

    private boolean insertDataToRawTable(long j, String str) {
        LogUtils.w(TAG, "insertDataToRawTable enter!");
        try {
            Class<?> loadClass = this.mNBDataSourceManager.getClassLoader().loadClass("com.huawei.nb.model.collectencrypt.RawAppDataOrigin");
            Object newInstance = loadClass.newInstance();
            Method method = loadClass.getMethod("setDataSerialNumber", Long.class);
            if (method == null) {
                LogUtils.e(TAG, "setDataSerialNumber is null!");
                return false;
            }
            method.invoke(newInstance, Long.valueOf(j));
            Method method2 = loadClass.getMethod("setTimestamp", Long.class);
            if (method2 == null) {
                LogUtils.e(TAG, "setTimestamp is null!");
                return false;
            }
            method2.invoke(newInstance, Long.valueOf(System.currentTimeMillis()));
            Method method3 = loadClass.getMethod("setPackageName", String.class);
            if (method3 == null) {
                LogUtils.e(TAG, "setPackageName is null!");
                return false;
            }
            method3.invoke(newInstance, "com.huawei.health");
            Method method4 = loadClass.getMethod("setJsonData", String.class);
            if (method4 == null) {
                LogUtils.e(TAG, "setJsonData is null!");
                return false;
            }
            method4.invoke(newInstance, str);
            return this.mNBDataSourceManager.insert("RawAppDataOrigin", newInstance) != -1;
        } catch (Exception e) {
            LogUtils.w(TAG, "insertDataToRawTable fail!" + e);
            return false;
        }
    }

    private boolean isParamsLegal(long j, String str) {
        String[] strArr;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (1000 == j) {
                strArr = Const.HealthMustParams.PAIRED_DEVICE;
            } else if (Const.RawDataType.HEALTH_EVENT_RECORD == j) {
                strArr = Const.HealthMustParams.EVENT_RECORD;
            } else {
                strArr = Const.RawDataType.HEALTH_SLEEP_RECORD == j ? Const.HealthMustParams.SLEEP_RECORD : null;
            }
            if (strArr == null) {
                return true;
            }
            for (String str2 : strArr) {
                if (jSONObject.optString(str2).trim().isEmpty()) {
                    return false;
                }
            }
            return true;
        } catch (JSONException unused) {
            LogUtils.e(TAG, "isParamsLegal: JSONException");
            return false;
        }
    }

    public int subscribeFeatureData(long j, ComponentName componentName, String str) {
        if (!this.isInitialized) {
            LogUtils.e(TAG, "HiDataManager hasn't been initialized!");
            return 0;
        }
        return CaptureLocalService.getInstance(this.mContext).subscribeFeatureData(j, componentName, str);
    }

    public int unSubscribeFeatureData(long j, ComponentName componentName) {
        if (!this.isInitialized) {
            LogUtils.e(TAG, "HiDataManager hasn't been initialized!");
            return 0;
        }
        return CaptureLocalService.getInstance(this.mContext).unSubscribeFeatureData(j, componentName);
    }
}
