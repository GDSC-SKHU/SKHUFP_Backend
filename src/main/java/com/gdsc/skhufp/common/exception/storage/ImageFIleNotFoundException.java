package com.gdsc.skhufp.common.exception.storage;

import com.gdsc.skhufp.common.exception.CustomAbstractException;
import com.gdsc.skhufp.common.response.StatusEnum;

public class ImageFIleNotFoundException extends CustomAbstractException {
    public ImageFIleNotFoundException() {
        super(StatusEnum.IMAGE_FILE_NOT_FOUND);
    }
}
