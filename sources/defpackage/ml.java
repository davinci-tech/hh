package defpackage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.KeyEvent;

/* loaded from: classes7.dex */
public class ml {

    public static final class a implements DialogInterface.OnKeyListener {
        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            return i == 4;
        }
    }

    public static Dialog bo_(Context context, String str, String str2, String str3, DialogInterface.OnClickListener onClickListener, String str4, DialogInterface.OnClickListener onClickListener2) {
        AlertDialog.Builder bn_ = bn_(context, str, str3, onClickListener, str4, onClickListener2);
        bn_.setTitle(str);
        bn_.setMessage(str2);
        AlertDialog create = bn_.create();
        create.setCanceledOnTouchOutside(false);
        create.setOnKeyListener(new a());
        try {
            create.show();
        } catch (Throwable th) {
            ma.a("mspl", "showDialog ", th);
        }
        return create;
    }

    public static AlertDialog.Builder bn_(Context context, String str, String str2, DialogInterface.OnClickListener onClickListener, String str3, DialogInterface.OnClickListener onClickListener2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (!TextUtils.isEmpty(str3) && onClickListener2 != null) {
            builder.setPositiveButton(str3, onClickListener2);
        }
        if (!TextUtils.isEmpty(str2) && onClickListener != null) {
            builder.setNegativeButton(str2, onClickListener);
        }
        return builder;
    }
}
