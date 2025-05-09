package com.huawei.maps.offlinedata.health.p2p;

import com.huawei.maps.offlinedata.utils.g;
import java.io.File;

/* loaded from: classes5.dex */
public class OfflineMapP2pMessage {
    public static final int MESSAGE_TYPE_DATA = 1;
    public static final int MESSAGE_TYPE_DEFAULT = 0;
    public static final int MESSAGE_TYPE_FILE = 2;
    private static final String TAG = "P2pMessage";
    private final byte[] data;
    private final String description;
    private final boolean enableEncrypt;
    private final File file;

    protected OfflineMapP2pMessage(Builder builder) {
        this.description = builder.description;
        this.data = builder.data;
        this.file = builder.file;
        this.enableEncrypt = builder.enableEncrypt;
    }

    public byte[] getData() {
        return this.data;
    }

    public File getFile() {
        return this.file;
    }

    public String getDescription() {
        return this.description;
    }

    public int getType() {
        if (this.data != null) {
            return 1;
        }
        return this.file != null ? 2 : 0;
    }

    public boolean isEnableEncrypt() {
        return this.enableEncrypt;
    }

    public static class Builder {
        private byte[] data;
        String description;
        private File file;
        private int type = 0;
        private boolean enableEncrypt = true;

        public Builder setPayload(byte[] bArr) {
            int i = this.type;
            if (i == 0 || i == 1) {
                this.data = bArr;
                this.type = 1;
            } else {
                g.c(OfflineMapP2pMessage.TAG, "Data setPayload type: " + this.type);
            }
            return this;
        }

        public Builder setPayload(File file) {
            int i = this.type;
            if (i == 0 || i == 2) {
                this.file = file;
                this.type = 2;
            } else {
                g.c(OfflineMapP2pMessage.TAG, "File setPayload type: " + this.type);
            }
            return this;
        }

        public Builder setDescription(String str) {
            this.description = str;
            return this;
        }

        public Builder setEnableEncrypt(boolean z) {
            this.enableEncrypt = z;
            return this;
        }

        public OfflineMapP2pMessage build() {
            return new OfflineMapP2pMessage(this);
        }
    }
}
