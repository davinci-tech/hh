package com.huawei.openalliance.ad;

/* loaded from: classes5.dex */
public class la extends ku<Long> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.ku
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public Long a(String str) {
        String str2;
        try {
            return Long.valueOf(Long.parseLong(str));
        } catch (NumberFormatException unused) {
            str2 = "convertStringToData NumberFormatException";
            ho.a("LongDataConverter", str2);
            return 0L;
        } catch (Exception unused2) {
            str2 = "convertStringToData Exception";
            ho.a("LongDataConverter", str2);
            return 0L;
        }
    }
}
