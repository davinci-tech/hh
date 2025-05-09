package com.google.protobuf;

import com.google.protobuf.MapField;
import com.google.protobuf.MessageOrBuilder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class MapFieldBuilder<KeyT, MessageOrBuilderT extends MessageOrBuilder, MessageT extends MessageOrBuilderT, BuilderT extends MessageOrBuilderT> extends MapFieldReflectionAccessor {
    Converter<KeyT, MessageOrBuilderT, MessageT> converter;
    Map<KeyT, MessageOrBuilderT> builderMap = new LinkedHashMap();
    Map<KeyT, MessageT> messageMap = null;
    List<Message> messageList = null;

    public interface Converter<KeyT, MessageOrBuilderT extends MessageOrBuilder, MessageT extends MessageOrBuilderT> {
        /* JADX WARN: Incorrect return type in method signature: (TMessageOrBuilderT;)TMessageT; */
        MessageOrBuilder build(MessageOrBuilder messageOrBuilder);

        MapEntry<KeyT, MessageT> defaultEntry();
    }

    public MapFieldBuilder(Converter<KeyT, MessageOrBuilderT, MessageT> converter) {
        this.converter = converter;
    }

    private List<MapEntry<KeyT, MessageT>> getMapEntryList() {
        ArrayList arrayList = new ArrayList(this.messageList.size());
        Class<?> cls = ((MessageOrBuilder) this.converter.defaultEntry().getValue()).getClass();
        for (Message message : this.messageList) {
            MapEntry mapEntry = (MapEntry) message;
            if (cls.isInstance(mapEntry.getValue())) {
                arrayList.add(mapEntry);
            } else {
                arrayList.add(this.converter.defaultEntry().toBuilder().mergeFrom(message).build());
            }
        }
        return arrayList;
    }

    public Map<KeyT, MessageOrBuilderT> ensureBuilderMap() {
        Map<KeyT, MessageOrBuilderT> map = this.builderMap;
        if (map != null) {
            return map;
        }
        if (this.messageMap != null) {
            this.builderMap = new LinkedHashMap(this.messageMap.size());
            for (Map.Entry<KeyT, MessageT> entry : this.messageMap.entrySet()) {
                this.builderMap.put(entry.getKey(), (MessageOrBuilder) entry.getValue());
            }
            this.messageMap = null;
            return this.builderMap;
        }
        this.builderMap = new LinkedHashMap(this.messageList.size());
        for (MapEntry<KeyT, MessageT> mapEntry : getMapEntryList()) {
            this.builderMap.put(mapEntry.getKey(), (MessageOrBuilder) mapEntry.getValue());
        }
        this.messageList = null;
        return this.builderMap;
    }

    public List<Message> ensureMessageList() {
        List<Message> list = this.messageList;
        if (list != null) {
            return list;
        }
        if (this.builderMap != null) {
            this.messageList = new ArrayList(this.builderMap.size());
            for (Map.Entry<KeyT, MessageOrBuilderT> entry : this.builderMap.entrySet()) {
                this.messageList.add(this.converter.defaultEntry().toBuilder().setKey(entry.getKey()).setValue(this.converter.build(entry.getValue())).build());
            }
            this.builderMap = null;
            return this.messageList;
        }
        this.messageList = new ArrayList(this.messageMap.size());
        for (Map.Entry<KeyT, MessageT> entry2 : this.messageMap.entrySet()) {
            this.messageList.add(this.converter.defaultEntry().toBuilder().setKey(entry2.getKey()).setValue((MessageOrBuilder) entry2.getValue()).build());
        }
        this.messageMap = null;
        return this.messageList;
    }

    public Map<KeyT, MessageT> ensureMessageMap() {
        Map<KeyT, MessageT> populateMutableMap = populateMutableMap();
        this.messageMap = populateMutableMap;
        this.builderMap = null;
        this.messageList = null;
        return populateMutableMap;
    }

    public Map<KeyT, MessageT> getImmutableMap() {
        return new MapField.MutabilityAwareMap(MutabilityOracle.IMMUTABLE, populateMutableMap());
    }

    private Map<KeyT, MessageT> populateMutableMap() {
        Map<KeyT, MessageT> map = this.messageMap;
        if (map != null) {
            return map;
        }
        if (this.builderMap != null) {
            LinkedHashMap linkedHashMap = new LinkedHashMap(this.builderMap.size());
            for (Map.Entry<KeyT, MessageOrBuilderT> entry : this.builderMap.entrySet()) {
                linkedHashMap.put(entry.getKey(), this.converter.build(entry.getValue()));
            }
            return linkedHashMap;
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(this.messageList.size());
        for (MapEntry<KeyT, MessageT> mapEntry : getMapEntryList()) {
            linkedHashMap2.put(mapEntry.getKey(), (MessageOrBuilder) mapEntry.getValue());
        }
        return linkedHashMap2;
    }

    public void mergeFrom(MapField<KeyT, MessageT> mapField) {
        ensureBuilderMap().putAll(MapFieldLite.copy((Map) mapField.getMap()));
    }

    public void clear() {
        this.builderMap = new LinkedHashMap();
        this.messageMap = null;
        this.messageList = null;
    }

    private boolean typedEquals(MapFieldBuilder<KeyT, MessageOrBuilderT, MessageT, BuilderT> mapFieldBuilder) {
        return MapFieldLite.equals((Map) ensureBuilderMap(), (Map) mapFieldBuilder.ensureBuilderMap());
    }

    public boolean equals(Object obj) {
        if (obj instanceof MapFieldBuilder) {
            return typedEquals((MapFieldBuilder) obj);
        }
        return false;
    }

    public int hashCode() {
        return MapFieldLite.calculateHashCodeForMap(ensureBuilderMap());
    }

    public MapFieldBuilder<KeyT, MessageOrBuilderT, MessageT, BuilderT> copy() {
        MapFieldBuilder<KeyT, MessageOrBuilderT, MessageT, BuilderT> mapFieldBuilder = new MapFieldBuilder<>(this.converter);
        mapFieldBuilder.ensureBuilderMap().putAll(ensureBuilderMap());
        return mapFieldBuilder;
    }

    public MapField<KeyT, MessageT> build(MapEntry<KeyT, MessageT> mapEntry) {
        MapField<KeyT, MessageT> newMapField = MapField.newMapField(mapEntry);
        Map<KeyT, MessageT> mutableMap = newMapField.getMutableMap();
        for (Map.Entry<KeyT, MessageOrBuilderT> entry : ensureBuilderMap().entrySet()) {
            mutableMap.put(entry.getKey(), this.converter.build(entry.getValue()));
        }
        newMapField.makeImmutable();
        return newMapField;
    }

    @Override // com.google.protobuf.MapFieldReflectionAccessor
    List<Message> getList() {
        return ensureMessageList();
    }

    @Override // com.google.protobuf.MapFieldReflectionAccessor
    List<Message> getMutableList() {
        return ensureMessageList();
    }

    @Override // com.google.protobuf.MapFieldReflectionAccessor
    Message getMapEntryMessageDefaultInstance() {
        return this.converter.defaultEntry();
    }
}
