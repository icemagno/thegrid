package br.com.cmabreu.dto;

import br.com.cmabreu.model.User;

public class UserDTO {
	private Long id;
	private String userName;
	private String email;
	private String role;
	private String walletAddress;
	private String publicKey;	
	private Boolean enabled;
	private Boolean denounced;	
	
	public UserDTO( User user ) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.role = user.getRole();
	}

	public Long getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getEmail() {
		return email;
	}

	public String getRole() {
		return role;
	}

	public String getWalletAddress() {
		return walletAddress;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public Boolean getDenounced() {
		return denounced;
	}
	
	

}
