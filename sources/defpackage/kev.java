package defpackage;

import android.content.ContentValues;
import android.util.SparseArray;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.hwdictionarymgr.strategy.DictionarySequenceInterface;
import com.huawei.hwdevice.phoneprocess.mgr.hwsynctaskmanager.commonTask.base.TaskInterface;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class kev extends kew implements TaskInterface {
    private final DictionarySequenceInterface c;

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwsynctaskmanager.commonTask.base.TaskInterface
    public void handleEvent(DeviceInfo deviceInfo, cvp cvpVar) {
    }

    public kev(String str, String str2) {
        super(str, str2);
        this.c = new kbw();
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwsynctaskmanager.commonTask.base.TaskInterface
    public void handlePoint(DeviceInfo deviceInfo, cvi cviVar) {
        if (cviVar.d() == 500031) {
            int c = cviVar.c();
            for (cvo cvoVar : cviVar.b()) {
                long c2 = cvoVar.c();
                long d = cvoVar.d();
                if (c2 > d) {
                    LogUtil.b("EmotionTask", "start time > end time " + c2 + " " + d);
                    return;
                }
                d(deviceInfo, c2, d, c);
            }
            return;
        }
        LogUtil.b("EmotionTask", "not emotion id " + cviVar.d());
    }

    private cvi e(int i) {
        cvi cviVar = new cvi();
        cviVar.b(500031L);
        cviVar.setCommandId(4);
        cviVar.setWearPkgName(this.e);
        cviVar.setSrcPkgName(this.b);
        cviVar.a(i);
        cviVar.b(new ArrayList());
        return cviVar;
    }

    private void d(final DeviceInfo deviceInfo, final long j, final long j2, final int i) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setSortOrder(0);
        hiDataReadOption.setType(new int[]{500031});
        hiDataReadOption.setModifiedStartTime(j);
        hiDataReadOption.setModifiedEndTime(j2);
        hiDataReadOption.setReadType(0);
        LogUtil.a("EmotionTask", "readHiHealthData ", hiDataReadOption);
        HiHealthManager.d(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: kev.4
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i2, int i3) {
                LogUtil.a("EmotionTask", "onResult errorCode: ", Integer.valueOf(i2));
                if (obj == null) {
                    LogUtil.h("EmotionTask", "object is null");
                } else if (obj instanceof SparseArray) {
                    kev.this.bNs_((SparseArray) obj, deviceInfo, j, j2, i);
                } else {
                    LogUtil.h("EmotionTask", "sparseArray !instanceof no process");
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i2, Object obj, int i3, int i4) {
                LogUtil.a("EmotionTask", "onResultIntent intentType:", Integer.valueOf(i2), ", object:", obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bNs_(SparseArray<?> sparseArray, DeviceInfo deviceInfo, long j, long j2, int i) {
        cvi e = e(i);
        if (sparseArray.size() <= 0) {
            LogUtil.h("EmotionTask", "sparseArray size is zero");
        }
        LogUtil.a("EmotionTask", "query emotion data size: ", Integer.valueOf(sparseArray.size()));
        Object obj = sparseArray.get((int) e.d());
        if (!(obj instanceof List)) {
            LogUtil.h("EmotionTask", "dataObject not list");
        } else {
            c(deviceInfo, j, j2, (List) obj, e);
        }
    }

    private static void c(DeviceInfo deviceInfo, long j, long j2, List<?> list, cvi cviVar) {
        for (Object obj : list) {
            if (obj instanceof HiHealthData) {
                HiHealthData hiHealthData = (HiHealthData) obj;
                if (hiHealthData.getType() == 500031195) {
                    j = hiHealthData.getStartTime();
                    j2 = hiHealthData.getEndTime();
                    cvv e = e(hiHealthData);
                    if (e == null) {
                        LogUtil.b("EmotionTask", "get samplePointInfo failed");
                    } else {
                        cvo cvoVar = new cvo();
                        cvoVar.a().add(e);
                        cvoVar.b(hiHealthData.getModifiedTime());
                        cvoVar.a(hiHealthData.getStartTime());
                        cvoVar.c(hiHealthData.getEndTime());
                        cviVar.b().add(cvoVar);
                    }
                }
            } else {
                LogUtil.h("EmotionTask", "data not HiHealthData");
            }
        }
        LogUtil.a("EmotionTask", "send point emotion startTime: " + j + " endTime: " + j2);
        cuk.b().sendDictionaryPointInfoCommand(deviceInfo, cviVar, "EmotionTask");
    }

    private static cvv e(HiHealthData hiHealthData) {
        cvv cvvVar = new cvv();
        cvvVar.d(hiHealthData.getType());
        ContentValues valueHolder = hiHealthData.getValueHolder();
        if (valueHolder == null) {
            LogUtil.b("EmotionTask", "get value is null");
            return null;
        }
        Double asDouble = valueHolder.getAsDouble("point_value");
        if (asDouble == null) {
            LogUtil.b("EmotionTask", "get double value is null");
            return null;
        }
        cvvVar.b(blq.i(asDouble.intValue()));
        return cvvVar;
    }
}
