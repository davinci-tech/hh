package com.huawei.hianalytics.core.storage;

import java.util.List;

/* loaded from: classes4.dex */
public interface IStorageHandler {
    boolean checkAndClearTable();

    void deleteAll();

    void deleteByTag(String str);

    void deleteByTagType(String str, String str2);

    void deleteCommonHeaderExAll();

    void deleteEvents(List<Event> list);

    int deleteMcInfo(List<String> list);

    int deleteMcTag(List<String> list);

    long insert(CommonHeaderEx commonHeaderEx);

    long insert(Event event);

    long insertMcInfo(String str);

    long insertMcTagList(List<String> list);

    List<String> queryMcInfo();

    List<String> queryMcTag(String str);

    CommonHeaderEx readCommonHeaderEx(String str);

    long readEventSize(String str);

    long readEventSize(String str, String str2);

    List<Event> readEvents(String str, String str2);

    long readEventsAllSize();

    List<Event> readOldEvents(String str, String str2);
}
