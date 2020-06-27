package car;

public class User {
    private String phoneNumber;
    private String password;
    private String nickName;
    private String image;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String userName) {
        this.phoneNumber = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
