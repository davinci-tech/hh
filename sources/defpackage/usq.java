package defpackage;

import net.lingala.zip4j.headers.HeaderSignature;
import net.lingala.zip4j.model.AbstractFileHeader;

/* loaded from: classes7.dex */
public class usq extends AbstractFileHeader {
    private boolean b;

    public usq() {
        setSignature(HeaderSignature.LOCAL_FILE_HEADER);
    }

    public boolean e() {
        return this.b;
    }

    public void a(boolean z) {
        this.b = z;
    }
}
