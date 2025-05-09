package defpackage;

import android.content.Context;
import com.huawei.appgallery.marketinstallerservice.api.Constant;
import com.huawei.appgallery.marketinstallerservice.api.FailResultParam;
import com.huawei.appgallery.marketinstallerservice.api.InstallCallback;
import com.huawei.appgallery.marketinstallerservice.api.InstallParamSpec;
import com.huawei.appgallery.marketinstallerservice.api.MarketInfo;
import com.huawei.appgallery.marketinstallerservice.impl.download.a;
import com.huawei.appgallery.marketinstallerservice.impl.download.b;
import java.io.File;

/* loaded from: classes3.dex */
public class agy implements a.InterfaceC0042a, b.a {
    private Context b;
    private InstallParamSpec c;
    private InstallCallback e;
    private a j;
    private String d = "";

    /* renamed from: a, reason: collision with root package name */
    private FailResultParam f200a = new FailResultParam();

    @Override // com.huawei.appgallery.marketinstallerservice.impl.download.a.InterfaceC0042a, com.huawei.appgallery.marketinstallerservice.impl.download.b.a
    public Context b() {
        return this.b;
    }

    @Override // com.huawei.appgallery.marketinstallerservice.impl.download.b.a
    public void a(boolean z) {
        if (z) {
            return;
        }
        this.f200a.setResult(-2);
        this.f200a.setReason(-10001);
        e();
        agh.e(this.d);
        agh.b(this.d);
    }

    @Override // com.huawei.appgallery.marketinstallerservice.impl.download.a.InterfaceC0042a
    public void a(MarketInfo marketInfo, int i, int i2) {
        this.f200a.setResponseCode(i);
        this.f200a.setRtnCode(i2);
        this.c.setMarketInfo(marketInfo);
        if (marketInfo == null) {
            this.f200a.setResult(-4);
            e();
        }
    }

    @Override // com.huawei.appgallery.marketinstallerservice.impl.download.a.InterfaceC0042a
    public void a(int i, int i2) {
        if (i != 2) {
            if (i == 3) {
                d();
                return;
            } else if (i != 4) {
                if (i != 5) {
                    return;
                }
                this.f200a.setReason(Constant.INSTALL_FAILED_SHA256_EEROR);
                this.f200a.setResult(-3);
                return;
            }
        }
        this.f200a.setResult(-3);
        e();
    }

    public void c() {
        if (!aha.d(this.b)) {
            this.f200a.setResult(-1);
            e();
        } else {
            a aVar = new a(this, this.c);
            this.j = aVar;
            aVar.execute(new Void[0]);
        }
    }

    private void d() {
        if (!b.e(this.b)) {
            this.f200a.setResult(-2);
            e();
        } else {
            String a2 = agh.a(this.e);
            this.d = a2;
            agh.e(a2, this.c.getMarketInfo());
            new b(this, this.c, this.d).execute(new Void[0]);
        }
    }

    private void e() {
        this.f200a.setMarketInfo(this.c.getMarketInfo());
        InstallCallback installCallback = this.e;
        if (installCallback != null) {
            installCallback.onFailed(this.f200a);
        }
        File file = new File(agj.a(this.b));
        if (!file.exists() || file.delete()) {
            return;
        }
        agr.c("SilentDownloadManager", "delete DownloadFile failed");
    }

    public agy(Context context, InstallParamSpec installParamSpec, InstallCallback installCallback) {
        this.b = context;
        this.c = installParamSpec;
        this.e = installCallback;
    }
}
