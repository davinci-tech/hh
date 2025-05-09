package com.huawei.watchface.mvp.model.datatype;

import java.util.List;

/* loaded from: classes7.dex */
public class RegisterInfo {
    private List<RegisterBean> list;

    public List<RegisterBean> getList() {
        return this.list;
    }

    public void setList(List<RegisterBean> list) {
        this.list = list;
    }

    public static class RegisterBean {
        private String bluetoothTlvName;
        private int commandId;
        private int commandType;
        private String deviceCommand;
        private int serviceId;
        private int type;

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

        public int getType() {
            return this.type;
        }

        public void setType(int i) {
            this.type = i;
        }

        public String getBluetoothTlvName() {
            return this.bluetoothTlvName;
        }

        public void setBluetoothTlvName(String str) {
            this.bluetoothTlvName = str;
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
    }
}
