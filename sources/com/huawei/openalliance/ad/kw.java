package com.huawei.openalliance.ad;

/* loaded from: classes5.dex */
public class kw extends ku<Float> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.ku
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public Float a(String str) {
        String str2;
        try {
            return Float.valueOf(Float.parseFloat(str));
        } catch (NumberFormatException unused) {
            str2 = "convertStringToData NumberFormatException";
            ho.c("FloatDataConverter", str2);
            return Float.valueOf(0.0f);
        } catch (Exception unused2) {
            str2 = "convertStringToData Exception";
            ho.c("FloatDataConverter", str2);
            return Float.valueOf(0.0f);
        }
    }
}
