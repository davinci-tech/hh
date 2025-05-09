package com.huawei.updatesdk.b.i;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/* loaded from: classes7.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private com.huawei.updatesdk.b.i.b f10849a;
    private final Context b;
    private AlertDialog c;
    private final AlertDialog.Builder d;
    private DialogInterface.OnShowListener e;
    private DialogInterface.OnDismissListener f;

    public interface e {
        void a();
    }

    public void c() {
        AlertDialog.Builder builder = this.d;
        if (builder == null) {
            return;
        }
        builder.setNegativeButton((CharSequence) null, (DialogInterface.OnClickListener) null);
    }

    public boolean b() {
        AlertDialog alertDialog = this.c;
        return alertDialog != null && alertDialog.isShowing();
    }

    public void a(boolean z) {
        AlertDialog alertDialog = this.c;
        if (alertDialog != null) {
            alertDialog.setCancelable(z);
        }
    }

    public void a(com.huawei.updatesdk.b.i.b bVar) {
        this.f10849a = bVar;
    }

    public void a(e eVar) {
        Context context = this.b;
        if (context != null && !((Activity) context).isFinishing()) {
            if (b()) {
                return;
            }
            b(eVar);
        } else {
            com.huawei.updatesdk.a.a.a.b("BaseAlertDialog", "context == null or activity isFinishing");
            if (eVar != null) {
                eVar.a();
            }
        }
    }

    public void a(View view) {
        ImageView imageView;
        if (this.d != null) {
            if ((com.huawei.updatesdk.b.h.a.f().a() >= 17 || com.huawei.updatesdk.b.h.a.f().b() > 0) && (imageView = (ImageView) view.findViewById(com.huawei.updatesdk.b.h.c.a(view.getContext(), "divider"))) != null) {
                imageView.setVisibility(8);
            }
            this.d.setMessage((CharSequence) null);
            this.d.setView(view);
        }
    }

    public void a(DialogInterface.OnShowListener onShowListener) {
        this.e = onShowListener;
    }

    public void a(DialogInterface.OnKeyListener onKeyListener) {
        AlertDialog alertDialog = this.c;
        if (alertDialog != null) {
            alertDialog.setOnKeyListener(onKeyListener);
        }
    }

    public void a(DialogInterface.OnDismissListener onDismissListener) {
        this.f = onDismissListener;
    }

    public void a(int i, String str) {
        Button button;
        AlertDialog alertDialog = this.c;
        if (alertDialog == null || (button = alertDialog.getButton(i)) == null) {
            return;
        }
        button.setText(str);
        button.setAllCaps(true);
    }

    public void a() {
        try {
            AlertDialog alertDialog = this.c;
            if (alertDialog != null) {
                alertDialog.dismiss();
                this.c = null;
            }
        } catch (IllegalArgumentException unused) {
            com.huawei.updatesdk.a.a.a.b("BaseAlertDialog", "dialog dismiss IllegalArgumentException");
        }
    }

    private void b(e eVar) {
        try {
            this.c = this.d.create();
            com.huawei.updatesdk.a.a.d.i.c.f().a(this.c.getWindow());
            this.c.setCanceledOnTouchOutside(false);
            this.c.setOnShowListener(new DialogInterfaceOnShowListenerC0280a());
            this.c.setOnDismissListener(new b());
            this.c.show();
            this.c.getButton(-1).requestFocus();
        } catch (Exception e2) {
            if (eVar != null) {
                eVar.a();
            }
            com.huawei.updatesdk.a.a.a.a("BaseAlertDialog", "show dlg error, e: " + e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(DialogInterface dialogInterface) {
        AlertDialog alertDialog = (AlertDialog) dialogInterface;
        alertDialog.getButton(-1).setOnClickListener(new c());
        Button button = alertDialog.getButton(-2);
        if (button != null) {
            button.setOnClickListener(new d());
        }
    }

    /* renamed from: com.huawei.updatesdk.b.i.a$a, reason: collision with other inner class name */
    class DialogInterfaceOnShowListenerC0280a implements DialogInterface.OnShowListener {
        @Override // android.content.DialogInterface.OnShowListener
        public void onShow(DialogInterface dialogInterface) {
            a.this.a(dialogInterface);
            if (a.this.e != null) {
                a.this.e.onShow(dialogInterface);
            }
        }

        DialogInterfaceOnShowListenerC0280a() {
        }
    }

    class b implements DialogInterface.OnDismissListener {
        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            if (a.this.f != null) {
                a.this.f.onDismiss(dialogInterface);
            }
        }

        b() {
        }
    }

    class c implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (a.this.f10849a != null) {
                a.this.f10849a.a();
            }
        }

        c() {
        }
    }

    class d implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (a.this.f10849a != null) {
                a.this.f10849a.b();
            }
        }

        d() {
        }
    }

    public static a a(Context context, String str, CharSequence charSequence) {
        return new a(context, str, charSequence);
    }

    protected a(Context context, String str, CharSequence charSequence) {
        this.b = context;
        AlertDialog.Builder builder = com.huawei.updatesdk.a.a.d.i.c.l() ? new AlertDialog.Builder(context) : ((context.getResources().getConfiguration().uiMode & 48) != 32 || Build.VERSION.SDK_INT < 29) ? new AlertDialog.Builder(context, R.style.Theme.DeviceDefault.Light.Dialog.Alert) : new AlertDialog.Builder(context, R.style.Theme.DeviceDefault.Dialog.Alert);
        this.d = builder;
        builder.setTitle(str);
        builder.setPositiveButton(com.huawei.updatesdk.b.h.c.c(context, "upsdk_third_app_dl_sure_cancel_download"), (DialogInterface.OnClickListener) null);
        builder.setNegativeButton(com.huawei.updatesdk.b.h.c.c(context, "upsdk_cancel"), (DialogInterface.OnClickListener) null);
        builder.setMessage(charSequence);
    }
}
