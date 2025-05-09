package com.huawei.hms.update.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import com.huawei.hms.utils.ResourceLoaderUtil;

/* loaded from: classes9.dex */
public final class PromptDialogs {

    public static class CheckFailurePrompt extends b {
        public CheckFailurePrompt() {
            super();
        }

        @Override // com.huawei.hms.update.ui.PromptDialogs.b
        protected int getMessageResId() {
            return ResourceLoaderUtil.getStringId("hms_check_failure");
        }

        @Override // com.huawei.hms.update.ui.PromptDialogs.b, com.huawei.hms.update.ui.AbstractDialog
        public /* bridge */ /* synthetic */ AlertDialog onCreateDialog() {
            return super.onCreateDialog();
        }
    }

    public static class DownloadFailurePrompt extends b {
        public DownloadFailurePrompt() {
            super();
        }

        @Override // com.huawei.hms.update.ui.PromptDialogs.b
        protected int getMessageResId() {
            return ResourceLoaderUtil.getStringId("hms_download_failure");
        }

        @Override // com.huawei.hms.update.ui.PromptDialogs.b, com.huawei.hms.update.ui.AbstractDialog
        public /* bridge */ /* synthetic */ AlertDialog onCreateDialog() {
            return super.onCreateDialog();
        }
    }

    public static class DownloadNoSpacePrompt extends b {
        public DownloadNoSpacePrompt() {
            super();
        }

        @Override // com.huawei.hms.update.ui.PromptDialogs.b
        protected int getMessageResId() {
            return ResourceLoaderUtil.getStringId("hms_download_no_space");
        }

        @Override // com.huawei.hms.update.ui.PromptDialogs.b, com.huawei.hms.update.ui.AbstractDialog
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

        private b() {
        }

        protected abstract int getMessageResId();

        protected int getPositiveButtonResId() {
            return ResourceLoaderUtil.getStringId("hms_confirm");
        }

        @Override // com.huawei.hms.update.ui.AbstractDialog
        public AlertDialog onCreateDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), getDialogThemeId());
            builder.setMessage(getMessageResId());
            builder.setPositiveButton(getPositiveButtonResId(), new a());
            return builder.create();
        }
    }
}
