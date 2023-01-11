package com.gdsc.skhufp.closet.converter;


import com.gdsc.skhufp.closet.domain.entity.ClothType;
import com.gdsc.skhufp.common.exception.closet.ClothTypeParamNotMatchException;
import org.springframework.core.convert.converter.Converter;

public class StringToClothTypeConverter implements Converter<String, ClothType> {
    @Override
    public ClothType convert(String source) {
        try {
            return ClothType.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ClothTypeParamNotMatchException();
        }
    }
}
