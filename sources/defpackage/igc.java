package defpackage;

import android.content.Context;
import android.os.RemoteException;
import com.huawei.hihealth.ICommonListener;
import com.huawei.hihealthservice.HiDataOperation;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;

/* loaded from: classes7.dex */
public class igc extends HiDataOperation {
    private ikv b;
    private ICommonListener d;
    private final ArrayList<Boolean> e;

    igc(Context context) {
        super(context);
        this.e = new ArrayList<>(10);
    }

    public boolean a(ICommonListener iCommonListener) throws RemoteException {
        if (iCommonListener == null) {
            return false;
        }
        this.b = igd.b().e();
        this.d = iCommonListener;
        if (igd.b().d() != -1) {
            return true;
        }
        ReleaseLogUtil.e("HiH_HiLogoutOperation", "hiLogout() appType is invalid");
        this.e.add(false);
        igb.c(this.d, 17, this.e);
        return false;
    }

    @Override // com.huawei.hihealthservice.HiDataOperation
    public void execute(ifq ifqVar) {
        ReleaseLogUtil.e("HiH_HiLogoutOperation", "hiLogout()");
        String a2 = igm.e().a();
        if (igm.e().hiLogout(this.b) > 0) {
            igm.e().e(a2);
            ima.a().b(a2);
            iwe.e(this.mContext, a2);
            this.e.add(true);
            igb.b(this.d, 0, this.e);
            return;
        }
        ReleaseLogUtil.e("HiH_HiLogoutOperation", "hiLogout() fail");
        this.e.add(false);
        igb.c(this.d, 13, this.e);
    }
}
