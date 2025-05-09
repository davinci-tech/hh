package defpackage;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.hihealth.HiAccountInfo;
import com.huawei.hihealth.ICommonListener;
import com.huawei.hihealthservice.HiDataOperation;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;

/* loaded from: classes7.dex */
public class iga extends HiDataOperation {

    /* renamed from: a, reason: collision with root package name */
    private final ArrayList<Boolean> f13358a;
    private ICommonListener c;
    private HiAccountInfo d;
    private ikv e;

    iga(Context context) {
        super(context);
        this.f13358a = new ArrayList<>(10);
    }

    public boolean c(HiAccountInfo hiAccountInfo, ICommonListener iCommonListener) throws RemoteException {
        if (iCommonListener == null) {
            return false;
        }
        this.e = igd.b().e();
        this.d = hiAccountInfo;
        this.c = iCommonListener;
        if (igd.b().d() == -1) {
            LogUtil.a("HiH_HiLoginOperation", "hiLogin() appType is invalid");
            this.f13358a.add(false);
            igb.c(iCommonListener, 17, this.f13358a);
            return false;
        }
        if (!igd.b().i()) {
            LogUtil.b("HiH_HiLoginOperation", "is not allow login");
            this.f13358a.add(false);
            igb.c(this.c, 12, this.f13358a);
            return false;
        }
        HiAccountInfo hiAccountInfo2 = this.d;
        if (hiAccountInfo2 != null && !TextUtils.isEmpty(hiAccountInfo2.getHuid()) && !this.d.getHuid().contains("com.") && !"0".equals(this.d.getHuid())) {
            return true;
        }
        this.f13358a.add(false);
        igb.c(this.c, 7, this.f13358a);
        return false;
    }

    @Override // com.huawei.hihealthservice.HiDataOperation
    public void execute(ifq ifqVar) {
        LogUtil.a("HiH_HiLoginOperation", "hiLogin() accountInfo:", this.d, "app context:", this.e);
        boolean hiLogin = igm.e().hiLogin(this.d, this.e);
        if (iwe.s(this.mContext)) {
            iwe.y(this.mContext);
        }
        LogUtil.c("HiH_HiLoginOperation", "hiLogin() result = ", Boolean.valueOf(hiLogin));
        if (hiLogin) {
            if (igd.b().j()) {
                LogUtil.a("HiH_HiLoginOperation", "hiLogin() boolean is ", Boolean.valueOf(igd.b().j()), " app = ", this.e);
                if (!igd.b().h()) {
                    ivo.b(this.mContext).d(this.d.getHuid());
                }
            }
            this.f13358a.add(true);
            igb.b(this.c, 0, this.f13358a);
            return;
        }
        LogUtil.a("HiH_HiLoginOperation", "hiLogin() fail");
        this.f13358a.add(false);
        igb.c(this.c, 12, this.f13358a);
    }
}
