package chronic

import net.sf.json.JSONSerializer;
import org.codehaus.groovy.grails.web.json.JSONArray
import org.joda.time.DateTime

import grails.converters.JSON
import com.mongodb.WriteConcern

class AuthController {
	
	def authenticationService
	def jsonService
	
	def index = {
		
		def result = [ "success":false ]
		
		// validation params
		if (!((params.username != null && params.username.size() > 0) &&
			  (params.password != null && params.password.size() > 0))) 
		{
			result.put("success", false)
			result.put("code", -10)
			result.put("message", "Check parameters")
		}

		def token = authenticationService.createToken(params.username, params.password)
		
		if (token == null) {
			result.put("success",false)
			result.put("code", -20)
			result.put("message", "Authentication failure")
		}
		else {
			result.put("success", true)
			result.put("code", 0)
			result.put("message", "Success")
			result.put("token", token.toString())
		}
		
		render contentType:"text/javascript", text: (result as JSON).toString()
	}
}
