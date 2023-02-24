package com.restapi.springbootrestapi.utils.response;

public interface CommentProjection {
    Long getCommentId();
    String getCommentName();
    String getCommentEmail();
    String getCommentBody();
}
