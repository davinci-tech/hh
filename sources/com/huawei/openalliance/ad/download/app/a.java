package com.huawei.openalliance.ad.download.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.os;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.x;

/* loaded from: classes5.dex */
public class a extends AlertDialog {

    /* renamed from: a, reason: collision with root package name */
    private InterfaceC0176a f6739a;
    private boolean b;
    private AppInfo c;
    private ContentRecord d;
    private Context e;
    private com.huawei.openalliance.ad.analysis.h f;

    /* renamed from: com.huawei.openalliance.ad.download.app.a$a, reason: collision with other inner class name */
    public interface InterfaceC0176a {
        void a();

        void b();
    }

    @Override // android.app.Dialog
    public void show() {
        ho.b("AppAllowInstallDialog", "show appAllowInstallDialog");
        if (!c()) {
            ho.b("AppAllowInstallDialog", "don't remind again!");
            InterfaceC0176a interfaceC0176a = this.f6739a;
            if (interfaceC0176a != null) {
                interfaceC0176a.a();
                d();
                return;
            }
            return;
        }
        if (this.d == null || this.c == null || b().length() <= 0) {
            return;
        }
        if (this.c.C() == 0) {
            ho.b("AppAllowInstallDialog", "show toast popUp!");
            Toast.makeText(this.e.getApplicationContext(), b(), 0).show();
            this.f.e(this.d, "149");
            d();
            return;
        }
        try {
            super.show();
            ho.b("AppAllowInstallDialog", "show dialog popUp!");
            this.f.e(this.d, "150");
        } catch (Exception unused) {
            d();
            ho.c("AppAllowInstallDialog", "error occurs while showing dialog");
        }
    }

    @Override // android.app.AlertDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        if (window != null) {
            if (!(this.e instanceof Activity)) {
                window.setType(2038);
            }
            window.setBackgroundDrawableResource(R.color._2131297887_res_0x7f09065f);
            if (x.o(this.e)) {
                window.setGravity(80);
            }
            setCancelable(false);
            setContentView(R.layout.hiad_app_allow_install_dialog_cotent);
            a();
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        ho.b("AppAllowInstallDialog", "dialog is dismissed");
        super.dismiss();
        d();
    }

    private void d() {
        InterfaceC0176a interfaceC0176a = this.f6739a;
        if (interfaceC0176a != null) {
            interfaceC0176a.b();
        }
    }

    private boolean c() {
        return fh.b(this.e).cb();
    }

    private String b() {
        Resources resources;
        int i;
        String string;
        Resources resources2;
        int i2;
        String string2;
        Resources resources3;
        int i3;
        String string3;
        ContentRecord contentRecord = this.d;
        if (contentRecord == null) {
            ho.b("AppAllowInstallDialog", "contendRecord is null");
            return "";
        }
        int C = os.C(contentRecord.V());
        if (cz.b(this.c.D())) {
            if (this.c.C() > 0) {
                resources = this.e.getResources();
                i = R.string._2130851003_res_0x7f0234bb;
            } else {
                resources = this.e.getResources();
                i = R.string._2130851004_res_0x7f0234bc;
            }
            string = resources.getString(i);
        } else {
            string = this.c.D();
        }
        if (cz.b(this.c.E())) {
            if (this.c.C() > 0) {
                resources2 = this.e.getResources();
                i2 = R.string._2130851005_res_0x7f0234bd;
            } else {
                resources2 = this.e.getResources();
                i2 = R.string._2130851006_res_0x7f0234be;
            }
            string2 = resources2.getString(i2);
        } else {
            string2 = this.c.E();
        }
        if (cz.b(this.c.F())) {
            if (this.c.C() > 0) {
                resources3 = this.e.getResources();
                i3 = R.string._2130851001_res_0x7f0234b9;
            } else {
                resources3 = this.e.getResources();
                i3 = R.string._2130851002_res_0x7f0234ba;
            }
            string3 = resources3.getString(i3);
        } else {
            string3 = this.c.F();
        }
        int a2 = a(C, dd.w(this.e), dd.v(this.e));
        return a2 != 1 ? a2 != 2 ? a2 != 3 ? "" : string3 : string : string2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        fh.b(this.e).f(!z);
    }

    private void a() {
        ((TextView) findViewById(R.id.hiad_allow_install_title)).setText(getContext().getResources().getString(R.string._2130850999_res_0x7f0234b7));
        ((TextView) findViewById(R.id.hiad_allow_install_close)).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.download.app.a.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                a.this.f.e(a.this.d, "151");
                a aVar = a.this;
                aVar.a(aVar.b);
                a.this.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        ((TextView) findViewById(R.id.hiad_allow_install_message)).setText(b());
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.hiad_allow_install_remind_again_parent);
        final CheckBox checkBox = (CheckBox) findViewById(R.id.hiad_allow_install_remind_again);
        checkBox.setChecked(this.b);
        checkBox.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.download.app.a.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (a.this.b) {
                    radioGroup.clearCheck();
                } else {
                    checkBox.setChecked(true);
                }
                a aVar = a.this;
                aVar.b = true ^ aVar.b;
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        TextView textView = (TextView) findViewById(R.id.hiad_allow_install_accept);
        textView.setText(!cz.b(this.c.G()) ? this.c.G() : this.e.getResources().getString(R.string._2130850998_res_0x7f0234b6));
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.download.app.a.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (a.this.f6739a != null) {
                    a.this.f.e(a.this.d, "152");
                    a.this.f6739a.a();
                }
                a aVar = a.this;
                aVar.a(aVar.b);
                a.this.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public static int a(int i, int i2, boolean z) {
        if (i > 3) {
            i -= 3;
        }
        int i3 = i2 != 1 ? 0 : 1;
        int i4 = z ? 0 : 2;
        ho.a("AppAllowInstallDialog", "pure mode is %s, install permission is %s", Integer.valueOf(i2), Boolean.valueOf(z));
        return (i & i3) | (i & i4);
    }

    public a(Context context, ContentRecord contentRecord, InterfaceC0176a interfaceC0176a) {
        super(context);
        this.b = true;
        this.e = context;
        this.d = contentRecord;
        this.f6739a = interfaceC0176a;
        if (contentRecord != null) {
            this.c = contentRecord.ae();
        }
        this.f = new com.huawei.openalliance.ad.analysis.h(context);
    }
}
