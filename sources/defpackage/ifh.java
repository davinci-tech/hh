package defpackage;

import android.content.Context;
import android.os.RemoteException;
import com.huawei.hihealth.HiAggregateOption;
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
public class ifh extends HiDataOperation {

    /* renamed from: a, reason: collision with root package name */
    private int f13335a;
    private int b;
    private String c;
    private List<HiAggregateOption> d;
    private IAggregateListenerEx e;
    private int f;

    ifh(Context context) {
        super(context);
    }

    public boolean a(List list, IAggregateListenerEx iAggregateListenerEx, boolean z) throws RemoteException {
        if (list == null) {
            LogUtil.h("HiH_HiDataAggExOp", "aggregateOptions is null");
            igb.e(iAggregateListenerEx, null, 7, 2);
            return false;
        }
        LogUtil.c("HiH_HiDataAggExOp", "initialize aggregateOptions = ", list);
        ArrayList arrayList = new ArrayList(10);
        this.d = arrayList;
        try {
            arrayList.addAll(list);
            this.e = iAggregateListenerEx;
            this.c = ivw.a(this.mContext);
            this.f13335a = igd.b().d(this.c);
            this.f = igm.e().d();
            this.b = igd.b().b(this.c);
            if (this.f <= 0) {
                LogUtil.h("HiH_HiDataAggExOp", "initialize userID <= 0 ,appID = ", Integer.valueOf(this.f13335a));
                igb.e(iAggregateListenerEx, null, 24, 2);
                return false;
            }
            if (z && !e()) {
                return false;
            }
            try {
                Iterator<HiAggregateOption> it = this.d.iterator();
                while (it.hasNext()) {
                    iwo.a(it.next());
                }
                return true;
            } catch (iwt e) {
                ReleaseLogUtil.c("HiH_HiDataAggExOp", "initialize HiValidException = ", e.getMessage());
                igb.e(iAggregateListenerEx, null, 3, 2);
                return false;
            }
        } catch (ClassCastException e2) {
            ReleaseLogUtil.c("HiH_HiDataAggExOp", "initialize ClassCastException e = ", e2.getMessage());
            throw new RemoteException(e2.getMessage());
        }
    }

    private boolean e() {
        int c;
        int i = this.b;
        if (i == -1) {
            LogUtil.a("HiH_HiDataAggExOp", "initialize appType is invalid");
            igb.e(this.e, null, 17, 2);
            return false;
        }
        if (i == 0 || (c = igm.e().c(this.f13335a)) == 0) {
            return true;
        }
        igb.e(this.e, null, c, 2);
        return false;
    }

    @Override // com.huawei.hihealthservice.HiDataOperation
    public void execute(ifq ifqVar) {
        long currentTimeMillis = System.currentTimeMillis();
        int i = 8;
        try {
            try {
                ikv ikvVar = new ikv();
                ikvVar.f(this.f);
                ikvVar.a(this.f13335a);
                char c = 6;
                char c2 = 3;
                LogUtil.c("HiH_HiDataAggExOp", "execute checkAppType  ", Integer.valueOf(this.b), " appID = ", Integer.valueOf(this.f13335a), " userID = ", Integer.valueOf(this.f));
                int i2 = 0;
                while (i2 < this.d.size()) {
                    HiAggregateOption hiAggregateOption = this.d.get(i2);
                    Object[] objArr = new Object[i];
                    objArr[0] = "appTp ";
                    objArr[1] = Integer.valueOf(this.b);
                    objArr[2] = " appId ";
                    objArr[c2] = Integer.valueOf(this.f13335a);
                    objArr[4] = " id ";
                    objArr[5] = Integer.valueOf(this.f);
                    objArr[c] = " aggOpt=";
                    objArr[7] = hiAggregateOption;
                    ReleaseLogUtil.e("HiH_HiDataAggExOp", objArr);
                    if (this.b != 0) {
                        HiAuthManager.getInstance().checkReadAuth(this.f13335a, this.f, hiAggregateOption.getType());
                    }
                    HiDivideUtil.d(i2, isl.b(this.mContext).readAggregateData(ikvVar, hiAggregateOption, null), this.e);
                    i2++;
                    i = 8;
                    c = 6;
                    c2 = 3;
                }
            } catch (HiAuthException e) {
                ReleaseLogUtil.c("HiH_HiDataAggExOp", "execute HiAuthException e = ", e.getMessage());
                igb.e(this.e, null, 8, 2);
            } catch (Exception e2) {
                ReleaseLogUtil.c("HiH_HiDataAggExOp", "execute Exception", LogAnonymous.b((Throwable) e2));
                igb.e(this.e, null, 5, 2);
            }
            LogUtil.a("HiH_HiDataAggExOp", "execute end totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        } finally {
            igb.e(this.e, null, 0, 2);
        }
    }
}
