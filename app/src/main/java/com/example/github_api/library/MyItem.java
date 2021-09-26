package com.example.github_api.library;

public class MyItem {
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
}
