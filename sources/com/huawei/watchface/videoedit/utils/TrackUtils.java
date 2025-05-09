package com.huawei.watchface.videoedit.utils;

import com.huawei.watchface.videoedit.param.Segments;
import com.huawei.watchface.videoedit.param.TemplateModel;
import com.huawei.watchface.videoedit.param.Tracks;
import com.huawei.watchface.videoedit.sysc.Function;
import com.huawei.watchface.videoedit.sysc.Optional;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class TrackUtils {
    private static final String AUDIO_TRACK_TYPE = "audio";
    private static final String VIDEO_TRACK_TYPE = "video";

    private TrackUtils() {
    }

    public static List<Segments> getVideoSegments(TemplateModel templateModel) {
        return (List) getVideoTracks(templateModel).map(new Function() { // from class: com.huawei.watchface.videoedit.utils.TrackUtils$$ExternalSyntheticLambda1
            @Override // com.huawei.watchface.videoedit.sysc.Function
            public final Object apply(Object obj) {
                return ((Tracks) obj).getSegments();
            }
        }).orElse(new ArrayList(0));
    }

    public static Optional<Tracks> getVideoTracks(TemplateModel templateModel) {
        return Optional.ofNullable(templateModel).flatMap(new Function() { // from class: com.huawei.watchface.videoedit.utils.TrackUtils$$ExternalSyntheticLambda0
            @Override // com.huawei.watchface.videoedit.sysc.Function
            public final Object apply(Object obj) {
                Optional videoTracks;
                videoTracks = TrackUtils.getVideoTracks(((TemplateModel) obj).getTracks());
                return videoTracks;
            }
        });
    }

    public static Optional<Tracks> getVideoTracks(List<Tracks> list) {
        return getTracksFromType(list, "video");
    }

    private static Optional<Tracks> getTracksFromType(List<Tracks> list, String str) {
        if (ListUtil.isEmpty(list)) {
            return Optional.empty();
        }
        for (Tracks tracks : list) {
            if (str.equals(tracks.getType())) {
                return Optional.of(tracks);
            }
        }
        return Optional.empty();
    }
}
