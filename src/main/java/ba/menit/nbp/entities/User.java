package ba.menit.nbp.entities;

import ba.menit.nbp.utilities.ValidationRegexes;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "\"user\"")
public class User implements UserDetails {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
@SequenceGenerator(name = "user_seq_gen", sequenceName = "user_seq", allocationSize = 1)
private Long id;
//    private Integer id;

    @Pattern(regexp = ValidationRegexes.onlyLettersRegex,message = "Ime moze sadrzavati samo slova")
    @NotBlank(message = "Ime ne može biti prazno")
    private String firstName;


    @NotBlank(message = "Prezime ne može biti prazno")
    @Pattern(regexp = ValidationRegexes.onlyLettersRegex,message = "Prezime moze sadrzavati samo slova")
    private String lastName;

    private String email;

    private String password;


    @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @Column(name = "profile_picture", columnDefinition = "BYTEA")
    private byte[] profilePicture;




    public User(String email, String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public User(String email,String firstName, String lastName,  String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Role getRole() {
        return this.role;
    }

    public String getRoleString(){
        return this.role.getName().toString();
    }

    public User setRole(Role role) {
        this.role = role;

        return this;
    }

    // Hibernate expects entities to have a no-arg constructor,
    // though it does not necessarily have to be public.
    public User() {

    }
    public User(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    public User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setFirstName(String firstName) {
        if (firstName.isEmpty() || firstName == null) {
            throw new IllegalArgumentException("Ime ne moze biti prazno");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName.isEmpty() || lastName == null) {
            throw new IllegalArgumentException("Ime ne moze biti prazno");
        }
        this.lastName = lastName;
    }
    public void setFullName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Full name cannot be empty");
        }
        String[] names = fullName.trim().split("\\s+");
        this.firstName = names[0];
        if (names.length > 1) this.lastName = names[1];
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.getName().toString());

        return List.of(authority);
    }




    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



    public User(Long id, String firstName, String lastName, String email, String password, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }
}
