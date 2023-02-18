package com.restapi.springbootrestapi.utils.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
public class PostDTO {
    @NotNull(message = "title is required")
    @NotEmpty(message = "field is not empty")
    @Length(min=1, max=30, message = "invalid length field title")
    private String title;

    @NotNull(message = "description is required")
    @NotEmpty
    @Length(min=1, max=30, message = "invalid length field title")
    private String description;

    @Max(6)
    @Min(1)
    @NotNull(message = "maxium of comments is required")
    private Integer maxiumOfComments;

    @NotNull(message = "field is content required")
    @NotEmpty(message = "field is content empty")
    @Length(min=1, max=30, message = "invalid length field title")
    private String content;
}
