package chronic

import org.bson.types.ObjectId
import org.codehaus.groovy.grails.web.json.JSONArray
import org.joda.time.DateTime

class JobService {

    static transactional = true
	
	def intervalHour = 1
	def DateTime period = createPeriod()
	
	def createPeriod() {
		DateTime current = new DateTime()
		
		return new DateTime(current.getYear(), current.getMonthOfYear(),current.getDayOfMonth(), current.getHourOfDay(), 0, 0, 0)		
	}					
	
    def add(Mission mission, User user, Date schedule, JSONArray params = []) {
		
		Job job = new Job()
		job.mission = mission
		job.createdBy = user	
		job.createdOn = new Date()
		job.schedule = schedule
		job.parameters = params
		
		if (inPeriod(schedule)) {
			job.status = "scheduled"
			job.save()
			
			println("HARNESS: " + period)
			HarnessJob.schedule(job.schedule, ["job":job])
			
		}
		else {
			job.status = "waiting"
			job.save()
	
			println("LATER: " + period)
		}
		
		return job
    }

	def nextPeriod() {
		period = createPeriod()
		return period.plusHours(intervalHour)
	}
	
	def updatePeriod()
	{
		period = nextPeriod()
		println("updated:"+period)
	}
	
	def inPeriod(Date scheduleDate) {
		
		if (scheduleDate <= period.toDate()) {
			return true;
		} 
		
		return false;
	}
	
	def findByPeriod(Date period) {
		
	}
	
	
	def remove(jobId) {
	
	}

	def get(jobId) {
		return Job.get(jobId)
	}

		
	def run(jobId) {
		
	}
}
