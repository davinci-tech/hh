package com.huawei.hianalytics.framework.config;

import android.util.Pair;
import java.util.List;

/* loaded from: classes4.dex */
public interface IMandatoryParameters {
    boolean checkDebugModeEnabled();

    List<String> getAllTags();

    String getAppVer();

    String getCipherType();

    String getDebugModeUrl();

    byte[] getLoadWorkKey();

    String getModel();

    String getProcessName();

    Pair<String, String> getRsaPublicKeyFromNetWork(String str, String str2);

    boolean isFlashKey();

    void refreshKey(String str, int i);

    String rsaEncrypt(String str, String str2, String str3);
}
