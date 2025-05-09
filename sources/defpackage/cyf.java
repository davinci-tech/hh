package defpackage;

import com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand.AbstractCommand;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cyg;
import defpackage.cyi;
import defpackage.cyj;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class cyf extends AbstractCommand {
    byte b;
    byte[] c;
    byte[] d;
    byte[] e;

    public cyf(c cVar) {
        this.mHead = cVar.d;
        this.mOrder = cVar.f2287a;
        this.mCommand = cVar.c;
        this.mCode = cVar.b;
        this.c = cVar.i;
        this.d = cVar.f;
        this.e = cVar.h;
        this.b = cVar.e;
    }

    @Override // com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand.AbstractCommand
    public byte[] toByteArray() {
        int i = 0;
        byte[] bArr = new byte[this.c.length + 5 + this.e.length + (this.mCode == 0 ? this.d.length : 0)];
        int byteArray = super.toByteArray(bArr);
        int i2 = 0;
        while (true) {
            byte[] bArr2 = this.d;
            if (i2 >= bArr2.length) {
                break;
            }
            byteArray++;
            bArr[byteArray] = bArr2[i2];
            i2++;
        }
        int i3 = 0;
        while (true) {
            byte[] bArr3 = this.c;
            if (i3 >= bArr3.length) {
                break;
            }
            byteArray++;
            bArr[byteArray] = bArr3[i3];
            i3++;
        }
        while (true) {
            byte[] bArr4 = this.e;
            if (i < bArr4.length) {
                byteArray++;
                bArr[byteArray] = bArr4[i];
                i++;
            } else {
                bArr[byteArray + 1] = this.b;
                LogUtil.a("Command", toString());
                return bArr;
            }
        }
    }

    public List<AbstractCommand> b() {
        ArrayList arrayList = new ArrayList();
        int length = this.e.length;
        byte[] bArr = this.d;
        if (length <= 13 - bArr.length) {
            arrayList.add(this);
        } else if (length <= 29 - bArr.length) {
            d(arrayList);
        } else {
            cyi.e eVar = new cyi.e();
            byte b = 0;
            eVar.e((byte) 0);
            eVar.c(this.mCommand);
            eVar.b(this.mCode);
            eVar.a(this.d);
            eVar.e(this.c);
            eVar.b(cyh.c(this.e, 0, 14 - this.d.length));
            arrayList.add(eVar.a());
            int length2 = 14 - this.d.length;
            while (length2 <= this.e.length - 16) {
                cyj.a aVar = new cyj.a();
                byte b2 = (byte) (((length2 / 16) + 1) & 255);
                aVar.e(b2);
                aVar.c(this.mCommand);
                aVar.b(this.mCode);
                int i = length2 + 16;
                aVar.e(cyh.c(this.e, length2, i));
                arrayList.add(aVar.d());
                b = b2;
                length2 = i;
            }
            cyg.a aVar2 = new cyg.a();
            aVar2.e((byte) (b + 1));
            aVar2.c(this.mCommand);
            aVar2.b(this.mCode);
            byte[] bArr2 = this.e;
            aVar2.b(cyh.c(bArr2, length2, bArr2.length));
            aVar2.a(this.b);
            arrayList.add(aVar2.b());
        }
        return arrayList;
    }

    private void d(List<AbstractCommand> list) {
        cyi.e eVar = new cyi.e();
        eVar.e((byte) 0);
        eVar.c(this.mCommand);
        eVar.b(this.mCode);
        eVar.e(this.c);
        eVar.b(cyh.c(this.e, 0, 14 - this.d.length));
        list.add(eVar.a());
        cyg.a aVar = new cyg.a();
        aVar.e((byte) 1);
        aVar.c(this.mCommand);
        aVar.b(this.mCode);
        byte[] bArr = this.e;
        aVar.b(cyh.c(bArr, 14 - this.d.length, bArr.length));
        aVar.a(this.b);
        list.add(aVar.b());
    }

    public static class c extends AbstractCommand.a {
        byte e;
        byte[] f;
        private byte[] h;
        byte[] i;

        public c() {
            super((byte) 0);
            this.f = new byte[0];
            this.i = new byte[0];
            this.h = new byte[0];
        }

        public void a(int i) {
            this.f = new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255)};
        }

        private void c(int i) {
            this.i = new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255)};
        }

        public void e(byte[] bArr) {
            this.h = bArr;
            c(bArr.length);
        }

        public cyf d() {
            c();
            return new cyf(this);
        }

        public cyf a(byte[] bArr) {
            int c = super.c(bArr);
            int i = 0;
            if (cyh.b(this.c, this.b)) {
                this.f = new byte[]{bArr[c + 1], bArr[c]};
                c += 2;
            }
            this.i = new byte[]{bArr[c + 1], bArr[c + 2]};
            int i2 = c + 3;
            this.h = new byte[(bArr.length - 1) - i2];
            while (i2 < bArr.length - 1) {
                this.h[i] = bArr[i2];
                i++;
                i2++;
            }
            this.e = bArr[bArr.length - 1];
            return new cyf(this);
        }

        private void c() {
            int i = this.c + this.b;
            int i2 = 0;
            int i3 = 0;
            while (true) {
                byte[] bArr = this.f;
                if (i3 >= bArr.length) {
                    break;
                }
                i += bArr[i3];
                i3++;
            }
            int i4 = 0;
            while (true) {
                byte[] bArr2 = this.i;
                if (i4 >= bArr2.length) {
                    break;
                }
                i += bArr2[i4];
                i4++;
            }
            while (true) {
                byte[] bArr3 = this.h;
                if (i2 < bArr3.length) {
                    i += bArr3[i2];
                    i2++;
                } else {
                    this.e = (byte) (i & 255);
                    return;
                }
            }
        }
    }

    public String toString() {
        return "Command{mHead=" + ((int) this.mHead) + ", mOrder=" + ((int) this.mOrder) + ", mCommand=" + ((int) this.mCommand) + ", mCode = " + ((int) this.mCode) + ", mFlag = " + cyw.a(this.d, 2) + ", mParameterLength = " + cyw.a(this.c, 10) + ", mPara = " + cyw.a(this.e, 16) + ", mCheck = " + ((int) this.b) + '}';
    }

    @Override // com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand.AbstractCommand
    public byte getCommand() {
        return this.mCommand;
    }

    @Override // com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand.AbstractCommand
    public byte getCode() {
        return this.mCode;
    }

    @Override // com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand.AbstractCommand
    public byte[] getParameterLength() {
        return this.c;
    }

    public byte[] a() {
        return this.d;
    }

    @Override // com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand.AbstractCommand
    public byte[] getPara() {
        return this.e;
    }

    @Override // com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand.AbstractCommand
    public byte getCheck() {
        return this.b;
    }
}
