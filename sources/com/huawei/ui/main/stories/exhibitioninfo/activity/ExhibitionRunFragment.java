package com.huawei.ui.main.stories.exhibitioninfo.activity;

import android.content.Context;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
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
import defpackage.sqd;
import health.compact.a.IoUtils;
import java.io.Closeable;
import java.io.File;
import java.math.BigDecimal;

/* loaded from: classes6.dex */
public class ExhibitionRunFragment extends BaseFragment {
    private ImageView b;
    private Context c;
    private ImageView d;
    private View e;

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.c = getContext();
    }

    public void c(Context context) {
        if (context == null) {
            this.c = BaseApplication.e();
            LogUtil.h("ExhibitionRunFragment", "initViewInActivity Activity is null");
        } else {
            this.c = context;
        }
        View inflate = LayoutInflater.from(context).inflate(R.layout.report_run_layout, (ViewGroup) null);
        this.e = inflate;
        dpm_(inflate);
    }

    public void b() {
        e();
    }

    private void dpm_(View view) {
        pgw.dpt_((RelativeLayout) view.findViewById(R.id.report_run), 104);
        this.d = (ImageView) view.findViewById(R.id.report_run_pic_1);
        this.b = (ImageView) view.findViewById(R.id.report_run_pic_2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0051 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0052  */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v9, types: [android.graphics.Bitmap] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void e() {
        /*
            r7 = this;
            android.content.Context r0 = r7.c
            java.lang.String r1 = "100001"
            java.lang.String r2 = "SP_REPORT_RUN"
            java.lang.String r0 = health.compact.a.SharedPreferenceManager.b(r0, r1, r2)
            android.net.Uri r0 = android.net.Uri.parse(r0)
            boolean r1 = com.huawei.hwcommonmodel.utils.PermissionUtil.c()
            if (r1 == 0) goto L20
            if (r0 != 0) goto L17
            return
        L17:
            java.lang.String r0 = r7.dpn_(r0)
            android.graphics.Bitmap r0 = defpackage.nrf.cHA_(r0)
            goto L4f
        L20:
            r1 = 0
            android.content.Context r2 = r7.c     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L34
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L34
            java.io.InputStream r0 = r2.openInputStream(r0)     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L34
            android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeStream(r0)     // Catch: java.io.IOException -> L30 java.lang.Throwable -> L65
            goto L4b
        L30:
            r2 = move-exception
            goto L37
        L32:
            r0 = move-exception
            goto L69
        L34:
            r0 = move-exception
            r2 = r0
            r0 = r1
        L37:
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L65
            java.lang.String r4 = "Failed to obtain the bitmap exception : "
            r5 = 0
            r3[r5] = r4     // Catch: java.lang.Throwable -> L65
            java.lang.String r2 = com.huawei.haf.common.exception.ExceptionUtils.d(r2)     // Catch: java.lang.Throwable -> L65
            r4 = 1
            r3[r4] = r2     // Catch: java.lang.Throwable -> L65
            java.lang.String r2 = "ExhibitionRunFragment"
            com.huawei.hwlogsmodel.LogUtil.b(r2, r3)     // Catch: java.lang.Throwable -> L65
        L4b:
            health.compact.a.IoUtils.e(r0)
            r0 = r1
        L4f:
            if (r0 != 0) goto L52
            return
        L52:
            android.widget.ImageView r1 = r7.d
            android.graphics.Bitmap r2 = r7.dpk_(r0)
            r1.setImageBitmap(r2)
            android.widget.ImageView r1 = r7.b
            android.graphics.Bitmap r0 = r7.dpl_(r0)
            r1.setImageBitmap(r0)
            return
        L65:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
        L69:
            health.compact.a.IoUtils.e(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.exhibitioninfo.activity.ExhibitionRunFragment.e():void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v3 */
    /* JADX WARN: Type inference failed for: r13v4 */
    /* JADX WARN: Type inference failed for: r13v5 */
    /* JADX WARN: Type inference failed for: r13v6, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r13v7, types: [android.database.Cursor] */
    private String dpn_(Uri uri) {
        Throwable th;
        SQLException e;
        String str = "ExhibitionRunFragment";
        String[] strArr = {"_data"};
        Closeable closeable = null;
        r11 = null;
        r11 = null;
        String str2 = null;
        try {
            try {
            } catch (Throwable th2) {
                th = th2;
                closeable = uri;
                IoUtils.e(closeable);
                throw th;
            }
        } catch (SQLException e2) {
            e = e2;
            uri = 0;
        } catch (Throwable th3) {
            th = th3;
            IoUtils.e(closeable);
            throw th;
        }
        if (!sqd.b(new File(uri.toString()))) {
            LogUtil.h("ExhibitionRunFragment", "path_crossing queryImages file is invalid,contentUri ", uri);
            IoUtils.e((Closeable) null);
            return null;
        }
        uri = this.c.getContentResolver().query(uri, strArr, null, null, null);
        try {
            if (uri != 0) {
                uri.moveToFirst();
                str = uri.getString(uri.getColumnIndex(strArr[0]));
                str2 = str;
            } else {
                LogUtil.h("ExhibitionRunFragment", "queryImages cursor = null");
            }
        } catch (SQLException e3) {
            e = e3;
            LogUtil.h(str, "queryImagesSQLException! exception : ", ExceptionUtils.d(e));
            IoUtils.e((Closeable) uri);
            return str2;
        }
        IoUtils.e((Closeable) uri);
        return str2;
    }

    private Bitmap dpk_(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        double doubleValue = new BigDecimal(width).divide(new BigDecimal(400), 2, 4).doubleValue();
        int intValue = Long.valueOf(Math.round(new BigDecimal(Double.toString(848.5d)).multiply(new BigDecimal(doubleValue)).doubleValue())).intValue();
        int intValue2 = Long.valueOf(Math.round(new BigDecimal(Double.toString(88.5d)).multiply(new BigDecimal(doubleValue)).doubleValue())).intValue();
        if ((height - intValue) - intValue2 > 0) {
            return Bitmap.createBitmap(bitmap, 0, intValue2, width, intValue);
        }
        return Bitmap.createBitmap(bitmap, 0, intValue2, width, (height - intValue2) - Long.valueOf(Math.round(new BigDecimal(Double.toString(57.0d)).multiply(new BigDecimal(doubleValue)).doubleValue())).intValue());
    }

    private Bitmap dpl_(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        double doubleValue = new BigDecimal(width).divide(new BigDecimal(400), 2, 4).doubleValue();
        int intValue = Long.valueOf(Math.round(new BigDecimal(Double.toString(930.5d)).multiply(new BigDecimal(doubleValue)).doubleValue())).intValue();
        int intValue2 = (height - intValue) - Long.valueOf(Math.round(new BigDecimal(Double.toString(57.0d)).multiply(new BigDecimal(doubleValue)).doubleValue())).intValue();
        if (intValue2 > 0) {
            return Bitmap.createBitmap(bitmap, 0, intValue, width, intValue2);
        }
        return null;
    }

    public Bitmap dpo_() {
        return pgw.dps_(this.e);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        LogUtil.a("ExhibitionRunFragment", "onDestroy");
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }
}
