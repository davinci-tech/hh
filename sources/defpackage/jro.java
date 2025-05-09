package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.util.List;

/* loaded from: classes5.dex */
public class jro {
    public static int a(byte[] bArr) throws cwg {
        return b(jru.b(bArr));
    }

    private static int b(cwe cweVar) {
        if (cweVar == null) {
            return 0;
        }
        List<cwd> e = cweVar.e();
        int size = e.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            String c = e.get(i2).c();
            if (CommonUtil.w(e.get(i2).e()) == 1) {
                i = CommonUtil.w(c);
            } else {
                LogUtil.h("TlvFitnessUtil", "unTlvPackDeviceSyncReport default");
            }
        }
        return i;
    }

    public static long e(byte[] bArr) throws cwg {
        if (koq.b(jru.b(bArr).e(), 1)) {
            LogUtil.h("TlvFitnessUtil", "getCoreSleepTimeStamps tlvs is error");
            return 0L;
        }
        return CommonUtil.w(r2.get(1).c());
    }
}
