package defpackage;

import android.util.SparseArray;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback;
import com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes7.dex */
public class rpm extends BaseOperate {
    public rpm() {
        this.pageType = 113;
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void readByMonthNoSource(final DataSourceCallback<List<rsg>> dataSourceCallback) {
        final rkb d = d();
        d.c(0);
        d.a(new int[]{DicDataTypeUtil.DataType.MAX_SKIN_TEMPERATURE.value(), DicDataTypeUtil.DataType.MIN_SKIN_TEMPERATURE.value()});
        readHiHealthDataPro(d, new DataSourceCallback() { // from class: rpl
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i, Object obj) {
                rpm.this.dPR_(dataSourceCallback, d, i, (SparseArray) obj);
            }
        });
    }

    /* synthetic */ void dPR_(final DataSourceCallback dataSourceCallback, rkb rkbVar, final int i, SparseArray sparseArray) {
        if (i != 0 || sparseArray == null || sparseArray.size() == 0) {
            this.mHandler.post(new Runnable() { // from class: rpk
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, new ArrayList());
                }
            });
            return;
        }
        if (sparseArray.get(rkbVar.l()[0]) != null) {
            this.mMaxDataList.addAll((Collection) sparseArray.get(rkbVar.l()[0]));
        }
        if (sparseArray.get(rkbVar.l()[1]) != null) {
            this.mMinDataList.addAll((Collection) sparseArray.get(rkbVar.l()[1]));
        }
        final List<rsg> hiHealthDataProcess = hiHealthDataProcess(mergeMaxMinList(this.mMaxDataList, this.mMinDataList));
        this.mHandler.post(new Runnable() { // from class: rpn
            @Override // java.lang.Runnable
            public final void run() {
                DataSourceCallback.this.onResponse(i, hiHealthDataProcess);
            }
        });
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void readByMonthByDeviceSource(String str, int i, String str2, DataSourceCallback<List<rsg>> dataSourceCallback) {
        rkb d = d();
        d.c(i);
        d.d(str);
        d.e(str2);
        d.c(new String[]{"maxSkinTemperature"});
        e(d, dataSourceCallback);
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void readByMonthByAppSource(String str, DataSourceCallback<List<rsg>> dataSourceCallback) {
        rkb d = d();
        d.c(1);
        d.e(str);
        d.c(new String[]{"maxSkinTemperature"});
        e(d, dataSourceCallback);
    }

    private rkb d() {
        rkb rkbVar = new rkb();
        rkbVar.a(new int[]{DicDataTypeUtil.DataType.SKIN_TEMPERATURE.value()});
        rkbVar.c(0L);
        rkbVar.d(System.currentTimeMillis());
        rkbVar.b(4);
        return rkbVar;
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    /* renamed from: readDayDataNoSource */
    public void m843xae5c7554(rkb rkbVar, DataSourceCallback<List<PrivacyDataModel>> dataSourceCallback) {
        rkbVar.a(new int[]{DicDataTypeUtil.DataType.SKIN_TEMPERATURE.value()});
        rkbVar.b(true);
        readForOneDayDatas(rkbVar, dataSourceCallback);
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    /* renamed from: readDayDataBySource */
    public void m842xa7339313(rkb rkbVar, DataSourceCallback<List<PrivacyDataModel>> dataSourceCallback) {
        rkbVar.a(new int[]{DicDataTypeUtil.DataType.SKIN_TEMPERATURE.value()});
        rkbVar.b(true);
        readForOneDayDatas(rkbVar, dataSourceCallback);
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void readInOneDayByDeviceSource(long j, String str, DataSourceCallback<List<PrivacyDataModel>> dataSourceCallback) {
        rkb e = e(j);
        e.c(2);
        e.d(str);
        readForOneDayDatas(e, dataSourceCallback);
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void readInOneDayByAppSource(long j, String str, String str2, int i, DataSourceCallback<List<PrivacyDataModel>> dataSourceCallback) {
        rkb e = e(j);
        e.c(i);
        e.d(str);
        e.e(str2);
        readForOneDayDatas(e, dataSourceCallback);
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public int[] getDeleteDataTypes(rkb rkbVar) {
        return new int[]{DicDataTypeUtil.DataType.SKIN_TEMPERATURE.value()};
    }

    private rkb e(long j) {
        rkb rkbVar = new rkb();
        rkbVar.c(rrb.c(j));
        rkbVar.d(rrb.e(j));
        rkbVar.a(new int[]{DicDataTypeUtil.DataType.SKIN_TEMPERATURE.value()});
        return rkbVar;
    }

    private void e(final rkb rkbVar, final DataSourceCallback<List<rsg>> dataSourceCallback) {
        aggregateProMaxAndMin(rkbVar, new DataSourceCallback() { // from class: rpi
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i, Object obj) {
                rpm.this.c(dataSourceCallback, rkbVar, i, (List) obj);
            }
        });
    }

    /* synthetic */ void c(final DataSourceCallback dataSourceCallback, final rkb rkbVar, final int i, final List list) {
        if (i != 0 || koq.b(list)) {
            this.mHandler.post(new Runnable() { // from class: rpr
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, new ArrayList());
                }
            });
            return;
        }
        transKeyToValue(list, this.mMaxDataList, rkbVar);
        rkbVar.c(new String[]{"minSkinTemperature"});
        rkbVar.b(5);
        aggregateProMaxAndMin(rkbVar, new DataSourceCallback() { // from class: rpo
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i2, Object obj) {
                rpm.this.e(list, dataSourceCallback, i, rkbVar, i2, (List) obj);
            }
        });
    }

    /* synthetic */ void e(List list, final DataSourceCallback dataSourceCallback, final int i, rkb rkbVar, int i2, List list2) {
        if (i2 != 0 || koq.b(list)) {
            this.mHandler.post(new Runnable() { // from class: rpp
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, new ArrayList());
                }
            });
            return;
        }
        transKeyToValue(list2, this.mMinDataList, rkbVar);
        final List<rsg> hiHealthDataProcess = hiHealthDataProcess(mergeMaxMinList(this.mMaxDataList, this.mMinDataList));
        this.mHandler.post(new Runnable() { // from class: rpq
            @Override // java.lang.Runnable
            public final void run() {
                DataSourceCallback.this.onResponse(i, hiHealthDataProcess);
            }
        });
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public List<PrivacyDataModel> proResultOfReadInOneDayNoSource(List<HiHealthData> list) {
        return recordDataProcess(list, 1005);
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfHiHealthDataProcess(HiHealthData hiHealthData, PrivacyDataModel privacyDataModel) {
        privacyDataModel.setDataTitle(rre.e(hiHealthData.getDouble("maxTemperature"), hiHealthData.getDouble("minTemperature")));
        privacyDataModel.setDataDesc(rsr.a(hiHealthData.getStartTime()));
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfRecordDataProcess(HiHealthData hiHealthData, PrivacyDataModel privacyDataModel) {
        privacyDataModel.setDoubleValue(hiHealthData.getValue());
        privacyDataModel.setDataTitle(rre.c(hiHealthData.getValue()));
    }
}
