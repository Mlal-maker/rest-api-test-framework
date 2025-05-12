package api.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data // it can use for other commented annotations like setter, getter, noArgConstructor
public class User {
	
	int id;
	String username;
	String firstName;
	String lastName;
	String email;
	String password;
	String phone;
	int userStatus = 0;
}
