package defpackage;

import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.unitedevice.hwwifip2ptransfermgr.HwWifiP2pTransferManager;
import com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pSendFileInterface;
import health.compact.a.LogUtil;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.List;

/* loaded from: classes7.dex */
public class sox extends Thread {
    private ExtendHandler b;
    private d e = new d();

    public sox() {
        setName("WifiP2pSendTask");
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        soz take;
        LogUtil.c("WifiP2pSendTask", "WifiP2pSendTask enter. : ", this);
        this.b = HandlerCenter.e("WifiP2pSendTask");
        while (true) {
            try {
                take = HwWifiP2pTransferManager.d().j().take();
            } catch (InterruptedException e) {
                LogUtil.e("WifiP2pSendTask", "WifiP2pSendTask take task wrong : ", bll.a(e));
            }
            if (take == null) {
                LogUtil.a("WifiP2pSendTask", "quit thread.");
                this.b.quit(true);
                return;
            }
            LogUtil.c("WifiP2pSendTask", "get task : ", take, "path : ", take.j(), " length : ", Long.valueOf(take.f()), " tag : ", Integer.valueOf(take.y()));
            if (a(take)) {
                LogUtil.a("WifiP2pSendTask", "stop thread.");
                HwWifiP2pTransferManager.d().j().clear();
                this.b.quit(true);
                return;
            } else if (take.u() == 2) {
                d(take);
            } else {
                b(take);
            }
        }
    }

    private boolean a(soz sozVar) {
        return sozVar.y() == 1;
    }

    private void d(soz sozVar) {
        FileOutputStream fileOutputStream;
        File file;
        if (sozVar != null) {
            long j = 0;
            if (sozVar.f() >= 0) {
                LogUtil.c("WifiP2pSendTask", "recvCommonFile path is:", sozVar.j(), " size:", Long.valueOf(sozVar.f()));
                byte[] bArr = new byte[new sou().getSocketBufferLength(sozVar)];
                FileOutputStream fileOutputStream2 = null;
                try {
                    try {
                        file = new File(sozVar.j());
                        File file2 = new File(file.getParent());
                        if (!file2.exists()) {
                            LogUtil.c("WifiP2pSendTask", "recv file directory not exists, create related directory.");
                            if (file2.mkdir()) {
                                LogUtil.c("WifiP2pSendTask", "recv file directory create success");
                            }
                        }
                        d(file);
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = null;
                    }
                } catch (IOException e) {
                    e = e;
                }
                if (!file.createNewFile()) {
                    LogUtil.c("WifiP2pSendTask", "WifiP2pSendTask isFileCreate false");
                    blv.d(null);
                    return;
                }
                fileOutputStream = new FileOutputStream(file);
                try {
                    DataInputStream dataInputStream = new DataInputStream(spc.d().a().getInputStream());
                    int i = 0;
                    while (true) {
                        int read = dataInputStream.read(bArr);
                        if (read < 0) {
                            blv.d(fileOutputStream);
                            break;
                        }
                        HwWifiP2pTransferManager.d().r();
                        j += read;
                        LogUtil.c("WifiP2pSendTask", "recvLen:", Integer.valueOf(read), " recvTotal: ", Long.valueOf(j));
                        int f = (int) ((100 * j) / sozVar.f());
                        e(sozVar, f, i);
                        LogUtil.c("WifiP2pSendTask", "writeLen :" + fileOutputStream.getChannel().write(ByteBuffer.wrap(bArr, 0, read)));
                        if (j >= sozVar.f()) {
                            fileOutputStream.getChannel().close();
                            e(sozVar);
                            LogUtil.c("WifiP2pSendTask", "recv file success.");
                            blv.d(fileOutputStream);
                            return;
                        }
                        i = f;
                    }
                } catch (IOException e2) {
                    e = e2;
                    fileOutputStream2 = fileOutputStream;
                    LogUtil.e("WifiP2pSendTask", "recvCommonFile error is: ", e);
                    blv.d(fileOutputStream2);
                    a(sozVar, 1004, "recv file error");
                    LogUtil.c("WifiP2pSendTask", "recv file fail.");
                    return;
                } catch (Throwable th2) {
                    th = th2;
                    blv.d(fileOutputStream);
                    throw th;
                }
                a(sozVar, 1004, "recv file error");
                LogUtil.c("WifiP2pSendTask", "recv file fail.");
                return;
            }
        }
        a(sozVar, 1001, "recvCommonFile params is wrong.");
    }

    private void d(File file) {
        if (file.exists() && file.delete()) {
            LogUtil.c("WifiP2pSendTask", "WifiP2pSendTask isFileDelete true");
        }
    }

    private void b(soz sozVar) {
        FileInputStream fileInputStream;
        DataOutputStream dataOutputStream;
        if (sozVar == null || sozVar.l() < 0 || sozVar.r() < 0) {
            a(sozVar, 1001, "fileInfo params is wrong.");
            return;
        }
        LogUtil.c("WifiP2pSendTask", " filePath is: ", sozVar.j(), " sendLength is: ", Integer.valueOf(sozVar.r()), "file length : ", Long.valueOf(sozVar.f()));
        WifiP2pSendFileInterface c = c(sozVar);
        if (c == null) {
            a(sozVar, 1001, "special tag set wrong.");
            return;
        }
        HwWifiP2pTransferManager d2 = HwWifiP2pTransferManager.d();
        Socket a2 = spc.d().a();
        if (d(a2, sozVar)) {
            FileInputStream fileInputStream2 = null;
            try {
                try {
                    dataOutputStream = new DataOutputStream(a2.getOutputStream());
                    fileInputStream = d2.ejZ_(sozVar.eke_(), sozVar.j(), sozVar.x());
                } catch (IOException e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
            }
            try {
            } catch (IOException e2) {
                fileInputStream2 = fileInputStream;
                e = e2;
                LogUtil.e("WifiP2pSendTask", "sendCommonFile error is: ", bll.a(e));
                b(d2, sozVar);
                LogUtil.c("WifiP2pSendTask", "close io");
                fileInputStream = fileInputStream2;
                blv.d(fileInputStream);
                LogUtil.c("WifiP2pSendTask", "read file to outputStream over.");
            } catch (Throwable th2) {
                th = th2;
                fileInputStream2 = fileInputStream;
                LogUtil.c("WifiP2pSendTask", "close io");
                blv.d(fileInputStream2);
                throw th;
            }
            if (fileInputStream == null) {
                LogUtil.a("WifiP2pSendTask", "file info is wrong, please check.");
                a(sozVar, 1017, "can not get inputStream, please check.");
                LogUtil.c("WifiP2pSendTask", "close io");
                blv.d(fileInputStream);
                return;
            }
            a(sozVar, c, dataOutputStream, fileInputStream, d2);
            LogUtil.c("WifiP2pSendTask", "close io");
            blv.d(fileInputStream);
            LogUtil.c("WifiP2pSendTask", "read file to outputStream over.");
        }
    }

    private boolean d(Socket socket, soz sozVar) {
        if (socket != null) {
            return true;
        }
        a(sozVar, 1019, "socket is null. please check.");
        return false;
    }

    private void a(soz sozVar, WifiP2pSendFileInterface wifiP2pSendFileInterface, DataOutputStream dataOutputStream, FileInputStream fileInputStream, HwWifiP2pTransferManager hwWifiP2pTransferManager) throws IOException {
        List<Integer> list;
        WifiP2pSendFileInterface wifiP2pSendFileInterface2 = wifiP2pSendFileInterface;
        FileInputStream fileInputStream2 = fileInputStream;
        long wantSendLength = wifiP2pSendFileInterface2.getWantSendLength(sozVar);
        long skip = fileInputStream2.skip(sozVar.l());
        long deviceReceived = wifiP2pSendFileInterface2.getDeviceReceived(sozVar);
        long l = sozVar.l();
        int socketBufferLength = wifiP2pSendFileInterface2.getSocketBufferLength(sozVar);
        LogUtil.c("WifiP2pSendTask", "readLength : ", Integer.valueOf(wifiP2pSendFileInterface2.getReadLength(sozVar)), "skipSize ", Long.valueOf(skip), "wantSendLength : ", Long.valueOf(wantSendLength), "totalSendSize : ", Long.valueOf(deviceReceived), "contentBufferSize : ", Integer.valueOf(socketBufferLength), "inputStream:", Integer.valueOf(fileInputStream.available()), " allOffset:", Long.valueOf(l));
        List<Integer> b = sozVar.b();
        ByteBuffer allocate = ByteBuffer.allocate(socketBufferLength);
        int i = -1;
        int i2 = 0;
        while (true) {
            int readFile = wifiP2pSendFileInterface2.readFile(allocate, l, fileInputStream2);
            if (readFile <= 0) {
                break;
            }
            if (!a(hwWifiP2pTransferManager, sozVar)) {
                LogUtil.a("WifiP2pSendTask", "stop wifi P2p");
                break;
            }
            LogUtil.c("WifiP2pSendTask", "ready write data.");
            if (a(i2, b)) {
                f(sozVar);
                list = b;
                wifiP2pSendFileInterface2.writeSocket(dataOutputStream, wifiP2pSendFileInterface2.wrapSendData(allocate, l, i2), readFile, i2);
                allocate.clear();
                LogUtil.c("WifiP2pSendTask", "close time out.");
                a();
            } else {
                list = b;
            }
            long j = readFile;
            l += j;
            deviceReceived += j;
            LogUtil.c("WifiP2pSendTask", "write over.", "send allOffset : ", Long.valueOf(l));
            int f = (int) ((100 * deviceReceived) / sozVar.f());
            i2++;
            e(sozVar, f, i);
            dataOutputStream.flush();
            if (wantSendLength <= l) {
                LogUtil.c("WifiP2pSendTask", "this wifi send all send.");
                break;
            }
            int min = Math.min((int) (wantSendLength - l), socketBufferLength);
            ByteBuffer allocate2 = ByteBuffer.allocate(min);
            LogUtil.c("WifiP2pSendTask", "readLength : ", Integer.valueOf(min));
            wifiP2pSendFileInterface2 = wifiP2pSendFileInterface;
            allocate = allocate2;
            b = list;
            i = f;
            fileInputStream2 = fileInputStream;
        }
        e(deviceReceived, sozVar);
    }

    private void f(soz sozVar) {
        LogUtil.c("WifiP2pSendTask", "ready new timer.");
        d dVar = new d();
        this.e = dVar;
        dVar.b(sozVar);
        this.b.postTask(this.e, 15000L);
    }

    private void a() {
        this.b.removeTasksAndMessages();
    }

    private void b(HwWifiP2pTransferManager hwWifiP2pTransferManager, soz sozVar) {
        if (hwWifiP2pTransferManager.n() == 1) {
            a(sozVar, 1004, "wifi socket ioException");
        } else {
            LogUtil.a("WifiP2pSendTask", "wifi socket exception. please check.");
        }
        a();
    }

    private boolean a(HwWifiP2pTransferManager hwWifiP2pTransferManager, soz sozVar) {
        if (hwWifiP2pTransferManager.n() == 1) {
            return true;
        }
        if (hwWifiP2pTransferManager.n() == 2) {
            LogUtil.c("WifiP2pSendTask", "user stop send file.");
            a(sozVar, 1014, "user stop wifi");
        }
        if (hwWifiP2pTransferManager.n() != 5) {
            return false;
        }
        LogUtil.c("WifiP2pSendTask", "watch post repeat ");
        hwWifiP2pTransferManager.q();
        return false;
    }

    private WifiP2pSendFileInterface c(soz sozVar) {
        int y = sozVar.y();
        if ((y & 2) == 2) {
            return new sou();
        }
        if ((y & 4) == 4) {
            return new sos();
        }
        LogUtil.a("WifiP2pSendTask", "unknown tag, please check : ", Integer.valueOf(y));
        return null;
    }

    private boolean a(int i, List<Integer> list) {
        if (list == null) {
            return true;
        }
        if (i <= list.size() - 1) {
            return list.get(i).intValue() == 0;
        }
        LogUtil.a("WifiP2pSendTask", "psn > bitmap.size, please check 5.9.3");
        return true;
    }

    private void e(long j, soz sozVar) {
        long f = sozVar.f();
        LogUtil.c("WifiP2pSendTask", "total : ", Long.valueOf(j), " fileLength : ", Long.valueOf(f));
        if (j == f) {
            e(sozVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(soz sozVar, int i, String str) {
        if (sozVar != null && sozVar.k() != null) {
            sozVar.k().onFail(i, str, sozVar.o());
        } else {
            LogUtil.a("WifiP2pSendTask", "set params is wrong, please check fileInfo and listener");
        }
    }

    private void e(soz sozVar, int i, int i2) {
        if (sozVar != null && sozVar.k() != null) {
            LogUtil.c("WifiP2pSendTask", "wifi p2p file : process : ", Integer.valueOf(i));
            sozVar.k().onProcess(i, sozVar.o());
            HwWifiP2pTransferManager.d().z();
            return;
        }
        LogUtil.a("WifiP2pSendTask", "onProcess set params is wrong, please check fileInfo and listener");
    }

    private void e(soz sozVar) {
        if (sozVar != null && sozVar.k() != null) {
            LogUtil.c("WifiP2pSendTask", "file send onSuccess");
            sozVar.k().onSuccess(10000, "file send over.", sozVar.o());
        } else {
            LogUtil.a("WifiP2pSendTask", "onSuccess set params is wrong, please check fileInfo and listener");
        }
    }

    static class d implements Runnable {
        private sox c;
        private soz d;

        private d(sox soxVar) {
            this.c = soxVar;
        }

        void b(soz sozVar) {
            this.d = sozVar;
            LogUtil.c("WifiP2pSendTask", "mWifiP2pFileInfo : ", sozVar);
            soz sozVar2 = this.d;
            if (sozVar2 != null) {
                if (sozVar2.k() != null) {
                    LogUtil.c("WifiP2pSendTask", "listener : ", this.d.k());
                    return;
                } else {
                    LogUtil.a("WifiP2pSendTask", "listener is null.");
                    return;
                }
            }
            LogUtil.a("WifiP2pSendTask", "file info is null.");
        }

        @Override // java.lang.Runnable
        public void run() {
            LogUtil.a("WifiP2pSendTask", "socket timeout, please check device, close wifi try again.");
            this.c.a(this.d, 1018, "socket send data timeout. please check device, close wifi try again.");
        }
    }
}
