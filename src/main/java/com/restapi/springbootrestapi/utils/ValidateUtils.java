package com.restapi.springbootrestapi.utils;

import lombok.Builder;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Builder
public class ValidateUtils {
    private Object value;
    private boolean required;
    private Integer maxLength;
    private String fieldName;
    private String regex;
    private boolean onlyNumber;
    private boolean isInteger;
    private Long max;
    private Long min;
    private String message;
    private final String ONLY_NUMBER = "[0-9]+";
    public Map validate(){
        Map<String, String> errors = new HashMap<>();

        if(required && ObjectUtils.isEmpty(value)){
            errors.put(fieldName, fieldName +" is required");
        }

        if(!ObjectUtils.isEmpty(maxLength)
                && !ObjectUtils.isEmpty(value)
                && value.toString().length() > maxLength.intValue()
                && !ObjectUtils.isEmpty(fieldName)
        ){
            errors.put(fieldName,fieldName +" must have 0 " + maxLength +" charcters");
        }

        if(onlyNumber && !ObjectUtils.isEmpty(value) && !ObjectUtils.isEmpty(fieldName)){
            Pattern patternOnlyNumber = Pattern.compile(ONLY_NUMBER);
            Matcher matcher = patternOnlyNumber.matcher(value.toString());

            if(!matcher.matches()){
                errors.put(fieldName, fieldName + " must contain only number");
            }
        }

        if(isInteger && !ObjectUtils.isEmpty(value) && !ObjectUtils.isEmpty(fieldName)){
            try {
                Integer.parseInt(value.toString());
            }
            catch (Exception ex){
                errors.put(fieldName,fieldName + " must is integer number");
            }
        }

        if(!ObjectUtils.isEmpty(max)
                && !ObjectUtils.isEmpty(value)
                && !ObjectUtils.isEmpty(min)
                && !ObjectUtils.isEmpty(fieldName)
        ){
            try{
                Long valueCheck = Long.valueOf(value.toString());
                if(valueCheck < min || valueCheck > max){
                    errors.put(fieldName, fieldName + " must range from "+ min + " to "+ max);
                }
            }
            catch (Exception ex){
                errors.put(fieldName,fieldName + " is valid data type");
            }
        }
        return errors;
    }
}
