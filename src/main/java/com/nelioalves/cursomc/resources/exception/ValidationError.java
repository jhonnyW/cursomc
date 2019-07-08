package com.nelioalves.cursomc.resources.exception;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandartError {
    private static final long serialVersionUID = -8594692900395987251L;
private List<FieldMessage> list = new ArrayList<>();


    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }

    public List<FieldMessage> getList() {
        return list;
    }
    public void addError(String fieldName, String message){
        list.add(new FieldMessage(fieldName,message));
    }
}
