package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes5.dex */
public class kei {
    public static int b(cwe cweVar) {
        int i = 0;
        if (cweVar == null) {
            ReleaseLogUtil.d("BTSYNC_AlarmData_FitnessUnTlvSample", "unTlvGetBloodOxygenDownSampleFrameCount tlvFather is null");
            return 0;
        }
        for (cwd cwdVar : cweVar.e()) {
            if (CommonUtil.w(cwdVar.e()) == 3) {
                i = CommonUtil.w(cwdVar.c());
            } else {
                LogUtil.c("FitnessUnTlvSample", "unTlvGetBloodOxygenDownSampleFrameCount else");
            }
        }
        LogUtil.a("FitnessUnTlvSample", "unTlvGetBloodOxygenDownSampleFrameCount count: ", Integer.valueOf(i));
        return i;
    }
}
