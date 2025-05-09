package defpackage;

import com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.initese.tsm.operator.TsmOperatorResponse;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class jmo implements TsmOperatorResponse {
    private int b = 100000;
    private jma d;

    public jmo() {
        jma jmaVar = new jma();
        this.d = jmaVar;
        jmaVar.a(this);
    }

    private int a(jmp jmpVar) {
        if (jmpVar == null) {
            LogUtil.h("TsmOperator", "checkCommonRequestParams request is null.");
            return 100001;
        }
        if (jmpVar.b() == null) {
            LogUtil.h("TsmOperator", "checkCommonRequestParams request.getCplc() is null.");
            return 100008;
        }
        if (jmpVar.c() == null) {
            LogUtil.h("TsmOperator", "checkCommonRequestParams request.getServiceId() is null.");
            return 100003;
        }
        if (jmpVar.a() != null) {
            return -1;
        }
        LogUtil.h("TsmOperator", "checkCommonRequestParams request.getFunCallId() is null.");
        return 100004;
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.initese.tsm.operator.TsmOperatorResponse
    public void onOperatorSuccess(String str) {
        this.b = 100000;
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.initese.tsm.operator.TsmOperatorResponse
    public void onOperatorFailure(int i, Error error) {
        this.b = i;
    }

    public int b(jmp jmpVar) {
        int a2 = a(jmpVar);
        if (a2 != -1) {
            return a2;
        }
        this.d.e(jmpVar);
        return this.b;
    }
}
