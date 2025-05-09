package com.huawei.health.suggestion.model;

import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.sensor.Sensor;
import java.util.List;

/* loaded from: classes8.dex */
public class DeviceSensorInfo {
    private Device mDevice;
    private List<Sensor> mSensorList;
    private int mWearPosition;

    private DeviceSensorInfo(Builder builder) {
        this.mDevice = builder.mDevice;
        this.mWearPosition = builder.mWearPosition;
        this.mSensorList = builder.mSensors;
    }

    public Device getDevice() {
        return this.mDevice;
    }

    public int getWearPosition() {
        return this.mWearPosition;
    }

    public List<Sensor> getSensors() {
        return this.mSensorList;
    }

    public static final class Builder {
        private Device mDevice;
        private List<Sensor> mSensors;
        private int mWearPosition;

        public Builder setDevice(Device device) {
            this.mDevice = device;
            return this;
        }

        public Builder setWearPosition(int i) {
            this.mWearPosition = i;
            return this;
        }

        public Builder setSensorList(List<Sensor> list) {
            this.mSensors = list;
            return this;
        }

        public DeviceSensorInfo build() {
            return new DeviceSensorInfo(this);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("mDevice = [");
        sb.append(this.mDevice);
        sb.append("], mWearPosition = ");
        sb.append(this.mWearPosition);
        sb.append(", mSensorList = [ ");
        for (Sensor sensor : this.mSensorList) {
            sb.append("{sensorName = ");
            sb.append(sensor.getName());
            sb.append(", sensorId = ");
            sb.append(sensor.getId());
            sb.append(", sensorType = ");
            sb.append(sensor.getType());
            sb.append(", sensorDescribeContents = ");
            sb.append(sensor.describeContents());
            sb.append(", sensorAccuracy = ");
            sb.append(sensor.getAccuracy());
            sb.append(", sensorResolution = ");
            sb.append(sensor.getResolution());
            sb.append("} ");
        }
        sb.append("]");
        return sb.toString();
    }
}
