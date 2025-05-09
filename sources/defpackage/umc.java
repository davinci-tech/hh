package defpackage;

import com.huawei.hms.network.embedded.r3;
import kotlin.Metadata;
import kotlinx.coroutines.Incomplete;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\n\u001a\u00020\u000bH\u0016R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/InactiveNodeList;", "Lkotlinx/coroutines/Incomplete;", "list", "Lkotlinx/coroutines/NodeList;", "(Lkotlinx/coroutines/NodeList;)V", r3.B, "", "()Z", "getList", "()Lkotlinx/coroutines/NodeList;", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class umc implements Incomplete {
    private final umj c;

    @Override // kotlinx.coroutines.Incomplete
    /* renamed from: isActive */
    public boolean getD() {
        return false;
    }

    public umc(umj umjVar) {
        this.c = umjVar;
    }

    @Override // kotlinx.coroutines.Incomplete
    /* renamed from: getList, reason: from getter */
    public umj getC() {
        return this.c;
    }

    public String toString() {
        return ASSERTIONS_ENABLED.d() ? getC().a("New") : super.toString();
    }
}
