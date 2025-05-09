package com.huawei.profile.container;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.profile.utils.BaseUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes6.dex */
public class ObjectContainer<T> implements Container<T>, Parcelable {
    public static final Parcelable.Creator<ObjectContainer<?>> CREATOR = new Parcelable.Creator<ObjectContainer<?>>() { // from class: com.huawei.profile.container.ObjectContainer.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ObjectContainer<?> createFromParcel(Parcel parcel) {
            return new ObjectContainer<>(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ObjectContainer<?>[] newArray(int i) {
            return new ObjectContainer[i];
        }
    };
    private static final int HAS_VALUE = 1;
    private static final int NOT_HAS_VALUE = 0;
    private Class<T> clazz;
    private String databaseName;
    private String groupName;
    private List<T> objects;
    private String pkgName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected ObjectContainer(Parcel parcel) {
        Class<T> cls = (Class) BaseUtil.cast(parcel.readSerializable());
        this.clazz = cls;
        if (cls != null) {
            this.objects = (List) BaseUtil.cast(parcel.readArrayList(cls.getClassLoader()));
        } else {
            this.objects = Collections.emptyList();
        }
        if (parcel.readInt() == 1) {
            this.pkgName = parcel.readString();
        } else {
            this.pkgName = null;
        }
        if (parcel.readInt() == 1) {
            this.databaseName = parcel.readString();
        } else {
            this.databaseName = null;
        }
        if (parcel.readInt() == 1) {
            this.groupName = parcel.readString();
        } else {
            this.groupName = null;
        }
    }

    public ObjectContainer(Class<T> cls, List<T> list, String str, String str2, String str3) {
        this(cls, list, str, str2);
        this.groupName = str3;
    }

    public ObjectContainer(Class<T> cls, List<T> list, String str, String str2) {
        this(cls, list, str);
        this.databaseName = str2;
    }

    public ObjectContainer(Class<T> cls, List<T> list, String str) {
        this(cls, list);
        this.pkgName = str;
    }

    public ObjectContainer(Class<T> cls, List<T> list) {
        if (cls == null) {
            throw new IllegalArgumentException();
        }
        this.clazz = cls;
        this.objects = list;
    }

    public ObjectContainer(Class<T> cls) {
        this(cls, new ArrayList());
    }

    public String getPkgName() {
        return this.pkgName;
    }

    public String getDatabaseName() {
        return this.databaseName;
    }

    public String getGroupName() {
        return this.groupName;
    }

    @Override // com.huawei.profile.container.Container
    public boolean add(T t) {
        if (t != null) {
            return this.objects.add(t);
        }
        return false;
    }

    @Override // com.huawei.profile.container.Container
    public boolean remove(T t) {
        return this.objects.remove(t);
    }

    @Override // com.huawei.profile.container.Container
    public boolean delete(T t) {
        return remove(t);
    }

    @Override // com.huawei.profile.container.Container
    public void clear() {
        this.objects.clear();
    }

    public void clearObjects() {
        this.objects.clear();
    }

    public Class<T> type() {
        return this.clazz;
    }

    public List<T> get() {
        return this.objects;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this.clazz);
        parcel.writeList(this.objects);
        if (this.pkgName != null) {
            parcel.writeInt(1);
            parcel.writeString(this.pkgName);
        } else {
            parcel.writeInt(0);
        }
        if (this.databaseName != null) {
            parcel.writeInt(1);
            parcel.writeString(this.databaseName);
        } else {
            parcel.writeInt(0);
        }
        if (this.groupName != null) {
            parcel.writeInt(1);
            parcel.writeString(this.groupName);
        } else {
            parcel.writeInt(0);
        }
    }

    public ObjectContainer<T> readFromParcel(Parcel parcel) {
        return new ObjectContainer<>(parcel);
    }
}
