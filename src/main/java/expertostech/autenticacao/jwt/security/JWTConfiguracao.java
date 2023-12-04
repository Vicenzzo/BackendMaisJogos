package expertostech.autenticacao.jwt.security;

import expertostech.autenticacao.jwt.services.DetalheUsuarioServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class JWTConfiguracao extends WebSecurityConfigurerAdapter {

    private final DetalheUsuarioServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;

    public JWTConfiguracao(DetalheUsuarioServiceImpl usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()

                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/api/usuario/salvar").permitAll()
                .antMatchers(HttpMethod.POST, "/api/adm/salvar").permitAll()
                .antMatchers(HttpMethod.POST, "/api/adm/login").permitAll()
                .antMatchers(HttpMethod.POST, "/api/review/salvar").permitAll()
                .antMatchers(HttpMethod.POST, "/api/check/salvar").permitAll()
                .antMatchers(HttpMethod.POST, "/api/avatar/salvar").permitAll()


                .antMatchers(HttpMethod.GET, "/api/adm/listarTodos").permitAll()
                .antMatchers(HttpMethod.GET, "/api/adm//listarAdm/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/avatar/listarAvatar/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/review/listarReview/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/review/listarTodos").permitAll()
                .antMatchers(HttpMethod.GET, "/api/check/listarTodos").permitAll()
                .antMatchers(HttpMethod.GET, "/api/jogo/listarTodos").permitAll()

                .antMatchers(HttpMethod.PUT, "/api/adm/alterarAdmin/{id}").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/avatar/alterarAvatar/{id}").permitAll()

                .antMatchers(HttpMethod.DELETE, "/api/adm/deletarAdm/{id}").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/review/deletarReview/{id}").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/avatar/deletarAvatar/{id}").permitAll()



                .anyRequest().authenticated()
                .and().cors()
                .and()
                .addFilter(new JWTAutenticarFilter(authenticationManager()))
                .addFilter(new JWTValidarFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("https://mais-jogos-frontend-react-mxfw.vercel.app/");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new
                UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}











