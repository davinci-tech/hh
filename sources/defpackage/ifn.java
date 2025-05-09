package defpackage;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.hihealth.HiDataDeleteOption;
import com.huawei.hihealth.HiDataDeleteProOption;
import com.huawei.hihealth.IDeleteListenerEx;
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
import java.util.HashMap;
import java.util.List;

/* loaded from: classes7.dex */
public class ifn extends HiDataOperation {

    /* renamed from: a, reason: collision with root package name */
    private int f13342a;
    private final HashMap<Integer, List<Object>> b;
    private int c;
    private ikv d;
    private int e;
    private int f;
    private String g;
    private List<HiDataDeleteProOption> h;
    private IDeleteListenerEx j;

    @Override // com.huawei.hihealthservice.HiDataOperation
    public boolean isMonitorThread() {
        return true;
    }

    ifn(Context context) {
        super(context);
        this.b = new HashMap<>(16);
    }

    public boolean b(List<HiDataDeleteProOption> list, IDeleteListenerEx iDeleteListenerEx, boolean z) throws RemoteException {
        if (trg.d(list) || list.contains(null)) {
            LogUtil.b("HiH_HiDataDeleteProExOperation", "initialize deleteProOptions is or contains null");
            a(iDeleteListenerEx, 7);
            return false;
        }
        ReleaseLogUtil.e("HiH_HiDataDeleteProExOperation", "deleteHiHealthDataProEx initializing deleteOptions = ", list, ", needCheckAppAuth = ", Boolean.valueOf(z));
        this.g = ivw.a(this.mContext);
        this.c = igd.b().b(this.g);
        this.f13342a = igd.b().e(this.g);
        this.f = igm.e().d();
        this.e = 1;
        this.j = iDeleteListenerEx;
        this.h = list;
        ikv a2 = igd.b().a(TextUtils.isEmpty(this.g) ? this.mContext.getPackageName() : this.g);
        this.d = a2;
        a2.f(this.f);
        for (HiDataDeleteProOption hiDataDeleteProOption : list) {
            if (c(hiDataDeleteProOption.getDeleteLevel()) || c(hiDataDeleteProOption.getDeleteType()) || c(hiDataDeleteProOption.getDeleteInterval())) {
                ReleaseLogUtil.d("HiH_HiDataDeleteProExOperation", "deleteHiHealthDataProEx initialize ExtendOption input wrong, DeleteLevel is ", hiDataDeleteProOption.getDeleteLevel(), ", DeleteType is ", hiDataDeleteProOption.getDeleteType(), ", DeleteInterval is ", hiDataDeleteProOption.getDeleteInterval());
                a(this.j, 7);
                return false;
            }
            HiDataDeleteOption dataDeleteOption = hiDataDeleteProOption.getDataDeleteOption();
            if (dataDeleteOption == null) {
                ReleaseLogUtil.c("HiH_HiDataDeleteProExOperation", "initialize deleteOptions contains null");
                a(this.j, 7);
                return false;
            }
            if (z && !c(dataDeleteOption.getTypes())) {
                return false;
            }
        }
        return true;
    }

    @Override // com.huawei.hihealthservice.HiDataOperation
    public void execute(ifq ifqVar) {
        List<Integer> e;
        for (HiDataDeleteProOption hiDataDeleteProOption : this.h) {
            try {
                int e2 = hiDataDeleteProOption.getPackageName() != null ? igd.b().e(this.g) : this.f13342a;
                if (this.c != -1) {
                    e = d(hiDataDeleteProOption.getDeleteType().intValue(), hiDataDeleteProOption.getDeviceUuid(), e2);
                } else {
                    e = iis.d().e(this.f, e2);
                }
                ikv b = b(e);
                HiDataDeleteOption dataDeleteOption = hiDataDeleteProOption.getDataDeleteOption();
                iwq.b(dataDeleteOption, b);
                List<DataTimeDelCondition> deleteHealthData = isa.d(this.mContext).deleteHealthData(dataDeleteOption, b, hiDataDeleteProOption.getDeleteInterval().intValue());
                if (!HiCommonUtil.d(deleteHealthData)) {
                    e(false, 9, HiJsonUtil.e(deleteHealthData));
                }
                ivg.c().e(iwd.b(dataDeleteOption.getTypes()), ArkUIXConstants.DELETE, b);
            } catch (iwt e3) {
                ReleaseLogUtil.c("HiH_HiDataDeleteProExOperation", "execute HiValidException = ", e3.getMessage());
                e(false, 3, HiErrorCode.d(3) + e3.getMessage());
            } catch (Exception e4) {
                ReleaseLogUtil.c("HiH_HiDataDeleteProExOperation", "execute Exception=", LogAnonymous.b((Throwable) e4));
                e(false, 2, HiErrorCode.d(2) + " delete exception");
            }
        }
        igb.e(this.j, this.b);
        LogUtil.a("HiH_HiDataDeleteProExOperation", "execute end totalTime = ", Long.valueOf(System.currentTimeMillis() - this.mStartTime));
    }

    private void a(IDeleteListenerEx iDeleteListenerEx, int i) {
        e(true, i, null);
        igb.e(iDeleteListenerEx, this.b);
    }

    private void e(boolean z, int i, Object obj) {
        if (z) {
            this.b.clear();
        }
        if (this.b.containsKey(Integer.valueOf(i))) {
            this.b.get(Integer.valueOf(i)).add(obj);
            return;
        }
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(obj);
        this.b.put(Integer.valueOf(i), arrayList);
    }

    private ikv b(List<Integer> list) {
        LogUtil.c("HiH_HiDataDeleteProExOperation", "getHealthContext callingAppID = ", Integer.valueOf(this.f13342a), ", UserId=", Integer.valueOf(this.f), ", dataType=", Integer.valueOf(this.e));
        this.d.c(list);
        return this.d;
    }

    private List<Integer> d(int i, String str, int i2) {
        iks e = iks.e();
        if (i == 0) {
            return e.a(this.f);
        }
        if (i == 1) {
            return e.a(i2, this.f);
        }
        if (i != 2) {
            return null;
        }
        return e.b(this.f, str);
    }

    private boolean c(int[] iArr) {
        int i = this.c;
        if (i == -1) {
            LogUtil.h("HiH_HiDataDeleteProExOperation", "initialize appType is invalid errorCode is ", 17);
            a(this.j, 17);
            return false;
        }
        LogUtil.c("HiH_HiDataDeleteProExOperation", "deleteHiHealthDataProEx checkAppType  ", Integer.valueOf(i), " appID = ", Integer.valueOf(this.f13342a));
        if (this.c != 0) {
            int c = igm.e().c(this.f13342a);
            if (c != 0) {
                a(this.j, c);
                return false;
            }
            try {
                HiAuthManager.getInstance().checkDeleteAuth(this.f13342a, this.f, iArr);
            } catch (HiAuthException e) {
                ReleaseLogUtil.c("HiH_HiDataDeleteProExOperation", "isDeleteAppTypeInvalid HiAuthException ex=", e.getMessage());
                e(true, 8, HiErrorCode.d(8) + e.getMessage());
                igb.e(this.j, this.b);
                return false;
            }
        }
        return true;
    }

    private boolean c(Integer num) {
        return num == null || num.intValue() == -1 || num.intValue() == -2;
    }
}
