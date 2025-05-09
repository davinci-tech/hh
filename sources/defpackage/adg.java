package defpackage;

import java.util.function.Function;

/* loaded from: classes8.dex */
public final /* synthetic */ class adg implements Function {
    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        int parseInt;
        parseInt = Integer.parseInt((String) obj);
        return Integer.valueOf(parseInt);
    }
}
