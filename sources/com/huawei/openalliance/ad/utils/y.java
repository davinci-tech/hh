package com.huawei.openalliance.ad.utils;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Window;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.download.app.a;

/* loaded from: classes5.dex */
public abstract class y {

    public interface a {
        void a();

        void a(boolean z);
    }

    public static Dialog a(Context context, String str, String str2, String str3, String str4, a aVar) {
        AlertDialog.Builder a2 = a(context);
        if (str2 != null) {
            a2.setMessage(str2);
        }
        Dialog a3 = a(context, a2, str, str3, str4, aVar);
        a3.show();
        return a3;
    }

    public static Dialog a(Context context, ContentRecord contentRecord, a.InterfaceC0176a interfaceC0176a) {
        com.huawei.openalliance.ad.download.app.a aVar = new com.huawei.openalliance.ad.download.app.a(context, contentRecord, interfaceC0176a);
        aVar.show();
        return aVar;
    }

    private static Dialog a(Context context, AlertDialog.Builder builder, String str, String str2, String str3, final a aVar) {
        Window window;
        if (str != null) {
            builder.setTitle(str);
        }
        builder.setPositiveButton(str2, new DialogInterface.OnClickListener() { // from class: com.huawei.openalliance.ad.utils.y.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                a aVar2 = a.this;
                if (aVar2 != null) {
                    aVar2.a();
                }
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        builder.setNegativeButton(str3, new DialogInterface.OnClickListener() { // from class: com.huawei.openalliance.ad.utils.y.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                a aVar2 = a.this;
                if (aVar2 != null) {
                    aVar2.a(true);
                }
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        AlertDialog create = builder.create();
        create.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.huawei.openalliance.ad.utils.y.3
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                a aVar2 = a.this;
                if (aVar2 != null) {
                    aVar2.a(false);
                }
            }
        });
        if (!(context instanceof Activity) && (window = create.getWindow()) != null) {
            window.setType(2038);
        }
        return create;
    }

    public static AlertDialog.Builder a(Context context) {
        return !com.huawei.openalliance.ad.bz.c(context) ? ao.l(context) ? new AlertDialog.Builder(context, R.style.Theme.DeviceDefault.Dialog.Alert) : new AlertDialog.Builder(context, R.style.Theme.DeviceDefault.Light.Dialog.Alert) : new AlertDialog.Builder(context);
    }
}
