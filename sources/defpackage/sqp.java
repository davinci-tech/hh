package defpackage;

import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.dfx.DfxUtils;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.HiSampleConfigKey;
import com.huawei.hihealth.HiSampleConfigProcessOption;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;

/* loaded from: classes7.dex */
public class sqp {
    public static void d(long j, HiCommonListener hiCommonListener) {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(900000000);
        hiSyncOption.setSyncMethod(2);
        hiSyncOption.setSyncId(j);
        hiSyncOption.setForceSync(true);
        HiHealthNativeApi.a(BaseApplication.e()).synCloud(hiSyncOption, hiCommonListener);
    }

    public static void d(long j) {
        d(j, null);
    }

    public static void c(String str, String str2, String str3, long j, HiDataOperateListener hiDataOperateListener) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            String str4 = "setSampleConfig type " + str + " configId " + str2 + " configData " + str3;
            ReleaseLogUtil.a("R_SampleConfigUtil", str4, " ", DfxUtils.d(Thread.currentThread(), null));
            if (hiDataOperateListener != null) {
                hiDataOperateListener.onResult(-1, str4);
                return;
            }
            return;
        }
        HiSampleConfig.Builder builder = new HiSampleConfig.Builder();
        builder.c(j);
        builder.g(String.valueOf(0));
        builder.c(String.valueOf(-1));
        builder.h(String.valueOf(0));
        builder.j(str);
        builder.d(str2);
        builder.b(str3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new HiSampleConfig(builder));
        HiHealthManager.d(BaseApplication.e()).setSampleConfig(arrayList, hiDataOperateListener);
    }

    public static void b(String str, String str2, HiDataReadResultListener hiDataReadResultListener) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            String str3 = "getSampleConfig type " + str + " configId " + str2;
            ReleaseLogUtil.a("R_SampleConfigUtil", str3, " ", DfxUtils.d(Thread.currentThread(), null));
            if (hiDataReadResultListener != null) {
                hiDataReadResultListener.onResult(str3, -1, -1);
                return;
            }
            return;
        }
        HiSampleConfigKey.Builder builder = new HiSampleConfigKey.Builder();
        builder.a(String.valueOf(0));
        builder.e(String.valueOf(0));
        builder.b(str);
        builder.d(str2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new HiSampleConfigKey(builder));
        HiSampleConfigProcessOption hiSampleConfigProcessOption = new HiSampleConfigProcessOption();
        hiSampleConfigProcessOption.setSampleConfigKeyList(arrayList);
        HiHealthManager.d(BaseApplication.e()).getSampleConfig(hiSampleConfigProcessOption, hiDataReadResultListener);
    }

    public static void d(String str, String str2, HiDataOperateListener hiDataOperateListener) {
        c("9003", str, str2, System.currentTimeMillis(), hiDataOperateListener);
    }

    public static void b(String str, String str2, long j, HiDataOperateListener hiDataOperateListener) {
        c("9003", str, str2, j, hiDataOperateListener);
    }

    public static void c(String str, HiDataReadResultListener hiDataReadResultListener) {
        b("9003", str, hiDataReadResultListener);
    }
}
