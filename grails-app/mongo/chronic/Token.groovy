package chronic

import com.google.code.morphia.annotations.Entity
import com.google.code.morphia.annotations.Reference
import com.google.code.morphia.annotations.Indexed

@Entity
class Token {
	
	@Reference
	User createdBy
	
	Date createdOn
	Date expiredOn
	
	/* open, expired */
	@Indexed
	String status 
	
    static constraints = {
    }
	
}
