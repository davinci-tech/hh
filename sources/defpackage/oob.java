package defpackage;

import android.text.TextUtils;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import com.huawei.hms.network.embedded.u3;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homehealth.qrcode.util.QrCodeDataBase;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Utils;

/* loaded from: classes6.dex */
public class oob extends QrCodeDataBase {

    /* renamed from: a, reason: collision with root package name */
    private String f15820a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String j;

    public oob(String str) {
        super(str);
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeDataBase
    public int parser(Object obj) {
        if (!(obj instanceof String)) {
            LogUtil.h("MeasureQrCodeData", "parser() data is null or not string type");
            return -3;
        }
        String str = (String) obj;
        String a2 = ope.a(FitRunPlayAudio.OPPORTUNITY_M, str);
        String a3 = ope.a("ble", str);
        if (TextUtils.isEmpty(a2)) {
            a2 = a3;
        }
        this.f15820a = a2;
        this.e = ope.a("brd", str);
        this.d = ope.a(u3.m, str);
        this.c = ope.a("l", str);
        this.b = ope.a("n", str);
        this.j = ope.a(FitRunPlayAudio.PLAY_TYPE_T, str);
        boolean z = (TextUtils.isEmpty(this.f15820a) && TextUtils.isEmpty(this.b)) ? false : true;
        boolean isEmpty = true ^ TextUtils.isEmpty(this.d);
        if (ope.a(this.j)) {
            if ("68".equals(this.j)) {
                if (TextUtils.isEmpty(this.e) || !z) {
                    return -2;
                }
                return j();
            }
            if ("69".equals(this.j)) {
                if (isEmpty && z) {
                    return j();
                }
                return -2;
            }
            LogUtil.h("MeasureQrCodeData", "type is error");
        } else {
            if (!TextUtils.isEmpty(this.j) && ope.d(this.j)) {
                if (isEmpty && z) {
                    return j();
                }
                return -2;
            }
            LogUtil.h("MeasureQrCodeData", "type is error");
        }
        return -2;
    }

    private int j() {
        if (i()) {
            LogUtil.a("MeasureQrCodeData", "isOversea, not allowed use!");
            return -2;
        }
        if (!CommonUtil.bl()) {
            LogUtil.a("MeasureQrCodeData", "not support this phone, return");
            return -2;
        }
        LogUtil.a("MeasureQrCodeData", "Parsing succeeded");
        return 0;
    }

    private boolean i() {
        if (LanguageUtil.al(BaseApplication.getContext()) && !Utils.o()) {
            return false;
        }
        LogUtil.a("MeasureQrCodeData", "isOverseaForIndoorEquip!");
        return true;
    }

    public String b() {
        return this.j;
    }

    public String a() {
        return this.e;
    }

    public String d() {
        return this.f15820a;
    }

    public String c() {
        return this.b;
    }

    public String e() {
        return this.d;
    }

    public String g() {
        return this.c;
    }
}
