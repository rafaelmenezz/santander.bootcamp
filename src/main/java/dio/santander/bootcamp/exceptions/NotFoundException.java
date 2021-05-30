package dio.santander.bootcamp.exceptions;

import dio.santander.bootcamp.util.MessageUtil;

public class NotFoundException extends RuntimeException{
    
    public NotFoundException(){
        super(MessageUtil.NO_RECORDS_FOUND);
    }
}
