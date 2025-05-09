package defpackage;

import com.huawei.hihealth.HiHealthData;
import com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import java.util.List;

/* loaded from: classes7.dex */
public class rqo extends BaseOperate {
    public rqo() {
        this.pageType = 103;
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfReadByMonthNoSource(rkb rkbVar) {
        rkbVar.c(new String[]{"step"});
        rkbVar.a(new int[]{40002});
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfReadByMonthByDeviceSource(rkb rkbVar) {
        setVarOfReadByMonthNoSource(rkbVar);
        rkbVar.a(new int[]{2});
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfReadByMonthByAppSource(rkb rkbVar) {
        setVarOfReadByMonthNoSource(rkbVar);
        rkbVar.a(new int[]{2});
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfReadInOneDayNoSource(rkb rkbVar) {
        rkbVar.a(new int[]{2});
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
    public List<PrivacyDataModel> proResultOfReadInOneDayNoSource(List<HiHealthData> list) {
        return recordDataProcess(list, 1001);
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfRecordDataProcess(HiHealthData hiHealthData, PrivacyDataModel privacyDataModel) {
        int value = (int) hiHealthData.getValue();
        privacyDataModel.setIntValue(value);
        privacyDataModel.setDataTitle(rre.f(value));
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfHiHealthDataProcess(HiHealthData hiHealthData, PrivacyDataModel privacyDataModel) {
        privacyDataModel.setDataTitle(rre.f(hiHealthData.getInt("step")));
        privacyDataModel.setDataDesc(rsr.a(hiHealthData.getStartTime()));
    }
}
