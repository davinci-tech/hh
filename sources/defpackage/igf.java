package defpackage;

import android.content.Context;
import android.os.RemoteException;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.IDataReadResultListener;
import com.huawei.hihealth.util.HiDivideUtil;
import com.huawei.hihealthservice.HiDataOperation;
import com.huawei.hihealthservice.auth.HiAuthException;
import com.huawei.hihealthservice.auth.HiAuthManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import health.compact.a.HuaweiHealth;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class igf extends HiDataOperation {

    /* renamed from: a, reason: collision with root package name */
    private int[] f13361a;
    private int b;
    private IDataReadResultListener c;
    private int d;
    private HiDataReadOption e;
    private int g;
    private iks j;

    igf(Context context) {
        super(context);
        this.f13361a = new int[]{-1};
    }

    public boolean d(HiDataReadOption hiDataReadOption, IDataReadResultListener iDataReadResultListener, boolean z) throws RemoteException {
        this.e = hiDataReadOption;
        this.c = iDataReadResultListener;
        this.j = iks.e();
        String a2 = ivw.a(this.mContext);
        this.b = igd.b().d(a2);
        this.g = igm.e().d();
        int b = igd.b().b(a2);
        this.d = b;
        if (b != 0) {
            this.b = igd.b().e(HuaweiHealth.b());
        }
        if (hiDataReadOption.getStartTime() > hiDataReadOption.getEndTime()) {
            igb.b(iDataReadResultListener, null, 3, 2);
            return false;
        }
        if (this.g > 0) {
            return !z || e();
        }
        LogUtil.h("HiH_HiSeqDataFetchOp", "initialize userID <= 0 ,appID = ", Integer.valueOf(this.b));
        igb.b(iDataReadResultListener, null, 24, 2);
        return false;
    }

    private boolean e() {
        int i = this.d;
        if (i == -1) {
            LogUtil.a("HiH_HiSeqDataFetchOp", "initialize appType is invalid");
            igb.b(this.c, null, 17, 2);
            return false;
        }
        if (i == 0) {
            return true;
        }
        int c = igm.e().c(this.b);
        if (c != 0) {
            igb.b(this.c, null, c, 2);
            return false;
        }
        try {
            HiAuthManager.getInstance().checkReadAuth(this.b, this.g, this.f13361a);
            return true;
        } catch (HiAuthException e) {
            ReleaseLogUtil.c("HiH_HiSeqDataFetchOp", "checkAppAuth HiAuthException e = ", e.getMessage(), " appID = ", Integer.valueOf(this.b), " who = ", Integer.valueOf(this.g));
            igb.b(this.c, null, 8, 2);
            return false;
        }
    }

    @Override // com.huawei.hihealthservice.HiDataOperation
    public void execute(ifq ifqVar) {
        long currentTimeMillis = System.currentTimeMillis();
        LogUtil.a("HiH_HiSeqDataFetchOp", "execute checkAppType  ", Integer.valueOf(this.d), " appID = ", Integer.valueOf(this.b));
        List<Integer> d = d(this.e.getReadType(), this.g, this.b, this.e.getDeviceUuid());
        if (koq.b(d)) {
            LogUtil.h("HiH_HiSeqDataFetchOp", "execute getClientIds is null");
            igb.b(this.c, null, 7, 2);
            return;
        }
        List<HiHealthData> a2 = iiz.a(this.mContext).a(d, this.e);
        b(a2, this.mContext);
        int i = 0;
        if (a2 != null) {
            ReleaseLogUtil.e("HiH_HiSeqDataFetchOp", "mReadOption=", this.e, ",sequenceDataList=", Integer.valueOf(a2.size()));
            try {
                HiDivideUtil.d(0, a2, this.c);
            } catch (Exception e) {
                ReleaseLogUtil.c("HiH_HiSeqDataFetchOp", "execute exception = ", LogAnonymous.b((Throwable) e));
                i = 5;
            }
        }
        igb.b(this.c, null, i, 2);
        LogUtil.a("HiH_HiSeqDataFetchOp", "execute end! cost time is ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
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

    private static void b(List<HiHealthData> list, Context context) {
        if (list != null) {
            for (HiHealthData hiHealthData : list) {
                int clientId = hiHealthData.getClientId();
                ikk a2 = ikk.a(context);
                hiHealthData.putInt("trackdata_deviceType", a2.b(clientId));
                HiDeviceInfo e = a2.e(clientId);
                String c = a2.c(e);
                String b = a2.b(e);
                String d = a2.d(e);
                String a3 = a2.a(e);
                hiHealthData.putString(PluginPayAdapter.KEY_DEVICE_INFO_MODEL, b);
                hiHealthData.putString(PluginPayAdapter.KEY_DEVICE_INFO_NAME, c);
                hiHealthData.putString("device_uniquecode", d);
                hiHealthData.putString("device_prodid", a3);
            }
        }
    }
}
