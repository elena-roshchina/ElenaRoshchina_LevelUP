package ru.levelup.elena.roshchina.qa.homework_07.posts;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class PostsPojo {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("data")
    @Expose
    private List<SinglePost> data = null;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<SinglePost> getData() {
        return data;
    }

    public void setData(List<SinglePost> data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this).append("code", code).append("meta", meta).append("data", data).toString();
    }

}

