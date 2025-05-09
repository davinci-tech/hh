package defpackage;

import android.content.Context;
import android.os.RemoteException;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.IDataOperateListener;
import com.huawei.hihealth.data.constant.HiErrorCode;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.HiDataOperation;
import health.compact.a.HiCommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class igg extends HiDataOperation {

    /* renamed from: a, reason: collision with root package name */
    private List<HiSampleConfig> f13362a;
    private IDataOperateListener b;
    private List<HiSampleConfig> c;
    private int d;
    private final ArrayList<Object> e;

    @Override // com.huawei.hihealthservice.HiDataOperation
    public boolean isMonitorThread() {
        return true;
    }

    igg(Context context) {
        super(context);
        this.e = new ArrayList<>(10);
        this.f13362a = new ArrayList(10);
    }

    public boolean d(List<HiSampleConfig> list, IDataOperateListener iDataOperateListener, boolean z) throws RemoteException {
        if (iDataOperateListener == null) {
            return false;
        }
        ReleaseLogUtil.d("HiH_HiSampCfgSetOp", "sampleConfig initialize start");
        if (HiCommonUtil.d(list)) {
            ReleaseLogUtil.d("HiH_HiSampCfgSetOp", "initialize sampleConfigs is null ");
            this.e.add(HiErrorCode.d(7));
            igb.b(iDataOperateListener, 7, this.e);
            return false;
        }
        this.c = list;
        this.b = iDataOperateListener;
        int d = igm.e().d();
        this.d = d;
        if (d <= 0) {
            ReleaseLogUtil.d("HiH_HiSampCfgSetOp", "setSampleConfig userID <= 0");
            return false;
        }
        if (z && igd.b().d() == -1) {
            ReleaseLogUtil.d("HiH_HiSampCfgSetOp", "initialize appType is invalid errorCode is ", 17);
            this.e.add(HiErrorCode.d(17));
            igb.b(this.b, 17, this.e);
            return false;
        }
        try {
            this.f13362a = iwn.b(this.c);
            return true;
        } catch (iwt e) {
            ReleaseLogUtil.c("HiH_HiSampCfgSetOp", "validSampleConfigs exception = ", e.getMessage());
            this.e.add(e.getMessage());
            igb.b(this.b, 3, this.e);
            return false;
        }
    }

    @Override // com.huawei.hihealthservice.HiDataOperation
    public void execute(ifq ifqVar) {
        int i;
        ArrayList arrayList;
        int i2;
        int i3 = 0;
        try {
            arrayList = new ArrayList(10);
            String a2 = ivw.a(this.mContext);
            int c = igm.e().c();
            ReleaseLogUtil.d("HiH_HiSampCfgSetOp", "sampleConfig execute start");
            i2 = 0;
            for (HiSampleConfig hiSampleConfig : this.c) {
                if (hiSampleConfig == null) {
                    ReleaseLogUtil.d("HiH_HiSampCfgSetOp", "sampleConfig is null");
                } else {
                    ikv c2 = c(a2, c, hiSampleConfig);
                    if (c2 == null) {
                        ReleaseLogUtil.d("HiH_HiSampCfgSetOp", "healthContext is null");
                    } else {
                        HiSampleConfig c3 = hiSampleConfig.toBuilder().a(c2.b()).e(0).c();
                        boolean b = ijq.e().b(this.d, c3);
                        ReleaseLogUtil.e("HiH_HiSampCfgSetOp", "setSampleConfig result = ", Boolean.valueOf(b), ", configId is ", c3.getConfigId());
                        if (b) {
                            i2++;
                            ivg.c().a(103, c3.getConfigId(), c2);
                            c2.b(1);
                            iis.d().b(c2);
                        } else {
                            arrayList.add(c3.getConfigId());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e = e;
            i = 0;
        } catch (Throwable th) {
            th = th;
            igb.b(this.b, i3, this.e);
            throw th;
        }
        if (!HiCommonUtil.d(this.f13362a)) {
            i = 27;
            try {
                try {
                    this.e.add(HiJsonUtil.e(this.f13362a));
                    i3 = 27;
                } catch (Exception e2) {
                    e = e2;
                    ReleaseLogUtil.c("HiH_HiSampCfgSetOp", "execute exception = ", LogAnonymous.b((Throwable) e));
                    this.e.add(HiErrorCode.d(4));
                    i3 = 4;
                    igb.b(this.b, i3, this.e);
                    ReleaseLogUtil.d("HiH_HiSampCfgSetOp", "sampleConfig execute end");
                }
            } catch (Throwable th2) {
                th = th2;
                i3 = i;
                igb.b(this.b, i3, this.e);
                throw th;
            }
        } else if (i2 != this.c.size()) {
            try {
                try {
                    this.e.add(HiJsonUtil.e(arrayList));
                } catch (Exception e3) {
                    e = e3;
                    i = 4;
                    ReleaseLogUtil.c("HiH_HiSampCfgSetOp", "execute exception = ", LogAnonymous.b((Throwable) e));
                    this.e.add(HiErrorCode.d(4));
                    i3 = 4;
                    igb.b(this.b, i3, this.e);
                    ReleaseLogUtil.d("HiH_HiSampCfgSetOp", "sampleConfig execute end");
                }
                i3 = 4;
            } catch (Throwable th3) {
                th = th3;
                i3 = 4;
                igb.b(this.b, i3, this.e);
                throw th;
            }
        } else {
            this.e.add(true);
        }
        igb.b(this.b, i3, this.e);
        ReleaseLogUtil.d("HiH_HiSampCfgSetOp", "sampleConfig execute end");
    }

    private ikv c(String str, int i, HiSampleConfig hiSampleConfig) throws RemoteException {
        String packageName = hiSampleConfig.getPackageName();
        if (packageName != null && packageName.matches("[a-zA-Z0-9._]+")) {
            str = packageName;
        }
        ikv e = ikr.b(this.mContext).e(igd.b().d(str), i, 0, hiSampleConfig.getDeviceUniqueId());
        if (e != null) {
            return e;
        }
        ReleaseLogUtil.d("HiH_HiSampCfgSetOp", "getHiHealthContext healthContext is null");
        return null;
    }
}
