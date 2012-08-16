package chronic

import static groovyx.net.http.ContentType.HTML
import groovyx.net.http.*
import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*

class HarnessJob {
	def concurrent = true
	
	static triggers = {}
	
    def execute(context) {
		
		print "Harness: "
		println new Date()
		
		Job job = (Job) context.mergedJobDataMap.get('job')
		
		if (job != null) {
			job.workedOn = new Date()
			job.status = "completed"
			
			Map prmtrs = job.mission.options
			if (job.parameters != null) {
				job.parameters.each { prm ->
					println(prm)
					prm.each { k, v ->
						prmtrs.put(k, v)
					} 
				}
			}
			
			println(job.mission.url)
			
			def http = new HTTPBuilder( job.mission.url )
		
			http.request( POST ) {
				uri.query = prmtrs

				response.success = { resp, html ->
					println resp.statusLine
					println "---------"
					println html
					println "---------"

				}

				response.failure = { resp ->
					println "Unexpected error: ${resp.statusLine.statusCode} : ${resp.statusLine.reasonPhrase}"
					
					job.status = "failed"
				}
			}
			
			job.save()
		}
				

		

		
		// perform a GET request, expecting JSON response data
//		http.request( POST ) {
//		  uri.path = '/index.html'
//		  
//		  uri.query = [ v:'1.0', q: 'Calvin and Hobbes' ]
//		
//		  headers.'User-Agent' = 'Mozilla/5.0 Ubuntu/8.10 Firefox/3.0.4'
		
//		  // response handler for a success response code:
//		  response.success = { resp, json ->
//		    println resp.statusLine
//		
//		    // parse the JSON response object:
//		    json.responseData.results.each {
//		      println "  ${it.titleNoFormatting} : ${it.visibleUrl}"
//		    }
//		  }
//		
//		  // handler for any failure status code:
//		  response.failure = { resp ->
//		    println "Unexpected error: ${resp.statusLine.statusCode} : ${resp.statusLine.reasonPhrase}"
//		  }
//		}
        // execute task
    }
}
