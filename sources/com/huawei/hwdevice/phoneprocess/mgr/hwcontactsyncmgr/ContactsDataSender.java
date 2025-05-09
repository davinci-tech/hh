package com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr;

import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.constant.TransferFileReqType;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback;
import defpackage.cvx;
import defpackage.jqj;
import defpackage.jsz;
import defpackage.jyx;

/* loaded from: classes5.dex */
public class ContactsDataSender {
    private static final ContactsDataSender d = new ContactsDataSender();

    /* renamed from: a, reason: collision with root package name */
    private SendFileCallback f6336a;
    private SendFileCallback b;
    private IAppTransferFileResultAIDLCallback c = new IAppTransferFileResultAIDLCallback.Stub() { // from class: com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.ContactsDataSender.2
        @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
        public void onFileTransferState(int i, String str) throws RemoteException {
            LogUtil.a("ContactsDataSender", "sync update zip file: ", "transferring ... ", Integer.valueOf(i), "%");
            if (ContactsDataSender.this.b != null) {
                ContactsDataSender.this.b.onTransferring(i);
            } else {
                LogUtil.h("ContactsDataSender", "sync update zip file: ", "onFileTransferState: callback is null.");
            }
        }

        @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
        public void onUpgradeFailed(int i, String str) throws RemoteException {
            LogUtil.a("ContactsDataSender", "sync update zip file: ", "transfer error.");
            if (ContactsDataSender.this.b != null) {
                ContactsDataSender.this.b.onTransferredFailed(i, str);
            } else {
                LogUtil.h("ContactsDataSender", "sync update zip file: ", "onUpgradeFailed: callback is null.");
            }
        }

        @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
        public void onFileRespond(int i, String str) throws RemoteException {
            LogUtil.a("ContactsDataSender", "sync update zip file: ", "transferred. checkResult: ", Integer.valueOf(i));
            if (ContactsDataSender.this.b == null) {
                LogUtil.h("ContactsDataSender", "sync update zip file: ", "onFileRespond: callback is null.");
                return;
            }
            if (i == 1) {
                ContactsDataSender.this.b.onTransferredSucceed();
                return;
            }
            if (i == 141000) {
                ContactsDataSender.this.b.onTransferredFailed(i, "Time out");
                return;
            }
            if (i == 140001) {
                ContactsDataSender.this.f6336a.onTransferredFailed(i, "distributed sync contacts");
                return;
            }
            ContactsDataSender.this.b.onTransferredFailed(-1, "invalid checkResult on 'onFileRespond' method : " + i);
        }
    };
    private IAppTransferFileResultAIDLCallback e = new IAppTransferFileResultAIDLCallback.Stub() { // from class: com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.ContactsDataSender.1
        @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
        public void onFileTransferState(int i, String str) throws RemoteException {
            LogUtil.a("ContactsDataSender", "sync delete csv file: ", "transferring ... ", Integer.valueOf(i), "%");
            if (ContactsDataSender.this.f6336a != null) {
                ContactsDataSender.this.f6336a.onTransferring(i);
            } else {
                LogUtil.h("ContactsDataSender", "sync delete csv file: ", "onFileTransferState: callback is null.");
            }
        }

        @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
        public void onUpgradeFailed(int i, String str) throws RemoteException {
            LogUtil.a("ContactsDataSender", "sync delete csv file: ", "transfer error: ", str);
            if (ContactsDataSender.this.f6336a != null) {
                ContactsDataSender.this.f6336a.onTransferredFailed(i, str);
            } else {
                LogUtil.h("ContactsDataSender", "sync delete csv file: ", "onUpgradeFailed: callback is null.");
            }
        }

        @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
        public void onFileRespond(int i, String str) throws RemoteException {
            LogUtil.a("ContactsDataSender", "sync delete csv file: ", "transferred. checkResult: ", Integer.valueOf(i));
            if (ContactsDataSender.this.f6336a == null) {
                LogUtil.h("ContactsDataSender", "sync delete csv file: ", "onFileRespond: callback is null.");
                return;
            }
            if (i == 1) {
                ContactsDataSender.this.f6336a.onTransferredSucceed();
                return;
            }
            if (i == 141000) {
                ContactsDataSender.this.f6336a.onTransferredFailed(i, "Time out");
                return;
            }
            if (i == 140001) {
                ContactsDataSender.this.f6336a.onTransferredFailed(i, "distributed sync contacts");
                return;
            }
            ContactsDataSender.this.f6336a.onTransferredFailed(-1, "invalid checkResult on 'onFileRespond' method : " + i);
        }
    };

    public interface SendFileCallback {
        void onTransferredFailed(int i, String str);

        void onTransferredSucceed();

        void onTransferring(int i);
    }

    private ContactsDataSender() {
    }

    public static ContactsDataSender e() {
        return d;
    }

    public void d(String str, String str2, SendFileCallback sendFileCallback) {
        LogUtil.a("ContactsDataSender", "syncContactZipFile:");
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || sendFileCallback == null) {
            LogUtil.h("ContactsDataSender", "syncContactZipFile: failure, parameter is null or empty.");
        } else {
            this.b = sendFileCallback;
            b(str, str2, 8, this.c);
        }
    }

    public void b(String str, String str2, SendFileCallback sendFileCallback) {
        LogUtil.a("ContactsDataSender", "syncContactCsvFile:");
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || sendFileCallback == null) {
            LogUtil.h("ContactsDataSender", "syncContactCsvFile: failure, parameter is null or empty.");
        } else {
            this.f6336a = sendFileCallback;
            b(str, str2, 9, this.e);
        }
    }

    public void d(String str, String str2, String str3, DeviceInfo deviceInfo) {
        e(3, cvx.a(e(str, 1) + e(str2, 2) + e(str3, 3)), deviceInfo);
    }

    private String e(String str, int i) {
        String e = cvx.e(i);
        String c = cvx.c(str);
        return e + cvx.d(c.length() / 2) + c;
    }

    private void b(String str, String str2, int i, IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback) {
        jqj jqjVar = new jqj();
        jqjVar.a(str2);
        jqjVar.f(str);
        jqjVar.d(i);
        jqjVar.c(TransferFileReqType.APP_DELIVERY);
        jqjVar.c((String) null);
        jyx.a().c(jqjVar, iAppTransferFileResultAIDLCallback);
    }

    public void e(int i, byte[] bArr, DeviceInfo deviceInfo) {
        if (bArr == null || bArr.length == 0) {
            LogUtil.h("ContactsDataSender", "sendDataToDevice: ");
            return;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(3);
        deviceCommand.setCommandID(i);
        deviceCommand.setDataContent(bArr);
        deviceCommand.setDataLen(bArr.length);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        LogUtil.a("ContactsDataSender", "sendDataToDevice: ", deviceCommand.toString());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }
}
