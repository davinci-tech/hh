package defpackage;

import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwdevice.phoneprocess.mgr.linkage.channel.SampelBaseHandler;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IBaseCallback;
import com.huawei.hwservicesmgr.LinkageDeviceCommandListener;
import defpackage.bmt;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes9.dex */
public class kgl implements SampelBaseHandler {
    @Override // com.huawei.hwdevice.phoneprocess.mgr.linkage.channel.SampelBaseHandler
    public void handle(cvr cvrVar, LinkageDeviceCommandListener linkageDeviceCommandListener, IBaseCallback iBaseCallback) {
        if (cvrVar instanceof cvq) {
            c((cvq) cvrVar);
        } else {
            ReleaseLogUtil.e("R_LINKAGE_Config_SamlpeConfigHandler", "message not SamplePoint");
        }
    }

    private void c(cvq cvqVar) {
        LogUtil.a("LINKAGE_Config_SamlpeConfigHandler", "handleSampleConfig sampleConfig = ", cvqVar);
        cvn cvnVar = cvqVar.getConfigInfoList().get(0);
        Long valueOf = Long.valueOf(cvnVar.a());
        ReleaseLogUtil.e("R_LINKAGE_Config_SamlpeConfigHandler", "handleSampleConfig configId = ", valueOf);
        if (ldr.d.contains(valueOf)) {
            e(cvnVar);
        } else {
            ReleaseLogUtil.e("R_LINKAGE_Config_SamlpeConfigHandler", "configId error");
        }
    }

    private void e(cvn cvnVar) {
        byte[] b = cvnVar.b();
        LogUtil.a("LINKAGE_Config_SamlpeConfigHandler", "mDataReceiveCallback: configDate = ", cvx.d(b));
        try {
            bmt.b b2 = new bmt().b(b, 0);
            long b3 = b2.b((byte) 1, -1L);
            int a2 = b2.a((byte) 2, -1);
            LogUtil.a("LINKAGE_Config_SamlpeConfigHandler", "handleSampleConfigInfo modifyTimestamp = ", Long.valueOf(b3), ", value =", Integer.valueOf(a2));
            njj.a("9002", Long.toString(cvnVar.a()), HiJsonUtil.e(e(String.valueOf(a2), b3)), new HiDataOperateListener() { // from class: kgl.5
                @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
                public void onResult(int i, Object obj) {
                    LogUtil.a("LINKAGE_Config_SamlpeConfigHandler", "onDataReceived setSampleConfig errorCode: ", Integer.valueOf(i), ", object: ", obj);
                }
            }, b3);
        } catch (bmk e) {
            ReleaseLogUtil.c("R_LINKAGE_Config_SamlpeConfigHandler", "handleSampleConfigInfo TlvException : ", ExceptionUtils.d(e));
        }
    }

    private static nji e(String str, long j) {
        nji njiVar = new nji();
        njiVar.c(str);
        njiVar.e(j);
        return njiVar;
    }
}
