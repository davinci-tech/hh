package com.huawei.watchface.videoedit.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes9.dex */
public class SuperDefaultDialog extends BaseDefaultContentDialog {
    private static Dialog sDialog;
    private static SuperDefaultDialog sSuperDefaultDialogInstance;
    private boolean mCancelable = true;

    public static SuperDefaultDialog newInstance(Dialog dialog) {
        sDialog = null;
        sSuperDefaultDialogInstance = null;
        SuperDefaultDialog superDefaultDialog = new SuperDefaultDialog();
        sSuperDefaultDialogInstance = superDefaultDialog;
        sDialog = dialog;
        return superDefaultDialog;
    }

    @Override // com.huawei.watchface.videoedit.ui.BaseDefaultContentDialog, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getDialog() != null) {
            getDialog().dismiss();
        }
    }

    public static SuperDefaultDialog getInstance() {
        return sSuperDefaultDialogInstance;
    }

    public void setDialogCancelable(boolean z) {
        this.mCancelable = z;
    }

    @Override // com.huawei.watchface.videoedit.ui.BaseDialogFragment, androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        return sDialog;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog getDialog() {
        return sDialog;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setCancelable(this.mCancelable);
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }
}
