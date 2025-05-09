package com.huawei.openalliance.ad.db.bean;

import com.huawei.openalliance.ad.annotations.e;
import com.huawei.openalliance.ad.beans.metadata.MediaFile;
import com.huawei.openalliance.ad.beans.metadata.Monitor;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes5.dex */
public class PlacementRecord extends a implements Serializable {
    public static final String CONTENT_ID = "contentId";
    private static final long serialVersionUID = 30422300;
    private List<Integer> clickActionList;
    private String clickUrl;
    private String contentId;
    private String ctrlSwitchs;
    private long endTime;
    private String fileCachePriority;
    private String intentUri;
    private String landPageType;
    private int landingTitle;
    private MediaFile mediaFile;

    @e
    private List<MediaFile> mediaFiles;
    private String metaData;
    private EncryptionField<List<Monitor>> monitors;
    private EncryptionField<String> parameter;
    private int placementAdType;
    private int recordcreativetype;
    private int recordinteractiontype;
    private int sequence;
    private String slotId;
    private long startTime;
    private String taskId;
    private String webConfig;
    private int precontentFlag = 0;

    @e
    private boolean autoDownloadApp = false;

    public void k(String str) {
        this.fileCachePriority = str;
    }

    public void j(String str) {
        this.landPageType = str;
    }

    public void i(String str) {
        this.clickUrl = str;
    }

    public void h(String str) {
        this.ctrlSwitchs = str;
    }

    public void g(String str) {
        this.webConfig = str;
    }

    public void f(String str) {
        this.taskId = str;
    }

    public void f(int i) {
        this.placementAdType = i;
    }

    public void e(String str) {
        this.metaData = str;
    }

    public void e(int i) {
        this.precontentFlag = i;
    }

    public void d(String str) {
        this.intentUri = str;
    }

    public void d(int i) {
        this.sequence = i;
    }

    public void c(List<MediaFile> list) {
        this.mediaFiles = list;
    }

    public void c(String str) {
        if (this.parameter == null) {
            this.parameter = new EncryptionField<>(String.class);
        }
        this.parameter.a((EncryptionField<String>) str);
    }

    public void c(int i) {
        this.landingTitle = i;
    }

    public void b(List<Integer> list) {
        this.clickActionList = list;
    }

    public void b(String str) {
        this.contentId = str;
    }

    public void b(long j) {
        this.endTime = j;
    }

    public void b(int i) {
        this.recordcreativetype = i;
    }

    public String b() {
        return this.metaData;
    }

    @Override // com.huawei.openalliance.ad.db.bean.a
    public String a_() {
        return "contentId";
    }

    public void a(List<Monitor> list) {
        EncryptionField<List<Monitor>> encryptionField = new EncryptionField<>(List.class, Monitor.class);
        encryptionField.a((EncryptionField<List<Monitor>>) list);
        this.monitors = encryptionField;
    }

    public void a(String str) {
        this.slotId = str;
    }

    public void a(MediaFile mediaFile) {
        this.mediaFile = mediaFile;
    }

    public void a(long j) {
        this.startTime = j;
    }

    public void a(int i) {
        this.recordinteractiontype = i;
    }

    public String a() {
        return this.contentId;
    }
}
