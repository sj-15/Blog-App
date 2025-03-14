package com.test.Blog.util;

import java.util.HashMap;
import java.util.Map;

import com.test.Blog.models.Posts;

public class PostMapper {
    public Map postDetailsToMap(Posts post) {
        Map map = new HashMap();
        map.put("post_id", post.getPostId().toString());
        map.put("title", post.getPostTitle());
        map.put("body", post.getPostBody());
        map.put("created_on",post.getCreatedOn());
        map.put("created_by", post.getPublishedBy().getUsername());
        map.put("last_updated", post.getUpdatedOn());
        return map;
    } 
}
