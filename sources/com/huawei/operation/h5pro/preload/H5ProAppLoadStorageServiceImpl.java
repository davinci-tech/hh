package com.huawei.operation.h5pro.preload;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.h5pro.scalable.storage.H5AppLoadStorageInfo;
import com.huawei.health.h5pro.scalable.storage.IH5ProAppLoadStorageService;
import com.huawei.hwcommonmodel.application.BaseApplication;
import defpackage.nsn;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;

/* loaded from: classes.dex */
public class H5ProAppLoadStorageServiceImpl implements IH5ProAppLoadStorageService {
    private static final String SHARED_PREFERENCES_FILE_NAME = "H5_Loaded_Count_SharedPreference";

    @Override // com.huawei.health.h5pro.scalable.storage.IH5ProAppLoadStorageService
    public int incrLoadCount(Context context, String str) {
        H5AppLoadStorageInfo info = getInfo(context, str);
        if (info == null) {
            info = initInfo();
        }
        info.setCount(info.getCount() + 1);
        info.setLoadTime(System.currentTimeMillis());
        return SharedPreferenceManager.e(BaseApplication.getContext(), SHARED_PREFERENCES_FILE_NAME, str, obj2Str(info), new StorageParams(0));
    }

    @Override // com.huawei.health.h5pro.scalable.storage.IH5ProAppLoadStorageService
    public H5AppLoadStorageInfo getInfo(Context context, String str) {
        H5AppLoadStorageInfo h5AppLoadStorageInfo;
        synchronized (this) {
            String b = SharedPreferenceManager.b(BaseApplication.getContext(), SHARED_PREFERENCES_FILE_NAME, str);
            if (TextUtils.isEmpty(b)) {
                h5AppLoadStorageInfo = null;
            } else {
                String[] split = b.split(",");
                h5AppLoadStorageInfo = new H5AppLoadStorageInfo();
                h5AppLoadStorageInfo.setName(str);
                if (split.length > 0) {
                    h5AppLoadStorageInfo.setCount(nsn.h(split[0]));
                }
                if (split.length > 1) {
                    h5AppLoadStorageInfo.setLoadTime(nsn.h(split[1]));
                }
                if (split.length > 2) {
                    h5AppLoadStorageInfo.setPreLoadTime(nsn.h(split[2]));
                }
            }
        }
        return h5AppLoadStorageInfo;
    }

    @Override // com.huawei.health.h5pro.scalable.storage.IH5ProAppLoadStorageService
    public int updatePreLoadTime(Context context, String str) {
        int e;
        synchronized (this) {
            H5AppLoadStorageInfo info = getInfo(context, str);
            if (info == null) {
                info = initInfo();
            }
            info.setPreLoadTime(System.currentTimeMillis());
            e = SharedPreferenceManager.e(BaseApplication.getContext(), SHARED_PREFERENCES_FILE_NAME, str, obj2Str(info), new StorageParams(0));
        }
        return e;
    }

    private String obj2Str(H5AppLoadStorageInfo h5AppLoadStorageInfo) {
        return h5AppLoadStorageInfo.getCount() + "," + h5AppLoadStorageInfo.getLoadTime() + "," + h5AppLoadStorageInfo.getPreLoadTime();
    }

    private H5AppLoadStorageInfo initInfo() {
        H5AppLoadStorageInfo h5AppLoadStorageInfo = new H5AppLoadStorageInfo();
        h5AppLoadStorageInfo.setCount(0L);
        h5AppLoadStorageInfo.setLoadTime(0L);
        h5AppLoadStorageInfo.setPreLoadTime(0L);
        return h5AppLoadStorageInfo;
    }
}
