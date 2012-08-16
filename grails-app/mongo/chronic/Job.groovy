package chronic

import com.google.code.morphia.annotations.Entity
import com.google.code.morphia.annotations.Embedded
import com.google.code.morphia.annotations.Reference
import org.codehaus.groovy.grails.web.json.JSONArray

@Entity
class Job {

	Date schedule
	
	@Reference
	Mission mission
	
	@Reference
	User createdBy
	
	Date createdOn
	Date workedOn
	
	@Embedded
	JSONArray parameters
	
	/*
	 * Status => {waiting, failed, completed}
	 */
	String status
	
    static constraints = {
		workedOn nullable:true
		
    }
}
