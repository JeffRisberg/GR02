package com.incra

/**
 * This is not actually used, it is just here to drive the scaffold generation for the views of ROIProject,
 * which are then manually copied into the project views.
 * 
 * @author Jeffrey Risberg
 * @since 11/30/10
 */
class ROIProjectController {
	
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def index = {
		redirect(action: "list", params: params)
	}
	
	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[ROIProjectInstanceList: ROIProject.list(params), ROIProjectInstanceTotal: ROIProject.count()]
	}
	
	def create = {
		def ROIProjectInstance = new ROIProject()
		ROIProjectInstance.properties = params
		return [ROIProjectInstance: ROIProjectInstance]
	}
	
	def save = {
		def ROIProjectInstance = new ROIProject(params)
		if (ROIProjectInstance.save(flush: true)) {
			flash.message = "${message(code: 'default.created.message', args: [message(code: 'ROIProject.label', default: 'ROIProject'), ROIProjectInstance.id])}"
			redirect(action: "show", id: ROIProjectInstance.id)
		}
		else {
			render(view: "create", model: [ROIProjectInstance: ROIProjectInstance])
		}
	}
	
	def show = {
		def ROIProjectInstance = ROIProject.get(params.id)
		if (!ROIProjectInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'ROIProject.label', default: 'ROIProject'), params.id])}"
			redirect(action: "list")
		}
		else {
			[ROIProjectInstance: ROIProjectInstance]
		}
	}
	
	def edit = {
		def ROIProjectInstance = ROIProject.get(params.id)
		if (!ROIProjectInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'ROIProject.label', default: 'ROIProject'), params.id])}"
			redirect(action: "list")
		}
		else {
			return [ROIProjectInstance: ROIProjectInstance]
		}
	}
	
	def update = {
		def ROIProjectInstance = ROIProject.get(params.id)
		if (ROIProjectInstance) {
			if (params.version) {
				def version = params.version.toLong()
				if (ROIProjectInstance.version > version) {
					
					ROIProjectInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'ROIProject.label', default: 'ROIProject')] as Object[], "Another user has updated this ROIProject while you were editing")
					render(view: "edit", model: [ROIProjectInstance: ROIProjectInstance])
					return
				}
			}
			ROIProjectInstance.properties = params
			if (!ROIProjectInstance.hasErrors() && ROIProjectInstance.save(flush: true)) {
				flash.message = "${message(code: 'default.updated.message', args: [message(code: 'ROIProject.label', default: 'ROIProject'), ROIProjectInstance.id])}"
				redirect(action: "show", id: ROIProjectInstance.id)
			}
			else {
				render(view: "edit", model: [ROIProjectInstance: ROIProjectInstance])
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'ROIProject.label', default: 'ROIProject'), params.id])}"
			redirect(action: "list")
		}
	}
	
	def delete = {
		def ROIProjectInstance = ROIProject.get(params.id)
		if (ROIProjectInstance) {
			try {
				ROIProjectInstance.delete(flush: true)
				flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'ROIProject.label', default: 'ROIProject'), params.id])}"
				redirect(action: "list")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'ROIProject.label', default: 'ROIProject'), params.id])}"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'ROIProject.label', default: 'ROIProject'), params.id])}"
			redirect(action: "list")
		}
	}
}
