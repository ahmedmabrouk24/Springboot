package com.global.Bosta.model;

public class UserModel {
	private String email;
	private String password;
	private Profile profile;
	private String heardAboutUsFrom;
	private String monthlyShipmentVolume;
	private String countryId;
	private String recaptchaValue;
	
	public String getRecaptchaValue() {
		return recaptchaValue;
	}

	public void setRecaptchaValue(String recaptchaValue) {
		this.recaptchaValue = recaptchaValue;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getHeardAboutUsFrom() {
		return heardAboutUsFrom;
	}

	public void setHeardAboutUsFrom(String heardAboutUsFrom) {
		this.heardAboutUsFrom = heardAboutUsFrom;
	}

	public String getMonthlyShipmentVolume() {
		return monthlyShipmentVolume;
	}

	public void setMonthlyShipmentVolume(String monthlyShipmentVolume) {
		this.monthlyShipmentVolume = monthlyShipmentVolume;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public static class Profile {
		private String firstName;
		private String lastName;
		private String phone;

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

	}
}
