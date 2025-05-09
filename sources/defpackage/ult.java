package defpackage;

import com.huawei.hms.network.embedded.r3;
import kotlin.Metadata;
import kotlinx.coroutines.Incomplete;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\n\u001a\u00020\u000bH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0005R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/Empty;", "Lkotlinx/coroutines/Incomplete;", r3.B, "", "(Z)V", "()Z", "list", "Lkotlinx/coroutines/NodeList;", "getList", "()Lkotlinx/coroutines/NodeList;", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
final class ult implements Incomplete {
    private final boolean d;

    @Override // kotlinx.coroutines.Incomplete
    public umj getList() {
        return null;
    }

    public ult(boolean z) {
        this.d = z;
    }

    @Override // kotlinx.coroutines.Incomplete
    /* renamed from: isActive, reason: from getter */
    public boolean getD() {
        return this.d;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Empty{");
        sb.append(getD() ? "Active" : "New");
        sb.append('}');
        return sb.toString();
    }
}
