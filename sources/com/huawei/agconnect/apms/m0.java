package com.huawei.agconnect.apms;

import com.google.gson.JsonPrimitive;
import com.huawei.operation.utils.Constants;

/* loaded from: classes2.dex */
public class m0 {
    public static final Number abc = Float.valueOf(Float.NaN);

    public static JsonPrimitive abc(String str) {
        if (str == null) {
            str = Constants.NULL;
        }
        return new JsonPrimitive(str);
    }

    public static JsonPrimitive abc(Boolean bool) {
        if (bool == null) {
            bool = Boolean.FALSE;
        }
        return new JsonPrimitive(bool);
    }

    public static JsonPrimitive abc(Number number) {
        if (number == null) {
            number = abc;
        }
        return new JsonPrimitive(number);
    }
}
