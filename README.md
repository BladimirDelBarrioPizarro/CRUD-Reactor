# The Publisher Interface

public interface Publisher<T> {
public void subscribe(Subscriber<? super T> s);
}

# The Subscriber Interface

public interface Subscriber<T> {
public void onSubscribe(Subscription s);
public void onNext(T t);
public void onError(Throwable t);
public void onComplete();
}

# The Subscription interface

public interface Subscription {
public void request(long n);
public void cancel();
}

# The Processor interface

public interface Processor<T, R> extends Subscriber<T>, Publisher<R> {
}

# Flux

public abstract class Flux<T> extends Object implements Publisher<T>

# Flux code example

Creating empty Flux:
Flux<String> emptyFlux = Flux.empty();

Creating Flux with items in it:
Flux<String> itemFlux = Flux.just("Spring”, "Security”, "Reactive”);

Creating Flux from an existing list:
List<String> existingList = Arrays.asList("Spring”, "Security”, "Reactive”);
Flux<String> listFlux = Flux.fromIterable(existingList);

Creating Flux that emits every x milliseconds in an infinite manner:
Flux<Long> timer = Flux.interval(Duration.ofMillis(x));

Creating Flux that emits an exception:
Flux.error(new CreatedException());


# Mono

public abstract class Mono<T> extends Object implements Publisher<T>

# Mono code example

Creating empty Mono:
Mono<String> emptyMono = Mono.empty();

Creating Mono with a value in it:
Mono<String> itemMono = Mono.just("Spring Security Reactive”);

Creating Mono that emits an exception:
Mono.error(new CreatedException());



# Spring Security terminologies

Principal, Authentication, Credentials, Authorization, Secured item/resource, GrantedAuthority, SecurityContext

# Working of Spring Security

Servlet Filter, Filter Chain, Security Interceptor (DelegatingFilterProxy)

# Core Spring Security modules

spring-security-core, spring-security-remoting, spring-security-aspects, spring-security-config, spring-security-crypto,
spring-security-data, spring-security-messaging, spring-security-oauth2-core, spring-security-oauth2-client,
spring-security-oauth2-jose, spring-security-openid, spring-security-cas, spring-security-taglibs, spring-security-test,
spring-security-web, spring-ldap, spring-security-oauth, spring-security-saml, spring-security-kerberos

# Authentication

public interface AuthenticationManager {
Authentication authenticate(Authentication authentication) throws AuthenticationException;
}


public interface AuthenticationProvider {
Authentication authenticate(Authentication authentication) throws AuthenticationException;
boolean supports(Class<?> authentication);
}


public interface UserDetailsService {
UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}

# Setting up AuthenticationManager

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
@Autowired
public void confGlobalAuthManager(AuthenticationManagerBuilder auth) throws Exception {
      auth
         .inMemoryAuthentication()
         .withUser("admin").password("admin@password").roles("ROLE_ADMIN");
 }
}

--------------------------------------------------------------------------------------

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
       .inMemoryAuthentication()
       .withUser("admin").password("admin@password").roles("ROLE_ADMIN");
 }
}

--------------------------------------------------------------------------------------

@Override
public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
}

--------------------------------------------------------------------------------------

# AuthenticationProvider

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
            if ("user".equals(username) && "password".equals(password)) {
                return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
            } 
            else {
                throw new BadCredentialsException("Authentication failed");
            }
        }
        
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}


# Multiple AuthenticationProvider

@EnableWebSecurity
@ComponentScan(basePackageClasses = CustomAuthenticationProvider.class)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    CustomAuthenticationProvider customAuthenticationProvider;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers("/**")
            .authenticated(); // Use Basic authentication
     }
     
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Custom authentication provider - Order 1
           auth.authenticationProvider(customAuthenticationProvider);
        // Built-in authentication provider - Order 2
           auth.inMemoryAuthentication().withUser("admin").password("{noop}admin@password")
        //{noop} makes sure that the password encoder doesn't do anything
                                         .roles("ADMIN") // Role of the user
                                         .and()
                                         .withUser("user")
                                         .password("{noop}user@password")
                                         .credentialsExpired(true)
                                         .accountExpired(true)
                                         .accountLocked(true)
                                         .roles("USER");
        }
    }



# HandleFunction

public class MovieHandler {
    public Mono<ServerResponse> listMovies(ServerRequest request) {
        // Logic that returns all Movies objects
    }
    public Mono<ServerResponse> createMovie(ServerRequest request) {
    // Logic that returns creates Movie object in the request object
    }
    public Mono<ServerResponse> getMovie(ServerRequest request) {
     // Logic that returns one Movie object
    }
    //.. More methods as needed
}

    Mono<String>  helloWorld = request.body(BodyExtractors.toMono(String.class);
    Mono<String> helloWorldUtil = request.bodyToMono(String.class);
    Flux<Person> movie = request.body(BodyExtractors.toFlux(Movie.class);
    Flux<Person> movieUtil = request.bodyToFlux(Movie.class);

   Mono<Movie> movie = ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(movie);
   
   public class MovieHandler {
    public Mono<ServerResponse> listMovies(ServerRequest request) {
        // Logic that returns all Movies objects
    }
    public Mono<ServerResponse> createMovie(ServerRequest request) {
        // Logic that returns creates Movie object in the request object
    }
    public Mono<ServerResponse> getMovie(ServerRequest request) {
        // Logic that returns one Movie object
    }
        //.. More methods as needed
   }

# RouterFunction

RouterFunction<ServerResponse> routeFunctionSample = RouterFunctions.route(RequestPredicates.path("/sample-route"),request -> Response.ok().body(fromObject("Sample Route")));

# WebClient

WebClient: WebClient client = WebClient.create ("http://any-domain.com");
    Mono <Movie> movie = client.get ()
        .url ("/ movie / {id}", 1L)
        .accept(APPLICATION_JSON)
        .exchange(request)
        .then(response -> response.bodyToMono(Movie.class));

