package defpackage;

import android.content.Context;
import android.os.RemoteException;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealthkit.context.OutOfBandData;
import com.huawei.hihealthkit.context.QuickAppInfo;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes7.dex */
public class inr {

    /* renamed from: a, reason: collision with root package name */
    private ima f13487a = ima.a();
    private Context b;
    private int c;
    private ijm d;
    private int e;

    public inr(Context context, int i, int i2) {
        this.b = context;
        this.d = ijm.e(context);
        this.e = i;
        this.c = i2;
    }

    public int c(OutOfBandData outOfBandData) throws RemoteException {
        if (!b(outOfBandData)) {
            return 1001;
        }
        QuickAppInfo quickAppInfo = (QuickAppInfo) outOfBandData;
        if (iqw.e(quickAppInfo)) {
            return c(this.f13487a.d(quickAppInfo.getPackageName()), quickAppInfo.getPackageName(), this.c, this.e) ? 0 : 1001;
        }
        ReleaseLogUtil.d("R_QuickAppPermissionChecker", "scope deny");
        LogUtil.c("QuickAppPermissionChecker", "quickApp", quickAppInfo.getPackageName());
        return 1001;
    }

    private boolean b(OutOfBandData outOfBandData) {
        return outOfBandData instanceof QuickAppInfo;
    }

    private boolean c(int i, String str, int i2, int i3) {
        if (i2 == 0) {
            return true;
        }
        if (i2 != 1) {
            if (i2 != 2) {
                return false;
            }
            return this.d.d(i, HiHealthDataType.c(i3));
        }
        if ("com.huawei.health".equals(iwi.a(this.b)) && "com.huawei.healthsport.h5-bloodsugar-export".equals(str)) {
            LogUtil.a("QuickAppPermissionChecker", "permission owned by default");
            return true;
        }
        return this.d.c(i, HiHealthDataType.c(i3));
    }
}
