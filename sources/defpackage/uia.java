package defpackage;

import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;

/* loaded from: classes10.dex */
public class uia extends uhz {
    public uia(int i, KDeclarationContainer kDeclarationContainer, String str, String str2) {
        super(i, NO_RECEIVER, ((ClassBasedDeclarationContainer) kDeclarationContainer).getJClass(), str, str2, !(kDeclarationContainer instanceof KClass) ? 1 : 0);
    }

    public uia(int i, Class cls, String str, String str2, int i2) {
        super(i, NO_RECEIVER, cls, str, str2, i2);
    }

    public uia(int i, Object obj, Class cls, String str, String str2, int i2) {
        super(i, obj, cls, str, str2, i2);
    }
}
