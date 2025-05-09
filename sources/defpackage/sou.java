package defpackage;

import com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pSendFileInterface;
import health.compact.a.LogUtil;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/* loaded from: classes7.dex */
class sou implements WifiP2pSendFileInterface {
    @Override // com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pSendFileInterface
    public ByteBuffer wrapSendData(ByteBuffer byteBuffer, long j, int i) {
        return byteBuffer;
    }

    sou() {
    }

    @Override // com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pSendFileInterface
    public int getSocketBufferLength(soz sozVar) {
        if (sozVar == null) {
            return 1024;
        }
        return getReadLength(sozVar);
    }

    @Override // com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pSendFileInterface
    public int readFile(ByteBuffer byteBuffer, long j, FileInputStream fileInputStream) throws IOException {
        if (byteBuffer == null || fileInputStream == null) {
            return 0;
        }
        FileChannel channel = fileInputStream.getChannel();
        int read = channel.read(byteBuffer, j);
        LogUtil.c("NormalWifiP2pSendFileImp", "read length : ", Integer.valueOf(read), "read size : ", Long.valueOf(channel.size()));
        return read;
    }

    @Override // com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pSendFileInterface
    public void writeSocket(DataOutputStream dataOutputStream, ByteBuffer byteBuffer, int i, int i2) throws IOException {
        if (dataOutputStream == null || byteBuffer == null) {
            return;
        }
        dataOutputStream.write(byteBuffer.array(), 0, i);
    }

    @Override // com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pSendFileInterface
    public int getReadLength(soz sozVar) {
        if (sozVar == null) {
            return 1024;
        }
        long f = sozVar.f();
        int e = e(sozVar.h());
        return f > ((long) e) ? e : (int) f;
    }

    @Override // com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pSendFileInterface
    public int getWantSendLength(soz sozVar) {
        if (sozVar == null) {
            return 0;
        }
        return (int) sozVar.f();
    }

    private int e(int i) {
        if (i >= 65536 || i == 0) {
            return 65536;
        }
        return (65536 / i) * i;
    }

    @Override // com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pSendFileInterface
    public int getDeviceReceived(soz sozVar) {
        if (sozVar == null) {
            return 0;
        }
        return sozVar.l();
    }
}
