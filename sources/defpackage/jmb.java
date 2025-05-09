package defpackage;

import com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.initese.apdu.ApduResponseHandler;
import com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.initese.oma.SmartCardCallback;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
public class jmb implements Runnable, SmartCardCallback {
    private static final Object c = new Object();

    /* renamed from: a, reason: collision with root package name */
    private jmc f13953a;
    private List<jmc> b;
    private String d;
    private int e = 0;
    private boolean f = false;
    private jmk g = new jmk();
    private int i;
    private ApduResponseHandler j;

    public jmb(ApduResponseHandler apduResponseHandler) {
        this.j = apduResponseHandler;
    }

    public jmk d() {
        return this.g;
    }

    @Override // java.lang.Runnable
    public void run() {
        c();
    }

    private void c() {
        synchronized (c) {
            List<jmc> list = this.b;
            if (list == null) {
                return;
            }
            if (this.e == list.size()) {
                LogUtil.h("ApduSmartCardRequest", "mCurrentExecuteIndex == mApduList.size()");
                return;
            }
            jmc jmcVar = this.b.get(this.e);
            this.f13953a = jmcVar;
            String c2 = jmcVar.c();
            LogUtil.a("ApduSmartCardRequest", "start get apdu index:", Integer.valueOf(this.e), ", apdu:", c2);
            try {
                if ("00A4".equals(c2.substring(0, 4))) {
                    LogUtil.a("ApduSmartCardRequest", "deal with select apdu:", c2);
                    this.d = c2.substring(c2.length() - (Integer.parseInt(c2.substring(8, 10), 16) * 2), c2.length());
                    this.e++;
                    this.g.e();
                    LogUtil.a("ApduSmartCardRequest", "has get select aid:", this.d);
                    c();
                    return;
                }
                if (this.d == null) {
                    LogUtil.h("ApduSmartCardRequest", "mApduAid is null");
                    return;
                }
                LogUtil.a("ApduSmartCardRequest", "start execute apdu:", c2);
                jml jmlVar = new jml(this.d);
                jmlVar.d(c2);
                this.g.d(this).e(this.i, jmlVar);
            } catch (NumberFormatException | StringIndexOutOfBoundsException unused) {
                LogUtil.b("ApduSmartCardRequest", "sendApudToSmartCard catch exception");
            }
        }
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.initese.oma.SmartCardCallback
    public void onOperatorSuccess(int i, String str) {
        String str2;
        LogUtil.a("ApduSmartCardRequest", "handle apdu response:", str, ",mIsGetLocalData:", Boolean.valueOf(this.f));
        if (this.f) {
            a(str);
            return;
        }
        if (str == null || str.length() <= 4) {
            str2 = "";
        } else {
            String substring = str.substring(str.length() - 4, str.length());
            str2 = str.substring(0, str.length() - 4).toUpperCase(Locale.getDefault());
            str = substring;
        }
        if (str != null) {
            str = str.toUpperCase(Locale.getDefault());
        }
        LogUtil.a("ApduSmartCardRequest", "get response respStatusWord:", str);
        if (this.f13953a.d() != null) {
            if (!Arrays.asList(this.f13953a.d()).contains(str)) {
                e(1, this.f13953a.e(), str2, str);
                return;
            }
        } else {
            LogUtil.h("ApduSmartCardRequest", "mCurrentExecuteApduBean.getStatusWord() is null");
        }
        if (this.e < this.b.size() - 1) {
            this.e++;
            c();
        } else {
            e(0, this.f13953a.e(), str2, str);
        }
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.initese.oma.SmartCardCallback
    public void onOperatorFailure(int i, Error error) {
        LogUtil.a("ApduSmartCardRequest", "onOperatorFailure mIsGetLocalData : ", Boolean.valueOf(this.f));
        if (this.f) {
            b(100009, error);
        } else {
            e(1, this.f13953a.e(), "", "", error);
        }
    }

    public void a(List<jmc> list) {
        this.b = list;
    }

    public void a(int i) {
        this.i = i;
    }

    private void e(int i, int i2, String str, String str2) {
        b();
        ApduResponseHandler apduResponseHandler = this.j;
        if (apduResponseHandler != null) {
            apduResponseHandler.sendSendNextMessage(i, i2, str, str2);
        }
    }

    private void e(int i, int i2, String str, String str2, Error error) {
        b();
        ApduResponseHandler apduResponseHandler = this.j;
        if (apduResponseHandler != null) {
            apduResponseHandler.sendSendNextErrorMessage(i, i2, str, str2, error);
        }
    }

    private void a(String str) {
        b();
        ApduResponseHandler apduResponseHandler = this.j;
        if (apduResponseHandler != null) {
            apduResponseHandler.sendSuccessMessage(str);
        }
    }

    private void b(int i, Error error) {
        b();
        ApduResponseHandler apduResponseHandler = this.j;
        if (apduResponseHandler != null) {
            apduResponseHandler.sendFailureMessage(i, error);
        }
    }

    private void b() {
        LogUtil.a("ApduSmartCardRequest", "clearData");
        this.e = 0;
        this.f13953a = null;
        this.f = false;
    }
}
