package defpackage;

import android.text.TextUtils;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class gzl {
    public static void b(final IBaseResponseCallback iBaseResponseCallback) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add("900100018");
        njj.d("9001", arrayList, new HiDataReadResultListener() { // from class: gzl.3
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                LogUtil.a("Track_RouteAutoSwitchUtil", "onResult errorCode: ", Integer.valueOf(i), ", data: ", obj);
                enh enhVar = new enh();
                if (!koq.e(obj, HiSampleConfig.class)) {
                    ReleaseLogUtil.d("Track_RouteAutoSwitchUtil", "getAutoSwitch isListTypeMatch false ");
                    IBaseResponseCallback.this.d(-1, enhVar);
                    return;
                }
                List<HiSampleConfig> list = (List) obj;
                if (koq.b(list)) {
                    ReleaseLogUtil.d("Track_RouteAutoSwitchUtil", "getAutoSwitch list is empty ");
                    IBaseResponseCallback.this.d(0, enhVar);
                    return;
                }
                for (HiSampleConfig hiSampleConfig : list) {
                    if (hiSampleConfig != null) {
                        String configData = hiSampleConfig.getConfigData();
                        if (!TextUtils.isEmpty(configData)) {
                            enhVar = (enh) HiJsonUtil.e(configData, enh.class);
                        }
                    }
                }
                LogUtil.a("Track_RouteAutoSwitchUtil", "getAutoSwitch return ", enhVar);
                IBaseResponseCallback.this.d(0, enhVar);
            }
        });
    }

    public static void b(enh enhVar) {
        LogUtil.a("Track_RouteAutoSwitchUtil", "saveAutoSwitch PathAutoSwitchInfo ", enhVar);
        njj.a("9001", "900100018", HiJsonUtil.e(enhVar), new HiDataOperateListener() { // from class: gzl.5
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                LogUtil.a("Track_RouteAutoSwitchUtil", "saveSampleConfig errorCode: ", Integer.valueOf(i), ", object: ", obj);
            }
        }, System.currentTimeMillis());
        d();
    }

    public static void c(boolean z) {
        LogUtil.a("Track_RouteAutoSwitchUtil", "saveDefaultTwoSwitch with : ", Boolean.valueOf(z));
        enh enhVar = new enh();
        enhVar.c(z);
        enhVar.e(z);
        b(enhVar);
    }

    private static void d() {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(900000000);
        hiSyncOption.setSyncMethod(2);
        HiHealthNativeApi.a(BaseApplication.getContext()).synCloud(hiSyncOption, null);
    }
}
