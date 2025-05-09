package defpackage;

import android.content.ContentValues;
import android.content.Context;
import com.huawei.hihealth.HiAppInfo;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.sync.syncdata.HiSyncBase;
import com.huawei.hwcloudmodel.healthdatacloud.model.BindDeviceReq;
import com.huawei.hwcloudmodel.model.userprofile.BindDeviceRsp;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class itd implements HiSyncBase {
    private static ArrayList<String> b;

    /* renamed from: a, reason: collision with root package name */
    private ijf f13592a;
    private iip c;
    private List<ikv> d;
    private iis e;
    private Context f;
    private int g;

    private boolean e(int i) {
        return 21 > i && 1 != i;
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByTime(long j, long j2) throws iut {
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByVersion() throws iut {
    }

    static {
        ArrayList<String> arrayList = new ArrayList<>();
        b = arrayList;
        arrayList.add("com.huawei.health.ecg.collection");
        b.add("com.huawei.health.device");
        b.add("com.huawei.health.h5.sleep-apnea");
    }

    public itd(Context context, int i) {
        LogUtil.c("HiH_HiSyncClient", "HiSyncClient create");
        this.f = context.getApplicationContext();
        this.g = i;
        c();
    }

    private void c() {
        this.e = iis.d();
        this.f13592a = ijf.d(this.f);
        this.c = iip.b();
        this.d = this.e.b(this.g, 0);
    }

    private BindDeviceReq e(ikv ikvVar) {
        if (ikvVar == null) {
            return null;
        }
        HiDeviceInfo a2 = this.f13592a.a(ikvVar.d());
        if (a2 == null) {
            LogUtil.h("HiH_HiSyncClient", "createBindDeviceReq get no hiDeviceInfo from DB");
            return null;
        }
        if (e(a2.getDeviceType())) {
            LogUtil.h("HiH_HiSyncClient", "createBindDeviceReq device productid is error, productid=", Integer.valueOf(a2.getDeviceType()));
            return null;
        }
        BindDeviceReq bindDeviceReq = new BindDeviceReq();
        if (a2.getModel() != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("model", a2.getModel());
            bindDeviceReq.setDeviceData(HiJsonUtil.e(contentValues));
        }
        bindDeviceReq.setProductId(Integer.valueOf(a2.getDeviceType()));
        bindDeviceReq.setUniqueId(a2.getDeviceUniqueCode());
        bindDeviceReq.setName(a2.getDeviceName());
        bindDeviceReq.setFirmwareVersion(a2.getFirmwareVersion());
        bindDeviceReq.setHardwareVersion(a2.getHardwareVersion());
        bindDeviceReq.setSoftwareVersion(a2.getSoftwareVersion());
        bindDeviceReq.setManufacturer(a2.getManufacturer());
        bindDeviceReq.setProdid(a2.getProdId());
        bindDeviceReq.setSubProdId(a2.getSubProdId());
        if (a2.getDeviceType() != 32) {
            bindDeviceReq.setSn(a2.getDeviceSN());
        }
        bindDeviceReq.setMac(a2.getDeviceMac());
        bindDeviceReq.setUdid(a2.getDeviceUdid());
        a(ikvVar, bindDeviceReq);
        if (a2.getDeviceUniqueCode() == null) {
            LogUtil.b("HiH_HiSyncClient", "binddevice deviceUUID is null !");
        }
        return bindDeviceReq;
    }

    private void a(ikv ikvVar, BindDeviceReq bindDeviceReq) {
        HiAppInfo c = this.c.c(ikvVar.e());
        if (c != null) {
            String packageName = c.getPackageName();
            String appName = c.getAppName();
            if (b.contains(packageName)) {
                bindDeviceReq.setAppName(packageName);
                bindDeviceReq.setThirdAppInfo(appName);
            }
        }
    }

    private void b(ikv ikvVar, BindDeviceReq bindDeviceReq) throws iut {
        LogUtil.a("HiH_HiSyncClient", "bindDevice start bindDevice , device name is ", bindDeviceReq.getName(), " product is ", bindDeviceReq.getProductId());
        BindDeviceRsp b2 = jbs.a(this.f).b(bindDeviceReq);
        if (!ius.a(b2, false)) {
            LogUtil.b("HiH_HiSyncClient", "bindDevice error");
            return;
        }
        long longValue = b2.getDeviceCode().longValue();
        if (longValue <= 0) {
            LogUtil.b("HiH_HiSyncClient", "bindDevice error ans from cloud, deviceCode is ", Long.valueOf(longValue), " client is ", ikvVar);
            return;
        }
        ikvVar.c(longValue);
        ikvVar.i(1);
        ikvVar.b(1);
        this.e.a(ikvVar);
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pushData() throws iut {
        LogUtil.a("HiH_HiSyncClient", "pushData() begin !");
        if (ism.b()) {
            LogUtil.h("HiH_HiSyncClient", "pushData() dataPrivacy switch is closed ,can not pushData right now ,push end !");
            return;
        }
        List<ikv> list = this.d;
        if (list == null || list.isEmpty()) {
            LogUtil.a("HiH_HiSyncClient", "pushData() end ! no device needed to bindDevice to cloud, stop pushData");
            return;
        }
        for (ikv ikvVar : this.d) {
            BindDeviceReq e = e(ikvVar);
            if (e != null) {
                b(ikvVar, e);
            }
        }
        LogUtil.a("HiH_HiSyncClient", "pushData() end !");
    }

    public String toString() {
        return "--HiSyncClient{}";
    }
}
