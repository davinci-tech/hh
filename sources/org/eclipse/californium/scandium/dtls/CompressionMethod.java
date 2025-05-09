package org.eclipse.californium.scandium.dtls;

import defpackage.vbn;
import defpackage.vbo;
import defpackage.vha;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public enum CompressionMethod {
    NULL(0),
    DEFLATE(1);

    public static final int COMPRESSION_METHOD_BITS = 8;
    private static final Logger LOGGER = vha.a((Class<?>) CompressionMethod.class);
    private final int code;

    CompressionMethod(int i) {
        this.code = i;
    }

    public int getCode() {
        return this.code;
    }

    public static CompressionMethod getMethodByCode(int i) {
        if (i == 0) {
            return NULL;
        }
        if (i == 1) {
            return DEFLATE;
        }
        LOGGER.debug("Unknown compression method code: {}", Integer.valueOf(i));
        return null;
    }

    public static void listToWriter(vbo vboVar, List<CompressionMethod> list) {
        Iterator<CompressionMethod> it = list.iterator();
        while (it.hasNext()) {
            vboVar.b(it.next().getCode(), 8);
        }
    }

    public static List<CompressionMethod> listFromReader(vbn vbnVar) {
        ArrayList arrayList = new ArrayList();
        while (vbnVar.e()) {
            CompressionMethod methodByCode = getMethodByCode(vbnVar.c(8));
            if (methodByCode != null) {
                arrayList.add(methodByCode);
            }
        }
        return arrayList;
    }
}
