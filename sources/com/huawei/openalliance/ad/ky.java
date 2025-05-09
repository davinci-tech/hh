package com.huawei.openalliance.ad;

/* loaded from: classes5.dex */
public class ky extends ku<Integer> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.ku
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public Integer a(String str) {
        String str2;
        try {
            return Integer.valueOf(Integer.parseInt(str));
        } catch (NumberFormatException unused) {
            str2 = "convertStringToData NumberFormatException";
            ho.c("IntegerDataConverter", str2);
            return 0;
        } catch (Exception unused2) {
            str2 = "convertStringToData Exception";
            ho.c("IntegerDataConverter", str2);
            return 0;
        }
    }
}
