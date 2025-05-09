package com.huawei.pluginhealthalgorithm;

import com.huawei.hihealth.HiHealthData;
import com.huawei.hwbasemgr.ResponseCallback;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public interface PpgHeartStudyAlgorithmApi {
    public static final int ERROR_CODE_SUCCESS = 0;

    JSONObject getDrawData(List<JSONObject> list, List<JSONObject> list2, int i, int i2);

    HiHealthData getPeriodMeasureResult(HiHealthData hiHealthData);

    int getSqiResult(List<JSONObject> list, int i, int i2);

    void setPpgAccData(String str);

    int setProductModelPara(int i, int i2);

    void startPpgAlgorithm(ResponseCallback responseCallback);

    void stopPpgAlgorithm();
}
