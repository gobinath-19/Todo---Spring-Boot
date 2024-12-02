// package com.example.Todo.Config;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// public class WebConfig implements WebMvcConfigurer {

//     @Override
//     public void addCorsMappings(CorsRegistry registry) {
//         registry.addMapping("/**") // Allow all paths
//                 .allowedOrigins("http://127.0.0.1:5500") // Allow your frontend's origin
//                 .allowedMethods("GET", "POST", "PUT", "DELETE") // Specify allowed methods
//                 .allowedHeaders("*") // Allow all headers
//                 .allowCredentials(true); // Allow cookies or credentials if needed
//     }
// }
