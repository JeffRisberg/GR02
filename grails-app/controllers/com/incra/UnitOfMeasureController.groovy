package com.incra

class UnitOfMeasureController {
	
	def scaffold = true
	
	def index = {
		redirect(action: "list", params: params)
	}
}
