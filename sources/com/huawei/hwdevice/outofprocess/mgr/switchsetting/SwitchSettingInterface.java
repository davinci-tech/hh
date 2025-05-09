package com.huawei.hwdevice.outofprocess.mgr.switchsetting;

import com.huawei.hwbasemgr.IBaseResponseCallback;
import java.util.List;

/* loaded from: classes5.dex */
public interface SwitchSettingInterface {
    void getSwitchSetting(String str, IBaseResponseCallback iBaseResponseCallback);

    void getSwitchSetting(String str, String str2, IBaseResponseCallback iBaseResponseCallback);

    void getSwitchSetting(List<String> list, IBaseResponseCallback iBaseResponseCallback);

    String getSwitchSettingFromDb(String str);

    String getSwitchSettingFromLocal(String str, int i);

    void sendSetSwitchSettingCmd(int i, String str, String str2);

    void sendSetSwitchSettingCmd(boolean z, String str, int i, int i2, IBaseResponseCallback iBaseResponseCallback);

    void sendSetSwitchSettingCmd(byte[] bArr, String str, int i, int i2);

    void sendSettingSwitchCommand(String str, String str2, long j, String str3);

    void setSwitchSetting(String str, String str2, IBaseResponseCallback iBaseResponseCallback);

    void setSwitchSetting(String str, String str2, String str3, IBaseResponseCallback iBaseResponseCallback);

    void setSwitchSettingToDb(String str, String str2);

    void setSwitchSettingToLocal(String str, String str2, int i);
}
