package defpackage;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.widget.ArrayAdapter;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwidauth.ui.d;

/* loaded from: classes5.dex */
public class ksa extends AlertDialog.Builder {

    /* renamed from: a, reason: collision with root package name */
    private d f14563a;
    private AlertDialog b;
    private Activity c;
    private String d;
    private Uri e;

    public ksa(Activity activity, Uri uri, final d dVar) {
        super(activity, kte.a(activity));
        this.d = ".hwidauth.fileProvider";
        this.c = activity;
        this.e = uri;
        this.f14563a = dVar;
        setAdapter(new ArrayAdapter(this.c, R.layout.item_hwid_auth_dialog_list, R.id.dialog_item_text, new CharSequence[]{activity.getResources().getString(R.string._2130841118_res_0x7f020e1e), activity.getResources().getString(R.string._2130841119_res_0x7f020e1f), activity.getResources().getString(android.R.string.cancel)}), new DialogInterface.OnClickListener() { // from class: ksa.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    ksy.b("WebViewActivityAddPicDialog", "startCamare", true);
                    dVar.b();
                } else if (i == 1) {
                    ksy.b("WebViewActivityAddPicDialog", "startGallery", true);
                    dVar.c();
                } else if (i == 2) {
                    ksy.b("WebViewActivityAddPicDialog", "CANCEL", true);
                    ksa.this.f14563a.a();
                    ksa.this.e();
                }
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
    }

    @Override // android.app.AlertDialog.Builder
    public AlertDialog show() {
        AlertDialog show = super.show();
        this.b = show;
        return show;
    }

    public void e() {
        ksy.c("WebViewActivityAddPicDialog", "cancelDialog mDialog = " + this.b, false);
        AlertDialog alertDialog = this.b;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        ksy.c("WebViewActivityAddPicDialog", "cancelDialog enter ", true);
        this.b.cancel();
    }
}
