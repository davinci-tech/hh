package defpackage;

import android.os.Handler;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataAggregateProOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback;
import com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class roo extends BaseOperate {
    public roo() {
        this.pageType = 110;
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public List<PrivacyDataModel> proResultOfReadInOneDayNoSource(List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList(10);
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData.getValue() > 0.0d) {
                PrivacyDataModel privacyDataModel = new PrivacyDataModel();
                privacyDataModel.setStartTime(hiHealthData.getStartTime());
                privacyDataModel.setEndTime(hiHealthData.getEndTime());
                privacyDataModel.setModifyTime(hiHealthData.getModifiedTime());
                privacyDataModel.setDataDesc(rrb.d(hiHealthData, 1001));
                privacyDataModel.setPageType(110);
                privacyDataModel.setType(hiHealthData.getType());
                privacyDataModel.setClientId(hiHealthData.getClientId());
                privacyDataModel.setDoubleValue(hiHealthData.getValue());
                privacyDataModel.setDataTitle(rre.c((float) hiHealthData.getValue()));
                arrayList.add(privacyDataModel);
            }
        }
        return arrayList;
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfReadByMonthNoSource(rkb rkbVar) {
        rkbVar.a(new int[]{SmartMsgConstant.MSG_TYPE_RIDE_USER});
        rkbVar.c(new String[]{"climbStair"});
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfReadByMonthByDeviceSource(rkb rkbVar) {
        setVarOfReadByMonthNoSource(rkbVar);
        rkbVar.a(new int[]{5});
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfReadByMonthByAppSource(rkb rkbVar) {
        setVarOfReadByMonthNoSource(rkbVar);
        rkbVar.a(new int[]{5});
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfHiHealthDataProcess(HiHealthData hiHealthData, PrivacyDataModel privacyDataModel) {
        privacyDataModel.setDataTitle(rre.c(hiHealthData.getFloat("climbStair")));
        privacyDataModel.setDataDesc(rsr.a(hiHealthData.getStartTime()));
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfReadInOneDayNoSource(rkb rkbVar) {
        rkbVar.a(new int[]{5});
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfReadInOneDayByDeviceSource(rkb rkbVar) {
        setVarOfReadInOneDayNoSource(rkbVar);
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void readInOneDayByAppSource(long j, String str, String str2, int i, DataSourceCallback<List<PrivacyDataModel>> dataSourceCallback) {
        this.mHiHealthDataListRecursive.clear();
        HiAggregateOption d = d(j);
        d.setDeviceUuid(str);
        d.setReadType(i);
        LogUtil.a("ClimbOperateImp", "readInOneDayByAppSource start");
        a(str2, d, new AnonymousClass4(dataSourceCallback));
    }

    /* renamed from: roo$4, reason: invalid class name */
    class AnonymousClass4 implements DataSourceCallback<List<HiHealthData>> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ DataSourceCallback f16851a;

        AnonymousClass4(DataSourceCallback dataSourceCallback) {
            this.f16851a = dataSourceCallback;
        }

        @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onResponse(final int i, List<HiHealthData> list) {
            LogUtil.a("ClimbOperateImp", "readInOneDayByAppSource end resultCode = ", Integer.valueOf(i));
            if (i != 0 || koq.b(list)) {
                Handler handler = roo.this.mHandler;
                final DataSourceCallback dataSourceCallback = this.f16851a;
                handler.post(new Runnable() { // from class: ros
                    @Override // java.lang.Runnable
                    public final void run() {
                        DataSourceCallback.this.onResponse(i, new ArrayList());
                    }
                });
            } else {
                final List<PrivacyDataModel> proResultOfReadInOneDayNoSource = roo.this.proResultOfReadInOneDayNoSource(list);
                Handler handler2 = roo.this.mHandler;
                final DataSourceCallback dataSourceCallback2 = this.f16851a;
                handler2.post(new Runnable() { // from class: rov
                    @Override // java.lang.Runnable
                    public final void run() {
                        DataSourceCallback.this.onResponse(i, proResultOfReadInOneDayNoSource);
                    }
                });
            }
        }
    }

    private HiAggregateOption d(long j) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(rrb.c(j));
        hiAggregateOption.setEndTime(rrb.e(j));
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setConstantsKey(new String[]{"climbStair"});
        hiAggregateOption.setType(new int[]{5});
        hiAggregateOption.setAlignType(20001);
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setSortOrder(1);
        return hiAggregateOption;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final String str, final HiAggregateOption hiAggregateOption, final DataSourceCallback<List<HiHealthData>> dataSourceCallback) {
        HiDataAggregateProOption c = HiDataAggregateProOption.builder().c(hiAggregateOption).c(str).c();
        LogUtil.b("ClimbOperateImp", "readInOneDayForApp start");
        HiHealthManager.d(this.mContext).aggregateHiHealthDataPro(c, new HiAggregateListener() { // from class: roo.2
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                LogUtil.b("ClimbOperateImp", "readInOneDayForApp end errorCode = ", Integer.valueOf(i));
                if (i != 0) {
                    dataSourceCallback.onResponse(1, new ArrayList());
                    return;
                }
                if (list == null || list.isEmpty()) {
                    dataSourceCallback.onResponse(0, roo.this.mHiHealthDataListRecursive);
                    return;
                }
                roo.this.mHiHealthDataListRecursive.addAll(list);
                hiAggregateOption.setEndTime(list.get(list.size() - 1).getStartTime() - 1);
                roo.this.a(str, hiAggregateOption, dataSourceCallback);
            }
        });
    }
}
