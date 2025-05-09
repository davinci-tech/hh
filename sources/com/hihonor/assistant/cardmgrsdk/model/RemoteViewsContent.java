package com.hihonor.assistant.cardmgrsdk.model;

import android.widget.RemoteViews;
import java.util.List;

@Deprecated
/* loaded from: classes8.dex */
public class RemoteViewsContent {
    public List<ClickPendingIntent> mClickPendingIntents;
    public RemoteViews mRemoteViews;

    public RemoteViews getRremoteViews() {
        return this.mRemoteViews;
    }

    public List<ClickPendingIntent> getLlickPendingIntents() {
        return this.mClickPendingIntents;
    }

    public RemoteViewsContent(RemoteViews remoteViews, List<ClickPendingIntent> list) {
        this.mRemoteViews = remoteViews;
        this.mClickPendingIntents = list;
    }
}
