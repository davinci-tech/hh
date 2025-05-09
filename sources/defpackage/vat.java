package defpackage;

import org.eclipse.californium.elements.config.BasicDefinition;

/* loaded from: classes7.dex */
public class vat extends BasicDefinition<Integer> {

    /* renamed from: a, reason: collision with root package name */
    private final Integer f17637a;

    public vat(String str, String str2) {
        super(str, str2, Integer.class, null);
        this.f17637a = null;
    }

    public vat(String str, String str2, Integer num) {
        super(str, str2, Integer.class, num);
        this.f17637a = null;
    }

    public vat(String str, String str2, Integer num, Integer num2) {
        super(str, str2, Integer.class, num);
        this.f17637a = num2;
    }

    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public String writeValue(Integer num) {
        return num.toString();
    }

    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public Integer checkValue(Integer num) throws vbh {
        if (this.f17637a == null || num == null || num.intValue() >= this.f17637a.intValue()) {
            return num;
        }
        throw new vbh("Value " + num + " must be not less than " + this.f17637a + "!");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public Integer parseValue(String str) {
        return Integer.valueOf(Integer.parseInt(str));
    }

    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    public String getTypeName() {
        return "Integer";
    }
}
