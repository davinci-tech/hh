package com.huawei.login.ui.login.util;

import android.content.Context;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdataaccessmodel.utils.StorageDataCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.StorageParams;

/* loaded from: classes.dex */
public class ContentProviderUtil {
    private static final String COUNTRY_CODE = "country_code";
    private static final String DEVICE_ID = "device_id";
    private static final String DEVICE_TYPE = "device_type";
    private static final Object LOCK_OBJECT = new Object();
    private static final String SERVICE_COUNTRY_CODE = "service_country_code";
    private static final String TAG = "ContentProviderUtil";
    private static ContentProviderUtil mContentProviderUtil;
    private static Context mContext;

    public static ContentProviderUtil getInstance(Context context) {
        ContentProviderUtil contentProviderUtil;
        synchronized (LOCK_OBJECT) {
            if (context == null) {
                mContext = BaseApplication.getContext();
            } else {
                mContext = context;
            }
            if (mContentProviderUtil == null) {
                mContentProviderUtil = new ContentProviderUtil();
            }
            contentProviderUtil = mContentProviderUtil;
        }
        return contentProviderUtil;
    }

    public void setCountryCode(String str, StorageDataCallback storageDataCallback) {
        Context context = mContext;
        if (context == null) {
            LogUtil.b(TAG, "setCountryCode: mContext is null !");
        } else {
            KeyValDbManager.b(context).e("country_code", str, new StorageParams(1), storageDataCallback);
            LogUtil.c(TAG, "setCountryCode completed, country is:", str);
        }
    }

    public String getCountryCode() {
        Context context = mContext;
        if (context == null) {
            LogUtil.b(TAG, "getCountryCode: mContext is null !");
            return "";
        }
        String d = KeyValDbManager.b(context).d("country_code", new StorageParams(1));
        LogUtil.c(TAG, "getCountryCode completed !");
        return d;
    }

    public void setServiceCountryCode(String str, StorageDataCallback storageDataCallback) {
        Context context = mContext;
        if (context == null) {
            LogUtil.b(TAG, "setServiceCountryCode: mContext is null !");
        } else {
            KeyValDbManager.b(context).e(SERVICE_COUNTRY_CODE, str, new StorageParams(1), storageDataCallback);
            LogUtil.c(TAG, "setServiceCountryCode completed, serviceCountryCode is:", str);
        }
    }

    public String getServiceCountryCode() {
        Context context = mContext;
        if (context == null) {
            LogUtil.b(TAG, "getServiceCountryCode: mContext is null !");
            return "";
        }
        String d = KeyValDbManager.b(context).d(SERVICE_COUNTRY_CODE, new StorageParams(1));
        LogUtil.c(TAG, "getServiceCountryCode completed !!!");
        return d;
    }

    public void setDeviceType(String str, StorageDataCallback storageDataCallback) {
        if (mContext == null) {
            LogUtil.b(TAG, "setDeviceType: mContext is null!");
            return;
        }
        LoginCache.configDeviceType(str);
        KeyValDbManager.b(mContext).d("device_type", str, storageDataCallback);
        LogUtil.c(TAG, "setCountryCode completed, deviceType is:", str);
    }

    public String getDeviceType() {
        Context context = mContext;
        if (context == null) {
            LogUtil.b(TAG, "getDeviceType: mContext is null !");
            return "";
        }
        String e = KeyValDbManager.b(context).e("device_type");
        LogUtil.c(TAG, "getDeviceType completed !!!");
        return e;
    }

    public void setDeviceId(String str, StorageDataCallback storageDataCallback) {
        Context context = mContext;
        if (context == null) {
            LogUtil.b(TAG, "setDeviceId: mContext is null !");
        } else {
            KeyValDbManager.b(context).e("device_id", str, new StorageParams(1), storageDataCallback);
            LogUtil.c(TAG, "setDeviceId completed, deviceId is:", str);
        }
    }

    public String getDeviceId() {
        Context context = mContext;
        if (context == null) {
            LogUtil.b(TAG, "getDeviceId: mContext is null !!!");
            return "";
        }
        String d = KeyValDbManager.b(context).d("device_id", new StorageParams(1));
        LogUtil.c(TAG, "getDeviceId completed !!!");
        return d;
    }
}
