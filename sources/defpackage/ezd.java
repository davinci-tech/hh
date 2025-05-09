package defpackage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcelable;
import com.huawei.hms.hmsscankit.ScanUtil;
import com.huawei.hms.hmsscankit.WriterException;
import com.huawei.hms.ml.scan.HmsBuildBitmapOption;
import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.hms.ml.scan.HmsScanAnalyzerOptions;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class ezd {
    public static void aue_(Activity activity, int i) {
        if (activity == null) {
            LogUtil.b("R_QrCode_QrCodeUtil", "startScan null activity");
            return;
        }
        HmsScanAnalyzerOptions create = new HmsScanAnalyzerOptions.Creator().setHmsScanTypes(HmsScan.QRCODE_SCAN_TYPE, HmsScan.CODE128_SCAN_TYPE).setErrorCheck(true).setShowGuide(true).create();
        LogUtil.a("R_QrCode_QrCodeUtil", "startScan activity ", activity.getLocalClassName(), " requestCode: ", Integer.valueOf(i));
        ScanUtil.startScan(activity, i, create);
    }

    public static void aud_(Activity activity, int i) {
        if (activity == null) {
            LogUtil.b("R_QrCode_QrCodeUtil", "startBarCodeScan null activity");
            return;
        }
        HmsScanAnalyzerOptions create = new HmsScanAnalyzerOptions.Creator().setHmsScanTypes(HmsScan.CODE39_SCAN_TYPE, HmsScan.CODE93_SCAN_TYPE, HmsScan.CODE128_SCAN_TYPE, HmsScan.EAN13_SCAN_TYPE, HmsScan.EAN8_SCAN_TYPE, HmsScan.UPCCODE_A_SCAN_TYPE, HmsScan.ITF14_SCAN_TYPE, HmsScan.UPCCODE_E_SCAN_TYPE, HmsScan.CODABAR_SCAN_TYPE).setErrorCheck(true).setShowGuide(true).create();
        LogUtil.a("R_QrCode_QrCodeUtil", "startBarCodeScan activity ", activity.getLocalClassName(), " requestCode: ", Integer.valueOf(i));
        ScanUtil.startScan(activity, i, create);
    }

    public static Bitmap aua_(String str, e eVar) throws ezl {
        if (eVar == null) {
            throw new ezl("generateOption is null");
        }
        try {
            LogUtil.a("R_QrCode_QrCodeUtil", "generateMap:(", Integer.valueOf(eVar.f12392a), ",", Integer.valueOf(eVar.c), ") ", eVar.b.toString());
            return ScanUtil.buildBitmap(str, HmsScan.QRCODE_SCAN_TYPE, eVar.f12392a, eVar.c, eVar.b);
        } catch (WriterException e2) {
            throw new ezl(e2.getMessage());
        }
    }

    public static String auc_(Intent intent) {
        if (intent == null) {
            LogUtil.h("R_QrCode_QrCodeUtil", "getUrlFromIntent null intent");
            return "";
        }
        Parcelable parcelableExtra = intent.getParcelableExtra(ScanUtil.RESULT);
        if (parcelableExtra == null) {
            LogUtil.b("R_QrCode_QrCodeUtil", "getText parcelableExtra is null");
            return "";
        }
        if (parcelableExtra instanceof HmsScan) {
            return ((HmsScan) parcelableExtra).getOriginalValue();
        }
        LogUtil.b("R_QrCode_QrCodeUtil", "getText parcelableExtra instance error");
        return "";
    }

    public static int aub_(Intent intent) {
        if (intent == null) {
            LogUtil.h("R_QrCode_QrCodeUtil", "getCodeFromIntent null intent");
            return -1;
        }
        return intent.getIntExtra(ScanUtil.RESULT_CODE, 0);
    }

    public static class e {

        /* renamed from: a, reason: collision with root package name */
        final int f12392a;
        final HmsBuildBitmapOption b;
        final int c;

        private e(int i, int i2, HmsBuildBitmapOption hmsBuildBitmapOption) {
            this.f12392a = i;
            this.c = i2;
            this.b = hmsBuildBitmapOption;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static HmsBuildBitmapOption b(int i, int i2, int i3, HmsBuildBitmapOption.ErrorCorrectionLevel errorCorrectionLevel) {
            return new HmsBuildBitmapOption.Creator().setBitmapMargin(i3).setBitmapColor(i).setBitmapBackgroundColor(i2).setQRErrorCorrection(errorCorrectionLevel).create();
        }

        public static class d {
            private HmsBuildBitmapOption.ErrorCorrectionLevel b;
            private int i = 400;
            private int c = 400;

            /* renamed from: a, reason: collision with root package name */
            private int f12393a = 0;
            private int e = -16777216;
            private int d = -1;

            public d a(int i, int i2) {
                this.i = i;
                this.c = i2;
                return this;
            }

            public e a() {
                return new e(this.i, this.c, e.b(this.e, this.d, this.f12393a, this.b));
            }
        }
    }
}
