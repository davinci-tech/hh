package defpackage;

import android.content.Context;
import android.os.RemoteException;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.IAggregateListener;
import com.huawei.hihealth.util.HiDivideUtil;
import com.huawei.hihealthservice.HiDataOperation;
import com.huawei.hihealthservice.auth.HiAuthException;
import com.huawei.hihealthservice.auth.HiAuthManager;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Arrays;

/* loaded from: classes7.dex */
public class ifj extends HiDataOperation {

    /* renamed from: a, reason: collision with root package name */
    private int f13337a;
    private IAggregateListener b;
    private HiAggregateOption c;
    private int d;
    private int e;
    private String f;
    private ifl h;
    private int j;

    ifj(Context context) {
        super(context);
    }

    public boolean a(HiAggregateOption hiAggregateOption, IAggregateListener iAggregateListener, ifl iflVar) throws RemoteException {
        if (hiAggregateOption == null || iflVar == null) {
            LogUtil.h("HiH_HiDataAggregateOperation", "aggregateOption or extendOption is null");
            igb.d(iAggregateListener, null, 7, 2);
            return false;
        }
        this.h = iflVar;
        this.c = hiAggregateOption;
        this.b = iAggregateListener;
        this.f = ivw.a(this.mContext);
        this.e = igd.b().d(this.f);
        this.j = igm.e().d();
        this.f13337a = igd.b().b(this.f);
        this.d = this.e;
        if (iflVar.i() != null) {
            this.d = igd.b().e(iflVar.i());
        }
        LogUtil.a("HiH_HiDataAggregateOperation", "appType ", Integer.valueOf(this.f13337a), " appId ", Integer.valueOf(this.e), " id ", Integer.valueOf(this.j), " aggregateOption is ", this.c, ", extendOption is ", iflVar);
        if (this.j <= 0) {
            LogUtil.h("HiH_HiDataAggregateOperation", "initialize userID <= 0 ,appID = ", Integer.valueOf(this.e));
            igb.d(iAggregateListener, null, 24, 2);
            return false;
        }
        if (iflVar.g() && !d()) {
            return false;
        }
        try {
            iwo.a(this.c);
            return true;
        } catch (iwt e) {
            ReleaseLogUtil.c("HiH_HiDataAggregateOperation", "initialize HiValidException = ", e.getMessage());
            igb.d(this.b, null, 3, 2);
            return false;
        }
    }

    private boolean d() {
        int i = this.f13337a;
        if (i == -1) {
            LogUtil.a("HiH_HiDataAggregateOperation", "initialize appType is invalid");
            igb.d(this.b, null, 17, 2);
            return false;
        }
        if (i == 0) {
            return true;
        }
        int c = igm.e().c(this.e);
        if (c != 0) {
            igb.d(this.b, null, c, 2);
            return false;
        }
        try {
            HiAuthManager.getInstance().checkReadAuth(this.e, this.j, this.c.getType());
            return true;
        } catch (HiAuthException e) {
            ReleaseLogUtil.c("HiH_HiDataAggregateOperation", "initialize HiAuthException e = ", e.getMessage());
            igb.d(this.b, null, 8, 2);
            return false;
        }
    }

    @Override // com.huawei.hihealthservice.HiDataOperation
    public void execute(ifq ifqVar) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            try {
                if (c(this.c)) {
                    isq.a(this.c.getStartTime());
                    iwe.q(this.mContext);
                }
                HiDivideUtil.d(isl.b(this.mContext).readAggregateData(new ikv(this.d, this.j), this.c, this.h), this.b);
            } catch (Exception e) {
                ReleaseLogUtil.c("HiH_HiDataAggregateOperation", "execute Exception: ", LogAnonymous.b((Throwable) e));
                igb.d(this.b, null, 5, 2);
            }
            LogUtil.a("HiH_HiDataAggregateOperation", "execute end time = ", Long.valueOf(currentTimeMillis), ", totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        } finally {
            igb.d(this.b, null, 0, 2);
        }
    }

    private boolean c(HiAggregateOption hiAggregateOption) {
        return iwe.c(this.mContext) && d(hiAggregateOption);
    }

    private boolean d(HiAggregateOption hiAggregateOption) {
        Arrays.sort(hiAggregateOption.getType());
        return Arrays.binarySearch(hiAggregateOption.getType(), 901) >= 0;
    }
}
