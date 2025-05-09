package com.huawei.ui.main.stories.health.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.InvoiceApplicationActivity;
import com.huawei.ui.main.stories.health.fragment.InvoiceSuccessfulFragment;
import defpackage.nrh;
import defpackage.nsy;
import defpackage.qrg;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes8.dex */
public class InvoiceSuccessfulFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private String f10174a;
    private Context b;
    private String c;
    private String d;
    private int e;
    private String f;
    private PermissionsResultAction h = null;
    private View j;

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        LogUtil.a("InvoiceSuccessfulFragment", "onCreate");
        super.onCreate(bundle);
        if (getActivity() instanceof InvoiceApplicationActivity) {
            this.b = getActivity();
        }
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.f10174a = arguments.getString("orderId");
            this.c = arguments.getString("orderCode");
            this.e = arguments.getInt("invoiceHeader");
            this.d = arguments.getString("invoiceTime");
            this.f = arguments.getString(HwPayConstant.KEY_AMOUNT);
        }
        LogUtil.a("InvoiceSuccessfulFragment", "mOrderId: ", this.f10174a);
        LogUtil.a("InvoiceSuccessfulFragment", "mOrderCode: ", this.c);
        LogUtil.a("InvoiceSuccessfulFragment", "mInvoiceHeader: ", Integer.valueOf(this.e));
        LogUtil.a("InvoiceSuccessfulFragment", "mInvoiceTime: ", this.d);
        LogUtil.a("InvoiceSuccessfulFragment", "mPrice: ", this.f);
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a("InvoiceSuccessfulFragment", "onCreateView");
        this.j = layoutInflater.inflate(R.layout.activity_invoice_successful, viewGroup, false);
        e();
        d();
        return this.j;
    }

    private void e() {
        this.h = new CustomPermissionAction(this.b) { // from class: com.huawei.ui.main.stories.health.fragment.InvoiceSuccessfulFragment.5
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                if (InvoiceSuccessfulFragment.this.b == null) {
                    LogUtil.h("InvoiceSuccessfulFragment", "onGranted: mContext is null");
                } else {
                    LogUtil.a("InvoiceSuccessfulFragment", "onGranted: CAMERA_IMAGE, downloadInvoice");
                    InvoiceSuccessfulFragment.this.b();
                }
            }
        };
    }

    private void d() {
        LogUtil.a("InvoiceSuccessfulFragment", "initView");
        ((HealthTextView) this.j.findViewById(R.id.invoice_header_successful)).setText(getString(this.e));
        ((HealthTextView) this.j.findViewById(R.id.transaction_no_successful)).setText(this.f10174a);
        ((HealthTextView) this.j.findViewById(R.id.invoice_time_successful)).setText(this.d);
        ((HealthSubHeader) nsy.cMd_(this.j, R.id.Invoicing_information_subheader_successful)).setSubHeaderBackgroundColor(ContextCompat.getColor(BaseApplication.e(), R.color._2131296971_res_0x7f0902cb));
        ((HealthButton) this.j.findViewById(R.id.btn_download)).setOnClickListener(new View.OnClickListener() { // from class: qiu
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InvoiceSuccessfulFragment.this.dEh_(view);
            }
        });
    }

    public /* synthetic */ void dEh_(View view) {
        LogUtil.a("InvoiceSuccessfulFragment", "setPermissionClick");
        e(this.b, this.h);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: com.huawei.ui.main.stories.health.fragment.InvoiceSuccessfulFragment$3, reason: invalid class name */
    public class AnonymousClass3 extends UiCallback<String> {
        AnonymousClass3() {
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.b("InvoiceSuccessfulFragment", "download request failed, errorCode: ", Integer.valueOf(i), " errorInfo: ", str);
            nrh.c(InvoiceSuccessfulFragment.this.b, InvoiceSuccessfulFragment.this.b.getResources().getString(R$string.IDS_invoice_toast_failed));
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onSuccess(final String str) {
            ReleaseLogUtil.e("InvoiceSuccessfulFragment", "download request successful");
            if (Looper.getMainLooper() != Looper.myLooper()) {
                InvoiceSuccessfulFragment.this.b(str);
            } else {
                ThreadPoolManager.d().execute(new Runnable() { // from class: qit
                    @Override // java.lang.Runnable
                    public final void run() {
                        InvoiceSuccessfulFragment.AnonymousClass3.this.e(str);
                    }
                });
            }
        }

        public /* synthetic */ void e(String str) {
            InvoiceSuccessfulFragment.this.b(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogUtil.a("InvoiceSuccessfulFragment", "downloadInvoice");
        qrg.c(this.c, new AnonymousClass3());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        Throwable th;
        BufferedOutputStream bufferedOutputStream;
        BufferedInputStream bufferedInputStream;
        FileOutputStream fileOutputStream;
        ByteArrayInputStream byteArrayInputStream;
        BufferedInputStream bufferedInputStream2;
        BufferedOutputStream bufferedOutputStream2;
        LogUtil.a("InvoiceSuccessfulFragment", "downloadFile");
        ByteArrayInputStream byteArrayInputStream2 = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(Base64.decode(str, 0));
            try {
                bufferedInputStream2 = new BufferedInputStream(byteArrayInputStream);
            } catch (IOException unused) {
                bufferedInputStream2 = null;
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream2 = null;
            }
        } catch (IOException unused2) {
            bufferedOutputStream = null;
            bufferedInputStream = null;
            fileOutputStream = null;
        } catch (Throwable th3) {
            th = th3;
            bufferedOutputStream = null;
            bufferedInputStream = null;
            fileOutputStream = null;
        }
        try {
            String a2 = a();
            String c = c();
            ReleaseLogUtil.e("InvoiceSuccessfulFragment", "pathName = ", a2);
            File file = new File(a2);
            File file2 = new File(a2, c);
            ReleaseLogUtil.e("InvoiceSuccessfulFragment", "path.exists() = ", Boolean.valueOf(file.exists()), " file.exists() = ", Boolean.valueOf(file2.exists()));
            b(file);
            fileOutputStream = new FileOutputStream(file2);
            try {
                bufferedOutputStream2 = new BufferedOutputStream(fileOutputStream);
                try {
                    byte[] bArr = new byte[1024];
                    for (int read = bufferedInputStream2.read(bArr); read != -1; read = bufferedInputStream2.read(bArr)) {
                        bufferedOutputStream2.write(bArr, 0, read);
                    }
                    bufferedOutputStream2.flush();
                    LogUtil.a("InvoiceSuccessfulFragment", "download Success");
                    Context context = this.b;
                    nrh.c(context, context.getResources().getString(R$string.IDS_invoice_download_path, c, "Download/HuaweiHealth"));
                    e(byteArrayInputStream, bufferedInputStream2, fileOutputStream, bufferedOutputStream2);
                } catch (IOException unused3) {
                    byteArrayInputStream2 = bufferedOutputStream2;
                    bufferedInputStream = bufferedInputStream2;
                    bufferedOutputStream = byteArrayInputStream2;
                    byteArrayInputStream2 = byteArrayInputStream;
                    try {
                        ReleaseLogUtil.c("InvoiceSuccessfulFragment", "downloadFile IOException");
                        Context context2 = this.b;
                        nrh.c(context2, context2.getResources().getString(R$string.IDS_invoice_toast_failed));
                        e(byteArrayInputStream2, bufferedInputStream, fileOutputStream, bufferedOutputStream);
                    } catch (Throwable th4) {
                        th = th4;
                        e(byteArrayInputStream2, bufferedInputStream, fileOutputStream, bufferedOutputStream);
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                    BufferedInputStream bufferedInputStream3 = bufferedInputStream2;
                    bufferedOutputStream = bufferedOutputStream2;
                    bufferedInputStream = bufferedInputStream3;
                    byteArrayInputStream2 = byteArrayInputStream;
                    e(byteArrayInputStream2, bufferedInputStream, fileOutputStream, bufferedOutputStream);
                    throw th;
                }
            } catch (IOException unused4) {
            } catch (Throwable th6) {
                th = th6;
                bufferedOutputStream2 = null;
            }
        } catch (IOException unused5) {
            fileOutputStream = null;
            bufferedInputStream = bufferedInputStream2;
            bufferedOutputStream = byteArrayInputStream2;
            byteArrayInputStream2 = byteArrayInputStream;
            ReleaseLogUtil.c("InvoiceSuccessfulFragment", "downloadFile IOException");
            Context context22 = this.b;
            nrh.c(context22, context22.getResources().getString(R$string.IDS_invoice_toast_failed));
            e(byteArrayInputStream2, bufferedInputStream, fileOutputStream, bufferedOutputStream);
        } catch (Throwable th7) {
            th = th7;
            fileOutputStream = null;
            bufferedOutputStream2 = null;
            BufferedInputStream bufferedInputStream32 = bufferedInputStream2;
            bufferedOutputStream = bufferedOutputStream2;
            bufferedInputStream = bufferedInputStream32;
            byteArrayInputStream2 = byteArrayInputStream;
            e(byteArrayInputStream2, bufferedInputStream, fileOutputStream, bufferedOutputStream);
            throw th;
        }
    }

    private String a(String str) {
        LogUtil.a("InvoiceSuccessfulFragment", "handleTime");
        String replaceAll = str.replaceAll("\\/", "").replaceAll("\\s+", Constants.LINK).replaceAll(":", Constants.LINK);
        LogUtil.a("InvoiceSuccessfulFragment", "res: ", replaceAll);
        return replaceAll;
    }

    private String a() {
        String str;
        LogUtil.a("InvoiceSuccessfulFragment", "getPath");
        try {
            str = Environment.getExternalStorageDirectory().getCanonicalPath() + File.separator + "Download/HuaweiHealth";
        } catch (IOException unused) {
            LogUtil.b("InvoiceSuccessfulFragment", "getPath IOException");
            str = null;
        }
        if (str == null || str.endsWith(File.separator)) {
            return str;
        }
        return str + File.separator;
    }

    private String c() {
        LogUtil.a("InvoiceSuccessfulFragment", "getName");
        return this.b.getResources().getString(R$string.IDS_invoice_ordinary_vat) + "_" + this.f + "_" + a(this.d) + "_" + System.currentTimeMillis() + ".pdf";
    }

    private void b(File file) {
        LogUtil.a("InvoiceSuccessfulFragment", "createDirectoryIfNotExists");
        if (file == null || file.exists() || file.mkdirs()) {
            return;
        }
        LogUtil.b("InvoiceSuccessfulFragment", "file.mkdirs() failed!");
    }

    private void e(ByteArrayInputStream byteArrayInputStream, BufferedInputStream bufferedInputStream, FileOutputStream fileOutputStream, BufferedOutputStream bufferedOutputStream) {
        LogUtil.a("InvoiceSuccessfulFragment", "closeStream");
        if (byteArrayInputStream != null) {
            try {
                byteArrayInputStream.close();
            } catch (IOException unused) {
                LogUtil.b("InvoiceSuccessfulFragment", "closeStream IOException");
                return;
            }
        }
        if (bufferedInputStream != null) {
            bufferedInputStream.close();
        }
        if (fileOutputStream != null) {
            fileOutputStream.close();
        }
        if (bufferedOutputStream != null) {
            bufferedOutputStream.close();
        }
    }

    private void e(final Context context, final PermissionsResultAction permissionsResultAction) {
        LoginInit.getInstance(context).browsingToLogin(new IBaseResponseCallback() { // from class: qiq
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                InvoiceSuccessfulFragment.e(context, permissionsResultAction, i, obj);
            }
        }, "");
    }

    public static /* synthetic */ void e(Context context, PermissionsResultAction permissionsResultAction, int i, Object obj) {
        if (i == 0) {
            PermissionUtil.b(context, PermissionUtil.PermissionType.MEDIA_VIDEO_IMAGES, permissionsResultAction);
        }
    }
}
