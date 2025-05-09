package com.huawei.hms.update.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import com.huawei.hms.utils.ResourceLoaderUtil;

/* loaded from: classes9.dex */
public final class ConfirmDialogs {

    public static class NetTypeConfirm extends b {
        public NetTypeConfirm() {
            super();
        }

        @Override // com.huawei.hms.update.ui.ConfirmDialogs.b
        protected int getMessageResId() {
            return ResourceLoaderUtil.getStringId("hms_update_nettype");
        }

        @Override // com.huawei.hms.update.ui.ConfirmDialogs.b
        protected int getNegativeButtonResId() {
            return ResourceLoaderUtil.getStringId("hms_cancel");
        }

        @Override // com.huawei.hms.update.ui.ConfirmDialogs.b
        protected int getPositiveButtonResId() {
            return ResourceLoaderUtil.getStringId("hms_update_continue");
        }

        @Override // com.huawei.hms.update.ui.ConfirmDialogs.b, com.huawei.hms.update.ui.AbstractDialog
        public /* bridge */ /* synthetic */ AlertDialog onCreateDialog() {
            return super.onCreateDialog();
        }
    }

    public static class RetryConfirm extends b {
        public RetryConfirm() {
            super();
        }

        @Override // com.huawei.hms.update.ui.ConfirmDialogs.b
        protected int getMessageResId() {
            return ResourceLoaderUtil.getStringId("hms_download_retry");
        }

        @Override // com.huawei.hms.update.ui.ConfirmDialogs.b
        protected int getNegativeButtonResId() {
            return ResourceLoaderUtil.getStringId("hms_cancel");
        }

        @Override // com.huawei.hms.update.ui.ConfirmDialogs.b
        protected int getPositiveButtonResId() {
            return ResourceLoaderUtil.getStringId("hms_retry");
        }

        @Override // com.huawei.hms.update.ui.ConfirmDialogs.b, com.huawei.hms.update.ui.AbstractDialog
        public /* bridge */ /* synthetic */ AlertDialog onCreateDialog() {
            return super.onCreateDialog();
        }
    }

    public static class StopConfirm extends b {
        public StopConfirm() {
            super();
        }

        @Override // com.huawei.hms.update.ui.ConfirmDialogs.b
        protected int getMessageResId() {
            return ResourceLoaderUtil.getStringId("hms_abort_message");
        }

        @Override // com.huawei.hms.update.ui.ConfirmDialogs.b
        protected int getNegativeButtonResId() {
            return ResourceLoaderUtil.getStringId("hms_cancel");
        }

        @Override // com.huawei.hms.update.ui.ConfirmDialogs.b
        protected int getPositiveButtonResId() {
            return ResourceLoaderUtil.getStringId("hms_abort");
        }

        @Override // com.huawei.hms.update.ui.ConfirmDialogs.b, com.huawei.hms.update.ui.AbstractDialog
        public /* bridge */ /* synthetic */ AlertDialog onCreateDialog() {
            return super.onCreateDialog();
        }
    }

    static abstract class b extends AbstractDialog {

        class a implements DialogInterface.OnClickListener {
            a() {
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                b.this.fireDoWork();
            }
        }

        /* renamed from: com.huawei.hms.update.ui.ConfirmDialogs$b$b, reason: collision with other inner class name */
        class DialogInterfaceOnClickListenerC0155b implements DialogInterface.OnClickListener {
            DialogInterfaceOnClickListenerC0155b() {
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                b.this.cancel();
            }
        }

        private b() {
        }

        protected abstract int getMessageResId();

        protected abstract int getNegativeButtonResId();

        protected abstract int getPositiveButtonResId();

        @Override // com.huawei.hms.update.ui.AbstractDialog
        public AlertDialog onCreateDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), getDialogThemeId());
            builder.setMessage(getMessageResId());
            builder.setPositiveButton(getPositiveButtonResId(), new a());
            builder.setNegativeButton(getNegativeButtonResId(), new DialogInterfaceOnClickListenerC0155b());
            return builder.create();
        }
    }
}
