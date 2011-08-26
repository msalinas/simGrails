package com.sim.empresa

class RsConfEmpresa {

	String claveEmpresa
	String nombreEmpresa
	Date   fechaCreacion

    static constraints = {
		claveEmpresa(size:5..20, unique: true, nullable: false,  blank: false)
		nombreEmpresa(size: 5..20, nullable: false, blank: false, validator: { nomEmpresa, rsConfEmpresa ->
			nomEmpresa != rsConfEmpresa.claveEmpresa})
		fechaCreacion()
    }

	static belongsTo = [ grupoEmpresa : RsConfGpoEmpresa ]

	String toString() {
		"${nombreEmpresa}"
	}


}
