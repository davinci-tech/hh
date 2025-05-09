package defpackage;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.CustomProgressDialog;
import com.huawei.ui.main.R$string;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes6.dex */
public class qjz {

    /* renamed from: a, reason: collision with root package name */
    private int f16447a;
    private Handler b;
    private a c;
    private List<HiTimeInterval> d;
    private Activity e;
    private int f;
    private CustomProgressDialog h;
    private CustomProgressDialog.Builder i;
    private Handler j = new e(this);
    private qkh g = qkh.c();

    public qjz(Activity activity, Handler handler) {
        this.e = activity;
        this.b = handler;
        this.c = new a(this, this.e);
    }

    public static class e extends BaseHandler<qjz> {
        e(qjz qjzVar) {
            super(qjzVar);
        }

        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dEK_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(qjz qjzVar, Message message) {
            LogUtil.a("DeleteHealthDataDialog", "handleMessageWhenReferenceNotNull()");
            if (qjzVar != null) {
                if (qjzVar.f16447a == qjzVar.f) {
                    qjzVar.c();
                    if (qjzVar.b != null) {
                        qjzVar.b.sendEmptyMessage(2);
                        return;
                    } else {
                        LogUtil.h("DeleteHealthDataDialog", "mCallBackHandler is null");
                        return;
                    }
                }
                qjzVar.b();
                return;
            }
            LogUtil.a("DeleteHealthDataDialog", "handleMessageWhenReferenceNotNull obj == null !");
        }
    }

    public void b(List<HiTimeInterval> list) {
        if (list != null && list.size() > 0) {
            int size = list.size();
            this.f = size;
            this.d = list;
            this.f16447a = 0;
            if (size > 100) {
                a();
            }
            b();
            return;
        }
        Handler handler = this.b;
        if (handler == null) {
            LogUtil.h("DeleteHealthDataDialog", "mCallBackHandler is null");
        } else {
            handler.sendEmptyMessage(2);
        }
    }

    private void a() {
        if (this.h == null) {
            this.h = new CustomProgressDialog(this.e);
            CustomProgressDialog.Builder builder = new CustomProgressDialog.Builder(this.e);
            this.i = builder;
            builder.d(this.e.getString(R$string.IDS_hw_health_show_healthdata_deleteing));
            CustomProgressDialog e2 = this.i.e();
            this.h = e2;
            e2.setCanceledOnTouchOutside(false);
            this.h.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: qjz.2
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    return i == 4;
                }
            });
        }
        if (this.e.isFinishing()) {
            return;
        }
        this.h.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        CustomProgressDialog customProgressDialog;
        int i = this.f16447a;
        int i2 = this.f;
        if (i < i2) {
            int i3 = i + 100;
            if (i3 <= i2) {
                this.g.c(this.e, this.d.subList(i, i3), this.c);
                this.f16447a += 100;
            } else {
                this.g.c(this.e, this.d.subList(i, i2), this.c);
                this.f16447a = this.f;
            }
            if (this.e.isFinishing() || (customProgressDialog = this.h) == null || !customProgressDialog.isShowing()) {
                return;
            }
            int i4 = (this.f16447a * 100) / this.f;
            this.i.d(i4);
            this.i.c(i4);
        }
    }

    static class a implements IBaseResponseCallback {
        WeakReference<Activity> c;
        WeakReference<qjz> d;

        a(qjz qjzVar, Activity activity) {
            this.d = new WeakReference<>(qjzVar);
            this.c = new WeakReference<>(activity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (i == 0) {
                LogUtil.a("DeleteHealthDataDialog", "delete successful");
            } else {
                LogUtil.a("DeleteHealthDataDialog", "delete failed");
            }
            WeakReference<Activity> weakReference = this.c;
            if (weakReference == null) {
                LogUtil.h("DeleteHealthDataDialog", "DeleteDataResponseCallback onResponse mActivityWeakReference is null");
                return;
            }
            Activity activity = weakReference.get();
            if (activity == null || activity.isDestroyed() || activity.isFinishing()) {
                LogUtil.h("DeleteHealthDataDialog", "DeleteDataResponseCallback onResponse activity is not exist");
                return;
            }
            WeakReference<qjz> weakReference2 = this.d;
            if (weakReference2 == null) {
                LogUtil.h("DeleteHealthDataDialog", "DeleteDataResponseCallback onResponse mDialogWeakReference is null");
                return;
            }
            qjz qjzVar = weakReference2.get();
            if (qjzVar != null) {
                if (qjzVar.j != null) {
                    qjzVar.j.sendEmptyMessage(1);
                    return;
                } else {
                    LogUtil.h("DeleteHealthDataDialog", "mDeleteHealthDataDialogHandler is null");
                    return;
                }
            }
            LogUtil.h("DeleteHealthDataDialog", "DeleteDataResponseCallback onResponse deleteHealthDataDialog is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        CustomProgressDialog customProgressDialog;
        if (this.e.isFinishing() || (customProgressDialog = this.h) == null) {
            return;
        }
        customProgressDialog.cancel();
        this.h = null;
    }
}
