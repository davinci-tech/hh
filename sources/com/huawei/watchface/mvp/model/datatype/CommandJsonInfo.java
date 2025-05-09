package com.huawei.watchface.mvp.model.datatype;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes7.dex */
public class CommandJsonInfo implements Parcelable {
    public static final int COMMAND_TYPE_BT_CONNECT_STATUS = 801;
    public static final int COMMAND_TYPE_WATCH_FACE = 800;
    public static final Parcelable.Creator<CommandJsonInfo> CREATOR = new Parcelable.Creator<CommandJsonInfo>() { // from class: com.huawei.watchface.mvp.model.datatype.CommandJsonInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CommandJsonInfo createFromParcel(Parcel parcel) {
            int readInt = parcel.readInt();
            String readString = parcel.readString();
            int readInt2 = parcel.readInt();
            int readInt3 = parcel.readInt();
            CommandJsonInfo commandJsonInfo = new CommandJsonInfo();
            commandJsonInfo.setCommandType(readInt);
            commandJsonInfo.setDeviceCommand(readString);
            commandJsonInfo.setServiceId(readInt2);
            commandJsonInfo.setCommandId(readInt3);
            return commandJsonInfo;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CommandJsonInfo[] newArray(int i) {
            return new CommandJsonInfo[i];
        }
    };
    private String bluetoothTlvName;
    private int commandId;
    private int commandType;
    private String deviceCommand;
    private int serviceId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getBluetoothTlvName() {
        return this.bluetoothTlvName;
    }

    public void setBluetoothTlvName(String str) {
        this.bluetoothTlvName = str;
    }

    public int getCommandType() {
        return this.commandType;
    }

    public void setCommandType(int i) {
        this.commandType = i;
    }

    public String getDeviceCommand() {
        return this.deviceCommand;
    }

    public void setDeviceCommand(String str) {
        this.deviceCommand = str;
    }

    public int getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(int i) {
        this.serviceId = i;
    }

    public int getCommandId() {
        return this.commandId;
    }

    public void setCommandId(int i) {
        this.commandId = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.commandType);
        parcel.writeString(this.deviceCommand);
        parcel.writeInt(this.serviceId);
        parcel.writeInt(this.commandId);
    }

    public String toString() {
        return "{commandType:" + this.commandType + ",deviceCommand:" + this.deviceCommand + ",serviceId:" + this.serviceId + ",commandId:" + this.commandId + "}";
    }
}
