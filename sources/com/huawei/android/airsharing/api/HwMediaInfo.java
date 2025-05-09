package com.huawei.android.airsharing.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.android.airsharing.tool.UtilTools;

/* loaded from: classes2.dex */
public class HwMediaInfo implements Parcelable {
    public static final Parcelable.Creator<HwMediaInfo> CREATOR = new Parcelable.Creator<HwMediaInfo>() { // from class: com.huawei.android.airsharing.api.HwMediaInfo.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public HwMediaInfo[] newArray(int i) {
            return new HwMediaInfo[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: dI_, reason: merged with bridge method [inline-methods] */
        public HwMediaInfo createFromParcel(Parcel parcel) {
            HwMediaInfo hwMediaInfo = new HwMediaInfo();
            hwMediaInfo.setName(parcel.readString());
            hwMediaInfo.setTitle(parcel.readString());
            hwMediaInfo.setMediaInfoType(EHwMediaInfoType.valueOf(parcel.readString()));
            hwMediaInfo.setUrl(parcel.readString());
            hwMediaInfo.setMimeType(parcel.readString());
            hwMediaInfo.setAddDate(parcel.readString());
            hwMediaInfo.setIconUri(parcel.readString());
            hwMediaInfo.setDuration(parcel.readString());
            hwMediaInfo.setArtist(parcel.readString());
            hwMediaInfo.setSize(parcel.readLong());
            hwMediaInfo.setWidth(parcel.readInt());
            hwMediaInfo.setHeight(parcel.readInt());
            hwMediaInfo.setPosition(parcel.readString());
            hwMediaInfo.setVolume(parcel.readInt());
            hwMediaInfo.setExtendObj(parcel.readValue(Object.class.getClassLoader()));
            return hwMediaInfo;
        }
    };
    public static final String DIDL_LITE_END = "</DIDL-Lite>";
    protected String addDate;
    protected String artist;
    protected String duration;
    protected Object extendObj;
    protected int height;
    protected String iconUri;
    protected EHwMediaInfoType mediaInfoType;
    protected String mimeType;
    protected String name;
    protected String position;
    protected long size;
    protected String title;
    protected String url;
    protected int volume;
    protected int width;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HwMediaInfo() {
        this(null);
    }

    public HwMediaInfo(EHwMediaInfoType eHwMediaInfoType) {
        this.mediaInfoType = eHwMediaInfoType;
    }

    protected String getUpnpClass() {
        if (this.mediaInfoType == null) {
            return null;
        }
        int i = AnonymousClass3.c[this.mediaInfoType.ordinal()];
        if (i == 1) {
            return "object.item.audioItem.musicTrack";
        }
        if (i == 2) {
            return "object.item.videoItem";
        }
        if (i == 3) {
            return "object.item.imageItem.photo";
        }
        if (i != 4) {
            return null;
        }
        return "object.container";
    }

    /* renamed from: com.huawei.android.airsharing.api.HwMediaInfo$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] c;

        static {
            int[] iArr = new int[EHwMediaInfoType.values().length];
            c = iArr;
            try {
                iArr[EHwMediaInfoType.AUDIO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                c[EHwMediaInfoType.VIDEO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                c[EHwMediaInfoType.IMAGE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                c[EHwMediaInfoType.FOLDER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    protected String getProtocolInfo() {
        String str = this.mimeType;
        return str == null ? "http-get:*:*:*" : str;
    }

    public boolean isLocalFile() {
        String str = this.url;
        if (str == null || str.length() == 0) {
            return false;
        }
        return this.url.startsWith("/") || UtilTools.isContentUri(this.url);
    }

    public String getMetaData() {
        if (isLocalFile()) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer("<DIDL-Lite xmlns=\"urn:schemas-upnp-org:metadata-1-0/DIDL-Lite/\" xmlns:dlna=\"urn:schemas-dlna-org:metadata-1-0/\" xmlns:dc=\"http://purl.org/dc/elements/1.1/\" xmlns:upnp=\"urn:schemas-upnp-org:metadata-1-0/upnp/\"><item id=\"0\" parentID=\"0\" restricted=\"1\"><dc:title>");
        stringBuffer.append(this.title);
        stringBuffer.append("</dc:title><upnp:class>");
        stringBuffer.append(getUpnpClass());
        stringBuffer.append("</upnp:class><res size=\"");
        stringBuffer.append(this.size);
        stringBuffer.append("\" protocolInfo=\"");
        stringBuffer.append(getProtocolInfo());
        stringBuffer.append("\">");
        stringBuffer.append(this.url);
        stringBuffer.append("</res></item></DIDL-Lite>");
        return stringBuffer.toString();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public EHwMediaInfoType getMediaInfoType() {
        return this.mediaInfoType;
    }

    public void setMediaInfoType(EHwMediaInfoType eHwMediaInfoType) {
        this.mediaInfoType = eHwMediaInfoType;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public void setMimeType(String str) {
        this.mimeType = str;
    }

    public String getAddDate() {
        return this.addDate;
    }

    public void setAddDate(String str) {
        this.addDate = str;
    }

    public String getIconUri() {
        return this.iconUri;
    }

    public void setIconUri(String str) {
        this.iconUri = str;
    }

    public long getSize() {
        return this.size;
    }

    public void setSize(long j) {
        this.size = j;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String str) {
        this.duration = str;
    }

    public String getArtist() {
        return this.artist;
    }

    public void setArtist(String str) {
        this.artist = str;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String str) {
        this.position = str;
    }

    public int getVolume() {
        return this.volume;
    }

    public void setVolume(int i) {
        this.volume = i;
    }

    public Object getExtendObj() {
        return this.extendObj;
    }

    public void setExtendObj(Object obj) {
        this.extendObj = obj;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeString(this.name);
        parcel.writeString(this.title);
        parcel.writeString(this.mediaInfoType.toString());
        parcel.writeString(this.url);
        parcel.writeString(this.mimeType);
        parcel.writeString(this.addDate);
        parcel.writeString(this.iconUri);
        parcel.writeString(this.duration);
        parcel.writeString(this.artist);
        parcel.writeLong(this.size);
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
        parcel.writeString(this.position);
        parcel.writeInt(this.volume);
        parcel.writeValue(this.extendObj);
    }

    public HwMediaInfo copy() {
        HwMediaInfo hwMediaInfo = new HwMediaInfo();
        hwMediaInfo.setAddDate(this.addDate);
        hwMediaInfo.setArtist(this.artist);
        hwMediaInfo.setDuration(this.duration);
        hwMediaInfo.setHeight(this.height);
        hwMediaInfo.setIconUri(this.iconUri);
        hwMediaInfo.setMediaInfoType(this.mediaInfoType);
        hwMediaInfo.setMimeType(this.mimeType);
        hwMediaInfo.setName(this.name);
        hwMediaInfo.setSize(this.size);
        hwMediaInfo.setTitle(this.title);
        hwMediaInfo.setUrl(this.url);
        hwMediaInfo.setWidth(this.width);
        hwMediaInfo.setPosition(this.position);
        hwMediaInfo.setVolume(this.volume);
        hwMediaInfo.setExtendObj(this.extendObj);
        return hwMediaInfo;
    }
}
