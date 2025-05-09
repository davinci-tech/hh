package defpackage;

import android.content.Context;
import android.os.RemoteException;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.HiSampleConfigKey;
import com.huawei.hihealth.HiSampleConfigProcessOption;
import com.huawei.hihealth.IDataOperateListener;
import com.huawei.hihealth.data.constant.HiErrorCode;
import com.huawei.hihealthservice.HiDataOperation;
import health.compact.a.HiCommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class igh extends HiDataOperation {

    /* renamed from: a, reason: collision with root package name */
    private IDataOperateListener f13363a;
    private ikv b;
    private final ArrayList<Object> c;
    private HiSampleConfigProcessOption d;

    @Override // com.huawei.hihealthservice.HiDataOperation
    public boolean isMonitorThread() {
        return true;
    }

    igh(Context context) {
        super(context);
        this.c = new ArrayList<>(10);
    }

    public boolean c(HiSampleConfigProcessOption hiSampleConfigProcessOption, IDataOperateListener iDataOperateListener, boolean z) throws RemoteException {
        if (iDataOperateListener == null) {
            return false;
        }
        if (hiSampleConfigProcessOption == null) {
            ReleaseLogUtil.d("HiH_HiSampleConfigDeleteOperation", "sampleConfigDeleteOption is null!");
            this.c.add(HiErrorCode.d(7));
            igb.b(iDataOperateListener, 7, this.c);
            return false;
        }
        this.d = hiSampleConfigProcessOption;
        this.f13363a = iDataOperateListener;
        this.b = igd.b().e();
        int d = igm.e().d();
        if (d <= 0) {
            ReleaseLogUtil.d("HiH_HiSampleConfigDeleteOperation", "setSampleConfig userID <= 0 appID = ", Integer.valueOf(this.b.e()));
            this.c.add(HiErrorCode.d(7));
            igb.b(iDataOperateListener, 7, this.c);
            return false;
        }
        this.b.f(d);
        if (z && igd.b().d() == -1) {
            ReleaseLogUtil.d("HiH_HiSampleConfigDeleteOperation", "initialize appType is invalid errorCode is ", 17);
            this.c.add(HiErrorCode.d(17));
            igb.b(this.f13363a, 17, this.c);
            return false;
        }
        try {
            iwn.e(this.d);
            return true;
        } catch (iwt e) {
            ReleaseLogUtil.c("HiH_HiSampleConfigDeleteOperation", "validSampleConfigDeleteOption exception = ", e.getMessage());
            this.c.add(e.getMessage());
            igb.b(this.f13363a, 3, this.c);
            return false;
        }
    }

    @Override // com.huawei.hihealthservice.HiDataOperation
    public void execute(ifq ifqVar) {
        boolean z;
        int i;
        Exception e;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            try {
                ReleaseLogUtil.e("HiH_HiSampleConfigDeleteOperation", "HiSampleConfigDeleteOperation begin, option is ", this.d);
                z = !ivu.i(BaseApplication.e(), 0) ? ivu.e(BaseApplication.e(), 0) : false;
            } catch (Throwable th) {
                th = th;
            }
            try {
                int e2 = this.b.e();
                if ((this.d.getProcessType() == 1 || this.d.getProcessType() == 3) && this.d.getPackageName() != null) {
                    e2 = igd.b().e(this.d.getPackageName());
                }
                List<Integer> e3 = e(this.d.getProcessType(), this.b.g(), e2, this.d.getDeviceUniqueId());
                Iterator<HiSampleConfigKey> it = this.d.getSampleConfigKeyList().iterator();
                int i2 = 0;
                while (it.hasNext()) {
                    try {
                        List<HiSampleConfig> a2 = ijq.e().a(e3, it.next());
                        if (!HiCommonUtil.d(a2)) {
                            i2 = e(a2, e3);
                        }
                    } catch (Exception e4) {
                        e = e4;
                        ReleaseLogUtil.c("HiH_HiSampleConfigDeleteOperation", "execute exception = ", LogAnonymous.b((Throwable) e));
                        if (z) {
                            ivu.c(BaseApplication.e(), 0);
                        }
                        this.c.add(HiErrorCode.d(5));
                        igb.b(this.f13363a, 5, this.c);
                        ReleaseLogUtil.e("HiH_HiSampleConfigDeleteOperation", "execute end time = ", Long.valueOf(currentTimeMillis), ", totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                    }
                }
                if (z) {
                    ivu.j(BaseApplication.e(), 0);
                }
                ReleaseLogUtil.e("HiH_HiSampleConfigDeleteOperation", "HiSampleConfigDeleteOperation end");
                if (z) {
                    ivu.c(BaseApplication.e(), 0);
                }
                this.c.add(HiErrorCode.d(i2));
                igb.b(this.f13363a, i2, this.c);
            } catch (Exception e5) {
                e = e5;
            } catch (Throwable th2) {
                th = th2;
                i = 0;
                if (z) {
                    ivu.c(BaseApplication.e(), 0);
                }
                this.c.add(HiErrorCode.d(i));
                igb.b(this.f13363a, i, this.c);
                throw th;
            }
        } catch (Exception e6) {
            e = e6;
            z = false;
        } catch (Throwable th3) {
            th = th3;
            z = false;
            i = 0;
        }
        ReleaseLogUtil.e("HiH_HiSampleConfigDeleteOperation", "execute end time = ", Long.valueOf(currentTimeMillis), ", totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    private int e(List<HiSampleConfig> list, List<Integer> list2) {
        boolean a2;
        int i = 0;
        for (HiSampleConfig hiSampleConfig : list) {
            HiSampleConfigKey d = new HiSampleConfigKey.Builder().b(hiSampleConfig.getType()).d(hiSampleConfig.getConfigId()).e(hiSampleConfig.getScopeApp()).a(hiSampleConfig.getScopeDeviceType()).d();
            int syncStatus = hiSampleConfig.getSyncStatus();
            if (syncStatus == 0) {
                a2 = ijq.e().b(list2, d, syncStatus);
            } else {
                a2 = ijq.e().a(hiSampleConfig.getId(), 2);
            }
            if (!a2) {
                ReleaseLogUtil.d("HiH_HiSampleConfigDeleteOperation", "deleteHealthPointDatasByTypes() change <= 0");
                i = 9;
            }
        }
        return i;
    }

    private List<Integer> e(int i, int i2, int i3, String str) {
        ArrayList arrayList = new ArrayList(10);
        iks e = iks.e();
        if (i == 0) {
            return e.a(i2);
        }
        if (i != 1) {
            return i != 2 ? arrayList : e.b(i2, str);
        }
        return e.a(i3, i2);
    }
}
