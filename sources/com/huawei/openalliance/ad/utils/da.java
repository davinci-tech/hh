package com.huawei.openalliance.ad.utils;

import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.ho;

/* loaded from: classes5.dex */
public abstract class da {
    public static Integer a(String str, int i, int i2) {
        if (TextUtils.isEmpty(str) || i2 < 0) {
            return null;
        }
        String[] split = str.split(Constants.LINK);
        if (split.length < i2 + 1) {
            return null;
        }
        return a(split[i2], i);
    }

    public static Integer a(String str, int i) {
        StringBuilder sb;
        if (TextUtils.isEmpty(str) || str.length() <= i) {
            return null;
        }
        try {
            return Integer.valueOf(Integer.parseInt(str.substring(i, i + 1)));
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("getSwitch RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("SwitchUtil", sb.toString());
            return null;
        } catch (Exception e2) {
            e = e2;
            sb = new StringBuilder("getSwitch Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("SwitchUtil", sb.toString());
            return null;
        }
    }
}
