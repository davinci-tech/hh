package defpackage;

import com.huawei.wisesecurity.kfs.validation.constrains.KfsIn;
import com.huawei.wisesecurity.kfs.validation.constrains.KfsIntegerRange;
import com.huawei.wisesecurity.kfs.validation.constrains.KfsLongRange;
import com.huawei.wisesecurity.kfs.validation.constrains.KfsMax;
import com.huawei.wisesecurity.kfs.validation.constrains.KfsMin;
import com.huawei.wisesecurity.kfs.validation.constrains.KfsNotBlank;
import com.huawei.wisesecurity.kfs.validation.constrains.KfsNotEmpty;
import com.huawei.wisesecurity.kfs.validation.constrains.KfsNotNull;
import com.huawei.wisesecurity.kfs.validation.constrains.KfsSize;
import com.huawei.wisesecurity.kfs.validation.constrains.KfsStringNotEmpty;
import com.huawei.wisesecurity.kfs.validation.constrains.KfsStringRange;
import com.huawei.wisesecurity.kfs.validation.constrains.KfsStringRegex;
import com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator;
import com.huawei.wisesecurity.kfs.validation.core.ValidatorDescriptor;
import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes7.dex */
public class tvs {
    private static final Map<Class<? extends Annotation>, ValidatorDescriptor<?>> d;

    static {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        d = concurrentHashMap;
        concurrentHashMap.put(KfsNotNull.class, new tvt(tuj.class));
        concurrentHashMap.put(KfsNotEmpty.class, new tvt(tul.class, tup.class, tuq.class, tur.class, tuu.class, tus.class, tut.class, tuy.class, tuz.class, tuw.class, tuv.class, tux.class));
        concurrentHashMap.put(KfsIn.class, new tvt(tuo.class, tug.class, tun.class, tum.class));
        concurrentHashMap.put(KfsSize.class, new tvt(tvd.class, tvi.class, tvj.class, tvf.class, tvh.class, tvg.class, tvk.class, tvo.class, tvn.class, tvm.class, tvq.class, tvl.class));
        concurrentHashMap.put(KfsNotBlank.class, new tvt(tuf.class));
        concurrentHashMap.put(KfsMin.class, new tvt(tvb.class, tva.class));
        concurrentHashMap.put(KfsMax.class, new tvt(tvc.class, tve.class));
        concurrentHashMap.put(KfsIntegerRange.class, new tvt(tud.class));
        concurrentHashMap.put(KfsLongRange.class, new tvt(tuc.class));
        concurrentHashMap.put(KfsStringRange.class, new tvt(tuk.class));
        concurrentHashMap.put(KfsStringNotEmpty.class, new tvt(tui.class));
        concurrentHashMap.put(KfsStringRegex.class, new tvt(tuh.class));
    }

    public static <A extends Annotation> Class<? extends KfsConstraintValidator<A, ?>> d(Class<A> cls, Class<?> cls2) throws ttr {
        Class<? extends KfsConstraintValidator<A, ?>> cls3 = (Class<? extends KfsConstraintValidator<A, ?>>) d.get(cls).getValidator(cls2);
        if (cls3 != null) {
            return cls3;
        }
        throw new ttr("unsupported target class:" + cls2.getSimpleName() + " for constraint:" + cls.getSimpleName());
    }
}
