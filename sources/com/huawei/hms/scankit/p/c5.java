package com.huawei.hms.scankit.p;

import com.huawei.hms.hmsscankit.WriterException;
import com.huawei.hms.scankit.aiscan.common.BarcodeFormat;
import java.util.Map;

/* loaded from: classes4.dex */
public final class c5 implements l8 {

    static /* synthetic */ class a {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f5755a;

        static {
            int[] iArr = new int[BarcodeFormat.values().length];
            f5755a = iArr;
            try {
                iArr[BarcodeFormat.EAN_8.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5755a[BarcodeFormat.UPC_E.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f5755a[BarcodeFormat.EAN_13.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f5755a[BarcodeFormat.UPC_A.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f5755a[BarcodeFormat.QR_CODE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f5755a[BarcodeFormat.CODE_39.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f5755a[BarcodeFormat.CODE_93.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f5755a[BarcodeFormat.CODE_128.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f5755a[BarcodeFormat.ITF.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f5755a[BarcodeFormat.PDF_417.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f5755a[BarcodeFormat.CODABAR.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f5755a[BarcodeFormat.DATA_MATRIX.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f5755a[BarcodeFormat.AZTEC.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    @Override // com.huawei.hms.scankit.p.l8
    public s a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<u2, ?> map) throws WriterException {
        l8 r2Var;
        switch (a.f5755a[barcodeFormat.ordinal()]) {
            case 1:
                r2Var = new r2();
                break;
            case 2:
                r2Var = new t7();
                break;
            case 3:
                r2Var = new p2();
                break;
            case 4:
                r2Var = new m7();
                break;
            case 5:
                r2Var = new k6();
                break;
            case 6:
                r2Var = new u0();
                break;
            case 7:
                r2Var = new w0();
                break;
            case 8:
                r2Var = new s0();
                break;
            case 9:
                r2Var = new k4();
                break;
            case 10:
                r2Var = new u5();
                break;
            case 11:
                r2Var = new q0();
                break;
            case 12:
                r2Var = new j1();
                break;
            case 13:
                r2Var = new i();
                break;
            default:
                throw new IllegalArgumentException("No encoder available for format " + barcodeFormat);
        }
        return r2Var.a(str, barcodeFormat, i, i2, map);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:11|(5:(14:13|(1:15)(2:164|(1:182))|16|(8:18|(1:20)|21|(1:23)|24|(1:26)|27|(1:29)(1:30))|31|(1:33)(2:127|(1:129)(4:130|(1:132)(1:(1:135)(2:136|(1:138)(2:139|(1:141)(2:142|(1:144)(2:145|(1:147)(2:148|(1:150)(2:151|(1:153)(2:154|(1:156)(2:157|(1:159)(2:160|(1:162)(1:163)))))))))))|133|(6:43|44|45|(2:85|(1:87)(6:88|(4:90|(3:92|(2:94|95)(2:97|98)|96)|99|100)|101|102|(3:108|(1:110)(1:112)|111)|113))(4:50|51|52|(1:54)(4:55|(4:57|(3:59|(2:70|(2:72|73)(2:74|75))(2:67|68)|69)|76|77)|78|79))|(1:81)(1:83)|82)(2:40|41)))|34|(1:36)|43|44|45|(0)|85|(0)(0))|45|(0)|85|(0)(0))|183|16|(0)|31|(0)(0)|34|(0)|43|44) */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x02a4, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x02a5, code lost:
    
        r17 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x028e, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x028f, code lost:
    
        r17 = r5;
     */
    /* JADX WARN: Removed duplicated region for block: B:127:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x012c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x020b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x020c A[Catch: Exception -> 0x028a, IllegalArgumentException -> 0x028c, OutOfMemoryError -> 0x02ba, TryCatch #3 {OutOfMemoryError -> 0x02ba, blocks: (B:44:0x0128, B:48:0x012e, B:50:0x0132, B:52:0x0150, B:55:0x0157, B:59:0x01aa, B:61:0x01ae, B:63:0x01b2, B:65:0x01b6, B:67:0x01ba, B:69:0x01db, B:70:0x01ca, B:72:0x01d0, B:74:0x01d6, B:77:0x01de, B:79:0x01e1, B:85:0x01f8, B:88:0x020c, B:92:0x021e, B:94:0x0224, B:96:0x022f, B:97:0x022a, B:100:0x0232, B:102:0x0235, B:110:0x0267, B:112:0x026e, B:113:0x0276), top: B:43:0x0128 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.graphics.Bitmap a(java.lang.String r27, int r28, int r29, int r30, com.huawei.hms.ml.scan.HmsBuildBitmapOption r31) throws com.huawei.hms.hmsscankit.WriterException {
        /*
            Method dump skipped, instructions count: 753
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.c5.a(java.lang.String, int, int, int, com.huawei.hms.ml.scan.HmsBuildBitmapOption):android.graphics.Bitmap");
    }
}
