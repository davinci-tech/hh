package defpackage;

import android.bluetooth.BluetoothDevice;
import android.content.ContentValues;
import android.text.TextUtils;
import com.huawei.health.device.api.DeviceManagerApi;
import com.huawei.health.device.api.EcologyDeviceApi;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.util.BrUtils;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.linechart.common.DateType;
import defpackage.dlb;
import health.compact.a.LogUtil;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

@ApiDefine(uri = EcologyDeviceApi.class)
/* loaded from: classes3.dex */
public class czo implements EcologyDeviceApi {
    @Override // com.huawei.health.device.api.EcologyDeviceApi
    public void uploadDataToHota(String str, String str2) {
        LogUtil.c("EcologyDeviceImpl", "uploadDataToHota");
        if (TextUtils.isEmpty(str2)) {
            LogUtil.a("EcologyDeviceImpl", "uploadDataToHota data is null");
            return;
        }
        try {
            new dlb.a().d(new JSONObject(str2).getString("sn")).e(str).c().a();
        } catch (JSONException unused) {
            LogUtil.e("EcologyDeviceImpl", "uploadDataToHota JSONException");
        }
    }

    @Override // com.huawei.health.device.api.EcologyDeviceApi
    public String getProductInfo(String str) {
        LogUtil.c("EcologyDeviceImpl", "getProductInfo");
        dcz d = ResourceManager.e().d(str);
        if (d != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("deviceName", d.n() == null ? "" : dcx.d(str, d.n().b()));
                jSONObject.put("ota", d.c("ota"));
                return jSONObject.toString();
            } catch (JSONException unused) {
                LogUtil.e("EcologyDeviceImpl", "getProductInfo JSONException");
            }
        }
        return "";
    }

    @Override // com.huawei.health.device.api.EcologyDeviceApi
    public boolean isSupportPerformanceByProductId(String str) {
        return "true".equals(dij.c("isSupportScore", str));
    }

    @Override // com.huawei.health.device.api.EcologyDeviceApi
    public boolean isHighRopeType(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("EcologyDeviceImpl", "isHighRopeType productId is null");
            return false;
        }
        return "2G98".equals(dij.e(str));
    }

    @Override // com.huawei.health.device.api.EcologyDeviceApi
    public String getNonWearableDeviceName(cjv cjvVar) {
        String str = "";
        if (cjvVar == null) {
            LogUtil.a("EcologyDeviceImpl", "deviceGroupInfo is null ");
            return "";
        }
        Object i = cjvVar.i();
        dcz dczVar = i instanceof dcz ? (dcz) i : null;
        if (dczVar == null) {
            LogUtil.a("EcologyDeviceImpl", "productInfo is null ");
            return "";
        }
        if (dczVar.n() == null) {
            LogUtil.a("EcologyDeviceImpl", "productInfo manifest is null ");
            return "";
        }
        ContentValues FT_ = cjvVar.FT_();
        if (FT_ != null) {
            if (!TextUtils.isEmpty(FT_.getAsString("sn"))) {
                str = FT_.getAsString("sn");
            } else {
                str = FT_.getAsString("uniqueId");
            }
        }
        if (dczVar.e() != null && dczVar.e().size() <= 0) {
            return d(dczVar.n().b(), str);
        }
        if (!TextUtils.isEmpty(str)) {
            return d(dcx.d(dczVar.t(), dczVar.n().b()), str);
        }
        return dcx.d(dczVar.t(), dczVar.n().b());
    }

    @Override // com.huawei.health.device.api.EcologyDeviceApi
    public void setPreference(String str, String str2) {
        deb.b(str, str2);
    }

    @Override // com.huawei.health.device.api.EcologyDeviceApi
    public MeasurableDevice fromBluetoothDevice(BluetoothDevice bluetoothDevice) {
        return cxh.Ra_(bluetoothDevice);
    }

    @Override // com.huawei.health.device.api.EcologyDeviceApi
    public void syncCloud(int i, IBaseResponseCallback iBaseResponseCallback) {
        syncCloud(i, false, iBaseResponseCallback);
    }

    @Override // com.huawei.health.device.api.EcologyDeviceApi
    public void syncCloud(int i, boolean z, IBaseResponseCallback iBaseResponseCallback) {
        ddx.a().b(i, z, iBaseResponseCallback);
    }

    private String d(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("EcologyDeviceImpl", "name is empty");
            return "";
        }
        if (str.toUpperCase(Locale.ROOT).contains(e(str2).toUpperCase(Locale.ROOT))) {
            return str;
        }
        return str + e(str2).toUpperCase(Locale.ROOT);
    }

    private String e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.c("EcologyDeviceImpl", "getDeviceIdentification identification is null");
            return "";
        }
        if (str.replace(":", "").length() < 3) {
            LogUtil.c("EcologyDeviceImpl", "identification's length less than 3");
            return Constants.LINK + str.replace(":", "");
        }
        return Constants.LINK + str.replace(":", "").substring(str.replace(":", "").length() - 3);
    }

    @Override // com.huawei.health.device.api.EcologyDeviceApi
    public List<HiHealthData> assembleRopeCaloriePoints(List<HiHealthData> list) {
        return cyo.d(list);
    }

    @Override // com.huawei.health.device.api.EcologyDeviceApi
    public String getProdId(String str) {
        return dij.e(str);
    }

    @Override // com.huawei.health.device.api.EcologyDeviceApi
    public void requestPerformanceRank(IBaseResponseCallback iBaseResponseCallback) {
        ddr.d().c(iBaseResponseCallback);
    }

    @Override // com.huawei.health.device.api.EcologyDeviceApi
    public float queryPerformanceRank(String str, float f, DateType dateType) {
        return ddr.d().b(str, f, dateType);
    }

    @Override // com.huawei.health.device.api.EcologyDeviceApi
    public void quaryPerformanceOfBest(IBaseResponseCallback iBaseResponseCallback, DateType dateType) {
        LogUtil.c("EcologyDeviceImpl", "quaryPerformanceOfBest()");
        ddr.d().e(iBaseResponseCallback, dateType);
    }

    @Override // com.huawei.health.device.api.EcologyDeviceApi
    public void ropeDataSetDeviceType(MotionPathSimplify motionPathSimplify) {
        if (motionPathSimplify == null) {
            LogUtil.a("EcologyDeviceImpl", "HighRopeSetDeviceType() motionPathSimplify is null");
            return;
        }
        ProductMapInfo a2 = dij.a(cei.b().getProductId());
        if (a2 == null) {
            LogUtil.a("EcologyDeviceImpl", "HighRopeSetDeviceType() productMapInfo is null");
        } else {
            motionPathSimplify.saveDeviceType(a2.c());
            motionPathSimplify.saveProductId(a2.f());
        }
    }

    @Override // com.huawei.health.device.api.EcologyDeviceApi
    public void ropeSetFitnessMachineControl(int i, int i2, int[] iArr) {
        dds.c().c(i, i2, iArr);
    }

    @Override // com.huawei.health.device.api.EcologyDeviceApi
    public boolean isDeviceConnected(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("EcologyDeviceImpl", "isDeviceConnected() productId is Empty.");
            return false;
        }
        ArrayList<ContentValues> bondedDeviceByProductId = ((DeviceManagerApi) Services.c("PluginDevice", DeviceManagerApi.class)).getBondedDeviceByProductId(str);
        BrUtils brUtils = new BrUtils();
        Iterator<ContentValues> it = bondedDeviceByProductId.iterator();
        while (it.hasNext()) {
            if (brUtils.d(it.next().getAsString("uniqueId"))) {
                return true;
            }
        }
        return false;
    }
}
