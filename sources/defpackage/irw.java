package defpackage;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealthservice.postprocessing.HiPostProcessAction;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes7.dex */
public class irw implements HiPostProcessAction {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<Integer, Integer> f13564a;
    private final Map<Integer, irv> c = new HashMap();

    static {
        HashMap hashMap = new HashMap(1);
        f13564a = hashMap;
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.VASCULAR_HEALTH.value()), Integer.valueOf(DicDataTypeUtil.DataType.ARTERIAL_ELASTICITY.value()));
    }

    public boolean c(Integer num, irv irvVar) {
        if (this.c.containsKey(num)) {
            return false;
        }
        this.c.put(num, irvVar);
        return true;
    }

    @Override // com.huawei.hihealthservice.postprocessing.HiPostProcessAction
    public boolean postProcessAction(HiHealthData hiHealthData, int i) {
        irv irvVar = this.c.get(Integer.valueOf(i));
        irvVar.a();
        irvVar.e();
        irvVar.setCreateTime(System.currentTimeMillis());
        if (TextUtils.isEmpty(irvVar.getMsgContent())) {
            LogUtil.h("HiNotifyAction", "postProcessAction post message failed by empty content");
            return false;
        }
        LogUtil.a("HiNotifyAction", "postProcessAction message id:", irvVar.getMsgId());
        ((MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class)).showNotification(BaseApplication.getContext(), irvVar);
        return true;
    }

    @Override // com.huawei.hihealthservice.postprocessing.HiPostProcessAction
    public Set<Integer> getDataTypes() {
        return this.c.keySet();
    }

    @Override // com.huawei.hihealthservice.postprocessing.HiPostProcessAction
    public boolean beforePostProcessAction(HiHealthData hiHealthData, int i) {
        int syncStatus = hiHealthData.getSyncStatus();
        if (syncStatus == 1) {
            LogUtil.a("HiNotifyAction", "beforePostProcessAction() syncStatus=", Integer.valueOf(syncStatus));
            return false;
        }
        HiDeviceInfo d = ijf.d(BaseApplication.getContext()).d(hiHealthData.getDeviceUuid());
        int deviceType = d != null ? d.getDeviceType() : 0;
        LogUtil.a("HiNotifyAction", "beforePostProcessAction() syncStatus=", Integer.valueOf(syncStatus), ", deviceType=", Integer.valueOf(deviceType));
        if (deviceType == 0 || deviceType == 1 || "-1".equals(hiHealthData.getDeviceUuid())) {
            return false;
        }
        if (i == 10002) {
            return a(hiHealthData, i, deviceType);
        }
        if (i == DicDataTypeUtil.DataType.ARRHYTHMIA_RESULT_TYPE.value() && !e(hiHealthData, i)) {
            return false;
        }
        if (i == DicDataTypeUtil.DataType.MEDICAL_EXAM_REPORT.value()) {
            HashMap hashMap = new HashMap(1);
            hashMap.put("reportTime", Long.toString(hiHealthData.getStartTime()));
            a(this.c.get(Integer.valueOf(i)), hashMap);
            return true;
        }
        Map<Integer, Integer> map = f13564a;
        return !map.containsKey(Integer.valueOf(i)) || hiHealthData.getType() == map.get(Integer.valueOf(i)).intValue();
    }

    private boolean e(HiHealthData hiHealthData, int i) {
        int i2;
        int intValue;
        if (!Utils.o() && !CommonUtil.aq()) {
            return false;
        }
        if (hiHealthData.getType() == DicDataTypeUtil.DataType.PPG_IRREGULAR_HEARTBEAT.value()) {
            intValue = hiHealthData.getIntValue();
            HiHealthData a2 = a(hiHealthData, DicDataTypeUtil.DataType.ARRHYTHMIA_MEASURE_TYPE.value());
            if (a2 == null) {
                LogUtil.a("HiNotifyAction", "beforePostActionPpg missing require data: ARRHYTHMIA_MEASURE_TYPE");
                return false;
            }
            i2 = a2.getIntValue();
        } else if (hiHealthData.getType() == DicDataTypeUtil.DataType.ARRHYTHMIA_MEASURE_TYPE.value()) {
            int intValue2 = hiHealthData.getIntValue();
            HiHealthData a3 = a(hiHealthData, DicDataTypeUtil.DataType.PPG_IRREGULAR_HEARTBEAT.value());
            if (a3 == null) {
                LogUtil.a("HiNotifyAction", "beforePostActionPpg missing require data: PPG_IRREGULAR_HEARTBEAT");
                return false;
            }
            i2 = intValue2;
            intValue = a3.getIntValue();
        } else {
            LogUtil.a("HiNotifyAction", "ARRHYTHMIA_RESULT_TYPE data ignore");
            return false;
        }
        return b(hiHealthData, i, intValue, i2);
    }

    private boolean b(HiHealthData hiHealthData, int i, int i2, int i3) {
        irv irvVar = this.c.get(Integer.valueOf(i));
        int i4 = R.string.IDS_ppg_sync_notify_premature_beat_suspect;
        if (i2 != 2) {
            int i5 = R.string.IDS_ppg_sync_notify_atrial_fibrillation_suspect;
            if (i2 == 3) {
                irvVar.e(R.string.IDS_ppg_sync_notify_atrial_fibrillation_suspect);
            } else if (i2 == 4) {
                if (Utils.o()) {
                    i5 = R.string.IDS_ppg_sync_notify_atrial_fibrillation_risk;
                }
                irvVar.e(i5);
            } else if (i2 == 5) {
                if (Utils.o()) {
                    i4 = R.string.IDS_ppg_sync_notify_premature_beat_risk;
                }
                irvVar.e(i4);
            } else {
                LogUtil.a("HiNotifyAction", "PPG_IRREGULAR_HEARTBEAT status without abnormal");
                return false;
            }
        } else {
            irvVar.e(R.string.IDS_ppg_sync_notify_premature_beat_suspect);
        }
        HiDeviceInfo d = ijf.d(BaseApplication.getContext()).d(hiHealthData.getDeviceUuid());
        if (d == null) {
            LogUtil.b("HiNotifyAction", "get an invalid device name during beforePostActionPpg");
            return false;
        }
        HashMap hashMap = new HashMap(5);
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, d.getDeviceName());
        hashMap.put("measureType", Integer.toString(i3));
        hashMap.put("start_time", Long.toString(hiHealthData.getStartTime()));
        hashMap.put("end_time", Long.toString(hiHealthData.getEndTime()));
        hashMap.put("heartRateStatus", Integer.toString(i2));
        a(irvVar, hashMap);
        this.c.put(Integer.valueOf(i), irvVar);
        return true;
    }

    private HiHealthData a(HiHealthData hiHealthData, int i) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(hiHealthData.getStartTime(), hiHealthData.getEndTime());
        hiDataReadOption.setType(new int[]{i});
        Context context = BaseApplication.getContext();
        List<HiHealthData> b = ijj.a(context).b(hiDataReadOption, i, iis.d().g(igm.e().d()), (ifl) null);
        if (koq.b(b)) {
            return null;
        }
        return b.get(0);
    }

    private boolean a(HiHealthData hiHealthData, int i, int i2) {
        if (hiHealthData.getType() == DicDataTypeUtil.DataType.BLOOD_BEFORE_ACTIVITY.value()) {
            LogUtil.a("HiNotifyAction", "BLOOD_BEFORE_ACTIVITY data ignore");
            return false;
        }
        LogUtil.a("HiNotifyAction", "start beforePostProcessAction of DATA_SET_BLOODPRESSURE_EX");
        irv irvVar = this.c.get(Integer.valueOf(i));
        d(irvVar, hiHealthData.getStartTime());
        if (a(i2)) {
            LogUtil.a("HiNotifyAction", "bloodPressure not from watch");
            irvVar.setGroupSummary(false);
        } else {
            irvVar.setGroupSummary(true);
        }
        this.c.put(Integer.valueOf(i), irvVar);
        LogUtil.a("HiNotifyAction", "end beforePostProcessAction of DATA_SET_BLOODPRESSURE_EX");
        return i2 != 10001;
    }

    private boolean a(int i) {
        Iterator<ProductMapInfo> it = ProductMap.b("deviceId", String.valueOf(i)).iterator();
        while (it.hasNext()) {
            if ("02B".equals(it.next().e())) {
                return true;
            }
        }
        return false;
    }

    private void d(irv irvVar, long j) {
        if (irvVar == null || j == 0) {
            return;
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("key_marker_view_time", Long.toString(j));
        a(irvVar, hashMap);
    }

    private void a(irv irvVar, Map<String, String> map) {
        Uri parse = Uri.parse(irvVar.c());
        Uri.Builder buildUpon = parse.buildUpon();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            if (!parse.getQueryParameterNames().contains(key)) {
                String value = entry.getValue();
                if (value == null) {
                    value = "";
                }
                buildUpon.appendQueryParameter(key, value);
            }
        }
        String uri = buildUpon.build().toString();
        LogUtil.c("HiNotifyAction", "addParamsIntoMsgUri result uri: ", uri);
        irvVar.setDetailUri(uri);
    }
}
