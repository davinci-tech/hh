package defpackage;

import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.ecologydevice.open.data.model.IHeartRateAndBloodPressure;
import java.util.Calendar;

/* loaded from: classes3.dex */
public class deo extends HealthData implements IHeartRateAndBloodPressure {

    /* renamed from: a, reason: collision with root package name */
    private short f11626a;
    private short b;
    private short c;
    private long d;
    private long e;

    public static int d(byte b) {
        return b & 255;
    }

    @Override // com.huawei.health.ecologydevice.open.data.model.IHeartRateAndBloodPressure
    public short getSystolic() {
        return this.b;
    }

    @Override // com.huawei.health.ecologydevice.open.data.model.IHeartRateAndBloodPressure
    public void setSystolic(short s) {
        this.b = s;
    }

    @Override // com.huawei.health.ecologydevice.open.data.model.IHeartRateAndBloodPressure
    public short getDiastolic() {
        return this.c;
    }

    @Override // com.huawei.health.ecologydevice.open.data.model.IHeartRateAndBloodPressure
    public void setDiastolic(short s) {
        this.c = s;
    }

    @Override // com.huawei.health.device.open.data.model.HealthData
    public long getStartTime() {
        return this.e;
    }

    @Override // com.huawei.health.device.open.data.model.HealthData
    public void setStartTime(long j) {
        this.e = j;
    }

    @Override // com.huawei.health.device.open.data.model.HealthData
    public long getEndTime() {
        return this.d;
    }

    @Override // com.huawei.health.device.open.data.model.HealthData
    public void setEndTime(long j) {
        this.d = j;
    }

    @Override // com.huawei.health.ecologydevice.open.data.model.IHeartRateAndBloodPressure
    public short getHeartRate() {
        return this.f11626a;
    }

    @Override // com.huawei.health.ecologydevice.open.data.model.IHeartRateAndBloodPressure
    public void setHeartRate(short s) {
        this.f11626a = s;
    }

    public void c(byte[] bArr) {
        setSystolic((short) d(bArr[3]));
        setDiastolic((short) d(bArr[4]));
        setStartTime(Calendar.getInstance().getTimeInMillis());
        setHeartRate((short) d(bArr[2]));
    }
}
