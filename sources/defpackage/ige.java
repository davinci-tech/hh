package defpackage;

import android.content.Context;
import android.os.RemoteException;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.HiSampleConfigKey;
import com.huawei.hihealth.HiSampleConfigProcessOption;
import com.huawei.hihealth.IDataReadResultListener;
import com.huawei.hihealthservice.HiDataOperation;
import health.compact.a.HiCommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class ige extends HiDataOperation {
    private HiSampleConfigProcessOption b;
    private ikv d;
    private IDataReadResultListener e;

    ige(Context context) {
        super(context);
    }

    public boolean e(HiSampleConfigProcessOption hiSampleConfigProcessOption, IDataReadResultListener iDataReadResultListener, boolean z) throws RemoteException {
        if (iDataReadResultListener == null) {
            return false;
        }
        if (hiSampleConfigProcessOption == null) {
            ReleaseLogUtil.d("HiH_HiSampCfgGetOp", "sampleConfigGetOption is null!");
            igb.b(iDataReadResultListener, null, 7, 2);
            return false;
        }
        this.b = hiSampleConfigProcessOption;
        this.e = iDataReadResultListener;
        this.d = igd.b().e();
        int d = igm.e().d();
        if (d <= 0) {
            ReleaseLogUtil.d("HiH_HiSampCfgGetOp", "setSampleConfig userID <= 0 appID = ", Integer.valueOf(this.d.e()));
            igb.b(iDataReadResultListener, null, 15, 2);
            return false;
        }
        this.d.f(d);
        if (z && igd.b().d() == -1) {
            ReleaseLogUtil.d("HiH_HiSampCfgGetOp", "initialize appType is invalid errorCode is ", 17);
            igb.b(iDataReadResultListener, null, 17, 2);
            return false;
        }
        try {
            iwn.d(this.b);
            return true;
        } catch (iwt e) {
            ReleaseLogUtil.c("HiH_HiSampCfgGetOp", "validSampleConfigGetOption exception = ", e.getMessage());
            igb.b(iDataReadResultListener, null, 3, 2);
            return false;
        }
    }

    @Override // com.huawei.hihealthservice.HiDataOperation
    public void execute(ifq ifqVar) {
        IDataReadResultListener iDataReadResultListener;
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList(10);
        int i = 0;
        try {
            try {
                int e = this.d.e();
                if ((this.b.getProcessType() == 1 || this.b.getProcessType() == 3) && this.b.getPackageName() != null) {
                    e = igd.b().e(this.b.getPackageName());
                }
                List<Integer> e2 = e(this.b.getProcessType(), this.d.g(), e, this.b.getDeviceUniqueId());
                Iterator<HiSampleConfigKey> it = this.b.getSampleConfigKeyList().iterator();
                while (it.hasNext()) {
                    List<HiSampleConfig> a2 = ijq.e().a(e2, it.next());
                    if (!HiCommonUtil.d(a2)) {
                        arrayList.addAll(a2);
                    }
                }
                iDataReadResultListener = this.e;
            } catch (Exception e3) {
                ReleaseLogUtil.c("HiH_HiSampCfgGetOp", "execute exception = ", LogAnonymous.b((Throwable) e3));
                iDataReadResultListener = this.e;
                i = 5;
            }
            igb.b(iDataReadResultListener, arrayList, i, 2);
            ReleaseLogUtil.e("HiH_HiSampCfgGetOp", "exec eTm=", Long.valueOf(currentTimeMillis), ",ttlTm=", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        } catch (Throwable th) {
            igb.b(this.e, arrayList, 0, 2);
            throw th;
        }
    }

    private List<Integer> e(int i, int i2, int i3, String str) {
        ikv a2;
        ArrayList arrayList = new ArrayList(10);
        if (i == 0) {
            return iks.e().a(i2);
        }
        if (i == 1) {
            return iks.e().a(i3, i2);
        }
        if (i == 2) {
            return iks.e().b(i2, str);
        }
        if (i != 3 || (a2 = ikr.b(this.mContext).a(i3, i2, str)) == null) {
            return arrayList;
        }
        int b = a2.b();
        ArrayList arrayList2 = new ArrayList(1);
        arrayList2.add(Integer.valueOf(b));
        return arrayList2;
    }
}
