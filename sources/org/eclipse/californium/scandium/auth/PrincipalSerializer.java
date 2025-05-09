package org.eclipse.californium.scandium.auth;

import defpackage.vao;
import defpackage.vap;
import defpackage.vas;
import defpackage.vbm;
import defpackage.vbn;
import defpackage.vbo;
import defpackage.vbt;
import java.security.GeneralSecurityException;
import java.security.Principal;

/* loaded from: classes10.dex */
public final class PrincipalSerializer {
    public static void d(Principal principal, vbo vboVar) {
        if (vboVar == null) {
            throw new NullPointerException("Writer must not be null");
        }
        if (principal != null) {
            if (principal instanceof vap) {
                b((vap) principal, vboVar);
                return;
            }
            if (principal instanceof vas) {
                e((vas) principal, vboVar);
                return;
            } else if (principal instanceof vao) {
                e((vao) principal, vboVar);
                return;
            } else {
                throw new IllegalArgumentException("unsupported principal type: " + principal.getClass().getName());
            }
        }
        vboVar.d(ClientAuthenticationType.ANONYMOUS.code);
    }

    private static void b(vap vapVar, vbo vboVar) {
        vboVar.d(ClientAuthenticationType.PSK.code);
        if (vapVar.b()) {
            vboVar.d((byte) 1);
            vbt.d(vboVar, vapVar.c(), 16);
            vbt.d(vboVar, vapVar.e(), 16);
        } else {
            vboVar.d((byte) 0);
            vbt.d(vboVar, vapVar.e(), 16);
        }
    }

    private static void e(vas vasVar, vbo vboVar) {
        vboVar.d(ClientAuthenticationType.RPK.code);
        vboVar.d(vasVar.d());
    }

    private static void e(vao vaoVar, vbo vboVar) {
        vboVar.d(ClientAuthenticationType.CERT.code);
        vboVar.d(vaoVar.b());
    }

    public static Principal e(vbn vbnVar) throws GeneralSecurityException {
        if (vbnVar == null) {
            throw new NullPointerException("reader must not be null");
        }
        int i = AnonymousClass4.b[ClientAuthenticationType.fromCode(vbnVar.c()).ordinal()];
        if (i == 1) {
            return c(vbnVar);
        }
        if (i == 2) {
            return b(vbnVar);
        }
        if (i != 3) {
            return null;
        }
        return a(vbnVar);
    }

    /* renamed from: org.eclipse.californium.scandium.auth.PrincipalSerializer$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[ClientAuthenticationType.values().length];
            b = iArr;
            try {
                iArr[ClientAuthenticationType.CERT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[ClientAuthenticationType.PSK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[ClientAuthenticationType.RPK.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static vao c(vbn vbnVar) {
        return vao.c(vbm.b(vbnVar));
    }

    private static vap b(vbn vbnVar) {
        if (vbnVar.c() == 1) {
            return new vap(vbt.c(vbnVar, 16), vbt.c(vbnVar, 16));
        }
        return new vap(vbt.c(vbnVar, 16));
    }

    private static vas a(vbn vbnVar) throws GeneralSecurityException {
        return new vas(vbm.b(vbnVar));
    }

    enum ClientAuthenticationType {
        ANONYMOUS((byte) 0),
        CERT((byte) 1),
        PSK((byte) 2),
        RPK((byte) -1);

        private byte code;

        ClientAuthenticationType(byte b) {
            this.code = b;
        }

        static ClientAuthenticationType fromCode(byte b) {
            for (ClientAuthenticationType clientAuthenticationType : values()) {
                if (clientAuthenticationType.code == b) {
                    return clientAuthenticationType;
                }
            }
            throw new IllegalArgumentException("unknown ClientAuthenticationType: " + ((int) b));
        }
    }
}
