package defpackage;

import android.content.ContentValues;
import android.content.Context;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealth.util.ReleaseLogUtil;
import com.huawei.hwcloudmodel.healthdatacloud.model.BindDeviceReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetBindDeviceReq;
import com.huawei.hwcloudmodel.model.userprofile.BindDeviceRsp;
import com.huawei.hwcloudmodel.model.userprofile.DeviceInfo;
import com.huawei.hwcloudmodel.model.userprofile.GetBindDeviceRsp;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.utils.FoundationCommonUtil;
import health.compact.a.HiCommonUtil;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
class ivb {
    private static boolean a(int i) {
        return 21 > i && 1 != i;
    }

    private static void c(Context context, ikv ikvVar) {
        if (ikvVar == null) {
            return;
        }
        ikvVar.c(0L);
        ikvVar.i(0);
        ikvVar.b(1);
        iis.d().a(ikvVar);
    }

    private static void a(Context context, ikv ikvVar, BindDeviceReq bindDeviceReq) throws iut {
        LogUtil.a("HiH_HiSyncUtilHelper", "reBindUnknownDevice bindDevice start bindDevice product is ", bindDeviceReq.getProductId());
        BindDeviceRsp b = jbs.a(context).b(bindDeviceReq);
        if (!ius.a(b, false)) {
            LogUtil.b("HiH_HiSyncUtilHelper", "reBindUnknownDevice bindDevice error");
            return;
        }
        long longValue = b.getDeviceCode().longValue();
        if (longValue <= 0) {
            LogUtil.b("HiH_HiSyncUtilHelper", "reBindUnknownDevice bindDevice error ans from cloud client is ", ikvVar);
            return;
        }
        ikvVar.c(longValue);
        ikvVar.i(1);
        ikvVar.b(1);
        iis.d().a(ikvVar);
    }

    private static BindDeviceReq e(Context context, ikv ikvVar) {
        if (ikvVar == null) {
            return null;
        }
        HiDeviceInfo a2 = ijf.d(context).a(ikvVar.d());
        if (a2 == null) {
            LogUtil.h("HiH_HiSyncUtilHelper", "reBindUnknownDevice createBindDeviceReq get no hiDeviceInfo from DB");
            return null;
        }
        BindDeviceReq bindDeviceReq = new BindDeviceReq();
        if (a(a2.getDeviceType())) {
            LogUtil.h("HiH_HiSyncUtilHelper", "reBindUnknownDevice createBindDeviceReq device productid is error, productid=", Integer.valueOf(a2.getDeviceType()));
            return null;
        }
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
        if (a2.getDeviceUniqueCode() == null) {
            LogUtil.b("HiH_HiSyncUtilHelper", "reBindUnknownDevice binddevice deviceUUID is null !");
        }
        return bindDeviceReq;
    }

    static void c(Context context, int i) throws iut {
        ReleaseLogUtil.b("HiH_HiSyncUtilHelper", "reBindUnKnowDevice, userId=", Integer.valueOf(i));
        List<ikv> b = iis.d().b(i);
        if (HiCommonUtil.d(b)) {
            return;
        }
        GetBindDeviceRsp d = jbs.a(context).d(new GetBindDeviceReq());
        if (!ius.a(d, true)) {
            LogUtil.b("HiH_HiSyncUtilHelper", "reBindUnknownDevice error");
            return;
        }
        List<DeviceInfo> deviceInfos = d.getDeviceInfos();
        if (HiCommonUtil.d(deviceInfos)) {
            LogUtil.b("HiH_HiSyncUtilHelper", "reBindUnknownDevice cloud deviceInfos is null or empty, need rebind all device");
            for (ikv ikvVar : b) {
                c(context, ikvVar);
                BindDeviceReq e = e(context, ikvVar);
                if (e != null) {
                    a(context, ikvVar, e);
                }
            }
            return;
        }
        for (ikv ikvVar2 : b) {
            Iterator<DeviceInfo> it = deviceInfos.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (ikvVar2.a() == it.next().getDeviceCode().longValue()) {
                        break;
                    }
                } else {
                    c(context, ikvVar2);
                    BindDeviceReq e2 = e(context, ikvVar2);
                    if (e2 != null) {
                        a(context, ikvVar2, e2);
                    }
                }
            }
        }
    }

    static void d(Context context) {
        if (context == null) {
            return;
        }
        HiDeviceInfo d = ijf.d(context).d(FoundationCommonUtil.getAndroidId(context));
        if (d == null) {
            LogUtil.b("HiH_HiSyncUtilHelper", "updateLocalPhoneModel device is not exist!");
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("model", iik.a());
        ijf.d(context).bBl_(d.getDeviceId(), contentValues);
    }
}
