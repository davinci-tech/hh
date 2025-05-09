package defpackage;

import com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand.AbstractCommand;

/* loaded from: classes3.dex */
public class cyg extends AbstractCommand {

    /* renamed from: a, reason: collision with root package name */
    private byte[] f11541a;
    byte b;

    public cyg(a aVar) {
        this.mHead = aVar.d;
        this.mOrder = aVar.f2287a;
        this.mCommand = aVar.c;
        this.mCode = aVar.b;
        this.f11541a = aVar.j;
        this.b = aVar.e;
    }

    @Override // com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand.AbstractCommand
    public byte[] toByteArray() {
        byte[] bArr = new byte[this.f11541a.length + 5];
        int byteArray = super.toByteArray(bArr);
        int i = 0;
        while (true) {
            byte[] bArr2 = this.f11541a;
            if (i < bArr2.length) {
                byteArray++;
                bArr[byteArray] = bArr2[i];
                i++;
            } else {
                bArr[byteArray + 1] = this.b;
                return bArr;
            }
        }
    }

    @Override // com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand.AbstractCommand
    public byte getCheck() {
        return this.b;
    }

    public static class a extends AbstractCommand.a {
        byte e;
        private byte[] j;

        public a() {
            super((byte) 64);
            this.j = new byte[0];
        }

        public void a(byte b) {
            this.e = b;
        }

        public void b(byte[] bArr) {
            this.j = bArr;
        }

        public cyg b() {
            return new cyg(this);
        }

        public cyg e(byte[] bArr) {
            int c = super.c(bArr) + 1;
            this.j = new byte[(bArr.length - 1) - c];
            int i = 0;
            while (c < bArr.length - 1) {
                this.j[i] = bArr[c];
                i++;
                c++;
            }
            this.e = bArr[bArr.length - 1];
            return new cyg(this);
        }
    }

    @Override // com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand.AbstractCommand
    public byte[] getPara() {
        return this.f11541a;
    }

    @Override // com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand.AbstractCommand
    public byte[] getParameterLength() {
        return new byte[0];
    }
}
