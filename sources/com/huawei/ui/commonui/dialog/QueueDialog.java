package com.huawei.ui.commonui.dialog;

import android.content.Context;
import android.content.DialogInterface;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.dialog.QueueDialog;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.util.LinkedList;
import java.util.Queue;

/* loaded from: classes6.dex */
public class QueueDialog extends BaseDialog {
    private static final String TAG = "QueueDialog";
    private static final Queue<QueueDialog> sDialogQueue = new LinkedList();
    private static QueueDialog sCurrentDialog = null;

    protected boolean isCheckTypeInQueue() {
        return false;
    }

    protected boolean isDialogAbandoned() {
        return false;
    }

    protected boolean isReadyToShow() {
        return true;
    }

    public QueueDialog(Context context) {
        super(context);
    }

    public QueueDialog(Context context, int i) {
        super(context, i);
    }

    public QueueDialog(Context context, int i, int i2) {
        super(context, i, i2);
    }

    protected QueueDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }

    private boolean isPendingShow(QueueDialog queueDialog) {
        Queue<QueueDialog> queue = sDialogQueue;
        return (queue.isEmpty() || queue.peek() == queueDialog) ? false : true;
    }

    private boolean isInQueue(QueueDialog queueDialog) {
        return sDialogQueue.contains(queueDialog);
    }

    private boolean checkTypeInQueue(QueueDialog queueDialog) {
        Class<?> cls = queueDialog.getClass();
        for (QueueDialog queueDialog2 : sDialogQueue) {
            if (queueDialog2 != null && cls == queueDialog2.getClass()) {
                return true;
            }
        }
        return false;
    }

    public void enqueueShowing(QueueDialog queueDialog) {
        if (queueDialog != null) {
            sDialogQueue.add(queueDialog);
        }
    }

    public static void dequeShowing() {
        Queue<QueueDialog> queue = sDialogQueue;
        if (queue.isEmpty()) {
            LogUtil.e(TAG, "dequeShowing failed, cause mDialogShowQueue is empty!");
            return;
        }
        QueueDialog peek = queue.peek();
        if (peek != null) {
            peek.show();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseDialog, android.app.Dialog
    public void show() {
        pollDialogToShow();
    }

    private void pollDialogToShow() {
        QueueDialog queueDialog = sCurrentDialog;
        if (queueDialog != null) {
            LogUtil.c(TAG, "has dialog showing,wait. sCurrentDialog=", queueDialog.toString());
            return;
        }
        Queue<QueueDialog> queue = sDialogQueue;
        if (queue.isEmpty()) {
            LogUtil.c(TAG, "sCurrentDialog is empty");
            return;
        }
        QueueDialog peek = queue.peek();
        if (peek != null && peek.isDialogAbandoned()) {
            LogUtil.d(TAG, "dialog is abandoned, ", queue.poll());
            pollDialogToShow();
            return;
        }
        if (peek != null && !peek.isReadyToShow()) {
            LogUtil.c(TAG, "dialog is not ready to show, ", peek);
            return;
        }
        QueueDialog poll = queue.poll();
        sCurrentDialog = poll;
        if (poll == null) {
            ReleaseLogUtil.a(TAG, "pollDialogToShow sCurrentDialog is null");
        } else {
            poll.superShow();
            sCurrentDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: nll
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    QueueDialog.this.m782x5aecdd07(dialogInterface);
                }
            });
        }
    }

    /* renamed from: lambda$pollDialogToShow$0$com-huawei-ui-commonui-dialog-QueueDialog, reason: not valid java name */
    public /* synthetic */ void m782x5aecdd07(DialogInterface dialogInterface) {
        sCurrentDialog = null;
        pollDialogToShow();
    }

    private void superShow() {
        super.show();
    }

    @Override // com.huawei.ui.commonui.base.BaseDialog, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
    }
}
