package com.sim.producto

class SimProductoEtapaActividadController {
	
	def simProductoEtapaActividadService

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
        params.max = Math.min(params.max ? params.max.toInteger() : 10,  100)
        [simProductoEtapaActividadInstanceList: SimProductoEtapaActividad.list(params), simProductoEtapaActividadInstanceTotal: SimProductoEtapaActividad.count()]
    }

    def create = {
        def simProductoEtapaActividadInstance = new SimProductoEtapaActividad()
        simProductoEtapaActividadInstance.properties = params
        return [simProductoEtapaActividadInstance: simProductoEtapaActividadInstance]
    }

    def save = {
        def simProductoEtapaActividadInstance = new SimProductoEtapaActividad(params)
		try{
			//VALIDA QUE LA ETAPA-ACTIVIDAD Y ORDEN NO ESTEN REPETIDOS
			def validaEtapaActividad = validaEtapaActividadService(simProductoEtapaActividadInstance)
			
			if (!simProductoEtapaActividadInstance.hasErrors()&& validaEtapaActividad && simProductoEtapaActividadInstance.save() ) {
				flash.message = "simProductoEtapaActividad.created"
				flash.args = [simProductoEtapaActividadInstance.id]
				flash.defaultMessage = "SimProductoEtapaActividad ${simProductoEtapaActividadInstance.id} created"
				redirect(action: "show", id: simProductoEtapaActividadInstance.id)
			}
			else {
				render(view: "create", model: [simProductoEtapaActividadInstance: simProductoEtapaActividadInstance])
			}
			
		}catch(SimProductoEtapaActividadException pe){
			//INVESTIGAR QUE UTILIDAD TIENE EL TEXTO "ErrorProductoEtapaActividad"
			simProductoEtapaActividadInstance.errors.reject("ErrorProductoEtapaActividad",pe.mensaje)
			log.error "Failed:", pe
			render(view: "create", model: [simProductoEtapaActividadInstance: simProductoEtapaActividadInstance])
		}		
    }

    def show = {
        def simProductoEtapaActividadInstance = SimProductoEtapaActividad.get(params.id)
        if (!simProductoEtapaActividadInstance) {
            flash.message = "simProductoEtapaActividad.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "SimProductoEtapaActividad not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [simProductoEtapaActividadInstance: simProductoEtapaActividadInstance]
        }
    }

    def edit = {
        def simProductoEtapaActividadInstance = SimProductoEtapaActividad.get(params.id)
        if (!simProductoEtapaActividadInstance) {
            flash.message = "simProductoEtapaActividad.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "SimProductoEtapaActividad not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [simProductoEtapaActividadInstance: simProductoEtapaActividadInstance]
        }
    }

	//NO SE IMPLEMENTA LA FUNCIONALIDAD DE MODIFICAR
    def update = {
        def simProductoEtapaActividadInstance = SimProductoEtapaActividad.get(params.id)
        if (simProductoEtapaActividadInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (simProductoEtapaActividadInstance.version > version) {
                    
                    simProductoEtapaActividadInstance.errors.rejectValue("version", "simProductoEtapaActividad.optimistic.locking.failure", "Another user has updated this SimProductoEtapaActividad while you were editing")
                    render(view: "edit", model: [simProductoEtapaActividadInstance: simProductoEtapaActividadInstance])
                    return
                }
            }
            simProductoEtapaActividadInstance.properties = params
            if (!simProductoEtapaActividadInstance.hasErrors() && simProductoEtapaActividadInstance.save()) {
                flash.message = "simProductoEtapaActividad.updated"
                flash.args = [params.id]
                flash.defaultMessage = "SimProductoEtapaActividad ${params.id} updated"
                redirect(action: "show", id: simProductoEtapaActividadInstance.id)
            }
            else {
                render(view: "edit", model: [simProductoEtapaActividadInstance: simProductoEtapaActividadInstance])
            }
        }
        else {
            flash.message = "simProductoEtapaActividad.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "SimProductoEtapaActividad not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def simProductoEtapaActividadInstance = SimProductoEtapaActividad.get(params.id)
        if (simProductoEtapaActividadInstance) {
            try {
                simProductoEtapaActividadInstance.delete()
                flash.message = "simProductoEtapaActividad.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "SimProductoEtapaActividad ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "simProductoEtapaActividad.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "SimProductoEtapaActividad ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "simProductoEtapaActividad.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "SimProductoEtapaActividad not found with id ${params.id}"
            redirect(action: "list")
        }
    }
	
	def validaEtapaActividadService(SimProductoEtapaActividad simProductoEtapaActividadInstance){
		//VALIDA QUE LA ETAPA-ACTIVIDAD Y ORDEN NO ESTEN REPETIDOS
		return 	simProductoEtapaActividadService.validaEtapaActividad(simProductoEtapaActividadInstance.producto,
				simProductoEtapaActividadInstance.etapaActividad,simProductoEtapaActividadInstance.orden )
		
	}
	
}
