package org.evilempire.helloworld.domain;

import java.util.Date;

public class Entity {

    private Integer entityId;
    private String entityName;
    private Date entityTimeStamp;
    private Double entityDouble;

    public Entity() {
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public Double getEntityDouble() {
        return entityDouble;
    }

    public void setEntityDouble(Double entityDouble) {
        this.entityDouble = entityDouble;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public Date getEntityTimeStamp() {
        return entityTimeStamp;
    }

    public void setEntityTimeStamp(Date entityTimeStamp) {
        this.entityTimeStamp = entityTimeStamp;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "entityId=" + entityId +
                ", entityName='" + entityName + '\'' +
                ", entityTimeStamp=" + entityTimeStamp +
                ", entityDouble=" + entityDouble +
                '}';
    }
}
