package defpackage;

import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class ctf extends uyq {
    private static final String b = "CustomUdpDataParser";

    @Override // org.eclipse.californium.core.network.serialization.DataParser
    public uxs createOption(int i, byte[] bArr) {
        LogUtil.a(b, "CustomUdpDataParser createOption");
        uxs uxsVar = new uxs(i);
        uxsVar.d(bArr);
        return uxsVar;
    }
}
