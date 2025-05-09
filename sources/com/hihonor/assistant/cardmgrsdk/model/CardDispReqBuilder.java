package com.hihonor.assistant.cardmgrsdk.model;

import android.net.Uri;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes2.dex */
public class CardDispReqBuilder {
    public static final int MAX_IMAGE_SIZE = 307200;
    public final String business;
    public final String businessId;
    public String businessParams;

    @Deprecated
    public RemoteViews cardRemoteViews;
    public ArrayList<RemoteViewsDescriptor> cardRemoteViewsList;
    public List<ClickPendingIntent> clickPendingIntents;
    public String detailType;
    public long endTime;
    public HashMap<String, String> extras;
    public List<ImageEntity> imageEntities;
    public ArrayList<ImageDescriptor> imageParcelList;

    @Deprecated
    public int operation;
    public String type;
    public long versionCode;
    public String viewData;
    public String widgetPackage;

    public CardDispReqBuilder widgetPackage(String str) {
        this.widgetPackage = str;
        return this;
    }

    public CardDispReqBuilder viewData(String str) {
        this.viewData = str;
        return this;
    }

    public CardDispReqBuilder versionCode(long j) {
        this.versionCode = j;
        return this;
    }

    public CardDispReqBuilder type(String str) {
        this.type = str;
        return this;
    }

    public CardDispReqBuilder setWidgetClass(String str) {
        this.extras.put("widgetClass", str);
        return this;
    }

    public CardDispReqBuilder setTitle(String str) {
        this.extras.put("title", str);
        return this;
    }

    public CardDispReqBuilder setSessionId(String str) {
        this.extras.put("sessionId", str);
        return this;
    }

    @Deprecated
    public CardDispReqBuilder operation(int i) {
        this.operation = i;
        return this;
    }

    public CardDispReqBuilder imageEntities(List<ImageEntity> list) {
        this.imageEntities = list;
        return this;
    }

    public String getWidgetPackage() {
        return this.widgetPackage;
    }

    public String getWidgetClass() {
        if (this.extras == null) {
            this.extras = new HashMap<>();
        }
        return this.extras.get("widgetClass");
    }

    public String getViewData() {
        return this.viewData;
    }

    public long getVersionCode() {
        return this.versionCode;
    }

    public String getType() {
        return this.type;
    }

    public String getTitle() {
        if (this.extras == null) {
            this.extras = new HashMap<>();
        }
        return this.extras.get("title");
    }

    public String getSessionId() {
        if (this.extras == null) {
            this.extras = new HashMap<>();
        }
        return this.extras.get("sessionId");
    }

    public int getOperation() {
        return this.operation;
    }

    public List<ImageEntity> getImageEntities() {
        return this.imageEntities;
    }

    public ArrayList<ImageDescriptor> getImageDescriptorList() {
        return this.imageParcelList;
    }

    public HashMap<String, String> getExtras() {
        return this.extras;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public String getDetailType() {
        return this.detailType;
    }

    public List<ClickPendingIntent> getClickPendingIntents() {
        return this.clickPendingIntents;
    }

    public ArrayList<RemoteViewsDescriptor> getCardRemoteViewsList() {
        return this.cardRemoteViewsList;
    }

    @Deprecated
    public RemoteViews getCardRemoteViews() {
        return this.cardRemoteViews;
    }

    public String getBusinessParams() {
        return this.businessParams;
    }

    public String getBusinessId() {
        return this.businessId;
    }

    public String getBusiness() {
        return this.business;
    }

    public CardDispReqBuilder extras(HashMap<String, String> hashMap) {
        if (hashMap == null) {
            hashMap = new HashMap<>();
        }
        this.extras = hashMap;
        return this;
    }

    public CardDispReqBuilder endTime(long j) {
        this.endTime = j;
        return this;
    }

    public CardDispReqBuilder detailType(String str) {
        this.detailType = str;
        return this;
    }

    public CardDispReqBuilder clickPendingIntents(List<ClickPendingIntent> list) {
        this.clickPendingIntents = list;
        return this;
    }

    public CardDispReqBuilder cardRemoteViewsList(ArrayList<RemoteViewsDescriptor> arrayList) {
        this.cardRemoteViewsList = arrayList;
        return this;
    }

    @Deprecated
    public CardDispReqBuilder cardRemoteViews(RemoteViews remoteViews) {
        this.cardRemoteViews = remoteViews;
        return this;
    }

    public CardDispReqBuilder businessParams(String str) {
        this.businessParams = str;
        return this;
    }

    public CardDisplayRequestMultiArgs buildMulti() {
        return new CardDisplayRequestMultiArgs(this);
    }

    @Deprecated
    public CardDisplayRequestArg build() {
        return new CardDisplayRequestArg(this);
    }

    public CardDispReqBuilder addImage(int i, byte[] bArr, String str) {
        if (this.imageParcelList == null) {
            this.imageParcelList = new ArrayList<>();
        }
        if (bArr.length > 307200) {
            throw new OutOfMemoryError("image size may call oom");
        }
        ImageDescriptor imageDescriptor = new ImageDescriptor();
        imageDescriptor.setViewId(i);
        imageDescriptor.setImageBytes(bArr);
        imageDescriptor.setSizeDescriptor(str);
        this.imageParcelList.add(imageDescriptor);
        return this;
    }

    public CardDispReqBuilder addImage(int i, byte[] bArr) {
        if (this.imageParcelList == null) {
            this.imageParcelList = new ArrayList<>();
        }
        if (bArr.length > 307200) {
            throw new OutOfMemoryError("image size may call oom");
        }
        ImageDescriptor imageDescriptor = new ImageDescriptor();
        imageDescriptor.setViewId(i);
        imageDescriptor.setImageBytes(bArr);
        imageDescriptor.setSizeDescriptor("normal");
        this.imageParcelList.add(imageDescriptor);
        return this;
    }

    public CardDispReqBuilder addImage(int i, Uri uri, String str) {
        if (this.imageParcelList == null) {
            this.imageParcelList = new ArrayList<>();
        }
        ImageDescriptor imageDescriptor = new ImageDescriptor();
        imageDescriptor.setViewId(i);
        imageDescriptor.setImageUri(uri);
        imageDescriptor.setSizeDescriptor(str);
        this.imageParcelList.add(imageDescriptor);
        return this;
    }

    public CardDispReqBuilder addImage(int i, Uri uri) {
        if (this.imageParcelList == null) {
            this.imageParcelList = new ArrayList<>();
        }
        ImageDescriptor imageDescriptor = new ImageDescriptor();
        imageDescriptor.setViewId(i);
        imageDescriptor.setImageUri(uri);
        imageDescriptor.setSizeDescriptor("normal");
        this.imageParcelList.add(imageDescriptor);
        return this;
    }

    public CardDispReqBuilder addCardRemoteViews(RemoteViews remoteViews, String str) {
        if (this.cardRemoteViewsList == null) {
            this.cardRemoteViewsList = new ArrayList<>();
        }
        RemoteViewsDescriptor remoteViewsDescriptor = new RemoteViewsDescriptor(remoteViews);
        remoteViewsDescriptor.sizeDescribe = str;
        this.cardRemoteViewsList.add(remoteViewsDescriptor);
        return this;
    }

    public CardDispReqBuilder addCardRemoteViews(RemoteViews remoteViews) {
        if (this.cardRemoteViewsList == null) {
            this.cardRemoteViewsList = new ArrayList<>();
        }
        RemoteViewsDescriptor remoteViewsDescriptor = new RemoteViewsDescriptor(remoteViews);
        remoteViewsDescriptor.sizeDescribe = "normal";
        this.cardRemoteViewsList.add(remoteViewsDescriptor);
        return this;
    }

    public CardDispReqBuilder(String str, String str2) {
        this.extras = new HashMap<>();
        this.businessId = str2;
        this.business = str;
    }

    public CardDispReqBuilder(CardDisplayRequestArg cardDisplayRequestArg) {
        this(cardDisplayRequestArg.getBusiness(), cardDisplayRequestArg.getBusinessId());
        operation(cardDisplayRequestArg.getOperation());
        businessParams(cardDisplayRequestArg.getBusinessParams());
        detailType(cardDisplayRequestArg.getDetailType());
        endTime(cardDisplayRequestArg.getEndTime());
        type(cardDisplayRequestArg.getType());
        widgetPackage(cardDisplayRequestArg.getWidgetPackage());
        cardRemoteViews(cardDisplayRequestArg.getCardRemoteViews());
        viewData(cardDisplayRequestArg.getViewData());
        versionCode(cardDisplayRequestArg.getVersionCode());
        clickPendingIntents(cardDisplayRequestArg.getClickPendingIntents());
        imageEntities(cardDisplayRequestArg.getImageEntities());
        extras(cardDisplayRequestArg.getExtras());
    }
}
