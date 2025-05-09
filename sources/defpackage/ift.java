package defpackage;

import android.content.Context;
import android.os.RemoteException;
import com.huawei.hihealth.HiDataSourceFetchOption;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.IDataClientListener;
import com.huawei.hihealthservice.HiDataOperation;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class ift extends HiDataOperation {

    /* renamed from: a, reason: collision with root package name */
    private String f13345a;
    private int b;
    private int c;
    private IDataClientListener d;
    private HiDataSourceFetchOption e;
    private int j;

    ift(Context context) {
        super(context);
    }

    public boolean c(HiDataSourceFetchOption hiDataSourceFetchOption, IDataClientListener iDataClientListener, boolean z) throws RemoteException {
        if (iDataClientListener == null) {
            LogUtil.h("HiH_HiDataSourceFetchOperation", "listener is null");
            return false;
        }
        if (hiDataSourceFetchOption == null) {
            LogUtil.h("HiH_HiDataSourceFetchOperation", "fetchOption is null");
            igb.c(iDataClientListener, (List) null);
            return false;
        }
        this.d = iDataClientListener;
        this.e = hiDataSourceFetchOption;
        this.f13345a = ivw.a(this.mContext);
        this.b = igd.b().d(this.f13345a);
        this.j = igm.e().d();
        this.c = igd.b().b(this.f13345a);
        if (this.j <= 0) {
            LogUtil.h("HiH_HiDataSourceFetchOperation", "fetchDataSource initialize userID <= 0 ,appID = ", Integer.valueOf(this.b));
            igb.c(iDataClientListener, (List) null);
            return false;
        }
        if (this.e.getFetchType() == null) {
            LogUtil.h("HiH_HiDataSourceFetchOperation", "FetchType is null!");
            igb.c(iDataClientListener, (List) null);
            return false;
        }
        if (this.e.getFetchType().intValue() == 1 && (this.e.getClientIds() == null || this.e.getClientIds().length == 0)) {
            LogUtil.h("HiH_HiDataSourceFetchOperation", "FetchType is READ_CLIENT but clientId list is empty");
            igb.c(iDataClientListener, (List) null);
            return false;
        }
        if (!z || a()) {
            return true;
        }
        LogUtil.h("HiH_HiDataSourceFetchOperation", "Check app auth is false!");
        return false;
    }

    private boolean a() {
        if (this.c != -1) {
            return true;
        }
        LogUtil.h("HiH_HiDataSourceFetchOperation", "initialize appType is invalid");
        igb.c(this.d, (List) null);
        return false;
    }

    @Override // com.huawei.hihealthservice.HiDataOperation
    public void execute(ifq ifqVar) {
        long currentTimeMillis = System.currentTimeMillis();
        List<HiHealthClient> healthClientListByUserId = isb.e(this.mContext).getHealthClientListByUserId(this.j, this.e);
        igb.c(this.d, healthClientListByUserId);
        LogUtil.c("HiH_HiDataSourceFetchOperation", "fetchDataSource end, clientList is ", healthClientListByUserId);
        LogUtil.a("HiH_HiDataSourceFetchOperation", "execute end totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }
}
