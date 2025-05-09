package defpackage;

import com.huawei.hihealth.HiHealthData;
import com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import java.util.List;

/* loaded from: classes7.dex */
public class rot extends BaseOperate {
    public rot() {
        this.pageType = 101;
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public List<PrivacyDataModel> proResultOfReadInOneDayNoSource(List<HiHealthData> list) {
        return recordDataProcess(list, 1001);
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfRecordDataProcess(HiHealthData hiHealthData, PrivacyDataModel privacyDataModel) {
        privacyDataModel.setDoubleValue(hiHealthData.getValue());
        privacyDataModel.setDataTitle(rre.a(hiHealthData.getValue()));
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfReadByMonthNoSource(rkb rkbVar) {
        rkbVar.c(new String[]{"distance"});
        rkbVar.a(new int[]{40004});
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfReadByMonthByDeviceSource(rkb rkbVar) {
        setVarOfReadByMonthNoSource(rkbVar);
        rkbVar.a(new int[]{3});
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfReadByMonthByAppSource(rkb rkbVar) {
        setVarOfReadByMonthNoSource(rkbVar);
        rkbVar.a(new int[]{3});
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfReadInOneDayNoSource(rkb rkbVar) {
        rkbVar.a(new int[]{3});
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfReadInOneDayByDeviceSource(rkb rkbVar) {
        setVarOfReadInOneDayNoSource(rkbVar);
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfReadInOneDayByAppSource(rkb rkbVar) {
        setVarOfReadInOneDayNoSource(rkbVar);
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfHiHealthDataProcess(HiHealthData hiHealthData, PrivacyDataModel privacyDataModel) {
        privacyDataModel.setDataTitle(rre.a(hiHealthData.getInt("distance")));
        privacyDataModel.setDataDesc(rsr.a(hiHealthData.getStartTime()));
    }
}
