package com.huawei.hwsmartinteractmgr.service;

import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.ISmartInteract;
import com.huawei.hwsmartinteractmgr.ISmartMsgReadResultListener;
import com.huawei.operation.OpAnalyticsUtil;
import defpackage.kvy;
import defpackage.kwa;

/* loaded from: classes9.dex */
public class SmartInteractBinder extends ISmartInteract.Stub {
    @Override // com.huawei.hwsmartinteractmgr.ISmartInteract
    public void checkSmartMsg(int i, final ISmartMsgReadResultListener iSmartMsgReadResultListener) {
        OpAnalyticsUtil.getInstance().setProbabilityProblemEvent("checkSmartMsg", "checkSmartMsg");
        LogUtil.a("SMART_SmartInteractBinder", "checkSmartMsg");
        kwa.c(i, new IBaseResponseCallback() { // from class: com.huawei.hwsmartinteractmgr.service.SmartInteractBinder.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                LogUtil.a("SMART_SmartInteractBinder", "checkSmartMsg errCode = ", Integer.valueOf(i2));
                try {
                    iSmartMsgReadResultListener.onResult(i2, null);
                } catch (RemoteException e) {
                    LogUtil.b("SMART_SmartInteractBinder", "remoteException, ", e.getMessage());
                }
            }
        });
    }

    @Override // com.huawei.hwsmartinteractmgr.ISmartInteract.Stub, android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        OpAnalyticsUtil.getInstance().setProbabilityProblemEvent("onTransact start", "onTransact");
        LogUtil.a("SMART_SmartInteractBinder", "SmartBinder onTransact");
        if (!kvy.b()) {
            OpAnalyticsUtil.getInstance().setProbabilityProblemEvent("onTransact exception", "RemoteException");
            throw new RemoteException("hw permission check failed");
        }
        return super.onTransact(i, parcel, parcel2, i2);
    }
}
