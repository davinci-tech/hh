package defpackage;

import android.os.RemoteException;
import com.huawei.hwbtsdk.btdatatype.callback.BluetoothDialogCallback;
import com.huawei.hwbtsdk.btdatatype.datatype.BluetoothDeviceNode;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.connectmgr.DeviceDialogMessage;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IBluetoothDialogAidlCallback;
import java.util.List;

/* loaded from: classes5.dex */
public class juo {
    public static String d(DeviceDialogMessage deviceDialogMessage, IBluetoothDialogAidlCallback iBluetoothDialogAidlCallback) {
        if (deviceDialogMessage == null) {
            LogUtil.h("DialogMessage", "dialogMessage deviceDialogMessage is null");
            return "";
        }
        int method = deviceDialogMessage.getMethod();
        boolean isStatusFlag = deviceDialogMessage.isStatusFlag();
        long selectId = deviceDialogMessage.getSelectId();
        String deviceName = deviceDialogMessage.getDeviceName();
        int bluetoothType = deviceDialogMessage.getBluetoothType();
        LogUtil.a("DialogMessage", "method :", Integer.valueOf(method));
        izy b = izy.b(BaseApplication.getContext());
        if (method == 10001) {
            b.i();
        } else if (method == 10002) {
            b.a(isStatusFlag);
        } else if (method == 10004) {
            b.n();
        } else if (method == 10015) {
            b.o();
        } else if (method == 10026) {
            b.e(selectId, bluetoothType);
        } else if (method != 10033) {
            switch (method) {
                case 10006:
                    b.bEd_(isStatusFlag, null);
                    break;
                case 10007:
                    return b.m();
                case 10008:
                    return b.g();
                default:
                    b(method, selectId, isStatusFlag, deviceName, b);
                    break;
            }
        } else {
            b.c(new d(iBluetoothDialogAidlCallback));
        }
        return deviceName;
    }

    private static void b(int i, long j, boolean z, String str, izy izyVar) {
        if (i == 10003) {
            izyVar.c();
            return;
        }
        if (i == 10016) {
            izyVar.j();
            return;
        }
        if (i == 10027) {
            izyVar.a(j);
        } else if (i == 10028) {
            a(z, str, izyVar);
        } else {
            LogUtil.h("DialogMessage", "processOtherDialogMessage default");
        }
    }

    private static void a(boolean z, String str, izy izyVar) {
        jtn.b().e(z, str);
        if (z) {
            izyVar.d(str, 1);
        } else {
            izyVar.d(str, 0);
        }
    }

    static class d implements BluetoothDialogCallback {
        private IBluetoothDialogAidlCallback b;

        d(IBluetoothDialogAidlCallback iBluetoothDialogAidlCallback) {
            this.b = iBluetoothDialogAidlCallback;
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BluetoothDialogCallback
        public void onSetList(List<BluetoothDeviceNode> list, boolean z, int i) {
            try {
                IBluetoothDialogAidlCallback iBluetoothDialogAidlCallback = this.b;
                if (iBluetoothDialogAidlCallback != null) {
                    iBluetoothDialogAidlCallback.onSetList(list, z, i);
                }
            } catch (RemoteException unused) {
                LogUtil.b("DialogMessage", "onSetList RemoteException");
            }
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BluetoothDialogCallback
        public void onScanFinished() {
            try {
                IBluetoothDialogAidlCallback iBluetoothDialogAidlCallback = this.b;
                if (iBluetoothDialogAidlCallback != null) {
                    iBluetoothDialogAidlCallback.onScanFinished();
                }
            } catch (RemoteException unused) {
                LogUtil.b("DialogMessage", "onScanFinished RemoteException");
            }
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BluetoothDialogCallback
        public void onSetNameFilter(List list) {
            try {
                IBluetoothDialogAidlCallback iBluetoothDialogAidlCallback = this.b;
                if (iBluetoothDialogAidlCallback != null) {
                    iBluetoothDialogAidlCallback.onSetNameFilter(list);
                }
            } catch (RemoteException unused) {
                LogUtil.b("DialogMessage", "onSetNameFilter RemoteException");
            }
        }
    }
}
