package defpackage;

import android.content.Context;
import android.os.RemoteException;
import com.huawei.hihealth.HiDataReadOption;
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
public class ign extends HiDataOperation {

    /* renamed from: a, reason: collision with root package name */
    private ICommonListener f13369a;
    private int b;
    private final ArrayList<Object> c;
    private int d;
    private HiDataReadOption e;
    private int h;
    private int[] i;
    private iks j;

    ign(Context context) {
        super(context);
        this.i = new int[]{-1};
        ArrayList<Object> arrayList = new ArrayList<>(10);
        this.c = arrayList;
        arrayList.add(HiErrorCode.d(0));
    }

    public boolean e(HiDataReadOption hiDataReadOption, ICommonListener iCommonListener, boolean z) throws RemoteException {
        this.f13369a = iCommonListener;
        this.e = hiDataReadOption;
        this.j = iks.e();
        String a2 = ivw.a(this.mContext);
        this.b = igd.b().d(a2);
        this.h = igm.e().d();
        int b = igd.b().b(a2);
        this.d = b;
        if (b != 0) {
            this.b = igd.b().e(HuaweiHealth.b());
        }
        if (this.h > 0) {
            return !z || e();
        }
        this.c.set(0, 2);
        igb.c(iCommonListener, 24, this.c);
        return false;
    }

    private boolean e() {
        int i = this.d;
        if (i == -1) {
            LogUtil.h("HiH_HiSptTypeLstFtOp", "initialize checkAppAuth() appType is invalid");
            this.c.set(0, 2);
            igb.c(this.f13369a, 17, this.c);
            return false;
        }
        if (i == 0) {
            return true;
        }
        int c = igm.e().c(this.b);
        if (c != 0) {
            this.c.set(0, 2);
            igb.c(this.f13369a, c, this.c);
            return false;
        }
        try {
            HiAuthManager.getInstance().checkReadAuth(this.b, this.h, this.i);
            return true;
        } catch (HiAuthException e) {
            ReleaseLogUtil.c("HiH_HiSptTypeLstFtOp", "initialize HiAuthException e = ", e.getMessage(), " appID = ", Integer.valueOf(this.b), " who = ", Integer.valueOf(this.h));
            this.c.set(0, 2);
            igb.c(this.f13369a, 8, this.c);
            return false;
        }
    }

    @Override // com.huawei.hihealthservice.HiDataOperation
    public void execute(ifq ifqVar) {
        long currentTimeMillis = System.currentTimeMillis();
        LogUtil.a("HiH_HiSptTypeLstFtOp", "execute checkAppType  ", Integer.valueOf(this.d), " appID = ", Integer.valueOf(this.b));
        List<Integer> d = d(this.e.getReadType(), this.h, this.b, this.e.getDeviceUuid());
        if (koq.b(d)) {
            LogUtil.h("HiH_HiSptTypeLstFtOp", "execute getClientIds is null");
            igb.c(this.f13369a, 7, new ArrayList());
            return;
        }
        List<Integer> b = iiz.a(this.mContext).b(d);
        ReleaseLogUtil.e("HiH_HiSptTypeLstFtOp", "execute fetchSportTypeList sportTypes = ", b);
        if (b != null) {
            igb.b(this.f13369a, 0, b);
        } else {
            LogUtil.a("HiH_HiSptTypeLstFtOp", "execute fail");
            ArrayList arrayList = new ArrayList(10);
            arrayList.add(false);
            igb.c(this.f13369a, 14, arrayList);
        }
        LogUtil.a("HiH_HiSptTypeLstFtOp", "execute end! cost time is ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    private List<Integer> d(int i, int i2, int i3, String str) {
        if (i == 0) {
            return this.j.a(i2);
        }
        if (i == 1) {
            return this.j.a(i3, i2);
        }
        if (i != 2) {
            return null;
        }
        return this.j.b(i2, str);
    }
}
