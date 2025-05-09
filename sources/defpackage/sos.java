package defpackage;

import com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pSendFileInterface;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: classes7.dex */
class sos implements WifiP2pSendFileInterface {
    sos() {
    }

    @Override // com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pSendFileInterface
    public int getSocketBufferLength(soz sozVar) {
        return getReadLength(sozVar);
    }

    @Override // com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pSendFileInterface
    public ByteBuffer wrapSendData(ByteBuffer byteBuffer, long j, int i) {
        if (byteBuffer == null || i != 0) {
            return byteBuffer;
        }
        ByteBuffer allocate = ByteBuffer.allocate(byteBuffer.array().length + 5);
        ByteBuffer wrap = ByteBuffer.wrap(blq.a(blq.d(j)));
        ByteBuffer wrap2 = ByteBuffer.wrap(blq.g(i));
        System.arraycopy(wrap.array(), 0, allocate.array(), 0, 4);
        System.arraycopy(wrap2.array(), 0, allocate.array(), 4, 1);
        System.arraycopy(byteBuffer.array(), 0, allocate.array(), 5, byteBuffer.array().length);
        return allocate;
    }

    @Override // com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pSendFileInterface
    public int readFile(ByteBuffer byteBuffer, long j, FileInputStream fileInputStream) throws IOException {
        if (byteBuffer == null || fileInputStream == null) {
            return 0;
        }
        return fileInputStream.getChannel().read(byteBuffer, j);
    }

    @Override // com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pSendFileInterface
    public void writeSocket(DataOutputStream dataOutputStream, ByteBuffer byteBuffer, int i, int i2) throws IOException {
        if (dataOutputStream == null || byteBuffer == null) {
            return;
        }
        if (i2 == 0) {
            dataOutputStream.write(byteBuffer.array(), 0, i + 5);
        } else {
            dataOutputStream.write(byteBuffer.array(), 0, i);
        }
    }

    @Override // com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pSendFileInterface
    public int getReadLength(soz sozVar) {
        if (sozVar == null) {
            return 147456;
        }
        return Math.min(sozVar.r(), 147456);
    }

    @Override // com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pSendFileInterface
    public int getWantSendLength(soz sozVar) {
        if (sozVar == null) {
            return 0;
        }
        return sozVar.r() + sozVar.l();
    }

    @Override // com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pSendFileInterface
    public int getDeviceReceived(soz sozVar) {
        if (sozVar == null) {
            return 0;
        }
        return sozVar.t() + sozVar.s();
    }
}
