package defpackage;

import android.content.Context;
import android.os.RemoteException;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.IDataReadResultListener;
import com.huawei.hihealthservice.HiDataOperation;
import com.huawei.hihealthservice.auth.HiAuthException;
import com.huawei.hihealthservice.auth.HiAuthManager;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes7.dex */
public class ifs extends HiDataOperation {

    /* renamed from: a, reason: collision with root package name */
    private int f13344a;
    private ifl b;
    private int c;
    private int d;
    private String e;
    private HiDataReadOption g;
    private int h;
    private IDataReadResultListener j;

    ifs(Context context) {
        super(context);
    }

    public boolean e(HiDataReadOption hiDataReadOption, IDataReadResultListener iDataReadResultListener, ifl iflVar) throws RemoteException {
        if (hiDataReadOption == null || iflVar == null) {
            LogUtil.h("HiH_HiDataReadOp", "readOption or extendOption is null");
            igb.b(iDataReadResultListener, null, 7, 2);
            return false;
        }
        this.b = iflVar;
        this.g = hiDataReadOption;
        this.j = iDataReadResultListener;
        this.e = ivw.a(this.mContext);
        this.f13344a = igd.b().d(this.e);
        this.h = igm.e().d();
        this.c = igd.b().b(this.e);
        this.d = this.f13344a;
        if (iflVar.i() != null) {
            this.d = igd.b().e(iflVar.i());
        }
        ReleaseLogUtil.e("HiH_HiDataReadOp", "AppTp:", Integer.valueOf(this.c), ",AppId=", Integer.valueOf(this.f13344a), ",id=", Integer.valueOf(this.h), ",readOpt=", this.g, ",extOpt=", iflVar);
        if (this.h <= 0) {
            LogUtil.h("HiH_HiDataReadOp", "initialize userID <= 0 ,appID = ", Integer.valueOf(this.f13344a));
            igb.b(this.j, null, 24, 2);
            return false;
        }
        if (iflVar.g() && !e()) {
            return false;
        }
        try {
            iwr.c(this.g);
            return true;
        } catch (iwt e) {
            ReleaseLogUtil.c("HiH_HiDataReadOp", "readHiHealthData() HiValidException = ", e.getMessage());
            igb.b(this.j, null, 3, 2);
            return false;
        }
    }

    private boolean e() {
        int i = this.c;
        if (i == -1) {
            LogUtil.a("HiH_HiDataReadOp", "initialize appType is invalid");
            igb.b(this.j, null, 17, 2);
            return false;
        }
        if (i == 0) {
            return true;
        }
        int c = igm.e().c(this.f13344a);
        if (c != 0) {
            igb.b(this.j, null, c, 2);
            return false;
        }
        try {
            HiAuthManager.getInstance().checkReadAuth(this.f13344a, this.h, this.g.getType());
            return true;
        } catch (HiAuthException e) {
            ReleaseLogUtil.c("HiH_HiDataReadOp", "checkReadAppAuth HiAuthException e = ", e.getMessage(), " appID = ", Integer.valueOf(this.f13344a), " who = ", Integer.valueOf(this.h));
            igb.b(this.j, null, 8, 2);
            return false;
        }
    }

    @Override // com.huawei.hihealthservice.HiDataOperation
    public void execute(ifq ifqVar) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            try {
                ikv ikvVar = new ikv(this.d, this.h);
                int alignType = this.g.getAlignType();
                isl b = isl.b(this.mContext);
                if (alignType > 0) {
                    b.readDataByAlignType(alignType, ikvVar, this.g, this.j);
                } else {
                    b.readDataByType(this.g, ikvVar, this.b, this.j);
                }
            } catch (Exception e) {
                ReleaseLogUtil.c("HiH_HiDataReadOp", "execute exception = ", LogAnonymous.b((Throwable) e));
                igb.b(this.j, null, 5, 2);
            }
            LogUtil.a("HiH_HiDataReadOp", "execute end time = ", Long.valueOf(currentTimeMillis), ", totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        } finally {
            igb.b(this.j, null, 0, 2);
        }
    }
}
