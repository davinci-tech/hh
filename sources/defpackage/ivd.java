package defpackage;

import android.content.ContentValues;
import android.content.Context;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcloudmodel.healthdatacloud.model.BindDeviceReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetBindDeviceReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.UpdateBindDeviceReq;
import com.huawei.hwcloudmodel.model.userprofile.BindDeviceRsp;
import com.huawei.hwcloudmodel.model.userprofile.DeviceInfo;
import com.huawei.hwcloudmodel.model.userprofile.GetBindDeviceRsp;
import com.huawei.hwcloudmodel.model.userprofile.UpdateBindDeviceRsp;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ivd {

    /* loaded from: classes7.dex */
    static class e {
        private static final ivd e = new ivd();
    }

    private ivd() {
    }

    public static ivd c() {
        return e.e;
    }

    public HiDeviceInfo c(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            return new HiDeviceInfo(1);
        }
        HiDeviceInfo hiDeviceInfo = new HiDeviceInfo(deviceInfo.getProductId().intValue());
        hiDeviceInfo.setDeviceUniqueCode(deviceInfo.getUniqueId());
        hiDeviceInfo.setDeviceName(deviceInfo.getName());
        hiDeviceInfo.setFirmwareVersion(deviceInfo.getFirmwareVersion());
        hiDeviceInfo.setHardwareVersion(deviceInfo.getHardwareVersion());
        hiDeviceInfo.setManufacturer(deviceInfo.getManufacturer());
        hiDeviceInfo.setSoftwareVersion(deviceInfo.getSoftwareVersion());
        hiDeviceInfo.setProdId(deviceInfo.getProdId());
        hiDeviceInfo.setSubProdId(deviceInfo.getSubProdId());
        hiDeviceInfo.setDeviceSN(deviceInfo.getSn());
        hiDeviceInfo.setDeviceMac(deviceInfo.getMac());
        hiDeviceInfo.setDeviceUdid(deviceInfo.getUdid());
        String deviceData = deviceInfo.getDeviceData();
        if (!HiCommonUtil.b(deviceData)) {
            hiDeviceInfo.setModel(a(deviceData, "mMap"));
            if (HiCommonUtil.b(hiDeviceInfo.getModel())) {
                hiDeviceInfo.setModel(a(deviceData, "mValues"));
            }
        }
        if (deviceInfo.getModifyTime() != null) {
            hiDeviceInfo.setModifyTime(deviceInfo.getModifyTime().longValue());
        }
        return hiDeviceInfo;
    }

    private String a(String str, String str2) {
        try {
            return new JSONObject(str).getJSONObject(str2).getString("model");
        } catch (JSONException unused) {
            ReleaseLogUtil.c("HiH_HiSyncUtilHelper", "getHiDeviceInfo get" + str2 + "JSONException");
            return "";
        }
    }

    HiDeviceInfo a(long j) {
        HiDeviceInfo hiDeviceInfo = new HiDeviceInfo(32);
        hiDeviceInfo.setDeviceUniqueCode(Long.toString(j));
        hiDeviceInfo.setDeviceName("UNKNOWN");
        hiDeviceInfo.setFirmwareVersion("UNKNOWN");
        hiDeviceInfo.setHardwareVersion("UNKNOWN");
        hiDeviceInfo.setSoftwareVersion("UNKNOWN");
        return hiDeviceInfo;
    }

    private UpdateBindDeviceReq d(Context context, ikv ikvVar) {
        long a2 = ikvVar.a();
        HiDeviceInfo a3 = ijf.d(context).a(ikvVar.d());
        if (a3 != null && 0 != a2) {
            UpdateBindDeviceReq updateBindDeviceReq = new UpdateBindDeviceReq();
            updateBindDeviceReq.setDeviceCode(Long.valueOf(a2));
            updateBindDeviceReq.setFirmwareVersion(a3.getFirmwareVersion());
            updateBindDeviceReq.setHardwareVersion(a3.getHardwareVersion());
            updateBindDeviceReq.setManufacturer(a3.getManufacturer());
            updateBindDeviceReq.setName(a3.getDeviceName());
            updateBindDeviceReq.setSoftwareVersion(a3.getSoftwareVersion());
            updateBindDeviceReq.setProdid(a3.getProdId());
            updateBindDeviceReq.setSubProdId(a3.getSubProdId());
            updateBindDeviceReq.setMac(a3.getDeviceMac());
            updateBindDeviceReq.setSn(a3.getDeviceSN());
            updateBindDeviceReq.setUdid(a3.getDeviceUdid());
            if (a3.getModel() != null) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("model", a3.getModel());
                updateBindDeviceReq.setDeviceData(HiJsonUtil.e(contentValues));
            }
            return updateBindDeviceReq;
        }
        LogUtil.h("HiH_HiSyncUtilHelper", "getUpdateBindDeviceReq device or deviceCode is null");
        return null;
    }

    private boolean e(Context context, ikv ikvVar) throws iut {
        HiDeviceInfo a2 = ijf.d(context).a(ikvVar.d());
        if (a2 == null) {
            return false;
        }
        BindDeviceReq bindDeviceReq = new BindDeviceReq();
        bindDeviceReq.setProductId(Integer.valueOf(a2.getDeviceType()));
        bindDeviceReq.setUniqueId(a2.getDeviceUniqueCode());
        bindDeviceReq.setName(a2.getDeviceName());
        bindDeviceReq.setFirmwareVersion(a2.getFirmwareVersion());
        bindDeviceReq.setHardwareVersion(a2.getHardwareVersion());
        bindDeviceReq.setSoftwareVersion(a2.getSoftwareVersion());
        bindDeviceReq.setManufacturer(a2.getManufacturer());
        bindDeviceReq.setProdid(a2.getProdId());
        bindDeviceReq.setSubProdId(a2.getSubProdId());
        bindDeviceReq.setMac(a2.getDeviceMac());
        bindDeviceReq.setSn(a2.getDeviceSN());
        bindDeviceReq.setUdid(a2.getDeviceUdid());
        if (a2.getDeviceUniqueCode() == null) {
            LogUtil.b("HiH_HiSyncUtilHelper", "binddevice device uuid is null");
        }
        BindDeviceRsp b = jbs.a(context).b(bindDeviceReq);
        if (!ius.a(b, false)) {
            LogUtil.b("HiH_HiSyncUtilHelper", "bindDevice error");
            return false;
        }
        long longValue = b.getDeviceCode().longValue();
        if (longValue <= 0) {
            LogUtil.b("HiH_HiSyncUtilHelper", "bindDevice error ans from cloud, deviceCode less than 0 ");
            return false;
        }
        ikvVar.c(longValue);
        ikvVar.i(1);
        iis.d().a(ikvVar);
        return true;
    }

    private List<HiDeviceInfo> b(Context context, int i) throws iut {
        if (context == null) {
            return null;
        }
        List<ikv> b = iis.d().b(i, 1);
        if (HiCommonUtil.d(b)) {
            return null;
        }
        for (ikv ikvVar : b) {
            if (ikvVar != null) {
                if (0 == ikvVar.a()) {
                    LogUtil.b("HiH_HiSyncUtilHelper", "updateBindPhoneDevice devicecode is 0, need bind device!");
                    if (!e(context, ikvVar)) {
                        LogUtil.b("HiH_HiSyncUtilHelper", "updateBindPhoneDevice binddevice fail!");
                        return null;
                    }
                }
                UpdateBindDeviceReq d = d(context, ikvVar);
                if (d == null) {
                    LogUtil.b("HiH_HiSyncUtilHelper", "local phone device not found!");
                    return null;
                }
                UpdateBindDeviceRsp b2 = jbs.a(context).b(d);
                if (!ius.a(b2, true)) {
                    LogUtil.b("HiH_HiSyncUtilHelper", "updateBindPhoneDevice cloud return error= ", b2.getResultDesc());
                }
            }
        }
        GetBindDeviceRsp d2 = jbs.a(context).d(new GetBindDeviceReq());
        if (!ius.a(d2, true)) {
            LogUtil.b("HiH_HiSyncUtilHelper", "getBindDeviceSync error");
            return null;
        }
        List<DeviceInfo> deviceInfos = d2.getDeviceInfos();
        if (HiCommonUtil.d(deviceInfos)) {
            LogUtil.b("HiH_HiSyncUtilHelper", "getAllBindDeviceRsp error,deviceInfos is null or empty ");
            return null;
        }
        ArrayList arrayList = new ArrayList(deviceInfos.size());
        for (DeviceInfo deviceInfo : deviceInfos) {
            if (deviceInfo != null && 32 == deviceInfo.getProductId().intValue()) {
                arrayList.add(c(deviceInfo));
            }
        }
        return arrayList;
    }

    int e(Context context, int i) {
        try {
            List<HiDeviceInfo> b = b(context, i);
            int i2 = 0;
            if (HiCommonUtil.d(b)) {
                LogUtil.a("HiH_HiSyncUtilHelper", "get bind device from cloud return error!");
                return -1;
            }
            long currentTimeMillis = System.currentTimeMillis();
            for (HiDeviceInfo hiDeviceInfo : b) {
                if (hiDeviceInfo != null) {
                    long modifyTime = currentTimeMillis - hiDeviceInfo.getModifyTime();
                    if (86400000 > modifyTime && 0 < modifyTime) {
                        i2++;
                    }
                    if (hiDeviceInfo.getModel() != null) {
                        ijf.d(context).d(hiDeviceInfo);
                    }
                }
            }
            LogUtil.a("HiH_HiSyncUtilHelper", "getbinddevice activity device num=", Integer.valueOf(i2));
            return i2;
        } catch (iut e2) {
            ReleaseLogUtil.c("HiH_HiSyncUtilHelper", "updateBindPhoneDevice return error , e = " + e2.getMessage());
            return -1;
        }
    }

    public void e(Context context) {
        if (context == null) {
            return;
        }
        b(context);
        ism.f().d(0);
    }

    private void b(Context context) {
        ivb.d(context);
    }

    void c(Context context, int i) throws iut {
        ivb.c(context, i);
    }
}
