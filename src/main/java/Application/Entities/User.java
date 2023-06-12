package Application.Entities;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import Application.Entities.enums.RoleType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Entity
@NoArgsConstructor
public class User implements UserDetails {

	@Id
	@GeneratedValue
	private UUID id;
	@NotNull
	private String username;
	@NotNull
	private String nome;
	@NotNull
	private String email;
	@NotNull
	private Boolean active = true;
	@NotNull
	private String cognome;
	@NotNull
	private String password;
	@NotNull
	@Enumerated(EnumType.STRING)
	private RoleType role = RoleType.USER;
	@NotNull
	@OneToMany(mappedBy = "utenteAssegnato")
	@JsonManagedReference
	List<Dispositivo> dispositivi;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public boolean isAccountNonExpired() {

		return false;
	}

	@Override
	public boolean isAccountNonLocked() {

		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return false;
	}

	@Override
	public boolean isEnabled() {

		return false;
	}

	public User(@NotNull String username, @NotNull String nome, @NotNull String email, @NotNull String cognome,
			@NotNull String password) {

		this.username = username;
		this.nome = nome;
		this.email = email;
		this.cognome = cognome;
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", nome=" + nome + ", email=" + email + ", active="
				+ active + ", cognome=" + cognome + ", password=" + password + "]";
	}

}
