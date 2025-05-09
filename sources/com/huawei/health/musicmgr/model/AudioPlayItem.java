package com.huawei.health.musicmgr.model;

import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public abstract class AudioPlayItem {
    private static final String DURATION_KEY = "DURATION_KEY";
    private String mDescription;
    private long mDuration;
    private Bundle mExtras;
    private Uri mIconUri;
    private String mMediaId;
    private Uri mMediaUri;
    private String mSubtitle;
    private String mTitle;

    public abstract Bitmap getIcon();

    public static List<MediaSession.QueueItem> transferToQueueItem(List<AudioPlayItem> list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        long j = 0;
        for (AudioPlayItem audioPlayItem : list) {
            Bundle extras = audioPlayItem.getExtras();
            if (extras == null) {
                extras = new Bundle();
            }
            extras.putLong(DURATION_KEY, audioPlayItem.getDuration());
            arrayList.add(new MediaSession.QueueItem(new MediaDescription.Builder().setDescription(audioPlayItem.getDescription()).setMediaId(audioPlayItem.getMediaId()).setTitle(audioPlayItem.getTitle()).setSubtitle(audioPlayItem.getSubtitle()).setMediaUri(audioPlayItem.mMediaUri).setExtras(extras).build(), j));
            j++;
        }
        return arrayList;
    }

    public static List<AudioPlayItem> transferToAudioPlayItem(List<MediaSession.QueueItem> list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (MediaSession.QueueItem queueItem : list) {
            final Bitmap iconBitmap = queueItem.getDescription().getIconBitmap();
            AudioPlayItem audioPlayItem = new AudioPlayItem() { // from class: com.huawei.health.musicmgr.model.AudioPlayItem.3
                @Override // com.huawei.health.musicmgr.model.AudioPlayItem
                public Bitmap getIcon() {
                    return iconBitmap;
                }
            };
            MediaDescription description = queueItem.getDescription();
            audioPlayItem.setDescription((String) description.getDescription());
            if (description.getExtras() != null) {
                audioPlayItem.setDuration(description.getExtras().getLong(DURATION_KEY));
            }
            audioPlayItem.setExtras(description.getExtras());
            audioPlayItem.setMediaId(description.getMediaId());
            audioPlayItem.setIconUri(description.getIconUri());
            audioPlayItem.setSubtitle((String) description.getSubtitle());
            audioPlayItem.setTitle((String) description.getTitle());
            arrayList.add(audioPlayItem);
        }
        return arrayList;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public String getMediaId() {
        return this.mMediaId;
    }

    public void setMediaId(String str) {
        this.mMediaId = str;
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    public void setSubtitle(String str) {
        this.mSubtitle = str;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public Uri getIconUri() {
        return this.mIconUri;
    }

    public void setIconUri(Uri uri) {
        this.mIconUri = uri;
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    public void setExtras(Bundle bundle) {
        this.mExtras = bundle;
    }

    public Uri getMediaUri() {
        return this.mMediaUri;
    }

    public void setMediaUri(Uri uri) {
        this.mMediaUri = uri;
    }

    public long getDuration() {
        return this.mDuration;
    }

    public void setDuration(long j) {
        this.mDuration = j;
    }

    public int hashCode() {
        return this.mMediaId.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AudioPlayItem)) {
            return super.equals(obj);
        }
        return ((AudioPlayItem) obj).mMediaId.equals(this.mMediaId);
    }
}
