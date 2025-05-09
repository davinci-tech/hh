package com.huawei.health.sportservice.datasource;

import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.inter.IndoorToSource;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import java.util.ArrayList;

@SportComponentType(classify = 3, name = ComponentName.OP_CODE_SOURCE)
/* loaded from: classes8.dex */
public class OpCodeSource extends BaseSource<Integer> implements SportLifecycle, IndoorToSource {
    private static final String TAG = "SportService_OpCodeSource";
    private int mOpCode;

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected void updateSourceData() {
        BaseSportManager.getInstance().stagingAndNotification("OP_CODE_DATA", Integer.valueOf(this.mOpCode));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.sportservice.datasource.BaseSource
    public void generateData(Integer num) {
        this.mOpCode = num.intValue();
        updateSourceData();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(20008);
        arrayList.add(20009);
        arrayList.add(10001);
        arrayList.add(Integer.valueOf(SmartMsgConstant.MSG_TYPE_SLEEP_USER));
        BaseSportManager.getInstance().subscribeToIndoorEquipment(arrayList, this);
    }

    @Override // com.huawei.health.sportservice.inter.IndoorToSource
    public void onIndoorDataChanged(int i, Object obj) {
        int intValue = ((Integer) obj).intValue();
        if (i == 20008) {
            int i2 = this.mOpCode;
            if (i2 != intValue) {
                LogUtil.a(TAG, "onIndoorDataChanged() mOpCode: ", Integer.valueOf(i2), " tempData: ", Integer.valueOf(intValue));
                this.mOpCode = intValue;
            }
            updateSourceData();
            return;
        }
        if (i == 20009) {
            BaseSportManager.getInstance().stagingAndNotification("MACHINE_STATUS_DATA", Integer.valueOf(intValue));
        } else if (i == 10001) {
            BaseSportManager.getInstance().stagingAndNotification("TRAINING_STATUS_DATA", Integer.valueOf(intValue));
        } else if (i == 40007) {
            BaseSportManager.getInstance().stagingAndNotification("ROPE_TRAINING_STATUS_DATA", Integer.valueOf(intValue));
        }
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    public void recoveryData() {
        Object data = BaseSportManager.getInstance().getData("OP_CODE_DATA");
        if (data instanceof Integer) {
            int intValue = ((Integer) data).intValue();
            this.mOpCode = intValue;
            LogUtil.a(TAG, "recoveryData() mOpCode: ", Integer.valueOf(intValue));
        }
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected String getLogTag() {
        return TAG;
    }
}
