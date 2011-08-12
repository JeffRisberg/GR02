package com.incra

class ResourcePriceController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [resourcePriceInstanceList: ResourcePrice.list(params), resourcePriceInstanceTotal: ResourcePrice.count()]
    }

    def create = {
        def resourcePriceInstance = new ResourcePrice()
        resourcePriceInstance.properties = params
        return [resourcePriceInstance: resourcePriceInstance]
    }

    def save = {
	   def resourcePriceInstance = new ResourcePrice(params)
        if (resourcePriceInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'resourcePrice.label', default: 'ResourcePrice'), resourcePriceInstance.id])}"
            redirect(action: "show", id: resourcePriceInstance.id)
        }
        else {
            render(view: "create", model: [resourcePriceInstance: resourcePriceInstance])
        }
    }

    def show = {
        def resourcePriceInstance = ResourcePrice.get(params.id)
        if (!resourcePriceInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'resourcePrice.label', default: 'ResourcePrice'), params.id])}"
            redirect(action: "list")
        }
        else {
            [resourcePriceInstance: resourcePriceInstance]
        }
    }

    def edit = {
        def resourcePriceInstance = ResourcePrice.get(params.id)
        if (!resourcePriceInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'resourcePrice.label', default: 'ResourcePrice'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [resourcePriceInstance: resourcePriceInstance]
        }
    }

    def update = {
		println params;
        def resourcePriceInstance = ResourcePrice.get(params.id)
        if (resourcePriceInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (resourcePriceInstance.version > version) {
                    
                    resourcePriceInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'resourcePrice.label', default: 'ResourcePrice')] as Object[], "Another user has updated this ResourcePrice while you were editing")
                    render(view: "edit", model: [resourcePriceInstance: resourcePriceInstance])
                    return
                }
            }
            resourcePriceInstance.properties = params
            if (!resourcePriceInstance.hasErrors() && resourcePriceInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'resourcePrice.label', default: 'ResourcePrice'), resourcePriceInstance.id])}"
                redirect(action: "show", id: resourcePriceInstance.id)
            }
            else {
                render(view: "edit", model: [resourcePriceInstance: resourcePriceInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'resourcePrice.label', default: 'ResourcePrice'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def resourcePriceInstance = ResourcePrice.get(params.id)
        if (resourcePriceInstance) {
            try {
                resourcePriceInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'resourcePrice.label', default: 'ResourcePrice'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'resourcePrice.label', default: 'ResourcePrice'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'resourcePrice.label', default: 'ResourcePrice'), params.id])}"
            redirect(action: "list")
        }
    }
}
