package com.huawei.operation.utils;

import android.content.Intent;

/* loaded from: classes5.dex */
public interface OperationWebActivityIntentBuilder {
    Intent build();

    OperationWebActivityIntentBuilder setBI(String str, String str2, String str3, String str4);

    OperationWebActivityIntentBuilder setFlags(int i);

    OperationWebActivityIntentBuilder setIntType(int i);

    OperationWebActivityIntentBuilder setStringType(String str);
}
