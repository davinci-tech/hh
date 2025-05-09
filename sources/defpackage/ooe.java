package defpackage;

import android.os.Build;
import android.text.TextUtils;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import com.huawei.hms.network.embedded.u3;
import com.huawei.hwadpaterhealthmgr.PluginHealthTrackAdapterImpl;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homehealth.qrcode.util.QrCodeDataBase;
import health.compact.a.CommonUtil;

/* loaded from: classes6.dex */
public class ooe extends QrCodeDataBase {

    /* renamed from: a, reason: collision with root package name */
    private String f15823a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String g;

    public ooe(String str) {
        super(str);
        PluginSuggestion pluginSuggestion = PluginSuggestion.getInstance();
        if (pluginSuggestion == null) {
            return;
        }
        pluginSuggestion.init(BaseApplication.getContext());
        gso.e().setAdapter(PluginHealthTrackAdapterImpl.getInstance(BaseApplication.getContext()));
        gso.e().init(BaseApplication.getContext());
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeDataBase
    public int parser(Object obj) {
        if (!(obj instanceof String)) {
            LogUtil.h("IndoorEquipQrCodeData", "parser() data is null or not string type");
            return -3;
        }
        String str = (String) obj;
        this.f15823a = ope.a(FitRunPlayAudio.OPPORTUNITY_M, str);
        this.c = ope.a("p", str);
        this.b = ope.a("n", str);
        this.g = ope.a(FitRunPlayAudio.PLAY_TYPE_T, str);
        this.d = ope.a(u3.m, str);
        this.e = ope.a("ble", str);
        boolean equals = "1".equals(this.c);
        if (!TextUtils.isEmpty(this.g) && this.g.equals("262")) {
            return 0;
        }
        boolean equals2 = "28L1".equals(this.d);
        if ("261".equals(this.g) && equals2 && !TextUtils.isEmpty(this.e)) {
            return 0;
        }
        boolean z = true;
        boolean z2 = ope.c(this.g) && !TextUtils.isEmpty(this.c);
        if (TextUtils.isEmpty(this.f15823a) && TextUtils.isEmpty(this.b)) {
            z = false;
        }
        if (!z2 || !z) {
            return -2;
        }
        if (!CommonUtil.bl()) {
            LogUtil.a("IndoorEquipQrCodeData", "not support this phone, return");
            return -2;
        }
        if (!CommonUtil.bh()) {
            LogUtil.a("IndoorEquipQrCodeData", "not huawei phone! API level:", Integer.valueOf(Build.VERSION.SDK_INT));
            if (!equals) {
                LogUtil.a("IndoorEquipQrCodeData", "is DFH and larger than android6, allowed use");
            } else {
                LogUtil.a("IndoorEquipQrCodeData", "not DFH, not allowed use!");
                return -2;
            }
        }
        LogUtil.a("IndoorEquipQrCodeData", "Parsing succeeded");
        return 0;
    }

    public String d() {
        return this.g;
    }

    public String g() {
        return this.c;
    }

    public String c() {
        return this.f15823a;
    }

    public String b() {
        return this.b;
    }

    public String e() {
        return this.d;
    }

    public String a() {
        return this.e;
    }
}
