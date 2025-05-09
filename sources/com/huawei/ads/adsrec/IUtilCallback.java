package com.huawei.ads.adsrec;

import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public interface IUtilCallback {
    String aes128Decrypt(String str);

    String aes128Encrypt(String str);

    String[] getAllowCachedTradeModeList(String str);

    String[] getAllowCachedTradeModeList(String str, String str2);

    String getConfig(String str);

    Long getCostEndTime(int i);

    List<String> getDailyIntentId(int i, int i2);

    byte[] getDeviceAiParamKey();

    int getMediaType(String str);

    Map<String, Double> getUserCost(int i);

    boolean isInHmsProcess();
}
