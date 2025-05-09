package defpackage;

import android.text.TextUtils;
import com.huawei.devicesdk.command.CommandBase;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;

/* loaded from: classes3.dex */
public class bgk implements CommandBase {
    @Override // com.huawei.devicesdk.command.CommandBase
    public boolean handleReceivedData(String str, biu biuVar) {
        if (biuVar == null || biuVar.a() == null || biuVar.a().length < 2) {
            LogUtil.a("ConnectParameterReportCommand", "dataContents is not valid");
            return true;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("ConnectParameterReportCommand", "handleReceivedData mac is null");
            return true;
        }
        byte[] a2 = biuVar.a();
        if (1 == a2[0] && 17 == a2[1]) {
            String d = blq.d(a2);
            if (d == null || d.length() < 8) {
                LogUtil.a("ConnectParameterReportCommand", "getMtsInterval is not valid data");
                return false;
            }
            try {
                int parseInt = Integer.parseInt(d.substring(8, d.length()), 16);
                biw c = bjx.a().c(str);
                if (c != null) {
                    c.g(parseInt);
                } else {
                    c = new biw();
                    c.g(parseInt);
                }
                bjx.a().a(str, c);
                ReleaseLogUtil.b("DEVMGR_ConnectParameterReportCommand", "handleReceivedData interval : ", Integer.valueOf(parseInt));
                return false;
            } catch (NumberFormatException unused) {
                LogUtil.e("ConnectParameterReportCommand", "getMTSInterval NumberFormatException");
            }
        }
        return true;
    }
}
