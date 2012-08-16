package chronic

import com.google.code.morphia.annotations.Entity
import com.google.code.morphia.annotations.Reference

@Entity
class User {
	
	String username
	String password

    static constraints = {
    }
}
