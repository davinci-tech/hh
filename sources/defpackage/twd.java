package defpackage;

import android.util.Log;
import com.huawei.wisesecurity.ucs.common.log.ILogUcs;

/* loaded from: classes7.dex */
public class twd implements ILogUcs {
    @Override // com.huawei.wisesecurity.ucs.common.log.ILogUcs
    public void w(String str, String str2) {
        Log.w(str, str2);
    }

    @Override // com.huawei.wisesecurity.ucs.common.log.ILogUcs
    public void i(String str, String str2) {
        Log.i(str, str2);
    }

    @Override // com.huawei.wisesecurity.ucs.common.log.ILogUcs
    public void e(String str, String str2) {
        Log.e(str, str2);
    }

    @Override // com.huawei.wisesecurity.ucs.common.log.ILogUcs
    public void d(String str, String str2) {
        Log.d(str, str2);
    }
}
