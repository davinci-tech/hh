package defpackage;

import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.devicesdk.callback.FrameReceiver;
import com.huawei.devicesdk.entity.DeviceDataFrameParcel;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.unitedevice.entity.UniteDevice;
import com.huawei.unitedevice.hwcommonfilemgr.ParserInterface;
import health.compact.a.LogUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class spt implements ParserInterface {
    private spt() {
        LogUtil.c("HwSampleCommandMgr", "create HwSampleCommandMgr");
    }

    public static spt d() {
        return a.d;
    }

    @Override // com.huawei.unitedevice.hwcommonfilemgr.ParserInterface
    public boolean getResult(DeviceInfo deviceInfo, byte[] bArr) {
        List<bmi> b;
        boolean c = c(bArr);
        LogUtil.c("HwSampleCommandMgr", "isSampleCommand: ", Boolean.valueOf(c));
        if (!c) {
            return true;
        }
        UniteDevice uniteDevice = new UniteDevice();
        uniteDevice.setIdentify(deviceInfo.getDeviceMac());
        uniteDevice.setDeviceInfo(deviceInfo);
        blt.d("HwSampleCommandMgr", bArr, "getResult() message:");
        try {
            b = d(bArr).b();
        } catch (bmk e) {
            LogUtil.e("HwSampleCommandMgr", "processSampleConfig Exception ", bll.a(e));
        }
        if (bkz.e(b)) {
            LogUtil.a("HwSampleCommandMgr", "getResult tlvList is null or size is zero");
            return false;
        }
        LogUtil.c("HwSampleCommandMgr", "fill destPkgName enter.");
        for (bmi bmiVar : b) {
            if (bli.e(bmiVar.e()) == 2) {
                String d = blq.d(bmiVar.c());
                if (TextUtils.isEmpty(d)) {
                    LogUtil.a("HwSampleCommandMgr", "getResult Constant.DEST_PKG_TYPE is null.");
                    return false;
                }
                b(d, uniteDevice, bArr);
                return false;
            }
        }
        return false;
    }

    private boolean c(byte[] bArr) {
        if (bArr == null || bArr.length < 1) {
            LogUtil.a("HwSampleCommandMgr", "isSampleCommandResult input param is invalid.");
            return false;
        }
        if (bArr[0] != 55) {
            return false;
        }
        LogUtil.c("HwSampleCommandMgr", "isSampleCommandResult data received");
        return true;
    }

    private void b(String str, UniteDevice uniteDevice, byte[] bArr) {
        boolean z;
        Exception e;
        Map<String, FrameReceiver> e2 = sps.a().e();
        int size = e2.size();
        LogUtil.c("HwSampleCommandMgr", "handleReceiveData enter. destPkgName: ", str, ", receiveCallbackList.size: ", Integer.valueOf(size));
        if (size == 0) {
            LogUtil.a("HwSampleCommandMgr", "getReceiver sampleBase is null.");
            return;
        }
        DeviceDataFrameParcel deviceDataFrameParcel = new DeviceDataFrameParcel();
        deviceDataFrameParcel.setData(bArr);
        Iterator<Map.Entry<String, FrameReceiver>> it = e2.entrySet().iterator();
        while (true) {
            z = false;
            if (!it.hasNext()) {
                break;
            }
            Map.Entry<String, FrameReceiver> next = it.next();
            if (str.equals(next.getKey())) {
                try {
                    if (next.getValue() instanceof FrameReceiver) {
                        LogUtil.c("HwSampleCommandMgr", "handleReceiveData success.");
                        try {
                            next.getValue().onFrameReceived(0, uniteDevice, deviceDataFrameParcel);
                            z = true;
                        } catch (RemoteException unused) {
                            z = true;
                            LogUtil.e("HwSampleCommandMgr", "preProcess RemoteException");
                            LogUtil.c("HwSampleCommandMgr", "handleReceiveData exit. isSuccess: ", Boolean.valueOf(z));
                        } catch (Exception e3) {
                            e = e3;
                            z = true;
                            LogUtil.e("HwSampleCommandMgr", "preProcess Exception : ", ExceptionUtils.d(e));
                            LogUtil.c("HwSampleCommandMgr", "handleReceiveData exit. isSuccess: ", Boolean.valueOf(z));
                        }
                    }
                } catch (RemoteException unused2) {
                } catch (Exception e4) {
                    e = e4;
                }
            }
        }
        LogUtil.c("HwSampleCommandMgr", "handleReceiveData exit. isSuccess: ", Boolean.valueOf(z));
    }

    private bmj d(byte[] bArr) throws bmk {
        String d = blq.d(bArr);
        return new bmn().c((d == null || d.length() <= 4) ? null : d.substring(4, d.length()));
    }

    static class a {
        private static spt d = new spt();
    }
}
