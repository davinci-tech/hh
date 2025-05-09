package defpackage;

import java.util.Arrays;
import java.util.List;
import org.eclipse.californium.elements.config.BasicDefinition;

/* loaded from: classes7.dex */
public class vba extends BasicDefinition<String> {

    /* renamed from: a, reason: collision with root package name */
    private final String f17642a;
    private final String b;
    private final List<String> d;

    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public String writeValue(String str) {
        return str;
    }

    public vba(String str, String str2, String... strArr) {
        super(str, str2, String.class, null);
        if (strArr == null) {
            throw new NullPointerException("Value set must not be null!");
        }
        if (strArr.length == 0) {
            throw new IllegalArgumentException("Value set must not be empty!");
        }
        for (String str3 : strArr) {
            if (str3 == null) {
                throw new IllegalArgumentException("Value set must not contain null!");
            }
        }
        String str4 = strArr[0];
        int i = 1;
        while (true) {
            if (i < strArr.length) {
                if (strArr[i].equals(str4)) {
                    this.b = str4;
                    this.d = Arrays.asList(Arrays.copyOfRange(strArr, 1, strArr.length));
                    break;
                }
                i++;
            } else {
                this.b = null;
                this.d = Arrays.asList(Arrays.copyOf(strArr, strArr.length));
                break;
            }
        }
        this.f17642a = vax.a(this.d, true);
    }

    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    public String getDocumentation() {
        return super.getDocumentation() + "\n" + this.f17642a + ".";
    }

    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public String getDefaultValue() {
        return this.b;
    }

    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public String checkValue(String str) throws vbh {
        if (str == null || this.d.contains(str)) {
            return str;
        }
        throw new IllegalArgumentException(str + " is not in " + this.f17642a);
    }

    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    public boolean isAssignableFrom(Object obj) {
        if (this.d.contains(obj)) {
            return true;
        }
        if (!super.isAssignableFrom(obj)) {
            return false;
        }
        throw new IllegalArgumentException(obj + " is not in " + this.f17642a);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public String parseValue(String str) throws vbh {
        if (this.d.contains(str)) {
            return str;
        }
        throw new vbh(str + " is not in " + this.f17642a);
    }

    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    public String getTypeName() {
        return "StringSet";
    }
}
