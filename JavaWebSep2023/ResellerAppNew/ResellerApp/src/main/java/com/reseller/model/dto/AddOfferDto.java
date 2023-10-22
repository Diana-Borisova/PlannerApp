package com.reseller.model.dto;




import com.reseller.model.entity.ConditionEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class AddOfferDto {
    @NotNull
    @Size(min= 2, max = 50, message = "Description length must be between 2 and 50 characters!")
    private String description;



    @NotNull
    @Positive(message = "Price must be positive number!")
    private BigDecimal price;

    @NotNull(message = "You must select a condition!")
    @Enumerated(EnumType.STRING)
    private ConditionEnum conditionEnum;



    public AddOfferDto() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ConditionEnum getConditionEnum() {
        return conditionEnum;
    }

    public void setConditionEnum(ConditionEnum conditionEnum) {
        this.conditionEnum = conditionEnum;
    }


}
