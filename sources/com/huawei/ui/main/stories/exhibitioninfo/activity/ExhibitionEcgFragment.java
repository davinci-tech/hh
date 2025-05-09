package com.huawei.ui.main.stories.exhibitioninfo.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.pdf.PdfRenderer;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseFragment;
import defpackage.pgw;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Locale;

/* loaded from: classes6.dex */
public class ExhibitionEcgFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private Context f9715a;
    private ImageView c;
    private View e;

    public void d(Context context) {
        if (context == null) {
            this.f9715a = BaseApplication.e();
            LogUtil.h("ExhibitionEcgFragment", "initViewInActivity Activity is null");
        } else {
            this.f9715a = context;
        }
        View inflate = LayoutInflater.from(context).inflate(R.layout.report_ecg_layout, (ViewGroup) null);
        this.e = inflate;
        doX_(inflate);
    }

    public void c() {
        d();
    }

    private void doX_(View view) {
        pgw.dpt_((RelativeLayout) view.findViewById(R.id.report_ecg), 103);
        this.c = (ImageView) view.findViewById(R.id.report_ecg_pic_1);
    }

    private void d() {
        Bitmap doW_;
        String b = b();
        if (TextUtils.isEmpty(b) || (doW_ = doW_(b)) == null) {
            return;
        }
        this.c.setImageBitmap(doW_);
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0085, code lost:
    
        if (r2 == null) goto L39;
     */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:45:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0091 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.graphics.Bitmap doW_(java.lang.String r10) {
        /*
            r9 = this;
            java.lang.String r0 = "getEcgBitmap IOException is"
            java.lang.String r1 = "ExhibitionEcgFragment"
            java.io.File r2 = new java.io.File
            r2.<init>(r10)
            r10 = 268435456(0x10000000, float:2.524355E-29)
            r3 = 0
            r4 = 0
            android.os.ParcelFileDescriptor r10 = android.os.ParcelFileDescriptor.open(r2, r10)     // Catch: java.lang.Throwable -> L5c java.io.IOException -> L5f
            android.graphics.pdf.PdfRenderer r2 = new android.graphics.pdf.PdfRenderer     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L58
            r2.<init>(r10)     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L58
            int r5 = r2.getPageCount()     // Catch: java.io.IOException -> L51 java.lang.Throwable -> L8b
            if (r5 <= 0) goto L3e
            android.graphics.pdf.PdfRenderer$Page r5 = r2.openPage(r3)     // Catch: java.io.IOException -> L51 java.lang.Throwable -> L8b
            android.graphics.Bitmap r5 = r9.doY_(r5)     // Catch: java.io.IOException -> L51 java.lang.Throwable -> L8b
            android.graphics.Bitmap r3 = r9.doV_(r5)     // Catch: java.io.IOException -> L51 java.lang.Throwable -> L8b
            if (r10 == 0) goto L3a
            r10.close()     // Catch: java.io.IOException -> L2e
            goto L3a
        L2e:
            r10 = move-exception
            java.lang.String r10 = com.huawei.haf.common.exception.ExceptionUtils.d(r10)
            java.lang.Object[] r10 = new java.lang.Object[]{r0, r10}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r10)
        L3a:
            r2.close()
            return r3
        L3e:
            if (r10 == 0) goto L87
            r10.close()     // Catch: java.io.IOException -> L44
            goto L87
        L44:
            r10 = move-exception
            java.lang.String r10 = com.huawei.haf.common.exception.ExceptionUtils.d(r10)
            java.lang.Object[] r10 = new java.lang.Object[]{r0, r10}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r10)
            goto L87
        L51:
            r5 = move-exception
            goto L62
        L53:
            r2 = move-exception
            r8 = r4
            r4 = r10
            r10 = r8
            goto L8f
        L58:
            r2 = move-exception
            r5 = r2
            r2 = r4
            goto L62
        L5c:
            r2 = move-exception
            r10 = r4
            goto L8f
        L5f:
            r5 = move-exception
            r10 = r4
            r2 = r10
        L62:
            r6 = 2
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: java.lang.Throwable -> L8b
            java.lang.String r7 = "getEcgBitmap PDFIOException is"
            r6[r3] = r7     // Catch: java.lang.Throwable -> L8b
            java.lang.String r3 = com.huawei.haf.common.exception.ExceptionUtils.d(r5)     // Catch: java.lang.Throwable -> L8b
            r5 = 1
            r6[r5] = r3     // Catch: java.lang.Throwable -> L8b
            com.huawei.hwlogsmodel.LogUtil.b(r1, r6)     // Catch: java.lang.Throwable -> L8b
            if (r10 == 0) goto L85
            r10.close()     // Catch: java.io.IOException -> L79
            goto L85
        L79:
            r10 = move-exception
            java.lang.String r10 = com.huawei.haf.common.exception.ExceptionUtils.d(r10)
            java.lang.Object[] r10 = new java.lang.Object[]{r0, r10}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r10)
        L85:
            if (r2 == 0) goto L8a
        L87:
            r2.close()
        L8a:
            return r4
        L8b:
            r3 = move-exception
            r4 = r10
            r10 = r2
            r2 = r3
        L8f:
            if (r4 == 0) goto La1
            r4.close()     // Catch: java.io.IOException -> L95
            goto La1
        L95:
            r3 = move-exception
            java.lang.String r3 = com.huawei.haf.common.exception.ExceptionUtils.d(r3)
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r3}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r0)
        La1:
            if (r10 == 0) goto La6
            r10.close()
        La6:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.exhibitioninfo.activity.ExhibitionEcgFragment.doW_(java.lang.String):android.graphics.Bitmap");
    }

    private Bitmap doV_(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        double doubleValue = new BigDecimal(width).divide(new BigDecimal(400), 2, 4).doubleValue();
        int intValue = Long.valueOf(Math.round(new BigDecimal(Double.toString(246.5d)).multiply(new BigDecimal(doubleValue)).doubleValue())).intValue();
        int intValue2 = Long.valueOf(Math.round(new BigDecimal(Double.toString(57.0d)).multiply(new BigDecimal(doubleValue)).doubleValue())).intValue();
        return doZ_(Bitmap.createBitmap(bitmap, 0, intValue2, width, (height - intValue2) - intValue));
    }

    private Bitmap doZ_(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setRotate(-90.0f);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
    }

    private Bitmap doY_(PdfRenderer.Page page) {
        Bitmap createBitmap = Bitmap.createBitmap(page.getWidth(), page.getHeight(), Bitmap.Config.ARGB_8888);
        page.render(createBitmap, null, null, 1);
        page.close();
        return createBitmap;
    }

    private String b() {
        String str = null;
        try {
            str = this.f9715a.getExternalFilesDir(null).getCanonicalPath() + "/h5pro/files/com.huawei.health.h5.ecg";
            LogUtil.a("TAG", "ecgFileDir == ", str);
        } catch (IOException e) {
            LogUtil.b("ExhibitionEcgFragment", "getPdfFilePath IOException is ", ExceptionUtils.d(e));
        }
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            LogUtil.h("ExhibitionEcgFragment", "getPdfFilePath ecgFile not exist or not Directory");
            return "";
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null && listFiles.length > 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < listFiles.length; i++) {
                if (!listFiles[i].isDirectory() && listFiles[i].getName().trim().toLowerCase(Locale.ENGLISH).endsWith(".pdf")) {
                    arrayList.add(listFiles[i]);
                }
            }
            if (arrayList.size() > 0) {
                return ((File) arrayList.get(arrayList.size() - 1)).getPath();
            }
        }
        return "";
    }

    public Bitmap dpa_() {
        return pgw.dps_(this.e);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        LogUtil.a("ExhibitionEcgFragment", "onDestroy");
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        LogUtil.a("ExhibitionEcgFragment", "onDestroyView");
        super.onDestroyView();
    }
}
