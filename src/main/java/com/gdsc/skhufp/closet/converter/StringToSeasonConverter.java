package com.gdsc.skhufp.closet.converter;

import com.gdsc.skhufp.closet.domain.entity.Season;
import com.gdsc.skhufp.common.exception.closet.SeasonTypeParamNotMatchException;
import org.springframework.core.convert.converter.Converter;

public class StringToSeasonConverter implements Converter<String, Season> {
    @Override
    public Season convert(String source) {
        try {
            return Season.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new SeasonTypeParamNotMatchException();
        }
    }
}
