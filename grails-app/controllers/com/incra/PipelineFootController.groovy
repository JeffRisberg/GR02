package com.incra

class PipelineFootController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [pipelineFootInstanceList: PipelineFoot.list(params), pipelineFootInstanceTotal: PipelineFoot.count()]
    }

    def create = {
        def pipelineFootInstance = new PipelineFoot()
        pipelineFootInstance.properties = params
        return [pipelineFootInstance: pipelineFootInstance]
    }

    def save = {
        def pipelineFootInstance = new PipelineFoot(params)
        if (pipelineFootInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'pipelineFoot.label', default: 'PipelineFoot'), pipelineFootInstance.id])}"
            redirect(action: "show", id: pipelineFootInstance.id)
        }
        else {
            render(view: "create", model: [pipelineFootInstance: pipelineFootInstance])
        }
    }

    def show = {
        def pipelineFootInstance = PipelineFoot.get(params.id)
        if (!pipelineFootInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'pipelineFoot.label', default: 'PipelineFoot'), params.id])}"
            redirect(action: "list")
        }
        else {
            [pipelineFootInstance: pipelineFootInstance]
        }
    }

    def edit = {
        def pipelineFootInstance = PipelineFoot.get(params.id)
        if (!pipelineFootInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'pipelineFoot.label', default: 'PipelineFoot'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [pipelineFootInstance: pipelineFootInstance]
        }
    }

    def update = {
        def pipelineFootInstance = PipelineFoot.get(params.id)
        if (pipelineFootInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (pipelineFootInstance.version > version) {
                    
                    pipelineFootInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'pipelineFoot.label', default: 'PipelineFoot')] as Object[], "Another user has updated this PipelineFoot while you were editing")
                    render(view: "edit", model: [pipelineFootInstance: pipelineFootInstance])
                    return
                }
            }
            pipelineFootInstance.properties = params
            if (!pipelineFootInstance.hasErrors() && pipelineFootInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'pipelineFoot.label', default: 'PipelineFoot'), pipelineFootInstance.id])}"
                redirect(action: "show", id: pipelineFootInstance.id)
            }
            else {
                render(view: "edit", model: [pipelineFootInstance: pipelineFootInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'pipelineFoot.label', default: 'PipelineFoot'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def pipelineFootInstance = PipelineFoot.get(params.id)
        if (pipelineFootInstance) {
            try {
                pipelineFootInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'pipelineFoot.label', default: 'PipelineFoot'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'pipelineFoot.label', default: 'PipelineFoot'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'pipelineFoot.label', default: 'PipelineFoot'), params.id])}"
            redirect(action: "list")
        }
    }
}
