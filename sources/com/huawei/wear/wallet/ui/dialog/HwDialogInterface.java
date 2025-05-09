package com.huawei.wear.wallet.ui.dialog;

import android.content.DialogInterface;

/* loaded from: classes9.dex */
public interface HwDialogInterface {
    void setCancelable(boolean z);

    void setCanceledOnTouchOutside(boolean z);

    HwDialogInterface setMessage(int i);

    HwDialogInterface setMessage(String str);

    HwDialogInterface setNegativeButton(int i, DialogInterface.OnClickListener onClickListener);

    HwDialogInterface setNegativeButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener);

    HwDialogInterface setPositiveButton(int i, DialogInterface.OnClickListener onClickListener);

    HwDialogInterface setPositiveButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener);

    void show();
}
