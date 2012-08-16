package chronic

import net.sf.json.JSONSerializer;
import org.codehaus.groovy.grails.web.json.JSONArray
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormatter
import org.joda.time.format.DateTimeFormat

import grails.converters.JSON

class JobController {
	
	def authenticationService
	def jobService

    def index = { 
		render "Scheduler Job Restful Service"
	}
	
	def add = {

		def result = [ "success":false ]
		
		
		def mission = Mission.findByGroupAndName(params.group, params.name)
		
		if (mission == null) {
			result.put("code", "-10")
			result.put("message", "Mission not found")
		}
		else {
			try {
				def jsonData = JSON.parse(request.reader.text)
				
				if (authenticationService.validateToken(jsonData.token)) {
				
					def token = Token.get(jsonData.token)
					DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
					Date scheduleDate = formatter.parseDateTime(jsonData.scheduleDate).toDate()
					
					def job = jobService.add(mission, token.createdBy, scheduleDate, jsonData.params)

					result.put("success", true)
					result.put("code", "0")
					result.put("jobId", job.id)
					result.put("message", "Success")
				}
				else {
					result.put("code", "-20")
					result.put("message", "Token is expired")
				}
			}
			catch (e) {
				result.put("code", "-99")
				result.put("message", "Exception =>"+e.getMessage())
			}
		}
				
		render contentType:"text/javascript", text: (result as JSON).toString()
	}
	
	def remove = {
		def jobId = params.id
		
		// HarnessJob.unschedule(...?)
	}
	
	def status = {
		def result = [
			"success":false,
			"code":"-10",
			"message": "Not found",
			"jobId": params.id
		]
		
		def job = jobService.get(params.id)
		
		if (job != null) {
			result.put("success", true)
			result.put("code", 0)
			result.put("message", "Sucess")
			result.put("job.mission.name", job.mission.name)
			result.put("job.mission.group", job.mission.group)
			result.put("job.schedule", job.schedule)
			result.put("job.createdOn", job.createdOn)
			result.put("job.createdBy", job.createdBy.username)
			result.put("job.workedOn", job.workedOn)
			result.put("job.status", job.status)
		}
		
		render contentType:"text/javascript", text: (result as JSON).toString()
	}

}
