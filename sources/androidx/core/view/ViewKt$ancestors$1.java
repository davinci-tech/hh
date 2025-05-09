package androidx.core.view;

import android.view.ViewParent;
import defpackage.uhy;
import defpackage.uia;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes8.dex */
final /* synthetic */ class ViewKt$ancestors$1 extends uia implements Function1<ViewParent, ViewParent> {
    public static final ViewKt$ancestors$1 INSTANCE = new ViewKt$ancestors$1();

    @Override // kotlin.jvm.functions.Function1
    public final ViewParent invoke(ViewParent viewParent) {
        uhy.e((Object) viewParent, "");
        return viewParent.getParent();
    }

    ViewKt$ancestors$1() {
        super(1, ViewParent.class, "getParent", "getParent()Landroid/view/ViewParent;", 0);
    }
}
