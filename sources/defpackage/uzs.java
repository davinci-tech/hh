package defpackage;

/* loaded from: classes7.dex */
public final class uzs {
    public static uzp b(uzp uzpVar) {
        if (uzpVar == null) {
            return null;
        }
        uxt c = uzpVar.c();
        if (c == null) {
            throw new IllegalArgumentException("missing request for observation!");
        }
        uxt uxtVar = new uxt(c.e());
        uxtVar.setDestinationContext(c.getDestinationContext());
        uxtVar.setType(c.getType());
        uxtVar.setMID(c.getMID());
        uxtVar.setToken(c.getToken());
        uxtVar.setOptions(c.getOptions());
        if (c.isUnintendedPayload()) {
            uxtVar.setUnintendedPayload();
        }
        uxtVar.setPayload(c.getPayload());
        uxtVar.c(c.g());
        uxtVar.setMaxResourceBodySize(c.getMaxResourceBodySize());
        return new uzp(uxtVar, uzpVar.d());
    }
}
