package com.huawei.openalliance.ad.utils;

import com.huawei.openalliance.ad.beans.metadata.AdSource;
import com.huawei.openalliance.ad.beans.metadata.ContentExt;
import com.huawei.openalliance.ad.beans.metadata.DefaultTemplate;
import com.huawei.openalliance.ad.beans.metadata.ImpEX;
import com.huawei.openalliance.ad.beans.metadata.InstallConfig;
import com.huawei.openalliance.ad.beans.metadata.LandpageAppWhiteList;
import com.huawei.openalliance.ad.beans.metadata.LandpageWebBlackList;
import com.huawei.openalliance.ad.beans.metadata.Monitor;
import com.huawei.openalliance.ad.beans.metadata.NegativeFb;
import com.huawei.openalliance.ad.beans.metadata.Om;
import com.huawei.openalliance.ad.beans.metadata.PromoteInfo;
import com.huawei.openalliance.ad.beans.metadata.v3.Asset;
import com.huawei.openalliance.ad.beans.metadata.v3.MotionData;
import com.huawei.openalliance.ad.beans.metadata.v3.TemplateData;
import com.huawei.openalliance.ad.beans.metadata.v3.openrtb.Data;
import com.huawei.openalliance.ad.beans.metadata.v3.openrtb.Image;
import com.huawei.openalliance.ad.beans.metadata.v3.openrtb.ImageExt;
import com.huawei.openalliance.ad.beans.metadata.v3.openrtb.Title;
import com.huawei.openalliance.ad.beans.metadata.v3.openrtb.Video;
import com.huawei.openalliance.ad.inter.data.AdvertiserInfo;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.data.AudioInfo;
import com.huawei.openalliance.ad.inter.data.BiddingInfo;
import com.huawei.openalliance.ad.inter.data.FeedbackInfo;
import com.huawei.openalliance.ad.inter.data.ImageInfo;
import com.huawei.openalliance.ad.inter.data.PermissionEntity;
import com.huawei.openalliance.ad.inter.data.VideoInfo;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes5.dex */
public class cq extends ObjectInputStream {

    /* renamed from: a, reason: collision with root package name */
    private static final Class[] f7666a = {String.class, ArrayList.class, CopyOnWriteArrayList.class, LinkedList.class, HashMap.class, HashSet.class, Integer.class, Float.class, Boolean.class, Long.class, Byte.class, Character.class, Short.class, Double.class, Number.class, int[].class, double[].class, boolean[].class, short[].class, long[].class, byte[].class, float[].class, ImageInfo.class, VideoInfo.class, AppInfo.class, PermissionEntity.class, com.huawei.openalliance.ad.inter.data.e.class, LandpageWebBlackList.class, LandpageAppWhiteList.class, InstallConfig.class, ImpEX.class, Om.class, AudioInfo.class, ContentExt.class, NegativeFb.class, FeedbackInfo.class, AdSource.class, Asset.class, com.huawei.openalliance.ad.inter.data.a.class, Data.class, Image.class, ImageExt.class, Video.class, Title.class, TemplateData.class, MotionData.class, AdvertiserInfo.class, DefaultTemplate.class, Monitor.class, PromoteInfo.class, BiddingInfo.class};

    @Override // java.io.ObjectInputStream
    protected Class<?> resolveClass(ObjectStreamClass objectStreamClass) {
        for (Class cls : f7666a) {
            if (cls.getName().equals(objectStreamClass.getName())) {
                return super.resolveClass(objectStreamClass);
            }
        }
        throw new ClassNotFoundException("Class " + objectStreamClass.getName() + " is not allowed!");
    }

    public cq(InputStream inputStream) {
        super(inputStream);
    }
}
