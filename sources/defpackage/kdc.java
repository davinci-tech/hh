package defpackage;

import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hwcommonmodel.application.BaseApplication;

/* loaded from: classes5.dex */
public class kdc {
    public static void a(int i) {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(i);
        hiSyncOption.setSyncMethod(2);
        HiHealthNativeApi.a(BaseApplication.getContext()).synCloud(hiSyncOption, null);
    }
}
