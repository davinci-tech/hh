package defpackage;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.view.View;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;

/* loaded from: classes5.dex */
public class kse extends AlertDialog {
    @Override // android.app.AlertDialog
    public void setView(View view) {
        super.setView(view);
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        e(true);
        try {
            super.onBackPressed();
        } catch (Exception unused) {
            ksy.c("CustomAlertDialog", "catch Exception", true);
        }
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean onSearchRequested() {
        e(true);
        return super.onSearchRequested();
    }

    public void e(final boolean z) {
        try {
            AccessController.doPrivileged(new PrivilegedAction<Object>() { // from class: kse.3
                @Override // java.security.PrivilegedAction
                public Object run() {
                    try {
                        ksy.b("CustomAlertDialog", "Brand:" + Build.MANUFACTURER + "Type:" + Build.MODEL, true);
                        Class<?> cls = kse.this.getClass();
                        int i = 0;
                        while (!cls.getName().equals("android.app.Dialog") && i < 10) {
                            cls = cls.getSuperclass();
                            i++;
                            ksy.b("CustomAlertDialog", "Super Class:" + cls.getName() + " Index:" + i, true);
                        }
                        Field declaredField = cls.getDeclaredField("mShowing");
                        declaredField.setAccessible(true);
                        declaredField.set(kse.this, Boolean.valueOf(z));
                        if (!z) {
                            return null;
                        }
                        kse.this.dismiss();
                        return null;
                    } catch (RuntimeException e) {
                        ksy.c("CustomAlertDialog", "RuntimeException: " + e.getClass().getSimpleName(), true);
                        return null;
                    } catch (Exception e2) {
                        ksy.c("CustomAlertDialog", "Exception: " + e2.getClass().getSimpleName(), true);
                        return null;
                    }
                }
            });
        } catch (Exception e) {
            ksy.c("CustomAlertDialog", "Exception: " + e.getClass().getSimpleName(), true);
        }
    }

    public static class a extends AlertDialog.Builder {
        public a(Context context, int i) {
            super(context, i);
        }
    }
}
