package defpackage;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.huawei.android.hicloud.sync.update.ui.dialog.UpHisyncDialogCallback;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;

/* loaded from: classes8.dex */
public class aax extends AlertDialog {

    /* renamed from: a, reason: collision with root package name */
    private long f153a;
    private Context b;
    UpHisyncDialogCallback d;

    class c implements DialogInterface.OnClickListener {
        c() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
            UpHisyncDialogCallback upHisyncDialogCallback = aax.this.d;
            if (upHisyncDialogCallback != null) {
                if (i == -1) {
                    upHisyncDialogCallback.onClickInstall();
                } else if (i == -2) {
                    upHisyncDialogCallback.onClickCancel();
                }
            }
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
        }
    }

    public aax(Context context, UpHisyncDialogCallback upHisyncDialogCallback, long j) {
        super(context);
        this.b = context;
        this.d = upHisyncDialogCallback;
        this.f153a = j;
        c();
    }

    private void c() {
        View inflate = LayoutInflater.from(this.b).inflate(R.layout.up_hisync_dialog, (ViewGroup) null);
        setView(inflate);
        setCancelable(false);
        TextView textView = (TextView) abj.fw_(inflate, R.id.up_hisync_dialog_content_tip);
        String b = aba.b(this.b, this.f153a);
        Context context = this.b;
        textView.setText(context.getString(R.string._2130851601_res_0x7f023711, context.getString(R.string._2130851190_res_0x7f023576), b));
        setButton(-2, this.b.getString(R.string._2130851598_res_0x7f02370e), new c());
        setButton(-1, this.b.getString(R.string._2130851599_res_0x7f02370f), new c());
    }
}
