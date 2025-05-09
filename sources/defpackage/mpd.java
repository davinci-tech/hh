package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.api.phoneprocess.SampleSequenceAfterProcess;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mpd implements SampleSequenceAfterProcess {
    @Override // com.huawei.health.devicemgr.api.phoneprocess.SampleSequenceAfterProcess
    public HiHealthData onSamplSequenceAfterProcess(HiHealthData hiHealthData, String str) {
        JSONObject jSONObject;
        boolean z;
        DeviceInfo d;
        String metaData = hiHealthData.getMetaData();
        if (metaData == null) {
            ReleaseLogUtil.e("ServicesApi_MicroExamSampleSequenceAfterProcess", "onSamplSequenceAfterProcess() skipped by empty content");
            return hiHealthData;
        }
        try {
            jSONObject = new JSONObject(metaData);
            if (jSONObject.has("name")) {
                z = true;
            } else {
                jSONObject.put("name", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1002));
                z = false;
            }
            d = d();
        } catch (JSONException unused) {
            ReleaseLogUtil.c("ServicesApi_MicroExamSampleSequenceAfterProcess", "onSamplSequenceAfterProcess() failed by JSONException");
        }
        if (d == null) {
            ReleaseLogUtil.d("ServicesApi_MicroExamSampleSequenceAfterProcess", "onSamplSequenceAfterProcess() connected device NOT found");
            return hiHealthData;
        }
        String deviceName = d.getDeviceName();
        if (!TextUtils.isEmpty(deviceName)) {
            jSONObject.put("deviceName", deviceName);
            z = false;
        }
        if (!jSONObject.has("deviceType")) {
            jSONObject.put("deviceType", d.getProductType());
        } else if (z) {
            ReleaseLogUtil.e("ServicesApi_MicroExamSampleSequenceAfterProcess", "onSamplSequenceAfterProcess() data NOT change");
            return hiHealthData;
        }
        ReleaseLogUtil.e("ServicesApi_MicroExamSampleSequenceAfterProcess", "onSamplSequenceAfterProcess() data filled");
        hiHealthData.setMetaData(jSONObject.toString());
        return hiHealthData;
    }

    private DeviceInfo d() {
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cuo.d().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "ServicesApi_MicroExamSampleSequenceAfterProcess");
        if (koq.b(deviceList)) {
            return null;
        }
        Iterator<DeviceInfo> it = deviceList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DeviceInfo next = it.next();
            if (next != null && !cvt.c(next.getProductType())) {
                deviceInfo = next;
                break;
            }
        }
        LogUtil.a("ServicesApi_MicroExamSampleSequenceAfterProcess", "getConnectDeviceInfo() deviceInfo ", deviceInfo);
        return deviceInfo;
    }
}
