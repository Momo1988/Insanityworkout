package com.example.zhangli.insanityworkout.db;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhangli on 16/4/20.
 */
public class ItemData implements Parcelable {
    private int ID;
    private String DAYITEM;
    private String EVENT;
    private String ISCOMPLETED;
    private String COMPLETE_TIME;

    public int getId() {
        return this.ID;
    }

    public void setId(int ID) {
        this.ID = ID;
    }

    public String getDay() {
        return this.DAYITEM;
    }

    public void setDay(String DAYITEM) {
        this.DAYITEM = DAYITEM;
    }

    public String getContent() {
        return this.EVENT;
    }

    public void setContent(String EVENT) {
        this.EVENT = EVENT;
    }

    public String getIscomplete() {
        return this.ISCOMPLETED;
    }

    public void setIscomplete(String ISCOMPLETED) {
        this.ISCOMPLETED = ISCOMPLETED;
    }

    public String getTime() {
        return this.COMPLETE_TIME;
    }

    public void setTime(String COMPLETE_TIME) {
        this.COMPLETE_TIME = COMPLETE_TIME;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeString(DAYITEM);
        dest.writeString(EVENT);
        dest.writeString(ISCOMPLETED);
        dest.writeString(COMPLETE_TIME);
    }

    public static final Parcelable.Creator<ItemData> CREATOR = new Parcelable. Creator<ItemData>() {
        @Override
        public ItemData createFromParcel(Parcel source) {
            ItemData itemData = new ItemData();
            itemData.ID = source.readInt();
            itemData.DAYITEM = source.readString();
            itemData.EVENT = source.readString();
            itemData.ISCOMPLETED = source.readString();
            itemData.COMPLETE_TIME = source.readString();
            // 读取name person.age = source.readInt(); // 读取age
            return itemData;
        }


        @Override
        public ItemData[] newArray(int size) {
            return new ItemData[size];
        }
    };


}
