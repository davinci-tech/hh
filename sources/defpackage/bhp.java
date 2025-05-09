package defpackage;

import com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase;

/* loaded from: classes3.dex */
public class bhp extends HandshakeGeneralCommandBase {
    biw c;

    public bhp(biw biwVar) {
        this.c = biwVar;
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public String getTag() {
        return "0116";
    }
}
