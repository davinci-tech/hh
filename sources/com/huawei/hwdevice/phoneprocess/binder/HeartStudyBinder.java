package com.huawei.hwdevice.phoneprocess.binder;

import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IHeartStudyAIDL;
import defpackage.bmf;
import defpackage.cvx;
import defpackage.iyq;

/* loaded from: classes.dex */
public class HeartStudyBinder extends IHeartStudyAIDL.Stub {
    @Override // com.huawei.hwservicesmgr.IHeartStudyAIDL
    public String getBtDeviceBondId(String str) {
        LogUtil.a("HeartHealthBinder", "enter getBtDeviceBondId");
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HeartHealthBinder", "getBtDeviceBondId mac is null");
            return "";
        }
        String b = iyq.b(BaseApplication.getContext()).b(str, BaseApplication.getContext());
        if (!TextUtils.isEmpty(b)) {
            return cvx.e(b);
        }
        String c = bmf.c(str);
        if (!TextUtils.isEmpty(c)) {
            return cvx.e(c);
        }
        LogUtil.a("HeartHealthBinder", "getBtDeviceBondId bindValue is null.");
        return "";
    }
}
