package defpackage;

import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwonesdk.process.HiHealthProcess;
import health.compact.a.util.LogUtil;

/* loaded from: classes9.dex */
public class kud {
    public static void d(String str, HiHealthProcess hiHealthProcess, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.e("HiHealthProcess_OneSdkFactory", "callback is null in execute");
        } else if (hiHealthProcess == null) {
            LogUtil.d("HiHealthProcess_OneSdkFactory", "hiHealthProcess is null in execute");
            iBaseResponseCallback.onResponse(-1, "");
        } else {
            hiHealthProcess.doAction(str, iBaseResponseCallback);
        }
    }
}
