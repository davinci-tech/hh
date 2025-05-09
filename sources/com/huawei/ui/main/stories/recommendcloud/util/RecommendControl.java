package com.huawei.ui.main.stories.recommendcloud.util;

import android.content.Context;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.recommendcloud.config.CoreSleepTagConfig;
import com.huawei.ui.main.stories.recommendcloud.data.SleepRecommendData;
import com.huawei.ui.main.stories.recommendcloud.data.SleepServiceData;
import defpackage.koq;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class RecommendControl {
    private static final String CORE_SLEEP_TAG_CONFIG_NEW_PATH = "sleep/CoreSleepTagConfigNew_2020.txt";
    private static final String CORE_SLEEP_TAG_CONFIG_PATH = "CoreSleepTagConfig.txt";
    private static final Object LOCK_OBJECT = new Object();
    private static final String TAG = "RecommendControl";
    private static Context sContext;
    private static RecommendControl sRecommendControl;
    private CoreSleepTagConfig mCoreSleepTagConfig = null;
    private String mSmartCardCoreSleepTime;

    private RecommendControl() {
    }

    public static RecommendControl newInstance(Context context) {
        RecommendControl recommendControl;
        sContext = context.getApplicationContext();
        synchronized (LOCK_OBJECT) {
            if (sContext == null) {
                sContext = context;
            }
            if (sRecommendControl == null) {
                sRecommendControl = new RecommendControl();
            }
            recommendControl = sRecommendControl;
        }
        return recommendControl;
    }

    public SleepRecommendData getSleepRecommendData(String str) {
        CoreSleepTagConfig coreSleepTagConfig = this.mCoreSleepTagConfig;
        if (coreSleepTagConfig == null) {
            coreSleepTagConfig = initCoreSleepTagConfig();
        }
        if (coreSleepTagConfig == null || koq.b(coreSleepTagConfig.getCoreSleepTagList())) {
            LogUtil.h(TAG, "coreSleepTagConfig or getCoreSleepTagList is null ");
            return null;
        }
        for (SleepRecommendData sleepRecommendData : coreSleepTagConfig.getCoreSleepTagList()) {
            if (str.equals(sleepRecommendData.getSuggestion())) {
                LogUtil.a(TAG, "perfect ");
                return sleepRecommendData;
            }
        }
        SleepRecommendData sleepRecommendData2 = coreSleepTagConfig.getCoreSleepTagList().get(0);
        LogUtil.a(TAG, "sleepData.suggestion :", sleepRecommendData2.getSuggestion());
        return sleepRecommendData2;
    }

    public List<SleepServiceData.SleepZoneConfigBean.InfoListBean> getInfoList() {
        SleepServiceData sleepServiceData = (SleepServiceData) HiJsonUtil.e(getAssetString(), SleepServiceData.class);
        ArrayList arrayList = new ArrayList(16);
        if (sleepServiceData != null) {
            SleepServiceData.SleepZoneConfigBean sleepZoneConfig = sleepServiceData.getSleepZoneConfig();
            if (sleepZoneConfig == null) {
                LogUtil.h(TAG, "getInfoList bean is null");
                return arrayList;
            }
            List<SleepServiceData.SleepZoneConfigBean.InfoListBean> infoList = sleepZoneConfig.getInfoList();
            if (infoList == null) {
                LogUtil.h(TAG, "getInfoList infoList is null");
                return arrayList;
            }
            for (int i = 0; i < 4 && i < infoList.size(); i++) {
                arrayList.add(infoList.get(i));
            }
        }
        return arrayList;
    }

    private String getAssetString() {
        String str;
        str = "";
        InputStream inputStream = null;
        try {
            try {
                inputStream = BaseApplication.getContext().getAssets().open(CORE_SLEEP_TAG_CONFIG_NEW_PATH);
                byte[] bArr = new byte[inputStream.available()];
                str = inputStream.read(bArr) > 0 ? new String(bArr, "UTF-8") : "";
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused) {
                        LogUtil.b(TAG, "getAssetString IOException");
                    }
                }
            } catch (IOException unused2) {
                LogUtil.b(TAG, "getAssetString IOException");
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused3) {
                        LogUtil.b(TAG, "getAssetString IOException");
                    }
                }
            }
            return str;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused4) {
                    LogUtil.b(TAG, "getAssetString IOException");
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0089  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.ui.main.stories.recommendcloud.config.CoreSleepTagConfig initCoreSleepTagConfig() {
        /*
            r5 = this;
            java.lang.String r0 = "RecommendControl"
            r1 = 0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L90
            r2.<init>()     // Catch: java.io.IOException -> L90
            android.content.Context r3 = com.huawei.ui.main.stories.recommendcloud.util.RecommendControl.sContext     // Catch: java.io.IOException -> L90
            android.content.Context r3 = r3.getApplicationContext()     // Catch: java.io.IOException -> L90
            java.io.File r3 = r3.getFilesDir()     // Catch: java.io.IOException -> L90
            java.lang.String r3 = r3.getCanonicalPath()     // Catch: java.io.IOException -> L90
            r2.append(r3)     // Catch: java.io.IOException -> L90
            java.lang.String r3 = java.io.File.separator     // Catch: java.io.IOException -> L90
            r2.append(r3)     // Catch: java.io.IOException -> L90
            java.lang.String r3 = "recommendcloud"
            r2.append(r3)     // Catch: java.io.IOException -> L90
            java.lang.String r3 = java.io.File.separator     // Catch: java.io.IOException -> L90
            r2.append(r3)     // Catch: java.io.IOException -> L90
            java.lang.String r3 = "sleepServiceConfig"
            r2.append(r3)     // Catch: java.io.IOException -> L90
            java.lang.String r3 = java.io.File.separator     // Catch: java.io.IOException -> L90
            r2.append(r3)     // Catch: java.io.IOException -> L90
            java.lang.String r2 = r2.toString()     // Catch: java.io.IOException -> L90
            java.lang.String r3 = "initCoreSleepTagConfig"
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r2)
            java.lang.String r2 = java.io.File.separator
            r3.append(r2)
            java.lang.String r2 = "CoreSleepTagConfig.txt"
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            java.lang.String r2 = health.compact.a.CommonUtil.t(r2)
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 == 0) goto L69
            java.lang.String r2 = "initCoreSleepTagConfig str is null"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            com.huawei.hwlogsmodel.LogUtil.h(r0, r2)
            return r1
        L69:
            java.lang.Class<com.huawei.ui.main.stories.recommendcloud.config.CoreSleepTagConfig> r3 = com.huawei.ui.main.stories.recommendcloud.config.CoreSleepTagConfig.class
            java.lang.Object r2 = com.huawei.hihealth.util.HiJsonUtil.e(r2, r3)     // Catch: com.google.gson.JsonSyntaxException -> L76
            com.huawei.ui.main.stories.recommendcloud.config.CoreSleepTagConfig r2 = (com.huawei.ui.main.stories.recommendcloud.config.CoreSleepTagConfig) r2     // Catch: com.google.gson.JsonSyntaxException -> L76
            r5.mCoreSleepTagConfig = r2     // Catch: com.google.gson.JsonSyntaxException -> L74
            goto L87
        L74:
            r1 = move-exception
            goto L7a
        L76:
            r2 = move-exception
            r4 = r2
            r2 = r1
            r1 = r4
        L7a:
            java.lang.String r3 = "initCoreSleepTagConfig JsonSyntaxException "
            java.lang.String r1 = r1.getMessage()
            java.lang.Object[] r1 = new java.lang.Object[]{r3, r1}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r1)
        L87:
            if (r2 == 0) goto L8f
            java.lang.String r0 = r2.getCoreSleepTime()
            r5.mSmartCardCoreSleepTime = r0
        L8f:
            return r2
        L90:
            java.lang.String r2 = "initCoreSleepTagConfig IOException"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.recommendcloud.util.RecommendControl.initCoreSleepTagConfig():com.huawei.ui.main.stories.recommendcloud.config.CoreSleepTagConfig");
    }

    public String getSmartCardCoreSleepTime() {
        return this.mSmartCardCoreSleepTime;
    }

    public void clearData() {
        this.mCoreSleepTagConfig = null;
    }
}
