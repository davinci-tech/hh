package com.huawei.hms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.hms.common.Preconditions;
import com.huawei.hms.common.parcel.ParcelReader;
import com.huawei.hms.common.parcel.ParcelWrite;
import com.huawei.hms.maps.model.internal.ITileProviderDelegate;

/* loaded from: classes9.dex */
public class TileOverlayOptions implements Parcelable {
    public static final Parcelable.Creator<TileOverlayOptions> CREATOR = new Parcelable.Creator<TileOverlayOptions>() { // from class: com.huawei.hms.maps.model.TileOverlayOptions.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public TileOverlayOptions[] newArray(int i) {
            return new TileOverlayOptions[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public TileOverlayOptions createFromParcel(Parcel parcel) {
            return new TileOverlayOptions(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private float f5013a;
    private boolean b;
    private float c;
    private ITileProviderDelegate d;
    private boolean e;
    private TileProvider f;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public TileOverlayOptions zIndex(float f) {
        this.f5013a = f;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ParcelWrite parcelWrite = new ParcelWrite(parcel);
        int beginObjectHeader = parcelWrite.beginObjectHeader();
        parcelWrite.writeFloat(2, this.f5013a);
        parcelWrite.writeBoolean(3, this.b);
        parcelWrite.writeFloat(4, this.c);
        parcelWrite.writeIBinder(5, this.d.asBinder(), false);
        parcelWrite.writeBoolean(6, this.e);
        parcelWrite.finishObjectHeader(beginObjectHeader);
    }

    public TileOverlayOptions visible(boolean z) {
        this.b = z;
        return this;
    }

    public TileOverlayOptions transparency(float f) {
        Preconditions.checkArgument(f >= 0.0f, "Transparency should be equal or more than 0.0");
        Preconditions.checkArgument(f <= 1.0f, "Transparency should be equal or less than 1.0");
        this.c = f;
        return this;
    }

    public TileOverlayOptions tileProvider(TileProvider tileProvider) {
        this.f = tileProvider;
        this.d = tileProvider == null ? null : new ITileProviderDelegate.Stub() { // from class: com.huawei.hms.maps.model.TileOverlayOptions.3
            @Override // com.huawei.hms.maps.model.internal.ITileProviderDelegate
            public Tile getTile(int i, int i2, int i3) {
                return TileOverlayOptions.this.f.getTile(i, i2, i3);
            }
        };
        return this;
    }

    public boolean isVisible() {
        return this.b;
    }

    public float getZIndex() {
        return this.f5013a;
    }

    public float getTransparency() {
        return this.c;
    }

    public TileProvider getTileProvider() {
        return this.f;
    }

    public boolean getFadeIn() {
        return this.e;
    }

    public TileOverlayOptions fadeIn(boolean z) {
        this.e = z;
        return this;
    }

    protected TileOverlayOptions(Parcel parcel) {
        this.b = true;
        this.c = 0.0f;
        this.d = null;
        this.e = true;
        ParcelReader parcelReader = new ParcelReader(parcel);
        this.f5013a = parcelReader.readFloat(2, 0.0f);
        this.b = parcelReader.readBoolean(3, true);
        this.c = parcelReader.readFloat(4, 0.0f);
        IBinder readIBinder = parcelReader.readIBinder(5, null);
        if (readIBinder != null) {
            this.d = ITileProviderDelegate.Stub.asInterface(readIBinder);
        }
        this.e = parcelReader.readBoolean(6, true);
        this.f = new TileProvider() { // from class: com.huawei.hms.maps.model.TileOverlayOptions.2
            @Override // com.huawei.hms.maps.model.TileProvider
            public Tile getTile(int i, int i2, int i3) {
                try {
                    return TileOverlayOptions.this.d.getTile(i, i2, i3);
                } catch (RemoteException unused) {
                    return null;
                }
            }
        };
    }

    public TileOverlayOptions() {
        this.b = true;
        this.c = 0.0f;
        this.d = null;
        this.e = true;
    }
}
