package defpackage;

import android.text.TextUtils;
import com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.initese.oma.SmartCardCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import java.io.IOException;
import java.util.Map;

/* loaded from: classes5.dex */
public class jmm implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    private jml f13959a;
    private int c = -1;
    private SmartCardCallback e;

    jmm() {
    }

    public void a(jml jmlVar) {
        this.f13959a = jmlVar;
    }

    public void e(SmartCardCallback smartCardCallback) {
        this.e = smartCardCallback;
    }

    public void a(int i) {
        this.c = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        String c = c(this.f13959a.d());
        LogUtil.a("SmartCardRequest", "run openResult:", c);
        if (TextUtils.isEmpty(c)) {
            return;
        }
        try {
            d(c);
        } catch (IOException unused) {
            jnk.a().d();
            a(this.c, "execute apdu error.");
        }
    }

    private void d(String str) throws IOException {
        String str2;
        String a2 = this.f13959a.a();
        if (TextUtils.isEmpty(a2)) {
            a(this.c, "executeApduCommand param is null");
            return;
        }
        try {
            str2 = jnk.a().b(str, a2);
        } catch (jms unused) {
            str2 = null;
        }
        if (str2 == null) {
            str2 = "-1";
        }
        LogUtil.a("SmartCardRequest", "executeApduCommand channelType:", str, "executeApduCommand command:", a2, "，response apdu:", str2);
        e(this.c, str2);
    }

    private String c(String str) {
        LogUtil.a("SmartCardRequest", "openCurrentAvailableChannel aid:", str);
        Map a2 = jnk.a().a(str, 0);
        String str2 = a2 != null ? (String) a2.get(PluginPayAdapter.KEY_OPEN_CHANNEL_ID) : "";
        LogUtil.a("SmartCardRequest", "open channel:", str2);
        return str2;
    }

    private void e(int i, String str) {
        SmartCardCallback smartCardCallback = this.e;
        if (smartCardCallback != null) {
            smartCardCallback.onOperatorSuccess(i, str);
        }
    }

    private void a(int i, String str) {
        LogUtil.a("SmartCardRequest", "operateFailure:", str);
        if (this.e != null) {
            this.e.onOperatorFailure(i, new Error(str));
        }
    }
}
