package defpackage;

import com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand.AbstractCommand;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class cyi extends AbstractCommand {
    byte[] b;
    byte[] c;
    private byte[] d;

    @Override // com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand.AbstractCommand
    public byte getCheck() {
        return (byte) 0;
    }

    public cyi(e eVar) {
        this.c = new byte[0];
        this.b = new byte[0];
        this.mHead = eVar.d;
        this.mOrder = eVar.f2287a;
        this.mCommand = eVar.c;
        this.mCode = eVar.b;
        this.c = eVar.e;
        this.b = eVar.h;
        this.d = eVar.f;
    }

    public static class e extends AbstractCommand.a {
        byte[] e;
        private byte[] f;
        byte[] h;

        public e() {
            super((byte) -64);
            this.e = new byte[0];
            this.h = new byte[0];
            this.f = new byte[0];
        }

        public void a(byte[] bArr) {
            this.e = bArr;
        }

        public void b(byte[] bArr) {
            this.f = bArr;
        }

        public void e(byte[] bArr) {
            this.h = bArr;
        }

        public cyi a() {
            return new cyi(this);
        }

        public cyi d(byte[] bArr) {
            int c = super.c(bArr);
            int i = 0;
            if (cyh.b(this.c, this.b)) {
                this.e = new byte[]{bArr[c + 1], bArr[c]};
                c += 2;
            }
            LogUtil.a("CommandHeadCommand", "HeadCommand, build(), mFlag = ", dis.d(this.e, ""));
            this.h = new byte[]{bArr[c + 1], bArr[c + 2]};
            int i2 = c + 3;
            this.f = new byte[bArr.length - i2];
            while (i2 < bArr.length) {
                this.f[i] = bArr[i2];
                i++;
                i2++;
            }
            return new cyi(this);
        }
    }

    @Override // com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand.AbstractCommand
    public byte[] toByteArray() {
        byte[] bArr = new byte[20];
        int byteArray = super.toByteArray(bArr);
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr2 = this.c;
            if (i2 >= bArr2.length) {
                break;
            }
            LogUtil.a("CommandHeadCommand", "mFlag[flagIndex] = ", Byte.valueOf(bArr2[i2]));
            byteArray++;
            bArr[byteArray] = this.c[i2];
            i2++;
        }
        int i3 = 0;
        while (true) {
            byte[] bArr3 = this.b;
            if (i3 >= bArr3.length) {
                break;
            }
            byteArray++;
            bArr[byteArray] = bArr3[i3];
            i3++;
        }
        while (true) {
            byte[] bArr4 = this.d;
            if (i >= bArr4.length) {
                return bArr;
            }
            byteArray++;
            bArr[byteArray] = bArr4[i];
            i++;
        }
    }

    @Override // com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand.AbstractCommand
    public byte[] getParameterLength() {
        return this.b;
    }

    @Override // com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand.AbstractCommand
    public byte[] getPara() {
        return this.d;
    }

    public byte[] e() {
        return this.c;
    }
}
