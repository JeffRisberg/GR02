class UrlMappings {

  static mappings = {
    "/$controller/$action?/$id?"{ constraints { // apply constraints here
      } }

    "/"(controller:"summary")
    "500"(view:'/error')
  }
}
