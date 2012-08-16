package chronic

import com.google.code.morphia.annotations.Embedded
import com.google.code.morphia.annotations.Entity
import com.google.code.morphia.annotations.Reference

@Entity 
class Mission {
	
	String name
	String group
	String url
	
	@Embedded
	Map options
	
	@Reference
	User createdBy
	
	Date createdOn
	
    static constraints = {
    }
}
