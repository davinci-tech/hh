package com.huawei.agconnect.apms;

import android.content.Context;
import com.huawei.agconnect.apms.collect.model.basic.ApplicationInformation;
import com.huawei.agconnect.apms.collect.model.basic.DeviceInformation;
import com.huawei.agconnect.apms.collect.model.basic.PlatformInformation;
import com.huawei.agconnect.apms.collect.model.basic.RuntimeEnvInformation;
import com.huawei.agconnect.apms.collect.model.basic.UserSettingsInformation;
import com.huawei.operation.utils.Constants;
import java.util.Map;

/* loaded from: classes2.dex */
public class fgh implements def {
    public final cde abc = new cde();

    @Override // com.huawei.agconnect.apms.def
    public long abc() {
        return System.currentTimeMillis();
    }

    @Override // com.huawei.agconnect.apms.def
    public void abc(int i) {
    }

    @Override // com.huawei.agconnect.apms.def
    public void abc(String str) {
    }

    @Override // com.huawei.agconnect.apms.def
    public void abc(String str, Map<String, String> map) {
    }

    @Override // com.huawei.agconnect.apms.def
    public void abc(boolean z) {
    }

    @Override // com.huawei.agconnect.apms.def
    public void bcd(int i) {
    }

    @Override // com.huawei.agconnect.apms.def
    public void bcd(String str) {
    }

    @Override // com.huawei.agconnect.apms.def
    public void bcd(boolean z) {
    }

    @Override // com.huawei.agconnect.apms.def
    public boolean bcd() {
        return false;
    }

    @Override // com.huawei.agconnect.apms.def
    public void cde() {
    }

    @Override // com.huawei.agconnect.apms.def
    public void cde(boolean z) {
    }

    @Override // com.huawei.agconnect.apms.def
    public void def() {
    }

    @Override // com.huawei.agconnect.apms.def
    public void def(boolean z) {
    }

    @Override // com.huawei.agconnect.apms.def
    public DeviceInformation efg() {
        return new DeviceInformation("", "", "");
    }

    @Override // com.huawei.agconnect.apms.def
    public void efg(boolean z) {
    }

    @Override // com.huawei.agconnect.apms.def
    public void fgh(boolean z) {
    }

    @Override // com.huawei.agconnect.apms.def
    public String ghi() {
        return null;
    }

    @Override // com.huawei.agconnect.apms.def
    public UserSettingsInformation hij() {
        return new UserSettingsInformation();
    }

    @Override // com.huawei.agconnect.apms.def
    public RuntimeEnvInformation ijk() {
        return new RuntimeEnvInformation(0L, 1, Constants.NULL, 0L);
    }

    @Override // com.huawei.agconnect.apms.def
    public Context jkl() {
        return null;
    }

    @Override // com.huawei.agconnect.apms.def
    public PlatformInformation klm() {
        return new PlatformInformation();
    }

    @Override // com.huawei.agconnect.apms.def
    public boolean lmn() {
        return true;
    }

    @Override // com.huawei.agconnect.apms.def
    public cde mno() {
        return this.abc;
    }

    @Override // com.huawei.agconnect.apms.def
    public ApplicationInformation nop() {
        return new ApplicationInformation(Constants.NULL, "0.0", Constants.NULL);
    }

    @Override // com.huawei.agconnect.apms.def
    public boolean opq() {
        return true;
    }

    @Override // com.huawei.agconnect.apms.def
    public String fgh() {
        return "";
    }
}
