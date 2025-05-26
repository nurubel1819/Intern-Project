package com.example.Appointment.Booking.System.config;



import com.example.Appointment.Booking.System.jwt.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    @Autowired
    JwtAuthFilter jwtAuthFilter;
    public static final String[] ADMIN_URLS = {
            "/admin/**",
            "/roles/**",
            "/labs/**",
            "/test-types/**",
            "/lab_test/**"
    };
    public static final String[] PATIENT_URLS = {
            "/users/**",
            "/lab-test-dashboard",
            "/doctor-dashboard",
            "/LabTestAppointmentForm",
            "/lab-test-appointment-book/**",
            "/doctor_appointment_form",
            "/doctor-appointment-book/**"
    };
    public static final String[] DOCTOR_URLS = {
           "/doctor/**"
    };

    public static final String[] PUBLIC_URLS = {
            "/",
            "/css/**",
            "/login-with-dummy-data",
            "/registration",
            "/login",
            "/user-logout",
            "/image/**",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-ui.html"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        System.out.println("inside Security Filer Chain");
        http.
                csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PATIENT_URLS).hasAnyRole("USER","ADMIN")
                        .requestMatchers(ADMIN_URLS).hasAnyRole("ADMIN")
                        .requestMatchers(DOCTOR_URLS).hasAnyRole("DOCTOR","ADMIN")
                        .requestMatchers(PUBLIC_URLS).permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.sendRedirect("/login");
                        })
                );
        return http.build();
    }
}