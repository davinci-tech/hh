package com.huawei.agconnect.apms;

import android.content.Context;
import com.huawei.agconnect.apms.collect.model.basic.ApplicationInformation;
import com.huawei.agconnect.apms.collect.model.basic.DeviceInformation;
import com.huawei.agconnect.apms.collect.model.basic.PlatformInformation;
import com.huawei.agconnect.apms.collect.model.basic.RuntimeEnvInformation;
import com.huawei.agconnect.apms.collect.model.basic.UserSettingsInformation;
import java.util.Map;

/* loaded from: classes.dex */
public interface def {
    long abc();

    void abc(int i);

    void abc(String str);

    void abc(String str, Map<String, String> map);

    void abc(boolean z);

    void bcd(int i);

    void bcd(String str);

    void bcd(boolean z);

    boolean bcd();

    void cde();

    void cde(boolean z);

    void def();

    void def(boolean z);

    DeviceInformation efg();

    void efg(boolean z);

    String fgh();

    void fgh(boolean z);

    String ghi();

    UserSettingsInformation hij();

    RuntimeEnvInformation ijk();

    Context jkl();

    PlatformInformation klm();

    boolean lmn();

    cde mno();

    ApplicationInformation nop();

    boolean opq();
}
