package chronic

import chronic.HarnessJob;

class FetchJob {
	
	static triggers = {
		cron name:'cronTrigger', startDelay:0, cronExpression: '0 0 */1 * * ?'
	}
	
	def jobService
	def concurrent = false
	
    def execute(context) {
		print "Fetch: "
		println(new Date())
		
		
		def jobs = Job.findAllByScheduleLessThanEqualsAndStatus(jobService.period.toDate(), "waiting")
		
		if (jobs != null) {
			
			jobs.each { job ->
				
				job.status = "scheduled"
				job.save()
				
				HarnessJob.schedule(job.schedule, ["job":job])
			}
			
		}
		else {
			println(items)
		}
		
		jobService.updatePeriod()
		
	
	}
}
