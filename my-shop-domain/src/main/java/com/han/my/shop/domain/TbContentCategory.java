package com.han.my.shop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.han.my.shop.commons.persistence.BaseEntity;
import lombok.Data;

/**
 * Created by han on 2019/9/8.
 */
@Data
public class TbContentCategory  extends BaseEntity {
     private Long parentId;
     private String name;
     private Integer status;
     private Integer sortOrder;
     @JsonProperty(value = "isParent")
     private Boolean isParent;


}
