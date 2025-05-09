package com.huawei.openalliance.ad;

/* loaded from: classes5.dex */
public class kv extends ku<Double> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.ku
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public Double a(String str) {
        String str2;
        try {
            return Double.valueOf(Double.parseDouble(str));
        } catch (NumberFormatException unused) {
            str2 = "convertStringToData NumberFormatException";
            ho.c("DoubleDataConverter", str2);
            return Double.valueOf(0.0d);
        } catch (Exception unused2) {
            str2 = "convertStringToData Exception";
            ho.c("DoubleDataConverter", str2);
            return Double.valueOf(0.0d);
        }
    }
}
