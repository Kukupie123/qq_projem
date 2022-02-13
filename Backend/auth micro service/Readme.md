#Flow

1. From controller we use authenticationManager to authenticate using email and password
2. In SecurityConfig we configured how authentication is going to work in ``` protected void configure(AuthenticationManagerBuilder auth) throws Exception {
   auth.userDetailsService(myUserDetailsService);
   }```
3. ```MyUserDetailsService``` Is going to use ```loadUserByName``` function to get ```MyUserDetail```
4. Inside ```loadUserByName``` we use repo and jpa to get record from database
5. And creates MyUserDetail object out of it and returns it.

We do not need filters because this service is only going to check if credentials are right and return a JWT token so we do not need to remember anything at all
