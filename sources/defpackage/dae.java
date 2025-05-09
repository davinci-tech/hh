package defpackage;

import android.os.ParcelFileDescriptor;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.ecologydevice.open.HdpMeasureController;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.IoUtils;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class dae extends HdpMeasureController {

    /* renamed from: a, reason: collision with root package name */
    private dah f11568a;
    private ArrayList<HealthData> b = new ArrayList<>();
    private ddz e;

    @Override // com.huawei.health.ecologydevice.open.HdpMeasureController
    public int getDataType() {
        return 4103;
    }

    @Override // com.huawei.health.ecologydevice.open.HdpMeasureController
    public void handleHealthChannelStateChange(int i, int i2, ParcelFileDescriptor parcelFileDescriptor, int i3) {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2;
        byte[] bArr = new byte[1024];
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(parcelFileDescriptor.getFileDescriptor());
            try {
                fileOutputStream2 = new FileOutputStream(parcelFileDescriptor.getFileDescriptor());
            } catch (IOException e) {
                e = e;
            } catch (Throwable th) {
                th = th;
            }
            try {
                this.f11568a = new dah();
                while (fileInputStream2.read(bArr) > -1) {
                    byte b = bArr[0];
                    if (b == -30) {
                        LogUtil.c("HdpBloodPressureMeasureController", "handleHealthChannelStateChange fos.write RESPONSE_2_2_3");
                        fileOutputStream2.write(dad.b);
                    } else if (b == -25 && bArr[18] == 13 && bArr[19] == 28) {
                        LogUtil.c("HdpBloodPressureMeasureController", "handleHealthChannelStateChange fos.write RESPONSE_3_2_3");
                        fileOutputStream2.write(dad.c);
                    } else if (b == -25 && bArr[18] == 13 && bArr[19] == 31) {
                        a(1, bArr, fileOutputStream2);
                    } else if (b == -26) {
                        LogUtil.c("HdpBloodPressureMeasureController", "handleHealthChannelStateChange ABORT_APDU");
                        b();
                    } else if (b == -28) {
                        LogUtil.c("HdpBloodPressureMeasureController", "handleHealthChannelStateChange fos.write RESPONSE_6_2");
                        fileOutputStream2.write(dad.f11567a);
                        b();
                    } else {
                        LogUtil.h("HdpBloodPressureMeasureController", "handleHealthChannelStateChange other type");
                    }
                    bArr = new byte[1024];
                    fileOutputStream2.flush();
                }
                SN_(parcelFileDescriptor, fileInputStream2, fileOutputStream2);
            } catch (IOException e2) {
                e = e2;
                fileInputStream = fileOutputStream2;
                fileOutputStream = fileInputStream;
                fileInputStream = fileInputStream2;
                try {
                    LogUtil.b("HdpBloodPressureMeasureController", "handleHealthChannelStateChange error = ", e.getMessage());
                    SN_(parcelFileDescriptor, fileInputStream, fileOutputStream);
                } catch (Throwable th2) {
                    th = th2;
                    SN_(parcelFileDescriptor, fileInputStream, fileOutputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = fileOutputStream2;
                fileOutputStream = fileInputStream;
                fileInputStream = fileInputStream2;
                SN_(parcelFileDescriptor, fileInputStream, fileOutputStream);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            fileOutputStream = null;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
        }
    }

    private void SN_(ParcelFileDescriptor parcelFileDescriptor, FileInputStream fileInputStream, FileOutputStream fileOutputStream) {
        IoUtils.e(parcelFileDescriptor);
        IoUtils.e(fileInputStream);
        IoUtils.e(fileOutputStream);
    }

    private void b() {
        if (this.b.size() <= 0 || this.mBaseResponseCallback == null) {
            return;
        }
        this.mBaseResponseCallback.onDataChanged(this.mDevice, this.b);
    }

    private void a(int i, byte[] bArr, FileOutputStream fileOutputStream) {
        dah dahVar = this.f11568a;
        if (dahVar != null) {
            HealthData parseData = dahVar.parseData(bArr);
            if (parseData instanceof ddz) {
                ddz ddzVar = (ddz) parseData;
                this.e = ddzVar;
                this.b.add(ddzVar);
            }
            dad.e[7] = (byte) (i + 1);
            try {
                fileOutputStream.write(dad.e);
            } catch (IOException e) {
                LogUtil.b("HdpBloodPressureMeasureController", "handleHealthChannelStateChange error = ", e.getMessage());
            }
        }
    }
}
