package defpackage;

import com.huawei.animationkit.computationalwallpaper.pattern.UpdateListener;
import java.util.function.Consumer;

/* loaded from: classes8.dex */
public final /* synthetic */ class adl implements Consumer {
    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((UpdateListener) obj).onUpdate();
    }
}
