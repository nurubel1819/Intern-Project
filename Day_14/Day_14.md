# Security configuration
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->
                        auth.requestMatchers("/swagger-ui.html/**").permitAll()
                                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return  new CustomUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
                                                       PasswordEncoder passwordEncoder){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(daoAuthenticationProvider);
    }
}
# Default admin set
@Bean
    public CommandLineRunner createAdminUser(UserDetailsRepo userDetailsRepo, PasswordEncoder passwordEncoder){
        return args -> {
            if(userDetailsRepo.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("kaka123"));
                admin.setRole("ROLE_ADMIN");

                userDetailsRepo.save(admin);
                System.out.println("Default admin user created");
            }
        };
    }
# Custom user details
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired // if here i user "final" and @RequiredArgsConstructor, then one error find in securityConfiguration class method userDetailsService() return type
    private UserDetailsRepo userDetailsRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDetailsRepo.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("User not found"));
    }
}