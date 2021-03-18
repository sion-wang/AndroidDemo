package com.dabenxiang.mvvm.model.api.vo.user

import com.google.gson.annotations.SerializedName

/**
{
    "login": "octocat",
    "id": 1,
    "node_id": "MDQ6VXNlcjE=",
    "avatar_url": "https://github.com/images/error/octocat_happy.gif",
    "gravatar_id": "",
    "url": "https://api.github.com/users/octocat",
    "html_url": "https://github.com/octocat",
    "followers_url": "https://api.github.com/users/octocat/followers",
    "following_url": "https://api.github.com/users/octocat/following{/other_user}",
    "gists_url": "https://api.github.com/users/octocat/gists{/gist_id}",
    "starred_url": "https://api.github.com/users/octocat/starred{/owner}{/repo}",
    "subscriptions_url": "https://api.github.com/users/octocat/subscriptions",
    "organizations_url": "https://api.github.com/users/octocat/orgs",
    "repos_url": "https://api.github.com/users/octocat/repos",
    "events_url": "https://api.github.com/users/octocat/events{/privacy}",
    "received_events_url": "https://api.github.com/users/octocat/received_events",
    "type": "User",
    "site_admin": false
}
*/

data class User(
    @SerializedName("login")
    val login: String = "",
    @SerializedName("id")
    val id: Long = 0,
    @SerializedName("node_id")
    val node_id: String = "",
    @SerializedName("avatar_url")
    val avatar_url: String = "",
    @SerializedName("gravatar_id")
    val gravatar_id: String = "",
    @SerializedName("html_url")
    val html_url: String = "",
    @SerializedName("type")
    val type: String = "",
    @SerializedName("site_admin")
    val site_admin: String = "",
)
