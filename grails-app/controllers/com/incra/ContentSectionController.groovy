package com.incra

class ContentSectionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [contentSectionInstanceList: ContentSection.list(params), contentSectionInstanceTotal: ContentSection.count()]
    }

    def create = {
        def contentSectionInstance = new ContentSection()
        contentSectionInstance.properties = params
        return [contentSectionInstance: contentSectionInstance]
    }

    def save = {
        def contentSectionInstance = new ContentSection(params)
        if (contentSectionInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'contentSection.label', default: 'ContentSection'), contentSectionInstance.id])}"
            redirect(action: "show", id: contentSectionInstance.id)
        }
        else {
            render(view: "create", model: [contentSectionInstance: contentSectionInstance])
        }
    }

    def show = {
        def contentSectionInstance = ContentSection.get(params.id)
        if (!contentSectionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'contentSection.label', default: 'ContentSection'), params.id])}"
            redirect(action: "list")
        }
        else {
            [contentSectionInstance: contentSectionInstance]
        }
    }

    def edit = {
        def contentSectionInstance = ContentSection.get(params.id)
        if (!contentSectionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'contentSection.label', default: 'ContentSection'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [contentSectionInstance: contentSectionInstance]
        }
    }

    def update = {
        def contentSectionInstance = ContentSection.get(params.id)
        if (contentSectionInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (contentSectionInstance.version > version) {
                    
                    contentSectionInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'contentSection.label', default: 'ContentSection')] as Object[], "Another user has updated this ContentSection while you were editing")
                    render(view: "edit", model: [contentSectionInstance: contentSectionInstance])
                    return
                }
            }
            contentSectionInstance.properties = params
            if (!contentSectionInstance.hasErrors() && contentSectionInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'contentSection.label', default: 'ContentSection'), contentSectionInstance.id])}"
                redirect(action: "show", id: contentSectionInstance.id)
            }
            else {
                render(view: "edit", model: [contentSectionInstance: contentSectionInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'contentSection.label', default: 'ContentSection'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def contentSectionInstance = ContentSection.get(params.id)
        if (contentSectionInstance) {
            try {
                contentSectionInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'contentSection.label', default: 'ContentSection'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'contentSection.label', default: 'ContentSection'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'contentSection.label', default: 'ContentSection'), params.id])}"
            redirect(action: "list")
        }
    }
}
