package defpackage;

import org.eclipse.californium.elements.config.BasicDefinition;

/* loaded from: classes7.dex */
public class vau extends BasicDefinition<Float> {
    private final Float e;

    public vau(String str, String str2, Float f) {
        super(str, str2, Float.class, f);
        this.e = null;
    }

    public vau(String str, String str2, Float f, Float f2) {
        super(str, str2, Float.class, f);
        this.e = f2;
    }

    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public String writeValue(Float f) {
        return f.toString();
    }

    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public Float checkValue(Float f) throws vbh {
        if (this.e == null || f == null || f.floatValue() >= this.e.floatValue()) {
            return f;
        }
        throw new vbh("Value " + f + " must be not less than " + this.e + "!");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Float parseValue(String str) {
        return Float.valueOf(Float.parseFloat(str));
    }

    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    public String getTypeName() {
        return "Float";
    }
}
