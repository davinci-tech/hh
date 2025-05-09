package com.huawei.health.device.api;

import android.bluetooth.BluetoothDevice;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.ui.commonui.linechart.common.DateType;
import defpackage.cjv;
import java.util.List;

/* loaded from: classes.dex */
public interface EcologyDeviceApi {
    List<HiHealthData> assembleRopeCaloriePoints(List<HiHealthData> list);

    MeasurableDevice fromBluetoothDevice(BluetoothDevice bluetoothDevice);

    String getNonWearableDeviceName(cjv cjvVar);

    String getProdId(String str);

    String getProductInfo(String str);

    boolean isDeviceConnected(String str);

    boolean isHighRopeType(String str);

    boolean isSupportPerformanceByProductId(String str);

    void quaryPerformanceOfBest(IBaseResponseCallback iBaseResponseCallback, DateType dateType);

    float queryPerformanceRank(String str, float f, DateType dateType);

    void requestPerformanceRank(IBaseResponseCallback iBaseResponseCallback);

    void ropeDataSetDeviceType(MotionPathSimplify motionPathSimplify);

    void ropeSetFitnessMachineControl(int i, int i2, int[] iArr);

    void setPreference(String str, String str2);

    void syncCloud(int i, IBaseResponseCallback iBaseResponseCallback);

    void syncCloud(int i, boolean z, IBaseResponseCallback iBaseResponseCallback);

    void uploadDataToHota(String str, String str2);
}
