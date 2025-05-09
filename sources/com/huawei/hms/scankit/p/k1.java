package com.huawei.hms.scankit.p;

import com.huawei.hms.scankit.aiscan.common.BarcodeFormat;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: classes9.dex */
public final class k1 {

    /* renamed from: a, reason: collision with root package name */
    public static final Set<BarcodeFormat> f5815a;
    public static final Set<BarcodeFormat> b;
    public static final Set<BarcodeFormat> c;
    public static final Set<BarcodeFormat> d;
    public static final Set<BarcodeFormat> e;
    public static final Set<BarcodeFormat> f;
    public static final Set<BarcodeFormat> g;
    private static final Map<String, Set<BarcodeFormat>> h;

    static {
        EnumSet of = EnumSet.of(BarcodeFormat.QR_CODE);
        d = of;
        EnumSet of2 = EnumSet.of(BarcodeFormat.AZTEC);
        e = of2;
        EnumSet of3 = EnumSet.of(BarcodeFormat.DATA_MATRIX);
        f = of3;
        EnumSet of4 = EnumSet.of(BarcodeFormat.PDF_417);
        g = of4;
        EnumSet of5 = EnumSet.of(BarcodeFormat.CODE_39, BarcodeFormat.CODE_93, BarcodeFormat.CODE_128, BarcodeFormat.ITF, BarcodeFormat.CODABAR);
        c = of5;
        EnumSet of6 = EnumSet.of(BarcodeFormat.UPC_A, BarcodeFormat.UPC_E, BarcodeFormat.EAN_13, BarcodeFormat.EAN_8);
        f5815a = of6;
        EnumSet copyOf = EnumSet.copyOf((Collection) of6);
        b = copyOf;
        copyOf.addAll(of5);
        HashMap hashMap = new HashMap();
        h = hashMap;
        hashMap.put("ONE_D_MODE", copyOf);
        hashMap.put("QR_CODE_MODE", of);
        hashMap.put("PRODUCT_MODE", of6);
        hashMap.put("AZTEC_MODE", of2);
        hashMap.put("DATA_MATRIX_MODE", of3);
        hashMap.put("PDF417_MODE", of4);
    }
}
