package defpackage;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.hihealth.HiDataDeleteOption;
import com.huawei.hihealth.IDataOperateListener;
import com.huawei.hihealth.data.constant.HiErrorCode;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.HiDataOperation;
import com.huawei.hihealthservice.auth.HiAuthException;
import com.huawei.hihealthservice.auth.HiAuthManager;
import com.huawei.hwcloudmodel.model.unite.DataTimeDelCondition;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class ifm extends HiDataOperation {

    /* renamed from: a, reason: collision with root package name */
    private int f13341a;
    private int b;
    private int c;
    private int d;
    private final ArrayList<Object> e;
    private int f;
    private String g;
    private ikv h;
    private IDataOperateListener i;
    private HiDataDeleteOption j;
    private int l;

    @Override // com.huawei.hihealthservice.HiDataOperation
    public boolean isMonitorThread() {
        return true;
    }

    ifm(Context context) {
        super(context);
        this.d = 2;
        ArrayList<Object> arrayList = new ArrayList<>(10);
        this.e = arrayList;
        arrayList.add(HiErrorCode.d(0));
    }

    public boolean c(HiDataDeleteOption hiDataDeleteOption, IDataOperateListener iDataOperateListener, ifl iflVar) throws RemoteException {
        if (hiDataDeleteOption == null || iflVar == null) {
            LogUtil.b("HiH_HiDataDeleteOperation", "initialize deleteOption or extendOption is null ");
            igb.b(iDataOperateListener, 7, this.e);
            return false;
        }
        ReleaseLogUtil.e("HiH_HiDataDeleteOperation", "deleteHiHealthData deleteOption = ", hiDataDeleteOption, ", extendOption = ", iflVar);
        if (iflVar.c(iflVar.b()) || iflVar.c(iflVar.e()) || iflVar.c(iflVar.a())) {
            ReleaseLogUtil.d("HiH_HiDataDeleteOperation", "ExtendOption input wrong, DeleteLevel is ", Integer.valueOf(iflVar.b()), ", DeleteType is ", Integer.valueOf(iflVar.e()), ", DeleteInterval is ", Integer.valueOf(iflVar.a()));
            igb.b(iDataOperateListener, 7, this.e);
            return false;
        }
        this.g = ivw.a(this.mContext);
        this.j = hiDataDeleteOption;
        this.i = iDataOperateListener;
        this.b = igd.b().e(this.g);
        this.l = igm.e().d();
        this.c = igd.b().b(this.g);
        this.d = 1;
        this.f13341a = this.b;
        this.f = iflVar.a();
        if (iflVar.i() != null) {
            this.f13341a = igd.b().e(iflVar.i());
        }
        if (iflVar.g() && !c()) {
            return false;
        }
        this.d = 1;
        b(this.g, iflVar);
        return true;
    }

    private boolean c() {
        int i = this.c;
        if (i == -1) {
            LogUtil.h("HiH_HiDataDeleteOperation", "initialize appType is invalid errorCode is ", 17);
            igb.b(this.i, 17, this.e);
            return false;
        }
        LogUtil.a("HiH_HiDataDeleteOperation", "isDeleteAppTypeInvalid checkAppType  ", Integer.valueOf(i), " appID = ", Integer.valueOf(this.b));
        if (this.c == 0) {
            return true;
        }
        int c = igm.e().c(this.b);
        if (c != 0) {
            igb.b(this.i, c, this.e);
            return false;
        }
        try {
            HiAuthManager.getInstance().checkDeleteAuth(this.b, this.l, this.j.getTypes());
            return true;
        } catch (HiAuthException e) {
            ReleaseLogUtil.c("HiH_HiDataDeleteOperation", "isDeleteAppTypeInvalid HiAuthException ex=", e.getMessage());
            this.e.clear();
            this.e.add(HiErrorCode.d(8) + e.getMessage());
            igb.b(this.i, 8, this.e);
            return false;
        }
    }

    private void b(String str, ifl iflVar) throws RemoteException {
        List<Integer> e;
        if (TextUtils.isEmpty(str)) {
            str = this.mContext.getPackageName();
        }
        ikv a2 = igd.b().a(str);
        this.h = a2;
        a2.f(this.l);
        if (this.c != -1) {
            e = a(iflVar.e(), this.l, this.f13341a, iflVar.d());
        } else {
            e = iis.d().e(this.l, this.f13341a);
        }
        this.h.c(e);
        LogUtil.a("HiH_HiDataDeleteOperation", "initHealthContext callingAppID = ", Integer.valueOf(this.b), ", deleteAppId=", Integer.valueOf(this.f13341a), ", UserId=", Integer.valueOf(this.l), ", dataType=", Integer.valueOf(this.d));
    }

    private List<Integer> a(int i, int i2, int i3, String str) {
        iks e = iks.e();
        if (i == 0) {
            return e.a(i2);
        }
        if (i == 1) {
            return e.a(i3, i2);
        }
        if (i != 2) {
            return null;
        }
        return e.b(i2, str);
    }

    /* JADX WARN: Not initialized variable reg: 5, insn: 0x010e: MOVE (r2 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r5 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:38:0x010e */
    @Override // com.huawei.hihealthservice.HiDataOperation
    public void execute(ifq ifqVar) {
        int i;
        int i2;
        int i3 = 2;
        try {
            try {
                iwq.b(this.j, this.h);
                List<DataTimeDelCondition> deleteHealthData = isa.d(this.mContext).deleteHealthData(this.j, this.h, this.f);
                if (HiCommonUtil.d(deleteHealthData)) {
                    i2 = 0;
                } else {
                    i2 = 9;
                    try {
                        this.e.clear();
                        this.e.add(HiJsonUtil.e(deleteHealthData));
                    } catch (iwt e) {
                        e = e;
                        ReleaseLogUtil.c("HiH_HiDataDeleteOperation", "execute HiValidException = ", e.getMessage());
                        try {
                            this.e.clear();
                            this.e.add(HiErrorCode.d(3) + e.getMessage());
                            igb.b(this.i, 3, this.e);
                            ReleaseLogUtil.e("HiH_HiDataDeleteOperation", "execute end totalTime = ", Long.valueOf(System.currentTimeMillis() - this.mStartTime));
                            return;
                        } catch (Throwable th) {
                            i3 = 3;
                            th = th;
                            igb.b(this.i, i3, this.e);
                            ReleaseLogUtil.e("HiH_HiDataDeleteOperation", "execute end totalTime = ", Long.valueOf(System.currentTimeMillis() - this.mStartTime));
                            throw th;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        ReleaseLogUtil.c("HiH_HiDataDeleteOperation", "execute Exception=", LogAnonymous.b((Throwable) e));
                        try {
                            this.e.clear();
                            this.e.add(HiErrorCode.d(2) + "delete exception");
                            igb.b(this.i, 2, this.e);
                            ReleaseLogUtil.e("HiH_HiDataDeleteOperation", "execute end totalTime = ", Long.valueOf(System.currentTimeMillis() - this.mStartTime));
                            return;
                        } catch (Throwable th2) {
                            th = th2;
                            igb.b(this.i, i3, this.e);
                            ReleaseLogUtil.e("HiH_HiDataDeleteOperation", "execute end totalTime = ", Long.valueOf(System.currentTimeMillis() - this.mStartTime));
                            throw th;
                        }
                    }
                }
                ivg.c().e(iwd.b(this.j.getTypes()), ArkUIXConstants.DELETE, this.h);
                igb.b(this.i, i2, this.e);
                ReleaseLogUtil.e("HiH_HiDataDeleteOperation", "execute end totalTime = ", Long.valueOf(System.currentTimeMillis() - this.mStartTime));
            } catch (Throwable th3) {
                th = th3;
                i3 = i;
                igb.b(this.i, i3, this.e);
                ReleaseLogUtil.e("HiH_HiDataDeleteOperation", "execute end totalTime = ", Long.valueOf(System.currentTimeMillis() - this.mStartTime));
                throw th;
            }
        } catch (iwt e3) {
            e = e3;
        } catch (Exception e4) {
            e = e4;
        } catch (Throwable th4) {
            th = th4;
            i3 = 0;
            igb.b(this.i, i3, this.e);
            ReleaseLogUtil.e("HiH_HiDataDeleteOperation", "execute end totalTime = ", Long.valueOf(System.currentTimeMillis() - this.mStartTime));
            throw th;
        }
    }

    @Override // com.huawei.hihealthservice.HiDataOperation
    public int getDataType() {
        return this.d;
    }
}
