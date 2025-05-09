package com.huawei.wisesecurity.ucs.credential.outer;

import defpackage.twc;
import java.io.IOException;

/* loaded from: classes7.dex */
public interface NetworkCapability {
    NetworkResponse get(NetworkRequest networkRequest) throws IOException;

    void initConfig(int i, int i2) throws twc;

    NetworkResponse post(NetworkRequest networkRequest) throws IOException;
}
