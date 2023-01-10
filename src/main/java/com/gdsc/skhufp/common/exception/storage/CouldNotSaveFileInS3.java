package com.gdsc.skhufp.common.exception.storage;

import com.gdsc.skhufp.common.exception.CustomAbstractException;
import com.gdsc.skhufp.common.response.StatusEnum;

public class CouldNotSaveFileInS3 extends CustomAbstractException {

    public CouldNotSaveFileInS3() {
        super(StatusEnum.COULD_NOT_SAVE_FILE_IN_S3);
    }
}
