package com.huawei.openalliance.ad;

import com.huawei.operation.utils.Constants;

/* loaded from: classes5.dex */
public class dj {
    public static int a(int i) {
        if (i == 1) {
            return 6999;
        }
        if (i == 3) {
            return 2999;
        }
        if (i == 12) {
            return Constants.CODE_UNKNOWN_ERROR;
        }
        if (i == 18) {
            return 6999;
        }
        if (i == 60) {
            return 4999;
        }
        if (i != 7) {
            return i != 8 ? 5 : 999;
        }
        return 3999;
    }
}
