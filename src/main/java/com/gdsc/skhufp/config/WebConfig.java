package com.gdsc.skhufp.config;

import com.gdsc.skhufp.closet.converter.StringToClothTypeConverter;
import com.gdsc.skhufp.closet.converter.StringToSeasonConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToClothTypeConverter());
        registry.addConverter(new StringToSeasonConverter());
    }
}
