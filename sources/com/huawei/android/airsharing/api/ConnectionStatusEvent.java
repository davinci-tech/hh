package com.huawei.android.airsharing.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/* loaded from: classes2.dex */
public class ConnectionStatusEvent extends Event implements Parcelable {
    public static final int ALLOWED_ALWAYS = 14;
    public static final int ALLOWED_ONCE = 13;
    public static final int AUTH_FAILED = 10023;
    public static final int CONNECTING_KIT_AUTH_ALTER = 11;
    public static final int CONNECTING_KIT_AUTH_DENY = 12;
    public static final int CONNECTING_NORMAL = 0;
    public static final int CONNECTING_WAITING_CONFIRM = 1;
    public static final int CONNECT_ERROR_NONE = 0;
    public static final int CONNECT_FAIL_REMOTE_DEVICE_BUSY = 4;
    public static final int CONNECT_FAIL_RETRY_PIN_OVER_THREE = 5;
    public static final int CONNECT_FAIL_RETRY_PWD_OVER_THREE = 6;
    public static final int CONNECT_FAIL_TIMEOUT = 1;
    public static final int CONNECT_FAIL_UNKNOWN = 10;
    public static final int CONNECT_FAIL_WRONG_HICHAIN_AUTH_CODE = 3;
    public static final int CONNECT_FAIL_WRONG_USER_AUTH_CODE = 2;
    public static final Parcelable.Creator<ConnectionStatusEvent> CREATOR = new Parcelable.Creator<ConnectionStatusEvent>() { // from class: com.huawei.android.airsharing.api.ConnectionStatusEvent.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: dx_, reason: merged with bridge method [inline-methods] */
        public ConnectionStatusEvent createFromParcel(Parcel parcel) {
            return new ConnectionStatusEvent(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public ConnectionStatusEvent[] newArray(int i) {
            return new ConnectionStatusEvent[i];
        }
    };
    public static final int DISTANCE_TOO_FAR_OR_LOCAL_BR_ERROR = 10018;
    public static final int DISTANCE_TOO_FAR_OR_PEER_P2P_ERROR = 10022;
    public static final int DUAL_END_BR_ERROR = 10017;
    public static final int DUAL_END_P2P_ERROR = 10021;
    public static final int LOCAL_BR_ERROR = 10015;
    public static final int LOCAL_P2P_ERROR = 10019;
    public static final int LOCAL_RTSP_ERROR = 10019;
    public static final int PEER_BR_ERROR = 10016;
    public static final int PEER_P2P_ERROR = 10020;
    public static final int PEER_RTSP_ERROR = 10020;
    public static final int PROJECTION_FAILED_REASON_BASE = 10000;
    private int mCauseReason;
    private String mDetails;
    private ProjectionDevice mDevice;
    private PlayerCapability mPlayerCapability;
    private EProjectionMode mProjectionMode;

    @Override // com.huawei.android.airsharing.api.Event, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ConnectionStatusEvent(int i, ProjectionDevice projectionDevice) {
        this(i, projectionDevice, 0, EProjectionMode.MIRROR);
    }

    public ConnectionStatusEvent(int i, ProjectionDevice projectionDevice, int i2) {
        this(i, projectionDevice, i2, EProjectionMode.MIRROR);
    }

    public ConnectionStatusEvent(int i, ProjectionDevice projectionDevice, int i2, EProjectionMode eProjectionMode) {
        super(i);
        this.mDetails = "connect state event";
        this.mPlayerCapability = null;
        this.mDevice = projectionDevice;
        this.mCauseReason = i2;
        this.mProjectionMode = eProjectionMode;
    }

    protected ConnectionStatusEvent(Parcel parcel) {
        super(parcel);
        this.mDetails = "connect state event";
        this.mCauseReason = 0;
        this.mPlayerCapability = null;
        this.mDetails = parcel.readString();
        this.mDevice = (ProjectionDevice) parcel.readParcelable(ProjectionDevice.class.getClassLoader());
        this.mCauseReason = parcel.readInt();
        this.mProjectionMode = checkProjectionMode(parcel.readString());
        this.mPlayerCapability = (PlayerCapability) parcel.readParcelable(PlayerCapability.class.getClassLoader());
    }

    protected ConnectionStatusEvent(int i, Parcel parcel) {
        super(i);
        this.mDetails = "connect state event";
        this.mCauseReason = 0;
        this.mPlayerCapability = null;
        this.mDetails = parcel.readString();
        this.mDevice = (ProjectionDevice) parcel.readParcelable(ProjectionDevice.class.getClassLoader());
        this.mCauseReason = parcel.readInt();
        this.mProjectionMode = checkProjectionMode(parcel.readString());
        this.mPlayerCapability = (PlayerCapability) parcel.readParcelable(PlayerCapability.class.getClassLoader());
    }

    @Override // com.huawei.android.airsharing.api.Event, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        super.writeToParcel(parcel, i);
        parcel.writeString(this.mDetails);
        parcel.writeParcelable(this.mDevice, i);
        parcel.writeInt(this.mCauseReason);
        parcel.writeString(this.mProjectionMode.toString());
        parcel.writeParcelable(this.mPlayerCapability, i);
    }

    public void setDevice(ProjectionDevice projectionDevice) {
        this.mDevice = projectionDevice;
    }

    public ProjectionDevice getDevice() {
        return this.mDevice;
    }

    public int getExceptionReason() {
        return this.mCauseReason;
    }

    public EProjectionMode getProjectionMode() {
        return this.mProjectionMode;
    }

    public PlayerCapability getPlayerCapability() {
        return this.mPlayerCapability;
    }

    public void setPlayerCapability(PlayerCapability playerCapability) {
        this.mPlayerCapability = playerCapability;
    }

    private EProjectionMode checkProjectionMode(String str) {
        if (str != null) {
            try {
                return EProjectionMode.valueOf(str);
            } catch (IllegalArgumentException unused) {
                Log.e("AirsharingEvent", "invalid ProjectionMode");
            }
        }
        return EProjectionMode.MIRROR;
    }
}
