package com.huawei.appgallery.marketinstallerservice.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;

/* loaded from: classes3.dex */
public class c {

    public interface a {
        void a();

        void b();
    }

    static class b implements DialogInterface.OnClickListener, DialogInterface.OnDismissListener {
        private int b;
        private boolean c = true;
        private a d;

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            a aVar = this.d;
            if (aVar == null || !this.c) {
                return;
            }
            aVar.b();
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            a aVar;
            int i2;
            if (i == -1 && (aVar = this.d) != null && ((i2 = this.b) == -3 || i2 == -2)) {
                aVar.a();
                this.c = false;
            }
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
        }

        b(a aVar, int i) {
            this.d = aVar;
            this.b = i;
        }
    }

    public static AlertDialog hM_(Context context, int i, a aVar, String str) {
        int i2;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        b bVar = new b(aVar, i);
        if (str == null) {
            str = "";
        }
        if (i != -4) {
            if (i != -3) {
                if (i == -2) {
                    builder.setMessage(context.getResources().getString(R.string._2130851386_res_0x7f02363a, str));
                    i2 = R.string._2130851390_res_0x7f02363e;
                }
                return builder.create();
            }
            builder.setMessage(context.getResources().getString(R.string._2130851382_res_0x7f023636));
            i2 = R.string._2130851389_res_0x7f02363d;
            builder.setPositiveButton(i2, bVar);
            builder.setNegativeButton(R.string._2130851380_res_0x7f023634, (DialogInterface.OnClickListener) null);
        } else {
            builder.setMessage(context.getResources().getString(R.string._2130851379_res_0x7f023633, str));
            builder.setPositiveButton(R.string._2130851376_res_0x7f023630, (DialogInterface.OnClickListener) null);
        }
        builder.setOnDismissListener(bVar);
        return builder.create();
    }
}
