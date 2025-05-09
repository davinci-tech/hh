package com.huawei.openalliance.ad;

import java.lang.reflect.Field;

/* loaded from: classes5.dex */
public class cx implements cw<com.huawei.openalliance.ad.annotations.h> {
    @Override // com.huawei.openalliance.ad.cw
    public void a(Object obj, Field field, com.huawei.openalliance.ad.annotations.h hVar) {
        if (hVar.a()) {
            Class b = hVar.b();
            if (b != Void.class) {
                a(obj, field, hVar, b);
            } else {
                b(obj, field, hVar);
            }
        }
    }

    private void b(Object obj, Field field, com.huawei.openalliance.ad.annotations.h hVar) {
        int[] e = hVar.e();
        if (e.length != 2) {
            ho.c("ValueConstraintProcessor", "%s - field %s the length of range constraint must be 2", obj.getClass().getSimpleName(), field.getName());
            return;
        }
        int i = e[0];
        int i2 = e[1];
        if (i2 < i) {
            i = i2;
            i2 = i;
        }
        Object obj2 = field.get(obj);
        if (obj2 instanceof Number) {
            int intValue = ((Number) obj2).intValue();
            if (intValue < i || intValue > i2) {
                ho.c("ValueConstraintProcessor", "%s - field %s not in constraint range, set default value", obj.getClass().getSimpleName(), field.getName());
                field.set(obj, Integer.valueOf(hVar.f()));
            }
        }
    }

    private void a(Object obj, Field field, com.huawei.openalliance.ad.annotations.h hVar, Class cls) {
        Object valueOf;
        Field[] a2 = com.huawei.openalliance.ad.utils.ck.a(cls);
        Object obj2 = field.get(obj);
        for (Field field2 : a2) {
            Object obj3 = field2.get(null);
            if (obj3 != null && (obj3 == obj2 || obj3.equals(obj2))) {
                return;
            }
            if (obj3 == null && obj2 == null) {
                return;
            }
        }
        ho.c("ValueConstraintProcessor", "%s - field %s not in constraint values, set default value", obj.getClass().getSimpleName(), field.getName());
        Class<?> type = field.getType();
        if (String.class == type) {
            valueOf = hVar.d();
        } else if (Integer.TYPE != type && Integer.class != type) {
            return;
        } else {
            valueOf = Integer.valueOf(hVar.c());
        }
        field.set(obj, valueOf);
    }
}
