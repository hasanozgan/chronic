class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?" {
			constraints {
				// apply constraints here
			}
		}
		"/job/$action?/$group/$name"(controller: "job") {
			constraints {
				// apply constraints here
			}
		}
		"/"(controller:"job")
		"500"(view:'/error')
	}
}
