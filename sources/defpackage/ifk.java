package defpackage;

import android.content.Context;
import android.os.RemoteException;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.IDataReadResultListener;
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
public class ifk extends HiDataOperation {

    /* renamed from: a, reason: collision with root package name */
    private int f13338a;
    private int b;
    private boolean c;
    private String d;
    private int e;
    private List<HiDataReadProOption> h;
    private IDataReadResultListener i;
    private int j;

    ifk(Context context) {
        super(context);
    }

    public boolean d(List list, IDataReadResultListener iDataReadResultListener, boolean z) throws RemoteException {
        if (list == null) {
            LogUtil.h("HiH_HiDataRdExOp", "readOptions is null");
            igb.b(iDataReadResultListener, null, 7, 2);
            return false;
        }
        ArrayList arrayList = new ArrayList(10);
        this.h = arrayList;
        try {
            arrayList.addAll(list);
            this.c = z;
            this.i = iDataReadResultListener;
            this.d = ivw.a(this.mContext);
            this.b = igd.b().d(this.d);
            this.j = igm.e().d();
            int b = igd.b().b(this.d);
            this.f13338a = b;
            this.e = this.b;
            LogUtil.a("HiH_HiDataRdExOp", "AppType  ", Integer.valueOf(b), " appID = ", Integer.valueOf(this.b), " id = ", Integer.valueOf(this.j), " readOptions = ", this.h);
            if (this.j <= 0) {
                LogUtil.h("HiH_HiDataRdExOp", "initialize userID <= 0 ,appID = ", Integer.valueOf(this.b));
                igb.b(this.i, null, 24, 2);
                return false;
            }
            if (z && !c()) {
                return false;
            }
            try {
                Iterator<HiDataReadProOption> it = this.h.iterator();
                while (it.hasNext()) {
                    iwr.c(it.next().getDataReadOption());
                }
                return true;
            } catch (iwt e) {
                ReleaseLogUtil.c("HiH_HiDataRdExOp", "readHiHealthData() HiValidException = ", e.getMessage());
                igb.b(this.i, null, 3, 2);
                return false;
            }
        } catch (ClassCastException e2) {
            ReleaseLogUtil.c("HiH_HiDataRdExOp", "initialize ClassCastException e = ", e2.getMessage());
            throw new RemoteException(e2.getMessage());
        }
    }

    private boolean c() {
        int c;
        int i = this.f13338a;
        if (i == -1) {
            LogUtil.a("HiH_HiDataRdExOp", "initialize appType is invalid");
            igb.b(this.i, null, 17, 2);
            return false;
        }
        if (i == 0 || (c = igm.e().c(this.b)) == 0) {
            return true;
        }
        igb.b(this.i, null, c, 2);
        return false;
    }

    @Override // com.huawei.hihealthservice.HiDataOperation
    public void execute(ifq ifqVar) {
        IDataReadResultListener iDataReadResultListener;
        int i;
        long currentTimeMillis = System.currentTimeMillis();
        char c = 5;
        int i2 = 8;
        try {
            try {
                try {
                    ikv ikvVar = new ikv(this.e, this.j);
                    isl b = isl.b(this.mContext);
                    int i3 = 0;
                    while (i3 < this.h.size()) {
                        HiDataReadProOption hiDataReadProOption = this.h.get(i3);
                        if (hiDataReadProOption != null) {
                            Object[] objArr = new Object[i2];
                            objArr[0] = "appType ";
                            objArr[1] = Integer.valueOf(this.f13338a);
                            objArr[2] = " appId ";
                            objArr[3] = Integer.valueOf(this.b);
                            objArr[4] = " id ";
                            objArr[c] = Integer.valueOf(this.j);
                            objArr[6] = " dataReadOption is ";
                            objArr[7] = hiDataReadProOption;
                            ReleaseLogUtil.e("HiH_HiDataRdExOp", objArr);
                            if (this.f13338a != 0) {
                                HiAuthManager.getInstance().checkReadAuth(this.b, this.j, hiDataReadProOption.getDataReadOption().getType());
                            }
                            int alignType = hiDataReadProOption.getDataReadOption().getAlignType();
                            ifl b2 = ifl.d(this.c).d(hiDataReadProOption.getPackageName()).b(Integer.valueOf(hiDataReadProOption.getSequenceDataType())).e(hiDataReadProOption.getDataFilter()).a(hiDataReadProOption.getMetadataFilter()).b(hiDataReadProOption.isLastDayDetail()).b();
                            if (alignType > 0) {
                                b.readDataByAlignType(alignType, ikvVar, hiDataReadProOption.getDataReadOption(), this.i);
                            } else {
                                b.readDataByType(hiDataReadProOption.getDataReadOption(), ikvVar, b2, this.i);
                            }
                        }
                        i3++;
                        c = 5;
                        i2 = 8;
                    }
                } catch (HiAuthException e) {
                    ReleaseLogUtil.c("HiH_HiDataRdExOp", "execute HiAuthException e = ", e.getMessage());
                    iDataReadResultListener = this.i;
                    i = 8;
                    igb.b(iDataReadResultListener, null, i, 2);
                    LogUtil.a("HiH_HiDataRdExOp", "execute end time = ", Long.valueOf(currentTimeMillis), ", totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                }
            } catch (Exception e2) {
                ReleaseLogUtil.c("HiH_HiDataRdExOp", "execute exception = ", LogAnonymous.b((Throwable) e2));
                iDataReadResultListener = this.i;
                i = 5;
                igb.b(iDataReadResultListener, null, i, 2);
                LogUtil.a("HiH_HiDataRdExOp", "execute end time = ", Long.valueOf(currentTimeMillis), ", totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            }
            LogUtil.a("HiH_HiDataRdExOp", "execute end time = ", Long.valueOf(currentTimeMillis), ", totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        } finally {
            igb.b(this.i, null, 0, 2);
        }
    }
}
