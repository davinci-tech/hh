package com.huawei.phoneservice.feedback.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.phoneservice.feedbackcommon.entity.FeedbackInfo;
import com.huawei.phoneservice.feedbackcommon.photolibrary.internal.entity.MediaItem;
import java.util.List;

/* loaded from: classes5.dex */
public class FeedbackBean implements Parcelable {
    public static final Parcelable.Creator<FeedbackBean> CREATOR = new c();
    private FeedbackInfo info;
    private List<MediaItem> medias;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.medias);
        parcel.writeParcelable(this.info, i);
    }

    public void setZipFileName(String str) {
        this.info.setZipFileName(str);
    }

    public void setUniqueCode(String str) {
        this.info.setUniqueCode(str);
    }

    public void setSrCode(String str) {
        this.info.setSrCode(str);
    }

    public void setShowLog(boolean z) {
        this.info.setShowLog(z);
    }

    public void setProblemType(String str, String str2) {
        this.info.setProblemType(str, str2);
    }

    public void setProblemName(String str) {
        this.info.setProblemName(str);
    }

    public void setProblemId(String str) {
        this.info.setProblemId(str);
    }

    public void setProblemDesc(String str) {
        this.info.setProblemDesc(str);
    }

    public void setParentId(String str) {
        this.info.setParentId(str);
    }

    public void setMedias(List<MediaItem> list) {
        this.medias = list;
    }

    public void setLogsSize(long j) {
        this.info.setLogsSize(j);
    }

    public void setInfo(FeedbackInfo feedbackInfo) {
        this.info = feedbackInfo;
    }

    public void setFlag(int i) {
        this.info.setFlag(i);
    }

    public void setFilesSize(long j) {
        this.info.setFilesSize(j);
    }

    public void setContact(String str) {
        this.info.setContact(str);
    }

    public void setChildId(String str) {
        this.info.setChildId(str);
    }

    public void setAssociatedId(long j) {
        this.info.setAssociatedId(j);
    }

    public boolean remove(MediaItem mediaItem) {
        return this.medias.remove(mediaItem);
    }

    public MediaItem remove(int i) {
        return this.medias.remove(i);
    }

    public boolean haveMedias() {
        return !com.huawei.phoneservice.faq.base.util.b.b(this.medias);
    }

    public String getZipFileName() {
        return this.info.getZipFileName();
    }

    public String getUniqueCode() {
        return this.info.getUniqueCode();
    }

    public String getSrCode() {
        return this.info.getSrCode();
    }

    public boolean getShowLog() {
        return this.info.getShowLog();
    }

    public String getProblemType() {
        return this.info.getProblemType();
    }

    public String getProblemName() {
        return this.info.getProblemName();
    }

    public String getProblemId() {
        return this.info.getProblemId();
    }

    public String getProblemDesc() {
        return this.info.getProblemDesc();
    }

    public String getParentId() {
        return this.info.getParentId();
    }

    public List<MediaItem> getMedias() {
        return this.medias;
    }

    public MediaItem getMediaItem(int i) {
        List<MediaItem> list = this.medias;
        if (list == null || i < 0 || i >= list.size()) {
            return null;
        }
        return this.medias.get(i);
    }

    public long getLogsSize() {
        return this.info.getLogsSize();
    }

    public FeedbackInfo getInfo() {
        return this.info;
    }

    public int getFlag() {
        return this.info.getFlag();
    }

    public long getFilesSize() {
        return this.info.getFilesSize();
    }

    public String getContact() {
        return this.info.getContact();
    }

    public String getChildId() {
        return this.info.getChildId();
    }

    public long getAssociatedId() {
        return this.info.getAssociatedId();
    }

    public FeedbackBean(String str, String str2, String str3, String str4, String str5) {
        this.info = new FeedbackInfo(str, str2, str3, str4, str5);
    }

    class c implements Parcelable.Creator<FeedbackBean> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public FeedbackBean[] newArray(int i) {
            return new FeedbackBean[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public FeedbackBean createFromParcel(Parcel parcel) {
            return new FeedbackBean(parcel);
        }

        c() {
        }
    }

    public FeedbackBean(String str, String str2, String str3, String str4) {
        this.info = new FeedbackInfo(str, str2, str3, str4);
    }

    protected FeedbackBean(Parcel parcel) {
        this.medias = parcel.createTypedArrayList(MediaItem.CREATOR);
        this.info = (FeedbackInfo) parcel.readParcelable(FeedbackInfo.class.getClassLoader());
    }

    public FeedbackBean() {
        this.info = new FeedbackInfo();
    }
}
