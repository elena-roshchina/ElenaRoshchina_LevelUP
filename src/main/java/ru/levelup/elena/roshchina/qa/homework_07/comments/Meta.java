package ru.levelup.elena.roshchina.qa.homework_07.comments;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

public class Meta {

    private Pagination pagination;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("pagination", pagination).append("additionalProperties", additionalProperties).toString();
    }

}