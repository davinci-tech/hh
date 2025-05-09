package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import com.huawei.appgallery.marketinstallerservice.api.Constant;
import com.huawei.appgallery.marketinstallerservice.api.FailResultParam;
import com.huawei.appgallery.marketinstallerservice.api.InstallCallback;
import com.huawei.appgallery.marketinstallerservice.api.InstallParamSpec;
import com.huawei.appgallery.marketinstallerservice.api.MarketInfo;
import com.huawei.appgallery.marketinstallerservice.impl.download.a;
import com.huawei.appgallery.marketinstallerservice.impl.download.b;
import com.huawei.appgallery.marketinstallerservice.ui.a;
import java.io.File;

/* loaded from: classes3.dex */
public class ahf implements a, a.InterfaceC0042a, b.a {

    /* renamed from: a, reason: collision with root package name */
    private com.huawei.appgallery.marketinstallerservice.impl.download.a f203a;
    private Activity c;
    private com.huawei.appgallery.marketinstallerservice.ui.b d;
    private InstallParamSpec e = null;
    private String b = null;
    private FailResultParam g = new FailResultParam();

    @Override // com.huawei.appgallery.marketinstallerservice.ui.a
    public void e() {
        InstallCallback a2 = agh.a(this.b);
        if (a2 != null) {
            a2.onFailed(this.g);
            agh.b(this.b);
        }
    }

    @Override // com.huawei.appgallery.marketinstallerservice.ui.a
    public MarketInfo d() {
        InstallParamSpec installParamSpec = this.e;
        return (installParamSpec == null || installParamSpec.getMarketInfo() == null) ? new MarketInfo() : this.e.getMarketInfo();
    }

    @Override // com.huawei.appgallery.marketinstallerservice.ui.a
    public void c() {
        com.huawei.appgallery.marketinstallerservice.impl.download.a aVar = this.f203a;
        if (aVar != null) {
            aVar.cancel(true);
        }
    }

    @Override // com.huawei.appgallery.marketinstallerservice.impl.download.a.InterfaceC0042a, com.huawei.appgallery.marketinstallerservice.impl.download.b.a
    public Context b() {
        return this.c;
    }

    @Override // com.huawei.appgallery.marketinstallerservice.impl.download.b.a
    public void a(boolean z) {
        if (z) {
            return;
        }
        a(-2, 0, 0, -10001);
        this.d.a(-2);
    }

    @Override // com.huawei.appgallery.marketinstallerservice.impl.download.a.InterfaceC0042a
    public void a(MarketInfo marketInfo, int i, int i2) {
        InstallParamSpec installParamSpec;
        if (marketInfo == null || (installParamSpec = this.e) == null) {
            a(-4, i, i2);
            this.d.a(-4);
        } else {
            installParamSpec.setMarketInfo(marketInfo);
            this.d.a(marketInfo);
        }
    }

    @Override // com.huawei.appgallery.marketinstallerservice.ui.a
    public void a(InstallParamSpec installParamSpec, String str) {
        this.e = installParamSpec;
        this.b = str;
        if (!aha.d(this.c)) {
            this.d.c();
            return;
        }
        com.huawei.appgallery.marketinstallerservice.impl.download.a aVar = new com.huawei.appgallery.marketinstallerservice.impl.download.a(this, this.e);
        this.f203a = aVar;
        aVar.execute(new Void[0]);
        this.d.d();
    }

    @Override // com.huawei.appgallery.marketinstallerservice.ui.a
    public void a(int i, int i2, int i3, int i4) {
        agr.c("MarketDownloadPresenter", "notifyResult errorCode" + i + ", responseCode=" + i2 + ", rtnCode=" + i3);
        this.g.setResult(i);
        this.g.setResponseCode(i2);
        this.g.setRtnCode(i3);
        this.g.setReason(i4);
        InstallParamSpec installParamSpec = this.e;
        MarketInfo marketInfo = installParamSpec != null ? installParamSpec.getMarketInfo() : null;
        this.g.setMarketInfo(marketInfo);
        InstallParamSpec installParamSpec2 = this.e;
        if (installParamSpec2 != null && installParamSpec2.getFailResultPromptType() == 2 && (-3 == i || -2 == i)) {
            agr.c("MarketDownloadPresenter", "need show retry dialog!");
        } else {
            InstallCallback a2 = agh.a(this.b);
            if (a2 != null) {
                if (i == 0) {
                    a2.onSuccess(marketInfo);
                } else {
                    a2.onFailed(this.g);
                }
                agh.b(this.b);
            }
        }
        File file = new File(agj.a(this.c));
        if (!file.exists() || file.delete()) {
            return;
        }
        agr.c("MarketDownloadPresenter", "delete DownloadFile failed");
    }

    @Override // com.huawei.appgallery.marketinstallerservice.ui.a
    public void a(int i, int i2, int i3) {
        a(i, i2, i3, 0);
    }

    @Override // com.huawei.appgallery.marketinstallerservice.impl.download.a.InterfaceC0042a
    public void a(int i, int i2) {
        if (i == 1) {
            this.d.a(i, i2);
            return;
        }
        if (i != 2) {
            if (i == 3) {
                this.d.a(i, i2);
                i();
                return;
            } else if (i != 4) {
                if (i != 5) {
                    return;
                }
                a(-3, 0, 0, Constant.INSTALL_FAILED_SHA256_EEROR);
                this.d.a(-3);
            }
        }
        a(-3, 0, 0);
        this.d.a(-3);
    }

    @Override // com.huawei.appgallery.marketinstallerservice.ui.a
    public void a() {
        this.e.setMarketInfo(null);
        a(this.e, this.b);
    }

    private void i() {
        if (b.e(this.c)) {
            new b(this).execute(new Void[0]);
            return;
        }
        try {
            Activity activity = this.c;
            activity.startActivityForResult(b.hJ_(activity), 1000);
        } catch (ActivityNotFoundException unused) {
            agr.e("MarketDownloadPresenter", "startActivityForResult ActivityNotFoundException");
            a(-2, 0, 0);
            this.d.a(-2);
        }
    }

    public ahf(Activity activity, com.huawei.appgallery.marketinstallerservice.ui.b bVar) {
        this.c = activity;
        this.d = bVar;
    }
}
