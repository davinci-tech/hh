package defpackage;

import android.content.Context;
import android.os.RemoteException;
import com.huawei.hihealth.HiDataAggregateProOption;
import com.huawei.hihealth.IAggregateListenerEx;
import com.huawei.hihealth.util.HiDivideUtil;
import com.huawei.hihealthservice.HiDataOperation;
import com.huawei.hihealthservice.auth.HiAuthException;
import com.huawei.hihealthservice.auth.HiAuthManager;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class ifg extends HiDataOperation {

    /* renamed from: a, reason: collision with root package name */
    private boolean f13334a;
    private IAggregateListenerEx b;
    private int c;
    private int d;
    private List<HiDataAggregateProOption> e;
    private int i;
    private String j;

    ifg(Context context) {
        super(context);
    }

    public boolean d(List list, IAggregateListenerEx iAggregateListenerEx, boolean z) throws RemoteException {
        if (list == null) {
            LogUtil.h("HiH_HiDataAggProEx", "aggregateProOptions is null");
            igb.e(iAggregateListenerEx, null, 7, 2);
            return false;
        }
        LogUtil.c("HiH_HiDataAggProEx", "initialize aggregateProOptions = ", list);
        ArrayList arrayList = new ArrayList(10);
        this.e = arrayList;
        try {
            arrayList.addAll(list);
            this.b = iAggregateListenerEx;
            this.j = ivw.a(this.mContext);
            this.c = igd.b().d(this.j);
            this.i = igm.e().d();
            this.d = igd.b().b(this.j);
            this.f13334a = z;
            if (this.i <= 0) {
                LogUtil.h("HiH_HiDataAggProEx", "initialize userID <= 0 ,appID = ", Integer.valueOf(this.c));
                igb.e(iAggregateListenerEx, null, 24, 2);
                return false;
            }
            if (z && !e()) {
                return false;
            }
            try {
                Iterator<HiDataAggregateProOption> it = this.e.iterator();
                while (it.hasNext()) {
                    iwo.a(it.next().getAggregateOption());
                }
                return true;
            } catch (iwt e) {
                ReleaseLogUtil.c("HiH_HiDataAggProEx", "initialize HiValidException = ", e.getMessage());
                igb.e(iAggregateListenerEx, null, 3, 2);
                return false;
            }
        } catch (ClassCastException e2) {
            ReleaseLogUtil.c("HiH_HiDataAggProEx", "initialize ClassCastException e = ", e2.getMessage());
            throw new RemoteException(e2.getMessage());
        }
    }

    private boolean e() {
        int c;
        int i = this.d;
        if (i == -1) {
            LogUtil.a("HiH_HiDataAggProEx", "initialize appType is invalid");
            igb.e(this.b, null, 17, 2);
            return false;
        }
        if (i == 0 || (c = igm.e().c(this.c)) == 0) {
            return true;
        }
        igb.e(this.b, null, c, 2);
        return false;
    }

    @Override // com.huawei.hihealthservice.HiDataOperation
    public void execute(ifq ifqVar) {
        long currentTimeMillis = System.currentTimeMillis();
        int i = 8;
        try {
            try {
                ikv ikvVar = new ikv();
                ikvVar.f(this.i);
                ikvVar.a(this.c);
                char c = 6;
                char c2 = 3;
                LogUtil.c("HiH_HiDataAggProEx", "execute checkAppType  ", Integer.valueOf(this.d), " appID = ", Integer.valueOf(this.c), " userID = ", Integer.valueOf(this.i));
                int i2 = 0;
                while (i2 < this.e.size()) {
                    HiDataAggregateProOption hiDataAggregateProOption = this.e.get(i2);
                    if (hiDataAggregateProOption != null) {
                        Object[] objArr = new Object[i];
                        objArr[0] = "appTp=";
                        objArr[1] = Integer.valueOf(this.d);
                        objArr[2] = " appId=";
                        objArr[c2] = Integer.valueOf(this.c);
                        objArr[4] = " id=";
                        objArr[5] = Integer.valueOf(this.i);
                        objArr[c] = " aggProOpt=";
                        objArr[7] = hiDataAggregateProOption;
                        ReleaseLogUtil.e("HiH_HiDataAggProEx", objArr);
                        if (this.f13334a && this.d != 0) {
                            HiAuthManager.getInstance().checkReadAuth(this.c, this.i, hiDataAggregateProOption.getAggregateOption().getType());
                        }
                        HiDivideUtil.d(i2, isl.b(this.mContext).readAggregateData(ikvVar, hiDataAggregateProOption.getAggregateOption(), ifl.d(this.f13334a).d(hiDataAggregateProOption.getPackageName()).e(hiDataAggregateProOption.getDataFilter()).a(hiDataAggregateProOption.getWithoutDefaultZero()).b()), this.b);
                    }
                    i2++;
                    i = 8;
                    c = 6;
                    c2 = 3;
                }
            } catch (HiAuthException e) {
                ReleaseLogUtil.c("HiH_HiDataAggProEx", "execute HiAuthException e = ", e.getMessage());
                igb.e(this.b, null, 8, 2);
            } catch (Exception e2) {
                ReleaseLogUtil.c("HiH_HiDataAggProEx", "execute Exception", LogAnonymous.b((Throwable) e2));
                igb.e(this.b, null, 5, 2);
            }
            LogUtil.a("HiH_HiDataAggProEx", "execute end totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        } finally {
            igb.e(this.b, null, 0, 2);
        }
    }
}
