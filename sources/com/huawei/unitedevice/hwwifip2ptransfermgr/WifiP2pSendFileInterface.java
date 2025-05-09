package com.huawei.unitedevice.hwwifip2ptransfermgr;

import defpackage.soz;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: classes7.dex */
public interface WifiP2pSendFileInterface {
    int getDeviceReceived(soz sozVar);

    int getReadLength(soz sozVar);

    int getSocketBufferLength(soz sozVar);

    int getWantSendLength(soz sozVar);

    int readFile(ByteBuffer byteBuffer, long j, FileInputStream fileInputStream) throws IOException;

    ByteBuffer wrapSendData(ByteBuffer byteBuffer, long j, int i);

    void writeSocket(DataOutputStream dataOutputStream, ByteBuffer byteBuffer, int i, int i2) throws IOException;
}
