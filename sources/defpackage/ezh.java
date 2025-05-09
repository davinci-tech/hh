package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import com.google.gson.annotations.SerializedName;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.main.api.WeChatApi;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class ezh {

    /* renamed from: a, reason: collision with root package name */
    private static ezh f12394a;
    private static final Object b = new Object();
    private Context d;

    private ezh(Context context) {
        this.d = null;
        if (context == null) {
            LogUtil.h("StepCounterDataHelper", "context is null");
            this.d = BaseApplication.getContext();
        } else {
            this.d = context;
        }
    }

    public static ezh a(Context context) {
        ezh ezhVar;
        synchronized (b) {
            if (f12394a == null) {
                f12394a = new ezh(context);
            }
            ezhVar = f12394a;
        }
        return ezhVar;
    }

    public String e() {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        long b2 = DateFormatUtil.b(System.currentTimeMillis());
        hiDataReadOption.setTimeInterval(String.valueOf(b2), String.valueOf(b2), HiDataReadOption.TimeFormatType.DATE_FORMAT_YYYY_MM_DD);
        hiDataReadOption.setType(new int[]{2});
        final String[] strArr = {HiJsonUtil.e(new ArrayList())};
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        HiHealthNativeApi.a(this.d).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: ezh.5
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                if (i == 0) {
                    strArr[0] = HiJsonUtil.e(ezh.this.d((List) ((SparseArray) obj).get(2)));
                } else {
                    LogUtil.b("StepCounterDataHelper", "Error query step counter, errorCode = ", Integer.valueOf(i));
                }
                countDownLatch.countDown();
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
                countDownLatch.countDown();
            }
        });
        try {
            LogUtil.a("StepCounterDataHelper", "getStepCounterDetails await: ", Boolean.valueOf(countDownLatch.await(500L, TimeUnit.MILLISECONDS)));
        } catch (InterruptedException unused) {
            LogUtil.b("StepCounterDataHelper", "getStepCounterDetails InterruptedException");
        }
        LogUtil.a("StepCounterDataHelper", "getStepCounterDetails: ", strArr[0]);
        return strArr[0];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<b> d(List<HiHealthData> list) {
        HashMap hashMap = new HashMap();
        for (HiHealthData hiHealthData : list) {
            String string = hiHealthData.getString(PluginPayAdapter.KEY_DEVICE_INFO_MODEL);
            if (TextUtils.isEmpty(string)) {
                string = hiHealthData.getString(PluginPayAdapter.KEY_DEVICE_INFO_NAME);
            }
            int intValue = hiHealthData.getIntValue();
            if (hashMap.containsKey(string)) {
                hashMap.put(string, Integer.valueOf(((Integer) hashMap.get(string)).intValue() + intValue));
            } else {
                hashMap.put(string, Integer.valueOf(intValue));
            }
        }
        ArrayList arrayList = new ArrayList();
        for (String str : hashMap.keySet()) {
            arrayList.add(new b(str, ((Integer) hashMap.get(str)).intValue()));
        }
        return arrayList;
    }

    public String b() {
        final String[] strArr = {""};
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ThreadPoolManager.d().execute(new Runnable() { // from class: ezh.2
            @Override // java.lang.Runnable
            public void run() {
                strArr[0] = ((WeChatApi) Services.c("Main", WeChatApi.class)).isUserBinded(ezh.this.d);
                countDownLatch.countDown();
            }
        });
        try {
            LogUtil.a("StepCounterDataHelper", "getUserBindStatus await: ", Boolean.valueOf(countDownLatch.await(5000L, TimeUnit.MILLISECONDS)));
        } catch (InterruptedException unused) {
            LogUtil.b("StepCounterDataHelper", "getUserBindStatus InterruptedException");
        }
        return strArr[0];
    }

    /* loaded from: classes3.dex */
    static class b {

        @SerializedName("stepCounter")
        private int b;

        @SerializedName("deviceName")
        private String d;

        public b(String str, int i) {
            this.d = str;
            this.b = i;
        }
    }
}
