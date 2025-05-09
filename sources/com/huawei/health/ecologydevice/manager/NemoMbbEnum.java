package com.huawei.health.ecologydevice.manager;

import com.huawei.health.ecologydevice.util.ProtocolUtils;
import defpackage.dcm;
import java.nio.charset.StandardCharsets;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'TYPE02' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes8.dex */
public abstract class NemoMbbEnum {
    private static final /* synthetic */ NemoMbbEnum[] $VALUES;
    private static final char F_F = 255;
    public static final NemoMbbEnum TYPE02;
    public static final NemoMbbEnum TYPE03;
    public static final NemoMbbEnum TYPE07;
    public static final NemoMbbEnum TYPE09;
    public static final NemoMbbEnum TYPE0A;
    public static final NemoMbbEnum TYPE0F;
    public static final NemoMbbEnum TYPE19;
    private final int type;

    public abstract void onParseDeviceInfo(dcm dcmVar, int i, byte[] bArr);

    public static NemoMbbEnum valueOf(String str) {
        return (NemoMbbEnum) Enum.valueOf(NemoMbbEnum.class, str);
    }

    public static NemoMbbEnum[] values() {
        return (NemoMbbEnum[]) $VALUES.clone();
    }

    static {
        int i = 2;
        NemoMbbEnum nemoMbbEnum = new NemoMbbEnum("TYPE02", 0, i) { // from class: com.huawei.health.ecologydevice.manager.NemoMbbEnum.1
            @Override // com.huawei.health.ecologydevice.manager.NemoMbbEnum
            public void onParseDeviceInfo(dcm dcmVar, int i2, byte[] bArr) {
                if (i2 == 1) {
                    dcmVar.b(bArr[0] & 255);
                }
            }
        };
        TYPE02 = nemoMbbEnum;
        int i2 = 3;
        NemoMbbEnum nemoMbbEnum2 = new NemoMbbEnum("TYPE03", 1, i2) { // from class: com.huawei.health.ecologydevice.manager.NemoMbbEnum.2
            @Override // com.huawei.health.ecologydevice.manager.NemoMbbEnum
            public void onParseDeviceInfo(dcm dcmVar, int i3, byte[] bArr) {
                dcmVar.j(new String(bArr, StandardCharsets.UTF_8));
            }
        };
        TYPE03 = nemoMbbEnum2;
        NemoMbbEnum nemoMbbEnum3 = new NemoMbbEnum("TYPE07", i, 7) { // from class: com.huawei.health.ecologydevice.manager.NemoMbbEnum.3
            @Override // com.huawei.health.ecologydevice.manager.NemoMbbEnum
            public void onParseDeviceInfo(dcm dcmVar, int i3, byte[] bArr) {
                dcmVar.a(new String(bArr, StandardCharsets.UTF_8));
            }
        };
        TYPE07 = nemoMbbEnum3;
        NemoMbbEnum nemoMbbEnum4 = new NemoMbbEnum("TYPE09", i2, 9) { // from class: com.huawei.health.ecologydevice.manager.NemoMbbEnum.4
            @Override // com.huawei.health.ecologydevice.manager.NemoMbbEnum
            public void onParseDeviceInfo(dcm dcmVar, int i3, byte[] bArr) {
                dcmVar.c(new String(bArr, StandardCharsets.UTF_8));
            }
        };
        TYPE09 = nemoMbbEnum4;
        NemoMbbEnum nemoMbbEnum5 = new NemoMbbEnum("TYPE0A", 4, 10) { // from class: com.huawei.health.ecologydevice.manager.NemoMbbEnum.5
            @Override // com.huawei.health.ecologydevice.manager.NemoMbbEnum
            public void onParseDeviceInfo(dcm dcmVar, int i3, byte[] bArr) {
                dcmVar.b(new String(bArr, StandardCharsets.UTF_8));
            }
        };
        TYPE0A = nemoMbbEnum5;
        NemoMbbEnum nemoMbbEnum6 = new NemoMbbEnum("TYPE0F", 5, 15) { // from class: com.huawei.health.ecologydevice.manager.NemoMbbEnum.6
            @Override // com.huawei.health.ecologydevice.manager.NemoMbbEnum
            public void onParseDeviceInfo(dcm dcmVar, int i3, byte[] bArr) {
                dcmVar.e(new String(bArr, StandardCharsets.UTF_8));
            }
        };
        TYPE0F = nemoMbbEnum6;
        NemoMbbEnum nemoMbbEnum7 = new NemoMbbEnum("TYPE19", 6, 25) { // from class: com.huawei.health.ecologydevice.manager.NemoMbbEnum.7
            @Override // com.huawei.health.ecologydevice.manager.NemoMbbEnum
            public void onParseDeviceInfo(dcm dcmVar, int i3, byte[] bArr) {
                if (i3 != 1) {
                    return;
                }
                dcmVar.d(ProtocolUtils.c(bArr[0]));
            }
        };
        TYPE19 = nemoMbbEnum7;
        $VALUES = new NemoMbbEnum[]{nemoMbbEnum, nemoMbbEnum2, nemoMbbEnum3, nemoMbbEnum4, nemoMbbEnum5, nemoMbbEnum6, nemoMbbEnum7};
    }

    private NemoMbbEnum(String str, int i, int i2) {
        this.type = i2;
    }

    public static NemoMbbEnum valueOf(int i) {
        NemoMbbEnum nemoMbbEnum = null;
        for (NemoMbbEnum nemoMbbEnum2 : values()) {
            if (nemoMbbEnum2.type == i) {
                nemoMbbEnum = nemoMbbEnum2;
            }
        }
        return nemoMbbEnum;
    }
}
