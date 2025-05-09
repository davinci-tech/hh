package defpackage;

import com.huawei.hihealth.HiDataFilter;
import java.lang.Enum;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.eclipse.californium.elements.config.BasicListDefinition;

/* loaded from: classes7.dex */
public class vav<E extends Enum<?>> extends BasicListDefinition<E> {

    /* renamed from: a, reason: collision with root package name */
    private final String f17638a;
    private final List<E> b;
    private final List<E> c;
    private final int d;
    private final Class<E> e;
    private final String f;

    public vav(String str, String str2, E[] eArr) {
        this(str, str2, null, 0, eArr);
    }

    public vav(String str, String str2, List<E> list, int i, E[] eArr) {
        super(str, str2, null);
        if (eArr == null) {
            throw new NullPointerException("Enum set must not be null!");
        }
        if (eArr.length == 0) {
            throw new IllegalArgumentException("Enum set must not be empty!");
        }
        for (E e : eArr) {
            if (e == null) {
                throw new IllegalArgumentException("Enum set must not contain null!");
            }
        }
        Class<E> cls = (Class<E>) eArr[0].getClass();
        this.e = cls;
        this.f17638a = "List<" + cls.getSimpleName() + HiDataFilter.DataFilterExpression.BIGGER_THAN;
        List<E> asList = Arrays.asList(Arrays.copyOf(eArr, eArr.length));
        this.b = asList;
        this.f = vax.d(asList, true);
        this.d = i;
        try {
            this.c = checkValue((List) list);
        } catch (vbh e2) {
            throw new IllegalArgumentException(e2.getMessage());
        }
    }

    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    public String getTypeName() {
        return this.f17638a;
    }

    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public String writeValue(List<E> list) {
        return vax.d(list, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.eclipse.californium.elements.config.BasicListDefinition, org.eclipse.californium.elements.config.DocumentedDefinition
    public List<E> checkValue(List<E> list) throws vbh {
        if (list != 0) {
            if (list.size() < this.d) {
                if (list.isEmpty()) {
                    throw new vbh("Values must not be empty!");
                }
                throw new vbh("Values with " + list.size() + " items must not contain less items than " + this.d + "!");
            }
            for (E e : list) {
                if (!this.b.contains(e)) {
                    if (this.e.isInstance(e)) {
                        throw new IllegalArgumentException(e + " is not in " + this.f);
                    }
                    throw new IllegalArgumentException(e + " is no " + this.e.getSimpleName());
                }
            }
        }
        return super.checkValue((List) list);
    }

    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public List<E> getDefaultValue() {
        return this.c;
    }

    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    public String getDocumentation() {
        return super.getDocumentation() + "\nList of " + this.f + ".";
    }

    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    public boolean isAssignableFrom(Object obj) {
        if (!(obj instanceof List)) {
            return false;
        }
        for (Object obj2 : (List) obj) {
            if (!this.e.isInstance(obj2)) {
                throw new IllegalArgumentException(obj2 + " is no " + this.e.getSimpleName());
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public List<E> parseValue(String str) throws vbh {
        String[] split = str.split(",");
        ArrayList arrayList = new ArrayList(split.length);
        for (String str2 : split) {
            String trim = str2.trim();
            Enum a2 = vax.a(trim, this.b);
            if (a2 == null) {
                throw new vbh(trim + " is not in " + this.f);
            }
            arrayList.add(a2);
        }
        return arrayList;
    }
}
