package defpackage;

import com.huawei.ads.fund.anno.ValueConstraint;
import com.huawei.ads.fund.c;
import com.huawei.openplatform.abl.log.HiAdLog;
import java.lang.reflect.Field;

/* loaded from: classes2.dex */
public class wl implements c<ValueConstraint> {
    @Override // com.huawei.ads.fund.c
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void a(Object obj, Field field, ValueConstraint valueConstraint) {
        if (valueConstraint.value()) {
            Class constraintClass = valueConstraint.constraintClass();
            if (constraintClass != Void.class) {
                d(obj, field, valueConstraint, constraintClass);
            } else {
                c(obj, field, valueConstraint);
            }
        }
    }

    private void c(Object obj, Field field, ValueConstraint valueConstraint) {
        int[] constraintRange = valueConstraint.constraintRange();
        if (constraintRange.length != 2) {
            HiAdLog.w("ValueConstraintProcessor", "%s - field %s the length of range constraint must be 2", obj.getClass().getSimpleName(), field.getName());
            return;
        }
        int i = constraintRange[0];
        int i2 = constraintRange[1];
        if (i2 < i) {
            i = i2;
            i2 = i;
        }
        Object obj2 = field.get(obj);
        if (obj2 instanceof Number) {
            int intValue = ((Number) obj2).intValue();
            if (intValue < i || intValue > i2) {
                HiAdLog.w("ValueConstraintProcessor", "%s - field %s not in constraint range, set default value", obj.getClass().getSimpleName(), field.getName());
                field.set(obj, Integer.valueOf(valueConstraint.defaultValueForRange()));
            }
        }
    }

    private void d(Object obj, Field field, ValueConstraint valueConstraint, Class cls) {
        Object valueOf;
        Field[] fields = cls.getFields();
        Object obj2 = field.get(obj);
        if (fields.length <= 0) {
            HiAdLog.w("ValueConstraintProcessor", "fields of %s is empty, don't check json field value for [%s#%s]", cls, obj.getClass().getSimpleName(), field.getName());
            return;
        }
        for (Field field2 : fields) {
            Object obj3 = field2.get(null);
            if (obj3 != null && (obj3 == obj2 || obj3.equals(obj2))) {
                return;
            }
            if (obj3 == null && obj2 == null) {
                return;
            }
        }
        HiAdLog.w("ValueConstraintProcessor", "%s - field %s not in constraint values, set default value", obj.getClass().getSimpleName(), field.getName());
        Class<?> type = field.getType();
        if (String.class == type) {
            valueOf = valueConstraint.defaultStringValueForClass();
        } else if (Integer.TYPE != type && Integer.class != type) {
            return;
        } else {
            valueOf = Integer.valueOf(valueConstraint.defaultIntValueForClass());
        }
        field.set(obj, valueOf);
    }
}
