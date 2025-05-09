package com.huawei.health.marketing.utils;

import android.util.SparseArray;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.marketing.utils.EcgDataInquirer;
import com.huawei.health.section.section.MeasureCardItemHolder;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.util.SharedPreferenceUtil;
import defpackage.fbp;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class EcgDataInquirer {
    public static final int ECG_ARRHY_TYPE_AFIB = 8;
    public static final int ECG_ARRHY_TYPE_AMBIGUOUS = 64;
    public static final int ECG_ARRHY_TYPE_AMBIGUOUS_BAD_SIGNAL = 128;
    public static final int ECG_ARRHY_TYPE_AMBIGUOUS_TOO_HIGH = 16;
    public static final int ECG_ARRHY_TYPE_AMBIGUOUS_TOO_LOW = 32;
    public static final int ECG_ARRHY_TYPE_PAC = 2;
    public static final int ECG_ARRHY_TYPE_PVC = 4;
    public static final int ECG_ARRHY_TYPE_SINUS = 1;
    public static final int ECG_ARR_TYPE_INVALID = 0;
    public static final String ECG_DATA_OBSERVER = "ECG_DATA_OBSERVER";
    private static final String TAG = "EcgDataInquirer";
    public static final int UPDATE_ALL = 63;
    public static final int UPDATE_ECG_ARRHYTHMIA_CHART = 8;
    public static final int UPDATE_ECG_ARRHYTHMIA_DESC = 4;
    public static final int UPDATE_ECG_NORMAL_CHART = 2;
    public static final int UPDATE_ECG_NORMAL_DESC = 1;
    public static final int UPDATE_ECG_VASCULAR_CHART = 32;
    public static final int UPDATE_ECG_VASCULAR_DESC = 16;
    private static final int VALID_MASK_MAX_READ_COUNT = 50;
    protected int[] mDataTypes;
    private volatile boolean mIsQuerying;
    protected fbp mMeasureCardBean;

    protected abstract void onQueryDetailDone(SparseArray<List<HiHealthData>> sparseArray);

    protected abstract void onQuerySummaryDone(SparseArray<List<HiHealthData>> sparseArray);

    public EcgDataInquirer(int[] iArr, MeasureCardItemHolder.MeasureType measureType) {
        this.mDataTypes = iArr;
        this.mMeasureCardBean = new fbp(measureType);
    }

    private void clearData() {
        fbp fbpVar = this.mMeasureCardBean;
        if (fbpVar != null) {
            fbpVar.a();
        }
    }

    public boolean isQuerying() {
        return this.mIsQuerying;
    }

    public void setQuerying(boolean z) {
        this.mIsQuerying = z;
    }

    public void clearAndRefreshUI() {
        clearData();
        ObserverManagerUtil.c(ECG_DATA_OBSERVER, 63);
    }

    public void querySummary() {
        if ("1".equals(SharedPreferenceUtil.getInstance(BaseApplication.e()).getAccountType())) {
            clearAndRefreshUI();
            return;
        }
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(0L);
        hiDataReadOption.setEndTime(System.currentTimeMillis());
        hiDataReadOption.setCount(50);
        hiDataReadOption.setType(this.mDataTypes);
        HiDataReadProOption e = HiDataReadProOption.builder().e(hiDataReadOption).b(1).e();
        this.mIsQuerying = true;
        LogUtil.a(TAG, "querySummary: dataTypes = ", Arrays.toString(this.mDataTypes));
        HiHealthNativeApi.a(BaseApplication.e()).readHiHealthDataPro(e, new AnonymousClass1());
    }

    /* renamed from: com.huawei.health.marketing.utils.EcgDataInquirer$1, reason: invalid class name */
    public class AnonymousClass1 implements HiDataReadResultListener {
        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        AnonymousClass1() {
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            LogUtil.a(EcgDataInquirer.TAG, "querySummary : dataTypes = ", Arrays.toString(EcgDataInquirer.this.mDataTypes), ", errorCode = ", Integer.valueOf(i), ", data = ", obj);
            if (i != 0 || !(obj instanceof SparseArray)) {
                EcgDataInquirer.this.mIsQuerying = false;
                return;
            }
            EcgDataInquirer.this.mIsQuerying = false;
            final SparseArray sparseArray = (SparseArray) obj;
            ThreadPoolManager.d().execute(new Runnable() { // from class: eic
                @Override // java.lang.Runnable
                public final void run() {
                    EcgDataInquirer.AnonymousClass1.this.alC_(sparseArray);
                }
            });
        }

        public /* synthetic */ void alC_(SparseArray sparseArray) {
            EcgDataInquirer.this.onQuerySummaryDone(sparseArray);
        }
    }

    protected void queryDetail(long j, long j2, int[] iArr) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(j);
        hiDataReadOption.setEndTime(j2);
        hiDataReadOption.setCount(1);
        hiDataReadOption.setType(iArr);
        HiHealthNativeApi.a(BaseApplication.e()).readHiHealthDataPro(HiDataReadProOption.builder().e(hiDataReadOption).b(0).e(), new HiDataReadResultListener() { // from class: com.huawei.health.marketing.utils.EcgDataInquirer.5
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                LogUtil.a(EcgDataInquirer.TAG, "parseEcgDiagramSeqData cause data = ", obj);
                if (obj instanceof SparseArray) {
                    EcgDataInquirer.this.onQueryDetailDone((SparseArray) obj);
                }
            }
        });
    }

    public fbp getMeasureCardBean() {
        return this.mMeasureCardBean;
    }
}
