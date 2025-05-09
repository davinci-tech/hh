package defpackage;

import com.huawei.devicesdk.entity.SendMode;
import defpackage.bir;
import health.compact.a.LogUtil;
import java.nio.ByteBuffer;

/* loaded from: classes9.dex */
public class soq {
    public static int b(int i, sol solVar, String str) {
        if (i == 127) {
            int e = bli.e(str);
            LogUtil.c("CommonFile_CommandUtils", "handleRequestPackageNameTlv error code:", Integer.valueOf(e));
            return e;
        }
        switch (i) {
            case 7:
                if (solVar == null) {
                    return 0;
                }
                String d = blq.d(str);
                solVar.h(d);
                LogUtil.c("CommonFile_CommandUtils", "handleRequestPackageNameTlv source:", d);
                return 0;
            case 8:
                if (solVar == null) {
                    return 0;
                }
                String d2 = blq.d(str);
                solVar.d(d2);
                LogUtil.c("CommonFile_CommandUtils", "handleRequestPackageNameTlv destination:", d2);
                return 0;
            case 9:
                if (solVar == null) {
                    return 0;
                }
                String d3 = blq.d(str);
                solVar.c(d3);
                LogUtil.c("CommonFile_CommandUtils", "handleRequestPackageNameTlv description:", d3);
                return 0;
            case 10:
                if (solVar == null) {
                    return 0;
                }
                String d4 = blq.d(str);
                solVar.f(d4);
                LogUtil.c("CommonFile_CommandUtils", "handleRequestPackageNameTlv sourceCertificate:", d4);
                return 0;
            case 11:
                if (solVar == null) {
                    return 0;
                }
                String d5 = blq.d(str);
                solVar.b(d5);
                LogUtil.c("CommonFile_CommandUtils", "handleRequestPackageNameTlv destinationCertificate:", d5);
                return 0;
            default:
                LogUtil.a("CommonFile_CommandUtils", "handleRequestPackageNameTlv default");
                return 0;
        }
    }

    public static void d(int i, sol solVar, String str) {
        if (solVar == null) {
            return;
        }
        if (i == 1) {
            String d = blq.d(str);
            solVar.a(d);
            LogUtil.c("CommonFile_CommandUtils", "handleRequestFileMessageTlv file_name:", d);
            return;
        }
        if (i == 2) {
            int e = bli.e(str);
            solVar.m(e);
            LogUtil.c("CommonFile_CommandUtils", "handleRequestFileMessageTlv file_type:", Integer.valueOf(e));
        } else if (i == 3) {
            int e2 = bli.e(str);
            solVar.c(e2);
            LogUtil.c("CommonFile_CommandUtils", "handleRequestFileMessageTlv file_id:", Integer.valueOf(e2));
        } else {
            if (i == 4) {
                int e3 = bli.e(str);
                solVar.i(e3);
                LogUtil.c("CommonFile_CommandUtils", "handleRequestFileMessageTlv file_size:", Integer.valueOf(e3));
                return;
            }
            LogUtil.a("CommonFile_CommandUtils", "handleRequestFileMessageTlv default");
        }
    }

    public static bir e(sol solVar) {
        if (solVar == null) {
            return new bir();
        }
        StringBuilder sb = new StringBuilder(16);
        String b = blq.b(solVar.m());
        sb.append(blq.b(1) + blq.b(b.length() / 2) + b);
        sb.append(blq.b(2) + blq.b(1) + blq.b(solVar.u()));
        String b2 = blq.b(solVar.ae());
        String str = blq.b(7) + blq.b(b2.length() / 2) + b2;
        sb.append(str);
        String b3 = blq.b(solVar.ae());
        String str2 = blq.b(8) + blq.b(b3.length() / 2) + b3;
        String b4 = blq.b(solVar.ah());
        String str3 = blq.b(10) + blq.b(b4.length() / 2) + b4;
        String b5 = blq.b(solVar.ah());
        String str4 = blq.b(11) + blq.b(b5.length() / 2) + b5;
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
        sb.append(str4);
        if (solVar.f() > 0 && solVar.u() == 22) {
            LogUtil.c("CommonFile_CommandUtils", "5.44.1 dictTypeId : ", Integer.valueOf(solVar.f()));
            String d = blq.d(solVar.f());
            sb.append(blq.b(12) + blq.a(d.length() / 2) + d);
        }
        return e(blq.a(sb.toString()), 44, 1);
    }

    public static bir e(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            return new bir();
        }
        ByteBuffer allocate = ByteBuffer.allocate(bArr.length + 2);
        allocate.put((byte) i);
        allocate.put((byte) i2);
        allocate.put(bArr);
        bir birVar = new bir();
        birVar.e(allocate.array());
        birVar.b(SendMode.PROTOCOL_TYPE_5A);
        return new bir.a().b(birVar);
    }
}
