package defpackage;

import org.eclipse.californium.elements.config.BasicDefinition;

/* loaded from: classes7.dex */
public class vaq extends BasicDefinition<Boolean> {
    public vaq(String str, String str2) {
        super(str, str2, Boolean.class, null);
    }

    public vaq(String str, String str2, Boolean bool) {
        super(str, str2, Boolean.class, bool);
    }

    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public String writeValue(Boolean bool) {
        return bool.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public Boolean parseValue(String str) {
        return Boolean.valueOf(Boolean.parseBoolean(str));
    }

    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    public String getTypeName() {
        return "Boolean";
    }
}
