package defpackage;

import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes5.dex */
public class kea {
    public static int a(byte[] bArr) throws cwg {
        String d = cvx.d(bArr);
        if (d == null) {
            ReleaseLogUtil.d("BTSYNC_AlarmData_FitnessUnPackage", "unGetBloodOxygenDownSampleCount tlv info is null");
            return 0;
        }
        return kei.b(new cwl().a(d.substring(4, d.length())));
    }
}
