package defpackage;

import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.ble.BleJsDataApi;
import com.huawei.operation.share.ResultCallback;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class cyo {
    public static List<HiHealthData> d(List<HiHealthData> list) {
        int i;
        Iterator<HiHealthData> it;
        ArrayList arrayList = new ArrayList(10);
        int i2 = 1;
        try {
            Iterator<HiHealthData> it2 = list.iterator();
            while (it2.hasNext()) {
                HiHealthData next = it2.next();
                JSONObject jSONObject = new JSONObject(next.getMetaData());
                if (next.getType() == 30001 && jSONObject.getInt(BleConstants.SPORT_TYPE) == 283) {
                    float f = jSONObject.getInt(BleConstants.TOTAL_CALORIES);
                    long startTime = next.getStartTime();
                    long j = startTime - (startTime % 60000);
                    long endTime = next.getEndTime();
                    long j2 = endTime - startTime;
                    if (Math.abs(f) > 0.001f && j2 > 0) {
                        if (j2 % 60000 > 0) {
                            i = ((int) (j2 / 60000)) + i2;
                        } else {
                            i = (int) (j2 / 60000);
                        }
                        float f2 = f / i;
                        while (true) {
                            HiHealthData hiHealthData = new HiHealthData(4);
                            it = it2;
                            long j3 = j + 60000;
                            hiHealthData.setTimeInterval(j, j3);
                            HiHealthData hiHealthData2 = new HiHealthData(20003);
                            hiHealthData2.setTimeInterval(j, j3);
                            startTime += 60000;
                            hiHealthData.setValue(f2);
                            hiHealthData.setDeviceUuid(next.getDeviceUuid());
                            arrayList.add(hiHealthData);
                            hiHealthData2.setValue(0);
                            hiHealthData2.setDeviceUuid(next.getDeviceUuid());
                            arrayList.add(hiHealthData2);
                            if (startTime >= endTime) {
                                break;
                            }
                            j = j3;
                            it2 = it;
                        }
                        it2 = it;
                        i2 = 1;
                    }
                }
                return arrayList;
            }
        } catch (JSONException unused) {
            LogUtil.a("PDROPE_RopeCalorieProcessor", "assembleCaloriePoints JSONException");
        }
        return arrayList;
    }

    public void a(List<HiHealthData> list) {
        LogUtil.a("PDROPE_RopeCalorieProcessor", "saveHealthDataToPlatform");
        if (list == null || list.isEmpty()) {
            return;
        }
        List<HiHealthData> d = d(list);
        if (d.isEmpty()) {
            return;
        }
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.setDatas(d);
        BleJsDataApi.getInstance().saveSample(hiDataInsertOption, 3, new ResultCallback() { // from class: cyn
            @Override // com.huawei.operation.share.ResultCallback
            public final void onResult(int i, Object obj) {
                LogUtil.a("PDROPE_RopeCalorieProcessor", "saveHealthDataToPlatform resultCode：", Integer.valueOf(i));
            }
        });
    }
}
