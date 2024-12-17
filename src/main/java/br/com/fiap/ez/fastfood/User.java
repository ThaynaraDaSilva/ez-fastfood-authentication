package br.com.fiap.ez.fastfood;

public class User {

	private String cpf;
	private String password;


	public User() {
	}


	public User(String cpf, String password) {
		this.cpf = cpf;
		this.password = password;
	}


	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User{" + "cpf='" + cpf + '\'' + '}';
	}

}
