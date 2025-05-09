package com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload;

/* loaded from: classes4.dex */
public interface OnPhotoSelectChangedListener<T> {
    boolean isMaxCount();

    void onPictureAdd(T t);

    void onPictureClick(int i, T t);

    void onPictureRemove(T t);
}
