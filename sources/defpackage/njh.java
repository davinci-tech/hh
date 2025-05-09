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
import health.compact.a.ReleaseLogUtil;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class njh {
    public static void b(final String str, final boolean z) {
        final long currentTimeMillis = System.currentTimeMillis();
        final String str2 = z ? "1" : "0";
        jdx.b(new Runnable() { // from class: njh.5
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("SportSwitchUtils", "setSportSwitch configId ", str, " isChecked ", Boolean.valueOf(z));
                njh.b(str, njh.e(str2, currentTimeMillis));
                njh.c();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(final String str, final nji njiVar) {
        njj.a("9002", str, HiJsonUtil.e(njiVar), new HiDataOperateListener() { // from class: njh.4
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                LogUtil.a("SportSwitchUtils", "setSportSwitch configId ", str, " switchStatus ", njiVar.e(), "errorCode: ", Integer.valueOf(i), ", object: ", obj);
            }
        }, njiVar.b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c() {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(900000000);
        hiSyncOption.setSyncMethod(2);
        HiHealthNativeApi.a(BaseApplication.getContext()).synCloud(hiSyncOption, null);
    }

    public static void e(List<String> list, final IBaseResponseCallback iBaseResponseCallback, final String str) {
        if (koq.b(list) || iBaseResponseCallback == null) {
            LogUtil.h("SportSwitchUtils", "getSwitchStatusFromDb keys or callback is null");
            return;
        }
        final HashMap hashMap = new HashMap(16);
        ReleaseLogUtil.b("SportSwitchUtils", "getSwitchStatusFromDb configIdList ", list.toString());
        njj.d("9002", list, new HiDataReadResultListener() { // from class: njh.3
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                ReleaseLogUtil.b("SportSwitchUtils", "onResult errorCode: ", Integer.valueOf(i), ", data: ", obj);
                if (!koq.e(obj, HiSampleConfig.class)) {
                    LogUtil.h("SportSwitchUtils", "list getActiveGoals isListTypeMatch false ");
                    IBaseResponseCallback.this.d(0, null);
                    return;
                }
                List list2 = (List) obj;
                if (koq.b(list2)) {
                    LogUtil.h("SportSwitchUtils", "list getActiveGoals list is empty ");
                    IBaseResponseCallback.this.d(0, null);
                    return;
                }
                for (int i3 = 0; i3 < list2.size(); i3++) {
                    HiSampleConfig hiSampleConfig = (HiSampleConfig) list2.get(i3);
                    String configData = hiSampleConfig.getConfigData();
                    if (TextUtils.isEmpty(configData)) {
                        hashMap.put(hiSampleConfig.getConfigId(), str);
                    } else {
                        hashMap.put(hiSampleConfig.getConfigId(), ((nji) HiJsonUtil.e(configData, nji.class)).e());
                    }
                }
                LogUtil.a("SportSwitchUtils", "buildDefaultSportSwitch map ", hashMap.toString());
                IBaseResponseCallback.this.d(0, hashMap);
            }
        });
    }

    public static void c(List<String> list, IBaseResponseCallback iBaseResponseCallback) {
        e(list, iBaseResponseCallback, "1");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static nji e(String str, long j) {
        nji njiVar = new nji();
        njiVar.c(str);
        njiVar.e(j);
        return njiVar;
    }
}
