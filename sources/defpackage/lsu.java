package defpackage;

import android.util.Log;
import com.huawei.openplatform.abl.log.a;
import com.huawei.openplatform.abl.log.i;

/* loaded from: classes5.dex */
public final class lsu extends a {
    @Override // com.huawei.openplatform.abl.log.i
    public void a(lta ltaVar, int i, String str) {
        if (ltaVar == null) {
            return;
        }
        e(ltaVar.e(), i, str);
        i iVar = this.f8220a;
        if (iVar != null) {
            iVar.a(ltaVar, i, str);
        }
    }

    @Override // com.huawei.openplatform.abl.log.i
    public i a(String str, String str2) {
        i iVar = this.f8220a;
        if (iVar != null) {
            iVar.a(str, str2);
        }
        return this;
    }

    private void e(String str, int i, String str2) {
        if (str == null) {
            return;
        }
        if (i == 3) {
            Log.d(str2, str);
            return;
        }
        if (i == 5) {
            Log.w(str2, str);
        } else if (i != 6) {
            Log.i(str2, str);
        } else {
            Log.e(str2, str);
        }
    }

    public static i a() {
        return new lsu();
    }

    private lsu() {
    }
}
