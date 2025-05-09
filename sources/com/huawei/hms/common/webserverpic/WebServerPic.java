package com.huawei.hms.common.webserverpic;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Locale;

/* loaded from: classes9.dex */
public class WebServerPic {
    public static final Parcelable.Creator<WebServerPic> CREATOR = new WebServerPicCreator();

    /* renamed from: a, reason: collision with root package name */
    private final Uri f4479a;
    private final int b;
    private final int c;

    public WebServerPic(Uri uri, int i, int i2) throws IllegalArgumentException {
        this.f4479a = uri;
        this.b = i;
        this.c = i2;
        if (uri == null) {
            throw new IllegalArgumentException("url is not able to be null");
        }
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("width and height should be positive or 0");
        }
    }

    public final int getHeight() {
        return this.c;
    }

    public final Uri getUrl() {
        return this.f4479a;
    }

    public final int getWidth() {
        return this.b;
    }

    public final String toString() {
        return String.format(Locale.ENGLISH, "Image %dx%d %s", Integer.valueOf(this.b), Integer.valueOf(this.c), this.f4479a.toString());
    }

    public final void writeToParcel(Parcel parcel, int i) {
        Preconditions.checkNotNull(parcel);
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getUrl(), i, false);
        SafeParcelWriter.writeInt(parcel, 2, getWidth());
        SafeParcelWriter.writeInt(parcel, 3, getHeight());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public WebServerPic(Uri uri) throws IllegalArgumentException {
        this(uri, 0, 0);
    }
}
