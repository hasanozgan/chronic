package chronic

import net.sf.json.JSONSerializer;
import org.codehaus.groovy.grails.web.json.JSONArray
import org.joda.time.DateTime

import grails.converters.JSON


class ApiController {
	
    def index = {
	
		User user = new User()
		user.username = "chronic"
		user.password = "(hr0n!("
		user.save()
		
		Mission mission = new Mission()
		mission.group = "harvest"
		mission.name = "dues"
		mission.url = "http://localhost/php-app/harves.php"
		mission.options = ["harvestType":"dues", "aaa":"bb"]
		mission.createdOn = new Date()
		mission.createdBy = user
		mission.save()
		
		render "OK!.."
		return
	

//		def b = new Job()
//		b.name = "The Shining"
//		b.group = "جماعة المشاهدين"
//		b.url = "http://google.com"
//		b.options = [test:"aaa", test2:"bbb"]
//		b.save();
//		
//		def d = new Task()
//		d.scheduleDate = new Date()
//		d.job = b
//		d.save();
//		 
		
//		def b = Job.findByGroupAndName("harvest", "monthly")
//		def b = new Job()
//		b.name = "monthly"
//		b.group = "harvesting"
//		b.url = "http://urbanville.com/harvest.php"
//		b.options = [type:"monthly", token:"1234"]
//		b.save()
		
//		def task = new Task()
//		task.scheduleDate = new Date()
//		task.jobId = b.id
//		task.status = "waiting"
//		task.job = b
//
//		task.save()
//		
//
//				
//		def t = Job.find(["tasks.status":"aa"])
//		
//		println(t.tas())

		
		

//		
//		HarnessJob.schedule(new DateTime(2011, 06, 29, 10, 22, 10, 0).toDate())
//		
//		HarnessJob.schedule(new DateTime(2011, 06, 28, 18, 18, 20, 0).toDate())
//		
//		HarnessJob.schedule(new DateTime(2011, 06, 28, 18, 18, 30, 0).toDate())
		
//	}

//		def jsonString = (params as JSON).toString()
	
//		for (int i = 0; i < 100; i++) {
//			HarnessJob.schedule(new DateTime(2011, 06, 28, 14, 25, 0, 0).toDate())
//			HarnessJob.schedule(new DateTime(2011, 06, 28, 14, 25, 10, 0).toDate())
//			HarnessJob.schedule(new DateTime(2011, 06, 28, 14, 25, 20, 0).toDate())
//		}

//		render "OK"		
		
		//JSON data = request.reader.text as JSON
		
//		JSONArray jsonArray = (JSONArray)JSONSerializer.toJSON(request.reader.text)
		//CALISMALIdef items = JSON.parse(request.reader.text)
	
//		def jsonString = ["aa":"aa", "bbbb":[23,2321,555]] ;
		  
//		render CALISMALI contentType:"text/javascript", 
//				text: items.getAt("aa")
//		render 	contentType:"text/javascript", 
//				text: (jsonString as JSON).toString()
		
	}
}