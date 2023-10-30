// import java.util.ArrayList;
// import java.util.Collection;
// import java.util.List;
// import java.util.Set;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import com.example.asm.domain.Account;

// public class ShopUserDetails implements UserDetails {

//     private static final long serialVersionUID = 1L;
//     private Account account;

//     public ShopUserDetails(Account account) {
//         this.account = account;
//     }

//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         // Set<Role> roles = user.getRoles();

//         List<SimpleGrantedAuthority> authories = new ArrayList<>();

     

//         authories.add(new SimpleGrantedAuthority("admin"));
//         authories.add(new SimpleGrantedAuthority("user"));
//         return authories;
//     }

//     @Override
//     public String getPassword() {
//         return account.getPassword();
//     }

//     @Override
//     public String getUsername() {
//         return account.getUsername();
//     }

//     @Override
//     public boolean isAccountNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isAccountNonLocked() {
//         return true;
//     }

//     @Override
//     public boolean isCredentialsNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isEnabled() {
//         return account.isActivated();
//     }

//     public String getFullname() {
//         return this.account.getFullName();
//     }

//     // public boolean hasRole(String roleName) {

//     //     if(roleName.equalsIgnoreCase("admin")){
//     //         return true;
//     //     }
        


//     //     return account.isAdmin(roleName);
//     // }

// }