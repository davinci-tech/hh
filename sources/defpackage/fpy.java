package defpackage;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.trade.datatype.ProductSummary;
import com.huawei.trade.datatype.TradeViewInfo;

/* loaded from: classes4.dex */
public class fpy {

    /* renamed from: a, reason: collision with root package name */
    private ProductSummary f12603a;
    private boolean b;
    private boolean c;
    private boolean d;
    private int e;
    private TradeViewInfo g;
    private ProductSummary j;

    public int c() {
        return this.e;
    }

    public void d(int i) {
        this.e = i;
    }

    public boolean j() {
        return this.c;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public boolean a() {
        return this.d;
    }

    public void b(boolean z) {
        this.d = z;
    }

    public boolean f() {
        if (this.f12603a != null) {
            return true;
        }
        return this.b;
    }

    public ProductSummary d() {
        return this.j;
    }

    public void a(ProductSummary productSummary) {
        this.j = productSummary;
    }

    public ProductSummary b() {
        return this.f12603a;
    }

    public void e(ProductSummary productSummary) {
        this.f12603a = productSummary;
    }

    public TradeViewInfo e() {
        return this.g;
    }

    public void d(TradeViewInfo tradeViewInfo) {
        this.g = tradeViewInfo;
    }

    public boolean i() {
        if (h()) {
            return true;
        }
        return LoginInit.getInstance(BaseApplication.getContext()).isKidAccount();
    }

    public boolean h() {
        int i = this.e;
        if (i == 0) {
            return true;
        }
        if (this.c && i == 2) {
            return true;
        }
        return i == 1 && this.d;
    }

    public boolean l() {
        return this.e == 2 && !this.c;
    }

    public boolean o() {
        return (this.e != 1 || f() || this.d) ? false : true;
    }

    public boolean n() {
        return this.e == 1 && f() && !this.c && !this.d;
    }

    public boolean k() {
        return this.e == 1 && f() && this.c && !this.d;
    }

    public boolean g() {
        return this.e == 1 && f() && !this.d;
    }
}
