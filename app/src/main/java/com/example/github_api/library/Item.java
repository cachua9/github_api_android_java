package com.example.github_api.library;

public class Item {
    private String name;
    private String ownerIconUrl;
    private String language;
    private long stargazersCount;
    private long watchersCount;
    private long forksCount;
    private long openIssuesCount;

    public Item(String name, String ownerIconUrl, String language, long stargazersCount, long watchersCount, long forksCount, long openIssuesCount) {
        this.name = name;
        this.ownerIconUrl = ownerIconUrl;
        this.language = language;
        this.stargazersCount = stargazersCount;
        this.watchersCount = watchersCount;
        this.forksCount = forksCount;
        this.openIssuesCount = openIssuesCount;
    }

    public String getName() {
        return name;
    }

    public String getOwnerIconUrl() {
        return ownerIconUrl;
    }

    public String getLanguage() {
        return language;
    }

    public long getStargazersCount() {
        return stargazersCount;
    }

    public long getWatchersCount() {
        return watchersCount;
    }

    public long getForksCount() {
        return forksCount;
    }

    public long getOpenIssuesCount() {
        return openIssuesCount;
    }
}
