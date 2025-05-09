package defpackage;

import com.careforeyou.library.bean.CsFatScale;
import com.careforeyou.library.enums.EnumProcessResult;
import com.careforeyou.library.protocal.iStraightFrame;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/* loaded from: classes2.dex */
public class nx implements iStraightFrame {

    /* renamed from: a, reason: collision with root package name */
    private CsFatScale f15526a = null;
    private ng e = null;
    private HashMap<Byte, byte[]> b = new HashMap<>();
    private HashMap<Byte, byte[]> d = new HashMap<>();
    private ArrayList<Byte> c = new ArrayList<>();

    private byte[] e(ArrayList<Byte> arrayList) {
        int size = arrayList.size();
        byte[] bArr = new byte[size];
        for (int i = 0; i < size; i++) {
            bArr[i] = arrayList.get(i).byteValue();
        }
        return bArr;
    }

    private byte[] e(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    private byte[] d(boolean z) {
        int i = 0;
        byte[] bArr = new byte[0];
        if (z) {
            while (i < this.d.size()) {
                i++;
                bArr = e(bArr, this.d.get(Byte.valueOf((byte) i)));
            }
        } else {
            while (i < this.b.size()) {
                i++;
                bArr = e(bArr, this.b.get(Byte.valueOf((byte) i)));
            }
        }
        return bArr;
    }

    @Override // com.careforeyou.library.protocal.iStraightFrame
    public EnumProcessResult process(byte[] bArr, String str) throws nq {
        byte[] d;
        byte b;
        EnumProcessResult enumProcessResult = EnumProcessResult.Wait_Scale_Data;
        if (bArr == null) {
            throw new nq("帧格式错误 -- 帧为空");
        }
        if (bArr[0] != -54) {
            throw new nq("帧格式错误 -- 不是正确的帧头");
        }
        byte b2 = bArr[1];
        byte b3 = bArr[2];
        if (b2 == 16) {
            byte b4 = bArr[4];
            byte[] a2 = oc.a(bArr, 7, 2);
            byte[] a3 = oc.a(bArr, 9, 2);
            byte[] a4 = oc.a(bArr, 11, 2);
            byte[] a5 = oc.a(bArr, 13, 2);
            byte[] a6 = oc.a(bArr, 15, 2);
            byte[] a7 = oc.a(bArr, 17, 1);
            CsFatScale csFatScale = new CsFatScale();
            this.f15526a = csFatScale;
            csFatScale.setHistoryData(false);
            byte d2 = oc.d(b4);
            this.f15526a.setLockFlag(d2);
            ny a8 = oh.a(bArr[5], bArr[6], b4, false);
            this.f15526a.setWeight(a8.f15551a * 10.0d);
            this.f15526a.setScaleWeightC(a8.e);
            this.f15526a.setScaleProperty(b4);
            if (d2 > 0) {
                this.f15526a.setAxunge(oc.a(a2));
                this.f15526a.setWater(oc.a(a3));
                this.f15526a.setMuscle(oc.a(a4));
                this.f15526a.setBmrC(oc.a(a5));
                this.f15526a.setVisceral_fat(oc.a(a6));
                this.f15526a.setBone(oc.a(a7));
            }
            return EnumProcessResult.Received_Scale_Data;
        }
        if (b2 != 17) {
            return enumProcessResult;
        }
        byte b5 = bArr[3];
        if (b5 == 0 || b5 == 1) {
            byte[] a9 = oc.a(bArr, 5, 2);
            CsFatScale csFatScale2 = new CsFatScale();
            this.f15526a = csFatScale2;
            csFatScale2.setHistoryData(false);
            this.f15526a.setLockFlag(b5);
            this.f15526a.setScaleProperty(bArr[11]);
            ny a10 = oh.a(a9[0], a9[1], this.f15526a.getScaleProperty(), false);
            this.f15526a.setWeight(a10.f15551a * 10.0d);
            this.f15526a.setScaleWeightC(a10.e);
            return EnumProcessResult.Received_Scale_Data;
        }
        switch (b5) {
            case 18:
            case 19:
                if (a(bArr)) {
                    if (b5 == 18) {
                        d = d(false);
                    } else {
                        d = d(true);
                    }
                    CsFatScale csFatScale3 = new CsFatScale();
                    this.f15526a = csFatScale3;
                    if (b5 == 18) {
                        csFatScale3.setHistoryData(false);
                        b = b5;
                    } else {
                        b = b5;
                        this.f15526a.weighingDate = new Date(oc.a(oc.a(d, 0, 4)) * 1000);
                        this.f15526a.setHistoryData(true);
                    }
                    byte b6 = d[4];
                    this.f15526a.setRoleId(oc.a(oc.a(d, 6, 4)));
                    byte[] a11 = oc.a(d, 10, 2);
                    this.f15526a.setScaleProperty(b6);
                    ny a12 = oh.a(a11[0], a11[1], b6, false);
                    this.f15526a.setWeight(a12.f15551a * 10.0d);
                    this.f15526a.setScaleWeightC(a12.e);
                    this.f15526a.setAxunge(oc.a(oc.a(d, 12, 2)));
                    this.f15526a.setWater(oc.a(oc.a(d, 14, 2)));
                    this.f15526a.setMuscle(oc.a(oc.a(d, 16, 2)));
                    this.f15526a.setBmrC(oc.a(oc.a(d, 18, 2)));
                    this.f15526a.setVisceral_fat(oc.a(oc.a(d, 20, 2)));
                    this.f15526a.setBone(oc.a(oc.a(d, 22, 1)));
                    double muscle = (this.f15526a.getMuscle() / this.f15526a.getWeight()) * 100.0d;
                    if (muscle >= 50.0d) {
                        muscle = this.f15526a.getMuscle();
                        if (muscle >= 50.0d) {
                            muscle = 50.0d;
                        }
                    }
                    this.f15526a.setMuscle((int) (muscle * 10.0d));
                    this.f15526a.setLockFlag((byte) 1);
                    if (b == 18) {
                        this.b.clear();
                        this.b = null;
                    } else {
                        this.d.clear();
                        this.d = null;
                    }
                    return EnumProcessResult.Received_Scale_Data;
                }
                return EnumProcessResult.Wait_Scale_Data;
            case 20:
                byte[] d3 = d(bArr[4]);
                if (this.c == null) {
                    this.c = new ArrayList<>();
                }
                for (int i = 0; i < b3 - 2; i++) {
                    this.c.add(Byte.valueOf(bArr[i + 5]));
                }
                if (d3[0] == d3[1]) {
                    byte[] e = e(this.c);
                    this.e = new ng();
                    byte[] a13 = oc.a(e, 0, 2);
                    ny a14 = oh.a(a13[0], a13[1], e[2], false);
                    this.e.e(a14.f15551a * 10.0d);
                    this.e.b(a14.e);
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    for (int i2 = 3; i2 < e.length; i2 += 4) {
                        arrayList.add(Integer.valueOf(oc.a(oc.a(e, i2, 4))));
                    }
                    this.e.e(arrayList);
                    this.c.clear();
                    this.c = null;
                    return EnumProcessResult.Match_User_Msg;
                }
                return EnumProcessResult.Wait_Scale_Data;
            default:
                return enumProcessResult;
        }
    }

    public ng e() {
        return this.e;
    }

    @Override // com.careforeyou.library.protocal.iStraightFrame
    public CsFatScale getFatScale() {
        return this.f15526a;
    }

    private boolean a(byte[] bArr) {
        byte b = bArr[2];
        byte b2 = bArr[3];
        int i = b - 2;
        byte[] d = d(bArr[4]);
        byte[] bArr2 = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr2[i2] = bArr[i2 + 5];
        }
        if (b2 == 18) {
            if (d[1] == 1) {
                HashMap<Byte, byte[]> hashMap = this.b;
                if (hashMap != null) {
                    hashMap.clear();
                } else {
                    this.b = new HashMap<>();
                }
            }
            if (this.b.containsKey(Byte.valueOf(d[1]))) {
                this.b.remove(Byte.valueOf(d[1]));
            }
            this.b.put(Byte.valueOf(d[1]), bArr2);
            if (d[0] != d[1]) {
                return false;
            }
            if (this.b.size() != d[0]) {
                this.b.clear();
                return false;
            }
        } else {
            if (d[1] == 1) {
                HashMap<Byte, byte[]> hashMap2 = this.d;
                if (hashMap2 != null) {
                    hashMap2.clear();
                } else {
                    this.d = new HashMap<>();
                }
            }
            if (this.d.containsKey(Byte.valueOf(d[1]))) {
                this.d.remove(Byte.valueOf(d[1]));
            }
            this.d.put(Byte.valueOf(d[1]), bArr2);
            if (d[0] != d[1]) {
                return false;
            }
            if (this.d.size() != d[0]) {
                this.d.clear();
                return false;
            }
        }
        return true;
    }

    private byte[] d(byte b) {
        return new byte[]{(byte) (((byte) (b << 4)) >> 4), (byte) (b >> 4)};
    }
}
