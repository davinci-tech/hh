package defpackage;

import java.lang.Enum;
import java.util.Arrays;
import java.util.List;
import org.eclipse.californium.elements.config.BasicDefinition;

/* loaded from: classes7.dex */
public class vaw<E extends Enum<?>> extends BasicDefinition<E> {

    /* renamed from: a, reason: collision with root package name */
    private final String f17639a;
    private final List<E> c;
    private final E e;

    public vaw(String str, String str2, E... eArr) {
        super(str, str2, vax.c(eArr), null);
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
        E e2 = eArr[0];
        int i = 1;
        while (true) {
            if (i < eArr.length) {
                if (eArr[i].equals(e2)) {
                    this.e = e2;
                    this.c = Arrays.asList(Arrays.copyOfRange(eArr, 1, eArr.length));
                    break;
                }
                i++;
            } else {
                this.e = null;
                this.c = Arrays.asList(Arrays.copyOf(eArr, eArr.length));
                break;
            }
        }
        this.f17639a = vax.d(Arrays.asList(eArr), true);
    }

    public vaw(String str, String str2, E e, E[] eArr) {
        super(str, str2, vax.c(eArr), null);
        if (eArr == null) {
            throw new NullPointerException("Enum set must not be null!");
        }
        if (eArr.length == 0) {
            throw new IllegalArgumentException("Enum set must not be empty!");
        }
        for (E e2 : eArr) {
            if (e2 == null) {
                throw new IllegalArgumentException("Enum set must not contain null!");
            }
        }
        this.e = e;
        List<E> asList = Arrays.asList(Arrays.copyOf(eArr, eArr.length));
        this.c = asList;
        this.f17639a = vax.d(asList, true);
        if (e != null) {
            isAssignableFrom(e);
        }
    }

    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public String writeValue(E e) {
        return e.name();
    }

    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    public String getDocumentation() {
        return super.getDocumentation() + "\n" + this.f17639a + ".";
    }

    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public E getDefaultValue() {
        return this.e;
    }

    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    public boolean isAssignableFrom(Object obj) {
        if (this.c.contains(obj)) {
            return true;
        }
        if (!super.isAssignableFrom(obj)) {
            return false;
        }
        throw new IllegalArgumentException(obj + " is not in " + this.f17639a);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public E parseValue(String str) throws vbh {
        E e = (E) vax.a(str, this.c);
        if (e != null) {
            return e;
        }
        throw new vbh(str + " is not in " + this.f17639a);
    }
}
