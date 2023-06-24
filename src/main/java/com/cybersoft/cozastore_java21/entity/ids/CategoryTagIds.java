package com.cybersoft.cozastore_java21.entity.ids;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class CategoryTagIds implements Serializable {

    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "tag_id")
    private int tagId;

    public CategoryTagIds(){};

    public CategoryTagIds(int categoryId, int tagId){
        this.categoryId = categoryId;
        this.tagId = tagId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }
}
