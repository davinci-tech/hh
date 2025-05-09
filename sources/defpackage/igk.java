package defpackage;

import android.content.Context;
import android.os.RemoteException;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.ICommonListener;
import com.huawei.hihealth.data.constant.HiErrorCode;
import com.huawei.hihealthservice.HiDataOperation;
import com.huawei.hihealthservice.auth.HiAuthException;
import com.huawei.hihealthservice.auth.HiAuthManager;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HuaweiHealth;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class igk extends HiDataOperation {

    /* renamed from: a, reason: collision with root package name */
    private ICommonListener f13366a;
    private int b;
    private int c;
    private String d;
    private final ArrayList<Object> e;
    private int[] g;
    private int j;

    igk(Context context) {
        super(context);
        this.g = new int[]{-1};
        ArrayList<Object> arrayList = new ArrayList<>(10);
        this.e = arrayList;
        arrayList.add(HiErrorCode.d(0));
    }

    public boolean b(ICommonListener iCommonListener, boolean z) throws RemoteException {
        this.f13366a = iCommonListener;
        this.d = ivw.a(this.mContext);
        this.c = igd.b().d(this.d);
        this.j = igm.e().d();
        int b = igd.b().b(this.d);
        this.b = b;
        if (b != 0) {
            this.c = igd.b().e(HuaweiHealth.b());
        }
        if (this.j > 0) {
            return !z || c();
        }
        this.e.set(0, 2);
        igb.c(iCommonListener, 24, this.e);
        return false;
    }

    private boolean c() {
        int i = this.b;
        if (i == -1) {
            LogUtil.a("HiH_HiUserDataFetchOperation", "initialize fetchUserData() appType is invalid");
            this.e.set(0, 2);
            igb.c(this.f13366a, 17, this.e);
            return false;
        }
        if (i == 0) {
            return true;
        }
        int c = igm.e().c(this.c);
        if (c != 0) {
            this.e.set(0, 2);
            igb.c(this.f13366a, c, this.e);
            return false;
        }
        try {
            HiAuthManager.getInstance().checkReadAuth(this.c, this.j, this.g);
            return true;
        } catch (HiAuthException e) {
            ReleaseLogUtil.c("HiH_HiUserDataFetchOperation", "initialize HiAuthException e = ", e.getMessage(), " appID = ", Integer.valueOf(this.c), " who = ", Integer.valueOf(this.j));
            this.e.set(0, 2);
            igb.c(this.f13366a, 8, this.e);
            return false;
        }
    }

    @Override // com.huawei.hihealthservice.HiDataOperation
    public void execute(ifq ifqVar) {
        long currentTimeMillis = System.currentTimeMillis();
        LogUtil.a("HiH_HiUserDataFetchOperation", "execute checkAppType  ", Integer.valueOf(this.b), " appID = ", Integer.valueOf(this.c));
        List<HiUserInfo> fetchUserData = igm.e().fetchUserData(this.c);
        LogUtil.c("HiH_HiUserDataFetchOperation", "execute fetchUserData userInfos = ", fetchUserData);
        if (fetchUserData != null) {
            igb.b(this.f13366a, 0, fetchUserData);
        } else {
            LogUtil.a("HiH_HiUserDataFetchOperation", "execute fail");
            ArrayList arrayList = new ArrayList(10);
            arrayList.add(false);
            igb.c(this.f13366a, 14, arrayList);
        }
        LogUtil.a("HiH_HiUserDataFetchOperation", "execute end! cost time is ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }
}
