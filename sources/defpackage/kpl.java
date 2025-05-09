package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.model.HiStressBreatheMetaData;
import com.huawei.hihealth.data.model.HiStressGameMetaData;
import com.huawei.hihealth.data.model.HiStressMetaData;
import com.huawei.hihealth.data.model.HiStressRelaxMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes5.dex */
public class kpl {
    private static kpl b;
    private static final Object e = new Object();
    private ExecutorService d = Executors.newSingleThreadExecutor();
    private Context c = BaseApplication.getContext();

    private kpl() {
    }

    public static kpl c() {
        kpl kplVar;
        synchronized (e) {
            if (b == null) {
                b = new kpl();
            }
            kplVar = b;
        }
        return kplVar;
    }

    public void e(long j, long j2, IBaseResponseCallback iBaseResponseCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(j * 1000);
        hiDataReadOption.setEndTime(j2 * 1000);
        int[] iArr = {2034};
        hiDataReadOption.setType(iArr);
        LogUtil.a("PressureMeasureModelInterator", "getStressDetailDatas errorCode setType =", Integer.valueOf(iArr[0]));
        c(iBaseResponseCallback, hiDataReadOption, 1);
    }

    public void a(long j, long j2, IBaseResponseCallback iBaseResponseCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(j * 1000);
        hiDataReadOption.setEndTime(j2 * 1000);
        hiDataReadOption.setType(new int[]{44306});
        c(iBaseResponseCallback, hiDataReadOption, 4);
    }

    private void c(final IBaseResponseCallback iBaseResponseCallback, HiDataReadOption hiDataReadOption, final int i) {
        HiHealthNativeApi.a(this.c).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: kpl.5
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i2, Object obj, int i3, int i4) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i2, int i3) {
                LogUtil.a("PressureMeasureModelInterator", "getDetailDatas errorCode = ", Integer.valueOf(i2));
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 == null) {
                    LogUtil.h("PressureMeasureModelInterator", "callback is null");
                }
                if (obj == null) {
                    iBaseResponseCallback2.d(-1, null);
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() <= 0) {
                    iBaseResponseCallback.d(-1, null);
                    return;
                }
                int i4 = i;
                switch (i4) {
                    case 1:
                        kpl.this.bPS_(sparseArray, iBaseResponseCallback);
                        break;
                    case 2:
                        kpl.this.bPT_(sparseArray, iBaseResponseCallback);
                        break;
                    case 3:
                        kpl.this.bPP_(sparseArray, iBaseResponseCallback);
                        break;
                    case 4:
                        kpl.this.bPR_(sparseArray, iBaseResponseCallback);
                        break;
                    case 5:
                        kpl.this.bPO_(sparseArray, iBaseResponseCallback);
                        break;
                    case 6:
                        kpl.this.bPQ_(sparseArray, iBaseResponseCallback);
                        break;
                    default:
                        LogUtil.h("PressureMeasureModelInterator", "dataType = ", Integer.valueOf(i4));
                        break;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bPS_(SparseArray<Object> sparseArray, IBaseResponseCallback iBaseResponseCallback) {
        List list = (List) sparseArray.get(2034);
        LogUtil.a("PressureMeasureModelInterator", "pressureMeasureValueList.size() = ", Integer.valueOf(list.size()));
        e(list, iBaseResponseCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bPT_(SparseArray<Object> sparseArray, IBaseResponseCallback iBaseResponseCallback) {
        ArrayList arrayList = new ArrayList();
        Iterator it = ((List) sparseArray.get(2034)).iterator();
        while (it.hasNext()) {
            arrayList.add((HiStressMetaData) HiJsonUtil.e(((HiHealthData) it.next()).getMetaData(), HiStressMetaData.class));
        }
        LogUtil.a("PressureMeasureModelInterator", "stressMetaDatasList.size() = ", Integer.valueOf(arrayList.size()));
        e(arrayList, iBaseResponseCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bPP_(SparseArray<Object> sparseArray, IBaseResponseCallback iBaseResponseCallback) {
        ArrayList arrayList = new ArrayList();
        Iterator it = ((List) sparseArray.get(2036)).iterator();
        while (it.hasNext()) {
            arrayList.add((HiStressGameMetaData) HiJsonUtil.e(((HiHealthData) it.next()).getMetaData(), HiStressGameMetaData.class));
        }
        LogUtil.a("PressureMeasureModelInterator", "stressGameMetaDatasList = ", arrayList.toString());
        e(arrayList, iBaseResponseCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bPR_(SparseArray<Object> sparseArray, IBaseResponseCallback iBaseResponseCallback) {
        ArrayList arrayList = new ArrayList();
        for (HiHealthData hiHealthData : (List) sparseArray.get(44306)) {
            int intValue = hiHealthData.getIntValue();
            long startTime = hiHealthData.getStartTime();
            HiStressMetaData hiStressMetaData = new HiStressMetaData();
            hiStressMetaData.configStressScore(intValue);
            hiStressMetaData.configStressStartTime(startTime);
            arrayList.add(hiStressMetaData);
        }
        LogUtil.a("PressureMeasureModelInterator", "stressStatisticsMetaDatasList.size() = ", Integer.valueOf(arrayList.size()));
        e(arrayList, iBaseResponseCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bPO_(SparseArray<Object> sparseArray, IBaseResponseCallback iBaseResponseCallback) {
        ArrayList arrayList = new ArrayList();
        Iterator it = ((List) sparseArray.get(2037)).iterator();
        while (it.hasNext()) {
            arrayList.add((HiStressBreatheMetaData) HiJsonUtil.e(((HiHealthData) it.next()).getMetaData(), HiStressBreatheMetaData.class));
        }
        LogUtil.a("PressureMeasureModelInterator", "stressBreatheMetaDatasList = ", arrayList.toString());
        e(arrayList, iBaseResponseCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bPQ_(SparseArray<Object> sparseArray, IBaseResponseCallback iBaseResponseCallback) {
        ArrayList arrayList = new ArrayList();
        Iterator it = ((List) sparseArray.get(2035)).iterator();
        while (it.hasNext()) {
            arrayList.add((HiStressRelaxMetaData) HiJsonUtil.e(((HiHealthData) it.next()).getMetaData(), HiStressRelaxMetaData.class));
        }
        LogUtil.a("PressureMeasureModelInterator", "stressRelaxMetaDatasList = ", arrayList.toString());
        e(arrayList, iBaseResponseCallback);
    }

    private void e(List list, IBaseResponseCallback iBaseResponseCallback) {
        if (list.size() > 0) {
            iBaseResponseCallback.d(0, list);
        } else {
            iBaseResponseCallback.d(-1, -1);
        }
    }

    public void d(String str, List<HiStressMetaData> list, IBaseResponseCallback iBaseResponseCallback) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("PressureMeasureModelInterator", "metaDataList.isEmpty() or metaDataList is null setStressData ERR_NONE = ", 100001);
            iBaseResponseCallback.d(100001, null);
            return;
        }
        LogUtil.a("PressureMeasureModelInterator", "enter setStressData");
        ArrayList arrayList = new ArrayList();
        for (HiStressMetaData hiStressMetaData : list) {
            HiHealthData hiHealthData = new HiHealthData();
            hiHealthData.setValue(hiStressMetaData.fetchStressScore());
            hiHealthData.setDeviceUuid(str);
            hiHealthData.setTimeInterval(hiStressMetaData.fetchStressStartTime(), hiStressMetaData.fetchStressEndTime());
            hiHealthData.setType(2034);
            try {
                hiHealthData.setMetaData(HiJsonUtil.e(hiStressMetaData));
                arrayList.add(hiHealthData);
            } catch (IllegalArgumentException e2) {
                LogUtil.a("PressureMeasureModelInterator", "setStressData : ", e2.getMessage());
                iBaseResponseCallback.d(100001, null);
                return;
            }
        }
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        if (arrayList.isEmpty()) {
            iBaseResponseCallback.d(100001, null);
        } else {
            hiDataInsertOption.setDatas(arrayList);
            a(iBaseResponseCallback, hiDataInsertOption);
        }
    }

    public void b(String str, HiStressMetaData hiStressMetaData, IBaseResponseCallback iBaseResponseCallback) {
        if (hiStressMetaData == null) {
            return;
        }
        LogUtil.c("PressureMeasureModelInterator", "setStressData uuid = ", str);
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setValue(hiStressMetaData.fetchStressScore());
        hiHealthData.setDeviceUuid(str);
        hiHealthData.setTimeInterval(hiStressMetaData.fetchStressStartTime(), hiStressMetaData.fetchStressEndTime());
        hiHealthData.setType(2034);
        LogUtil.c("PressureMeasureModelInterator", "setStressData metaData = ", hiStressMetaData.toString());
        LogUtil.c("PressureMeasureModelInterator", "setStressData HiJsonUtil.toJson(metaData) = ", HiJsonUtil.e(hiStressMetaData));
        hiHealthData.setMetaData(HiJsonUtil.e(hiStressMetaData));
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.addData(hiHealthData);
        a(iBaseResponseCallback, hiDataInsertOption);
    }

    private void a(final IBaseResponseCallback iBaseResponseCallback, HiDataInsertOption hiDataInsertOption) {
        HiHealthNativeApi.a(this.c).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: kpl.4
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                LogUtil.a("PressureMeasureModelInterator", "errorCode = ", Integer.valueOf(i), "obj = ", HiJsonUtil.e(obj));
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 != null) {
                    iBaseResponseCallback2.d(i, obj);
                }
            }
        });
    }

    public void c(final Context context, final HiStressMetaData hiStressMetaData) {
        this.d.execute(new Runnable() { // from class: kpl.2
            @Override // java.lang.Runnable
            public void run() {
                HiUserPreference hiUserPreference = new HiUserPreference();
                hiUserPreference.setKey("custom.pressure_adjust_userinfo");
                String e2 = HiJsonUtil.e(hiStressMetaData);
                LogUtil.a("PressureMeasureModelInterator", "stressValues = ", e2);
                hiUserPreference.setValue(e2);
                HiHealthManager.d(context).setUserPreference(hiUserPreference);
            }
        });
    }

    public void a(final IBaseResponseCallback iBaseResponseCallback) {
        this.d.execute(new Runnable() { // from class: kpl.3
            @Override // java.lang.Runnable
            public void run() {
                HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("custom.pressure_adjust_userinfo");
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 == null) {
                    LogUtil.h("PressureMeasureModelInterator", "callback is null");
                    return;
                }
                if (userPreference != null) {
                    LogUtil.a("PressureMeasureModelInterator", "hiUserPreferenceBase != null");
                    if (!TextUtils.isEmpty(userPreference.getValue())) {
                        String value = userPreference.getValue();
                        LogUtil.a("PressureMeasureModelInterator", "getUserPressureAdjustDatas value = ", value);
                        iBaseResponseCallback.d(0, value);
                        return;
                    }
                    iBaseResponseCallback.d(100001, null);
                    return;
                }
                iBaseResponseCallback2.d(100006, null);
            }
        });
    }
}
