package defpackage;

import android.os.RemoteException;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwdevice.phoneprocess.mgr.linkage.channel.SampelBaseHandler;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IBaseCallback;
import com.huawei.hwservicesmgr.LinkageDeviceCommandListener;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes9.dex */
public class kgh implements SampelBaseHandler {
    @Override // com.huawei.hwdevice.phoneprocess.mgr.linkage.channel.SampelBaseHandler
    public void handle(cvr cvrVar, LinkageDeviceCommandListener linkageDeviceCommandListener, IBaseCallback iBaseCallback) {
        if (cvrVar instanceof cvu) {
            b((cvu) cvrVar, iBaseCallback);
        } else {
            ReleaseLogUtil.e("R_LINKAGE_Real_SampleRealHandler", "message not SamplePoint");
        }
    }

    private void b(cvu cvuVar, IBaseCallback iBaseCallback) {
        LogUtil.a("LINKAGE_Real_SampleRealHandler", "sampleRealHandle samplePoint = ", cvuVar);
        List<cvv> a2 = cvuVar.a();
        if (koq.b(a2)) {
            ReleaseLogUtil.d("R_LINKAGE_Real_SampleRealHandler", "handle dataInfoList is empty.");
            return;
        }
        HashMap<Integer, Double> b = b(a2);
        if (b.isEmpty()) {
            ReleaseLogUtil.d("R_LINKAGE_Real_SampleRealHandler", "handle fieldsValue is empty.");
            return;
        }
        String e = HiJsonUtil.e(b);
        try {
            ReleaseLogUtil.d("R_LINKAGE_Real_SampleRealHandler", "deviceDataListener: ", iBaseCallback);
            if (iBaseCallback == null) {
                return;
            }
            iBaseCallback.onResponse(0, e);
        } catch (RemoteException e2) {
            ReleaseLogUtil.c("R_LINKAGE_Real_SampleRealHandler", "handle RemoteException: ", ExceptionUtils.d(e2));
        }
    }

    private HashMap<Integer, Double> b(List<cvv> list) {
        double a2;
        int d;
        HashMap<Integer, Double> hashMap = new HashMap<>(20);
        if (koq.b(list)) {
            ReleaseLogUtil.d("R_LINKAGE_Real_SampleRealHandler", "parseRunPostureSamplePoints failed, SamplePointInfos is null");
            return hashMap;
        }
        for (cvv cvvVar : list) {
            int a3 = (int) cvvVar.a();
            if (ldr.f14767a.contains(Integer.valueOf(a3))) {
                if (a3 == 50004388 || a3 == 50004935) {
                    a2 = blq.a(cvvVar.d(), -255.0d);
                    LogUtil.a("LINKAGE_Real_SampleRealHandler", "parseSamplePoints dataDicId: ", Integer.valueOf(a3), ", value: ", Double.valueOf(a2), ", valueHex: ", cvx.d(cvvVar.d()));
                } else {
                    if (a3 == 50004286 || a3 == 50004642) {
                        d = blq.d(cvvVar.d(), 0);
                    } else {
                        d = CommonUtil.w(cvx.d(cvvVar.d()));
                    }
                    a2 = d;
                }
                hashMap.put(Integer.valueOf(a3), Double.valueOf(a2));
            }
        }
        return hashMap;
    }
}
