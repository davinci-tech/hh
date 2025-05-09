package defpackage;

import android.util.SparseArray;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback;
import com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes7.dex */
public class rnj extends BaseOperate {
    public rnj() {
        this.pageType = 108;
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public List<PrivacyDataModel> proResultOfReadInOneDayNoSource(List<HiHealthData> list) {
        return Collections.emptyList();
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public int[] getDeleteDataTypes(rkb rkbVar) {
        return HiHealthDataType.d(10001);
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void readByMonthByDeviceSource(String str, int i, String str2, DataSourceCallback<List<rsg>> dataSourceCallback) {
        this.mHiHealthDataListRecursive.clear();
        rkb rkbVar = new rkb();
        rkbVar.d(str);
        rkbVar.e(str2);
        rkbVar.c(i);
        ThreadPoolManager.d().execute(b(rkbVar, dataSourceCallback));
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void readByMonthByAppSource(String str, DataSourceCallback<List<rsg>> dataSourceCallback) {
        this.mHiHealthDataListRecursive.clear();
        rkb rkbVar = new rkb();
        rkbVar.c(1);
        rkbVar.e(str);
        ThreadPoolManager.d().execute(b(rkbVar, dataSourceCallback));
    }

    Runnable b(final rkb rkbVar, final DataSourceCallback<List<rsg>> dataSourceCallback) {
        final int[] iArr = {2015, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2106};
        rkbVar.e(300);
        return new Runnable() { // from class: rnh
            @Override // java.lang.Runnable
            public final void run() {
                rnj.this.e(iArr, rkbVar, dataSourceCallback);
            }
        };
    }

    /* synthetic */ void e(int[] iArr, rkb rkbVar, final DataSourceCallback dataSourceCallback) {
        for (int i : iArr) {
            rkbVar.a(new int[]{i});
            rkbVar.c(0L);
            rkbVar.d(System.currentTimeMillis());
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            d(rkbVar, new DataSourceCallback() { // from class: rno
                @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
                public final void onResponse(int i2, Object obj) {
                    rnj.this.c(dataSourceCallback, countDownLatch, i2, (List) obj);
                }
            });
            try {
                countDownLatch.await();
            } catch (InterruptedException unused) {
                LogUtil.b("BloodSugarOperateImp", "CountDownLatch Exception");
                this.mHandler.post(new Runnable() { // from class: rnn
                    @Override // java.lang.Runnable
                    public final void run() {
                        DataSourceCallback.this.onResponse(1, new ArrayList());
                    }
                });
                return;
            }
        }
        sortHiHealthDataToDesc(this.mHiHealthDataListRecursive);
        final List<rsg> hiHealthDataProcess = hiHealthDataProcess(this.mHiHealthDataListRecursive);
        this.mHandler.post(new Runnable() { // from class: rnk
            @Override // java.lang.Runnable
            public final void run() {
                DataSourceCallback.this.onResponse(0, hiHealthDataProcess);
            }
        });
    }

    /* synthetic */ void c(final DataSourceCallback dataSourceCallback, CountDownLatch countDownLatch, final int i, List list) {
        if (i != 0) {
            LogUtil.b("BloodSugarOperateImp", "type data null");
            this.mHandler.post(new Runnable() { // from class: rnm
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, new ArrayList());
                }
            });
            countDownLatch.countDown();
            return;
        }
        countDownLatch.countDown();
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfHiHealthDataProcess(HiHealthData hiHealthData, PrivacyDataModel privacyDataModel) {
        double value = hiHealthData.getValue();
        privacyDataModel.setDoubleValue(value);
        privacyDataModel.setDataTitle(rre.b(value));
        privacyDataModel.setDataDesc(rsr.c(hiHealthData.getStartTime()));
    }

    protected void d(final rkb rkbVar, final DataSourceCallback<List<HiHealthData>> dataSourceCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setReadType(rkbVar.m());
        hiDataReadOption.setStartTime(rkbVar.o());
        hiDataReadOption.setEndTime(rkbVar.h());
        hiDataReadOption.setType(rkbVar.l());
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setDeviceUuid(rkbVar.i());
        hiDataReadOption.setCount(rkbVar.a());
        HiDataReadProOption e = HiDataReadProOption.builder().e(hiDataReadOption).a(rkbVar.j()).e();
        LogUtil.a("BloodSugarOperateImp", "readHiHealthDataProForBloodSugar start");
        HiHealthNativeApi.a(this.mContext).readHiHealthDataPro(e, new HiDataReadResultListener() { // from class: rnj.3
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                LogUtil.a("BloodSugarOperateImp", "readHiHealthDataProForBloodSugar end errorCode = ", Integer.valueOf(i));
                if (i != 0) {
                    dataSourceCallback.onResponse(1, new ArrayList());
                    return;
                }
                SparseArray<List<HiHealthData>> sparseArray = new SparseArray<>();
                if (obj instanceof SparseArray) {
                    sparseArray = (SparseArray) obj;
                }
                if (sparseArray.size() == 0) {
                    dataSourceCallback.onResponse(0, rnj.this.mHiHealthDataListRecursive);
                    return;
                }
                List<HiHealthData> dealResultForReadInOneDay = rnj.this.dealResultForReadInOneDay(sparseArray, rkbVar);
                rkbVar.d(dealResultForReadInOneDay.get(dealResultForReadInOneDay.size() - 1).getStartTime() - 1);
                rnj.this.readHiHealthDataProInOneDay(rkbVar, dataSourceCallback);
            }
        });
    }
}
