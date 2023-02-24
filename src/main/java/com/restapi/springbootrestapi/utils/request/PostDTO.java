package com.restapi.springbootrestapi.utils.request;

import lombok.Data;

@Data
public class PostDTO {
    private String title;
    private String description;
    private Integer maxiumOfComments;
    private String content;
    private String phoneNumber;
}
