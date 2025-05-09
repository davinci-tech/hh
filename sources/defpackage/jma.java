package defpackage;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.initese.apdu.ApduResponseHandler;
import com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.initese.tsm.operator.TsmOperatorResponse;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;

/* loaded from: classes5.dex */
public class jma {
    private jmp d;
    private String i;
    private TsmOperatorResponse j;
    private int c = 1;
    private int b = -1;

    /* renamed from: a, reason: collision with root package name */
    private ApduResponseHandler f13952a = new ApduResponseHandler() { // from class: jma.2
        @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.initese.apdu.ApduResponseHandler
        public void onSuccess(String str) {
            if (jma.this.j != null) {
                jma.this.j.onOperatorSuccess(str);
            }
            jma.this.d();
        }

        @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.initese.apdu.ApduResponseHandler
        public void onSendNext(int i, int i2, String str, String str2) {
            jma.d(jma.this);
            LogUtil.a("ApduManager", "onSendNext");
            jma.this.a(i, i2, str, str2);
        }

        @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.initese.apdu.ApduResponseHandler
        public void onSendNextError(int i, int i2, String str, String str2, Error error) {
            if (error != null) {
                jma.this.i = error.getMessage();
            }
            jma.d(jma.this);
            LogUtil.a("ApduManager", "onSendNextError");
            jma.this.a(i, i2, str, str2);
        }

        @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.initese.apdu.ApduResponseHandler
        public void onFailure(int i, Error error) {
            if (jma.this.j != null) {
                jma.this.j.onOperatorFailure(i, error);
            }
            jma.this.d();
        }
    };
    private String h = b();
    private jmb e = new jmb(this.f13952a);

    static /* synthetic */ int d(jma jmaVar) {
        int i = jmaVar.c;
        jmaVar.c = i + 1;
        return i;
    }

    public void e(jmp jmpVar) {
        this.b = 40;
        this.e.a(-1);
        this.d = jmpVar;
        a(jnn.a(jmpVar, this.b, this.c));
    }

    private void a(String str) {
        c(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2, String str, String str2) {
        LogUtil.a("ApduManager", "sendNextApdu index:", Integer.valueOf(i2), ", result:", Integer.valueOf(i), ",respApdu:", str, ",statusWord:", str2);
        jmg jmgVar = new jmg();
        jmgVar.a(i2);
        jmgVar.c(str);
        jmgVar.d(str2);
        c(jnn.e(this.d, this.b, jmgVar, i, this.c));
    }

    private void c(String str) {
        LogUtil.a("ApduManager", "sendApduToServer request:", str);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(this.h)) {
            return;
        }
        String e = e(str);
        if (TextUtils.isEmpty(e)) {
            this.f13952a.sendFailureMessage(100011, new Error("string is null."));
        } else {
            b(e);
        }
    }

    private String e(String str) {
        if (d(this.h)) {
            return jeb.d(this.h, str);
        }
        return jee.d(this.h, str, false);
    }

    private boolean d(String str) {
        int indexOf;
        if (TextUtils.isEmpty(str) || (indexOf = str.indexOf(":")) < 0) {
            return false;
        }
        String substring = str.substring(0, indexOf);
        LogUtil.a("ApduManager", "isHttpProtocol protocol:", substring);
        return "http".equalsIgnoreCase(substring);
    }

    private String b() {
        if (CommonUtil.cc()) {
            return GRSManager.a(BaseApplication.getContext()).getUrl("domainLfpaytest01Hwcloudtest") + "/TSMAPKP/HwTSMServer/applicationBusiness.action?version=" + jnu.h();
        }
        return GRSManager.a(BaseApplication.getContext()).getUrl("domainTsmServiceHicloud") + "/TSMAPKP/HwTSMServer/applicationBusiness.action?version=" + jnu.h();
    }

    private void b(String str) {
        jmi jmiVar;
        LogUtil.a("ApduManager", "onSuccess response:", str);
        try {
            jmiVar = (jmi) new Gson().fromJson(str, new TypeToken<jmi<jmh>>() { // from class: jma.4
            }.getType());
        } catch (JsonSyntaxException unused) {
            this.f13952a.sendFailureMessage(100012, new Error("response data parse failure."));
            jmiVar = null;
        }
        if (jmiVar == null) {
            this.f13952a.sendFailureMessage(100012, new Error("response data is empty."));
            return;
        }
        jmh jmhVar = (jmh) jmiVar.d();
        if (jmhVar == null) {
            this.f13952a.sendFailureMessage(100012, new Error("business data is empty."));
            return;
        }
        int a2 = jmhVar.a();
        LogUtil.a("ApduManager", "onSuccess result:", Integer.valueOf(a2));
        if (a2 != 100000) {
            String e = jmhVar.e();
            String str2 = this.i;
            if (str2 != null && !"".equals(str2)) {
                e = e + ":" + this.i;
                this.i = null;
            }
            this.f13952a.sendFailureMessage(100013, new Error(e));
            return;
        }
        int c = jmhVar.c();
        LogUtil.a("ApduManager", "onSuccess finishFlag:", Integer.valueOf(c));
        if (c == 0) {
            this.f13952a.sendSuccessMessage(null);
        } else {
            this.e.a(jmhVar.b());
            e();
        }
    }

    public void a(TsmOperatorResponse tsmOperatorResponse) {
        this.j = tsmOperatorResponse;
    }

    private void e() {
        jmb jmbVar = this.e;
        if (jmbVar != null) {
            jmbVar.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.c = 1;
        this.b = -1;
        this.d = null;
        jmb jmbVar = this.e;
        if (jmbVar != null) {
            jmbVar.d().e();
        }
    }
}
