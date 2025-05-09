package defpackage;

import com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand.AbstractCommand;

/* loaded from: classes3.dex */
public class cyj extends AbstractCommand {
    private final byte[] d;

    @Override // com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand.AbstractCommand
    public byte getCheck() {
        return (byte) 0;
    }

    public cyj(a aVar) {
        this.mHead = aVar.d;
        this.mOrder = aVar.f2287a;
        this.mCommand = aVar.c;
        this.mCode = aVar.b;
        this.d = aVar.e;
    }

    public static class a extends AbstractCommand.a {
        private byte[] e;

        public a() {
            super((byte) -64);
            this.e = new byte[0];
        }

        public void e(byte[] bArr) {
            this.e = bArr;
        }

        public cyj d() {
            return new cyj(this);
        }

        public cyj a(byte[] bArr) {
            int c = super.c(bArr) + 1;
            this.e = new byte[bArr.length - c];
            int i = 0;
            while (c < bArr.length) {
                this.e[i] = bArr[c];
                i++;
                c++;
            }
            return new cyj(this);
        }
    }

    @Override // com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand.AbstractCommand
    public byte[] toByteArray() {
        byte[] bArr = new byte[20];
        int byteArray = super.toByteArray(bArr);
        int i = 0;
        while (true) {
            byte[] bArr2 = this.d;
            if (i >= bArr2.length) {
                return bArr;
            }
            byteArray++;
            bArr[byteArray] = bArr2[i];
            i++;
        }
    }

    @Override // com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand.AbstractCommand
    public byte[] getPara() {
        return this.d;
    }

    @Override // com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand.AbstractCommand
    public byte[] getParameterLength() {
        return new byte[0];
    }
}
