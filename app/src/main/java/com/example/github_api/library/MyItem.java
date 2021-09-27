package com.example.github_api.library;

import android.os.Parcel;
import android.os.Parcelable;

public class MyItem implements Parcelable {
    private String name;
    private String ownerIconUrl;
    private String language;
    private Long stargazersCount;
    private Long watchersCount;
    private Long forksCount;
    private Long openIssuesCount;

    public MyItem(String name, String ownerIconUrl, String language, Long stargazersCount, Long watchersCount, Long forksCount, Long openIssuesCount) {
        this.name = name;
        this.ownerIconUrl = ownerIconUrl;
        this.language = language;
        this.stargazersCount = stargazersCount;
        this.watchersCount = watchersCount;
        this.forksCount = forksCount;
        this.openIssuesCount = openIssuesCount;
    }

    protected MyItem(Parcel in) {
        name = in.readString();
        ownerIconUrl = in.readString();
        language = in.readString();
        if (in.readByte() == 0) {
            stargazersCount = null;
        } else {
            stargazersCount = in.readLong();
        }
        if (in.readByte() == 0) {
            watchersCount = null;
        } else {
            watchersCount = in.readLong();
        }
        if (in.readByte() == 0) {
            forksCount = null;
        } else {
            forksCount = in.readLong();
        }
        if (in.readByte() == 0) {
            openIssuesCount = null;
        } else {
            openIssuesCount = in.readLong();
        }
    }

    public static final Creator<MyItem> CREATOR = new Creator<MyItem>() {
        @Override
        public MyItem createFromParcel(Parcel in) {
            return new MyItem(in);
        }

        @Override
        public MyItem[] newArray(int size) {
            return new MyItem[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getOwnerIconUrl() {
        return ownerIconUrl;
    }

    public String getLanguage() {
        return language;
    }

    public Long getStargazersCount() {
        return stargazersCount;
    }

    public Long getWatchersCount() {
        return watchersCount;
    }

    public Long getForksCount() {
        return forksCount;
    }

    public Long getOpenIssuesCount() {
        return openIssuesCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(ownerIconUrl);
        dest.writeString(language);
        if (stargazersCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(stargazersCount);
        }
        if (watchersCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(watchersCount);
        }
        if (forksCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(forksCount);
        }
        if (openIssuesCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(openIssuesCount);
        }
    }
}
