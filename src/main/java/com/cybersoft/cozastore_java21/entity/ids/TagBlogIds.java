package com.cybersoft.cozastore_java21.entity.ids;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class TagBlogIds implements Serializable {

    @Column(name = "tag_id")
    private int tagId;

    @Column(name = "blog_id")
    private int blogId;

    public TagBlogIds(){}

    public TagBlogIds(int tagId,int blogId){
        this.blogId = blogId;
        this.tagId = tagId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }
}
