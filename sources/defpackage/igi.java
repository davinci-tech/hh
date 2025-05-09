package defpackage;

import android.content.Context;
import android.os.RemoteException;
import com.huawei.hihealth.HiSportStatDataAggregateOption;
import com.huawei.hihealth.IAggregateListener;
import com.huawei.hihealth.util.HiDivideUtil;
import com.huawei.hihealthservice.HiDataOperation;
import com.huawei.hihealthservice.auth.HiAuthException;
import com.huawei.hihealthservice.auth.HiAuthManager;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes7.dex */
public class igi extends HiDataOperation {

    /* renamed from: a, reason: collision with root package name */
    private int f13364a;
    private int b;
    private IAggregateListener c;
    private HiSportStatDataAggregateOption d;
    private int e;

    igi(Context context) {
        super(context);
    }

    public boolean a(HiSportStatDataAggregateOption hiSportStatDataAggregateOption, IAggregateListener iAggregateListener, boolean z) throws RemoteException {
        if (hiSportStatDataAggregateOption == null) {
            LogUtil.h("HiH_HiSportStatDataAggregateOperation", "aggregateOption is null");
            igb.d(iAggregateListener, null, 7, 2);
            return false;
        }
        this.d = hiSportStatDataAggregateOption;
        this.c = iAggregateListener;
        String a2 = ivw.a(this.mContext);
        this.f13364a = igd.b().d(a2);
        this.e = igm.e().d();
        this.b = igd.b().b(a2);
        if (this.e <= 0) {
            LogUtil.h("HiH_HiSportStatDataAggregateOperation", "initialize userID <= 0 ,appID = ", Integer.valueOf(this.f13364a));
            igb.d(iAggregateListener, null, 24, 2);
            return false;
        }
        if (z && !d()) {
            return false;
        }
        try {
            iwo.d(this.d);
            return true;
        } catch (iwt e) {
            ReleaseLogUtil.c("HiH_HiSportStatDataAggregateOperation", "initialize HiValidException = ", e.getMessage());
            igb.d(this.c, null, 3, 2);
            return false;
        }
    }

    private boolean d() {
        int i = this.b;
        if (i == -1) {
            LogUtil.a("HiH_HiSportStatDataAggregateOperation", "initialize appType is invalid");
            igb.d(this.c, null, 17, 2);
            return false;
        }
        if (i == 0) {
            return true;
        }
        int c = igm.e().c(this.f13364a);
        if (c != 0) {
            igb.d(this.c, null, c, 2);
            return false;
        }
        try {
            HiAuthManager.getInstance().checkReadAuth(this.f13364a, this.e, this.d.getType());
            return true;
        } catch (HiAuthException e) {
            ReleaseLogUtil.c("HiH_HiSportStatDataAggregateOperation", "initialize HiAuthException e = ", e.getMessage());
            igb.d(this.c, null, 8, 2);
            return false;
        }
    }

    @Override // com.huawei.hihealthservice.HiDataOperation
    public void execute(ifq ifqVar) {
        long currentTimeMillis = System.currentTimeMillis();
        LogUtil.a("HiH_HiSportStatDataAggregateOperation", "execute appType ", Integer.valueOf(this.b), " appId ", Integer.valueOf(this.f13364a), " id ", Integer.valueOf(this.e), " aggregateOption is ", this.d);
        try {
            try {
                HiDivideUtil.d(isl.b(this.mContext).readSportStatAggregateData(new ikv(this.f13364a, this.e), this.d), this.c);
            } catch (Exception e) {
                ReleaseLogUtil.c("HiH_HiSportStatDataAggregateOperation", "execute Exception: ", LogAnonymous.b((Throwable) e));
                igb.d(this.c, null, 5, 2);
            }
            LogUtil.a("HiH_HiSportStatDataAggregateOperation", "execute end time = ", Long.valueOf(currentTimeMillis), ", totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        } finally {
            igb.d(this.c, null, 0, 2);
        }
    }
}
