package defpackage;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.health.device.api.WeightDataUtilsApi;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class dkw {
    public static void Wr_(final Handler handler, String str) {
        if (handler == null || TextUtils.isEmpty(str)) {
            LogUtil.h("HealthDataQueryUtils", "queryLastUricAcidData handler = null or uniqueId is empty");
            return;
        }
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(0L, System.currentTimeMillis());
        hiDataReadOption.setCount(1);
        hiDataReadOption.setDeviceUuid(str);
        hiDataReadOption.setReadType(2);
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setType(new int[]{2109});
        HiHealthManager.d(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: dkw.4
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                dkw.Wn_((SparseArray) obj, handler);
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
                LogUtil.a("HealthDataQueryUtils", "queryLastUricAcidData onResultIntent");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void Wn_(SparseArray<Object> sparseArray, Handler handler) {
        if (sparseArray == null || sparseArray.size() == 0) {
            LogUtil.a("HealthDataQueryUtils", "queryLastUricAcidData map = null or map size =0 ");
            return;
        }
        Object obj = sparseArray.get(2109);
        HiHealthData hiHealthData = null;
        if (obj instanceof List) {
            for (HiHealthData hiHealthData2 : (List) obj) {
                if (hiHealthData2.getEndTime() < System.currentTimeMillis()) {
                    hiHealthData = hiHealthData2;
                }
            }
        }
        Message obtainMessage = handler.obtainMessage();
        obtainMessage.what = 106;
        obtainMessage.obj = hiHealthData;
        handler.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void Wt_(Handler handler, int i, Object obj) {
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = obj;
        if (handler != null) {
            handler.sendMessage(obtain);
        }
    }

    public static void Ws_(Handler handler, int i) {
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(((WeightDataUtilsApi) Services.c("PluginDevice", WeightDataUtilsApi.class)).buildHiAggregateOption(), new b(handler, i));
    }

    public static void Wp_(Handler handler, int i) {
        HiAggregateOption b2 = b();
        b2.setStartTime(0L);
        b2.setEndTime(System.currentTimeMillis());
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(b2, new b(handler, i));
    }

    public static void Wq_(Handler handler, int i) {
        HiHealthManager.d(BaseApplication.getContext()).readHiHealthData(a(HiHealthDataType.b), new e(handler, i, HiHealthDataType.b));
    }

    public static void Wo_(Handler handler, int i) {
        HiHealthManager.d(BaseApplication.getContext()).readHiHealthData(a(2103), new e(handler, i, 2103));
    }

    private static HiAggregateOption b() {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setType(new int[]{2006, 2007});
        hiAggregateOption.setConstantsKey(new String[]{"BLOOD_PRESSURE_SYSTOLIC", "BLOOD_PRESSURE_DIASTOLIC"});
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setCount(1);
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setSortOrder(1);
        return hiAggregateOption;
    }

    private static HiDataReadOption a(int i) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(0L, System.currentTimeMillis());
        hiDataReadOption.setCount(1);
        hiDataReadOption.setReadType(0);
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setType(new int[]{i});
        return hiDataReadOption;
    }

    static class b implements HiAggregateListener {
        private Handler b;
        private int d;

        b(Handler handler, int i) {
            this.b = handler;
            this.d = i;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            LogUtil.a("HealthDataQueryUtils", "query onResult called");
            if (!koq.b(list)) {
                dkw.Wt_(this.b, this.d, list.get(0));
                return;
            }
            LogUtil.h("HealthDataQueryUtils", "query onResult data is null");
            Message.obtain().what = 103;
            dkw.Wt_(this.b, 103, null);
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            LogUtil.a("HealthDataQueryUtils", "onResultIntent errorCode = ", Integer.valueOf(i2));
        }
    }

    static class e implements HiDataReadResultListener {
        private Handler b;
        private int c;
        private int e;

        e(Handler handler, int i, int i2) {
            this.b = handler;
            this.e = i;
            this.c = i2;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            LogUtil.a("HealthDataQueryUtils", "DataReadResultListener onResult mQueryType: ", Integer.valueOf(this.c), " onResult called");
            if (obj instanceof SparseArray) {
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() <= 0) {
                    dkw.Wt_(this.b, 103, null);
                } else if (sparseArray.get(this.c) instanceof ArrayList) {
                    dkw.Wt_(this.b, this.e, ((ArrayList) sparseArray.get(this.c)).get(0));
                }
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
            LogUtil.a("HealthDataQueryUtils", "DataReadResultListener onResultIntent mQueryType: ", Integer.valueOf(this.c), " onResultIntent errorCode = ", Integer.valueOf(i2));
        }
    }
}
