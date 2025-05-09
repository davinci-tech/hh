package org.eclipse.californium.elements.config;

import defpackage.vbh;
import java.util.Collections;
import java.util.List;

/* loaded from: classes7.dex */
public abstract class BasicListDefinition<T> extends BasicDefinition<List<T>> {
    public BasicListDefinition(String str, String str2, List<T> list) {
        super(str, str2, List.class, list);
    }

    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    public List<T> checkValue(List<T> list) throws vbh {
        if (list == null) {
            return list;
        }
        try {
            list.remove(-1);
            return list;
        } catch (IndexOutOfBoundsException unused) {
            return Collections.unmodifiableList(list);
        } catch (UnsupportedOperationException unused2) {
            return list;
        }
    }
}
