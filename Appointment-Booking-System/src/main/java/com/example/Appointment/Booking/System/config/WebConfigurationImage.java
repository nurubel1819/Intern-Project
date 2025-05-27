package com.example.Appointment.Booking.System.config;

import com.example.Appointment.Booking.System.helper.AppConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurationImage implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/"+ AppConstants.LAB_IMAGE_UPLOAD_DIR +"/**")
                .addResourceLocations("file:"+ AppConstants.LAB_IMAGE_UPLOAD_DIR +"/");
                /*.addResourceHandler("/uploads/labs/**")
                .addResourceLocations("file:uploads/labs/");*/
        registry
                .addResourceHandler("/"+ AppConstants.LAB_TEST_IMAGE_UPLOAD_DIR +"/**")
                .addResourceLocations("file:"+ AppConstants.LAB_TEST_IMAGE_UPLOAD_DIR +"/");

        registry
                .addResourceHandler("/"+ AppConstants.USER_IMAGE_UPLOAD_DIR +"/**")
                .addResourceLocations("file:"+ AppConstants.USER_IMAGE_UPLOAD_DIR +"/");
    }
}
