package com.huawei.health.pluginhealthzone;

import android.content.Context;
import com.huawei.health.h5pro.jsbridge.base.JsModuleBase;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import defpackage.dst;
import defpackage.exf;
import defpackage.exg;
import defpackage.exh;
import java.util.List;

/* loaded from: classes.dex */
public interface FamilyHealthZoneApi {
    Class<? extends JsModuleBase> getCommonJsModule(String str);

    void getCommonUsedDevices(IBaseResponseCallback iBaseResponseCallback);

    void getOtherUserInfo(String str, UserInfoCallback<exg> userInfoCallback);

    void getUserGrpList(IBaseResponseCallback iBaseResponseCallback);

    void gotoFamilyHealth(Context context, String str);

    void notifyMemberCheckHealth(String str, IBaseResponseCallback iBaseResponseCallback);

    void requestFindUserInfo(int i, String str, UserInfoCallback<exh.b> userInfoCallback);

    void setCommonUsedDevices(List<exf> list, IBaseResponseCallback iBaseResponseCallback);

    void startFamilySpaceH5(Context context, dst dstVar);
}
